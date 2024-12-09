package org.computate.site.model.fiware.weatherobserved;

import org.computate.site.request.SiteRequest;
import org.computate.site.user.SiteUser;
import org.computate.vertx.api.ApiRequest;
import org.computate.vertx.search.list.SearchResult;
import org.computate.vertx.verticle.EmailVerticle;
import org.computate.site.config.ConfigKeys;
import org.computate.vertx.api.BaseApiServiceImpl;
import io.vertx.ext.web.client.WebClient;
import java.util.Objects;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.pgclient.PgPool;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.core.json.impl.JsonUtil;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import com.hubspot.jinjava.Jinjava;
import io.vertx.core.eventbus.DeliveryOptions;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.time.Instant;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.computate.search.response.solr.SolrResponse.StatsField;
import java.util.stream.Collectors;
import io.vertx.core.json.Json;
import org.apache.commons.lang3.StringUtils;
import java.security.Principal;
import org.apache.commons.lang3.exception.ExceptionUtils;
import java.io.PrintWriter;
import java.util.Collection;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.math.NumberUtils;
import io.vertx.ext.web.Router;
import java.nio.file.Path;
import java.nio.file.Files;
import com.google.common.io.Resources;
import java.nio.charset.StandardCharsets;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.config.ComputateConfigKeys;
import io.vertx.ext.reactivestreams.ReactiveReadStream;
import io.vertx.ext.reactivestreams.ReactiveWriteStream;
import io.vertx.core.MultiMap;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.sqlclient.Transaction;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;
import io.vertx.sqlclient.Row;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.sql.Timestamp;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.AsyncResult;
import java.net.URLEncoder;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.CompositeFuture;
import io.vertx.core.http.HttpHeaders;
import java.nio.charset.Charset;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import java.util.HashMap;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import java.util.Optional;
import java.util.stream.Stream;
import java.net.URLDecoder;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map.Entry;
import java.util.Iterator;
import org.computate.search.tool.SearchTool;
import org.computate.search.response.solr.SolrResponse;
import java.util.Base64;
import java.time.ZonedDateTime;
import org.apache.commons.lang3.BooleanUtils;
import org.computate.vertx.search.list.SearchList;
import org.computate.site.model.fiware.weatherobserved.WeatherObservedPage;


/**
 * Translate: false
 * Generated: true
 **/
public class WeatherObservedEnUSGenApiServiceImpl extends BaseApiServiceImpl implements WeatherObservedEnUSGenApiService {

	protected static final Logger LOG = LoggerFactory.getLogger(WeatherObservedEnUSGenApiServiceImpl.class);

	// Search //

