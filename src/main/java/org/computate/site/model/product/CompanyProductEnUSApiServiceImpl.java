package org.computate.site.model.product;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.computate.site.config.ConfigKeys;
import org.computate.site.user.SiteUser;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
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
  public void userpageCompanyProductPageInit(JsonObject ctx, CompanyProductPage page, SearchList<CompanyProduct> listCompanyProduct, Promise<Void> promise) {
    try {
      SiteUser siteUser = page.getSiteRequest_().getSiteUser_();
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
          MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
          merchantAuthenticationType.setName(authorizeApiLoginId);
          merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
          ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

          GetHostedProfilePageRequest createCustomerProfileRequest = new GetHostedProfilePageRequest();
          createCustomerProfileRequest.setMerchantAuthentication(merchantAuthenticationType);
          createCustomerProfileRequest.setCustomerProfileId(customerProfileId);
          ArrayOfSetting customerProfileSettings = new ArrayOfSetting();
          {
            SettingType settingType = new SettingType();
            settingType.setSettingName("hostedProfileManageOptions");
            settingType.setSettingValue("showPayment");
            customerProfileSettings.getSetting().add(settingType);
          }
          {
            SettingType settingType = new SettingType();
            settingType.setSettingName("hostedProfileValidationMode");
            settingType.setSettingValue("testMode");
            customerProfileSettings.getSetting().add(settingType);
          }
          {
            SettingType settingType = new SettingType();
            settingType.setSettingName("hostedProfileBillingAddressOptions");
            settingType.setSettingValue("showNone");
            customerProfileSettings.getSetting().add(settingType);
          }
          {
            SettingType settingType = new SettingType();
            settingType.setSettingName("hostedProfileCardCodeRequired");
            settingType.setSettingValue("true");
            customerProfileSettings.getSetting().add(settingType);
          }
          createCustomerProfileRequest.setHostedProfileSettings(customerProfileSettings);

          GetHostedProfilePageController hostedProfileController = new GetHostedProfilePageController(createCustomerProfileRequest);
          GetTransactionListForCustomerController.setEnvironment(Environment.valueOf(authorizeEnvironment));
          GetHostedProfilePageResponse profilePageResponse = null;
          hostedProfileController.execute();
          if(hostedProfileController.getErrorResponse() != null)
            throw new RuntimeException(hostedProfileController.getResults().toString());
          else {
            profilePageResponse = hostedProfileController.getApiResponse();
            if(MessageTypeEnum.ERROR.equals(profilePageResponse.getMessages().getResultCode())) {
              Message message = profilePageResponse.getMessages().getMessage().stream().findFirst().orElse(null);
              if(message != null && message.getCode().equals("E00124"))
                profilePageResponse = null;
              else 
                throw new RuntimeException(Optional.ofNullable(message).map(m -> String.format("%s %s, %s, userName: %s, userFullName: %s", m.getCode(), m.getText(), customerProfileId, siteUser.getUserName(), siteUser.getUserFullName())).orElse("GetHostedProfilePageRequest failed. "));
            }
          }

          if(profilePageResponse != null) {
            GetHostedPaymentPageRequest hostedPaymentPageRequest = new GetHostedPaymentPageRequest();
            hostedPaymentPageRequest.setMerchantAuthentication(merchantAuthenticationType);
  
            ArrayOfSetting hostedPaymentSettings = new ArrayOfSetting();
            SettingType settingType = new SettingType();
            JsonObject hostedPaymentReturnOptions = new JsonObject().put("showReceipt", true);
            if(!siteBaseUrl.startsWith("http://localhost")) {
              hostedPaymentReturnOptions.put("url", String.format("%s%s", siteBaseUrl, page.getSiteRequest_().getRequestUri()));
              hostedPaymentReturnOptions.put("cancelUrl", String.format("%s%s", siteBaseUrl, page.getSiteRequest_().getRequestUri()));
            }
            settingType.setSettingName("hostedPaymentReturnOptions");
            settingType.setSettingValue(hostedPaymentReturnOptions.encode());
            hostedPaymentSettings.getSetting().add(settingType);
            hostedPaymentPageRequest.setHostedPaymentSettings(hostedPaymentSettings);
            TransactionRequestType transactionRequest = new TransactionRequestType();
            // This is the default transaction type. 
            // When using AIM, if the x_type field is not sent to us, the type will default to AUTH_CAPTURE. 
            // Simple Checkout uses AUTH_CAPTURE only. 
            // The Virtual Terminal defaults to AUTH_CAPTURE unless you select a different transaction type.
            // With an AUTH_CAPTURE transaction, the process is completely automatic. 
            // The transaction is submitted to your processor for authorization and, 
            // if approved, is placed in your Unsettled Transactions with the status Captured Pending Settlement. 
            // The transaction will settle at your next batch. 
            // Settlement occurs every 24 hours, within 24 hours of your Transaction Cut-off Time.
            // See: https://support.authorize.net/s/article/What-Are-the-Transaction-Types-That-Can-Be-Submitted
            transactionRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
  
            // Removed transaction fee
            //transactionRequest.setAmount(amount.multiply(BigDecimal.valueOf(1.00)).setScale(1, RoundingMode.CEILING));
            transactionRequest.setAmount(companyProduct.getPrice());
  
            ArrayOfLineItem lineItems = new ArrayOfLineItem();
            LineItemType lineItem = new LineItemType();
            DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMM yyyy", Locale.US);
            LocalDate now = LocalDate.now();
            // Integer paymentDay = siteConfig.getPaymentDay();
            LocalDate chargeEndDate = LocalDate.now();
            // LocalDate chargeEndDate = LocalDate.now().getDayOfMonth() <= paymentDay ? now.withDayOfMonth(paymentDay) : now.plusMonths(1).withDayOfMonth(paymentDay);
            CustomerProfilePaymentType profile = new CustomerProfilePaymentType();
            profile.setCustomerProfileId(customerProfileId);
            transactionRequest.setProfile(profile);
            OrderType order = new OrderType();
            order.setDescription(StringUtils.truncate(String.format("%s<%s> payment for $%s %s %s", siteUser.getUserFullName(), siteUser.getUserEmail(), companyProduct.getPrice(), companyProduct.getName(), fd.format(chargeEndDate)), 255));
            transactionRequest.setOrder(order);
            hostedPaymentPageRequest.setTransactionRequest(transactionRequest);
  
            GetHostedPaymentPageController hostedPaymentController = new GetHostedPaymentPageController(hostedPaymentPageRequest);
            GetHostedPaymentPageController.setEnvironment(Environment.valueOf(authorizeEnvironment));
            GetHostedPaymentPageResponse hostedPaymentResponse = null;
            hostedPaymentController.execute();
            if(hostedPaymentController.getErrorResponse() != null)
              promise.fail(new RuntimeException(String.format("Failed to set up hosted payment controller: %s", hostedPaymentController.getResults().toString())));
            else {
              hostedPaymentResponse = hostedPaymentController.getApiResponse();
              if(MessageTypeEnum.ERROR.equals(hostedPaymentResponse.getMessages().getResultCode())) {
                promise.fail(new RuntimeException(String.format("Failed to set up hosted payment controller: %s", hostedPaymentResponse.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("GetHostedPaymentPageRequest failed. "))));
              } else {
                ctx.put("hostedPaymentResponseToken", hostedPaymentResponse.getToken());
                promise.complete();
              }
            }
          } else {
            promise.fail(new RuntimeException(String.format("Failed to set up profilePageResponse: %s", profilePageResponse)));
          }
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
