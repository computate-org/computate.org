package org.computate.site.model.switchtolinux;

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
import org.computate.site.model.switchtolinux.SwitchToLinux;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these SwitchToLinuxGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SwitchToLinuxGenPageGen into the class SwitchToLinuxGenPage. 
 * </li>
 * <h3>About the SwitchToLinuxGenPage class and it's generated class SwitchToLinuxGenPageGen&lt;PageLayout&gt;: </h3>extends SwitchToLinuxGenPageGen
 * <p>
 * This Java class extends a generated Java class SwitchToLinuxGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.switchtolinux.SwitchToLinuxGenPage">Find the class SwitchToLinuxGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SwitchToLinuxGenPageGen<PageLayout>
 * <p>This <code>class SwitchToLinuxGenPage extends SwitchToLinuxGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated SwitchToLinuxGenPageGen. 
 * The generated <code>class SwitchToLinuxGenPageGen extends PageLayout</code> which means that SwitchToLinuxGenPage extends SwitchToLinuxGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SwitchToLinuxGenPage class will inherit the helpful inherited class comments from the super class SwitchToLinuxGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the SwitchToLinuxGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class SwitchToLinuxGenPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.switchtolinux.SwitchToLinuxGenPage&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.switchtolinux in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.switchtolinux&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class SwitchToLinuxGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(SwitchToLinuxGenPage.class);

	//////////////////////////////
  // searchListSwitchToLinux_ //
	//////////////////////////////


  /**
   *  The entity searchListSwitchToLinux_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<SwitchToLinux> searchListSwitchToLinux_;

  /**
   * <br> The entity searchListSwitchToLinux_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.switchtolinux.SwitchToLinuxGenPage&fq=entiteVar_enUS_indexed_string:searchListSwitchToLinux_">Find the entity searchListSwitchToLinux_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListSwitchToLinux_(Wrap<SearchList<SwitchToLinux>> w);

  public SearchList<SwitchToLinux> getSearchListSwitchToLinux_() {
    return searchListSwitchToLinux_;
  }

  public void setSearchListSwitchToLinux_(SearchList<SwitchToLinux> searchListSwitchToLinux_) {
    this.searchListSwitchToLinux_ = searchListSwitchToLinux_;
  }
  public static SearchList<SwitchToLinux> staticSetSearchListSwitchToLinux_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected SwitchToLinuxGenPage searchListSwitchToLinux_Init() {
    Wrap<SearchList<SwitchToLinux>> searchListSwitchToLinux_Wrap = new Wrap<SearchList<SwitchToLinux>>().var("searchListSwitchToLinux_");
    if(searchListSwitchToLinux_ == null) {
      _searchListSwitchToLinux_(searchListSwitchToLinux_Wrap);
      Optional.ofNullable(searchListSwitchToLinux_Wrap.getO()).ifPresent(o -> {
        setSearchListSwitchToLinux_(o);
      });
    }
    return (SwitchToLinuxGenPage)this;
  }

	///////////////////////
  // listSwitchToLinux //
	///////////////////////


  /**
   *  The entity listSwitchToLinux
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listSwitchToLinux = new JsonArray();

  /**
   * <br> The entity listSwitchToLinux
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.switchtolinux.SwitchToLinuxGenPage&fq=entiteVar_enUS_indexed_string:listSwitchToLinux">Find the entity listSwitchToLinux in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listSwitchToLinux(JsonArray l);

  public JsonArray getListSwitchToLinux() {
    return listSwitchToLinux;
  }

  public void setListSwitchToLinux(JsonArray listSwitchToLinux) {
    this.listSwitchToLinux = listSwitchToLinux;
  }
  @JsonIgnore
  public void setListSwitchToLinux(String o) {
    this.listSwitchToLinux = SwitchToLinuxGenPage.staticSetListSwitchToLinux(siteRequest_, o);
  }
  public static JsonArray staticSetListSwitchToLinux(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected SwitchToLinuxGenPage listSwitchToLinuxInit() {
    _listSwitchToLinux(listSwitchToLinux);
    return (SwitchToLinuxGenPage)this;
  }

  public static String staticSearchListSwitchToLinux(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListSwitchToLinux(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListSwitchToLinux(SiteRequest siteRequest_, String o) {
    return SwitchToLinuxGenPage.staticSearchListSwitchToLinux(siteRequest_, SwitchToLinuxGenPage.staticSetListSwitchToLinux(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.switchtolinux.SwitchToLinuxGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = SwitchToLinuxGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected SwitchToLinuxGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (SwitchToLinuxGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return SwitchToLinuxGenPage.staticSearchResultCount(siteRequest_, SwitchToLinuxGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected SwitchToLinux result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.switchtolinux.SwitchToLinuxGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<SwitchToLinux> w);

  public SwitchToLinux getResult() {
    return result;
  }

  public void setResult(SwitchToLinux result) {
    this.result = result;
  }
  public static SwitchToLinux staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected SwitchToLinuxGenPage resultInit() {
    Wrap<SwitchToLinux> resultWrap = new Wrap<SwitchToLinux>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (SwitchToLinuxGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.switchtolinux.SwitchToLinuxGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = SwitchToLinuxGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SwitchToLinuxGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (SwitchToLinuxGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return SwitchToLinuxGenPage.staticSearchSolrId(siteRequest_, SwitchToLinuxGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	//////////////////////////
  // pageUriSwitchToLinux //
	//////////////////////////


  /**
   *  The entity pageUriSwitchToLinux
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriSwitchToLinux;

  /**
   * <br> The entity pageUriSwitchToLinux
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.switchtolinux.SwitchToLinuxGenPage&fq=entiteVar_enUS_indexed_string:pageUriSwitchToLinux">Find the entity pageUriSwitchToLinux in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriSwitchToLinux(Wrap<String> c);

  public String getPageUriSwitchToLinux() {
    return pageUriSwitchToLinux;
  }
  public void setPageUriSwitchToLinux(String o) {
    this.pageUriSwitchToLinux = SwitchToLinuxGenPage.staticSetPageUriSwitchToLinux(siteRequest_, o);
  }
  public static String staticSetPageUriSwitchToLinux(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SwitchToLinuxGenPage pageUriSwitchToLinuxInit() {
    Wrap<String> pageUriSwitchToLinuxWrap = new Wrap<String>().var("pageUriSwitchToLinux");
    if(pageUriSwitchToLinux == null) {
      _pageUriSwitchToLinux(pageUriSwitchToLinuxWrap);
      Optional.ofNullable(pageUriSwitchToLinuxWrap.getO()).ifPresent(o -> {
        setPageUriSwitchToLinux(o);
      });
    }
    return (SwitchToLinuxGenPage)this;
  }

  public static String staticSearchPageUriSwitchToLinux(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriSwitchToLinux(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriSwitchToLinux(SiteRequest siteRequest_, String o) {
    return SwitchToLinuxGenPage.staticSearchPageUriSwitchToLinux(siteRequest_, SwitchToLinuxGenPage.staticSetPageUriSwitchToLinux(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<SwitchToLinuxGenPageGen<DEV>> promiseDeepSwitchToLinuxGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepSwitchToLinuxGenPage();
  }

  public Future<SwitchToLinuxGenPageGen<DEV>> promiseDeepSwitchToLinuxGenPage() {
    Promise<SwitchToLinuxGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseSwitchToLinuxGenPage(promise2);
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

  public Future<Void> promiseSwitchToLinuxGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListSwitchToLinux_Init();
        listSwitchToLinuxInit();
        resultCountInit();
        resultInit();
        solrIdInit();
        pageUriSwitchToLinuxInit();
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

  @Override public Future<? extends SwitchToLinuxGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepSwitchToLinuxGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestSwitchToLinuxGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestSwitchToLinuxGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainSwitchToLinuxGenPage(v);
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
  public Object obtainSwitchToLinuxGenPage(String var) {
    SwitchToLinuxGenPage oSwitchToLinuxGenPage = (SwitchToLinuxGenPage)this;
    switch(var) {
      case "searchListSwitchToLinux_":
        return oSwitchToLinuxGenPage.searchListSwitchToLinux_;
      case "listSwitchToLinux":
        return oSwitchToLinuxGenPage.listSwitchToLinux;
      case "resultCount":
        return oSwitchToLinuxGenPage.resultCount;
      case "result":
        return oSwitchToLinuxGenPage.result;
      case "solrId":
        return oSwitchToLinuxGenPage.solrId;
      case "pageUriSwitchToLinux":
        return oSwitchToLinuxGenPage.pageUriSwitchToLinux;
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
        o = relateSwitchToLinuxGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateSwitchToLinuxGenPage(String var, Object val) {
    SwitchToLinuxGenPage oSwitchToLinuxGenPage = (SwitchToLinuxGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, SwitchToLinuxGenPage o) {
    return staticSetSwitchToLinuxGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetSwitchToLinuxGenPage(String entityVar, SiteRequest siteRequest_, String v, SwitchToLinuxGenPage o) {
    switch(entityVar) {
    case "listSwitchToLinux":
      return SwitchToLinuxGenPage.staticSetListSwitchToLinux(siteRequest_, v);
    case "resultCount":
      return SwitchToLinuxGenPage.staticSetResultCount(siteRequest_, v);
    case "solrId":
      return SwitchToLinuxGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriSwitchToLinux":
      return SwitchToLinuxGenPage.staticSetPageUriSwitchToLinux(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchSwitchToLinuxGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchSwitchToLinuxGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listSwitchToLinux":
      return SwitchToLinuxGenPage.staticSearchListSwitchToLinux(siteRequest_, (JsonArray)o);
    case "resultCount":
      return SwitchToLinuxGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return SwitchToLinuxGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriSwitchToLinux":
      return SwitchToLinuxGenPage.staticSearchPageUriSwitchToLinux(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrSwitchToLinuxGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrSwitchToLinuxGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listSwitchToLinux":
      return SwitchToLinuxGenPage.staticSearchStrListSwitchToLinux(siteRequest_, (String)o);
    case "resultCount":
      return SwitchToLinuxGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "solrId":
      return SwitchToLinuxGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriSwitchToLinux":
      return SwitchToLinuxGenPage.staticSearchStrPageUriSwitchToLinux(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqSwitchToLinuxGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqSwitchToLinuxGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listSwitchToLinux":
      return SwitchToLinuxGenPage.staticSearchFqListSwitchToLinux(siteRequest_, o);
    case "resultCount":
      return SwitchToLinuxGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "solrId":
      return SwitchToLinuxGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriSwitchToLinux":
      return SwitchToLinuxGenPage.staticSearchFqPageUriSwitchToLinux(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "SwitchToLinuxGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.switchtolinux.SwitchToLinuxGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListSwitchToLinux_ = "searchListSwitchToLinux_";
  public static final String SET_searchListSwitchToLinux_ = "setSearchListSwitchToLinux_";
  public static final String VAR_listSwitchToLinux = "listSwitchToLinux";
  public static final String SET_listSwitchToLinux = "setListSwitchToLinux";
  public static final String VAR_resultCount = "resultCount";
  public static final String SET_resultCount = "setResultCount";
  public static final String VAR_result = "result";
  public static final String SET_result = "setResult";
  public static final String VAR_solrId = "solrId";
  public static final String SET_solrId = "setSolrId";
  public static final String VAR_pageUriSwitchToLinux = "pageUriSwitchToLinux";
  public static final String SET_pageUriSwitchToLinux = "setPageUriSwitchToLinux";

  public static final String DISPLAY_NAME_searchListSwitchToLinux_ = "";
  public static final String DISPLAY_NAME_listSwitchToLinux = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriSwitchToLinux = "";

  public static String displayNameForClass(String var) {
    return SwitchToLinuxGenPage.displayNameSwitchToLinuxGenPage(var);
  }
  public static String displayNameSwitchToLinuxGenPage(String var) {
    switch(var) {
    case VAR_searchListSwitchToLinux_:
      return DISPLAY_NAME_searchListSwitchToLinux_;
    case VAR_listSwitchToLinux:
      return DISPLAY_NAME_listSwitchToLinux;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriSwitchToLinux:
      return DISPLAY_NAME_pageUriSwitchToLinux;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }
}
