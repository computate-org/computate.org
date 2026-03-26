package org.computate.site.model.deployspine;

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
import org.computate.site.model.deployspine.DeploySpine;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these DeploySpineGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class DeploySpineGenPageGen into the class DeploySpineGenPage. 
 * </li>
 * <h3>About the DeploySpineGenPage class and it's generated class DeploySpineGenPageGen&lt;PageLayout&gt;: </h3>extends DeploySpineGenPageGen
 * <p>
 * This Java class extends a generated Java class DeploySpineGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpineGenPage">Find the class DeploySpineGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends DeploySpineGenPageGen<PageLayout>
 * <p>This <code>class DeploySpineGenPage extends DeploySpineGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated DeploySpineGenPageGen. 
 * The generated <code>class DeploySpineGenPageGen extends PageLayout</code> which means that DeploySpineGenPage extends DeploySpineGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the DeploySpineGenPage class will inherit the helpful inherited class comments from the super class DeploySpineGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the DeploySpineGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class DeploySpineGenPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpineGenPage&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.deployspine in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.deployspine&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class DeploySpineGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(DeploySpineGenPage.class);

	////////////////////////////
  // searchListDeploySpine_ //
	////////////////////////////


  /**
   *  The entity searchListDeploySpine_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<DeploySpine> searchListDeploySpine_;

  /**
   * <br> The entity searchListDeploySpine_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpineGenPage&fq=entiteVar_enUS_indexed_string:searchListDeploySpine_">Find the entity searchListDeploySpine_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListDeploySpine_(Wrap<SearchList<DeploySpine>> w);

  public SearchList<DeploySpine> getSearchListDeploySpine_() {
    return searchListDeploySpine_;
  }

  public void setSearchListDeploySpine_(SearchList<DeploySpine> searchListDeploySpine_) {
    this.searchListDeploySpine_ = searchListDeploySpine_;
  }
  public static SearchList<DeploySpine> staticSetSearchListDeploySpine_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected DeploySpineGenPage searchListDeploySpine_Init() {
    Wrap<SearchList<DeploySpine>> searchListDeploySpine_Wrap = new Wrap<SearchList<DeploySpine>>().var("searchListDeploySpine_");
    if(searchListDeploySpine_ == null) {
      _searchListDeploySpine_(searchListDeploySpine_Wrap);
      Optional.ofNullable(searchListDeploySpine_Wrap.getO()).ifPresent(o -> {
        setSearchListDeploySpine_(o);
      });
    }
    return (DeploySpineGenPage)this;
  }

	/////////////////////
  // listDeploySpine //
	/////////////////////


  /**
   *  The entity listDeploySpine
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listDeploySpine = new JsonArray();

  /**
   * <br> The entity listDeploySpine
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpineGenPage&fq=entiteVar_enUS_indexed_string:listDeploySpine">Find the entity listDeploySpine in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listDeploySpine(JsonArray l);

  public JsonArray getListDeploySpine() {
    return listDeploySpine;
  }

  public void setListDeploySpine(JsonArray listDeploySpine) {
    this.listDeploySpine = listDeploySpine;
  }
  @JsonIgnore
  public void setListDeploySpine(String o) {
    this.listDeploySpine = DeploySpineGenPage.staticSetListDeploySpine(siteRequest_, o);
  }
  public static JsonArray staticSetListDeploySpine(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected DeploySpineGenPage listDeploySpineInit() {
    _listDeploySpine(listDeploySpine);
    return (DeploySpineGenPage)this;
  }

  public static String staticSearchListDeploySpine(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListDeploySpine(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListDeploySpine(SiteRequest siteRequest_, String o) {
    return DeploySpineGenPage.staticSearchListDeploySpine(siteRequest_, DeploySpineGenPage.staticSetListDeploySpine(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpineGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = DeploySpineGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected DeploySpineGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (DeploySpineGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return DeploySpineGenPage.staticSearchResultCount(siteRequest_, DeploySpineGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected DeploySpine result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpineGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<DeploySpine> w);

  public DeploySpine getResult() {
    return result;
  }

  public void setResult(DeploySpine result) {
    this.result = result;
  }
  public static DeploySpine staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected DeploySpineGenPage resultInit() {
    Wrap<DeploySpine> resultWrap = new Wrap<DeploySpine>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (DeploySpineGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpineGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = DeploySpineGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected DeploySpineGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (DeploySpineGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return DeploySpineGenPage.staticSearchSolrId(siteRequest_, DeploySpineGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	////////////////////////
  // pageUriDeploySpine //
	////////////////////////


  /**
   *  The entity pageUriDeploySpine
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriDeploySpine;

  /**
   * <br> The entity pageUriDeploySpine
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpineGenPage&fq=entiteVar_enUS_indexed_string:pageUriDeploySpine">Find the entity pageUriDeploySpine in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriDeploySpine(Wrap<String> c);

  public String getPageUriDeploySpine() {
    return pageUriDeploySpine;
  }
  public void setPageUriDeploySpine(String o) {
    this.pageUriDeploySpine = DeploySpineGenPage.staticSetPageUriDeploySpine(siteRequest_, o);
  }
  public static String staticSetPageUriDeploySpine(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected DeploySpineGenPage pageUriDeploySpineInit() {
    Wrap<String> pageUriDeploySpineWrap = new Wrap<String>().var("pageUriDeploySpine");
    if(pageUriDeploySpine == null) {
      _pageUriDeploySpine(pageUriDeploySpineWrap);
      Optional.ofNullable(pageUriDeploySpineWrap.getO()).ifPresent(o -> {
        setPageUriDeploySpine(o);
      });
    }
    return (DeploySpineGenPage)this;
  }

  public static String staticSearchPageUriDeploySpine(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriDeploySpine(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriDeploySpine(SiteRequest siteRequest_, String o) {
    return DeploySpineGenPage.staticSearchPageUriDeploySpine(siteRequest_, DeploySpineGenPage.staticSetPageUriDeploySpine(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<DeploySpineGenPageGen<DEV>> promiseDeepDeploySpineGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepDeploySpineGenPage();
  }

  public Future<DeploySpineGenPageGen<DEV>> promiseDeepDeploySpineGenPage() {
    Promise<DeploySpineGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseDeploySpineGenPage(promise2);
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

  public Future<Void> promiseDeploySpineGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListDeploySpine_Init();
        listDeploySpineInit();
        resultCountInit();
        resultInit();
        solrIdInit();
        pageUriDeploySpineInit();
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

  @Override public Future<? extends DeploySpineGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepDeploySpineGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestDeploySpineGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestDeploySpineGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainDeploySpineGenPage(v);
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
  public Object obtainDeploySpineGenPage(String var) {
    DeploySpineGenPage oDeploySpineGenPage = (DeploySpineGenPage)this;
    switch(var) {
      case "searchListDeploySpine_":
        return oDeploySpineGenPage.searchListDeploySpine_;
      case "listDeploySpine":
        return oDeploySpineGenPage.listDeploySpine;
      case "resultCount":
        return oDeploySpineGenPage.resultCount;
      case "result":
        return oDeploySpineGenPage.result;
      case "solrId":
        return oDeploySpineGenPage.solrId;
      case "pageUriDeploySpine":
        return oDeploySpineGenPage.pageUriDeploySpine;
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
        o = relateDeploySpineGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateDeploySpineGenPage(String var, Object val) {
    DeploySpineGenPage oDeploySpineGenPage = (DeploySpineGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, DeploySpineGenPage o) {
    return staticSetDeploySpineGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetDeploySpineGenPage(String entityVar, SiteRequest siteRequest_, String v, DeploySpineGenPage o) {
    switch(entityVar) {
    case "listDeploySpine":
      return DeploySpineGenPage.staticSetListDeploySpine(siteRequest_, v);
    case "resultCount":
      return DeploySpineGenPage.staticSetResultCount(siteRequest_, v);
    case "solrId":
      return DeploySpineGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriDeploySpine":
      return DeploySpineGenPage.staticSetPageUriDeploySpine(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchDeploySpineGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchDeploySpineGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listDeploySpine":
      return DeploySpineGenPage.staticSearchListDeploySpine(siteRequest_, (JsonArray)o);
    case "resultCount":
      return DeploySpineGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return DeploySpineGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriDeploySpine":
      return DeploySpineGenPage.staticSearchPageUriDeploySpine(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrDeploySpineGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrDeploySpineGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listDeploySpine":
      return DeploySpineGenPage.staticSearchStrListDeploySpine(siteRequest_, (String)o);
    case "resultCount":
      return DeploySpineGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return DeploySpineGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriDeploySpine":
      return DeploySpineGenPage.staticSearchStrPageUriDeploySpine(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqDeploySpineGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqDeploySpineGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listDeploySpine":
      return DeploySpineGenPage.staticSearchFqListDeploySpine(siteRequest_, o);
    case "resultCount":
      return DeploySpineGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "solrId":
      return DeploySpineGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriDeploySpine":
      return DeploySpineGenPage.staticSearchFqPageUriDeploySpine(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "DeploySpineGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.deployspine.DeploySpineGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListDeploySpine_ = "searchListDeploySpine_";
  public static final String SET_searchListDeploySpine_ = "setSearchListDeploySpine_";
  public static final String VAR_listDeploySpine = "listDeploySpine";
  public static final String SET_listDeploySpine = "setListDeploySpine";
  public static final String VAR_resultCount = "resultCount";
  public static final String SET_resultCount = "setResultCount";
  public static final String VAR_result = "result";
  public static final String SET_result = "setResult";
  public static final String VAR_solrId = "solrId";
  public static final String SET_solrId = "setSolrId";
  public static final String VAR_pageUriDeploySpine = "pageUriDeploySpine";
  public static final String SET_pageUriDeploySpine = "setPageUriDeploySpine";

  public static final String DISPLAY_NAME_searchListDeploySpine_ = "";
  public static final String DISPLAY_NAME_listDeploySpine = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriDeploySpine = "";

  public static String displayNameForClass(String var) {
    return DeploySpineGenPage.displayNameDeploySpineGenPage(var);
  }
  public static String displayNameDeploySpineGenPage(String var) {
    switch(var) {
    case VAR_searchListDeploySpine_:
      return DISPLAY_NAME_searchListDeploySpine_;
    case VAR_listDeploySpine:
      return DISPLAY_NAME_listDeploySpine;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriDeploySpine:
      return DISPLAY_NAME_pageUriDeploySpine;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }
}