	@Override
	public void searchWeatherObserved(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.expect(ResponsePredicate.status(200))
					.sendForm(MultiMap.caseInsensitiveMultiMap()
							.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket")
							.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT))
							.add("response_mode", "permissions")
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "GET"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "POST"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "DELETE"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "PATCH"))
			).onFailure(ex -> {
				String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(403, "FORBIDDEN",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "403")
								.put("errorMessage", msg)
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
					)
				));
			}).onSuccess(authorizationDecision -> {
				try {
					JsonArray scopes = authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					if(!scopes.contains("GET")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchWeatherObservedList(siteRequest, false, true, false).onSuccess(listWeatherObserved -> {
							response200SearchWeatherObserved(listWeatherObserved).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchWeatherObserved succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchWeatherObserved failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchWeatherObserved failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchWeatherObserved failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchWeatherObserved failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("searchWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200SearchWeatherObserved(SearchList<WeatherObserved> listWeatherObserved) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listWeatherObserved.getSiteRequest_(SiteRequest.class);
			List<String> fls = listWeatherObserved.getRequest().getFields();
			JsonObject json = new JsonObject();
			JsonArray l = new JsonArray();
			listWeatherObserved.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					for(String fieldName : json2.fieldNames()) {
						String v = WeatherObserved.varIndexedWeatherObserved(fieldName);
						if(v != null)
							fieldNames.add(WeatherObserved.varIndexedWeatherObserved(fieldName));
					}
					if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals("saves_docvalues_strings")) {
						fieldNames.removeAll(Optional.ofNullable(json2.getJsonArray("saves_docvalues_strings")).orElse(new JsonArray()).stream().map(s -> s.toString()).collect(Collectors.toList()));
						fieldNames.remove("pk_docvalues_long");
						fieldNames.remove("created_docvalues_date");
					}
					else if(fls.size() >= 1) {
						fieldNames.removeAll(fls);
					}
					for(String fieldName : fieldNames) {
						if(!fls.contains(fieldName))
							json2.remove(fieldName);
					}
				}
				l.add(json2);
			});
			json.put("list", l);
			response200Search(listWeatherObserved.getRequest(), listWeatherObserved.getResponse(), json);
			if(json == null) {
				String entityId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("entityId");
						String m = String.format("%s %s not found", "weather observed device", entityId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchWeatherObserved(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
		if(pivots != null) {
			for(SolrResponse.Pivot pivotField : pivots) {
				String entityIndexed = pivotField.getField();
				String entityVar = StringUtils.substringBefore(entityIndexed, "_docvalues_");
				JsonObject pivotJson = new JsonObject();
				pivotArray.add(pivotJson);
				pivotJson.put("field", entityVar);
				pivotJson.put("value", pivotField.getValue());
				pivotJson.put("count", pivotField.getCount());
				Collection<SolrResponse.PivotRange> pivotRanges = pivotField.getRanges().values();
				List<SolrResponse.Pivot> pivotFields2 = pivotField.getPivotList();
				if(pivotRanges != null) {
					JsonObject rangeJson = new JsonObject();
					pivotJson.put("ranges", rangeJson);
					for(SolrResponse.PivotRange rangeFacet : pivotRanges) {
						JsonObject rangeFacetJson = new JsonObject();
						String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), "_docvalues_");
						rangeJson.put(rangeFacetVar, rangeFacetJson);
						JsonObject rangeFacetCountsObject = new JsonObject();
						rangeFacetJson.put("counts", rangeFacetCountsObject);
						rangeFacet.getCounts().forEach((value, count) -> {
							rangeFacetCountsObject.put(value, count);
						});
					}
				}
				if(pivotFields2 != null) {
					JsonArray pivotArray2 = new JsonArray();
					pivotJson.put("pivot", pivotArray2);
					responsePivotSearchWeatherObserved(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// GET //

	@Override
	public void getWeatherObserved(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.expect(ResponsePredicate.status(200))
					.sendForm(MultiMap.caseInsensitiveMultiMap()
							.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket")
							.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT))
							.add("response_mode", "permissions")
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "GET"))
			).onFailure(ex -> {
				String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(403, "FORBIDDEN",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "403")
								.put("errorMessage", msg)
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
					)
				));
			}).onSuccess(authorizationDecision -> {
				try {
					JsonArray scopes = authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					if(!scopes.contains("GET")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchWeatherObservedList(siteRequest, false, true, false).onSuccess(listWeatherObserved -> {
							response200GETWeatherObserved(listWeatherObserved).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("getWeatherObserved succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("getWeatherObserved failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("getWeatherObserved failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("getWeatherObserved failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("getWeatherObserved failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("getWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200GETWeatherObserved(SearchList<WeatherObserved> listWeatherObserved) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listWeatherObserved.getSiteRequest_(SiteRequest.class);
			JsonObject json = JsonObject.mapFrom(listWeatherObserved.getList().stream().findFirst().orElse(null));
			if(json == null) {
				String entityId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("entityId");
						String m = String.format("%s %s not found", "weather observed device", entityId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200GETWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PATCH //

	@Override
	public void patchWeatherObserved(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("patchWeatherObserved started. "));
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.expect(ResponsePredicate.status(200))
					.sendForm(MultiMap.caseInsensitiveMultiMap()
							.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket")
							.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT))
							.add("response_mode", "permissions")
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "PATCH"))
			).onFailure(ex -> {
				String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(403, "FORBIDDEN",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "403")
								.put("errorMessage", msg)
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
					)
				));
			}).onSuccess(authorizationDecision -> {
				try {
					JsonArray scopes = authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					if(!scopes.contains("PATCH")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchWeatherObservedList(siteRequest, false, true, true).onSuccess(listWeatherObserved -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listWeatherObserved.getRequest().getRows());
								apiRequest.setNumFound(listWeatherObserved.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listWeatherObserved.first());
								apiRequest.setId(Optional.ofNullable(listWeatherObserved.first()).map(o2 -> o2.getEntityId()).orElse(null));
								apiRequest.setPk(Optional.ofNullable(listWeatherObserved.first()).map(o2 -> o2.getPk()).orElse(null));
								eventBus.publish("websocketWeatherObserved", JsonObject.mapFrom(apiRequest).toString());

								listPATCHWeatherObserved(apiRequest, listWeatherObserved).onSuccess(e -> {
									response200PATCHWeatherObserved(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("patchWeatherObserved succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("patchWeatherObserved failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("patchWeatherObserved failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("patchWeatherObserved failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("patchWeatherObserved failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("patchWeatherObserved failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("patchWeatherObserved failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("patchWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPATCHWeatherObserved(ApiRequest apiRequest, SearchList<WeatherObserved> listWeatherObserved) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listWeatherObserved.getSiteRequest_(SiteRequest.class);
		listWeatherObserved.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			WeatherObserved o2 = jsonObject.mapTo(WeatherObserved.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				patchWeatherObservedFuture(o2, false).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listPATCHWeatherObserved failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listWeatherObserved.next().onSuccess(next -> {
				if(next) {
					listPATCHWeatherObserved(apiRequest, listWeatherObserved).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPATCHWeatherObserved failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listPATCHWeatherObserved failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listPATCHWeatherObserved failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void patchWeatherObservedFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			try {
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				searchWeatherObservedList(siteRequest, false, true, true).onSuccess(listWeatherObserved -> {
					try {
						WeatherObserved o = listWeatherObserved.first();
						if(o != null && listWeatherObserved.getResponse().getResponse().getNumFound() == 1) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1L);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
								siteRequest.getRequestVars().put( "refresh", "false" );
							}
							if(apiRequest.getNumFound() == 1L)
								apiRequest.setOriginal(o);
							apiRequest.setId(Optional.ofNullable(listWeatherObserved.first()).map(o2 -> o2.getEntityId()).orElse(null));
							apiRequest.setPk(Optional.ofNullable(listWeatherObserved.first()).map(o2 -> o2.getPk()).orElse(null));
							JsonObject jsonObject = JsonObject.mapFrom(o);
							WeatherObserved o2 = jsonObject.mapTo(WeatherObserved.class);
							o2.setSiteRequest_(siteRequest);
							patchWeatherObservedFuture(o2, false).onSuccess(o3 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("patchWeatherObserved failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("patchWeatherObserved failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("patchWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("patchWeatherObserved failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<WeatherObserved> patchWeatherObservedFuture(WeatherObserved o, Boolean entityId) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<WeatherObserved> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<WeatherObserved> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsWeatherObserved(siteRequest).onSuccess(a -> {
					sqlPATCHWeatherObserved(o, entityId).onSuccess(weatherObserved -> {
						persistWeatherObserved(weatherObserved, true).onSuccess(c -> {
							relateWeatherObserved(weatherObserved).onSuccess(d -> {
								indexWeatherObserved(weatherObserved).onSuccess(o2 -> {
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
											o2.apiRequestWeatherObserved();
											if(apiRequest.getVars().size() > 0)
												eventBus.publish("websocketWeatherObserved", JsonObject.mapFrom(apiRequest).toString());
										}
									}
									promise1.complete(weatherObserved);
								}).onFailure(ex -> {
									promise1.fail(ex);
								});
							}).onFailure(ex -> {
								promise1.fail(ex);
							});
						}).onFailure(ex -> {
							promise1.fail(ex);
						});
					}).onFailure(ex -> {
						promise1.fail(ex);
					});
				}).onFailure(ex -> {
					promise1.fail(ex);
				});
				return promise1.future();
			}).onSuccess(a -> {
				siteRequest.setSqlConnection(null);
			}).onFailure(ex -> {
				siteRequest.setSqlConnection(null);
				promise.fail(ex);
			}).compose(weatherObserved -> {
				Promise<WeatherObserved> promise2 = Promise.promise();
				refreshWeatherObserved(weatherObserved).onSuccess(a -> {
					promise2.complete(weatherObserved);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(weatherObserved -> {
				promise.complete(weatherObserved);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("patchWeatherObservedFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<WeatherObserved> sqlPATCHWeatherObserved(WeatherObserved o, Boolean entityId) {
		Promise<WeatherObserved> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE WeatherObserved SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			WeatherObserved o2 = new WeatherObserved();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			for(String entityVar : methodNames) {
				switch(entityVar) {
					case "setName":
							o2.setName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_name + "=$" + num);
							num++;
							bParams.add(o2.sqlName());
						break;
					case "setDescription":
							o2.setDescription(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_description + "=$" + num);
							num++;
							bParams.add(o2.sqlDescription());
						break;
					case "setCreated":
							o2.setCreated(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_created + "=$" + num);
							num++;
							bParams.add(o2.sqlCreated());
						break;
					case "setAddress":
							o2.setAddress(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_address + "=$" + num);
							num++;
							bParams.add(o2.sqlAddress());
						break;
					case "setAlternateName":
							o2.setAlternateName(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_alternateName + "=$" + num);
							num++;
							bParams.add(o2.sqlAlternateName());
						break;
					case "setArchived":
							o2.setArchived(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_archived + "=$" + num);
							num++;
							bParams.add(o2.sqlArchived());
						break;
					case "setLocation":
							o2.setLocation(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_location + "=$" + num);
							num++;
							bParams.add(o2.sqlLocation());
						break;
					case "setSessionId":
							o2.setSessionId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_sessionId + "=$" + num);
							num++;
							bParams.add(o2.sqlSessionId());
						break;
					case "setAreaServed":
							o2.setAreaServed(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_areaServed + "=$" + num);
							num++;
							bParams.add(o2.sqlAreaServed());
						break;
					case "setUserKey":
							o2.setUserKey(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_userKey + "=$" + num);
							num++;
							bParams.add(o2.sqlUserKey());
						break;
					case "setDataProvider":
							o2.setDataProvider(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_dataProvider + "=$" + num);
							num++;
							bParams.add(o2.sqlDataProvider());
						break;
					case "setDateCreated":
							o2.setDateCreated(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_dateCreated + "=$" + num);
							num++;
							bParams.add(o2.sqlDateCreated());
						break;
					case "setDateModified":
							o2.setDateModified(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_dateModified + "=$" + num);
							num++;
							bParams.add(o2.sqlDateModified());
						break;
					case "setTitle":
							o2.setTitle(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_title + "=$" + num);
							num++;
							bParams.add(o2.sqlTitle());
						break;
					case "setDateObserved":
							o2.setDateObserved(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_dateObserved + "=$" + num);
							num++;
							bParams.add(o2.sqlDateObserved());
						break;
					case "setDisplayPage":
							o2.setDisplayPage(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_displayPage + "=$" + num);
							num++;
							bParams.add(o2.sqlDisplayPage());
						break;
					case "setOwner":
							o2.setOwner(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_owner + "=$" + num);
							num++;
							bParams.add(o2.sqlOwner());
						break;
					case "setRefDevice":
							o2.setRefDevice(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_refDevice + "=$" + num);
							num++;
							bParams.add(o2.sqlRefDevice());
						break;
					case "setRefPointOfInterest":
							o2.setRefPointOfInterest(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_refPointOfInterest + "=$" + num);
							num++;
							bParams.add(o2.sqlRefPointOfInterest());
						break;
					case "setSource":
							o2.setSource(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_source + "=$" + num);
							num++;
							bParams.add(o2.sqlSource());
						break;
					case "setSeeAlso":
							o2.setSeeAlso(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_seeAlso + "=$" + num);
							num++;
							bParams.add(o2.sqlSeeAlso());
						break;
					case "setAirQualityIndex":
							o2.setAirQualityIndex(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_airQualityIndex + "=$" + num);
							num++;
							bParams.add(o2.sqlAirQualityIndex());
						break;
					case "setAirQualityIndexForecast":
							o2.setAirQualityIndexForecast(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_airQualityIndexForecast + "=$" + num);
							num++;
							bParams.add(o2.sqlAirQualityIndexForecast());
						break;
					case "setAqiMajorPollutant":
							o2.setAqiMajorPollutant(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_aqiMajorPollutant + "=$" + num);
							num++;
							bParams.add(o2.sqlAqiMajorPollutant());
						break;
					case "setAqiMajorPollutantForecast":
							o2.setAqiMajorPollutantForecast(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_aqiMajorPollutantForecast + "=$" + num);
							num++;
							bParams.add(o2.sqlAqiMajorPollutantForecast());
						break;
					case "setAtmosphericPressure":
							o2.setAtmosphericPressure(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_atmosphericPressure + "=$" + num);
							num++;
							bParams.add(o2.sqlAtmosphericPressure());
						break;
					case "setPressureTendency":
							o2.setPressureTendency(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_pressureTendency + "=$" + num);
							num++;
							bParams.add(o2.sqlPressureTendency());
						break;
					case "setGustSpeed":
							o2.setGustSpeed(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_gustSpeed + "=$" + num);
							num++;
							bParams.add(o2.sqlGustSpeed());
						break;
					case "setWindDirection":
							o2.setWindDirection(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_windDirection + "=$" + num);
							num++;
							bParams.add(o2.sqlWindDirection());
						break;
					case "setWindSpeed":
							o2.setWindSpeed(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_windSpeed + "=$" + num);
							num++;
							bParams.add(o2.sqlWindSpeed());
						break;
					case "setPrecipitation":
							o2.setPrecipitation(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_precipitation + "=$" + num);
							num++;
							bParams.add(o2.sqlPrecipitation());
						break;
					case "setPrecipitationForecast":
							o2.setPrecipitationForecast(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_precipitationForecast + "=$" + num);
							num++;
							bParams.add(o2.sqlPrecipitationForecast());
						break;
					case "setRelativeHumidity":
							o2.setRelativeHumidity(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_relativeHumidity + "=$" + num);
							num++;
							bParams.add(o2.sqlRelativeHumidity());
						break;
					case "setRelativeHumidityForecast":
							o2.setRelativeHumidityForecast(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_relativeHumidityForecast + "=$" + num);
							num++;
							bParams.add(o2.sqlRelativeHumidityForecast());
						break;
					case "setSnowHeight":
							o2.setSnowHeight(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_snowHeight + "=$" + num);
							num++;
							bParams.add(o2.sqlSnowHeight());
						break;
					case "setAirTemperatureForecast":
							o2.setAirTemperatureForecast(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_airTemperatureForecast + "=$" + num);
							num++;
							bParams.add(o2.sqlAirTemperatureForecast());
						break;
					case "setAirTemperatureTSA":
							o2.setAirTemperatureTSA(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_airTemperatureTSA + "=$" + num);
							num++;
							bParams.add(o2.sqlAirTemperatureTSA());
						break;
					case "setFeelsLikeTemperature":
							o2.setFeelsLikeTemperature(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_feelsLikeTemperature + "=$" + num);
							num++;
							bParams.add(o2.sqlFeelsLikeTemperature());
						break;
					case "setTemperature":
							o2.setTemperature(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_temperature + "=$" + num);
							num++;
							bParams.add(o2.sqlTemperature());
						break;
					case "setSolarRadiation":
							o2.setSolarRadiation(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_solarRadiation + "=$" + num);
							num++;
							bParams.add(o2.sqlSolarRadiation());
						break;
					case "setStreamGauge":
							o2.setStreamGauge(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_streamGauge + "=$" + num);
							num++;
							bParams.add(o2.sqlStreamGauge());
						break;
					case "setUVIndexMax":
							o2.setUVIndexMax(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_uVIndexMax + "=$" + num);
							num++;
							bParams.add(o2.sqlUVIndexMax());
						break;
					case "setVisibility":
							o2.setVisibility(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_visibility + "=$" + num);
							num++;
							bParams.add(o2.sqlVisibility());
						break;
					case "setWeatherType":
							o2.setWeatherType(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_weatherType + "=$" + num);
							num++;
							bParams.add(o2.sqlWeatherType());
						break;
					case "setDewPoint":
							o2.setDewPoint(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_dewPoint + "=$" + num);
							num++;
							bParams.add(o2.sqlDewPoint());
						break;
					case "setDiffuseIrradiation":
							o2.setDiffuseIrradiation(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_diffuseIrradiation + "=$" + num);
							num++;
							bParams.add(o2.sqlDiffuseIrradiation());
						break;
					case "setDirectIrradiation":
							o2.setDirectIrradiation(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_directIrradiation + "=$" + num);
							num++;
							bParams.add(o2.sqlDirectIrradiation());
						break;
					case "setIlluminance":
							o2.setIlluminance(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_illuminance + "=$" + num);
							num++;
							bParams.add(o2.sqlIlluminance());
						break;
					case "setNgsildTenant":
							o2.setNgsildTenant(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_ngsildTenant + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildTenant());
						break;
					case "setNgsildPath":
							o2.setNgsildPath(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_ngsildPath + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildPath());
						break;
					case "setNgsildData":
							o2.setNgsildData(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_ngsildData + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildData());
						break;
					case "setEntityId":
							o2.setEntityId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_entityId + "=$" + num);
							num++;
							bParams.add(o2.sqlEntityId());
						break;
					case "setNgsildContext":
							o2.setNgsildContext(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(WeatherObserved.VAR_ngsildContext + "=$" + num);
							num++;
							bParams.add(o2.sqlNgsildContext());
						break;
				}
			}
			bSql.append(" WHERE pk=$" + num);
			if(bParams.size() > 0) {
				bParams.add(pk);
				num++;
				futures2.add(0, Future.future(a -> {
					sqlConnection.preparedQuery(bSql.toString())
							.execute(Tuple.tuple(bParams)
							).onSuccess(b -> {
						a.handle(Future.succeededFuture());
					}).onFailure(ex -> {
						RuntimeException ex2 = new RuntimeException("value WeatherObserved failed", ex);
						LOG.error(String.format("relateWeatherObserved failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					WeatherObserved o3 = new WeatherObserved();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					promise.complete(o3);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPATCHWeatherObserved failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPATCHWeatherObserved failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPATCHWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200PATCHWeatherObserved(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String entityId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("entityId");
						String m = String.format("%s %s not found", "weather observed device", entityId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PATCHWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// POST //

	@Override
	public void postWeatherObserved(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("postWeatherObserved started. "));
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.expect(ResponsePredicate.status(200))
					.sendForm(MultiMap.caseInsensitiveMultiMap()
							.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket")
							.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT))
							.add("response_mode", "permissions")
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "POST"))
			).onFailure(ex -> {
				String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(403, "FORBIDDEN",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "403")
								.put("errorMessage", msg)
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
					)
				));
			}).onSuccess(authorizationDecision -> {
				try {
					JsonArray scopes = authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					if(!scopes.contains("POST")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						ApiRequest apiRequest = new ApiRequest();
						apiRequest.setRows(1L);
						apiRequest.setNumFound(1L);
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest);
						siteRequest.setApiRequest_(apiRequest);
						eventBus.publish("websocketWeatherObserved", JsonObject.mapFrom(apiRequest).toString());
						JsonObject params = new JsonObject();
						params.put("body", siteRequest.getJsonObject());
						params.put("path", new JsonObject());
						params.put("cookie", new JsonObject());
						params.put("header", siteRequest.getServiceRequest().getParams().getJsonObject("header"));
						params.put("form", new JsonObject());
						JsonObject query = new JsonObject();
						Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
						Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
						if(softCommit == null && commitWithin == null)
							softCommit = true;
						if(softCommit != null)
							query.put("softCommit", softCommit);
						if(commitWithin != null)
							query.put("commitWithin", commitWithin);
						params.put("query", query);
						JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
						JsonObject json = new JsonObject().put("context", context);
						eventBus.request(WeatherObserved.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postWeatherObservedFuture")).onSuccess(a -> {
							JsonObject responseMessage = (JsonObject)a.body();
							JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
							apiRequest.setPk(Long.parseLong(responseBody.getString("pk")));
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
							LOG.debug(String.format("postWeatherObserved succeeded. "));
						}).onFailure(ex -> {
							LOG.error(String.format("postWeatherObserved failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("postWeatherObserved failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postWeatherObserved failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("postWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	@Override
	public void postWeatherObservedFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			ApiRequest apiRequest = new ApiRequest();
			apiRequest.setRows(1L);
			apiRequest.setNumFound(1L);
			apiRequest.setNumPATCH(0L);
			apiRequest.initDeepApiRequest(siteRequest);
			siteRequest.setApiRequest_(apiRequest);
			if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
				siteRequest.getRequestVars().put( "refresh", "false" );
			}
			postWeatherObservedFuture(siteRequest, false).onSuccess(o -> {
				eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
			}).onFailure(ex -> {
				eventHandler.handle(Future.failedFuture(ex));
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postWeatherObserved failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("postWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<WeatherObserved> postWeatherObservedFuture(SiteRequest siteRequest, Boolean entityId) {
		Promise<WeatherObserved> promise = Promise.promise();

		try {
			pgPool.withTransaction(sqlConnection -> {
				Promise<WeatherObserved> promise1 = Promise.promise();
				siteRequest.setSqlConnection(sqlConnection);
				varsWeatherObserved(siteRequest).onSuccess(a -> {
					createWeatherObserved(siteRequest).onSuccess(weatherObserved -> {
						sqlPOSTWeatherObserved(weatherObserved, entityId).onSuccess(b -> {
							persistWeatherObserved(weatherObserved, false).onSuccess(c -> {
								relateWeatherObserved(weatherObserved).onSuccess(d -> {
									indexWeatherObserved(weatherObserved).onSuccess(o2 -> {
										promise1.complete(weatherObserved);
									}).onFailure(ex -> {
										promise1.fail(ex);
									});
								}).onFailure(ex -> {
									promise1.fail(ex);
								});
							}).onFailure(ex -> {
								promise1.fail(ex);
							});
						}).onFailure(ex -> {
							promise1.fail(ex);
						});
					}).onFailure(ex -> {
						promise1.fail(ex);
					});
				}).onFailure(ex -> {
					promise1.fail(ex);
				});
				return promise1.future();
			}).onSuccess(a -> {
				siteRequest.setSqlConnection(null);
			}).onFailure(ex -> {
				siteRequest.setSqlConnection(null);
				promise.fail(ex);
			}).compose(weatherObserved -> {
				Promise<WeatherObserved> promise2 = Promise.promise();
				refreshWeatherObserved(weatherObserved).onSuccess(a -> {
					try {
						ApiRequest apiRequest = siteRequest.getApiRequest_();
						if(apiRequest != null) {
							apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
							weatherObserved.apiRequestWeatherObserved();
							eventBus.publish("websocketWeatherObserved", JsonObject.mapFrom(apiRequest).toString());
						}
						promise2.complete(weatherObserved);
					} catch(Exception ex) {
						LOG.error(String.format("postWeatherObservedFuture failed. "), ex);
						promise.fail(ex);
					}
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(weatherObserved -> {
				try {
					ApiRequest apiRequest = siteRequest.getApiRequest_();
					if(apiRequest != null) {
						apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
						weatherObserved.apiRequestWeatherObserved();
						eventBus.publish("websocketWeatherObserved", JsonObject.mapFrom(apiRequest).toString());
					}
					promise.complete(weatherObserved);
				} catch(Exception ex) {
					LOG.error(String.format("postWeatherObservedFuture failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("postWeatherObservedFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<WeatherObserved> sqlPOSTWeatherObserved(WeatherObserved o, Boolean entityId) {
		Promise<WeatherObserved> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE WeatherObserved SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			WeatherObserved o2 = new WeatherObserved();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			if(siteRequest.getSessionId() != null) {
				if(bParams.size() > 0) {
					bSql.append(", ");
				}
				bSql.append("sessionId=$" + num);
				num++;
				bParams.add(siteRequest.getSessionId());
			}
			if(siteRequest.getUserKey() != null) {
				if(bParams.size() > 0) {
					bSql.append(", ");
				}
				bSql.append("userKey=$" + num);
				num++;
				bParams.add(siteRequest.getUserKey());
			}

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					case WeatherObserved.VAR_name:
						o2.setName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_name + "=$" + num);
						num++;
						bParams.add(o2.sqlName());
						break;
					case WeatherObserved.VAR_description:
						o2.setDescription(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_description + "=$" + num);
						num++;
						bParams.add(o2.sqlDescription());
						break;
					case WeatherObserved.VAR_created:
						o2.setCreated(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_created + "=$" + num);
						num++;
						bParams.add(o2.sqlCreated());
						break;
					case WeatherObserved.VAR_address:
						o2.setAddress(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_address + "=$" + num);
						num++;
						bParams.add(o2.sqlAddress());
						break;
					case WeatherObserved.VAR_alternateName:
						o2.setAlternateName(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_alternateName + "=$" + num);
						num++;
						bParams.add(o2.sqlAlternateName());
						break;
					case WeatherObserved.VAR_archived:
						o2.setArchived(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_archived + "=$" + num);
						num++;
						bParams.add(o2.sqlArchived());
						break;
					case WeatherObserved.VAR_location:
						o2.setLocation(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_location + "=$" + num);
						num++;
						bParams.add(o2.sqlLocation());
						break;
					case WeatherObserved.VAR_sessionId:
						o2.setSessionId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_sessionId + "=$" + num);
						num++;
						bParams.add(o2.sqlSessionId());
						break;
					case WeatherObserved.VAR_areaServed:
						o2.setAreaServed(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_areaServed + "=$" + num);
						num++;
						bParams.add(o2.sqlAreaServed());
						break;
					case WeatherObserved.VAR_userKey:
						o2.setUserKey(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_userKey + "=$" + num);
						num++;
						bParams.add(o2.sqlUserKey());
						break;
					case WeatherObserved.VAR_dataProvider:
						o2.setDataProvider(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_dataProvider + "=$" + num);
						num++;
						bParams.add(o2.sqlDataProvider());
						break;
					case WeatherObserved.VAR_dateCreated:
						o2.setDateCreated(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_dateCreated + "=$" + num);
						num++;
						bParams.add(o2.sqlDateCreated());
						break;
					case WeatherObserved.VAR_dateModified:
						o2.setDateModified(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_dateModified + "=$" + num);
						num++;
						bParams.add(o2.sqlDateModified());
						break;
					case WeatherObserved.VAR_title:
						o2.setTitle(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_title + "=$" + num);
						num++;
						bParams.add(o2.sqlTitle());
						break;
					case WeatherObserved.VAR_dateObserved:
						o2.setDateObserved(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_dateObserved + "=$" + num);
						num++;
						bParams.add(o2.sqlDateObserved());
						break;
					case WeatherObserved.VAR_displayPage:
						o2.setDisplayPage(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_displayPage + "=$" + num);
						num++;
						bParams.add(o2.sqlDisplayPage());
						break;
					case WeatherObserved.VAR_owner:
						o2.setOwner(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_owner + "=$" + num);
						num++;
						bParams.add(o2.sqlOwner());
						break;
					case WeatherObserved.VAR_refDevice:
						o2.setRefDevice(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_refDevice + "=$" + num);
						num++;
						bParams.add(o2.sqlRefDevice());
						break;
					case WeatherObserved.VAR_refPointOfInterest:
						o2.setRefPointOfInterest(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_refPointOfInterest + "=$" + num);
						num++;
						bParams.add(o2.sqlRefPointOfInterest());
						break;
					case WeatherObserved.VAR_source:
						o2.setSource(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_source + "=$" + num);
						num++;
						bParams.add(o2.sqlSource());
						break;
					case WeatherObserved.VAR_seeAlso:
						o2.setSeeAlso(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_seeAlso + "=$" + num);
						num++;
						bParams.add(o2.sqlSeeAlso());
						break;
					case WeatherObserved.VAR_airQualityIndex:
						o2.setAirQualityIndex(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_airQualityIndex + "=$" + num);
						num++;
						bParams.add(o2.sqlAirQualityIndex());
						break;
					case WeatherObserved.VAR_airQualityIndexForecast:
						o2.setAirQualityIndexForecast(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_airQualityIndexForecast + "=$" + num);
						num++;
						bParams.add(o2.sqlAirQualityIndexForecast());
						break;
					case WeatherObserved.VAR_aqiMajorPollutant:
						o2.setAqiMajorPollutant(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_aqiMajorPollutant + "=$" + num);
						num++;
						bParams.add(o2.sqlAqiMajorPollutant());
						break;
					case WeatherObserved.VAR_aqiMajorPollutantForecast:
						o2.setAqiMajorPollutantForecast(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_aqiMajorPollutantForecast + "=$" + num);
						num++;
						bParams.add(o2.sqlAqiMajorPollutantForecast());
						break;
					case WeatherObserved.VAR_atmosphericPressure:
						o2.setAtmosphericPressure(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_atmosphericPressure + "=$" + num);
						num++;
						bParams.add(o2.sqlAtmosphericPressure());
						break;
					case WeatherObserved.VAR_pressureTendency:
						o2.setPressureTendency(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_pressureTendency + "=$" + num);
						num++;
						bParams.add(o2.sqlPressureTendency());
						break;
					case WeatherObserved.VAR_gustSpeed:
						o2.setGustSpeed(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_gustSpeed + "=$" + num);
						num++;
						bParams.add(o2.sqlGustSpeed());
						break;
					case WeatherObserved.VAR_windDirection:
						o2.setWindDirection(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_windDirection + "=$" + num);
						num++;
						bParams.add(o2.sqlWindDirection());
						break;
					case WeatherObserved.VAR_windSpeed:
						o2.setWindSpeed(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_windSpeed + "=$" + num);
						num++;
						bParams.add(o2.sqlWindSpeed());
						break;
					case WeatherObserved.VAR_precipitation:
						o2.setPrecipitation(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_precipitation + "=$" + num);
						num++;
						bParams.add(o2.sqlPrecipitation());
						break;
					case WeatherObserved.VAR_precipitationForecast:
						o2.setPrecipitationForecast(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_precipitationForecast + "=$" + num);
						num++;
						bParams.add(o2.sqlPrecipitationForecast());
						break;
					case WeatherObserved.VAR_relativeHumidity:
						o2.setRelativeHumidity(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_relativeHumidity + "=$" + num);
						num++;
						bParams.add(o2.sqlRelativeHumidity());
						break;
					case WeatherObserved.VAR_relativeHumidityForecast:
						o2.setRelativeHumidityForecast(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_relativeHumidityForecast + "=$" + num);
						num++;
						bParams.add(o2.sqlRelativeHumidityForecast());
						break;
					case WeatherObserved.VAR_snowHeight:
						o2.setSnowHeight(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_snowHeight + "=$" + num);
						num++;
						bParams.add(o2.sqlSnowHeight());
						break;
					case WeatherObserved.VAR_airTemperatureForecast:
						o2.setAirTemperatureForecast(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_airTemperatureForecast + "=$" + num);
						num++;
						bParams.add(o2.sqlAirTemperatureForecast());
						break;
					case WeatherObserved.VAR_airTemperatureTSA:
						o2.setAirTemperatureTSA(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_airTemperatureTSA + "=$" + num);
						num++;
						bParams.add(o2.sqlAirTemperatureTSA());
						break;
					case WeatherObserved.VAR_feelsLikeTemperature:
						o2.setFeelsLikeTemperature(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_feelsLikeTemperature + "=$" + num);
						num++;
						bParams.add(o2.sqlFeelsLikeTemperature());
						break;
					case WeatherObserved.VAR_temperature:
						o2.setTemperature(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_temperature + "=$" + num);
						num++;
						bParams.add(o2.sqlTemperature());
						break;
					case WeatherObserved.VAR_solarRadiation:
						o2.setSolarRadiation(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_solarRadiation + "=$" + num);
						num++;
						bParams.add(o2.sqlSolarRadiation());
						break;
					case WeatherObserved.VAR_streamGauge:
						o2.setStreamGauge(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_streamGauge + "=$" + num);
						num++;
						bParams.add(o2.sqlStreamGauge());
						break;
					case WeatherObserved.VAR_uVIndexMax:
						o2.setUVIndexMax(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_uVIndexMax + "=$" + num);
						num++;
						bParams.add(o2.sqlUVIndexMax());
						break;
					case WeatherObserved.VAR_visibility:
						o2.setVisibility(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_visibility + "=$" + num);
						num++;
						bParams.add(o2.sqlVisibility());
						break;
					case WeatherObserved.VAR_weatherType:
						o2.setWeatherType(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_weatherType + "=$" + num);
						num++;
						bParams.add(o2.sqlWeatherType());
						break;
					case WeatherObserved.VAR_dewPoint:
						o2.setDewPoint(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_dewPoint + "=$" + num);
						num++;
						bParams.add(o2.sqlDewPoint());
						break;
					case WeatherObserved.VAR_diffuseIrradiation:
						o2.setDiffuseIrradiation(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_diffuseIrradiation + "=$" + num);
						num++;
						bParams.add(o2.sqlDiffuseIrradiation());
						break;
					case WeatherObserved.VAR_directIrradiation:
						o2.setDirectIrradiation(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_directIrradiation + "=$" + num);
						num++;
						bParams.add(o2.sqlDirectIrradiation());
						break;
					case WeatherObserved.VAR_illuminance:
						o2.setIlluminance(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_illuminance + "=$" + num);
						num++;
						bParams.add(o2.sqlIlluminance());
						break;
					case WeatherObserved.VAR_ngsildTenant:
						o2.setNgsildTenant(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_ngsildTenant + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildTenant());
						break;
					case WeatherObserved.VAR_ngsildPath:
						o2.setNgsildPath(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_ngsildPath + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildPath());
						break;
					case WeatherObserved.VAR_ngsildData:
						o2.setNgsildData(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_ngsildData + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildData());
						break;
					case WeatherObserved.VAR_entityId:
						o2.setEntityId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_entityId + "=$" + num);
						num++;
						bParams.add(o2.sqlEntityId());
						break;
					case WeatherObserved.VAR_ngsildContext:
						o2.setNgsildContext(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(WeatherObserved.VAR_ngsildContext + "=$" + num);
						num++;
						bParams.add(o2.sqlNgsildContext());
						break;
					}
				}
			}
			bSql.append(" WHERE pk=$" + num);
			if(bParams.size() > 0) {
			bParams.add(pk);
			num++;
				futures2.add(0, Future.future(a -> {
					sqlConnection.preparedQuery(bSql.toString())
							.execute(Tuple.tuple(bParams)
							).onSuccess(b -> {
						a.handle(Future.succeededFuture());
					}).onFailure(ex -> {
						RuntimeException ex2 = new RuntimeException("value WeatherObserved failed", ex);
						LOG.error(String.format("relateWeatherObserved failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete(o2);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPOSTWeatherObserved failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPOSTWeatherObserved failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPOSTWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200POSTWeatherObserved(WeatherObserved o) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			if(json == null) {
				String entityId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("entityId");
						String m = String.format("%s %s not found", "weather observed device", entityId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200POSTWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// DELETE //

	@Override
	public void deleteWeatherObserved(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("deleteWeatherObserved started. "));
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.expect(ResponsePredicate.status(200))
					.sendForm(MultiMap.caseInsensitiveMultiMap()
							.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket")
							.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT))
							.add("response_mode", "permissions")
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "DELETE"))
			).onFailure(ex -> {
				String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(403, "FORBIDDEN",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "403")
								.put("errorMessage", msg)
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
					)
				));
			}).onSuccess(authorizationDecision -> {
				try {
					JsonArray scopes = authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					if(!scopes.contains("DELETE")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchWeatherObservedList(siteRequest, false, true, true).onSuccess(listWeatherObserved -> {
							try {
								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(listWeatherObserved.getRequest().getRows());
								apiRequest.setNumFound(listWeatherObserved.getResponse().getResponse().getNumFound());
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest);
								siteRequest.setApiRequest_(apiRequest);
								if(apiRequest.getNumFound() == 1L)
									apiRequest.setOriginal(listWeatherObserved.first());
								apiRequest.setPk(Optional.ofNullable(listWeatherObserved.first()).map(o2 -> o2.getPk()).orElse(null));
								eventBus.publish("websocketWeatherObserved", JsonObject.mapFrom(apiRequest).toString());

								listDELETEWeatherObserved(apiRequest, listWeatherObserved).onSuccess(e -> {
									response200DELETEWeatherObserved(siteRequest).onSuccess(response -> {
										LOG.debug(String.format("deleteWeatherObserved succeeded. "));
										eventHandler.handle(Future.succeededFuture(response));
									}).onFailure(ex -> {
										LOG.error(String.format("deleteWeatherObserved failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}).onFailure(ex -> {
									LOG.error(String.format("deleteWeatherObserved failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							} catch(Exception ex) {
								LOG.error(String.format("deleteWeatherObserved failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("deleteWeatherObserved failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("deleteWeatherObserved failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("deleteWeatherObserved failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("deleteWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listDELETEWeatherObserved(ApiRequest apiRequest, SearchList<WeatherObserved> listWeatherObserved) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listWeatherObserved.getSiteRequest_(SiteRequest.class);
		listWeatherObserved.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			JsonObject jsonObject = JsonObject.mapFrom(o);
			WeatherObserved o2 = jsonObject.mapTo(WeatherObserved.class);
			o2.setSiteRequest_(siteRequest2);
			futures.add(Future.future(promise1 -> {
				deleteWeatherObservedFuture(o).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listDELETEWeatherObserved failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listWeatherObserved.next().onSuccess(next -> {
				if(next) {
					listDELETEWeatherObserved(apiRequest, listWeatherObserved).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listDELETEWeatherObserved failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listDELETEWeatherObserved failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listDELETEWeatherObserved failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void deleteWeatherObservedFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			try {
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				searchWeatherObservedList(siteRequest, false, true, true).onSuccess(listWeatherObserved -> {
					try {
						WeatherObserved o = listWeatherObserved.first();
						if(o != null && listWeatherObserved.getResponse().getResponse().getNumFound() == 1) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1L);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
								siteRequest.getRequestVars().put( "refresh", "false" );
							}
							if(apiRequest.getNumFound() == 1L)
								apiRequest.setOriginal(o);
							apiRequest.setId(Optional.ofNullable(listWeatherObserved.first()).map(o2 -> o2.getEntityId()).orElse(null));
							apiRequest.setPk(Optional.ofNullable(listWeatherObserved.first()).map(o2 -> o2.getPk()).orElse(null));
							deleteWeatherObservedFuture(o).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("deleteWeatherObserved failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("deleteWeatherObserved failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("deleteWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("deleteWeatherObserved failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<WeatherObserved> deleteWeatherObservedFuture(WeatherObserved o) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<WeatherObserved> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<WeatherObserved> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsWeatherObserved(siteRequest).onSuccess(a -> {
					sqlDELETEWeatherObserved(o).onSuccess(weatherObserved -> {
						relateWeatherObserved(o).onSuccess(d -> {
							unindexWeatherObserved(o).onSuccess(o2 -> {
								if(apiRequest != null) {
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
										o2.apiRequestWeatherObserved();
										if(apiRequest.getVars().size() > 0)
											eventBus.publish("websocketWeatherObserved", JsonObject.mapFrom(apiRequest).toString());
									}
								}
								promise1.complete();
							}).onFailure(ex -> {
								promise1.fail(ex);
							});
						}).onFailure(ex -> {
							promise1.fail(ex);
						});
					}).onFailure(ex -> {
						promise1.fail(ex);
					});
				}).onFailure(ex -> {
					promise1.fail(ex);
				});
				return promise1.future();
			}).onSuccess(a -> {
				siteRequest.setSqlConnection(null);
			}).onFailure(ex -> {
				siteRequest.setSqlConnection(null);
				promise.fail(ex);
			}).compose(weatherObserved -> {
				Promise<WeatherObserved> promise2 = Promise.promise();
				refreshWeatherObserved(o).onSuccess(a -> {
					promise2.complete(o);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(weatherObserved -> {
				promise.complete(weatherObserved);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("deleteWeatherObservedFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlDELETEWeatherObserved(WeatherObserved o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("DELETE FROM WeatherObserved ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			WeatherObserved o2 = new WeatherObserved();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					}
				}
			}
			bSql.append(" WHERE pk=$" + num);
			bParams.add(pk);
			num++;
			futures2.add(0, Future.future(a -> {
				sqlConnection.preparedQuery(bSql.toString())
						.execute(Tuple.tuple(bParams)
						).onSuccess(b -> {
					a.handle(Future.succeededFuture());
				}).onFailure(ex -> {
					RuntimeException ex2 = new RuntimeException("value WeatherObserved failed", ex);
					LOG.error(String.format("unrelateWeatherObserved failed. "), ex2);
					a.handle(Future.failedFuture(ex2));
				});
			}));
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlDELETEWeatherObserved failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlDELETEWeatherObserved failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlDELETEWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200DELETEWeatherObserved(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String entityId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("entityId");
						String m = String.format("%s %s not found", "weather observed device", entityId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200DELETEWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PUTImport //

	@Override
	public void putimportWeatherObserved(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("putimportWeatherObserved started. "));
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.expect(ResponsePredicate.status(200))
					.sendForm(MultiMap.caseInsensitiveMultiMap()
							.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket")
							.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT))
							.add("response_mode", "permissions")
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "GET"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "POST"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "DELETE"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "PATCH"))
			).onFailure(ex -> {
				String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(403, "FORBIDDEN",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "403")
								.put("errorMessage", msg)
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
					)
				));
			}).onSuccess(authorizationDecision -> {
				try {
					JsonArray scopes = authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					if(!scopes.contains("PUT")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						ApiRequest apiRequest = new ApiRequest();
						JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
						apiRequest.setRows(Long.valueOf(jsonArray.size()));
						apiRequest.setNumFound(Long.valueOf(jsonArray.size()));
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest);
						siteRequest.setApiRequest_(apiRequest);
						eventBus.publish("websocketWeatherObserved", JsonObject.mapFrom(apiRequest).toString());
						varsWeatherObserved(siteRequest).onSuccess(d -> {
							listPUTImportWeatherObserved(apiRequest, siteRequest).onSuccess(e -> {
								response200PUTImportWeatherObserved(siteRequest).onSuccess(response -> {
									LOG.debug(String.format("putimportWeatherObserved succeeded. "));
									eventHandler.handle(Future.succeededFuture(response));
								}).onFailure(ex -> {
									LOG.error(String.format("putimportWeatherObserved failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							}).onFailure(ex -> {
								LOG.error(String.format("putimportWeatherObserved failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("putimportWeatherObserved failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("putimportWeatherObserved failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportWeatherObserved failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("putimportWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<Void> listPUTImportWeatherObserved(ApiRequest apiRequest, SiteRequest siteRequest) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				futures.add(Future.future(promise1 -> {
					JsonObject params = new JsonObject();
					params.put("body", obj);
					params.put("path", new JsonObject());
					params.put("cookie", new JsonObject());
					params.put("header", siteRequest.getServiceRequest().getParams().getJsonObject("header"));
					params.put("form", new JsonObject());
					JsonObject query = new JsonObject();
					Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
					Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
					if(softCommit == null && commitWithin == null)
						softCommit = true;
					if(softCommit != null)
						query.put("softCommit", softCommit);
					if(commitWithin != null)
						query.put("commitWithin", commitWithin);
					params.put("query", query);
					JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
					JsonObject json = new JsonObject().put("context", context);
					eventBus.request(WeatherObserved.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportWeatherObservedFuture")).onSuccess(a -> {
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPUTImportWeatherObserved failed. "), ex);
						promise1.fail(ex);
					});
				}));
			});
			CompositeFuture.all(futures).onSuccess(a -> {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
				promise.complete();
			}).onFailure(ex -> {
				LOG.error(String.format("listPUTImportWeatherObserved failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("listPUTImportWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public void putimportWeatherObservedFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			try {
				ApiRequest apiRequest = new ApiRequest();
				apiRequest.setRows(1L);
				apiRequest.setNumFound(1L);
				apiRequest.setNumPATCH(0L);
				apiRequest.initDeepApiRequest(siteRequest);
				siteRequest.setApiRequest_(apiRequest);
				String entityId = Optional.ofNullable(body.getString(WeatherObserved.VAR_entityId)).orElse(body.getString(WeatherObserved.VAR_solrId));
				if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
					siteRequest.getRequestVars().put( "refresh", "false" );
				}

				SearchList<WeatherObserved> searchList = new SearchList<WeatherObserved>();
				searchList.setStore(true);
				searchList.q("*:*");
				searchList.setC(WeatherObserved.class);
				searchList.fq("archived_docvalues_boolean:false");
				searchList.fq("entityId_docvalues_string:" + SearchTool.escapeQueryChars(entityId));
				searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
					try {
						if(searchList.size() >= 1) {
							WeatherObserved o = searchList.getList().stream().findFirst().orElse(null);
							WeatherObserved o2 = new WeatherObserved();
							o2.setSiteRequest_(siteRequest);
							JsonObject body2 = new JsonObject();
							for(String f : body.fieldNames()) {
								Object bodyVal = body.getValue(f);
								if(bodyVal instanceof JsonArray) {
									JsonArray bodyVals = (JsonArray)bodyVal;
									Object valsObj = o.obtainForClass(f);
									Collection<?> vals = valsObj instanceof JsonArray ? ((JsonArray)valsObj).getList() : (Collection<?>)valsObj;
									if(bodyVals.size() == vals.size()) {
										Boolean match = true;
										for(Object val : vals) {
											if(val != null) {
												if(!bodyVals.contains(val.toString())) {
													match = false;
													break;
												}
											} else {
												match = false;
												break;
											}
										}
										vals.clear();
										body2.put("set" + StringUtils.capitalize(f), bodyVal);
									} else {
										vals.clear();
										body2.put("set" + StringUtils.capitalize(f), bodyVal);
									}
								} else {
									o2.persistForClass(f, bodyVal);
									o2.relateForClass(f, bodyVal);
									if(!StringUtils.containsAny(f, "entityId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
										body2.put("set" + StringUtils.capitalize(f), bodyVal);
								}
							}
							for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
								if(!body.fieldNames().contains(f)) {
									if(!StringUtils.containsAny(f, "entityId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
										body2.putNull("set" + StringUtils.capitalize(f));
								}
							}
							if(searchList.size() == 1) {
								apiRequest.setOriginal(o);
								apiRequest.setId(o.getEntityId());
								apiRequest.setPk(o.getPk());
							}
							siteRequest.setJsonObject(body2);
							patchWeatherObservedFuture(o, true).onSuccess(b -> {
								LOG.debug("Import WeatherObserved {} succeeded, modified WeatherObserved. ", body.getValue(WeatherObserved.VAR_entityId));
								eventHandler.handle(Future.succeededFuture());
							}).onFailure(ex -> {
								LOG.error(String.format("putimportWeatherObservedFuture failed. "), ex);
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							postWeatherObservedFuture(siteRequest, true).onSuccess(b -> {
								LOG.debug("Import WeatherObserved {} succeeded, created new WeatherObserved. ", body.getValue(WeatherObserved.VAR_entityId));
								eventHandler.handle(Future.succeededFuture());
							}).onFailure(ex -> {
								LOG.error(String.format("putimportWeatherObservedFuture failed. "), ex);
								eventHandler.handle(Future.failedFuture(ex));
							});
						}
					} catch(Exception ex) {
						LOG.error(String.format("putimportWeatherObservedFuture failed. "), ex);
						eventHandler.handle(Future.failedFuture(ex));
					}
				}).onFailure(ex -> {
					LOG.error(String.format("putimportWeatherObservedFuture failed. "), ex);
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Exception ex) {
				LOG.error(String.format("putimportWeatherObservedFuture failed. "), ex);
				eventHandler.handle(Future.failedFuture(ex));
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportWeatherObserved failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("putimportWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200PUTImportWeatherObserved(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			if(json == null) {
				String entityId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("entityId");
						String m = String.format("%s %s not found", "weather observed device", entityId);
				promise.complete(new ServiceResponse(404
						, m
						, Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
			} else {
				promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
			}
		} catch(Exception ex) {
			LOG.error(String.format("response200PUTImportWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// SearchPage //

	@Override
	public void searchpageWeatherObserved(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.expect(ResponsePredicate.status(200))
					.sendForm(MultiMap.caseInsensitiveMultiMap()
							.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket")
							.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT))
							.add("response_mode", "permissions")
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "GET"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "POST"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "DELETE"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "PATCH"))
			).onFailure(ex -> {
				String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(403, "FORBIDDEN",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "403")
								.put("errorMessage", msg)
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
					)
				));
			}).onSuccess(authorizationDecision -> {
				try {
					JsonArray scopes = authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					if(!scopes.contains("GET")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchWeatherObservedList(siteRequest, false, true, false).onSuccess(listWeatherObserved -> {
							response200SearchPageWeatherObserved(listWeatherObserved).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchpageWeatherObserved succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchpageWeatherObserved failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchpageWeatherObserved failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchpageWeatherObserved failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchpageWeatherObserved failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("searchpageWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void searchpageWeatherObservedPageInit(WeatherObservedPage page, SearchList<WeatherObserved> listWeatherObserved) {
	}

	public String templateSearchPageWeatherObserved(ServiceRequest serviceRequest) {
		return "en-us/search/weather-observed/WeatherObservedSearchPage.htm";
	}
	public Future<ServiceResponse> response200SearchPageWeatherObserved(SearchList<WeatherObserved> listWeatherObserved) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listWeatherObserved.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateSearchPageWeatherObserved(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			WeatherObservedPage page = new WeatherObservedPage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listWeatherObserved.size() == 1)
				siteRequest.setRequestPk(listWeatherObserved.get(0).getPk());
			page.setSearchListWeatherObserved_(listWeatherObserved);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.promiseDeepWeatherObservedPage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ComputateConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200SearchPageWeatherObserved failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchPageWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchPageWeatherObserved(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
		if(pivots != null) {
			for(SolrResponse.Pivot pivotField : pivots) {
				String entityIndexed = pivotField.getField();
				String entityVar = StringUtils.substringBefore(entityIndexed, "_docvalues_");
				JsonObject pivotJson = new JsonObject();
				pivotArray.add(pivotJson);
				pivotJson.put("field", entityVar);
				pivotJson.put("value", pivotField.getValue());
				pivotJson.put("count", pivotField.getCount());
				Collection<SolrResponse.PivotRange> pivotRanges = pivotField.getRanges().values();
				List<SolrResponse.Pivot> pivotFields2 = pivotField.getPivotList();
				if(pivotRanges != null) {
					JsonObject rangeJson = new JsonObject();
					pivotJson.put("ranges", rangeJson);
					for(SolrResponse.PivotRange rangeFacet : pivotRanges) {
						JsonObject rangeFacetJson = new JsonObject();
						String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), "_docvalues_");
						rangeJson.put(rangeFacetVar, rangeFacetJson);
						JsonObject rangeFacetCountsObject = new JsonObject();
						rangeFacetJson.put("counts", rangeFacetCountsObject);
						rangeFacet.getCounts().forEach((value, count) -> {
							rangeFacetCountsObject.put(value, count);
						});
					}
				}
				if(pivotFields2 != null) {
					JsonArray pivotArray2 = new JsonArray();
					pivotJson.put("pivot", pivotArray2);
					responsePivotSearchPageWeatherObserved(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// EditPage //

	@Override
	public void editpageWeatherObserved(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			webClient.post(
					config.getInteger(ComputateConfigKeys.AUTH_PORT)
					, config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
					, config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
					)
					.ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
					.putHeader("Authorization", String.format("Bearer %s", siteRequest.getUser().principal().getString("access_token")))
					.expect(ResponsePredicate.status(200))
					.sendForm(MultiMap.caseInsensitiveMultiMap()
							.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket")
							.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT))
							.add("response_mode", "permissions")
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "GET"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "POST"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "DELETE"))
							.add("permission", String.format("%s#%s", WeatherObserved.CLASS_SIMPLE_NAME, "PATCH"))
			).onFailure(ex -> {
				String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(403, "FORBIDDEN",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "403")
								.put("errorMessage", msg)
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
					)
				));
			}).onSuccess(authorizationDecision -> {
				try {
					JsonArray scopes = authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
					if(!scopes.contains("GET")) {
						String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
						eventHandler.handle(Future.succeededFuture(
							new ServiceResponse(403, "FORBIDDEN",
								Buffer.buffer().appendString(
									new JsonObject()
										.put("errorCode", "403")
										.put("errorMessage", msg)
										.encodePrettily()
									), MultiMap.caseInsensitiveMultiMap()
							)
						));
					} else {
						siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
						searchWeatherObservedList(siteRequest, false, true, false).onSuccess(listWeatherObserved -> {
							response200EditPageWeatherObserved(listWeatherObserved).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("editpageWeatherObserved succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("editpageWeatherObserved failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("editpageWeatherObserved failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("editpageWeatherObserved failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("editpageWeatherObserved failed. ", ex2));
					error(null, eventHandler, ex2);
				}
			} else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
				eventHandler.handle(Future.succeededFuture(
					new ServiceResponse(401, "UNAUTHORIZED",
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "SSO Resource Permission check returned DENY")
								.encodePrettily()
							), MultiMap.caseInsensitiveMultiMap()
							)
					));
			} else {
				LOG.error(String.format("editpageWeatherObserved failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public void editpageWeatherObservedPageInit(WeatherObservedPage page, SearchList<WeatherObserved> listWeatherObserved) {
	}

	public String templateEditPageWeatherObserved(ServiceRequest serviceRequest) {
		return "en-us/edit/weather-observed/WeatherObservedEditPage.htm";
	}
	public Future<ServiceResponse> response200EditPageWeatherObserved(SearchList<WeatherObserved> listWeatherObserved) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listWeatherObserved.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateEditPageWeatherObserved(siteRequest.getServiceRequest());
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			WeatherObservedPage page = new WeatherObservedPage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listWeatherObserved.size() == 1)
				siteRequest.setRequestPk(listWeatherObserved.get(0).getPk());
			page.setSearchListWeatherObserved_(listWeatherObserved);
			page.setSiteRequest_(siteRequest);
			page.setServiceRequest(siteRequest.getServiceRequest());
			page.promiseDeepWeatherObservedPage(siteRequest).onSuccess(a -> {
				try {
					JsonObject ctx = ComputateConfigKeys.getPageContext(config);
					ctx.mergeIn(JsonObject.mapFrom(page));
					String renderedTemplate = jinjava.render(template, ctx.getMap());
					Buffer buffer = Buffer.buffer(renderedTemplate);
					promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
				} catch(Exception ex) {
					LOG.error(String.format("response200EditPageWeatherObserved failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200EditPageWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotEditPageWeatherObserved(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
		if(pivots != null) {
			for(SolrResponse.Pivot pivotField : pivots) {
				String entityIndexed = pivotField.getField();
				String entityVar = StringUtils.substringBefore(entityIndexed, "_docvalues_");
				JsonObject pivotJson = new JsonObject();
				pivotArray.add(pivotJson);
				pivotJson.put("field", entityVar);
				pivotJson.put("value", pivotField.getValue());
				pivotJson.put("count", pivotField.getCount());
				Collection<SolrResponse.PivotRange> pivotRanges = pivotField.getRanges().values();
				List<SolrResponse.Pivot> pivotFields2 = pivotField.getPivotList();
				if(pivotRanges != null) {
					JsonObject rangeJson = new JsonObject();
					pivotJson.put("ranges", rangeJson);
					for(SolrResponse.PivotRange rangeFacet : pivotRanges) {
						JsonObject rangeFacetJson = new JsonObject();
						String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), "_docvalues_");
						rangeJson.put(rangeFacetVar, rangeFacetJson);
						JsonObject rangeFacetCountsObject = new JsonObject();
						rangeFacetJson.put("counts", rangeFacetCountsObject);
						rangeFacet.getCounts().forEach((value, count) -> {
							rangeFacetCountsObject.put(value, count);
						});
					}
				}
				if(pivotFields2 != null) {
					JsonArray pivotArray2 = new JsonArray();
					pivotJson.put("pivot", pivotArray2);
					responsePivotEditPageWeatherObserved(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// General //

	public Future<WeatherObserved> createWeatherObserved(SiteRequest siteRequest) {
		Promise<WeatherObserved> promise = Promise.promise();
		try {
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long userKey = siteRequest.getUserKey();
			ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

			sqlConnection.preparedQuery("INSERT INTO WeatherObserved(created, userKey) VALUES($1, $2) RETURNING pk")
					.collecting(Collectors.toList())
					.execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
				Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				WeatherObserved o = new WeatherObserved();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				promise.complete(o);
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error("createWeatherObserved failed. ", ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("createWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public void searchWeatherObservedQ(SearchList<WeatherObserved> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
		}
	}

	public String searchWeatherObservedFq(SearchList<WeatherObserved> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		if(StringUtils.startsWith(valueIndexed, "[")) {
			String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
			if(fqs.length != 2)
				throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
			String fq1 = fqs[0].equals("*") ? fqs[0] : WeatherObserved.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
			String fq2 = fqs[1].equals("*") ? fqs[1] : WeatherObserved.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
			 return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
		} else {
			return varIndexed + ":" + SearchTool.escapeQueryChars(WeatherObserved.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
		}
	}

	public void searchWeatherObservedSort(SearchList<WeatherObserved> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.sort(varIndexed, valueIndexed);
	}

	public void searchWeatherObservedRows(SearchList<WeatherObserved> searchList, Long valueRows) {
			searchList.rows(valueRows != null ? valueRows : 10L);
	}

	public void searchWeatherObservedStart(SearchList<WeatherObserved> searchList, Long valueStart) {
		searchList.start(valueStart);
	}

	public void searchWeatherObservedVar(SearchList<WeatherObserved> searchList, String var, String value) {
		searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
	}

	public void searchWeatherObservedUri(SearchList<WeatherObserved> searchList) {
	}

	public Future<ServiceResponse> varsWeatherObserved(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			ServiceRequest serviceRequest = siteRequest.getServiceRequest();

			serviceRequest.getParams().getJsonObject("query").stream().filter(paramRequest -> "var".equals(paramRequest.getKey()) && paramRequest.getValue() != null).findFirst().ifPresent(paramRequest -> {
				String entityVar = null;
				String valueIndexed = null;
				Object paramValuesObject = paramRequest.getValue();
				JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);

				try {
					for(Object paramObject : paramObjects) {
						entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
						valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
						siteRequest.getRequestVars().put(entityVar, valueIndexed);
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchWeatherObserved failed. "), ex);
					promise.fail(ex);
				}
			});
			promise.complete();
		} catch(Exception ex) {
			LOG.error(String.format("searchWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<SearchList<WeatherObserved>> searchWeatherObservedList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify) {
		Promise<SearchList<WeatherObserved>> promise = Promise.promise();
		try {
			ServiceRequest serviceRequest = siteRequest.getServiceRequest();
			String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<WeatherObserved> searchList = new SearchList<WeatherObserved>();
			String facetRange = null;
			Date facetRangeStart = null;
			Date facetRangeEnd = null;
			String facetRangeGap = null;
			String statsField = null;
			String statsFieldIndexed = null;
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.q("*:*");
			searchList.setC(WeatherObserved.class);
			searchList.setSiteRequest_(siteRequest);
			searchList.facetMinCount(1);
			if(entityList != null) {
				for(String v : entityList) {
					searchList.fl(WeatherObserved.varIndexedWeatherObserved(v));
				}
			}

			String entityId = serviceRequest.getParams().getJsonObject("path").getString("entityId");
			if(entityId != null && NumberUtils.isCreatable(entityId)) {
				searchList.fq("(_docvalues_string:" + SearchTool.escapeQueryChars(entityId) + " OR entityId_docvalues_string:" + SearchTool.escapeQueryChars(entityId) + ")");
			} else if(entityId != null) {
				searchList.fq("entityId_docvalues_string:" + SearchTool.escapeQueryChars(entityId));
			}

			for(String paramName : serviceRequest.getParams().getJsonObject("query").fieldNames()) {
				Object paramValuesObject = serviceRequest.getParams().getJsonObject("query").getValue(paramName);
				String entityVar = null;
				String valueIndexed = null;
				String varIndexed = null;
				String valueSort = null;
				Long valueStart = null;
				Long valueRows = null;
				String valueCursorMark = null;
				JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);

				try {
					if(paramValuesObject != null && "facet.pivot".equals(paramName)) {
						Matcher mFacetPivot = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher(StringUtils.join(paramObjects.getList().toArray(), ","));
						boolean foundFacetPivot = mFacetPivot.find();
						if(foundFacetPivot) {
							String solrLocalParams = mFacetPivot.group(1);
							String[] entityVars = mFacetPivot.group(2).trim().split(",");
							String[] varsIndexed = new String[entityVars.length];
							for(Integer i = 0; i < entityVars.length; i++) {
								entityVar = entityVars[i];
								varsIndexed[i] = WeatherObserved.varIndexedWeatherObserved(entityVar);
							}
							searchList.facetPivot((solrLocalParams == null ? "" : solrLocalParams) + StringUtils.join(varsIndexed, ","));
						}
					} else if(paramValuesObject != null) {
						for(Object paramObject : paramObjects) {
							if(paramName.equals("q")) {
								Matcher mQ = Pattern.compile("(\\w+):(.+?(?=(\\)|\\s+OR\\s+|\\s+AND\\s+|\\^|$)))").matcher((String)paramObject);
								boolean foundQ = mQ.find();
								if(foundQ) {
									StringBuffer sb = new StringBuffer();
									while(foundQ) {
										entityVar = mQ.group(1).trim();
										valueIndexed = mQ.group(2).trim();
										varIndexed = WeatherObserved.varIndexedWeatherObserved(entityVar);
										String entityQ = searchWeatherObservedFq(searchList, entityVar, valueIndexed, varIndexed);
										mQ.appendReplacement(sb, entityQ);
										foundQ = mQ.find();
									}
									mQ.appendTail(sb);
									searchList.q(sb.toString());
								}
							} else if(paramName.equals("fq")) {
								Matcher mFq = Pattern.compile("(\\w+):(.+?(?=(\\)|\\s+OR\\s+|\\s+AND\\s+|$)))").matcher((String)paramObject);
								boolean foundFq = mFq.find();
								if(foundFq) {
									StringBuffer sb = new StringBuffer();
									while(foundFq) {
										entityVar = mFq.group(1).trim();
										valueIndexed = mFq.group(2).trim();
										varIndexed = WeatherObserved.varIndexedWeatherObserved(entityVar);
										String entityFq = searchWeatherObservedFq(searchList, entityVar, valueIndexed, varIndexed);
										mFq.appendReplacement(sb, entityFq);
										foundFq = mFq.find();
									}
									mFq.appendTail(sb);
									searchList.fq(sb.toString());
								}
							} else if(paramName.equals("sort")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = WeatherObserved.varIndexedWeatherObserved(entityVar);
								searchWeatherObservedSort(searchList, entityVar, valueIndexed, varIndexed);
							} else if(paramName.equals("start")) {
								valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchWeatherObservedStart(searchList, valueStart);
							} else if(paramName.equals("rows")) {
								valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchWeatherObservedRows(searchList, valueRows);
							} else if(paramName.equals("stats")) {
								searchList.stats((Boolean)paramObject);
							} else if(paramName.equals("stats.field")) {
								Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
								boolean foundStats = mStats.find();
								if(foundStats) {
									String solrLocalParams = mStats.group(1);
									entityVar = mStats.group(2).trim();
									varIndexed = WeatherObserved.varIndexedWeatherObserved(entityVar);
									searchList.statsField((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
									statsField = entityVar;
									statsFieldIndexed = varIndexed;
								}
							} else if(paramName.equals("facet")) {
								searchList.facet((Boolean)paramObject);
							} else if(paramName.equals("facet.range.start")) {
								String startMathStr = (String)paramObject;
								Date start = SearchTool.parseMath(startMathStr);
								searchList.facetRangeStart(start.toInstant().toString());
								facetRangeStart = start;
							} else if(paramName.equals("facet.range.end")) {
								String endMathStr = (String)paramObject;
								Date end = SearchTool.parseMath(endMathStr);
								searchList.facetRangeEnd(end.toInstant().toString());
								facetRangeEnd = end;
							} else if(paramName.equals("facet.range.gap")) {
								String gap = (String)paramObject;
								searchList.facetRangeGap(gap);
								facetRangeGap = gap;
							} else if(paramName.equals("facet.range")) {
								Matcher mFacetRange = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
								boolean foundFacetRange = mFacetRange.find();
								if(foundFacetRange) {
									String solrLocalParams = mFacetRange.group(1);
									entityVar = mFacetRange.group(2).trim();
									varIndexed = WeatherObserved.varIndexedWeatherObserved(entityVar);
									searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
									facetRange = entityVar;
								}
							} else if(paramName.equals("facet.field")) {
								entityVar = (String)paramObject;
								varIndexed = WeatherObserved.varIndexedWeatherObserved(entityVar);
								if(varIndexed != null)
									searchList.facetField(varIndexed);
							} else if(paramName.equals("var")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								searchWeatherObservedVar(searchList, entityVar, valueIndexed);
							} else if(paramName.equals("cursorMark")) {
								valueCursorMark = (String)paramObject;
								searchList.cursorMark((String)paramObject);
							}
						}
						searchWeatherObservedUri(searchList);
					}
				} catch(Exception e) {
					ExceptionUtils.rethrow(e);
				}
			}
			if("*:*".equals(searchList.getQuery()) && searchList.getSorts().size() == 0) {
				searchList.sort("created_docvalues_date", "desc");
			}
			String facetRange2 = facetRange;
			Date facetRangeStart2 = facetRangeStart;
			Date facetRangeEnd2 = facetRangeEnd;
			String facetRangeGap2 = facetRangeGap;
			String statsField2 = statsField;
			String statsFieldIndexed2 = statsFieldIndexed;
			searchWeatherObserved2(siteRequest, populate, store, modify, searchList);
			searchList.promiseDeepForClass(siteRequest).onSuccess(searchList2 -> {
				if(facetRange2 != null && statsField2 != null && facetRange2.equals(statsField2)) {
					StatsField stats = searchList.getResponse().getStats().getStatsFields().get(statsFieldIndexed2);
					Instant min = Optional.ofNullable(stats.getMin()).map(val -> Instant.parse(val.toString())).orElse(Instant.now());
					Instant max = Optional.ofNullable(stats.getMax()).map(val -> Instant.parse(val.toString())).orElse(Instant.now());
					if(min.equals(max)) {
						min = min.minus(1, ChronoUnit.DAYS);
						max = max.plus(2, ChronoUnit.DAYS);
					}
					Duration duration = Duration.between(min, max);
					String gap = "DAY";
					if(duration.toDays() >= 365)
						gap = "YEAR";
					else if(duration.toDays() >= 28)
						gap = "MONTH";
					else if(duration.toDays() >= 1)
						gap = "DAY";
					else if(duration.toHours() >= 1)
						gap = "HOUR";
					else if(duration.toMinutes() >= 1)
						gap = "MINUTE";
					else if(duration.toMillis() >= 1000)
						gap = "SECOND";
					else if(duration.toMillis() >= 1)
						gap = "MILLI";

					if(facetRangeStart2 == null)
						searchList.facetRangeStart(min.toString());
					if(facetRangeEnd2 == null)
						searchList.facetRangeEnd(max.toString());
					if(facetRangeGap2 == null)
						searchList.facetRangeGap(String.format("+1%s", gap));
					searchList.query().onSuccess(b -> {
						promise.complete(searchList);
					}).onFailure(ex -> {
						LOG.error(String.format("searchWeatherObserved failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete(searchList);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("searchWeatherObserved failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("searchWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void searchWeatherObserved2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<WeatherObserved> searchList) {
	}

	public Future<Void> persistWeatherObserved(WeatherObserved o, Boolean patch) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			sqlConnection.preparedQuery("SELECT * FROM WeatherObserved WHERE pk=$1")
					.collecting(Collectors.toList())
					.execute(Tuple.of(pk)
					).onSuccess(result -> {
				try {
					for(Row definition : result.value()) {
						for(Integer i = 0; i < definition.size(); i++) {
							String columnName = definition.getColumnName(i);
							Object columnValue = definition.getValue(i);
							if(!"pk".equals(columnName)) {
								try {
									o.persistForClass(columnName, columnValue);
								} catch(Exception e) {
									LOG.error(String.format("persistWeatherObserved failed. "), e);
								}
							}
						}
					}
					o.promiseDeepForClass(siteRequest).onSuccess(a -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("persistWeatherObserved failed. "), ex);
						promise.fail(ex);
					});
				} catch(Exception ex) {
					LOG.error(String.format("persistWeatherObserved failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error(String.format("persistWeatherObserved failed. "), ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("persistWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> relateWeatherObserved(WeatherObserved o) {
		Promise<Void> promise = Promise.promise();
			promise.complete();
		return promise.future();
	}

	public String searchVar(String varIndexed) {
		return WeatherObserved.searchVarWeatherObserved(varIndexed);
	}

	@Override
	public String getClassApiAddress() {
		return WeatherObserved.CLASS_API_ADDRESS_WeatherObserved;
	}

	public Future<WeatherObserved> indexWeatherObserved(WeatherObserved o) {
		Promise<WeatherObserved> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			JsonObject json = new JsonObject();
			JsonObject add = new JsonObject();
			json.put("add", add);
			JsonObject doc = new JsonObject();
			add.put("doc", doc);
			o.indexWeatherObserved(doc);
			String solrUsername = siteRequest.getConfig().getString(ConfigKeys.SOLR_USERNAME);
			String solrPassword = siteRequest.getConfig().getString(ConfigKeys.SOLR_PASSWORD);
			String solrHostName = siteRequest.getConfig().getString(ConfigKeys.SOLR_HOST_NAME);
			Integer solrPort = siteRequest.getConfig().getInteger(ConfigKeys.SOLR_PORT);
			String solrCollection = siteRequest.getConfig().getString(ConfigKeys.SOLR_COLLECTION);
			Boolean solrSsl = siteRequest.getConfig().getBoolean(ConfigKeys.SOLR_SSL);
			Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
			Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
				if(softCommit == null && commitWithin == null)
					softCommit = true;
				else if(softCommit == null)
					softCommit = false;
			String solrRequestUri = String.format("/solr/%s/update%s%s%s", solrCollection, "?overwrite=true&wt=json", softCommit ? "&softCommit=true" : "", commitWithin != null ? ("&commitWithin=" + commitWithin) : "");
			webClient.post(solrPort, solrHostName, solrRequestUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).putHeader("Content-Type", "application/json").expect(ResponsePredicate.SC_OK).sendBuffer(json.toBuffer()).onSuccess(b -> {
				promise.complete(o);
			}).onFailure(ex -> {
				LOG.error(String.format("indexWeatherObserved failed. "), new RuntimeException(ex));
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("indexWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<WeatherObserved> unindexWeatherObserved(WeatherObserved o) {
		Promise<WeatherObserved> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			o.promiseDeepForClass(siteRequest).onSuccess(a -> {
				JsonObject json = new JsonObject();
				JsonObject delete = new JsonObject();
				json.put("delete", delete);
				String query = String.format("filter(entityId_docvalues_string:%s)", o.obtainForClass("entityId"));
				delete.put("query", query);
				String solrUsername = siteRequest.getConfig().getString(ConfigKeys.SOLR_USERNAME);
				String solrPassword = siteRequest.getConfig().getString(ConfigKeys.SOLR_PASSWORD);
				String solrHostName = siteRequest.getConfig().getString(ConfigKeys.SOLR_HOST_NAME);
				Integer solrPort = siteRequest.getConfig().getInteger(ConfigKeys.SOLR_PORT);
				String solrCollection = siteRequest.getConfig().getString(ConfigKeys.SOLR_COLLECTION);
				Boolean solrSsl = siteRequest.getConfig().getBoolean(ConfigKeys.SOLR_SSL);
				Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
				Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
					if(softCommit == null && commitWithin == null)
						softCommit = true;
					else if(softCommit == null)
						softCommit = false;
				String solrRequestUri = String.format("/solr/%s/update%s%s%s", solrCollection, "?overwrite=true&wt=json", softCommit ? "&softCommit=true" : "", commitWithin != null ? ("&commitWithin=" + commitWithin) : "");
				webClient.post(solrPort, solrHostName, solrRequestUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).putHeader("Content-Type", "application/json").expect(ResponsePredicate.SC_OK).sendBuffer(json.toBuffer()).onSuccess(b -> {
					promise.complete(o);
				}).onFailure(ex -> {
					LOG.error(String.format("unindexWeatherObserved failed. "), new RuntimeException(ex));
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("unindexWeatherObserved failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("unindexWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> refreshWeatherObserved(WeatherObserved o) {
		Promise<Void> promise = Promise.promise();
		SiteRequest siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Boolean refresh = !"false".equals(siteRequest.getRequestVars().get("refresh"));
			if(refresh && !Optional.ofNullable(siteRequest.getJsonObject()).map(JsonObject::isEmpty).orElse(true)) {
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < pks.size(); i++) {
					Long pk2 = pks.get(i);
					String classSimpleName2 = classes.get(i);
				}

				CompositeFuture.all(futures).onSuccess(b -> {
					JsonObject params = new JsonObject();
					params.put("body", new JsonObject());
					params.put("cookie", new JsonObject());
					params.put("header", siteRequest.getServiceRequest().getParams().getJsonObject("header"));
					params.put("form", new JsonObject());
					params.put("path", new JsonObject());
					JsonObject query = new JsonObject();
					Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
					Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
					if(softCommit == null && commitWithin == null)
						softCommit = true;
					if(softCommit != null)
						query.put("softCommit", softCommit);
					if(commitWithin != null)
						query.put("commitWithin", commitWithin);
					query.put("q", "*:*").put("fq", new JsonArray().add("pk:" + o.getPk())).put("var", new JsonArray().add("refresh:false"));
					params.put("query", query);
					JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
					JsonObject json = new JsonObject().put("context", context);
					eventBus.request(WeatherObserved.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchWeatherObservedFuture")).onSuccess(c -> {
						JsonObject responseMessage = (JsonObject)c.body();
						Integer statusCode = responseMessage.getInteger("statusCode");
						if(statusCode.equals(200))
							promise.complete();
						else
							promise.fail(new RuntimeException(responseMessage.getString("statusMessage")));
					}).onFailure(ex -> {
						LOG.error("Refresh relations failed. ", ex);
						promise.fail(ex);
					});
				}).onFailure(ex -> {
					LOG.error("Refresh relations failed. ", ex);
					promise.fail(ex);
				});
			} else {
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error(String.format("refreshWeatherObserved failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public Future<JsonObject> generatePageBody(ComputateSiteRequest siteRequest, Map<String, Object> ctx, String resourceUri, String templateUri, String classSimpleName) {
		Promise<JsonObject> promise = Promise.promise();
		try {
			Map<String, Object> result = (Map<String, Object>)ctx.get("result");
			SiteRequest siteRequest2 = (SiteRequest)siteRequest;
			String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);
			WeatherObserved page = new WeatherObserved();
			page.setSiteRequest_((SiteRequest)siteRequest);

			page.persistForClass(WeatherObserved.VAR_name, WeatherObserved.staticSetName(siteRequest2, (String)result.get(WeatherObserved.VAR_name)));
			page.persistForClass(WeatherObserved.VAR_description, WeatherObserved.staticSetDescription(siteRequest2, (String)result.get(WeatherObserved.VAR_description)));
			page.persistForClass(WeatherObserved.VAR_created, WeatherObserved.staticSetCreated(siteRequest2, (String)result.get(WeatherObserved.VAR_created)));
			page.persistForClass(WeatherObserved.VAR_address, WeatherObserved.staticSetAddress(siteRequest2, (String)result.get(WeatherObserved.VAR_address)));
			page.persistForClass(WeatherObserved.VAR_alternateName, WeatherObserved.staticSetAlternateName(siteRequest2, (String)result.get(WeatherObserved.VAR_alternateName)));
			page.persistForClass(WeatherObserved.VAR_archived, WeatherObserved.staticSetArchived(siteRequest2, (String)result.get(WeatherObserved.VAR_archived)));
			page.persistForClass(WeatherObserved.VAR_location, WeatherObserved.staticSetLocation(siteRequest2, (String)result.get(WeatherObserved.VAR_location)));
			page.persistForClass(WeatherObserved.VAR_sessionId, WeatherObserved.staticSetSessionId(siteRequest2, (String)result.get(WeatherObserved.VAR_sessionId)));
			page.persistForClass(WeatherObserved.VAR_areaServed, WeatherObserved.staticSetAreaServed(siteRequest2, (String)result.get(WeatherObserved.VAR_areaServed)));
			page.persistForClass(WeatherObserved.VAR_userKey, WeatherObserved.staticSetUserKey(siteRequest2, (String)result.get(WeatherObserved.VAR_userKey)));
			page.persistForClass(WeatherObserved.VAR_dataProvider, WeatherObserved.staticSetDataProvider(siteRequest2, (String)result.get(WeatherObserved.VAR_dataProvider)));
			page.persistForClass(WeatherObserved.VAR_dateCreated, WeatherObserved.staticSetDateCreated(siteRequest2, (String)result.get(WeatherObserved.VAR_dateCreated)));
			page.persistForClass(WeatherObserved.VAR_dateModified, WeatherObserved.staticSetDateModified(siteRequest2, (String)result.get(WeatherObserved.VAR_dateModified)));
			page.persistForClass(WeatherObserved.VAR_title, WeatherObserved.staticSetTitle(siteRequest2, (String)result.get(WeatherObserved.VAR_title)));
			page.persistForClass(WeatherObserved.VAR_dateObserved, WeatherObserved.staticSetDateObserved(siteRequest2, (String)result.get(WeatherObserved.VAR_dateObserved)));
			page.persistForClass(WeatherObserved.VAR_displayPage, WeatherObserved.staticSetDisplayPage(siteRequest2, (String)result.get(WeatherObserved.VAR_displayPage)));
			page.persistForClass(WeatherObserved.VAR_owner, WeatherObserved.staticSetOwner(siteRequest2, (String)result.get(WeatherObserved.VAR_owner)));
			page.persistForClass(WeatherObserved.VAR_refDevice, WeatherObserved.staticSetRefDevice(siteRequest2, (String)result.get(WeatherObserved.VAR_refDevice)));
			page.persistForClass(WeatherObserved.VAR_refPointOfInterest, WeatherObserved.staticSetRefPointOfInterest(siteRequest2, (String)result.get(WeatherObserved.VAR_refPointOfInterest)));
			page.persistForClass(WeatherObserved.VAR_source, WeatherObserved.staticSetSource(siteRequest2, (String)result.get(WeatherObserved.VAR_source)));
			page.persistForClass(WeatherObserved.VAR_seeAlso, WeatherObserved.staticSetSeeAlso(siteRequest2, (String)result.get(WeatherObserved.VAR_seeAlso)));
			page.persistForClass(WeatherObserved.VAR_airQualityIndex, WeatherObserved.staticSetAirQualityIndex(siteRequest2, (String)result.get(WeatherObserved.VAR_airQualityIndex)));
			page.persistForClass(WeatherObserved.VAR_airQualityIndexForecast, WeatherObserved.staticSetAirQualityIndexForecast(siteRequest2, (String)result.get(WeatherObserved.VAR_airQualityIndexForecast)));
			page.persistForClass(WeatherObserved.VAR_aqiMajorPollutant, WeatherObserved.staticSetAqiMajorPollutant(siteRequest2, (String)result.get(WeatherObserved.VAR_aqiMajorPollutant)));
			page.persistForClass(WeatherObserved.VAR_aqiMajorPollutantForecast, WeatherObserved.staticSetAqiMajorPollutantForecast(siteRequest2, (String)result.get(WeatherObserved.VAR_aqiMajorPollutantForecast)));
			page.persistForClass(WeatherObserved.VAR_atmosphericPressure, WeatherObserved.staticSetAtmosphericPressure(siteRequest2, (String)result.get(WeatherObserved.VAR_atmosphericPressure)));
			page.persistForClass(WeatherObserved.VAR_pressureTendency, WeatherObserved.staticSetPressureTendency(siteRequest2, (String)result.get(WeatherObserved.VAR_pressureTendency)));
			page.persistForClass(WeatherObserved.VAR_gustSpeed, WeatherObserved.staticSetGustSpeed(siteRequest2, (String)result.get(WeatherObserved.VAR_gustSpeed)));
			page.persistForClass(WeatherObserved.VAR_windDirection, WeatherObserved.staticSetWindDirection(siteRequest2, (String)result.get(WeatherObserved.VAR_windDirection)));
			page.persistForClass(WeatherObserved.VAR_windSpeed, WeatherObserved.staticSetWindSpeed(siteRequest2, (String)result.get(WeatherObserved.VAR_windSpeed)));
			page.persistForClass(WeatherObserved.VAR_precipitation, WeatherObserved.staticSetPrecipitation(siteRequest2, (String)result.get(WeatherObserved.VAR_precipitation)));
			page.persistForClass(WeatherObserved.VAR_precipitationForecast, WeatherObserved.staticSetPrecipitationForecast(siteRequest2, (String)result.get(WeatherObserved.VAR_precipitationForecast)));
			page.persistForClass(WeatherObserved.VAR_relativeHumidity, WeatherObserved.staticSetRelativeHumidity(siteRequest2, (String)result.get(WeatherObserved.VAR_relativeHumidity)));
			page.persistForClass(WeatherObserved.VAR_relativeHumidityForecast, WeatherObserved.staticSetRelativeHumidityForecast(siteRequest2, (String)result.get(WeatherObserved.VAR_relativeHumidityForecast)));
			page.persistForClass(WeatherObserved.VAR_snowHeight, WeatherObserved.staticSetSnowHeight(siteRequest2, (String)result.get(WeatherObserved.VAR_snowHeight)));
			page.persistForClass(WeatherObserved.VAR_airTemperatureForecast, WeatherObserved.staticSetAirTemperatureForecast(siteRequest2, (String)result.get(WeatherObserved.VAR_airTemperatureForecast)));
			page.persistForClass(WeatherObserved.VAR_airTemperatureTSA, WeatherObserved.staticSetAirTemperatureTSA(siteRequest2, (String)result.get(WeatherObserved.VAR_airTemperatureTSA)));
			page.persistForClass(WeatherObserved.VAR_feelsLikeTemperature, WeatherObserved.staticSetFeelsLikeTemperature(siteRequest2, (String)result.get(WeatherObserved.VAR_feelsLikeTemperature)));
			page.persistForClass(WeatherObserved.VAR_temperature, WeatherObserved.staticSetTemperature(siteRequest2, (String)result.get(WeatherObserved.VAR_temperature)));
			page.persistForClass(WeatherObserved.VAR_solarRadiation, WeatherObserved.staticSetSolarRadiation(siteRequest2, (String)result.get(WeatherObserved.VAR_solarRadiation)));
			page.persistForClass(WeatherObserved.VAR_streamGauge, WeatherObserved.staticSetStreamGauge(siteRequest2, (String)result.get(WeatherObserved.VAR_streamGauge)));
			page.persistForClass(WeatherObserved.VAR_uVIndexMax, WeatherObserved.staticSetUVIndexMax(siteRequest2, (String)result.get(WeatherObserved.VAR_uVIndexMax)));
			page.persistForClass(WeatherObserved.VAR_visibility, WeatherObserved.staticSetVisibility(siteRequest2, (String)result.get(WeatherObserved.VAR_visibility)));
			page.persistForClass(WeatherObserved.VAR_weatherType, WeatherObserved.staticSetWeatherType(siteRequest2, (String)result.get(WeatherObserved.VAR_weatherType)));
			page.persistForClass(WeatherObserved.VAR_dewPoint, WeatherObserved.staticSetDewPoint(siteRequest2, (String)result.get(WeatherObserved.VAR_dewPoint)));
			page.persistForClass(WeatherObserved.VAR_diffuseIrradiation, WeatherObserved.staticSetDiffuseIrradiation(siteRequest2, (String)result.get(WeatherObserved.VAR_diffuseIrradiation)));
			page.persistForClass(WeatherObserved.VAR_directIrradiation, WeatherObserved.staticSetDirectIrradiation(siteRequest2, (String)result.get(WeatherObserved.VAR_directIrradiation)));
			page.persistForClass(WeatherObserved.VAR_illuminance, WeatherObserved.staticSetIlluminance(siteRequest2, (String)result.get(WeatherObserved.VAR_illuminance)));
			page.persistForClass(WeatherObserved.VAR_ngsildTenant, WeatherObserved.staticSetNgsildTenant(siteRequest2, (String)result.get(WeatherObserved.VAR_ngsildTenant)));
			page.persistForClass(WeatherObserved.VAR_ngsildPath, WeatherObserved.staticSetNgsildPath(siteRequest2, (String)result.get(WeatherObserved.VAR_ngsildPath)));
			page.persistForClass(WeatherObserved.VAR_ngsildData, WeatherObserved.staticSetNgsildData(siteRequest2, (String)result.get(WeatherObserved.VAR_ngsildData)));
			page.persistForClass(WeatherObserved.VAR_entityId, WeatherObserved.staticSetEntityId(siteRequest2, (String)result.get(WeatherObserved.VAR_entityId)));
			page.persistForClass(WeatherObserved.VAR_ngsildContext, WeatherObserved.staticSetNgsildContext(siteRequest2, (String)result.get(WeatherObserved.VAR_ngsildContext)));

			page.promiseDeepForClass((SiteRequest)siteRequest).onSuccess(a -> {
				try {
					JsonObject data = JsonObject.mapFrom(result);
					promise.complete(data);
				} catch(Exception ex) {
					LOG.error(String.format(importModelFail, classSimpleName), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("generatePageBody failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("generatePageBody failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
}
