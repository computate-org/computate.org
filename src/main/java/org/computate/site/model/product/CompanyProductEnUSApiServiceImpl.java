package org.computate.site.model.product;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.computate.site.config.ConfigKeys;
import org.computate.site.request.SiteRequest;
import org.computate.site.user.SiteUser;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.search.list.SearchList;

import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfLineItem;
import net.authorize.api.contract.v1.ArrayOfSetting;
import net.authorize.api.contract.v1.CustomerProfilePaymentType;
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
import net.authorize.api.controller.GetHostedPaymentPageController;
import net.authorize.api.controller.GetHostedProfilePageController;
import net.authorize.api.controller.GetTransactionListForCustomerController;
import net.authorize.api.controller.base.ApiOperationBase;

import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class CompanyProductEnUSApiServiceImpl extends CompanyProductEnUSGenApiServiceImpl {

  @Override
  public void userDefine(Promise<Boolean> promise, ComputateSiteRequest siteRequest, JsonObject jsonObject, Boolean patch) {
    userDefineAuthorizeNet(promise, siteRequest, jsonObject, patch);
  }

  @Override
  public void userpageCompanyProductPageInit(JsonObject ctx, CompanyProductPage page, SearchList<CompanyProduct> listCompanyProduct, Promise<Void> promise) {
    try {
      SiteRequest siteRequest = page.getSiteRequest_();
      SiteUser siteUser = siteRequest.getSiteUser_();
      CompanyProduct companyProduct = listCompanyProduct.first();
      String authorizeEnvironment = config.getString(ConfigKeys.AUTHORIZE_NET_ENVIRONMENT);
      String authorizeApiLoginId = config.getString(ConfigKeys.AUTHORIZE_NET_API_LOGIN_ID);
      String authorizeTransactionKey = config.getString(ConfigKeys.AUTHORIZE_NET_TRANSACTION_KEY);
      String authorizeSignatureKey = config.getString(ConfigKeys.AUTHORIZE_NET_SIGNATURE_KEY);
      String authorizePublicClientKey = config.getString(ConfigKeys.AUTHORIZE_NET_PUBLIC_CLIENT_KEY);
      String authorizeNotificationUrl = config.getString(ConfigKeys.AUTHORIZE_NET_NOTIFICATION_URL);
      String siteBaseUrl = config.getString(ConfigKeys.SITE_BASE_URL);

      if(siteUser != null) {
        String customerProfileId = (String)siteUser.obtainSiteUser("customerProfileId");
        if(customerProfileId != null) {
          JsonObject getHostedProfilePageRequestObj = new JsonObject();
          JsonObject getHostedProfilePageRequest = new JsonObject();
          getHostedProfilePageRequestObj.put("getHostedProfilePageRequest", getHostedProfilePageRequest);
          JsonObject merchantAuthentication = new JsonObject();
          getHostedProfilePageRequest.put("merchantAuthentication", merchantAuthentication);
          merchantAuthentication.put("name", authorizeApiLoginId);
          merchantAuthentication.put("transactionKey", authorizeTransactionKey);
          getHostedProfilePageRequestObj.put("customerProfileId", customerProfileId);
          JsonObject hostedProfileSettings = new JsonObject();
          getHostedProfilePageRequest.put("hostedProfileSettings", hostedProfileSettings);
          hostedProfileSettings.put("setting", new JsonArray()
              .add(new JsonObject()
                  .put("settingName", "hostedProfileManageOptions")
                  .put("settingValue", "showPayment")
              )
              .add(new JsonObject()
                  .put("settingName", "hostedProfileValidationMode")
                  .put("settingValue", "testMode")
              )
              .add(new JsonObject()
                  .put("settingName", "hostedProfileBillingAddressOptions")
                  .put("settingValue", "showNone")
              )
              .add(new JsonObject()
                  .put("settingName", "hostedProfileCardCodeRequired")
                  .put("settingValue", "true")
              )
          );
          webClient.post(Integer.parseInt(config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_PORT))
              , config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_HOST_NAME)
              , config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_URI))
              .ssl(Boolean.parseBoolean(config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_SSL)))
              .putHeader("Content-Type", "application/json")
              .sendJsonObject(getHostedProfilePageRequestObj)
              .expecting(HttpResponseExpectation.SC_OK)
                  .onSuccess(hostedProfilePageResponse -> {
            try {
              JsonObject hostedProfilePageResponseObj = hostedProfilePageResponse.bodyAsJsonObject();
              JsonObject messages = hostedProfilePageResponseObj.getJsonObject("messages");
              if("Error".equals(messages.getString("resultCode"))) {
                JsonObject message = messages.getJsonArray("message").stream().findFirst().map(m -> (JsonObject)m).orElse(null);
                String messageStr = Optional.ofNullable(message).map(m -> m.getString("text")).orElse("");
                String code = Optional.ofNullable(message).map(m -> m.getString("code")).orElse(null);
                if("E00124".equals(code))
                  hostedProfilePageResponseObj = null;
                else 
                  throw new RuntimeException(Optional.ofNullable(message).map(m -> String.format("%s %s, %s, userName: %s, userFullName: %s", m.getString("code"), m.getString("text"), customerProfileId, siteUser.getUserName(), siteUser.getUserFullName())).orElse("GetHostedProfilePageRequest failed. "));
              }

              if(hostedProfilePageResponseObj != null) {
                JsonObject getHostedPaymentPageRequestObj = new JsonObject();
                JsonObject getHostedPaymentPageRequest = new JsonObject();
                getHostedPaymentPageRequestObj.put("getHostedPaymentPageRequest", getHostedPaymentPageRequest);
                getHostedPaymentPageRequest.put("merchantAuthentication", merchantAuthentication);
                JsonObject hostedPaymentReturnOptions = new JsonObject();
                hostedPaymentReturnOptions.put("showReceipt", true);
                if(!siteBaseUrl.startsWith("http://localhost")) {
                  hostedPaymentReturnOptions.put("url", String.format("%s%s", siteBaseUrl, page.getSiteRequest_().getRequestUri()));
                  hostedPaymentReturnOptions.put("cancelUrl", String.format("%s%s", siteBaseUrl, page.getSiteRequest_().getRequestUri()));
                }
                getHostedPaymentPageRequest.put("hostedPaymentSettings", new JsonObject()
                    .put("setting", new JsonArray()
                        .add(new JsonObject()
                            .put("settingName", "hostedPaymentReturnOptions")
                            .put("settingValue", hostedPaymentReturnOptions.encode())
                        )
                    )
                );
                DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US);
                LocalDate now = LocalDate.now();
                LocalDate chargeEndDate = LocalDate.now();
                String orderDescription = StringUtils.truncate(String.format("%s $%s payment from %s %s on %s", companyProduct.getName(), companyProduct.getPrice(), siteUser.getUserFullName(), siteUser.getUserEmail(), fd.format(chargeEndDate)), 255);
                getHostedPaymentPageRequest.put("transactionRequest", new JsonObject()
                    .put("transactionType", "authCaptureTransaction")
                    .put("amount", companyProduct.getPrice().setScale(2, RoundingMode.HALF_UP).toString())
                    .put("profile", new JsonObject()
                        .put("customerProfileId", customerProfileId)
                    )
                    .put("customer", new JsonObject()
                        .put("email", siteUser.getUserEmail())
                    )
                    .put("billTo", new JsonObject()
                        .put("firstName", siteUser.getUserFirstName())
                        .put("lastName", siteUser.getUserLastName())
                    )
                    .put("lineItems", new JsonObject()
                        .put("lineItem", new JsonArray().add(new JsonObject()
                            .put("itemId", StringUtils.truncate(companyProduct.getPageId(), 31))
                            .put("description", StringUtils.truncate(companyProduct.getDescription(), 255))
                            .put("name", StringUtils.truncate(companyProduct.getName(), 31))
                            .put("totalAmount", companyProduct.getPrice())
                        ))
                    )
                    .put("order", new JsonObject()
                        .put("description", orderDescription)
                    )
                );
                webClient.post(Integer.parseInt(config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_PORT))
                    , config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_HOST_NAME)
                    , config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_URI))
                    .ssl(Boolean.parseBoolean(config.getString(ComputateConfigKeys.AUTHORIZE_NET_API_SSL)))
                    .putHeader("Content-Type", "application/json")
                    .sendJsonObject(getHostedPaymentPageRequestObj)
                    .expecting(HttpResponseExpectation.SC_OK)
                        .onSuccess(hostedPaymentPageResponse -> {
                  try {
                    JsonObject hostedPaymentPageResponseObj = hostedPaymentPageResponse.bodyAsJsonObject();
                    JsonObject hostedPaymentPageResponseMessages = hostedPaymentPageResponseObj.getJsonObject("messages");
                    if("Error".equals(hostedPaymentPageResponseMessages.getString("resultCode"))) {
                      JsonObject message = hostedPaymentPageResponseMessages.getJsonArray("message").stream().findFirst().map(m -> (JsonObject)m).orElse(null);
                      throw new RuntimeException(Optional.ofNullable(message).map(m -> String.format("Failed to set up hosted payment %s %s, %s, userName: %s, userFullName: %s", m.getString("code"), m.getString("text"), customerProfileId, siteUser.getUserName(), siteUser.getUserFullName())).orElse("GetHostedPaymentPageRequest failed. "));
                    }
                    ctx.put("hostedPaymentResponseToken", hostedPaymentPageResponseObj.getString("token"));
                    promise.complete();
                  } catch(Throwable ex) {
                    LOG.error(String.format("Failed to process hosted profile page for user: %s", siteRequest.getUserName()), ex);
                    promise.fail(ex);
                  }
                }).onFailure(ex -> {
                  LOG.error(String.format("Failed to create hosted profile page for user: %s", siteRequest.getUserName()), ex);
                  promise.fail(ex);
                });
              } else {
                promise.fail(new RuntimeException(String.format("Failed to set up profilePageResponse: %s", hostedProfilePageResponseObj.encode())));
              }
            } catch(Throwable ex) {
              LOG.error(String.format("Failed to process hosted profile page for user: %s", siteRequest.getUserName()), ex);
              promise.fail(ex);
            }
          }).onFailure(ex -> {
            LOG.error(String.format("Failed to create hosted profile page for user: %s", siteRequest.getUserName()), ex);
            promise.fail(ex);
          });
        } else {
          promise.complete();
        }
      } else {
        promise.complete();
      }
    } catch(Throwable ex) {
    }
  }
}
