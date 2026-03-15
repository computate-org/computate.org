package org.computate.site.model.developer.dcm;

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
import org.computate.site.model.developer.dcm.DeveloperComputerMinion;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these DeveloperComputerMinionGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class DeveloperComputerMinionGenPageGen into the class DeveloperComputerMinionGenPage. 
 * </li>
 * <h3>About the DeveloperComputerMinionGenPage class and it's generated class DeveloperComputerMinionGenPageGen&lt;PageLayout&gt;: </h3>extends DeveloperComputerMinionGenPageGen
 * <p>
 * This Java class extends a generated Java class DeveloperComputerMinionGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.dcm.DeveloperComputerMinionGenPage">Find the class DeveloperComputerMinionGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends DeveloperComputerMinionGenPageGen<PageLayout>
 * <p>This <code>class DeveloperComputerMinionGenPage extends DeveloperComputerMinionGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated DeveloperComputerMinionGenPageGen. 
 * The generated <code>class DeveloperComputerMinionGenPageGen extends PageLayout</code> which means that DeveloperComputerMinionGenPage extends DeveloperComputerMinionGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the DeveloperComputerMinionGenPage class will inherit the helpful inherited class comments from the super class DeveloperComputerMinionGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the DeveloperComputerMinionGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class DeveloperComputerMinionGenPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.dcm.DeveloperComputerMinionGenPage&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.developer.dcm in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.developer.dcm&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class DeveloperComputerMinionGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(DeveloperComputerMinionGenPage.class);

	////////////////////////////////////////
  // searchListDeveloperComputerMinion_ //
	////////////////////////////////////////


  /**
   *  The entity searchListDeveloperComputerMinion_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<DeveloperComputerMinion> searchListDeveloperComputerMinion_;

  /**
   * <br> The entity searchListDeveloperComputerMinion_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.dcm.DeveloperComputerMinionGenPage&fq=entiteVar_enUS_indexed_string:searchListDeveloperComputerMinion_">Find the entity searchListDeveloperComputerMinion_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListDeveloperComputerMinion_(Wrap<SearchList<DeveloperComputerMinion>> w);

  public SearchList<DeveloperComputerMinion> getSearchListDeveloperComputerMinion_() {
    return searchListDeveloperComputerMinion_;
  }

  public void setSearchListDeveloperComputerMinion_(SearchList<DeveloperComputerMinion> searchListDeveloperComputerMinion_) {
    this.searchListDeveloperComputerMinion_ = searchListDeveloperComputerMinion_;
  }
  public static SearchList<DeveloperComputerMinion> staticSetSearchListDeveloperComputerMinion_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected DeveloperComputerMinionGenPage searchListDeveloperComputerMinion_Init() {
    Wrap<SearchList<DeveloperComputerMinion>> searchListDeveloperComputerMinion_Wrap = new Wrap<SearchList<DeveloperComputerMinion>>().var("searchListDeveloperComputerMinion_");
    if(searchListDeveloperComputerMinion_ == null) {
      _searchListDeveloperComputerMinion_(searchListDeveloperComputerMinion_Wrap);
      Optional.ofNullable(searchListDeveloperComputerMinion_Wrap.getO()).ifPresent(o -> {
        setSearchListDeveloperComputerMinion_(o);
      });
    }
    return (DeveloperComputerMinionGenPage)this;
  }

	/////////////////////////////////
  // listDeveloperComputerMinion //
	/////////////////////////////////


  /**
   *  The entity listDeveloperComputerMinion
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listDeveloperComputerMinion = new JsonArray();

  /**
   * <br> The entity listDeveloperComputerMinion
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.dcm.DeveloperComputerMinionGenPage&fq=entiteVar_enUS_indexed_string:listDeveloperComputerMinion">Find the entity listDeveloperComputerMinion in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listDeveloperComputerMinion(JsonArray l);

  public JsonArray getListDeveloperComputerMinion() {
    return listDeveloperComputerMinion;
  }

  public void setListDeveloperComputerMinion(JsonArray listDeveloperComputerMinion) {
    this.listDeveloperComputerMinion = listDeveloperComputerMinion;
  }
  @JsonIgnore
  public void setListDeveloperComputerMinion(String o) {
    this.listDeveloperComputerMinion = DeveloperComputerMinionGenPage.staticSetListDeveloperComputerMinion(siteRequest_, o);
  }
  public static JsonArray staticSetListDeveloperComputerMinion(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected DeveloperComputerMinionGenPage listDeveloperComputerMinionInit() {
    _listDeveloperComputerMinion(listDeveloperComputerMinion);
    return (DeveloperComputerMinionGenPage)this;
  }

  public static String staticSearchListDeveloperComputerMinion(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListDeveloperComputerMinion(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListDeveloperComputerMinion(SiteRequest siteRequest_, String o) {
    return DeveloperComputerMinionGenPage.staticSearchListDeveloperComputerMinion(siteRequest_, DeveloperComputerMinionGenPage.staticSetListDeveloperComputerMinion(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.dcm.DeveloperComputerMinionGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = DeveloperComputerMinionGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected DeveloperComputerMinionGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (DeveloperComputerMinionGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return DeveloperComputerMinionGenPage.staticSearchResultCount(siteRequest_, DeveloperComputerMinionGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected DeveloperComputerMinion result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.dcm.DeveloperComputerMinionGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<DeveloperComputerMinion> w);

  public DeveloperComputerMinion getResult() {
    return result;
  }

  public void setResult(DeveloperComputerMinion result) {
    this.result = result;
  }
  public static DeveloperComputerMinion staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected DeveloperComputerMinionGenPage resultInit() {
    Wrap<DeveloperComputerMinion> resultWrap = new Wrap<DeveloperComputerMinion>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (DeveloperComputerMinionGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.dcm.DeveloperComputerMinionGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = DeveloperComputerMinionGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected DeveloperComputerMinionGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (DeveloperComputerMinionGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return DeveloperComputerMinionGenPage.staticSearchSolrId(siteRequest_, DeveloperComputerMinionGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	////////////////////////////////////
  // pageUriDeveloperComputerMinion //
	////////////////////////////////////


  /**
   *  The entity pageUriDeveloperComputerMinion
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriDeveloperComputerMinion;

  /**
   * <br> The entity pageUriDeveloperComputerMinion
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.developer.dcm.DeveloperComputerMinionGenPage&fq=entiteVar_enUS_indexed_string:pageUriDeveloperComputerMinion">Find the entity pageUriDeveloperComputerMinion in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriDeveloperComputerMinion(Wrap<String> c);

  public String getPageUriDeveloperComputerMinion() {
    return pageUriDeveloperComputerMinion;
  }
  public void setPageUriDeveloperComputerMinion(String o) {
    this.pageUriDeveloperComputerMinion = DeveloperComputerMinionGenPage.staticSetPageUriDeveloperComputerMinion(siteRequest_, o);
  }
  public static String staticSetPageUriDeveloperComputerMinion(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected DeveloperComputerMinionGenPage pageUriDeveloperComputerMinionInit() {
    Wrap<String> pageUriDeveloperComputerMinionWrap = new Wrap<String>().var("pageUriDeveloperComputerMinion");
    if(pageUriDeveloperComputerMinion == null) {
      _pageUriDeveloperComputerMinion(pageUriDeveloperComputerMinionWrap);
      Optional.ofNullable(pageUriDeveloperComputerMinionWrap.getO()).ifPresent(o -> {
        setPageUriDeveloperComputerMinion(o);
      });
    }
    return (DeveloperComputerMinionGenPage)this;
  }

  public static String staticSearchPageUriDeveloperComputerMinion(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriDeveloperComputerMinion(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriDeveloperComputerMinion(SiteRequest siteRequest_, String o) {
    return DeveloperComputerMinionGenPage.staticSearchPageUriDeveloperComputerMinion(siteRequest_, DeveloperComputerMinionGenPage.staticSetPageUriDeveloperComputerMinion(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<DeveloperComputerMinionGenPageGen<DEV>> promiseDeepDeveloperComputerMinionGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepDeveloperComputerMinionGenPage();
  }

  public Future<DeveloperComputerMinionGenPageGen<DEV>> promiseDeepDeveloperComputerMinionGenPage() {
    Promise<DeveloperComputerMinionGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseDeveloperComputerMinionGenPage(promise2);
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

  public Future<Void> promiseDeveloperComputerMinionGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListDeveloperComputerMinion_Init();
        listDeveloperComputerMinionInit();
        resultCountInit();
        resultInit();
        solrIdInit();
        pageUriDeveloperComputerMinionInit();
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

  @Override public Future<? extends DeveloperComputerMinionGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepDeveloperComputerMinionGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestDeveloperComputerMinionGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestDeveloperComputerMinionGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainDeveloperComputerMinionGenPage(v);
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
  public Object obtainDeveloperComputerMinionGenPage(String var) {
    DeveloperComputerMinionGenPage oDeveloperComputerMinionGenPage = (DeveloperComputerMinionGenPage)this;
    switch(var) {
      case "searchListDeveloperComputerMinion_":
        return oDeveloperComputerMinionGenPage.searchListDeveloperComputerMinion_;
      case "listDeveloperComputerMinion":
        return oDeveloperComputerMinionGenPage.listDeveloperComputerMinion;
      case "resultCount":
        return oDeveloperComputerMinionGenPage.resultCount;
      case "result":
        return oDeveloperComputerMinionGenPage.result;
      case "solrId":
        return oDeveloperComputerMinionGenPage.solrId;
      case "pageUriDeveloperComputerMinion":
        return oDeveloperComputerMinionGenPage.pageUriDeveloperComputerMinion;
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
        o = relateDeveloperComputerMinionGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateDeveloperComputerMinionGenPage(String var, Object val) {
    DeveloperComputerMinionGenPage oDeveloperComputerMinionGenPage = (DeveloperComputerMinionGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, DeveloperComputerMinionGenPage o) {
    return staticSetDeveloperComputerMinionGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetDeveloperComputerMinionGenPage(String entityVar, SiteRequest siteRequest_, String v, DeveloperComputerMinionGenPage o) {
    switch(entityVar) {
    case "listDeveloperComputerMinion":
      return DeveloperComputerMinionGenPage.staticSetListDeveloperComputerMinion(siteRequest_, v);
    case "resultCount":
      return DeveloperComputerMinionGenPage.staticSetResultCount(siteRequest_, v);
    case "solrId":
      return DeveloperComputerMinionGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriDeveloperComputerMinion":
      return DeveloperComputerMinionGenPage.staticSetPageUriDeveloperComputerMinion(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchDeveloperComputerMinionGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchDeveloperComputerMinionGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listDeveloperComputerMinion":
      return DeveloperComputerMinionGenPage.staticSearchListDeveloperComputerMinion(siteRequest_, (JsonArray)o);
    case "resultCount":
      return DeveloperComputerMinionGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return DeveloperComputerMinionGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriDeveloperComputerMinion":
      return DeveloperComputerMinionGenPage.staticSearchPageUriDeveloperComputerMinion(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrDeveloperComputerMinionGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrDeveloperComputerMinionGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listDeveloperComputerMinion":
      return DeveloperComputerMinionGenPage.staticSearchStrListDeveloperComputerMinion(siteRequest_, (String)o);
    case "resultCount":
      return DeveloperComputerMinionGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return DeveloperComputerMinionGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriDeveloperComputerMinion":
      return DeveloperComputerMinionGenPage.staticSearchStrPageUriDeveloperComputerMinion(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqDeveloperComputerMinionGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqDeveloperComputerMinionGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listDeveloperComputerMinion":
      return DeveloperComputerMinionGenPage.staticSearchFqListDeveloperComputerMinion(siteRequest_, o);
    case "resultCount":
      return DeveloperComputerMinionGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "solrId":
      return DeveloperComputerMinionGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriDeveloperComputerMinion":
      return DeveloperComputerMinionGenPage.staticSearchFqPageUriDeveloperComputerMinion(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "DeveloperComputerMinionGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.developer.dcm.DeveloperComputerMinionGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListDeveloperComputerMinion_ = "searchListDeveloperComputerMinion_";
  public static final String SET_searchListDeveloperComputerMinion_ = "setSearchListDeveloperComputerMinion_";
  public static final String VAR_listDeveloperComputerMinion = "listDeveloperComputerMinion";
  public static final String SET_listDeveloperComputerMinion = "setListDeveloperComputerMinion";
  public static final String VAR_resultCount = "resultCount";
  public static final String SET_resultCount = "setResultCount";
  public static final String VAR_result = "result";
  public static final String SET_result = "setResult";
  public static final String VAR_solrId = "solrId";
  public static final String SET_solrId = "setSolrId";
  public static final String VAR_pageUriDeveloperComputerMinion = "pageUriDeveloperComputerMinion";
  public static final String SET_pageUriDeveloperComputerMinion = "setPageUriDeveloperComputerMinion";

  public static final String DISPLAY_NAME_searchListDeveloperComputerMinion_ = "";
  public static final String DISPLAY_NAME_listDeveloperComputerMinion = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriDeveloperComputerMinion = "";

  public static String displayNameForClass(String var) {
    return DeveloperComputerMinionGenPage.displayNameDeveloperComputerMinionGenPage(var);
  }
  public static String displayNameDeveloperComputerMinionGenPage(String var) {
    switch(var) {
    case VAR_searchListDeveloperComputerMinion_:
      return DISPLAY_NAME_searchListDeveloperComputerMinion_;
    case VAR_listDeveloperComputerMinion:
      return DISPLAY_NAME_listDeveloperComputerMinion;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriDeveloperComputerMinion:
      return DISPLAY_NAME_pageUriDeveloperComputerMinion;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }
}
