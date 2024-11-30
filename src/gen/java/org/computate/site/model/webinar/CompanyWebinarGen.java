package org.computate.site.model.webinar;

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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyWebinarGen into the class CompanyWebinar. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the CompanyWebinar API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "SqlOrder: " followed by an Integer to sort this class compared when generating the SQL code to create and drop tables. 
 * The Order comment allows you do define which order the SQL code is generated. 
 * </li>
 * <h3>About the CompanyWebinar class and it's generated class CompanyWebinarGen&lt;BaseModel&gt;: </h3>extends CompanyWebinarGen
 * <p>
 * This Java class extends a generated Java class CompanyWebinarGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinar">Find the class CompanyWebinar in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyWebinarGen<BaseModel>
 * <p>This <code>class CompanyWebinar extends CompanyWebinarGen&lt;BaseModel&gt;</code>, which means it extends a newly generated CompanyWebinarGen. 
 * The generated <code>class CompanyWebinarGen extends BaseModel</code> which means that CompanyWebinar extends CompanyWebinarGen which extends BaseModel. 
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
 * <h2>ApiMethode: EditPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: EditPage"</b>, which creates an API "EditPage". 
 * </p>
 * <h2>ApiMethode: DisplayPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: DisplayPage"</b>, which creates an API "DisplayPage". 
 * </p>
 * <h2>ApiMethode: UserPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: UserPage"</b>, which creates an API "UserPage". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: webinars"</b>, which groups all of the OpenAPIs for CompanyWebinar objects under the tag "webinars". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/webinar</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/webinar"</b>, which defines the base API URI for CompanyWebinar objects as "/en-us/api/webinar" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyWebinar class will inherit the helpful inherited class comments from the super class CompanyWebinarGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 7</h2>
 * <p>This class contains a comment <b>"Order: 7"</b>, which means this class will be sorted by the given number 7 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.webinar.CompanyWebinarPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.site.model.webinar.CompanyWebinarPage extends org.computate.site.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyWebinar Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a webinar</h2>
 * <p>This class contains a comment <b>"AName.enUS: a webinar"</b>, which identifies the language context to describe a CompanyWebinar as "a webinar". 
 * </p>
 * <p>
 * Delete the class CompanyWebinar in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinar&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.webinar in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.webinar&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyWebinarGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyWebinar.class);

	public static final String Description_enUS = "Join the Computate community for regular weekly webinars";
	public static final String AName_enUS = "a webinar";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this webinar";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the webinar";
	public static final String SingularName_enUS = "webinar";
	public static final String PluralName_enUS = "webinars";
	public static final String NameActual_enUS = "current webinar";
	public static final String AllName_enUS = "all webinars";
	public static final String SearchAllNameBy_enUS = "search webinars by ";
	public static final String Title_enUS = "webinars";
	public static final String ThePluralName_enUS = "the webinars";
	public static final String NoNameFound_enUS = "no webinar found";
	public static final String ApiUri_enUS = "/en-us/api/webinar";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/webinar";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/webinar/{pageId}";
	public static final String OfName_enUS = "of webinar";
	public static final String ANameAdjective_enUS = "a webinar";
	public static final String NameAdjectiveSingular_enUS = "webinar";
	public static final String NameAdjectivePlural_enUS = "webinars";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/webinar";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/webinar";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/webinar";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/webinar/{pageId}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/webinar/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/webinar/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/webinar";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/webinar";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/webinar";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/webinar";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/webinar";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/webinar";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/webinar-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/webinar-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/webinar-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/webinar";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/webinar";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/webinar";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/webinar/{pageId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/webinar/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/webinar/%s";
	public static final String DisplayPage_enUS_OpenApiUri = "/en-us/view/webinar/{pageId}";
	public static final String DisplayPage_enUS_StringFormatUri = "/en-us/view/webinar/%s";
	public static final String DisplayPage_enUS_StringFormatUrl = "%s/en-us/view/webinar/%s";
	public static final String UserPage_enUS_OpenApiUri = "/en-us/join/webinar/{pageId}";
	public static final String UserPage_enUS_StringFormatUri = "/en-us/join/webinar/%s";
	public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/join/webinar/%s";

	public static final String Icon = "<i class=\"fa-regular fa-video\"></i>";

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinar&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = CompanyWebinar.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebinar nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (CompanyWebinar)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return CompanyWebinar.staticSearchName(siteRequest_, CompanyWebinar.staticSetName(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinar&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = CompanyWebinar.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebinar descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (CompanyWebinar)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return CompanyWebinar.staticSearchDescription(siteRequest_, CompanyWebinar.staticSetDescription(siteRequest_, o)).toString();
	}

	public String sqlDescription() {
		return description;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinar&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageId(Wrap<String> w);

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String o) {
		this.pageId = CompanyWebinar.staticSetPageId(siteRequest_, o);
	}
	public static String staticSetPageId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebinar pageIdInit() {
		Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
		if(pageId == null) {
			_pageId(pageIdWrap);
			Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
				setPageId(o);
			});
		}
		return (CompanyWebinar)this;
	}

	public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
		return CompanyWebinar.staticSearchPageId(siteRequest_, CompanyWebinar.staticSetPageId(siteRequest_, o)).toString();
	}

	public String sqlPageId() {
		return pageId;
	}

	/////////////
	// joinUri //
	/////////////


	/**	 The entity joinUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String joinUri;

	/**	<br> The entity joinUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinar&fq=entiteVar_enUS_indexed_string:joinUri">Find the entity joinUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _joinUri(Wrap<String> w);

	public String getJoinUri() {
		return joinUri;
	}
	public void setJoinUri(String o) {
		this.joinUri = CompanyWebinar.staticSetJoinUri(siteRequest_, o);
	}
	public static String staticSetJoinUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebinar joinUriInit() {
		Wrap<String> joinUriWrap = new Wrap<String>().var("joinUri");
		if(joinUri == null) {
			_joinUri(joinUriWrap);
			Optional.ofNullable(joinUriWrap.getO()).ifPresent(o -> {
				setJoinUri(o);
			});
		}
		return (CompanyWebinar)this;
	}

	public static String staticSearchJoinUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrJoinUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqJoinUri(SiteRequest siteRequest_, String o) {
		return CompanyWebinar.staticSearchJoinUri(siteRequest_, CompanyWebinar.staticSetJoinUri(siteRequest_, o)).toString();
	}

	public String sqlJoinUri() {
		return joinUri;
	}

	////////////////////////
	// webinarUrlAmericas //
	////////////////////////


	/**	 The entity webinarUrlAmericas
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String webinarUrlAmericas;

	/**	<br> The entity webinarUrlAmericas
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinar&fq=entiteVar_enUS_indexed_string:webinarUrlAmericas">Find the entity webinarUrlAmericas in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _webinarUrlAmericas(Wrap<String> w);

	public String getWebinarUrlAmericas() {
		return webinarUrlAmericas;
	}
	public void setWebinarUrlAmericas(String o) {
		this.webinarUrlAmericas = CompanyWebinar.staticSetWebinarUrlAmericas(siteRequest_, o);
	}
	public static String staticSetWebinarUrlAmericas(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebinar webinarUrlAmericasInit() {
		Wrap<String> webinarUrlAmericasWrap = new Wrap<String>().var("webinarUrlAmericas");
		if(webinarUrlAmericas == null) {
			_webinarUrlAmericas(webinarUrlAmericasWrap);
			Optional.ofNullable(webinarUrlAmericasWrap.getO()).ifPresent(o -> {
				setWebinarUrlAmericas(o);
			});
		}
		return (CompanyWebinar)this;
	}

	public static String staticSearchWebinarUrlAmericas(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrWebinarUrlAmericas(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqWebinarUrlAmericas(SiteRequest siteRequest_, String o) {
		return CompanyWebinar.staticSearchWebinarUrlAmericas(siteRequest_, CompanyWebinar.staticSetWebinarUrlAmericas(siteRequest_, o)).toString();
	}

	public String sqlWebinarUrlAmericas() {
		return webinarUrlAmericas;
	}

	////////////////////
	// webinarUrlApac //
	////////////////////


	/**	 The entity webinarUrlApac
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String webinarUrlApac;

	/**	<br> The entity webinarUrlApac
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinar&fq=entiteVar_enUS_indexed_string:webinarUrlApac">Find the entity webinarUrlApac in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _webinarUrlApac(Wrap<String> w);

	public String getWebinarUrlApac() {
		return webinarUrlApac;
	}
	public void setWebinarUrlApac(String o) {
		this.webinarUrlApac = CompanyWebinar.staticSetWebinarUrlApac(siteRequest_, o);
	}
	public static String staticSetWebinarUrlApac(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebinar webinarUrlApacInit() {
		Wrap<String> webinarUrlApacWrap = new Wrap<String>().var("webinarUrlApac");
		if(webinarUrlApac == null) {
			_webinarUrlApac(webinarUrlApacWrap);
			Optional.ofNullable(webinarUrlApacWrap.getO()).ifPresent(o -> {
				setWebinarUrlApac(o);
			});
		}
		return (CompanyWebinar)this;
	}

	public static String staticSearchWebinarUrlApac(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrWebinarUrlApac(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqWebinarUrlApac(SiteRequest siteRequest_, String o) {
		return CompanyWebinar.staticSearchWebinarUrlApac(siteRequest_, CompanyWebinar.staticSetWebinarUrlApac(siteRequest_, o)).toString();
	}

	public String sqlWebinarUrlApac() {
		return webinarUrlApac;
	}

	////////////////////
	// webinarUrlEmea //
	////////////////////


	/**	 The entity webinarUrlEmea
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String webinarUrlEmea;

	/**	<br> The entity webinarUrlEmea
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinar&fq=entiteVar_enUS_indexed_string:webinarUrlEmea">Find the entity webinarUrlEmea in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _webinarUrlEmea(Wrap<String> w);

	public String getWebinarUrlEmea() {
		return webinarUrlEmea;
	}
	public void setWebinarUrlEmea(String o) {
		this.webinarUrlEmea = CompanyWebinar.staticSetWebinarUrlEmea(siteRequest_, o);
	}
	public static String staticSetWebinarUrlEmea(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebinar webinarUrlEmeaInit() {
		Wrap<String> webinarUrlEmeaWrap = new Wrap<String>().var("webinarUrlEmea");
		if(webinarUrlEmea == null) {
			_webinarUrlEmea(webinarUrlEmeaWrap);
			Optional.ofNullable(webinarUrlEmeaWrap.getO()).ifPresent(o -> {
				setWebinarUrlEmea(o);
			});
		}
		return (CompanyWebinar)this;
	}

	public static String staticSearchWebinarUrlEmea(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrWebinarUrlEmea(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqWebinarUrlEmea(SiteRequest siteRequest_, String o) {
		return CompanyWebinar.staticSearchWebinarUrlEmea(siteRequest_, CompanyWebinar.staticSetWebinarUrlEmea(siteRequest_, o)).toString();
	}

	public String sqlWebinarUrlEmea() {
		return webinarUrlEmea;
	}

	/////////////
	// joinUrl //
	/////////////


	/**	 The entity joinUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String joinUrl;

	/**	<br> The entity joinUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinar&fq=entiteVar_enUS_indexed_string:joinUrl">Find the entity joinUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _joinUrl(Wrap<String> w);

	public String getJoinUrl() {
		return joinUrl;
	}
	public void setJoinUrl(String o) {
		this.joinUrl = CompanyWebinar.staticSetJoinUrl(siteRequest_, o);
	}
	public static String staticSetJoinUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebinar joinUrlInit() {
		Wrap<String> joinUrlWrap = new Wrap<String>().var("joinUrl");
		if(joinUrl == null) {
			_joinUrl(joinUrlWrap);
			Optional.ofNullable(joinUrlWrap.getO()).ifPresent(o -> {
				setJoinUrl(o);
			});
		}
		return (CompanyWebinar)this;
	}

	public static String staticSearchJoinUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrJoinUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqJoinUrl(SiteRequest siteRequest_, String o) {
		return CompanyWebinar.staticSearchJoinUrl(siteRequest_, CompanyWebinar.staticSetJoinUrl(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<CompanyWebinarGen<DEV>> promiseDeepCompanyWebinar(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyWebinar();
	}

	public Future<CompanyWebinarGen<DEV>> promiseDeepCompanyWebinar() {
		Promise<CompanyWebinarGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyWebinar(promise2);
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

	public Future<Void> promiseCompanyWebinar(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				nameInit();
				descriptionInit();
				pageIdInit();
				joinUriInit();
				webinarUrlAmericasInit();
				webinarUrlApacInit();
				webinarUrlEmeaInit();
				joinUrlInit();
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

	@Override public Future<? extends CompanyWebinarGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyWebinar(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyWebinar(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyWebinar(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyWebinar(v);
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
	public Object obtainCompanyWebinar(String var) {
		CompanyWebinar oCompanyWebinar = (CompanyWebinar)this;
		switch(var) {
			case "name":
				return oCompanyWebinar.name;
			case "description":
				return oCompanyWebinar.description;
			case "pageId":
				return oCompanyWebinar.pageId;
			case "joinUri":
				return oCompanyWebinar.joinUri;
			case "webinarUrlAmericas":
				return oCompanyWebinar.webinarUrlAmericas;
			case "webinarUrlApac":
				return oCompanyWebinar.webinarUrlApac;
			case "webinarUrlEmea":
				return oCompanyWebinar.webinarUrlEmea;
			case "joinUrl":
				return oCompanyWebinar.joinUrl;
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
				o = relateCompanyWebinar(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyWebinar(String var, Object val) {
		CompanyWebinar oCompanyWebinar = (CompanyWebinar)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyWebinar(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyWebinar(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return CompanyWebinar.staticSetName(siteRequest_, o);
		case "description":
			return CompanyWebinar.staticSetDescription(siteRequest_, o);
		case "pageId":
			return CompanyWebinar.staticSetPageId(siteRequest_, o);
		case "joinUri":
			return CompanyWebinar.staticSetJoinUri(siteRequest_, o);
		case "webinarUrlAmericas":
			return CompanyWebinar.staticSetWebinarUrlAmericas(siteRequest_, o);
		case "webinarUrlApac":
			return CompanyWebinar.staticSetWebinarUrlApac(siteRequest_, o);
		case "webinarUrlEmea":
			return CompanyWebinar.staticSetWebinarUrlEmea(siteRequest_, o);
		case "joinUrl":
			return CompanyWebinar.staticSetJoinUrl(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyWebinar(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyWebinar(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return CompanyWebinar.staticSearchName(siteRequest_, (String)o);
		case "description":
			return CompanyWebinar.staticSearchDescription(siteRequest_, (String)o);
		case "pageId":
			return CompanyWebinar.staticSearchPageId(siteRequest_, (String)o);
		case "joinUri":
			return CompanyWebinar.staticSearchJoinUri(siteRequest_, (String)o);
		case "webinarUrlAmericas":
			return CompanyWebinar.staticSearchWebinarUrlAmericas(siteRequest_, (String)o);
		case "webinarUrlApac":
			return CompanyWebinar.staticSearchWebinarUrlApac(siteRequest_, (String)o);
		case "webinarUrlEmea":
			return CompanyWebinar.staticSearchWebinarUrlEmea(siteRequest_, (String)o);
		case "joinUrl":
			return CompanyWebinar.staticSearchJoinUrl(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyWebinar(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyWebinar(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return CompanyWebinar.staticSearchStrName(siteRequest_, (String)o);
		case "description":
			return CompanyWebinar.staticSearchStrDescription(siteRequest_, (String)o);
		case "pageId":
			return CompanyWebinar.staticSearchStrPageId(siteRequest_, (String)o);
		case "joinUri":
			return CompanyWebinar.staticSearchStrJoinUri(siteRequest_, (String)o);
		case "webinarUrlAmericas":
			return CompanyWebinar.staticSearchStrWebinarUrlAmericas(siteRequest_, (String)o);
		case "webinarUrlApac":
			return CompanyWebinar.staticSearchStrWebinarUrlApac(siteRequest_, (String)o);
		case "webinarUrlEmea":
			return CompanyWebinar.staticSearchStrWebinarUrlEmea(siteRequest_, (String)o);
		case "joinUrl":
			return CompanyWebinar.staticSearchStrJoinUrl(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyWebinar(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyWebinar(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return CompanyWebinar.staticSearchFqName(siteRequest_, o);
		case "description":
			return CompanyWebinar.staticSearchFqDescription(siteRequest_, o);
		case "pageId":
			return CompanyWebinar.staticSearchFqPageId(siteRequest_, o);
		case "joinUri":
			return CompanyWebinar.staticSearchFqJoinUri(siteRequest_, o);
		case "webinarUrlAmericas":
			return CompanyWebinar.staticSearchFqWebinarUrlAmericas(siteRequest_, o);
		case "webinarUrlApac":
			return CompanyWebinar.staticSearchFqWebinarUrlApac(siteRequest_, o);
		case "webinarUrlEmea":
			return CompanyWebinar.staticSearchFqWebinarUrlEmea(siteRequest_, o);
		case "joinUrl":
			return CompanyWebinar.staticSearchFqJoinUrl(siteRequest_, o);
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
					o = persistCompanyWebinar(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistCompanyWebinar(String var, Object val) {
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
			} else if("pageid".equals(varLower)) {
				if(val instanceof String) {
					setPageId((String)val);
				}
				saves.add("pageId");
				return val;
			} else if("joinuri".equals(varLower)) {
				if(val instanceof String) {
					setJoinUri((String)val);
				}
				saves.add("joinUri");
				return val;
			} else if("webinarurlamericas".equals(varLower)) {
				if(val instanceof String) {
					setWebinarUrlAmericas((String)val);
				}
				saves.add("webinarUrlAmericas");
				return val;
			} else if("webinarurlapac".equals(varLower)) {
				if(val instanceof String) {
					setWebinarUrlApac((String)val);
				}
				saves.add("webinarUrlApac");
				return val;
			} else if("webinarurlemea".equals(varLower)) {
				if(val instanceof String) {
					setWebinarUrlEmea((String)val);
				}
				saves.add("webinarUrlEmea");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateCompanyWebinar(doc);
	}
	public void populateCompanyWebinar(SolrResponse.Doc doc) {
		CompanyWebinar oCompanyWebinar = (CompanyWebinar)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oCompanyWebinar.setName(name);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oCompanyWebinar.setDescription(description);
			}

			if(saves.contains("pageId")) {
				String pageId = (String)doc.get("pageId_docvalues_string");
				if(pageId != null)
					oCompanyWebinar.setPageId(pageId);
			}

			if(saves.contains("joinUri")) {
				String joinUri = (String)doc.get("joinUri_docvalues_string");
				if(joinUri != null)
					oCompanyWebinar.setJoinUri(joinUri);
			}

			if(saves.contains("webinarUrlAmericas")) {
				String webinarUrlAmericas = (String)doc.get("webinarUrlAmericas_docvalues_string");
				if(webinarUrlAmericas != null)
					oCompanyWebinar.setWebinarUrlAmericas(webinarUrlAmericas);
			}

			if(saves.contains("webinarUrlApac")) {
				String webinarUrlApac = (String)doc.get("webinarUrlApac_docvalues_string");
				if(webinarUrlApac != null)
					oCompanyWebinar.setWebinarUrlApac(webinarUrlApac);
			}

			if(saves.contains("webinarUrlEmea")) {
				String webinarUrlEmea = (String)doc.get("webinarUrlEmea_docvalues_string");
				if(webinarUrlEmea != null)
					oCompanyWebinar.setWebinarUrlEmea(webinarUrlEmea);
			}

			if(saves.contains("joinUrl")) {
				String joinUrl = (String)doc.get("joinUrl_docvalues_string");
				if(joinUrl != null)
					oCompanyWebinar.setJoinUrl(joinUrl);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexCompanyWebinar(JsonObject doc) {
		if(name != null) {
			doc.put("name_docvalues_string", name);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(pageId != null) {
			doc.put("pageId_docvalues_string", pageId);
		}
		if(joinUri != null) {
			doc.put("joinUri_docvalues_string", joinUri);
		}
		if(webinarUrlAmericas != null) {
			doc.put("webinarUrlAmericas_docvalues_string", webinarUrlAmericas);
		}
		if(webinarUrlApac != null) {
			doc.put("webinarUrlApac_docvalues_string", webinarUrlApac);
		}
		if(webinarUrlEmea != null) {
			doc.put("webinarUrlEmea_docvalues_string", webinarUrlEmea);
		}
		if(joinUrl != null) {
			doc.put("joinUrl_docvalues_string", joinUrl);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredCompanyWebinar(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "joinUri":
				return "joinUri_docvalues_string";
			case "webinarUrlAmericas":
				return "webinarUrlAmericas_docvalues_string";
			case "webinarUrlApac":
				return "webinarUrlApac_docvalues_string";
			case "webinarUrlEmea":
				return "webinarUrlEmea_docvalues_string";
			case "joinUrl":
				return "joinUrl_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedCompanyWebinar(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "joinUri":
				return "joinUri_docvalues_string";
			case "webinarUrlAmericas":
				return "webinarUrlAmericas_docvalues_string";
			case "webinarUrlApac":
				return "webinarUrlApac_docvalues_string";
			case "webinarUrlEmea":
				return "webinarUrlEmea_docvalues_string";
			case "joinUrl":
				return "joinUrl_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarCompanyWebinar(String searchVar) {
		switch(searchVar) {
			case "name_docvalues_string":
				return "name";
			case "description_docvalues_string":
				return "description";
			case "pageId_docvalues_string":
				return "pageId";
			case "joinUri_docvalues_string":
				return "joinUri";
			case "webinarUrlAmericas_docvalues_string":
				return "webinarUrlAmericas";
			case "webinarUrlApac_docvalues_string":
				return "webinarUrlApac";
			case "webinarUrlEmea_docvalues_string":
				return "webinarUrlEmea";
			case "joinUrl_docvalues_string":
				return "joinUrl";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchCompanyWebinar(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedCompanyWebinar(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeCompanyWebinar(doc);
	}
	public void storeCompanyWebinar(SolrResponse.Doc doc) {
		CompanyWebinar oCompanyWebinar = (CompanyWebinar)this;
		SiteRequest siteRequest = oCompanyWebinar.getSiteRequest_();

		oCompanyWebinar.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyWebinar.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyWebinar.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyWebinar.setJoinUri(Optional.ofNullable(doc.get("joinUri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyWebinar.setWebinarUrlAmericas(Optional.ofNullable(doc.get("webinarUrlAmericas_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyWebinar.setWebinarUrlApac(Optional.ofNullable(doc.get("webinarUrlApac_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyWebinar.setWebinarUrlEmea(Optional.ofNullable(doc.get("webinarUrlEmea_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyWebinar.setJoinUrl(Optional.ofNullable(doc.get("joinUrl_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestCompanyWebinar() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof CompanyWebinar) {
			CompanyWebinar original = (CompanyWebinar)o;
			if(!Objects.equals(name, original.getName()))
				apiRequest.addVars("name");
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
			if(!Objects.equals(pageId, original.getPageId()))
				apiRequest.addVars("pageId");
			if(!Objects.equals(joinUri, original.getJoinUri()))
				apiRequest.addVars("joinUri");
			if(!Objects.equals(webinarUrlAmericas, original.getWebinarUrlAmericas()))
				apiRequest.addVars("webinarUrlAmericas");
			if(!Objects.equals(webinarUrlApac, original.getWebinarUrlApac()))
				apiRequest.addVars("webinarUrlApac");
			if(!Objects.equals(webinarUrlEmea, original.getWebinarUrlEmea()))
				apiRequest.addVars("webinarUrlEmea");
			if(!Objects.equals(joinUrl, original.getJoinUrl()))
				apiRequest.addVars("joinUrl");
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
		sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(joinUri).map(v -> "joinUri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(webinarUrlAmericas).map(v -> "webinarUrlAmericas: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(webinarUrlApac).map(v -> "webinarUrlApac: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(webinarUrlEmea).map(v -> "webinarUrlEmea: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(joinUrl).map(v -> "joinUrl: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "CompanyWebinar";
	public static final String CLASS_API_ADDRESS_CompanyWebinar = "computate.org-enUS-CompanyWebinar";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_CompanyWebinar;
	}
	public static final String VAR_name = "name";
	public static final String VAR_description = "description";
	public static final String VAR_pageId = "pageId";
	public static final String VAR_joinUri = "joinUri";
	public static final String VAR_webinarUrlAmericas = "webinarUrlAmericas";
	public static final String VAR_webinarUrlApac = "webinarUrlApac";
	public static final String VAR_webinarUrlEmea = "webinarUrlEmea";
	public static final String VAR_joinUrl = "joinUrl";

	public static List<String> varsQForClass() {
		return CompanyWebinar.varsQCompanyWebinar(new ArrayList<String>());
	}
	public static List<String> varsQCompanyWebinar(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return CompanyWebinar.varsFqCompanyWebinar(new ArrayList<String>());
	}
	public static List<String> varsFqCompanyWebinar(List<String> vars) {
		vars.add(VAR_name);
		vars.add(VAR_description);
		vars.add(VAR_pageId);
		vars.add(VAR_joinUri);
		vars.add(VAR_webinarUrlAmericas);
		vars.add(VAR_webinarUrlApac);
		vars.add(VAR_webinarUrlEmea);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return CompanyWebinar.varsRangeCompanyWebinar(new ArrayList<String>());
	}
	public static List<String> varsRangeCompanyWebinar(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_name = "name";
	public static final String DISPLAY_NAME_description = "description";
	public static final String DISPLAY_NAME_pageId = "Page ID";
	public static final String DISPLAY_NAME_joinUri = "join URI";
	public static final String DISPLAY_NAME_webinarUrlAmericas = "Webinar URL Americas";
	public static final String DISPLAY_NAME_webinarUrlApac = "Webinar URL APAC";
	public static final String DISPLAY_NAME_webinarUrlEmea = "Webinar URL EMEA";
	public static final String DISPLAY_NAME_joinUrl = "Join the webinar";

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
		return name;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return CompanyWebinar.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/webinar/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return "%s/en-us/view/webinar/%s";
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return "%s/en-us/join/webinar/%s";
	}

	public static String displayNameForClass(String var) {
		return CompanyWebinar.displayNameCompanyWebinar(var);
	}
	public static String displayNameCompanyWebinar(String var) {
		switch(var) {
		case VAR_name:
			return DISPLAY_NAME_name;
		case VAR_description:
			return DISPLAY_NAME_description;
		case VAR_pageId:
			return DISPLAY_NAME_pageId;
		case VAR_joinUri:
			return DISPLAY_NAME_joinUri;
		case VAR_webinarUrlAmericas:
			return DISPLAY_NAME_webinarUrlAmericas;
		case VAR_webinarUrlApac:
			return DISPLAY_NAME_webinarUrlApac;
		case VAR_webinarUrlEmea:
			return DISPLAY_NAME_webinarUrlEmea;
		case VAR_joinUrl:
			return DISPLAY_NAME_joinUrl;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionCompanyWebinar(String var) {
		switch(var) {
		case VAR_name:
			return "The name of this webinar";
		case VAR_description:
			return "A description of this webinar";
		case VAR_pageId:
			return "The ID for this page. ";
		case VAR_joinUri:
			return "The join relative URI for this page. ";
		case VAR_webinarUrlAmericas:
			return "The actual link to the Webinar in Americas";
		case VAR_webinarUrlApac:
			return "The actual link to the Webinar in APAC";
		case VAR_webinarUrlEmea:
			return "The actual link to the Webinar in EMEA";
		case VAR_joinUrl:
			return "Access the webinar links. ";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameCompanyWebinar(String var) {
		switch(var) {
		case VAR_name:
			return "String";
		case VAR_description:
			return "String";
		case VAR_pageId:
			return "String";
		case VAR_joinUri:
			return "String";
		case VAR_webinarUrlAmericas:
			return "String";
		case VAR_webinarUrlApac:
			return "String";
		case VAR_webinarUrlEmea:
			return "String";
		case VAR_joinUrl:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnCompanyWebinar(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
		case VAR_joinUrl:
			return 0;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowCompanyWebinar(String var) {
		switch(var) {
		case VAR_name:
			return 3;
		case VAR_description:
			return 3;
		case VAR_pageId:
			return 99;
		case VAR_webinarUrlAmericas:
			return 4;
		case VAR_webinarUrlApac:
			return 4;
		case VAR_webinarUrlEmea:
			return 4;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellCompanyWebinar(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
		case VAR_pageId:
			return 1;
		case VAR_webinarUrlAmericas:
			return 1;
		case VAR_webinarUrlApac:
			return 2;
		case VAR_webinarUrlEmea:
			return 3;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinCompanyWebinar(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxCompanyWebinar(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxCompanyWebinar(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minCompanyWebinar(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
