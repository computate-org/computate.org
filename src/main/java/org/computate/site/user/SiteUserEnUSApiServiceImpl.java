package org.computate.site.user;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;

import java.net.URLEncoder;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.computate.site.config.ConfigKeys;
import org.computate.site.request.SiteRequest;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.request.ComputateSiteRequest;

import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;

import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class SiteUserEnUSApiServiceImpl extends SiteUserEnUSGenApiServiceImpl {

  @Override
  public void userDefine(Promise<Boolean> promise, ComputateSiteRequest siteRequest, JsonObject jsonObject, Boolean patch) {
    userDefineAuthorizeNet(promise, siteRequest, jsonObject, patch);
  }

  public void userDefineAuthorizeNet(Promise<Boolean> promise, ComputateSiteRequest siteRequest, JsonObject jsonObject, Boolean patch) {
    try {
      JsonObject attributes = siteRequest.getUser().attributes();
      JsonObject accessToken = attributes.getJsonObject("accessToken");
      String authorizeApiLoginId = config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_LOGIN_ID);
      String authorizeTransactionKey = config.getString(ComputateConfigKeys.AUTHORIZE_NET_TRANSACTION_KEY);
      String authorizeEnvironment = config.getString(ComputateConfigKeys.AUTHORIZE_NET_ENVIRONMENT);

      if(Optional.ofNullable(siteRequest.getServiceRequest()).map(serviceRequest -> serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() == 0L
          && StringUtils.isNotBlank(authorizeEnvironment) && authorizeApiLoginId != null && authorizeTransactionKey != null) {
        String customerProfileId1 = accessToken.getString("customerProfileId");
  
        if(customerProfileId1 == null) {
          JsonObject requestObj = new JsonObject();
          JsonObject createCustomerProfileRequest = new JsonObject();
          requestObj.put("createCustomerProfileRequest", createCustomerProfileRequest);
          JsonObject merchantAuthentication = new JsonObject();
          createCustomerProfileRequest.put("merchantAuthentication", merchantAuthentication);
          merchantAuthentication.put("name", authorizeApiLoginId);
          merchantAuthentication.put("transactionKey", authorizeTransactionKey);
          JsonObject profile = new JsonObject();
          createCustomerProfileRequest.put("profile", profile);

          if(patch) {
            profile.put("merchantCustomerId", StringUtils.substring(jsonObject.getString("setUserCompleteName"), 0, 20));
            profile.put("description", jsonObject.getString("setUserId"));
            profile.put("email", jsonObject.getString("setUserEmail"));
          }
          else {
            profile.put("merchantCustomerId", StringUtils.substring(jsonObject.getString("userCompleteName"), 0, 20));
            profile.put("description", jsonObject.getString("userId"));
            profile.put("email", jsonObject.getString("userEmail"));
          }
          webClient.post(Integer.parseInt(config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_PORT))
              , config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_HOST_NAME)
              , config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_URI))
              .ssl(Boolean.parseBoolean(config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_SSL)))
              .putHeader("Content-Type", "application/json")
              .sendJsonObject(requestObj)
              .expecting(HttpResponseExpectation.SC_OK)
                  .onSuccess(createCustomerProfileResponse -> {
            try {
             String customerProfileId2;
              JsonObject response = createCustomerProfileResponse.bodyAsJsonObject();
              JsonObject messages = response.getJsonObject("messages");
              if("Error".equals(messages.getString("resultCode"))) {
                JsonObject message = messages.getJsonArray("message").stream().findFirst().map(m -> (JsonObject)m).orElse(null);
                String messageStr = Optional.ofNullable(message).map(m -> m.getString("text")).orElse("");
                Matcher matcher = Pattern.compile("A duplicate record with ID (\\d+) already exists.").matcher(messageStr);
                if(matcher.find()) {
                  customerProfileId2 = matcher.group(1);
                }
                else {
                  String errorMessage = Optional.ofNullable(message).map(m -> String.format("%s %s", m.getString("code"), m.getString("text"))).orElse("CreateCustomerProfileRequest failed. ");
                  throw new RuntimeException(errorMessage);
                }
              }
              else {
                customerProfileId2 = response.getString("customerProfileId");
              }
              if(patch)
                jsonObject.put("setCustomerProfileId", customerProfileId2);
              else
                jsonObject.put("customerProfileId", customerProfileId2);

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
                          promise.complete(false);
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
            } catch(Throwable ex) {
              LOG.error(String.format("Failed to process customer profile for user: %s", siteRequest.getUserName()), ex);
              promise.fail(ex);
            }
          }).onFailure(ex -> {
            LOG.error(String.format("Failed to create customer profile for user: %s", siteRequest.getUserName()), ex);
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
      LOG.error(String.format("Failed to handle customer profile for user: %s", siteRequest.getUserName()), ex);
      promise.fail(ex);
    }
  }
}
