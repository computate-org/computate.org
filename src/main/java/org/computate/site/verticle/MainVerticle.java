package org.computate.site.verticle;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.serialize.ComputateLocalTimeSerializer;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import org.computate.search.tool.SearchTool;
import org.computate.vertx.api.BaseApiServiceImpl;
import org.computate.vertx.api.BaseApiServiceInterface;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.model.base.ComputateBaseModel;
import org.computate.vertx.model.user.ComputateSiteUser;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.openapi.OpenApi3Generator;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.result.base.ComputateBaseResult;
import org.computate.vertx.search.list.SearchList;
import org.computate.vertx.verticle.EmailVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.loader.FileLocator;

import org.yaml.snakeyaml.Yaml;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.vertx.amqp.AmqpClient;
import io.vertx.amqp.AmqpClientOptions;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.rabbitmq.RabbitMQOptions;
import io.vertx.serviceproxy.ServiceBinder;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.squareup.square.Environment;
import com.squareup.square.SquareClient;
import com.squareup.square.api.CheckoutApi;
import com.squareup.square.api.CustomersApi;
import com.squareup.square.api.EventsApi;
import com.squareup.square.api.OrdersApi;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.authentication.BearerAuthModel;
import com.squareup.square.models.Customer;
import com.squareup.square.models.GetPaymentResponse;
import com.squareup.square.models.Order;
import com.squareup.square.models.OrderLineItem;
import com.squareup.square.models.OrderLineItemModifier;
import com.squareup.square.models.Payment;
import com.squareup.square.models.RetrieveCustomerResponse;
import com.squareup.square.models.RetrieveOrderResponse;
import com.squareup.square.models.Tender;
import com.squareup.square.models.UpdateOrderRequest;
import com.squareup.square.utilities.WebhooksHelper;

import io.vertx.amqp.AmqpMessage;
import io.vertx.amqp.AmqpMessageBuilder;
import io.vertx.amqp.AmqpSenderOptions;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxBuilder;
import io.vertx.core.VertxOptions;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.impl.VertxImpl;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.shareddata.SharedData;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.cluster.NodeInfo;
import io.opentelemetry.api.trace.Tracer;
import io.vertx.core.streams.Pump;
import io.vertx.core.tracing.TracingPolicy;
import io.vertx.core.tracing.TracingOptions;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2Options;
import io.vertx.ext.auth.oauth2.Oauth2Credentials;
import io.vertx.ext.auth.oauth2.authorization.KeycloakAuthorization;
import io.vertx.ext.auth.oauth2.impl.OAuth2AuthProviderImpl;
import io.vertx.ext.auth.oauth2.providers.OpenIDConnectAuth;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.healthchecks.HealthCheckHandler;
import io.vertx.ext.healthchecks.Status;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.impl.RoutingContextImpl;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.mqtt.MqttClient;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgBuilder;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Transaction;
import io.vertx.sqlclient.Tuple;
import io.vertx.tracing.opentelemetry.OpenTelemetryOptions;
import io.vertx.tracing.opentracing.OpenTracingTracerFactory;

import org.computate.site.config.ConfigKeys;
import org.computate.site.request.SiteRequest;
import org.computate.site.user.SiteUser;
import org.computate.site.user.SiteUserEnUSGenApiService;
import org.computate.site.user.SiteUserEnUSApiServiceImpl;
import org.computate.site.result.BaseResult;
import org.computate.site.model.BaseModel;
import org.computate.site.model.about.CompanyAboutEnUSGenApiService;
import org.computate.site.model.about.CompanyAboutEnUSApiServiceImpl;
import org.computate.site.model.about.CompanyAbout;
import org.computate.site.model.usecase.UseCaseEnUSGenApiService;
import org.computate.site.model.usecase.UseCaseEnUSApiServiceImpl;
import org.computate.site.model.usecase.UseCase;
import org.computate.site.model.course.CompanyCourseEnUSGenApiService;
import org.computate.site.model.course.CompanyCourseEnUSApiServiceImpl;
import org.computate.site.model.course.CompanyCourse;
import org.computate.site.page.SitePageEnUSGenApiService;
import org.computate.site.page.SitePageEnUSApiServiceImpl;
import org.computate.site.page.SitePage;
import org.computate.site.model.product.CompanyProductEnUSGenApiService;
import org.computate.site.model.product.CompanyProductEnUSApiServiceImpl;
import org.computate.site.model.product.CompanyProduct;
import org.computate.site.model.event.CompanyEventEnUSGenApiService;
import org.computate.site.model.event.CompanyEventEnUSApiServiceImpl;
import org.computate.site.model.event.CompanyEvent;
import org.computate.site.model.webinar.CompanyWebinarEnUSGenApiService;
import org.computate.site.model.webinar.CompanyWebinarEnUSApiServiceImpl;
import org.computate.site.model.webinar.CompanyWebinar;
import org.computate.site.model.service.CompanyServiceEnUSGenApiService;
import org.computate.site.model.service.CompanyServiceEnUSApiServiceImpl;
import org.computate.site.model.service.CompanyService;
import org.computate.site.model.research.CompanyResearchEnUSGenApiService;
import org.computate.site.model.research.CompanyResearchEnUSApiServiceImpl;
import org.computate.site.model.research.CompanyResearch;
import org.computate.site.model.website.CompanyWebsiteEnUSGenApiService;
import org.computate.site.model.website.CompanyWebsiteEnUSApiServiceImpl;
import org.computate.site.model.website.CompanyWebsite;


/**
 * Description: A Java class to start the Vert.x application as a main method. 
 * Keyword: classSimpleNameVerticle
 **/
public class MainVerticle extends MainVerticleGen<AbstractVerticle> {
	private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

	private Integer siteInstances;
	private Integer workerPoolSize;
	private Integer jdbcMaxPoolSize; 
	private Integer jdbcMaxWaitQueueSize;

	LocalSessionStore sessionStore;
	SessionHandler sessionHandler;
	ComputateOAuth2AuthHandlerImpl oauth2AuthHandler;

	private JsonObject i18n;

	/**
	 * A io.vertx.ext.jdbc.JDBCClient for connecting to the relational database PostgreSQL. 
	 **/
	private Pool pgPool;

	private WebClient webClient;

	private Router router;

	private WorkerExecutor workerExecutor;

	private OAuth2Auth oauth2AuthenticationProvider;

	private AuthorizationProvider authorizationProvider;

	private KafkaProducer<String, String> kafkaProducer;
	private KafkaConsumer<String, String> kafkaConsumer;

	private MqttClient mqttClient;

	private AmqpClient amqpClient;

	private AmqpSender amqpSender;

	private RabbitMQClient rabbitmqClient;

	private Jinjava jinjava;

	SdkTracerProvider sdkTracerProvider;
	public void setSdkTracerProvider(SdkTracerProvider sdkTracerProvider) {
		this.sdkTracerProvider = sdkTracerProvider;
	}

	SdkMeterProvider sdkMeterProvider;
	public void setSdkMeterProvider(SdkMeterProvider sdkMeterProvider) {
		this.sdkMeterProvider = sdkMeterProvider;
	}

	private SquareClient squareClient;

	/**	
	 *	The main method for the Vert.x application that runs the Vert.x Runner class
	 **/
	public static void  main(String[] args) {
		Vertx vertx = Vertx.vertx();
		String configVarsPath = System.getenv(ConfigKeys.VARS_PATH);
		configureConfig(vertx).onSuccess(config -> {
			try {
				Future<Void> originalFuture = Future.future(a -> a.complete());
				Future<Void> future = originalFuture;
				Boolean sslVerify = Boolean.valueOf(config.getString(ConfigKeys.SSL_VERIFY));
				WebClient webClient = WebClient.create(vertx, new WebClientOptions().setVerifyHost(sslVerify).setTrustAll(!sslVerify));
				Boolean runOpenApi3Generator = Optional.ofNullable(Boolean.valueOf(config.getString(ConfigKeys.RUN_OPENAPI3_GENERATOR))).orElse(false);
				Boolean runSqlGenerator = Optional.ofNullable(Boolean.valueOf(config.getString(ConfigKeys.RUN_SQL_GENERATOR))).orElse(false);
				Boolean runAuthorizationGenerator = Optional.ofNullable(Boolean.valueOf(config.getString(ConfigKeys.RUN_AUTHORIZATION_GENERATOR))).orElse(false);
				Boolean runFiwareGenerator = Optional.ofNullable(Boolean.valueOf(config.getString(ConfigKeys.RUN_FIWARE_GENERATOR))).orElse(false);
				Boolean runProjectGenerator = Optional.ofNullable(Boolean.valueOf(config.getString(ConfigKeys.RUN_PROJECT_GENERATOR))).orElse(false);

				if(runOpenApi3Generator || runSqlGenerator || runFiwareGenerator || runProjectGenerator) {
					SiteRequest siteRequest = new SiteRequest();
					siteRequest.setConfig(config);
					siteRequest.setWebClient(webClient);
					siteRequest.initDeepSiteRequest();
					OpenApi3Generator api = new OpenApi3Generator();
					api.setVertx_(vertx);
					api.setWebClient(webClient);
					api.setConfig(config);
					api.initDeepOpenApi3Generator(siteRequest);
					if(runOpenApi3Generator)
						future = future.compose(a -> api.writeOpenApi());
					if(runSqlGenerator) {
						future = future.compose(a -> api.writeSql());
						future = future.compose(a -> configureDatabaseSchema(vertx, config));
					}
					if(runAuthorizationGenerator)
						future = future.compose(a -> authorizeData(vertx, config, webClient));
					if(runFiwareGenerator)
						future = future.compose(a -> api.writeFiware());
					if(runProjectGenerator)
						future = future.compose(a -> api.writeProject());
					future.onComplete(a -> {
						vertx.close();
						System.exit(0);
					});
				} else {
					future = future.compose(a -> run(config).onSuccess(b -> {
						LOG.info("MainVerticle run completed");
					}).onFailure(ex -> {
						LOG.info("MainVerticle run failed");
						vertx.close();
						System.exit(0);
					}));
				}
			} catch(Exception ex) {
				LOG.error(String.format("Error loading config: %s", configVarsPath), ex);
				vertx.close();
			}
		}).onFailure(ex -> {
			LOG.error(String.format("Error loading config: %s", configVarsPath), ex);
			vertx.close();
		});
	}

