package org.computate.site.model.service;

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
import org.computate.site.model.service.CompanyService;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyServiceGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyServiceGenPageGen into the class CompanyServiceGenPage. 
 * </li>
 * <h3>About the CompanyServiceGenPage class and it's generated class CompanyServiceGenPageGen&lt;PageLayout&gt;: </h3>extends CompanyServiceGenPageGen
 * <p>
 * This Java class extends a generated Java class CompanyServiceGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServiceGenPage">Find the class CompanyServiceGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyServiceGenPageGen<PageLayout>
 * <p>This <code>class CompanyServiceGenPage extends CompanyServiceGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated CompanyServiceGenPageGen. 
 * The generated <code>class CompanyServiceGenPageGen extends PageLayout</code> which means that CompanyServiceGenPage extends CompanyServiceGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyServiceGenPage class will inherit the helpful inherited class comments from the super class CompanyServiceGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyServiceGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class CompanyServiceGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServiceGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.service in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.service&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyServiceGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyServiceGenPage.class);

	///////////////////////////////
	// searchListCompanyService_ //
	///////////////////////////////


	/**	 The entity searchListCompanyService_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<CompanyService> searchListCompanyService_;

	/**	<br> The entity searchListCompanyService_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServiceGenPage&fq=entiteVar_enUS_indexed_string:searchListCompanyService_">Find the entity searchListCompanyService_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListCompanyService_(Wrap<SearchList<CompanyService>> w);

	public SearchList<CompanyService> getSearchListCompanyService_() {
		return searchListCompanyService_;
	}

	public void setSearchListCompanyService_(SearchList<CompanyService> searchListCompanyService_) {
		this.searchListCompanyService_ = searchListCompanyService_;
	}
	public static SearchList<CompanyService> staticSetSearchListCompanyService_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyServiceGenPage searchListCompanyService_Init() {
		Wrap<SearchList<CompanyService>> searchListCompanyService_Wrap = new Wrap<SearchList<CompanyService>>().var("searchListCompanyService_");
		if(searchListCompanyService_ == null) {
			_searchListCompanyService_(searchListCompanyService_Wrap);
			Optional.ofNullable(searchListCompanyService_Wrap.getO()).ifPresent(o -> {
				setSearchListCompanyService_(o);
			});
		}
		return (CompanyServiceGenPage)this;
	}

	////////////////////////
	// listCompanyService //
	////////////////////////


	/**	 The entity listCompanyService
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listCompanyService = new JsonArray();

	/**	<br> The entity listCompanyService
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServiceGenPage&fq=entiteVar_enUS_indexed_string:listCompanyService">Find the entity listCompanyService in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listCompanyService(JsonArray l);

	public JsonArray getListCompanyService() {
		return listCompanyService;
	}

	public void setListCompanyService(JsonArray listCompanyService) {
		this.listCompanyService = listCompanyService;
	}
	@JsonIgnore
	public void setListCompanyService(String o) {
		this.listCompanyService = CompanyServiceGenPage.staticSetListCompanyService(siteRequest_, o);
	}
	public static JsonArray staticSetListCompanyService(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected CompanyServiceGenPage listCompanyServiceInit() {
		_listCompanyService(listCompanyService);
		return (CompanyServiceGenPage)this;
	}

	public static String staticSearchListCompanyService(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListCompanyService(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListCompanyService(SiteRequest siteRequest_, String o) {
		return CompanyServiceGenPage.staticSearchListCompanyService(siteRequest_, CompanyServiceGenPage.staticSetListCompanyService(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServiceGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = CompanyServiceGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyServiceGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (CompanyServiceGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return CompanyServiceGenPage.staticSearchResultCount(siteRequest_, CompanyServiceGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CompanyService result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServiceGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<CompanyService> w);

	public CompanyService getResult() {
		return result;
	}

	public void setResult(CompanyService result) {
		this.result = result;
	}
	public static CompanyService staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyServiceGenPage resultInit() {
		Wrap<CompanyService> resultWrap = new Wrap<CompanyService>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (CompanyServiceGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServiceGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = CompanyServiceGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyServiceGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (CompanyServiceGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return CompanyServiceGenPage.staticSearchSolrId(siteRequest_, CompanyServiceGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	///////////////////////////
	// pageUriCompanyService //
	///////////////////////////


	/**	 The entity pageUriCompanyService
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriCompanyService;

	/**	<br> The entity pageUriCompanyService
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServiceGenPage&fq=entiteVar_enUS_indexed_string:pageUriCompanyService">Find the entity pageUriCompanyService in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriCompanyService(Wrap<String> c);

	public String getPageUriCompanyService() {
		return pageUriCompanyService;
	}
	public void setPageUriCompanyService(String o) {
		this.pageUriCompanyService = CompanyServiceGenPage.staticSetPageUriCompanyService(siteRequest_, o);
	}
	public static String staticSetPageUriCompanyService(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyServiceGenPage pageUriCompanyServiceInit() {
		Wrap<String> pageUriCompanyServiceWrap = new Wrap<String>().var("pageUriCompanyService");
		if(pageUriCompanyService == null) {
			_pageUriCompanyService(pageUriCompanyServiceWrap);
			Optional.ofNullable(pageUriCompanyServiceWrap.getO()).ifPresent(o -> {
				setPageUriCompanyService(o);
			});
		}
		return (CompanyServiceGenPage)this;
	}

	public static String staticSearchPageUriCompanyService(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriCompanyService(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriCompanyService(SiteRequest siteRequest_, String o) {
		return CompanyServiceGenPage.staticSearchPageUriCompanyService(siteRequest_, CompanyServiceGenPage.staticSetPageUriCompanyService(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<CompanyServiceGenPageGen<DEV>> promiseDeepCompanyServiceGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyServiceGenPage();
	}

	public Future<CompanyServiceGenPageGen<DEV>> promiseDeepCompanyServiceGenPage() {
		Promise<CompanyServiceGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyServiceGenPage(promise2);
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

	public Future<Void> promiseCompanyServiceGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListCompanyService_Init();
				listCompanyServiceInit();
				resultCountInit();
				resultInit();
				solrIdInit();
				pageUriCompanyServiceInit();
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

	@Override public Future<? extends CompanyServiceGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyServiceGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyServiceGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyServiceGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyServiceGenPage(v);
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
	public Object obtainCompanyServiceGenPage(String var) {
		CompanyServiceGenPage oCompanyServiceGenPage = (CompanyServiceGenPage)this;
		switch(var) {
			case "searchListCompanyService_":
				return oCompanyServiceGenPage.searchListCompanyService_;
			case "listCompanyService":
				return oCompanyServiceGenPage.listCompanyService;
			case "resultCount":
				return oCompanyServiceGenPage.resultCount;
			case "result":
				return oCompanyServiceGenPage.result;
			case "solrId":
				return oCompanyServiceGenPage.solrId;
			case "pageUriCompanyService":
				return oCompanyServiceGenPage.pageUriCompanyService;
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
				o = relateCompanyServiceGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyServiceGenPage(String var, Object val) {
		CompanyServiceGenPage oCompanyServiceGenPage = (CompanyServiceGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, CompanyServiceGenPage o) {
		return staticSetCompanyServiceGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetCompanyServiceGenPage(String entityVar, SiteRequest siteRequest_, String v, CompanyServiceGenPage o) {
		switch(entityVar) {
		case "listCompanyService":
			return CompanyServiceGenPage.staticSetListCompanyService(siteRequest_, v);
		case "resultCount":
			return CompanyServiceGenPage.staticSetResultCount(siteRequest_, v);
		case "solrId":
			return CompanyServiceGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriCompanyService":
			return CompanyServiceGenPage.staticSetPageUriCompanyService(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyServiceGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyServiceGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyService":
			return CompanyServiceGenPage.staticSearchListCompanyService(siteRequest_, (JsonArray)o);
		case "resultCount":
			return CompanyServiceGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return CompanyServiceGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriCompanyService":
			return CompanyServiceGenPage.staticSearchPageUriCompanyService(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyServiceGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyServiceGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyService":
			return CompanyServiceGenPage.staticSearchStrListCompanyService(siteRequest_, (String)o);
		case "resultCount":
			return CompanyServiceGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return CompanyServiceGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriCompanyService":
			return CompanyServiceGenPage.staticSearchStrPageUriCompanyService(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyServiceGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyServiceGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyService":
			return CompanyServiceGenPage.staticSearchFqListCompanyService(siteRequest_, o);
		case "resultCount":
			return CompanyServiceGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "solrId":
			return CompanyServiceGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriCompanyService":
			return CompanyServiceGenPage.staticSearchFqPageUriCompanyService(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyServiceGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.service.CompanyServiceGenPage";
	public static final String VAR_searchListCompanyService_ = "searchListCompanyService_";
	public static final String VAR_listCompanyService = "listCompanyService";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriCompanyService = "pageUriCompanyService";

	public static final String DISPLAY_NAME_searchListCompanyService_ = "";
	public static final String DISPLAY_NAME_listCompanyService = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriCompanyService = "";

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
		return CompanyServiceGenPage.displayNameCompanyServiceGenPage(var);
	}
	public static String displayNameCompanyServiceGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyService_:
			return DISPLAY_NAME_searchListCompanyService_;
		case VAR_listCompanyService:
			return DISPLAY_NAME_listCompanyService;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriCompanyService:
			return DISPLAY_NAME_pageUriCompanyService;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionCompanyServiceGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameCompanyServiceGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyService_:
			return "SearchList";
		case VAR_listCompanyService:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "CompanyService";
		case VAR_solrId:
			return "String";
		case VAR_pageUriCompanyService:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnCompanyServiceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowCompanyServiceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellCompanyServiceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinCompanyServiceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxCompanyServiceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxCompanyServiceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minCompanyServiceGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
