package org.computate.site.model.fiware.weatherobserved;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.computate.site.model.BaseModel;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.search.list.SearchList;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.data.Path;
import io.vertx.pgclient.data.Point;
import io.vertx.pgclient.data.Polygon;

/**
 * Order: 12
 * Description: For keeping track of temperature, humidity, and other weather related details. 
 * AName: a weather observed device
 * Color: 2017-shaded-spruce
 * Icon: <i class="fa-regular fa-cloud-bolt-sun"></i>
 * Rows: 100
 * 
 * SmartDataModel: WeatherObserved - Weather - SmartCities
 * Fiware: true
 * 
 * SearchPageUri: /en-us/search/weather-observed
 * EditPageUri: /en-us/edit/weather-observed/{pageId}
 * ApiUri: /en-us/api/weather-observed
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
 **/
public class WeatherObserved extends WeatherObservedGen<BaseModel> {


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: name
	 * Description: The name of this item
	 * HtmRow: 3
	 * HtmCell: 1
	 * HtmColumn: 1
	 * HtmRowTitle: weather observed details
	 * Facet: true
	 * VarName: true
	 **/
	protected void _name(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: description
	 * Description: A description of this item
	 * HtmRow: 3
	 * HtmCell: 2
	 * Facet: true
	 * HtmColumn: 2
	 * VarDescription: true
	 **/
	protected void _description(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: address
	 * Description: The mailing address
	 * HtmRow: 3
	 * HtmCell: 3
	 * Facet: true
	 **/
	protected void _address(Wrap<JsonObject> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: alternate name
	 * Description: An alternative name for this item
	 * Facet: true
	 **/
	protected void _alternateName(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * FiwareType: geo:point
	 * DocValues: true
	 * Persist: true
	 * DisplayName: location
	 * Description: Geojson reference to the item. It can be Point, LineString, Polygon, MultiPoint, MultiLineString or MultiPolygon
	 * HtmRow: 3
	 * HtmCell: 4
	 * Facet: true
	 **/
	protected void _location(Wrap<Point> w) {}


	/**
	 * {@inheritDoc}
	
	 * LocationColor: true
	 * Indexed: true
	 * Stored: true
	 * DisplayName: area served colors
	 * Description: The colors of each areaServed Paths. 
	 */
	protected void _areaServedColors(List<String> l) {
	}

	/**
	 * {@inheritDoc}
	 * LocationTitle: true
	 * Indexed: true
	 * Stored: true
	 * DisplayName: area served titles
	 * Description: The titles of each areaServed Paths. 
	 */
	protected void _areaServedTitles(List<String> l) {
	}

	/**
	 * {@inheritDoc}
	 * LocationUrl: true
	 * Indexed: true
	 * Stored: true
	 * DisplayName: area served links
	 * Description: The links of each areaServed Paths. 
	 */
	protected void _areaServedLinks(List<String> l) {
	}

	/**
	 * {@inheritDoc}
	 * FiwareType: geo:linestring
	 * Location: true
	 * DocValues: true
	 * Persist: true
	 * DisplayName: area served
	 * Description: The geographic area where a service or offered item is provided
	 * HtmRow: 3
	 * HtmCell: 4
	 * Facet: true
	 **/
	protected void _areaServed(Wrap<Polygon> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: data provider
	 * Description: A sequence of characters identifying the provider of the harmonised data entity
	 * Facet: true
	 **/
	protected void _dataProvider(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: date created
	 * Description: Entity creation timestamp. This will usually be allocated by the storage platform
	 * Facet: true
	 **/
	protected void _dateCreated(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: date modified
	 * Description: Timestamp of the last modification of the entity. This will usually be allocated by the storage platform
	 * Facet: true
	 **/
	protected void _dateModified(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: date observed
	 * Description: Date of the observed entity defined by the user
	 * Facet: true
	 **/
	protected void _dateObserved(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: owner
	 * Description: A List containing a JSON encoded sequence of characters referencing the unique Ids of the owner(s)
	 * Facet: true
	 **/
	protected void _owner(Wrap<JsonObject> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: ref device
	 * Description: A reference to the device(s) which captured this observation
	 * Facet: true
	 **/
	protected void _refDevice(Wrap<JsonObject> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: ref point of interest
	 * Description: Point of interest related to the item
	 * Facet: true
	 **/
	protected void _refPointOfInterest(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: source
	 * Description: A sequence of characters giving the original source of the entity data as a URL. Recommended to be the fully qualified domain name of the source provider, or the URL to the source object
	 * Facet: true
	 **/
	protected void _source(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: see also
	 * Description: list of uri pointing to additional resources about the item
	 * HtmRow: 13
	 * HtmCell: 2
	 * Facet: true
	 **/
	protected void _seeAlso(Wrap<JsonObject> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: air quality index
	 * Description: Air quality index is a number used to report the quality of the air on any given day
	 * HtmRow: 4
	 * HtmCell: 1
	 * HtmRowTitle: air quality details
	 * Facet: true
	 **/
	protected void _airQualityIndex(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: air quality index forecast
	 * Description: Forecasted overall Air Quality Index (AQI) over a certain duration in future
	 * HtmRow: 4
	 * HtmCell: 2
	 * Facet: true
	 **/
	protected void _airQualityIndexForecast(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: aqi major pollutant
	 * Description: Major pollutant in the Air Quality Index (AQI)
	 * HtmRow: 4
	 * HtmCell: 3
	 * Facet: true
	 **/
	protected void _aqiMajorPollutant(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: aqi major pollutant forecast
	 * Description: Forecasted major air pollutant in the Air Quality Index (AQI) over a certain duration in future
	 * HtmRow: 4
	 * HtmCell: 4
	 * Facet: true
	 **/
	protected void _aqiMajorPollutantForecast(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: atmospheric pressure
	 * Description: The atmospheric pressure observed measured in Hecto Pascals
	 * HtmRowTitle: atmospheric pressure details
	 * HtmRow: 7
	 * HtmCell: 1
	 * Facet: true
	 **/
	protected void _atmosphericPressure(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: pressure tendency
	 * Description: Enum:'falling, raising, steady'. Is the pressure rising or falling? It can be expressed in quantitative terms or qualitative terms
	 * HtmRow: 7
	 * HtmCell: 2
	 * Facet: true
	 **/
	protected void _pressureTendency(Wrap<JsonObject> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: gust speed
	 * Description: A sudden burst of high-speed wind over the observed average wind speed lasting only for a few seconds
	 * HtmRowTitle: wind details
	 * HtmRow: 8
	 * HtmCell: 1
	 * Facet: true
	 **/
	protected void _gustSpeed(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: wind direction
	 * Description: Direction of the wind bet
	 * HtmRow: 8
	 * HtmCell: 2
	 * Facet: true
	 **/
	protected void _windDirection(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: wind speed
	 * Description: Intensity of the wind
	 * HtmRow: 8
	 * HtmCell: 3
	 * Facet: true
	 **/
	protected void _windSpeed(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: precipitation
	 * Description: Amount of water rain registered. 
	 * HtmRowTitle: precipitation details
	 * HtmRow: 9
	 * HtmCell: 1
	 * Facet: true
	 **/
	protected void _precipitation(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: precipitation forecast
	 * Description: Forecasted rainfall over a certain duration in future
	 * HtmRow: 9
	 * HtmCell: 2
	 * Facet: true
	 **/
	protected void _precipitationForecast(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: relative humidity
	 * Description: Humidity in the Air. Observed instantaneous relative humidity (water vapour in air)
	 * HtmRow: 9
	 * HtmCell: 3
	 * Facet: true
	 **/
	protected void _relativeHumidity(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: relative humidity forecast
	 * Description: Forecasted relative humidity (water vapour in air) over a certain duration in future
	 * HtmRow: 9
	 * HtmCell: 4
	 * Facet: true
	 **/
	protected void _relativeHumidityForecast(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: snow height
	 * Description: The snow height observed by generic snow depth measurement sensors, expressed in centimeters
	 * HtmRow: 9
	 * HtmCell: 5
	 * Facet: true
	 **/
	protected void _snowHeight(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: air temperature forecast
	 * Description: Forecasted value of air temperature over a certain duration in future
	 * HtmRowTitle: air temperature details
	 * HtmRow: 10
	 * HtmCell: 1
	 * Facet: true
	 **/
	protected void _airTemperatureForecast(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: air temperature tsa
	 * Description: Air temperature time series aggregation
	 * HtmRow: 10
	 * HtmCell: 2
	 * Facet: true
	 **/
	protected void _airTemperatureTSA(Wrap<JsonObject> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: feels like temperature
	 * Description: Temperature appreciation of the item
	 * HtmRow: 10
	 * HtmCell: 3
	 * Facet: true
	 **/
	protected void _feelsLikeTemperature(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: temperature
	 * Description: Temperature of the item
	 * HtmRow: 10
	 * HtmCell: 4
	 * Facet: true
	 **/
	protected void _temperature(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: solar radiation
	 * Description: The solar radiation observed measured in Watts per square
	 * HtmRowTitle: other weather details
	 * HtmRow: 11
	 * HtmCell: 1
	 * Facet: true
	 **/
	protected void _solarRadiation(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: stream gauge
	 * Description: The water level surface elevation observed by Hydrometric measurement sensors, namely a [Stream Gauge](https://en.wikipedia.org/wiki/Stream_gauge) expressed in centimeters
	 * HtmRow: 11
	 * HtmCell: 2
	 * Facet: true
	 **/
	protected void _streamGauge(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: u v index max
	 * Description: The maximum UV index for the period, based on the World Health Organization's UV Index measure. [http://www.who.int/uv/intersunprogramme/activities/uv_index/en/](http://www.who.int/uv/intersunprogramme/activities/uv_index/en/) the values between 1 and 11 are the valid range for the index. The value 0 is for describing that no signal is detected so no value is stored
	 * HtmRow: 11
	 * HtmCell: 3
	 * Facet: true
	 **/
	protected void _uVIndexMax(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: visibility
	 * Description: Categories of visibility
	 * HtmRow: 11
	 * HtmCell: 4
	 * Facet: true
	 **/
	protected void _visibility(Wrap<JsonObject> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: weather type
	 * Description: Text description of the weather
	 * HtmRow: 11
	 * HtmCell: 5
	 * Facet: true
	 **/
	protected void _weatherType(Wrap<String> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: dew point
	 * Description: The dew point encoded as a number. Observed temperature to which air must be cooled to become saturated with water vapor
	 * HtmRowTitle: weather details
	 * HtmRow: 11
	 * HtmCell: 6
	 * Facet: true
	 **/
	protected void _dewPoint(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: diffuse irradiation
	 * Description: Diffuse irradiance is the part of the solar irradiance that is scattered by the atmosphere
	 * HtmRow: 11
	 * HtmCell: 7
	 * Facet: true
	 **/
	protected void _diffuseIrradiation(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: direct irradiation
	 * Description: Direct irradiance is the part of the solar irradiance that directly reaches a surface
	 * HtmRow: 11
	 * HtmCell: 8
	 * Facet: true
	 **/
	protected void _directIrradiation(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: illuminance
	 * Description: (https://en.wikipedia.org/wiki/Illuminance) observed measured in lux (lx) or lumens per square metre (cd·sr·m−2)
	 * HtmRow: 11
	 * HtmCell: 9
	 * Facet: true
	 **/
	protected void _illuminance(Wrap<BigDecimal> w) {}


	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: NGSILD-Tenant
	 * Description: The NGSILD-Tenant or Fiware-Service
	 * HtmRowTitle: NGSI-LD details
	 * HtmRow: 12
	 * HtmCell: 1
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
	 * HtmRow: 12
	 * HtmCell: 2
	 * Facet: true
	 */
	protected void _ngsildPath(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Description: The NGSILD data with @context from the context broker
	 * HtmRowTitle: NGSI-LD data
	 * HtmRow: 13
	 * HtmCell: 1
	 * Facet: true
	 * Multiline: true
	 */
	protected void _ngsildData(Wrap<JsonObject> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: entity ID
	 * Description: A unique ID for this Smart Data Model
	 * HtmRow: 13
	 * HtmCell: 2
	 * Facet: true
	 * VarId: true
	 */
	protected void _entityId(Wrap<String> w) {
		w.o(String.format("urn:ngsi-ld:%s:%s", CLASS_SIMPLE_NAME, toId(name)));
	}

	/**
	 * {@inheritDoc}
	 * DisplayName: short entity ID
	 * Description: A short ID for this Smart Data Model
	 * Facet: true
	 */
	protected void _entityShortId(Wrap<String> w) {
		if(entityId != null) {
			w.o(StringUtils.substringAfter(entityId, String.format("urn:ngsi-ld:%s:", CLASS_SIMPLE_NAME)));
		}
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: NGSILD context
	 * Description: The NGSILD context URL for @context data. 
	 * HtmRow: 13
	 * HtmCell: 3
	 * Facet: true
	 */
	protected void _ngsildContext(Wrap<String> w) {
		w.o(String.format("https://raw.githubusercontent.com/%s/%s/main/fiware/context.jsonld", siteRequest_.getConfig().getString(ComputateConfigKeys.GITHUB_ORG), siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_STATIC_NAME)));
	}
}

