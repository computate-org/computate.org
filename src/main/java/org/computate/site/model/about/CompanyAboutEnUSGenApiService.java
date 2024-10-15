package org.computate.site.model.about;

import io.vertx.ext.web.client.WebClient;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.serviceproxy.ServiceBinder;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.api.service.WebApiServiceGen;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import com.hubspot.jinjava.Jinjava;
import io.vertx.core.WorkerExecutor;
import io.vertx.pgclient.PgPool;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.authorization.AuthorizationProvider;

/**
 * Translate: false
 * Gen: false
 * Generated: true
 **/
@WebApiServiceGen
@ProxyGen
public interface CompanyAboutEnUSGenApiService {
	static CompanyAboutEnUSApiServiceImpl registerService(Vertx vertx, JsonObject config, WorkerExecutor workerExecutor, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, PgPool pgPool, KafkaProducer<String, String> kafkaProducer, MqttClient mqttClient, AmqpSender amqpSender, RabbitMQClient rabbitmqClient, WebClient webClient, OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider, Jinjava jinjava) {
		CompanyAboutEnUSApiServiceImpl api =  new CompanyAboutEnUSApiServiceImpl(vertx, config, workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, oauth2AuthenticationProvider, authorizationProvider, jinjava);
		new ServiceBinder(vertx).setAddress(CompanyAbout.getClassApiAddress()).register(CompanyAboutEnUSGenApiService.class, api);
		return api;
	}

	public void searchCompanyAbout(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void getCompanyAbout(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void patchCompanyAbout(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void patchCompanyAboutFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void postCompanyAbout(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void postCompanyAboutFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void putimportCompanyAbout(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void putimportCompanyAboutFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void searchpageCompanyAboutId(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
	public void searchpageCompanyAbout(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
}
