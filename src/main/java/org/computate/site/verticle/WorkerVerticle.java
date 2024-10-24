
package org.computate.site.verticle;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import org.computate.search.tool.TimeTool;
import org.computate.search.tool.XmlTool;
import org.computate.vertx.api.ApiCounter;
import org.computate.vertx.api.ApiRequest;
import org.computate.vertx.api.ApiCounter;
import org.computate.vertx.api.ApiRequest;
import org.computate.site.config.ConfigKeys;
import org.computate.site.request.SiteRequest;
import org.computate.site.page.SitePage;
import org.computate.site.page.SitePageEnUSApiServiceImpl;
import org.computate.site.model.about.CompanyAbout;
import org.computate.site.model.about.CompanyAboutEnUSApiServiceImpl;
import org.computate.site.model.casestudy.CaseStudy;
import org.computate.site.model.casestudy.CaseStudyEnUSApiServiceImpl;
import org.computate.site.model.course.CompanyCourse;
import org.computate.site.model.course.CompanyCourseEnUSApiServiceImpl;
import org.computate.site.page.SitePage;
import org.computate.site.page.SitePageEnUSApiServiceImpl;
import org.computate.site.model.product.CompanyProduct;
import org.computate.site.model.product.CompanyProductEnUSApiServiceImpl;
import org.computate.site.model.event.CompanyEvent;
import org.computate.site.model.event.CompanyEventEnUSApiServiceImpl;
import org.computate.site.model.webinar.CompanyWebinar;
import org.computate.site.model.webinar.CompanyWebinarEnUSApiServiceImpl;
import org.computate.site.model.service.CompanyService;
import org.computate.site.model.service.CompanyServiceEnUSApiServiceImpl;
import org.computate.site.model.research.CompanyResearch;
import org.computate.site.model.research.CompanyResearchEnUSApiServiceImpl;
import org.computate.site.model.website.CompanyWebsite;
import org.computate.site.model.website.CompanyWebsiteEnUSApiServiceImpl;
import org.computate.site.model.fiware.iotservice.IotService;
import org.computate.site.model.fiware.iotservice.IotServiceEnUSApiServiceImpl;
import org.computate.site.model.fiware.weatherobserved.WeatherObserved;
import org.computate.site.model.fiware.weatherobserved.WeatherObservedEnUSApiServiceImpl;
import org.computate.vertx.api.ApiCounter;
import org.computate.vertx.api.ApiRequest;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.handlebars.AuthHelpers;
import org.computate.vertx.handlebars.DateHelpers;
import org.computate.vertx.handlebars.SiteHelpers;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.PatternFilenameFilter;
import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.loader.FileLocator;

import io.vertx.config.yaml.YamlProcessor;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.TokenCredentials;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpClient;
import io.vertx.amqp.AmqpClientOptions;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.rabbitmq.RabbitMQOptions;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import io.vertx.amqp.AmqpMessage;
import io.vertx.amqp.AmqpMessageBuilder;
import io.vertx.amqp.AmqpSenderOptions;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Cursor;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowStream;
import io.vertx.sqlclient.SqlConnection;
import org.computate.site.user.SiteUser;
import org.computate.site.user.SiteUserEnUSApiServiceImpl;
import org.computate.site.user.SiteUserEnUSGenApiService;

/**
 */
public class WorkerVerticle extends WorkerVerticleGen<AbstractVerticle> {
	private static final Logger LOG = LoggerFactory.getLogger(WorkerVerticle.class);

	public static final Integer FACET_LIMIT = 100;

	public final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss VV");

	private KafkaProducer<String, String> kafkaProducer;

	private MqttClient mqttClient;

	private AmqpClient amqpClient;

	private AmqpSender amqpSender;

	private RabbitMQClient rabbitmqClient;

	private ComputateOAuth2AuthHandlerImpl oauth2AuthHandler = null;

	public ComputateOAuth2AuthHandlerImpl getOauth2AuthHandler() {
		return oauth2AuthHandler;
	}

	public void setOauth2AuthHandler(ComputateOAuth2AuthHandlerImpl oauth2AuthHandler) {
		this.oauth2AuthHandler = oauth2AuthHandler;
	}

	/**
	 * A io.vertx.ext.jdbc.JDBCClient for connecting to the relational database PostgreSQL. 
	 **/
	private PgPool pgPool;

