package org.computate.site.model.fiware.weatherobserved;

import java.util.List;
import java.util.Optional;

import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;

import com.hubspot.jinjava.Jinjava;

import io.vertx.amqp.AmqpSender;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.pgclient.PgPool;
import io.vertx.rabbitmq.RabbitMQClient;

/**
 * Translate: false
 **/
public class WeatherObservedEnUSApiServiceImpl extends WeatherObservedEnUSGenApiServiceImpl {

	public WeatherObservedEnUSApiServiceImpl(EventBus eventBus, JsonObject config, WorkerExecutor workerExecutor, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, PgPool pgPool, KafkaProducer<String, String> kafkaProducer, MqttClient mqttClient, AmqpSender amqpSender, RabbitMQClient rabbitmqClient, WebClient webClient, OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider, Jinjava jinjava) {
		super(eventBus, config, workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, oauth2AuthenticationProvider, authorizationProvider, jinjava);
	}

	@Override
	public Future<Void> persistWeatherObserved(WeatherObserved weatherObserved, Boolean patch) {
		Promise<Void> promise = Promise.promise();
		super.persistWeatherObserved(weatherObserved, patch).onSuccess(a -> {
			iotagentCreateDevice(weatherObserved, patch).onSuccess(b -> {
				iotagentCreateSubscription(weatherObserved, patch).onSuccess(c -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("patchIotServiceFuture failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("patchIotServiceFuture failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("patchIotServiceFuture failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> iotagentCreateDevice(WeatherObserved weatherObserved, Boolean patch) {
		Promise<Void> promise = Promise.promise();
		try {
			String entityName = weatherObserved.getName();
			JsonObject serviceBody = new JsonObject();
			JsonArray attributes = new JsonArray();
			List<String> vars = WeatherObserved.varsFqForClass();
			for (String var : vars) {
				String ngsiType = WeatherObserved.ngsiType(var);
				if (ngsiType != null) {
					attributes.add(new JsonObject()
							.put("object_id", var)
							.put("name", Optional.ofNullable(WeatherObserved.displayNameWeatherObserved(var)).orElse(var))
							.put("type", ngsiType));
				}
			}

			serviceBody.put("attributes", attributes);
			if(patch) {
				webClient.put(
						config.getInteger(ComputateConfigKeys.IOTAGENT_NORTH_PORT)
						, config.getString(ComputateConfigKeys.IOTAGENT_HOST_NAME)
						, String.format("/iot/devices/%s", urlEncode(entityName))
						)
						.ssl(config.getBoolean(ComputateConfigKeys.IOTAGENT_SSL))
						.putHeader("Content-Type", "application/json")
						.putHeader("Fiware-Service", weatherObserved.getNgsildTenant())
						.putHeader("Fiware-ServicePath", weatherObserved.getNgsildPath())
						.putHeader("NGSILD-Tenant", weatherObserved.getNgsildTenant())
						.putHeader("NGSILD-Path", weatherObserved.getNgsildPath())
						.putHeader("Cache-Control", "no-cache")
						.sendJsonObject(serviceBody)
						.expecting(HttpResponseExpectation.SC_NO_CONTENT).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("postIotServiceFuture failed. "), ex);
					promise.fail(ex);
				});
			} else {
				JsonObject body = new JsonObject().put("devices", new JsonArray().add(serviceBody));
				serviceBody.put("device_id", entityName);
				serviceBody.put("entity_name", entityName);
				serviceBody.put("entity_type", WeatherObserved.CLASS_SIMPLE_NAME);
				serviceBody.put("transport", config.getString(ComputateConfigKeys.IOTAGENT_TRANSPORT));
				webClient.post(
						config.getInteger(ComputateConfigKeys.IOTAGENT_NORTH_PORT)
						, config.getString(ComputateConfigKeys.IOTAGENT_HOST_NAME)
						, "/iot/devices"
						)
						.ssl(config.getBoolean(ComputateConfigKeys.IOTAGENT_SSL))
						.putHeader("Content-Type", "application/json")
						.putHeader("Fiware-Service", weatherObserved.getNgsildTenant())
						.putHeader("Fiware-ServicePath", weatherObserved.getNgsildPath())
						.putHeader("NGSILD-Tenant", weatherObserved.getNgsildTenant())
						.putHeader("NGSILD-Path", weatherObserved.getNgsildPath())
						.putHeader("Cache-Control", "no-cache")
						.sendJsonObject(body)
						.expecting(HttpResponseExpectation.SC_CREATED).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("postIotServiceFuture failed. "), ex);
					promise.fail(ex);
				});
			}
		} catch(Throwable ex) {
			LOG.error(String.format("postIotServiceFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> iotagentCreateSubscription(WeatherObserved weatherObserved, Boolean patch) {
		Promise<Void> promise = Promise.promise();
		try {
			String entityName = weatherObserved.getName();
			String entityType = WeatherObserved.CLASS_SIMPLE_NAME;
			String subscriptionName = String.format("urn:subscription:%s:%s", entityType, entityName);
			String subscriptionUrl = String.format("%s/ngsi-ld/subscription", config.getString(ComputateConfigKeys.SITE_BASE_URL));
			String ngsildContext = String.format("https://raw.githubusercontent.com/%s/%s/main/fiware/context.jsonld", config.getString(ComputateConfigKeys.GITHUB_ORG), config.getString(ComputateConfigKeys.SITE_STATIC_NAME));
			JsonObject body = new JsonObject();

			body.put("id", subscriptionName);
			body.put("type", "Subscription");
			body.put("description", entityName);
			body.put("entities", new JsonArray().add(new JsonObject()
					.put("id", entityName)
					.put("type", entityType)
					));
			body.put("notification", new JsonObject()
					.put("endpoint", new JsonObject()
							.put("uri", subscriptionUrl)
							.put("accept", "application/json")
							.put("receiverInfo", new JsonArray()
									.add(new JsonObject().put("Link", String.format("<%s>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"", ngsildContext)))
									.add(new JsonObject().put("Fiware-Service", weatherObserved.getNgsildTenant()))
									.add(new JsonObject().put("Fiware-ServicePath", weatherObserved.getNgsildPath()))
									.add(new JsonObject().put("NGSILD-Tenant", weatherObserved.getNgsildTenant()))
									.add(new JsonObject().put("NGSILD-Path", weatherObserved.getNgsildPath()))
									.add(new JsonObject().put("Computate-API", WeatherObserved.CLASS_API_ADDRESS_WeatherObserved))
									.add(new JsonObject().put("Computate-ClassSimpleName", WeatherObserved.CLASS_SIMPLE_NAME))
									)
							)
					);
			body.put("@context", new JsonArray()
					.add(ngsildContext)
					.add("https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld")
					);
			if(patch) {
				webClient.patch(
						config.getInteger(ComputateConfigKeys.CONTEXT_BROKER_PORT)
						, config.getString(ComputateConfigKeys.CONTEXT_BROKER_HOST_NAME)
						, String.format("/ngsi-ld/v1/subscriptions/%s", urlEncode(subscriptionName))
						)
						.ssl(config.getBoolean(ComputateConfigKeys.CONTEXT_BROKER_SSL))
						.putHeader("Content-Type", "application/ld+json")
						.putHeader("Fiware-Service", weatherObserved.getNgsildTenant())
						.putHeader("Fiware-ServicePath", weatherObserved.getNgsildPath())
						.putHeader("NGSILD-Tenant", weatherObserved.getNgsildTenant())
						.putHeader("NGSILD-PATH", weatherObserved.getNgsildPath())
						.putHeader("Accept", "*/*")
						.sendJsonObject(body)
						.expecting(HttpResponseExpectation.SC_NO_CONTENT).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("postIotServiceFuture failed. "), ex);
					promise.fail(ex);
				});
			} else {
				webClient.post(
						config.getInteger(ComputateConfigKeys.CONTEXT_BROKER_PORT)
						, config.getString(ComputateConfigKeys.CONTEXT_BROKER_HOST_NAME)
						, "/ngsi-ld/v1/subscriptions"
						)
						.ssl(config.getBoolean(ComputateConfigKeys.CONTEXT_BROKER_SSL))
						.putHeader("Content-Type", "application/ld+json")
						.putHeader("Fiware-Service", weatherObserved.getNgsildTenant())
						.putHeader("Fiware-ServicePath", weatherObserved.getNgsildPath())
						.putHeader("NGSILD-Tenant", weatherObserved.getNgsildTenant())
						.putHeader("NGSILD-PATH", weatherObserved.getNgsildPath())
						.putHeader("Accept", "*/*")
						.sendJsonObject(body)
						.expecting(HttpResponseExpectation.SC_CREATED).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("postIotServiceFuture failed. "), ex);
					promise.fail(ex);
				});
			}
		} catch(Throwable ex) {
			LOG.error(String.format("postIotServiceFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public Future<Void> sqlDELETEWeatherObserved(WeatherObserved o) {
		Promise<Void> promise = Promise.promise();
		super.sqlDELETEWeatherObserved(o).onSuccess(a -> {
			iotagentDeleteDevice(o).onSuccess(b -> {
				iotagentDeleteSubscription(o).onSuccess(c -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETEWeatherObserved failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETEWeatherObserved failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("sqlDELETEWeatherObserved failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> iotagentDeleteDevice(WeatherObserved weatherObserved) {
		Promise<Void> promise = Promise.promise();
		try {
			String entityName = weatherObserved.getName();

			webClient.delete(
					config.getInteger(ComputateConfigKeys.IOTAGENT_NORTH_PORT)
					, config.getString(ComputateConfigKeys.IOTAGENT_HOST_NAME)
					, String.format("/iot/devices/%s", urlEncode(entityName))
					)
					.ssl(config.getBoolean(ComputateConfigKeys.IOTAGENT_SSL))
					.putHeader("Content-Type", "application/json")
					.putHeader("Fiware-Service", weatherObserved.getNgsildTenant())
					.putHeader("Fiware-ServicePath", weatherObserved.getNgsildPath())
					.putHeader("NGSILD-Tenant", weatherObserved.getNgsildTenant())
					.putHeader("NGSILD-Path", weatherObserved.getNgsildPath())
					.putHeader("Cache-Control", "no-cache")
					.send()
					.expecting(HttpResponseExpectation.SC_NO_CONTENT).onSuccess(b -> {
				promise.complete();
			}).onFailure(ex -> {
				LOG.error(String.format("postIotServiceFuture failed. "), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error(String.format("postIotServiceFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> iotagentDeleteSubscription(WeatherObserved weatherObserved) {
		Promise<Void> promise = Promise.promise();
		try {
			String entityName = weatherObserved.getName();
			String entityType = WeatherObserved.CLASS_SIMPLE_NAME;
			String subscriptionName = String.format("urn:subscription:%s:%s", entityType, entityName);

			webClient.delete(
					config.getInteger(ComputateConfigKeys.CONTEXT_BROKER_PORT)
					, config.getString(ComputateConfigKeys.CONTEXT_BROKER_HOST_NAME)
					, String.format("/ngsi-ld/v1/subscriptions/%s", urlEncode(subscriptionName))
					)
					.ssl(config.getBoolean(ComputateConfigKeys.CONTEXT_BROKER_SSL))
					.putHeader("Content-Type", "application/ld+json")
					.putHeader("Fiware-Service", weatherObserved.getNgsildTenant())
					.putHeader("Fiware-ServicePath", weatherObserved.getNgsildPath())
					.putHeader("NGSILD-Tenant", weatherObserved.getNgsildTenant())
					.putHeader("NGSILD-Path", weatherObserved.getNgsildPath())
					.putHeader("Accept", "*/*")
					.send()
					.expecting(HttpResponseExpectation.SC_NO_CONTENT).onSuccess(b -> {
				promise.complete();
			}).onFailure(ex -> {
				LOG.error(String.format("postIotServiceFuture failed. "), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error(String.format("postIotServiceFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
}
