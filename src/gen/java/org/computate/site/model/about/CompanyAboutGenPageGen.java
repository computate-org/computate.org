package org.computate.site.model.about;

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
import org.computate.site.model.about.CompanyAbout;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyAboutGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyAboutGenPageGen into the class CompanyAboutGenPage. 
 * </li>
 * <h3>About the CompanyAboutGenPage class and it's generated class CompanyAboutGenPageGen&lt;PageLayout&gt;: </h3>extends CompanyAboutGenPageGen
 * <p>
 * This Java class extends a generated Java class CompanyAboutGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAboutGenPage">Find the class CompanyAboutGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyAboutGenPageGen<PageLayout>
 * <p>This <code>class CompanyAboutGenPage extends CompanyAboutGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated CompanyAboutGenPageGen. 
 * The generated <code>class CompanyAboutGenPageGen extends PageLayout</code> which means that CompanyAboutGenPage extends CompanyAboutGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyAboutGenPage class will inherit the helpful inherited class comments from the super class CompanyAboutGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyAboutGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class CompanyAboutGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAboutGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.about in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.about&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyAboutGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyAboutGenPage.class);

	/////////////////////////////
	// searchListCompanyAbout_ //
	/////////////////////////////


	/**	 The entity searchListCompanyAbout_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<CompanyAbout> searchListCompanyAbout_;

	/**	<br> The entity searchListCompanyAbout_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAboutGenPage&fq=entiteVar_enUS_indexed_string:searchListCompanyAbout_">Find the entity searchListCompanyAbout_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListCompanyAbout_(Wrap<SearchList<CompanyAbout>> w);

	public SearchList<CompanyAbout> getSearchListCompanyAbout_() {
		return searchListCompanyAbout_;
	}

	public void setSearchListCompanyAbout_(SearchList<CompanyAbout> searchListCompanyAbout_) {
		this.searchListCompanyAbout_ = searchListCompanyAbout_;
	}
	public static SearchList<CompanyAbout> staticSetSearchListCompanyAbout_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyAboutGenPage searchListCompanyAbout_Init() {
		Wrap<SearchList<CompanyAbout>> searchListCompanyAbout_Wrap = new Wrap<SearchList<CompanyAbout>>().var("searchListCompanyAbout_");
		if(searchListCompanyAbout_ == null) {
			_searchListCompanyAbout_(searchListCompanyAbout_Wrap);
			Optional.ofNullable(searchListCompanyAbout_Wrap.getO()).ifPresent(o -> {
				setSearchListCompanyAbout_(o);
			});
		}
		return (CompanyAboutGenPage)this;
	}

	//////////////////////
	// listCompanyAbout //
	//////////////////////


	/**	 The entity listCompanyAbout
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listCompanyAbout = new JsonArray();

	/**	<br> The entity listCompanyAbout
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAboutGenPage&fq=entiteVar_enUS_indexed_string:listCompanyAbout">Find the entity listCompanyAbout in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listCompanyAbout(JsonArray l);

	public JsonArray getListCompanyAbout() {
		return listCompanyAbout;
	}

	public void setListCompanyAbout(JsonArray listCompanyAbout) {
		this.listCompanyAbout = listCompanyAbout;
	}
	@JsonIgnore
	public void setListCompanyAbout(String o) {
		this.listCompanyAbout = CompanyAboutGenPage.staticSetListCompanyAbout(siteRequest_, o);
	}
	public static JsonArray staticSetListCompanyAbout(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected CompanyAboutGenPage listCompanyAboutInit() {
		_listCompanyAbout(listCompanyAbout);
		return (CompanyAboutGenPage)this;
	}

	public static String staticSearchListCompanyAbout(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListCompanyAbout(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListCompanyAbout(SiteRequest siteRequest_, String o) {
		return CompanyAboutGenPage.staticSearchListCompanyAbout(siteRequest_, CompanyAboutGenPage.staticSetListCompanyAbout(siteRequest_, o)).toString();
	}

	/////////////////
	// resultCount //
	/////////////////


	/**	 The entity resultCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer resultCount;

	/**	<br> The entity resultCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAboutGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = CompanyAboutGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyAboutGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (CompanyAboutGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return CompanyAboutGenPage.staticSearchResultCount(siteRequest_, CompanyAboutGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CompanyAbout result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAboutGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<CompanyAbout> w);

	public CompanyAbout getResult() {
		return result;
	}

	public void setResult(CompanyAbout result) {
		this.result = result;
	}
	public static CompanyAbout staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyAboutGenPage resultInit() {
		Wrap<CompanyAbout> resultWrap = new Wrap<CompanyAbout>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (CompanyAboutGenPage)this;
	}

	////////////
	// solrId //
	////////////


	/**	 The entity solrId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String solrId;

	/**	<br> The entity solrId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAboutGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = CompanyAboutGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyAboutGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (CompanyAboutGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return CompanyAboutGenPage.staticSearchSolrId(siteRequest_, CompanyAboutGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	/////////////////////////
	// pageUriCompanyAbout //
	/////////////////////////


	/**	 The entity pageUriCompanyAbout
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriCompanyAbout;

	/**	<br> The entity pageUriCompanyAbout
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.about.CompanyAboutGenPage&fq=entiteVar_enUS_indexed_string:pageUriCompanyAbout">Find the entity pageUriCompanyAbout in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriCompanyAbout(Wrap<String> c);

	public String getPageUriCompanyAbout() {
		return pageUriCompanyAbout;
	}
	public void setPageUriCompanyAbout(String o) {
		this.pageUriCompanyAbout = CompanyAboutGenPage.staticSetPageUriCompanyAbout(siteRequest_, o);
	}
	public static String staticSetPageUriCompanyAbout(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyAboutGenPage pageUriCompanyAboutInit() {
		Wrap<String> pageUriCompanyAboutWrap = new Wrap<String>().var("pageUriCompanyAbout");
		if(pageUriCompanyAbout == null) {
			_pageUriCompanyAbout(pageUriCompanyAboutWrap);
			Optional.ofNullable(pageUriCompanyAboutWrap.getO()).ifPresent(o -> {
				setPageUriCompanyAbout(o);
			});
		}
		return (CompanyAboutGenPage)this;
	}

	public static String staticSearchPageUriCompanyAbout(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriCompanyAbout(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriCompanyAbout(SiteRequest siteRequest_, String o) {
		return CompanyAboutGenPage.staticSearchPageUriCompanyAbout(siteRequest_, CompanyAboutGenPage.staticSetPageUriCompanyAbout(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<CompanyAboutGenPageGen<DEV>> promiseDeepCompanyAboutGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyAboutGenPage();
	}

	public Future<CompanyAboutGenPageGen<DEV>> promiseDeepCompanyAboutGenPage() {
		Promise<CompanyAboutGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyAboutGenPage(promise2);
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

	public Future<Void> promiseCompanyAboutGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListCompanyAbout_Init();
				listCompanyAboutInit();
				resultCountInit();
				resultInit();
				solrIdInit();
				pageUriCompanyAboutInit();
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

	@Override public Future<? extends CompanyAboutGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyAboutGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyAboutGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyAboutGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyAboutGenPage(v);
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
	public Object obtainCompanyAboutGenPage(String var) {
		CompanyAboutGenPage oCompanyAboutGenPage = (CompanyAboutGenPage)this;
		switch(var) {
			case "searchListCompanyAbout_":
				return oCompanyAboutGenPage.searchListCompanyAbout_;
			case "listCompanyAbout":
				return oCompanyAboutGenPage.listCompanyAbout;
			case "resultCount":
				return oCompanyAboutGenPage.resultCount;
			case "result":
				return oCompanyAboutGenPage.result;
			case "solrId":
				return oCompanyAboutGenPage.solrId;
			case "pageUriCompanyAbout":
				return oCompanyAboutGenPage.pageUriCompanyAbout;
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
				o = relateCompanyAboutGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyAboutGenPage(String var, Object val) {
		CompanyAboutGenPage oCompanyAboutGenPage = (CompanyAboutGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, CompanyAboutGenPage o) {
		return staticSetCompanyAboutGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetCompanyAboutGenPage(String entityVar, SiteRequest siteRequest_, String v, CompanyAboutGenPage o) {
		switch(entityVar) {
		case "listCompanyAbout":
			return CompanyAboutGenPage.staticSetListCompanyAbout(siteRequest_, v);
		case "resultCount":
			return CompanyAboutGenPage.staticSetResultCount(siteRequest_, v);
		case "solrId":
			return CompanyAboutGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriCompanyAbout":
			return CompanyAboutGenPage.staticSetPageUriCompanyAbout(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyAboutGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyAboutGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyAbout":
			return CompanyAboutGenPage.staticSearchListCompanyAbout(siteRequest_, (JsonArray)o);
		case "resultCount":
			return CompanyAboutGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return CompanyAboutGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriCompanyAbout":
			return CompanyAboutGenPage.staticSearchPageUriCompanyAbout(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyAboutGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyAboutGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyAbout":
			return CompanyAboutGenPage.staticSearchStrListCompanyAbout(siteRequest_, (String)o);
		case "resultCount":
			return CompanyAboutGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return CompanyAboutGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriCompanyAbout":
			return CompanyAboutGenPage.staticSearchStrPageUriCompanyAbout(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyAboutGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyAboutGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyAbout":
			return CompanyAboutGenPage.staticSearchFqListCompanyAbout(siteRequest_, o);
		case "resultCount":
			return CompanyAboutGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "solrId":
			return CompanyAboutGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriCompanyAbout":
			return CompanyAboutGenPage.staticSearchFqPageUriCompanyAbout(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyAboutGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.about.CompanyAboutGenPage";
	public static final String VAR_searchListCompanyAbout_ = "searchListCompanyAbout_";
	public static final String VAR_listCompanyAbout = "listCompanyAbout";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriCompanyAbout = "pageUriCompanyAbout";

	public static final String DISPLAY_NAME_searchListCompanyAbout_ = "";
	public static final String DISPLAY_NAME_listCompanyAbout = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriCompanyAbout = "";

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
		return CompanyAboutGenPage.displayNameCompanyAboutGenPage(var);
	}
	public static String displayNameCompanyAboutGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyAbout_:
			return DISPLAY_NAME_searchListCompanyAbout_;
		case VAR_listCompanyAbout:
			return DISPLAY_NAME_listCompanyAbout;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriCompanyAbout:
			return DISPLAY_NAME_pageUriCompanyAbout;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionCompanyAboutGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameCompanyAboutGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyAbout_:
			return "SearchList";
		case VAR_listCompanyAbout:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "CompanyAbout";
		case VAR_solrId:
			return "String";
		case VAR_pageUriCompanyAbout:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnCompanyAboutGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowCompanyAboutGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellCompanyAboutGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinCompanyAboutGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxCompanyAboutGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxCompanyAboutGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minCompanyAboutGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
