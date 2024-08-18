package org.computate.site.model.service;

import org.computate.site.request.SiteRequest;
import org.computate.site.result.BaseResult;
import org.computate.site.model.BaseModel;
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
import io.vertx.core.json.JsonObject;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyServiceGen into the class CompanyService. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the CompanyService API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "Model: true" if you wish to persist these CompanyService objects in a relational PostgreSQL database transactionally in the RESTful API. 
 * The code to persist and query the CompanyServiceGen data in the database will then be automatically generated. 
 * </li>
 * <h3>About the CompanyService class and it's generated class CompanyServiceGen&lt;BaseResult&gt;: </h3>extends CompanyServiceGen
 * <p>
 * This Java class extends a generated Java class CompanyServiceGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService">Find the class CompanyService in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyServiceGen<BaseResult>
 * <p>This <code>class CompanyService extends CompanyServiceGen&lt;BaseResult&gt;</code>, which means it extends a newly generated CompanyServiceGen. 
 * The generated <code>class CompanyServiceGen extends BaseResult</code> which means that CompanyService extends CompanyServiceGen which extends BaseResult. 
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
 * <p>This class contains a comment <b>"ApiTag: service"</b>, which groups all of the OpenAPIs for CompanyService objects under the tag "service". 
 * </p>
 * <h2>ApiUri.enUS: /api/service</h2>
 * <p>This class contains a comment <b>"ApiUri: /api/service"</b>, which defines the base API URI for CompanyService objects as "/api/service" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyService class will inherit the helpful inherited class comments from the super class CompanyServiceGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 7</h2>
 * <p>This class contains a comment <b>"Order: 7"</b>, which means this class will be sorted by the given number 7 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 7</h2>
 * <p>This class contains a comment <b>"SqlOrder: 7"</b>, which means this class will be sorted by the given number 7 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.service.CompanyServicePage. 
 * </p>
 * <h2>SuperPage.enUS: BaseResultPage</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: BaseResultPage"</b>, which identifies the Java super class of the page code by it's class simple name "BaseResultPage". 
 * This means that the newly created class org.computate.site.model.service.CompanyServicePage extends org.computate.site.result.BaseResultPage. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyService Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * This class contains a comment <b>"Role.enUS: SiteAdmin"</b>, which identifies the default role name "SiteAdmin" of the OAuth2/OpenID Connect user role required to access this CompanyService API. 
 * It's possible to reconfigure the roles required to access the CompanyService API by configuring an environment variable like this: 
 * </p>
 * <pre>AUTH_ROLE_REQUIRED_CompanyService: ["SiteAdmin"]</pre>
 * <h2>AName.enUS: a service</h2>
 * <p>This class contains a comment <b>"AName.enUS: a service"</b>, which identifies the language context to describe a CompanyService as "a service". 
 * </p>
 * <p>
 * Delete the class CompanyService in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.service in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.service&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyServiceGen<DEV> extends BaseResult {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyService.class);

	public static final String CompanyService_Description_enUS = "See the services provided by computate that will help you build your own data-driven platforms";
	public static final String CompanyService_AName_enUS = "a service";
	public static final String CompanyService_This_enUS = "this ";
	public static final String CompanyService_ThisName_enUS = "this service";
	public static final String CompanyService_A_enUS = "a ";
	public static final String CompanyService_TheName_enUS = "the service";
	public static final String CompanyService_SingularName_enUS = "service";
	public static final String CompanyService_PluralName_enUS = "services";
	public static final String CompanyService_NameActual_enUS = "current service";
	public static final String CompanyService_AllName_enUS = "all services";
	public static final String CompanyService_SearchAllNameBy_enUS = "search services by ";
	public static final String CompanyService_Title_enUS = "services";
	public static final String CompanyService_ThePluralName_enUS = "the services";
	public static final String CompanyService_NoNameFound_enUS = "no service found";
	public static final String CompanyService_ApiUri_enUS = "/api/service";
	public static final String CompanyService_ApiUriSearchPage_enUS = "/service";
	public static final String CompanyService_OfName_enUS = "of service";
	public static final String CompanyService_ANameAdjective_enUS = "a service";
	public static final String CompanyService_NameAdjectiveSingular_enUS = "service";
	public static final String CompanyService_NameAdjectivePlural_enUS = "services";
	public static final String Search_enUS_Uri = "/api/service";
	public static final String Search_enUS_ImageUri = "/png/api/service-999.png";
	public static final String GET_enUS_Uri = "/api/service/{id}";
	public static final String GET_enUS_ImageUri = "/png/api/service/{id}-999.png";
	public static final String PATCH_enUS_Uri = "/api/service";
	public static final String PATCH_enUS_ImageUri = "/png/api/service-999.png";
	public static final String POST_enUS_Uri = "/api/service";
	public static final String POST_enUS_ImageUri = "/png/api/service-999.png";
	public static final String PUTImport_enUS_Uri = "/api/service-import";
	public static final String PUTImport_enUS_ImageUri = "/png/api/service-import-999.png";
	public static final String SearchPage_enUS_Uri = "/service";
	public static final String SearchPage_enUS_ImageUri = "/png/service-999.png";

	public static final String CompanyService_Icon = "<i class=\"fa-regular fa-conveyor-belt\"></i>";

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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = CompanyService.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyService nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (CompanyService)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return CompanyService.staticSearchName(siteRequest_, CompanyService.staticSetName(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = CompanyService.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyService descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (CompanyService)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return CompanyService.staticSearchDescription(siteRequest_, CompanyService.staticSetDescription(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageId(Wrap<String> w);

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String o) {
		this.pageId = CompanyService.staticSetPageId(siteRequest_, o);
	}
	public static String staticSetPageId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyService pageIdInit() {
		Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
		if(pageId == null) {
			_pageId(pageIdWrap);
			Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
				setPageId(o);
			});
		}
		return (CompanyService)this;
	}

	public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
		return CompanyService.staticSearchPageId(siteRequest_, CompanyService.staticSetPageId(siteRequest_, o)).toString();
	}

	public String sqlPageId() {
		return pageId;
	}

	/////////////////
	// resourceUri //
	/////////////////


	/**	 The entity resourceUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String resourceUri;

	/**	<br> The entity resourceUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:resourceUri">Find the entity resourceUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _resourceUri(Wrap<String> w);

	public String getResourceUri() {
		return resourceUri;
	}
	public void setResourceUri(String o) {
		this.resourceUri = CompanyService.staticSetResourceUri(siteRequest_, o);
	}
	public static String staticSetResourceUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyService resourceUriInit() {
		Wrap<String> resourceUriWrap = new Wrap<String>().var("resourceUri");
		if(resourceUri == null) {
			_resourceUri(resourceUriWrap);
			Optional.ofNullable(resourceUriWrap.getO()).ifPresent(o -> {
				setResourceUri(o);
			});
		}
		return (CompanyService)this;
	}

	public static String staticSearchResourceUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrResourceUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResourceUri(SiteRequest siteRequest_, String o) {
		return CompanyService.staticSearchResourceUri(siteRequest_, CompanyService.staticSetResourceUri(siteRequest_, o)).toString();
	}

	public String sqlResourceUri() {
		return resourceUri;
	}

	/////////////////
	// templateUri //
	/////////////////


	/**	 The entity templateUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String templateUri;

	/**	<br> The entity templateUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:templateUri">Find the entity templateUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _templateUri(Wrap<String> w);

	public String getTemplateUri() {
		return templateUri;
	}
	public void setTemplateUri(String o) {
		this.templateUri = CompanyService.staticSetTemplateUri(siteRequest_, o);
	}
	public static String staticSetTemplateUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyService templateUriInit() {
		Wrap<String> templateUriWrap = new Wrap<String>().var("templateUri");
		if(templateUri == null) {
			_templateUri(templateUriWrap);
			Optional.ofNullable(templateUriWrap.getO()).ifPresent(o -> {
				setTemplateUri(o);
			});
		}
		return (CompanyService)this;
	}

	public static String staticSearchTemplateUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTemplateUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTemplateUri(SiteRequest siteRequest_, String o) {
		return CompanyService.staticSearchTemplateUri(siteRequest_, CompanyService.staticSetTemplateUri(siteRequest_, o)).toString();
	}

	public String sqlTemplateUri() {
		return templateUri;
	}

	/////////
	// uri //
	/////////


	/**	 The entity uri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String uri;

	/**	<br> The entity uri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:uri">Find the entity uri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _uri(Wrap<String> w);

	public String getUri() {
		return uri;
	}
	public void setUri(String o) {
		this.uri = CompanyService.staticSetUri(siteRequest_, o);
	}
	public static String staticSetUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyService uriInit() {
		Wrap<String> uriWrap = new Wrap<String>().var("uri");
		if(uri == null) {
			_uri(uriWrap);
			Optional.ofNullable(uriWrap.getO()).ifPresent(o -> {
				setUri(o);
			});
		}
		return (CompanyService)this;
	}

	public static String staticSearchUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUri(SiteRequest siteRequest_, String o) {
		return CompanyService.staticSearchUri(siteRequest_, CompanyService.staticSetUri(siteRequest_, o)).toString();
	}

	public String sqlUri() {
		return uri;
	}

	/////////
	// url //
	/////////


	/**	 The entity url
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String url;

	/**	<br> The entity url
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:url">Find the entity url in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _url(Wrap<String> w);

	public String getUrl() {
		return url;
	}
	public void setUrl(String o) {
		this.url = CompanyService.staticSetUrl(siteRequest_, o);
	}
	public static String staticSetUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyService urlInit() {
		Wrap<String> urlWrap = new Wrap<String>().var("url");
		if(url == null) {
			_url(urlWrap);
			Optional.ofNullable(urlWrap.getO()).ifPresent(o -> {
				setUrl(o);
			});
		}
		return (CompanyService)this;
	}

	public static String staticSearchUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUrl(SiteRequest siteRequest_, String o) {
		return CompanyService.staticSearchUrl(siteRequest_, CompanyService.staticSetUrl(siteRequest_, o)).toString();
	}

	public String sqlUrl() {
		return url;
	}

	///////////
	// title //
	///////////


	/**	 The entity title
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String title;

	/**	<br> The entity title
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:title">Find the entity title in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _title(Wrap<String> w);

	public String getTitle() {
		return title;
	}
	public void setTitle(String o) {
		this.title = CompanyService.staticSetTitle(siteRequest_, o);
	}
	public static String staticSetTitle(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyService titleInit() {
		Wrap<String> titleWrap = new Wrap<String>().var("title");
		if(title == null) {
			_title(titleWrap);
			Optional.ofNullable(titleWrap.getO()).ifPresent(o -> {
				setTitle(o);
			});
		}
		return (CompanyService)this;
	}

	public static String staticSearchTitle(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTitle(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTitle(SiteRequest siteRequest_, String o) {
		return CompanyService.staticSearchTitle(siteRequest_, CompanyService.staticSetTitle(siteRequest_, o)).toString();
	}

	public String sqlTitle() {
		return title;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepCompanyService(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyService();
	}

	public Future<Void> promiseDeepCompanyService() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyService(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseResult(siteRequest_).onSuccess(b -> {
				promise.complete();
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseCompanyService(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				nameInit();
				descriptionInit();
				pageIdInit();
				resourceUriInit();
				templateUriInit();
				uriInit();
				urlInit();
				titleInit();
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
		return promiseDeepCompanyService(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyService(SiteRequest siteRequest_) {
			super.siteRequestBaseResult(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyService(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyService(v);
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
	public Object obtainCompanyService(String var) {
		CompanyService oCompanyService = (CompanyService)this;
		switch(var) {
			case "name":
				return oCompanyService.name;
			case "description":
				return oCompanyService.description;
			case "pageId":
				return oCompanyService.pageId;
			case "resourceUri":
				return oCompanyService.resourceUri;
			case "templateUri":
				return oCompanyService.templateUri;
			case "uri":
				return oCompanyService.uri;
			case "url":
				return oCompanyService.url;
			case "title":
				return oCompanyService.title;
			default:
				return super.obtainBaseResult(var);
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
				o = relateCompanyService(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyService(String var, Object val) {
		CompanyService oCompanyService = (CompanyService)this;
		switch(var) {
			default:
				return super.relateBaseResult(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyService(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyService(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return CompanyService.staticSetName(siteRequest_, o);
		case "description":
			return CompanyService.staticSetDescription(siteRequest_, o);
		case "pageId":
			return CompanyService.staticSetPageId(siteRequest_, o);
		case "resourceUri":
			return CompanyService.staticSetResourceUri(siteRequest_, o);
		case "templateUri":
			return CompanyService.staticSetTemplateUri(siteRequest_, o);
		case "uri":
			return CompanyService.staticSetUri(siteRequest_, o);
		case "url":
			return CompanyService.staticSetUrl(siteRequest_, o);
		case "title":
			return CompanyService.staticSetTitle(siteRequest_, o);
			default:
				return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyService(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyService(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return CompanyService.staticSearchName(siteRequest_, (String)o);
		case "description":
			return CompanyService.staticSearchDescription(siteRequest_, (String)o);
		case "pageId":
			return CompanyService.staticSearchPageId(siteRequest_, (String)o);
		case "resourceUri":
			return CompanyService.staticSearchResourceUri(siteRequest_, (String)o);
		case "templateUri":
			return CompanyService.staticSearchTemplateUri(siteRequest_, (String)o);
		case "uri":
			return CompanyService.staticSearchUri(siteRequest_, (String)o);
		case "url":
			return CompanyService.staticSearchUrl(siteRequest_, (String)o);
		case "title":
			return CompanyService.staticSearchTitle(siteRequest_, (String)o);
			default:
				return BaseResult.staticSearchBaseResult(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyService(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyService(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return CompanyService.staticSearchStrName(siteRequest_, (String)o);
		case "description":
			return CompanyService.staticSearchStrDescription(siteRequest_, (String)o);
		case "pageId":
			return CompanyService.staticSearchStrPageId(siteRequest_, (String)o);
		case "resourceUri":
			return CompanyService.staticSearchStrResourceUri(siteRequest_, (String)o);
		case "templateUri":
			return CompanyService.staticSearchStrTemplateUri(siteRequest_, (String)o);
		case "uri":
			return CompanyService.staticSearchStrUri(siteRequest_, (String)o);
		case "url":
			return CompanyService.staticSearchStrUrl(siteRequest_, (String)o);
		case "title":
			return CompanyService.staticSearchStrTitle(siteRequest_, (String)o);
			default:
				return BaseResult.staticSearchStrBaseResult(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyService(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyService(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return CompanyService.staticSearchFqName(siteRequest_, o);
		case "description":
			return CompanyService.staticSearchFqDescription(siteRequest_, o);
		case "pageId":
			return CompanyService.staticSearchFqPageId(siteRequest_, o);
		case "resourceUri":
			return CompanyService.staticSearchFqResourceUri(siteRequest_, o);
		case "templateUri":
			return CompanyService.staticSearchFqTemplateUri(siteRequest_, o);
		case "uri":
			return CompanyService.staticSearchFqUri(siteRequest_, o);
		case "url":
			return CompanyService.staticSearchFqUrl(siteRequest_, o);
		case "title":
			return CompanyService.staticSearchFqTitle(siteRequest_, o);
			default:
				return BaseResult.staticSearchFqBaseResult(entityVar,  siteRequest_, o);
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
					o = persistCompanyService(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistCompanyService(String var, Object val) {
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
			} else if("resourceuri".equals(varLower)) {
				if(val instanceof String) {
					setResourceUri((String)val);
				}
				saves.add("resourceUri");
				return val;
			} else if("templateuri".equals(varLower)) {
				if(val instanceof String) {
					setTemplateUri((String)val);
				}
				saves.add("templateUri");
				return val;
			} else if("uri".equals(varLower)) {
				if(val instanceof String) {
					setUri((String)val);
				}
				saves.add("uri");
				return val;
			} else if("url".equals(varLower)) {
				if(val instanceof String) {
					setUrl((String)val);
				}
				saves.add("url");
				return val;
			} else if("title".equals(varLower)) {
				if(val instanceof String) {
					setTitle((String)val);
				}
				saves.add("title");
				return val;
		} else {
			return super.persistBaseResult(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateCompanyService(doc);
	}
	public void populateCompanyService(SolrResponse.Doc doc) {
		CompanyService oCompanyService = (CompanyService)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oCompanyService.setName(name);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oCompanyService.setDescription(description);
			}

			if(saves.contains("pageId")) {
				String pageId = (String)doc.get("pageId_docvalues_string");
				if(pageId != null)
					oCompanyService.setPageId(pageId);
			}

			if(saves.contains("resourceUri")) {
				String resourceUri = (String)doc.get("resourceUri_docvalues_string");
				if(resourceUri != null)
					oCompanyService.setResourceUri(resourceUri);
			}

			if(saves.contains("templateUri")) {
				String templateUri = (String)doc.get("templateUri_docvalues_string");
				if(templateUri != null)
					oCompanyService.setTemplateUri(templateUri);
			}

			if(saves.contains("uri")) {
				String uri = (String)doc.get("uri_docvalues_string");
				if(uri != null)
					oCompanyService.setUri(uri);
			}

			if(saves.contains("url")) {
				String url = (String)doc.get("url_docvalues_string");
				if(url != null)
					oCompanyService.setUrl(url);
			}

			if(saves.contains("title")) {
				String title = (String)doc.get("title_docvalues_string");
				if(title != null)
					oCompanyService.setTitle(title);
			}
		}

		super.populateBaseResult(doc);
	}

	public void indexCompanyService(JsonObject doc) {
		if(name != null) {
			doc.put("name_docvalues_string", name);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(pageId != null) {
			doc.put("pageId_docvalues_string", pageId);
		}
		if(resourceUri != null) {
			doc.put("resourceUri_docvalues_string", resourceUri);
		}
		if(templateUri != null) {
			doc.put("templateUri_docvalues_string", templateUri);
		}
		if(uri != null) {
			doc.put("uri_docvalues_string", uri);
		}
		if(url != null) {
			doc.put("url_docvalues_string", url);
		}
		if(title != null) {
			doc.put("title_docvalues_string", title);
		}
		super.indexBaseResult(doc);

	}

	public static String varStoredCompanyService(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "resourceUri":
				return "resourceUri_docvalues_string";
			case "templateUri":
				return "templateUri_docvalues_string";
			case "uri":
				return "uri_docvalues_string";
			case "url":
				return "url_docvalues_string";
			case "title":
				return "title_docvalues_string";
			default:
				return BaseResult.varStoredBaseResult(entityVar);
		}
	}

	public static String varIndexedCompanyService(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "resourceUri":
				return "resourceUri_docvalues_string";
			case "templateUri":
				return "templateUri_docvalues_string";
			case "uri":
				return "uri_docvalues_string";
			case "url":
				return "url_docvalues_string";
			case "title":
				return "title_docvalues_string";
			default:
				return BaseResult.varIndexedBaseResult(entityVar);
		}
	}

	public static String searchVarCompanyService(String searchVar) {
		switch(searchVar) {
			case "name_docvalues_string":
				return "name";
			case "description_docvalues_string":
				return "description";
			case "pageId_docvalues_string":
				return "pageId";
			case "resourceUri_docvalues_string":
				return "resourceUri";
			case "templateUri_docvalues_string":
				return "templateUri";
			case "uri_docvalues_string":
				return "uri";
			case "url_docvalues_string":
				return "url";
			case "title_docvalues_string":
				return "title";
			default:
				return BaseResult.searchVarBaseResult(searchVar);
		}
	}

	public static String varSearchCompanyService(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSearchBaseResult(entityVar);
		}
	}

	public static String varSuggestedCompanyService(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSuggestedBaseResult(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeCompanyService(doc);
	}
	public void storeCompanyService(SolrResponse.Doc doc) {
		CompanyService oCompanyService = (CompanyService)this;
		SiteRequest siteRequest = oCompanyService.getSiteRequest_();

		oCompanyService.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyService.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyService.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyService.setResourceUri(Optional.ofNullable(doc.get("resourceUri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyService.setTemplateUri(Optional.ofNullable(doc.get("templateUri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyService.setUri(Optional.ofNullable(doc.get("uri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyService.setUrl(Optional.ofNullable(doc.get("url_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyService.setTitle(Optional.ofNullable(doc.get("title_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseResult(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestCompanyService() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof CompanyService) {
			CompanyService original = (CompanyService)o;
			if(!Objects.equals(name, original.getName()))
				apiRequest.addVars("name");
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
			if(!Objects.equals(pageId, original.getPageId()))
				apiRequest.addVars("pageId");
			if(!Objects.equals(resourceUri, original.getResourceUri()))
				apiRequest.addVars("resourceUri");
			if(!Objects.equals(templateUri, original.getTemplateUri()))
				apiRequest.addVars("templateUri");
			if(!Objects.equals(uri, original.getUri()))
				apiRequest.addVars("uri");
			if(!Objects.equals(url, original.getUrl()))
				apiRequest.addVars("url");
			if(!Objects.equals(title, original.getTitle()))
				apiRequest.addVars("title");
			super.apiRequestBaseResult();
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
		sb.append(Optional.ofNullable(resourceUri).map(v -> "resourceUri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(templateUri).map(v -> "templateUri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(uri).map(v -> "uri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(url).map(v -> "url: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(title).map(v -> "title: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "CompanyService";
	public static final String CLASS_API_ADDRESS_CompanyService = "computate.org-enUS-CompanyService";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_CompanyService;
	}
	public static final String VAR_name = "name";
	public static final String VAR_description = "description";
	public static final String VAR_pageId = "pageId";
	public static final String VAR_resourceUri = "resourceUri";
	public static final String VAR_templateUri = "templateUri";
	public static final String VAR_uri = "uri";
	public static final String VAR_url = "url";
	public static final String VAR_title = "title";

	public static List<String> varsQForClass() {
		return CompanyService.varsQCompanyService(new ArrayList<String>());
	}
	public static List<String> varsQCompanyService(List<String> vars) {
		BaseResult.varsQBaseResult(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return CompanyService.varsFqCompanyService(new ArrayList<String>());
	}
	public static List<String> varsFqCompanyService(List<String> vars) {
		vars.add(VAR_name);
		vars.add(VAR_description);
		vars.add(VAR_pageId);
		vars.add(VAR_resourceUri);
		vars.add(VAR_templateUri);
		vars.add(VAR_uri);
		vars.add(VAR_url);
		BaseResult.varsFqBaseResult(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return CompanyService.varsRangeCompanyService(new ArrayList<String>());
	}
	public static List<String> varsRangeCompanyService(List<String> vars) {
		BaseResult.varsRangeBaseResult(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_name = "service name";
	public static final String DISPLAY_NAME_description = "service description";
	public static final String DISPLAY_NAME_pageId = "Page ID";
	public static final String DISPLAY_NAME_resourceUri = "resource URI";
	public static final String DISPLAY_NAME_templateUri = "template URI";
	public static final String DISPLAY_NAME_uri = "URI";
	public static final String DISPLAY_NAME_url = "URL";
	public static final String DISPLAY_NAME_title = "title";

	public static String displayNameForClass(String var) {
		return CompanyService.displayNameCompanyService(var);
	}
	public static String displayNameCompanyService(String var) {
		switch(var) {
		case VAR_name:
			return DISPLAY_NAME_name;
		case VAR_description:
			return DISPLAY_NAME_description;
		case VAR_pageId:
			return DISPLAY_NAME_pageId;
		case VAR_resourceUri:
			return DISPLAY_NAME_resourceUri;
		case VAR_templateUri:
			return DISPLAY_NAME_templateUri;
		case VAR_uri:
			return DISPLAY_NAME_uri;
		case VAR_url:
			return DISPLAY_NAME_url;
		case VAR_title:
			return DISPLAY_NAME_title;
		default:
			return BaseResult.displayNameBaseResult(var);
		}
	}

	public static String descriptionCompanyService(String var) {
		switch(var) {
		case VAR_name:
			return "The service name. ";
		case VAR_description:
			return "The service description. ";
		case VAR_pageId:
			return "The ID for this page. ";
		case VAR_resourceUri:
			return "The resource relative URI for this page. ";
		case VAR_templateUri:
			return "The template relative URI for this page. ";
		case VAR_uri:
			return "The relative URI for this page. ";
		case VAR_url:
			return "The URL for this page. ";
		case VAR_title:
			return "The title of this page. ";
			default:
				return BaseResult.descriptionBaseResult(var);
		}
	}

	public static String classSimpleNameCompanyService(String var) {
		switch(var) {
		case VAR_name:
			return "String";
		case VAR_description:
			return "String";
		case VAR_pageId:
			return "String";
		case VAR_resourceUri:
			return "String";
		case VAR_templateUri:
			return "String";
		case VAR_uri:
			return "String";
		case VAR_url:
			return "String";
		case VAR_title:
			return "String";
			default:
				return BaseResult.classSimpleNameBaseResult(var);
		}
	}

	public static Integer htmColumnCompanyService(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
			default:
				return BaseResult.htmColumnBaseResult(var);
		}
	}

	public static Integer htmRowCompanyService(String var) {
		switch(var) {
		case VAR_name:
			return 3;
		case VAR_description:
			return 3;
		case VAR_pageId:
			return 4;
		case VAR_uri:
			return 3;
		case VAR_url:
			return 3;
			default:
				return BaseResult.htmRowBaseResult(var);
		}
	}

	public static Integer htmCellCompanyService(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
		case VAR_pageId:
			return 1;
		case VAR_uri:
			return 2;
		case VAR_url:
			return 2;
			default:
				return BaseResult.htmCellBaseResult(var);
		}
	}

	public static Integer lengthMinCompanyService(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMinBaseResult(var);
		}
	}

	public static Integer lengthMaxCompanyService(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMaxBaseResult(var);
		}
	}

	public static Integer maxCompanyService(String var) {
		switch(var) {
			default:
				return BaseResult.maxBaseResult(var);
		}
	}

	public static Integer minCompanyService(String var) {
		switch(var) {
			default:
				return BaseResult.minBaseResult(var);
		}
	}
}