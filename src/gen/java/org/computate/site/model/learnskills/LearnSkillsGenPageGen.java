package org.computate.site.model.learnskills;

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
import org.computate.site.model.learnskills.LearnSkills;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these LearnSkillsGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class LearnSkillsGenPageGen into the class LearnSkillsGenPage. 
 * </li>
 * <h3>About the LearnSkillsGenPage class and it's generated class LearnSkillsGenPageGen&lt;PageLayout&gt;: </h3>extends LearnSkillsGenPageGen
 * <p>
 * This Java class extends a generated Java class LearnSkillsGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.learnskills.LearnSkillsGenPage">Find the class LearnSkillsGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends LearnSkillsGenPageGen<PageLayout>
 * <p>This <code>class LearnSkillsGenPage extends LearnSkillsGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated LearnSkillsGenPageGen. 
 * The generated <code>class LearnSkillsGenPageGen extends PageLayout</code> which means that LearnSkillsGenPage extends LearnSkillsGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the LearnSkillsGenPage class will inherit the helpful inherited class comments from the super class LearnSkillsGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the LearnSkillsGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class LearnSkillsGenPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.learnskills.LearnSkillsGenPage&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.learnskills in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.learnskills&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class LearnSkillsGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(LearnSkillsGenPage.class);

	////////////////////////////
  // searchListLearnSkills_ //
	////////////////////////////


  /**
   *  The entity searchListLearnSkills_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<LearnSkills> searchListLearnSkills_;

  /**
   * <br> The entity searchListLearnSkills_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.learnskills.LearnSkillsGenPage&fq=entiteVar_enUS_indexed_string:searchListLearnSkills_">Find the entity searchListLearnSkills_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListLearnSkills_(Wrap<SearchList<LearnSkills>> w);

  public SearchList<LearnSkills> getSearchListLearnSkills_() {
    return searchListLearnSkills_;
  }

  public void setSearchListLearnSkills_(SearchList<LearnSkills> searchListLearnSkills_) {
    this.searchListLearnSkills_ = searchListLearnSkills_;
  }
  public static SearchList<LearnSkills> staticSetSearchListLearnSkills_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected LearnSkillsGenPage searchListLearnSkills_Init() {
    Wrap<SearchList<LearnSkills>> searchListLearnSkills_Wrap = new Wrap<SearchList<LearnSkills>>().var("searchListLearnSkills_");
    if(searchListLearnSkills_ == null) {
      _searchListLearnSkills_(searchListLearnSkills_Wrap);
      Optional.ofNullable(searchListLearnSkills_Wrap.getO()).ifPresent(o -> {
        setSearchListLearnSkills_(o);
      });
    }
    return (LearnSkillsGenPage)this;
  }

	/////////////////////
  // listLearnSkills //
	/////////////////////


  /**
   *  The entity listLearnSkills
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listLearnSkills = new JsonArray();

  /**
   * <br> The entity listLearnSkills
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.learnskills.LearnSkillsGenPage&fq=entiteVar_enUS_indexed_string:listLearnSkills">Find the entity listLearnSkills in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listLearnSkills(JsonArray l);

  public JsonArray getListLearnSkills() {
    return listLearnSkills;
  }

  public void setListLearnSkills(JsonArray listLearnSkills) {
    this.listLearnSkills = listLearnSkills;
  }
  @JsonIgnore
  public void setListLearnSkills(String o) {
    this.listLearnSkills = LearnSkillsGenPage.staticSetListLearnSkills(siteRequest_, o);
  }
  public static JsonArray staticSetListLearnSkills(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected LearnSkillsGenPage listLearnSkillsInit() {
    _listLearnSkills(listLearnSkills);
    return (LearnSkillsGenPage)this;
  }

  public static String staticSearchListLearnSkills(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListLearnSkills(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListLearnSkills(SiteRequest siteRequest_, String o) {
    return LearnSkillsGenPage.staticSearchListLearnSkills(siteRequest_, LearnSkillsGenPage.staticSetListLearnSkills(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.learnskills.LearnSkillsGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = LearnSkillsGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected LearnSkillsGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (LearnSkillsGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return LearnSkillsGenPage.staticSearchResultCount(siteRequest_, LearnSkillsGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected LearnSkills result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.learnskills.LearnSkillsGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<LearnSkills> w);

  public LearnSkills getResult() {
    return result;
  }

  public void setResult(LearnSkills result) {
    this.result = result;
  }
  public static LearnSkills staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected LearnSkillsGenPage resultInit() {
    Wrap<LearnSkills> resultWrap = new Wrap<LearnSkills>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (LearnSkillsGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.learnskills.LearnSkillsGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = LearnSkillsGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected LearnSkillsGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (LearnSkillsGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return LearnSkillsGenPage.staticSearchSolrId(siteRequest_, LearnSkillsGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	////////////////////////
  // pageUriLearnSkills //
	////////////////////////


  /**
   *  The entity pageUriLearnSkills
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriLearnSkills;

  /**
   * <br> The entity pageUriLearnSkills
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.learnskills.LearnSkillsGenPage&fq=entiteVar_enUS_indexed_string:pageUriLearnSkills">Find the entity pageUriLearnSkills in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriLearnSkills(Wrap<String> c);

  public String getPageUriLearnSkills() {
    return pageUriLearnSkills;
  }
  public void setPageUriLearnSkills(String o) {
    this.pageUriLearnSkills = LearnSkillsGenPage.staticSetPageUriLearnSkills(siteRequest_, o);
  }
  public static String staticSetPageUriLearnSkills(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected LearnSkillsGenPage pageUriLearnSkillsInit() {
    Wrap<String> pageUriLearnSkillsWrap = new Wrap<String>().var("pageUriLearnSkills");
    if(pageUriLearnSkills == null) {
      _pageUriLearnSkills(pageUriLearnSkillsWrap);
      Optional.ofNullable(pageUriLearnSkillsWrap.getO()).ifPresent(o -> {
        setPageUriLearnSkills(o);
      });
    }
    return (LearnSkillsGenPage)this;
  }

  public static String staticSearchPageUriLearnSkills(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriLearnSkills(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriLearnSkills(SiteRequest siteRequest_, String o) {
    return LearnSkillsGenPage.staticSearchPageUriLearnSkills(siteRequest_, LearnSkillsGenPage.staticSetPageUriLearnSkills(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<LearnSkillsGenPageGen<DEV>> promiseDeepLearnSkillsGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepLearnSkillsGenPage();
  }

  public Future<LearnSkillsGenPageGen<DEV>> promiseDeepLearnSkillsGenPage() {
    Promise<LearnSkillsGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseLearnSkillsGenPage(promise2);
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

  public Future<Void> promiseLearnSkillsGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListLearnSkills_Init();
        listLearnSkillsInit();
        resultCountInit();
        resultInit();
        solrIdInit();
        pageUriLearnSkillsInit();
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

  @Override public Future<? extends LearnSkillsGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepLearnSkillsGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestLearnSkillsGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestLearnSkillsGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainLearnSkillsGenPage(v);
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
  public Object obtainLearnSkillsGenPage(String var) {
    LearnSkillsGenPage oLearnSkillsGenPage = (LearnSkillsGenPage)this;
    switch(var) {
      case "searchListLearnSkills_":
        return oLearnSkillsGenPage.searchListLearnSkills_;
      case "listLearnSkills":
        return oLearnSkillsGenPage.listLearnSkills;
      case "resultCount":
        return oLearnSkillsGenPage.resultCount;
      case "result":
        return oLearnSkillsGenPage.result;
      case "solrId":
        return oLearnSkillsGenPage.solrId;
      case "pageUriLearnSkills":
        return oLearnSkillsGenPage.pageUriLearnSkills;
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
        o = relateLearnSkillsGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateLearnSkillsGenPage(String var, Object val) {
    LearnSkillsGenPage oLearnSkillsGenPage = (LearnSkillsGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, LearnSkillsGenPage o) {
    return staticSetLearnSkillsGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetLearnSkillsGenPage(String entityVar, SiteRequest siteRequest_, String v, LearnSkillsGenPage o) {
    switch(entityVar) {
    case "listLearnSkills":
      return LearnSkillsGenPage.staticSetListLearnSkills(siteRequest_, v);
    case "resultCount":
      return LearnSkillsGenPage.staticSetResultCount(siteRequest_, v);
    case "solrId":
      return LearnSkillsGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriLearnSkills":
      return LearnSkillsGenPage.staticSetPageUriLearnSkills(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchLearnSkillsGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchLearnSkillsGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listLearnSkills":
      return LearnSkillsGenPage.staticSearchListLearnSkills(siteRequest_, (JsonArray)o);
    case "resultCount":
      return LearnSkillsGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return LearnSkillsGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriLearnSkills":
      return LearnSkillsGenPage.staticSearchPageUriLearnSkills(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrLearnSkillsGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrLearnSkillsGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listLearnSkills":
      return LearnSkillsGenPage.staticSearchStrListLearnSkills(siteRequest_, (String)o);
    case "resultCount":
      return LearnSkillsGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return LearnSkillsGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriLearnSkills":
      return LearnSkillsGenPage.staticSearchStrPageUriLearnSkills(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqLearnSkillsGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqLearnSkillsGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listLearnSkills":
      return LearnSkillsGenPage.staticSearchFqListLearnSkills(siteRequest_, o);
    case "resultCount":
      return LearnSkillsGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "solrId":
      return LearnSkillsGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriLearnSkills":
      return LearnSkillsGenPage.staticSearchFqPageUriLearnSkills(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "LearnSkillsGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.learnskills.LearnSkillsGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListLearnSkills_ = "searchListLearnSkills_";
  public static final String SET_searchListLearnSkills_ = "setSearchListLearnSkills_";
  public static final String VAR_listLearnSkills = "listLearnSkills";
  public static final String SET_listLearnSkills = "setListLearnSkills";
  public static final String VAR_resultCount = "resultCount";
  public static final String SET_resultCount = "setResultCount";
  public static final String VAR_result = "result";
  public static final String SET_result = "setResult";
  public static final String VAR_solrId = "solrId";
  public static final String SET_solrId = "setSolrId";
  public static final String VAR_pageUriLearnSkills = "pageUriLearnSkills";
  public static final String SET_pageUriLearnSkills = "setPageUriLearnSkills";

  public static final String DISPLAY_NAME_searchListLearnSkills_ = "";
  public static final String DISPLAY_NAME_listLearnSkills = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriLearnSkills = "";

  public static String displayNameForClass(String var) {
    return LearnSkillsGenPage.displayNameLearnSkillsGenPage(var);
  }
  public static String displayNameLearnSkillsGenPage(String var) {
    switch(var) {
    case VAR_searchListLearnSkills_:
      return DISPLAY_NAME_searchListLearnSkills_;
    case VAR_listLearnSkills:
      return DISPLAY_NAME_listLearnSkills;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriLearnSkills:
      return DISPLAY_NAME_pageUriLearnSkills;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }
}
