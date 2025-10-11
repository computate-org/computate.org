package org.computate.site.model.product;

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

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.computate.site.config.ConfigKeys;
import org.computate.site.user.SiteUser;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.search.list.SearchList;

import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfLineItem;
import net.authorize.api.contract.v1.ArrayOfSetting;
import net.authorize.api.contract.v1.CreateCustomerProfileRequest;
import net.authorize.api.contract.v1.CreateCustomerProfileResponse;
import net.authorize.api.contract.v1.CustomerProfilePaymentType;
import net.authorize.api.contract.v1.CustomerProfileType;
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
          if(patch) {
            profile.setEmail(jsonObject.getString("setUserEmail"));
            profile.setDescription(jsonObject.getString("setUserId"));
            profile.setMerchantCustomerId(StringUtils.substring(jsonObject.getString("setUserCompleteName"), 0, 20));
          }
          else {
            profile.setEmail(jsonObject.getString("userEmail"));
            profile.setDescription(jsonObject.getString("userId"));
            profile.setMerchantCustomerId(StringUtils.substring(jsonObject.getString("userCompleteName"), 0, 20));
          }
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
      BigDecimal productPrice = companyProduct.getPrice();

      if(siteUser != null && productPrice != null && productPrice.compareTo(BigDecimal.ZERO) > 0) {
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
              else {
                Exception ex = new RuntimeException(Optional.ofNullable(message).map(m -> String.format("%s %s, %s, userName: %s, userFullName: %s", m.getCode(), m.getText(), customerProfileId, siteUser.getUserName(), siteUser.getUserFullName())).orElse("GetHostedProfilePageRequest failed. "));
                LOG.error("Error setting up authorize.net payment", ex);
              }
            }
          }

          if(profilePageResponse != null) {
            GetHostedPaymentPageRequest hostedPaymentPageRequest = new GetHostedPaymentPageRequest();
            hostedPaymentPageRequest.setMerchantAuthentication(merchantAuthenticationType);
  
            ArrayOfSetting hostedPaymentSettings = new ArrayOfSetting();
            SettingType settingType = new SettingType();
            JsonObject hostedPaymentReturnOptions = new JsonObject().put("showReceipt", true);
            // if(!siteBaseUrl.startsWith("http://localhost")) {
              // hostedPaymentReturnOptions.put("url", companyProduct.getUserPage());
              // hostedPaymentReturnOptions.put("cancelUrl", companyProduct.getDisplayPage());
            // }
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
            transactionRequest.setAmount(productPrice);
  
            ArrayOfLineItem lineItems = new ArrayOfLineItem();
            LineItemType lineItem = new LineItemType();
            lineItem.setItemId(StringUtils.truncate(companyProduct.getPageId(), 31));
            lineItem.setDescription(StringUtils.truncate(companyProduct.getDescription(), 255));
            lineItem.setName(StringUtils.truncate(companyProduct.getName(), 31));
            lineItem.setTotalAmount(companyProduct.getPrice());
            lineItem.setQuantity(BigDecimal.ONE);
            lineItem.setUnitPrice(companyProduct.getPrice());
            lineItems.getLineItem().add(lineItem);
            DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US);
            LocalDate now = LocalDate.now();
            LocalDate chargeEndDate = LocalDate.now();
            CustomerProfilePaymentType profile = new CustomerProfilePaymentType();
            profile.setCustomerProfileId(customerProfileId);
            transactionRequest.setProfile(profile);
            transactionRequest.setLineItems(lineItems);
            OrderType order = new OrderType();
            order.setDescription(StringUtils.truncate(String.format("%s bought %s $%s payment from %s %s on %s", siteUser.getUserName(), companyProduct.getPageId(), companyProduct.getPrice(), siteUser.getUserFullName(), siteUser.getUserEmail(), fd.format(chargeEndDate)), 255));
            transactionRequest.setOrder(order);
            hostedPaymentPageRequest.setTransactionRequest(transactionRequest);
  
            GetHostedPaymentPageController hostedPaymentController = new GetHostedPaymentPageController(hostedPaymentPageRequest);
            GetHostedPaymentPageController.setEnvironment(Environment.valueOf(authorizeEnvironment));
            GetHostedPaymentPageResponse hostedPaymentResponse = null;
            hostedPaymentController.execute();
            if(hostedPaymentController.getErrorResponse() != null) {
              Exception ex = new RuntimeException(String.format("Failed to set up hosted payment controller: %s", hostedPaymentController.getResults().toString()));
              LOG.error("Failed to execute hosted payment controller", ex);
              promise.fail(ex);
            } else {
              hostedPaymentResponse = hostedPaymentController.getApiResponse();
              if(MessageTypeEnum.ERROR.equals(hostedPaymentResponse.getMessages().getResultCode())) {
                Exception ex = new RuntimeException(String.format("Failed to set up hosted payment controller: %s", hostedPaymentResponse.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("GetHostedPaymentPageRequest failed. ")));
                LOG.error("Error response from hosted payment controller", ex);
                promise.fail(ex);
              } else {
                ctx.put("hostedPaymentResponseToken", hostedPaymentResponse.getToken());
                promise.complete();
              }
            }
          } else {
            Exception ex = new RuntimeException(String.format("Failed to set up profilePageResponse: %s", profilePageResponse));
            LOG.error("Failed profile page response", ex);
            promise.fail(ex);
          }
        } else {
          promise.complete();
        }
      } else {
        promise.complete();
      }
    } catch(Throwable ex) {
      promise.fail(new RuntimeException("Failed to set up authorize.net payment"));
    }
  }
}
