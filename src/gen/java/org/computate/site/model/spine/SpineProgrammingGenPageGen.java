package org.computate.site.model.spine;

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
import org.computate.site.model.spine.SpineProgramming;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these SpineProgrammingGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SpineProgrammingGenPageGen into the class SpineProgrammingGenPage. 
 * </li>
 * <h3>About the SpineProgrammingGenPage class and it's generated class SpineProgrammingGenPageGen&lt;PageLayout&gt;: </h3>extends SpineProgrammingGenPageGen
 * <p>
 * This Java class extends a generated Java class SpineProgrammingGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spine.SpineProgrammingGenPage">Find the class SpineProgrammingGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SpineProgrammingGenPageGen<PageLayout>
 * <p>This <code>class SpineProgrammingGenPage extends SpineProgrammingGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated SpineProgrammingGenPageGen. 
 * The generated <code>class SpineProgrammingGenPageGen extends PageLayout</code> which means that SpineProgrammingGenPage extends SpineProgrammingGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SpineProgrammingGenPage class will inherit the helpful inherited class comments from the super class SpineProgrammingGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the SpineProgrammingGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class SpineProgrammingGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.spine.SpineProgrammingGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.spine in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.spine&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class SpineProgrammingGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(SpineProgrammingGenPage.class);

	/////////////////////////////////
  // searchListSpineProgramming_ //
	/////////////////////////////////


  /**
   *  The entity searchListSpineProgramming_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<SpineProgramming> searchListSpineProgramming_;

  /**
   * <br> The entity searchListSpineProgramming_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spine.SpineProgrammingGenPage&fq=entiteVar_enUS_indexed_string:searchListSpineProgramming_">Find the entity searchListSpineProgramming_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListSpineProgramming_(Wrap<SearchList<SpineProgramming>> w);

  public SearchList<SpineProgramming> getSearchListSpineProgramming_() {
    return searchListSpineProgramming_;
  }

  public void setSearchListSpineProgramming_(SearchList<SpineProgramming> searchListSpineProgramming_) {
    this.searchListSpineProgramming_ = searchListSpineProgramming_;
  }
  public static SearchList<SpineProgramming> staticSetSearchListSpineProgramming_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected SpineProgrammingGenPage searchListSpineProgramming_Init() {
    Wrap<SearchList<SpineProgramming>> searchListSpineProgramming_Wrap = new Wrap<SearchList<SpineProgramming>>().var("searchListSpineProgramming_");
    if(searchListSpineProgramming_ == null) {
      _searchListSpineProgramming_(searchListSpineProgramming_Wrap);
      Optional.ofNullable(searchListSpineProgramming_Wrap.getO()).ifPresent(o -> {
        setSearchListSpineProgramming_(o);
      });
    }
    return (SpineProgrammingGenPage)this;
  }

	//////////////////////////
  // listSpineProgramming //
	//////////////////////////


  /**
   *  The entity listSpineProgramming
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listSpineProgramming = new JsonArray();

  /**
   * <br> The entity listSpineProgramming
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spine.SpineProgrammingGenPage&fq=entiteVar_enUS_indexed_string:listSpineProgramming">Find the entity listSpineProgramming in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listSpineProgramming(JsonArray l);

  public JsonArray getListSpineProgramming() {
    return listSpineProgramming;
  }

  public void setListSpineProgramming(JsonArray listSpineProgramming) {
    this.listSpineProgramming = listSpineProgramming;
  }
  @JsonIgnore
  public void setListSpineProgramming(String o) {
    this.listSpineProgramming = SpineProgrammingGenPage.staticSetListSpineProgramming(siteRequest_, o);
  }
  public static JsonArray staticSetListSpineProgramming(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected SpineProgrammingGenPage listSpineProgrammingInit() {
    _listSpineProgramming(listSpineProgramming);
    return (SpineProgrammingGenPage)this;
  }

  public static String staticSearchListSpineProgramming(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListSpineProgramming(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListSpineProgramming(SiteRequest siteRequest_, String o) {
    return SpineProgrammingGenPage.staticSearchListSpineProgramming(siteRequest_, SpineProgrammingGenPage.staticSetListSpineProgramming(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spine.SpineProgrammingGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = SpineProgrammingGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected SpineProgrammingGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (SpineProgrammingGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return SpineProgrammingGenPage.staticSearchResultCount(siteRequest_, SpineProgrammingGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected SpineProgramming result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spine.SpineProgrammingGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<SpineProgramming> w);

  public SpineProgramming getResult() {
    return result;
  }

  public void setResult(SpineProgramming result) {
    this.result = result;
  }
  public static SpineProgramming staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected SpineProgrammingGenPage resultInit() {
    Wrap<SpineProgramming> resultWrap = new Wrap<SpineProgramming>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (SpineProgrammingGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spine.SpineProgrammingGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = SpineProgrammingGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineProgrammingGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (SpineProgrammingGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return SpineProgrammingGenPage.staticSearchSolrId(siteRequest_, SpineProgrammingGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	/////////////////////////////
  // pageUriSpineProgramming //
	/////////////////////////////


  /**
   *  The entity pageUriSpineProgramming
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriSpineProgramming;

  /**
   * <br> The entity pageUriSpineProgramming
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spine.SpineProgrammingGenPage&fq=entiteVar_enUS_indexed_string:pageUriSpineProgramming">Find the entity pageUriSpineProgramming in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriSpineProgramming(Wrap<String> c);

  public String getPageUriSpineProgramming() {
    return pageUriSpineProgramming;
  }
  public void setPageUriSpineProgramming(String o) {
    this.pageUriSpineProgramming = SpineProgrammingGenPage.staticSetPageUriSpineProgramming(siteRequest_, o);
  }
  public static String staticSetPageUriSpineProgramming(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineProgrammingGenPage pageUriSpineProgrammingInit() {
    Wrap<String> pageUriSpineProgrammingWrap = new Wrap<String>().var("pageUriSpineProgramming");
    if(pageUriSpineProgramming == null) {
      _pageUriSpineProgramming(pageUriSpineProgrammingWrap);
      Optional.ofNullable(pageUriSpineProgrammingWrap.getO()).ifPresent(o -> {
        setPageUriSpineProgramming(o);
      });
    }
    return (SpineProgrammingGenPage)this;
  }

  public static String staticSearchPageUriSpineProgramming(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriSpineProgramming(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriSpineProgramming(SiteRequest siteRequest_, String o) {
    return SpineProgrammingGenPage.staticSearchPageUriSpineProgramming(siteRequest_, SpineProgrammingGenPage.staticSetPageUriSpineProgramming(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<SpineProgrammingGenPageGen<DEV>> promiseDeepSpineProgrammingGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepSpineProgrammingGenPage();
  }

  public Future<SpineProgrammingGenPageGen<DEV>> promiseDeepSpineProgrammingGenPage() {
    Promise<SpineProgrammingGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseSpineProgrammingGenPage(promise2);
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

  public Future<Void> promiseSpineProgrammingGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListSpineProgramming_Init();
        listSpineProgrammingInit();
        resultCountInit();
        resultInit();
        solrIdInit();
        pageUriSpineProgrammingInit();
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

  @Override public Future<? extends SpineProgrammingGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepSpineProgrammingGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestSpineProgrammingGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestSpineProgrammingGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainSpineProgrammingGenPage(v);
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
  public Object obtainSpineProgrammingGenPage(String var) {
    SpineProgrammingGenPage oSpineProgrammingGenPage = (SpineProgrammingGenPage)this;
    switch(var) {
      case "searchListSpineProgramming_":
        return oSpineProgrammingGenPage.searchListSpineProgramming_;
      case "listSpineProgramming":
        return oSpineProgrammingGenPage.listSpineProgramming;
      case "resultCount":
        return oSpineProgrammingGenPage.resultCount;
      case "result":
        return oSpineProgrammingGenPage.result;
      case "solrId":
        return oSpineProgrammingGenPage.solrId;
      case "pageUriSpineProgramming":
        return oSpineProgrammingGenPage.pageUriSpineProgramming;
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
        o = relateSpineProgrammingGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateSpineProgrammingGenPage(String var, Object val) {
    SpineProgrammingGenPage oSpineProgrammingGenPage = (SpineProgrammingGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, SpineProgrammingGenPage o) {
    return staticSetSpineProgrammingGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetSpineProgrammingGenPage(String entityVar, SiteRequest siteRequest_, String v, SpineProgrammingGenPage o) {
    switch(entityVar) {
    case "listSpineProgramming":
      return SpineProgrammingGenPage.staticSetListSpineProgramming(siteRequest_, v);
    case "resultCount":
      return SpineProgrammingGenPage.staticSetResultCount(siteRequest_, v);
    case "solrId":
      return SpineProgrammingGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriSpineProgramming":
      return SpineProgrammingGenPage.staticSetPageUriSpineProgramming(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchSpineProgrammingGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchSpineProgrammingGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listSpineProgramming":
      return SpineProgrammingGenPage.staticSearchListSpineProgramming(siteRequest_, (JsonArray)o);
    case "resultCount":
      return SpineProgrammingGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return SpineProgrammingGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriSpineProgramming":
      return SpineProgrammingGenPage.staticSearchPageUriSpineProgramming(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrSpineProgrammingGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrSpineProgrammingGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listSpineProgramming":
      return SpineProgrammingGenPage.staticSearchStrListSpineProgramming(siteRequest_, (String)o);
    case "resultCount":
      return SpineProgrammingGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return SpineProgrammingGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriSpineProgramming":
      return SpineProgrammingGenPage.staticSearchStrPageUriSpineProgramming(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqSpineProgrammingGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqSpineProgrammingGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listSpineProgramming":
      return SpineProgrammingGenPage.staticSearchFqListSpineProgramming(siteRequest_, o);
    case "resultCount":
      return SpineProgrammingGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "solrId":
      return SpineProgrammingGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriSpineProgramming":
      return SpineProgrammingGenPage.staticSearchFqPageUriSpineProgramming(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "SpineProgrammingGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.spine.SpineProgrammingGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListSpineProgramming_ = "searchListSpineProgramming_";
  public static final String VAR_listSpineProgramming = "listSpineProgramming";
  public static final String VAR_resultCount = "resultCount";
  public static final String VAR_result = "result";
  public static final String VAR_solrId = "solrId";
  public static final String VAR_pageUriSpineProgramming = "pageUriSpineProgramming";

  public static final String DISPLAY_NAME_searchListSpineProgramming_ = "";
  public static final String DISPLAY_NAME_listSpineProgramming = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriSpineProgramming = "";

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
  public String enUSStringFormatUrlEditPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlDisplayPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlUserPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlDownloadForClass() {
    return null;
  }

  public static String displayNameForClass(String var) {
    return SpineProgrammingGenPage.displayNameSpineProgrammingGenPage(var);
  }
  public static String displayNameSpineProgrammingGenPage(String var) {
    switch(var) {
    case VAR_searchListSpineProgramming_:
      return DISPLAY_NAME_searchListSpineProgramming_;
    case VAR_listSpineProgramming:
      return DISPLAY_NAME_listSpineProgramming;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriSpineProgramming:
      return DISPLAY_NAME_pageUriSpineProgramming;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }

  public static String descriptionSpineProgrammingGenPage(String var) {
    if(var == null)
      return null;
    switch(var) {
      default:
        return PageLayout.descriptionPageLayout(var);
    }
  }

  public static String classSimpleNameSpineProgrammingGenPage(String var) {
    switch(var) {
    case VAR_searchListSpineProgramming_:
      return "SearchList";
    case VAR_listSpineProgramming:
      return "JsonArray";
    case VAR_resultCount:
      return "Integer";
    case VAR_result:
      return "SpineProgramming";
    case VAR_solrId:
      return "String";
    case VAR_pageUriSpineProgramming:
      return "String";
      default:
        return PageLayout.classSimpleNamePageLayout(var);
    }
  }

  public static Integer htmColumnSpineProgrammingGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmColumnPageLayout(var);
    }
  }

  public static Integer htmRowSpineProgrammingGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmRowPageLayout(var);
    }
  }

  public static Integer htmCellSpineProgrammingGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmCellPageLayout(var);
    }
  }

  public static Integer lengthMinSpineProgrammingGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.lengthMinPageLayout(var);
    }
  }

  public static Integer lengthMaxSpineProgrammingGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.lengthMaxPageLayout(var);
    }
  }

  public static Integer maxSpineProgrammingGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.maxPageLayout(var);
    }
  }

  public static Integer minSpineProgrammingGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.minPageLayout(var);
    }
  }
}
