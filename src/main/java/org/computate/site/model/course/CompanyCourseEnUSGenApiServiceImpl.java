package org.computate.site.model.course;

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
import io.vertx.core.http.HttpResponseExpectation;
import java.nio.charset.Charset;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.client.HttpResponse;
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
import org.computate.site.model.course.CompanyCoursePage;


/**
 * Translate: false
 * Generated: true
 **/
public class CompanyCourseEnUSGenApiServiceImpl extends BaseApiServiceImpl implements CompanyCourseEnUSGenApiService {

  protected static final Logger LOG = LoggerFactory.getLogger(CompanyCourseEnUSGenApiServiceImpl.class);

  // Search //

  @Override
  public void searchCompanyCourse(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
            searchCompanyCourseList(siteRequest, false, true, false).onSuccess(listCompanyCourse -> {
              response200SearchCompanyCourse(listCompanyCourse).onSuccess(response -> {
                eventHandler.handle(Future.succeededFuture(response));
                LOG.debug(String.format("searchCompanyCourse succeeded. "));
              }).onFailure(ex -> {
                LOG.error(String.format("searchCompanyCourse failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }).onFailure(ex -> {
              LOG.error(String.format("searchCompanyCourse failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("searchCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200SearchCompanyCourse(SearchList<CompanyCourse> listCompanyCourse) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listCompanyCourse.getSiteRequest_(SiteRequest.class);
      List<String> fls = listCompanyCourse.getRequest().getFields();
      JsonObject json = new JsonObject();
      JsonArray l = new JsonArray();
      listCompanyCourse.getList().stream().forEach(o -> {
        JsonObject json2 = JsonObject.mapFrom(o);
        if(fls.size() > 0) {
          Set<String> fieldNames = new HashSet<String>();
          for(String fieldName : json2.fieldNames()) {
            String v = CompanyCourse.varIndexedCompanyCourse(fieldName);
            if(v != null)
              fieldNames.add(CompanyCourse.varIndexedCompanyCourse(fieldName));
          }
          if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals("saves_docvalues_strings")) {
            fieldNames.removeAll(Optional.ofNullable(json2.getJsonArray("saves_docvalues_strings")).orElse(new JsonArray()).stream().map(s -> s.toString()).collect(Collectors.toList()));
            fieldNames.remove("_docvalues_long");
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
      response200Search(listCompanyCourse.getRequest(), listCompanyCourse.getResponse(), json);
      if(json == null) {
        String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
        String m = String.format("%s %s not found", "course", pageId);
        promise.complete(new ServiceResponse(404
            , m
            , Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
      } else {
        promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
      }
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchCompanyCourse(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotSearchCompanyCourse(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // GET //

  @Override
  public void getCompanyCourse(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
            searchCompanyCourseList(siteRequest, false, true, false).onSuccess(listCompanyCourse -> {
              response200GETCompanyCourse(listCompanyCourse).onSuccess(response -> {
                eventHandler.handle(Future.succeededFuture(response));
                LOG.debug(String.format("getCompanyCourse succeeded. "));
              }).onFailure(ex -> {
                LOG.error(String.format("getCompanyCourse failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }).onFailure(ex -> {
              LOG.error(String.format("getCompanyCourse failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("getCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("getCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200GETCompanyCourse(SearchList<CompanyCourse> listCompanyCourse) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listCompanyCourse.getSiteRequest_(SiteRequest.class);
      JsonObject json = JsonObject.mapFrom(listCompanyCourse.getList().stream().findFirst().orElse(null));
      if(json == null) {
        String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
        String m = String.format("%s %s not found", "course", pageId);
        promise.complete(new ServiceResponse(404
            , m
            , Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
      } else {
        promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
      }
    } catch(Exception ex) {
      LOG.error(String.format("response200GETCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  // PATCH //

  @Override
  public void patchCompanyCourse(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("patchCompanyCourse started. "));
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
      String COMPANYCOURSE = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("COMPANYCOURSE");
      MultiMap form = MultiMap.caseInsensitiveMultiMap();
      form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
      form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
      form.add("response_mode", "permissions");
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "GET"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "POST"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "DELETE"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PATCH"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PUT"));
      if(pageId != null)
        form.add("permission", String.format("%s#%s", pageId, "PATCH"));
      webClient.post(
          config.getInteger(ComputateConfigKeys.AUTH_PORT)
          , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
          , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
          )
          .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
          .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
          .sendForm(form)
          .expecting(HttpResponseExpectation.SC_OK)
      .onComplete(authorizationDecisionResponse -> {
        try {
          HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
          JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
          if(authorizationDecisionResponse.failed() && !scopes.contains("PATCH")) {
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
            List<String> scopes2 = siteRequest.getScopes();
            searchCompanyCourseList(siteRequest, true, false, true).onSuccess(listCompanyCourse -> {
              try {
                ApiRequest apiRequest = new ApiRequest();
                apiRequest.setRows(listCompanyCourse.getRequest().getRows());
                apiRequest.setNumFound(listCompanyCourse.getResponse().getResponse().getNumFound());
                apiRequest.setNumPATCH(0L);
                apiRequest.initDeepApiRequest(siteRequest);
                siteRequest.setApiRequest_(apiRequest);
                if(apiRequest.getNumFound() == 1L)
                  apiRequest.setOriginal(listCompanyCourse.first());
                apiRequest.setId(Optional.ofNullable(listCompanyCourse.first()).map(o2 -> o2.getPageId().toString()).orElse(null));
                eventBus.publish("websocketCompanyCourse", JsonObject.mapFrom(apiRequest).toString());

                listPATCHCompanyCourse(apiRequest, listCompanyCourse).onSuccess(e -> {
                  response200PATCHCompanyCourse(siteRequest).onSuccess(response -> {
                    LOG.debug(String.format("patchCompanyCourse succeeded. "));
                    eventHandler.handle(Future.succeededFuture(response));
                  }).onFailure(ex -> {
                    LOG.error(String.format("patchCompanyCourse failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                }).onFailure(ex -> {
                  LOG.error(String.format("patchCompanyCourse failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              } catch(Exception ex) {
                LOG.error(String.format("patchCompanyCourse failed. "), ex);
                error(siteRequest, eventHandler, ex);
              }
            }).onFailure(ex -> {
              LOG.error(String.format("patchCompanyCourse failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
          }
        } catch(Exception ex) {
          LOG.error(String.format("patchCompanyCourse failed. "), ex);
          error(null, eventHandler, ex);
        }
      });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("patchCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("patchCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPATCHCompanyCourse(ApiRequest apiRequest, SearchList<CompanyCourse> listCompanyCourse) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listCompanyCourse.getSiteRequest_(SiteRequest.class);
    listCompanyCourse.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      CompanyCourse o2 = jsonObject.mapTo(CompanyCourse.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        patchCompanyCourseFuture(o2, false).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listPATCHCompanyCourse failed. "), ex);
          promise1.fail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listCompanyCourse.next().onSuccess(next -> {
        if(next) {
          listPATCHCompanyCourse(apiRequest, listCompanyCourse).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPATCHCompanyCourse failed. "), ex);
            promise.fail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listPATCHCompanyCourse failed. "), ex);
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listPATCHCompanyCourse failed. "), ex);
      promise.fail(ex);
    });
    return promise.future();
  }

  @Override
  public void patchCompanyCourseFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setJsonObject(body);
        serviceRequest.getParams().getJsonObject("query").put("rows", 1);
        Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
          scopes.stream().map(v -> v.toString()).forEach(scope -> {
            siteRequest.addScopes(scope);
          });
        });
        searchCompanyCourseList(siteRequest, false, true, true).onSuccess(listCompanyCourse -> {
          try {
            CompanyCourse o = listCompanyCourse.first();
            ApiRequest apiRequest = new ApiRequest();
            apiRequest.setRows(1L);
            apiRequest.setNumFound(1L);
            apiRequest.setNumPATCH(0L);
            apiRequest.initDeepApiRequest(siteRequest);
            siteRequest.setApiRequest_(apiRequest);
            if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
              siteRequest.getRequestVars().put( "refresh", "false" );
            }
            CompanyCourse o2;
            if(o != null) {
              if(apiRequest.getNumFound() == 1L)
                apiRequest.setOriginal(o);
              apiRequest.setId(Optional.ofNullable(listCompanyCourse.first()).map(o3 -> o3.getPageId().toString()).orElse(null));
              JsonObject jsonObject = JsonObject.mapFrom(o);
              o2 = jsonObject.mapTo(CompanyCourse.class);
              o2.setSiteRequest_(siteRequest);
              patchCompanyCourseFuture(o2, false).onSuccess(o3 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              String m = String.format("%s %s not found", "course", null);
              eventHandler.handle(Future.failedFuture(m));
            }
          } catch(Exception ex) {
            LOG.error(String.format("patchCompanyCourse failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("patchCompanyCourse failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("patchCompanyCourse failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<CompanyCourse> patchCompanyCourseFuture(CompanyCourse o, Boolean inheritPrimaryKey) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<CompanyCourse> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      persistCompanyCourse(o, true).onSuccess(c -> {
        indexCompanyCourse(o).onSuccess(e -> {
          if(apiRequest != null) {
            apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
            if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
              o.apiRequestCompanyCourse();
              if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                eventBus.publish("websocketCompanyCourse", JsonObject.mapFrom(apiRequest).toString());
            }
          }
          promise.complete(o);
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("patchCompanyCourseFuture failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200PATCHCompanyCourse(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      if(json == null) {
        String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
        String m = String.format("%s %s not found", "course", pageId);
        promise.complete(new ServiceResponse(404
            , m
            , Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
      } else {
        promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
      }
    } catch(Exception ex) {
      LOG.error(String.format("response200PATCHCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  // POST //

  @Override
  public void postCompanyCourse(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("postCompanyCourse started. "));
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
      String COMPANYCOURSE = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("COMPANYCOURSE");
      MultiMap form = MultiMap.caseInsensitiveMultiMap();
      form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
      form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
      form.add("response_mode", "permissions");
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "GET"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "POST"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "DELETE"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PATCH"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PUT"));
      if(pageId != null)
        form.add("permission", String.format("%s#%s", pageId, "POST"));
      webClient.post(
          config.getInteger(ComputateConfigKeys.AUTH_PORT)
          , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
          , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
          )
          .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
          .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
          .sendForm(form)
          .expecting(HttpResponseExpectation.SC_OK)
      .onComplete(authorizationDecisionResponse -> {
        try {
          HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
          JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
          if(authorizationDecisionResponse.failed() && !scopes.contains("POST")) {
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
            List<String> scopes2 = siteRequest.getScopes();
            ApiRequest apiRequest = new ApiRequest();
            apiRequest.setRows(1L);
            apiRequest.setNumFound(1L);
            apiRequest.setNumPATCH(0L);
            apiRequest.initDeepApiRequest(siteRequest);
            siteRequest.setApiRequest_(apiRequest);
            eventBus.publish("websocketCompanyCourse", JsonObject.mapFrom(apiRequest).toString());
            JsonObject params = new JsonObject();
            params.put("body", siteRequest.getJsonObject());
            params.put("path", new JsonObject());
            params.put("cookie", siteRequest.getServiceRequest().getParams().getJsonObject("cookie"));
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
            eventBus.request(CompanyCourse.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postCompanyCourseFuture")).onSuccess(a -> {
              JsonObject responseMessage = (JsonObject)a.body();
              JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
              LOG.debug(String.format("postCompanyCourse succeeded. "));
            }).onFailure(ex -> {
              LOG.error(String.format("postCompanyCourse failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
          }
        } catch(Exception ex) {
          LOG.error(String.format("postCompanyCourse failed. "), ex);
          error(null, eventHandler, ex);
        }
      });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("postCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  @Override
  public void postCompanyCourseFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
          scopes.stream().map(v -> v.toString()).forEach(scope -> {
            siteRequest.addScopes(scope);
          });
        });
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setRows(1L);
        apiRequest.setNumFound(1L);
        apiRequest.setNumPATCH(0L);
        apiRequest.initDeepApiRequest(siteRequest);
        siteRequest.setApiRequest_(apiRequest);
        if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
          siteRequest.getRequestVars().put( "refresh", "false" );
        }
        postCompanyCourseFuture(siteRequest, false).onSuccess(o -> {
          eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
        }).onFailure(ex -> {
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Throwable ex) {
        LOG.error(String.format("postCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("postCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<CompanyCourse> postCompanyCourseFuture(SiteRequest siteRequest, Boolean pageId) {
    Promise<CompanyCourse> promise = Promise.promise();

    try {
      createCompanyCourse(siteRequest).onSuccess(companyCourse -> {
        persistCompanyCourse(companyCourse, false).onSuccess(c -> {
          indexCompanyCourse(companyCourse).onSuccess(o2 -> {
            promise.complete(companyCourse);
          }).onFailure(ex -> {
            promise.fail(ex);
          });
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("postCompanyCourseFuture failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200POSTCompanyCourse(CompanyCourse o) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      JsonObject json = JsonObject.mapFrom(o);
      if(json == null) {
        String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
        String m = String.format("%s %s not found", "course", pageId);
        promise.complete(new ServiceResponse(404
            , m
            , Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
      } else {
        promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
      }
    } catch(Exception ex) {
      LOG.error(String.format("response200POSTCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  // DELETE //

  @Override
  public void deleteCompanyCourse(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deleteCompanyCourse started. "));
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
      String COMPANYCOURSE = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("COMPANYCOURSE");
      MultiMap form = MultiMap.caseInsensitiveMultiMap();
      form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
      form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
      form.add("response_mode", "permissions");
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "GET"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "POST"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "DELETE"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PATCH"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PUT"));
      if(pageId != null)
        form.add("permission", String.format("%s#%s", pageId, "DELETE"));
      webClient.post(
          config.getInteger(ComputateConfigKeys.AUTH_PORT)
          , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
          , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
          )
          .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
          .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
          .sendForm(form)
          .expecting(HttpResponseExpectation.SC_OK)
      .onComplete(authorizationDecisionResponse -> {
        try {
          HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
          JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
          if(authorizationDecisionResponse.failed() && !scopes.contains("DELETE")) {
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
            List<String> scopes2 = siteRequest.getScopes();
            searchCompanyCourseList(siteRequest, true, false, true).onSuccess(listCompanyCourse -> {
              try {
                ApiRequest apiRequest = new ApiRequest();
                apiRequest.setRows(listCompanyCourse.getRequest().getRows());
                apiRequest.setNumFound(listCompanyCourse.getResponse().getResponse().getNumFound());
                apiRequest.setNumPATCH(0L);
                apiRequest.initDeepApiRequest(siteRequest);
                siteRequest.setApiRequest_(apiRequest);
                if(apiRequest.getNumFound() == 1L)
                  apiRequest.setOriginal(listCompanyCourse.first());
                eventBus.publish("websocketCompanyCourse", JsonObject.mapFrom(apiRequest).toString());

                listDELETECompanyCourse(apiRequest, listCompanyCourse).onSuccess(e -> {
                  response200DELETECompanyCourse(siteRequest).onSuccess(response -> {
                    LOG.debug(String.format("deleteCompanyCourse succeeded. "));
                    eventHandler.handle(Future.succeededFuture(response));
                  }).onFailure(ex -> {
                    LOG.error(String.format("deleteCompanyCourse failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                }).onFailure(ex -> {
                  LOG.error(String.format("deleteCompanyCourse failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              } catch(Exception ex) {
                LOG.error(String.format("deleteCompanyCourse failed. "), ex);
                error(siteRequest, eventHandler, ex);
              }
            }).onFailure(ex -> {
              LOG.error(String.format("deleteCompanyCourse failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
          }
        } catch(Exception ex) {
          LOG.error(String.format("deleteCompanyCourse failed. "), ex);
          error(null, eventHandler, ex);
        }
      });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deleteCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("deleteCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETECompanyCourse(ApiRequest apiRequest, SearchList<CompanyCourse> listCompanyCourse) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listCompanyCourse.getSiteRequest_(SiteRequest.class);
    listCompanyCourse.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      CompanyCourse o2 = jsonObject.mapTo(CompanyCourse.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deleteCompanyCourseFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETECompanyCourse failed. "), ex);
          promise1.fail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listCompanyCourse.next().onSuccess(next -> {
        if(next) {
          listDELETECompanyCourse(apiRequest, listCompanyCourse).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETECompanyCourse failed. "), ex);
            promise.fail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETECompanyCourse failed. "), ex);
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETECompanyCourse failed. "), ex);
      promise.fail(ex);
    });
    return promise.future();
  }

  @Override
  public void deleteCompanyCourseFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setJsonObject(body);
        serviceRequest.getParams().getJsonObject("query").put("rows", 1);
        Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
          scopes.stream().map(v -> v.toString()).forEach(scope -> {
            siteRequest.addScopes(scope);
          });
        });
        searchCompanyCourseList(siteRequest, false, true, true).onSuccess(listCompanyCourse -> {
          try {
            CompanyCourse o = listCompanyCourse.first();
            if(o != null && listCompanyCourse.getResponse().getResponse().getNumFound() == 1) {
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
              apiRequest.setId(Optional.ofNullable(listCompanyCourse.first()).map(o2 -> o2.getPageId().toString()).orElse(null));
              deleteCompanyCourseFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deleteCompanyCourse failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deleteCompanyCourse failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deleteCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deleteCompanyCourse failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<CompanyCourse> deleteCompanyCourseFuture(CompanyCourse o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<CompanyCourse> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      unindexCompanyCourse(o).onSuccess(e -> {
        promise.complete(o);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deleteCompanyCourseFuture failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETECompanyCourse(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      if(json == null) {
        String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
        String m = String.format("%s %s not found", "course", pageId);
        promise.complete(new ServiceResponse(404
            , m
            , Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
      } else {
        promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
      }
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETECompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  // PUTImport //

  @Override
  public void putimportCompanyCourse(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("putimportCompanyCourse started. "));
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
      String COMPANYCOURSE = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("COMPANYCOURSE");
      MultiMap form = MultiMap.caseInsensitiveMultiMap();
      form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
      form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
      form.add("response_mode", "permissions");
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "GET"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "POST"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "DELETE"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PATCH"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PUT"));
      if(pageId != null)
        form.add("permission", String.format("%s#%s", pageId, "PUT"));
      webClient.post(
          config.getInteger(ComputateConfigKeys.AUTH_PORT)
          , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
          , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
          )
          .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
          .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
          .sendForm(form)
          .expecting(HttpResponseExpectation.SC_OK)
      .onComplete(authorizationDecisionResponse -> {
        try {
          HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
          JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
          if(authorizationDecisionResponse.failed() && !scopes.contains("PUT")) {
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
            List<String> scopes2 = siteRequest.getScopes();
            ApiRequest apiRequest = new ApiRequest();
            JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
            apiRequest.setRows(Long.valueOf(jsonArray.size()));
            apiRequest.setNumFound(Long.valueOf(jsonArray.size()));
            apiRequest.setNumPATCH(0L);
            apiRequest.initDeepApiRequest(siteRequest);
            siteRequest.setApiRequest_(apiRequest);
            eventBus.publish("websocketCompanyCourse", JsonObject.mapFrom(apiRequest).toString());
            varsCompanyCourse(siteRequest).onSuccess(d -> {
              listPUTImportCompanyCourse(apiRequest, siteRequest).onSuccess(e -> {
                response200PUTImportCompanyCourse(siteRequest).onSuccess(response -> {
                  LOG.debug(String.format("putimportCompanyCourse succeeded. "));
                  eventHandler.handle(Future.succeededFuture(response));
                }).onFailure(ex -> {
                  LOG.error(String.format("putimportCompanyCourse failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("putimportCompanyCourse failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }).onFailure(ex -> {
              LOG.error(String.format("putimportCompanyCourse failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
          }
        } catch(Exception ex) {
          LOG.error(String.format("putimportCompanyCourse failed. "), ex);
          error(null, eventHandler, ex);
        }
      });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("putimportCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPUTImportCompanyCourse(ApiRequest apiRequest, SiteRequest siteRequest) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
    try {
      jsonArray.forEach(obj -> {
        futures.add(Future.future(promise1 -> {
          JsonObject params = new JsonObject();
          params.put("body", obj);
          params.put("path", new JsonObject());
          params.put("cookie", siteRequest.getServiceRequest().getParams().getJsonObject("cookie"));
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
          eventBus.request(CompanyCourse.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportCompanyCourseFuture")).onSuccess(a -> {
            promise1.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPUTImportCompanyCourse failed. "), ex);
            promise1.fail(ex);
          });
        }));
      });
      CompositeFuture.all(futures).onSuccess(a -> {
        apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("listPUTImportCompanyCourse failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("listPUTImportCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public void putimportCompanyCourseFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
          scopes.stream().map(v -> v.toString()).forEach(scope -> {
            siteRequest.addScopes(scope);
          });
        });
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setRows(1L);
        apiRequest.setNumFound(1L);
        apiRequest.setNumPATCH(0L);
        apiRequest.initDeepApiRequest(siteRequest);
        siteRequest.setApiRequest_(apiRequest);
        String pageId = Optional.ofNullable(body.getString(CompanyCourse.VAR_pageId)).orElse(body.getString(CompanyCourse.VAR_solrId));
        if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
          siteRequest.getRequestVars().put( "refresh", "false" );
        }

        SearchList<CompanyCourse> searchList = new SearchList<CompanyCourse>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(CompanyCourse.class);
        searchList.fq("archived_docvalues_boolean:false");
        searchList.fq("pageId_docvalues_string:" + SearchTool.escapeQueryChars(pageId));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            if(searchList.size() >= 1) {
              CompanyCourse o = searchList.getList().stream().findFirst().orElse(null);
              CompanyCourse o2 = new CompanyCourse();
              o2.setSiteRequest_(siteRequest);
              JsonObject body2 = new JsonObject();
              for(String f : body.fieldNames()) {
                Object bodyVal = body.getValue(f);
                if(bodyVal instanceof JsonArray) {
                  JsonArray bodyVals = (JsonArray)bodyVal;
                  Object valsObj = o.obtainForClass(f);
                  Collection<?> vals = valsObj instanceof JsonArray ? ((JsonArray)valsObj).getList() : (Collection<?>)valsObj;
                  if(vals != null && bodyVals.size() == vals.size()) {
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
                    if(vals != null)
                      vals.clear();
                    body2.put("set" + StringUtils.capitalize(f), bodyVal);
                  }
                } else {
                  o2.persistForClass(f, bodyVal);
                  o2.relateForClass(f, bodyVal);
                  if(!StringUtils.containsAny(f, "pageId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                    body2.put("set" + StringUtils.capitalize(f), bodyVal);
                }
              }
              for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
                if(!body.fieldNames().contains(f)) {
                  if(!StringUtils.containsAny(f, "pageId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                    body2.putNull("set" + StringUtils.capitalize(f));
                }
              }
              if(searchList.size() == 1) {
                apiRequest.setOriginal(o);
                apiRequest.setId(Optional.ofNullable(o.getPageId()).map(v -> v.toString()).orElse(null));
              }
              siteRequest.setJsonObject(body2);
              patchCompanyCourseFuture(o2, true).onSuccess(b -> {
                LOG.debug("Import CompanyCourse {} succeeded, modified CompanyCourse. ", body.getValue(CompanyCourse.VAR_pageId));
                eventHandler.handle(Future.succeededFuture());
              }).onFailure(ex -> {
                LOG.error(String.format("putimportCompanyCourseFuture failed. "), ex);
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              postCompanyCourseFuture(siteRequest, true).onSuccess(b -> {
                LOG.debug("Import CompanyCourse {} succeeded, created new CompanyCourse. ", body.getValue(CompanyCourse.VAR_pageId));
                eventHandler.handle(Future.succeededFuture());
              }).onFailure(ex -> {
                LOG.error(String.format("putimportCompanyCourseFuture failed. "), ex);
                eventHandler.handle(Future.failedFuture(ex));
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("putimportCompanyCourseFuture failed. "), ex);
            eventHandler.handle(Future.failedFuture(ex));
          }
        }).onFailure(ex -> {
          LOG.error(String.format("putimportCompanyCourseFuture failed. "), ex);
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Exception ex) {
        LOG.error(String.format("putimportCompanyCourseFuture failed. "), ex);
        eventHandler.handle(Future.failedFuture(ex));
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("putimportCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200PUTImportCompanyCourse(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      if(json == null) {
        String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
        String m = String.format("%s %s not found", "course", pageId);
        promise.complete(new ServiceResponse(404
            , m
            , Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
      } else {
        promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
      }
    } catch(Exception ex) {
      LOG.error(String.format("response200PUTImportCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  // SearchPage //

  @Override
  public void searchpageCompanyCourse(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
            searchCompanyCourseList(siteRequest, false, true, false).onSuccess(listCompanyCourse -> {
              response200SearchPageCompanyCourse(listCompanyCourse).onSuccess(response -> {
                eventHandler.handle(Future.succeededFuture(response));
                LOG.debug(String.format("searchpageCompanyCourse succeeded. "));
              }).onFailure(ex -> {
                LOG.error(String.format("searchpageCompanyCourse failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }).onFailure(ex -> {
              LOG.error(String.format("searchpageCompanyCourse failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchpageCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("searchpageCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void searchpageCompanyCoursePageInit(JsonObject ctx, CompanyCoursePage page, SearchList<CompanyCourse> listCompanyCourse, Promise<Void> promise) {
    promise.complete();
  }

  public String templateSearchPageCompanyCourse(ServiceRequest serviceRequest) {
    return "en-us/search/course/CompanyCourseSearchPage.htm";
  }
  public Future<ServiceResponse> response200SearchPageCompanyCourse(SearchList<CompanyCourse> listCompanyCourse) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listCompanyCourse.getSiteRequest_(SiteRequest.class);
      String pageTemplateUri = templateSearchPageCompanyCourse(siteRequest.getServiceRequest());
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
      CompanyCoursePage page = new CompanyCoursePage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      page.setSearchListCompanyCourse_(listCompanyCourse);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepCompanyCoursePage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          searchpageCompanyCoursePageInit(ctx, page, listCompanyCourse, promise1);
          promise1.future().onSuccess(b -> {
            String renderedTemplate = jinjava.render(template, ctx.getMap());
            Buffer buffer = Buffer.buffer(renderedTemplate);
            promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
          }).onFailure(ex -> {
            promise.fail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200SearchPageCompanyCourse failed. "), ex);
          promise.fail(ex);
        }
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchPageCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchPageCompanyCourse(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotSearchPageCompanyCourse(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // EditPage //

  @Override
  public void editpageCompanyCourse(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
      String COMPANYCOURSE = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("COMPANYCOURSE");
      MultiMap form = MultiMap.caseInsensitiveMultiMap();
      form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
      form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
      form.add("response_mode", "permissions");
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "GET"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "POST"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "DELETE"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PATCH"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PUT"));
      form.add("permission", String.format("%s-%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, pageId, "GET"));
      if(pageId != null)
        form.add("permission", String.format("%s#%s", pageId, "GET"));
      webClient.post(
          config.getInteger(ComputateConfigKeys.AUTH_PORT)
          , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
          , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
          )
          .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
          .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
          .sendForm(form)
          .expecting(HttpResponseExpectation.SC_OK)
      .onComplete(authorizationDecisionResponse -> {
        try {
          HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
          JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
          {
            siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
            List<String> scopes2 = siteRequest.getScopes();
            searchCompanyCourseList(siteRequest, false, true, false).onSuccess(listCompanyCourse -> {
              response200EditPageCompanyCourse(listCompanyCourse).onSuccess(response -> {
                eventHandler.handle(Future.succeededFuture(response));
                LOG.debug(String.format("editpageCompanyCourse succeeded. "));
              }).onFailure(ex -> {
                LOG.error(String.format("editpageCompanyCourse failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }).onFailure(ex -> {
              LOG.error(String.format("editpageCompanyCourse failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
          }
        } catch(Exception ex) {
          LOG.error(String.format("editpageCompanyCourse failed. "), ex);
          error(null, eventHandler, ex);
        }
      });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("editpageCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("editpageCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void editpageCompanyCoursePageInit(JsonObject ctx, CompanyCoursePage page, SearchList<CompanyCourse> listCompanyCourse, Promise<Void> promise) {
    promise.complete();
  }

  public String templateEditPageCompanyCourse(ServiceRequest serviceRequest) {
    return "en-us/edit/course/CompanyCourseEditPage.htm";
  }
  public Future<ServiceResponse> response200EditPageCompanyCourse(SearchList<CompanyCourse> listCompanyCourse) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listCompanyCourse.getSiteRequest_(SiteRequest.class);
      String pageTemplateUri = templateEditPageCompanyCourse(siteRequest.getServiceRequest());
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
      CompanyCoursePage page = new CompanyCoursePage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      page.setSearchListCompanyCourse_(listCompanyCourse);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepCompanyCoursePage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          editpageCompanyCoursePageInit(ctx, page, listCompanyCourse, promise1);
          promise1.future().onSuccess(b -> {
            String renderedTemplate = jinjava.render(template, ctx.getMap());
            Buffer buffer = Buffer.buffer(renderedTemplate);
            promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
          }).onFailure(ex -> {
            promise.fail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200EditPageCompanyCourse failed. "), ex);
          promise.fail(ex);
        }
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200EditPageCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }
  public void responsePivotEditPageCompanyCourse(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotEditPageCompanyCourse(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // DisplayPage //

  @Override
  public void displaypageCompanyCourse(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
            searchCompanyCourseList(siteRequest, false, true, false).onSuccess(listCompanyCourse -> {
              response200DisplayPageCompanyCourse(listCompanyCourse).onSuccess(response -> {
                eventHandler.handle(Future.succeededFuture(response));
                LOG.debug(String.format("displaypageCompanyCourse succeeded. "));
              }).onFailure(ex -> {
                LOG.error(String.format("displaypageCompanyCourse failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }).onFailure(ex -> {
              LOG.error(String.format("displaypageCompanyCourse failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("displaypageCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("displaypageCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void displaypageCompanyCoursePageInit(JsonObject ctx, CompanyCoursePage page, SearchList<CompanyCourse> listCompanyCourse, Promise<Void> promise) {
    promise.complete();
  }

  public String templateDisplayPageCompanyCourse(ServiceRequest serviceRequest) {
    return String.format("%s.htm", StringUtils.substringBefore(serviceRequest.getExtra().getString("uri").substring(1), "?"));
  }
  public Future<ServiceResponse> response200DisplayPageCompanyCourse(SearchList<CompanyCourse> listCompanyCourse) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listCompanyCourse.getSiteRequest_(SiteRequest.class);
      String pageTemplateUri = templateDisplayPageCompanyCourse(siteRequest.getServiceRequest());
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
      CompanyCoursePage page = new CompanyCoursePage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      page.setSearchListCompanyCourse_(listCompanyCourse);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepCompanyCoursePage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          displaypageCompanyCoursePageInit(ctx, page, listCompanyCourse, promise1);
          promise1.future().onSuccess(b -> {
            String renderedTemplate = jinjava.render(template, ctx.getMap());
            Buffer buffer = Buffer.buffer(renderedTemplate);
            promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
          }).onFailure(ex -> {
            promise.fail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200DisplayPageCompanyCourse failed. "), ex);
          promise.fail(ex);
        }
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200DisplayPageCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }
  public void responsePivotDisplayPageCompanyCourse(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotDisplayPageCompanyCourse(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // UserPage //

  @Override
  public void userpageCompanyCourse(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
      String COMPANYCOURSE = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("COMPANYCOURSE");
      MultiMap form = MultiMap.caseInsensitiveMultiMap();
      form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
      form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
      form.add("response_mode", "permissions");
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "GET"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "POST"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "DELETE"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PATCH"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PUT"));
      form.add("permission", String.format("%s-%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, pageId, "GET"));
      if(pageId != null)
        form.add("permission", String.format("%s#%s", pageId, "GET"));
      webClient.post(
          config.getInteger(ComputateConfigKeys.AUTH_PORT)
          , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
          , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
          )
          .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
          .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
          .sendForm(form)
          .expecting(HttpResponseExpectation.SC_OK)
      .onComplete(authorizationDecisionResponse -> {
        try {
          HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
          JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
          {
            siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
            List<String> scopes2 = siteRequest.getScopes();
            searchCompanyCourseList(siteRequest, false, true, false).onSuccess(listCompanyCourse -> {
              response200UserPageCompanyCourse(listCompanyCourse).onSuccess(response -> {
                eventHandler.handle(Future.succeededFuture(response));
                LOG.debug(String.format("userpageCompanyCourse succeeded. "));
              }).onFailure(ex -> {
                LOG.error(String.format("userpageCompanyCourse failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }).onFailure(ex -> {
              LOG.error(String.format("userpageCompanyCourse failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
          }
        } catch(Exception ex) {
          LOG.error(String.format("userpageCompanyCourse failed. "), ex);
          error(null, eventHandler, ex);
        }
      });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("userpageCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("userpageCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void userpageCompanyCoursePageInit(JsonObject ctx, CompanyCoursePage page, SearchList<CompanyCourse> listCompanyCourse, Promise<Void> promise) {
    promise.complete();
  }

  public String templateUserPageCompanyCourse(ServiceRequest serviceRequest) {
    return String.format("%s.htm", StringUtils.substringBefore(serviceRequest.getExtra().getString("uri").substring(1), "?"));
  }
  public Future<ServiceResponse> response200UserPageCompanyCourse(SearchList<CompanyCourse> listCompanyCourse) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listCompanyCourse.getSiteRequest_(SiteRequest.class);
      String pageTemplateUri = templateUserPageCompanyCourse(siteRequest.getServiceRequest());
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
      CompanyCoursePage page = new CompanyCoursePage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      page.setSearchListCompanyCourse_(listCompanyCourse);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepCompanyCoursePage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          userpageCompanyCoursePageInit(ctx, page, listCompanyCourse, promise1);
          promise1.future().onSuccess(b -> {
            String renderedTemplate = jinjava.render(template, ctx.getMap());
            Buffer buffer = Buffer.buffer(renderedTemplate);
            promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
          }).onFailure(ex -> {
            promise.fail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200UserPageCompanyCourse failed. "), ex);
          promise.fail(ex);
        }
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200UserPageCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }
  public void responsePivotUserPageCompanyCourse(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotUserPageCompanyCourse(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // DELETEFilter //

  @Override
  public void deletefilterCompanyCourse(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deletefilterCompanyCourse started. "));
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
      String COMPANYCOURSE = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("COMPANYCOURSE");
      MultiMap form = MultiMap.caseInsensitiveMultiMap();
      form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
      form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
      form.add("response_mode", "permissions");
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, config.getString(ComputateConfigKeys.AUTH_SCOPE_SUPER_ADMIN)));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "GET"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "POST"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "DELETE"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PATCH"));
      form.add("permission", String.format("%s#%s", CompanyCourse.CLASS_AUTH_RESOURCE, "PUT"));
      if(pageId != null)
        form.add("permission", String.format("%s#%s", pageId, "DELETE"));
      webClient.post(
          config.getInteger(ComputateConfigKeys.AUTH_PORT)
          , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
          , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
          )
          .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
          .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
          .sendForm(form)
          .expecting(HttpResponseExpectation.SC_OK)
      .onComplete(authorizationDecisionResponse -> {
        try {
          HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
          JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
          if(authorizationDecisionResponse.failed() && !scopes.contains("DELETE")) {
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
            List<String> scopes2 = siteRequest.getScopes();
            searchCompanyCourseList(siteRequest, true, false, true).onSuccess(listCompanyCourse -> {
              try {
                ApiRequest apiRequest = new ApiRequest();
                apiRequest.setRows(listCompanyCourse.getRequest().getRows());
                apiRequest.setNumFound(listCompanyCourse.getResponse().getResponse().getNumFound());
                apiRequest.setNumPATCH(0L);
                apiRequest.initDeepApiRequest(siteRequest);
                siteRequest.setApiRequest_(apiRequest);
                if(apiRequest.getNumFound() == 1L)
                  apiRequest.setOriginal(listCompanyCourse.first());
                eventBus.publish("websocketCompanyCourse", JsonObject.mapFrom(apiRequest).toString());

                listDELETEFilterCompanyCourse(apiRequest, listCompanyCourse).onSuccess(e -> {
                  response200DELETEFilterCompanyCourse(siteRequest).onSuccess(response -> {
                    LOG.debug(String.format("deletefilterCompanyCourse succeeded. "));
                    eventHandler.handle(Future.succeededFuture(response));
                  }).onFailure(ex -> {
                    LOG.error(String.format("deletefilterCompanyCourse failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                }).onFailure(ex -> {
                  LOG.error(String.format("deletefilterCompanyCourse failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              } catch(Exception ex) {
                LOG.error(String.format("deletefilterCompanyCourse failed. "), ex);
                error(siteRequest, eventHandler, ex);
              }
            }).onFailure(ex -> {
              LOG.error(String.format("deletefilterCompanyCourse failed. "), ex);
              error(siteRequest, eventHandler, ex);
            });
          }
        } catch(Exception ex) {
          LOG.error(String.format("deletefilterCompanyCourse failed. "), ex);
          error(null, eventHandler, ex);
        }
      });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deletefilterCompanyCourse failed. ", ex2));
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
        LOG.error(String.format("deletefilterCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETEFilterCompanyCourse(ApiRequest apiRequest, SearchList<CompanyCourse> listCompanyCourse) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listCompanyCourse.getSiteRequest_(SiteRequest.class);
    listCompanyCourse.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      CompanyCourse o2 = jsonObject.mapTo(CompanyCourse.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deletefilterCompanyCourseFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETEFilterCompanyCourse failed. "), ex);
          promise1.fail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listCompanyCourse.next().onSuccess(next -> {
        if(next) {
          listDELETEFilterCompanyCourse(apiRequest, listCompanyCourse).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETEFilterCompanyCourse failed. "), ex);
            promise.fail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETEFilterCompanyCourse failed. "), ex);
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETEFilterCompanyCourse failed. "), ex);
      promise.fail(ex);
    });
    return promise.future();
  }

  @Override
  public void deletefilterCompanyCourseFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = true;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setJsonObject(body);
        serviceRequest.getParams().getJsonObject("query").put("rows", 1);
        Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
          scopes.stream().map(v -> v.toString()).forEach(scope -> {
            siteRequest.addScopes(scope);
          });
        });
        searchCompanyCourseList(siteRequest, false, true, true).onSuccess(listCompanyCourse -> {
          try {
            CompanyCourse o = listCompanyCourse.first();
            if(o != null && listCompanyCourse.getResponse().getResponse().getNumFound() == 1) {
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
              apiRequest.setId(Optional.ofNullable(listCompanyCourse.first()).map(o2 -> o2.getPageId().toString()).orElse(null));
              deletefilterCompanyCourseFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deletefilterCompanyCourse failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deletefilterCompanyCourse failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deletefilterCompanyCourse failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deletefilterCompanyCourse failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<CompanyCourse> deletefilterCompanyCourseFuture(CompanyCourse o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<CompanyCourse> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      unindexCompanyCourse(o).onSuccess(e -> {
        promise.complete(o);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deletefilterCompanyCourseFuture failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETEFilterCompanyCourse(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      if(json == null) {
        String pageId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("pageId");
        String m = String.format("%s %s not found", "course", pageId);
        promise.complete(new ServiceResponse(404
            , m
            , Buffer.buffer(new JsonObject().put("message", m).encodePrettily()), null));
      } else {
        promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
      }
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETEFilterCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  // General //

  public Future<CompanyCourse> createCompanyCourse(SiteRequest siteRequest) {
    Promise<CompanyCourse> promise = Promise.promise();
    try {
      CompanyCourse o = new CompanyCourse();
      o.setSiteRequest_(siteRequest);
      promise.complete(o);
    } catch(Exception ex) {
      LOG.error(String.format("createCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public void searchCompanyCourseQ(SearchList<CompanyCourse> searchList, String entityVar, String valueIndexed, String varIndexed) {
    searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
    if(!"*".equals(entityVar)) {
    }
  }

  public String searchCompanyCourseFq(SearchList<CompanyCourse> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    if(StringUtils.startsWith(valueIndexed, "[")) {
      String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
      if(fqs.length != 2)
        throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
      String fq1 = fqs[0].equals("*") ? fqs[0] : CompanyCourse.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
      String fq2 = fqs[1].equals("*") ? fqs[1] : CompanyCourse.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
       return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
    } else {
      return varIndexed + ":" + SearchTool.escapeQueryChars(CompanyCourse.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
    }
  }

  public void searchCompanyCourseSort(SearchList<CompanyCourse> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    searchList.sort(varIndexed, valueIndexed);
  }

  public void searchCompanyCourseRows(SearchList<CompanyCourse> searchList, Long valueRows) {
      searchList.rows(valueRows != null ? valueRows : 10L);
  }

  public void searchCompanyCourseStart(SearchList<CompanyCourse> searchList, Long valueStart) {
    searchList.start(valueStart);
  }

  public void searchCompanyCourseVar(SearchList<CompanyCourse> searchList, String var, String value) {
    searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
  }

  public void searchCompanyCourseUri(SearchList<CompanyCourse> searchList) {
  }

  public Future<ServiceResponse> varsCompanyCourse(SiteRequest siteRequest) {
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
          LOG.error(String.format("searchCompanyCourse failed. "), ex);
          promise.fail(ex);
        }
      });
      promise.complete();
    } catch(Exception ex) {
      LOG.error(String.format("searchCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<SearchList<CompanyCourse>> searchCompanyCourseList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify) {
    Promise<SearchList<CompanyCourse>> promise = Promise.promise();
    try {
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
      String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
      SearchList<CompanyCourse> searchList = new SearchList<CompanyCourse>();
      String facetRange = null;
      Date facetRangeStart = null;
      Date facetRangeEnd = null;
      String facetRangeGap = null;
      String statsField = null;
      String statsFieldIndexed = null;
      searchList.setPopulate(populate);
      searchList.setStore(store);
      searchList.q("*:*");
      searchList.setC(CompanyCourse.class);
      searchList.setSiteRequest_(siteRequest);
      searchList.facetMinCount(1);
      if(entityList != null) {
        for(String v : entityList) {
          searchList.fl(CompanyCourse.varIndexedCompanyCourse(v));
        }
      }

      String pageId = serviceRequest.getParams().getJsonObject("path").getString("pageId");
      if(pageId != null) {
        searchList.fq("pageId_docvalues_string:" + SearchTool.escapeQueryChars(pageId));
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
            if(mFacetPivot.find()) {
              String solrLocalParams = mFacetPivot.group(1);
              String[] entityVars = mFacetPivot.group(2).trim().split(",");
              String[] varsIndexed = new String[entityVars.length];
              for(Integer i = 0; i < entityVars.length; i++) {
                entityVar = entityVars[i];
                varsIndexed[i] = CompanyCourse.varIndexedCompanyCourse(entityVar);
              }
              searchList.facetPivot((solrLocalParams == null ? "" : solrLocalParams) + StringUtils.join(varsIndexed, ","));
            }
          } else if(paramValuesObject != null) {
            for(Object paramObject : paramObjects) {
              if(paramName.equals("q")) {
                Matcher mQ = Pattern.compile("(\\w+):(.+?(?=(\\)|\\s+OR\\s+|\\s+AND\\s+|\\^|$)))").matcher((String)paramObject);
                StringBuffer sb = new StringBuffer();
                while(mQ.find()) {
                  entityVar = mQ.group(1).trim();
                  valueIndexed = mQ.group(2).trim();
                  varIndexed = CompanyCourse.varIndexedCompanyCourse(entityVar);
                  String entityQ = searchCompanyCourseFq(searchList, entityVar, valueIndexed, varIndexed);
                  mQ.appendReplacement(sb, entityQ);
                }
                if(!sb.isEmpty()) {
                  mQ.appendTail(sb);
                  searchList.q(sb.toString());
                }
              } else if(paramName.equals("fq")) {
                Matcher mFq = Pattern.compile("(\\w+):(.+?(?=(\\)|\\s+OR\\s+|\\s+AND\\s+|$)))").matcher((String)paramObject);
                  StringBuffer sb = new StringBuffer();
                while(mFq.find()) {
                  entityVar = mFq.group(1).trim();
                  valueIndexed = mFq.group(2).trim();
                  varIndexed = CompanyCourse.varIndexedCompanyCourse(entityVar);
                  String entityFq = searchCompanyCourseFq(searchList, entityVar, valueIndexed, varIndexed);
                  mFq.appendReplacement(sb, entityFq);
                }
                if(!sb.isEmpty()) {
                  mFq.appendTail(sb);
                  searchList.fq(sb.toString());
                }
              } else if(paramName.equals("sort")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
                valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
                varIndexed = CompanyCourse.varIndexedCompanyCourse(entityVar);
                searchCompanyCourseSort(searchList, entityVar, valueIndexed, varIndexed);
              } else if(paramName.equals("start")) {
                valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchCompanyCourseStart(searchList, valueStart);
              } else if(paramName.equals("rows")) {
                valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchCompanyCourseRows(searchList, valueRows);
              } else if(paramName.equals("stats")) {
                searchList.stats((Boolean)paramObject);
              } else if(paramName.equals("stats.field")) {
                Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
                if(mStats.find()) {
                  String solrLocalParams = mStats.group(1);
                  entityVar = mStats.group(2).trim();
                  varIndexed = CompanyCourse.varIndexedCompanyCourse(entityVar);
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
                if(mFacetRange.find()) {
                  String solrLocalParams = mFacetRange.group(1);
                  entityVar = mFacetRange.group(2).trim();
                  varIndexed = CompanyCourse.varIndexedCompanyCourse(entityVar);
                  searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
                  facetRange = entityVar;
                }
              } else if(paramName.equals("facet.field")) {
                entityVar = (String)paramObject;
                varIndexed = CompanyCourse.varIndexedCompanyCourse(entityVar);
                if(varIndexed != null)
                  searchList.facetField(varIndexed);
              } else if(paramName.equals("var")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
                valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
                searchCompanyCourseVar(searchList, entityVar, valueIndexed);
              } else if(paramName.equals("cursorMark")) {
                valueCursorMark = (String)paramObject;
                searchList.cursorMark((String)paramObject);
              }
            }
            searchCompanyCourseUri(searchList);
          }
        } catch(Exception e) {
          ExceptionUtils.rethrow(e);
        }
      }
      if("*:*".equals(searchList.getQuery()) && searchList.getSorts().size() == 0) {
        searchList.sort("courseNum_docvalues_int", "asc");
        searchList.setDefaultSort(true);
      }
      String facetRange2 = facetRange;
      Date facetRangeStart2 = facetRangeStart;
      Date facetRangeEnd2 = facetRangeEnd;
      String facetRangeGap2 = facetRangeGap;
      String statsField2 = statsField;
      String statsFieldIndexed2 = statsFieldIndexed;
      searchCompanyCourse2(siteRequest, populate, store, modify, searchList);
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
          String gap = "HOUR";
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
            LOG.error(String.format("searchCompanyCourse failed. "), ex);
            promise.fail(ex);
          });
        } else {
          promise.complete(searchList);
        }
      }).onFailure(ex -> {
        LOG.error(String.format("searchCompanyCourse failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("searchCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }
  public void searchCompanyCourse2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<CompanyCourse> searchList) {
  }

  public Future<Void> persistCompanyCourse(CompanyCourse o, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
        try {
          JsonObject jsonObject = siteRequest.getJsonObject();
          jsonObject.forEach(definition -> {
              String columnName;
              Object columnValue;
            if(patch && StringUtils.startsWith(definition.getKey(), "set")) {
              columnName = StringUtils.uncapitalize(StringUtils.substringAfter(definition.getKey(), "set"));
              columnValue = definition.getValue();
            } else {
              columnName = definition.getKey();
              columnValue = definition.getValue();
            }
            if(!"".equals(columnName)) {
              try {
                o.persistForClass(columnName, columnValue);
              } catch(Exception e) {
                LOG.error(String.format("persistCompanyCourse failed. "), e);
              }
            }
          });
          o.promiseDeepForClass(siteRequest).onSuccess(a -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("persistCompanyCourse failed. "), ex);
            promise.fail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("persistCompanyCourse failed. "), ex);
          promise.fail(ex);
        }
    } catch(Exception ex) {
      LOG.error(String.format("persistCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public String searchVar(String varIndexed) {
    return CompanyCourse.searchVarCompanyCourse(varIndexed);
  }

  @Override
  public String getClassApiAddress() {
    return CompanyCourse.CLASS_API_ADDRESS_CompanyCourse;
  }

  public Future<CompanyCourse> indexCompanyCourse(CompanyCourse o) {
    Promise<CompanyCourse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      JsonObject json = new JsonObject();
      JsonObject add = new JsonObject();
      json.put("add", add);
      JsonObject doc = new JsonObject();
      add.put("doc", doc);
      o.indexCompanyCourse(doc);
      String solrUsername = siteRequest.getConfig().getString(ConfigKeys.SOLR_USERNAME);
      String solrPassword = siteRequest.getConfig().getString(ConfigKeys.SOLR_PASSWORD);
      String solrHostName = siteRequest.getConfig().getString(ConfigKeys.SOLR_HOST_NAME);
      Integer solrPort = Integer.parseInt(siteRequest.getConfig().getString(ConfigKeys.SOLR_PORT));
      String solrCollection = siteRequest.getConfig().getString(ConfigKeys.SOLR_COLLECTION);
      Boolean solrSsl = Boolean.parseBoolean(siteRequest.getConfig().getString(ConfigKeys.SOLR_SSL));
      Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
      Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
        if(softCommit == null && commitWithin == null)
          softCommit = true;
        else if(softCommit == null)
          softCommit = false;
      String solrRequestUri = String.format("/solr/%s/update%s%s%s", solrCollection, "?overwrite=true&wt=json", softCommit ? "&softCommit=true" : "", commitWithin != null ? ("&commitWithin=" + commitWithin) : "");
      webClient.post(solrPort, solrHostName, solrRequestUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).putHeader("Content-Type", "application/json").sendBuffer(json.toBuffer()).expecting(HttpResponseExpectation.SC_OK).onSuccess(b -> {
        promise.complete(o);
      }).onFailure(ex -> {
        LOG.error(String.format("indexCompanyCourse failed. "), new RuntimeException(ex));
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("indexCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<CompanyCourse> unindexCompanyCourse(CompanyCourse o) {
    Promise<CompanyCourse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      o.promiseDeepForClass(siteRequest).onSuccess(a -> {
        JsonObject json = new JsonObject();
        JsonObject delete = new JsonObject();
        json.put("delete", delete);
        String query = String.format("filter(%s:%s)", CompanyCourse.VAR_solrId, o.obtainForClass(CompanyCourse.VAR_solrId));
        delete.put("query", query);
        String solrUsername = siteRequest.getConfig().getString(ConfigKeys.SOLR_USERNAME);
        String solrPassword = siteRequest.getConfig().getString(ConfigKeys.SOLR_PASSWORD);
        String solrHostName = siteRequest.getConfig().getString(ConfigKeys.SOLR_HOST_NAME);
        Integer solrPort = Integer.parseInt(siteRequest.getConfig().getString(ConfigKeys.SOLR_PORT));
        String solrCollection = siteRequest.getConfig().getString(ConfigKeys.SOLR_COLLECTION);
        Boolean solrSsl = Boolean.parseBoolean(siteRequest.getConfig().getString(ConfigKeys.SOLR_SSL));
        Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
        Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
          if(softCommit == null && commitWithin == null)
            softCommit = true;
          else if(softCommit == null)
            softCommit = false;
        String solrRequestUri = String.format("/solr/%s/update%s%s%s", solrCollection, "?overwrite=true&wt=json", softCommit ? "&softCommit=true" : "", commitWithin != null ? ("&commitWithin=" + commitWithin) : "");
        webClient.post(solrPort, solrHostName, solrRequestUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).putHeader("Content-Type", "application/json").sendBuffer(json.toBuffer()).expecting(HttpResponseExpectation.SC_OK).onSuccess(b -> {
          promise.complete(o);
        }).onFailure(ex -> {
          LOG.error(String.format("unindexCompanyCourse failed. "), new RuntimeException(ex));
          promise.fail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("unindexCompanyCourse failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("unindexCompanyCourse failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<JsonObject> generatePageBody(ComputateSiteRequest siteRequest, Map<String, Object> ctx, String templatePath, String classSimpleName) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      Map<String, Object> result = (Map<String, Object>)ctx.get("result");
      SiteRequest siteRequest2 = (SiteRequest)siteRequest;
      String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);
      CompanyCourse page = new CompanyCourse();
      page.setSiteRequest_((SiteRequest)siteRequest);

      page.persistForClass(CompanyCourse.VAR_name, CompanyCourse.staticSetName(siteRequest2, (String)result.get(CompanyCourse.VAR_name)));
      page.persistForClass(CompanyCourse.VAR_created, CompanyCourse.staticSetCreated(siteRequest2, (String)result.get(CompanyCourse.VAR_created), Optional.ofNullable(siteRequest).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))));
      page.persistForClass(CompanyCourse.VAR_description, CompanyCourse.staticSetDescription(siteRequest2, (String)result.get(CompanyCourse.VAR_description)));
      page.persistForClass(CompanyCourse.VAR_price, CompanyCourse.staticSetPrice(siteRequest2, (String)result.get(CompanyCourse.VAR_price)));
      page.persistForClass(CompanyCourse.VAR_archived, CompanyCourse.staticSetArchived(siteRequest2, (String)result.get(CompanyCourse.VAR_archived)));
      page.persistForClass(CompanyCourse.VAR_pageId, CompanyCourse.staticSetPageId(siteRequest2, (String)result.get(CompanyCourse.VAR_pageId)));
      page.persistForClass(CompanyCourse.VAR_emailTemplate, CompanyCourse.staticSetEmailTemplate(siteRequest2, (String)result.get(CompanyCourse.VAR_emailTemplate)));
      page.persistForClass(CompanyCourse.VAR_storeUrl, CompanyCourse.staticSetStoreUrl(siteRequest2, (String)result.get(CompanyCourse.VAR_storeUrl)));
      page.persistForClass(CompanyCourse.VAR_downloadUri, CompanyCourse.staticSetDownloadUri(siteRequest2, (String)result.get(CompanyCourse.VAR_downloadUri)));
      page.persistForClass(CompanyCourse.VAR_courseNum, CompanyCourse.staticSetCourseNum(siteRequest2, (String)result.get(CompanyCourse.VAR_courseNum)));
      page.persistForClass(CompanyCourse.VAR_objectTitle, CompanyCourse.staticSetObjectTitle(siteRequest2, (String)result.get(CompanyCourse.VAR_objectTitle)));
      page.persistForClass(CompanyCourse.VAR_pageImageUri, CompanyCourse.staticSetPageImageUri(siteRequest2, (String)result.get(CompanyCourse.VAR_pageImageUri)));
      page.persistForClass(CompanyCourse.VAR_displayPage, CompanyCourse.staticSetDisplayPage(siteRequest2, (String)result.get(CompanyCourse.VAR_displayPage)));
      page.persistForClass(CompanyCourse.VAR_editPage, CompanyCourse.staticSetEditPage(siteRequest2, (String)result.get(CompanyCourse.VAR_editPage)));
      page.persistForClass(CompanyCourse.VAR_userPage, CompanyCourse.staticSetUserPage(siteRequest2, (String)result.get(CompanyCourse.VAR_userPage)));
      page.persistForClass(CompanyCourse.VAR_download, CompanyCourse.staticSetDownload(siteRequest2, (String)result.get(CompanyCourse.VAR_download)));
      page.persistForClass(CompanyCourse.VAR_pageImageAlt, CompanyCourse.staticSetPageImageAlt(siteRequest2, (String)result.get(CompanyCourse.VAR_pageImageAlt)));
      page.persistForClass(CompanyCourse.VAR_relatedArticleIds, CompanyCourse.staticSetRelatedArticleIds(siteRequest2, (String)result.get(CompanyCourse.VAR_relatedArticleIds)));
      page.persistForClass(CompanyCourse.VAR_solrId, CompanyCourse.staticSetSolrId(siteRequest2, (String)result.get(CompanyCourse.VAR_solrId)));

      page.promiseDeepForClass((SiteRequest)siteRequest).onSuccess(o -> {
        try {
          JsonObject data = JsonObject.mapFrom(o);
          ctx.put("result", data.getMap());
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
