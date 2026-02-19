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
import java.math.BigDecimal;
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
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService">Find the class CompanyService in Solr. </a></p>
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
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: services"</b>, which groups all of the OpenAPIs for CompanyService objects under the tag "services". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/service</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/service"</b>, which defines the base API URI for CompanyService objects as "/en-us/api/service" in the OpenAPI spec. 
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
 * <h2>Order: 8</h2>
 * <p>This class contains a comment <b>"Order: 8"</b>, which means this class will be sorted by the given number 8 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 8</h2>
 * <p>This class contains a comment <b>"SqlOrder: 8"</b>, which means this class will be sorted by the given number 8 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.service.CompanyServicePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.site.model.service.CompanyServicePage extends org.computate.site.page.PageLayout. 
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
 * <h2>AName.enUS: a service</h2>
 * <p>This class contains a comment <b>"AName.enUS: a service"</b>, which identifies the language context to describe a CompanyService as "a service". 
 * </p>
 * <p>
 * Delete the class CompanyService in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.service in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.service&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * Generated: true
 **/
public abstract class CompanyServiceGen<DEV> extends BaseResult {
  protected static final Logger LOG = LoggerFactory.getLogger(CompanyService.class);

  public static final String Description_frFR = "See the services provided by computate that will help you build your own data-driven platforms";
  public static final String AName_frFR = "a service";
  public static final String SingularName_frFR = "service";
  public static final String PluralName_frFR = "services";
  public static final String Title_frFR = "services";
  public static final String ThePluralName_frFR = "les services";
  public static final String NameAdjectiveSingular_frFR = "service";
  public static final String NameAdjectivePlural_frFR = "services";

  public static final String Description_enUS = "See the services provided by computate that will help you build your own data-driven platforms";
  public static final String AName_enUS = "a service";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this service";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the service";
  public static final String SingularName_enUS = "service";
  public static final String PluralName_enUS = "services";
  public static final String NameActual_enUS = "current service";
  public static final String AllName_enUS = "all services";
  public static final String SearchAllNameBy_enUS = "search services by ";
  public static final String SearchAllName_enUS = "search services";
  public static final String Title_enUS = "services";
  public static final String ThePluralName_enUS = "the services";
  public static final String NoNameFound_enUS = "no service found";
  public static final String ApiUri_enUS = "/en-us/api/service";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/service";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/service/{pageId}";
  public static final String OfName_enUS = "of service";
  public static final String ANameAdjective_enUS = "a service";
  public static final String NameAdjectiveSingular_enUS = "service";
  public static final String NameAdjectivePlural_enUS = "services";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/service";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/service";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/service";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/service/{pageId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/service/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/service/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/service";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/service";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/service";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/service";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/service";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/service";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/service-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/service-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/service-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/service";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/service";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/service";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/service/{pageId}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/service/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/service/%s";
  public static final String DisplayPage_enUS_OpenApiUri = "/en-us/shop/service/{pageId}";
  public static final String DisplayPage_enUS_StringFormatUri = "/en-us/shop/service/%s";
  public static final String DisplayPage_enUS_StringFormatUrl = "%s/en-us/shop/service/%s";

  public static final String Icon = "<i class=\"fa-duotone fa-regular fa-conveyor-belt\"></i>";

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
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

