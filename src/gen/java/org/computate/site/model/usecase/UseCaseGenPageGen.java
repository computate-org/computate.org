package org.computate.site.model.usecase;

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
import org.computate.site.model.usecase.UseCase;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these UseCaseGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class UseCaseGenPageGen into the class UseCaseGenPage. 
 * </li>
 * <h3>About the UseCaseGenPage class and it's generated class UseCaseGenPageGen&lt;PageLayout&gt;: </h3>extends UseCaseGenPageGen
 * <p>
 * This Java class extends a generated Java class UseCaseGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCaseGenPage">Find the class UseCaseGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends UseCaseGenPageGen<PageLayout>
 * <p>This <code>class UseCaseGenPage extends UseCaseGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated UseCaseGenPageGen. 
 * The generated <code>class UseCaseGenPageGen extends PageLayout</code> which means that UseCaseGenPage extends UseCaseGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the UseCaseGenPage class will inherit the helpful inherited class comments from the super class UseCaseGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the UseCaseGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class UseCaseGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCaseGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.usecase in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.usecase&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class UseCaseGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(UseCaseGenPage.class);

	////////////////////////
	// searchListUseCase_ //
	////////////////////////


	/**	 The entity searchListUseCase_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<UseCase> searchListUseCase_;

	/**	<br> The entity searchListUseCase_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCaseGenPage&fq=entiteVar_enUS_indexed_string:searchListUseCase_">Find the entity searchListUseCase_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListUseCase_(Wrap<SearchList<UseCase>> w);

	public SearchList<UseCase> getSearchListUseCase_() {
		return searchListUseCase_;
	}

	public void setSearchListUseCase_(SearchList<UseCase> searchListUseCase_) {
		this.searchListUseCase_ = searchListUseCase_;
	}
	public static SearchList<UseCase> staticSetSearchListUseCase_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected UseCaseGenPage searchListUseCase_Init() {
		Wrap<SearchList<UseCase>> searchListUseCase_Wrap = new Wrap<SearchList<UseCase>>().var("searchListUseCase_");
		if(searchListUseCase_ == null) {
			_searchListUseCase_(searchListUseCase_Wrap);
			Optional.ofNullable(searchListUseCase_Wrap.getO()).ifPresent(o -> {
				setSearchListUseCase_(o);
			});
		}
		return (UseCaseGenPage)this;
	}

	/////////////////
	// listUseCase //
	/////////////////


	/**	 The entity listUseCase
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listUseCase = new JsonArray();

	/**	<br> The entity listUseCase
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCaseGenPage&fq=entiteVar_enUS_indexed_string:listUseCase">Find the entity listUseCase in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listUseCase(JsonArray l);

	public JsonArray getListUseCase() {
		return listUseCase;
	}

	public void setListUseCase(JsonArray listUseCase) {
		this.listUseCase = listUseCase;
	}
	@JsonIgnore
	public void setListUseCase(String o) {
		this.listUseCase = UseCaseGenPage.staticSetListUseCase(siteRequest_, o);
	}
	public static JsonArray staticSetListUseCase(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected UseCaseGenPage listUseCaseInit() {
		_listUseCase(listUseCase);
		return (UseCaseGenPage)this;
	}

	public static String staticSearchListUseCase(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListUseCase(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListUseCase(SiteRequest siteRequest_, String o) {
		return UseCaseGenPage.staticSearchListUseCase(siteRequest_, UseCaseGenPage.staticSetListUseCase(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCaseGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = UseCaseGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected UseCaseGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (UseCaseGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return UseCaseGenPage.staticSearchResultCount(siteRequest_, UseCaseGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected UseCase result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCaseGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<UseCase> w);

	public UseCase getResult() {
		return result;
	}

	public void setResult(UseCase result) {
		this.result = result;
	}
	public static UseCase staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected UseCaseGenPage resultInit() {
		Wrap<UseCase> resultWrap = new Wrap<UseCase>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (UseCaseGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCaseGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = UseCaseGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected UseCaseGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (UseCaseGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return UseCaseGenPage.staticSearchSolrId(siteRequest_, UseCaseGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	////////////////////
	// pageUriUseCase //
	////////////////////


	/**	 The entity pageUriUseCase
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriUseCase;

	/**	<br> The entity pageUriUseCase
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.usecase.UseCaseGenPage&fq=entiteVar_enUS_indexed_string:pageUriUseCase">Find the entity pageUriUseCase in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriUseCase(Wrap<String> c);

	public String getPageUriUseCase() {
		return pageUriUseCase;
	}
	public void setPageUriUseCase(String o) {
		this.pageUriUseCase = UseCaseGenPage.staticSetPageUriUseCase(siteRequest_, o);
	}
	public static String staticSetPageUriUseCase(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected UseCaseGenPage pageUriUseCaseInit() {
		Wrap<String> pageUriUseCaseWrap = new Wrap<String>().var("pageUriUseCase");
		if(pageUriUseCase == null) {
			_pageUriUseCase(pageUriUseCaseWrap);
			Optional.ofNullable(pageUriUseCaseWrap.getO()).ifPresent(o -> {
				setPageUriUseCase(o);
			});
		}
		return (UseCaseGenPage)this;
	}

	public static String staticSearchPageUriUseCase(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriUseCase(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriUseCase(SiteRequest siteRequest_, String o) {
		return UseCaseGenPage.staticSearchPageUriUseCase(siteRequest_, UseCaseGenPage.staticSetPageUriUseCase(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<UseCaseGenPageGen<DEV>> promiseDeepUseCaseGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepUseCaseGenPage();
	}

	public Future<UseCaseGenPageGen<DEV>> promiseDeepUseCaseGenPage() {
		Promise<UseCaseGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseUseCaseGenPage(promise2);
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

	public Future<Void> promiseUseCaseGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListUseCase_Init();
				listUseCaseInit();
				resultCountInit();
				resultInit();
				solrIdInit();
				pageUriUseCaseInit();
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

	@Override public Future<? extends UseCaseGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepUseCaseGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestUseCaseGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestUseCaseGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainUseCaseGenPage(v);
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
	public Object obtainUseCaseGenPage(String var) {
		UseCaseGenPage oUseCaseGenPage = (UseCaseGenPage)this;
		switch(var) {
			case "searchListUseCase_":
				return oUseCaseGenPage.searchListUseCase_;
			case "listUseCase":
				return oUseCaseGenPage.listUseCase;
			case "resultCount":
				return oUseCaseGenPage.resultCount;
			case "result":
				return oUseCaseGenPage.result;
			case "solrId":
				return oUseCaseGenPage.solrId;
			case "pageUriUseCase":
				return oUseCaseGenPage.pageUriUseCase;
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
				o = relateUseCaseGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateUseCaseGenPage(String var, Object val) {
		UseCaseGenPage oUseCaseGenPage = (UseCaseGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, UseCaseGenPage o) {
		return staticSetUseCaseGenPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetUseCaseGenPage(String entityVar, SiteRequest siteRequest_, String v, UseCaseGenPage o) {
		switch(entityVar) {
		case "listUseCase":
			return UseCaseGenPage.staticSetListUseCase(siteRequest_, v);
		case "resultCount":
			return UseCaseGenPage.staticSetResultCount(siteRequest_, v);
		case "solrId":
			return UseCaseGenPage.staticSetSolrId(siteRequest_, v);
		case "pageUriUseCase":
			return UseCaseGenPage.staticSetPageUriUseCase(siteRequest_, v);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchUseCaseGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchUseCaseGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listUseCase":
			return UseCaseGenPage.staticSearchListUseCase(siteRequest_, (JsonArray)o);
		case "resultCount":
			return UseCaseGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return UseCaseGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriUseCase":
			return UseCaseGenPage.staticSearchPageUriUseCase(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrUseCaseGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrUseCaseGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listUseCase":
			return UseCaseGenPage.staticSearchStrListUseCase(siteRequest_, (String)o);
		case "resultCount":
			return UseCaseGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return UseCaseGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriUseCase":
			return UseCaseGenPage.staticSearchStrPageUriUseCase(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqUseCaseGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqUseCaseGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listUseCase":
			return UseCaseGenPage.staticSearchFqListUseCase(siteRequest_, o);
		case "resultCount":
			return UseCaseGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "solrId":
			return UseCaseGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriUseCase":
			return UseCaseGenPage.staticSearchFqPageUriUseCase(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "UseCaseGenPage";
	public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.usecase.UseCaseGenPage";
	public static final String CLASS_AUTH_RESOURCE = "";
	public static final String VAR_searchListUseCase_ = "searchListUseCase_";
	public static final String VAR_listUseCase = "listUseCase";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriUseCase = "pageUriUseCase";

	public static final String DISPLAY_NAME_searchListUseCase_ = "";
	public static final String DISPLAY_NAME_listUseCase = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriUseCase = "";

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
		return UseCaseGenPage.displayNameUseCaseGenPage(var);
	}
	public static String displayNameUseCaseGenPage(String var) {
		switch(var) {
		case VAR_searchListUseCase_:
			return DISPLAY_NAME_searchListUseCase_;
		case VAR_listUseCase:
			return DISPLAY_NAME_listUseCase;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriUseCase:
			return DISPLAY_NAME_pageUriUseCase;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionUseCaseGenPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameUseCaseGenPage(String var) {
		switch(var) {
		case VAR_searchListUseCase_:
			return "SearchList";
		case VAR_listUseCase:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "UseCase";
		case VAR_solrId:
			return "String";
		case VAR_pageUriUseCase:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnUseCaseGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowUseCaseGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellUseCaseGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinUseCaseGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxUseCaseGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxUseCaseGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minUseCaseGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