	/**
	 * Description: Add Keycloak authorization resources, policies, and permissions for a data model. 
	 * Val.Fail.enUS: Adding Keycloak authorization resources, policies, and permissions failed. 
	 **/
	public static Future<Void> authorizeData(Vertx vertx, JsonObject config, WebClient webClient) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = new SiteRequest();
			siteRequest.setConfig(config);
			siteRequest.setWebClient(webClient);
			siteRequest.initDeepSiteRequest(siteRequest);
			SiteUserEnUSApiServiceImpl apiSiteUser = new SiteUserEnUSApiServiceImpl();
			apiSiteUser.setVertx(vertx);
			apiSiteUser.setConfig(config);
			apiSiteUser.setWebClient(webClient);
			CompanyAboutEnUSApiServiceImpl apiCompanyAbout = new CompanyAboutEnUSApiServiceImpl();
			apiCompanyAbout.setVertx(vertx);
			apiCompanyAbout.setConfig(config);
			apiCompanyAbout.setWebClient(webClient);
			UseCaseEnUSApiServiceImpl apiUseCase = new UseCaseEnUSApiServiceImpl();
			apiUseCase.setVertx(vertx);
			apiUseCase.setConfig(config);
			apiUseCase.setWebClient(webClient);
			CompanyCourseEnUSApiServiceImpl apiCompanyCourse = new CompanyCourseEnUSApiServiceImpl();
			apiCompanyCourse.setVertx(vertx);
			apiCompanyCourse.setConfig(config);
			apiCompanyCourse.setWebClient(webClient);
			SitePageEnUSApiServiceImpl apiSitePage = new SitePageEnUSApiServiceImpl();
			apiSitePage.setVertx(vertx);
			apiSitePage.setConfig(config);
			apiSitePage.setWebClient(webClient);
			CompanyProductEnUSApiServiceImpl apiCompanyProduct = new CompanyProductEnUSApiServiceImpl();
			apiCompanyProduct.setVertx(vertx);
			apiCompanyProduct.setConfig(config);
			apiCompanyProduct.setWebClient(webClient);
			CompanyEventEnUSApiServiceImpl apiCompanyEvent = new CompanyEventEnUSApiServiceImpl();
			apiCompanyEvent.setVertx(vertx);
			apiCompanyEvent.setConfig(config);
			apiCompanyEvent.setWebClient(webClient);
			CompanyWebinarEnUSApiServiceImpl apiCompanyWebinar = new CompanyWebinarEnUSApiServiceImpl();
			apiCompanyWebinar.setVertx(vertx);
			apiCompanyWebinar.setConfig(config);
			apiCompanyWebinar.setWebClient(webClient);
			CompanyServiceEnUSApiServiceImpl apiCompanyService = new CompanyServiceEnUSApiServiceImpl();
			apiCompanyService.setVertx(vertx);
			apiCompanyService.setConfig(config);
			apiCompanyService.setWebClient(webClient);
			CompanyResearchEnUSApiServiceImpl apiCompanyResearch = new CompanyResearchEnUSApiServiceImpl();
			apiCompanyResearch.setVertx(vertx);
			apiCompanyResearch.setConfig(config);
			apiCompanyResearch.setWebClient(webClient);
			CompanyWebsiteEnUSApiServiceImpl apiCompanyWebsite = new CompanyWebsiteEnUSApiServiceImpl();
			apiCompanyWebsite.setVertx(vertx);
			apiCompanyWebsite.setConfig(config);
			apiCompanyWebsite.setWebClient(webClient);
			apiSiteUser.createAuthorizationScopes().onSuccess(authToken -> {
					apiCompanyAbout.authorizeGroupData(authToken, CompanyAbout.CLASS_SIMPLE_NAME, "Admin", new String[] { "POST", "PATCH", "GET", "DELETE", "Admin" })
							.compose(q2 -> apiCompanyAbout.authorizeGroupData(authToken, CompanyAbout.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" }))
							.onSuccess(q2 -> {
						apiUseCase.authorizeGroupData(authToken, UseCase.CLASS_SIMPLE_NAME, "Admin", new String[] { "POST", "PATCH", "GET", "DELETE", "Admin" })
								.compose(q3 -> apiUseCase.authorizeGroupData(authToken, UseCase.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" }))
								.onSuccess(q3 -> {
							apiCompanyCourse.authorizeGroupData(authToken, CompanyCourse.CLASS_SIMPLE_NAME, "Admin", new String[] { "POST", "PATCH", "GET", "DELETE", "Admin" })
									.compose(q4 -> apiCompanyCourse.authorizeGroupData(authToken, CompanyCourse.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" }))
									.onSuccess(q4 -> {
								apiSitePage.authorizeGroupData(authToken, SitePage.CLASS_SIMPLE_NAME, "Admin", new String[] { "POST", "PATCH", "GET", "DELETE", "Admin" })
										.compose(q5 -> apiSitePage.authorizeGroupData(authToken, SitePage.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" }))
										.onSuccess(q5 -> {
									apiCompanyProduct.authorizeGroupData(authToken, CompanyProduct.CLASS_SIMPLE_NAME, "Admin", new String[] { "POST", "PATCH", "GET", "DELETE", "Admin" })
											.compose(q6 -> apiCompanyProduct.authorizeGroupData(authToken, CompanyProduct.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" }))
											.onSuccess(q6 -> {
										apiCompanyEvent.authorizeGroupData(authToken, CompanyEvent.CLASS_SIMPLE_NAME, "Admin", new String[] { "POST", "PATCH", "GET", "DELETE", "Admin" })
												.compose(q7 -> apiCompanyEvent.authorizeGroupData(authToken, CompanyEvent.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" }))
												.onSuccess(q7 -> {
											apiCompanyWebinar.authorizeGroupData(authToken, CompanyWebinar.CLASS_SIMPLE_NAME, "Admin", new String[] { "POST", "PATCH", "GET", "DELETE", "Admin" })
													.compose(q8 -> apiCompanyWebinar.authorizeGroupData(authToken, CompanyWebinar.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" }))
													.onSuccess(q8 -> {
												apiCompanyService.authorizeGroupData(authToken, CompanyService.CLASS_SIMPLE_NAME, "Admin", new String[] { "POST", "PATCH", "GET", "DELETE", "Admin" })
														.compose(q9 -> apiCompanyService.authorizeGroupData(authToken, CompanyService.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" }))
														.onSuccess(q9 -> {
													apiCompanyResearch.authorizeGroupData(authToken, CompanyResearch.CLASS_SIMPLE_NAME, "Admin", new String[] { "POST", "PATCH", "GET", "DELETE", "Admin" })
															.compose(q10 -> apiCompanyResearch.authorizeGroupData(authToken, CompanyResearch.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" }))
															.onSuccess(q10 -> {
														apiCompanyWebsite.authorizeGroupData(authToken, CompanyWebsite.CLASS_SIMPLE_NAME, "Admin", new String[] { "POST", "PATCH", "GET", "DELETE", "Admin" })
																.compose(q11 -> apiCompanyWebsite.authorizeGroupData(authToken, CompanyWebsite.CLASS_SIMPLE_NAME, "SuperAdmin", new String[] { "POST", "PATCH", "GET", "DELETE", "SuperAdmin" }))
																.onSuccess(q11 -> {
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
		} catch(Throwable ex) {
			LOG.error(authorizeDataFail, ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 *	Configure shared database connections across the cluster for massive scaling of the application. 
	 *	Return a promise that configures a shared database client connection. 
	 *	Load the database configuration into a shared io.vertx.ext.jdbc.JDBCClient for a scalable, clustered datasource connection pool. 
	 **/
	public static Future<Void> configureDatabaseSchema(Vertx vertx, JsonObject config) {
		Promise<Void> promise = Promise.promise();
		try {
			if(Boolean.valueOf(config.getString(ConfigKeys.ENABLE_DATABASE))) {
				PgConnectOptions pgOptions = new PgConnectOptions();
				pgOptions.setPort(Integer.parseInt(config.getString(ConfigKeys.DATABASE_PORT)));
				pgOptions.setHost(config.getString(ConfigKeys.DATABASE_HOST_NAME));
				pgOptions.setDatabase(config.getString(ConfigKeys.DATABASE_DATABASE));
				pgOptions.setUser(config.getString(ConfigKeys.DATABASE_USERNAME));
				pgOptions.setPassword(config.getString(ConfigKeys.DATABASE_PASSWORD));
				pgOptions.setIdleTimeout(Integer.parseInt(config.getString(ConfigKeys.DATABASE_MAX_IDLE_TIME)));
				pgOptions.setIdleTimeoutUnit(TimeUnit.SECONDS);
				pgOptions.setConnectTimeout(Integer.parseInt(config.getString(ConfigKeys.DATABASE_CONNECT_TIMEOUT)));

				PoolOptions poolOptions = new PoolOptions();
				poolOptions.setMaxSize(Integer.parseInt(config.getString(ConfigKeys.DATABASE_MAX_POOL_SIZE)));
				poolOptions.setMaxWaitQueueSize(Integer.parseInt(config.getString(ConfigKeys.DATABASE_MAX_WAIT_QUEUE_SIZE)));

				Pool pgPool = PgBuilder.pool().connectingTo(pgOptions).with(poolOptions).using(vertx).build();
				Promise<Void> promise1 = Promise.promise();
				pgPool.withConnection(sqlConnection -> {
					try {
						String sqlPath = String.format("%s/src/main/resources/sql/db-create.sql", config.getString(ConfigKeys.SITE_SRC));
						vertx.fileSystem().readFile(sqlPath).onSuccess(buffer -> {
							Future<Transaction> transactionFuture = sqlConnection.begin();
							String sql = buffer.toString();
							List<Future<String>> futures = new ArrayList<>();
							List<String> nonBlankLines = Arrays.stream(sql.split("\n"))
									.filter(line -> !line.trim().isEmpty())
									.collect(Collectors.toList());
							for(String line : nonBlankLines) {
								LOG.info(line);
								transactionFuture.compose(transaction -> 
									sqlConnection.preparedQuery(line)
											.execute(Tuple.tuple())
								);
							}

							transactionFuture.onSuccess(transaction -> {
								transaction.commit().onSuccess(c -> {
									LOG.info("All database schema statements ran successfully.");
									promise1.complete();
								}).onFailure(ex -> {
									LOG.error("Could not initialize the database schema.", ex);
									promise1.fail(ex);
								});
							}).onFailure(ex -> {
								LOG.error("Could not initialize the database schema.", ex);
								promise1.fail(ex);
							});
						}).onFailure(ex -> {
							LOG.error("Could not initialize the database schema.", ex);
							promise1.fail(ex);
						});
					} catch (Exception ex) {
						LOG.error("Could not initialize the database schema.", ex);
						promise1.fail(ex);
					}
					return promise1.future();
				}).onSuccess(a -> {
					LOG.info("The database schema initialization was completed successfully.");
					promise.complete();
				}).onFailure(ex -> {
					LOG.error("Could not initialize the database schema.", ex);
					promise.fail(ex);
				});
			} else {
				promise.complete();
			}
		} catch (Exception ex) {
			LOG.error("Could not initialize the database schema.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	public static void  runOpenApi3Generator(String[] args, Vertx vertx, JsonObject config) {
		OpenApi3Generator api = new OpenApi3Generator();
		Boolean sslVerify = Boolean.valueOf(config.getString(ConfigKeys.SSL_VERIFY));
		WebClient webClient = WebClient.create(vertx, new WebClientOptions().setVerifyHost(sslVerify).setTrustAll(!sslVerify));
		SiteRequest siteRequest = new SiteRequest();
		siteRequest.setConfig(config);
		siteRequest.setWebClient(webClient);
		api.setWebClient(webClient);
		api.setConfig(config);
		siteRequest.initDeepSiteRequest();
		api.initDeepOpenApi3Generator(siteRequest);
		api.writeOpenApi().onSuccess(a -> {
			LOG.info("Write OpenAPI completed. ");
			vertx.close();
		}).onFailure(ex -> {
			LOG.error("Write OpenAPI failed. ", ex);
			vertx.close();
		});
	}

	public static Future<Void> runVerticles(Vertx vertx, JsonObject config, SdkTracerProvider sdkTracerProvider, SdkMeterProvider sdkMeterProvider) {
		Promise<Void> promise = Promise.promise();
		try {
			Long vertxWarningExceptionSeconds = config.getLong(ConfigKeys.VERTX_WARNING_EXCEPTION_SECONDS);
			Long vertxMaxEventLoopExecuteTime = config.getLong(ConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME);
			Long vertxMaxWorkerExecuteTime = config.getLong(ConfigKeys.VERTX_MAX_WORKER_EXECUTE_TIME);
			Integer siteInstances = Integer.parseInt(config.getString(ConfigKeys.SITE_INSTANCES));

			DeploymentOptions deploymentOptions = new DeploymentOptions();
			deploymentOptions.setInstances(siteInstances);
			deploymentOptions.setConfig(config);
			deploymentOptions.setMaxWorkerExecuteTime(vertxMaxWorkerExecuteTime);
			deploymentOptions.setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS);

			DeploymentOptions workerVerticleDeploymentOptions = new DeploymentOptions();
			workerVerticleDeploymentOptions.setConfig(config);
			workerVerticleDeploymentOptions.setInstances(1);
			workerVerticleDeploymentOptions.setMaxWorkerExecuteTime(vertxMaxWorkerExecuteTime);
			workerVerticleDeploymentOptions.setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS);

			DeploymentOptions emailVerticleDeploymentOptions = new DeploymentOptions();
			emailVerticleDeploymentOptions.setConfig(config);
			emailVerticleDeploymentOptions.setInstances(1);
			emailVerticleDeploymentOptions.setMaxWorkerExecuteTime(vertxMaxWorkerExecuteTime);
			emailVerticleDeploymentOptions.setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS);

			vertx.deployVerticle(new Supplier<Verticle>() {
						@Override
						public Verticle get() {
							MainVerticle mainVerticle = new MainVerticle();
							mainVerticle.setSdkTracerProvider(sdkTracerProvider);
							mainVerticle.setSdkMeterProvider(sdkMeterProvider);
							return mainVerticle;
						}
					}, deploymentOptions).onSuccess(a -> {
				LOG.info("Started main verticle. ");
				List<Future<String>> futures = new ArrayList<>();
				if(Boolean.valueOf(config.getString(ConfigKeys.ENABLE_WORKER_VERTICLE))) {
					futures.add(vertx.deployVerticle(new Supplier<Verticle>() {
								@Override
								public Verticle get() {
									WorkerVerticle workerVerticle = new WorkerVerticle();
									workerVerticle.setSdkTracerProvider(sdkTracerProvider);
									workerVerticle.setSdkMeterProvider(sdkMeterProvider);
									return workerVerticle;
								}
							}, workerVerticleDeploymentOptions));
				}
				if(Boolean.valueOf(config.getString(ConfigKeys.ENABLE_EMAIL))) {
					futures.add(vertx.deployVerticle(EmailVerticle.class, emailVerticleDeploymentOptions));
				}
				Future.all(futures).onSuccess(b -> {
					LOG.info("All verticles started successfully.");
					promise.complete();
				}).onFailure(ex -> {
					LOG.error("Failed to start verticles.", ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error("Failed to start main verticle. ", ex);
				vertx.close();
			});
		} catch (Throwable ex) {
			vertx.close();
			LOG.error("Creating clustered Vertx failed. ", ex);
			ExceptionUtils.rethrow(ex);
		}
		return promise.future();
	}

	public static Future<Void> run(JsonObject config) {
		Promise<Void> promise = Promise.promise();
		try {
			Boolean enableZookeeperCluster = Boolean.valueOf(config.getString(ConfigKeys.ENABLE_ZOOKEEPER_CLUSTER));
			VertxOptions vertxOptions = new VertxOptions();
			EventBusOptions eventBusOptions = new EventBusOptions();
	
			ClusterManager clusterManager = null;
			if(enableZookeeperCluster) {
				JsonObject zkConfig = new JsonObject();
				String hostname = config.getString(ConfigKeys.HOSTNAME);
				String openshiftService = config.getString(ConfigKeys.OPENSHIFT_SERVICE);
				String zookeeperHostName = config.getString(ConfigKeys.ZOOKEEPER_HOST_NAME);
				Integer zookeeperPort = Integer.parseInt(config.getString(ConfigKeys.ZOOKEEPER_PORT));
				String zookeeperHosts = Optional.ofNullable(config.getString(ConfigKeys.ZOOKEEPER_HOSTS)).orElse(zookeeperHostName + ":" + zookeeperPort);
				String clusterHostName = config.getString(ConfigKeys.CLUSTER_HOST_NAME);
				Integer clusterPort = Integer.parseInt(config.getString(ConfigKeys.CLUSTER_PORT));
				String clusterPublicHostName = config.getString(ConfigKeys.CLUSTER_PUBLIC_HOST_NAME);
				Integer clusterPublicPort = Integer.parseInt(config.getString(ConfigKeys.CLUSTER_PUBLIC_PORT));
				String zookeeperRetryPolicy = config.getString(ConfigKeys.ZOOKEEPER_RETRY_POLICY);
				Integer zookeeperBaseSleepTimeMillis = Integer.parseInt(config.getString(ConfigKeys.ZOOKEEPER_BASE_SLEEP_TIME_MILLIS));
				Integer zookeeperMaxSleepMillis = Integer.parseInt(config.getString(ConfigKeys.ZOOKEEPER_MAX_SLEEP_MILLIS));
				Integer zookeeperMaxRetries = Integer.parseInt(config.getString(ConfigKeys.ZOOKEEPER_MAX_RETRIES));
				Integer zookeeperConnectionTimeoutMillis = Integer.parseInt(config.getString(ConfigKeys.ZOOKEEPER_CONNECTION_TIMEOUT_MILLIS));
				Integer zookeeperSessionTimeoutMillis = Integer.parseInt(config.getString(ConfigKeys.ZOOKEEPER_SESSION_TIMEOUT_MILLIS));
				zkConfig.put("zookeeperHosts", zookeeperHosts);
				zkConfig.put("sessionTimeout", zookeeperSessionTimeoutMillis);
				zkConfig.put("connectTimeout", zookeeperConnectionTimeoutMillis);
				zkConfig.put("rootPath", config.getString(ConfigKeys.SITE_NAME));
				zkConfig.put("retry", new JsonObject()
						.put("policy", zookeeperRetryPolicy)
						.put("initialSleepTime", zookeeperBaseSleepTimeMillis)
						.put("intervalTimes", zookeeperMaxSleepMillis)
						.put("maxTimes", zookeeperMaxRetries)
				);
				clusterManager = new ZookeeperClusterManager(zkConfig);
	
				if(clusterHostName == null) {
					clusterHostName = hostname;
				}
				if(clusterPublicHostName == null) {
					if(hostname != null && openshiftService != null) {
						clusterPublicHostName = hostname + "." + openshiftService;
					}
				}
				if(clusterHostName != null) {
					LOG.info(String.format("%s — %s", ConfigKeys.CLUSTER_HOST_NAME, clusterHostName));
					eventBusOptions.setHost(clusterHostName);
				}
				if(clusterPort != null) {
					LOG.info(String.format("%s — %s", ConfigKeys.CLUSTER_PORT, clusterPort));
					eventBusOptions.setPort(clusterPort);
				}
				if(clusterPublicHostName != null) {
					LOG.info(String.format("%s — %s", ConfigKeys.CLUSTER_PUBLIC_HOST_NAME, clusterPublicHostName));
					eventBusOptions.setClusterPublicHost(clusterPublicHostName);
				}
				if(clusterPublicPort != null) {
					LOG.info(String.format("%s — %s", ConfigKeys.CLUSTER_PUBLIC_PORT, clusterPublicPort));
					eventBusOptions.setClusterPublicPort(clusterPublicPort);
				}
			}
			Long vertxWarningExceptionSeconds = config.getLong(ConfigKeys.VERTX_WARNING_EXCEPTION_SECONDS);
			Long vertxMaxEventLoopExecuteTime = config.getLong(ConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME);
			Long vertxMaxWorkerExecuteTime = config.getLong(ConfigKeys.VERTX_MAX_WORKER_EXECUTE_TIME);
			vertxOptions.setEventBusOptions(eventBusOptions);
			vertxOptions.setWarningExceptionTime(vertxWarningExceptionSeconds);
			vertxOptions.setWarningExceptionTimeUnit(TimeUnit.SECONDS);
			vertxOptions.setMaxEventLoopExecuteTime(vertxMaxEventLoopExecuteTime);
			vertxOptions.setMaxEventLoopExecuteTimeUnit(TimeUnit.SECONDS);
			vertxOptions.setMaxWorkerExecuteTime(vertxMaxWorkerExecuteTime);
			vertxOptions.setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS);
			vertxOptions.setWorkerPoolSize(Integer.parseInt(config.getString(ConfigKeys.WORKER_POOL_SIZE)));

			Resource resource = Resource.builder()
					.put("service.name", SITE_NAME)
					.build();
			SdkTracerProvider sdkTracerProvider = Boolean.valueOf(config.getString(ConfigKeys.ENABLE_OPEN_TELEMETRY)) ? 
					SdkTracerProvider.builder().addSpanProcessor(SimpleSpanProcessor.create(OtlpHttpSpanExporter.builder().build())).build() : null;
			SdkMeterProvider sdkMeterProvider = Boolean.valueOf(config.getString(ConfigKeys.ENABLE_OPEN_TELEMETRY)) ? 
					SdkMeterProvider.builder().build() : null;
			if(Boolean.valueOf(config.getString(ConfigKeys.ENABLE_OPEN_TELEMETRY))) {
				// Switch oc config current-context to your other cluster to port forward the opentelemetry-collector
				// oc -n opentelemetry port-forward $(oc -n opentelemetry get pod -l app.kubernetes.io/name=opentelemetry-collector -o name) 4318:4318
				// Switch oc config current-context back to OpenShift Local
				OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
						.setTracerProvider(sdkTracerProvider)
						.setMeterProvider(sdkMeterProvider)
						.setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
						.buildAndRegisterGlobal();
				vertxOptions.setTracingOptions(new OpenTelemetryOptions(openTelemetry));
			}
	
			if(enableZookeeperCluster) {
				VertxBuilder vertxBuilder = Vertx.builder();
				vertxBuilder.with(vertxOptions);
				vertxBuilder.withClusterManager(clusterManager);
				vertxBuilder.buildClustered().onSuccess(vertx -> {
					runVerticles(vertx, config, sdkTracerProvider, sdkMeterProvider).onSuccess(a -> {
						promise.complete();
					});
				}).onFailure(ex -> {
					LOG.error("Creating clustered Vertx failed. ", ex);
					promise.fail(ex);
				});
			} else {
				VertxBuilder vertxBuilder = Vertx.builder();
				vertxBuilder.with(vertxOptions);
				Vertx vertx = vertxBuilder.build();
				runVerticles(vertx, config, sdkTracerProvider, sdkMeterProvider).onSuccess(a -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error("Running verticles failed. ", ex);
					promise.fail(ex);
				});
			}
		} catch (Throwable ex) {
			LOG.error("Creating Vertx failed. ", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**
	 * This is called by Vert.x when the verticle instance is deployed. 
	 * Initialize a new site context object for storing information about the entire site in English. 
	 * Setup the startPromise to handle the configuration steps and starting the server. 
	 **/
	@Override()
	public void start(Promise<Void> startPromise) throws Exception, Exception {
		try {
			configureI18n().onSuccess(a ->
				configureWebClient().onSuccess(b ->
					configureDataLoop().onSuccess(c -> 
						configureOpenApi().onSuccess(d -> 
							configureHealthChecks().onSuccess(e -> 
								configureSharedWorkerExecutor().onSuccess(f -> 
									configureWebsockets().onSuccess(g -> 
										configureKafka().onSuccess(h -> 
											configureMqtt().onSuccess(i -> 
												configureAmqp().onSuccess(j -> 
													configureRabbitmq().onSuccess(k -> 
														configureJinjava().onSuccess(l -> 
															configureSquare().onSuccess(m -> 
																configureApi().onSuccess(n -> 
																	configureUi().onSuccess(o -> 
																		startServer().onSuccess(p -> startPromise.complete())
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
							).onFailure(ex -> startPromise.fail(ex))
						).onFailure(ex -> startPromise.fail(ex))
					).onFailure(ex -> startPromise.fail(ex))
				).onFailure(ex -> startPromise.fail(ex))
			).onFailure(ex -> startPromise.fail(ex));
		} catch (Exception ex) {
			LOG.error("Couldn't start verticle. ", ex);
			startPromise.fail(ex);
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
	public Future<Void> configureWebClient() {
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
	 **/
	public Future<KafkaProducer<String, String>> configureKafka() {
		Promise<KafkaProducer<String, String>> promise = Promise.promise();

		try {
			if(Boolean.valueOf(config().getString(ConfigKeys.ENABLE_KAFKA))) {
				Map<String, String> kafkaConfig = new HashMap<>();
				kafkaConfig.put("bootstrap.servers", config().getString(ConfigKeys.KAFKA_BROKERS));
				kafkaConfig.put("acks", "1");
				kafkaConfig.put("security.protocol", "SSL");
				kafkaConfig.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				kafkaConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
				kafkaConfig.put("group.id", config().getString(ConfigKeys.KAFKA_GROUP));
				kafkaConfig.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
				kafkaConfig.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
				kafkaConfig.put("auto.offset.reset", "earliest");
				kafkaConfig.put("enable.auto.commit", "true");
				Optional.ofNullable(config().getString(ConfigKeys.KAFKA_SSL_KEYSTORE_TYPE)).ifPresent(keystoreType -> {
					kafkaConfig.put("ssl.keystore.type", keystoreType);
					kafkaConfig.put("ssl.keystore.location", config().getString(ConfigKeys.KAFKA_SSL_KEYSTORE_LOCATION));
					kafkaConfig.put("ssl.keystore.password", config().getString(ConfigKeys.KAFKA_SSL_KEYSTORE_PASSWORD));
				});
				Optional.ofNullable(config().getString(ConfigKeys.KAFKA_SSL_KEYSTORE_TYPE)).ifPresent(truststoreType -> {
					kafkaConfig.put("ssl.truststore.type", truststoreType);
					kafkaConfig.put("ssl.truststore.location", config().getString(ConfigKeys.KAFKA_SSL_TRUSTSTORE_LOCATION));
					kafkaConfig.put("ssl.truststore.password", config().getString(ConfigKeys.KAFKA_SSL_TRUSTSTORE_PASSWORD));
				});

				kafkaProducer = KafkaProducer.createShared(vertx, config().getString(ConfigKeys.SITE_NAME), kafkaConfig);
							
				// use consumer for interacting with Apache Kafka
				kafkaConsumer = KafkaConsumer.create(vertx, kafkaConfig);
				SiteRoutes.kafkaConsumer(vertx, kafkaConsumer, config()).onSuccess(a -> {
					LOG.info("The Kafka producer was initialized successfully. ");
					promise.complete(kafkaProducer);
				}).onFailure(ex -> {
					promise.fail(ex);
				});
			} else {
				LOG.info("The Kafka producer was initialized successfully. ");
				promise.complete(null);
			}
		} catch(Exception ex) {
			LOG.error("Unable to configure Kafka. ", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**
	 **/
	public Future<MqttClient> configureMqtt() {
		Promise<MqttClient> promise = Promise.promise();

		try {
			if(Boolean.valueOf(config().getString(ConfigKeys.ENABLE_MQTT))) {
				try {
					mqttClient = MqttClient.create(vertx);
					mqttClient.connect(Integer.parseInt(config().getString(ConfigKeys.MQTT_PORT)), config().getString(ConfigKeys.MQTT_HOST_NAME)).onSuccess(mqttConnection -> {
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
			if(Boolean.valueOf(config().getString(ConfigKeys.ENABLE_AMQP))) {
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
			if(Boolean.valueOf(config().getString(ConfigKeys.ENABLE_RABBITMQ))) {
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

	public Future<Void> configureDataLoop() {
		Promise<Void> promise = Promise.promise();
		configureData().onSuccess(a -> {
			promise.complete();
		}).onFailure(ex -> {
			LOG.info("Call timer");
			vertx.setTimer(10000, a -> {
			LOG.info("Timer triggered");
				configureDataLoop();
			});
		});
		return promise.future();
	}

	/**	
	 *	Configure shared database connections across the cluster for massive scaling of the application. 
	 *	Return a promise that configures a shared database client connection. 
	 *	Load the database configuration into a shared io.vertx.ext.jdbc.JDBCClient for a scalable, clustered datasource connection pool. 
	 **/
	public Future<Void> configureData() {
		Promise<Void> promise = Promise.promise();
		try {
			if(Boolean.valueOf(config().getString(ConfigKeys.ENABLE_DATABASE))) {
				PgConnectOptions pgOptions = new PgConnectOptions();
				pgOptions.setPort(Integer.parseInt(config().getString(ConfigKeys.DATABASE_PORT)));
				pgOptions.setHost(config().getString(ConfigKeys.DATABASE_HOST_NAME));
				pgOptions.setDatabase(config().getString(ConfigKeys.DATABASE_DATABASE));
				pgOptions.setUser(config().getString(ConfigKeys.DATABASE_USERNAME));
				pgOptions.setPassword(config().getString(ConfigKeys.DATABASE_PASSWORD));
				pgOptions.setIdleTimeout(Integer.parseInt(config().getString(ConfigKeys.DATABASE_MAX_IDLE_TIME)));
				pgOptions.setIdleTimeoutUnit(TimeUnit.SECONDS);
				pgOptions.setConnectTimeout(Integer.parseInt(config().getString(ConfigKeys.DATABASE_CONNECT_TIMEOUT)));

				PoolOptions poolOptions = new PoolOptions();
				jdbcMaxPoolSize = Integer.parseInt(config().getString(ConfigKeys.DATABASE_MAX_POOL_SIZE));
				jdbcMaxWaitQueueSize = Integer.parseInt(config().getString(ConfigKeys.DATABASE_MAX_WAIT_QUEUE_SIZE));
				poolOptions.setMaxSize(jdbcMaxPoolSize);
				poolOptions.setMaxWaitQueueSize(jdbcMaxWaitQueueSize);

				pgPool = PgBuilder.pool().connectingTo(pgOptions).with(poolOptions).using(vertx).build();
				Promise<Void> promise1 = Promise.promise();
				pgPool.withConnection(sqlConnection -> {
					sqlConnection.preparedQuery("SELECT")
							.execute(Tuple.tuple()
							).onSuccess(result -> {
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error("Could not initialize the database.", ex);
						promise1.fail(ex);
					});
					return promise1.future();
				}).onSuccess(a -> {
					LOG.info("The database was initialized successfully.");
					promise.complete();
				}).onFailure(ex -> {
					LOG.error("Could not initialize the database.", ex);
					promise.fail(ex);
				});
			} else {
				promise.complete();
			}
		} catch (Exception ex) {
			LOG.error("Could not initialize the database.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**	
	 * Configure the connection to the auth server and setup the routes based on the OpenAPI definition. 
	 * Setup a callback route when returning from the auth server after successful authentication. 
	 * Setup a logout route for logging out completely of the application. 
	 * Return a promise that configures the authentication server and OpenAPI. 
	 **/
	public Future<OAuth2AuthHandler> configureAuthClient(String authClientOpenApiId, JsonObject clientConfig, Map<String, OAuth2AuthHandler> authHandlers, Map<String, OAuth2Auth> authProviders) {
		Promise<OAuth2AuthHandler> promise = Promise.promise();
		try {
			String siteBaseUrl = config().getString(ConfigKeys.SITE_BASE_URL);

			OAuth2Options oauth2ClientOptions = new OAuth2Options();
			Boolean authSsl = Boolean.valueOf(clientConfig.getString(ConfigKeys.AUTH_SSL));
			String authHostName = clientConfig.getString(ConfigKeys.AUTH_HOST_NAME);
			Integer authPort = Integer.valueOf(clientConfig.getString(ConfigKeys.AUTH_PORT));
			String authUrl = String.format("%s", clientConfig.getString(ConfigKeys.AUTH_URL));
			oauth2ClientOptions.setSite(authUrl + "/realms/" + clientConfig.getString(ConfigKeys.AUTH_REALM));
			oauth2ClientOptions.setTenant(clientConfig.getString(ConfigKeys.AUTH_REALM));
			String authClient = clientConfig.getString(ConfigKeys.AUTH_CLIENT);
			oauth2ClientOptions.setClientId(authClient);
			oauth2ClientOptions.setClientSecret(clientConfig.getString(ConfigKeys.AUTH_SECRET));
			oauth2ClientOptions.setAuthorizationPath("/oauth/authorize");
			JsonObject extraParams = new JsonObject();
			extraParams.put("scope", "profile");
			oauth2ClientOptions.setExtraParameters(extraParams);
			oauth2ClientOptions.setHttpClientOptions(new HttpClientOptions().setTrustAll(true).setVerifyHost(false).setConnectTimeout(120000));
			String authCallbackUri = clientConfig.getString(ConfigKeys.AUTH_CALLBACK_URI);
			if(authCallbackUri == null)
				throw new RuntimeException(String.format("Please configure an AUTH_CALLBACK_URI for the AUTH_CLIENT %s.", authClient));

			OpenIDConnectAuth.discover(vertx, oauth2ClientOptions).onSuccess(oauth2AuthenticationProvider -> {
				authorizationProvider = KeycloakAuthorization.create();

				oauth2AuthHandler = new ComputateOAuth2AuthHandlerImpl(vertx, oauth2AuthenticationProvider, siteBaseUrl + authCallbackUri);
				Router tempRouter = Router.router(vertx);
				oauth2AuthHandler.setupCallback(tempRouter.get(authCallbackUri));
				authHandlers.put(authClientOpenApiId, oauth2AuthHandler);
				authProviders.put(authClientOpenApiId, oauth2AuthenticationProvider);
				LOG.info(String.format("Configured the auth server and API successfully.", authClient));
				promise.complete(oauth2AuthHandler);
			}).onFailure(ex -> {
				Exception ex2 = new RuntimeException("OpenID Connect Discovery failed", ex);
				LOG.error("Could not configure the auth server and API.", ex2);
				promise.fail(ex2);
			});
		} catch (Exception ex) {
			LOG.error("Could not configure the auth server and API.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 * Configure the connection to the auth server and setup the routes based on the OpenAPI definition. 
	 * Setup a callback route when returning from the auth server after successful authentication. 
	 * Setup a logout route for logging out completely of the application. 
	 * Return a promise that configures the authentication server and OpenAPI. 
	 **/
	public Future<Void> configureOpenApi() {
		Promise<Void> promise = Promise.promise();
		try {
			String siteBaseUrl = config().getString(ConfigKeys.SITE_BASE_URL);
			
			JsonObject authClients = Optional.ofNullable(config().getValue(ConfigKeys.AUTH_CLIENTS))
					.map(v -> v instanceof JsonObject ? (JsonObject)v : new JsonObject(v.toString()))
					.orElse(new JsonObject().put(Optional.ofNullable(config().getString(ConfigKeys.AUTH_OPEN_API_ID)).orElse("openIdConnect"), config()))
					;
			List<Future<OAuth2AuthHandler>> futures = new ArrayList<>();
			Map<String, OAuth2AuthHandler> authHandlers = new LinkedHashMap<>();
			Map<String, OAuth2Auth> authProviders = new LinkedHashMap<>();
			for(String authClientOpenApiId : authClients.fieldNames()) {
				JsonObject authClient = authClients.getJsonObject(authClientOpenApiId);
				futures.add(Future.future(promise1 -> {
					configureAuthClient(authClientOpenApiId, authClient, authHandlers, authProviders).onSuccess(a -> {
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("configureAuthClient failed. "), ex);
						promise1.fail(ex);
					});
				}));
			}
			Future.all(futures).onSuccess(a -> {
				oauth2AuthenticationProvider = authProviders.get(authProviders.keySet().toArray()[0]);
				authorizationProvider = KeycloakAuthorization.create();
		
				//ClusteredSessionStore sessionStore = ClusteredSessionStore.create(vertx);
				sessionStore = LocalSessionStore.create(vertx, config().getString(ConfigKeys.SITE_NAME));
				sessionHandler = SessionHandler.create(sessionStore);
				if(StringUtils.startsWith(siteBaseUrl, "https://"))
					sessionHandler.setCookieSecureFlag(true);
		
				String siteSrc = config().getString(ComputateConfigKeys.SITE_SRC);
				RouterBuilder.create(vertx, Path.of(siteSrc, "src/main/resources/webroot/openapi3-enUS.yaml").toAbsolutePath().toString()).onSuccess(routerBuilder -> {
					routerBuilder.rootHandler(sessionHandler);
					routerBuilder.rootHandler(BodyHandler.create());

					routerBuilder.mountServicesFromExtensions();
	
					routerBuilder.serviceExtraPayloadMapper(routingContext -> new JsonObject()
							.put("uri", routingContext.request().uri())
							.put("method", routingContext.request().method().name())
							);
					for(String authClientOpenApiId : authHandlers.keySet()) {
						OAuth2AuthHandler authHandler = authHandlers.get(authClientOpenApiId);
						routerBuilder.securityHandler(authClientOpenApiId, authHandler);
					}
					routerBuilder.operation("callback").handler(ctx -> {
	
						// Handle the callback of the flow
						final String code = ctx.request().getParam("code");
	
						// code is a require value
						if (code == null) {
							ctx.fail(400);
							return;
						}
	
						final String state = ctx.request().getParam("state");
	
						final JsonObject config = new JsonObject().put("code", code);
	
						String authClientOpenApiId = config().getString(ConfigKeys.AUTH_OPEN_API_ID);
						JsonObject clientConfig = authClients.getJsonObject(authClientOpenApiId);
						String authCallbackUri = clientConfig.getString(ConfigKeys.AUTH_CALLBACK_URI);
						config.put("redirectUri", siteBaseUrl + authCallbackUri);
						OAuth2Auth authProvider = authProviders.get(authClientOpenApiId);

						authProvider.authenticate(new Oauth2Credentials(config), res -> {
							if (res.failed()) {
								LOG.error("Failed to authenticate user. ", res.cause());
								ctx.fail(res.cause());
							} else {
								ctx.setUser(res.result());
								Session session = ctx.session();
								if (session != null) {
									// the user has upgraded from unauthenticated to authenticated
									// session should be upgraded as recommended by owasp
									Cookie cookie = Cookie.cookie("sessionIdBefore", session.id());
									if(StringUtils.startsWith(siteBaseUrl, "https://"))
										cookie.setSecure(true);
									ctx.response().addCookie(cookie);
									session.regenerateId();
									String redirectUri = session.get("redirect_uri");
									// we should redirect the UA so this link becomes invalid
									ctx.response()
											// disable all caching
											.putHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
											.putHeader("Pragma", "no-cache")
											.putHeader(HttpHeaders.EXPIRES, "0")
											// redirect (when there is no state, redirect to home
											.putHeader(HttpHeaders.LOCATION, redirectUri != null ? redirectUri : "/")
											.setStatusCode(302)
											.end("Redirecting to " + (redirectUri != null ? redirectUri : "/") + ".");
								} else {
									// there is no session object so we cannot keep state
									ctx.reroute(state != null ? state : "/");
								}
							}
						});
	
					});
					routerBuilder.operation("callback").failureHandler(ex -> {
						LOG.error("Failed callback. ", ex);
					});
	
					routerBuilder.operation("logout").handler(rc -> {
						String redirectUri = rc.request().params().get("redirect_uri");
						if(redirectUri == null)
							redirectUri = "/";
						rc.clearUser();
						rc.response()
								.putHeader(HttpHeaders.LOCATION, redirectUri)
								.setStatusCode(302)
								.end("Redirecting to " + redirectUri + ".");
					});
					routerBuilder.operation("logout").handler(c -> {});
	
					router = routerBuilder.createRouter();
	
					LOG.info("The auth server and API was configured successfully.");
					promise.complete();
				}).onFailure(ex -> {
					LOG.error("Could not configure the auth server and API.", ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error("Could not configure the auth server and API.", ex);
				promise.fail(ex);
			});
		} catch (Exception ex) {
			LOG.error("Could not configure the auth server and API.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 **/
	public static Future<JsonObject> configureConfig(Vertx vertx) {
		Promise<JsonObject> promise = Promise.promise();

		try {
			ConfigRetrieverOptions retrieverOptions = new ConfigRetrieverOptions();

			String configVarsPath = System.getenv(ComputateConfigKeys.VARS_PATH);
			if(StringUtils.isNotBlank(configVarsPath)) {
				Jinjava jinjava = ComputateConfigKeys.getJinjava();
				JsonObject config = ComputateConfigKeys.getConfig(jinjava);
				ConfigStoreOptions configOptions = new ConfigStoreOptions().setType("json").setConfig(config);
				retrieverOptions.addStore(configOptions);
			}

			ConfigStoreOptions storeEnv = new ConfigStoreOptions().setType("env");
			retrieverOptions.addStore(storeEnv);

			ConfigRetriever configRetriever = ConfigRetriever.create(vertx, retrieverOptions);
			configRetriever.getConfig().onSuccess(config -> {
				LOG.info("The config was configured successfully. ");
				promise.complete(config);
			}).onFailure(ex -> {
				LOG.error("Unable to configure site context. ", ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error("Unable to configure site context. ", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**
	 * Configure a shared worker executor for running blocking tasks in the background. 
	 * Return a promise that configures the shared worker executor. 
	 **/
	public Future<Void> configureSharedWorkerExecutor() {
		Promise<Void> promise = Promise.promise();
		try {
			String name = "MainVerticle-WorkerExecutor";
			Integer workerPoolSize = System.getenv(ConfigKeys.WORKER_POOL_SIZE) == null ? 5 : Integer.parseInt(System.getenv(ConfigKeys.WORKER_POOL_SIZE));
			Long vertxMaxWorkerExecuteTime = config().getLong(ConfigKeys.VERTX_MAX_WORKER_EXECUTE_TIME);
			workerExecutor = vertx.createSharedWorkerExecutor(name, workerPoolSize, vertxMaxWorkerExecuteTime, TimeUnit.SECONDS);
			LOG.info("The shared worker executor \"{}\" was configured successfully.", name);
			promise.complete();
		} catch (Exception ex) {
			LOG.error("Could not configure the shared worker executor.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 * Configure health checks for the status of the website and it's dependent services. 
	 * Return a promise that configures the health checks. 
	 **/
	public Future<Void> configureHealthChecks() {
		Promise<Void> promise = Promise.promise();
		try {
			ClusterManager clusterManager = ((VertxImpl)vertx).getClusterManager();
			HealthCheckHandler healthCheckHandler = HealthCheckHandler.create(vertx);
			siteInstances = Optional.ofNullable(System.getenv(ConfigKeys.SITE_INSTANCES)).map(s -> Integer.parseInt(s)).orElse(1);
			workerPoolSize = System.getenv(ConfigKeys.WORKER_POOL_SIZE) == null ? null : Integer.parseInt(System.getenv(ConfigKeys.WORKER_POOL_SIZE));

			healthCheckHandler.register("vertx", 2000, a -> {
				a.complete(Status.OK(new JsonObject().put(ConfigKeys.SITE_INSTANCES, siteInstances).put("workerPoolSize", workerPoolSize)));
			});
			if(clusterManager != null) {
				healthCheckHandler.register("cluster", 2000, a -> {
					NodeInfo nodeInfo = clusterManager.getNodeInfo();
					JsonArray nodeArray = new JsonArray();
					clusterManager.getNodes().forEach(node -> nodeArray.add(node));
					a.complete(Status.OK(new JsonObject()
							.put("nodeId", clusterManager.getNodeId())
							.put("nodes", nodeArray)
							));
				});
			}
			router.get("/health").handler(healthCheckHandler);
			LOG.info("The health checks were configured successfully.");
			promise.complete();
		} catch (Exception ex) {
			LOG.error("Could not configure the health checks.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 * Configure websockets for realtime messages. 
	 **/
	public Future<Void> configureWebsockets() {
		Promise<Void> promise = Promise.promise();
		try {
			SockJSBridgeOptions options = new SockJSBridgeOptions()
					.addOutboundPermitted(new PermittedOptions().setAddressRegex("websocket.*"))
					;
			router.route("/eventbus*").subRouter(SockJSHandler.create(vertx).bridge(options));
			LOG.info("Configure websockets succeeded.");
			promise.complete();
		} catch (Exception ex) {
			LOG.error("Configure websockets failed.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 * Configure square webhooks
	 **/
	public Future<Void> configureSquare() {
		Promise<Void> promise = Promise.promise();
		try {
			String squareAccessToken = config().getString(ConfigKeys.SQUARE_ACCESS_TOKEN);
			String squareSignatureKey = config().getString(ConfigKeys.SQUARE_SIGNATURE_KEY);
			String squareNotificationUrl = config().getString(ConfigKeys.SQUARE_NOTIFICATION_URL);
			if(squareAccessToken == null || squareSignatureKey == null || squareNotificationUrl == null) {
				promise.complete();
			} else {
				squareClient = new SquareClient.Builder()
						.bearerAuthCredentials(new BearerAuthModel.Builder(squareAccessToken).build())
						.environment(Environment.PRODUCTION)
						.build();
				LOG.info("Configure Square succeeded.");
				promise.complete();
			}
		} catch (Exception ex) {
			LOG.error("Configure Square failed.", ex);
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
		service.setOauth2AuthenticationProvider(oauth2AuthenticationProvider);
		service.setPgPool(pgPool);
		service.setKafkaProducer(kafkaProducer);
		service.setMqttClient(mqttClient);
		service.setAmqpClient(amqpClient);
		service.setRabbitmqClient(rabbitmqClient);
		service.setWebClient(webClient);
		service.setJinjava(jinjava);
		service.setI18n(i18n);
	}

	public <API_INTERFACE, API_IMPL extends API_INTERFACE> void registerApiService(Class<API_INTERFACE> serviceClass, API_IMPL service, String apiAddress) {
		new ServiceBinder(vertx).setAddress(apiAddress).register(serviceClass, service);
	}

	/**
	 */
	public Future<Void> configureApi() {
		Promise<Void> promise = Promise.promise();
		try {
			List<Future<?>> futures = new ArrayList<>();
			List<String> authResources = Arrays.asList("CompanyAbout","UseCase","CompanyCourse","SitePage","CompanyProduct","CompanyEvent","CompanyWebinar","CompanyService","CompanyResearch","CompanyWebsite");
			List<String> publicResources = Arrays.asList("CompanyAbout","UseCase","CompanyCourse","SitePage","CompanyProduct","CompanyEvent","CompanyWebinar","CompanyService","CompanyResearch","CompanyWebsite");
			SiteUserEnUSApiServiceImpl apiSiteUser = new SiteUserEnUSApiServiceImpl();
			initializeApiService(apiSiteUser);
			registerApiService(SiteUserEnUSGenApiService.class, apiSiteUser, SiteUser.getClassApiAddress());
			apiSiteUser.configureUserSearchApi(config().getString(ComputateConfigKeys.USER_SEARCH_URI), router, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_SiteUser, config(), webClient, authResources);
			apiSiteUser.configurePublicSearchApi(config().getString(ComputateConfigKeys.PUBLIC_SEARCH_URI), router, SiteRequest.class, config(), webClient, publicResources);

			CompanyAboutEnUSApiServiceImpl apiCompanyAbout = new CompanyAboutEnUSApiServiceImpl();
			initializeApiService(apiCompanyAbout);
			registerApiService(CompanyAboutEnUSGenApiService.class, apiCompanyAbout, CompanyAbout.getClassApiAddress());

			UseCaseEnUSApiServiceImpl apiUseCase = new UseCaseEnUSApiServiceImpl();
			initializeApiService(apiUseCase);
			registerApiService(UseCaseEnUSGenApiService.class, apiUseCase, UseCase.getClassApiAddress());

			CompanyCourseEnUSApiServiceImpl apiCompanyCourse = new CompanyCourseEnUSApiServiceImpl();
			initializeApiService(apiCompanyCourse);
			registerApiService(CompanyCourseEnUSGenApiService.class, apiCompanyCourse, CompanyCourse.getClassApiAddress());

			SitePageEnUSApiServiceImpl apiSitePage = new SitePageEnUSApiServiceImpl();
			initializeApiService(apiSitePage);
			registerApiService(SitePageEnUSGenApiService.class, apiSitePage, SitePage.getClassApiAddress());

			CompanyProductEnUSApiServiceImpl apiCompanyProduct = new CompanyProductEnUSApiServiceImpl();
			initializeApiService(apiCompanyProduct);
			registerApiService(CompanyProductEnUSGenApiService.class, apiCompanyProduct, CompanyProduct.getClassApiAddress());

			CompanyEventEnUSApiServiceImpl apiCompanyEvent = new CompanyEventEnUSApiServiceImpl();
			initializeApiService(apiCompanyEvent);
			registerApiService(CompanyEventEnUSGenApiService.class, apiCompanyEvent, CompanyEvent.getClassApiAddress());

			CompanyWebinarEnUSApiServiceImpl apiCompanyWebinar = new CompanyWebinarEnUSApiServiceImpl();
			initializeApiService(apiCompanyWebinar);
			registerApiService(CompanyWebinarEnUSGenApiService.class, apiCompanyWebinar, CompanyWebinar.getClassApiAddress());

			CompanyServiceEnUSApiServiceImpl apiCompanyService = new CompanyServiceEnUSApiServiceImpl();
			initializeApiService(apiCompanyService);
			registerApiService(CompanyServiceEnUSGenApiService.class, apiCompanyService, CompanyService.getClassApiAddress());

			CompanyResearchEnUSApiServiceImpl apiCompanyResearch = new CompanyResearchEnUSApiServiceImpl();
			initializeApiService(apiCompanyResearch);
			registerApiService(CompanyResearchEnUSGenApiService.class, apiCompanyResearch, CompanyResearch.getClassApiAddress());

			CompanyWebsiteEnUSApiServiceImpl apiCompanyWebsite = new CompanyWebsiteEnUSApiServiceImpl();
			initializeApiService(apiCompanyWebsite);
			registerApiService(CompanyWebsiteEnUSGenApiService.class, apiCompanyWebsite, CompanyWebsite.getClassApiAddress());

			Future.all(futures).onSuccess( a -> {
				LOG.info("The API was configured properly.");
				promise.complete();
			}).onFailure(ex -> {
				LOG.error("The API was not configured properly.", ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error("The API was not configured properly.", ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> processSquareItem(CustomersApi customersApi, Message<Object> message, JsonObject orderBody, Order order, String orderId, String state, OrderLineItem item) {
		Promise<Void> promise = Promise.promise();
		try {
			String githubU = null;
			if(item.getModifiers() != null) {
				for(OrderLineItemModifier modifier : item.getModifiers()) {
					String modifierName = modifier.getName();
					Matcher m = Pattern.compile("GitHub username lowercase: (.*)", Pattern.MULTILINE).matcher(modifierName);
					if (m.find())
						githubU = m.group(1).trim();
				}
			}
			String name = item.getName();
			String githubUsername = githubU;
			if(name != null && githubUsername != null) {
				LOG.info(String.format("Processing %s %s order %s for GitHub user %s", state, name, orderId, githubUsername));

				SiteUserEnUSApiServiceImpl apiSiteUser = new SiteUserEnUSApiServiceImpl();
				initializeApiService(apiSiteUser);
				List<String> publicResources = Arrays.asList("CompanyEvent","CompanyCourse","CompanyProduct","CompanyService");
				SiteRequest siteRequest = new SiteRequest();
				siteRequest.setWebClient(this.webClient);
				siteRequest.setJsonObject(orderBody);
				siteRequest.setUserPrincipal(new JsonObject());
				siteRequest.setUser(null);
				siteRequest.setConfig(config());
				siteRequest.setServiceRequest(null);
				siteRequest.setI18n(this.i18n);
				siteRequest.setSiteRequest_(siteRequest);
				siteRequest.initDeepForClass();
				siteRequest.setPublicRead(true);

				SearchList<ComputateBaseResult> searchList = new SearchList<ComputateBaseResult>();
				searchList.setStore(true);
				searchList.q("*:*");
				searchList.setSiteRequest_(siteRequest);
				searchList.fq(String.format("classSimpleName_docvalues_string:" + publicResources.stream().collect(Collectors.joining(" OR ", "(", ")"))));
				searchList.fq(String.format("name_docvalues_string:\"" + name + "\""));
				searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
					if(searchList.size() > 0) {
						ComputateBaseResult result = searchList.first();
						String pageId = (String)result.obtainForClass("pageId");
						String classSimpleName = (String)result.obtainForClass("classSimpleName");
						String groupName = String.format("%s-%s-GET", classSimpleName, pageId);
						String authAdminUsername = config().getString(ConfigKeys.AUTH_ADMIN_USERNAME);
						String authAdminPassword = config().getString(ConfigKeys.AUTH_ADMIN_PASSWORD);
						Integer authPort = Integer.parseInt(config().getString(ConfigKeys.AUTH_PORT));
						String authHostName = config().getString(ConfigKeys.AUTH_HOST_NAME);
						Boolean authSsl = Boolean.valueOf(config().getString(ConfigKeys.AUTH_SSL));
						String authRealm = config().getString(ConfigKeys.AUTH_REALM);
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
								webClient.get(authPort, authHostName, String.format("/admin/realms/%s/groups?exact=false&global=true&first=0&max=1&search=%s", authRealm, URLEncoder.encode(groupName, "UTF-8"))).ssl(authSsl).putHeader("Authorization", String.format("Bearer %s", authToken))
								.send()
								.expecting(HttpResponseExpectation.SC_OK)
								.onSuccess(groupResponse -> {
									try {
										JsonArray groups = Optional.ofNullable(groupResponse.bodyAsJsonArray()).orElse(new JsonArray());
										JsonObject group = groups.stream().findFirst().map(o -> (JsonObject)o).orElse(null);
										if(group != null) {
											String groupId = group.getString("id");
											webClient.get(authPort, authHostName, String.format("/admin/realms/%s/users?username=%s", authRealm, URLEncoder.encode(githubUsername, "UTF-8"))).ssl(authSsl).putHeader("Authorization", String.format("Bearer %s", authToken))
											.send()
											.expecting(HttpResponseExpectation.SC_OK)
											.onSuccess(userResponse -> {
												JsonArray users = Optional.ofNullable(userResponse.bodyAsJsonArray()).orElse(new JsonArray());
												JsonObject user = users.stream().findFirst().map(o -> (JsonObject)o).orElse(null);
												if(user != null) {
													String userId = user.getString("id");
													String userEmail = user.getString("email");
													String userName = String.format("%s %s", user.getString("firstName"), user.getString("lastName"));
													JsonArray userGroups = user.getJsonArray("groups");
													LOG.info(String.format("user %s group %s in groups: %s", githubUsername, groupName, userGroups));
													// if(!userGroups.contains(groupName)) {
														webClient.put(authPort, authHostName, String.format("/admin/realms/%s/users/%s/groups/%s", authRealm, userId, groupId)).ssl(authSsl)
																.putHeader("Authorization", String.format("Bearer %s", authToken))
																.putHeader("Content-Type", "application/json")
																.putHeader("Content-Length", "0")
																.send()
																.expecting(HttpResponseExpectation.SC_NO_CONTENT)
																.onSuccess(groupUserResponse -> {
															try {
																DeliveryOptions options = new DeliveryOptions();
																String siteName = config().getString(ComputateConfigKeys.SITE_NAME);
																String emailFrom = config().getString(ComputateConfigKeys.EMAIL_FROM);
																String customerId = order.getCustomerId();
																String emailTo = userEmail;
																String customerName = userName;
																Payment payment = null;
																if(emailTo == null && customerId == null) {
																	List<Tender> tenders = order.getTenders();
																	if(tenders != null) {
																		Tender tender = order.getTenders().get(0);
																		String paymentId = tender.getPaymentId();
																		PaymentsApi paymentsApi = squareClient.getPaymentsApi();
																		payment = paymentsApi.getPayment(paymentId).getPayment();
																		customerId = payment.getCustomerId();
																	}
																}
																if(emailTo == null && customerId != null) {
																	Customer customer = customersApi.retrieveCustomer(customerId).getCustomer();
																	emailTo = customer.getEmailAddress();
																	customerName = String.format("%s %s", customer.getGivenName(), customer.getFamilyName());
																} else if(payment != null) {
																	emailTo = payment.getBuyerEmailAddress();
																}

																String subject = String.format("Hello %s! Thank you for ordering the %s from %s! ", customerName, name, siteName);
																String emailTemplate = (String)result.obtainForClass("emailTemplate");
																BigDecimal total = new BigDecimal(order.getTotalMoney().getAmount()).divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
																BigDecimal totalTax = new BigDecimal(order.getTotalTaxMoney().getAmount()).divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
																BigDecimal netAmountDue = new BigDecimal(order.getNetAmountDueMoney().getAmount()).divide(new BigDecimal(100), RoundingMode.HALF_EVEN);
																options.addHeader(EmailVerticle.MAIL_HEADER_SUBJECT, subject);
																options.addHeader(EmailVerticle.MAIL_HEADER_FROM, emailFrom);
																options.addHeader(EmailVerticle.MAIL_HEADER_TO, emailTo);
																options.addHeader(EmailVerticle.MAIL_HEADER_TEMPLATE, emailTemplate);

																ZoneId zoneId = ZoneId.of(config().getString(ComputateConfigKeys.SITE_ZONE));
																ZonedDateTime createdAt = ZonedDateTime.parse(order.getCreatedAt(), ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER);
																Locale locale = Locale.forLanguageTag(config().getString(ComputateConfigKeys.SITE_LOCALE));
																DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEE d MMM uuuu h:mm a VV", locale);
																JsonObject body = new JsonObject();
																body.put(ComputateConfigKeys.SITE_BASE_URL, config().getString(ComputateConfigKeys.SITE_BASE_URL));
																body.put("siteName", siteName);
																body.put("githubUsername", githubUsername);
																body.put("orderId", order.getId());
																body.put("subject", subject);
																body.put("emailTo", emailTo);
																body.put("customerName", customerName);
																body.put("result", JsonObject.mapFrom(result));
																body.put("totalMoney", NumberFormat.getCurrencyInstance(locale).format(total));
																body.put("totalTax", NumberFormat.getCurrencyInstance(locale).format(totalTax));
																body.put("netAmountDue", NumberFormat.getCurrencyInstance(locale).format(netAmountDue));

																String createdAtStr = dateFormat.format(createdAt.withZoneSameInstant(zoneId));
																body.put("createdAt", createdAtStr);

																vertx.eventBus().request(EmailVerticle.MAIL_EVENTBUS_ADDRESS, body.encode(), options).onSuccess(b -> {
																	LOG.info(String.format("Successfully granted %s access to %s", githubUsername, name));
																	promise.complete();
																}).onFailure(ex -> {
																	LOG.error(String.format("Failed to send email to %s. ", userEmail), ex);
																	promise.fail(ex);
																});
															} catch(Throwable ex) {
																LOG.error("Failed to process square webook while querying customer. ", ex);
																promise.fail(ex);
															}
														}).onFailure(ex -> {
															LOG.error("Failed to process square webook while adding user to group. ", ex);
															promise.fail(ex);
														});
													// } else {
													// 	LOG.info(String.format("User %s already in group %s", githubUsername, groupName));
													// }
												} else {
													Throwable ex = new RuntimeException(String.format("Failed to find user %s. ", githubUsername));
													LOG.error(ex.getMessage(), ex);
													promise.fail(ex);
												}
											}).onFailure(ex -> {
												LOG.error("Failed to process square webook while querying user. ", ex);
												promise.fail(ex);
											});
										} else {
											Throwable ex = new RuntimeException("Failed to find group. ");
											LOG.error(ex.getMessage(), ex);
											promise.fail(ex);
										}
									} catch(Throwable ex) {
										LOG.error("Failed to process square webook while querying group. ", ex);
										promise.fail(ex);
									}
								}).onFailure(ex -> {
									LOG.error("Failed to process square webook while querying group. ", ex);
									promise.fail(ex);
								});
							} catch(Throwable ex) {
								LOG.error("Failed to process square webook while querying group. ", ex);
								promise.fail(ex);
							}
						}).onFailure(ex -> {
							LOG.error("Failed to process square webook. ", ex);
							promise.fail(ex);
						});
					} else {
						LOG.warn(String.format("Item not found with name %s. ", name));
						promise.complete();
					}
				}).onFailure(ex -> {
					LOG.error("Failed to process square webook. ", ex);
					promise.fail(ex);
				});
			} else {
				LOG.warn(String.format("Order %s missing name %s or GitHub username %s in state %s", orderId, name, githubUsername, state));
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error("The square item failed to process.");
			promise.fail(ex);
		}
		return promise.future();
	}

	/**
	 */
	public Future<Void> configureUi() {
		Promise<Void> promise = Promise.promise();
		try {
			String staticPath = config().getString(ConfigKeys.STATIC_PATH);

			StaticHandler staticHandler = StaticHandler.create().setCachingEnabled(false).setFilesReadOnly(false);
			if(staticPath != null) {
				staticHandler.setAllowRootFileSystemAccess(true);
				staticHandler.setWebRoot(staticPath);
				staticHandler.setFilesReadOnly(true);
			}
			router.route("/static/*").handler(staticHandler);

			router.get("/").handler(handler -> {
				try {
					String siteTemplatePath = config().getString(ConfigKeys.TEMPLATE_PATH);
					String pageTemplateUri = "/en-us/HomePage.htm";
					Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
					String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
					JsonObject ctx = ConfigKeys.getPageContext(config());
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					handler.response().putHeader("Content-Type", "text/html");
					handler.end(buffer);
				} catch(Exception ex) {
					LOG.error("Failed to load page. ", ex);
					handler.fail(ex);
				}
			});

			vertx.eventBus().consumer("square-order", message -> {
				try {
					String signature = message.headers().get("x-square-hmacsha256-signature");
					JsonObject orderBody = ((JsonObject)message.body()).getJsonObject("context").getJsonObject("params").getJsonObject("body");
					String oId = Optional.ofNullable(orderBody.getJsonObject("data").getJsonObject("object").getJsonObject("order_created")).map(c -> c.getString("order_id")).orElse(null);
					if(oId == null)
						oId = Optional.ofNullable(orderBody.getJsonObject("data").getJsonObject("object").getJsonObject("order_updated")).map(c -> c.getString("order_id")).orElse(null);
					String orderId = oId;
					String st = Optional.ofNullable(orderBody.getJsonObject("data").getJsonObject("object").getJsonObject("order_created")).map(c -> c.getString("state")).orElse(null);
					if(st == null)
						st = Optional.ofNullable(orderBody.getJsonObject("data").getJsonObject("object").getJsonObject("order_updated")).map(c -> c.getString("state")).orElse(null);
					String state = st;
					if(orderId != null && "OPEN".equals(state)) {
						String orderLock = String.format("square-order-", orderId);
						SharedData sharedData = vertx.sharedData();
						sharedData.getLockWithTimeout(orderLock, config().getLong(ConfigKeys.SQUARE_WEBHOOK_TIMEOUT_MILLIS, 3000L)).onSuccess(lock -> {
							try {
								String squareSignatureKey = config().getString(ConfigKeys.SQUARE_SIGNATURE_KEY);
								String squareNotificationUrl = config().getString(ConfigKeys.SQUARE_NOTIFICATION_URL);
								if(squareSignatureKey != null && squareNotificationUrl != null) {
									Boolean isFromSquare = WebhooksHelper.isValidWebhookEventSignature(orderBody.encode(), signature, squareSignatureKey, squareNotificationUrl);
									if(isFromSquare) {
										OrdersApi ordersApi = squareClient.getOrdersApi();
										CustomersApi customersApi = squareClient.getCustomersApi();
										RetrieveOrderResponse orderResponse = ordersApi.retrieveOrder(orderId);
										Order order = orderResponse.getOrder();
										String state2 = state;
										String orderId2 = orderId;

										List<Future<String>> futures = new ArrayList<>();
										for(OrderLineItem item : order.getLineItems()) {
											futures.add(Future.future(promise1 -> {
												processSquareItem(customersApi, message, orderBody, order, orderId2, state2, item).onSuccess(a -> {
													promise1.complete();
												}).onFailure(ex -> {
													promise1.fail(ex);
												});
											}));
										}
										Future.all(futures).onSuccess(b -> {
											vertx.setTimer(config().getInteger(ConfigKeys.SQUARE_WEBHOOK_UNLOCK_MILLIS, 60000), tid -> {
												lock.release();
												LOG.info(String.format("The orderId %s lock was released", orderId));
											});
										}).onFailure(ex -> {
											message.fail(400, ex.getMessage());
										});
									} else {
										Throwable ex = new RuntimeException("Webhook is not from Square. ");
										LOG.error("Webhook is not from Square. ", ex);
										message.fail(400, ex.getMessage());
									}
								} else {
									Throwable ex = new RuntimeException("Missing Square Signature Key and Notification URL. ");
									LOG.error("Missing Square Signature Key and Notification URL. ", ex);
									message.fail(400, ex.getMessage());
								}
							} catch(Throwable ex) {
								LOG.error("Failed to process square webook. ", ex);
								message.fail(400, ex.getMessage());
							}
						}).onFailure(ex -> {
							LOG.warn(String.format("The orderId %s did not obtain a lock", orderId));
						});
					} else {
						LOG.info(String.format("Missing orderId %s or OPEN state %s", orderId, state));
					}
				} catch(Throwable ex) {
					LOG.error("Failed to process square webook. ", ex);
					message.fail(400, ex.getMessage());
				}
			});
			if(Boolean.valueOf(config().getString(ConfigKeys.ENABLE_SQUARE))) {
				router.post("/square/order").handler(BodyHandler.create()).handler(handler -> {
					try {
						String signature = handler.request().headers().get("x-square-hmacsha256-signature");
						JsonObject params = new JsonObject();
						params.put("body", handler.body().asJsonObject());
						params.put("path", new JsonObject());
						params.put("cookie", new JsonObject());
						params.put("header", new JsonObject());
						params.put("form", new JsonObject());
						params.put("query", new JsonObject());
						JsonObject context = new JsonObject().put("params", params).put("user", null);
						JsonObject json = new JsonObject().put("context", context);
						vertx.eventBus().publish("square-order", json, new DeliveryOptions().addHeader("x-square-hmacsha256-signature", signature));

						Buffer buffer = Buffer.buffer(new JsonObject().encodePrettily());
						handler.response().putHeader("Content-Type", "application/json");
						handler.end(buffer);
					} catch(Throwable ex) {
						LOG.error("Failed to process square webook. ", ex);
						handler.fail(ex);
					}
				});
			}

			router.getWithRegex("\\/en-us/download(?<uri>.*)").handler(oauth2AuthHandler).handler(handler -> {
				String originalUri = handler.pathParam("uri");
				SiteUserEnUSApiServiceImpl apiSiteUser = new SiteUserEnUSApiServiceImpl();
				initializeApiService(apiSiteUser);
				ServiceRequest serviceRequest = apiSiteUser.generateServiceRequest(handler);
				apiSiteUser.user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_ComputateSiteUser, "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
					try {

						String uri = handler.pathParam("uri");
						String url = String.format("%s%s", config().getString(ComputateConfigKeys.SITE_BASE_URL), uri);
						webClient.post(
								Integer.parseInt(config().getString(ComputateConfigKeys.AUTH_PORT))
								, config().getString(ComputateConfigKeys.AUTH_HOST_NAME)
								, config().getString(ComputateConfigKeys.AUTH_TOKEN_URI)
								)
								.ssl(Boolean.valueOf(config().getString(ComputateConfigKeys.AUTH_SSL)))
								.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
								.expect(ResponsePredicate.status(200))
								.sendForm(MultiMap.caseInsensitiveMultiMap()
										.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket")
										.add("audience", config().getString(ComputateConfigKeys.AUTH_CLIENT))
										.add("response_mode", "permissions")
										.add("permission", String.format("%s#%s", uri, "GET"))
						).onFailure(ex -> {
							String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
							LOG.error(String.format("Failed to render page %s", originalUri), ex);
							handler.fail(403, ex);
						}).onSuccess(authorizationDecision -> {
							try {
								JsonArray scopes = authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
								if(!scopes.contains("GET")) {
									String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
									Throwable ex = new RuntimeException(msg);
									LOG.error(String.format("Failed to render page %s", originalUri), ex);
									handler.fail(403, ex);
								} else {
									SiteUser user = siteRequest.getSiteUser_(SiteUser.class);
									JsonObject query = new JsonObject();
									MultiMap queryParams = handler.queryParams();
									for(String name : queryParams.names()) {
										JsonArray array = query.getJsonArray(name);
										List<String> vals = queryParams.getAll(name);
										if(array == null) {
											array = new JsonArray();
											query.put(name, array);
										}
										for(String val : vals) {
											array.add(val);
										}
									}
									SearchList<BaseResult> l = new SearchList<>();
									l.q("*:*");
									l.setC(BaseResult.class);
									l.fq(String.format("%s_docvalues_string:%s", "uri", SearchTool.escapeQueryChars(uri)));
									l.setStore(true);
									handler.response().headers().add("Content-Type", "text/html");
									l.promiseDeepForClass(siteRequest).onSuccess(a -> {
										BaseResult result = l.first();
										try {
											String downloadPath = String.format("%s%s.zip", config().getString(ConfigKeys.DOWNLOAD_PATH), uri);
											vertx.fileSystem().readFile(downloadPath).onSuccess(buffer -> {
												handler.response().putHeader("Content-Type", "application/zip")
														.putHeader("Content-Disposition", "attachment; filename=\"" + (String)result.obtainForClass("pageId") + ".zip\"");
												handler.end(buffer);
											}).onFailure(ex -> {
												LOG.error(String.format("Failed to find download %s", uri), ex);
												handler.fail(ex);
											});
										} catch (Exception ex) {
											LOG.error(String.format("Failed to render page %s", uri), ex);
											handler.fail(ex);
										}
									}).onFailure(ex -> {
										LOG.error(String.format("Failed to render page %s", uri), ex);
										handler.fail(ex);
									});
								}
							} catch (Exception ex) {
								LOG.error(String.format("Failed to render page %s", uri), ex);
								handler.fail(ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("Failed to render page %s", originalUri), ex);
							handler.fail(ex);
						});
					} catch(Exception ex) {
						LOG.error("Failed to load page. ", ex);
						handler.fail(ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("Failed to render page %s", originalUri), ex);
					handler.fail(ex);
				});
			});

			router.post("/ngsi-ld/subscription").handler(ctx -> {
				SiteUserEnUSApiServiceImpl apiSiteUser = new SiteUserEnUSApiServiceImpl();
				initializeApiService(apiSiteUser);
				apiSiteUser.listPUTImportSmartDataModel(ctx).onSuccess(a -> {
					ctx.response().setStatusCode(200);
					ctx.end();
				}).onFailure(ex -> {
					LOG.error("NGSI-LD subscription failed", ex);
					promise.fail(ex);
				});
			});

			router.get("/hackathons").handler(ctx -> {
				ctx.response().putHeader("location", "/en-us/view/article/hackathons");
				ctx.response().setStatusCode(302);
				ctx.end();
			});

			router.get("/websites").handler(ctx -> {
				ctx.response().putHeader("location", "/en-us/view/article/websites");
				ctx.response().setStatusCode(302);
				ctx.end();
			});

			SiteUserEnUSApiServiceImpl apiSiteUser = new SiteUserEnUSApiServiceImpl();
			initializeApiService(apiSiteUser);
			SiteRoutes.routes(router, oauth2AuthHandler, config(), webClient, apiSiteUser);

			LOG.info("The UI was configured properly.");
			promise.complete();
		} catch(Exception ex) {
			LOG.error("The UI was not configured properly.");
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> putVarsInRoutingContext(RoutingContext ctx) {
		Promise<Void> promise = Promise.promise();
		try {
			for(Entry<String, String> entry : ctx.queryParams()) {
				String paramName = entry.getKey();
				String paramObject = entry.getValue();
				String entityVar = null;
				String valueIndexed = null;

				switch(paramName) {
					case "var":
						entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
						valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
						ctx.put(entityVar, valueIndexed);
						break;
				}
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error(String.format("putVarsInRoutingContext failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**	
	 * Start the Vert.x server. 
	 **/
	public Future<Void> startServer() {
		Promise<Void> promise = Promise.promise();

		try {
			Boolean sslPassthrough = Boolean.valueOf(config().getString(ConfigKeys.SSL_PASSTHROUGH));
			String siteBaseUrl = config().getString(ConfigKeys.SITE_BASE_URL);
			Integer sitePort = Integer.parseInt(config().getString(ConfigKeys.SITE_PORT));
			String sslJksPath = config().getString(ConfigKeys.SSL_JKS_PATH);
			String sslPrivateKeyPath = config().getString(ConfigKeys.SSL_KEY_PATH);
			String sslCertPath = config().getString(ConfigKeys.SSL_CERT_PATH);
			HttpServerOptions options = new HttpServerOptions();
			if(sslPassthrough) {
				if(sslPrivateKeyPath != null && sslCertPath != null) {
					options.setPemKeyCertOptions(new PemKeyCertOptions().setKeyPath(sslPrivateKeyPath).setCertPath(sslCertPath));
					LOG.info(String.format("Configuring SSL: %s", sslPrivateKeyPath));
					LOG.info(String.format("Configuring SSL: %s", sslCertPath));
				} else if(sslJksPath != null) {
					options.setKeyStoreOptions(new JksOptions().setPath(sslJksPath).setPassword(config().getString(ConfigKeys.SSL_JKS_PASSWORD)));
					LOG.info(String.format("Configuring SSL: %s", sslJksPath));
				}
				options.setSsl(true);
			}
			options.setPort(sitePort);
	
			LOG.info(String.format("HTTP server starting: %s", siteBaseUrl));
			vertx.createHttpServer(options).requestHandler(router).listen(ar -> {
				if (ar.succeeded()) {
					LOG.info(String.format("The HTTP server is running: %s", siteBaseUrl));
					promise.complete();
				} else {
					LOG.error("The server is not configured properly.", ar.cause());
					promise.fail(ar.cause());
				}
			});
		} catch (Exception ex) {
			LOG.error("The server is not configured properly.", ex);
			promise.fail(ex);
		}

		return promise.future();
	}

	/**	
	 * This is called by Vert.x when the verticle instance is undeployed. 
	 * Setup the stopPromise to handle tearing down the server. 
	 **/
	@Override()
	public void stop(Promise<Void> promise) throws Exception, Exception {
		stopPgPool().onComplete(a -> {
			stopMqtt().onComplete(b -> {
				promise.complete();
			});
		});
	}

	/**
	 **/
	public Future<Void> stopPgPool() {
		Promise<Void> promise = Promise.promise();

		if(pgPool != null) {
			pgPool.close().onSuccess(a -> {
				LOG.info("The database client connection was closed.");
				promise.complete();
			}).onFailure(ex -> {
				LOG.error("The database client connection was closed.", ex);
				promise.fail(ex);
			});
		} else {
			promise.complete();
		}

		return promise.future();
	}

	/**
	 **/
	public Future<Void> stopMqtt() {
		Promise<Void> promise = Promise.promise();

		if(mqttClient != null) {
			mqttClient.disconnect().onSuccess(a -> {
				LOG.info("The MQTT client connection was closed.");
				promise.complete();
			}).onFailure(ex -> {
				LOG.error("Could not close the MQTT client connection.", ex);
				promise.fail(ex);
			});
		} else {
			promise.complete();
		}

		return promise.future();
	}

	public String toId(String s) {
		if(s != null) {
			s = Normalizer.normalize(s, Normalizer.Form.NFD);
			s = StringUtils.lowerCase(s);
			s = StringUtils.trim(s);
			s = StringUtils.replacePattern(s, "\\s{1,}", "-");
			s = StringUtils.replacePattern(s, "[^\\w-]", "");
			s = StringUtils.replacePattern(s, "-{2,}", "-");
		}

		return s;
	}

	public Integer getSiteInstances() {
		return siteInstances;
	}

	public void setSiteInstances(Integer siteInstances) {
		this.siteInstances = siteInstances;
	}

	public Integer getWorkerPoolSize() {
		return workerPoolSize;
	}

	public void setWorkerPoolSize(Integer workerPoolSize) {
		this.workerPoolSize = workerPoolSize;
	}

	public Integer getJdbcMaxPoolSize() {
		return jdbcMaxPoolSize;
	}

	public void setJdbcMaxPoolSize(Integer jdbcMaxPoolSize) {
		this.jdbcMaxPoolSize = jdbcMaxPoolSize;
	}

	public Integer getJdbcMaxWaitQueueSize() {
		return jdbcMaxWaitQueueSize;
	}

	public void setJdbcMaxWaitQueueSize(Integer jdbcMaxWaitQueueSize) {
		this.jdbcMaxWaitQueueSize = jdbcMaxWaitQueueSize;
	}

	public Pool getPgPool() {
		return pgPool;
	}

	public void setPgPool(Pool pgPool) {
		this.pgPool = pgPool;
	}

	public WebClient getWebClient() {
		return webClient;
	}

	public void setWebClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Router getRouter() {
		return router;
	}

	public void setRouter(Router router) {
		this.router = router;
	}

	public WorkerExecutor getWorkerExecutor() {
		return workerExecutor;
	}

	public void setWorkerExecutor(WorkerExecutor workerExecutor) {
		this.workerExecutor = workerExecutor;
	}

	public OAuth2Auth getOauth2AuthenticationProvider() {
		return oauth2AuthenticationProvider;
	}

	public void setOauth2AuthenticationProvider(OAuth2Auth oauth2AuthenticationProvider) {
		this.oauth2AuthenticationProvider = oauth2AuthenticationProvider;
	}

	public AuthorizationProvider getAuthorizationProvider() {
		return authorizationProvider;
	}

	public void setAuthorizationProvider(AuthorizationProvider authorizationProvider) {
		this.authorizationProvider = authorizationProvider;
	}
}
