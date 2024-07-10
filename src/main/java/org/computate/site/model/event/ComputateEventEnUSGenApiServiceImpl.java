package org.computate.site.model.event;

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
import io.vertx.core.eventbus.EventBus;
import io.vertx.pgclient.PgPool;
import io.vertx.kafka.client.producer.KafkaProducer;
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
import org.computate.vertx.config.ComputateConfigKeys;
import io.vertx.core.Vertx;
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
import org.computate.site.model.event.ComputateEventPage;


/**
 * Translate: false
 * Generated: true
 **/
public class ComputateEventEnUSGenApiServiceImpl extends BaseApiServiceImpl implements ComputateEventEnUSGenApiService {

	protected static final Logger LOG = LoggerFactory.getLogger(ComputateEventEnUSGenApiServiceImpl.class);

	public ComputateEventEnUSGenApiServiceImpl(EventBus eventBus, JsonObject config, WorkerExecutor workerExecutor, PgPool pgPool, KafkaProducer<String, String> kafkaProducer, WebClient webClient, OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider, Jinjava jinjava) {
		super(eventBus, config, workerExecutor, pgPool, kafkaProducer, webClient, oauth2AuthenticationProvider, authorizationProvider, jinjava);
	}

	// Search //

	@Override
	public void searchComputateEvent(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "GET"))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "POST"))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "PATCH"))
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
						searchComputateEventList(siteRequest, false, true, false).onSuccess(listComputateEvent -> {
							response200SearchComputateEvent(listComputateEvent).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchComputateEvent succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchComputateEvent failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchComputateEvent failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchComputateEvent failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchComputateEvent failed. ", ex2));
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
				LOG.error(String.format("searchComputateEvent failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}


	public Future<ServiceResponse> response200SearchComputateEvent(SearchList<ComputateEvent> listComputateEvent) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listComputateEvent.getSiteRequest_(SiteRequest.class);
			List<String> fls = listComputateEvent.getRequest().getFields();
			JsonObject json = new JsonObject();
			JsonArray l = new JsonArray();
			listComputateEvent.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					for(String fieldName : json2.fieldNames()) {
						String v = ComputateEvent.varIndexedComputateEvent(fieldName);
						if(v != null)
							fieldNames.add(ComputateEvent.varIndexedComputateEvent(fieldName));
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
			response200Search(listComputateEvent.getRequest(), listComputateEvent.getResponse(), json);
			promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void responsePivotSearchComputateEvent(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
					responsePivotSearchComputateEvent(pivotFields2, pivotArray2);
				}
			}
		}
	}

	// GET //

	@Override
	public void getComputateEvent(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "GET"))
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
						searchComputateEventList(siteRequest, false, true, false).onSuccess(listComputateEvent -> {
							response200GETComputateEvent(listComputateEvent).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("getComputateEvent succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("getComputateEvent failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("getComputateEvent failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("getComputateEvent failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("getComputateEvent failed. ", ex2));
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
				LOG.error(String.format("getComputateEvent failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}


	public Future<ServiceResponse> response200GETComputateEvent(SearchList<ComputateEvent> listComputateEvent) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listComputateEvent.getSiteRequest_(SiteRequest.class);
			JsonObject json = JsonObject.mapFrom(listComputateEvent.getList().stream().findFirst().orElse(null));
			promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
		} catch(Exception ex) {
			LOG.error(String.format("response200GETComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PATCH //

	@Override
	public void patchComputateEvent(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("patchComputateEvent started. "));
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
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "PATCH"))
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
						searchComputateEventList(siteRequest, false, true, true).onSuccess(listComputateEvent -> {
							try {
								if(listComputateEvent.getResponse().getResponse().getNumFound() > 1
										&& !Optional.ofNullable(config.getString(ComputateConfigKeys.AUTH_ROLE_REQUIRED + "_ComputateEvent")).map(v -> RoleBasedAuthorization.create(v).match(siteRequest.getUser())).orElse(false)
										) {
									String message = String.format("roles required: " + config.getString(ComputateConfigKeys.AUTH_ROLE_REQUIRED + "_ComputateEvent"));
									LOG.error(message);
									error(siteRequest, eventHandler, new RuntimeException(message));
								} else {

									ApiRequest apiRequest = new ApiRequest();
									apiRequest.setRows(listComputateEvent.getRequest().getRows());
									apiRequest.setNumFound(listComputateEvent.getResponse().getResponse().getNumFound());
									apiRequest.setNumPATCH(0L);
									apiRequest.initDeepApiRequest(siteRequest);
									siteRequest.setApiRequest_(apiRequest);
									if(apiRequest.getNumFound() == 1L)
										apiRequest.setOriginal(listComputateEvent.first());
									apiRequest.setPk(Optional.ofNullable(listComputateEvent.first()).map(o2 -> o2.getPk()).orElse(null));
									eventBus.publish("websocketComputateEvent", JsonObject.mapFrom(apiRequest).toString());

									listPATCHComputateEvent(apiRequest, listComputateEvent).onSuccess(e -> {
										response200PATCHComputateEvent(siteRequest).onSuccess(response -> {
											LOG.debug(String.format("patchComputateEvent succeeded. "));
											eventHandler.handle(Future.succeededFuture(response));
										}).onFailure(ex -> {
											LOG.error(String.format("patchComputateEvent failed. "), ex);
											error(siteRequest, eventHandler, ex);
										});
									}).onFailure(ex -> {
										LOG.error(String.format("patchComputateEvent failed. "), ex);
										error(siteRequest, eventHandler, ex);
									});
								}
							} catch(Exception ex) {
								LOG.error(String.format("patchComputateEvent failed. "), ex);
								error(siteRequest, eventHandler, ex);
							}
						}).onFailure(ex -> {
							LOG.error(String.format("patchComputateEvent failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("patchComputateEvent failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("patchComputateEvent failed. ", ex2));
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
				LOG.error(String.format("patchComputateEvent failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}


	public Future<Void> listPATCHComputateEvent(ApiRequest apiRequest, SearchList<ComputateEvent> listComputateEvent) {
		Promise<Void> promise = Promise.promise();
		List<Future> futures = new ArrayList<>();
		SiteRequest siteRequest = listComputateEvent.getSiteRequest_(SiteRequest.class);
		listComputateEvent.getList().forEach(o -> {
			SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
			o.setSiteRequest_(siteRequest2);
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			futures.add(Future.future(promise1 -> {
				patchComputateEventFuture(o, false).onSuccess(a -> {
					promise1.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("listPATCHComputateEvent failed. "), ex);
					promise1.fail(ex);
				});
			}));
		});
		CompositeFuture.all(futures).onSuccess( a -> {
			listComputateEvent.next().onSuccess(next -> {
				if(next) {
					listPATCHComputateEvent(apiRequest, listComputateEvent).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPATCHComputateEvent failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete();
				}
			}).onFailure(ex -> {
				LOG.error(String.format("listPATCHComputateEvent failed. "), ex);
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			LOG.error(String.format("listPATCHComputateEvent failed. "), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override
	public void patchComputateEventFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			try {
				siteRequest.setJsonObject(body);
				serviceRequest.getParams().getJsonObject("query").put("rows", 1);
				searchComputateEventList(siteRequest, false, true, true).onSuccess(listComputateEvent -> {
					try {
						ComputateEvent o = listComputateEvent.first();
						if(o != null && listComputateEvent.getResponse().getResponse().getNumFound() == 1) {
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
							apiRequest.setPk(Optional.ofNullable(listComputateEvent.first()).map(o2 -> o2.getPk()).orElse(null));
							patchComputateEventFuture(o, false).onSuccess(o2 -> {
								eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
							}).onFailure(ex -> {
								eventHandler.handle(Future.failedFuture(ex));
							});
						} else {
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
						}
					} catch(Exception ex) {
						LOG.error(String.format("patchComputateEvent failed. "), ex);
						error(siteRequest, eventHandler, ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format("patchComputateEvent failed. "), ex);
					error(siteRequest, eventHandler, ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format("patchComputateEvent failed. "), ex);
				error(null, eventHandler, ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("patchComputateEvent failed. "), ex);
			error(null, eventHandler, ex);
		});
	}

	public Future<ComputateEvent> patchComputateEventFuture(ComputateEvent o, Boolean inheritPk) {
		SiteRequest siteRequest = o.getSiteRequest_();
		Promise<ComputateEvent> promise = Promise.promise();

		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			Promise<ComputateEvent> promise1 = Promise.promise();
			pgPool.withTransaction(sqlConnection -> {
				siteRequest.setSqlConnection(sqlConnection);
				varsComputateEvent(siteRequest).onSuccess(a -> {
					sqlPATCHComputateEvent(o, inheritPk).onSuccess(computateEvent -> {
						persistComputateEvent(computateEvent).onSuccess(c -> {
							relateComputateEvent(computateEvent).onSuccess(d -> {
								indexComputateEvent(computateEvent).onSuccess(o2 -> {
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
											o2.apiRequestComputateEvent();
											if(apiRequest.getVars().size() > 0)
												eventBus.publish("websocketComputateEvent", JsonObject.mapFrom(apiRequest).toString());
										}
									}
									promise1.complete(computateEvent);
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
			}).compose(computateEvent -> {
				Promise<ComputateEvent> promise2 = Promise.promise();
				refreshComputateEvent(computateEvent).onSuccess(a -> {
					promise2.complete(computateEvent);
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(computateEvent -> {
				promise.complete(computateEvent);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("patchComputateEventFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ComputateEvent> sqlPATCHComputateEvent(ComputateEvent o, Boolean inheritPk) {
		Promise<ComputateEvent> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE ComputateEvent SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			ComputateEvent o2 = new ComputateEvent();
			o2.setSiteRequest_(siteRequest);
			List<Future> futures1 = new ArrayList<>();
			List<Future> futures2 = new ArrayList<>();

			for(String entityVar : methodNames) {
				switch(entityVar) {
					case "setInheritPk":
							o2.setInheritPk(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ComputateEvent.VAR_inheritPk + "=$" + num);
							num++;
							bParams.add(o2.sqlInheritPk());
						break;
					case "setCreated":
							o2.setCreated(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ComputateEvent.VAR_created + "=$" + num);
							num++;
							bParams.add(o2.sqlCreated());
						break;
					case "setArchived":
							o2.setArchived(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ComputateEvent.VAR_archived + "=$" + num);
							num++;
							bParams.add(o2.sqlArchived());
						break;
					case "setDeleted":
							o2.setDeleted(jsonObject.getBoolean(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ComputateEvent.VAR_deleted + "=$" + num);
							num++;
							bParams.add(o2.sqlDeleted());
						break;
					case "setSessionId":
							o2.setSessionId(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ComputateEvent.VAR_sessionId + "=$" + num);
							num++;
							bParams.add(o2.sqlSessionId());
						break;
					case "setUserKey":
							o2.setUserKey(jsonObject.getString(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ComputateEvent.VAR_userKey + "=$" + num);
							num++;
							bParams.add(o2.sqlUserKey());
						break;
					case "setLocation":
							o2.setLocation(jsonObject.getJsonObject(entityVar));
							if(bParams.size() > 0)
								bSql.append(", ");
							bSql.append(ComputateEvent.VAR_location + "=$" + num);
							num++;
							bParams.add(o2.sqlLocation());
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
						RuntimeException ex2 = new RuntimeException("value ComputateEvent failed", ex);
						LOG.error(String.format("relateComputateEvent failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					ComputateEvent o3 = new ComputateEvent();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					promise.complete(o3);
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPATCHComputateEvent failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPATCHComputateEvent failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPATCHComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200PATCHComputateEvent(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
		} catch(Exception ex) {
			LOG.error(String.format("response200PATCHComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// POST //

	@Override
	public void postComputateEvent(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("postComputateEvent started. "));
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
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "POST"))
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
						eventBus.publish("websocketComputateEvent", JsonObject.mapFrom(apiRequest).toString());
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
						eventBus.request(ComputateEvent.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postComputateEventFuture")).onSuccess(a -> {
							JsonObject responseMessage = (JsonObject)a.body();
							JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
							apiRequest.setPk(Long.parseLong(responseBody.getString("pk")));
							eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
							LOG.debug(String.format("postComputateEvent succeeded. "));
						}).onFailure(ex -> {
							LOG.error(String.format("postComputateEvent failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("postComputateEvent failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postComputateEvent failed. ", ex2));
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
				LOG.error(String.format("postComputateEvent failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}


	@Override
	public void postComputateEventFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
			postComputateEventFuture(siteRequest, false).onSuccess(o -> {
				eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
			}).onFailure(ex -> {
				eventHandler.handle(Future.failedFuture(ex));
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("postComputateEvent failed. ", ex2));
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
				LOG.error(String.format("postComputateEvent failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ComputateEvent> postComputateEventFuture(SiteRequest siteRequest, Boolean inheritPk) {
		Promise<ComputateEvent> promise = Promise.promise();

		try {
			pgPool.withTransaction(sqlConnection -> {
				Promise<ComputateEvent> promise1 = Promise.promise();
				siteRequest.setSqlConnection(sqlConnection);
				varsComputateEvent(siteRequest).onSuccess(a -> {
					createComputateEvent(siteRequest).onSuccess(computateEvent -> {
						sqlPOSTComputateEvent(computateEvent, inheritPk).onSuccess(b -> {
							persistComputateEvent(computateEvent).onSuccess(c -> {
								relateComputateEvent(computateEvent).onSuccess(d -> {
									indexComputateEvent(computateEvent).onSuccess(o2 -> {
										promise1.complete(computateEvent);
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
			}).compose(computateEvent -> {
				Promise<ComputateEvent> promise2 = Promise.promise();
				refreshComputateEvent(computateEvent).onSuccess(a -> {
					try {
						ApiRequest apiRequest = siteRequest.getApiRequest_();
						if(apiRequest != null) {
							apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
							computateEvent.apiRequestComputateEvent();
							eventBus.publish("websocketComputateEvent", JsonObject.mapFrom(apiRequest).toString());
						}
						promise2.complete(computateEvent);
					} catch(Exception ex) {
						LOG.error(String.format("postComputateEventFuture failed. "), ex);
						promise.fail(ex);
					}
				}).onFailure(ex -> {
					promise2.fail(ex);
				});
				return promise2.future();
			}).onSuccess(computateEvent -> {
				promise.complete(computateEvent);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("postComputateEventFuture failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> sqlPOSTComputateEvent(ComputateEvent o, Boolean inheritPk) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Integer num = 1;
			StringBuilder bSql = new StringBuilder("UPDATE ComputateEvent SET ");
			List<Object> bParams = new ArrayList<Object>();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			ComputateEvent o2 = new ComputateEvent();
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
					case ComputateEvent.VAR_inheritPk:
						o2.setInheritPk(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ComputateEvent.VAR_inheritPk + "=$" + num);
						num++;
						bParams.add(o2.sqlInheritPk());
						break;
					case ComputateEvent.VAR_created:
						o2.setCreated(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ComputateEvent.VAR_created + "=$" + num);
						num++;
						bParams.add(o2.sqlCreated());
						break;
					case ComputateEvent.VAR_archived:
						o2.setArchived(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ComputateEvent.VAR_archived + "=$" + num);
						num++;
						bParams.add(o2.sqlArchived());
						break;
					case ComputateEvent.VAR_deleted:
						o2.setDeleted(jsonObject.getBoolean(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ComputateEvent.VAR_deleted + "=$" + num);
						num++;
						bParams.add(o2.sqlDeleted());
						break;
					case ComputateEvent.VAR_sessionId:
						o2.setSessionId(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ComputateEvent.VAR_sessionId + "=$" + num);
						num++;
						bParams.add(o2.sqlSessionId());
						break;
					case ComputateEvent.VAR_userKey:
						o2.setUserKey(jsonObject.getString(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ComputateEvent.VAR_userKey + "=$" + num);
						num++;
						bParams.add(o2.sqlUserKey());
						break;
					case ComputateEvent.VAR_location:
						o2.setLocation(jsonObject.getJsonObject(entityVar));
						if(bParams.size() > 0) {
							bSql.append(", ");
						}
						bSql.append(ComputateEvent.VAR_location + "=$" + num);
						num++;
						bParams.add(o2.sqlLocation());
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
						RuntimeException ex2 = new RuntimeException("value ComputateEvent failed", ex);
						LOG.error(String.format("relateComputateEvent failed. "), ex2);
						a.handle(Future.failedFuture(ex2));
					});
				}));
			}
			CompositeFuture.all(futures1).onSuccess(a -> {
				CompositeFuture.all(futures2).onSuccess(b -> {
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format("sqlPOSTComputateEvent failed. "), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("sqlPOSTComputateEvent failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("sqlPOSTComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<ServiceResponse> response200POSTComputateEvent(ComputateEvent o) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
		} catch(Exception ex) {
			LOG.error(String.format("response200POSTComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// PUTImport //

	@Override
	public void putimportComputateEvent(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		LOG.debug(String.format("putimportComputateEvent started. "));
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
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "GET"))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "POST"))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "PATCH"))
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
						eventBus.publish("websocketComputateEvent", JsonObject.mapFrom(apiRequest).toString());
						varsComputateEvent(siteRequest).onSuccess(d -> {
							listPUTImportComputateEvent(apiRequest, siteRequest).onSuccess(e -> {
								response200PUTImportComputateEvent(siteRequest).onSuccess(response -> {
									LOG.debug(String.format("putimportComputateEvent succeeded. "));
									eventHandler.handle(Future.succeededFuture(response));
								}).onFailure(ex -> {
									LOG.error(String.format("putimportComputateEvent failed. "), ex);
									error(siteRequest, eventHandler, ex);
								});
							}).onFailure(ex -> {
								LOG.error(String.format("putimportComputateEvent failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("putimportComputateEvent failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("putimportComputateEvent failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportComputateEvent failed. ", ex2));
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
				LOG.error(String.format("putimportComputateEvent failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}


	public Future<Void> listPUTImportComputateEvent(ApiRequest apiRequest, SiteRequest siteRequest) {
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
					eventBus.request(ComputateEvent.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportComputateEventFuture")).onSuccess(a -> {
						promise1.complete();
					}).onFailure(ex -> {
						LOG.error(String.format("listPUTImportComputateEvent failed. "), ex);
						promise1.fail(ex);
					});
				}));
			});
			CompositeFuture.all(futures).onSuccess(a -> {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
				promise.complete();
			}).onFailure(ex -> {
				LOG.error(String.format("listPUTImportComputateEvent failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("listPUTImportComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	@Override
	public void putimportComputateEventFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture").onSuccess(siteRequest -> {
			try {
				ApiRequest apiRequest = new ApiRequest();
				apiRequest.setRows(1L);
				apiRequest.setNumFound(1L);
				apiRequest.setNumPATCH(0L);
				apiRequest.initDeepApiRequest(siteRequest);
				siteRequest.setApiRequest_(apiRequest);
				String inheritPk = Optional.ofNullable(body.getString(ComputateEvent.VAR_pk)).orElse(body.getString(ComputateEvent.VAR_id));
				body.put("inheritPk", inheritPk);
				if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
					siteRequest.getRequestVars().put( "refresh", "false" );
				}

				SearchList<ComputateEvent> searchList = new SearchList<ComputateEvent>();
				searchList.setStore(true);
				searchList.q("*:*");
				searchList.setC(ComputateEvent.class);
				searchList.fq("deleted_docvalues_boolean:false");
				searchList.fq("archived_docvalues_boolean:false");
				searchList.fq("inheritPk_docvalues_string:" + SearchTool.escapeQueryChars(inheritPk));
				searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
					try {
						if(searchList.size() >= 1) {
							ComputateEvent o = searchList.getList().stream().findFirst().orElse(null);
							ComputateEvent o2 = new ComputateEvent();
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
									if(!StringUtils.containsAny(f, "pk", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
										body2.put("set" + StringUtils.capitalize(f), bodyVal);
								}
							}
							for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
								if(!body.fieldNames().contains(f)) {
									if(!StringUtils.containsAny(f, "pk", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
										body2.putNull("set" + StringUtils.capitalize(f));
								}
							}
							if(body2.size() > 0) {
								if(searchList.size() == 1) {
									apiRequest.setOriginal(o);
									apiRequest.setPk(o.getPk());
								}
								siteRequest.setJsonObject(body2);
								patchComputateEventFuture(o, true).onSuccess(b -> {
									LOG.debug("Import ComputateEvent {} succeeded, modified ComputateEvent. ", body.getValue(ComputateEvent.VAR_pk));
									eventHandler.handle(Future.succeededFuture());
								}).onFailure(ex -> {
									LOG.error(String.format("putimportComputateEventFuture failed. "), ex);
									eventHandler.handle(Future.failedFuture(ex));
								});
							} else {
								eventHandler.handle(Future.succeededFuture());
							}
						} else {
							postComputateEventFuture(siteRequest, true).onSuccess(b -> {
								LOG.debug("Import ComputateEvent {} succeeded, created new ComputateEvent. ", body.getValue(ComputateEvent.VAR_pk));
								eventHandler.handle(Future.succeededFuture());
							}).onFailure(ex -> {
								LOG.error(String.format("putimportComputateEventFuture failed. "), ex);
								eventHandler.handle(Future.failedFuture(ex));
							});
						}
					} catch(Exception ex) {
						LOG.error(String.format("putimportComputateEventFuture failed. "), ex);
						eventHandler.handle(Future.failedFuture(ex));
					}
				}).onFailure(ex -> {
					LOG.error(String.format("putimportComputateEventFuture failed. "), ex);
					eventHandler.handle(Future.failedFuture(ex));
				});
			} catch(Exception ex) {
				LOG.error(String.format("putimportComputateEventFuture failed. "), ex);
				eventHandler.handle(Future.failedFuture(ex));
			}
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("putimportComputateEvent failed. ", ex2));
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
				LOG.error(String.format("putimportComputateEvent failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}

	public Future<ServiceResponse> response200PUTImportComputateEvent(SiteRequest siteRequest) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			JsonObject json = new JsonObject();
			promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
		} catch(Exception ex) {
			LOG.error(String.format("response200PUTImportComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// SearchPage //

	@Override
	public void searchpageComputateEventId(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
		searchpageComputateEvent(serviceRequest, eventHandler);
	}

	@Override
	public void searchpageComputateEvent(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "GET"))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "POST"))
							.add("permission", String.format("%s#%s", ComputateEvent.CLASS_SIMPLE_NAME, "PATCH"))
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
						searchComputateEventList(siteRequest, false, true, false).onSuccess(listComputateEvent -> {
							response200SearchPageComputateEvent(listComputateEvent).onSuccess(response -> {
								eventHandler.handle(Future.succeededFuture(response));
								LOG.debug(String.format("searchpageComputateEvent succeeded. "));
							}).onFailure(ex -> {
								LOG.error(String.format("searchpageComputateEvent failed. "), ex);
								error(siteRequest, eventHandler, ex);
							});
						}).onFailure(ex -> {
							LOG.error(String.format("searchpageComputateEvent failed. "), ex);
							error(siteRequest, eventHandler, ex);
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format("searchpageComputateEvent failed. "), ex);
					error(null, eventHandler, ex);
				}
			});
		}).onFailure(ex -> {
			if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
				try {
					eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
				} catch(Exception ex2) {
					LOG.error(String.format("searchpageComputateEvent failed. ", ex2));
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
				LOG.error(String.format("searchpageComputateEvent failed. "), ex);
				error(null, eventHandler, ex);
			}
		});
	}


	public void searchpageComputateEventPageInit(ComputateEventPage page, SearchList<ComputateEvent> listComputateEvent) {
	}

	public String templateSearchPageComputateEvent() {
		return "/enUS/ComputateEventPage.htm";
	}
	public Future<ServiceResponse> response200SearchPageComputateEvent(SearchList<ComputateEvent> listComputateEvent) {
		Promise<ServiceResponse> promise = Promise.promise();
		try {
			SiteRequest siteRequest = listComputateEvent.getSiteRequest_(SiteRequest.class);
			String pageTemplateUri = templateSearchPageComputateEvent();
			String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
			Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
			String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
			ComputateEventPage page = new ComputateEventPage();
			MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
			siteRequest.setRequestHeaders(requestHeaders);

			if(listComputateEvent.size() == 1)
				siteRequest.setRequestPk(listComputateEvent.get(0).getPk());
			page.setSearchListComputateEvent_(listComputateEvent);
			page.setSiteRequest_(siteRequest);
			page.promiseDeepComputateEventPage(siteRequest).onSuccess(a -> {
				JsonObject ctx = ComputateConfigKeys.getPageContext(config);
				ctx.mergeIn(JsonObject.mapFrom(page));
				String renderedTemplate = jinjava.render(template, ctx.getMap());
				Buffer buffer = Buffer.buffer(renderedTemplate);
				promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("response200SearchPageComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	// General //

	public Future<ComputateEvent> createComputateEvent(SiteRequest siteRequest) {
		Promise<ComputateEvent> promise = Promise.promise();
		try {
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long userKey = siteRequest.getUserKey();
			ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

			sqlConnection.preparedQuery("INSERT INTO ComputateEvent(created, userKey) VALUES($1, $2) RETURNING pk")
					.collecting(Collectors.toList())
					.execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
				Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				ComputateEvent o = new ComputateEvent();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				promise.complete(o);
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error("createComputateEvent failed. ", ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("createComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public void searchComputateEventQ(SearchList<ComputateEvent> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
		}
	}

	public String searchComputateEventFq(SearchList<ComputateEvent> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		if(StringUtils.startsWith(valueIndexed, "[")) {
			String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
			if(fqs.length != 2)
				throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
			String fq1 = fqs[0].equals("*") ? fqs[0] : ComputateEvent.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
			String fq2 = fqs[1].equals("*") ? fqs[1] : ComputateEvent.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
			 return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
		} else {
			return varIndexed + ":" + SearchTool.escapeQueryChars(ComputateEvent.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
		}
	}

	public void searchComputateEventSort(SearchList<ComputateEvent> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.sort(varIndexed, valueIndexed);
	}

	public void searchComputateEventRows(SearchList<ComputateEvent> searchList, Long valueRows) {
			searchList.rows(valueRows != null ? valueRows : 10L);
	}

	public void searchComputateEventStart(SearchList<ComputateEvent> searchList, Long valueStart) {
		searchList.start(valueStart);
	}

	public void searchComputateEventVar(SearchList<ComputateEvent> searchList, String var, String value) {
		searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
	}

	public void searchComputateEventUri(SearchList<ComputateEvent> searchList) {
	}

	public Future<ServiceResponse> varsComputateEvent(SiteRequest siteRequest) {
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
					LOG.error(String.format("searchComputateEvent failed. "), ex);
					promise.fail(ex);
				}
			});
			promise.complete();
		} catch(Exception ex) {
			LOG.error(String.format("searchComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<SearchList<ComputateEvent>> searchComputateEventList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify) {
		Promise<SearchList<ComputateEvent>> promise = Promise.promise();
		try {
			ServiceRequest serviceRequest = siteRequest.getServiceRequest();
			String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<ComputateEvent> searchList = new SearchList<ComputateEvent>();
			String facetRange = null;
			Date facetRangeStart = null;
			Date facetRangeEnd = null;
			String facetRangeGap = null;
			String statsField = null;
			String statsFieldIndexed = null;
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.q("*:*");
			searchList.setC(ComputateEvent.class);
			searchList.setSiteRequest_(siteRequest);
			if(entityList != null) {
				for(String v : entityList) {
					searchList.fl(ComputateEvent.varIndexedComputateEvent(v));
				}
			}

			String id = serviceRequest.getParams().getJsonObject("path").getString("id");
			if(id != null && NumberUtils.isCreatable(id)) {
				searchList.fq("(pk_docvalues_long:" + SearchTool.escapeQueryChars(id) + " OR objectId_docvalues_string:" + SearchTool.escapeQueryChars(id) + ")");
			} else if(id != null) {
				searchList.fq("objectId_docvalues_string:" + SearchTool.escapeQueryChars(id));
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
								varsIndexed[i] = ComputateEvent.varIndexedComputateEvent(entityVar);
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
										varIndexed = ComputateEvent.varIndexedComputateEvent(entityVar);
										String entityQ = searchComputateEventFq(searchList, entityVar, valueIndexed, varIndexed);
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
										varIndexed = ComputateEvent.varIndexedComputateEvent(entityVar);
										String entityFq = searchComputateEventFq(searchList, entityVar, valueIndexed, varIndexed);
										mFq.appendReplacement(sb, entityFq);
										foundFq = mFq.find();
									}
									mFq.appendTail(sb);
									searchList.fq(sb.toString());
								}
							} else if(paramName.equals("sort")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = ComputateEvent.varIndexedComputateEvent(entityVar);
								searchComputateEventSort(searchList, entityVar, valueIndexed, varIndexed);
							} else if(paramName.equals("start")) {
								valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchComputateEventStart(searchList, valueStart);
							} else if(paramName.equals("rows")) {
								valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
								searchComputateEventRows(searchList, valueRows);
							} else if(paramName.equals("stats")) {
								searchList.stats((Boolean)paramObject);
							} else if(paramName.equals("stats.field")) {
								Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
								boolean foundStats = mStats.find();
								if(foundStats) {
									String solrLocalParams = mStats.group(1);
									entityVar = mStats.group(2).trim();
									varIndexed = ComputateEvent.varIndexedComputateEvent(entityVar);
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
									varIndexed = ComputateEvent.varIndexedComputateEvent(entityVar);
									searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
									facetRange = entityVar;
								}
							} else if(paramName.equals("facet.field")) {
								entityVar = (String)paramObject;
								varIndexed = ComputateEvent.varIndexedComputateEvent(entityVar);
								if(varIndexed != null)
									searchList.facetField(varIndexed);
							} else if(paramName.equals("var")) {
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								searchComputateEventVar(searchList, entityVar, valueIndexed);
							} else if(paramName.equals("cursorMark")) {
								valueCursorMark = (String)paramObject;
								searchList.cursorMark((String)paramObject);
							}
						}
						searchComputateEventUri(searchList);
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
			searchComputateEvent2(siteRequest, populate, store, modify, searchList);
			searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
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
						LOG.error(String.format("searchComputateEvent failed. "), ex);
						promise.fail(ex);
					});
				} else {
					promise.complete(searchList);
				}
			}).onFailure(ex -> {
				LOG.error(String.format("searchComputateEvent failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("searchComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
	public void searchComputateEvent2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<ComputateEvent> searchList) {
	}

	public Future<Void> persistComputateEvent(ComputateEvent o) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			SqlConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			sqlConnection.preparedQuery("SELECT * FROM ComputateEvent WHERE pk=$1")
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
									LOG.error(String.format("persistComputateEvent failed. "), e);
								}
							}
						}
					}
					promise.complete();
				} catch(Exception ex) {
					LOG.error(String.format("persistComputateEvent failed. "), ex);
					promise.fail(ex);
				}
			}).onFailure(ex -> {
				RuntimeException ex2 = new RuntimeException(ex);
				LOG.error(String.format("persistComputateEvent failed. "), ex2);
				promise.fail(ex2);
			});
		} catch(Exception ex) {
			LOG.error(String.format("persistComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public Future<Void> relateComputateEvent(ComputateEvent o) {
		Promise<Void> promise = Promise.promise();
			promise.complete();
		return promise.future();
	}

	public Future<ComputateEvent> indexComputateEvent(ComputateEvent o) {
		Promise<ComputateEvent> promise = Promise.promise();
		try {
			SiteRequest siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			o.promiseDeepForClass(siteRequest).onSuccess(a -> {
				JsonObject json = new JsonObject();
				JsonObject add = new JsonObject();
				json.put("add", add);
				JsonObject doc = new JsonObject();
				add.put("doc", doc);
				o.indexComputateEvent(doc);
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
					LOG.error(String.format("indexComputateEvent failed. "), new RuntimeException(ex));
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format("indexComputateEvent failed. "), ex);
				promise.fail(ex);
			});
		} catch(Exception ex) {
			LOG.error(String.format("indexComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public String searchVar(String varIndexed) {
		return ComputateEvent.searchVarComputateEvent(varIndexed);
	}

	@Override
	public String getClassApiAddress() {
		return ComputateEvent.CLASS_API_ADDRESS_ComputateEvent;
	}

	public Future<Void> refreshComputateEvent(ComputateEvent o) {
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
					eventBus.request(ComputateEvent.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchComputateEventFuture")).onSuccess(c -> {
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
			LOG.error(String.format("refreshComputateEvent failed. "), ex);
			promise.fail(ex);
		}
		return promise.future();
	}
}
