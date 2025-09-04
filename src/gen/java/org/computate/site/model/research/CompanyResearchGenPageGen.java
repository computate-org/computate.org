package org.computate.site.model.research;

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
import org.computate.site.model.research.CompanyResearch;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyResearchGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyResearchGenPageGen into the class CompanyResearchGenPage. 
 * </li>
 * <h3>About the CompanyResearchGenPage class and it's generated class CompanyResearchGenPageGen&lt;PageLayout&gt;: </h3>extends CompanyResearchGenPageGen
 * <p>
 * This Java class extends a generated Java class CompanyResearchGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage">Find the class CompanyResearchGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyResearchGenPageGen<PageLayout>
 * <p>This <code>class CompanyResearchGenPage extends CompanyResearchGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated CompanyResearchGenPageGen. 
 * The generated <code>class CompanyResearchGenPageGen extends PageLayout</code> which means that CompanyResearchGenPage extends CompanyResearchGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyResearchGenPage class will inherit the helpful inherited class comments from the super class CompanyResearchGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyResearchGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class CompanyResearchGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.research in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.research&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyResearchGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyResearchGenPage.class);

	////////////////////////////////
	// searchListCompanyResearch_ //
	////////////////////////////////


	/**	 The entity searchListCompanyResearch_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<CompanyResearch> searchListCompanyResearch_;

	/**	<br> The entity searchListCompanyResearch_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:searchListCompanyResearch_">Find the entity searchListCompanyResearch_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListCompanyResearch_(Wrap<SearchList<CompanyResearch>> w);

	public SearchList<CompanyResearch> getSearchListCompanyResearch_() {
		return searchListCompanyResearch_;
	}

	public void setSearchListCompanyResearch_(SearchList<CompanyResearch> searchListCompanyResearch_) {
		this.searchListCompanyResearch_ = searchListCompanyResearch_;
	}
	public static SearchList<CompanyResearch> staticSetSearchListCompanyResearch_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyResearchGenPage searchListCompanyResearch_Init() {
		Wrap<SearchList<CompanyResearch>> searchListCompanyResearch_Wrap = new Wrap<SearchList<CompanyResearch>>().var("searchListCompanyResearch_");
		if(searchListCompanyResearch_ == null) {
			_searchListCompanyResearch_(searchListCompanyResearch_Wrap);
			Optional.ofNullable(searchListCompanyResearch_Wrap.getO()).ifPresent(o -> {
				setSearchListCompanyResearch_(o);
			});
		}
		return (CompanyResearchGenPage)this;
	}

	/////////////////////////
	// listCompanyResearch //
	/////////////////////////


	/**	 The entity listCompanyResearch
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listCompanyResearch = new JsonArray();

	/**	<br> The entity listCompanyResearch
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:listCompanyResearch">Find the entity listCompanyResearch in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listCompanyResearch(JsonArray l);

	public JsonArray getListCompanyResearch() {
		return listCompanyResearch;
	}

	public void setListCompanyResearch(JsonArray listCompanyResearch) {
		this.listCompanyResearch = listCompanyResearch;
	}
	@JsonIgnore
	public void setListCompanyResearch(String o) {
		this.listCompanyResearch = CompanyResearchGenPage.staticSetListCompanyResearch(siteRequest_, o);
	}
	public static JsonArray staticSetListCompanyResearch(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected CompanyResearchGenPage listCompanyResearchInit() {
		_listCompanyResearch(listCompanyResearch);
		return (CompanyResearchGenPage)this;
	}

	public static String staticSearchListCompanyResearch(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListCompanyResearch(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListCompanyResearch(SiteRequest siteRequest_, String o) {
		return CompanyResearchGenPage.staticSearchListCompanyResearch(siteRequest_, CompanyResearchGenPage.staticSetListCompanyResearch(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = CompanyResearchGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyResearchGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (CompanyResearchGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return CompanyResearchGenPage.staticSearchResultCount(siteRequest_, CompanyResearchGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CompanyResearch result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<CompanyResearch> w);

	public CompanyResearch getResult() {
		return result;
	}

	public void setResult(CompanyResearch result) {
		this.result = result;
	}
	public static CompanyResearch staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyResearchGenPage resultInit() {
		Wrap<CompanyResearch> resultWrap = new Wrap<CompanyResearch>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (CompanyResearchGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = CompanyResearchGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyResearchGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (CompanyResearchGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return CompanyResearchGenPage.staticSearchSolrId(siteRequest_, CompanyResearchGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	////////////////////////////
	// pageUriCompanyResearch //
	////////////////////////////


	/**	 The entity pageUriCompanyResearch
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriCompanyResearch;

	/**	<br> The entity pageUriCompanyResearch
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:pageUriCompanyResearch">Find the entity pageUriCompanyResearch in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriCompanyResearch(Wrap<String> c);

	public String getPageUriCompanyResearch() {
		return pageUriCompanyResearch;
	}
	public void setPageUriCompanyResearch(String o) {
		this.pageUriCompanyResearch = CompanyResearchGenPage.staticSetPageUriCompanyResearch(siteRequest_, o);
	}
	public static String staticSetPageUriCompanyResearch(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyResearchGenPage pageUriCompanyResearchInit() {
		Wrap<String> pageUriCompanyResearchWrap = new Wrap<String>().var("pageUriCompanyResearch");
		if(pageUriCompanyResearch == null) {
			_pageUriCompanyResearch(pageUriCompanyResearchWrap);
			Optional.ofNullable(pageUriCompanyResearchWrap.getO()).ifPresent(o -> {
				setPageUriCompanyResearch(o);
			});
		}
		return (CompanyResearchGenPage)this;
	}

	public static String staticSearchPageUriCompanyResearch(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriCompanyResearch(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriCompanyResearch(SiteRequest siteRequest_, String o) {
		return CompanyResearchGenPage.staticSearchPageUriCompanyResearch(siteRequest_, CompanyResearchGenPage.staticSetPageUriCompanyResearch(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<CompanyResearchGenPageGen<DEV>> promiseDeepCompanyResearchGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyResearchGenPage();
	}

	public Future<CompanyResearchGenPageGen<DEV>> promiseDeepCompanyResearchGenPage() {
		Promise<CompanyResearchGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyResearchGenPage(promise2);
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

	public Future<Void> promiseCompanyResearchGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListCompanyResearch_Init();
				listCompanyResearchInit();
				resultCountInit();
				resultInit();
				solrIdInit();
				pageUriCompanyResearchInit();
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

	@Override public Future<? extends CompanyResearchGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyResearchGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyResearchGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyResearchGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyResearchGenPage(v);
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
	public Object obtainCompanyResearchGenPage(String var) {
		CompanyResearchGenPage oCompanyResearchGenPage = (CompanyResearchGenPage)this;
		switch(var) {
			case "searchListCompanyResearch_":
				return oCompanyResearchGenPage.searchListCompanyResearch_;
			case "listCompanyResearch":
				return oCompanyResearchGenPage.listCompanyResearch;
			case "resultCount":
				return oCompanyResearchGenPage.resultCount;
			case "result":
				return oCompanyResearchGenPage.result;
			case "solrId":
				return oCompanyResearchGenPage.solrId;
			case "pageUriCompanyResearch":
				return oCompanyResearchGenPage.pageUriCompanyResearch;
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
				o = relateCompanyResearchGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyResearchGenPage(String var, Object val) {
		CompanyResearchGenPage oCompanyResearchGenPage = (CompanyResearchGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, CompanyResearchGenPage o) {
		return staticSetCompanyResearchGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetCompanyResearchGenPage(String entityVar, SiteRequest siteRequest_, String v, CompanyResearchGenPage o) {
		switch(entityVar) {
		case "listCompanyResearch":
			return CompanyResearchGenPage.staticSetListCompanyResearch(siteRequest_, v);
		case "resultCount":
			return CompanyResearchGenPage.staticSetResultCount(siteRequest_, v);
		case "solrId":
			return CompanyResearchGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriCompanyResearch":
			return CompanyResearchGenPage.staticSetPageUriCompanyResearch(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyResearchGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyResearchGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyResearch":
			return CompanyResearchGenPage.staticSearchListCompanyResearch(siteRequest_, (JsonArray)o);
		case "resultCount":
			return CompanyResearchGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return CompanyResearchGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriCompanyResearch":
			return CompanyResearchGenPage.staticSearchPageUriCompanyResearch(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyResearchGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyResearchGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyResearch":
			return CompanyResearchGenPage.staticSearchStrListCompanyResearch(siteRequest_, (String)o);
		case "resultCount":
			return CompanyResearchGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return CompanyResearchGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriCompanyResearch":
			return CompanyResearchGenPage.staticSearchStrPageUriCompanyResearch(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyResearchGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyResearchGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyResearch":
			return CompanyResearchGenPage.staticSearchFqListCompanyResearch(siteRequest_, o);
		case "resultCount":
			return CompanyResearchGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "solrId":
			return CompanyResearchGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriCompanyResearch":
			return CompanyResearchGenPage.staticSearchFqPageUriCompanyResearch(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyResearchGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.research.CompanyResearchGenPage";
	public static final String CLASS_AUTH_RESOURCE = "";
	public static final String VAR_searchListCompanyResearch_ = "searchListCompanyResearch_";
	public static final String VAR_listCompanyResearch = "listCompanyResearch";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriCompanyResearch = "pageUriCompanyResearch";

	public static final String DISPLAY_NAME_searchListCompanyResearch_ = "";
	public static final String DISPLAY_NAME_listCompanyResearch = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriCompanyResearch = "";

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
		return CompanyResearchGenPage.displayNameCompanyResearchGenPage(var);
	}
	public static String displayNameCompanyResearchGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyResearch_:
			return DISPLAY_NAME_searchListCompanyResearch_;
		case VAR_listCompanyResearch:
			return DISPLAY_NAME_listCompanyResearch;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriCompanyResearch:
			return DISPLAY_NAME_pageUriCompanyResearch;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionCompanyResearchGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameCompanyResearchGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyResearch_:
			return "SearchList";
		case VAR_listCompanyResearch:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "CompanyResearch";
		case VAR_solrId:
			return "String";
		case VAR_pageUriCompanyResearch:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnCompanyResearchGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowCompanyResearchGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellCompanyResearchGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinCompanyResearchGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxCompanyResearchGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxCompanyResearchGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minCompanyResearchGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