	public PgPool getPgPool() {
		return pgPool;
	}

	public void setPgPool(PgPool pgPool) {
		this.pgPool = pgPool;
	}

	private WebClient webClient;

	WorkerExecutor workerExecutor;

	Integer commitWithin;

	Jinjava jinjava;

	/**	
	 *	This is called by Vert.x when the verticle instance is deployed. 
	 *	Initialize a new site context object for storing information about the entire site in English. 
	 *	Setup the startPromise to handle the configuration steps and starting the server. 
	 **/
	@Override()
	public void start(Promise<Void> startPromise) throws Exception, Exception {
		commitWithin = config().getInteger(ConfigKeys.SOLR_WORKER_COMMIT_WITHIN_MILLIS);

		try {
			configureData().onSuccess(a -> 
				configureJinjava().onSuccess(b -> 
					configureWebClient().onSuccess(c -> 
						configureSharedWorkerExecutor().onSuccess(d -> 
							configureKafka().onSuccess(e -> 
								configureMqtt().onSuccess(f -> 
									configureAmqp().onSuccess(g -> 
										configureRabbitmq().onSuccess(h -> 
											authorizeData().onSuccess(i -> 
												importData().onSuccess(j -> 
													startPromise.complete()
												).onFailure(ex -> startPromise.fail(ex))
											).onFailure(ex -> startPromise.fail(ex))
										).onFailure(ex -> startPromise.fail(ex))
									).onFailure(ex -> startPromise.fail(ex))
								).onFailure(ex -> startPromise.fail(ex))
							).onFailure(ex -> startPromise.fail(ex))
						).onFailure(ex -> startPromise.fail(ex))
					).onFailure(ex -> startPromise.fail(ex))
				).onFailure(ex -> startPromise.fail(ex))
			).onFailure(ex -> startPromise.fail(ex));
		} catch (Exception ex) {
			LOG.error("Couldn't start verticle. ", ex);
		}
	}

