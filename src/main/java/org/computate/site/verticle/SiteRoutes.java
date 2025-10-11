package org.computate.site.verticle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.search.response.solr.SolrResponse;
import org.computate.search.response.solr.SolrResponse.FacetField;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import org.computate.vertx.api.BaseApiServiceImpl;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.result.base.ComputateBaseResult;
import org.computate.vertx.search.list.SearchList;
import org.computate.vertx.verticle.EmailVerticle;
import org.computate.site.config.ConfigKeys;
import org.computate.site.model.course.CompanyCourse;
import org.computate.site.page.PageLayout;
import org.computate.site.page.SitePage;
import org.computate.site.request.SiteRequest;
import org.computate.site.user.SiteUser;
import org.computate.site.user.SiteUserEnUSApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import net.authorize.Environment;
import net.authorize.api.contract.v1.CustomerDataType;
import net.authorize.api.contract.v1.GetTransactionDetailsRequest;
import net.authorize.api.contract.v1.GetTransactionDetailsResponse;
import net.authorize.api.contract.v1.LineItemType;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.TransactionDetailsType;
import net.authorize.api.controller.GetTransactionDetailsController;
import net.authorize.api.controller.base.ApiOperationBase;

public class SiteRoutes {
  protected static final Logger LOG = LoggerFactory.getLogger(SiteRoutes.class);

