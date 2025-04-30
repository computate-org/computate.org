package org.computate.site.model.website;

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
import java.lang.Integer;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyWebsiteGen into the class CompanyWebsite. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the CompanyWebsite API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "Model: true" if you wish to persist these CompanyWebsite objects in a relational PostgreSQL database transactionally in the RESTful API. 
 * The code to persist and query the CompanyWebsiteGen data in the database will then be automatically generated. 
 * </li>
 * <h3>About the CompanyWebsite class and it's generated class CompanyWebsiteGen&lt;BaseResult&gt;: </h3>extends CompanyWebsiteGen
 * <p>
 * This Java class extends a generated Java class CompanyWebsiteGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsite">Find the class CompanyWebsite in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyWebsiteGen<BaseResult>
 * <p>This <code>class CompanyWebsite extends CompanyWebsiteGen&lt;BaseResult&gt;</code>, which means it extends a newly generated CompanyWebsiteGen. 
 * The generated <code>class CompanyWebsiteGen extends BaseResult</code> which means that CompanyWebsite extends CompanyWebsiteGen which extends BaseResult. 
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
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: websites"</b>, which groups all of the OpenAPIs for CompanyWebsite objects under the tag "websites". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/website</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/website"</b>, which defines the base API URI for CompanyWebsite objects as "/en-us/api/website" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyWebsite class will inherit the helpful inherited class comments from the super class CompanyWebsiteGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 10</h2>
 * <p>This class contains a comment <b>"Order: 10"</b>, which means this class will be sorted by the given number 10 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 10</h2>
 * <p>This class contains a comment <b>"SqlOrder: 10"</b>, which means this class will be sorted by the given number 10 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.website.CompanyWebsitePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.site.model.website.CompanyWebsitePage extends org.computate.site.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyWebsite Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a website</h2>
 * <p>This class contains a comment <b>"AName.enUS: a website"</b>, which identifies the language context to describe a CompanyWebsite as "a website". 
 * </p>
 * <p>
 * Delete the class CompanyWebsite in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsite&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.website in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.website&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyWebsiteGen<DEV> extends BaseResult {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyWebsite.class);

	public static final String Description_enUS = "See the live websites built with computate";
	public static final String AName_enUS = "a website";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this website";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the website";
	public static final String SingularName_enUS = "website";
	public static final String PluralName_enUS = "websites";
	public static final String NameActual_enUS = "current website";
	public static final String AllName_enUS = "all websites";
	public static final String SearchAllNameBy_enUS = "search websites by ";
	public static final String Title_enUS = "websites";
	public static final String ThePluralName_enUS = "the websites";
	public static final String NoNameFound_enUS = "no website found";
	public static final String ApiUri_enUS = "/en-us/api/website";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/website";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/website/{pageId}";
	public static final String OfName_enUS = "of website";
	public static final String ANameAdjective_enUS = "a website";
	public static final String NameAdjectiveSingular_enUS = "website";
	public static final String NameAdjectivePlural_enUS = "websites";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/website";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/website";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/website";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/website/{pageId}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/website/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/website/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/website";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/website";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/website";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/website";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/website";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/website";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/website-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/website-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/website-import";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/website";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/website";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/website";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/website/{pageId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/website/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/website/%s";
	public static final String DisplayPage_enUS_OpenApiUri = "/en-us/view/website/{pageId}";
	public static final String DisplayPage_enUS_StringFormatUri = "/en-us/view/website/%s";
	public static final String DisplayPage_enUS_StringFormatUrl = "%s/en-us/view/website/%s";

	public static final String Icon = "<i class=\"fa-duotone fa-regular fa-globe-pointer\"></i>";

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsite&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = CompanyWebsite.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebsite nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (CompanyWebsite)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return CompanyWebsite.staticSearchName(siteRequest_, CompanyWebsite.staticSetName(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsite&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = CompanyWebsite.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebsite descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (CompanyWebsite)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return CompanyWebsite.staticSearchDescription(siteRequest_, CompanyWebsite.staticSetDescription(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsite&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageId(Wrap<String> w);

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String o) {
		this.pageId = CompanyWebsite.staticSetPageId(siteRequest_, o);
	}
	public static String staticSetPageId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebsite pageIdInit() {
		Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
		if(pageId == null) {
			_pageId(pageIdWrap);
			Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
				setPageId(o);
			});
		}
		return (CompanyWebsite)this;
	}

	public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
		return CompanyWebsite.staticSearchPageId(siteRequest_, CompanyWebsite.staticSetPageId(siteRequest_, o)).toString();
	}

	public String sqlPageId() {
		return pageId;
	}

	////////////////
	// websiteNum //
	////////////////


	/**	 The entity websiteNum
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer websiteNum;

	/**	<br> The entity websiteNum
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsite&fq=entiteVar_enUS_indexed_string:websiteNum">Find the entity websiteNum in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _websiteNum(Wrap<Integer> w);

	public Integer getWebsiteNum() {
		return websiteNum;
	}

	public void setWebsiteNum(Integer websiteNum) {
		this.websiteNum = websiteNum;
	}
	@JsonIgnore
	public void setWebsiteNum(String o) {
		this.websiteNum = CompanyWebsite.staticSetWebsiteNum(siteRequest_, o);
	}
	public static Integer staticSetWebsiteNum(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyWebsite websiteNumInit() {
		Wrap<Integer> websiteNumWrap = new Wrap<Integer>().var("websiteNum");
		if(websiteNum == null) {
			_websiteNum(websiteNumWrap);
			Optional.ofNullable(websiteNumWrap.getO()).ifPresent(o -> {
				setWebsiteNum(o);
			});
		}
		return (CompanyWebsite)this;
	}

	public static Integer staticSearchWebsiteNum(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrWebsiteNum(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqWebsiteNum(SiteRequest siteRequest_, String o) {
		return CompanyWebsite.staticSearchWebsiteNum(siteRequest_, CompanyWebsite.staticSetWebsiteNum(siteRequest_, o)).toString();
	}

	public Integer sqlWebsiteNum() {
		return websiteNum;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<CompanyWebsiteGen<DEV>> promiseDeepCompanyWebsite(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyWebsite();
	}

	public Future<CompanyWebsiteGen<DEV>> promiseDeepCompanyWebsite() {
		Promise<CompanyWebsiteGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyWebsite(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseResult(siteRequest_).onSuccess(b -> {
				promise.complete(this);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseCompanyWebsite(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				nameInit();
				descriptionInit();
				pageIdInit();
				websiteNumInit();
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

	@Override public Future<? extends CompanyWebsiteGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyWebsite(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyWebsite(SiteRequest siteRequest_) {
			super.siteRequestBaseResult(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyWebsite(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyWebsite(v);
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
	public Object obtainCompanyWebsite(String var) {
		CompanyWebsite oCompanyWebsite = (CompanyWebsite)this;
		switch(var) {
			case "name":
				return oCompanyWebsite.name;
			case "description":
				return oCompanyWebsite.description;
			case "pageId":
				return oCompanyWebsite.pageId;
			case "websiteNum":
				return oCompanyWebsite.websiteNum;
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
				o = relateCompanyWebsite(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyWebsite(String var, Object val) {
		CompanyWebsite oCompanyWebsite = (CompanyWebsite)this;
		switch(var) {
			default:
				return super.relateBaseResult(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyWebsite(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyWebsite(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return CompanyWebsite.staticSetName(siteRequest_, o);
		case "description":
			return CompanyWebsite.staticSetDescription(siteRequest_, o);
		case "pageId":
			return CompanyWebsite.staticSetPageId(siteRequest_, o);
		case "websiteNum":
			return CompanyWebsite.staticSetWebsiteNum(siteRequest_, o);
			default:
				return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyWebsite(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyWebsite(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return CompanyWebsite.staticSearchName(siteRequest_, (String)o);
		case "description":
			return CompanyWebsite.staticSearchDescription(siteRequest_, (String)o);
		case "pageId":
			return CompanyWebsite.staticSearchPageId(siteRequest_, (String)o);
		case "websiteNum":
			return CompanyWebsite.staticSearchWebsiteNum(siteRequest_, (Integer)o);
			default:
				return BaseResult.staticSearchBaseResult(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyWebsite(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyWebsite(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return CompanyWebsite.staticSearchStrName(siteRequest_, (String)o);
		case "description":
			return CompanyWebsite.staticSearchStrDescription(siteRequest_, (String)o);
		case "pageId":
			return CompanyWebsite.staticSearchStrPageId(siteRequest_, (String)o);
		case "websiteNum":
			return CompanyWebsite.staticSearchStrWebsiteNum(siteRequest_, (Integer)o);
			default:
				return BaseResult.staticSearchStrBaseResult(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyWebsite(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyWebsite(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return CompanyWebsite.staticSearchFqName(siteRequest_, o);
		case "description":
			return CompanyWebsite.staticSearchFqDescription(siteRequest_, o);
		case "pageId":
			return CompanyWebsite.staticSearchFqPageId(siteRequest_, o);
		case "websiteNum":
			return CompanyWebsite.staticSearchFqWebsiteNum(siteRequest_, o);
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
					o = persistCompanyWebsite(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistCompanyWebsite(String var, Object val) {
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
			} else if("websitenum".equals(varLower)) {
				if(val instanceof Integer) {
					setWebsiteNum((Integer)val);
				} else {
					setWebsiteNum(val == null ? null : val.toString());
				}
				saves.add("websiteNum");
				return val;
		} else {
			return super.persistBaseResult(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateCompanyWebsite(doc);
	}
	public void populateCompanyWebsite(SolrResponse.Doc doc) {
		CompanyWebsite oCompanyWebsite = (CompanyWebsite)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oCompanyWebsite.setName(name);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oCompanyWebsite.setDescription(description);
			}

			if(saves.contains("pageId")) {
				String pageId = (String)doc.get("pageId_docvalues_string");
				if(pageId != null)
					oCompanyWebsite.setPageId(pageId);
			}

			if(saves.contains("websiteNum")) {
				Integer websiteNum = (Integer)doc.get("websiteNum_docvalues_int");
				if(websiteNum != null)
					oCompanyWebsite.setWebsiteNum(websiteNum);
			}
		}

		super.populateBaseResult(doc);
	}

	public void indexCompanyWebsite(JsonObject doc) {
		if(name != null) {
			doc.put("name_docvalues_string", name);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(pageId != null) {
			doc.put("pageId_docvalues_string", pageId);
		}
		if(websiteNum != null) {
			doc.put("websiteNum_docvalues_int", websiteNum);
		}
		super.indexBaseResult(doc);

	}

	public static String varStoredCompanyWebsite(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "websiteNum":
				return "websiteNum_docvalues_int";
			default:
				return BaseResult.varStoredBaseResult(entityVar);
		}
	}

	public static String varIndexedCompanyWebsite(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "websiteNum":
				return "websiteNum_docvalues_int";
			default:
				return BaseResult.varIndexedBaseResult(entityVar);
		}
	}

	public static String searchVarCompanyWebsite(String searchVar) {
		switch(searchVar) {
			case "name_docvalues_string":
				return "name";
			case "description_docvalues_string":
				return "description";
			case "pageId_docvalues_string":
				return "pageId";
			case "websiteNum_docvalues_int":
				return "websiteNum";
			default:
				return BaseResult.searchVarBaseResult(searchVar);
		}
	}

	public static String varSearchCompanyWebsite(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSearchBaseResult(entityVar);
		}
	}

	public static String varSuggestedCompanyWebsite(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSuggestedBaseResult(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeCompanyWebsite(doc);
	}
	public void storeCompanyWebsite(SolrResponse.Doc doc) {
		CompanyWebsite oCompanyWebsite = (CompanyWebsite)this;
		SiteRequest siteRequest = oCompanyWebsite.getSiteRequest_();

		oCompanyWebsite.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyWebsite.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyWebsite.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyWebsite.setWebsiteNum(Optional.ofNullable(doc.get("websiteNum_docvalues_int")).map(v -> v.toString()).orElse(null));

		super.storeBaseResult(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestCompanyWebsite() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof CompanyWebsite) {
			CompanyWebsite original = (CompanyWebsite)o;
			if(!Objects.equals(name, original.getName()))
				apiRequest.addVars("name");
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
			if(!Objects.equals(pageId, original.getPageId()))
				apiRequest.addVars("pageId");
			if(!Objects.equals(websiteNum, original.getWebsiteNum()))
				apiRequest.addVars("websiteNum");
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
		sb.append(Optional.ofNullable(websiteNum).map(v -> "websiteNum: " + v + "\n").orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "CompanyWebsite";
	public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.website.CompanyWebsite";
	public static final String CLASS_API_ADDRESS_CompanyWebsite = "computate.org-enUS-CompanyWebsite";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_CompanyWebsite;
	}
	public static final String VAR_name = "name";
	public static final String VAR_description = "description";
	public static final String VAR_pageId = "pageId";
	public static final String VAR_websiteNum = "websiteNum";

	public static List<String> varsQForClass() {
		return CompanyWebsite.varsQCompanyWebsite(new ArrayList<String>());
	}
	public static List<String> varsQCompanyWebsite(List<String> vars) {
		BaseResult.varsQBaseResult(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return CompanyWebsite.varsFqCompanyWebsite(new ArrayList<String>());
	}
	public static List<String> varsFqCompanyWebsite(List<String> vars) {
		vars.add(VAR_name);
		vars.add(VAR_description);
		vars.add(VAR_pageId);
		vars.add(VAR_websiteNum);
		BaseResult.varsFqBaseResult(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return CompanyWebsite.varsRangeCompanyWebsite(new ArrayList<String>());
	}
	public static List<String> varsRangeCompanyWebsite(List<String> vars) {
		vars.add(VAR_websiteNum);
		BaseResult.varsRangeBaseResult(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_name = "service name";
	public static final String DISPLAY_NAME_description = "service description";
	public static final String DISPLAY_NAME_pageId = "Page ID";
	public static final String DISPLAY_NAME_websiteNum = "Website Number";

	@Override
	public String idForClass() {
		return pageId;
	}

	@Override
	public String titleForClass() {
		return objectTitle;
	}

	@Override
	public String nameForClass() {
		return name;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return CompanyWebsite.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/website/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return "%s/en-us/view/website/%s";
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return CompanyWebsite.displayNameCompanyWebsite(var);
	}
	public static String displayNameCompanyWebsite(String var) {
		switch(var) {
		case VAR_name:
			return DISPLAY_NAME_name;
		case VAR_description:
			return DISPLAY_NAME_description;
		case VAR_pageId:
			return DISPLAY_NAME_pageId;
		case VAR_websiteNum:
			return DISPLAY_NAME_websiteNum;
		default:
			return BaseResult.displayNameBaseResult(var);
		}
	}

	public static String descriptionCompanyWebsite(String var) {
		switch(var) {
		case VAR_name:
			return "The service name. ";
		case VAR_description:
			return "The service description. ";
		case VAR_pageId:
			return "The ID for this page. ";
		case VAR_websiteNum:
			return "The website number for sorting. ";
			default:
				return BaseResult.descriptionBaseResult(var);
		}
	}

	public static String classSimpleNameCompanyWebsite(String var) {
		switch(var) {
		case VAR_name:
			return "String";
		case VAR_description:
			return "String";
		case VAR_pageId:
			return "String";
		case VAR_websiteNum:
			return "Integer";
			default:
				return BaseResult.classSimpleNameBaseResult(var);
		}
	}

	public static Integer htmColumnCompanyWebsite(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
			default:
				return BaseResult.htmColumnBaseResult(var);
		}
	}

	public static Integer htmRowCompanyWebsite(String var) {
		switch(var) {
		case VAR_name:
			return 3;
		case VAR_description:
			return 3;
		case VAR_pageId:
			return 99;
			default:
				return BaseResult.htmRowBaseResult(var);
		}
	}

	public static Integer htmCellCompanyWebsite(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
		case VAR_pageId:
			return 1;
			default:
				return BaseResult.htmCellBaseResult(var);
		}
	}

	public static Integer lengthMinCompanyWebsite(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMinBaseResult(var);
		}
	}

	public static Integer lengthMaxCompanyWebsite(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMaxBaseResult(var);
		}
	}

	public static Integer maxCompanyWebsite(String var) {
		switch(var) {
			default:
				return BaseResult.maxBaseResult(var);
		}
	}

	public static Integer minCompanyWebsite(String var) {
		switch(var) {
			default:
				return BaseResult.minBaseResult(var);
		}
	}
}
