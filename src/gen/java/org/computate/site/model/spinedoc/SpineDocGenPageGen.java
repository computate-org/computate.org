package org.computate.site.model.spinedoc;

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
import org.computate.site.model.spinedoc.SpineDoc;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these SpineDocGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SpineDocGenPageGen into the class SpineDocGenPage. 
 * </li>
 * <h3>About the SpineDocGenPage class and it's generated class SpineDocGenPageGen&lt;PageLayout&gt;: </h3>extends SpineDocGenPageGen
 * <p>
 * This Java class extends a generated Java class SpineDocGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDocGenPage">Find the class SpineDocGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SpineDocGenPageGen<PageLayout>
 * <p>This <code>class SpineDocGenPage extends SpineDocGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated SpineDocGenPageGen. 
 * The generated <code>class SpineDocGenPageGen extends PageLayout</code> which means that SpineDocGenPage extends SpineDocGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SpineDocGenPage class will inherit the helpful inherited class comments from the super class SpineDocGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the SpineDocGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class SpineDocGenPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDocGenPage&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.spinedoc in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.spinedoc&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class SpineDocGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(SpineDocGenPage.class);

	/////////////////////////
  // searchListSpineDoc_ //
	/////////////////////////


  /**
   *  The entity searchListSpineDoc_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<SpineDoc> searchListSpineDoc_;

  /**
   * <br> The entity searchListSpineDoc_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDocGenPage&fq=entiteVar_enUS_indexed_string:searchListSpineDoc_">Find the entity searchListSpineDoc_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListSpineDoc_(Wrap<SearchList<SpineDoc>> w);

  public SearchList<SpineDoc> getSearchListSpineDoc_() {
    return searchListSpineDoc_;
  }

  public void setSearchListSpineDoc_(SearchList<SpineDoc> searchListSpineDoc_) {
    this.searchListSpineDoc_ = searchListSpineDoc_;
  }
  public static SearchList<SpineDoc> staticSetSearchListSpineDoc_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected SpineDocGenPage searchListSpineDoc_Init() {
    Wrap<SearchList<SpineDoc>> searchListSpineDoc_Wrap = new Wrap<SearchList<SpineDoc>>().var("searchListSpineDoc_");
    if(searchListSpineDoc_ == null) {
      _searchListSpineDoc_(searchListSpineDoc_Wrap);
      Optional.ofNullable(searchListSpineDoc_Wrap.getO()).ifPresent(o -> {
        setSearchListSpineDoc_(o);
      });
    }
    return (SpineDocGenPage)this;
  }

	//////////////////
  // listSpineDoc //
	//////////////////


  /**
   *  The entity listSpineDoc
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listSpineDoc = new JsonArray();

  /**
   * <br> The entity listSpineDoc
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDocGenPage&fq=entiteVar_enUS_indexed_string:listSpineDoc">Find the entity listSpineDoc in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listSpineDoc(JsonArray l);

  public JsonArray getListSpineDoc() {
    return listSpineDoc;
  }

  public void setListSpineDoc(JsonArray listSpineDoc) {
    this.listSpineDoc = listSpineDoc;
  }
  @JsonIgnore
  public void setListSpineDoc(String o) {
    this.listSpineDoc = SpineDocGenPage.staticSetListSpineDoc(siteRequest_, o);
  }
  public static JsonArray staticSetListSpineDoc(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected SpineDocGenPage listSpineDocInit() {
    _listSpineDoc(listSpineDoc);
    return (SpineDocGenPage)this;
  }

  public static String staticSearchListSpineDoc(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListSpineDoc(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListSpineDoc(SiteRequest siteRequest_, String o) {
    return SpineDocGenPage.staticSearchListSpineDoc(siteRequest_, SpineDocGenPage.staticSetListSpineDoc(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDocGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = SpineDocGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected SpineDocGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (SpineDocGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return SpineDocGenPage.staticSearchResultCount(siteRequest_, SpineDocGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected SpineDoc result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDocGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<SpineDoc> w);

  public SpineDoc getResult() {
    return result;
  }

  public void setResult(SpineDoc result) {
    this.result = result;
  }
  public static SpineDoc staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected SpineDocGenPage resultInit() {
    Wrap<SpineDoc> resultWrap = new Wrap<SpineDoc>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (SpineDocGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDocGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = SpineDocGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDocGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (SpineDocGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return SpineDocGenPage.staticSearchSolrId(siteRequest_, SpineDocGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	/////////////////////
  // pageUriSpineDoc //
	/////////////////////


  /**
   *  The entity pageUriSpineDoc
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriSpineDoc;

  /**
   * <br> The entity pageUriSpineDoc
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDocGenPage&fq=entiteVar_enUS_indexed_string:pageUriSpineDoc">Find the entity pageUriSpineDoc in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriSpineDoc(Wrap<String> c);

  public String getPageUriSpineDoc() {
    return pageUriSpineDoc;
  }
  public void setPageUriSpineDoc(String o) {
    this.pageUriSpineDoc = SpineDocGenPage.staticSetPageUriSpineDoc(siteRequest_, o);
  }
  public static String staticSetPageUriSpineDoc(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDocGenPage pageUriSpineDocInit() {
    Wrap<String> pageUriSpineDocWrap = new Wrap<String>().var("pageUriSpineDoc");
    if(pageUriSpineDoc == null) {
      _pageUriSpineDoc(pageUriSpineDocWrap);
      Optional.ofNullable(pageUriSpineDocWrap.getO()).ifPresent(o -> {
        setPageUriSpineDoc(o);
      });
    }
    return (SpineDocGenPage)this;
  }

  public static String staticSearchPageUriSpineDoc(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriSpineDoc(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriSpineDoc(SiteRequest siteRequest_, String o) {
    return SpineDocGenPage.staticSearchPageUriSpineDoc(siteRequest_, SpineDocGenPage.staticSetPageUriSpineDoc(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<SpineDocGenPageGen<DEV>> promiseDeepSpineDocGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepSpineDocGenPage();
  }

  public Future<SpineDocGenPageGen<DEV>> promiseDeepSpineDocGenPage() {
    Promise<SpineDocGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseSpineDocGenPage(promise2);
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

  public Future<Void> promiseSpineDocGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListSpineDoc_Init();
        listSpineDocInit();
        resultCountInit();
        resultInit();
        solrIdInit();
        pageUriSpineDocInit();
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

  @Override public Future<? extends SpineDocGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepSpineDocGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestSpineDocGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestSpineDocGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainSpineDocGenPage(v);
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
  public Object obtainSpineDocGenPage(String var) {
    SpineDocGenPage oSpineDocGenPage = (SpineDocGenPage)this;
    switch(var) {
      case "searchListSpineDoc_":
        return oSpineDocGenPage.searchListSpineDoc_;
      case "listSpineDoc":
        return oSpineDocGenPage.listSpineDoc;
      case "resultCount":
        return oSpineDocGenPage.resultCount;
      case "result":
        return oSpineDocGenPage.result;
      case "solrId":
        return oSpineDocGenPage.solrId;
      case "pageUriSpineDoc":
        return oSpineDocGenPage.pageUriSpineDoc;
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
        o = relateSpineDocGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateSpineDocGenPage(String var, Object val) {
    SpineDocGenPage oSpineDocGenPage = (SpineDocGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, SpineDocGenPage o) {
    return staticSetSpineDocGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetSpineDocGenPage(String entityVar, SiteRequest siteRequest_, String v, SpineDocGenPage o) {
    switch(entityVar) {
    case "listSpineDoc":
      return SpineDocGenPage.staticSetListSpineDoc(siteRequest_, v);
    case "resultCount":
      return SpineDocGenPage.staticSetResultCount(siteRequest_, v);
    case "solrId":
      return SpineDocGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriSpineDoc":
      return SpineDocGenPage.staticSetPageUriSpineDoc(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchSpineDocGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchSpineDocGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listSpineDoc":
      return SpineDocGenPage.staticSearchListSpineDoc(siteRequest_, (JsonArray)o);
    case "resultCount":
      return SpineDocGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return SpineDocGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriSpineDoc":
      return SpineDocGenPage.staticSearchPageUriSpineDoc(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrSpineDocGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrSpineDocGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listSpineDoc":
      return SpineDocGenPage.staticSearchStrListSpineDoc(siteRequest_, (String)o);
    case "resultCount":
      return SpineDocGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return SpineDocGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriSpineDoc":
      return SpineDocGenPage.staticSearchStrPageUriSpineDoc(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqSpineDocGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqSpineDocGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listSpineDoc":
      return SpineDocGenPage.staticSearchFqListSpineDoc(siteRequest_, o);
    case "resultCount":
      return SpineDocGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "solrId":
      return SpineDocGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriSpineDoc":
      return SpineDocGenPage.staticSearchFqPageUriSpineDoc(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "SpineDocGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.spinedoc.SpineDocGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListSpineDoc_ = "searchListSpineDoc_";
  public static final String SET_searchListSpineDoc_ = "setSearchListSpineDoc_";
  public static final String VAR_listSpineDoc = "listSpineDoc";
  public static final String SET_listSpineDoc = "setListSpineDoc";
  public static final String VAR_resultCount = "resultCount";
  public static final String SET_resultCount = "setResultCount";
  public static final String VAR_result = "result";
  public static final String SET_result = "setResult";
  public static final String VAR_solrId = "solrId";
  public static final String SET_solrId = "setSolrId";
  public static final String VAR_pageUriSpineDoc = "pageUriSpineDoc";
  public static final String SET_pageUriSpineDoc = "setPageUriSpineDoc";

  public static final String DISPLAY_NAME_searchListSpineDoc_ = "";
  public static final String DISPLAY_NAME_listSpineDoc = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriSpineDoc = "";

  public static String displayNameForClass(String var) {
    return SpineDocGenPage.displayNameSpineDocGenPage(var);
  }
  public static String displayNameSpineDocGenPage(String var) {
    switch(var) {
    case VAR_searchListSpineDoc_:
      return DISPLAY_NAME_searchListSpineDoc_;
    case VAR_listSpineDoc:
      return DISPLAY_NAME_listSpineDoc;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriSpineDoc:
      return DISPLAY_NAME_pageUriSpineDoc;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }
}
