package org.computate.site.model.about;

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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyAboutGen into the class CompanyAbout. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the CompanyAbout API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "Model: true" if you wish to persist these CompanyAbout objects in a relational PostgreSQL database transactionally in the RESTful API. 
 * The code to persist and query the CompanyAboutGen data in the database will then be automatically generated. 
 * </li>
 * <h3>About the CompanyAbout class and it's generated class CompanyAboutGen&lt;BaseResult&gt;: </h3>extends CompanyAboutGen
 * <p>
 * This Java class extends a generated Java class CompanyAboutGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAbout">Find the class CompanyAbout in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyAboutGen<BaseResult>
 * <p>This <code>class CompanyAbout extends CompanyAboutGen&lt;BaseResult&gt;</code>, which means it extends a newly generated CompanyAboutGen. 
 * The generated <code>class CompanyAboutGen extends BaseResult</code> which means that CompanyAbout extends CompanyAboutGen which extends BaseResult. 
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
 * <p>This class contains a comment <b>"ApiTag: about"</b>, which groups all of the OpenAPIs for CompanyAbout objects under the tag "about". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/about</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/about"</b>, which defines the base API URI for CompanyAbout objects as "/en-us/api/about" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyAbout class will inherit the helpful inherited class comments from the super class CompanyAboutGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 2</h2>
 * <p>This class contains a comment <b>"Order: 2"</b>, which means this class will be sorted by the given number 2 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 2</h2>
 * <p>This class contains a comment <b>"SqlOrder: 2"</b>, which means this class will be sorted by the given number 2 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.about.CompanyAboutPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.site.model.about.CompanyAboutPage extends org.computate.site.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyAbout Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: an about page</h2>
 * <p>This class contains a comment <b>"AName.enUS: an about page"</b>, which identifies the language context to describe a CompanyAbout as "an about page". 
 * </p>
 * <p>
 * Delete the class CompanyAbout in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAbout&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.about in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.about&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyAboutGen<DEV> extends BaseResult {
  protected static final Logger LOG = LoggerFactory.getLogger(CompanyAbout.class);

  public static final String Description_enUS = "Learn about our team, company, culture, and values. ";
  public static final String AName_enUS = "an about page";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this about page";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "theabout page";
  public static final String SingularName_enUS = "about page";
  public static final String PluralName_enUS = "about";
  public static final String NameActual_enUS = "current about page";
  public static final String AllName_enUS = "all about";
  public static final String SearchAllNameBy_enUS = "search about by ";
  public static final String SearchAllName_enUS = "search about";
  public static final String Title_enUS = "about";
  public static final String ThePluralName_enUS = "the about";
  public static final String NoNameFound_enUS = "no about page found";
  public static final String ApiUri_enUS = "/en-us/api/about";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/about";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/about/{pageId}";
  public static final String OfName_enUS = "of about page";
  public static final String ANameAdjective_enUS = "an about page";
  public static final String NameAdjectiveSingular_enUS = "about page";
  public static final String NameAdjectivePlural_enUS = "about";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/about";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/about";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/about";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/about/{pageId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/about/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/about/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/about";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/about";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/about";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/about";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/about";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/about";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/about/{pageId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/about/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/about/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/about-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/about-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/about-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/about";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/about";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/about";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/about/{pageId}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/about/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/about/%s";
  public static final String DisplayPage_enUS_OpenApiUri = "/en-us/learn/about/{pageId}";
  public static final String DisplayPage_enUS_StringFormatUri = "/en-us/learn/about/%s";
  public static final String DisplayPage_enUS_StringFormatUrl = "%s/en-us/learn/about/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/about";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/about";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/about";

  public static final String Icon = "<i class=\"fa-duotone fa-regular fa-address-card\"></i>";

	//////////
  // name //
	//////////


  /**   The entity name
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String name;

  /**  <br> The entity name
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAbout&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _name(Wrap<String> w);

  public String getName() {
    return name;
  }
  public void setName(String o) {
    this.name = CompanyAbout.staticSetName(siteRequest_, o);
  }
  public static String staticSetName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected CompanyAbout nameInit() {
    Wrap<String> nameWrap = new Wrap<String>().var("name");
    if(name == null) {
      _name(nameWrap);
      Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
        setName(o);
      });
    }
    return (CompanyAbout)this;
  }

  public static String staticSearchName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
    return CompanyAbout.staticSearchName(siteRequest_, CompanyAbout.staticSetName(siteRequest_, o)).toString();
  }

  public String sqlName() {
    return name;
  }

	/////////////////
  // description //
	/////////////////


  /**   The entity description
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String description;

  /**  <br> The entity description
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAbout&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _description(Wrap<String> w);

  public String getDescription() {
    return description;
  }
  public void setDescription(String o) {
    this.description = CompanyAbout.staticSetDescription(siteRequest_, o);
  }
  public static String staticSetDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected CompanyAbout descriptionInit() {
    Wrap<String> descriptionWrap = new Wrap<String>().var("description");
    if(description == null) {
      _description(descriptionWrap);
      Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
        setDescription(o);
      });
    }
    return (CompanyAbout)this;
  }

  public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
    return CompanyAbout.staticSearchDescription(siteRequest_, CompanyAbout.staticSetDescription(siteRequest_, o)).toString();
  }

  public String sqlDescription() {
    return description;
  }

	////////////
  // pageId //
	////////////


  /**   The entity pageId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageId;

  /**  <br> The entity pageId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAbout&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageId(Wrap<String> w);

  public String getPageId() {
    return pageId;
  }
  public void setPageId(String o) {
    this.pageId = CompanyAbout.staticSetPageId(siteRequest_, o);
  }
  public static String staticSetPageId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected CompanyAbout pageIdInit() {
    Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
    if(pageId == null) {
      _pageId(pageIdWrap);
      Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
        setPageId(o);
      });
    }
    return (CompanyAbout)this;
  }

  public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
    return CompanyAbout.staticSearchPageId(siteRequest_, CompanyAbout.staticSetPageId(siteRequest_, o)).toString();
  }

  public String sqlPageId() {
    return pageId;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<CompanyAboutGen<DEV>> promiseDeepCompanyAbout(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepCompanyAbout();
  }

  public Future<CompanyAboutGen<DEV>> promiseDeepCompanyAbout() {
    Promise<CompanyAboutGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseCompanyAbout(promise2);
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

  public Future<Void> promiseCompanyAbout(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        nameInit();
        descriptionInit();
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

  @Override public Future<? extends CompanyAboutGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepCompanyAbout(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestCompanyAbout(SiteRequest siteRequest_) {
      super.siteRequestBaseResult(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestCompanyAbout(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainCompanyAbout(v);
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
  public Object obtainCompanyAbout(String var) {
    CompanyAbout oCompanyAbout = (CompanyAbout)this;
    switch(var) {
      case "name":
        return oCompanyAbout.name;
      case "description":
        return oCompanyAbout.description;
      case "pageId":
        return oCompanyAbout.pageId;
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
        o = relateCompanyAbout(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateCompanyAbout(String var, Object val) {
    CompanyAbout oCompanyAbout = (CompanyAbout)this;
    switch(var) {
      default:
        return super.relateBaseResult(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, CompanyAbout o) {
    return staticSetCompanyAbout(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetCompanyAbout(String entityVar, SiteRequest siteRequest_, String v, CompanyAbout o) {
    switch(entityVar) {
    case "name":
      return CompanyAbout.staticSetName(siteRequest_, v);
    case "description":
      return CompanyAbout.staticSetDescription(siteRequest_, v);
    case "pageId":
      return CompanyAbout.staticSetPageId(siteRequest_, v);
      default:
        return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchCompanyAbout(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchCompanyAbout(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "name":
      return CompanyAbout.staticSearchName(siteRequest_, (String)o);
    case "description":
      return CompanyAbout.staticSearchDescription(siteRequest_, (String)o);
    case "pageId":
      return CompanyAbout.staticSearchPageId(siteRequest_, (String)o);
      default:
        return BaseResult.staticSearchBaseResult(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrCompanyAbout(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrCompanyAbout(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "name":
      return CompanyAbout.staticSearchStrName(siteRequest_, (String)o);
    case "description":
      return CompanyAbout.staticSearchStrDescription(siteRequest_, (String)o);
    case "pageId":
      return CompanyAbout.staticSearchStrPageId(siteRequest_, (String)o);
      default:
        return BaseResult.staticSearchStrBaseResult(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqCompanyAbout(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqCompanyAbout(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "name":
      return CompanyAbout.staticSearchFqName(siteRequest_, o);
    case "description":
      return CompanyAbout.staticSearchFqDescription(siteRequest_, o);
    case "pageId":
      return CompanyAbout.staticSearchFqPageId(siteRequest_, o);
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
          o = persistCompanyAbout(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistCompanyAbout(String var, Object val) {
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
    } else {
      return super.persistBaseResult(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateCompanyAbout(doc);
  }
  public void populateCompanyAbout(SolrResponse.Doc doc) {
    CompanyAbout oCompanyAbout = (CompanyAbout)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("name")) {
        String name = (String)doc.get("name_docvalues_string");
        if(name != null)
          oCompanyAbout.setName(name);
      }

      if(saves.contains("description")) {
        String description = (String)doc.get("description_docvalues_string");
        if(description != null)
          oCompanyAbout.setDescription(description);
      }

      if(saves.contains("pageId")) {
        String pageId = (String)doc.get("pageId_docvalues_string");
        if(pageId != null)
          oCompanyAbout.setPageId(pageId);
      }
    }

    super.populateBaseResult(doc);
  }

  public void indexCompanyAbout(JsonObject doc) {
    if(name != null) {
      doc.put("name_docvalues_string", name);
    }
    if(description != null) {
      doc.put("description_docvalues_string", description);
    }
    if(pageId != null) {
      doc.put("pageId_docvalues_string", pageId);
    }
    super.indexBaseResult(doc);

	}

  public static String varStoredCompanyAbout(String entityVar) {
    switch(entityVar) {
      case "name":
        return "name_docvalues_string";
      case "description":
        return "description_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      default:
        return BaseResult.varStoredBaseResult(entityVar);
    }
  }

  public static String varIndexedCompanyAbout(String entityVar) {
    switch(entityVar) {
      case "name":
        return "name_docvalues_string";
      case "description":
        return "description_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      default:
        return BaseResult.varIndexedBaseResult(entityVar);
    }
  }

  public static String searchVarCompanyAbout(String searchVar) {
    switch(searchVar) {
      case "name_docvalues_string":
        return "name";
      case "description_docvalues_string":
        return "description";
      case "pageId_docvalues_string":
        return "pageId";
      default:
        return BaseResult.searchVarBaseResult(searchVar);
    }
  }

  public static String varSearchCompanyAbout(String entityVar) {
    switch(entityVar) {
      default:
        return BaseResult.varSearchBaseResult(entityVar);
    }
  }

  public static String varSuggestedCompanyAbout(String entityVar) {
    switch(entityVar) {
      default:
        return BaseResult.varSuggestedBaseResult(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeCompanyAbout(doc);
  }
  public void storeCompanyAbout(SolrResponse.Doc doc) {
    CompanyAbout oCompanyAbout = (CompanyAbout)this;
    SiteRequest siteRequest = oCompanyAbout.getSiteRequest_();

    oCompanyAbout.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
    oCompanyAbout.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
    oCompanyAbout.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));

    super.storeBaseResult(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestCompanyAbout() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof CompanyAbout) {
      CompanyAbout original = (CompanyAbout)o;
      if(!Objects.equals(name, original.getName()))
        apiRequest.addVars("name");
      if(!Objects.equals(description, original.getDescription()))
        apiRequest.addVars("description");
      if(!Objects.equals(pageId, original.getPageId()))
        apiRequest.addVars("pageId");
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
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "CompanyAbout";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.about.CompanyAbout";
  public static final String CLASS_AUTH_RESOURCE = "COMPANYABOUT";
  public static final String CLASS_API_ADDRESS_CompanyAbout = "computate.org-enUS-CompanyAbout";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_CompanyAbout;
  }
  public static final String VAR_name = "name";
  public static final String VAR_description = "description";
  public static final String VAR_pageId = "pageId";

  public static List<String> varsQForClass() {
    return CompanyAbout.varsQCompanyAbout(new ArrayList<String>());
  }
  public static List<String> varsQCompanyAbout(List<String> vars) {
    BaseResult.varsQBaseResult(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return CompanyAbout.varsFqCompanyAbout(new ArrayList<String>());
  }
  public static List<String> varsFqCompanyAbout(List<String> vars) {
    vars.add(VAR_name);
    vars.add(VAR_description);
    vars.add(VAR_pageId);
    BaseResult.varsFqBaseResult(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return CompanyAbout.varsRangeCompanyAbout(new ArrayList<String>());
  }
  public static List<String> varsRangeCompanyAbout(List<String> vars) {
    BaseResult.varsRangeBaseResult(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_name = "about name";
  public static final String DISPLAY_NAME_description = "about description";
  public static final String DISPLAY_NAME_pageId = "Page ID";

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
    return CompanyAbout.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return description;
  }

  @Override
  public String classStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/about/%s";
  }

  @Override
  public String classStringFormatUrlDisplayPageForClass() {
    return "%s/en-us/learn/about/%s";
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
    return CompanyAbout.displayNameCompanyAbout(var);
  }
  public static String displayNameCompanyAbout(String var) {
    switch(var) {
    case VAR_name:
      return DISPLAY_NAME_name;
    case VAR_description:
      return DISPLAY_NAME_description;
    case VAR_pageId:
      return DISPLAY_NAME_pageId;
    default:
      return BaseResult.displayNameBaseResult(var);
    }
  }

  public static String descriptionCompanyAbout(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_name:
      return "The about name. ";
    case VAR_description:
      return "The about description. ";
    case VAR_pageId:
      return "The ID for this page. ";
      default:
        return BaseResult.descriptionBaseResult(var);
    }
  }

  public static String classSimpleNameCompanyAbout(String var) {
    switch(var) {
    case VAR_name:
      return "String";
    case VAR_description:
      return "String";
    case VAR_pageId:
      return "String";
      default:
        return BaseResult.classSimpleNameBaseResult(var);
    }
  }

  public static Integer htmColumnCompanyAbout(String var) {
    switch(var) {
    case VAR_name:
      return 1;
    case VAR_description:
      return 2;
      default:
        return BaseResult.htmColumnBaseResult(var);
    }
  }

  public static Integer htmRowCompanyAbout(String var) {
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

  public static Integer htmCellCompanyAbout(String var) {
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

  public static Integer lengthMinCompanyAbout(String var) {
    switch(var) {
      default:
        return BaseResult.lengthMinBaseResult(var);
    }
  }

  public static Integer lengthMaxCompanyAbout(String var) {
    switch(var) {
      default:
        return BaseResult.lengthMaxBaseResult(var);
    }
  }

  public static Integer maxCompanyAbout(String var) {
    switch(var) {
      default:
        return BaseResult.maxBaseResult(var);
    }
  }

  public static Integer minCompanyAbout(String var) {
    switch(var) {
      default:
        return BaseResult.minBaseResult(var);
    }
  }
}
