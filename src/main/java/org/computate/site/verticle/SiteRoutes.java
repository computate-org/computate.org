package org.computate.site.verticle;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.search.response.solr.SolrResponse;
import org.computate.search.response.solr.SolrResponse.FacetField;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.search.list.SearchList;
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

  public static void authorizeNet(Vertx vertx, Router router, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, JsonObject config, WebClient webClient, Jinjava jinjava, SiteUserEnUSApiServiceImpl apiSiteUser) {
      vertx.eventBus().consumer("authorize-order", message -> {
        try {
          JsonObject paymentBody = ((JsonObject)message.body()).getJsonObject("context").getJsonObject("params").getJsonObject("body");
          String notificationId = paymentBody.getString("notificationId");
          String eventType = paymentBody.getString("eventType");
          String eventDate = paymentBody.getString("eventDate");
          String webhookId = paymentBody.getString("webhookId");
          JsonObject payload = paymentBody.getJsonObject("payload");
          Long responseCode = payload.getLong("responseCode");
          String merchantReferenceId = payload.getString("merchantReferenceId");
          String authCode = payload.getString("authCode");
          String avsResponse = payload.getString("avsResponse");
          BigDecimal authAmount = Optional.ofNullable(payload.getString("authAmount")).map(a -> new BigDecimal(a)).orElse(null);
          String entityName = payload.getString("entityName");
          String id = payload.getString("id");
          LOG.info(String.format("An authorize.net payment received: %s", paymentBody.encodePrettily()));
          LOG.info(String.format("authorize.net payment received: %s", paymentBody.encodePrettily()));
          // if("net.authorize.payment.authcapture.created".equals(eventType)) {
          //   String orderLock = String.format("authorize-order-", notificationId);
          //   SharedData sharedData = vertx.sharedData();
          //   sharedData.getLockWithTimeout(orderLock, config.getLong(ConfigKeys.SQUARE_WEBHOOK_TIMEOUT_MILLIS, 3000L)).onSuccess(lock -> {
          //     try {
          //       String squareSignatureKey = config.getString(ConfigKeys.SQUARE_SIGNATURE_KEY);
          //       String squareNotificationUrl = config.getString(ConfigKeys.SQUARE_NOTIFICATION_URL);
          //       if(squareSignatureKey != null && squareNotificationUrl != null) {
          //         Boolean isFromSquare = WebhooksHelper.isValidWebhookEventSignature(paymentBody.encode(), signature, squareSignatureKey, squareNotificationUrl);
          //         if(isFromSquare) {
          //           OrdersApi ordersApi = squareClient.getOrdersApi();
          //           CustomersApi customersApi = squareClient.getCustomersApi();
          //           RetrieveOrderResponse orderResponse = ordersApi.retrieveOrder(notificationId);
          //           Order order = orderResponse.getOrder();
          //           String state2 = state;
          //           String notificationId2 = notificationId;

          //           List<Future<String>> futures = new ArrayList<>();
          //           for(OrderLineItem item : order.getLineItems()) {
          //             futures.add(Future.future(promise1 -> {
          //               processSquareItem(customersApi, message, orderBody, order, notificationId2, state2, item).onSuccess(a -> {
          //                 promise1.complete();
          //               }).onFailure(ex -> {
          //                 promise1.fail(ex);
          //               });
          //             }));
          //           }
          //           Future.all(futures).onSuccess(b -> {
          //             vertx.setTimer(config().getInteger(ConfigKeys.SQUARE_WEBHOOK_UNLOCK_MILLIS, 60000), tid -> {
          //               lock.release();
          //               LOG.info(String.format("The notificationId %s lock was released", notificationId));
          //             });
          //           }).onFailure(ex -> {
          //             message.fail(400, ex.getMessage());
          //           });
          //         } else {
          //           Throwable ex = new RuntimeException("Webhook is not from Square. ");
          //           LOG.error("Webhook is not from Square. ", ex);
          //           message.fail(400, ex.getMessage());
          //         }
          //       } else {
          //         Throwable ex = new RuntimeException("Missing Square Signature Key and Notification URL. ");
          //         LOG.error("Missing Square Signature Key and Notification URL. ", ex);
          //         message.fail(400, ex.getMessage());
          //       }
          //     } catch(Throwable ex) {
          //       LOG.error("Failed to process square webook. ", ex);
          //       message.fail(400, ex.getMessage());
          //     }
          //   }).onFailure(ex -> {
          //     LOG.warn(String.format("The notificationId %s did not obtain a lock", notificationId));
          //   });
          // } else {
          //   LOG.info(String.format("The notification %s is the wrong event type %s", notificationId, eventType));
          // }
        } catch(Throwable ex) {
          LOG.error("Failed to process square webook. ", ex);
          message.fail(400, ex.getMessage());
        }
      });
      if(Boolean.valueOf(config.getString(ConfigKeys.ENABLE_AUTHORIZE_NET))) {
        router.post("/authorize/webhook").handler(BodyHandler.create()).handler(handler -> {
          try {
            String bodyStr = handler.body().asString();
            String signature = Optional.ofNullable(handler.request().headers().get("X-ANET-Signature")).map(s -> StringUtils.substringAfter(s, "sha512=")).orElse(null);
            String authorizeSignatureKey = config.getString(ConfigKeys.AUTHORIZE_NET_SIGNATURE_KEY);
            String authorizePublicClientKey = config.getString(ConfigKeys.AUTHORIZE_NET_PUBLIC_CLIENT_KEY);
            HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, authorizeSignatureKey);
            String generatedSignature = hmacUtils.hmacHex(bodyStr);
            if(generatedSignature.equalsIgnoreCase(signature)) {
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
              vertx.eventBus().publish("authorize-order", json, new DeliveryOptions().addHeader("X-ANET-Signature", signature));
            } else {
              LOG.warn(String.format("Invalid authorize.net webhook with header X-ANET-Signature: %s\n%s", signature, bodyStr));
            }
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
