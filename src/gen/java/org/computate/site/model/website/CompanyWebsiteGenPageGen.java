package org.computate.site.model.website;

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
import org.computate.site.model.website.CompanyWebsite;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyWebsiteGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyWebsiteGenPageGen into the class CompanyWebsiteGenPage. 
 * </li>
 * <h3>About the CompanyWebsiteGenPage class and it's generated class CompanyWebsiteGenPageGen&lt;PageLayout&gt;: </h3>extends CompanyWebsiteGenPageGen
 * <p>
 * This Java class extends a generated Java class CompanyWebsiteGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage">Find the class CompanyWebsiteGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyWebsiteGenPageGen<PageLayout>
 * <p>This <code>class CompanyWebsiteGenPage extends CompanyWebsiteGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated CompanyWebsiteGenPageGen. 
 * The generated <code>class CompanyWebsiteGenPageGen extends PageLayout</code> which means that CompanyWebsiteGenPage extends CompanyWebsiteGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyWebsiteGenPage class will inherit the helpful inherited class comments from the super class CompanyWebsiteGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyWebsiteGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class CompanyWebsiteGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.website in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.website&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyWebsiteGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyWebsiteGenPage.class);

	///////////////////////////////
	// searchListCompanyWebsite_ //
	///////////////////////////////


	/**	 The entity searchListCompanyWebsite_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<CompanyWebsite> searchListCompanyWebsite_;

	/**	<br> The entity searchListCompanyWebsite_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:searchListCompanyWebsite_">Find the entity searchListCompanyWebsite_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListCompanyWebsite_(Wrap<SearchList<CompanyWebsite>> w);

	public SearchList<CompanyWebsite> getSearchListCompanyWebsite_() {
		return searchListCompanyWebsite_;
	}

	public void setSearchListCompanyWebsite_(SearchList<CompanyWebsite> searchListCompanyWebsite_) {
		this.searchListCompanyWebsite_ = searchListCompanyWebsite_;
	}
	public static SearchList<CompanyWebsite> staticSetSearchListCompanyWebsite_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyWebsiteGenPage searchListCompanyWebsite_Init() {
		Wrap<SearchList<CompanyWebsite>> searchListCompanyWebsite_Wrap = new Wrap<SearchList<CompanyWebsite>>().var("searchListCompanyWebsite_");
		if(searchListCompanyWebsite_ == null) {
			_searchListCompanyWebsite_(searchListCompanyWebsite_Wrap);
			Optional.ofNullable(searchListCompanyWebsite_Wrap.getO()).ifPresent(o -> {
				setSearchListCompanyWebsite_(o);
			});
		}
		return (CompanyWebsiteGenPage)this;
	}

	////////////////////////
	// listCompanyWebsite //
	////////////////////////


	/**	 The entity listCompanyWebsite
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listCompanyWebsite = new JsonArray();

	/**	<br> The entity listCompanyWebsite
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:listCompanyWebsite">Find the entity listCompanyWebsite in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listCompanyWebsite(JsonArray l);

	public JsonArray getListCompanyWebsite() {
		return listCompanyWebsite;
	}

	public void setListCompanyWebsite(JsonArray listCompanyWebsite) {
		this.listCompanyWebsite = listCompanyWebsite;
	}
	@JsonIgnore
	public void setListCompanyWebsite(String o) {
		this.listCompanyWebsite = CompanyWebsiteGenPage.staticSetListCompanyWebsite(siteRequest_, o);
	}
	public static JsonArray staticSetListCompanyWebsite(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected CompanyWebsiteGenPage listCompanyWebsiteInit() {
		_listCompanyWebsite(listCompanyWebsite);
		return (CompanyWebsiteGenPage)this;
	}

	public static String staticSearchListCompanyWebsite(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListCompanyWebsite(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListCompanyWebsite(SiteRequest siteRequest_, String o) {
		return CompanyWebsiteGenPage.staticSearchListCompanyWebsite(siteRequest_, CompanyWebsiteGenPage.staticSetListCompanyWebsite(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = CompanyWebsiteGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyWebsiteGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (CompanyWebsiteGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return CompanyWebsiteGenPage.staticSearchResultCount(siteRequest_, CompanyWebsiteGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CompanyWebsite result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<CompanyWebsite> w);

	public CompanyWebsite getResult() {
		return result;
	}

	public void setResult(CompanyWebsite result) {
		this.result = result;
	}
	public static CompanyWebsite staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyWebsiteGenPage resultInit() {
		Wrap<CompanyWebsite> resultWrap = new Wrap<CompanyWebsite>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (CompanyWebsiteGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = CompanyWebsiteGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebsiteGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (CompanyWebsiteGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return CompanyWebsiteGenPage.staticSearchSolrId(siteRequest_, CompanyWebsiteGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	///////////////////////////
	// pageUriCompanyWebsite //
	///////////////////////////


	/**	 The entity pageUriCompanyWebsite
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriCompanyWebsite;

	/**	<br> The entity pageUriCompanyWebsite
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:pageUriCompanyWebsite">Find the entity pageUriCompanyWebsite in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriCompanyWebsite(Wrap<String> c);

	public String getPageUriCompanyWebsite() {
		return pageUriCompanyWebsite;
	}
	public void setPageUriCompanyWebsite(String o) {
		this.pageUriCompanyWebsite = CompanyWebsiteGenPage.staticSetPageUriCompanyWebsite(siteRequest_, o);
	}
	public static String staticSetPageUriCompanyWebsite(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebsiteGenPage pageUriCompanyWebsiteInit() {
		Wrap<String> pageUriCompanyWebsiteWrap = new Wrap<String>().var("pageUriCompanyWebsite");
		if(pageUriCompanyWebsite == null) {
			_pageUriCompanyWebsite(pageUriCompanyWebsiteWrap);
			Optional.ofNullable(pageUriCompanyWebsiteWrap.getO()).ifPresent(o -> {
				setPageUriCompanyWebsite(o);
			});
		}
		return (CompanyWebsiteGenPage)this;
	}

	public static String staticSearchPageUriCompanyWebsite(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriCompanyWebsite(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriCompanyWebsite(SiteRequest siteRequest_, String o) {
		return CompanyWebsiteGenPage.staticSearchPageUriCompanyWebsite(siteRequest_, CompanyWebsiteGenPage.staticSetPageUriCompanyWebsite(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<CompanyWebsiteGenPageGen<DEV>> promiseDeepCompanyWebsiteGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyWebsiteGenPage();
	}

	public Future<CompanyWebsiteGenPageGen<DEV>> promiseDeepCompanyWebsiteGenPage() {
		Promise<CompanyWebsiteGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyWebsiteGenPage(promise2);
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

	public Future<Void> promiseCompanyWebsiteGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListCompanyWebsite_Init();
				listCompanyWebsiteInit();
				resultCountInit();
				resultInit();
				solrIdInit();
				pageUriCompanyWebsiteInit();
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

	@Override public Future<? extends CompanyWebsiteGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyWebsiteGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyWebsiteGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyWebsiteGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyWebsiteGenPage(v);
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
	public Object obtainCompanyWebsiteGenPage(String var) {
		CompanyWebsiteGenPage oCompanyWebsiteGenPage = (CompanyWebsiteGenPage)this;
		switch(var) {
			case "searchListCompanyWebsite_":
				return oCompanyWebsiteGenPage.searchListCompanyWebsite_;
			case "listCompanyWebsite":
				return oCompanyWebsiteGenPage.listCompanyWebsite;
			case "resultCount":
				return oCompanyWebsiteGenPage.resultCount;
			case "result":
				return oCompanyWebsiteGenPage.result;
			case "solrId":
				return oCompanyWebsiteGenPage.solrId;
			case "pageUriCompanyWebsite":
				return oCompanyWebsiteGenPage.pageUriCompanyWebsite;
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
				o = relateCompanyWebsiteGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyWebsiteGenPage(String var, Object val) {
		CompanyWebsiteGenPage oCompanyWebsiteGenPage = (CompanyWebsiteGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, CompanyWebsiteGenPage o) {
		return staticSetCompanyWebsiteGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetCompanyWebsiteGenPage(String entityVar, SiteRequest siteRequest_, String v, CompanyWebsiteGenPage o) {
		switch(entityVar) {
		case "listCompanyWebsite":
			return CompanyWebsiteGenPage.staticSetListCompanyWebsite(siteRequest_, v);
		case "resultCount":
			return CompanyWebsiteGenPage.staticSetResultCount(siteRequest_, v);
		case "solrId":
			return CompanyWebsiteGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriCompanyWebsite":
			return CompanyWebsiteGenPage.staticSetPageUriCompanyWebsite(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyWebsiteGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyWebsiteGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchListCompanyWebsite(siteRequest_, (JsonArray)o);
		case "resultCount":
			return CompanyWebsiteGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return CompanyWebsiteGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchPageUriCompanyWebsite(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyWebsiteGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyWebsiteGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchStrListCompanyWebsite(siteRequest_, (String)o);
		case "resultCount":
			return CompanyWebsiteGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return CompanyWebsiteGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchStrPageUriCompanyWebsite(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyWebsiteGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyWebsiteGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchFqListCompanyWebsite(siteRequest_, o);
		case "resultCount":
			return CompanyWebsiteGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "solrId":
			return CompanyWebsiteGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchFqPageUriCompanyWebsite(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyWebsiteGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.website.CompanyWebsiteGenPage";
	public static final String VAR_searchListCompanyWebsite_ = "searchListCompanyWebsite_";
	public static final String VAR_listCompanyWebsite = "listCompanyWebsite";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriCompanyWebsite = "pageUriCompanyWebsite";

	public static final String DISPLAY_NAME_searchListCompanyWebsite_ = "";
	public static final String DISPLAY_NAME_listCompanyWebsite = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriCompanyWebsite = "";

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
		return CompanyWebsiteGenPage.displayNameCompanyWebsiteGenPage(var);
	}
	public static String displayNameCompanyWebsiteGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyWebsite_:
			return DISPLAY_NAME_searchListCompanyWebsite_;
		case VAR_listCompanyWebsite:
			return DISPLAY_NAME_listCompanyWebsite;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriCompanyWebsite:
			return DISPLAY_NAME_pageUriCompanyWebsite;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionCompanyWebsiteGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameCompanyWebsiteGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyWebsite_:
			return "SearchList";
		case VAR_listCompanyWebsite:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "CompanyWebsite";
		case VAR_solrId:
			return "String";
		case VAR_pageUriCompanyWebsite:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnCompanyWebsiteGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowCompanyWebsiteGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellCompanyWebsiteGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinCompanyWebsiteGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxCompanyWebsiteGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxCompanyWebsiteGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minCompanyWebsiteGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