  public static String staticJsonName(String name) {
    return name;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
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

  public static String staticJsonDescription(String description) {
    return description;
  }

	///////////
  // price //
	///////////


  /**
   *  The entity price
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected BigDecimal price;

  /**
   * <br> The entity price
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:price">Find the entity price in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _price(Wrap<BigDecimal> w);

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  @JsonIgnore
  public void setPrice(String o) {
    this.price = CompanyService.staticSetPrice(siteRequest_, o);
  }
  public static MathContext staticMathContextPrice() {
    return new MathContext(2, RoundingMode.valueOf("HALF_UP"));
  }
  public static BigDecimal staticSetPrice(SiteRequest siteRequest_, String o) {
    o = StringUtils.removeAll(o, "[^\\d\\.-]");
    if(NumberUtils.isParsable(o))
      return new BigDecimal(o, staticMathContextPrice());
    return null;
  }
  @JsonIgnore
  public void setPrice(Double o) {
    setPrice(new BigDecimal(o, staticMathContextPrice()));
  }
  @JsonIgnore
  public void setPrice(Integer o) {
    setPrice(new BigDecimal(o, staticMathContextPrice()));
  }
  @JsonIgnore
  public void setPrice(Number o) {
    setPrice(new BigDecimal(o.doubleValue(), staticMathContextPrice()));
  }
  protected CompanyService priceInit() {
    Wrap<BigDecimal> priceWrap = new Wrap<BigDecimal>().var("price");
    if(price == null) {
      _price(priceWrap);
      Optional.ofNullable(priceWrap.getO()).ifPresent(o -> {
        setPrice(o);
      });
    }
    return (CompanyService)this;
  }

  public static Double staticSearchPrice(SiteRequest siteRequest_, BigDecimal o) {
    return o == null ? null : o.doubleValue();
  }

  public static String staticSearchStrPrice(SiteRequest siteRequest_, Double o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPrice(SiteRequest siteRequest_, String o) {
    return CompanyService.staticSearchPrice(siteRequest_, CompanyService.staticSetPrice(siteRequest_, o)).toString();
  }

  public BigDecimal sqlPrice() {
    return price;
  }

  public static String staticJsonPrice(BigDecimal price) {
    return Optional.ofNullable(price).map(v -> v.toString()).orElse(null);
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyService&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
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

  public static String staticJsonPageId(String pageId) {
    return pageId;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<CompanyServiceGen<DEV>> promiseDeepCompanyService(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepCompanyService();
  }

  public Future<CompanyServiceGen<DEV>> promiseDeepCompanyService() {
    Promise<CompanyServiceGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseCompanyService(promise2);
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

  public Future<Void> promiseCompanyService(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        nameInit();
        descriptionInit();
        priceInit();
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

  @Override public Future<? extends CompanyServiceGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
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
      case "price":
        return oCompanyService.price;
      case "pageId":
        return oCompanyService.pageId;
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

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, CompanyService o) {
    return staticSetCompanyService(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetCompanyService(String entityVar, SiteRequest siteRequest_, String v, CompanyService o) {
    switch(entityVar) {
    case "name":
      return CompanyService.staticSetName(siteRequest_, v);
    case "description":
      return CompanyService.staticSetDescription(siteRequest_, v);
    case "price":
      return CompanyService.staticSetPrice(siteRequest_, v);
    case "pageId":
      return CompanyService.staticSetPageId(siteRequest_, v);
      default:
        return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, v, o);
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
    case "price":
      return CompanyService.staticSearchPrice(siteRequest_, (BigDecimal)o);
    case "pageId":
      return CompanyService.staticSearchPageId(siteRequest_, (String)o);
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
    case "price":
      return CompanyService.staticSearchStrPrice(siteRequest_, (Double)o);
    case "pageId":
      return CompanyService.staticSearchStrPageId(siteRequest_, (String)o);
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
    case "price":
      return CompanyService.staticSearchFqPrice(siteRequest_, o);
    case "pageId":
      return CompanyService.staticSearchFqPageId(siteRequest_, o);
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
      } else if("price".equals(varLower)) {
        if(val instanceof String) {
          setPrice((String)val);
        } else if(val instanceof Number) {
          setPrice(new BigDecimal(((Number)val).doubleValue()));
        } else if(val instanceof BigDecimal) {
          setPrice((BigDecimal)val);
        }
        saves.add("price");
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

      if(saves.contains("price")) {
        Double price = (Double)doc.get("price_docvalues_string");
        if(price != null)
          oCompanyService.setPrice(price);
      }

      if(saves.contains("pageId")) {
        String pageId = (String)doc.get("pageId_docvalues_string");
        if(pageId != null)
          oCompanyService.setPageId(pageId);
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
    if(price != null) {
      doc.put("price_docvalues_string", price.toPlainString());
    }
    if(pageId != null) {
      doc.put("pageId_docvalues_string", pageId);
    }
    super.indexBaseResult(doc);

	}

  public static String varStoredCompanyService(String entityVar) {
    switch(entityVar) {
      case "name":
        return "name_docvalues_string";
      case "description":
        return "description_docvalues_string";
      case "price":
        return "price_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
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
      case "price":
        return "price_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
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
      case "price_docvalues_string":
        return "price";
      case "pageId_docvalues_string":
        return "pageId";
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
    oCompanyService.setPrice(Optional.ofNullable(doc.get("price_docvalues_string")).map(v -> v.toString()).orElse(null));
    oCompanyService.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));

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
      if(!Objects.equals(price, original.getPrice()) && price != null && original.getPrice() != null && price.compareTo(original.getPrice()) != 0)
        apiRequest.addVars("price");
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
    sb.append(Optional.ofNullable(price).map(v -> "price: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "CompanyService";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.service.CompanyService";
  public static final String CLASS_AUTH_RESOURCE = "COMPANYSERVICE";
  public static final String CLASS_API_ADDRESS_CompanyService = "computate.org-enUS-CompanyService";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_CompanyService;
  }
  public static final String VAR_name = "name";
  public static final String VAR_description = "description";
  public static final String VAR_price = "price";
  public static final String VAR_pageId = "pageId";

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
    vars.add(VAR_price);
    vars.add(VAR_pageId);
    BaseResult.varsFqBaseResult(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return CompanyService.varsRangeCompanyService(new ArrayList<String>());
  }
  public static List<String> varsRangeCompanyService(List<String> vars) {
    vars.add(VAR_price);
    BaseResult.varsRangeBaseResult(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_name = "service name";
  public static final String DISPLAY_NAME_description = "service description";
  public static final String DISPLAY_NAME_price = "price";
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
    return CompanyService.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return description;
  }

  @Override
  public String frFRStringFormatUrlEditPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/service/%s";
  }

  @Override
  public String frFRStringFormatUrlDisplayPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlDisplayPageForClass() {
    return "%s/en-us/shop/service/%s";
  }

  @Override
  public String frFRStringFormatUrlUserPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlUserPageForClass() {
    return null;
  }

  @Override
  public String frFRStringFormatUrlDownloadForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlDownloadForClass() {
    return null;
  }

  public static String displayNameForClass(String var) {
    return CompanyService.displayNameCompanyService(var);
  }
  public static String displayNameCompanyService(String var) {
    switch(var) {
    case VAR_name:
      return DISPLAY_NAME_name;
    case VAR_description:
      return DISPLAY_NAME_description;
    case VAR_price:
      return DISPLAY_NAME_price;
    case VAR_pageId:
      return DISPLAY_NAME_pageId;
    default:
      return BaseResult.displayNameBaseResult(var);
    }
  }

  public static String descriptionCompanyService(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_name:
      return "The service name. ";
    case VAR_description:
      return "The service description. ";
    case VAR_price:
      return "The price of the product per developer. ";
    case VAR_pageId:
      return "The ID for this page. ";
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
    case VAR_price:
      return "BigDecimal";
    case VAR_pageId:
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
    case VAR_price:
      return 3;
    case VAR_pageId:
      return 99;
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
    case VAR_price:
      return 3;
    case VAR_pageId:
      return 1;
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