	/**
	 **/
	public Future<Jinjava> configureJinjava() {
		Promise<Jinjava> promise = Promise.promise();

		try {
			jinjava = new Jinjava();
			String templatePath = config().getString(ConfigKeys.TEMPLATE_PATH);
			if(!StringUtils.isBlank(templatePath))
				jinjava.setResourceLocator(new FileLocator(new File(templatePath)));
			promise.complete(jinjava);
		} catch(Exception ex) {
			LOG.error("Jinjava failed to initialize.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**	
	 **/
	private Future<Void> configureWebClient() {
		Promise<Void> promise = Promise.promise();

		try {
			Boolean sslVerify = config().getBoolean(ConfigKeys.SSL_VERIFY);
			webClient = WebClient.create(vertx, new WebClientOptions().setVerifyHost(sslVerify).setTrustAll(!sslVerify));
			promise.complete();
		} catch(Exception ex) {
			LOG.error("Unable to configure site context. ", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**	
	 * 
	 * Val.ConnectionError.enUS:Could not open the database client connection. 
	 * Val.ConnectionSuccess.enUS:The database client connection was successful. 
	 * 
	 * Val.InitError.enUS:Could not initialize the database tables. 
	 * Val.InitSuccess.enUS:The database tables were created successfully. 
	 * 
	 *	Configure shared database connections across the cluster for massive scaling of the application. 
	 *	Return a promise that configures a shared database client connection. 
	 *	Load the database configuration into a shared io.vertx.ext.jdbc.JDBCClient for a scalable, clustered datasource connection pool. 
	 *	Initialize the database tables if not already created for the first time. 
	 **/
	private Future<Void> configureData() {
		Promise<Void> promise = Promise.promise();
		try {
			PgConnectOptions pgOptions = new PgConnectOptions();
			Integer jdbcMaxPoolSize = config().getInteger(ConfigKeys.DATABASE_MAX_POOL_SIZE, 1);

			pgOptions.setPort(config().getInteger(ConfigKeys.DATABASE_PORT));
			pgOptions.setHost(config().getString(ConfigKeys.DATABASE_HOST));
			pgOptions.setDatabase(config().getString(ConfigKeys.DATABASE_DATABASE));
			pgOptions.setUser(config().getString(ConfigKeys.DATABASE_USERNAME));
			pgOptions.setPassword(config().getString(ConfigKeys.DATABASE_PASSWORD));
			pgOptions.setIdleTimeout(config().getInteger(ConfigKeys.DATABASE_MAX_IDLE_TIME, 24));
			pgOptions.setIdleTimeoutUnit(TimeUnit.HOURS);
			pgOptions.setConnectTimeout(config().getInteger(ConfigKeys.DATABASE_CONNECT_TIMEOUT, 5000));

			PoolOptions poolOptions = new PoolOptions();
			poolOptions.setMaxSize(jdbcMaxPoolSize);
			poolOptions.setMaxWaitQueueSize(config().getInteger(ConfigKeys.DATABASE_MAX_WAIT_QUEUE_SIZE, 10));

			pgPool = PgPool.pool(vertx, pgOptions, poolOptions);

			LOG.info(configureDataInitSuccess);
			promise.complete();
		} catch (Exception ex) {
			LOG.error(configureDataInitError, ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**	
	 * Val.Fail.enUS:Could not configure the shared worker executor. 
	 * Val.Complete.enUS:The shared worker executor "{}" was configured successfully. 
	 * 
	 *	Configure a shared worker executor for running blocking tasks in the background. 
	 *	Return a promise that configures the shared worker executor. 
	 **/
	private Future<Void> configureSharedWorkerExecutor() {
		Promise<Void> promise = Promise.promise();
		try {
			String name = "WorkerVerticle-WorkerExecutor";
			Integer workerPoolSize = System.getenv(ConfigKeys.WORKER_POOL_SIZE) == null ? 5 : Integer.parseInt(System.getenv(ConfigKeys.WORKER_POOL_SIZE));
			Long vertxMaxWorkerExecuteTime = config().getLong(ConfigKeys.VERTX_MAX_WORKER_EXECUTE_TIME);
			workerExecutor = vertx.createSharedWorkerExecutor(name, workerPoolSize, vertxMaxWorkerExecuteTime, TimeUnit.SECONDS);
			LOG.info(configureSharedWorkerExecutorComplete, name);
			promise.complete();
		} catch (Exception ex) {
			LOG.error(configureSharedWorkerExecutorFail, ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**
	 * Val.Success.enUS:The Kafka producer was initialized successfully. 
	 **/
	public Future<KafkaProducer<String, String>> configureKafka() {
		Promise<KafkaProducer<String, String>> promise = Promise.promise();

		try {
			if(config().getBoolean(ConfigKeys.ENABLE_KAFKA)) {
				Map<String, String> kafkaConfig = new HashMap<>();
				kafkaConfig.put("bootstrap.servers", config().getString(ConfigKeys.KAFKA_BROKERS));
				kafkaConfig.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				kafkaConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				kafkaConfig.put("acks", "1");
				kafkaConfig.put("security.protocol", "SSL");
				kafkaConfig.put("ssl.keystore.type", config().getString(ConfigKeys.KAFKA_SSL_KEYSTORE_TYPE));
				kafkaConfig.put("ssl.keystore.location", config().getString(ConfigKeys.KAFKA_SSL_KEYSTORE_LOCATION));
				kafkaConfig.put("ssl.keystore.password", config().getString(ConfigKeys.KAFKA_SSL_KEYSTORE_PASSWORD));
				kafkaConfig.put("ssl.truststore.type", config().getString(ConfigKeys.KAFKA_SSL_TRUSTSTORE_TYPE));
				kafkaConfig.put("ssl.truststore.location", config().getString(ConfigKeys.KAFKA_SSL_TRUSTSTORE_LOCATION));
				kafkaConfig.put("ssl.truststore.password", config().getString(ConfigKeys.KAFKA_SSL_TRUSTSTORE_PASSWORD));

				kafkaProducer = KafkaProducer.createShared(vertx, config().getString(ConfigKeys.SITE_NAME), kafkaConfig);
				LOG.info(configureKafkaSuccess);
				promise.complete(kafkaProducer);
			} else {
				LOG.info(configureKafkaSuccess);
				promise.complete(null);
			}
		} catch(Exception ex) {
			LOG.error("Unable to configure site context. ", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**
	 **/
	public Future<MqttClient> configureMqtt() {
		Promise<MqttClient> promise = Promise.promise();

		try {
			if(BooleanUtils.isTrue(config().getBoolean(ConfigKeys.ENABLE_MQTT))) {
				try {
					mqttClient = MqttClient.create(vertx);
					mqttClient.connect(config().getInteger(ConfigKeys.MQTT_PORT), config().getString(ConfigKeys.MQTT_HOST)).onSuccess(a -> {
						try {
							LOG.info("The MQTT client was initialized successfully.");
							promise.complete(mqttClient);
						} catch(Exception ex) {
							LOG.error("The MQTT client failed to initialize.", ex);
							promise.fail(ex);
						}
					}).onFailure(ex -> {
						LOG.error("The MQTT client failed to initialize.", ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error("The MQTT client failed to initialize.", ex);
					promise.fail(ex);
				}
			} else {
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error("The MQTT client failed to initialize.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**
	 **/
	public Future<AmqpClient> configureAmqp() {
		Promise<AmqpClient> promise = Promise.promise();

		try {
			if(BooleanUtils.isTrue(config().getBoolean(ConfigKeys.ENABLE_AMQP))) {
				try {
					AmqpClientOptions options = new AmqpClientOptions()
							.setHost(config().getString(ConfigKeys.AMQP_HOST))
							.setPort(config().getInteger(ConfigKeys.AMQP_PORT))
							.setUsername(config().getString(ConfigKeys.AMQP_USER))
							.setPassword(config().getString(ConfigKeys.AMQP_PASSWORD))
							.setVirtualHost(config().getString(ConfigKeys.AMQP_VIRTUAL_HOST))
							;
					amqpClient = AmqpClient.create(vertx, options);
					amqpClient.connect().onSuccess(amqpConnection -> {
						try {
							AmqpSenderOptions senderOptions = new AmqpSenderOptions();
							amqpConnection
									.createSender("my-queue", senderOptions)
									.onSuccess(sender -> {
								this.amqpSender = sender;
								LOG.info("The AMQP client was initialized successfully.");
								promise.complete(amqpClient);
							}).onFailure(ex -> {
								LOG.error("The AMQP client failed to initialize.", ex);
								promise.fail(ex);
							});
						} catch(Exception ex) {
							LOG.error("The AMQP client failed to initialize.", ex);
							promise.fail(ex);
						}
					}).onFailure(ex -> {
						LOG.error("The AMQP client failed to initialize.", ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error("The AMQP client failed to initialize.", ex);
					promise.fail(ex);
				}
			} else {
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error("The AMQP client failed to initialize.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**
	 **/
	public Future<RabbitMQClient> configureRabbitmq() {
		Promise<RabbitMQClient> promise = Promise.promise();

		try {
			if(BooleanUtils.isTrue(config().getBoolean(ConfigKeys.ENABLE_RABBITMQ))) {
				try {
					RabbitMQOptions options = new RabbitMQOptions()
							.setHost(config().getString(ConfigKeys.RABBITMQ_HOST_NAME))
							.setPort(config().getInteger(ConfigKeys.RABBITMQ_PORT))
							.setUser(config().getString(ConfigKeys.RABBITMQ_USER))
							.setPassword(config().getString(ConfigKeys.RABBITMQ_PASSWORD))
							.setVirtualHost(config().getString(ConfigKeys.RABBITMQ_VIRTUAL_HOST))
							.setAutomaticRecoveryEnabled(true)
							;
					this.rabbitmqClient = RabbitMQClient.create(vertx, options);
					rabbitmqClient.start().onSuccess(a -> {
						LOG.info("The AMQP client was initialized successfully.");
						promise.complete(rabbitmqClient);
					}).onFailure(ex -> {
						LOG.error("The AMQP client failed to initialize.", ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error("The AMQP client failed to initialize.", ex);
					promise.fail(ex);
				}
			} else {
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error("The AMQP client failed to initialize.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**
	 * Description: Add Keycloak authorization resources, policies, and permissions for a data model. 
	 * Val.Fail.enUS: Adding Keycloak authorization resources, policies, and permissions failed. 
	 **/
	private Future<Void> authorizeData() {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = new SiteRequest();
			siteRequest.setConfig(config());
			siteRequest.setWebClient(webClient);
			siteRequest.initDeepSiteRequest(siteRequest);
			SiteUserEnUSApiServiceImpl apiSiteUser = new SiteUserEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyAboutEnUSApiServiceImpl apiCompanyAbout = new CompanyAboutEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CaseStudyEnUSApiServiceImpl apiCaseStudy = new CaseStudyEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyCourseEnUSApiServiceImpl apiCompanyCourse = new CompanyCourseEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			SitePageEnUSApiServiceImpl apiSitePage = new SitePageEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyProductEnUSApiServiceImpl apiCompanyProduct = new CompanyProductEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyEventEnUSApiServiceImpl apiCompanyEvent = new CompanyEventEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyWebinarEnUSApiServiceImpl apiCompanyWebinar = new CompanyWebinarEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyServiceEnUSApiServiceImpl apiCompanyService = new CompanyServiceEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyResearchEnUSApiServiceImpl apiCompanyResearch = new CompanyResearchEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyWebsiteEnUSApiServiceImpl apiCompanyWebsite = new CompanyWebsiteEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			IotServiceEnUSApiServiceImpl apiIotService = new IotServiceEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			WeatherObservedEnUSApiServiceImpl apiWeatherObserved = new WeatherObservedEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			apiSiteUser.createAuthorizationScopes().onSuccess(authToken -> {
				apiSiteUser.authorizeClientData(authToken, SiteUser.CLASS_SIMPLE_NAME, config().getString(ComputateConfigKeys.AUTH_CLIENT), new String[] { "GET", "PATCH" }).onSuccess(q1 -> {
					apiCompanyAbout.authorizeGroupData(authToken, CompanyAbout.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
							.onSuccess(q2 -> {
						apiCaseStudy.authorizeGroupData(authToken, CaseStudy.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
								.onSuccess(q3 -> {
							apiCompanyCourse.authorizeGroupData(authToken, CompanyCourse.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
									.onSuccess(q4 -> {
								apiSitePage.authorizeGroupData(authToken, SitePage.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
										.onSuccess(q5 -> {
									apiCompanyProduct.authorizeGroupData(authToken, CompanyProduct.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
											.onSuccess(q6 -> {
										apiCompanyEvent.authorizeGroupData(authToken, CompanyEvent.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
												.onSuccess(q7 -> {
											apiCompanyWebinar.authorizeGroupData(authToken, CompanyWebinar.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
													.onSuccess(q8 -> {
												apiCompanyService.authorizeGroupData(authToken, CompanyService.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
														.onSuccess(q9 -> {
													apiCompanyResearch.authorizeGroupData(authToken, CompanyResearch.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
															.onSuccess(q10 -> {
														apiCompanyWebsite.authorizeGroupData(authToken, CompanyWebsite.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
																.onSuccess(q11 -> {
															apiIotService.authorizeGroupData(authToken, IotService.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
																	.onSuccess(q12 -> {
																apiWeatherObserved.authorizeGroupData(authToken, WeatherObserved.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" })
																		.onSuccess(q13 -> {
																	LOG.info("authorize data complete");
																	promise.complete();
																}).onFailure(ex -> promise.fail(ex));
															}).onFailure(ex -> promise.fail(ex));
														}).onFailure(ex -> promise.fail(ex));
													}).onFailure(ex -> promise.fail(ex));
												}).onFailure(ex -> promise.fail(ex));
											}).onFailure(ex -> promise.fail(ex));
										}).onFailure(ex -> promise.fail(ex));
									}).onFailure(ex -> promise.fail(ex));
								}).onFailure(ex -> promise.fail(ex));
							}).onFailure(ex -> promise.fail(ex));
						}).onFailure(ex -> promise.fail(ex));
					}).onFailure(ex -> promise.fail(ex));
				}).onFailure(ex -> promise.fail(ex));
			}).onFailure(ex -> promise.fail(ex));
		} catch(Throwable ex) {
			LOG.error(authorizeDataFail, ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**
	 * Description: Import initial data
	 * Val.Skip.enUS:The data import is disabled. 
	 **/
	private Future<Void> importData() {
		Promise<Void> promise = Promise.promise();
		if(config().getBoolean(ConfigKeys.ENABLE_IMPORT_DATA)) {
			SiteRequest siteRequest = new SiteRequest();
			siteRequest.setConfig(config());
			siteRequest.setWebClient(webClient);
			siteRequest.initDeepSiteRequest(siteRequest);
			String templatePath = config().getString(ComputateConfigKeys.TEMPLATE_PATH);
			CompanyAboutEnUSApiServiceImpl apiCompanyAbout = new CompanyAboutEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CaseStudyEnUSApiServiceImpl apiCaseStudy = new CaseStudyEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyCourseEnUSApiServiceImpl apiCompanyCourse = new CompanyCourseEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			SitePageEnUSApiServiceImpl apiSitePage = new SitePageEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyProductEnUSApiServiceImpl apiCompanyProduct = new CompanyProductEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyEventEnUSApiServiceImpl apiCompanyEvent = new CompanyEventEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyWebinarEnUSApiServiceImpl apiCompanyWebinar = new CompanyWebinarEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyServiceEnUSApiServiceImpl apiCompanyService = new CompanyServiceEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyResearchEnUSApiServiceImpl apiCompanyResearch = new CompanyResearchEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			CompanyWebsiteEnUSApiServiceImpl apiCompanyWebsite = new CompanyWebsiteEnUSApiServiceImpl(vertx, config(), workerExecutor, oauth2AuthHandler, pgPool, kafkaProducer, mqttClient, amqpSender, rabbitmqClient, webClient, null, null, jinjava);
			apiCompanyAbout.importTimer(Paths.get(templatePath, "/en-us/about"), vertx, siteRequest, CompanyAbout.CLASS_SIMPLE_NAME, CompanyAbout.CLASS_API_ADDRESS_CompanyAbout).onSuccess(q1 -> {
				apiCaseStudy.importTimer(Paths.get(templatePath, "/en-us/case-study"), vertx, siteRequest, CaseStudy.CLASS_SIMPLE_NAME, CaseStudy.CLASS_API_ADDRESS_CaseStudy).onSuccess(q2 -> {
					apiCompanyCourse.importTimer(Paths.get(templatePath, "/en-us/course"), vertx, siteRequest, CompanyCourse.CLASS_SIMPLE_NAME, CompanyCourse.CLASS_API_ADDRESS_CompanyCourse).onSuccess(q3 -> {
						apiSitePage.importTimer(Paths.get(templatePath, "/en-us/article"), vertx, siteRequest, SitePage.CLASS_SIMPLE_NAME, SitePage.CLASS_API_ADDRESS_SitePage).onSuccess(q4 -> {
							apiCompanyProduct.importTimer(Paths.get(templatePath, "/en-us/product"), vertx, siteRequest, CompanyProduct.CLASS_SIMPLE_NAME, CompanyProduct.CLASS_API_ADDRESS_CompanyProduct).onSuccess(q5 -> {
								apiCompanyEvent.importTimer(Paths.get(templatePath, "/en-us/event"), vertx, siteRequest, CompanyEvent.CLASS_SIMPLE_NAME, CompanyEvent.CLASS_API_ADDRESS_CompanyEvent).onSuccess(q6 -> {
									apiCompanyWebinar.importTimer(Paths.get(templatePath, "/en-us/webinar"), vertx, siteRequest, CompanyWebinar.CLASS_SIMPLE_NAME, CompanyWebinar.CLASS_API_ADDRESS_CompanyWebinar).onSuccess(q7 -> {
										apiCompanyService.importTimer(Paths.get(templatePath, "/en-us/service"), vertx, siteRequest, CompanyService.CLASS_SIMPLE_NAME, CompanyService.CLASS_API_ADDRESS_CompanyService).onSuccess(q8 -> {
											apiCompanyResearch.importTimer(Paths.get(templatePath, "/en-us/research"), vertx, siteRequest, CompanyResearch.CLASS_SIMPLE_NAME, CompanyResearch.CLASS_API_ADDRESS_CompanyResearch).onSuccess(q9 -> {
												apiCompanyWebsite.importTimer(Paths.get(templatePath, "/en-us/website"), vertx, siteRequest, CompanyWebsite.CLASS_SIMPLE_NAME, CompanyWebsite.CLASS_API_ADDRESS_CompanyWebsite).onSuccess(q10 -> {
													LOG.info("data import complete");
													promise.complete();
												}).onFailure(ex -> promise.fail(ex));
											}).onFailure(ex -> promise.fail(ex));
										}).onFailure(ex -> promise.fail(ex));
									}).onFailure(ex -> promise.fail(ex));
								}).onFailure(ex -> promise.fail(ex));
							}).onFailure(ex -> promise.fail(ex));
						}).onFailure(ex -> promise.fail(ex));
					}).onFailure(ex -> promise.fail(ex));
				}).onFailure(ex -> promise.fail(ex));
			}).onFailure(ex -> promise.fail(ex));
		}
		else {
			LOG.info(importDataSkip);
			promise.complete();
		}
		return promise.future();
	}
}
