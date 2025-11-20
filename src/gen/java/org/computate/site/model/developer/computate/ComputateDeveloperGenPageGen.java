package org.computate.site.model.developer.computate;

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
import org.computate.site.model.developer.computate.ComputateDeveloper;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these ComputateDeveloperGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ComputateDeveloperGenPageGen into the class ComputateDeveloperGenPage. 
 * </li>
 * <h3>About the ComputateDeveloperGenPage class and it's generated class ComputateDeveloperGenPageGen&lt;PageLayout&gt;: </h3>extends ComputateDeveloperGenPageGen
 * <p>
 * This Java class extends a generated Java class ComputateDeveloperGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.computate.ComputateDeveloperGenPage">Find the class ComputateDeveloperGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ComputateDeveloperGenPageGen<PageLayout>
 * <p>This <code>class ComputateDeveloperGenPage extends ComputateDeveloperGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated ComputateDeveloperGenPageGen. 
 * The generated <code>class ComputateDeveloperGenPageGen extends PageLayout</code> which means that ComputateDeveloperGenPage extends ComputateDeveloperGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ComputateDeveloperGenPage class will inherit the helpful inherited class comments from the super class ComputateDeveloperGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ComputateDeveloperGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class ComputateDeveloperGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.computate.ComputateDeveloperGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.developer.computate in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.developer.computate&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ComputateDeveloperGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(ComputateDeveloperGenPage.class);

	///////////////////////////////////
  // searchListComputateDeveloper_ //
	///////////////////////////////////


  /**
   *  The entity searchListComputateDeveloper_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<ComputateDeveloper> searchListComputateDeveloper_;

  /**
   * <br> The entity searchListComputateDeveloper_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.computate.ComputateDeveloperGenPage&fq=entiteVar_enUS_indexed_string:searchListComputateDeveloper_">Find the entity searchListComputateDeveloper_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListComputateDeveloper_(Wrap<SearchList<ComputateDeveloper>> w);

  public SearchList<ComputateDeveloper> getSearchListComputateDeveloper_() {
    return searchListComputateDeveloper_;
  }

  public void setSearchListComputateDeveloper_(SearchList<ComputateDeveloper> searchListComputateDeveloper_) {
    this.searchListComputateDeveloper_ = searchListComputateDeveloper_;
  }
  public static SearchList<ComputateDeveloper> staticSetSearchListComputateDeveloper_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected ComputateDeveloperGenPage searchListComputateDeveloper_Init() {
    Wrap<SearchList<ComputateDeveloper>> searchListComputateDeveloper_Wrap = new Wrap<SearchList<ComputateDeveloper>>().var("searchListComputateDeveloper_");
    if(searchListComputateDeveloper_ == null) {
      _searchListComputateDeveloper_(searchListComputateDeveloper_Wrap);
      Optional.ofNullable(searchListComputateDeveloper_Wrap.getO()).ifPresent(o -> {
        setSearchListComputateDeveloper_(o);
      });
    }
    return (ComputateDeveloperGenPage)this;
  }

	////////////////////////////
  // listComputateDeveloper //
	////////////////////////////


  /**
   *  The entity listComputateDeveloper
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listComputateDeveloper = new JsonArray();

  /**
   * <br> The entity listComputateDeveloper
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.computate.ComputateDeveloperGenPage&fq=entiteVar_enUS_indexed_string:listComputateDeveloper">Find the entity listComputateDeveloper in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listComputateDeveloper(JsonArray l);

  public JsonArray getListComputateDeveloper() {
    return listComputateDeveloper;
  }

  public void setListComputateDeveloper(JsonArray listComputateDeveloper) {
    this.listComputateDeveloper = listComputateDeveloper;
  }
  @JsonIgnore
  public void setListComputateDeveloper(String o) {
    this.listComputateDeveloper = ComputateDeveloperGenPage.staticSetListComputateDeveloper(siteRequest_, o);
  }
  public static JsonArray staticSetListComputateDeveloper(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected ComputateDeveloperGenPage listComputateDeveloperInit() {
    _listComputateDeveloper(listComputateDeveloper);
    return (ComputateDeveloperGenPage)this;
  }

  public static String staticSearchListComputateDeveloper(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListComputateDeveloper(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListComputateDeveloper(SiteRequest siteRequest_, String o) {
    return ComputateDeveloperGenPage.staticSearchListComputateDeveloper(siteRequest_, ComputateDeveloperGenPage.staticSetListComputateDeveloper(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.computate.ComputateDeveloperGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = ComputateDeveloperGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected ComputateDeveloperGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (ComputateDeveloperGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return ComputateDeveloperGenPage.staticSearchResultCount(siteRequest_, ComputateDeveloperGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected ComputateDeveloper result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.computate.ComputateDeveloperGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<ComputateDeveloper> w);

  public ComputateDeveloper getResult() {
    return result;
  }

  public void setResult(ComputateDeveloper result) {
    this.result = result;
  }
  public static ComputateDeveloper staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected ComputateDeveloperGenPage resultInit() {
    Wrap<ComputateDeveloper> resultWrap = new Wrap<ComputateDeveloper>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (ComputateDeveloperGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.computate.ComputateDeveloperGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = ComputateDeveloperGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ComputateDeveloperGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (ComputateDeveloperGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return ComputateDeveloperGenPage.staticSearchSolrId(siteRequest_, ComputateDeveloperGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	///////////////////////////////
  // pageUriComputateDeveloper //
	///////////////////////////////


  /**
   *  The entity pageUriComputateDeveloper
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriComputateDeveloper;

  /**
   * <br> The entity pageUriComputateDeveloper
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.computate.ComputateDeveloperGenPage&fq=entiteVar_enUS_indexed_string:pageUriComputateDeveloper">Find the entity pageUriComputateDeveloper in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriComputateDeveloper(Wrap<String> c);

  public String getPageUriComputateDeveloper() {
    return pageUriComputateDeveloper;
  }
  public void setPageUriComputateDeveloper(String o) {
    this.pageUriComputateDeveloper = ComputateDeveloperGenPage.staticSetPageUriComputateDeveloper(siteRequest_, o);
  }
  public static String staticSetPageUriComputateDeveloper(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ComputateDeveloperGenPage pageUriComputateDeveloperInit() {
    Wrap<String> pageUriComputateDeveloperWrap = new Wrap<String>().var("pageUriComputateDeveloper");
    if(pageUriComputateDeveloper == null) {
      _pageUriComputateDeveloper(pageUriComputateDeveloperWrap);
      Optional.ofNullable(pageUriComputateDeveloperWrap.getO()).ifPresent(o -> {
        setPageUriComputateDeveloper(o);
      });
    }
    return (ComputateDeveloperGenPage)this;
  }

  public static String staticSearchPageUriComputateDeveloper(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriComputateDeveloper(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriComputateDeveloper(SiteRequest siteRequest_, String o) {
    return ComputateDeveloperGenPage.staticSearchPageUriComputateDeveloper(siteRequest_, ComputateDeveloperGenPage.staticSetPageUriComputateDeveloper(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<ComputateDeveloperGenPageGen<DEV>> promiseDeepComputateDeveloperGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepComputateDeveloperGenPage();
  }

  public Future<ComputateDeveloperGenPageGen<DEV>> promiseDeepComputateDeveloperGenPage() {
    Promise<ComputateDeveloperGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseComputateDeveloperGenPage(promise2);
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

  public Future<Void> promiseComputateDeveloperGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListComputateDeveloper_Init();
        listComputateDeveloperInit();
        resultCountInit();
        resultInit();
        solrIdInit();
        pageUriComputateDeveloperInit();
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

  @Override public Future<? extends ComputateDeveloperGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepComputateDeveloperGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestComputateDeveloperGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestComputateDeveloperGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainComputateDeveloperGenPage(v);
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
  public Object obtainComputateDeveloperGenPage(String var) {
    ComputateDeveloperGenPage oComputateDeveloperGenPage = (ComputateDeveloperGenPage)this;
    switch(var) {
      case "searchListComputateDeveloper_":
        return oComputateDeveloperGenPage.searchListComputateDeveloper_;
      case "listComputateDeveloper":
        return oComputateDeveloperGenPage.listComputateDeveloper;
      case "resultCount":
        return oComputateDeveloperGenPage.resultCount;
      case "result":
        return oComputateDeveloperGenPage.result;
      case "solrId":
        return oComputateDeveloperGenPage.solrId;
      case "pageUriComputateDeveloper":
        return oComputateDeveloperGenPage.pageUriComputateDeveloper;
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
        o = relateComputateDeveloperGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateComputateDeveloperGenPage(String var, Object val) {
    ComputateDeveloperGenPage oComputateDeveloperGenPage = (ComputateDeveloperGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, ComputateDeveloperGenPage o) {
    return staticSetComputateDeveloperGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetComputateDeveloperGenPage(String entityVar, SiteRequest siteRequest_, String v, ComputateDeveloperGenPage o) {
    switch(entityVar) {
    case "listComputateDeveloper":
      return ComputateDeveloperGenPage.staticSetListComputateDeveloper(siteRequest_, v);
    case "resultCount":
      return ComputateDeveloperGenPage.staticSetResultCount(siteRequest_, v);
    case "solrId":
      return ComputateDeveloperGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriComputateDeveloper":
      return ComputateDeveloperGenPage.staticSetPageUriComputateDeveloper(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchComputateDeveloperGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchComputateDeveloperGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listComputateDeveloper":
      return ComputateDeveloperGenPage.staticSearchListComputateDeveloper(siteRequest_, (JsonArray)o);
    case "resultCount":
      return ComputateDeveloperGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return ComputateDeveloperGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriComputateDeveloper":
      return ComputateDeveloperGenPage.staticSearchPageUriComputateDeveloper(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrComputateDeveloperGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrComputateDeveloperGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listComputateDeveloper":
      return ComputateDeveloperGenPage.staticSearchStrListComputateDeveloper(siteRequest_, (String)o);
    case "resultCount":
      return ComputateDeveloperGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return ComputateDeveloperGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriComputateDeveloper":
      return ComputateDeveloperGenPage.staticSearchStrPageUriComputateDeveloper(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqComputateDeveloperGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqComputateDeveloperGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listComputateDeveloper":
      return ComputateDeveloperGenPage.staticSearchFqListComputateDeveloper(siteRequest_, o);
    case "resultCount":
      return ComputateDeveloperGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "solrId":
      return ComputateDeveloperGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriComputateDeveloper":
      return ComputateDeveloperGenPage.staticSearchFqPageUriComputateDeveloper(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "ComputateDeveloperGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.developer.computate.ComputateDeveloperGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListComputateDeveloper_ = "searchListComputateDeveloper_";
  public static final String VAR_listComputateDeveloper = "listComputateDeveloper";
  public static final String VAR_resultCount = "resultCount";
  public static final String VAR_result = "result";
  public static final String VAR_solrId = "solrId";
  public static final String VAR_pageUriComputateDeveloper = "pageUriComputateDeveloper";

  public static final String DISPLAY_NAME_searchListComputateDeveloper_ = "";
  public static final String DISPLAY_NAME_listComputateDeveloper = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriComputateDeveloper = "";

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
    return ComputateDeveloperGenPage.displayNameComputateDeveloperGenPage(var);
  }
  public static String displayNameComputateDeveloperGenPage(String var) {
    switch(var) {
    case VAR_searchListComputateDeveloper_:
      return DISPLAY_NAME_searchListComputateDeveloper_;
    case VAR_listComputateDeveloper:
      return DISPLAY_NAME_listComputateDeveloper;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriComputateDeveloper:
      return DISPLAY_NAME_pageUriComputateDeveloper;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }

  public static String descriptionComputateDeveloperGenPage(String var) {
    if(var == null)
      return null;
    switch(var) {
      default:
        return PageLayout.descriptionPageLayout(var);
    }
  }

  public static String classSimpleNameComputateDeveloperGenPage(String var) {
    switch(var) {
    case VAR_searchListComputateDeveloper_:
      return "SearchList";
    case VAR_listComputateDeveloper:
      return "JsonArray";
    case VAR_resultCount:
      return "Integer";
    case VAR_result:
      return "ComputateDeveloper";
    case VAR_solrId:
      return "String";
    case VAR_pageUriComputateDeveloper:
      return "String";
      default:
        return PageLayout.classSimpleNamePageLayout(var);
    }
  }

  public static Integer htmColumnComputateDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmColumnPageLayout(var);
    }
  }

  public static Integer htmRowComputateDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmRowPageLayout(var);
    }
  }

  public static Integer htmCellComputateDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmCellPageLayout(var);
    }
  }

  public static Integer lengthMinComputateDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.lengthMinPageLayout(var);
    }
  }

  public static Integer lengthMaxComputateDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.lengthMaxPageLayout(var);
    }
  }

  public static Integer maxComputateDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.maxPageLayout(var);
    }
  }

  public static Integer minComputateDeveloperGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.minPageLayout(var);
    }
  }
}
