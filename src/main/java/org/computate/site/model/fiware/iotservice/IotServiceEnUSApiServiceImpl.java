package org.computate.site.model.fiware.iotservice;


import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;

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
public class IotServiceEnUSApiServiceImpl extends IotServiceEnUSGenApiServiceImpl {

	@Override
	public Future<Void> persistIotService(IotService iotService, Boolean patch) {
		Promise<Void> promise = Promise.promise();
		try {
			super.persistIotService(iotService, patch).onSuccess(a -> {
				if(patch) {
					JsonObject body = new JsonObject();
					body.put("cbHost", iotService.getCbHost());
					body.put("entity_type", iotService.getEntityType());
					body.put("attributes", iotService.getAttributes());
					webClient.put(
							config.getInteger(ComputateConfigKeys.IOTAGENT_NORTH_PORT)
							, config.getString(ComputateConfigKeys.IOTAGENT_HOST_NAME)
							, "/iot/services"
							)
							.addQueryParam("apikey", iotService.getApiKey())
							.addQueryParam("resource", iotService.getResource())
							.ssl(config.getBoolean(ComputateConfigKeys.IOTAGENT_SSL))
							.putHeader("Content-Type", "application/json")
							.putHeader("Fiware-Service", iotService.getNgsildTenant())
							.putHeader("Fiware-ServicePath", iotService.getNgsildPath())
							.putHeader("NGSILD-Tenant", iotService.getNgsildTenant())
							.putHeader("NGSILD-Path", iotService.getNgsildPath())
							.putHeader("Cache-Control", "no-cache")
							.sendJsonObject(body)
							.expecting(HttpResponseExpectation.SC_NO_CONTENT).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("postIotServiceFuture failed. "), ex);
						promise.fail(ex);
					});
				} else {
					JsonObject serviceBody = new JsonObject();
					serviceBody.put("apikey", iotService.getApiKey());
					serviceBody.put("resource", iotService.getResource());
					serviceBody.put("cbHost", iotService.getCbHost());
					serviceBody.put("entity_type", iotService.getEntityType());
					serviceBody.put("attributes", iotService.getAttributes());
					JsonObject body = new JsonObject().put("services", new JsonArray().add(serviceBody));
					webClient.post(
							config.getInteger(ComputateConfigKeys.IOTAGENT_NORTH_PORT)
							, config.getString(ComputateConfigKeys.IOTAGENT_HOST_NAME)
							, "/iot/services"
							)
							.ssl(config.getBoolean(ComputateConfigKeys.IOTAGENT_SSL))
							.putHeader("Content-Type", "application/json")
							.putHeader("Fiware-Service", iotService.getNgsildTenant())
							.putHeader("Fiware-ServicePath", iotService.getNgsildPath())
							.putHeader("NGSILD-Tenant", iotService.getNgsildTenant())
							.putHeader("NGSILD-Path", iotService.getNgsildPath())
							.putHeader("Cache-Control", "no-cache")
							.sendJsonObject(body)
							.expecting(HttpResponseExpectation.SC_CREATED).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("postIotServiceFuture failed. "), ex);
						promise.fail(ex);
					});
				}
			}).onFailure(ex -> {
				LOG.error(String.format("patchIotServiceFuture failed. "), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error(String.format("postIotServiceFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public Future<Void> sqlDELETEIotService(IotService o) {
		Promise<Void> promise = Promise.promise();
		super.sqlDELETEIotService(o).onSuccess(a -> {
			try {
				webClient.delete(
						config.getInteger(ComputateConfigKeys.IOTAGENT_NORTH_PORT)
						, config.getString(ComputateConfigKeys.IOTAGENT_HOST_NAME)
						, "/iot/services"
						)
						.addQueryParam("apikey", o.getApiKey())
						.addQueryParam("resource", o.getResource())
						.ssl(config.getBoolean(ComputateConfigKeys.IOTAGENT_SSL))
						.putHeader("Content-Type", "application/json")
						.putHeader("Fiware-Service", o.getNgsildTenant())
						.putHeader("Fiware-ServicePath", o.getNgsildPath())
						.putHeader("NGSILD-Tenant", o.getNgsildTenant())
						.putHeader("NGSILD-Path", o.getNgsildPath())
						.putHeader("Cache-Control", "no-cache")
						.send()
						.expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND.or(HttpResponseExpectation.SC_BAD_REQUEST.or(HttpResponseExpectation.SC_INTERNAL_SERVER_ERROR)))).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("postIotServiceFuture failed. "), ex);
					promise.fail(ex);
				});
			} catch(Throwable ex) {
				LOG.error(String.format("postIotServiceFuture failed. "), ex);
				promise.fail(ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("patchIotServiceFuture failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}
}
