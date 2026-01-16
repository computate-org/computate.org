package org.computate.site.model.usecase;

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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class UseCaseGen into the class UseCase. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the UseCase API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "Model: true" if you wish to persist these UseCase objects in a relational PostgreSQL database transactionally in the RESTful API. 
 * The code to persist and query the UseCaseGen data in the database will then be automatically generated. 
 * </li>
 * <h3>About the UseCase class and it's generated class UseCaseGen&lt;BaseResult&gt;: </h3>extends UseCaseGen
 * <p>
 * This Java class extends a generated Java class UseCaseGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCase">Find the class UseCase in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends UseCaseGen<BaseResult>
 * <p>This <code>class UseCase extends UseCaseGen&lt;BaseResult&gt;</code>, which means it extends a newly generated UseCaseGen. 
 * The generated <code>class UseCaseGen extends BaseResult</code> which means that UseCase extends UseCaseGen which extends BaseResult. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: use cases"</b>, which groups all of the OpenAPIs for UseCase objects under the tag "use cases". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/use-case</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/use-case"</b>, which defines the base API URI for UseCase objects as "/en-us/api/use-case" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the UseCase class will inherit the helpful inherited class comments from the super class UseCaseGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 3</h2>
 * <p>This class contains a comment <b>"Order: 3"</b>, which means this class will be sorted by the given number 3 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 3</h2>
 * <p>This class contains a comment <b>"SqlOrder: 3"</b>, which means this class will be sorted by the given number 3 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.usecase.UseCasePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.site.model.usecase.UseCasePage extends org.computate.site.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the UseCase Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a use case</h2>
 * <p>This class contains a comment <b>"AName.enUS: a use case"</b>, which identifies the language context to describe a UseCase as "a use case". 
 * </p>
 * <p>
 * Delete the class UseCase in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCase&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.usecase in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.usecase&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class UseCaseGen<DEV> extends BaseResult {
  protected static final Logger LOG = LoggerFactory.getLogger(UseCase.class);

  public static final String Description_enUS = "See computate use cases for real organizations. ";
  public static final String AName_enUS = "a use case";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this use case";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "theuse case";
  public static final String SingularName_enUS = "use case";
  public static final String PluralName_enUS = "use cases";
  public static final String NameActual_enUS = "current use case";
  public static final String AllName_enUS = "all use cases";
  public static final String SearchAllNameBy_enUS = "search use cases by ";
  public static final String SearchAllName_enUS = "search use cases";
  public static final String Title_enUS = "use cases";
  public static final String ThePluralName_enUS = "the use cases";
  public static final String NoNameFound_enUS = "no use case found";
  public static final String ApiUri_enUS = "/en-us/api/use-case";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/use-case";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/use-case/{pageId}";
  public static final String OfName_enUS = "of use case";
  public static final String ANameAdjective_enUS = "an use case";
  public static final String NameAdjectiveSingular_enUS = "use case";
  public static final String NameAdjectivePlural_enUS = "use cases";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/use-case";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/use-case";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/use-case";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/use-case/{pageId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/use-case/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/use-case/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/use-case";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/use-case";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/use-case";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/use-case";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/use-case";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/use-case";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/use-case/{pageId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/use-case/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/use-case/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/use-case-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/use-case-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/use-case-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/use-case";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/use-case";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/use-case";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/use-case/{pageId}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/use-case/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/use-case/%s";
  public static final String DisplayPage_enUS_OpenApiUri = "/en-us/shop/use-case/{pageId}";
  public static final String DisplayPage_enUS_StringFormatUri = "/en-us/shop/use-case/%s";
  public static final String DisplayPage_enUS_StringFormatUrl = "%s/en-us/shop/use-case/%s";
  public static final String UserPage_enUS_OpenApiUri = "/en-us/use/use-case/{pageId}";
  public static final String UserPage_enUS_StringFormatUri = "/en-us/use/use-case/%s";
  public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/use/use-case/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/use-case";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/use-case";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/use-case";

  public static final String Icon = "<i class=\"fa-duotone fa-regular fa-file-invoice\"></i>";

	//////////
  // name //
	//////////


  /**
   *  The entity name
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String name;

  /**
   * <br> The entity name
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCase&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _name(Wrap<String> w);

  public String getName() {
    return name;
  }
  public void setName(String o) {
    this.name = UseCase.staticSetName(siteRequest_, o);
  }
  public static String staticSetName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected UseCase nameInit() {
    Wrap<String> nameWrap = new Wrap<String>().var("name");
    if(name == null) {
      _name(nameWrap);
      Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
        setName(o);
      });
    }
    return (UseCase)this;
  }

  public static String staticSearchName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
    return UseCase.staticSearchName(siteRequest_, UseCase.staticSetName(siteRequest_, o)).toString();
  }

  public String sqlName() {
    return name;
  }

  public static String staticJsonName(String name) {
    return name;
  }

	////////////////
  // authorName //
	////////////////


  /**
   *  The entity authorName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String authorName;

  /**
   * <br> The entity authorName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCase&fq=entiteVar_enUS_indexed_string:authorName">Find the entity authorName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _authorName(Wrap<String> w);

  public String getAuthorName() {
    return authorName;
  }
  public void setAuthorName(String o) {
    this.authorName = UseCase.staticSetAuthorName(siteRequest_, o);
  }
  public static String staticSetAuthorName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected UseCase authorNameInit() {
    Wrap<String> authorNameWrap = new Wrap<String>().var("authorName");
    if(authorName == null) {
      _authorName(authorNameWrap);
      Optional.ofNullable(authorNameWrap.getO()).ifPresent(o -> {
        setAuthorName(o);
      });
    }
    return (UseCase)this;
  }

  public static String staticSearchAuthorName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAuthorName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAuthorName(SiteRequest siteRequest_, String o) {
    return UseCase.staticSearchAuthorName(siteRequest_, UseCase.staticSetAuthorName(siteRequest_, o)).toString();
  }

  public String sqlAuthorName() {
    return authorName;
  }

  public static String staticJsonAuthorName(String authorName) {
    return authorName;
  }

	/////////////////
  // description //
	/////////////////


  /**
   *  The entity description
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String description;

  /**
   * <br> The entity description
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCase&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _description(Wrap<String> w);

  public String getDescription() {
    return description;
  }
  public void setDescription(String o) {
    this.description = UseCase.staticSetDescription(siteRequest_, o);
  }
  public static String staticSetDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected UseCase descriptionInit() {
    Wrap<String> descriptionWrap = new Wrap<String>().var("description");
    if(description == null) {
      _description(descriptionWrap);
      Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
        setDescription(o);
      });
    }
    return (UseCase)this;
  }

  public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
    return UseCase.staticSearchDescription(siteRequest_, UseCase.staticSetDescription(siteRequest_, o)).toString();
  }

  public String sqlDescription() {
    return description;
  }

  public static String staticJsonDescription(String description) {
    return description;
  }

	////////////
  // pageId //
	////////////


  /**
   *  The entity pageId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageId;

  /**
   * <br> The entity pageId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCase&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageId(Wrap<String> w);

  public String getPageId() {
    return pageId;
  }
  public void setPageId(String o) {
    this.pageId = UseCase.staticSetPageId(siteRequest_, o);
  }
  public static String staticSetPageId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected UseCase pageIdInit() {
    Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
    if(pageId == null) {
      _pageId(pageIdWrap);
      Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
        setPageId(o);
      });
    }
    return (UseCase)this;
  }

  public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
    return UseCase.staticSearchPageId(siteRequest_, UseCase.staticSetPageId(siteRequest_, o)).toString();
  }

  public String sqlPageId() {
    return pageId;
  }

  public static String staticJsonPageId(String pageId) {
    return pageId;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<UseCaseGen<DEV>> promiseDeepUseCase(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepUseCase();
  }

  public Future<UseCaseGen<DEV>> promiseDeepUseCase() {
    Promise<UseCaseGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseUseCase(promise2);
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

  public Future<Void> promiseUseCase(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        nameInit();
        authorNameInit();
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

  @Override public Future<? extends UseCaseGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepUseCase(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestUseCase(SiteRequest siteRequest_) {
      super.siteRequestBaseResult(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestUseCase(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainUseCase(v);
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
  public Object obtainUseCase(String var) {
    UseCase oUseCase = (UseCase)this;
    switch(var) {
      case "name":
        return oUseCase.name;
      case "authorName":
        return oUseCase.authorName;
      case "description":
        return oUseCase.description;
      case "pageId":
        return oUseCase.pageId;
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
        o = relateUseCase(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateUseCase(String var, Object val) {
    UseCase oUseCase = (UseCase)this;
    switch(var) {
      default:
        return super.relateBaseResult(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, UseCase o) {
    return staticSetUseCase(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetUseCase(String entityVar, SiteRequest siteRequest_, String v, UseCase o) {
    switch(entityVar) {
    case "name":
      return UseCase.staticSetName(siteRequest_, v);
    case "authorName":
      return UseCase.staticSetAuthorName(siteRequest_, v);
    case "description":
      return UseCase.staticSetDescription(siteRequest_, v);
    case "pageId":
      return UseCase.staticSetPageId(siteRequest_, v);
      default:
        return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchUseCase(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchUseCase(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "name":
      return UseCase.staticSearchName(siteRequest_, (String)o);
    case "authorName":
      return UseCase.staticSearchAuthorName(siteRequest_, (String)o);
    case "description":
      return UseCase.staticSearchDescription(siteRequest_, (String)o);
    case "pageId":
      return UseCase.staticSearchPageId(siteRequest_, (String)o);
      default:
        return BaseResult.staticSearchBaseResult(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrUseCase(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrUseCase(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "name":
      return UseCase.staticSearchStrName(siteRequest_, (String)o);
    case "authorName":
      return UseCase.staticSearchStrAuthorName(siteRequest_, (String)o);
    case "description":
      return UseCase.staticSearchStrDescription(siteRequest_, (String)o);
    case "pageId":
      return UseCase.staticSearchStrPageId(siteRequest_, (String)o);
      default:
        return BaseResult.staticSearchStrBaseResult(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqUseCase(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqUseCase(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "name":
      return UseCase.staticSearchFqName(siteRequest_, o);
    case "authorName":
      return UseCase.staticSearchFqAuthorName(siteRequest_, o);
    case "description":
      return UseCase.staticSearchFqDescription(siteRequest_, o);
    case "pageId":
      return UseCase.staticSearchFqPageId(siteRequest_, o);
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
          o = persistUseCase(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistUseCase(String var, Object val) {
    String varLower = var.toLowerCase();
      if("name".equals(varLower)) {
        if(val instanceof String) {
          setName((String)val);
        }
        saves.add("name");
        return val;
      } else if("authorname".equals(varLower)) {
        if(val instanceof String) {
          setAuthorName((String)val);
        }
        saves.add("authorName");
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
    populateUseCase(doc);
  }
  public void populateUseCase(SolrResponse.Doc doc) {
    UseCase oUseCase = (UseCase)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("name")) {
        String name = (String)doc.get("name_docvalues_string");
        if(name != null)
          oUseCase.setName(name);
      }

      if(saves.contains("authorName")) {
        String authorName = (String)doc.get("authorName_docvalues_string");
        if(authorName != null)
          oUseCase.setAuthorName(authorName);
      }

      if(saves.contains("description")) {
        String description = (String)doc.get("description_docvalues_string");
        if(description != null)
          oUseCase.setDescription(description);
      }

      if(saves.contains("pageId")) {
        String pageId = (String)doc.get("pageId_docvalues_string");
        if(pageId != null)
          oUseCase.setPageId(pageId);
      }
    }

    super.populateBaseResult(doc);
  }

  public void indexUseCase(JsonObject doc) {
    if(name != null) {
      doc.put("name_docvalues_string", name);
    }
    if(authorName != null) {
      doc.put("authorName_docvalues_string", authorName);
    }
    if(description != null) {
      doc.put("description_docvalues_string", description);
    }
    if(pageId != null) {
      doc.put("pageId_docvalues_string", pageId);
    }
    super.indexBaseResult(doc);

	}

  public static String varStoredUseCase(String entityVar) {
    switch(entityVar) {
      case "name":
        return "name_docvalues_string";
      case "authorName":
        return "authorName_docvalues_string";
      case "description":
        return "description_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      default:
        return BaseResult.varStoredBaseResult(entityVar);
    }
  }

  public static String varIndexedUseCase(String entityVar) {
    switch(entityVar) {
      case "name":
        return "name_docvalues_string";
      case "authorName":
        return "authorName_docvalues_string";
      case "description":
        return "description_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      default:
        return BaseResult.varIndexedBaseResult(entityVar);
    }
  }

  public static String searchVarUseCase(String searchVar) {
    switch(searchVar) {
      case "name_docvalues_string":
        return "name";
      case "authorName_docvalues_string":
        return "authorName";
      case "description_docvalues_string":
        return "description";
      case "pageId_docvalues_string":
        return "pageId";
      default:
        return BaseResult.searchVarBaseResult(searchVar);
    }
  }

  public static String varSearchUseCase(String entityVar) {
    switch(entityVar) {
      default:
        return BaseResult.varSearchBaseResult(entityVar);
    }
  }

  public static String varSuggestedUseCase(String entityVar) {
    switch(entityVar) {
      default:
        return BaseResult.varSuggestedBaseResult(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeUseCase(doc);
  }
  public void storeUseCase(SolrResponse.Doc doc) {
    UseCase oUseCase = (UseCase)this;
    SiteRequest siteRequest = oUseCase.getSiteRequest_();

    oUseCase.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
    oUseCase.setAuthorName(Optional.ofNullable(doc.get("authorName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oUseCase.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
    oUseCase.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));

    super.storeBaseResult(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestUseCase() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof UseCase) {
      UseCase original = (UseCase)o;
      if(!Objects.equals(name, original.getName()))
        apiRequest.addVars("name");
      if(!Objects.equals(authorName, original.getAuthorName()))
        apiRequest.addVars("authorName");
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
    sb.append(Optional.ofNullable(authorName).map(v -> "authorName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "UseCase";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.usecase.UseCase";
  public static final String CLASS_AUTH_RESOURCE = "USECASE";
  public static final String CLASS_API_ADDRESS_UseCase = "computate.org-enUS-UseCase";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_UseCase;
  }
  public static final String VAR_name = "name";
  public static final String VAR_authorName = "authorName";
  public static final String VAR_description = "description";
  public static final String VAR_pageId = "pageId";

  public static List<String> varsQForClass() {
    return UseCase.varsQUseCase(new ArrayList<String>());
  }
  public static List<String> varsQUseCase(List<String> vars) {
    BaseResult.varsQBaseResult(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return UseCase.varsFqUseCase(new ArrayList<String>());
  }
  public static List<String> varsFqUseCase(List<String> vars) {
    vars.add(VAR_name);
    vars.add(VAR_authorName);
    vars.add(VAR_description);
    vars.add(VAR_pageId);
    BaseResult.varsFqBaseResult(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return UseCase.varsRangeUseCase(new ArrayList<String>());
  }
  public static List<String> varsRangeUseCase(List<String> vars) {
    BaseResult.varsRangeBaseResult(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_name = "use case name";
  public static final String DISPLAY_NAME_authorName = "author name";
  public static final String DISPLAY_NAME_description = "use case description";
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
    return UseCase.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return description;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/use-case/%s";
  }

  @Override
  public String enUSStringFormatUrlDisplayPageForClass() {
    return "%s/en-us/shop/use-case/%s";
  }

  @Override
  public String enUSStringFormatUrlUserPageForClass() {
    return "%s/en-us/use/use-case/%s";
  }

  @Override
  public String enUSStringFormatUrlDownloadForClass() {
    return null;
  }

  public static String displayNameForClass(String var) {
    return UseCase.displayNameUseCase(var);
  }
  public static String displayNameUseCase(String var) {
    switch(var) {
    case VAR_name:
      return DISPLAY_NAME_name;
    case VAR_authorName:
      return DISPLAY_NAME_authorName;
    case VAR_description:
      return DISPLAY_NAME_description;
    case VAR_pageId:
      return DISPLAY_NAME_pageId;
    default:
      return BaseResult.displayNameBaseResult(var);
    }
  }

  public static String descriptionUseCase(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_name:
      return "The use case name. ";
    case VAR_authorName:
      return "The author name. ";
    case VAR_description:
      return "The use case description. ";
    case VAR_pageId:
      return "The ID for this page. ";
      default:
        return BaseResult.descriptionBaseResult(var);
    }
  }

  public static String classSimpleNameUseCase(String var) {
    switch(var) {
    case VAR_name:
      return "String";
    case VAR_authorName:
      return "String";
    case VAR_description:
      return "String";
    case VAR_pageId:
      return "String";
      default:
        return BaseResult.classSimpleNameBaseResult(var);
    }
  }

  public static Integer htmColumnUseCase(String var) {
    switch(var) {
    case VAR_name:
      return 1;
    case VAR_authorName:
      return 2;
    case VAR_description:
      return 3;
      default:
        return BaseResult.htmColumnBaseResult(var);
    }
  }

  public static Integer htmRowUseCase(String var) {
    switch(var) {
    case VAR_name:
      return 3;
    case VAR_authorName:
      return 3;
    case VAR_description:
      return 3;
    case VAR_pageId:
      return 99;
      default:
        return BaseResult.htmRowBaseResult(var);
    }
  }

  public static Integer htmCellUseCase(String var) {
    switch(var) {
    case VAR_name:
      return 1;
    case VAR_authorName:
      return 2;
    case VAR_description:
      return 3;
    case VAR_pageId:
      return 1;
      default:
        return BaseResult.htmCellBaseResult(var);
    }
  }

  public static Integer lengthMinUseCase(String var) {
    switch(var) {
      default:
        return BaseResult.lengthMinBaseResult(var);
    }
  }

  public static Integer lengthMaxUseCase(String var) {
    switch(var) {
      default:
        return BaseResult.lengthMaxBaseResult(var);
    }
  }

  public static Integer maxUseCase(String var) {
    switch(var) {
      default:
        return BaseResult.maxBaseResult(var);
    }
  }

  public static Integer minUseCase(String var) {
    switch(var) {
      default:
        return BaseResult.minBaseResult(var);
    }
  }
}
