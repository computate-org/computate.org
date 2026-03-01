package org.computate.site.model.product;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.site.config.ConfigKeys;
import org.computate.site.request.SiteRequest;
import org.computate.site.user.SiteUser;
import org.computate.site.user.SiteUserEnUSApiServiceImpl;
import org.computate.vertx.api.ApiRequest;
import org.computate.vertx.api.BaseApiServiceImpl;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.result.base.ComputateBaseResult;
import org.computate.vertx.search.list.SearchList;
import org.computate.vertx.verticle.EmailVerticle;

import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfLineItem;
import net.authorize.api.contract.v1.ArrayOfSetting;
import net.authorize.api.contract.v1.CreateCustomerProfileRequest;
import net.authorize.api.contract.v1.CreateCustomerProfileResponse;
import net.authorize.api.contract.v1.CustomerProfilePaymentType;
import net.authorize.api.contract.v1.CustomerProfileType;
import net.authorize.api.contract.v1.ExtendedAmountType;
import net.authorize.api.contract.v1.GetHostedPaymentPageRequest;
import net.authorize.api.contract.v1.GetHostedPaymentPageResponse;
import net.authorize.api.contract.v1.GetHostedProfilePageRequest;
import net.authorize.api.contract.v1.GetHostedProfilePageResponse;
import net.authorize.api.contract.v1.LineItemType;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.OrderType;
import net.authorize.api.contract.v1.MessagesType.Message;
import net.authorize.api.contract.v1.SettingType;
import net.authorize.api.contract.v1.TransactionRequestType;
import net.authorize.api.contract.v1.TransactionTypeEnum;
import net.authorize.api.controller.CreateCustomerProfileController;
import net.authorize.api.controller.GetHostedPaymentPageController;
import net.authorize.api.controller.GetHostedProfilePageController;
import net.authorize.api.controller.GetTransactionListForCustomerController;
import net.authorize.api.controller.base.ApiOperationBase;

/**
 * Translate: false
 **/
public class CompanyProductEnUSApiServiceImpl extends CompanyProductEnUSGenApiServiceImpl {

