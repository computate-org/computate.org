package org.computate.site.model.developer.smartagriculture;

import org.computate.site.request.SiteRequest;
import org.computate.site.page.PageLayout;
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
import com.fasterxml.jackson.databind.JsonDeserializer;
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
import org.computate.vertx.search.list.SearchList;
import org.computate.site.model.developer.smartagriculture.SmartAgricultureDeveloper;
import java.lang.String;
import org.computate.search.response.solr.SolrResponse.Stats;
import org.computate.search.response.solr.SolrResponse.FacetCounts;
import io.vertx.core.json.JsonObject;
import org.computate.vertx.serialize.vertx.JsonObjectDeserializer;
import java.lang.Integer;
import java.time.ZoneId;
import java.util.Locale;
import java.lang.Long;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.math.BigDecimal;
import io.vertx.core.json.JsonArray;
import org.computate.vertx.serialize.vertx.JsonArrayDeserializer;
import java.lang.Void;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these SmartAgricultureDeveloperGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SmartAgricultureDeveloperGenPageGen into the class SmartAgricultureDeveloperGenPage. 
 * </li>
 * <h3>About the SmartAgricultureDeveloperGenPage class and it's generated class SmartAgricultureDeveloperGenPageGen&lt;PageLayout&gt;: </h3>extends SmartAgricultureDeveloperGenPageGen
 * <p>
 * This Java class extends a generated Java class SmartAgricultureDeveloperGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartagriculture.SmartAgricultureDeveloperGenPage">Find the class SmartAgricultureDeveloperGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SmartAgricultureDeveloperGenPageGen<PageLayout>
 * <p>This <code>class SmartAgricultureDeveloperGenPage extends SmartAgricultureDeveloperGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated SmartAgricultureDeveloperGenPageGen. 
 * The generated <code>class SmartAgricultureDeveloperGenPageGen extends PageLayout</code> which means that SmartAgricultureDeveloperGenPage extends SmartAgricultureDeveloperGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SmartAgricultureDeveloperGenPage class will inherit the helpful inherited class comments from the super class SmartAgricultureDeveloperGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the SmartAgricultureDeveloperGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: null</h2>
 * <p>
 * Delete the class SmartAgricultureDeveloperGenPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartagriculture.SmartAgricultureDeveloperGenPage&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.developer.smartagriculture in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.developer.smartagriculture&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class SmartAgricultureDeveloperGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(SmartAgricultureDeveloperGenPage.class);

	//////////////////////////////////////////
  // searchListSmartAgricultureDeveloper_ //
	//////////////////////////////////////////


  /**
   *  The entity searchListSmartAgricultureDeveloper_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<SmartAgricultureDeveloper> searchListSmartAgricultureDeveloper_;

  /**
   * <br> The entity searchListSmartAgricultureDeveloper_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartagriculture.SmartAgricultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:searchListSmartAgricultureDeveloper_">Find the entity searchListSmartAgricultureDeveloper_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListSmartAgricultureDeveloper_(Wrap<SearchList<SmartAgricultureDeveloper>> w);

  public SearchList<SmartAgricultureDeveloper> getSearchListSmartAgricultureDeveloper_() {
    return searchListSmartAgricultureDeveloper_;
  }

  public void setSearchListSmartAgricultureDeveloper_(SearchList<SmartAgricultureDeveloper> searchListSmartAgricultureDeveloper_) {
    this.searchListSmartAgricultureDeveloper_ = searchListSmartAgricultureDeveloper_;
  }
  public static SearchList<SmartAgricultureDeveloper> staticSetSearchListSmartAgricultureDeveloper_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected SmartAgricultureDeveloperGenPage searchListSmartAgricultureDeveloper_Init() {
    Wrap<SearchList<SmartAgricultureDeveloper>> searchListSmartAgricultureDeveloper_Wrap = new Wrap<SearchList<SmartAgricultureDeveloper>>().var("searchListSmartAgricultureDeveloper_");
    if(searchListSmartAgricultureDeveloper_ == null) {
      _searchListSmartAgricultureDeveloper_(searchListSmartAgricultureDeveloper_Wrap);
      Optional.ofNullable(searchListSmartAgricultureDeveloper_Wrap.getO()).ifPresent(o -> {
        setSearchListSmartAgricultureDeveloper_(o);
      });
    }
    return (SmartAgricultureDeveloperGenPage)this;
  }

	///////////////////////////////////
  // listSmartAgricultureDeveloper //
	///////////////////////////////////


  /**
   *  The entity listSmartAgricultureDeveloper
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listSmartAgricultureDeveloper = new JsonArray();

  /**
   * <br> The entity listSmartAgricultureDeveloper
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartagriculture.SmartAgricultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:listSmartAgricultureDeveloper">Find the entity listSmartAgricultureDeveloper in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listSmartAgricultureDeveloper(JsonArray l);

  public JsonArray getListSmartAgricultureDeveloper() {
    return listSmartAgricultureDeveloper;
  }

  public void setListSmartAgricultureDeveloper(JsonArray listSmartAgricultureDeveloper) {
    this.listSmartAgricultureDeveloper = listSmartAgricultureDeveloper;
  }
  @JsonIgnore
  public void setListSmartAgricultureDeveloper(String o) {
    this.listSmartAgricultureDeveloper = SmartAgricultureDeveloperGenPage.staticSetListSmartAgricultureDeveloper(siteRequest_, o);
  }
  public static JsonArray staticSetListSmartAgricultureDeveloper(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected SmartAgricultureDeveloperGenPage listSmartAgricultureDeveloperInit() {
    _listSmartAgricultureDeveloper(listSmartAgricultureDeveloper);
    return (SmartAgricultureDeveloperGenPage)this;
  }

  public static String staticSearchListSmartAgricultureDeveloper(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListSmartAgricultureDeveloper(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListSmartAgricultureDeveloper(SiteRequest siteRequest_, String o) {
    return SmartAgricultureDeveloperGenPage.staticSearchListSmartAgricultureDeveloper(siteRequest_, SmartAgricultureDeveloperGenPage.staticSetListSmartAgricultureDeveloper(siteRequest_, o)).toString();
  }

	/////////////////
  // resultCount //
	/////////////////


  /**
   *  The entity resultCount
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer resultCount;

  /**
   * <br> The entity resultCount
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartagriculture.SmartAgricultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _resultCount(Wrap<Integer> w);

  public Integer getResultCount() {
    return resultCount;
  }

  public void setResultCount(Integer resultCount) {
    this.resultCount = resultCount;
  }
  @JsonIgnore
  public void setResultCount(String o) {
    this.resultCount = SmartAgricultureDeveloperGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected SmartAgricultureDeveloperGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (SmartAgricultureDeveloperGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return SmartAgricultureDeveloperGenPage.staticSearchResultCount(siteRequest_, SmartAgricultureDeveloperGenPage.staticSetResultCount(siteRequest_, o)).toString();
  }

	////////////
  // result //
	////////////


  /**
   *  The entity result
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected SmartAgricultureDeveloper result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartagriculture.SmartAgricultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<SmartAgricultureDeveloper> w);

  public SmartAgricultureDeveloper getResult() {
    return result;
  }

  public void setResult(SmartAgricultureDeveloper result) {
    this.result = result;
  }
  public static SmartAgricultureDeveloper staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected SmartAgricultureDeveloperGenPage resultInit() {
    Wrap<SmartAgricultureDeveloper> resultWrap = new Wrap<SmartAgricultureDeveloper>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (SmartAgricultureDeveloperGenPage)this;
  }

	////////////
  // solrId //
	////////////


  /**
   *  The entity solrId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String solrId;

  /**
   * <br> The entity solrId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartagriculture.SmartAgricultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = SmartAgricultureDeveloperGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SmartAgricultureDeveloperGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (SmartAgricultureDeveloperGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return SmartAgricultureDeveloperGenPage.staticSearchSolrId(siteRequest_, SmartAgricultureDeveloperGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	//////////////////////////////////////
  // pageUriSmartAgricultureDeveloper //
	//////////////////////////////////////


  /**
   *  The entity pageUriSmartAgricultureDeveloper
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriSmartAgricultureDeveloper;

  /**
   * <br> The entity pageUriSmartAgricultureDeveloper
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartagriculture.SmartAgricultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:pageUriSmartAgricultureDeveloper">Find the entity pageUriSmartAgricultureDeveloper in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriSmartAgricultureDeveloper(Wrap<String> c);

  public String getPageUriSmartAgricultureDeveloper() {
    return pageUriSmartAgricultureDeveloper;
  }
  public void setPageUriSmartAgricultureDeveloper(String o) {
    this.pageUriSmartAgricultureDeveloper = SmartAgricultureDeveloperGenPage.staticSetPageUriSmartAgricultureDeveloper(siteRequest_, o);
  }
  public static String staticSetPageUriSmartAgricultureDeveloper(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SmartAgricultureDeveloperGenPage pageUriSmartAgricultureDeveloperInit() {
    Wrap<String> pageUriSmartAgricultureDeveloperWrap = new Wrap<String>().var("pageUriSmartAgricultureDeveloper");
    if(pageUriSmartAgricultureDeveloper == null) {
      _pageUriSmartAgricultureDeveloper(pageUriSmartAgricultureDeveloperWrap);
      Optional.ofNullable(pageUriSmartAgricultureDeveloperWrap.getO()).ifPresent(o -> {
        setPageUriSmartAgricultureDeveloper(o);
      });
    }
    return (SmartAgricultureDeveloperGenPage)this;
  }

  public static String staticSearchPageUriSmartAgricultureDeveloper(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriSmartAgricultureDeveloper(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriSmartAgricultureDeveloper(SiteRequest siteRequest_, String o) {
    return SmartAgricultureDeveloperGenPage.staticSearchPageUriSmartAgricultureDeveloper(siteRequest_, SmartAgricultureDeveloperGenPage.staticSetPageUriSmartAgricultureDeveloper(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<SmartAgricultureDeveloperGenPageGen<DEV>> promiseDeepSmartAgricultureDeveloperGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepSmartAgricultureDeveloperGenPage();
  }

  public Future<SmartAgricultureDeveloperGenPageGen<DEV>> promiseDeepSmartAgricultureDeveloperGenPage() {
    Promise<SmartAgricultureDeveloperGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseSmartAgricultureDeveloperGenPage(promise2);
    promise2.future().onSuccess(a -> {
      super.promiseDeepPageLayout(siteRequest_).onSuccess(b -> {
        promise.complete(this);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> promiseSmartAgricultureDeveloperGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListSmartAgricultureDeveloper_Init();
        listSmartAgricultureDeveloperInit();
        resultCountInit();
        resultInit();
        solrIdInit();
        pageUriSmartAgricultureDeveloperInit();
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

  @Override public Future<? extends SmartAgricultureDeveloperGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepSmartAgricultureDeveloperGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestSmartAgricultureDeveloperGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestSmartAgricultureDeveloperGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainSmartAgricultureDeveloperGenPage(v);
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
  public Object obtainSmartAgricultureDeveloperGenPage(String var) {
    SmartAgricultureDeveloperGenPage oSmartAgricultureDeveloperGenPage = (SmartAgricultureDeveloperGenPage)this;
    switch(var) {
      case "searchListSmartAgricultureDeveloper_":
        return oSmartAgricultureDeveloperGenPage.searchListSmartAgricultureDeveloper_;
      case "listSmartAgricultureDeveloper":
        return oSmartAgricultureDeveloperGenPage.listSmartAgricultureDeveloper;
      case "resultCount":
        return oSmartAgricultureDeveloperGenPage.resultCount;
      case "result":
        return oSmartAgricultureDeveloperGenPage.result;
      case "solrId":
        return oSmartAgricultureDeveloperGenPage.solrId;
      case "pageUriSmartAgricultureDeveloper":
        return oSmartAgricultureDeveloperGenPage.pageUriSmartAgricultureDeveloper;
      default:
        return super.obtainPageLayout(var);
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
        o = relateSmartAgricultureDeveloperGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateSmartAgricultureDeveloperGenPage(String var, Object val) {
    SmartAgricultureDeveloperGenPage oSmartAgricultureDeveloperGenPage = (SmartAgricultureDeveloperGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, SmartAgricultureDeveloperGenPage o) {
    return staticSetSmartAgricultureDeveloperGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetSmartAgricultureDeveloperGenPage(String entityVar, SiteRequest siteRequest_, String v, SmartAgricultureDeveloperGenPage o) {
    switch(entityVar) {
    case "listSmartAgricultureDeveloper":
      return SmartAgricultureDeveloperGenPage.staticSetListSmartAgricultureDeveloper(siteRequest_, v);
    case "resultCount":
      return SmartAgricultureDeveloperGenPage.staticSetResultCount(siteRequest_, v);
    case "solrId":
      return SmartAgricultureDeveloperGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriSmartAgricultureDeveloper":
      return SmartAgricultureDeveloperGenPage.staticSetPageUriSmartAgricultureDeveloper(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchSmartAgricultureDeveloperGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchSmartAgricultureDeveloperGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listSmartAgricultureDeveloper":
      return SmartAgricultureDeveloperGenPage.staticSearchListSmartAgricultureDeveloper(siteRequest_, (JsonArray)o);
    case "resultCount":
      return SmartAgricultureDeveloperGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return SmartAgricultureDeveloperGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriSmartAgricultureDeveloper":
      return SmartAgricultureDeveloperGenPage.staticSearchPageUriSmartAgricultureDeveloper(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrSmartAgricultureDeveloperGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrSmartAgricultureDeveloperGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listSmartAgricultureDeveloper":
      return SmartAgricultureDeveloperGenPage.staticSearchStrListSmartAgricultureDeveloper(siteRequest_, (String)o);
    case "resultCount":
      return SmartAgricultureDeveloperGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return SmartAgricultureDeveloperGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriSmartAgricultureDeveloper":
      return SmartAgricultureDeveloperGenPage.staticSearchStrPageUriSmartAgricultureDeveloper(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqSmartAgricultureDeveloperGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqSmartAgricultureDeveloperGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listSmartAgricultureDeveloper":
      return SmartAgricultureDeveloperGenPage.staticSearchFqListSmartAgricultureDeveloper(siteRequest_, o);
    case "resultCount":
      return SmartAgricultureDeveloperGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "solrId":
      return SmartAgricultureDeveloperGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriSmartAgricultureDeveloper":
      return SmartAgricultureDeveloperGenPage.staticSearchFqPageUriSmartAgricultureDeveloper(siteRequest_, o);
      default:
        return PageLayout.staticSearchFqPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "SmartAgricultureDeveloperGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.developer.smartagriculture.SmartAgricultureDeveloperGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListSmartAgricultureDeveloper_ = "searchListSmartAgricultureDeveloper_";
  public static final String SET_searchListSmartAgricultureDeveloper_ = "setSearchListSmartAgricultureDeveloper_";
  public static final String VAR_listSmartAgricultureDeveloper = "listSmartAgricultureDeveloper";
  public static final String SET_listSmartAgricultureDeveloper = "setListSmartAgricultureDeveloper";
  public static final String VAR_resultCount = "resultCount";
  public static final String SET_resultCount = "setResultCount";
  public static final String VAR_result = "result";
  public static final String SET_result = "setResult";
  public static final String VAR_solrId = "solrId";
  public static final String SET_solrId = "setSolrId";
  public static final String VAR_pageUriSmartAgricultureDeveloper = "pageUriSmartAgricultureDeveloper";
  public static final String SET_pageUriSmartAgricultureDeveloper = "setPageUriSmartAgricultureDeveloper";

  public static final String DISPLAY_NAME_searchListSmartAgricultureDeveloper_ = "";
  public static final String DISPLAY_NAME_listSmartAgricultureDeveloper = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriSmartAgricultureDeveloper = "";

  public static String displayNameForClass(String var) {
    return SmartAgricultureDeveloperGenPage.displayNameSmartAgricultureDeveloperGenPage(var);
  }
  public static String displayNameSmartAgricultureDeveloperGenPage(String var) {
    switch(var) {
    case VAR_searchListSmartAgricultureDeveloper_:
      return DISPLAY_NAME_searchListSmartAgricultureDeveloper_;
    case VAR_listSmartAgricultureDeveloper:
      return DISPLAY_NAME_listSmartAgricultureDeveloper;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriSmartAgricultureDeveloper:
      return DISPLAY_NAME_pageUriSmartAgricultureDeveloper;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }
}
