package org.computate.site.model.developer.smartaquaculture;

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
import org.computate.site.model.developer.smartaquaculture.SmartAquacultureDeveloper;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these SmartAquacultureDeveloperGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SmartAquacultureDeveloperGenPageGen into the class SmartAquacultureDeveloperGenPage. 
 * </li>
 * <h3>About the SmartAquacultureDeveloperGenPage class and it's generated class SmartAquacultureDeveloperGenPageGen&lt;PageLayout&gt;: </h3>extends SmartAquacultureDeveloperGenPageGen
 * <p>
 * This Java class extends a generated Java class SmartAquacultureDeveloperGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartaquaculture.SmartAquacultureDeveloperGenPage">Find the class SmartAquacultureDeveloperGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SmartAquacultureDeveloperGenPageGen<PageLayout>
 * <p>This <code>class SmartAquacultureDeveloperGenPage extends SmartAquacultureDeveloperGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated SmartAquacultureDeveloperGenPageGen. 
 * The generated <code>class SmartAquacultureDeveloperGenPageGen extends PageLayout</code> which means that SmartAquacultureDeveloperGenPage extends SmartAquacultureDeveloperGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SmartAquacultureDeveloperGenPage class will inherit the helpful inherited class comments from the super class SmartAquacultureDeveloperGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the SmartAquacultureDeveloperGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class SmartAquacultureDeveloperGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartaquaculture.SmartAquacultureDeveloperGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.developer.smartaquaculture in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.developer.smartaquaculture&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class SmartAquacultureDeveloperGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(SmartAquacultureDeveloperGenPage.class);

	//////////////////////////////////////////
  // searchListSmartAquacultureDeveloper_ //
	//////////////////////////////////////////


  /**
   *  The entity searchListSmartAquacultureDeveloper_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<SmartAquacultureDeveloper> searchListSmartAquacultureDeveloper_;

  /**
   * <br> The entity searchListSmartAquacultureDeveloper_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartaquaculture.SmartAquacultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:searchListSmartAquacultureDeveloper_">Find the entity searchListSmartAquacultureDeveloper_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListSmartAquacultureDeveloper_(Wrap<SearchList<SmartAquacultureDeveloper>> w);

  public SearchList<SmartAquacultureDeveloper> getSearchListSmartAquacultureDeveloper_() {
    return searchListSmartAquacultureDeveloper_;
  }

  public void setSearchListSmartAquacultureDeveloper_(SearchList<SmartAquacultureDeveloper> searchListSmartAquacultureDeveloper_) {
    this.searchListSmartAquacultureDeveloper_ = searchListSmartAquacultureDeveloper_;
  }
  public static SearchList<SmartAquacultureDeveloper> staticSetSearchListSmartAquacultureDeveloper_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected SmartAquacultureDeveloperGenPage searchListSmartAquacultureDeveloper_Init() {
    Wrap<SearchList<SmartAquacultureDeveloper>> searchListSmartAquacultureDeveloper_Wrap = new Wrap<SearchList<SmartAquacultureDeveloper>>().var("searchListSmartAquacultureDeveloper_");
    if(searchListSmartAquacultureDeveloper_ == null) {
      _searchListSmartAquacultureDeveloper_(searchListSmartAquacultureDeveloper_Wrap);
      Optional.ofNullable(searchListSmartAquacultureDeveloper_Wrap.getO()).ifPresent(o -> {
        setSearchListSmartAquacultureDeveloper_(o);
      });
    }
    return (SmartAquacultureDeveloperGenPage)this;
  }

	///////////////////////////////////
  // listSmartAquacultureDeveloper //
	///////////////////////////////////


  /**
   *  The entity listSmartAquacultureDeveloper
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listSmartAquacultureDeveloper = new JsonArray();

  /**
   * <br> The entity listSmartAquacultureDeveloper
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartaquaculture.SmartAquacultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:listSmartAquacultureDeveloper">Find the entity listSmartAquacultureDeveloper in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listSmartAquacultureDeveloper(JsonArray l);

  public JsonArray getListSmartAquacultureDeveloper() {
    return listSmartAquacultureDeveloper;
  }

  public void setListSmartAquacultureDeveloper(JsonArray listSmartAquacultureDeveloper) {
    this.listSmartAquacultureDeveloper = listSmartAquacultureDeveloper;
  }
  @JsonIgnore
  public void setListSmartAquacultureDeveloper(String o) {
    this.listSmartAquacultureDeveloper = SmartAquacultureDeveloperGenPage.staticSetListSmartAquacultureDeveloper(siteRequest_, o);
  }
  public static JsonArray staticSetListSmartAquacultureDeveloper(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected SmartAquacultureDeveloperGenPage listSmartAquacultureDeveloperInit() {
    _listSmartAquacultureDeveloper(listSmartAquacultureDeveloper);
    return (SmartAquacultureDeveloperGenPage)this;
  }

  public static String staticSearchListSmartAquacultureDeveloper(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListSmartAquacultureDeveloper(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListSmartAquacultureDeveloper(SiteRequest siteRequest_, String o) {
    return SmartAquacultureDeveloperGenPage.staticSearchListSmartAquacultureDeveloper(siteRequest_, SmartAquacultureDeveloperGenPage.staticSetListSmartAquacultureDeveloper(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartaquaculture.SmartAquacultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = SmartAquacultureDeveloperGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected SmartAquacultureDeveloperGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (SmartAquacultureDeveloperGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return SmartAquacultureDeveloperGenPage.staticSearchResultCount(siteRequest_, SmartAquacultureDeveloperGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected SmartAquacultureDeveloper result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartaquaculture.SmartAquacultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<SmartAquacultureDeveloper> w);

  public SmartAquacultureDeveloper getResult() {
    return result;
  }

  public void setResult(SmartAquacultureDeveloper result) {
    this.result = result;
  }
  public static SmartAquacultureDeveloper staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected SmartAquacultureDeveloperGenPage resultInit() {
    Wrap<SmartAquacultureDeveloper> resultWrap = new Wrap<SmartAquacultureDeveloper>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (SmartAquacultureDeveloperGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartaquaculture.SmartAquacultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = SmartAquacultureDeveloperGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SmartAquacultureDeveloperGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (SmartAquacultureDeveloperGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return SmartAquacultureDeveloperGenPage.staticSearchSolrId(siteRequest_, SmartAquacultureDeveloperGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	//////////////////////////////////////
  // pageUriSmartAquacultureDeveloper //
	//////////////////////////////////////


  /**
   *  The entity pageUriSmartAquacultureDeveloper
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriSmartAquacultureDeveloper;

  /**
   * <br> The entity pageUriSmartAquacultureDeveloper
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.smartaquaculture.SmartAquacultureDeveloperGenPage&fq=entiteVar_enUS_indexed_string:pageUriSmartAquacultureDeveloper">Find the entity pageUriSmartAquacultureDeveloper in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriSmartAquacultureDeveloper(Wrap<String> c);

  public String getPageUriSmartAquacultureDeveloper() {
    return pageUriSmartAquacultureDeveloper;
  }
  public void setPageUriSmartAquacultureDeveloper(String o) {
    this.pageUriSmartAquacultureDeveloper = SmartAquacultureDeveloperGenPage.staticSetPageUriSmartAquacultureDeveloper(siteRequest_, o);
  }
  public static String staticSetPageUriSmartAquacultureDeveloper(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SmartAquacultureDeveloperGenPage pageUriSmartAquacultureDeveloperInit() {
    Wrap<String> pageUriSmartAquacultureDeveloperWrap = new Wrap<String>().var("pageUriSmartAquacultureDeveloper");
    if(pageUriSmartAquacultureDeveloper == null) {
      _pageUriSmartAquacultureDeveloper(pageUriSmartAquacultureDeveloperWrap);
      Optional.ofNullable(pageUriSmartAquacultureDeveloperWrap.getO()).ifPresent(o -> {
        setPageUriSmartAquacultureDeveloper(o);
      });
    }
    return (SmartAquacultureDeveloperGenPage)this;
  }

  public static String staticSearchPageUriSmartAquacultureDeveloper(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriSmartAquacultureDeveloper(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriSmartAquacultureDeveloper(SiteRequest siteRequest_, String o) {
    return SmartAquacultureDeveloperGenPage.staticSearchPageUriSmartAquacultureDeveloper(siteRequest_, SmartAquacultureDeveloperGenPage.staticSetPageUriSmartAquacultureDeveloper(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<SmartAquacultureDeveloperGenPageGen<DEV>> promiseDeepSmartAquacultureDeveloperGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepSmartAquacultureDeveloperGenPage();
  }

  public Future<SmartAquacultureDeveloperGenPageGen<DEV>> promiseDeepSmartAquacultureDeveloperGenPage() {
    Promise<SmartAquacultureDeveloperGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseSmartAquacultureDeveloperGenPage(promise2);
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

  public Future<Void> promiseSmartAquacultureDeveloperGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListSmartAquacultureDeveloper_Init();
        listSmartAquacultureDeveloperInit();
        resultCountInit();
        resultInit();
        solrIdInit();
        pageUriSmartAquacultureDeveloperInit();
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

  @Override public Future<? extends SmartAquacultureDeveloperGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepSmartAquacultureDeveloperGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestSmartAquacultureDeveloperGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestSmartAquacultureDeveloperGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainSmartAquacultureDeveloperGenPage(v);
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
  public Object obtainSmartAquacultureDeveloperGenPage(String var) {
    SmartAquacultureDeveloperGenPage oSmartAquacultureDeveloperGenPage = (SmartAquacultureDeveloperGenPage)this;
    switch(var) {
      case "searchListSmartAquacultureDeveloper_":
        return oSmartAquacultureDeveloperGenPage.searchListSmartAquacultureDeveloper_;
      case "listSmartAquacultureDeveloper":
        return oSmartAquacultureDeveloperGenPage.listSmartAquacultureDeveloper;
      case "resultCount":
        return oSmartAquacultureDeveloperGenPage.resultCount;
      case "result":
        return oSmartAquacultureDeveloperGenPage.result;
      case "solrId":
        return oSmartAquacultureDeveloperGenPage.solrId;
      case "pageUriSmartAquacultureDeveloper":
        return oSmartAquacultureDeveloperGenPage.pageUriSmartAquacultureDeveloper;
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
        o = relateSmartAquacultureDeveloperGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateSmartAquacultureDeveloperGenPage(String var, Object val) {
    SmartAquacultureDeveloperGenPage oSmartAquacultureDeveloperGenPage = (SmartAquacultureDeveloperGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, SmartAquacultureDeveloperGenPage o) {
    return staticSetSmartAquacultureDeveloperGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetSmartAquacultureDeveloperGenPage(String entityVar, SiteRequest siteRequest_, String v, SmartAquacultureDeveloperGenPage o) {
    switch(entityVar) {
    case "listSmartAquacultureDeveloper":
      return SmartAquacultureDeveloperGenPage.staticSetListSmartAquacultureDeveloper(siteRequest_, v);
    case "resultCount":
      return SmartAquacultureDeveloperGenPage.staticSetResultCount(siteRequest_, v);
    case "solrId":
      return SmartAquacultureDeveloperGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriSmartAquacultureDeveloper":
      return SmartAquacultureDeveloperGenPage.staticSetPageUriSmartAquacultureDeveloper(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchSmartAquacultureDeveloperGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchSmartAquacultureDeveloperGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listSmartAquacultureDeveloper":
      return SmartAquacultureDeveloperGenPage.staticSearchListSmartAquacultureDeveloper(siteRequest_, (JsonArray)o);
    case "resultCount":
      return SmartAquacultureDeveloperGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return SmartAquacultureDeveloperGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriSmartAquacultureDeveloper":
      return SmartAquacultureDeveloperGenPage.staticSearchPageUriSmartAquacultureDeveloper(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrSmartAquacultureDeveloperGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrSmartAquacultureDeveloperGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listSmartAquacultureDeveloper":
      return SmartAquacultureDeveloperGenPage.staticSearchStrListSmartAquacultureDeveloper(siteRequest_, (String)o);
    case "resultCount":
      return SmartAquacultureDeveloperGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return SmartAquacultureDeveloperGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriSmartAquacultureDeveloper":
      return SmartAquacultureDeveloperGenPage.staticSearchStrPageUriSmartAquacultureDeveloper(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqSmartAquacultureDeveloperGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqSmartAquacultureDeveloperGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listSmartAquacultureDeveloper":
      return SmartAquacultureDeveloperGenPage.staticSearchFqListSmartAquacultureDeveloper(siteRequest_, o);
    case "resultCount":
      return SmartAquacultureDeveloperGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "solrId":
      return SmartAquacultureDeveloperGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriSmartAquacultureDeveloper":
      return SmartAquacultureDeveloperGenPage.staticSearchFqPageUriSmartAquacultureDeveloper(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "SmartAquacultureDeveloperGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.developer.smartaquaculture.SmartAquacultureDeveloperGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListSmartAquacultureDeveloper_ = "searchListSmartAquacultureDeveloper_";
  public static final String VAR_listSmartAquacultureDeveloper = "listSmartAquacultureDeveloper";
  public static final String VAR_resultCount = "resultCount";
  public static final String VAR_result = "result";
  public static final String VAR_solrId = "solrId";
  public static final String VAR_pageUriSmartAquacultureDeveloper = "pageUriSmartAquacultureDeveloper";

  public static final String DISPLAY_NAME_searchListSmartAquacultureDeveloper_ = "";
  public static final String DISPLAY_NAME_listSmartAquacultureDeveloper = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriSmartAquacultureDeveloper = "";

  @Override
  public String idForClass() {
    return null;
  }

  @Override
  public String titleForClass() {
    return null;
  }

  @Override
  public String nameForClass() {
    return null;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return null;
  }

  @Override
  public String descriptionForClass() {
    return null;
  }

  @Override
  public String classStringFormatUrlEditPageForClass() {
    return null;
  }

  @Override
  public String classStringFormatUrlDisplayPageForClass() {
    return null;
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
    return SmartAquacultureDeveloperGenPage.displayNameSmartAquacultureDeveloperGenPage(var);
  }
  public static String displayNameSmartAquacultureDeveloperGenPage(String var) {
    switch(var) {
    case VAR_searchListSmartAquacultureDeveloper_:
      return DISPLAY_NAME_searchListSmartAquacultureDeveloper_;
    case VAR_listSmartAquacultureDeveloper:
      return DISPLAY_NAME_listSmartAquacultureDeveloper;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriSmartAquacultureDeveloper:
      return DISPLAY_NAME_pageUriSmartAquacultureDeveloper;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }

  public static String descriptionSmartAquacultureDeveloperGenPage(String var) {
    if(var == null)
      return null;
    switch(var) {
      default:
        return PageLayout.descriptionPageLayout(var);
    }
  }

  public static String classSimpleNameSmartAquacultureDeveloperGenPage(String var) {
    switch(var) {
    case VAR_searchListSmartAquacultureDeveloper_:
      return "SearchList";
    case VAR_listSmartAquacultureDeveloper:
      return "JsonArray";
    case VAR_resultCount:
      return "Integer";
    case VAR_result:
      return "SmartAquacultureDeveloper";
    case VAR_solrId:
      return "String";
    case VAR_pageUriSmartAquacultureDeveloper:
      return "String";
      default:
        return PageLayout.classSimpleNamePageLayout(var);
    }
  }

  public static Integer htmColumnSmartAquacultureDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmColumnPageLayout(var);
    }
  }

  public static Integer htmRowSmartAquacultureDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmRowPageLayout(var);
    }
  }

  public static Integer htmCellSmartAquacultureDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmCellPageLayout(var);
    }
  }

  public static Integer lengthMinSmartAquacultureDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.lengthMinPageLayout(var);
    }
  }

  public static Integer lengthMaxSmartAquacultureDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.lengthMaxPageLayout(var);
    }
  }

  public static Integer maxSmartAquacultureDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.maxPageLayout(var);
    }
  }

  public static Integer minSmartAquacultureDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.minPageLayout(var);
    }
  }
}