  @Override
  public void userDefine(Promise<Boolean> promise, ComputateSiteRequest siteRequest, JsonObject jsonObject, Boolean patch) {
    try {
      SiteUser siteUser = siteRequest.getSiteUser_(null);
      JsonObject attributes = siteRequest.getUser().attributes();
      JsonObject accessToken = attributes.getJsonObject("accessToken");
      String authorizeApiLoginId = config.getString(ConfigKeys.AUTHORIZE_NET_API_LOGIN_ID);
      String authorizeTransactionKey = config.getString(ConfigKeys.AUTHORIZE_NET_TRANSACTION_KEY);
      String authorizeEnvironment = config.getString(ConfigKeys.AUTHORIZE_NET_ENVIRONMENT);

      if(Optional.ofNullable(siteRequest.getServiceRequest()).map(serviceRequest -> serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() == 0L
          && StringUtils.isNotBlank(authorizeEnvironment) && authorizeApiLoginId != null && authorizeTransactionKey != null) {
        String customerProfileId1 = Optional.ofNullable(siteRequest.getSiteUser_(SiteUser.class).getCustomerProfileId()).orElse(accessToken.getString("customerProfileId"));
  
        if(customerProfileId1 == null) {
          String customerProfileId2 = customerProfileId1;
          MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
          merchantAuthenticationType.setName(authorizeApiLoginId);
          merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
          ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
          
          CreateCustomerProfileRequest createCustomerProfileRequest = new CreateCustomerProfileRequest();
          createCustomerProfileRequest.setMerchantAuthentication(merchantAuthenticationType);
          CustomerProfileType profile = new CustomerProfileType();
          profile.setEmail(siteUser.getUserEmail());
          profile.setDescription(siteUser.getUserId());
          profile.setMerchantCustomerId(StringUtils.substring(siteUser.getUserFullName(), 0, 20));
          createCustomerProfileRequest.setProfile(profile);
    
          CreateCustomerProfileController controller = new CreateCustomerProfileController(createCustomerProfileRequest);
          GetTransactionListForCustomerController.setEnvironment(Environment.valueOf(authorizeEnvironment));
          controller.execute();
          if(controller.getErrorResponse() != null)
            throw new RuntimeException(controller.getResults().toString());
          CreateCustomerProfileResponse response = controller.getApiResponse();
          if(MessageTypeEnum.ERROR.equals(response.getMessages().getResultCode())) {
            String message = response.getMessages().getMessage().stream().findFirst().map(m -> m.getText()).orElse("");
            Matcher matcher = Pattern.compile("A duplicate record with ID (\\d+) already exists.").matcher(message);
            if(matcher.find()) {
              customerProfileId2 = matcher.group(1);
            }
            else {
              LOG.error(response.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("CreateCustomerProfileRequest failed. "));
            }
          }
          else {
            customerProfileId2 = response.getCustomerProfileId();
          }
          siteUser.setCustomerProfileId(customerProfileId2);

          String customerProfileId = customerProfileId2;
          String authAdminUsername = config.getString(ComputateConfigKeys.AUTH_ADMIN_USERNAME);
          String authAdminPassword = config.getString(ComputateConfigKeys.AUTH_ADMIN_PASSWORD);
          Integer authPort = Integer.parseInt(config.getString(ComputateConfigKeys.AUTH_PORT));
          String authHostName = config.getString(ComputateConfigKeys.AUTH_HOST_NAME);
          Boolean authSsl = Boolean.parseBoolean(config.getString(ComputateConfigKeys.AUTH_SSL));
          String authRealm = config.getString(ComputateConfigKeys.AUTH_REALM);
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
              webClient.get(authPort, authHostName
                  , String.format("/admin/realms/%s/users?exact=true&first=0&max=1&search=%s"
                  , authRealm
                  , URLEncoder.encode(siteRequest.getUserName(), "UTF-8")
                  )).ssl(authSsl).putHeader("Authorization", String.format("Bearer %s", authToken))
              .send()
              .expecting(HttpResponseExpectation.SC_OK)
              .onSuccess(userResponse -> {
                try {
                  JsonArray users = userResponse.bodyAsJsonArray();
                  JsonObject userObject = users.stream().findFirst().map(o -> (JsonObject)o).orElse(null);
                  if(userObject != null) {
                    String userId = userObject.getString("id");
                    JsonObject newUserObject = userObject.copy();
                    JsonObject newAttibutes = Optional.ofNullable(newUserObject.getJsonObject("attributes")).map(a -> a.copy()).orElse(new JsonObject());
                    newAttibutes.put("customerProfileId", new JsonArray().add(customerProfileId));
                    newUserObject.put("attributes", newAttibutes);
                    webClient.put(authPort, authHostName
                        , String.format("/admin/realms/%s/users/%s"
                        , authRealm
                        , URLEncoder.encode(userId, "UTF-8")
                        )).ssl(authSsl).putHeader("Authorization", String.format("Bearer %s", authToken))
                    .sendJsonObject(newUserObject)
                    .expecting(HttpResponseExpectation.SC_NO_CONTENT)
                    .onSuccess(groupResponse -> {
                      promise.complete(true);
                    }).onFailure(ex -> {
                      LOG.error(String.format("Failed to update customerProfileId attribute for user: %s", userId), ex);
                      promise.fail(ex);
                    });
                  } else {
                    Exception ex = new RuntimeException(String.format("Failed to prepare query to update customerProfileId for user: %s", userResponse.bodyAsJsonObject().getString("id")));
                    LOG.error(ex.getMessage(), ex);
                    promise.fail(ex);
                  }
                } catch(Throwable ex) {
                  LOG.error(String.format("Failed to prepare query to update customerProfileId for user: %s", userResponse.bodyAsJsonObject().getString("id")), ex);
                  promise.fail(ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("Failed to query user by id: %s", siteRequest.getUserName()), ex);
                LOG.error("Failed to render page. ", ex);
                promise.fail(ex);
              });
            } catch(Throwable ex) {
              LOG.error(String.format("Failed preparing to query user by id: %s", siteRequest.getUserName()), ex);
              promise.fail(ex);
            }
          }).onFailure(ex -> {
            LOG.error("Failed to query admin access token. ", ex);
            promise.fail(ex);
          });
        }
        else {
          promise.complete(false);
        }
      }
      else {
        promise.complete(false);
      }
    } catch(Throwable ex) {
    }
  }

  @Override
  public void patchpayCompanyProduct(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("patchpayCompanyProduct started. "));
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
        searchCompanyProductList(siteRequest, true, false, true, "PATCH").onSuccess(listCompanyProduct -> {
          try {
            ApiRequest apiRequest = new ApiRequest();
            apiRequest.setRows(listCompanyProduct.getRequest().getRows());
            apiRequest.setNumFound(listCompanyProduct.getResponse().getResponse().getNumFound());
            apiRequest.setNumPATCH(0L);
            apiRequest.initDeepApiRequest(siteRequest);
            siteRequest.setApiRequest_(apiRequest);
            if(apiRequest.getNumFound() == 1L)
              apiRequest.setOriginal(listCompanyProduct.first());
            apiRequest.setId(Optional.ofNullable(listCompanyProduct.first()).map(o2 -> o2.getPageId().toString()).orElse(null));
            eventBus.publish("websocketCompanyProduct", JsonObject.mapFrom(apiRequest).toString());

            listPATCHPayCompanyProductModified(apiRequest, listCompanyProduct).onSuccess(response -> {
              LOG.debug(String.format("patchpayCompanyProduct succeeded. "));
              eventHandler.handle(Future.succeededFuture(response));
            }).onFailure(ex -> {
              LOG.error(String.format("patchpayCompanyProduct failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
          } catch(Exception ex) {
            LOG.error(String.format("patchpayCompanyProduct failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("patchpayCompanyProduct failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchpayCompanyProduct failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("patchpayCompanyProduct failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("patchpayCompanyProduct failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> listPATCHPayCompanyProductModified(ApiRequest apiRequest, SearchList<CompanyProduct> listCompanyProduct) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listCompanyProduct.getSiteRequest_(SiteRequest.class);
      if(listCompanyProduct.getSize() > 1) {
        throw new RuntimeException("Should not be patching more than one result in a PATCHPay request");
      } else if(listCompanyProduct.getSize() == 0) {
        throw new RuntimeException("No result found in a PATCHPay request");
      } else {
        CompanyProduct o = listCompanyProduct.first();
        SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
        siteRequest2.setScopes(siteRequest.getScopes());
        o.setSiteRequest_(siteRequest2);
        siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
        JsonObject jsonObject = JsonObject.mapFrom(o);
        CompanyProduct o2 = jsonObject.mapTo(CompanyProduct.class);
        o2.setSiteRequest_(siteRequest2);
        patchpayCompanyProductFutureModified(o2, false).onSuccess(json -> {
          promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
        }).onFailure(ex -> {
          LOG.error(String.format("listPATCHPayCompanyProduct failed. "), ex);
          promise.tryFail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Failed to list PATCHPay request", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<JsonObject> patchpayCompanyProductFutureModified(CompanyProduct o, Boolean inheritPrimaryKey) {
    Promise<JsonObject> promise = Promise.promise();

    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      JsonObject requestBody = siteRequest.getJsonObject();
      JsonObject responseBody = new JsonObject();
      JsonObject createTransactionRequest = requestBody.getJsonObject("createTransactionRequest");

      String state = requestBody.getString("state");
      BigDecimal total = o.getPrice().setScale(2, RoundingMode.HALF_UP);
      BigDecimal netAmountDue = total;
      BigDecimal itemPrice;
      BigDecimal totalTax;
      if(StringUtils.equalsIgnoreCase(state, "Utah") || StringUtils.equalsIgnoreCase(state, "UT")) {
        BigDecimal calculatedTaxRate = new BigDecimal(0.0725).setScale(4, RoundingMode.HALF_UP);
        totalTax = total.subtract(total.divide(calculatedTaxRate.add(BigDecimal.ONE), 2, RoundingMode.HALF_DOWN));
        responseBody.put("calculatedTaxRate", calculatedTaxRate.toString());
        responseBody.put("calculatedTaxAmount", totalTax.toString());
        responseBody.put("taxExempt", false);
        itemPrice = total.subtract(totalTax);
      } else {
        totalTax = BigDecimal.ZERO.setScale(2);
        responseBody.put("taxExempt", true);
        itemPrice = total;
      }

      if(createTransactionRequest != null) {
        JsonObject transactionRequest = createTransactionRequest.getJsonObject("transactionRequest");
        BigDecimal requestAmount = new BigDecimal(transactionRequest.getString("amount")).setScale(2, RoundingMode.HALF_UP);
        if(!total.equals(requestAmount)) {
          throw new RuntimeException(String.format("%s product price %s does not match request price %s", o.getName(), total, requestAmount));
        } else {
          responseBody = new JsonObject();
          JsonObject createTransactionRequest2 = new JsonObject();
          createTransactionRequest2.put("merchantAuthentication", new JsonObject()
            .put("name", config.getString(ConfigKeys.AUTHORIZE_NET_API_LOGIN_ID))
            .put("transactionKey", config.getString(ConfigKeys.AUTHORIZE_NET_TRANSACTION_KEY))
            );
          JsonObject lineItemRequest = transactionRequest.getJsonObject("lineItems").getJsonObject("lineItem");
          JsonObject lineItemResponse = new JsonObject();
          lineItemResponse.put("itemId", lineItemRequest.getString("itemId"));
          lineItemResponse.put("name", StringUtils.substring(lineItemRequest.getString("name"), 0, 31));
          lineItemResponse.put("description", StringUtils.substring(lineItemRequest.getString("description"), 0, 255));
          lineItemResponse.put("quantity", lineItemRequest.getString("quantity"));
          lineItemResponse.put("unitPrice", lineItemRequest.getString("unitPrice"));
          createTransactionRequest2.put("transactionRequest", new JsonObject()
            .put("transactionType", "authCaptureTransaction")
            .put("amount", total.toString())
            .put("payment", transactionRequest.getJsonObject("payment"))
            .put("lineItems", new JsonObject().put("lineItem", lineItemResponse))
            .put("billTo", transactionRequest.getJsonObject("billTo"))
            );
          responseBody.put("createTransactionRequest", createTransactionRequest2);

          Integer authorizePort = Integer.parseInt(config.getString(ConfigKeys.AUTHORIZE_NET_API_PORT));
          String authorizeHostName = config.getString(ConfigKeys.AUTHORIZE_NET_API_HOST_NAME);
          Boolean authorizeSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AUTHORIZE_NET_API_SSL));
          String authorizeUri = config.getString(ConfigKeys.AUTHORIZE_NET_API_URI);

          webClient.post(authorizePort, authorizeHostName, authorizeUri).ssl(authorizeSsl)
              .putHeader("Content-Type", "application/json")
              .sendJsonObject(responseBody)
              .expecting(HttpResponseExpectation.SC_OK)
              .onSuccess(authorizeResponse -> {
            try {
              JsonObject transactionResponseBody = authorizeResponse.bodyAsJsonObject();
              if("Error".equals(Optional.ofNullable(transactionResponseBody.getJsonObject("messages")).map(m -> m.getString("resultCode")).orElse(null))) {
                throw new RuntimeException(String.format("The createTransactionRequest to authorize.net failed: %s", transactionResponseBody));
              } else {
                String userName = siteRequest.getUserName();
                String pageId = o.getPageId();
                JsonObject transactionResponse = transactionResponseBody.getJsonObject("transactionResponse");
                String transactionId = transactionResponse.getString("transId");
                processAuthorizeItem(total, totalTax, netAmountDue, userName, pageId, vertx, config, webClient, transactionId, lineItemResponse).onSuccess(a -> {
                  promise.complete(transactionResponseBody);
                }).onFailure(ex -> {
                  LOG.error(String.format("Failed to process authorize.net item. "), ex);
                  promise.tryFail(ex);
                });
              }
            } catch(Exception ex) {
              LOG.error(String.format("The createTransactionRequest to authorize.net failed. "), ex);
              promise.tryFail(ex);
            }
          }).onFailure(ex -> {
            LOG.error(String.format("Updating AUTHORIZE_NET_API host failed. "), ex);
            promise.fail(ex);
          });
        }
      } else {
        responseBody.put("itemPrice", itemPrice.toString());
        responseBody.put("totalPrice", total.toString());
        promise.complete(responseBody);
      }
    } catch(Exception ex) {
      LOG.error(String.format("patchpayCompanyProductFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public static Future<Void> processAuthorizeItem(BigDecimal total, BigDecimal totalTax, BigDecimal netAmountDue, String userName, String pageId, Vertx vertx, JsonObject config, WebClient webClient, String transactionId, JsonObject item) {
    Promise<Void> promise = Promise.promise();
    try {
      if(userName != null) {
        String itemId = item.getString("itemId");
        LOG.info(String.format("Processing %s order for userName %s", itemId, userName));
        String itemName = item.getString("name");

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
        searchList.fq(String.format("pageId_docvalues_string:\"" + pageId + "\""));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          if(searchList.size() > 0) {
            ComputateBaseResult result = searchList.first();
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
                      webClient.get(authPort, authHostName, String.format("/admin/realms/%s/users?exact=true&username=%s", authRealm, URLEncoder.encode(userName, "UTF-8"))).ssl(authSsl).putHeader("Authorization", String.format("Bearer %s", authToken))
                      .send()
                      .expecting(HttpResponseExpectation.SC_OK)
                      .onSuccess(userResponse -> {
                        JsonArray users = Optional.ofNullable(userResponse.bodyAsJsonArray()).orElse(new JsonArray());
                        JsonObject user = users.stream().map(o -> (JsonObject)o).filter(o -> userName.equals(o.getString("username"))).findFirst().orElse(null);
                        if(user != null) {
                          String userId = user.getString("id");
                          String userEmail = user.getString("email");
                          String userFullName = String.format("%s %s", user.getString("firstName"), user.getString("lastName"));
                          JsonArray userGroups = user.getJsonArray("groups");
                          webClient.put(authPort, authHostName, String.format("/admin/realms/%s/users/%s/groups/%s", authRealm, userId, groupId)).ssl(authSsl)
                              .putHeader("Authorization", String.format("Bearer %s", authToken))
                              .putHeader("Content-Type", "application/json")
                              .putHeader("Content-Length", "0")
                              .send()
                              .expecting(HttpResponseExpectation.SC_NO_CONTENT)
                              .onSuccess(groupUserResponse -> {
                            LOG.info(String.format("Successfully added user %s to the group %s in Keycloak", userName, groupName));
                            sendProductEmail(total, totalTax, netAmountDue, siteRequest, userName, pageId, vertx, config, transactionId, item, user, result).onSuccess(c -> {
                              try {
                                grantGithubTeamAccess(itemId, userName, config, webClient).onSuccess(b -> {
                                  promise.complete();
                                }).onFailure(ex -> {
                                  promise.fail(ex);
                                });
                              } catch(Throwable ex) {
                                LOG.error("Failed to process authorize.net webook while querying customer. ", ex);
                                promise.fail(ex);
                              }
                            }).onFailure(ex -> {
                              LOG.error("Failed to process authorize.net webook while sending email. ", ex);
                              promise.fail(ex);
                            });
                          }).onFailure(ex -> {
                            LOG.error("Failed to process authorize.net webook while adding user to group. ", ex);
                            promise.fail(ex);
                          });
                        } else {
                          Throwable ex = new RuntimeException(String.format("Failed to find user %s. ", userName));
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
        LOG.warn(String.format("Order %s missing userName", transactionId));
        promise.complete();
      }
    } catch(Exception ex) {
      LOG.error("The authorize.net item failed to process.");
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<Void> grantGithubTeamAccess(String githubTeam, String githubUsername, JsonObject config, WebClient webClient) {
    Promise<Void> promise = Promise.promise();
    try {
      String githubOrg = config.getString(ConfigKeys.GITHUB_ORG);
      String githubUri = String.format("/orgs/%s/teams/%s/memberships/%s", BaseApiServiceImpl.urlEncode(githubOrg), BaseApiServiceImpl.urlEncode(githubTeam), BaseApiServiceImpl.urlEncode(githubUsername));
      webClient.put(443, "api.github.com", githubUri).ssl(true)
          .putHeader("Accept", "application/vnd.github+json")
          .putHeader("X-GitHub-Api-Version", "2022-11-28")
          .putHeader("Authorization", String.format("Bearer %s", config.getString(ConfigKeys.GITHUB_TEAMS_TOKEN)))
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

  public static Future<Void> sendProductEmail(BigDecimal total, BigDecimal totalTax, BigDecimal netAmountDue, SiteRequest siteRequest, String userName, String pageId, Vertx vertx, JsonObject config, String transactionId, JsonObject item, JsonObject user, ComputateBaseResult result) {
    Promise<Void> promise = Promise.promise();
    try {
      String userEmail = user.getString("email");
      String customerName = String.format("%s %s", user.getString("firstName"), user.getString("lastName"));
      String itemName = item.getString("name");
      DeliveryOptions options = new DeliveryOptions();
      String siteName = config.getString(ComputateConfigKeys.SITE_NAME);
      String emailFrom = config.getString(ComputateConfigKeys.EMAIL_FROM);
      String emailTo = userEmail;

      String subject = String.format("Hello %s! Thank you for ordering the %s from %s! ", customerName, itemName, siteName);
      String emailTemplate = (String)result.obtainForClass("emailTemplate");
      options.addHeader(EmailVerticle.MAIL_HEADER_SUBJECT, subject);
      options.addHeader(EmailVerticle.MAIL_HEADER_FROM, emailFrom);
      options.addHeader(EmailVerticle.MAIL_HEADER_TO, emailTo);
      options.addHeader(EmailVerticle.MAIL_HEADER_TEMPLATE, emailTemplate);

      ZoneId zoneId = ZoneId.of(config.getString(ComputateConfigKeys.SITE_ZONE));
      ZoneId zoneIdSite = ZoneId.of(siteRequest.getConfig().getString(ConfigKeys.SITE_ZONE));
      ZonedDateTime createdAt = ZonedDateTime.now(zoneIdSite);
      Locale locale = Locale.forLanguageTag(config.getString(ComputateConfigKeys.SITE_LOCALE));
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE d MMM uuuu h:mm a VV", locale);
      JsonObject body = new JsonObject();
      body.put(ComputateConfigKeys.SITE_BASE_URL, config.getString(ComputateConfigKeys.SITE_BASE_URL));
      body.put("siteName", siteName);
      body.put("githubUsername", userName);
      body.put("transactionId", transactionId);
      body.put("subject", subject);
      body.put("emailTo", emailTo);
      body.put("customerName", customerName);
      body.put("result", JsonObject.mapFrom(result));
      body.put("totalMoney", NumberFormat.getCurrencyInstance(locale).format(total));
      body.put("totalTax", NumberFormat.getCurrencyInstance(locale).format(totalTax));
      body.put("netAmountDue", NumberFormat.getCurrencyInstance(locale).format(netAmountDue));

      String createdAtStr = dateFormat.format(createdAt.withZoneSameInstant(zoneId));
      body.put("createdAt", createdAtStr);

      vertx.eventBus().send(EmailVerticle.MAIL_EVENTBUS_ADDRESS, body.encode(), options);
      LOG.info(String.format("Sending email to %s for purchasing %s", userName, pageId));
    } catch(Exception ex) {
      LOG.error("The square item failed to process.");
      promise.fail(ex);
    }
    return promise.future();
  }

  // @Override
  // public void userpageCompanyProductPageInit(JsonObject ctx, CompanyProductPage page, SearchList<CompanyProduct> listCompanyProduct, Promise<Void> promise) {
  //   try {
  //     SiteUser siteUser = page.getSiteRequest_().getSiteUser_();
  //     SiteRequest siteRequest = siteUser.getSiteRequest_();
  //     CompanyProduct companyProduct = listCompanyProduct.first();
  //     String authorizeEnvironment = config.getString(ConfigKeys.AUTHORIZE_NET_ENVIRONMENT);
  //     String authorizeApiLoginId = config.getString(ConfigKeys.AUTHORIZE_NET_API_LOGIN_ID);
  //     String authorizeTransactionKey = config.getString(ConfigKeys.AUTHORIZE_NET_TRANSACTION_KEY);
  //     String authorizeSignatureKey = config.getString(ConfigKeys.AUTHORIZE_NET_SIGNATURE_KEY);
  //     String authorizePublicClientKey = config.getString(ConfigKeys.AUTHORIZE_NET_PUBLIC_CLIENT_KEY);
  //     String authorizeNotificationUrl = config.getString(ConfigKeys.AUTHORIZE_NET_NOTIFICATION_URL);
  //     String siteBaseUrl = config.getString(ConfigKeys.SITE_BASE_URL);
  //     BigDecimal productPrice = companyProduct.getPrice();

  //     if(siteUser != null && productPrice != null && productPrice.compareTo(BigDecimal.ZERO) > 0) {
  //       String customerProfileId = (String)siteUser.obtainSiteUser("customerProfileId");
  //       if(customerProfileId != null) {
  //         MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
  //         merchantAuthenticationType.setName(authorizeApiLoginId);
  //         merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
  //         ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

  //         GetHostedProfilePageRequest createCustomerProfileRequest = new GetHostedProfilePageRequest();
  //         createCustomerProfileRequest.setMerchantAuthentication(merchantAuthenticationType);
  //         createCustomerProfileRequest.setCustomerProfileId(customerProfileId);
  //         ArrayOfSetting customerProfileSettings = new ArrayOfSetting();
  //         {
  //           SettingType settingType = new SettingType();
  //           settingType.setSettingName("hostedProfileManageOptions");
  //           settingType.setSettingValue("showPayment");
  //           customerProfileSettings.getSetting().add(settingType);
  //         }
  //         {
  //           SettingType settingType = new SettingType();
  //           settingType.setSettingName("hostedProfileValidationMode");
  //           settingType.setSettingValue("testMode");
  //           customerProfileSettings.getSetting().add(settingType);
  //         }
  //         {
  //           SettingType settingType = new SettingType();
  //           settingType.setSettingName("hostedProfileBillingAddressOptions");
  //           settingType.setSettingValue("showNone");
  //           customerProfileSettings.getSetting().add(settingType);
  //         }
  //         {
  //           SettingType settingType = new SettingType();
  //           settingType.setSettingName("hostedProfileCardCodeRequired");
  //           settingType.setSettingValue("true");
  //           customerProfileSettings.getSetting().add(settingType);
  //         }
  //         createCustomerProfileRequest.setHostedProfileSettings(customerProfileSettings);

  //         GetHostedProfilePageController hostedProfileController = new GetHostedProfilePageController(createCustomerProfileRequest);
  //         GetTransactionListForCustomerController.setEnvironment(Environment.valueOf(authorizeEnvironment));
  //         GetHostedProfilePageResponse profilePageResponse = null;
  //         hostedProfileController.execute();
  //         if(hostedProfileController.getErrorResponse() != null)
  //           throw new RuntimeException(hostedProfileController.getResults().toString());
  //         else {
  //           profilePageResponse = hostedProfileController.getApiResponse();
  //           if(MessageTypeEnum.ERROR.equals(profilePageResponse.getMessages().getResultCode())) {
  //             Message message = profilePageResponse.getMessages().getMessage().stream().findFirst().orElse(null);
  //             if(message != null && message.getCode().equals("E00124"))
  //               profilePageResponse = null;
  //             else {
  //               Exception ex = new RuntimeException(Optional.ofNullable(message).map(m -> String.format("%s %s, %s, userName: %s, userFullName: %s", m.getCode(), m.getText(), customerProfileId, siteUser.getUserName(), siteUser.getUserFullName())).orElse("GetHostedProfilePageRequest failed. "));
  //               LOG.error("Error setting up authorize.net payment", ex);
  //             }
  //           }
  //         }

  //         if(profilePageResponse != null) {
  //           GetHostedPaymentPageRequest hostedPaymentPageRequest = new GetHostedPaymentPageRequest();
  //           hostedPaymentPageRequest.setMerchantAuthentication(merchantAuthenticationType);
  
  //           ArrayOfSetting hostedPaymentSettings = new ArrayOfSetting();
  //           SettingType settingType = new SettingType();
  //           JsonObject hostedPaymentReturnOptions = new JsonObject().put("showReceipt", true);
  //           // if(!siteBaseUrl.startsWith("http://localhost")) {
  //             // hostedPaymentReturnOptions.put("url", companyProduct.getUserPage());
  //             // hostedPaymentReturnOptions.put("cancelUrl", companyProduct.getDisplayPage());
  //           // }
  //           settingType.setSettingName("hostedPaymentReturnOptions");
  //           settingType.setSettingValue(hostedPaymentReturnOptions.encode());
  //           hostedPaymentSettings.getSetting().add(settingType);
  //           hostedPaymentPageRequest.setHostedPaymentSettings(hostedPaymentSettings);
  //           TransactionRequestType transactionRequest = new TransactionRequestType();
  //           // This is the default transaction type. 
  //           // When using AIM, if the x_type field is not sent to us, the type will default to AUTH_CAPTURE. 
  //           // Simple Checkout uses AUTH_CAPTURE only. 
  //           // The Virtual Terminal defaults to AUTH_CAPTURE unless you select a different transaction type.
  //           // With an AUTH_CAPTURE transaction, the process is completely automatic. 
  //           // The transaction is submitted to your processor for authorization and, 
  //           // if approved, is placed in your Unsettled Transactions with the status Captured Pending Settlement. 
  //           // The transaction will settle at your next batch. 
  //           // Settlement occurs every 24 hours, within 24 hours of your Transaction Cut-off Time.
  //           // See: https://support.authorize.net/s/article/What-Are-the-Transaction-Types-That-Can-Be-Submitted
  //           transactionRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());

  //           BigDecimal itemPrice;
  //           if(BooleanUtils.toBoolean(siteRequest.getRequestVars().get("utah"))) {
  //             BigDecimal calculatedTaxRate = new BigDecimal(0.0725).setScale(4, RoundingMode.HALF_UP);
  //             BigDecimal calculatedTaxAmount = productPrice.subtract(productPrice.divide(calculatedTaxRate.add(BigDecimal.ONE), 2, RoundingMode.HALF_DOWN));
  //             ExtendedAmountType tax = new ExtendedAmountType();
  //             tax.setAmount(calculatedTaxAmount);
  //             tax.setName("Sales Tax");
  //             tax.setDescription("Utah State Sales Tax");
  //             transactionRequest.setTax(tax);
  //             transactionRequest.setTaxExempt(false);
  //             itemPrice = productPrice.subtract(calculatedTaxAmount);
  //           } else {
  //             transactionRequest.setTaxExempt(true);
  //             itemPrice = productPrice;
  //           }
  //           transactionRequest.setAmount(productPrice);
  
  //           ArrayOfLineItem lineItems = new ArrayOfLineItem();
  //           LineItemType lineItem = new LineItemType();
  //           lineItem.setItemId(StringUtils.truncate(companyProduct.getPageId(), 31));
  //           lineItem.setDescription(StringUtils.truncate(companyProduct.getDescription(), 255));
  //           lineItem.setName(StringUtils.truncate(companyProduct.getName(), 31));
  //           lineItem.setTotalAmount(itemPrice);
  //           lineItem.setQuantity(BigDecimal.ONE);
  //           lineItem.setUnitPrice(itemPrice);
  //           lineItems.getLineItem().add(lineItem);
  //           DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US);
  //           LocalDate now = LocalDate.now();
  //           LocalDate chargeEndDate = LocalDate.now();
  //           CustomerProfilePaymentType profile = new CustomerProfilePaymentType();
  //           profile.setCustomerProfileId(customerProfileId);
  //           transactionRequest.setProfile(profile);
  //           transactionRequest.setLineItems(lineItems);
  //           OrderType order = new OrderType();
  //           order.setDescription(StringUtils.truncate(String.format("%s bought %s $%s payment from %s %s on %s", siteUser.getUserName(), companyProduct.getPageId(), companyProduct.getPrice(), siteUser.getUserFullName(), siteUser.getUserEmail(), fd.format(chargeEndDate)), 255));
  //           transactionRequest.setOrder(order);
  //           hostedPaymentPageRequest.setTransactionRequest(transactionRequest);
  
  //           GetHostedPaymentPageController hostedPaymentController = new GetHostedPaymentPageController(hostedPaymentPageRequest);
  //           GetHostedPaymentPageController.setEnvironment(Environment.valueOf(authorizeEnvironment));
  //           GetHostedPaymentPageResponse hostedPaymentResponse = null;
  //           hostedPaymentController.execute();
  //           if(hostedPaymentController.getErrorResponse() != null) {
  //             Exception ex = new RuntimeException(String.format("Failed to set up hosted payment controller: %s", hostedPaymentController.getResults().toString()));
  //             LOG.error("Failed to execute hosted payment controller", ex);
  //             promise.fail(ex);
  //           } else {
  //             hostedPaymentResponse = hostedPaymentController.getApiResponse();
  //             if(MessageTypeEnum.ERROR.equals(hostedPaymentResponse.getMessages().getResultCode())) {
  //               Exception ex = new RuntimeException(String.format("Failed to set up hosted payment controller: %s", hostedPaymentResponse.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("GetHostedPaymentPageRequest failed. ")));
  //               LOG.error("Error response from hosted payment controller", ex);
  //               promise.fail(ex);
  //             } else {
  //               LOG.info(String.format("hostedPaymentResponseToken found for user %s", siteUser.getUserName()));
  //               ctx.put("hostedPaymentResponseToken", hostedPaymentResponse.getToken());
  //               promise.complete();
  //             }
  //           }
  //         } else {
  //           Exception ex = new RuntimeException(String.format("Failed to set up profilePageResponse: %s", profilePageResponse));
  //           LOG.error("Failed profile page response", ex);
  //           promise.fail(ex);
  //         }
  //       } else {
  //         LOG.warn(String.format("The customerProfileId for user %s was null", siteUser.getUserName()));
  //         promise.complete();
  //       }
  //     } else {
  //       LOG.warn(String.format("No price %s for user %s", productPrice, siteUser.getUserName()));
  //       promise.complete();
  //     }
  //   } catch(Throwable ex) {
  //     promise.fail(new RuntimeException("Failed to set up authorize.net payment"));
  //   }
  // }
}
