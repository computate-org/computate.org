package org.computate.site.user;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.computate.site.config.ConfigKeys;
import org.computate.site.request.SiteRequest;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.request.ComputateSiteRequest;

import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import net.authorize.Environment;
import net.authorize.api.contract.v1.CreateCustomerProfileRequest;
import net.authorize.api.contract.v1.CreateCustomerProfileResponse;
import net.authorize.api.contract.v1.CustomerProfileType;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.controller.CreateCustomerProfileController;
import net.authorize.api.controller.GetTransactionListForCustomerController;
import net.authorize.api.controller.base.ApiOperationBase;

import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class SiteUserEnUSApiServiceImpl extends SiteUserEnUSGenApiServiceImpl {

  @Override
  public Boolean userDefine(ComputateSiteRequest siteRequest, JsonObject jsonObject, Boolean patch) {
    String sessionIdBefore = siteRequest.getSessionIdBefore();
    String sessionId = siteRequest.getSessionId();

// 		SearchList<SchoolEnrollment> enrollmentList = new SearchList<SchoolEnrollment>();
// 		if(sessionIdBefore != null) {
// 			enrollmentList.setStore(true);
// 			enrollmentList.setQuery("*:*");
// 			enrollmentList.setC(SchoolEnrollment.class);
// 			enrollmentList.setSiteRequest_(siteRequest);
// 			enrollmentList.addFilterQuery(
// 					"sessionId_indexed_string:" + ClientUtils.escapeQueryChars(sessionIdBefore) 
// 					+ " OR sessionId_indexed_string:" + ClientUtils.escapeQueryChars(sessionId)
// 					+ " OR enrollmentEmails_indexed_strings:" + ClientUtils.escapeQueryChars(StringUtils.lowerCase(jsonObject.getString(patch ? "setUserEmail" : "userEmail")))
// 					);
// //			enrollmentList.addFilterQuery("!userId_indexed_string:[* TO *]");
// 			enrollmentList.initDeepForClass(siteRequest);
// 			for(SchoolEnrollment enrollment : enrollmentList.getList()) {
// 				if(patch)
// 					jsonObject.put("addEnrollmentKeys", enrollment.getPk().toString());
// 				else
// 					jsonObject.put("enrollmentKeys", new JsonArray().add(enrollment.getPk().toString()));
// 			}
// 		}

    Boolean defineProfile = userSiteUserDefineProfile(siteRequest, jsonObject, patch);
    return defineProfile;
    // if(defineProfile) 
    //   return true;
    // else 
    //   return enrollmentList.size() > 0;
  }

  public Boolean userSiteUserDefineProfile(ComputateSiteRequest siteRequest, JsonObject jsonObject, Boolean patch) {
    String authorizeApiLoginId = config.getString(ConfigKeys.AUTHORIZE_NET_API_LOGIN_ID);
    String authorizeTransactionKey = config.getString(ConfigKeys.AUTHORIZE_NET_TRANSACTION_KEY);
    String authorizeEnvironment = config.getString(ConfigKeys.AUTHORIZE_NET_ENVIRONMENT);

    if(Optional.ofNullable(siteRequest.getServiceRequest()).map(serviceRequest -> serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() == 0L
        && StringUtils.isNotBlank(authorizeEnvironment) && authorizeApiLoginId != null && authorizeTransactionKey != null) {
      String customerProfileId;
      if(patch)
        customerProfileId = jsonObject.getString("setCustomerProfileId");
      else
        customerProfileId = jsonObject.getString("customerProfileId");
  
      if(customerProfileId == null) {
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
            customerProfileId = matcher.group(1);
          }
          else {
            LOG.error(response.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("CreateCustomerProfileRequest failed. "));
          }
        }
        else {
          customerProfileId = response.getCustomerProfileId();
        }
        if(patch)
          jsonObject.put("setCustomerProfileId", customerProfileId);
        else
          jsonObject.put("customerProfileId", customerProfileId);
        return true;
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }
}
