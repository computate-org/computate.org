package org.computate.site.model.event;

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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ComputateEventGen into the class ComputateEvent. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the ComputateEvent API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the ComputateEvent class and it's generated class ComputateEventGen&lt;BaseModel&gt;: </h3>extends ComputateEventGen
 * <p>
 * This Java class extends a generated Java class ComputateEventGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEvent">Find the class ComputateEvent in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ComputateEventGen<BaseModel>
 * <p>This <code>class ComputateEvent extends ComputateEventGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ComputateEventGen. 
 * The generated <code>class ComputateEventGen extends BaseModel</code> which means that ComputateEvent extends ComputateEventGen which extends BaseModel. 
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
 * <h2>ApiMethode: PUTImport</h2>
 * <p>This class contains a comment <b>"ApiMethod: PUTImport"</b>, which creates an API "PUTImport". 
 * </p>
 * <h2>ApiMethode: SearchPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: SearchPage"</b>, which creates an API "SearchPage". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: Computate Event"</b>, which groups all of the OpenAPIs for ComputateEvent objects under the tag "Computate Event". 
 * </p>
 * <h2>ApiUri.enUS: /api/event</h2>
 * <p>This class contains a comment <b>"ApiUri: /api/event"</b>, which defines the base API URI for ComputateEvent objects as "/api/event" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>IconGroup: duotone</h2>
 * <p>This class contains a comment <b>"IconGroup: duotone"</b>, which adds icons on the ComputateEvent page with a group of "duotone". 
 * This will reference a Font Awesome icon group that starts with "fa-" followed by the icon group "duotone", together is "fa-duotone". 
 * A Font Awesome icon group of "duotone" is expected to exist. 
 * The Font Awesome groups currently supported include: solid, regular, light, thin, duotone, sharp, and kit. 
 * See: <a href="https://fontawesome.com/docs/web/dig-deeper/styles">https://fontawesome.com/docs/web/dig-deeper/styles</a>. 
 * </p>
 * <h2>IconName: map-location-dot</h2>
 * <p>This class contains a comment <b>"IconName: map-location-dot"</b>, which adds icons on the ComputateEvent page with a name of "map-location-dot". 
 * This will reference a Font Awesome icon that starts with the icon group "fa-duotone fa-" followed by the icon name, which is "fa-duotone fa-map-location-dot". 
 * A Font Awesome icon of "fa-duotone fa-map-location-dot" is expected to exist. 
 * See: <a href="https://fontawesome.com/icons">https://fontawesome.com/icons</a>. 
 * </p>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ComputateEvent class will inherit the helpful inherited class comments from the super class ComputateEventGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 2</h2>
 * <p>This class contains a comment <b>"Order: 2"</b>, which means this class will be sorted by the given number 2 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 2</h2>
 * <p>This class contains a comment <b>"SqlOrder: 2"</b>, which means this class will be sorted by the given number 2 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.event.ComputateEventPage. 
 * </p>
 * <h2>SuperPage.enUS: BaseModelPage</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: BaseModelPage"</b>, which identifies the Java super class of the page code by it's class simple name "BaseModelPage". 
 * This means that the newly created class org.computate.site.model.event.ComputateEventPage extends org.computate.site.model.BaseModelPage. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ComputateEvent Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>Role.enUS: SiteAdmin</h2>
 * <p>
 * This class contains a comment <b>"Role.enUS: SiteAdmin"</b>, which identifies the default role name "SiteAdmin" of the OAuth2/OpenID Connect user role required to access this ComputateEvent API. 
 * It's possible to reconfigure the roles required to access the ComputateEvent API by configuring an environment variable like this: 
 * </p>
 * <pre>AUTH_ROLE_REQUIRED_ComputateEvent: ["SiteAdmin"]</pre>
 * <h2>AName.enUS: an event</h2>
 * <p>This class contains a comment <b>"AName.enUS: an event"</b>, which identifies the language context to describe a ComputateEvent as "an event". 
 * </p>
 * <p>
 * Delete the class ComputateEvent in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEvent&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.event in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.event&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ComputateEventGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(ComputateEvent.class);

	public static final String ComputateEvent_Description_enUS = "See the upcoming computate in-person and online events";
	public static final String ComputateEvent_AName_enUS = "an event";
	public static final String ComputateEvent_This_enUS = "this ";
	public static final String ComputateEvent_ThisName_enUS = "this event";
	public static final String ComputateEvent_A_enUS = "a ";
	public static final String ComputateEvent_TheName_enUS = "theevent";
	public static final String ComputateEvent_SingularName_enUS = "event";
	public static final String ComputateEvent_PluralName_enUS = "events";
	public static final String ComputateEvent_NameActual_enUS = "current event";
	public static final String ComputateEvent_AllName_enUS = "all the events";
	public static final String ComputateEvent_SearchAllNameBy_enUS = "search events by ";
	public static final String ComputateEvent_Title_enUS = "events";
	public static final String ComputateEvent_ThePluralName_enUS = "the events";
	public static final String ComputateEvent_NoNameFound_enUS = "no event found";
	public static final String ComputateEvent_ApiUri_enUS = "/api/event";
	public static final String ComputateEvent_ApiUriSearchPage_enUS = "/event";
	public static final String ComputateEvent_OfName_enUS = "of event";
	public static final String ComputateEvent_ANameAdjective_enUS = "an event";
	public static final String ComputateEvent_NameAdjectiveSingular_enUS = "event";
	public static final String ComputateEvent_NameAdjectivePlural_enUS = "events";
	public static final String Search_enUS_Uri = "/api/event";
	public static final String Search_enUS_ImageUri = "/png/api/event-999.png";
	public static final String GET_enUS_Uri = "/api/event/{id}";
	public static final String GET_enUS_ImageUri = "/png/api/event/{id}-999.png";
	public static final String PATCH_enUS_Uri = "/api/event";
	public static final String PATCH_enUS_ImageUri = "/png/api/event-999.png";
	public static final String POST_enUS_Uri = "/api/event";
	public static final String POST_enUS_ImageUri = "/png/api/event-999.png";
	public static final String PUTImport_enUS_Uri = "/api/event-import";
	public static final String PUTImport_enUS_ImageUri = "/png/api/event-import-999.png";
	public static final String SearchPage_enUS_Uri = "/event";
	public static final String SearchPage_enUS_ImageUri = "/png/event-999.png";

	public static final String ComputateEvent_IconGroup = "duotone";
	public static final String ComputateEvent_IconName = "map-location-dot";

	////////
	// id //
	////////


	/**	 The entity id
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String id;

	/**	<br> The entity id
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEvent&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = ComputateEvent.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateEvent idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (ComputateEvent)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return ComputateEvent.staticSearchId(siteRequest_, ComputateEvent.staticSetId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEvent&fq=entiteVar_enUS_indexed_string:location">Find the entity location in Solr</a>
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
		this.location = ComputateEvent.staticSetLocation(siteRequest_, o);
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
		this.location = ComputateEvent.staticSetLocation(siteRequest_, o);
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
	protected ComputateEvent locationInit() {
		Wrap<Point> locationWrap = new Wrap<Point>().var("location");
		if(location == null) {
			_location(locationWrap);
			Optional.ofNullable(locationWrap.getO()).ifPresent(o -> {
				setLocation(o);
			});
		}
		return (ComputateEvent)this;
	}

	public static Point staticSearchLocation(SiteRequest siteRequest_, Point o) {
		return o;
	}

	public static String staticSearchStrLocation(SiteRequest siteRequest_, Point o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocation(SiteRequest siteRequest_, String o) {
		return ComputateEvent.staticSearchLocation(siteRequest_, ComputateEvent.staticSetLocation(siteRequest_, o)).toString();
	}

	public Point sqlLocation() {
		return location;
	}

	////////////////////
	// locationColors //
	////////////////////


	/**	 The entity locationColors
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> locationColors = new ArrayList<String>();

	/**	<br> The entity locationColors
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEvent&fq=entiteVar_enUS_indexed_string:locationColors">Find the entity locationColors in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _locationColors(List<String> l);

	public List<String> getLocationColors() {
		return locationColors;
	}

	public void setLocationColors(List<String> locationColors) {
		this.locationColors = locationColors;
	}
	public void setLocationColors(String o) {
		String l = ComputateEvent.staticSetLocationColors(siteRequest_, o);
		if(l != null)
			addLocationColors(l);
	}
	public static String staticSetLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}
	public ComputateEvent addLocationColors(String...objects) {
		for(String o : objects) {
			addLocationColors(o);
		}
		return (ComputateEvent)this;
	}
	public ComputateEvent addLocationColors(String o) {
		if(o != null)
			this.locationColors.add(o);
		return (ComputateEvent)this;
	}
	@JsonIgnore
	public void setLocationColors(JsonArray objects) {
		locationColors.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addLocationColors(o);
		}
	}
	protected ComputateEvent locationColorsInit() {
		_locationColors(locationColors);
		return (ComputateEvent)this;
	}

	public static String staticSearchLocationColors(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationColors(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationColors(SiteRequest siteRequest_, String o) {
		return ComputateEvent.staticSearchLocationColors(siteRequest_, ComputateEvent.staticSetLocationColors(siteRequest_, o)).toString();
	}

	////////////////////
	// locationTitles //
	////////////////////


	/**	 The entity locationTitles
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> locationTitles = new ArrayList<String>();

	/**	<br> The entity locationTitles
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEvent&fq=entiteVar_enUS_indexed_string:locationTitles">Find the entity locationTitles in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _locationTitles(List<String> l);

	public List<String> getLocationTitles() {
		return locationTitles;
	}

	public void setLocationTitles(List<String> locationTitles) {
		this.locationTitles = locationTitles;
	}
	public void setLocationTitles(String o) {
		String l = ComputateEvent.staticSetLocationTitles(siteRequest_, o);
		if(l != null)
			addLocationTitles(l);
	}
	public static String staticSetLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}
	public ComputateEvent addLocationTitles(String...objects) {
		for(String o : objects) {
			addLocationTitles(o);
		}
		return (ComputateEvent)this;
	}
	public ComputateEvent addLocationTitles(String o) {
		if(o != null)
			this.locationTitles.add(o);
		return (ComputateEvent)this;
	}
	@JsonIgnore
	public void setLocationTitles(JsonArray objects) {
		locationTitles.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addLocationTitles(o);
		}
	}
	protected ComputateEvent locationTitlesInit() {
		_locationTitles(locationTitles);
		return (ComputateEvent)this;
	}

	public static String staticSearchLocationTitles(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationTitles(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationTitles(SiteRequest siteRequest_, String o) {
		return ComputateEvent.staticSearchLocationTitles(siteRequest_, ComputateEvent.staticSetLocationTitles(siteRequest_, o)).toString();
	}

	///////////////////
	// locationLinks //
	///////////////////


	/**	 The entity locationLinks
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> locationLinks = new ArrayList<String>();

	/**	<br> The entity locationLinks
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEvent&fq=entiteVar_enUS_indexed_string:locationLinks">Find the entity locationLinks in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _locationLinks(List<String> l);

	public List<String> getLocationLinks() {
		return locationLinks;
	}

	public void setLocationLinks(List<String> locationLinks) {
		this.locationLinks = locationLinks;
	}
	public void setLocationLinks(String o) {
		String l = ComputateEvent.staticSetLocationLinks(siteRequest_, o);
		if(l != null)
			addLocationLinks(l);
	}
	public static String staticSetLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}
	public ComputateEvent addLocationLinks(String...objects) {
		for(String o : objects) {
			addLocationLinks(o);
		}
		return (ComputateEvent)this;
	}
	public ComputateEvent addLocationLinks(String o) {
		if(o != null)
			this.locationLinks.add(o);
		return (ComputateEvent)this;
	}
	@JsonIgnore
	public void setLocationLinks(JsonArray objects) {
		locationLinks.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addLocationLinks(o);
		}
	}
	protected ComputateEvent locationLinksInit() {
		_locationLinks(locationLinks);
		return (ComputateEvent)this;
	}

	public static String staticSearchLocationLinks(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrLocationLinks(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLocationLinks(SiteRequest siteRequest_, String o) {
		return ComputateEvent.staticSearchLocationLinks(siteRequest_, ComputateEvent.staticSetLocationLinks(siteRequest_, o)).toString();
	}

	///////////////
	// eventName //
	///////////////


	/**	 The entity eventName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String eventName;

	/**	<br> The entity eventName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEvent&fq=entiteVar_enUS_indexed_string:eventName">Find the entity eventName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _eventName(Wrap<String> w);

	public String getEventName() {
		return eventName;
	}
	public void setEventName(String o) {
		this.eventName = ComputateEvent.staticSetEventName(siteRequest_, o);
	}
	public static String staticSetEventName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateEvent eventNameInit() {
		Wrap<String> eventNameWrap = new Wrap<String>().var("eventName");
		if(eventName == null) {
			_eventName(eventNameWrap);
			Optional.ofNullable(eventNameWrap.getO()).ifPresent(o -> {
				setEventName(o);
			});
		}
		return (ComputateEvent)this;
	}

	public static String staticSearchEventName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEventName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEventName(SiteRequest siteRequest_, String o) {
		return ComputateEvent.staticSearchEventName(siteRequest_, ComputateEvent.staticSetEventName(siteRequest_, o)).toString();
	}

	public String sqlEventName() {
		return eventName;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepComputateEvent(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepComputateEvent();
	}

	public Future<Void> promiseDeepComputateEvent() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseComputateEvent(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseModel(siteRequest_).onSuccess(b -> {
				promise.complete();
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseComputateEvent(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				idInit();
				locationInit();
				locationColorsInit();
				locationTitlesInit();
				locationLinksInit();
				eventNameInit();
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

	@Override public Future<Void> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepComputateEvent(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestComputateEvent(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestComputateEvent(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainComputateEvent(v);
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
	public Object obtainComputateEvent(String var) {
		ComputateEvent oComputateEvent = (ComputateEvent)this;
		switch(var) {
			case "id":
				return oComputateEvent.id;
			case "location":
				return oComputateEvent.location;
			case "locationColors":
				return oComputateEvent.locationColors;
			case "locationTitles":
				return oComputateEvent.locationTitles;
			case "locationLinks":
				return oComputateEvent.locationLinks;
			case "eventName":
				return oComputateEvent.eventName;
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
				o = relateComputateEvent(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateComputateEvent(String var, Object val) {
		ComputateEvent oComputateEvent = (ComputateEvent)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetComputateEvent(entityVar,  siteRequest_, o);
	}
	public static Object staticSetComputateEvent(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "id":
			return ComputateEvent.staticSetId(siteRequest_, o);
		case "location":
			return ComputateEvent.staticSetLocation(siteRequest_, o);
		case "locationColors":
			return ComputateEvent.staticSetLocationColors(siteRequest_, o);
		case "locationTitles":
			return ComputateEvent.staticSetLocationTitles(siteRequest_, o);
		case "locationLinks":
			return ComputateEvent.staticSetLocationLinks(siteRequest_, o);
		case "eventName":
			return ComputateEvent.staticSetEventName(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchComputateEvent(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchComputateEvent(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "id":
			return ComputateEvent.staticSearchId(siteRequest_, (String)o);
		case "location":
			return ComputateEvent.staticSearchLocation(siteRequest_, (Point)o);
		case "locationColors":
			return ComputateEvent.staticSearchLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return ComputateEvent.staticSearchLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return ComputateEvent.staticSearchLocationLinks(siteRequest_, (String)o);
		case "eventName":
			return ComputateEvent.staticSearchEventName(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrComputateEvent(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrComputateEvent(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "id":
			return ComputateEvent.staticSearchStrId(siteRequest_, (String)o);
		case "location":
			return ComputateEvent.staticSearchStrLocation(siteRequest_, (Point)o);
		case "locationColors":
			return ComputateEvent.staticSearchStrLocationColors(siteRequest_, (String)o);
		case "locationTitles":
			return ComputateEvent.staticSearchStrLocationTitles(siteRequest_, (String)o);
		case "locationLinks":
			return ComputateEvent.staticSearchStrLocationLinks(siteRequest_, (String)o);
		case "eventName":
			return ComputateEvent.staticSearchStrEventName(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqComputateEvent(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqComputateEvent(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "id":
			return ComputateEvent.staticSearchFqId(siteRequest_, o);
		case "location":
			return ComputateEvent.staticSearchFqLocation(siteRequest_, o);
		case "locationColors":
			return ComputateEvent.staticSearchFqLocationColors(siteRequest_, o);
		case "locationTitles":
			return ComputateEvent.staticSearchFqLocationTitles(siteRequest_, o);
		case "locationLinks":
			return ComputateEvent.staticSearchFqLocationLinks(siteRequest_, o);
		case "eventName":
			return ComputateEvent.staticSearchFqEventName(siteRequest_, o);
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
					o = persistComputateEvent(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistComputateEvent(String var, Object val) {
		String varLower = var.toLowerCase();
			if("location".equals(varLower)) {
				if(val instanceof String) {
					setLocation((String)val);
				} else if(val instanceof Point) {
					setLocation((Point)val);
				}
				saves.add("location");
				return val;
			} else if("eventname".equals(varLower)) {
				if(val instanceof String) {
					setEventName((String)val);
				}
				saves.add("eventName");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateComputateEvent(doc);
	}
	public void populateComputateEvent(SolrResponse.Doc doc) {
		ComputateEvent oComputateEvent = (ComputateEvent)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("location")) {
				Point location = (Point)doc.get("location_docvalues_location");
				if(location != null)
					oComputateEvent.setLocation(location);
			}

			if(saves.contains("locationColors")) {
				List<String> locationColors = (List<String>)doc.get("locationColors_indexedstored_strings");
				if(locationColors != null)
					oComputateEvent.locationColors.addAll(locationColors);
			}

			if(saves.contains("locationTitles")) {
				List<String> locationTitles = (List<String>)doc.get("locationTitles_indexedstored_strings");
				if(locationTitles != null)
					oComputateEvent.locationTitles.addAll(locationTitles);
			}

			if(saves.contains("locationLinks")) {
				List<String> locationLinks = (List<String>)doc.get("locationLinks_indexedstored_strings");
				if(locationLinks != null)
					oComputateEvent.locationLinks.addAll(locationLinks);
			}

			if(saves.contains("eventName")) {
				String eventName = (String)doc.get("eventName_docvalues_string");
				if(eventName != null)
					oComputateEvent.setEventName(eventName);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexComputateEvent(JsonObject doc) {
		if(location != null) {
			doc.put("location_docvalues_location", String.format("%s,%s", location.getX(), location.getY()));
		}
		if(locationColors != null) {
			JsonArray l = new JsonArray();
			doc.put("locationColors_indexedstored_strings", l);
			for(String o : locationColors) {
				l.add(o);
			}
		}
		if(locationTitles != null) {
			JsonArray l = new JsonArray();
			doc.put("locationTitles_indexedstored_strings", l);
			for(String o : locationTitles) {
				l.add(o);
			}
		}
		if(locationLinks != null) {
			JsonArray l = new JsonArray();
			doc.put("locationLinks_indexedstored_strings", l);
			for(String o : locationLinks) {
				l.add(o);
			}
		}
		if(eventName != null) {
			doc.put("eventName_docvalues_string", eventName);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredComputateEvent(String entityVar) {
		switch(entityVar) {
			case "location":
				return "location_docvalues_location";
			case "locationColors":
				return "locationColors_indexedstored_strings";
			case "locationTitles":
				return "locationTitles_indexedstored_strings";
			case "locationLinks":
				return "locationLinks_indexedstored_strings";
			case "eventName":
				return "eventName_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedComputateEvent(String entityVar) {
		switch(entityVar) {
			case "location":
				return "location_docvalues_location";
			case "locationColors":
				return "locationColors_indexedstored_strings";
			case "locationTitles":
				return "locationTitles_indexedstored_strings";
			case "locationLinks":
				return "locationLinks_indexedstored_strings";
			case "eventName":
				return "eventName_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarComputateEvent(String searchVar) {
		switch(searchVar) {
			case "location_docvalues_location":
				return "location";
			case "locationColors_indexedstored_strings":
				return "locationColors";
			case "locationTitles_indexedstored_strings":
				return "locationTitles";
			case "locationLinks_indexedstored_strings":
				return "locationLinks";
			case "eventName_docvalues_string":
				return "eventName";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchComputateEvent(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedComputateEvent(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeComputateEvent(doc);
	}
	public void storeComputateEvent(SolrResponse.Doc doc) {
		ComputateEvent oComputateEvent = (ComputateEvent)this;
		SiteRequest siteRequest = oComputateEvent.getSiteRequest_();

		oComputateEvent.setLocation(Optional.ofNullable(doc.get("location_docvalues_location")).map(v -> v.toString()).orElse(null));
		Optional.ofNullable((List<?>)doc.get("locationColors_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oComputateEvent.addLocationColors(ComputateEvent.staticSetLocationColors(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationTitles_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oComputateEvent.addLocationTitles(ComputateEvent.staticSetLocationTitles(siteRequest, v.toString()));
		});
		Optional.ofNullable((List<?>)doc.get("locationLinks_indexedstored_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
			oComputateEvent.addLocationLinks(ComputateEvent.staticSetLocationLinks(siteRequest, v.toString()));
		});
		oComputateEvent.setEventName(Optional.ofNullable(doc.get("eventName_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestComputateEvent() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ComputateEvent) {
			ComputateEvent original = (ComputateEvent)o;
			if(!Objects.equals(location, original.getLocation()))
				apiRequest.addVars("location");
			if(!Objects.equals(locationColors, original.getLocationColors()))
				apiRequest.addVars("locationColors");
			if(!Objects.equals(locationTitles, original.getLocationTitles()))
				apiRequest.addVars("locationTitles");
			if(!Objects.equals(locationLinks, original.getLocationLinks()))
				apiRequest.addVars("locationLinks");
			if(!Objects.equals(eventName, original.getEventName()))
				apiRequest.addVars("eventName");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(location).map(v -> "location: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationColors).map(v -> "locationColors: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationTitles).map(v -> "locationTitles: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(locationLinks).map(v -> "locationLinks: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(eventName).map(v -> "eventName: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "ComputateEvent";
	public static final String CLASS_API_ADDRESS_ComputateEvent = "computate.org-enUS-ComputateEvent";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_ComputateEvent;
	}
	public static final String VAR_id = "id";
	public static final String VAR_location = "location";
	public static final String VAR_locationColors = "locationColors";
	public static final String VAR_locationTitles = "locationTitles";
	public static final String VAR_locationLinks = "locationLinks";
	public static final String VAR_eventName = "eventName";

	public static List<String> varsQForClass() {
		return ComputateEvent.varsQComputateEvent(new ArrayList<String>());
	}
	public static List<String> varsQComputateEvent(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return ComputateEvent.varsFqComputateEvent(new ArrayList<String>());
	}
	public static List<String> varsFqComputateEvent(List<String> vars) {
		vars.add(VAR_location);
		vars.add(VAR_eventName);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return ComputateEvent.varsRangeComputateEvent(new ArrayList<String>());
	}
	public static List<String> varsRangeComputateEvent(List<String> vars) {
		vars.add(VAR_location);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_location = "map location";
	public static final String DISPLAY_NAME_locationColors = "location colors";
	public static final String DISPLAY_NAME_locationTitles = "location titles";
	public static final String DISPLAY_NAME_locationLinks = "location links";
	public static final String DISPLAY_NAME_eventName = "event name";

	public static String displayNameForClass(String var) {
		return ComputateEvent.displayNameComputateEvent(var);
	}
	public static String displayNameComputateEvent(String var) {
		switch(var) {
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_location:
			return DISPLAY_NAME_location;
		case VAR_locationColors:
			return DISPLAY_NAME_locationColors;
		case VAR_locationTitles:
			return DISPLAY_NAME_locationTitles;
		case VAR_locationLinks:
			return DISPLAY_NAME_locationLinks;
		case VAR_eventName:
			return DISPLAY_NAME_eventName;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionComputateEvent(String var) {
		switch(var) {
		case VAR_locationColors:
			return "The colors of each location Points. ";
		case VAR_locationTitles:
			return "The titles of each location Paths. ";
		case VAR_locationLinks:
			return "The links of each location Paths. ";
		case VAR_eventName:
			return "The event name. ";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameComputateEvent(String var) {
		switch(var) {
		case VAR_id:
			return "String";
		case VAR_location:
			return "Point";
		case VAR_locationColors:
			return "List";
		case VAR_locationTitles:
			return "List";
		case VAR_locationLinks:
			return "List";
		case VAR_eventName:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnComputateEvent(String var) {
		switch(var) {
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowComputateEvent(String var) {
		switch(var) {
		case VAR_location:
			return 3;
		case VAR_eventName:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellComputateEvent(String var) {
		switch(var) {
		case VAR_location:
			return 1;
		case VAR_eventName:
			return 2;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinComputateEvent(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxComputateEvent(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxComputateEvent(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minComputateEvent(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
