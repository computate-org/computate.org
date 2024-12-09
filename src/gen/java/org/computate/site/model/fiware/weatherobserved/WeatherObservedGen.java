package org.computate.site.model.fiware.weatherobserved;

import org.computate.site.request.SiteRequest;
import org.computate.site.model.BaseModel;
import io.vertx.core.json.JsonObject;
import java.util.Date;
import java.util.Set;
import org.computate.vertx.api.ApiRequest;
import org.computate.site.config.ConfigKeys;
import java.util.Optional;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.computate.search.serialize.ComputateLocalDateSerializer;
import org.computate.search.serialize.ComputateLocalDateDeserializer;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import org.computate.search.serialize.ComputateZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.apache.commons.lang3.math.NumberUtils;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.RoundingMode;
import java.util.Map;
import java.lang.String;
import io.vertx.pgclient.data.Point;
import org.computate.vertx.serialize.pgclient.PgClientPointSerializer;
import org.computate.vertx.serialize.pgclient.PgClientPointDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.BeanDescription;
import java.util.stream.Collectors;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.pgclient.data.Polygon;
import org.computate.vertx.serialize.pgclient.PgClientPolygonSerializer;
import org.computate.vertx.serialize.pgclient.PgClientPolygonDeserializer;
import java.math.BigDecimal;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class WeatherObservedGen into the class WeatherObserved. 
 * </li><li>You can add a class comment "SqlOrder: " followed by an Integer to sort this class compared when generating the SQL code to create and drop tables. 
 * The Order comment allows you do define which order the SQL code is generated. 
 * </li>
 * <h3>About the WeatherObserved class and it's generated class WeatherObservedGen&lt;BaseModel&gt;: </h3>extends WeatherObservedGen
 * <p>
 * This Java class extends a generated Java class WeatherObservedGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved">Find the class WeatherObserved in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends WeatherObservedGen<BaseModel>
 * <p>This <code>class WeatherObserved extends WeatherObservedGen&lt;BaseModel&gt;</code>, which means it extends a newly generated WeatherObservedGen. 
 * The generated <code>class WeatherObservedGen extends BaseModel</code> which means that WeatherObserved extends WeatherObservedGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiMethode: Search</h2>
 * <p>This class contains a comment <b>"ApiMethod: Search"</b>, which creates an API "Search". 
 * </p>
 * <h2>ApiMethode: GET</h2>
 * <p>This class contains a comment <b>"ApiMethod: GET"</b>, which creates an API "GET". 
 * </p>
 * <h2>ApiMethode: PATCH</h2>
 * <p>This class contains a comment <b>"ApiMethod: PATCH"</b>, which creates an API "PATCH". 
 * </p>
 * <h2>ApiMethode: POST</h2>
 * <p>This class contains a comment <b>"ApiMethod: POST"</b>, which creates an API "POST". 
 * </p>
 * <h2>ApiMethode: DELETE</h2>
 * <p>This class contains a comment <b>"ApiMethod: DELETE"</b>, which creates an API "DELETE". 
 * </p>
 * <h2>ApiMethode: PUTImport</h2>
 * <p>This class contains a comment <b>"ApiMethod: PUTImport"</b>, which creates an API "PUTImport". 
 * </p>
 * <h2>ApiMethode: SearchPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: SearchPage"</b>, which creates an API "SearchPage". 
 * </p>
 * <h2>ApiMethode: EditPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: EditPage"</b>, which creates an API "EditPage". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: weather observed devices"</b>, which groups all of the OpenAPIs for WeatherObserved objects under the tag "weather observed devices". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/weather-observed</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/weather-observed"</b>, which defines the base API URI for WeatherObserved objects as "/en-us/api/weather-observed" in the OpenAPI spec. 
 * </p>
 * <h2>Color: 2017-shaded-spruce</h2>
 * <p>This class contains a comment <b>"Color: 2017-shaded-spruce"</b>, which styles the WeatherObserved page "2017-shaded-spruce". 
 * This will reference a CSS class defined by the stylesheets in the project that starts with "w3-". 
 * A css class of "w3-2017-shaded-spruce" is expected to exist in the project stylesheets, and is inspired by W3 CSS colors. 
 * See: <a href="https://www.w3schools.com/w3css/w3css_colors.asp">https://www.w3schools.com/w3css/w3css_colors.asp</a>. 
 * </p>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the WeatherObserved class will inherit the helpful inherited class comments from the super class WeatherObservedGen. 
 * </p>
 * <h2>Rows: 100</h2>
 * <p>This class contains a comment <b>"Rows: 100"</b>, which means the WeatherObserved API will return a default of 100 records instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>Order: 12</h2>
 * <p>This class contains a comment <b>"Order: 12"</b>, which means this class will be sorted by the given number 12 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.fiware.weatherobserved.WeatherObservedPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.site.model.fiware.weatherobserved.WeatherObservedPage extends org.computate.site.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the WeatherObserved Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
 * </p>
 * <p>
 *   Adding protected void methods beginning with an underscore with a Promise as the only parameter will automatically set `Promise: true`. 
 * </p>
 * <p>
 *   <pre>
 *   
 *   	protected void _promiseBefore(Promise&lt;Void&gt; promise) {
 *   		promise.complete();
 *   	}
 *   </pre>
 * </p>
 * <p>
 *   Java classes with the `Model: true` will automatically set `Promise: true`. 
 * </p>
 * <p>
 *   If a super class of this Java class with `Model: true`, then the child class will also inherit `Promise: true`. 
 * </p>
 * <h2>AName.enUS: a weather observed device</h2>
 * <p>This class contains a comment <b>"AName.enUS: a weather observed device"</b>, which identifies the language context to describe a WeatherObserved as "a weather observed device". 
 * </p>
 * <p>
 * Delete the class WeatherObserved in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.fiware.weatherobserved in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class WeatherObservedGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(WeatherObserved.class);

	public static final String Description_enUS = "For keeping track of temperature, humidity, and other weather related details. ";
	public static final String AName_enUS = "a weather observed device";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this weather observed device";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the weather observed device";
	public static final String SingularName_enUS = "weather observed device";
	public static final String PluralName_enUS = "weather observed devices";
	public static final String NameActual_enUS = "current weather observed device";
	public static final String AllName_enUS = "all weather observed devices";
	public static final String SearchAllNameBy_enUS = "search weather observed devices by ";
	public static final String Title_enUS = "weather observed devices";
	public static final String ThePluralName_enUS = "the weather observed devices";
	public static final String NoNameFound_enUS = "no weather observed device found";
	public static final String ApiUri_enUS = "/en-us/api/weather-observed";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/weather-observed";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/weather-observed/{pageId}";
	public static final String OfName_enUS = "of weather observed device";
	public static final String ANameAdjective_enUS = "a weather observed device";
	public static final String NameAdjectiveSingular_enUS = "weather observed device";
	public static final String NameAdjectivePlural_enUS = "weather observed devices";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/weather-observed";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/weather-observed";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/weather-observed";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/weather-observed/{entityId}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/weather-observed/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/weather-observed/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/weather-observed";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/weather-observed";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/weather-observed";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/weather-observed";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/weather-observed";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/weather-observed";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/weather-observed/{entityId}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/weather-observed/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/weather-observed/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/weather-observed-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/weather-observed-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/weather-observed-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/weather-observed";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/weather-observed";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/weather-observed";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/weather-observed/{pageId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/weather-observed/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/weather-observed/%s";

	public static final String Color = "2017-shaded-spruce";
	public static final String Icon = "<i class=\"fa-regular fa-cloud-bolt-sun\"></i>";
	public static final Integer Rows = 100;

	//////////
	// name //
	//////////


	/**	 The entity name
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String name;

	/**	<br> The entity name
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = WeatherObserved.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchName(siteRequest_, WeatherObserved.staticSetName(siteRequest_, o)).toString();
	}

	public String sqlName() {
		return name;
	}

	/////////////////
	// description //
	/////////////////


	/**	 The entity description
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String description;

	/**	<br> The entity description
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = WeatherObserved.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchDescription(siteRequest_, WeatherObserved.staticSetDescription(siteRequest_, o)).toString();
	}

	public String sqlDescription() {
		return description;
	}

	/////////////
	// address //
	/////////////


	/**	 The entity address
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject address;

	/**	<br> The entity address
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:address">Find the entity address in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _address(Wrap<JsonObject> w);

	public JsonObject getAddress() {
		return address;
	}

	public void setAddress(JsonObject address) {
		this.address = address;
	}
	@JsonIgnore
	public void setAddress(String o) {
		this.address = WeatherObserved.staticSetAddress(siteRequest_, o);
	}
	public static JsonObject staticSetAddress(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected WeatherObserved addressInit() {
		Wrap<JsonObject> addressWrap = new Wrap<JsonObject>().var("address");
		if(address == null) {
			_address(addressWrap);
			Optional.ofNullable(addressWrap.getO()).ifPresent(o -> {
				setAddress(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchAddress(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrAddress(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAddress(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAddress(siteRequest_, WeatherObserved.staticSetAddress(siteRequest_, o)).toString();
	}

	public JsonObject sqlAddress() {
		return address;
	}

	///////////////////
	// alternateName //
	///////////////////


	/**	 The entity alternateName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String alternateName;

	/**	<br> The entity alternateName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:alternateName">Find the entity alternateName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _alternateName(Wrap<String> w);

	public String getAlternateName() {
		return alternateName;
	}
	public void setAlternateName(String o) {
		this.alternateName = WeatherObserved.staticSetAlternateName(siteRequest_, o);
	}
	public static String staticSetAlternateName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved alternateNameInit() {
		Wrap<String> alternateNameWrap = new Wrap<String>().var("alternateName");
		if(alternateName == null) {
			_alternateName(alternateNameWrap);
			Optional.ofNullable(alternateNameWrap.getO()).ifPresent(o -> {
				setAlternateName(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchAlternateName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrAlternateName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAlternateName(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAlternateName(siteRequest_, WeatherObserved.staticSetAlternateName(siteRequest_, o)).toString();
	}

	public String sqlAlternateName() {
		return alternateName;
	}

	//////////////
	// location //
	//////////////


	/**	 The entity location
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = PgClientPointDeserializer.class)
	@JsonSerialize(using = PgClientPointSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Point location;

	/**	<br> The entity location
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:location">Find the entity location in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _location(Wrap<Point> w);

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	@JsonIgnore
	public void setLocation(String o) {
		this.location = WeatherObserved.staticSetLocation(siteRequest_, o);
	}
	public static Point staticSetLocation(SiteRequest siteRequest_, String o) {
		if(o != null) {
			try {
				Point shape = null;
				if(StringUtils.isNotBlank(o)) {
					ObjectMapper objectMapper = new ObjectMapper();
					SimpleModule module = new SimpleModule();
					module.setDeserializerModifier(new BeanDeserializerModifier() {
						@Override
						public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
							if (beanDesc.getBeanClass() == Point.class) {
								return new PgClientPointDeserializer();
							}
							return deserializer;
						}
					});
					objectMapper.registerModule(module);
					shape = objectMapper.readValue(Json.encode(o), Point.class);
				}
				return shape;
			} catch(Exception ex) {
				ExceptionUtils.rethrow(ex);
			}
		}
		return null;
	}
	public void setLocation(JsonObject o) {
		this.location = WeatherObserved.staticSetLocation(siteRequest_, o);
	}
	public static Point staticSetLocation(SiteRequest siteRequest_, JsonObject o) {
		if(o != null) {
			try {
				Point shape = new Point();
				JsonArray coordinates = o.getJsonArray("coordinates");
				shape.setX(coordinates.getDouble(0));
				shape.setY(coordinates.getDouble(1));
				return shape;
			} catch(Exception ex) {
				ExceptionUtils.rethrow(ex);
			}
		}
		return null;
	}
	protected WeatherObserved locationInit() {
		Wrap<Point> locationWrap = new Wrap<Point>().var("location");
		if(location == null) {
			_location(locationWrap);
			Optional.ofNullable(locationWrap.getO()).ifPresent(o -> {
				setLocation(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Point staticSearchLocation(SiteRequest siteRequest_, Point o) {
		return o;
	}

	public static String staticSearchStrLocation(SiteRequest siteRequest_, Point o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocation(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchLocation(siteRequest_, WeatherObserved.staticSetLocation(siteRequest_, o)).toString();
	}

	public Point sqlLocation() {
		return location;
	}

	//////////////////////
	// areaServedColors //
	//////////////////////


	/**	 The entity areaServedColors
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> areaServedColors = new ArrayList<String>();

	/**	<br> The entity areaServedColors
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:areaServedColors">Find the entity areaServedColors in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _areaServedColors(List<String> l);

	public List<String> getAreaServedColors() {
		return areaServedColors;
	}

	public void setAreaServedColors(List<String> areaServedColors) {
		this.areaServedColors = areaServedColors;
	}
	@JsonIgnore
	public void setAreaServedColors(String o) {
		String l = WeatherObserved.staticSetAreaServedColors(siteRequest_, o);
		if(l != null)
			addAreaServedColors(l);
	}
	public static String staticSetAreaServedColors(SiteRequest siteRequest_, String o) {
		return o;
	}
	public WeatherObserved addAreaServedColors(String...objects) {
		for(String o : objects) {
			addAreaServedColors(o);
		}
		return (WeatherObserved)this;
	}
	public WeatherObserved addAreaServedColors(String o) {
		if(o != null)
			this.areaServedColors.add(o);
		return (WeatherObserved)this;
	}
	@JsonIgnore
	public void setAreaServedColors(JsonArray objects) {
		areaServedColors.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addAreaServedColors(o);
		}
	}
	protected WeatherObserved areaServedColorsInit() {
		_areaServedColors(areaServedColors);
		return (WeatherObserved)this;
	}

	public static String staticSearchAreaServedColors(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrAreaServedColors(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAreaServedColors(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAreaServedColors(siteRequest_, WeatherObserved.staticSetAreaServedColors(siteRequest_, o)).toString();
	}

	//////////////////////
	// areaServedTitles //
	//////////////////////


	/**	 The entity areaServedTitles
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> areaServedTitles = new ArrayList<String>();

	/**	<br> The entity areaServedTitles
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:areaServedTitles">Find the entity areaServedTitles in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _areaServedTitles(List<String> l);

	public List<String> getAreaServedTitles() {
		return areaServedTitles;
	}

	public void setAreaServedTitles(List<String> areaServedTitles) {
		this.areaServedTitles = areaServedTitles;
	}
	@JsonIgnore
	public void setAreaServedTitles(String o) {
		String l = WeatherObserved.staticSetAreaServedTitles(siteRequest_, o);
		if(l != null)
			addAreaServedTitles(l);
	}
	public static String staticSetAreaServedTitles(SiteRequest siteRequest_, String o) {
		return o;
	}
	public WeatherObserved addAreaServedTitles(String...objects) {
		for(String o : objects) {
			addAreaServedTitles(o);
		}
		return (WeatherObserved)this;
	}
	public WeatherObserved addAreaServedTitles(String o) {
		if(o != null)
			this.areaServedTitles.add(o);
		return (WeatherObserved)this;
	}
	@JsonIgnore
	public void setAreaServedTitles(JsonArray objects) {
		areaServedTitles.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addAreaServedTitles(o);
		}
	}
	protected WeatherObserved areaServedTitlesInit() {
		_areaServedTitles(areaServedTitles);
		return (WeatherObserved)this;
	}

	public static String staticSearchAreaServedTitles(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrAreaServedTitles(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAreaServedTitles(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAreaServedTitles(siteRequest_, WeatherObserved.staticSetAreaServedTitles(siteRequest_, o)).toString();
	}

	/////////////////////
	// areaServedLinks //
	/////////////////////


	/**	 The entity areaServedLinks
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> areaServedLinks = new ArrayList<String>();

	/**	<br> The entity areaServedLinks
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:areaServedLinks">Find the entity areaServedLinks in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _areaServedLinks(List<String> l);

	public List<String> getAreaServedLinks() {
		return areaServedLinks;
	}

	public void setAreaServedLinks(List<String> areaServedLinks) {
		this.areaServedLinks = areaServedLinks;
	}
	@JsonIgnore
	public void setAreaServedLinks(String o) {
		String l = WeatherObserved.staticSetAreaServedLinks(siteRequest_, o);
		if(l != null)
			addAreaServedLinks(l);
	}
	public static String staticSetAreaServedLinks(SiteRequest siteRequest_, String o) {
		return o;
	}
	public WeatherObserved addAreaServedLinks(String...objects) {
		for(String o : objects) {
			addAreaServedLinks(o);
		}
		return (WeatherObserved)this;
	}
	public WeatherObserved addAreaServedLinks(String o) {
		if(o != null)
			this.areaServedLinks.add(o);
		return (WeatherObserved)this;
	}
	@JsonIgnore
	public void setAreaServedLinks(JsonArray objects) {
		areaServedLinks.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addAreaServedLinks(o);
		}
	}
	protected WeatherObserved areaServedLinksInit() {
		_areaServedLinks(areaServedLinks);
		return (WeatherObserved)this;
	}

	public static String staticSearchAreaServedLinks(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrAreaServedLinks(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAreaServedLinks(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAreaServedLinks(siteRequest_, WeatherObserved.staticSetAreaServedLinks(siteRequest_, o)).toString();
	}

	////////////////
	// areaServed //
	////////////////


	/**	 The entity areaServed
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = PgClientPolygonDeserializer.class)
	@JsonSerialize(using = PgClientPolygonSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Polygon areaServed;

	/**	<br> The entity areaServed
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:areaServed">Find the entity areaServed in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _areaServed(Wrap<Polygon> w);

	public Polygon getAreaServed() {
		return areaServed;
	}

	public void setAreaServed(Polygon areaServed) {
		this.areaServed = areaServed;
	}
	@JsonIgnore
	public void setAreaServed(String o) {
		this.areaServed = WeatherObserved.staticSetAreaServed(siteRequest_, o);
	}
	public static Polygon staticSetAreaServed(SiteRequest siteRequest_, String o) {
		if(o != null) {
			try {
				Polygon shape = null;
				if(StringUtils.isNotBlank(o)) {
					ObjectMapper objectMapper = new ObjectMapper();
					SimpleModule module = new SimpleModule();
					module.setDeserializerModifier(new BeanDeserializerModifier() {
						@Override
						public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
							if (beanDesc.getBeanClass() == Polygon.class) {
								return new PgClientPolygonDeserializer();
							}
							return deserializer;
						}
					});
					objectMapper.registerModule(module);
					shape = objectMapper.readValue(Json.encode(o), Polygon.class);
				}
				return shape;
			} catch(Exception ex) {
				ExceptionUtils.rethrow(ex);
			}
		}
		return null;
	}
	public void setAreaServed(JsonObject o) {
		this.areaServed = WeatherObserved.staticSetAreaServed(siteRequest_, o);
	}
	public static Polygon staticSetAreaServed(SiteRequest siteRequest_, JsonObject o) {
		if(o != null) {
			try {
				Polygon shape = new Polygon();
				o.getJsonArray("coordinates").stream().map(a -> (JsonArray)a).forEach(g -> {
					g.stream().map(a -> (JsonArray)a).forEach(points -> {
						shape.addPoint(new Point(Double.parseDouble(points.getString(0)), Double.parseDouble(points.getString(1))));
					});
				});
				return shape;
			} catch(Exception ex) {
				ExceptionUtils.rethrow(ex);
			}
		}
		return null;
	}
	protected WeatherObserved areaServedInit() {
		Wrap<Polygon> areaServedWrap = new Wrap<Polygon>().var("areaServed");
		if(areaServed == null) {
			_areaServed(areaServedWrap);
			Optional.ofNullable(areaServedWrap.getO()).ifPresent(o -> {
				setAreaServed(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Polygon staticSearchAreaServed(SiteRequest siteRequest_, Polygon o) {
		return o;
	}

	public static String staticSearchStrAreaServed(SiteRequest siteRequest_, Polygon o) {
		JsonArray pointsArray = new JsonArray();
		o.getPoints().stream().map(point -> new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))).collect(Collectors.toList()).forEach(pointArray -> pointsArray.add(pointArray));
		return new JsonObject().put("type", "LineString").put("coordinates", pointsArray).toString();
	}

	public static String staticSearchFqAreaServed(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAreaServed(siteRequest_, WeatherObserved.staticSetAreaServed(siteRequest_, o)).toString();
	}

	public Polygon sqlAreaServed() {
		return areaServed;
	}

	//////////////////
	// dataProvider //
	//////////////////


	/**	 The entity dataProvider
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String dataProvider;

	/**	<br> The entity dataProvider
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:dataProvider">Find the entity dataProvider in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dataProvider(Wrap<String> w);

	public String getDataProvider() {
		return dataProvider;
	}
	public void setDataProvider(String o) {
		this.dataProvider = WeatherObserved.staticSetDataProvider(siteRequest_, o);
	}
	public static String staticSetDataProvider(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved dataProviderInit() {
		Wrap<String> dataProviderWrap = new Wrap<String>().var("dataProvider");
		if(dataProvider == null) {
			_dataProvider(dataProviderWrap);
			Optional.ofNullable(dataProviderWrap.getO()).ifPresent(o -> {
				setDataProvider(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchDataProvider(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDataProvider(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDataProvider(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchDataProvider(siteRequest_, WeatherObserved.staticSetDataProvider(siteRequest_, o)).toString();
	}

	public String sqlDataProvider() {
		return dataProvider;
	}

	/////////////////
	// dateCreated //
	/////////////////


	/**	 The entity dateCreated
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String dateCreated;

	/**	<br> The entity dateCreated
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:dateCreated">Find the entity dateCreated in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dateCreated(Wrap<String> w);

	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String o) {
		this.dateCreated = WeatherObserved.staticSetDateCreated(siteRequest_, o);
	}
	public static String staticSetDateCreated(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved dateCreatedInit() {
		Wrap<String> dateCreatedWrap = new Wrap<String>().var("dateCreated");
		if(dateCreated == null) {
			_dateCreated(dateCreatedWrap);
			Optional.ofNullable(dateCreatedWrap.getO()).ifPresent(o -> {
				setDateCreated(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchDateCreated(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDateCreated(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDateCreated(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchDateCreated(siteRequest_, WeatherObserved.staticSetDateCreated(siteRequest_, o)).toString();
	}

	public String sqlDateCreated() {
		return dateCreated;
	}

	//////////////////
	// dateModified //
	//////////////////


	/**	 The entity dateModified
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String dateModified;

	/**	<br> The entity dateModified
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:dateModified">Find the entity dateModified in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dateModified(Wrap<String> w);

	public String getDateModified() {
		return dateModified;
	}
	public void setDateModified(String o) {
		this.dateModified = WeatherObserved.staticSetDateModified(siteRequest_, o);
	}
	public static String staticSetDateModified(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved dateModifiedInit() {
		Wrap<String> dateModifiedWrap = new Wrap<String>().var("dateModified");
		if(dateModified == null) {
			_dateModified(dateModifiedWrap);
			Optional.ofNullable(dateModifiedWrap.getO()).ifPresent(o -> {
				setDateModified(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchDateModified(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDateModified(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDateModified(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchDateModified(siteRequest_, WeatherObserved.staticSetDateModified(siteRequest_, o)).toString();
	}

	public String sqlDateModified() {
		return dateModified;
	}

	//////////////////
	// dateObserved //
	//////////////////


	/**	 The entity dateObserved
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String dateObserved;

	/**	<br> The entity dateObserved
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:dateObserved">Find the entity dateObserved in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dateObserved(Wrap<String> w);

	public String getDateObserved() {
		return dateObserved;
	}
	public void setDateObserved(String o) {
		this.dateObserved = WeatherObserved.staticSetDateObserved(siteRequest_, o);
	}
	public static String staticSetDateObserved(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved dateObservedInit() {
		Wrap<String> dateObservedWrap = new Wrap<String>().var("dateObserved");
		if(dateObserved == null) {
			_dateObserved(dateObservedWrap);
			Optional.ofNullable(dateObservedWrap.getO()).ifPresent(o -> {
				setDateObserved(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchDateObserved(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDateObserved(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDateObserved(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchDateObserved(siteRequest_, WeatherObserved.staticSetDateObserved(siteRequest_, o)).toString();
	}

	public String sqlDateObserved() {
		return dateObserved;
	}

	///////////
	// owner //
	///////////


	/**	 The entity owner
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject owner;

	/**	<br> The entity owner
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:owner">Find the entity owner in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _owner(Wrap<JsonObject> w);

	public JsonObject getOwner() {
		return owner;
	}

	public void setOwner(JsonObject owner) {
		this.owner = owner;
	}
	@JsonIgnore
	public void setOwner(String o) {
		this.owner = WeatherObserved.staticSetOwner(siteRequest_, o);
	}
	public static JsonObject staticSetOwner(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected WeatherObserved ownerInit() {
		Wrap<JsonObject> ownerWrap = new Wrap<JsonObject>().var("owner");
		if(owner == null) {
			_owner(ownerWrap);
			Optional.ofNullable(ownerWrap.getO()).ifPresent(o -> {
				setOwner(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchOwner(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrOwner(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqOwner(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchOwner(siteRequest_, WeatherObserved.staticSetOwner(siteRequest_, o)).toString();
	}

	public JsonObject sqlOwner() {
		return owner;
	}

	///////////////
	// refDevice //
	///////////////


	/**	 The entity refDevice
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject refDevice;

	/**	<br> The entity refDevice
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:refDevice">Find the entity refDevice in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _refDevice(Wrap<JsonObject> w);

	public JsonObject getRefDevice() {
		return refDevice;
	}

	public void setRefDevice(JsonObject refDevice) {
		this.refDevice = refDevice;
	}
	@JsonIgnore
	public void setRefDevice(String o) {
		this.refDevice = WeatherObserved.staticSetRefDevice(siteRequest_, o);
	}
	public static JsonObject staticSetRefDevice(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected WeatherObserved refDeviceInit() {
		Wrap<JsonObject> refDeviceWrap = new Wrap<JsonObject>().var("refDevice");
		if(refDevice == null) {
			_refDevice(refDeviceWrap);
			Optional.ofNullable(refDeviceWrap.getO()).ifPresent(o -> {
				setRefDevice(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchRefDevice(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrRefDevice(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRefDevice(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchRefDevice(siteRequest_, WeatherObserved.staticSetRefDevice(siteRequest_, o)).toString();
	}

	public JsonObject sqlRefDevice() {
		return refDevice;
	}

	////////////////////////
	// refPointOfInterest //
	////////////////////////


	/**	 The entity refPointOfInterest
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String refPointOfInterest;

	/**	<br> The entity refPointOfInterest
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:refPointOfInterest">Find the entity refPointOfInterest in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _refPointOfInterest(Wrap<String> w);

	public String getRefPointOfInterest() {
		return refPointOfInterest;
	}
	public void setRefPointOfInterest(String o) {
		this.refPointOfInterest = WeatherObserved.staticSetRefPointOfInterest(siteRequest_, o);
	}
	public static String staticSetRefPointOfInterest(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved refPointOfInterestInit() {
		Wrap<String> refPointOfInterestWrap = new Wrap<String>().var("refPointOfInterest");
		if(refPointOfInterest == null) {
			_refPointOfInterest(refPointOfInterestWrap);
			Optional.ofNullable(refPointOfInterestWrap.getO()).ifPresent(o -> {
				setRefPointOfInterest(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchRefPointOfInterest(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrRefPointOfInterest(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRefPointOfInterest(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchRefPointOfInterest(siteRequest_, WeatherObserved.staticSetRefPointOfInterest(siteRequest_, o)).toString();
	}

	public String sqlRefPointOfInterest() {
		return refPointOfInterest;
	}

	////////////
	// source //
	////////////


	/**	 The entity source
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String source;

	/**	<br> The entity source
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:source">Find the entity source in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _source(Wrap<String> w);

	public String getSource() {
		return source;
	}
	public void setSource(String o) {
		this.source = WeatherObserved.staticSetSource(siteRequest_, o);
	}
	public static String staticSetSource(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved sourceInit() {
		Wrap<String> sourceWrap = new Wrap<String>().var("source");
		if(source == null) {
			_source(sourceWrap);
			Optional.ofNullable(sourceWrap.getO()).ifPresent(o -> {
				setSource(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchSource(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSource(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSource(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchSource(siteRequest_, WeatherObserved.staticSetSource(siteRequest_, o)).toString();
	}

	public String sqlSource() {
		return source;
	}

	/////////////
	// seeAlso //
	/////////////


	/**	 The entity seeAlso
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject seeAlso;

	/**	<br> The entity seeAlso
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:seeAlso">Find the entity seeAlso in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seeAlso(Wrap<JsonObject> w);

	public JsonObject getSeeAlso() {
		return seeAlso;
	}

	public void setSeeAlso(JsonObject seeAlso) {
		this.seeAlso = seeAlso;
	}
	@JsonIgnore
	public void setSeeAlso(String o) {
		this.seeAlso = WeatherObserved.staticSetSeeAlso(siteRequest_, o);
	}
	public static JsonObject staticSetSeeAlso(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected WeatherObserved seeAlsoInit() {
		Wrap<JsonObject> seeAlsoWrap = new Wrap<JsonObject>().var("seeAlso");
		if(seeAlso == null) {
			_seeAlso(seeAlsoWrap);
			Optional.ofNullable(seeAlsoWrap.getO()).ifPresent(o -> {
				setSeeAlso(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchSeeAlso(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrSeeAlso(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSeeAlso(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchSeeAlso(siteRequest_, WeatherObserved.staticSetSeeAlso(siteRequest_, o)).toString();
	}

	public JsonObject sqlSeeAlso() {
		return seeAlso;
	}

	/////////////////////
	// airQualityIndex //
	/////////////////////


	/**	 The entity airQualityIndex
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal airQualityIndex;

	/**	<br> The entity airQualityIndex
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:airQualityIndex">Find the entity airQualityIndex in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _airQualityIndex(Wrap<BigDecimal> w);

	public BigDecimal getAirQualityIndex() {
		return airQualityIndex;
	}

	public void setAirQualityIndex(BigDecimal airQualityIndex) {
		this.airQualityIndex = airQualityIndex;
	}
	@JsonIgnore
	public void setAirQualityIndex(String o) {
		this.airQualityIndex = WeatherObserved.staticSetAirQualityIndex(siteRequest_, o);
	}
	public static BigDecimal staticSetAirQualityIndex(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setAirQualityIndex(Double o) {
		setAirQualityIndex(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setAirQualityIndex(Integer o) {
		setAirQualityIndex(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setAirQualityIndex(Number o) {
		setAirQualityIndex(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved airQualityIndexInit() {
		Wrap<BigDecimal> airQualityIndexWrap = new Wrap<BigDecimal>().var("airQualityIndex");
		if(airQualityIndex == null) {
			_airQualityIndex(airQualityIndexWrap);
			Optional.ofNullable(airQualityIndexWrap.getO()).ifPresent(o -> {
				setAirQualityIndex(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchAirQualityIndex(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrAirQualityIndex(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAirQualityIndex(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAirQualityIndex(siteRequest_, WeatherObserved.staticSetAirQualityIndex(siteRequest_, o)).toString();
	}

	public BigDecimal sqlAirQualityIndex() {
		return airQualityIndex;
	}

	/////////////////////////////
	// airQualityIndexForecast //
	/////////////////////////////


	/**	 The entity airQualityIndexForecast
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal airQualityIndexForecast;

	/**	<br> The entity airQualityIndexForecast
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:airQualityIndexForecast">Find the entity airQualityIndexForecast in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _airQualityIndexForecast(Wrap<BigDecimal> w);

	public BigDecimal getAirQualityIndexForecast() {
		return airQualityIndexForecast;
	}

	public void setAirQualityIndexForecast(BigDecimal airQualityIndexForecast) {
		this.airQualityIndexForecast = airQualityIndexForecast;
	}
	@JsonIgnore
	public void setAirQualityIndexForecast(String o) {
		this.airQualityIndexForecast = WeatherObserved.staticSetAirQualityIndexForecast(siteRequest_, o);
	}
	public static BigDecimal staticSetAirQualityIndexForecast(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setAirQualityIndexForecast(Double o) {
		setAirQualityIndexForecast(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setAirQualityIndexForecast(Integer o) {
		setAirQualityIndexForecast(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setAirQualityIndexForecast(Number o) {
		setAirQualityIndexForecast(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved airQualityIndexForecastInit() {
		Wrap<BigDecimal> airQualityIndexForecastWrap = new Wrap<BigDecimal>().var("airQualityIndexForecast");
		if(airQualityIndexForecast == null) {
			_airQualityIndexForecast(airQualityIndexForecastWrap);
			Optional.ofNullable(airQualityIndexForecastWrap.getO()).ifPresent(o -> {
				setAirQualityIndexForecast(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchAirQualityIndexForecast(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrAirQualityIndexForecast(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAirQualityIndexForecast(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAirQualityIndexForecast(siteRequest_, WeatherObserved.staticSetAirQualityIndexForecast(siteRequest_, o)).toString();
	}

	public BigDecimal sqlAirQualityIndexForecast() {
		return airQualityIndexForecast;
	}

	///////////////////////
	// aqiMajorPollutant //
	///////////////////////


	/**	 The entity aqiMajorPollutant
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String aqiMajorPollutant;

	/**	<br> The entity aqiMajorPollutant
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:aqiMajorPollutant">Find the entity aqiMajorPollutant in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _aqiMajorPollutant(Wrap<String> w);

	public String getAqiMajorPollutant() {
		return aqiMajorPollutant;
	}
	public void setAqiMajorPollutant(String o) {
		this.aqiMajorPollutant = WeatherObserved.staticSetAqiMajorPollutant(siteRequest_, o);
	}
	public static String staticSetAqiMajorPollutant(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved aqiMajorPollutantInit() {
		Wrap<String> aqiMajorPollutantWrap = new Wrap<String>().var("aqiMajorPollutant");
		if(aqiMajorPollutant == null) {
			_aqiMajorPollutant(aqiMajorPollutantWrap);
			Optional.ofNullable(aqiMajorPollutantWrap.getO()).ifPresent(o -> {
				setAqiMajorPollutant(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchAqiMajorPollutant(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrAqiMajorPollutant(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAqiMajorPollutant(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAqiMajorPollutant(siteRequest_, WeatherObserved.staticSetAqiMajorPollutant(siteRequest_, o)).toString();
	}

	public String sqlAqiMajorPollutant() {
		return aqiMajorPollutant;
	}

	///////////////////////////////
	// aqiMajorPollutantForecast //
	///////////////////////////////


	/**	 The entity aqiMajorPollutantForecast
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String aqiMajorPollutantForecast;

	/**	<br> The entity aqiMajorPollutantForecast
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:aqiMajorPollutantForecast">Find the entity aqiMajorPollutantForecast in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _aqiMajorPollutantForecast(Wrap<String> w);

	public String getAqiMajorPollutantForecast() {
		return aqiMajorPollutantForecast;
	}
	public void setAqiMajorPollutantForecast(String o) {
		this.aqiMajorPollutantForecast = WeatherObserved.staticSetAqiMajorPollutantForecast(siteRequest_, o);
	}
	public static String staticSetAqiMajorPollutantForecast(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved aqiMajorPollutantForecastInit() {
		Wrap<String> aqiMajorPollutantForecastWrap = new Wrap<String>().var("aqiMajorPollutantForecast");
		if(aqiMajorPollutantForecast == null) {
			_aqiMajorPollutantForecast(aqiMajorPollutantForecastWrap);
			Optional.ofNullable(aqiMajorPollutantForecastWrap.getO()).ifPresent(o -> {
				setAqiMajorPollutantForecast(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchAqiMajorPollutantForecast(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrAqiMajorPollutantForecast(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAqiMajorPollutantForecast(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAqiMajorPollutantForecast(siteRequest_, WeatherObserved.staticSetAqiMajorPollutantForecast(siteRequest_, o)).toString();
	}

	public String sqlAqiMajorPollutantForecast() {
		return aqiMajorPollutantForecast;
	}

	/////////////////////////
	// atmosphericPressure //
	/////////////////////////


	/**	 The entity atmosphericPressure
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal atmosphericPressure;

	/**	<br> The entity atmosphericPressure
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:atmosphericPressure">Find the entity atmosphericPressure in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _atmosphericPressure(Wrap<BigDecimal> w);

	public BigDecimal getAtmosphericPressure() {
		return atmosphericPressure;
	}

	public void setAtmosphericPressure(BigDecimal atmosphericPressure) {
		this.atmosphericPressure = atmosphericPressure;
	}
	@JsonIgnore
	public void setAtmosphericPressure(String o) {
		this.atmosphericPressure = WeatherObserved.staticSetAtmosphericPressure(siteRequest_, o);
	}
	public static BigDecimal staticSetAtmosphericPressure(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setAtmosphericPressure(Double o) {
		setAtmosphericPressure(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setAtmosphericPressure(Integer o) {
		setAtmosphericPressure(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setAtmosphericPressure(Number o) {
		setAtmosphericPressure(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved atmosphericPressureInit() {
		Wrap<BigDecimal> atmosphericPressureWrap = new Wrap<BigDecimal>().var("atmosphericPressure");
		if(atmosphericPressure == null) {
			_atmosphericPressure(atmosphericPressureWrap);
			Optional.ofNullable(atmosphericPressureWrap.getO()).ifPresent(o -> {
				setAtmosphericPressure(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchAtmosphericPressure(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrAtmosphericPressure(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAtmosphericPressure(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAtmosphericPressure(siteRequest_, WeatherObserved.staticSetAtmosphericPressure(siteRequest_, o)).toString();
	}

	public BigDecimal sqlAtmosphericPressure() {
		return atmosphericPressure;
	}

	//////////////////////
	// pressureTendency //
	//////////////////////


	/**	 The entity pressureTendency
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject pressureTendency;

	/**	<br> The entity pressureTendency
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:pressureTendency">Find the entity pressureTendency in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pressureTendency(Wrap<JsonObject> w);

	public JsonObject getPressureTendency() {
		return pressureTendency;
	}

	public void setPressureTendency(JsonObject pressureTendency) {
		this.pressureTendency = pressureTendency;
	}
	@JsonIgnore
	public void setPressureTendency(String o) {
		this.pressureTendency = WeatherObserved.staticSetPressureTendency(siteRequest_, o);
	}
	public static JsonObject staticSetPressureTendency(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected WeatherObserved pressureTendencyInit() {
		Wrap<JsonObject> pressureTendencyWrap = new Wrap<JsonObject>().var("pressureTendency");
		if(pressureTendency == null) {
			_pressureTendency(pressureTendencyWrap);
			Optional.ofNullable(pressureTendencyWrap.getO()).ifPresent(o -> {
				setPressureTendency(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchPressureTendency(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrPressureTendency(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPressureTendency(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchPressureTendency(siteRequest_, WeatherObserved.staticSetPressureTendency(siteRequest_, o)).toString();
	}

	public JsonObject sqlPressureTendency() {
		return pressureTendency;
	}

	///////////////
	// gustSpeed //
	///////////////


	/**	 The entity gustSpeed
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal gustSpeed;

	/**	<br> The entity gustSpeed
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:gustSpeed">Find the entity gustSpeed in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _gustSpeed(Wrap<BigDecimal> w);

	public BigDecimal getGustSpeed() {
		return gustSpeed;
	}

	public void setGustSpeed(BigDecimal gustSpeed) {
		this.gustSpeed = gustSpeed;
	}
	@JsonIgnore
	public void setGustSpeed(String o) {
		this.gustSpeed = WeatherObserved.staticSetGustSpeed(siteRequest_, o);
	}
	public static BigDecimal staticSetGustSpeed(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setGustSpeed(Double o) {
		setGustSpeed(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setGustSpeed(Integer o) {
		setGustSpeed(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setGustSpeed(Number o) {
		setGustSpeed(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved gustSpeedInit() {
		Wrap<BigDecimal> gustSpeedWrap = new Wrap<BigDecimal>().var("gustSpeed");
		if(gustSpeed == null) {
			_gustSpeed(gustSpeedWrap);
			Optional.ofNullable(gustSpeedWrap.getO()).ifPresent(o -> {
				setGustSpeed(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchGustSpeed(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrGustSpeed(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGustSpeed(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchGustSpeed(siteRequest_, WeatherObserved.staticSetGustSpeed(siteRequest_, o)).toString();
	}

	public BigDecimal sqlGustSpeed() {
		return gustSpeed;
	}

	///////////////////
	// windDirection //
	///////////////////


	/**	 The entity windDirection
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal windDirection;

	/**	<br> The entity windDirection
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:windDirection">Find the entity windDirection in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _windDirection(Wrap<BigDecimal> w);

	public BigDecimal getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(BigDecimal windDirection) {
		this.windDirection = windDirection;
	}
	@JsonIgnore
	public void setWindDirection(String o) {
		this.windDirection = WeatherObserved.staticSetWindDirection(siteRequest_, o);
	}
	public static BigDecimal staticSetWindDirection(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setWindDirection(Double o) {
		setWindDirection(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setWindDirection(Integer o) {
		setWindDirection(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setWindDirection(Number o) {
		setWindDirection(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved windDirectionInit() {
		Wrap<BigDecimal> windDirectionWrap = new Wrap<BigDecimal>().var("windDirection");
		if(windDirection == null) {
			_windDirection(windDirectionWrap);
			Optional.ofNullable(windDirectionWrap.getO()).ifPresent(o -> {
				setWindDirection(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchWindDirection(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrWindDirection(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqWindDirection(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchWindDirection(siteRequest_, WeatherObserved.staticSetWindDirection(siteRequest_, o)).toString();
	}

	public BigDecimal sqlWindDirection() {
		return windDirection;
	}

	///////////////
	// windSpeed //
	///////////////


	/**	 The entity windSpeed
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal windSpeed;

	/**	<br> The entity windSpeed
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:windSpeed">Find the entity windSpeed in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _windSpeed(Wrap<BigDecimal> w);

	public BigDecimal getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(BigDecimal windSpeed) {
		this.windSpeed = windSpeed;
	}
	@JsonIgnore
	public void setWindSpeed(String o) {
		this.windSpeed = WeatherObserved.staticSetWindSpeed(siteRequest_, o);
	}
	public static BigDecimal staticSetWindSpeed(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setWindSpeed(Double o) {
		setWindSpeed(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setWindSpeed(Integer o) {
		setWindSpeed(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setWindSpeed(Number o) {
		setWindSpeed(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved windSpeedInit() {
		Wrap<BigDecimal> windSpeedWrap = new Wrap<BigDecimal>().var("windSpeed");
		if(windSpeed == null) {
			_windSpeed(windSpeedWrap);
			Optional.ofNullable(windSpeedWrap.getO()).ifPresent(o -> {
				setWindSpeed(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchWindSpeed(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrWindSpeed(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqWindSpeed(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchWindSpeed(siteRequest_, WeatherObserved.staticSetWindSpeed(siteRequest_, o)).toString();
	}

	public BigDecimal sqlWindSpeed() {
		return windSpeed;
	}

	///////////////////
	// precipitation //
	///////////////////


	/**	 The entity precipitation
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal precipitation;

	/**	<br> The entity precipitation
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:precipitation">Find the entity precipitation in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _precipitation(Wrap<BigDecimal> w);

	public BigDecimal getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(BigDecimal precipitation) {
		this.precipitation = precipitation;
	}
	@JsonIgnore
	public void setPrecipitation(String o) {
		this.precipitation = WeatherObserved.staticSetPrecipitation(siteRequest_, o);
	}
	public static BigDecimal staticSetPrecipitation(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setPrecipitation(Double o) {
		setPrecipitation(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setPrecipitation(Integer o) {
		setPrecipitation(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setPrecipitation(Number o) {
		setPrecipitation(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved precipitationInit() {
		Wrap<BigDecimal> precipitationWrap = new Wrap<BigDecimal>().var("precipitation");
		if(precipitation == null) {
			_precipitation(precipitationWrap);
			Optional.ofNullable(precipitationWrap.getO()).ifPresent(o -> {
				setPrecipitation(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchPrecipitation(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrPrecipitation(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPrecipitation(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchPrecipitation(siteRequest_, WeatherObserved.staticSetPrecipitation(siteRequest_, o)).toString();
	}

	public BigDecimal sqlPrecipitation() {
		return precipitation;
	}

	///////////////////////////
	// precipitationForecast //
	///////////////////////////


	/**	 The entity precipitationForecast
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal precipitationForecast;

	/**	<br> The entity precipitationForecast
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:precipitationForecast">Find the entity precipitationForecast in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _precipitationForecast(Wrap<BigDecimal> w);

	public BigDecimal getPrecipitationForecast() {
		return precipitationForecast;
	}

	public void setPrecipitationForecast(BigDecimal precipitationForecast) {
		this.precipitationForecast = precipitationForecast;
	}
	@JsonIgnore
	public void setPrecipitationForecast(String o) {
		this.precipitationForecast = WeatherObserved.staticSetPrecipitationForecast(siteRequest_, o);
	}
	public static BigDecimal staticSetPrecipitationForecast(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setPrecipitationForecast(Double o) {
		setPrecipitationForecast(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setPrecipitationForecast(Integer o) {
		setPrecipitationForecast(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setPrecipitationForecast(Number o) {
		setPrecipitationForecast(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved precipitationForecastInit() {
		Wrap<BigDecimal> precipitationForecastWrap = new Wrap<BigDecimal>().var("precipitationForecast");
		if(precipitationForecast == null) {
			_precipitationForecast(precipitationForecastWrap);
			Optional.ofNullable(precipitationForecastWrap.getO()).ifPresent(o -> {
				setPrecipitationForecast(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchPrecipitationForecast(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrPrecipitationForecast(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPrecipitationForecast(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchPrecipitationForecast(siteRequest_, WeatherObserved.staticSetPrecipitationForecast(siteRequest_, o)).toString();
	}

	public BigDecimal sqlPrecipitationForecast() {
		return precipitationForecast;
	}

	//////////////////////
	// relativeHumidity //
	//////////////////////


	/**	 The entity relativeHumidity
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal relativeHumidity;

	/**	<br> The entity relativeHumidity
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:relativeHumidity">Find the entity relativeHumidity in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _relativeHumidity(Wrap<BigDecimal> w);

	public BigDecimal getRelativeHumidity() {
		return relativeHumidity;
	}

	public void setRelativeHumidity(BigDecimal relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}
	@JsonIgnore
	public void setRelativeHumidity(String o) {
		this.relativeHumidity = WeatherObserved.staticSetRelativeHumidity(siteRequest_, o);
	}
	public static BigDecimal staticSetRelativeHumidity(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setRelativeHumidity(Double o) {
		setRelativeHumidity(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setRelativeHumidity(Integer o) {
		setRelativeHumidity(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setRelativeHumidity(Number o) {
		setRelativeHumidity(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved relativeHumidityInit() {
		Wrap<BigDecimal> relativeHumidityWrap = new Wrap<BigDecimal>().var("relativeHumidity");
		if(relativeHumidity == null) {
			_relativeHumidity(relativeHumidityWrap);
			Optional.ofNullable(relativeHumidityWrap.getO()).ifPresent(o -> {
				setRelativeHumidity(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchRelativeHumidity(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrRelativeHumidity(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRelativeHumidity(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchRelativeHumidity(siteRequest_, WeatherObserved.staticSetRelativeHumidity(siteRequest_, o)).toString();
	}

	public BigDecimal sqlRelativeHumidity() {
		return relativeHumidity;
	}

	//////////////////////////////
	// relativeHumidityForecast //
	//////////////////////////////


	/**	 The entity relativeHumidityForecast
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal relativeHumidityForecast;

	/**	<br> The entity relativeHumidityForecast
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:relativeHumidityForecast">Find the entity relativeHumidityForecast in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _relativeHumidityForecast(Wrap<BigDecimal> w);

	public BigDecimal getRelativeHumidityForecast() {
		return relativeHumidityForecast;
	}

	public void setRelativeHumidityForecast(BigDecimal relativeHumidityForecast) {
		this.relativeHumidityForecast = relativeHumidityForecast;
	}
	@JsonIgnore
	public void setRelativeHumidityForecast(String o) {
		this.relativeHumidityForecast = WeatherObserved.staticSetRelativeHumidityForecast(siteRequest_, o);
	}
	public static BigDecimal staticSetRelativeHumidityForecast(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setRelativeHumidityForecast(Double o) {
		setRelativeHumidityForecast(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setRelativeHumidityForecast(Integer o) {
		setRelativeHumidityForecast(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setRelativeHumidityForecast(Number o) {
		setRelativeHumidityForecast(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved relativeHumidityForecastInit() {
		Wrap<BigDecimal> relativeHumidityForecastWrap = new Wrap<BigDecimal>().var("relativeHumidityForecast");
		if(relativeHumidityForecast == null) {
			_relativeHumidityForecast(relativeHumidityForecastWrap);
			Optional.ofNullable(relativeHumidityForecastWrap.getO()).ifPresent(o -> {
				setRelativeHumidityForecast(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchRelativeHumidityForecast(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrRelativeHumidityForecast(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqRelativeHumidityForecast(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchRelativeHumidityForecast(siteRequest_, WeatherObserved.staticSetRelativeHumidityForecast(siteRequest_, o)).toString();
	}

	public BigDecimal sqlRelativeHumidityForecast() {
		return relativeHumidityForecast;
	}

	////////////////
	// snowHeight //
	////////////////


	/**	 The entity snowHeight
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal snowHeight;

	/**	<br> The entity snowHeight
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:snowHeight">Find the entity snowHeight in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _snowHeight(Wrap<BigDecimal> w);

	public BigDecimal getSnowHeight() {
		return snowHeight;
	}

	public void setSnowHeight(BigDecimal snowHeight) {
		this.snowHeight = snowHeight;
	}
	@JsonIgnore
	public void setSnowHeight(String o) {
		this.snowHeight = WeatherObserved.staticSetSnowHeight(siteRequest_, o);
	}
	public static BigDecimal staticSetSnowHeight(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setSnowHeight(Double o) {
		setSnowHeight(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setSnowHeight(Integer o) {
		setSnowHeight(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setSnowHeight(Number o) {
		setSnowHeight(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved snowHeightInit() {
		Wrap<BigDecimal> snowHeightWrap = new Wrap<BigDecimal>().var("snowHeight");
		if(snowHeight == null) {
			_snowHeight(snowHeightWrap);
			Optional.ofNullable(snowHeightWrap.getO()).ifPresent(o -> {
				setSnowHeight(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchSnowHeight(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrSnowHeight(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSnowHeight(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchSnowHeight(siteRequest_, WeatherObserved.staticSetSnowHeight(siteRequest_, o)).toString();
	}

	public BigDecimal sqlSnowHeight() {
		return snowHeight;
	}

	////////////////////////////
	// airTemperatureForecast //
	////////////////////////////


	/**	 The entity airTemperatureForecast
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal airTemperatureForecast;

	/**	<br> The entity airTemperatureForecast
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:airTemperatureForecast">Find the entity airTemperatureForecast in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _airTemperatureForecast(Wrap<BigDecimal> w);

	public BigDecimal getAirTemperatureForecast() {
		return airTemperatureForecast;
	}

	public void setAirTemperatureForecast(BigDecimal airTemperatureForecast) {
		this.airTemperatureForecast = airTemperatureForecast;
	}
	@JsonIgnore
	public void setAirTemperatureForecast(String o) {
		this.airTemperatureForecast = WeatherObserved.staticSetAirTemperatureForecast(siteRequest_, o);
	}
	public static BigDecimal staticSetAirTemperatureForecast(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setAirTemperatureForecast(Double o) {
		setAirTemperatureForecast(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setAirTemperatureForecast(Integer o) {
		setAirTemperatureForecast(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setAirTemperatureForecast(Number o) {
		setAirTemperatureForecast(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved airTemperatureForecastInit() {
		Wrap<BigDecimal> airTemperatureForecastWrap = new Wrap<BigDecimal>().var("airTemperatureForecast");
		if(airTemperatureForecast == null) {
			_airTemperatureForecast(airTemperatureForecastWrap);
			Optional.ofNullable(airTemperatureForecastWrap.getO()).ifPresent(o -> {
				setAirTemperatureForecast(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchAirTemperatureForecast(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrAirTemperatureForecast(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAirTemperatureForecast(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAirTemperatureForecast(siteRequest_, WeatherObserved.staticSetAirTemperatureForecast(siteRequest_, o)).toString();
	}

	public BigDecimal sqlAirTemperatureForecast() {
		return airTemperatureForecast;
	}

	///////////////////////
	// airTemperatureTSA //
	///////////////////////


	/**	 The entity airTemperatureTSA
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject airTemperatureTSA;

	/**	<br> The entity airTemperatureTSA
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:airTemperatureTSA">Find the entity airTemperatureTSA in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _airTemperatureTSA(Wrap<JsonObject> w);

	public JsonObject getAirTemperatureTSA() {
		return airTemperatureTSA;
	}

	public void setAirTemperatureTSA(JsonObject airTemperatureTSA) {
		this.airTemperatureTSA = airTemperatureTSA;
	}
	@JsonIgnore
	public void setAirTemperatureTSA(String o) {
		this.airTemperatureTSA = WeatherObserved.staticSetAirTemperatureTSA(siteRequest_, o);
	}
	public static JsonObject staticSetAirTemperatureTSA(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected WeatherObserved airTemperatureTSAInit() {
		Wrap<JsonObject> airTemperatureTSAWrap = new Wrap<JsonObject>().var("airTemperatureTSA");
		if(airTemperatureTSA == null) {
			_airTemperatureTSA(airTemperatureTSAWrap);
			Optional.ofNullable(airTemperatureTSAWrap.getO()).ifPresent(o -> {
				setAirTemperatureTSA(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchAirTemperatureTSA(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrAirTemperatureTSA(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAirTemperatureTSA(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchAirTemperatureTSA(siteRequest_, WeatherObserved.staticSetAirTemperatureTSA(siteRequest_, o)).toString();
	}

	public JsonObject sqlAirTemperatureTSA() {
		return airTemperatureTSA;
	}

	//////////////////////////
	// feelsLikeTemperature //
	//////////////////////////


	/**	 The entity feelsLikeTemperature
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal feelsLikeTemperature;

	/**	<br> The entity feelsLikeTemperature
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:feelsLikeTemperature">Find the entity feelsLikeTemperature in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _feelsLikeTemperature(Wrap<BigDecimal> w);

	public BigDecimal getFeelsLikeTemperature() {
		return feelsLikeTemperature;
	}

	public void setFeelsLikeTemperature(BigDecimal feelsLikeTemperature) {
		this.feelsLikeTemperature = feelsLikeTemperature;
	}
	@JsonIgnore
	public void setFeelsLikeTemperature(String o) {
		this.feelsLikeTemperature = WeatherObserved.staticSetFeelsLikeTemperature(siteRequest_, o);
	}
	public static BigDecimal staticSetFeelsLikeTemperature(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setFeelsLikeTemperature(Double o) {
		setFeelsLikeTemperature(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setFeelsLikeTemperature(Integer o) {
		setFeelsLikeTemperature(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setFeelsLikeTemperature(Number o) {
		setFeelsLikeTemperature(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved feelsLikeTemperatureInit() {
		Wrap<BigDecimal> feelsLikeTemperatureWrap = new Wrap<BigDecimal>().var("feelsLikeTemperature");
		if(feelsLikeTemperature == null) {
			_feelsLikeTemperature(feelsLikeTemperatureWrap);
			Optional.ofNullable(feelsLikeTemperatureWrap.getO()).ifPresent(o -> {
				setFeelsLikeTemperature(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchFeelsLikeTemperature(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrFeelsLikeTemperature(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqFeelsLikeTemperature(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchFeelsLikeTemperature(siteRequest_, WeatherObserved.staticSetFeelsLikeTemperature(siteRequest_, o)).toString();
	}

	public BigDecimal sqlFeelsLikeTemperature() {
		return feelsLikeTemperature;
	}

	/////////////////
	// temperature //
	/////////////////


	/**	 The entity temperature
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal temperature;

	/**	<br> The entity temperature
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:temperature">Find the entity temperature in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _temperature(Wrap<BigDecimal> w);

	public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}
	@JsonIgnore
	public void setTemperature(String o) {
		this.temperature = WeatherObserved.staticSetTemperature(siteRequest_, o);
	}
	public static BigDecimal staticSetTemperature(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setTemperature(Double o) {
		setTemperature(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setTemperature(Integer o) {
		setTemperature(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setTemperature(Number o) {
		setTemperature(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved temperatureInit() {
		Wrap<BigDecimal> temperatureWrap = new Wrap<BigDecimal>().var("temperature");
		if(temperature == null) {
			_temperature(temperatureWrap);
			Optional.ofNullable(temperatureWrap.getO()).ifPresent(o -> {
				setTemperature(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchTemperature(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrTemperature(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTemperature(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchTemperature(siteRequest_, WeatherObserved.staticSetTemperature(siteRequest_, o)).toString();
	}

	public BigDecimal sqlTemperature() {
		return temperature;
	}

	////////////////////
	// solarRadiation //
	////////////////////


	/**	 The entity solarRadiation
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal solarRadiation;

	/**	<br> The entity solarRadiation
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:solarRadiation">Find the entity solarRadiation in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solarRadiation(Wrap<BigDecimal> w);

	public BigDecimal getSolarRadiation() {
		return solarRadiation;
	}

	public void setSolarRadiation(BigDecimal solarRadiation) {
		this.solarRadiation = solarRadiation;
	}
	@JsonIgnore
	public void setSolarRadiation(String o) {
		this.solarRadiation = WeatherObserved.staticSetSolarRadiation(siteRequest_, o);
	}
	public static BigDecimal staticSetSolarRadiation(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setSolarRadiation(Double o) {
		setSolarRadiation(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setSolarRadiation(Integer o) {
		setSolarRadiation(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setSolarRadiation(Number o) {
		setSolarRadiation(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved solarRadiationInit() {
		Wrap<BigDecimal> solarRadiationWrap = new Wrap<BigDecimal>().var("solarRadiation");
		if(solarRadiation == null) {
			_solarRadiation(solarRadiationWrap);
			Optional.ofNullable(solarRadiationWrap.getO()).ifPresent(o -> {
				setSolarRadiation(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchSolarRadiation(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrSolarRadiation(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolarRadiation(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchSolarRadiation(siteRequest_, WeatherObserved.staticSetSolarRadiation(siteRequest_, o)).toString();
	}

	public BigDecimal sqlSolarRadiation() {
		return solarRadiation;
	}

	/////////////////
	// streamGauge //
	/////////////////


	/**	 The entity streamGauge
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal streamGauge;

	/**	<br> The entity streamGauge
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:streamGauge">Find the entity streamGauge in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _streamGauge(Wrap<BigDecimal> w);

	public BigDecimal getStreamGauge() {
		return streamGauge;
	}

	public void setStreamGauge(BigDecimal streamGauge) {
		this.streamGauge = streamGauge;
	}
	@JsonIgnore
	public void setStreamGauge(String o) {
		this.streamGauge = WeatherObserved.staticSetStreamGauge(siteRequest_, o);
	}
	public static BigDecimal staticSetStreamGauge(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setStreamGauge(Double o) {
		setStreamGauge(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setStreamGauge(Integer o) {
		setStreamGauge(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setStreamGauge(Number o) {
		setStreamGauge(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved streamGaugeInit() {
		Wrap<BigDecimal> streamGaugeWrap = new Wrap<BigDecimal>().var("streamGauge");
		if(streamGauge == null) {
			_streamGauge(streamGaugeWrap);
			Optional.ofNullable(streamGaugeWrap.getO()).ifPresent(o -> {
				setStreamGauge(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchStreamGauge(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrStreamGauge(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqStreamGauge(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchStreamGauge(siteRequest_, WeatherObserved.staticSetStreamGauge(siteRequest_, o)).toString();
	}

	public BigDecimal sqlStreamGauge() {
		return streamGauge;
	}

	////////////////
	// uVIndexMax //
	////////////////


	/**	 The entity uVIndexMax
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal uVIndexMax;

	/**	<br> The entity uVIndexMax
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:uVIndexMax">Find the entity uVIndexMax in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _uVIndexMax(Wrap<BigDecimal> w);

	public BigDecimal getUVIndexMax() {
		return uVIndexMax;
	}

	public void setUVIndexMax(BigDecimal uVIndexMax) {
		this.uVIndexMax = uVIndexMax;
	}
	@JsonIgnore
	public void setUVIndexMax(String o) {
		this.uVIndexMax = WeatherObserved.staticSetUVIndexMax(siteRequest_, o);
	}
	public static BigDecimal staticSetUVIndexMax(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setUVIndexMax(Double o) {
		setUVIndexMax(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setUVIndexMax(Integer o) {
		setUVIndexMax(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setUVIndexMax(Number o) {
		setUVIndexMax(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved uVIndexMaxInit() {
		Wrap<BigDecimal> uVIndexMaxWrap = new Wrap<BigDecimal>().var("uVIndexMax");
		if(uVIndexMax == null) {
			_uVIndexMax(uVIndexMaxWrap);
			Optional.ofNullable(uVIndexMaxWrap.getO()).ifPresent(o -> {
				setUVIndexMax(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchUVIndexMax(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrUVIndexMax(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUVIndexMax(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchUVIndexMax(siteRequest_, WeatherObserved.staticSetUVIndexMax(siteRequest_, o)).toString();
	}

	public BigDecimal sqlUVIndexMax() {
		return uVIndexMax;
	}

	////////////////
	// visibility //
	////////////////


	/**	 The entity visibility
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject visibility;

	/**	<br> The entity visibility
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:visibility">Find the entity visibility in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _visibility(Wrap<JsonObject> w);

	public JsonObject getVisibility() {
		return visibility;
	}

	public void setVisibility(JsonObject visibility) {
		this.visibility = visibility;
	}
	@JsonIgnore
	public void setVisibility(String o) {
		this.visibility = WeatherObserved.staticSetVisibility(siteRequest_, o);
	}
	public static JsonObject staticSetVisibility(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected WeatherObserved visibilityInit() {
		Wrap<JsonObject> visibilityWrap = new Wrap<JsonObject>().var("visibility");
		if(visibility == null) {
			_visibility(visibilityWrap);
			Optional.ofNullable(visibilityWrap.getO()).ifPresent(o -> {
				setVisibility(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchVisibility(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrVisibility(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqVisibility(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchVisibility(siteRequest_, WeatherObserved.staticSetVisibility(siteRequest_, o)).toString();
	}

	public JsonObject sqlVisibility() {
		return visibility;
	}

	/////////////////
	// weatherType //
	/////////////////


	/**	 The entity weatherType
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String weatherType;

	/**	<br> The entity weatherType
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:weatherType">Find the entity weatherType in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _weatherType(Wrap<String> w);

	public String getWeatherType() {
		return weatherType;
	}
	public void setWeatherType(String o) {
		this.weatherType = WeatherObserved.staticSetWeatherType(siteRequest_, o);
	}
	public static String staticSetWeatherType(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved weatherTypeInit() {
		Wrap<String> weatherTypeWrap = new Wrap<String>().var("weatherType");
		if(weatherType == null) {
			_weatherType(weatherTypeWrap);
			Optional.ofNullable(weatherTypeWrap.getO()).ifPresent(o -> {
				setWeatherType(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchWeatherType(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrWeatherType(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqWeatherType(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchWeatherType(siteRequest_, WeatherObserved.staticSetWeatherType(siteRequest_, o)).toString();
	}

	public String sqlWeatherType() {
		return weatherType;
	}

	//////////////
	// dewPoint //
	//////////////


	/**	 The entity dewPoint
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal dewPoint;

	/**	<br> The entity dewPoint
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:dewPoint">Find the entity dewPoint in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dewPoint(Wrap<BigDecimal> w);

	public BigDecimal getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(BigDecimal dewPoint) {
		this.dewPoint = dewPoint;
	}
	@JsonIgnore
	public void setDewPoint(String o) {
		this.dewPoint = WeatherObserved.staticSetDewPoint(siteRequest_, o);
	}
	public static BigDecimal staticSetDewPoint(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setDewPoint(Double o) {
		setDewPoint(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setDewPoint(Integer o) {
		setDewPoint(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setDewPoint(Number o) {
		setDewPoint(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved dewPointInit() {
		Wrap<BigDecimal> dewPointWrap = new Wrap<BigDecimal>().var("dewPoint");
		if(dewPoint == null) {
			_dewPoint(dewPointWrap);
			Optional.ofNullable(dewPointWrap.getO()).ifPresent(o -> {
				setDewPoint(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchDewPoint(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrDewPoint(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDewPoint(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchDewPoint(siteRequest_, WeatherObserved.staticSetDewPoint(siteRequest_, o)).toString();
	}

	public BigDecimal sqlDewPoint() {
		return dewPoint;
	}

	////////////////////////
	// diffuseIrradiation //
	////////////////////////


	/**	 The entity diffuseIrradiation
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal diffuseIrradiation;

	/**	<br> The entity diffuseIrradiation
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:diffuseIrradiation">Find the entity diffuseIrradiation in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _diffuseIrradiation(Wrap<BigDecimal> w);

	public BigDecimal getDiffuseIrradiation() {
		return diffuseIrradiation;
	}

	public void setDiffuseIrradiation(BigDecimal diffuseIrradiation) {
		this.diffuseIrradiation = diffuseIrradiation;
	}
	@JsonIgnore
	public void setDiffuseIrradiation(String o) {
		this.diffuseIrradiation = WeatherObserved.staticSetDiffuseIrradiation(siteRequest_, o);
	}
	public static BigDecimal staticSetDiffuseIrradiation(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setDiffuseIrradiation(Double o) {
		setDiffuseIrradiation(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setDiffuseIrradiation(Integer o) {
		setDiffuseIrradiation(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setDiffuseIrradiation(Number o) {
		setDiffuseIrradiation(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved diffuseIrradiationInit() {
		Wrap<BigDecimal> diffuseIrradiationWrap = new Wrap<BigDecimal>().var("diffuseIrradiation");
		if(diffuseIrradiation == null) {
			_diffuseIrradiation(diffuseIrradiationWrap);
			Optional.ofNullable(diffuseIrradiationWrap.getO()).ifPresent(o -> {
				setDiffuseIrradiation(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchDiffuseIrradiation(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrDiffuseIrradiation(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDiffuseIrradiation(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchDiffuseIrradiation(siteRequest_, WeatherObserved.staticSetDiffuseIrradiation(siteRequest_, o)).toString();
	}

	public BigDecimal sqlDiffuseIrradiation() {
		return diffuseIrradiation;
	}

	///////////////////////
	// directIrradiation //
	///////////////////////


	/**	 The entity directIrradiation
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal directIrradiation;

	/**	<br> The entity directIrradiation
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:directIrradiation">Find the entity directIrradiation in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _directIrradiation(Wrap<BigDecimal> w);

	public BigDecimal getDirectIrradiation() {
		return directIrradiation;
	}

	public void setDirectIrradiation(BigDecimal directIrradiation) {
		this.directIrradiation = directIrradiation;
	}
	@JsonIgnore
	public void setDirectIrradiation(String o) {
		this.directIrradiation = WeatherObserved.staticSetDirectIrradiation(siteRequest_, o);
	}
	public static BigDecimal staticSetDirectIrradiation(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setDirectIrradiation(Double o) {
		setDirectIrradiation(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setDirectIrradiation(Integer o) {
		setDirectIrradiation(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setDirectIrradiation(Number o) {
		setDirectIrradiation(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved directIrradiationInit() {
		Wrap<BigDecimal> directIrradiationWrap = new Wrap<BigDecimal>().var("directIrradiation");
		if(directIrradiation == null) {
			_directIrradiation(directIrradiationWrap);
			Optional.ofNullable(directIrradiationWrap.getO()).ifPresent(o -> {
				setDirectIrradiation(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchDirectIrradiation(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrDirectIrradiation(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDirectIrradiation(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchDirectIrradiation(siteRequest_, WeatherObserved.staticSetDirectIrradiation(siteRequest_, o)).toString();
	}

	public BigDecimal sqlDirectIrradiation() {
		return directIrradiation;
	}

	/////////////////
	// illuminance //
	/////////////////


	/**	 The entity illuminance
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal illuminance;

	/**	<br> The entity illuminance
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:illuminance">Find the entity illuminance in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _illuminance(Wrap<BigDecimal> w);

	public BigDecimal getIlluminance() {
		return illuminance;
	}

	public void setIlluminance(BigDecimal illuminance) {
		this.illuminance = illuminance;
	}
	@JsonIgnore
	public void setIlluminance(String o) {
		this.illuminance = WeatherObserved.staticSetIlluminance(siteRequest_, o);
	}
	public static BigDecimal staticSetIlluminance(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setIlluminance(Double o) {
		setIlluminance(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setIlluminance(Integer o) {
		setIlluminance(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setIlluminance(Number o) {
		setIlluminance(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected WeatherObserved illuminanceInit() {
		Wrap<BigDecimal> illuminanceWrap = new Wrap<BigDecimal>().var("illuminance");
		if(illuminance == null) {
			_illuminance(illuminanceWrap);
			Optional.ofNullable(illuminanceWrap.getO()).ifPresent(o -> {
				setIlluminance(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static Double staticSearchIlluminance(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrIlluminance(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqIlluminance(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchIlluminance(siteRequest_, WeatherObserved.staticSetIlluminance(siteRequest_, o)).toString();
	}

	public BigDecimal sqlIlluminance() {
		return illuminance;
	}

	//////////////////
	// ngsildTenant //
	//////////////////


	/**	 The entity ngsildTenant
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String ngsildTenant;

	/**	<br> The entity ngsildTenant
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:ngsildTenant">Find the entity ngsildTenant in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildTenant(Wrap<String> w);

	public String getNgsildTenant() {
		return ngsildTenant;
	}
	public void setNgsildTenant(String o) {
		this.ngsildTenant = WeatherObserved.staticSetNgsildTenant(siteRequest_, o);
	}
	public static String staticSetNgsildTenant(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved ngsildTenantInit() {
		Wrap<String> ngsildTenantWrap = new Wrap<String>().var("ngsildTenant");
		if(ngsildTenant == null) {
			_ngsildTenant(ngsildTenantWrap);
			Optional.ofNullable(ngsildTenantWrap.getO()).ifPresent(o -> {
				setNgsildTenant(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchNgsildTenant(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildTenant(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildTenant(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchNgsildTenant(siteRequest_, WeatherObserved.staticSetNgsildTenant(siteRequest_, o)).toString();
	}

	public String sqlNgsildTenant() {
		return ngsildTenant;
	}

	////////////////
	// ngsildPath //
	////////////////


	/**	 The entity ngsildPath
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String ngsildPath;

	/**	<br> The entity ngsildPath
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:ngsildPath">Find the entity ngsildPath in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildPath(Wrap<String> w);

	public String getNgsildPath() {
		return ngsildPath;
	}
	public void setNgsildPath(String o) {
		this.ngsildPath = WeatherObserved.staticSetNgsildPath(siteRequest_, o);
	}
	public static String staticSetNgsildPath(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved ngsildPathInit() {
		Wrap<String> ngsildPathWrap = new Wrap<String>().var("ngsildPath");
		if(ngsildPath == null) {
			_ngsildPath(ngsildPathWrap);
			Optional.ofNullable(ngsildPathWrap.getO()).ifPresent(o -> {
				setNgsildPath(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchNgsildPath(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildPath(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildPath(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchNgsildPath(siteRequest_, WeatherObserved.staticSetNgsildPath(siteRequest_, o)).toString();
	}

	public String sqlNgsildPath() {
		return ngsildPath;
	}

	////////////////
	// ngsildData //
	////////////////


	/**	 The entity ngsildData
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject ngsildData;

	/**	<br> The entity ngsildData
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:ngsildData">Find the entity ngsildData in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildData(Wrap<JsonObject> w);

	public JsonObject getNgsildData() {
		return ngsildData;
	}

	public void setNgsildData(JsonObject ngsildData) {
		this.ngsildData = ngsildData;
	}
	@JsonIgnore
	public void setNgsildData(String o) {
		this.ngsildData = WeatherObserved.staticSetNgsildData(siteRequest_, o);
	}
	public static JsonObject staticSetNgsildData(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected WeatherObserved ngsildDataInit() {
		Wrap<JsonObject> ngsildDataWrap = new Wrap<JsonObject>().var("ngsildData");
		if(ngsildData == null) {
			_ngsildData(ngsildDataWrap);
			Optional.ofNullable(ngsildDataWrap.getO()).ifPresent(o -> {
				setNgsildData(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchNgsildData(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrNgsildData(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildData(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchNgsildData(siteRequest_, WeatherObserved.staticSetNgsildData(siteRequest_, o)).toString();
	}

	public JsonObject sqlNgsildData() {
		return ngsildData;
	}

	//////////////
	// entityId //
	//////////////


	/**	 The entity entityId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String entityId;

	/**	<br> The entity entityId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:entityId">Find the entity entityId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _entityId(Wrap<String> w);

	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String o) {
		this.entityId = WeatherObserved.staticSetEntityId(siteRequest_, o);
	}
	public static String staticSetEntityId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved entityIdInit() {
		Wrap<String> entityIdWrap = new Wrap<String>().var("entityId");
		if(entityId == null) {
			_entityId(entityIdWrap);
			Optional.ofNullable(entityIdWrap.getO()).ifPresent(o -> {
				setEntityId(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchEntityId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEntityId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEntityId(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchEntityId(siteRequest_, WeatherObserved.staticSetEntityId(siteRequest_, o)).toString();
	}

	public String sqlEntityId() {
		return entityId;
	}

	///////////////////
	// entityShortId //
	///////////////////


	/**	 The entity entityShortId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String entityShortId;

	/**	<br> The entity entityShortId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:entityShortId">Find the entity entityShortId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _entityShortId(Wrap<String> w);

	public String getEntityShortId() {
		return entityShortId;
	}
	public void setEntityShortId(String o) {
		this.entityShortId = WeatherObserved.staticSetEntityShortId(siteRequest_, o);
	}
	public static String staticSetEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved entityShortIdInit() {
		Wrap<String> entityShortIdWrap = new Wrap<String>().var("entityShortId");
		if(entityShortId == null) {
			_entityShortId(entityShortIdWrap);
			Optional.ofNullable(entityShortIdWrap.getO()).ifPresent(o -> {
				setEntityShortId(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchEntityShortId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEntityShortId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEntityShortId(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchEntityShortId(siteRequest_, WeatherObserved.staticSetEntityShortId(siteRequest_, o)).toString();
	}

	///////////////////
	// ngsildContext //
	///////////////////


	/**	 The entity ngsildContext
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String ngsildContext;

	/**	<br> The entity ngsildContext
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObserved&fq=entiteVar_enUS_indexed_string:ngsildContext">Find the entity ngsildContext in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildContext(Wrap<String> w);

	public String getNgsildContext() {
		return ngsildContext;
	}
	public void setNgsildContext(String o) {
		this.ngsildContext = WeatherObserved.staticSetNgsildContext(siteRequest_, o);
	}
	public static String staticSetNgsildContext(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObserved ngsildContextInit() {
		Wrap<String> ngsildContextWrap = new Wrap<String>().var("ngsildContext");
		if(ngsildContext == null) {
			_ngsildContext(ngsildContextWrap);
			Optional.ofNullable(ngsildContextWrap.getO()).ifPresent(o -> {
				setNgsildContext(o);
			});
		}
		return (WeatherObserved)this;
	}

	public static String staticSearchNgsildContext(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildContext(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildContext(SiteRequest siteRequest_, String o) {
		return WeatherObserved.staticSearchNgsildContext(siteRequest_, WeatherObserved.staticSetNgsildContext(siteRequest_, o)).toString();
	}

	public String sqlNgsildContext() {
		return ngsildContext;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<WeatherObservedGen<DEV>> promiseDeepWeatherObserved(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepWeatherObserved();
	}

	public Future<WeatherObservedGen<DEV>> promiseDeepWeatherObserved() {
		Promise<WeatherObservedGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseWeatherObserved(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseModel(siteRequest_).onSuccess(b -> {
				promise.complete(this);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseWeatherObserved(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				nameInit();
				descriptionInit();
				addressInit();
				alternateNameInit();
				locationInit();
				areaServedColorsInit();
				areaServedTitlesInit();
				areaServedLinksInit();
				areaServedInit();
				dataProviderInit();
				dateCreatedInit();
				dateModifiedInit();
				dateObservedInit();
				ownerInit();
				refDeviceInit();
				refPointOfInterestInit();
				sourceInit();
				seeAlsoInit();
				airQualityIndexInit();
				airQualityIndexForecastInit();
				aqiMajorPollutantInit();
				aqiMajorPollutantForecastInit();
				atmosphericPressureInit();
				pressureTendencyInit();
				gustSpeedInit();
				windDirectionInit();
				windSpeedInit();
				precipitationInit();
				precipitationForecastInit();
				relativeHumidityInit();
				relativeHumidityForecastInit();
				snowHeightInit();
				airTemperatureForecastInit();
				airTemperatureTSAInit();
				feelsLikeTemperatureInit();
				temperatureInit();
				solarRadiationInit();
				streamGaugeInit();
				uVIndexMaxInit();
				visibilityInit();
				weatherTypeInit();
				dewPointInit();
				diffuseIrradiationInit();
				directIrradiationInit();
				illuminanceInit();
				ngsildTenantInit();
				ngsildPathInit();
				ngsildDataInit();
				entityIdInit();
				entityShortIdInit();
				ngsildContextInit();
				promise2.complete();
			} catch(Exception ex) {
				promise2.fail(ex);
			}
			return promise2.future();
		}).onSuccess(a -> {
			promise.complete();
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	@Override public Future<? extends WeatherObservedGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepWeatherObserved(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestWeatherObserved(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestWeatherObserved(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainWeatherObserved(v);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.obtainForClass(v);
			}
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtainWeatherObserved(String var) {
		WeatherObserved oWeatherObserved = (WeatherObserved)this;
		switch(var) {
			case "name":
				return oWeatherObserved.name;
			case "description":
				return oWeatherObserved.description;
			case "address":
				return oWeatherObserved.address;
			case "alternateName":
				return oWeatherObserved.alternateName;
			case "location":
				return oWeatherObserved.location;
			case "areaServedColors":
				return oWeatherObserved.areaServedColors;
			case "areaServedTitles":
				return oWeatherObserved.areaServedTitles;
			case "areaServedLinks":
				return oWeatherObserved.areaServedLinks;
			case "areaServed":
				return oWeatherObserved.areaServed;
			case "dataProvider":
				return oWeatherObserved.dataProvider;
			case "dateCreated":
				return oWeatherObserved.dateCreated;
			case "dateModified":
				return oWeatherObserved.dateModified;
			case "dateObserved":
				return oWeatherObserved.dateObserved;
			case "owner":
				return oWeatherObserved.owner;
			case "refDevice":
				return oWeatherObserved.refDevice;
			case "refPointOfInterest":
				return oWeatherObserved.refPointOfInterest;
			case "source":
				return oWeatherObserved.source;
			case "seeAlso":
				return oWeatherObserved.seeAlso;
			case "airQualityIndex":
				return oWeatherObserved.airQualityIndex;
			case "airQualityIndexForecast":
				return oWeatherObserved.airQualityIndexForecast;
			case "aqiMajorPollutant":
				return oWeatherObserved.aqiMajorPollutant;
			case "aqiMajorPollutantForecast":
				return oWeatherObserved.aqiMajorPollutantForecast;
			case "atmosphericPressure":
				return oWeatherObserved.atmosphericPressure;
			case "pressureTendency":
				return oWeatherObserved.pressureTendency;
			case "gustSpeed":
				return oWeatherObserved.gustSpeed;
			case "windDirection":
				return oWeatherObserved.windDirection;
			case "windSpeed":
				return oWeatherObserved.windSpeed;
			case "precipitation":
				return oWeatherObserved.precipitation;
			case "precipitationForecast":
				return oWeatherObserved.precipitationForecast;
			case "relativeHumidity":
				return oWeatherObserved.relativeHumidity;
			case "relativeHumidityForecast":
				return oWeatherObserved.relativeHumidityForecast;
			case "snowHeight":
				return oWeatherObserved.snowHeight;
			case "airTemperatureForecast":
				return oWeatherObserved.airTemperatureForecast;
			case "airTemperatureTSA":
				return oWeatherObserved.airTemperatureTSA;
			case "feelsLikeTemperature":
				return oWeatherObserved.feelsLikeTemperature;
			case "temperature":
				return oWeatherObserved.temperature;
			case "solarRadiation":
				return oWeatherObserved.solarRadiation;
			case "streamGauge":
				return oWeatherObserved.streamGauge;
			case "uVIndexMax":
				return oWeatherObserved.uVIndexMax;
			case "visibility":
				return oWeatherObserved.visibility;
			case "weatherType":
				return oWeatherObserved.weatherType;
			case "dewPoint":
				return oWeatherObserved.dewPoint;
			case "diffuseIrradiation":
				return oWeatherObserved.diffuseIrradiation;
			case "directIrradiation":
				return oWeatherObserved.directIrradiation;
			case "illuminance":
				return oWeatherObserved.illuminance;
			case "ngsildTenant":
				return oWeatherObserved.ngsildTenant;
			case "ngsildPath":
				return oWeatherObserved.ngsildPath;
			case "ngsildData":
				return oWeatherObserved.ngsildData;
			case "entityId":
				return oWeatherObserved.entityId;
			case "entityShortId":
				return oWeatherObserved.entityShortId;
			case "ngsildContext":
				return oWeatherObserved.ngsildContext;
			default:
				return super.obtainBaseModel(var);
		}
	}

	///////////////
	// relate //
	///////////////

	@Override public boolean relateForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = relateWeatherObserved(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateWeatherObserved(String var, Object val) {
		WeatherObserved oWeatherObserved = (WeatherObserved)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetWeatherObserved(entityVar,  siteRequest_, o);
	}
	public static Object staticSetWeatherObserved(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return WeatherObserved.staticSetName(siteRequest_, o);
		case "description":
			return WeatherObserved.staticSetDescription(siteRequest_, o);
		case "address":
			return WeatherObserved.staticSetAddress(siteRequest_, o);
		case "alternateName":
			return WeatherObserved.staticSetAlternateName(siteRequest_, o);
		case "location":
			return WeatherObserved.staticSetLocation(siteRequest_, o);
		case "areaServedColors":
			return WeatherObserved.staticSetAreaServedColors(siteRequest_, o);
		case "areaServedTitles":
			return WeatherObserved.staticSetAreaServedTitles(siteRequest_, o);
		case "areaServedLinks":
			return WeatherObserved.staticSetAreaServedLinks(siteRequest_, o);
		case "areaServed":
			return WeatherObserved.staticSetAreaServed(siteRequest_, o);
		case "dataProvider":
			return WeatherObserved.staticSetDataProvider(siteRequest_, o);
		case "dateCreated":
			return WeatherObserved.staticSetDateCreated(siteRequest_, o);
		case "dateModified":
			return WeatherObserved.staticSetDateModified(siteRequest_, o);
		case "dateObserved":
			return WeatherObserved.staticSetDateObserved(siteRequest_, o);
		case "owner":
			return WeatherObserved.staticSetOwner(siteRequest_, o);
		case "refDevice":
			return WeatherObserved.staticSetRefDevice(siteRequest_, o);
		case "refPointOfInterest":
			return WeatherObserved.staticSetRefPointOfInterest(siteRequest_, o);
		case "source":
			return WeatherObserved.staticSetSource(siteRequest_, o);
		case "seeAlso":
			return WeatherObserved.staticSetSeeAlso(siteRequest_, o);
		case "airQualityIndex":
			return WeatherObserved.staticSetAirQualityIndex(siteRequest_, o);
		case "airQualityIndexForecast":
			return WeatherObserved.staticSetAirQualityIndexForecast(siteRequest_, o);
		case "aqiMajorPollutant":
			return WeatherObserved.staticSetAqiMajorPollutant(siteRequest_, o);
		case "aqiMajorPollutantForecast":
			return WeatherObserved.staticSetAqiMajorPollutantForecast(siteRequest_, o);
		case "atmosphericPressure":
			return WeatherObserved.staticSetAtmosphericPressure(siteRequest_, o);
		case "pressureTendency":
			return WeatherObserved.staticSetPressureTendency(siteRequest_, o);
		case "gustSpeed":
			return WeatherObserved.staticSetGustSpeed(siteRequest_, o);
		case "windDirection":
			return WeatherObserved.staticSetWindDirection(siteRequest_, o);
		case "windSpeed":
			return WeatherObserved.staticSetWindSpeed(siteRequest_, o);
		case "precipitation":
			return WeatherObserved.staticSetPrecipitation(siteRequest_, o);
		case "precipitationForecast":
			return WeatherObserved.staticSetPrecipitationForecast(siteRequest_, o);
		case "relativeHumidity":
			return WeatherObserved.staticSetRelativeHumidity(siteRequest_, o);
		case "relativeHumidityForecast":
			return WeatherObserved.staticSetRelativeHumidityForecast(siteRequest_, o);
		case "snowHeight":
			return WeatherObserved.staticSetSnowHeight(siteRequest_, o);
		case "airTemperatureForecast":
			return WeatherObserved.staticSetAirTemperatureForecast(siteRequest_, o);
		case "airTemperatureTSA":
			return WeatherObserved.staticSetAirTemperatureTSA(siteRequest_, o);
		case "feelsLikeTemperature":
			return WeatherObserved.staticSetFeelsLikeTemperature(siteRequest_, o);
		case "temperature":
			return WeatherObserved.staticSetTemperature(siteRequest_, o);
		case "solarRadiation":
			return WeatherObserved.staticSetSolarRadiation(siteRequest_, o);
		case "streamGauge":
			return WeatherObserved.staticSetStreamGauge(siteRequest_, o);
		case "uVIndexMax":
			return WeatherObserved.staticSetUVIndexMax(siteRequest_, o);
		case "visibility":
			return WeatherObserved.staticSetVisibility(siteRequest_, o);
		case "weatherType":
			return WeatherObserved.staticSetWeatherType(siteRequest_, o);
		case "dewPoint":
			return WeatherObserved.staticSetDewPoint(siteRequest_, o);
		case "diffuseIrradiation":
			return WeatherObserved.staticSetDiffuseIrradiation(siteRequest_, o);
		case "directIrradiation":
			return WeatherObserved.staticSetDirectIrradiation(siteRequest_, o);
		case "illuminance":
			return WeatherObserved.staticSetIlluminance(siteRequest_, o);
		case "ngsildTenant":
			return WeatherObserved.staticSetNgsildTenant(siteRequest_, o);
		case "ngsildPath":
			return WeatherObserved.staticSetNgsildPath(siteRequest_, o);
		case "ngsildData":
			return WeatherObserved.staticSetNgsildData(siteRequest_, o);
		case "entityId":
			return WeatherObserved.staticSetEntityId(siteRequest_, o);
		case "entityShortId":
			return WeatherObserved.staticSetEntityShortId(siteRequest_, o);
		case "ngsildContext":
			return WeatherObserved.staticSetNgsildContext(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchWeatherObserved(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchWeatherObserved(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return WeatherObserved.staticSearchName(siteRequest_, (String)o);
		case "description":
			return WeatherObserved.staticSearchDescription(siteRequest_, (String)o);
		case "address":
			return WeatherObserved.staticSearchAddress(siteRequest_, (JsonObject)o);
		case "alternateName":
			return WeatherObserved.staticSearchAlternateName(siteRequest_, (String)o);
		case "location":
			return WeatherObserved.staticSearchLocation(siteRequest_, (Point)o);
		case "areaServedColors":
			return WeatherObserved.staticSearchAreaServedColors(siteRequest_, (String)o);
		case "areaServedTitles":
			return WeatherObserved.staticSearchAreaServedTitles(siteRequest_, (String)o);
		case "areaServedLinks":
			return WeatherObserved.staticSearchAreaServedLinks(siteRequest_, (String)o);
		case "areaServed":
			return WeatherObserved.staticSearchAreaServed(siteRequest_, (Polygon)o);
		case "dataProvider":
			return WeatherObserved.staticSearchDataProvider(siteRequest_, (String)o);
		case "dateCreated":
			return WeatherObserved.staticSearchDateCreated(siteRequest_, (String)o);
		case "dateModified":
			return WeatherObserved.staticSearchDateModified(siteRequest_, (String)o);
		case "dateObserved":
			return WeatherObserved.staticSearchDateObserved(siteRequest_, (String)o);
		case "owner":
			return WeatherObserved.staticSearchOwner(siteRequest_, (JsonObject)o);
		case "refDevice":
			return WeatherObserved.staticSearchRefDevice(siteRequest_, (JsonObject)o);
		case "refPointOfInterest":
			return WeatherObserved.staticSearchRefPointOfInterest(siteRequest_, (String)o);
		case "source":
			return WeatherObserved.staticSearchSource(siteRequest_, (String)o);
		case "seeAlso":
			return WeatherObserved.staticSearchSeeAlso(siteRequest_, (JsonObject)o);
		case "airQualityIndex":
			return WeatherObserved.staticSearchAirQualityIndex(siteRequest_, (BigDecimal)o);
		case "airQualityIndexForecast":
			return WeatherObserved.staticSearchAirQualityIndexForecast(siteRequest_, (BigDecimal)o);
		case "aqiMajorPollutant":
			return WeatherObserved.staticSearchAqiMajorPollutant(siteRequest_, (String)o);
		case "aqiMajorPollutantForecast":
			return WeatherObserved.staticSearchAqiMajorPollutantForecast(siteRequest_, (String)o);
		case "atmosphericPressure":
			return WeatherObserved.staticSearchAtmosphericPressure(siteRequest_, (BigDecimal)o);
		case "pressureTendency":
			return WeatherObserved.staticSearchPressureTendency(siteRequest_, (JsonObject)o);
		case "gustSpeed":
			return WeatherObserved.staticSearchGustSpeed(siteRequest_, (BigDecimal)o);
		case "windDirection":
			return WeatherObserved.staticSearchWindDirection(siteRequest_, (BigDecimal)o);
		case "windSpeed":
			return WeatherObserved.staticSearchWindSpeed(siteRequest_, (BigDecimal)o);
		case "precipitation":
			return WeatherObserved.staticSearchPrecipitation(siteRequest_, (BigDecimal)o);
		case "precipitationForecast":
			return WeatherObserved.staticSearchPrecipitationForecast(siteRequest_, (BigDecimal)o);
		case "relativeHumidity":
			return WeatherObserved.staticSearchRelativeHumidity(siteRequest_, (BigDecimal)o);
		case "relativeHumidityForecast":
			return WeatherObserved.staticSearchRelativeHumidityForecast(siteRequest_, (BigDecimal)o);
		case "snowHeight":
			return WeatherObserved.staticSearchSnowHeight(siteRequest_, (BigDecimal)o);
		case "airTemperatureForecast":
			return WeatherObserved.staticSearchAirTemperatureForecast(siteRequest_, (BigDecimal)o);
		case "airTemperatureTSA":
			return WeatherObserved.staticSearchAirTemperatureTSA(siteRequest_, (JsonObject)o);
		case "feelsLikeTemperature":
			return WeatherObserved.staticSearchFeelsLikeTemperature(siteRequest_, (BigDecimal)o);
		case "temperature":
			return WeatherObserved.staticSearchTemperature(siteRequest_, (BigDecimal)o);
		case "solarRadiation":
			return WeatherObserved.staticSearchSolarRadiation(siteRequest_, (BigDecimal)o);
		case "streamGauge":
			return WeatherObserved.staticSearchStreamGauge(siteRequest_, (BigDecimal)o);
		case "uVIndexMax":
			return WeatherObserved.staticSearchUVIndexMax(siteRequest_, (BigDecimal)o);
		case "visibility":
			return WeatherObserved.staticSearchVisibility(siteRequest_, (JsonObject)o);
		case "weatherType":
			return WeatherObserved.staticSearchWeatherType(siteRequest_, (String)o);
		case "dewPoint":
			return WeatherObserved.staticSearchDewPoint(siteRequest_, (BigDecimal)o);
		case "diffuseIrradiation":
			return WeatherObserved.staticSearchDiffuseIrradiation(siteRequest_, (BigDecimal)o);
		case "directIrradiation":
			return WeatherObserved.staticSearchDirectIrradiation(siteRequest_, (BigDecimal)o);
		case "illuminance":
			return WeatherObserved.staticSearchIlluminance(siteRequest_, (BigDecimal)o);
		case "ngsildTenant":
			return WeatherObserved.staticSearchNgsildTenant(siteRequest_, (String)o);
		case "ngsildPath":
			return WeatherObserved.staticSearchNgsildPath(siteRequest_, (String)o);
		case "ngsildData":
			return WeatherObserved.staticSearchNgsildData(siteRequest_, (JsonObject)o);
		case "entityId":
			return WeatherObserved.staticSearchEntityId(siteRequest_, (String)o);
		case "entityShortId":
			return WeatherObserved.staticSearchEntityShortId(siteRequest_, (String)o);
		case "ngsildContext":
			return WeatherObserved.staticSearchNgsildContext(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrWeatherObserved(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrWeatherObserved(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return WeatherObserved.staticSearchStrName(siteRequest_, (String)o);
		case "description":
			return WeatherObserved.staticSearchStrDescription(siteRequest_, (String)o);
		case "address":
			return WeatherObserved.staticSearchStrAddress(siteRequest_, (String)o);
		case "alternateName":
			return WeatherObserved.staticSearchStrAlternateName(siteRequest_, (String)o);
		case "location":
			return WeatherObserved.staticSearchStrLocation(siteRequest_, (Point)o);
		case "areaServedColors":
			return WeatherObserved.staticSearchStrAreaServedColors(siteRequest_, (String)o);
		case "areaServedTitles":
			return WeatherObserved.staticSearchStrAreaServedTitles(siteRequest_, (String)o);
		case "areaServedLinks":
			return WeatherObserved.staticSearchStrAreaServedLinks(siteRequest_, (String)o);
		case "areaServed":
			return WeatherObserved.staticSearchStrAreaServed(siteRequest_, (Polygon)o);
		case "dataProvider":
			return WeatherObserved.staticSearchStrDataProvider(siteRequest_, (String)o);
		case "dateCreated":
			return WeatherObserved.staticSearchStrDateCreated(siteRequest_, (String)o);
		case "dateModified":
			return WeatherObserved.staticSearchStrDateModified(siteRequest_, (String)o);
		case "dateObserved":
			return WeatherObserved.staticSearchStrDateObserved(siteRequest_, (String)o);
		case "owner":
			return WeatherObserved.staticSearchStrOwner(siteRequest_, (String)o);
		case "refDevice":
			return WeatherObserved.staticSearchStrRefDevice(siteRequest_, (String)o);
		case "refPointOfInterest":
			return WeatherObserved.staticSearchStrRefPointOfInterest(siteRequest_, (String)o);
		case "source":
			return WeatherObserved.staticSearchStrSource(siteRequest_, (String)o);
		case "seeAlso":
			return WeatherObserved.staticSearchStrSeeAlso(siteRequest_, (String)o);
		case "airQualityIndex":
			return WeatherObserved.staticSearchStrAirQualityIndex(siteRequest_, (Double)o);
		case "airQualityIndexForecast":
			return WeatherObserved.staticSearchStrAirQualityIndexForecast(siteRequest_, (Double)o);
		case "aqiMajorPollutant":
			return WeatherObserved.staticSearchStrAqiMajorPollutant(siteRequest_, (String)o);
		case "aqiMajorPollutantForecast":
			return WeatherObserved.staticSearchStrAqiMajorPollutantForecast(siteRequest_, (String)o);
		case "atmosphericPressure":
			return WeatherObserved.staticSearchStrAtmosphericPressure(siteRequest_, (Double)o);
		case "pressureTendency":
			return WeatherObserved.staticSearchStrPressureTendency(siteRequest_, (String)o);
		case "gustSpeed":
			return WeatherObserved.staticSearchStrGustSpeed(siteRequest_, (Double)o);
		case "windDirection":
			return WeatherObserved.staticSearchStrWindDirection(siteRequest_, (Double)o);
		case "windSpeed":
			return WeatherObserved.staticSearchStrWindSpeed(siteRequest_, (Double)o);
		case "precipitation":
			return WeatherObserved.staticSearchStrPrecipitation(siteRequest_, (Double)o);
		case "precipitationForecast":
			return WeatherObserved.staticSearchStrPrecipitationForecast(siteRequest_, (Double)o);
		case "relativeHumidity":
			return WeatherObserved.staticSearchStrRelativeHumidity(siteRequest_, (Double)o);
		case "relativeHumidityForecast":
			return WeatherObserved.staticSearchStrRelativeHumidityForecast(siteRequest_, (Double)o);
		case "snowHeight":
			return WeatherObserved.staticSearchStrSnowHeight(siteRequest_, (Double)o);
		case "airTemperatureForecast":
			return WeatherObserved.staticSearchStrAirTemperatureForecast(siteRequest_, (Double)o);
		case "airTemperatureTSA":
			return WeatherObserved.staticSearchStrAirTemperatureTSA(siteRequest_, (String)o);
		case "feelsLikeTemperature":
			return WeatherObserved.staticSearchStrFeelsLikeTemperature(siteRequest_, (Double)o);
		case "temperature":
			return WeatherObserved.staticSearchStrTemperature(siteRequest_, (Double)o);
		case "solarRadiation":
			return WeatherObserved.staticSearchStrSolarRadiation(siteRequest_, (Double)o);
		case "streamGauge":
			return WeatherObserved.staticSearchStrStreamGauge(siteRequest_, (Double)o);
		case "uVIndexMax":
			return WeatherObserved.staticSearchStrUVIndexMax(siteRequest_, (Double)o);
		case "visibility":
			return WeatherObserved.staticSearchStrVisibility(siteRequest_, (String)o);
		case "weatherType":
			return WeatherObserved.staticSearchStrWeatherType(siteRequest_, (String)o);
		case "dewPoint":
			return WeatherObserved.staticSearchStrDewPoint(siteRequest_, (Double)o);
		case "diffuseIrradiation":
			return WeatherObserved.staticSearchStrDiffuseIrradiation(siteRequest_, (Double)o);
		case "directIrradiation":
			return WeatherObserved.staticSearchStrDirectIrradiation(siteRequest_, (Double)o);
		case "illuminance":
			return WeatherObserved.staticSearchStrIlluminance(siteRequest_, (Double)o);
		case "ngsildTenant":
			return WeatherObserved.staticSearchStrNgsildTenant(siteRequest_, (String)o);
		case "ngsildPath":
			return WeatherObserved.staticSearchStrNgsildPath(siteRequest_, (String)o);
		case "ngsildData":
			return WeatherObserved.staticSearchStrNgsildData(siteRequest_, (String)o);
		case "entityId":
			return WeatherObserved.staticSearchStrEntityId(siteRequest_, (String)o);
		case "entityShortId":
			return WeatherObserved.staticSearchStrEntityShortId(siteRequest_, (String)o);
		case "ngsildContext":
			return WeatherObserved.staticSearchStrNgsildContext(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqWeatherObserved(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqWeatherObserved(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return WeatherObserved.staticSearchFqName(siteRequest_, o);
		case "description":
			return WeatherObserved.staticSearchFqDescription(siteRequest_, o);
		case "address":
			return WeatherObserved.staticSearchFqAddress(siteRequest_, o);
		case "alternateName":
			return WeatherObserved.staticSearchFqAlternateName(siteRequest_, o);
		case "location":
			return WeatherObserved.staticSearchFqLocation(siteRequest_, o);
		case "areaServedColors":
			return WeatherObserved.staticSearchFqAreaServedColors(siteRequest_, o);
		case "areaServedTitles":
			return WeatherObserved.staticSearchFqAreaServedTitles(siteRequest_, o);
		case "areaServedLinks":
			return WeatherObserved.staticSearchFqAreaServedLinks(siteRequest_, o);
		case "areaServed":
			return WeatherObserved.staticSearchFqAreaServed(siteRequest_, o);
		case "dataProvider":
			return WeatherObserved.staticSearchFqDataProvider(siteRequest_, o);
		case "dateCreated":
			return WeatherObserved.staticSearchFqDateCreated(siteRequest_, o);
		case "dateModified":
			return WeatherObserved.staticSearchFqDateModified(siteRequest_, o);
		case "dateObserved":
			return WeatherObserved.staticSearchFqDateObserved(siteRequest_, o);
		case "owner":
			return WeatherObserved.staticSearchFqOwner(siteRequest_, o);
		case "refDevice":
			return WeatherObserved.staticSearchFqRefDevice(siteRequest_, o);
		case "refPointOfInterest":
			return WeatherObserved.staticSearchFqRefPointOfInterest(siteRequest_, o);
		case "source":
			return WeatherObserved.staticSearchFqSource(siteRequest_, o);
		case "seeAlso":
			return WeatherObserved.staticSearchFqSeeAlso(siteRequest_, o);
		case "airQualityIndex":
			return WeatherObserved.staticSearchFqAirQualityIndex(siteRequest_, o);
		case "airQualityIndexForecast":
			return WeatherObserved.staticSearchFqAirQualityIndexForecast(siteRequest_, o);
		case "aqiMajorPollutant":
			return WeatherObserved.staticSearchFqAqiMajorPollutant(siteRequest_, o);
		case "aqiMajorPollutantForecast":
			return WeatherObserved.staticSearchFqAqiMajorPollutantForecast(siteRequest_, o);
		case "atmosphericPressure":
			return WeatherObserved.staticSearchFqAtmosphericPressure(siteRequest_, o);
		case "pressureTendency":
			return WeatherObserved.staticSearchFqPressureTendency(siteRequest_, o);
		case "gustSpeed":
			return WeatherObserved.staticSearchFqGustSpeed(siteRequest_, o);
		case "windDirection":
			return WeatherObserved.staticSearchFqWindDirection(siteRequest_, o);
		case "windSpeed":
			return WeatherObserved.staticSearchFqWindSpeed(siteRequest_, o);
		case "precipitation":
			return WeatherObserved.staticSearchFqPrecipitation(siteRequest_, o);
		case "precipitationForecast":
			return WeatherObserved.staticSearchFqPrecipitationForecast(siteRequest_, o);
		case "relativeHumidity":
			return WeatherObserved.staticSearchFqRelativeHumidity(siteRequest_, o);
		case "relativeHumidityForecast":
			return WeatherObserved.staticSearchFqRelativeHumidityForecast(siteRequest_, o);
		case "snowHeight":
			return WeatherObserved.staticSearchFqSnowHeight(siteRequest_, o);
		case "airTemperatureForecast":
			return WeatherObserved.staticSearchFqAirTemperatureForecast(siteRequest_, o);
		case "airTemperatureTSA":
			return WeatherObserved.staticSearchFqAirTemperatureTSA(siteRequest_, o);
		case "feelsLikeTemperature":
			return WeatherObserved.staticSearchFqFeelsLikeTemperature(siteRequest_, o);
		case "temperature":
			return WeatherObserved.staticSearchFqTemperature(siteRequest_, o);
		case "solarRadiation":
			return WeatherObserved.staticSearchFqSolarRadiation(siteRequest_, o);
		case "streamGauge":
			return WeatherObserved.staticSearchFqStreamGauge(siteRequest_, o);
		case "uVIndexMax":
			return WeatherObserved.staticSearchFqUVIndexMax(siteRequest_, o);
		case "visibility":
			return WeatherObserved.staticSearchFqVisibility(siteRequest_, o);
		case "weatherType":
			return WeatherObserved.staticSearchFqWeatherType(siteRequest_, o);
		case "dewPoint":
			return WeatherObserved.staticSearchFqDewPoint(siteRequest_, o);
		case "diffuseIrradiation":
			return WeatherObserved.staticSearchFqDiffuseIrradiation(siteRequest_, o);
		case "directIrradiation":
			return WeatherObserved.staticSearchFqDirectIrradiation(siteRequest_, o);
		case "illuminance":
			return WeatherObserved.staticSearchFqIlluminance(siteRequest_, o);
		case "ngsildTenant":
			return WeatherObserved.staticSearchFqNgsildTenant(siteRequest_, o);
		case "ngsildPath":
			return WeatherObserved.staticSearchFqNgsildPath(siteRequest_, o);
		case "ngsildData":
			return WeatherObserved.staticSearchFqNgsildData(siteRequest_, o);
		case "entityId":
			return WeatherObserved.staticSearchFqEntityId(siteRequest_, o);
		case "entityShortId":
			return WeatherObserved.staticSearchFqEntityShortId(siteRequest_, o);
		case "ngsildContext":
			return WeatherObserved.staticSearchFqNgsildContext(siteRequest_, o);
			default:
				return BaseModel.staticSearchFqBaseModel(entityVar,  siteRequest_, o);
		}
	}

	/////////////
	// persist //
	/////////////

	@Override public boolean persistForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = persistWeatherObserved(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistWeatherObserved(String var, Object val) {
		String varLower = var.toLowerCase();
			if("name".equals(varLower)) {
				if(val instanceof String) {
					setName((String)val);
				}
				saves.add("name");
				return val;
			} else if("description".equals(varLower)) {
				if(val instanceof String) {
					setDescription((String)val);
				}
				saves.add("description");
				return val;
			} else if("address".equals(varLower)) {
				if(val instanceof String) {
					setAddress((String)val);
				} else if(val instanceof JsonObject) {
					setAddress((JsonObject)val);
				}
				saves.add("address");
				return val;
			} else if("alternatename".equals(varLower)) {
				if(val instanceof String) {
					setAlternateName((String)val);
				}
				saves.add("alternateName");
				return val;
			} else if("location".equals(varLower)) {
				if(val instanceof String) {
					setLocation((String)val);
				} else if(val instanceof Point) {
					setLocation((Point)val);
				}
				saves.add("location");
				return val;
			} else if("areaserved".equals(varLower)) {
				if(val instanceof Polygon) {
					setAreaServed((Polygon)val);
				} else {
					setAreaServed(val == null ? null : val.toString());
				}
				saves.add("areaServed");
				return val;
			} else if("dataprovider".equals(varLower)) {
				if(val instanceof String) {
					setDataProvider((String)val);
				}
				saves.add("dataProvider");
				return val;
			} else if("datecreated".equals(varLower)) {
				if(val instanceof String) {
					setDateCreated((String)val);
				}
				saves.add("dateCreated");
				return val;
			} else if("datemodified".equals(varLower)) {
				if(val instanceof String) {
					setDateModified((String)val);
				}
				saves.add("dateModified");
				return val;
			} else if("dateobserved".equals(varLower)) {
				if(val instanceof String) {
					setDateObserved((String)val);
				}
				saves.add("dateObserved");
				return val;
			} else if("owner".equals(varLower)) {
				if(val instanceof String) {
					setOwner((String)val);
				} else if(val instanceof JsonObject) {
					setOwner((JsonObject)val);
				}
				saves.add("owner");
				return val;
			} else if("refdevice".equals(varLower)) {
				if(val instanceof String) {
					setRefDevice((String)val);
				} else if(val instanceof JsonObject) {
					setRefDevice((JsonObject)val);
				}
				saves.add("refDevice");
				return val;
			} else if("refpointofinterest".equals(varLower)) {
				if(val instanceof String) {
					setRefPointOfInterest((String)val);
				}
				saves.add("refPointOfInterest");
				return val;
			} else if("source".equals(varLower)) {
				if(val instanceof String) {
					setSource((String)val);
				}
				saves.add("source");
				return val;
			} else if("seealso".equals(varLower)) {
				if(val instanceof String) {
					setSeeAlso((String)val);
				} else if(val instanceof JsonObject) {
					setSeeAlso((JsonObject)val);
				}
				saves.add("seeAlso");
				return val;
			} else if("airqualityindex".equals(varLower)) {
				if(val instanceof String) {
					setAirQualityIndex((String)val);
				} else if(val instanceof Number) {
					setAirQualityIndex(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("airQualityIndex");
				return val;
			} else if("airqualityindexforecast".equals(varLower)) {
				if(val instanceof String) {
					setAirQualityIndexForecast((String)val);
				} else if(val instanceof Number) {
					setAirQualityIndexForecast(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("airQualityIndexForecast");
				return val;
			} else if("aqimajorpollutant".equals(varLower)) {
				if(val instanceof String) {
					setAqiMajorPollutant((String)val);
				}
				saves.add("aqiMajorPollutant");
				return val;
			} else if("aqimajorpollutantforecast".equals(varLower)) {
				if(val instanceof String) {
					setAqiMajorPollutantForecast((String)val);
				}
				saves.add("aqiMajorPollutantForecast");
				return val;
			} else if("atmosphericpressure".equals(varLower)) {
				if(val instanceof String) {
					setAtmosphericPressure((String)val);
				} else if(val instanceof Number) {
					setAtmosphericPressure(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("atmosphericPressure");
				return val;
			} else if("pressuretendency".equals(varLower)) {
				if(val instanceof String) {
					setPressureTendency((String)val);
				} else if(val instanceof JsonObject) {
					setPressureTendency((JsonObject)val);
				}
				saves.add("pressureTendency");
				return val;
			} else if("gustspeed".equals(varLower)) {
				if(val instanceof String) {
					setGustSpeed((String)val);
				} else if(val instanceof Number) {
					setGustSpeed(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("gustSpeed");
				return val;
			} else if("winddirection".equals(varLower)) {
				if(val instanceof String) {
					setWindDirection((String)val);
				} else if(val instanceof Number) {
					setWindDirection(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("windDirection");
				return val;
			} else if("windspeed".equals(varLower)) {
				if(val instanceof String) {
					setWindSpeed((String)val);
				} else if(val instanceof Number) {
					setWindSpeed(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("windSpeed");
				return val;
			} else if("precipitation".equals(varLower)) {
				if(val instanceof String) {
					setPrecipitation((String)val);
				} else if(val instanceof Number) {
					setPrecipitation(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("precipitation");
				return val;
			} else if("precipitationforecast".equals(varLower)) {
				if(val instanceof String) {
					setPrecipitationForecast((String)val);
				} else if(val instanceof Number) {
					setPrecipitationForecast(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("precipitationForecast");
				return val;
			} else if("relativehumidity".equals(varLower)) {
				if(val instanceof String) {
					setRelativeHumidity((String)val);
				} else if(val instanceof Number) {
					setRelativeHumidity(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("relativeHumidity");
				return val;
			} else if("relativehumidityforecast".equals(varLower)) {
				if(val instanceof String) {
					setRelativeHumidityForecast((String)val);
				} else if(val instanceof Number) {
					setRelativeHumidityForecast(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("relativeHumidityForecast");
				return val;
			} else if("snowheight".equals(varLower)) {
				if(val instanceof String) {
					setSnowHeight((String)val);
				} else if(val instanceof Number) {
					setSnowHeight(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("snowHeight");
				return val;
			} else if("airtemperatureforecast".equals(varLower)) {
				if(val instanceof String) {
					setAirTemperatureForecast((String)val);
				} else if(val instanceof Number) {
					setAirTemperatureForecast(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("airTemperatureForecast");
				return val;
			} else if("airtemperaturetsa".equals(varLower)) {
				if(val instanceof String) {
					setAirTemperatureTSA((String)val);
				} else if(val instanceof JsonObject) {
					setAirTemperatureTSA((JsonObject)val);
				}
				saves.add("airTemperatureTSA");
				return val;
			} else if("feelsliketemperature".equals(varLower)) {
				if(val instanceof String) {
					setFeelsLikeTemperature((String)val);
				} else if(val instanceof Number) {
					setFeelsLikeTemperature(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("feelsLikeTemperature");
				return val;
			} else if("temperature".equals(varLower)) {
				if(val instanceof String) {
					setTemperature((String)val);
				} else if(val instanceof Number) {
					setTemperature(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("temperature");
				return val;
			} else if("solarradiation".equals(varLower)) {
				if(val instanceof String) {
					setSolarRadiation((String)val);
				} else if(val instanceof Number) {
					setSolarRadiation(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("solarRadiation");
				return val;
			} else if("streamgauge".equals(varLower)) {
				if(val instanceof String) {
					setStreamGauge((String)val);
				} else if(val instanceof Number) {
					setStreamGauge(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("streamGauge");
				return val;
			} else if("uvindexmax".equals(varLower)) {
				if(val instanceof String) {
					setUVIndexMax((String)val);
				} else if(val instanceof Number) {
					setUVIndexMax(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("uVIndexMax");
				return val;
			} else if("visibility".equals(varLower)) {
				if(val instanceof String) {
					setVisibility((String)val);
				} else if(val instanceof JsonObject) {
					setVisibility((JsonObject)val);
				}
				saves.add("visibility");
				return val;
			} else if("weathertype".equals(varLower)) {
				if(val instanceof String) {
					setWeatherType((String)val);
				}
				saves.add("weatherType");
				return val;
			} else if("dewpoint".equals(varLower)) {
				if(val instanceof String) {
					setDewPoint((String)val);
				} else if(val instanceof Number) {
					setDewPoint(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("dewPoint");
				return val;
			} else if("diffuseirradiation".equals(varLower)) {
				if(val instanceof String) {
					setDiffuseIrradiation((String)val);
				} else if(val instanceof Number) {
					setDiffuseIrradiation(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("diffuseIrradiation");
				return val;
			} else if("directirradiation".equals(varLower)) {
				if(val instanceof String) {
					setDirectIrradiation((String)val);
				} else if(val instanceof Number) {
					setDirectIrradiation(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("directIrradiation");
				return val;
			} else if("illuminance".equals(varLower)) {
				if(val instanceof String) {
					setIlluminance((String)val);
				} else if(val instanceof Number) {
					setIlluminance(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("illuminance");
				return val;
			} else if("ngsildtenant".equals(varLower)) {
				if(val instanceof String) {
					setNgsildTenant((String)val);
				}
				saves.add("ngsildTenant");
				return val;
			} else if("ngsildpath".equals(varLower)) {
				if(val instanceof String) {
					setNgsildPath((String)val);
				}
				saves.add("ngsildPath");
				return val;
			} else if("ngsilddata".equals(varLower)) {
				if(val instanceof String) {
					setNgsildData((String)val);
				} else if(val instanceof JsonObject) {
					setNgsildData((JsonObject)val);
				}
				saves.add("ngsildData");
				return val;
			} else if("entityid".equals(varLower)) {
				if(val instanceof String) {
					setEntityId((String)val);
				}
				saves.add("entityId");
				return val;
			} else if("ngsildcontext".equals(varLower)) {
				if(val instanceof String) {
					setNgsildContext((String)val);
				}
				saves.add("ngsildContext");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateWeatherObserved(doc);
	}
	public void populateWeatherObserved(SolrResponse.Doc doc) {
		WeatherObserved oWeatherObserved = (WeatherObserved)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oWeatherObserved.setName(name);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oWeatherObserved.setDescription(description);
			}

			if(saves.contains("address")) {
				String address = (String)doc.get("address_docvalues_string");
				if(address != null)
					oWeatherObserved.setAddress(address);
			}

			if(saves.contains("alternateName")) {
				String alternateName = (String)doc.get("alternateName_docvalues_string");
				if(alternateName != null)
					oWeatherObserved.setAlternateName(alternateName);
			}

			if(saves.contains("location")) {
				Point location = (Point)doc.get("location_docvalues_location");
				if(location != null)
					oWeatherObserved.setLocation(location);
			}

			if(saves.contains("areaServedColors")) {
				List<String> areaServedColors = (List<String>)doc.get("areaServedColors_indexedstored_strings");
				if(areaServedColors != null)
					oWeatherObserved.areaServedColors.addAll(areaServedColors);
			}

			if(saves.contains("areaServedTitles")) {
				List<String> areaServedTitles = (List<String>)doc.get("areaServedTitles_indexedstored_strings");
				if(areaServedTitles != null)
					oWeatherObserved.areaServedTitles.addAll(areaServedTitles);
			}

			if(saves.contains("areaServedLinks")) {
				List<String> areaServedLinks = (List<String>)doc.get("areaServedLinks_indexedstored_strings");
				if(areaServedLinks != null)
					oWeatherObserved.areaServedLinks.addAll(areaServedLinks);
			}

			if(saves.contains("areaServed")) {
				Polygon areaServed = (Polygon)doc.get("areaServed_docvalues_location");
				if(areaServed != null)
					oWeatherObserved.setAreaServed(areaServed);
			}

			if(saves.contains("dataProvider")) {
				String dataProvider = (String)doc.get("dataProvider_docvalues_string");
				if(dataProvider != null)
					oWeatherObserved.setDataProvider(dataProvider);
			}

			if(saves.contains("dateCreated")) {
				String dateCreated = (String)doc.get("dateCreated_docvalues_string");
				if(dateCreated != null)
					oWeatherObserved.setDateCreated(dateCreated);
			}

			if(saves.contains("dateModified")) {
				String dateModified = (String)doc.get("dateModified_docvalues_string");
				if(dateModified != null)
					oWeatherObserved.setDateModified(dateModified);
			}

			if(saves.contains("dateObserved")) {
				String dateObserved = (String)doc.get("dateObserved_docvalues_string");
				if(dateObserved != null)
					oWeatherObserved.setDateObserved(dateObserved);
			}

			if(saves.contains("owner")) {
				String owner = (String)doc.get("owner_docvalues_string");
				if(owner != null)
					oWeatherObserved.setOwner(owner);
			}

			if(saves.contains("refDevice")) {
				String refDevice = (String)doc.get("refDevice_docvalues_string");
				if(refDevice != null)
					oWeatherObserved.setRefDevice(refDevice);
			}

			if(saves.contains("refPointOfInterest")) {
				String refPointOfInterest = (String)doc.get("refPointOfInterest_docvalues_string");
				if(refPointOfInterest != null)
					oWeatherObserved.setRefPointOfInterest(refPointOfInterest);
			}

			if(saves.contains("source")) {
				String source = (String)doc.get("source_docvalues_string");
				if(source != null)
					oWeatherObserved.setSource(source);
			}

			if(saves.contains("seeAlso")) {
				String seeAlso = (String)doc.get("seeAlso_docvalues_string");
				if(seeAlso != null)
					oWeatherObserved.setSeeAlso(seeAlso);
			}

			if(saves.contains("airQualityIndex")) {
				Double airQualityIndex = (Double)doc.get("airQualityIndex_docvalues_double");
				if(airQualityIndex != null)
					oWeatherObserved.setAirQualityIndex(airQualityIndex);
			}

			if(saves.contains("airQualityIndexForecast")) {
				Double airQualityIndexForecast = (Double)doc.get("airQualityIndexForecast_docvalues_double");
				if(airQualityIndexForecast != null)
					oWeatherObserved.setAirQualityIndexForecast(airQualityIndexForecast);
			}

			if(saves.contains("aqiMajorPollutant")) {
				String aqiMajorPollutant = (String)doc.get("aqiMajorPollutant_docvalues_string");
				if(aqiMajorPollutant != null)
					oWeatherObserved.setAqiMajorPollutant(aqiMajorPollutant);
			}

			if(saves.contains("aqiMajorPollutantForecast")) {
				String aqiMajorPollutantForecast = (String)doc.get("aqiMajorPollutantForecast_docvalues_string");
				if(aqiMajorPollutantForecast != null)
					oWeatherObserved.setAqiMajorPollutantForecast(aqiMajorPollutantForecast);
			}

			if(saves.contains("atmosphericPressure")) {
				Double atmosphericPressure = (Double)doc.get("atmosphericPressure_docvalues_double");
				if(atmosphericPressure != null)
					oWeatherObserved.setAtmosphericPressure(atmosphericPressure);
			}

			if(saves.contains("pressureTendency")) {
				String pressureTendency = (String)doc.get("pressureTendency_docvalues_string");
				if(pressureTendency != null)
					oWeatherObserved.setPressureTendency(pressureTendency);
			}

			if(saves.contains("gustSpeed")) {
				Double gustSpeed = (Double)doc.get("gustSpeed_docvalues_double");
				if(gustSpeed != null)
					oWeatherObserved.setGustSpeed(gustSpeed);
			}

			if(saves.contains("windDirection")) {
				Double windDirection = (Double)doc.get("windDirection_docvalues_double");
				if(windDirection != null)
					oWeatherObserved.setWindDirection(windDirection);
			}

			if(saves.contains("windSpeed")) {
				Double windSpeed = (Double)doc.get("windSpeed_docvalues_double");
				if(windSpeed != null)
					oWeatherObserved.setWindSpeed(windSpeed);
			}

			if(saves.contains("precipitation")) {
				Double precipitation = (Double)doc.get("precipitation_docvalues_double");
				if(precipitation != null)
					oWeatherObserved.setPrecipitation(precipitation);
			}

			if(saves.contains("precipitationForecast")) {
				Double precipitationForecast = (Double)doc.get("precipitationForecast_docvalues_double");
				if(precipitationForecast != null)
					oWeatherObserved.setPrecipitationForecast(precipitationForecast);
			}

			if(saves.contains("relativeHumidity")) {
				Double relativeHumidity = (Double)doc.get("relativeHumidity_docvalues_double");
				if(relativeHumidity != null)
					oWeatherObserved.setRelativeHumidity(relativeHumidity);
			}

			if(saves.contains("relativeHumidityForecast")) {
				Double relativeHumidityForecast = (Double)doc.get("relativeHumidityForecast_docvalues_double");
				if(relativeHumidityForecast != null)
					oWeatherObserved.setRelativeHumidityForecast(relativeHumidityForecast);
			}

			if(saves.contains("snowHeight")) {
				Double snowHeight = (Double)doc.get("snowHeight_docvalues_double");
				if(snowHeight != null)
					oWeatherObserved.setSnowHeight(snowHeight);
			}

			if(saves.contains("airTemperatureForecast")) {
				Double airTemperatureForecast = (Double)doc.get("airTemperatureForecast_docvalues_double");
				if(airTemperatureForecast != null)
					oWeatherObserved.setAirTemperatureForecast(airTemperatureForecast);
			}

			if(saves.contains("airTemperatureTSA")) {
				String airTemperatureTSA = (String)doc.get("airTemperatureTSA_docvalues_string");
				if(airTemperatureTSA != null)
					oWeatherObserved.setAirTemperatureTSA(airTemperatureTSA);
			}

			if(saves.contains("feelsLikeTemperature")) {
				Double feelsLikeTemperature = (Double)doc.get("feelsLikeTemperature_docvalues_double");
				if(feelsLikeTemperature != null)
					oWeatherObserved.setFeelsLikeTemperature(feelsLikeTemperature);
			}

			if(saves.contains("temperature")) {
				Double temperature = (Double)doc.get("temperature_docvalues_double");
				if(temperature != null)
					oWeatherObserved.setTemperature(temperature);
			}

			if(saves.contains("solarRadiation")) {
				Double solarRadiation = (Double)doc.get("solarRadiation_docvalues_double");
				if(solarRadiation != null)
					oWeatherObserved.setSolarRadiation(solarRadiation);
			}

			if(saves.contains("streamGauge")) {
				Double streamGauge = (Double)doc.get("streamGauge_docvalues_double");
				if(streamGauge != null)
					oWeatherObserved.setStreamGauge(streamGauge);
			}

			if(saves.contains("uVIndexMax")) {
				Double uVIndexMax = (Double)doc.get("uVIndexMax_docvalues_double");
				if(uVIndexMax != null)
					oWeatherObserved.setUVIndexMax(uVIndexMax);
			}

			if(saves.contains("visibility")) {
				String visibility = (String)doc.get("visibility_docvalues_string");
				if(visibility != null)
					oWeatherObserved.setVisibility(visibility);
			}

			if(saves.contains("weatherType")) {
				String weatherType = (String)doc.get("weatherType_docvalues_string");
				if(weatherType != null)
					oWeatherObserved.setWeatherType(weatherType);
			}

			if(saves.contains("dewPoint")) {
				Double dewPoint = (Double)doc.get("dewPoint_docvalues_double");
				if(dewPoint != null)
					oWeatherObserved.setDewPoint(dewPoint);
			}

			if(saves.contains("diffuseIrradiation")) {
				Double diffuseIrradiation = (Double)doc.get("diffuseIrradiation_docvalues_double");
				if(diffuseIrradiation != null)
					oWeatherObserved.setDiffuseIrradiation(diffuseIrradiation);
			}

			if(saves.contains("directIrradiation")) {
				Double directIrradiation = (Double)doc.get("directIrradiation_docvalues_double");
				if(directIrradiation != null)
					oWeatherObserved.setDirectIrradiation(directIrradiation);
			}

			if(saves.contains("illuminance")) {
				Double illuminance = (Double)doc.get("illuminance_docvalues_double");
				if(illuminance != null)
					oWeatherObserved.setIlluminance(illuminance);
			}

			if(saves.contains("ngsildTenant")) {
				String ngsildTenant = (String)doc.get("ngsildTenant_docvalues_string");
				if(ngsildTenant != null)
					oWeatherObserved.setNgsildTenant(ngsildTenant);
			}

			if(saves.contains("ngsildPath")) {
				String ngsildPath = (String)doc.get("ngsildPath_docvalues_string");
				if(ngsildPath != null)
					oWeatherObserved.setNgsildPath(ngsildPath);
			}

			if(saves.contains("ngsildData")) {
				String ngsildData = (String)doc.get("ngsildData_docvalues_string");
				if(ngsildData != null)
					oWeatherObserved.setNgsildData(ngsildData);
			}

			if(saves.contains("entityId")) {
				String entityId = (String)doc.get("entityId_docvalues_string");
				if(entityId != null)
					oWeatherObserved.setEntityId(entityId);
			}

			if(saves.contains("ngsildContext")) {
				String ngsildContext = (String)doc.get("ngsildContext_docvalues_string");
				if(ngsildContext != null)
					oWeatherObserved.setNgsildContext(ngsildContext);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexWeatherObserved(JsonObject doc) {
		if(name != null) {
			doc.put("name_docvalues_string", name);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(address != null) {
			doc.put("address_docvalues_string", address.toString());
		}
		if(alternateName != null) {
			doc.put("alternateName_docvalues_string", alternateName);
		}
		if(location != null) {
			doc.put("location_docvalues_location", String.format("%s,%s", location.getX(), location.getY()));
		}
		if(areaServedColors != null) {
			JsonArray l = new JsonArray();
			doc.put("areaServedColors_indexedstored_strings", l);
			for(String o : areaServedColors) {
				l.add(o);
			}
		}
		if(areaServedTitles != null) {
			JsonArray l = new JsonArray();
			doc.put("areaServedTitles_indexedstored_strings", l);
			for(String o : areaServedTitles) {
				l.add(o);
			}
		}
		if(areaServedLinks != null) {
			JsonArray l = new JsonArray();
			doc.put("areaServedLinks_indexedstored_strings", l);
			for(String o : areaServedLinks) {
				l.add(o);
			}
		}
		if(areaServed != null) {
			JsonArray pointsArray = new JsonArray();
			areaServed.getPoints().stream().map(point -> new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))).collect(Collectors.toList()).forEach(pointArray -> pointsArray.add(pointArray));
			doc.put("areaServed_docvalues_location", new JsonObject().put("type", "LineString").put("coordinates", pointsArray).toString());
		}
		if(dataProvider != null) {
			doc.put("dataProvider_docvalues_string", dataProvider);
		}
		if(dateCreated != null) {
			doc.put("dateCreated_docvalues_string", dateCreated);
		}
		if(dateModified != null) {
			doc.put("dateModified_docvalues_string", dateModified);
		}
		if(dateObserved != null) {
			doc.put("dateObserved_docvalues_string", dateObserved);
		}
		if(owner != null) {
			doc.put("owner_docvalues_string", owner.toString());
		}
		if(refDevice != null) {
			doc.put("refDevice_docvalues_string", refDevice.toString());
		}
		if(refPointOfInterest != null) {
			doc.put("refPointOfInterest_docvalues_string", refPointOfInterest);
		}
		if(source != null) {
			doc.put("source_docvalues_string", source);
		}
		if(seeAlso != null) {
			doc.put("seeAlso_docvalues_string", seeAlso.toString());
		}
		if(airQualityIndex != null) {
			doc.put("airQualityIndex_docvalues_double", airQualityIndex.doubleValue());
		}
		if(airQualityIndexForecast != null) {
			doc.put("airQualityIndexForecast_docvalues_double", airQualityIndexForecast.doubleValue());
		}
		if(aqiMajorPollutant != null) {
			doc.put("aqiMajorPollutant_docvalues_string", aqiMajorPollutant);
		}
		if(aqiMajorPollutantForecast != null) {
			doc.put("aqiMajorPollutantForecast_docvalues_string", aqiMajorPollutantForecast);
		}
		if(atmosphericPressure != null) {
			doc.put("atmosphericPressure_docvalues_double", atmosphericPressure.doubleValue());
		}
		if(pressureTendency != null) {
			doc.put("pressureTendency_docvalues_string", pressureTendency.toString());
		}
		if(gustSpeed != null) {
			doc.put("gustSpeed_docvalues_double", gustSpeed.doubleValue());
		}
		if(windDirection != null) {
			doc.put("windDirection_docvalues_double", windDirection.doubleValue());
		}
		if(windSpeed != null) {
			doc.put("windSpeed_docvalues_double", windSpeed.doubleValue());
		}
		if(precipitation != null) {
			doc.put("precipitation_docvalues_double", precipitation.doubleValue());
		}
		if(precipitationForecast != null) {
			doc.put("precipitationForecast_docvalues_double", precipitationForecast.doubleValue());
		}
		if(relativeHumidity != null) {
			doc.put("relativeHumidity_docvalues_double", relativeHumidity.doubleValue());
		}
		if(relativeHumidityForecast != null) {
			doc.put("relativeHumidityForecast_docvalues_double", relativeHumidityForecast.doubleValue());
		}
		if(snowHeight != null) {
			doc.put("snowHeight_docvalues_double", snowHeight.doubleValue());
		}
		if(airTemperatureForecast != null) {
			doc.put("airTemperatureForecast_docvalues_double", airTemperatureForecast.doubleValue());
		}
		if(airTemperatureTSA != null) {
			doc.put("airTemperatureTSA_docvalues_string", airTemperatureTSA.toString());
		}
		if(feelsLikeTemperature != null) {
			doc.put("feelsLikeTemperature_docvalues_double", feelsLikeTemperature.doubleValue());
		}
		if(temperature != null) {
			doc.put("temperature_docvalues_double", temperature.doubleValue());
		}
		if(solarRadiation != null) {
			doc.put("solarRadiation_docvalues_double", solarRadiation.doubleValue());
		}
		if(streamGauge != null) {
			doc.put("streamGauge_docvalues_double", streamGauge.doubleValue());
		}
		if(uVIndexMax != null) {
			doc.put("uVIndexMax_docvalues_double", uVIndexMax.doubleValue());
		}
		if(visibility != null) {
			doc.put("visibility_docvalues_string", visibility.toString());
		}
		if(weatherType != null) {
			doc.put("weatherType_docvalues_string", weatherType);
		}
		if(dewPoint != null) {
			doc.put("dewPoint_docvalues_double", dewPoint.doubleValue());
		}
		if(diffuseIrradiation != null) {
			doc.put("diffuseIrradiation_docvalues_double", diffuseIrradiation.doubleValue());
		}
		if(directIrradiation != null) {
			doc.put("directIrradiation_docvalues_double", directIrradiation.doubleValue());
		}
		if(illuminance != null) {
			doc.put("illuminance_docvalues_double", illuminance.doubleValue());
		}
		if(ngsildTenant != null) {
			doc.put("ngsildTenant_docvalues_string", ngsildTenant);
		}
		if(ngsildPath != null) {
			doc.put("ngsildPath_docvalues_string", ngsildPath);
		}
		if(ngsildData != null) {
			doc.put("ngsildData_docvalues_string", ngsildData.toString());
		}
		if(entityId != null) {
			doc.put("entityId_docvalues_string", entityId);
		}
		if(ngsildContext != null) {
			doc.put("ngsildContext_docvalues_string", ngsildContext);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredWeatherObserved(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "address":
				return "address_docvalues_string";
			case "alternateName":
				return "alternateName_docvalues_string";
			case "location":
				return "location_docvalues_location";
			case "areaServedColors":
				return "areaServedColors_indexedstored_strings";
			case "areaServedTitles":
				return "areaServedTitles_indexedstored_strings";
			case "areaServedLinks":
				return "areaServedLinks_indexedstored_strings";
			case "areaServed":
				return "areaServed_docvalues_location";
			case "dataProvider":
				return "dataProvider_docvalues_string";
			case "dateCreated":
				return "dateCreated_docvalues_string";
			case "dateModified":
				return "dateModified_docvalues_string";
			case "dateObserved":
				return "dateObserved_docvalues_string";
			case "owner":
				return "owner_docvalues_string";
			case "refDevice":
				return "refDevice_docvalues_string";
			case "refPointOfInterest":
				return "refPointOfInterest_docvalues_string";
			case "source":
				return "source_docvalues_string";
			case "seeAlso":
				return "seeAlso_docvalues_string";
			case "airQualityIndex":
				return "airQualityIndex_docvalues_double";
			case "airQualityIndexForecast":
				return "airQualityIndexForecast_docvalues_double";
			case "aqiMajorPollutant":
				return "aqiMajorPollutant_docvalues_string";
			case "aqiMajorPollutantForecast":
				return "aqiMajorPollutantForecast_docvalues_string";
			case "atmosphericPressure":
				return "atmosphericPressure_docvalues_double";
			case "pressureTendency":
				return "pressureTendency_docvalues_string";
			case "gustSpeed":
				return "gustSpeed_docvalues_double";
			case "windDirection":
				return "windDirection_docvalues_double";
			case "windSpeed":
				return "windSpeed_docvalues_double";
			case "precipitation":
				return "precipitation_docvalues_double";
			case "precipitationForecast":
				return "precipitationForecast_docvalues_double";
			case "relativeHumidity":
				return "relativeHumidity_docvalues_double";
			case "relativeHumidityForecast":
				return "relativeHumidityForecast_docvalues_double";
			case "snowHeight":
				return "snowHeight_docvalues_double";
			case "airTemperatureForecast":
				return "airTemperatureForecast_docvalues_double";
			case "airTemperatureTSA":
				return "airTemperatureTSA_docvalues_string";
			case "feelsLikeTemperature":
				return "feelsLikeTemperature_docvalues_double";
			case "temperature":
				return "temperature_docvalues_double";
			case "solarRadiation":
				return "solarRadiation_docvalues_double";
			case "streamGauge":
				return "streamGauge_docvalues_double";
			case "uVIndexMax":
				return "uVIndexMax_docvalues_double";
			case "visibility":
				return "visibility_docvalues_string";
			case "weatherType":
				return "weatherType_docvalues_string";
			case "dewPoint":
				return "dewPoint_docvalues_double";
			case "diffuseIrradiation":
				return "diffuseIrradiation_docvalues_double";
			case "directIrradiation":
				return "directIrradiation_docvalues_double";
			case "illuminance":
				return "illuminance_docvalues_double";
			case "ngsildTenant":
				return "ngsildTenant_docvalues_string";
			case "ngsildPath":
				return "ngsildPath_docvalues_string";
			case "ngsildData":
				return "ngsildData_docvalues_string";
			case "entityId":
				return "entityId_docvalues_string";
			case "ngsildContext":
				return "ngsildContext_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedWeatherObserved(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "address":
				return "address_docvalues_string";
			case "alternateName":
				return "alternateName_docvalues_string";
			case "location":
				return "location_docvalues_location";
			case "areaServedColors":
				return "areaServedColors_indexedstored_strings";
			case "areaServedTitles":
				return "areaServedTitles_indexedstored_strings";
			case "areaServedLinks":
				return "areaServedLinks_indexedstored_strings";
			case "areaServed":
				return "areaServed_docvalues_location";
			case "dataProvider":
				return "dataProvider_docvalues_string";
			case "dateCreated":
				return "dateCreated_docvalues_string";
			case "dateModified":
				return "dateModified_docvalues_string";
			case "dateObserved":
				return "dateObserved_docvalues_string";
			case "owner":
				return "owner_docvalues_string";
			case "refDevice":
				return "refDevice_docvalues_string";
			case "refPointOfInterest":
				return "refPointOfInterest_docvalues_string";
			case "source":
				return "source_docvalues_string";
			case "seeAlso":
				return "seeAlso_docvalues_string";
			case "airQualityIndex":
				return "airQualityIndex_docvalues_double";
			case "airQualityIndexForecast":
				return "airQualityIndexForecast_docvalues_double";
			case "aqiMajorPollutant":
				return "aqiMajorPollutant_docvalues_string";
			case "aqiMajorPollutantForecast":
				return "aqiMajorPollutantForecast_docvalues_string";
			case "atmosphericPressure":
				return "atmosphericPressure_docvalues_double";
			case "pressureTendency":
				return "pressureTendency_docvalues_string";
			case "gustSpeed":
				return "gustSpeed_docvalues_double";
			case "windDirection":
				return "windDirection_docvalues_double";
			case "windSpeed":
				return "windSpeed_docvalues_double";
			case "precipitation":
				return "precipitation_docvalues_double";
			case "precipitationForecast":
				return "precipitationForecast_docvalues_double";
			case "relativeHumidity":
				return "relativeHumidity_docvalues_double";
			case "relativeHumidityForecast":
				return "relativeHumidityForecast_docvalues_double";
			case "snowHeight":
				return "snowHeight_docvalues_double";
			case "airTemperatureForecast":
				return "airTemperatureForecast_docvalues_double";
			case "airTemperatureTSA":
				return "airTemperatureTSA_docvalues_string";
			case "feelsLikeTemperature":
				return "feelsLikeTemperature_docvalues_double";
			case "temperature":
				return "temperature_docvalues_double";
			case "solarRadiation":
				return "solarRadiation_docvalues_double";
			case "streamGauge":
				return "streamGauge_docvalues_double";
			case "uVIndexMax":
				return "uVIndexMax_docvalues_double";
			case "visibility":
				return "visibility_docvalues_string";
			case "weatherType":
				return "weatherType_docvalues_string";
			case "dewPoint":
				return "dewPoint_docvalues_double";
			case "diffuseIrradiation":
				return "diffuseIrradiation_docvalues_double";
			case "directIrradiation":
				return "directIrradiation_docvalues_double";
			case "illuminance":
				return "illuminance_docvalues_double";
			case "ngsildTenant":
				return "ngsildTenant_docvalues_string";
			case "ngsildPath":
				return "ngsildPath_docvalues_string";
			case "ngsildData":
				return "ngsildData_docvalues_string";
			case "entityId":
				return "entityId_docvalues_string";
			case "ngsildContext":
				return "ngsildContext_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarWeatherObserved(String searchVar) {
		switch(searchVar) {
			case "name_docvalues_string":
				return "name";
			case "description_docvalues_string":
				return "description";
			case "address_docvalues_string":
				return "address";
			case "alternateName_docvalues_string":
				return "alternateName";
			case "location_docvalues_location":
				return "location";
			case "areaServedColors_indexedstored_strings":
				return "areaServedColors";
			case "areaServedTitles_indexedstored_strings":
				return "areaServedTitles";
			case "areaServedLinks_indexedstored_strings":
				return "areaServedLinks";
			case "areaServed_docvalues_location":
				return "areaServed";
			case "dataProvider_docvalues_string":
				return "dataProvider";
			case "dateCreated_docvalues_string":
				return "dateCreated";
			case "dateModified_docvalues_string":
				return "dateModified";
			case "dateObserved_docvalues_string":
				return "dateObserved";
			case "owner_docvalues_string":
				return "owner";
			case "refDevice_docvalues_string":
				return "refDevice";
			case "refPointOfInterest_docvalues_string":
				return "refPointOfInterest";
			case "source_docvalues_string":
				return "source";
			case "seeAlso_docvalues_string":
				return "seeAlso";
			case "airQualityIndex_docvalues_double":
				return "airQualityIndex";
			case "airQualityIndexForecast_docvalues_double":
				return "airQualityIndexForecast";
			case "aqiMajorPollutant_docvalues_string":
				return "aqiMajorPollutant";
			case "aqiMajorPollutantForecast_docvalues_string":
				return "aqiMajorPollutantForecast";
			case "atmosphericPressure_docvalues_double":
				return "atmosphericPressure";
			case "pressureTendency_docvalues_string":
				return "pressureTendency";
			case "gustSpeed_docvalues_double":
				return "gustSpeed";
			case "windDirection_docvalues_double":
				return "windDirection";
			case "windSpeed_docvalues_double":
				return "windSpeed";
			case "precipitation_docvalues_double":
				return "precipitation";
			case "precipitationForecast_docvalues_double":
				return "precipitationForecast";
			case "relativeHumidity_docvalues_double":
				return "relativeHumidity";
			case "relativeHumidityForecast_docvalues_double":
				return "relativeHumidityForecast";
			case "snowHeight_docvalues_double":
				return "snowHeight";
			case "airTemperatureForecast_docvalues_double":
				return "airTemperatureForecast";
			case "airTemperatureTSA_docvalues_string":
				return "airTemperatureTSA";
			case "feelsLikeTemperature_docvalues_double":
				return "feelsLikeTemperature";
			case "temperature_docvalues_double":
				return "temperature";
			case "solarRadiation_docvalues_double":
				return "solarRadiation";
			case "streamGauge_docvalues_double":
				return "streamGauge";
			case "uVIndexMax_docvalues_double":
				return "uVIndexMax";
			case "visibility_docvalues_string":
				return "visibility";
			case "weatherType_docvalues_string":
				return "weatherType";
			case "dewPoint_docvalues_double":
				return "dewPoint";
			case "diffuseIrradiation_docvalues_double":
				return "diffuseIrradiation";
			case "directIrradiation_docvalues_double":
				return "directIrradiation";
			case "illuminance_docvalues_double":
				return "illuminance";
			case "ngsildTenant_docvalues_string":
				return "ngsildTenant";
			case "ngsildPath_docvalues_string":
				return "ngsildPath";
			case "ngsildData_docvalues_string":
				return "ngsildData";
			case "entityId_docvalues_string":
				return "entityId";
			case "ngsildContext_docvalues_string":
				return "ngsildContext";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchWeatherObserved(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedWeatherObserved(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeWeatherObserved(doc);
	}
	public void storeWeatherObserved(SolrResponse.Doc doc) {
		WeatherObserved oWeatherObserved = (WeatherObserved)this;
		SiteRequest siteRequest = oWeatherObserved.getSiteRequest_();

		oWeatherObserved.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setAddress(Optional.ofNullable(doc.get("address_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setAlternateName(Optional.ofNullable(doc.get("alternateName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setLocation(Optional.ofNullable(doc.get("location_docvalues_location")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("areaServedColors_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oWeatherObserved.addAreaServedColors(WeatherObserved.staticSetAreaServedColors(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("areaServedTitles_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oWeatherObserved.addAreaServedTitles(WeatherObserved.staticSetAreaServedTitles(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("areaServedLinks_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oWeatherObserved.addAreaServedLinks(WeatherObserved.staticSetAreaServedLinks(siteRequest, v.toString()));
		});
		oWeatherObserved.setAreaServed(Optional.ofNullable(doc.get("areaServed_docvalues_location")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setDataProvider(Optional.ofNullable(doc.get("dataProvider_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setDateCreated(Optional.ofNullable(doc.get("dateCreated_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setDateModified(Optional.ofNullable(doc.get("dateModified_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setDateObserved(Optional.ofNullable(doc.get("dateObserved_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setOwner(Optional.ofNullable(doc.get("owner_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setRefDevice(Optional.ofNullable(doc.get("refDevice_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setRefPointOfInterest(Optional.ofNullable(doc.get("refPointOfInterest_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setSource(Optional.ofNullable(doc.get("source_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setSeeAlso(Optional.ofNullable(doc.get("seeAlso_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setAirQualityIndex(Optional.ofNullable(doc.get("airQualityIndex_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setAirQualityIndexForecast(Optional.ofNullable(doc.get("airQualityIndexForecast_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setAqiMajorPollutant(Optional.ofNullable(doc.get("aqiMajorPollutant_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setAqiMajorPollutantForecast(Optional.ofNullable(doc.get("aqiMajorPollutantForecast_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setAtmosphericPressure(Optional.ofNullable(doc.get("atmosphericPressure_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setPressureTendency(Optional.ofNullable(doc.get("pressureTendency_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setGustSpeed(Optional.ofNullable(doc.get("gustSpeed_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setWindDirection(Optional.ofNullable(doc.get("windDirection_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setWindSpeed(Optional.ofNullable(doc.get("windSpeed_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setPrecipitation(Optional.ofNullable(doc.get("precipitation_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setPrecipitationForecast(Optional.ofNullable(doc.get("precipitationForecast_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setRelativeHumidity(Optional.ofNullable(doc.get("relativeHumidity_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setRelativeHumidityForecast(Optional.ofNullable(doc.get("relativeHumidityForecast_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setSnowHeight(Optional.ofNullable(doc.get("snowHeight_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setAirTemperatureForecast(Optional.ofNullable(doc.get("airTemperatureForecast_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setAirTemperatureTSA(Optional.ofNullable(doc.get("airTemperatureTSA_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setFeelsLikeTemperature(Optional.ofNullable(doc.get("feelsLikeTemperature_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setTemperature(Optional.ofNullable(doc.get("temperature_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setSolarRadiation(Optional.ofNullable(doc.get("solarRadiation_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setStreamGauge(Optional.ofNullable(doc.get("streamGauge_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setUVIndexMax(Optional.ofNullable(doc.get("uVIndexMax_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setVisibility(Optional.ofNullable(doc.get("visibility_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setWeatherType(Optional.ofNullable(doc.get("weatherType_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setDewPoint(Optional.ofNullable(doc.get("dewPoint_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setDiffuseIrradiation(Optional.ofNullable(doc.get("diffuseIrradiation_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setDirectIrradiation(Optional.ofNullable(doc.get("directIrradiation_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setIlluminance(Optional.ofNullable(doc.get("illuminance_docvalues_double")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setNgsildTenant(Optional.ofNullable(doc.get("ngsildTenant_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setNgsildPath(Optional.ofNullable(doc.get("ngsildPath_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setNgsildData(Optional.ofNullable(doc.get("ngsildData_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setEntityId(Optional.ofNullable(doc.get("entityId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oWeatherObserved.setNgsildContext(Optional.ofNullable(doc.get("ngsildContext_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestWeatherObserved() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof WeatherObserved) {
			WeatherObserved original = (WeatherObserved)o;
			if(!Objects.equals(name, original.getName()))
				apiRequest.addVars("name");
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
			if(!Objects.equals(address, original.getAddress()))
				apiRequest.addVars("address");
			if(!Objects.equals(alternateName, original.getAlternateName()))
				apiRequest.addVars("alternateName");
			if(!Objects.equals(location, original.getLocation()))
				apiRequest.addVars("location");
			if(!Objects.equals(areaServedColors, original.getAreaServedColors()))
				apiRequest.addVars("areaServedColors");
			if(!Objects.equals(areaServedTitles, original.getAreaServedTitles()))
				apiRequest.addVars("areaServedTitles");
			if(!Objects.equals(areaServedLinks, original.getAreaServedLinks()))
				apiRequest.addVars("areaServedLinks");
			if(!Objects.equals(areaServed, original.getAreaServed()))
				apiRequest.addVars("areaServed");
			if(!Objects.equals(dataProvider, original.getDataProvider()))
				apiRequest.addVars("dataProvider");
			if(!Objects.equals(dateCreated, original.getDateCreated()))
				apiRequest.addVars("dateCreated");
			if(!Objects.equals(dateModified, original.getDateModified()))
				apiRequest.addVars("dateModified");
			if(!Objects.equals(dateObserved, original.getDateObserved()))
				apiRequest.addVars("dateObserved");
			if(!Objects.equals(owner, original.getOwner()))
				apiRequest.addVars("owner");
			if(!Objects.equals(refDevice, original.getRefDevice()))
				apiRequest.addVars("refDevice");
			if(!Objects.equals(refPointOfInterest, original.getRefPointOfInterest()))
				apiRequest.addVars("refPointOfInterest");
			if(!Objects.equals(source, original.getSource()))
				apiRequest.addVars("source");
			if(!Objects.equals(seeAlso, original.getSeeAlso()))
				apiRequest.addVars("seeAlso");
			if(!Objects.equals(airQualityIndex, original.getAirQualityIndex()) && airQualityIndex != null && original.getAirQualityIndex() != null && airQualityIndex.compareTo(original.getAirQualityIndex()) != 0)
				apiRequest.addVars("airQualityIndex");
			if(!Objects.equals(airQualityIndexForecast, original.getAirQualityIndexForecast()) && airQualityIndexForecast != null && original.getAirQualityIndexForecast() != null && airQualityIndexForecast.compareTo(original.getAirQualityIndexForecast()) != 0)
				apiRequest.addVars("airQualityIndexForecast");
			if(!Objects.equals(aqiMajorPollutant, original.getAqiMajorPollutant()))
				apiRequest.addVars("aqiMajorPollutant");
			if(!Objects.equals(aqiMajorPollutantForecast, original.getAqiMajorPollutantForecast()))
				apiRequest.addVars("aqiMajorPollutantForecast");
			if(!Objects.equals(atmosphericPressure, original.getAtmosphericPressure()) && atmosphericPressure != null && original.getAtmosphericPressure() != null && atmosphericPressure.compareTo(original.getAtmosphericPressure()) != 0)
				apiRequest.addVars("atmosphericPressure");
			if(!Objects.equals(pressureTendency, original.getPressureTendency()))
				apiRequest.addVars("pressureTendency");
			if(!Objects.equals(gustSpeed, original.getGustSpeed()) && gustSpeed != null && original.getGustSpeed() != null && gustSpeed.compareTo(original.getGustSpeed()) != 0)
				apiRequest.addVars("gustSpeed");
			if(!Objects.equals(windDirection, original.getWindDirection()) && windDirection != null && original.getWindDirection() != null && windDirection.compareTo(original.getWindDirection()) != 0)
				apiRequest.addVars("windDirection");
			if(!Objects.equals(windSpeed, original.getWindSpeed()) && windSpeed != null && original.getWindSpeed() != null && windSpeed.compareTo(original.getWindSpeed()) != 0)
				apiRequest.addVars("windSpeed");
			if(!Objects.equals(precipitation, original.getPrecipitation()) && precipitation != null && original.getPrecipitation() != null && precipitation.compareTo(original.getPrecipitation()) != 0)
				apiRequest.addVars("precipitation");
			if(!Objects.equals(precipitationForecast, original.getPrecipitationForecast()) && precipitationForecast != null && original.getPrecipitationForecast() != null && precipitationForecast.compareTo(original.getPrecipitationForecast()) != 0)
				apiRequest.addVars("precipitationForecast");
			if(!Objects.equals(relativeHumidity, original.getRelativeHumidity()) && relativeHumidity != null && original.getRelativeHumidity() != null && relativeHumidity.compareTo(original.getRelativeHumidity()) != 0)
				apiRequest.addVars("relativeHumidity");
			if(!Objects.equals(relativeHumidityForecast, original.getRelativeHumidityForecast()) && relativeHumidityForecast != null && original.getRelativeHumidityForecast() != null && relativeHumidityForecast.compareTo(original.getRelativeHumidityForecast()) != 0)
				apiRequest.addVars("relativeHumidityForecast");
			if(!Objects.equals(snowHeight, original.getSnowHeight()) && snowHeight != null && original.getSnowHeight() != null && snowHeight.compareTo(original.getSnowHeight()) != 0)
				apiRequest.addVars("snowHeight");
			if(!Objects.equals(airTemperatureForecast, original.getAirTemperatureForecast()) && airTemperatureForecast != null && original.getAirTemperatureForecast() != null && airTemperatureForecast.compareTo(original.getAirTemperatureForecast()) != 0)
				apiRequest.addVars("airTemperatureForecast");
			if(!Objects.equals(airTemperatureTSA, original.getAirTemperatureTSA()))
				apiRequest.addVars("airTemperatureTSA");
			if(!Objects.equals(feelsLikeTemperature, original.getFeelsLikeTemperature()) && feelsLikeTemperature != null && original.getFeelsLikeTemperature() != null && feelsLikeTemperature.compareTo(original.getFeelsLikeTemperature()) != 0)
				apiRequest.addVars("feelsLikeTemperature");
			if(!Objects.equals(temperature, original.getTemperature()) && temperature != null && original.getTemperature() != null && temperature.compareTo(original.getTemperature()) != 0)
				apiRequest.addVars("temperature");
			if(!Objects.equals(solarRadiation, original.getSolarRadiation()) && solarRadiation != null && original.getSolarRadiation() != null && solarRadiation.compareTo(original.getSolarRadiation()) != 0)
				apiRequest.addVars("solarRadiation");
			if(!Objects.equals(streamGauge, original.getStreamGauge()) && streamGauge != null && original.getStreamGauge() != null && streamGauge.compareTo(original.getStreamGauge()) != 0)
				apiRequest.addVars("streamGauge");
			if(!Objects.equals(uVIndexMax, original.getUVIndexMax()) && uVIndexMax != null && original.getUVIndexMax() != null && uVIndexMax.compareTo(original.getUVIndexMax()) != 0)
				apiRequest.addVars("uVIndexMax");
			if(!Objects.equals(visibility, original.getVisibility()))
				apiRequest.addVars("visibility");
			if(!Objects.equals(weatherType, original.getWeatherType()))
				apiRequest.addVars("weatherType");
			if(!Objects.equals(dewPoint, original.getDewPoint()) && dewPoint != null && original.getDewPoint() != null && dewPoint.compareTo(original.getDewPoint()) != 0)
				apiRequest.addVars("dewPoint");
			if(!Objects.equals(diffuseIrradiation, original.getDiffuseIrradiation()) && diffuseIrradiation != null && original.getDiffuseIrradiation() != null && diffuseIrradiation.compareTo(original.getDiffuseIrradiation()) != 0)
				apiRequest.addVars("diffuseIrradiation");
			if(!Objects.equals(directIrradiation, original.getDirectIrradiation()) && directIrradiation != null && original.getDirectIrradiation() != null && directIrradiation.compareTo(original.getDirectIrradiation()) != 0)
				apiRequest.addVars("directIrradiation");
			if(!Objects.equals(illuminance, original.getIlluminance()) && illuminance != null && original.getIlluminance() != null && illuminance.compareTo(original.getIlluminance()) != 0)
				apiRequest.addVars("illuminance");
			if(!Objects.equals(ngsildTenant, original.getNgsildTenant()))
				apiRequest.addVars("ngsildTenant");
			if(!Objects.equals(ngsildPath, original.getNgsildPath()))
				apiRequest.addVars("ngsildPath");
			if(!Objects.equals(ngsildData, original.getNgsildData()))
				apiRequest.addVars("ngsildData");
			if(!Objects.equals(entityId, original.getEntityId()))
				apiRequest.addVars("entityId");
			if(!Objects.equals(ngsildContext, original.getNgsildContext()))
				apiRequest.addVars("ngsildContext");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(name).map(v -> "name: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(address).map(v -> "address: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(alternateName).map(v -> "alternateName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(location).map(v -> "location: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(areaServedColors).map(v -> "areaServedColors: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(areaServedTitles).map(v -> "areaServedTitles: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(areaServedLinks).map(v -> "areaServedLinks: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(areaServed).map(v -> "areaServed: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(dataProvider).map(v -> "dataProvider: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(dateCreated).map(v -> "dateCreated: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(dateModified).map(v -> "dateModified: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(dateObserved).map(v -> "dateObserved: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(owner).map(v -> "owner: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(refDevice).map(v -> "refDevice: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(refPointOfInterest).map(v -> "refPointOfInterest: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(source).map(v -> "source: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(seeAlso).map(v -> "seeAlso: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(airQualityIndex).map(v -> "airQualityIndex: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(airQualityIndexForecast).map(v -> "airQualityIndexForecast: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(aqiMajorPollutant).map(v -> "aqiMajorPollutant: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(aqiMajorPollutantForecast).map(v -> "aqiMajorPollutantForecast: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(atmosphericPressure).map(v -> "atmosphericPressure: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(pressureTendency).map(v -> "pressureTendency: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(gustSpeed).map(v -> "gustSpeed: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(windDirection).map(v -> "windDirection: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(windSpeed).map(v -> "windSpeed: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(precipitation).map(v -> "precipitation: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(precipitationForecast).map(v -> "precipitationForecast: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(relativeHumidity).map(v -> "relativeHumidity: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(relativeHumidityForecast).map(v -> "relativeHumidityForecast: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(snowHeight).map(v -> "snowHeight: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(airTemperatureForecast).map(v -> "airTemperatureForecast: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(airTemperatureTSA).map(v -> "airTemperatureTSA: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(feelsLikeTemperature).map(v -> "feelsLikeTemperature: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(temperature).map(v -> "temperature: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(solarRadiation).map(v -> "solarRadiation: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(streamGauge).map(v -> "streamGauge: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(uVIndexMax).map(v -> "uVIndexMax: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(visibility).map(v -> "visibility: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(weatherType).map(v -> "weatherType: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(dewPoint).map(v -> "dewPoint: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(diffuseIrradiation).map(v -> "diffuseIrradiation: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(directIrradiation).map(v -> "directIrradiation: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(illuminance).map(v -> "illuminance: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(ngsildTenant).map(v -> "ngsildTenant: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildPath).map(v -> "ngsildPath: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildData).map(v -> "ngsildData: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(entityId).map(v -> "entityId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildContext).map(v -> "ngsildContext: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "WeatherObserved";
	public static final String CLASS_API_ADDRESS_WeatherObserved = "computate.org-enUS-WeatherObserved";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_WeatherObserved;
	}
	public static final String VAR_name = "name";
	public static final String VAR_description = "description";
	public static final String VAR_address = "address";
	public static final String VAR_alternateName = "alternateName";
	public static final String VAR_location = "location";
	public static final String VAR_areaServedColors = "areaServedColors";
	public static final String VAR_areaServedTitles = "areaServedTitles";
	public static final String VAR_areaServedLinks = "areaServedLinks";
	public static final String VAR_areaServed = "areaServed";
	public static final String VAR_dataProvider = "dataProvider";
	public static final String VAR_dateCreated = "dateCreated";
	public static final String VAR_dateModified = "dateModified";
	public static final String VAR_dateObserved = "dateObserved";
	public static final String VAR_owner = "owner";
	public static final String VAR_refDevice = "refDevice";
	public static final String VAR_refPointOfInterest = "refPointOfInterest";
	public static final String VAR_source = "source";
	public static final String VAR_seeAlso = "seeAlso";
	public static final String VAR_airQualityIndex = "airQualityIndex";
	public static final String VAR_airQualityIndexForecast = "airQualityIndexForecast";
	public static final String VAR_aqiMajorPollutant = "aqiMajorPollutant";
	public static final String VAR_aqiMajorPollutantForecast = "aqiMajorPollutantForecast";
	public static final String VAR_atmosphericPressure = "atmosphericPressure";
	public static final String VAR_pressureTendency = "pressureTendency";
	public static final String VAR_gustSpeed = "gustSpeed";
	public static final String VAR_windDirection = "windDirection";
	public static final String VAR_windSpeed = "windSpeed";
	public static final String VAR_precipitation = "precipitation";
	public static final String VAR_precipitationForecast = "precipitationForecast";
	public static final String VAR_relativeHumidity = "relativeHumidity";
	public static final String VAR_relativeHumidityForecast = "relativeHumidityForecast";
	public static final String VAR_snowHeight = "snowHeight";
	public static final String VAR_airTemperatureForecast = "airTemperatureForecast";
	public static final String VAR_airTemperatureTSA = "airTemperatureTSA";
	public static final String VAR_feelsLikeTemperature = "feelsLikeTemperature";
	public static final String VAR_temperature = "temperature";
	public static final String VAR_solarRadiation = "solarRadiation";
	public static final String VAR_streamGauge = "streamGauge";
	public static final String VAR_uVIndexMax = "uVIndexMax";
	public static final String VAR_visibility = "visibility";
	public static final String VAR_weatherType = "weatherType";
	public static final String VAR_dewPoint = "dewPoint";
	public static final String VAR_diffuseIrradiation = "diffuseIrradiation";
	public static final String VAR_directIrradiation = "directIrradiation";
	public static final String VAR_illuminance = "illuminance";
	public static final String VAR_ngsildTenant = "ngsildTenant";
	public static final String VAR_ngsildPath = "ngsildPath";
	public static final String VAR_ngsildData = "ngsildData";
	public static final String VAR_entityId = "entityId";
	public static final String VAR_entityShortId = "entityShortId";
	public static final String VAR_ngsildContext = "ngsildContext";

	public static List<String> varsQForClass() {
		return WeatherObserved.varsQWeatherObserved(new ArrayList<String>());
	}
	public static List<String> varsQWeatherObserved(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return WeatherObserved.varsFqWeatherObserved(new ArrayList<String>());
	}
	public static List<String> varsFqWeatherObserved(List<String> vars) {
		vars.add(VAR_name);
		vars.add(VAR_description);
		vars.add(VAR_address);
		vars.add(VAR_alternateName);
		vars.add(VAR_location);
		vars.add(VAR_areaServed);
		vars.add(VAR_dataProvider);
		vars.add(VAR_dateCreated);
		vars.add(VAR_dateModified);
		vars.add(VAR_dateObserved);
		vars.add(VAR_owner);
		vars.add(VAR_refDevice);
		vars.add(VAR_refPointOfInterest);
		vars.add(VAR_source);
		vars.add(VAR_seeAlso);
		vars.add(VAR_airQualityIndex);
		vars.add(VAR_airQualityIndexForecast);
		vars.add(VAR_aqiMajorPollutant);
		vars.add(VAR_aqiMajorPollutantForecast);
		vars.add(VAR_atmosphericPressure);
		vars.add(VAR_pressureTendency);
		vars.add(VAR_gustSpeed);
		vars.add(VAR_windDirection);
		vars.add(VAR_windSpeed);
		vars.add(VAR_precipitation);
		vars.add(VAR_precipitationForecast);
		vars.add(VAR_relativeHumidity);
		vars.add(VAR_relativeHumidityForecast);
		vars.add(VAR_snowHeight);
		vars.add(VAR_airTemperatureForecast);
		vars.add(VAR_airTemperatureTSA);
		vars.add(VAR_feelsLikeTemperature);
		vars.add(VAR_temperature);
		vars.add(VAR_solarRadiation);
		vars.add(VAR_streamGauge);
		vars.add(VAR_uVIndexMax);
		vars.add(VAR_visibility);
		vars.add(VAR_weatherType);
		vars.add(VAR_dewPoint);
		vars.add(VAR_diffuseIrradiation);
		vars.add(VAR_directIrradiation);
		vars.add(VAR_illuminance);
		vars.add(VAR_ngsildTenant);
		vars.add(VAR_ngsildPath);
		vars.add(VAR_ngsildData);
		vars.add(VAR_entityId);
		vars.add(VAR_ngsildContext);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return WeatherObserved.varsRangeWeatherObserved(new ArrayList<String>());
	}
	public static List<String> varsRangeWeatherObserved(List<String> vars) {
		vars.add(VAR_address);
		vars.add(VAR_location);
		vars.add(VAR_owner);
		vars.add(VAR_refDevice);
		vars.add(VAR_seeAlso);
		vars.add(VAR_airQualityIndex);
		vars.add(VAR_airQualityIndexForecast);
		vars.add(VAR_atmosphericPressure);
		vars.add(VAR_pressureTendency);
		vars.add(VAR_gustSpeed);
		vars.add(VAR_windDirection);
		vars.add(VAR_windSpeed);
		vars.add(VAR_precipitation);
		vars.add(VAR_precipitationForecast);
		vars.add(VAR_relativeHumidity);
		vars.add(VAR_relativeHumidityForecast);
		vars.add(VAR_snowHeight);
		vars.add(VAR_airTemperatureForecast);
		vars.add(VAR_airTemperatureTSA);
		vars.add(VAR_feelsLikeTemperature);
		vars.add(VAR_temperature);
		vars.add(VAR_solarRadiation);
		vars.add(VAR_streamGauge);
		vars.add(VAR_uVIndexMax);
		vars.add(VAR_visibility);
		vars.add(VAR_dewPoint);
		vars.add(VAR_diffuseIrradiation);
		vars.add(VAR_directIrradiation);
		vars.add(VAR_illuminance);
		vars.add(VAR_ngsildData);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_name = "name";
	public static final String DISPLAY_NAME_description = "description";
	public static final String DISPLAY_NAME_address = "address";
	public static final String DISPLAY_NAME_alternateName = "alternate name";
	public static final String DISPLAY_NAME_location = "location";
	public static final String DISPLAY_NAME_areaServedColors = "area served colors";
	public static final String DISPLAY_NAME_areaServedTitles = "area served titles";
	public static final String DISPLAY_NAME_areaServedLinks = "area served links";
	public static final String DISPLAY_NAME_areaServed = "area served";
	public static final String DISPLAY_NAME_dataProvider = "data provider";
	public static final String DISPLAY_NAME_dateCreated = "date created";
	public static final String DISPLAY_NAME_dateModified = "date modified";
	public static final String DISPLAY_NAME_dateObserved = "date observed";
	public static final String DISPLAY_NAME_owner = "owner";
	public static final String DISPLAY_NAME_refDevice = "ref device";
	public static final String DISPLAY_NAME_refPointOfInterest = "ref point of interest";
	public static final String DISPLAY_NAME_source = "source";
	public static final String DISPLAY_NAME_seeAlso = "see also";
	public static final String DISPLAY_NAME_airQualityIndex = "air quality index";
	public static final String DISPLAY_NAME_airQualityIndexForecast = "air quality index forecast";
	public static final String DISPLAY_NAME_aqiMajorPollutant = "aqi major pollutant";
	public static final String DISPLAY_NAME_aqiMajorPollutantForecast = "aqi major pollutant forecast";
	public static final String DISPLAY_NAME_atmosphericPressure = "atmospheric pressure";
	public static final String DISPLAY_NAME_pressureTendency = "pressure tendency";
	public static final String DISPLAY_NAME_gustSpeed = "gust speed";
	public static final String DISPLAY_NAME_windDirection = "wind direction";
	public static final String DISPLAY_NAME_windSpeed = "wind speed";
	public static final String DISPLAY_NAME_precipitation = "precipitation";
	public static final String DISPLAY_NAME_precipitationForecast = "precipitation forecast";
	public static final String DISPLAY_NAME_relativeHumidity = "relative humidity";
	public static final String DISPLAY_NAME_relativeHumidityForecast = "relative humidity forecast";
	public static final String DISPLAY_NAME_snowHeight = "snow height";
	public static final String DISPLAY_NAME_airTemperatureForecast = "air temperature forecast";
	public static final String DISPLAY_NAME_airTemperatureTSA = "air temperature tsa";
	public static final String DISPLAY_NAME_feelsLikeTemperature = "feels like temperature";
	public static final String DISPLAY_NAME_temperature = "temperature";
	public static final String DISPLAY_NAME_solarRadiation = "solar radiation";
	public static final String DISPLAY_NAME_streamGauge = "stream gauge";
	public static final String DISPLAY_NAME_uVIndexMax = "u v index max";
	public static final String DISPLAY_NAME_visibility = "visibility";
	public static final String DISPLAY_NAME_weatherType = "weather type";
	public static final String DISPLAY_NAME_dewPoint = "dew point";
	public static final String DISPLAY_NAME_diffuseIrradiation = "diffuse irradiation";
	public static final String DISPLAY_NAME_directIrradiation = "direct irradiation";
	public static final String DISPLAY_NAME_illuminance = "illuminance";
	public static final String DISPLAY_NAME_ngsildTenant = "NGSILD-Tenant";
	public static final String DISPLAY_NAME_ngsildPath = "NGSILD-Path";
	public static final String DISPLAY_NAME_ngsildData = "";
	public static final String DISPLAY_NAME_entityId = "entity ID";
	public static final String DISPLAY_NAME_entityShortId = "short entity ID";
	public static final String DISPLAY_NAME_ngsildContext = "NGSILD context";

	@Override
	public String idForClass() {
		return entityId;
	}

	@Override
	public String titleForClass() {
		return title;
	}

	@Override
	public String nameForClass() {
		return name;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return WeatherObserved.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/weather-observed/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return WeatherObserved.displayNameWeatherObserved(var);
	}
	public static String displayNameWeatherObserved(String var) {
		switch(var) {
		case VAR_name:
			return DISPLAY_NAME_name;
		case VAR_description:
			return DISPLAY_NAME_description;
		case VAR_address:
			return DISPLAY_NAME_address;
		case VAR_alternateName:
			return DISPLAY_NAME_alternateName;
		case VAR_location:
			return DISPLAY_NAME_location;
		case VAR_areaServedColors:
			return DISPLAY_NAME_areaServedColors;
		case VAR_areaServedTitles:
			return DISPLAY_NAME_areaServedTitles;
		case VAR_areaServedLinks:
			return DISPLAY_NAME_areaServedLinks;
		case VAR_areaServed:
			return DISPLAY_NAME_areaServed;
		case VAR_dataProvider:
			return DISPLAY_NAME_dataProvider;
		case VAR_dateCreated:
			return DISPLAY_NAME_dateCreated;
		case VAR_dateModified:
			return DISPLAY_NAME_dateModified;
		case VAR_dateObserved:
			return DISPLAY_NAME_dateObserved;
		case VAR_owner:
			return DISPLAY_NAME_owner;
		case VAR_refDevice:
			return DISPLAY_NAME_refDevice;
		case VAR_refPointOfInterest:
			return DISPLAY_NAME_refPointOfInterest;
		case VAR_source:
			return DISPLAY_NAME_source;
		case VAR_seeAlso:
			return DISPLAY_NAME_seeAlso;
		case VAR_airQualityIndex:
			return DISPLAY_NAME_airQualityIndex;
		case VAR_airQualityIndexForecast:
			return DISPLAY_NAME_airQualityIndexForecast;
		case VAR_aqiMajorPollutant:
			return DISPLAY_NAME_aqiMajorPollutant;
		case VAR_aqiMajorPollutantForecast:
			return DISPLAY_NAME_aqiMajorPollutantForecast;
		case VAR_atmosphericPressure:
			return DISPLAY_NAME_atmosphericPressure;
		case VAR_pressureTendency:
			return DISPLAY_NAME_pressureTendency;
		case VAR_gustSpeed:
			return DISPLAY_NAME_gustSpeed;
		case VAR_windDirection:
			return DISPLAY_NAME_windDirection;
		case VAR_windSpeed:
			return DISPLAY_NAME_windSpeed;
		case VAR_precipitation:
			return DISPLAY_NAME_precipitation;
		case VAR_precipitationForecast:
			return DISPLAY_NAME_precipitationForecast;
		case VAR_relativeHumidity:
			return DISPLAY_NAME_relativeHumidity;
		case VAR_relativeHumidityForecast:
			return DISPLAY_NAME_relativeHumidityForecast;
		case VAR_snowHeight:
			return DISPLAY_NAME_snowHeight;
		case VAR_airTemperatureForecast:
			return DISPLAY_NAME_airTemperatureForecast;
		case VAR_airTemperatureTSA:
			return DISPLAY_NAME_airTemperatureTSA;
		case VAR_feelsLikeTemperature:
			return DISPLAY_NAME_feelsLikeTemperature;
		case VAR_temperature:
			return DISPLAY_NAME_temperature;
		case VAR_solarRadiation:
			return DISPLAY_NAME_solarRadiation;
		case VAR_streamGauge:
			return DISPLAY_NAME_streamGauge;
		case VAR_uVIndexMax:
			return DISPLAY_NAME_uVIndexMax;
		case VAR_visibility:
			return DISPLAY_NAME_visibility;
		case VAR_weatherType:
			return DISPLAY_NAME_weatherType;
		case VAR_dewPoint:
			return DISPLAY_NAME_dewPoint;
		case VAR_diffuseIrradiation:
			return DISPLAY_NAME_diffuseIrradiation;
		case VAR_directIrradiation:
			return DISPLAY_NAME_directIrradiation;
		case VAR_illuminance:
			return DISPLAY_NAME_illuminance;
		case VAR_ngsildTenant:
			return DISPLAY_NAME_ngsildTenant;
		case VAR_ngsildPath:
			return DISPLAY_NAME_ngsildPath;
		case VAR_ngsildData:
			return DISPLAY_NAME_ngsildData;
		case VAR_entityId:
			return DISPLAY_NAME_entityId;
		case VAR_entityShortId:
			return DISPLAY_NAME_entityShortId;
		case VAR_ngsildContext:
			return DISPLAY_NAME_ngsildContext;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionWeatherObserved(String var) {
		switch(var) {
		case VAR_name:
			return "The name of this item";
		case VAR_description:
			return "A description of this item";
		case VAR_address:
			return "The mailing address";
		case VAR_alternateName:
			return "An alternative name for this item";
		case VAR_location:
			return "Geojson reference to the item. It can be Point, LineString, Polygon, MultiPoint, MultiLineString or MultiPolygon";
		case VAR_areaServedColors:
			return "The colors of each areaServed Paths. ";
		case VAR_areaServedTitles:
			return "The titles of each areaServed Paths. ";
		case VAR_areaServedLinks:
			return "The links of each areaServed Paths. ";
		case VAR_areaServed:
			return "The geographic area where a service or offered item is provided";
		case VAR_dataProvider:
			return "A sequence of characters identifying the provider of the harmonised data entity";
		case VAR_dateCreated:
			return "Entity creation timestamp. This will usually be allocated by the storage platform";
		case VAR_dateModified:
			return "Timestamp of the last modification of the entity. This will usually be allocated by the storage platform";
		case VAR_dateObserved:
			return "Date of the observed entity defined by the user";
		case VAR_owner:
			return "A List containing a JSON encoded sequence of characters referencing the unique Ids of the owner(s)";
		case VAR_refDevice:
			return "A reference to the device(s) which captured this observation";
		case VAR_refPointOfInterest:
			return "Point of interest related to the item";
		case VAR_source:
			return "A sequence of characters giving the original source of the entity data as a URL. Recommended to be the fully qualified domain name of the source provider, or the URL to the source object";
		case VAR_seeAlso:
			return "list of uri pointing to additional resources about the item";
		case VAR_airQualityIndex:
			return "Air quality index is a number used to report the quality of the air on any given day";
		case VAR_airQualityIndexForecast:
			return "Forecasted overall Air Quality Index (AQI) over a certain duration in future";
		case VAR_aqiMajorPollutant:
			return "Major pollutant in the Air Quality Index (AQI)";
		case VAR_aqiMajorPollutantForecast:
			return "Forecasted major air pollutant in the Air Quality Index (AQI) over a certain duration in future";
		case VAR_atmosphericPressure:
			return "The atmospheric pressure observed measured in Hecto Pascals";
		case VAR_pressureTendency:
			return "Enum:'falling, raising, steady'. Is the pressure rising or falling? It can be expressed in quantitative terms or qualitative terms";
		case VAR_gustSpeed:
			return "A sudden burst of high-speed wind over the observed average wind speed lasting only for a few seconds";
		case VAR_windDirection:
			return "Direction of the wind bet";
		case VAR_windSpeed:
			return "Intensity of the wind";
		case VAR_precipitation:
			return "Amount of water rain registered. ";
		case VAR_precipitationForecast:
			return "Forecasted rainfall over a certain duration in future";
		case VAR_relativeHumidity:
			return "Humidity in the Air. Observed instantaneous relative humidity (water vapour in air)";
		case VAR_relativeHumidityForecast:
			return "Forecasted relative humidity (water vapour in air) over a certain duration in future";
		case VAR_snowHeight:
			return "The snow height observed by generic snow depth measurement sensors, expressed in centimeters";
		case VAR_airTemperatureForecast:
			return "Forecasted value of air temperature over a certain duration in future";
		case VAR_airTemperatureTSA:
			return "Air temperature time series aggregation";
		case VAR_feelsLikeTemperature:
			return "Temperature appreciation of the item";
		case VAR_temperature:
			return "Temperature of the item";
		case VAR_solarRadiation:
			return "The solar radiation observed measured in Watts per square";
		case VAR_streamGauge:
			return "The water level surface elevation observed by Hydrometric measurement sensors, namely a [Stream Gauge](https://en.wikipedia.org/wiki/Stream_gauge) expressed in centimeters";
		case VAR_uVIndexMax:
			return "The maximum UV index for the period, based on the World Health Organization's UV Index measure. [http://www.who.int/uv/intersunprogramme/activities/uv_index/en/](http://www.who.int/uv/intersunprogramme/activities/uv_index/en/) the values between 1 and 11 are the valid range for the index. The value 0 is for describing that no signal is detected so no value is stored";
		case VAR_visibility:
			return "Categories of visibility";
		case VAR_weatherType:
			return "Text description of the weather";
		case VAR_dewPoint:
			return "The dew point encoded as a number. Observed temperature to which air must be cooled to become saturated with water vapor";
		case VAR_diffuseIrradiation:
			return "Diffuse irradiance is the part of the solar irradiance that is scattered by the atmosphere";
		case VAR_directIrradiation:
			return "Direct irradiance is the part of the solar irradiance that directly reaches a surface";
		case VAR_illuminance:
			return "(https://en.wikipedia.org/wiki/Illuminance) observed measured in lux (lx) or lumens per square metre (cd\u00B7sr\u00B7m\u22122)";
		case VAR_ngsildTenant:
			return "The NGSILD-Tenant or Fiware-Service";
		case VAR_ngsildPath:
			return "The NGSILD-Path or Fiware-ServicePath";
		case VAR_ngsildData:
			return "The NGSILD data with @context from the context broker";
		case VAR_entityId:
			return "A unique ID for this Smart Data Model";
		case VAR_entityShortId:
			return "A short ID for this Smart Data Model";
		case VAR_ngsildContext:
			return "The NGSILD context URL for @context data. ";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameWeatherObserved(String var) {
		switch(var) {
		case VAR_name:
			return "String";
		case VAR_description:
			return "String";
		case VAR_address:
			return "JsonObject";
		case VAR_alternateName:
			return "String";
		case VAR_location:
			return "Point";
		case VAR_areaServedColors:
			return "List";
		case VAR_areaServedTitles:
			return "List";
		case VAR_areaServedLinks:
			return "List";
		case VAR_areaServed:
			return "Polygon";
		case VAR_dataProvider:
			return "String";
		case VAR_dateCreated:
			return "String";
		case VAR_dateModified:
			return "String";
		case VAR_dateObserved:
			return "String";
		case VAR_owner:
			return "JsonObject";
		case VAR_refDevice:
			return "JsonObject";
		case VAR_refPointOfInterest:
			return "String";
		case VAR_source:
			return "String";
		case VAR_seeAlso:
			return "JsonObject";
		case VAR_airQualityIndex:
			return "BigDecimal";
		case VAR_airQualityIndexForecast:
			return "BigDecimal";
		case VAR_aqiMajorPollutant:
			return "String";
		case VAR_aqiMajorPollutantForecast:
			return "String";
		case VAR_atmosphericPressure:
			return "BigDecimal";
		case VAR_pressureTendency:
			return "JsonObject";
		case VAR_gustSpeed:
			return "BigDecimal";
		case VAR_windDirection:
			return "BigDecimal";
		case VAR_windSpeed:
			return "BigDecimal";
		case VAR_precipitation:
			return "BigDecimal";
		case VAR_precipitationForecast:
			return "BigDecimal";
		case VAR_relativeHumidity:
			return "BigDecimal";
		case VAR_relativeHumidityForecast:
			return "BigDecimal";
		case VAR_snowHeight:
			return "BigDecimal";
		case VAR_airTemperatureForecast:
			return "BigDecimal";
		case VAR_airTemperatureTSA:
			return "JsonObject";
		case VAR_feelsLikeTemperature:
			return "BigDecimal";
		case VAR_temperature:
			return "BigDecimal";
		case VAR_solarRadiation:
			return "BigDecimal";
		case VAR_streamGauge:
			return "BigDecimal";
		case VAR_uVIndexMax:
			return "BigDecimal";
		case VAR_visibility:
			return "JsonObject";
		case VAR_weatherType:
			return "String";
		case VAR_dewPoint:
			return "BigDecimal";
		case VAR_diffuseIrradiation:
			return "BigDecimal";
		case VAR_directIrradiation:
			return "BigDecimal";
		case VAR_illuminance:
			return "BigDecimal";
		case VAR_ngsildTenant:
			return "String";
		case VAR_ngsildPath:
			return "String";
		case VAR_ngsildData:
			return "JsonObject";
		case VAR_entityId:
			return "String";
		case VAR_entityShortId:
			return "String";
		case VAR_ngsildContext:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static String ngsiType(String var) {
		switch(var) {
		case VAR_name:
			return "Property";
		case VAR_description:
			return "Property";
		case VAR_address:
			return "Property";
		case VAR_alternateName:
			return "Property";
		case VAR_location:
			return "GeoProperty";
		case VAR_areaServedColors:
			return "Property";
		case VAR_areaServedTitles:
			return "Property";
		case VAR_areaServedLinks:
			return "Property";
		case VAR_areaServed:
			return "GeoProperty";
		case VAR_dataProvider:
			return "Property";
		case VAR_dateCreated:
			return "Property";
		case VAR_dateModified:
			return "Property";
		case VAR_dateObserved:
			return "Property";
		case VAR_owner:
			return "Property";
		case VAR_refDevice:
			return "Property";
		case VAR_refPointOfInterest:
			return "Property";
		case VAR_source:
			return "Property";
		case VAR_seeAlso:
			return "Property";
		case VAR_airQualityIndex:
			return "Property";
		case VAR_airQualityIndexForecast:
			return "Property";
		case VAR_aqiMajorPollutant:
			return "Property";
		case VAR_aqiMajorPollutantForecast:
			return "Property";
		case VAR_atmosphericPressure:
			return "Property";
		case VAR_pressureTendency:
			return "Property";
		case VAR_gustSpeed:
			return "Property";
		case VAR_windDirection:
			return "Property";
		case VAR_windSpeed:
			return "Property";
		case VAR_precipitation:
			return "Property";
		case VAR_precipitationForecast:
			return "Property";
		case VAR_relativeHumidity:
			return "Property";
		case VAR_relativeHumidityForecast:
			return "Property";
		case VAR_snowHeight:
			return "Property";
		case VAR_airTemperatureForecast:
			return "Property";
		case VAR_airTemperatureTSA:
			return "Property";
		case VAR_feelsLikeTemperature:
			return "Property";
		case VAR_temperature:
			return "Property";
		case VAR_solarRadiation:
			return "Property";
		case VAR_streamGauge:
			return "Property";
		case VAR_uVIndexMax:
			return "Property";
		case VAR_visibility:
			return "Property";
		case VAR_weatherType:
			return "Property";
		case VAR_dewPoint:
			return "Property";
		case VAR_diffuseIrradiation:
			return "Property";
		case VAR_directIrradiation:
			return "Property";
		case VAR_illuminance:
			return "Property";
		case VAR_ngsildTenant:
			return "Property";
		case VAR_ngsildPath:
			return "Property";
		case VAR_ngsildData:
			return "Property";
		case VAR_entityId:
			return "Property";
		case VAR_entityShortId:
			return "Property";
		case VAR_ngsildContext:
			return "Property";
			default:
				return null;
		}
	}

	public static Integer htmColumnWeatherObserved(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowWeatherObserved(String var) {
		switch(var) {
		case VAR_name:
			return 3;
		case VAR_description:
			return 3;
		case VAR_address:
			return 3;
		case VAR_location:
			return 3;
		case VAR_areaServed:
			return 3;
		case VAR_seeAlso:
			return 13;
		case VAR_airQualityIndex:
			return 4;
		case VAR_airQualityIndexForecast:
			return 4;
		case VAR_aqiMajorPollutant:
			return 4;
		case VAR_aqiMajorPollutantForecast:
			return 4;
		case VAR_atmosphericPressure:
			return 7;
		case VAR_pressureTendency:
			return 7;
		case VAR_gustSpeed:
			return 8;
		case VAR_windDirection:
			return 8;
		case VAR_windSpeed:
			return 8;
		case VAR_precipitation:
			return 9;
		case VAR_precipitationForecast:
			return 9;
		case VAR_relativeHumidity:
			return 9;
		case VAR_relativeHumidityForecast:
			return 9;
		case VAR_snowHeight:
			return 9;
		case VAR_airTemperatureForecast:
			return 10;
		case VAR_airTemperatureTSA:
			return 10;
		case VAR_feelsLikeTemperature:
			return 10;
		case VAR_temperature:
			return 10;
		case VAR_solarRadiation:
			return 11;
		case VAR_streamGauge:
			return 11;
		case VAR_uVIndexMax:
			return 11;
		case VAR_visibility:
			return 11;
		case VAR_weatherType:
			return 11;
		case VAR_dewPoint:
			return 11;
		case VAR_diffuseIrradiation:
			return 11;
		case VAR_directIrradiation:
			return 11;
		case VAR_illuminance:
			return 11;
		case VAR_ngsildTenant:
			return 12;
		case VAR_ngsildPath:
			return 12;
		case VAR_ngsildData:
			return 13;
		case VAR_entityId:
			return 13;
		case VAR_ngsildContext:
			return 13;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellWeatherObserved(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
		case VAR_address:
			return 3;
		case VAR_location:
			return 4;
		case VAR_areaServed:
			return 4;
		case VAR_seeAlso:
			return 2;
		case VAR_airQualityIndex:
			return 1;
		case VAR_airQualityIndexForecast:
			return 2;
		case VAR_aqiMajorPollutant:
			return 3;
		case VAR_aqiMajorPollutantForecast:
			return 4;
		case VAR_atmosphericPressure:
			return 1;
		case VAR_pressureTendency:
			return 2;
		case VAR_gustSpeed:
			return 1;
		case VAR_windDirection:
			return 2;
		case VAR_windSpeed:
			return 3;
		case VAR_precipitation:
			return 1;
		case VAR_precipitationForecast:
			return 2;
		case VAR_relativeHumidity:
			return 3;
		case VAR_relativeHumidityForecast:
			return 4;
		case VAR_snowHeight:
			return 5;
		case VAR_airTemperatureForecast:
			return 1;
		case VAR_airTemperatureTSA:
			return 2;
		case VAR_feelsLikeTemperature:
			return 3;
		case VAR_temperature:
			return 4;
		case VAR_solarRadiation:
			return 1;
		case VAR_streamGauge:
			return 2;
		case VAR_uVIndexMax:
			return 3;
		case VAR_visibility:
			return 4;
		case VAR_weatherType:
			return 5;
		case VAR_dewPoint:
			return 6;
		case VAR_diffuseIrradiation:
			return 7;
		case VAR_directIrradiation:
			return 8;
		case VAR_illuminance:
			return 9;
		case VAR_ngsildTenant:
			return 1;
		case VAR_ngsildPath:
			return 2;
		case VAR_ngsildData:
			return 1;
		case VAR_entityId:
			return 2;
		case VAR_ngsildContext:
			return 3;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinWeatherObserved(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxWeatherObserved(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxWeatherObserved(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minWeatherObserved(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
