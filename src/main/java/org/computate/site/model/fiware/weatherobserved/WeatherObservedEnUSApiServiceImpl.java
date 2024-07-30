package org.computate.site.model.fiware.weatherobserved;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;
import io.vertx.kafka.client.producer.KafkaProducer;
import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class WeatherObservedEnUSApiServiceImpl extends WeatherObservedEnUSGenApiServiceImpl {

	public WeatherObservedEnUSApiServiceImpl(EventBus eventBus, JsonObject config, WorkerExecutor workerExecutor, PgPool pgPool, KafkaProducer<String, String> kafkaProducer, WebClient webClient, OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider, Jinjava jinjava) {
		super(eventBus, config, workerExecutor, pgPool, kafkaProducer, webClient, oauth2AuthenticationProvider, authorizationProvider, jinjava);
	}
}