  public static void routes(Vertx vertx, Router router, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, JsonObject config, WebClient webClient, Jinjava jinjava, SiteUserEnUSApiServiceImpl apiSiteUser) {

    router.get("/").handler(eventHandler -> {
      ServiceRequest serviceRequest = apiSiteUser.generateServiceRequest(eventHandler);
      apiSiteUser.user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_SiteUser, "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
        siteRequest.addScopes("GET");
        facetAll(siteRequest, router, oauth2AuthHandler, config, webClient, jinjava, apiSiteUser).onSuccess(facetResponse -> {
          searchFreeCourses(siteRequest, router, oauth2AuthHandler, config, webClient, jinjava, apiSiteUser).onSuccess(topCourses -> {
            searchPathToComputerEnlightenment(siteRequest, router, oauth2AuthHandler, config, webClient, jinjava, apiSiteUser).onSuccess(pathToComputerEnlightenment -> {
              try {
                PageLayout page = new PageLayout();
                MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
                siteRequest.setRequestHeaders(requestHeaders);
                page.setSiteRequest_(siteRequest);
                page.setServiceRequest(siteRequest.getServiceRequest());
                page.setWebClient(webClient);
                page.promiseDeepPageLayout(siteRequest).onSuccess(a -> {
                  try {
                    JsonObject ctx = ConfigKeys.getPageContext(config);
                    ctx.mergeIn(JsonObject.mapFrom(page));

                    FacetField facetClass = facetResponse.getFacetField("classSimpleName_docvalues_string");
                    ctx.put("facetClass", facetClass);
                    ctx.put("topCourses", topCourses);
                    ctx.put("pathToComputerEnlightenment", pathToComputerEnlightenment);

                    Path resourceTemplatePath = Path.of(config.getString(ConfigKeys.TEMPLATE_PATH), "/en-us/HomePage.htm");
                    String template = Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
                    String renderedTemplate = jinjava.render(template, ctx.getMap());
                    Buffer buffer = Buffer.buffer(renderedTemplate);
                    eventHandler.response().putHeader("Content-Type", "text/html");
                    eventHandler.end(buffer);
                  } catch(Exception ex) {
                    LOG.error(String.format("GET home page failed. "), ex);
                  }
                }).onFailure(ex -> {
                  LOG.error(String.format("GET home page failed. "), ex);
                });
              } catch(Exception ex) {
                LOG.error("Failed to load page. ", ex);
                eventHandler.fail(ex);
              }
            }).onFailure(ex -> {
              LOG.error(String.format("Search failed. "), new RuntimeException(ex));
            });
          }).onFailure(ex -> {
            LOG.error(String.format("Search failed. "), new RuntimeException(ex));
          });
        }).onFailure(ex -> {
          LOG.error(String.format("Search failed. "), new RuntimeException(ex));
        });
      }).onFailure(ex -> {
        if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
          try {
            eventHandler.redirect("/logout?redirect_uri=" + URLEncoder.encode("/", "UTF-8"));
          } catch(Exception ex2) {
            LOG.error(String.format("searchSiteUser failed. ", ex2));
            eventHandler.fail(ex2);
          }
        } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
          eventHandler.response().setStatusCode(401).setStatusMessage("UNAUTHORIZED")
              .send(Buffer.buffer().appendString(
                new JsonObject()
                  .put("errorCode", "401")
                  .put("errorMessage", "SSO Resource Permission check returned DENY")
                  .encodePrettily()
                )
              );
        } else {
          LOG.error(String.format("searchSiteUser failed. "), ex);
          eventHandler.fail(ex);
        }
      });
    });
    authorizeNet(vertx, router, oauth2AuthHandler, config, webClient, jinjava, apiSiteUser);
  }

  public static Future<Void> grantGithubTeamAccess(String githubTeam, String githubUsername, JsonObject config, WebClient webClient) {
    Promise<Void> promise = Promise.promise();
    try {
      String githubOrg = config.getString(ConfigKeys.GITHUB_ORG);
      String githubUri = String.format("/orgs/%s/teams/%s/memberships/%s", BaseApiServiceImpl.urlEncode(githubOrg), BaseApiServiceImpl.urlEncode(githubTeam), BaseApiServiceImpl.urlEncode(githubUsername));
      webClient.put(443, "api.github.com", githubUri).ssl(true)
          .putHeader("Accept", "application/vnd.github+json")
          .putHeader("X-GitHub-Api-Version", "2022-11-28")
          .putHeader("Authorization", String.format("Bearer %s", config.getString(ConfigKeys.GITHUB_API_TOKEN)))
          .sendJsonObject(new JsonObject().put("role", "member"))
          .expecting(HttpResponseExpectation.SC_OK)
          .onSuccess(memberResponse -> {
        LOG.info(String.format("Successfully granted user %s access to team %s in org %s in GitHub", githubUsername, githubTeam, githubOrg));
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("Failed to add user %s to team %s in org %s. ", githubUsername, githubTeam, githubOrg), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error("The GitHub team access failed. ");
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<Void> processAuthorizeItem(Vertx vertx, Router router, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, JsonObject config, WebClient webClient, Jinjava jinjava, SiteUserEnUSApiServiceImpl apiSiteUser, String transactionId, TransactionDetailsType transactionDetails, Message<Object> message, LineItemType item) {
    Promise<Void> promise = Promise.promise();
    try {
      String customerProfileId = transactionDetails.getProfile().getCustomerProfileId();
      if(customerProfileId != null) {
        LOG.info(String.format("Processing %s order for customerProfileId %s", item.getItemId(), customerProfileId));
        String itemId = item.getItemId();
        String itemName = item.getName();

        List<String> publicResources = Arrays.asList("CompanyEvent","CompanyCourse","CompanyProduct","CompanyService");
        SiteRequest siteRequest = new SiteRequest();
        siteRequest.setWebClient(webClient);
        siteRequest.setUserPrincipal(new JsonObject());
        siteRequest.setUser(null);
        siteRequest.setConfig(config);
        siteRequest.setServiceRequest(null);
        siteRequest.setSiteRequest_(siteRequest);
        siteRequest.initDeepForClass();
        siteRequest.setPublicRead(true);

        SearchList<ComputateBaseResult> searchList = new SearchList<ComputateBaseResult>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setSiteRequest_(siteRequest);
        searchList.fq(String.format("classSimpleName_docvalues_string:" + publicResources.stream().collect(Collectors.joining(" OR ", "(", ")"))));
        searchList.fq(String.format("pageId_docvalues_string:\"" + itemId + "\""));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          if(searchList.size() > 0) {
            ComputateBaseResult result = searchList.first();
            String pageId = (String)result.obtainForClass("pageId");
            String classSimpleName = (String)result.obtainForClass("classSimpleName");
            String groupName = String.format("%s-%s-GET", classSimpleName, pageId);
            String authAdminUsername = config.getString(ConfigKeys.AUTH_ADMIN_USERNAME);
            String authAdminPassword = config.getString(ConfigKeys.AUTH_ADMIN_PASSWORD);
            Integer authPort = Integer.parseInt(config.getString(ConfigKeys.AUTH_PORT));
            String authHostName = config.getString(ConfigKeys.AUTH_HOST_NAME);
            Boolean authSsl = Boolean.valueOf(config.getString(ConfigKeys.AUTH_SSL));
            String authRealm = config.getString(ConfigKeys.AUTH_REALM);
            webClient.post(authPort, authHostName, "/realms/master/protocol/openid-connect/token").ssl(authSsl)
                .sendForm(MultiMap.caseInsensitiveMultiMap()
                    .add("username", authAdminUsername)
                    .add("password", authAdminPassword)
                    .add("grant_type", "password")
                    .add("client_id", "admin-cli")
                    )
                .expecting(HttpResponseExpectation.SC_OK)
                    .onSuccess(tokenResponse -> {
              try {
                String authToken = tokenResponse.bodyAsJsonObject().getString("access_token");
                webClient.get(authPort, authHostName, String.format("/admin/realms/%s/groups?exact=false&global=true&first=0&max=1&search=%s", authRealm, URLEncoder.encode(groupName, "UTF-8"))).ssl(authSsl).putHeader("Authorization", String.format("Bearer %s", authToken))
                .send()
                .expecting(HttpResponseExpectation.SC_OK)
                .onSuccess(groupResponse -> {
                  try {
                    JsonArray groups = Optional.ofNullable(groupResponse.bodyAsJsonArray()).orElse(new JsonArray());
                    JsonObject group = groups.stream().findFirst().map(o -> (JsonObject)o).orElse(null);
                    if(group != null) {
                      String groupId = group.getString("id");
                      webClient.get(authPort, authHostName, String.format("/admin/realms/%s/users?q=", authRealm, URLEncoder.encode(String.format("customerProfileId:%s", customerProfileId), "UTF-8"))).ssl(authSsl).putHeader("Authorization", String.format("Bearer %s", authToken))
                      .send()
                      .expecting(HttpResponseExpectation.SC_OK)
                      .onSuccess(userResponse -> {
                        JsonArray users = Optional.ofNullable(userResponse.bodyAsJsonArray()).orElse(new JsonArray());
                        JsonObject user = users.stream().findFirst().map(o -> (JsonObject)o).orElse(null);
                        if(user != null) {
                          String userId = user.getString("id");
                          String userEmail = user.getString("email");
                          String userFullName = String.format("%s %s", user.getString("firstName"), user.getString("lastName"));
                          String userName = user.getString("username");
                          JsonArray userGroups = user.getJsonArray("groups");
                          // LOG.info(String.format("user %s group %s in groups: %s", customerProfileId, groupName, userGroups));
                          // if(!userGroups.contains(groupName)) {
                            webClient.put(authPort, authHostName, String.format("/admin/realms/%s/users/%s/groups/%s", authRealm, userId, groupId)).ssl(authSsl)
                                .putHeader("Authorization", String.format("Bearer %s", authToken))
                                .putHeader("Content-Type", "application/json")
                                .putHeader("Content-Length", "0")
                                .send()
                                .expecting(HttpResponseExpectation.SC_NO_CONTENT)
                                .onSuccess(groupUserResponse -> {
                              try {
                                LOG.info(String.format("Successfully added user %s to the group %s in Keycloak", userName, groupName));
                                grantGithubTeamAccess(itemId, userName, config, webClient).onSuccess(b -> {
                                  promise.complete();
                                }).onFailure(ex -> {
                                  promise.fail(ex);
                                });
                                // DeliveryOptions options = new DeliveryOptions();
                                // String siteName = config.getString(ComputateConfigKeys.SITE_NAME);
                                // String emailFrom = config.getString(ComputateConfigKeys.EMAIL_FROM);
                                // String customerId = customerProfileId;
                                // String emailTo = userEmail;
                                // String customerName = userName;
                                // Payment payment = null;
                                // if(emailTo == null && customerId == null) {
                                //   List<Tender> tenders = order.getTenders();
                                //   if(tenders != null) {
                                //     Tender tender = order.getTenders().get(0);
                                //     String paymentId = tender.getPaymentId();
                                //     PaymentsApi paymentsApi = squareClient.getPaymentsApi();
                                //     payment = paymentsApi.getPayment(paymentId).getPayment();
                                //     customerId = payment.getCustomerId();
                                //   }
                                // }
                                // if(emailTo == null && customerId != null) {
                                //   Customer customer = customersApi.retrieveCustomer(customerId).getCustomer();
                                //   emailTo = customer.getEmailAddress();
                                //   customerName = String.format("%s %s", customer.getGivenName(), customer.getFamilyName());
                                // } else if(payment != null) {
                                //   emailTo = payment.getBuyerEmailAddress();
                                // }

                                // String subject = String.format("Hello %s! Thank you for ordering the %s from %s! ", customerName, itemI, siteName);
                                // String emailTemplate = (String)result.obtainForClass("emailTemplate");
                                // BigDecimal total = new BigDecimal(order.getTotalMoney().getAmount()).divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
                                // BigDecimal totalTax = new BigDecimal(order.getTotalTaxMoney().getAmount()).divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
                                // BigDecimal netAmountDue = new BigDecimal(order.getNetAmountDueMoney().getAmount()).divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
                                // options.addHeader(EmailVerticle.MAIL_HEADER_SUBJECT, subject);
                                // options.addHeader(EmailVerticle.MAIL_HEADER_FROM, emailFrom);
                                // options.addHeader(EmailVerticle.MAIL_HEADER_TO, emailTo);
                                // options.addHeader(EmailVerticle.MAIL_HEADER_TEMPLATE, emailTemplate);

                                // ZoneId zoneId = ZoneId.of(config.getString(ComputateConfigKeys.SITE_ZONE));
                                // ZonedDateTime createdAt = ZonedDateTime.parse(order.getCreatedAt(), ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER);
                                // Locale locale = Locale.forLanguageTag(config.getString(ComputateConfigKeys.SITE_LOCALE));
                                // DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE d MMM uuuu h:mm a VV", locale);
                                // JsonObject body = new JsonObject();
                                // body.put(ComputateConfigKeys.SITE_BASE_URL, config.getString(ComputateConfigKeys.SITE_BASE_URL));
                                // body.put("siteName", siteName);
                                // body.put("customerProfileId", customerProfileId);
                                // body.put("orderId", order.getId());
                                // body.put("subject", subject);
                                // body.put("emailTo", emailTo);
                                // body.put("customerName", customerName);
                                // body.put("result", JsonObject.mapFrom(result));
                                // body.put("totalMoney", NumberFormat.getCurrencyInstance(locale).format(total));
                                // body.put("totalTax", NumberFormat.getCurrencyInstance(locale).format(totalTax));
                                // body.put("netAmountDue", NumberFormat.getCurrencyInstance(locale).format(netAmountDue));

                                // String createdAtStr = dateFormat.format(createdAt.withZoneSameInstant(zoneId));
                                // body.put("createdAt", createdAtStr);

                                // vertx.eventBus().request(EmailVerticle.MAIL_EVENTBUS_ADDRESS, body.encode(), options).onSuccess(b -> {
                                //   LOG.info(String.format("Successfully granted %s access to %s", customerProfileId, name));
                                //   promise.complete();
                                // }).onFailure(ex -> {
                                //   LOG.error(String.format("Failed to send email to %s. ", userEmail), ex);
                                //   promise.fail(ex);
                                // });
                              } catch(Throwable ex) {
                                LOG.error("Failed to process authorize.net webook while querying customer. ", ex);
                                promise.fail(ex);
                              }
                            }).onFailure(ex -> {
                              LOG.error("Failed to process authorize.net webook while adding user to group. ", ex);
                              promise.fail(ex);
                            });
                          // } else {
                          //   LOG.info(String.format("User %s already in group %s", customerProfileId, groupName));
                          // }
                        } else {
                          Throwable ex = new RuntimeException(String.format("Failed to find user %s. ", customerProfileId));
                          LOG.error(ex.getMessage(), ex);
                          promise.fail(ex);
                        }
                      }).onFailure(ex -> {
                        LOG.error("Failed to process authorize.net webook while querying user. ", ex);
                        promise.fail(ex);
                      });
                    } else {
                      Throwable ex = new RuntimeException("Failed to find group. ");
                      LOG.error(ex.getMessage(), ex);
                      promise.fail(ex);
                    }
                  } catch(Throwable ex) {
                    LOG.error("Failed to process authorize.net webook while querying group. ", ex);
                    promise.fail(ex);
                  }
                }).onFailure(ex -> {
                  LOG.error("Failed to process authorize.net webook while querying group. ", ex);
                  promise.fail(ex);
                });
              } catch(Throwable ex) {
                LOG.error("Failed to process authorize.net webook while querying group. ", ex);
                promise.fail(ex);
              }
            }).onFailure(ex -> {
              LOG.error("Failed to process authorize.net webook. ", ex);
              promise.fail(ex);
            });
          } else {
            LOG.warn(String.format("Item not found with name %s. ", itemName));
            promise.complete();
          }
        }).onFailure(ex -> {
          LOG.error("Failed to process authorize.net webook. ", ex);
          promise.fail(ex);
        });
      } else {
        LOG.warn(String.format("Order %s missing customerProfileId", transactionId));
        promise.complete();
      }
    } catch(Exception ex) {
      LOG.error("The authorize.net item failed to process.");
      promise.fail(ex);
    }
    return promise.future();
  }

  public static void authorizeNet(Vertx vertx, Router router, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, JsonObject config, WebClient webClient, Jinjava jinjava, SiteUserEnUSApiServiceImpl apiSiteUser) {
      vertx.eventBus().consumer("authorize-order", message -> {
        try {
          JsonObject paymentBody = ((JsonObject)message.body()).getJsonObject("context").getJsonObject("params").getJsonObject("body");
          String notificationId = paymentBody.getString("notificationId");
          String eventType = paymentBody.getString("eventType");
          JsonObject payload = paymentBody.getJsonObject("payload");
          String transactionId = payload.getString("id");
          LOG.info(String.format("An authorize.net payment received: %s", paymentBody.encode()));
          if("net.authorize.payment.authcapture.created".equals(eventType) && payload != null) {
            String orderLock = String.format("authorize-order-", notificationId);
            SharedData sharedData = vertx.sharedData();
            sharedData.getLockWithTimeout(orderLock, Long.parseLong(config.getString(ConfigKeys.AUTHORIZE_NET_WEBHOOK_TIMEOUT_MILLIS, "3000"))).onSuccess(lock -> {
              try {
                String authorizeEnvironment = config.getString(ConfigKeys.AUTHORIZE_NET_ENVIRONMENT);
                String authorizeApiLoginId = config.getString(ConfigKeys.AUTHORIZE_NET_API_LOGIN_ID);
                String authorizeTransactionKey = config.getString(ConfigKeys.AUTHORIZE_NET_TRANSACTION_KEY);

                MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
                merchantAuthenticationType.setName(authorizeApiLoginId);
                merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
                ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
                ApiOperationBase.setEnvironment(Environment.valueOf(authorizeEnvironment)); 

                GetTransactionDetailsRequest getRequest = new GetTransactionDetailsRequest();
                getRequest.setMerchantAuthentication(merchantAuthenticationType);
                getRequest.setTransId(transactionId);
                GetTransactionDetailsController controller = new GetTransactionDetailsController(getRequest);
                controller.execute();
                GetTransactionDetailsResponse response = controller.getApiResponse();
                TransactionDetailsType transactionDetails = Optional.ofNullable(response).map(r -> r.getTransaction()).orElse(null);
                if (transactionDetails != null) {
                  String customerProfileId = transactionDetails.getProfile().getCustomerProfileId();
                  LOG.info(String.format("An authorize.net payment received for customerProfileId %s: %s", customerProfileId, paymentBody.encode()));
                  transactionDetails.getOrder().getPurchaserCode();

                  List<Future<String>> futures = new ArrayList<>();
                  for(LineItemType item : transactionDetails.getLineItems().getLineItem()) {
                    futures.add(Future.future(promise1 -> {
                      processAuthorizeItem(vertx, router, oauth2AuthHandler, config, webClient, jinjava, apiSiteUser, transactionId, transactionDetails, message, item).onSuccess(a -> {
                        promise1.complete();
                      }).onFailure(ex -> {
                        promise1.fail(ex);
                      });
                    }));
                  }
                  Future.all(futures).onSuccess(b -> {
                    vertx.setTimer(Integer.parseInt(config.getString(ConfigKeys.AUTHORIZE_NET_WEBHOOK_UNLOCK_MILLIS, "60000")), tid -> {
                      lock.release();
                      LOG.info(String.format("The notificationId %s lock was released", notificationId));
                    });
                  }).onFailure(ex -> {
                    lock.release();
                    message.fail(400, ex.getMessage());
                  });
                }
              } catch(Throwable ex) {
                lock.release();
                LOG.error("Failed to process authorize.net webook. ", ex);
                message.fail(400, ex.getMessage());
              }
            }).onFailure(ex -> {
              LOG.warn(String.format("The notificationId %s did not obtain a lock", notificationId));
            });
          } else if(payload == null) {
            LOG.info(String.format("The notification %s has a null payload", notificationId));
          } else {
            LOG.info(String.format("The notification %s is the wrong event type %s", notificationId, eventType));
          }
        } catch(Throwable ex) {
          LOG.error("Failed to process authorize.net webook. ", ex);
          message.fail(400, ex.getMessage());
        }
      });
      if(Boolean.valueOf(config.getString(ConfigKeys.ENABLE_AUTHORIZE_NET))) {
        router.post("/authorize/webhook").handler(BodyHandler.create()).handler(handler -> {
          try {
            String bodyStr = handler.body().asString();
            String signature = Optional.ofNullable(handler.request().headers().get("X-ANET-Signature")).map(s -> StringUtils.substringAfter(s, "sha512=")).orElse(null);
            String authorizeSignatureKey = config.getString(ConfigKeys.AUTHORIZE_NET_SIGNATURE_KEY);
            HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, authorizeSignatureKey);
            String generatedSignature = hmacUtils.hmacHex(bodyStr);
            // if(generatedSignature.equalsIgnoreCase(signature)) {
              JsonObject body = handler.body().asJsonObject();
              JsonObject params = new JsonObject();
              params.put("body", body);
              params.put("path", new JsonObject());
              params.put("cookie", new JsonObject());
              params.put("header", new JsonObject());
              params.put("form", new JsonObject());
              params.put("query", new JsonObject());
              JsonObject context = new JsonObject().put("params", params).put("user", null);
              JsonObject json = new JsonObject().put("context", context);
              // vertx.eventBus().publish("authorize-order", json, new DeliveryOptions().addHeader("X-ANET-Signature", signature));
              vertx.eventBus().publish("authorize-order", json, new DeliveryOptions());
              handler.response().putHeader("Content-Type", "application/json");
              handler.end(new JsonObject().toBuffer());
            // } else {
            //   LOG.warn(String.format("Invalid authorize.net webhook with header X-ANET-Signature: %s\n%s", signature, bodyStr));
            //   handler.response().putHeader("Content-Type", "application/json");
            //   handler.end(new JsonObject().toBuffer());
            // }
          } catch(Throwable ex) {
            LOG.error("Failed to process authorize.net webook. ", ex);
            handler.fail(ex);
          }
        });
      }
  }

  public static Future<SearchList<CompanyCourse>> searchFreeCourses(SiteRequest siteRequest, Router router, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, JsonObject config, WebClient webClient, Jinjava jinjava, SiteUserEnUSApiServiceImpl apiSiteUser) {
    Promise<SearchList<CompanyCourse>> promise = Promise.promise();
    try {
      SearchList<CompanyCourse> searchList = new SearchList<CompanyCourse>();
      searchList.setStore(true);
      searchList.q("*:*");
      searchList.sort("created_docvalues_date", "desc");
      searchList.fq("price_docvalues_double:0.00");
      searchList.setC(CompanyCourse.class);
      searchList.setSiteRequest_(siteRequest);
      searchList.promiseDeepForClass(siteRequest).onSuccess(searchList2 -> {
        promise.complete(searchList);
      }).onFailure(ex -> {
        LOG.error(String.format("searchCompanyCourse failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("searchCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<SearchList<SitePage>> searchPathToComputerEnlightenment(SiteRequest siteRequest, Router router, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, JsonObject config, WebClient webClient, Jinjava jinjava, SiteUserEnUSApiServiceImpl apiSiteUser) {
    Promise<SearchList<SitePage>> promise = Promise.promise();
    try {
      SearchList<SitePage> searchList = new SearchList<SitePage>();
      searchList.setStore(true);
      searchList.q("*:*");
      searchList.sort("created_docvalues_date", "desc");
      searchList.fq("labels_docvalues_strings:path-to-computer-enlightenment");
      searchList.setC(SitePage.class);
      searchList.setSiteRequest_(siteRequest);
      searchList.promiseDeepForClass(siteRequest).onSuccess(searchList2 -> {
        promise.complete(searchList);
      }).onFailure(ex -> {
        LOG.error(String.format("searchSitePage failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("searchSitePage failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<SolrResponse> facetAll(SiteRequest siteRequest, Router router, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, JsonObject config, WebClient webClient, Jinjava jinjava, SiteUserEnUSApiServiceImpl apiSiteUser) {
    Promise<SolrResponse> promise = Promise.promise();
    try {
      String solrUsername = config.getString(ComputateConfigKeys.SOLR_USERNAME);
      String solrPassword = config.getString(ComputateConfigKeys.SOLR_PASSWORD);
      String solrHostName = config.getString(ComputateConfigKeys.SOLR_HOST_NAME);
      Integer solrPort = Integer.parseInt(config.getString(ComputateConfigKeys.SOLR_PORT));
      String solrCollection = config.getString(ComputateConfigKeys.SOLR_COLLECTION);
      Boolean solrSsl = Boolean.parseBoolean(config.getString(ComputateConfigKeys.SOLR_SSL));
      String facetUri = String.format(
          "/solr/%s/select%s%s%s"
          , solrCollection
          , "?rows=0&q="
          , URLEncoder.encode("*:*", "UTF-8")
          , "&facet=true&facet.mincount=1&facet.field=classSimpleName_docvalues_string"
          );
      webClient.get(solrPort, solrHostName, facetUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).send().onSuccess(a -> {
        try {
          promise.complete(a.bodyAsJson(SolrResponse.class));
        } catch(Exception ex) {
          LOG.error("Failed to load page. ", ex);
          promise.fail(ex);
        }
      }).onFailure(ex -> {
        LOG.error(String.format("Search failed. "), new RuntimeException(ex));
      });
    } catch(Exception ex) {
      LOG.error("Failed to load page. ", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<Void> kafkaConsumer(Vertx vertx, KafkaConsumer<String, String> consumer, JsonObject config) {
    Promise<Void> promise = Promise.promise();
    try {
      promise.complete();
    } catch(Exception ex) {
      LOG.error("Unable to configure Kafka consumers. ", ex);
      promise.fail(ex);
    }

    return promise.future();
  }
}
