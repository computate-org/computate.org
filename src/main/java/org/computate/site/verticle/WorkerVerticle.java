
package org.computate.site.verticle;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
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

import org.yaml.snakeyaml.Yaml;
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
import org.computate.site.model.about.CompanyAbout;
import org.computate.site.model.about.CompanyAboutEnUSApiServiceImpl;
import org.computate.site.model.about.CompanyAboutEnUSGenApiService;
import org.computate.site.model.usecase.UseCase;
import org.computate.site.model.usecase.UseCaseEnUSApiServiceImpl;
import org.computate.site.model.usecase.UseCaseEnUSGenApiService;
import org.computate.site.model.course.CompanyCourse;
import org.computate.site.model.course.CompanyCourseEnUSApiServiceImpl;
import org.computate.site.model.course.CompanyCourseEnUSGenApiService;
import org.computate.site.page.SitePage;
import org.computate.site.page.SitePageEnUSApiServiceImpl;
import org.computate.site.page.SitePageEnUSGenApiService;
import org.computate.site.model.product.CompanyProduct;
import org.computate.site.model.product.CompanyProductEnUSApiServiceImpl;
import org.computate.site.model.product.CompanyProductEnUSGenApiService;
import org.computate.site.model.event.CompanyEvent;
import org.computate.site.model.event.CompanyEventEnUSApiServiceImpl;
import org.computate.site.model.event.CompanyEventEnUSGenApiService;
import org.computate.site.model.webinar.CompanyWebinar;
import org.computate.site.model.webinar.CompanyWebinarEnUSApiServiceImpl;
import org.computate.site.model.webinar.CompanyWebinarEnUSGenApiService;
import org.computate.site.model.service.CompanyService;
import org.computate.site.model.service.CompanyServiceEnUSApiServiceImpl;
import org.computate.site.model.service.CompanyServiceEnUSGenApiService;
import org.computate.site.model.research.CompanyResearch;
import org.computate.site.model.research.CompanyResearchEnUSApiServiceImpl;
import org.computate.site.model.research.CompanyResearchEnUSGenApiService;
import org.computate.site.model.website.CompanyWebsite;
import org.computate.site.model.website.CompanyWebsiteEnUSApiServiceImpl;
import org.computate.site.model.website.CompanyWebsiteEnUSGenApiService;
import org.computate.vertx.api.ApiCounter;
import org.computate.vertx.api.ApiRequest;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.handlebars.AuthHelpers;
import org.computate.vertx.handlebars.DateHelpers;
import org.computate.vertx.handlebars.SiteHelpers;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.api.BaseApiServiceInterface;
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
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.api.trace.Tracer;
import io.vertx.ext.auth.authentication.TokenCredentials;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.netty.handler.codec.mqtt.MqttQoS;
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
import io.vertx.pgclient.PgBuilder;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.Pool;
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

	private JsonObject i18n;

	/**
	 * A io.vertx.ext.jdbc.JDBCClient for connecting to the relational database PostgreSQL. 
	 **/
	private Pool pgPool;

	public Pool getPgPool() {
		return pgPool;
	}

	public void setPgPool(Pool pgPool) {
		this.pgPool = pgPool;
	}

	private WebClient webClient;

	WorkerExecutor workerExecutor;

	Integer commitWithin;

	Jinjava jinjava;

	SdkTracerProvider sdkTracerProvider;
	public void setSdkTracerProvider(SdkTracerProvider sdkTracerProvider) {
		this.sdkTracerProvider = sdkTracerProvider;
	}

	SdkMeterProvider sdkMeterProvider;
	public void setSdkMeterProvider(SdkMeterProvider sdkMeterProvider) {
		this.sdkMeterProvider = sdkMeterProvider;
	}

	/**	
	 *	This is called by Vert.x when the verticle instance is deployed. 
	 *	Initialize a new site context object for storing information about the entire site in English. 
	 *	Setup the startPromise to handle the configuration steps and starting the server. 
	 **/
	@Override()
	public void start(Promise<Void> startPromise) throws Exception, Exception {
		commitWithin = Integer.parseInt(config().getString(ConfigKeys.SOLR_WORKER_COMMIT_WITHIN_MILLIS));

		try {
			configureI18n().onSuccess(a -> 
				configureData().onSuccess(b -> 
					configureJinjava().onSuccess(c -> 
						configureWebClient().onSuccess(d -> 
							configureSharedWorkerExecutor().onSuccess(e -> 
								configureKafka().onSuccess(f -> 
									configureMqtt().onSuccess(g -> 
										configureAmqp().onSuccess(h -> 
											configureRabbitmq().onSuccess(i -> 
												MainVerticle.authorizeData(vertx, config(), webClient).onComplete(j -> 
													importData().onSuccess(k -> 
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
				).onFailure(ex -> startPromise.fail(ex))
			).onFailure(ex -> startPromise.fail(ex));
		} catch (Exception ex) {
			LOG.error("Couldn't start verticle. ", ex);
		}
	}

	/**
	 * Configure internationalization. 
	 * Val.FileError.enUS: Failed to load internationalization data from file: %s
	 * Val.Error.enUS: Failed to load internationalization data. 
	 * Val.Complete.enUS: Loading internationalization data is complete. 
	 * Val.Loaded.enUS: Loaded internationalization data: %s
	 **/
	public Future<JsonObject> configureI18n() {
		Promise<JsonObject> promise = Promise.promise();
		try {
			List<Future<String>> futures = new ArrayList<>();
			JsonArray i18nPaths = Optional.ofNullable(config().getValue(ConfigKeys.I18N_PATHS))
					.map(v -> v instanceof JsonArray ? (JsonArray)v : new JsonArray(v.toString()))
					.orElse(new JsonArray())
					;
			i18n = new JsonObject();
			i18nPaths.stream().map(o -> (String)o).forEach(i18nPath -> {
				futures.add(Future.future(promise1 -> {
					vertx.fileSystem().readFile(i18nPath).onSuccess(buffer -> {
						Yaml yaml = new Yaml();
						Map<String, Object> map = yaml.load(buffer.toString());
						i18n.mergeIn(new JsonObject(map));
						LOG.info(String.format(configureI18nLoaded, i18nPath));
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error(String.format(configureI18nFileError, i18nPath), ex);
						promise1.fail(ex);
					});
				}));
			});
			Future.all(futures).onSuccess(b -> {
				LOG.info(configureI18nComplete);
				promise.complete(i18n);
			}).onFailure(ex -> {
				LOG.error(configureI18nError, ex);
				promise.fail(ex);
			});
		} catch (Throwable ex) {
			LOG.error(configureI18nError, ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**
	 **/
	public Future<Jinjava> configureJinjava() {
		Promise<Jinjava> promise = Promise.promise();

		try {
			jinjava = ComputateConfigKeys.getJinjava();
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
			Boolean sslVerify = Boolean.valueOf(config().getString(ConfigKeys.SSL_VERIFY));
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
	 * Val.ConnectionError.enUS: Could not open the database client connection. 
	 * Val.ConnectionSuccess.enUS: The database client connection was successful. 
	 * 
	 * Val.InitError.enUS: Could not initialize the database tables. 
	 * Val.InitSuccess.enUS: The database was initialized successfully. 
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
			Integer jdbcMaxPoolSize = Integer.parseInt(config().getString(ConfigKeys.DATABASE_MAX_POOL_SIZE));

			pgOptions.setPort(Integer.parseInt(config().getString(ConfigKeys.DATABASE_PORT)));
			pgOptions.setHost(config().getString(ConfigKeys.DATABASE_HOST_NAME));
			pgOptions.setDatabase(config().getString(ConfigKeys.DATABASE_DATABASE));
			pgOptions.setUser(config().getString(ConfigKeys.DATABASE_USERNAME));
			pgOptions.setPassword(config().getString(ConfigKeys.DATABASE_PASSWORD));
			pgOptions.setIdleTimeout(Integer.parseInt(config().getString(ConfigKeys.DATABASE_MAX_IDLE_TIME)));
			pgOptions.setIdleTimeoutUnit(TimeUnit.HOURS);
			pgOptions.setConnectTimeout(Integer.parseInt(config().getString(ConfigKeys.DATABASE_CONNECT_TIMEOUT)));

			PoolOptions poolOptions = new PoolOptions();
			poolOptions.setMaxSize(jdbcMaxPoolSize);
			poolOptions.setMaxWaitQueueSize(Integer.parseInt(config().getString(ConfigKeys.DATABASE_MAX_WAIT_QUEUE_SIZE)));

			pgPool = PgBuilder.pool().connectingTo(pgOptions).with(poolOptions).using(vertx).build();

			MainVerticle.configureDatabaseSchema(vertx, config()).onComplete(a -> {
				LOG.info(configureDataInitSuccess);
				promise.complete();
			}).onFailure(ex -> {
				LOG.error(configureDataInitError, ex);
				promise.fail(ex);
			});
		} catch (Exception ex) {
			LOG.error(configureDataInitError, ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**	
	 * Val.Fail.enUS: Could not configure the shared worker executor. 
	 * Val.Complete.enUS: The shared worker executor "{}" was configured successfully. 
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
	 * Val.Success.enUS: The Kafka producer was initialized successfully. 
	 **/
	public Future<KafkaProducer<String, String>> configureKafka() {
		Promise<KafkaProducer<String, String>> promise = Promise.promise();

		try {
			if(Boolean.valueOf(config().getString(ConfigKeys.ENABLE_KAFKA))) {
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
			if(BooleanUtils.isTrue(Boolean.valueOf(config().getString(ConfigKeys.ENABLE_MQTT)))) {
				try {
					mqttClient = MqttClient.create(vertx);
					mqttClient.connect(Integer.parseInt(config().getString(ConfigKeys.MQTT_PORT)), config().getString(ConfigKeys.MQTT_HOST_NAME)).onSuccess(mqttConnection -> {
						try {
							mqttClient.publishHandler(message -> {
								LOG.info(String.format("MQTT: %s", message.payload().toString(Charset.defaultCharset())));
							}).subscribe("workbench-user1", MqttQoS.EXACTLY_ONCE.value());
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
			if(BooleanUtils.isTrue(Boolean.valueOf(config().getString(ConfigKeys.ENABLE_AMQP)))) {
				try {
					AmqpClientOptions options = new AmqpClientOptions()
							.setHost(config().getString(ConfigKeys.AMQP_HOST_NAME))
							.setPort(Integer.parseInt(config().getString(ConfigKeys.AMQP_PORT)))
							.setUsername(config().getString(ConfigKeys.AMQP_USERNAME))
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
			if(BooleanUtils.isTrue(Boolean.valueOf(config().getString(ConfigKeys.ENABLE_RABBITMQ)))) {
				try {
					RabbitMQOptions options = new RabbitMQOptions()
							.setHost(config().getString(ConfigKeys.RABBITMQ_HOST_NAME))
							.setPort(Integer.parseInt(config().getString(ConfigKeys.RABBITMQ_PORT)))
							.setUser(config().getString(ConfigKeys.RABBITMQ_USERNAME))
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

	public <API_IMPL extends BaseApiServiceInterface> void initializeApiService(API_IMPL service) {
		service.setVertx(vertx);
		service.setEventBus(vertx.eventBus());
		service.setConfig(config());
		service.setWorkerExecutor(workerExecutor);
		service.setOauth2AuthHandler(oauth2AuthHandler);
		service.setPgPool(pgPool);
		service.setKafkaProducer(kafkaProducer);
		service.setMqttClient(mqttClient);
		service.setAmqpClient(amqpClient);
		service.setRabbitmqClient(rabbitmqClient);
		service.setWebClient(webClient);
		service.setJinjava(jinjava);
		service.setI18n(i18n);
	}

	/**
	 * Description: Import initial data
	 * Val.Skip.enUS: The data import is disabled. 
	 **/
	private Future<Void> importData() {
		Promise<Void> promise = Promise.promise();
		if(Boolean.valueOf(config().getString(ConfigKeys.ENABLE_IMPORT_DATA))) {
			SiteRequest siteRequest = new SiteRequest();
			siteRequest.setConfig(config());
			siteRequest.setWebClient(webClient);
			siteRequest.initDeepSiteRequest(siteRequest);
			siteRequest.addScopes("GET");
			String templatePath = config().getString(ComputateConfigKeys.TEMPLATE_PATH);

			CompanyAboutEnUSApiServiceImpl apiCompanyAbout = new CompanyAboutEnUSApiServiceImpl();
			initializeApiService(apiCompanyAbout);
			UseCaseEnUSApiServiceImpl apiUseCase = new UseCaseEnUSApiServiceImpl();
			initializeApiService(apiUseCase);
			CompanyCourseEnUSApiServiceImpl apiCompanyCourse = new CompanyCourseEnUSApiServiceImpl();
			initializeApiService(apiCompanyCourse);
			SitePageEnUSApiServiceImpl apiSitePage = new SitePageEnUSApiServiceImpl();
			initializeApiService(apiSitePage);
			CompanyProductEnUSApiServiceImpl apiCompanyProduct = new CompanyProductEnUSApiServiceImpl();
			initializeApiService(apiCompanyProduct);
			CompanyEventEnUSApiServiceImpl apiCompanyEvent = new CompanyEventEnUSApiServiceImpl();
			initializeApiService(apiCompanyEvent);
			CompanyWebinarEnUSApiServiceImpl apiCompanyWebinar = new CompanyWebinarEnUSApiServiceImpl();
			initializeApiService(apiCompanyWebinar);
			CompanyServiceEnUSApiServiceImpl apiCompanyService = new CompanyServiceEnUSApiServiceImpl();
			initializeApiService(apiCompanyService);
			CompanyResearchEnUSApiServiceImpl apiCompanyResearch = new CompanyResearchEnUSApiServiceImpl();
			initializeApiService(apiCompanyResearch);
			CompanyWebsiteEnUSApiServiceImpl apiCompanyWebsite = new CompanyWebsiteEnUSApiServiceImpl();
			initializeApiService(apiCompanyWebsite);

			apiCompanyAbout.importTimer(Paths.get(templatePath, "/en-us/learn/about"), vertx, siteRequest, CompanyAbout.CLASS_CANONICAL_NAME, CompanyAbout.CLASS_SIMPLE_NAME, CompanyAbout.CLASS_API_ADDRESS_CompanyAbout, "pageId", "userPage", "download").onSuccess(q1 -> {
				apiUseCase.importTimer(Paths.get(templatePath, "/en-us/shop/use-case"), vertx, siteRequest, UseCase.CLASS_CANONICAL_NAME, UseCase.CLASS_SIMPLE_NAME, UseCase.CLASS_API_ADDRESS_UseCase, "pageId", "userPage", "download").onSuccess(q2 -> {
					apiCompanyCourse.importTimer(Paths.get(templatePath, "/en-us/shop/course"), vertx, siteRequest, CompanyCourse.CLASS_CANONICAL_NAME, CompanyCourse.CLASS_SIMPLE_NAME, CompanyCourse.CLASS_API_ADDRESS_CompanyCourse, "pageId", "userPage", "download").onSuccess(q3 -> {
						apiSitePage.importTimer(Paths.get(templatePath, "/en-us/view/article"), vertx, siteRequest, SitePage.CLASS_CANONICAL_NAME, SitePage.CLASS_SIMPLE_NAME, SitePage.CLASS_API_ADDRESS_SitePage, "pageId", "userPage", "download").onSuccess(q4 -> {
							apiCompanyProduct.importTimer(Paths.get(templatePath, "/en-us/shop/product"), vertx, siteRequest, CompanyProduct.CLASS_CANONICAL_NAME, CompanyProduct.CLASS_SIMPLE_NAME, CompanyProduct.CLASS_API_ADDRESS_CompanyProduct, "pageId", "userPage", "downloadUrl").onSuccess(q5 -> {
								apiCompanyEvent.importTimer(Paths.get(templatePath, "/en-us/shop/event"), vertx, siteRequest, CompanyEvent.CLASS_CANONICAL_NAME, CompanyEvent.CLASS_SIMPLE_NAME, CompanyEvent.CLASS_API_ADDRESS_CompanyEvent, "pageId", "userPage", "download").onSuccess(q6 -> {
									apiCompanyWebinar.importTimer(Paths.get(templatePath, "/en-us/view/webinar"), vertx, siteRequest, CompanyWebinar.CLASS_CANONICAL_NAME, CompanyWebinar.CLASS_SIMPLE_NAME, CompanyWebinar.CLASS_API_ADDRESS_CompanyWebinar, "pageId", "userPage", "download").onSuccess(q7 -> {
										apiCompanyService.importTimer(Paths.get(templatePath, "/en-us/shop/service"), vertx, siteRequest, CompanyService.CLASS_CANONICAL_NAME, CompanyService.CLASS_SIMPLE_NAME, CompanyService.CLASS_API_ADDRESS_CompanyService, "pageId", "userPage", "download").onSuccess(q8 -> {
											apiCompanyResearch.importTimer(Paths.get(templatePath, "/en-us/view/research"), vertx, siteRequest, CompanyResearch.CLASS_CANONICAL_NAME, CompanyResearch.CLASS_SIMPLE_NAME, CompanyResearch.CLASS_API_ADDRESS_CompanyResearch, "pageId", "userPage", "download").onSuccess(q9 -> {
												apiCompanyWebsite.importTimer(Paths.get(templatePath, "/en-us/view/website"), vertx, siteRequest, CompanyWebsite.CLASS_CANONICAL_NAME, CompanyWebsite.CLASS_SIMPLE_NAME, CompanyWebsite.CLASS_API_ADDRESS_CompanyWebsite, "pageId", "userPage", "download").onSuccess(q10 -> {
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
