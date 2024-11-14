package org.computate.site.model.fiware.iotservice;

import java.text.Normalizer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.computate.search.wrap.Wrap;
import org.computate.site.model.BaseModel;
import org.computate.site.model.BaseModel;
import org.computate.site.model.fiware.weatherobserved.WeatherObserved;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.request.ComputateSiteRequest;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Order: 11
 * Description: A service within a service path in the NGSI-LD standard
 * AName: an IoT service
 * PluralName: IoT services
 * Icon: <i class="fa-solid fa-tower-cell"></i>
 * 
 * SearchPageUri: /en-us/search/iot-service
 * EditPageUri: /en-us/edit/iot-service/{objectId}
 * DisplayPageUri: /en-us/shop/iot-service/{objectId}
 * ApiUri: /en-us/api/iot-service
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   Admin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     Admin:
 *   SuperAdmin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     SuperAdmin:
 */
public class IotService extends IotServiceGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: entity type
   * Description: The NGSI-LD entity type
	 * HtmRowTitle: IoT service details
   * HtmRow: 3
   * HtmCell: 1
   * Facet: true
   **/
  protected void _entityType(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: API key
   * Description: The NGSI-LD API Key
   * HtmRow: 3
   * HtmCell: 2
   * Facet: true
   **/
  protected void _apiKey(Wrap<String> w) {
    w.o(entityType);
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: resource
   * Description: The NGSI-LD resource
   * HtmRow: 3
   * HtmCell: 3
   * Facet: true
   **/
  protected void _resource(Wrap<String> w) {
    w.o("/iot/json");
  }

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: NGSILD-Tenant
	 * Description: The NGSILD-Tenant or Fiware-Service
	 * HtmRow: 4
	 * HtmCell: 1
	 * HtmColumn: 1
	 * Facet: true
	 */
	protected void _ngsildTenant(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: NGSILD-Path
	 * Description: The NGSILD-Path or Fiware-ServicePath
	 * HtmRow: 4
	 * HtmCell: 2
	 * HtmColumn: 2
	 * Facet: true
	 */
	protected void _ngsildPath(Wrap<String> w) {
	}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: context broker host
   * Description: The NGSI-LD Context Broker host
   * HtmRow: 5
   * HtmCell: 1
   * Facet: true
   **/
  protected void _cbHost(Wrap<String> w) {
    String cbHostName = siteRequest_.getConfig().getString(ComputateConfigKeys.CONTEXT_BROKER_HOST_NAME);
    Integer cbPort = siteRequest_.getConfig().getInteger(ComputateConfigKeys.CONTEXT_BROKER_PORT);
    Boolean cbSsl = siteRequest_.getConfig().getBoolean(ComputateConfigKeys.CONTEXT_BROKER_SSL);
    String cbBaseUrl = String.format("%s://%s:%s", cbSsl ? "https" : "http", cbHostName, cbPort);
    w.o(cbBaseUrl);
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: attributes
   * Description: The NGSI-LD attributes
   * HtmRow: 6
   * HtmCell: 1
   * Facet: true
   **/
  protected void _attributes(Wrap<JsonArray> w) {
    JsonArray attributes = new JsonArray();
    if (WeatherObserved.CLASS_SIMPLE_NAME.equals(entityType)) {
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
    }
    w.o(attributes);
  }

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRowTitle: page details
	 * HtmRow: 7
	 * HtmCell: 1
	 * Facet: true
	 * DisplayName: Page ID
	 * Description: The ID for this page. 
	 */
	protected void _pageId(Wrap<String> w) {
		w.o(toId(String.format("%s-%s-%s", entityType, ngsildTenant, ngsildPath)));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: resource URI
	 * Description: The resource relative URI for this page. 
	 */
	protected void _resourceUri(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: template URI
	 * Description: The template relative URI for this page. 
	 */
	protected void _templateUri(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 7
	 * HtmCell: 2
	 * Facet: true
	 * DisplayName: URI
	 * Description: The relative URI for this page. 
	 */
	protected void _uri(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 8
	 * HtmCell: 1
	 * Facet: true
	 * DisplayName: URL
	 * Description: The URL for this page. 
	 */
	protected void _url(Wrap<String> w) {
		w.o(String.format("%s%s", siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), uri));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: title
	 * Description: The title of this page. 
	 * UrlVar: pageUrlId
	 */
	protected void _title(Wrap<String> w) {
		w.o(String.format("%s entities in %s tenant and %s path", entityType, ngsildTenant, ngsildPath));
	}

	@Override
	protected void _objectTitle(Wrap<String> w) {
		w.o(String.format("%s — %s", IotService_NameAdjectiveSingular_enUS, title));
	}

	@Override
	protected void _objectId(Wrap<String> w) {
		w.o(pageId);
	}

	@Override
	protected void _id(Wrap<String> w) {
		w.o(String.format("%s_%s", CLASS_SIMPLE_NAME, pageId));
	}

	@Override
	protected void _pageUrlId(Wrap<String> w) {
		w.o(url);
	}
}
