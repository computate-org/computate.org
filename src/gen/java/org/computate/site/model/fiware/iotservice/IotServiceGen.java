package org.computate.site.model.fiware.iotservice;

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
import io.vertx.core.json.JsonArray;
import org.computate.vertx.serialize.vertx.JsonArrayDeserializer;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class IotServiceGen into the class IotService. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the IotService API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "SqlOrder: " followed by an Integer to sort this class compared when generating the SQL code to create and drop tables. 
 * The Order comment allows you do define which order the SQL code is generated. 
 * </li>
 * <h3>About the IotService class and it's generated class IotServiceGen&lt;BaseModel&gt;: </h3>extends IotServiceGen
 * <p>
 * This Java class extends a generated Java class IotServiceGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotService">Find the class IotService in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends IotServiceGen<BaseModel>
 * <p>This <code>class IotService extends IotServiceGen&lt;BaseModel&gt;</code>, which means it extends a newly generated IotServiceGen. 
 * The generated <code>class IotServiceGen extends BaseModel</code> which means that IotService extends IotServiceGen which extends BaseModel. 
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
 * <h2>ApiMethode: DisplayPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: DisplayPage"</b>, which creates an API "DisplayPage". 
 * </p>
 * <h2>ApiMethode: DELETEFilter</h2>
 * <p>This class contains a comment <b>"ApiMethod: DELETEFilter"</b>, which creates an API "DELETEFilter". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: IoT services"</b>, which groups all of the OpenAPIs for IotService objects under the tag "IoT services". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/iot-service</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/iot-service"</b>, which defines the base API URI for IotService objects as "/en-us/api/iot-service" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the IotService class will inherit the helpful inherited class comments from the super class IotServiceGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 11</h2>
 * <p>This class contains a comment <b>"Order: 11"</b>, which means this class will be sorted by the given number 11 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.fiware.iotservice.IotServicePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.site.model.fiware.iotservice.IotServicePage extends org.computate.site.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the IotService Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: an IoT service</h2>
 * <p>This class contains a comment <b>"AName.enUS: an IoT service"</b>, which identifies the language context to describe a IotService as "an IoT service". 
 * </p>
 * <p>
 * Delete the class IotService in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotService&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.fiware.iotservice in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.fiware.iotservice&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class IotServiceGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(IotService.class);

	public static final String Description_enUS = "A service within a service path in the NGSI-LD standard";
	public static final String AName_enUS = "an IoT service";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this IoT service";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "theIoT service";
	public static final String SingularName_enUS = "IoT service";
	public static final String PluralName_enUS = "IoT services";
	public static final String NameActual_enUS = "current IoT service";
	public static final String AllName_enUS = "all IoT services";
	public static final String SearchAllNameBy_enUS = "search IoT services by ";
	public static final String Title_enUS = "IoT services";
	public static final String ThePluralName_enUS = "the IoT services";
	public static final String NoNameFound_enUS = "no IoT service found";
	public static final String ApiUri_enUS = "/en-us/api/iot-service";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/iot-service";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/iot-service/{pageId}";
	public static final String OfName_enUS = "of IoT service";
	public static final String ANameAdjective_enUS = "an IoT service";
	public static final String NameAdjectiveSingular_enUS = "IoT service";
	public static final String NameAdjectivePlural_enUS = "IoT services";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/iot-service";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/iot-service";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/iot-service";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/iot-service/{pageId}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/iot-service/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/iot-service/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/iot-service";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/iot-service";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/iot-service";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/iot-service";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/iot-service";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/iot-service";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/iot-service/{pageId}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/iot-service/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/iot-service/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/iot-service-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/iot-service-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/iot-service-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/iot-service";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/iot-service";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/iot-service";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/iot-service/{pageId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/iot-service/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/iot-service/%s";
	public static final String DisplayPage_enUS_OpenApiUri = "/en-us/shop/iot-service/{pageId}";
	public static final String DisplayPage_enUS_StringFormatUri = "/en-us/shop/iot-service/%s";
	public static final String DisplayPage_enUS_StringFormatUrl = "%s/en-us/shop/iot-service/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/iot-service";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/iot-service";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/iot-service";

	public static final String Icon = "<i class=\"fa-solid fa-tower-cell\"></i>";

	////////////////
	// entityType //
	////////////////


	/**	 The entity entityType
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String entityType;

	/**	<br> The entity entityType
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotService&fq=entiteVar_enUS_indexed_string:entityType">Find the entity entityType in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _entityType(Wrap<String> w);

	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String o) {
		this.entityType = IotService.staticSetEntityType(siteRequest_, o);
	}
	public static String staticSetEntityType(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected IotService entityTypeInit() {
		Wrap<String> entityTypeWrap = new Wrap<String>().var("entityType");
		if(entityType == null) {
			_entityType(entityTypeWrap);
			Optional.ofNullable(entityTypeWrap.getO()).ifPresent(o -> {
				setEntityType(o);
			});
		}
		return (IotService)this;
	}

	public static String staticSearchEntityType(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEntityType(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEntityType(SiteRequest siteRequest_, String o) {
		return IotService.staticSearchEntityType(siteRequest_, IotService.staticSetEntityType(siteRequest_, o)).toString();
	}

	public String sqlEntityType() {
		return entityType;
	}

	////////////
	// apiKey //
	////////////


	/**	 The entity apiKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String apiKey;

	/**	<br> The entity apiKey
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotService&fq=entiteVar_enUS_indexed_string:apiKey">Find the entity apiKey in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _apiKey(Wrap<String> w);

	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String o) {
		this.apiKey = IotService.staticSetApiKey(siteRequest_, o);
	}
	public static String staticSetApiKey(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected IotService apiKeyInit() {
		Wrap<String> apiKeyWrap = new Wrap<String>().var("apiKey");
		if(apiKey == null) {
			_apiKey(apiKeyWrap);
			Optional.ofNullable(apiKeyWrap.getO()).ifPresent(o -> {
				setApiKey(o);
			});
		}
		return (IotService)this;
	}

	public static String staticSearchApiKey(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrApiKey(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqApiKey(SiteRequest siteRequest_, String o) {
		return IotService.staticSearchApiKey(siteRequest_, IotService.staticSetApiKey(siteRequest_, o)).toString();
	}

	public String sqlApiKey() {
		return apiKey;
	}

	//////////////
	// resource //
	//////////////


	/**	 The entity resource
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String resource;

	/**	<br> The entity resource
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotService&fq=entiteVar_enUS_indexed_string:resource">Find the entity resource in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _resource(Wrap<String> w);

	public String getResource() {
		return resource;
	}
	public void setResource(String o) {
		this.resource = IotService.staticSetResource(siteRequest_, o);
	}
	public static String staticSetResource(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected IotService resourceInit() {
		Wrap<String> resourceWrap = new Wrap<String>().var("resource");
		if(resource == null) {
			_resource(resourceWrap);
			Optional.ofNullable(resourceWrap.getO()).ifPresent(o -> {
				setResource(o);
			});
		}
		return (IotService)this;
	}

	public static String staticSearchResource(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrResource(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResource(SiteRequest siteRequest_, String o) {
		return IotService.staticSearchResource(siteRequest_, IotService.staticSetResource(siteRequest_, o)).toString();
	}

	public String sqlResource() {
		return resource;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotService&fq=entiteVar_enUS_indexed_string:ngsildTenant">Find the entity ngsildTenant in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildTenant(Wrap<String> w);

	public String getNgsildTenant() {
		return ngsildTenant;
	}
	public void setNgsildTenant(String o) {
		this.ngsildTenant = IotService.staticSetNgsildTenant(siteRequest_, o);
	}
	public static String staticSetNgsildTenant(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected IotService ngsildTenantInit() {
		Wrap<String> ngsildTenantWrap = new Wrap<String>().var("ngsildTenant");
		if(ngsildTenant == null) {
			_ngsildTenant(ngsildTenantWrap);
			Optional.ofNullable(ngsildTenantWrap.getO()).ifPresent(o -> {
				setNgsildTenant(o);
			});
		}
		return (IotService)this;
	}

	public static String staticSearchNgsildTenant(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildTenant(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildTenant(SiteRequest siteRequest_, String o) {
		return IotService.staticSearchNgsildTenant(siteRequest_, IotService.staticSetNgsildTenant(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotService&fq=entiteVar_enUS_indexed_string:ngsildPath">Find the entity ngsildPath in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ngsildPath(Wrap<String> w);

	public String getNgsildPath() {
		return ngsildPath;
	}
	public void setNgsildPath(String o) {
		this.ngsildPath = IotService.staticSetNgsildPath(siteRequest_, o);
	}
	public static String staticSetNgsildPath(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected IotService ngsildPathInit() {
		Wrap<String> ngsildPathWrap = new Wrap<String>().var("ngsildPath");
		if(ngsildPath == null) {
			_ngsildPath(ngsildPathWrap);
			Optional.ofNullable(ngsildPathWrap.getO()).ifPresent(o -> {
				setNgsildPath(o);
			});
		}
		return (IotService)this;
	}

	public static String staticSearchNgsildPath(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrNgsildPath(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqNgsildPath(SiteRequest siteRequest_, String o) {
		return IotService.staticSearchNgsildPath(siteRequest_, IotService.staticSetNgsildPath(siteRequest_, o)).toString();
	}

	public String sqlNgsildPath() {
		return ngsildPath;
	}

	////////////
	// cbHost //
	////////////


	/**	 The entity cbHost
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String cbHost;

	/**	<br> The entity cbHost
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotService&fq=entiteVar_enUS_indexed_string:cbHost">Find the entity cbHost in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _cbHost(Wrap<String> w);

	public String getCbHost() {
		return cbHost;
	}
	public void setCbHost(String o) {
		this.cbHost = IotService.staticSetCbHost(siteRequest_, o);
	}
	public static String staticSetCbHost(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected IotService cbHostInit() {
		Wrap<String> cbHostWrap = new Wrap<String>().var("cbHost");
		if(cbHost == null) {
			_cbHost(cbHostWrap);
			Optional.ofNullable(cbHostWrap.getO()).ifPresent(o -> {
				setCbHost(o);
			});
		}
		return (IotService)this;
	}

	public static String staticSearchCbHost(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrCbHost(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCbHost(SiteRequest siteRequest_, String o) {
		return IotService.staticSearchCbHost(siteRequest_, IotService.staticSetCbHost(siteRequest_, o)).toString();
	}

	public String sqlCbHost() {
		return cbHost;
	}

	////////////////
	// attributes //
	////////////////


	/**	 The entity attributes
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray attributes;

	/**	<br> The entity attributes
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotService&fq=entiteVar_enUS_indexed_string:attributes">Find the entity attributes in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _attributes(Wrap<JsonArray> w);

	public JsonArray getAttributes() {
		return attributes;
	}

	public void setAttributes(JsonArray attributes) {
		this.attributes = attributes;
	}
	@JsonIgnore
	public void setAttributes(String o) {
		this.attributes = IotService.staticSetAttributes(siteRequest_, o);
	}
	public static JsonArray staticSetAttributes(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected IotService attributesInit() {
		Wrap<JsonArray> attributesWrap = new Wrap<JsonArray>().var("attributes");
		if(attributes == null) {
			_attributes(attributesWrap);
			Optional.ofNullable(attributesWrap.getO()).ifPresent(o -> {
				setAttributes(o);
			});
		}
		return (IotService)this;
	}

	public static String staticSearchAttributes(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrAttributes(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAttributes(SiteRequest siteRequest_, String o) {
		return IotService.staticSearchAttributes(siteRequest_, IotService.staticSetAttributes(siteRequest_, o)).toString();
	}

	public JsonArray sqlAttributes() {
		return attributes;
	}

	////////////
	// pageId //
	////////////


	/**	 The entity pageId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageId;

	/**	<br> The entity pageId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotService&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageId(Wrap<String> w);

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String o) {
		this.pageId = IotService.staticSetPageId(siteRequest_, o);
	}
	public static String staticSetPageId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected IotService pageIdInit() {
		Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
		if(pageId == null) {
			_pageId(pageIdWrap);
			Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
				setPageId(o);
			});
		}
		return (IotService)this;
	}

	public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
		return IotService.staticSearchPageId(siteRequest_, IotService.staticSetPageId(siteRequest_, o)).toString();
	}

	public String sqlPageId() {
		return pageId;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<IotServiceGen<DEV>> promiseDeepIotService(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepIotService();
	}

	public Future<IotServiceGen<DEV>> promiseDeepIotService() {
		Promise<IotServiceGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseIotService(promise2);
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

	public Future<Void> promiseIotService(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				entityTypeInit();
				apiKeyInit();
				resourceInit();
				ngsildTenantInit();
				ngsildPathInit();
				cbHostInit();
				attributesInit();
				pageIdInit();
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

	@Override public Future<? extends IotServiceGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepIotService(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestIotService(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestIotService(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainIotService(v);
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
	public Object obtainIotService(String var) {
		IotService oIotService = (IotService)this;
		switch(var) {
			case "entityType":
				return oIotService.entityType;
			case "apiKey":
				return oIotService.apiKey;
			case "resource":
				return oIotService.resource;
			case "ngsildTenant":
				return oIotService.ngsildTenant;
			case "ngsildPath":
				return oIotService.ngsildPath;
			case "cbHost":
				return oIotService.cbHost;
			case "attributes":
				return oIotService.attributes;
			case "pageId":
				return oIotService.pageId;
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
				o = relateIotService(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateIotService(String var, Object val) {
		IotService oIotService = (IotService)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetIotService(entityVar,  siteRequest_, o);
	}
	public static Object staticSetIotService(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "entityType":
			return IotService.staticSetEntityType(siteRequest_, o);
		case "apiKey":
			return IotService.staticSetApiKey(siteRequest_, o);
		case "resource":
			return IotService.staticSetResource(siteRequest_, o);
		case "ngsildTenant":
			return IotService.staticSetNgsildTenant(siteRequest_, o);
		case "ngsildPath":
			return IotService.staticSetNgsildPath(siteRequest_, o);
		case "cbHost":
			return IotService.staticSetCbHost(siteRequest_, o);
		case "attributes":
			return IotService.staticSetAttributes(siteRequest_, o);
		case "pageId":
			return IotService.staticSetPageId(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchIotService(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchIotService(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "entityType":
			return IotService.staticSearchEntityType(siteRequest_, (String)o);
		case "apiKey":
			return IotService.staticSearchApiKey(siteRequest_, (String)o);
		case "resource":
			return IotService.staticSearchResource(siteRequest_, (String)o);
		case "ngsildTenant":
			return IotService.staticSearchNgsildTenant(siteRequest_, (String)o);
		case "ngsildPath":
			return IotService.staticSearchNgsildPath(siteRequest_, (String)o);
		case "cbHost":
			return IotService.staticSearchCbHost(siteRequest_, (String)o);
		case "attributes":
			return IotService.staticSearchAttributes(siteRequest_, (JsonArray)o);
		case "pageId":
			return IotService.staticSearchPageId(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrIotService(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrIotService(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "entityType":
			return IotService.staticSearchStrEntityType(siteRequest_, (String)o);
		case "apiKey":
			return IotService.staticSearchStrApiKey(siteRequest_, (String)o);
		case "resource":
			return IotService.staticSearchStrResource(siteRequest_, (String)o);
		case "ngsildTenant":
			return IotService.staticSearchStrNgsildTenant(siteRequest_, (String)o);
		case "ngsildPath":
			return IotService.staticSearchStrNgsildPath(siteRequest_, (String)o);
		case "cbHost":
			return IotService.staticSearchStrCbHost(siteRequest_, (String)o);
		case "attributes":
			return IotService.staticSearchStrAttributes(siteRequest_, (String)o);
		case "pageId":
			return IotService.staticSearchStrPageId(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqIotService(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqIotService(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "entityType":
			return IotService.staticSearchFqEntityType(siteRequest_, o);
		case "apiKey":
			return IotService.staticSearchFqApiKey(siteRequest_, o);
		case "resource":
			return IotService.staticSearchFqResource(siteRequest_, o);
		case "ngsildTenant":
			return IotService.staticSearchFqNgsildTenant(siteRequest_, o);
		case "ngsildPath":
			return IotService.staticSearchFqNgsildPath(siteRequest_, o);
		case "cbHost":
			return IotService.staticSearchFqCbHost(siteRequest_, o);
		case "attributes":
			return IotService.staticSearchFqAttributes(siteRequest_, o);
		case "pageId":
			return IotService.staticSearchFqPageId(siteRequest_, o);
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
					o = persistIotService(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistIotService(String var, Object val) {
		String varLower = var.toLowerCase();
			if("entitytype".equals(varLower)) {
				if(val instanceof String) {
					setEntityType((String)val);
				}
				saves.add("entityType");
				return val;
			} else if("apikey".equals(varLower)) {
				if(val instanceof String) {
					setApiKey((String)val);
				}
				saves.add("apiKey");
				return val;
			} else if("resource".equals(varLower)) {
				if(val instanceof String) {
					setResource((String)val);
				}
				saves.add("resource");
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
			} else if("cbhost".equals(varLower)) {
				if(val instanceof String) {
					setCbHost((String)val);
				}
				saves.add("cbHost");
				return val;
			} else if("attributes".equals(varLower)) {
				if(val instanceof String) {
					setAttributes((String)val);
				} else if(val instanceof JsonArray) {
					setAttributes((JsonArray)val);
				}
				saves.add("attributes");
				return val;
			} else if("pageid".equals(varLower)) {
				if(val instanceof String) {
					setPageId((String)val);
				}
				saves.add("pageId");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateIotService(doc);
	}
	public void populateIotService(SolrResponse.Doc doc) {
		IotService oIotService = (IotService)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("entityType")) {
				String entityType = (String)doc.get("entityType_docvalues_string");
				if(entityType != null)
					oIotService.setEntityType(entityType);
			}

			if(saves.contains("apiKey")) {
				String apiKey = (String)doc.get("apiKey_docvalues_string");
				if(apiKey != null)
					oIotService.setApiKey(apiKey);
			}

			if(saves.contains("resource")) {
				String resource = (String)doc.get("resource_docvalues_string");
				if(resource != null)
					oIotService.setResource(resource);
			}

			if(saves.contains("ngsildTenant")) {
				String ngsildTenant = (String)doc.get("ngsildTenant_docvalues_string");
				if(ngsildTenant != null)
					oIotService.setNgsildTenant(ngsildTenant);
			}

			if(saves.contains("ngsildPath")) {
				String ngsildPath = (String)doc.get("ngsildPath_docvalues_string");
				if(ngsildPath != null)
					oIotService.setNgsildPath(ngsildPath);
			}

			if(saves.contains("cbHost")) {
				String cbHost = (String)doc.get("cbHost_docvalues_string");
				if(cbHost != null)
					oIotService.setCbHost(cbHost);
			}

			if(saves.contains("attributes")) {
				String attributes = (String)doc.get("attributes_docvalues_string");
				if(attributes != null)
					oIotService.setAttributes(attributes);
			}

			if(saves.contains("pageId")) {
				String pageId = (String)doc.get("pageId_docvalues_string");
				if(pageId != null)
					oIotService.setPageId(pageId);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexIotService(JsonObject doc) {
		if(entityType != null) {
			doc.put("entityType_docvalues_string", entityType);
		}
		if(apiKey != null) {
			doc.put("apiKey_docvalues_string", apiKey);
		}
		if(resource != null) {
			doc.put("resource_docvalues_string", resource);
		}
		if(ngsildTenant != null) {
			doc.put("ngsildTenant_docvalues_string", ngsildTenant);
		}
		if(ngsildPath != null) {
			doc.put("ngsildPath_docvalues_string", ngsildPath);
		}
		if(cbHost != null) {
			doc.put("cbHost_docvalues_string", cbHost);
		}
		if(attributes != null) {
			doc.put("attributes_docvalues_string", attributes.toString());
		}
		if(pageId != null) {
			doc.put("pageId_docvalues_string", pageId);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredIotService(String entityVar) {
		switch(entityVar) {
			case "entityType":
				return "entityType_docvalues_string";
			case "apiKey":
				return "apiKey_docvalues_string";
			case "resource":
				return "resource_docvalues_string";
			case "ngsildTenant":
				return "ngsildTenant_docvalues_string";
			case "ngsildPath":
				return "ngsildPath_docvalues_string";
			case "cbHost":
				return "cbHost_docvalues_string";
			case "attributes":
				return "attributes_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedIotService(String entityVar) {
		switch(entityVar) {
			case "entityType":
				return "entityType_docvalues_string";
			case "apiKey":
				return "apiKey_docvalues_string";
			case "resource":
				return "resource_docvalues_string";
			case "ngsildTenant":
				return "ngsildTenant_docvalues_string";
			case "ngsildPath":
				return "ngsildPath_docvalues_string";
			case "cbHost":
				return "cbHost_docvalues_string";
			case "attributes":
				return "attributes_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarIotService(String searchVar) {
		switch(searchVar) {
			case "entityType_docvalues_string":
				return "entityType";
			case "apiKey_docvalues_string":
				return "apiKey";
			case "resource_docvalues_string":
				return "resource";
			case "ngsildTenant_docvalues_string":
				return "ngsildTenant";
			case "ngsildPath_docvalues_string":
				return "ngsildPath";
			case "cbHost_docvalues_string":
				return "cbHost";
			case "attributes_docvalues_string":
				return "attributes";
			case "pageId_docvalues_string":
				return "pageId";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchIotService(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedIotService(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeIotService(doc);
	}
	public void storeIotService(SolrResponse.Doc doc) {
		IotService oIotService = (IotService)this;
		SiteRequest siteRequest = oIotService.getSiteRequest_();

		oIotService.setEntityType(Optional.ofNullable(doc.get("entityType_docvalues_string")).map(v -> v.toString()).orElse(null));
		oIotService.setApiKey(Optional.ofNullable(doc.get("apiKey_docvalues_string")).map(v -> v.toString()).orElse(null));
		oIotService.setResource(Optional.ofNullable(doc.get("resource_docvalues_string")).map(v -> v.toString()).orElse(null));
		oIotService.setNgsildTenant(Optional.ofNullable(doc.get("ngsildTenant_docvalues_string")).map(v -> v.toString()).orElse(null));
		oIotService.setNgsildPath(Optional.ofNullable(doc.get("ngsildPath_docvalues_string")).map(v -> v.toString()).orElse(null));
		oIotService.setCbHost(Optional.ofNullable(doc.get("cbHost_docvalues_string")).map(v -> v.toString()).orElse(null));
		oIotService.setAttributes(Optional.ofNullable(doc.get("attributes_docvalues_string")).map(v -> v.toString()).orElse(null));
		oIotService.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestIotService() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof IotService) {
			IotService original = (IotService)o;
			if(!Objects.equals(entityType, original.getEntityType()))
				apiRequest.addVars("entityType");
			if(!Objects.equals(apiKey, original.getApiKey()))
				apiRequest.addVars("apiKey");
			if(!Objects.equals(resource, original.getResource()))
				apiRequest.addVars("resource");
			if(!Objects.equals(ngsildTenant, original.getNgsildTenant()))
				apiRequest.addVars("ngsildTenant");
			if(!Objects.equals(ngsildPath, original.getNgsildPath()))
				apiRequest.addVars("ngsildPath");
			if(!Objects.equals(cbHost, original.getCbHost()))
				apiRequest.addVars("cbHost");
			if(!Objects.equals(attributes, original.getAttributes()))
				apiRequest.addVars("attributes");
			if(!Objects.equals(pageId, original.getPageId()))
				apiRequest.addVars("pageId");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(entityType).map(v -> "entityType: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(apiKey).map(v -> "apiKey: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(resource).map(v -> "resource: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildTenant).map(v -> "ngsildTenant: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(ngsildPath).map(v -> "ngsildPath: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(cbHost).map(v -> "cbHost: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(attributes).map(v -> "attributes: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "IotService";
	public static final String CLASS_API_ADDRESS_IotService = "computate.org-enUS-IotService";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_IotService;
	}
	public static final String VAR_entityType = "entityType";
	public static final String VAR_apiKey = "apiKey";
	public static final String VAR_resource = "resource";
	public static final String VAR_ngsildTenant = "ngsildTenant";
	public static final String VAR_ngsildPath = "ngsildPath";
	public static final String VAR_cbHost = "cbHost";
	public static final String VAR_attributes = "attributes";
	public static final String VAR_pageId = "pageId";

	public static List<String> varsQForClass() {
		return IotService.varsQIotService(new ArrayList<String>());
	}
	public static List<String> varsQIotService(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return IotService.varsFqIotService(new ArrayList<String>());
	}
	public static List<String> varsFqIotService(List<String> vars) {
		vars.add(VAR_entityType);
		vars.add(VAR_apiKey);
		vars.add(VAR_resource);
		vars.add(VAR_ngsildTenant);
		vars.add(VAR_ngsildPath);
		vars.add(VAR_cbHost);
		vars.add(VAR_attributes);
		vars.add(VAR_pageId);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return IotService.varsRangeIotService(new ArrayList<String>());
	}
	public static List<String> varsRangeIotService(List<String> vars) {
		vars.add(VAR_attributes);
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_entityType = "entity type";
	public static final String DISPLAY_NAME_apiKey = "API key";
	public static final String DISPLAY_NAME_resource = "resource";
	public static final String DISPLAY_NAME_ngsildTenant = "NGSILD-Tenant";
	public static final String DISPLAY_NAME_ngsildPath = "NGSILD-Path";
	public static final String DISPLAY_NAME_cbHost = "context broker host";
	public static final String DISPLAY_NAME_attributes = "attributes";
	public static final String DISPLAY_NAME_pageId = "Page ID";

	@Override
	public String idForClass() {
		return pageId;
	}

	@Override
	public String titleForClass() {
		return title;
	}

	@Override
	public String nameForClass() {
		return null;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return IotService.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/iot-service/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return "%s/en-us/shop/iot-service/%s";
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return IotService.displayNameIotService(var);
	}
	public static String displayNameIotService(String var) {
		switch(var) {
		case VAR_entityType:
			return DISPLAY_NAME_entityType;
		case VAR_apiKey:
			return DISPLAY_NAME_apiKey;
		case VAR_resource:
			return DISPLAY_NAME_resource;
		case VAR_ngsildTenant:
			return DISPLAY_NAME_ngsildTenant;
		case VAR_ngsildPath:
			return DISPLAY_NAME_ngsildPath;
		case VAR_cbHost:
			return DISPLAY_NAME_cbHost;
		case VAR_attributes:
			return DISPLAY_NAME_attributes;
		case VAR_pageId:
			return DISPLAY_NAME_pageId;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionIotService(String var) {
		switch(var) {
		case VAR_entityType:
			return "The NGSI-LD entity type";
		case VAR_apiKey:
			return "The NGSI-LD API Key";
		case VAR_resource:
			return "The NGSI-LD resource";
		case VAR_ngsildTenant:
			return "The NGSILD-Tenant or Fiware-Service";
		case VAR_ngsildPath:
			return "The NGSILD-Path or Fiware-ServicePath";
		case VAR_cbHost:
			return "The NGSI-LD Context Broker host";
		case VAR_attributes:
			return "The NGSI-LD attributes";
		case VAR_pageId:
			return "The ID for this page. ";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameIotService(String var) {
		switch(var) {
		case VAR_entityType:
			return "String";
		case VAR_apiKey:
			return "String";
		case VAR_resource:
			return "String";
		case VAR_ngsildTenant:
			return "String";
		case VAR_ngsildPath:
			return "String";
		case VAR_cbHost:
			return "String";
		case VAR_attributes:
			return "JsonArray";
		case VAR_pageId:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnIotService(String var) {
		switch(var) {
		case VAR_ngsildTenant:
			return 1;
		case VAR_ngsildPath:
			return 2;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowIotService(String var) {
		switch(var) {
		case VAR_entityType:
			return 3;
		case VAR_apiKey:
			return 3;
		case VAR_resource:
			return 3;
		case VAR_ngsildTenant:
			return 4;
		case VAR_ngsildPath:
			return 4;
		case VAR_cbHost:
			return 5;
		case VAR_attributes:
			return 6;
		case VAR_pageId:
			return 99;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellIotService(String var) {
		switch(var) {
		case VAR_entityType:
			return 1;
		case VAR_apiKey:
			return 2;
		case VAR_resource:
			return 3;
		case VAR_ngsildTenant:
			return 1;
		case VAR_ngsildPath:
			return 2;
		case VAR_cbHost:
			return 1;
		case VAR_attributes:
			return 1;
		case VAR_pageId:
			return 1;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinIotService(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxIotService(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxIotService(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minIotService(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
