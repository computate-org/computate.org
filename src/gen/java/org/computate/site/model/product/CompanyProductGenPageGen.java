package org.computate.site.model.product;

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
import org.computate.site.model.product.CompanyProduct;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyProductGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyProductGenPageGen into the class CompanyProductGenPage. 
 * </li>
 * <h3>About the CompanyProductGenPage class and it's generated class CompanyProductGenPageGen&lt;PageLayout&gt;: </h3>extends CompanyProductGenPageGen
 * <p>
 * This Java class extends a generated Java class CompanyProductGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage">Find the class CompanyProductGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyProductGenPageGen<PageLayout>
 * <p>This <code>class CompanyProductGenPage extends CompanyProductGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated CompanyProductGenPageGen. 
 * The generated <code>class CompanyProductGenPageGen extends PageLayout</code> which means that CompanyProductGenPage extends CompanyProductGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyProductGenPage class will inherit the helpful inherited class comments from the super class CompanyProductGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyProductGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class CompanyProductGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.product in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.product&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyProductGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyProductGenPage.class);

	///////////////////////////////
	// searchListCompanyProduct_ //
	///////////////////////////////


	/**	 The entity searchListCompanyProduct_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<CompanyProduct> searchListCompanyProduct_;

	/**	<br> The entity searchListCompanyProduct_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:searchListCompanyProduct_">Find the entity searchListCompanyProduct_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListCompanyProduct_(Wrap<SearchList<CompanyProduct>> w);

	public SearchList<CompanyProduct> getSearchListCompanyProduct_() {
		return searchListCompanyProduct_;
	}

	public void setSearchListCompanyProduct_(SearchList<CompanyProduct> searchListCompanyProduct_) {
		this.searchListCompanyProduct_ = searchListCompanyProduct_;
	}
	public static SearchList<CompanyProduct> staticSetSearchListCompanyProduct_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyProductGenPage searchListCompanyProduct_Init() {
		Wrap<SearchList<CompanyProduct>> searchListCompanyProduct_Wrap = new Wrap<SearchList<CompanyProduct>>().var("searchListCompanyProduct_");
		if(searchListCompanyProduct_ == null) {
			_searchListCompanyProduct_(searchListCompanyProduct_Wrap);
			Optional.ofNullable(searchListCompanyProduct_Wrap.getO()).ifPresent(o -> {
				setSearchListCompanyProduct_(o);
			});
		}
		return (CompanyProductGenPage)this;
	}

	////////////////////////
	// listCompanyProduct //
	////////////////////////


	/**	 The entity listCompanyProduct
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonDeserialize(using = JsonArrayDeserializer.class)
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listCompanyProduct = new JsonArray();

	/**	<br> The entity listCompanyProduct
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:listCompanyProduct">Find the entity listCompanyProduct in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listCompanyProduct(JsonArray l);

	public JsonArray getListCompanyProduct() {
		return listCompanyProduct;
	}

	public void setListCompanyProduct(JsonArray listCompanyProduct) {
		this.listCompanyProduct = listCompanyProduct;
	}
	@JsonIgnore
	public void setListCompanyProduct(String o) {
		this.listCompanyProduct = CompanyProductGenPage.staticSetListCompanyProduct(siteRequest_, o);
	}
	public static JsonArray staticSetListCompanyProduct(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected CompanyProductGenPage listCompanyProductInit() {
		_listCompanyProduct(listCompanyProduct);
		return (CompanyProductGenPage)this;
	}

	public static String staticSearchListCompanyProduct(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListCompanyProduct(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListCompanyProduct(SiteRequest siteRequest_, String o) {
		return CompanyProductGenPage.staticSearchListCompanyProduct(siteRequest_, CompanyProductGenPage.staticSetListCompanyProduct(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
		this.resultCount = CompanyProductGenPage.staticSetResultCount(siteRequest_, o);
	}
	public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyProductGenPage resultCountInit() {
		Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
		if(resultCount == null) {
			_resultCount(resultCountWrap);
			Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
				setResultCount(o);
			});
		}
		return (CompanyProductGenPage)this;
	}

	public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
		return CompanyProductGenPage.staticSearchResultCount(siteRequest_, CompanyProductGenPage.staticSetResultCount(siteRequest_, o)).toString();
	}

	////////////
	// result //
	////////////


	/**	 The entity result
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CompanyProduct result;

	/**	<br> The entity result
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _result(Wrap<CompanyProduct> w);

	public CompanyProduct getResult() {
		return result;
	}

	public void setResult(CompanyProduct result) {
		this.result = result;
	}
	public static CompanyProduct staticSetResult(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyProductGenPage resultInit() {
		Wrap<CompanyProduct> resultWrap = new Wrap<CompanyProduct>().var("result");
		if(result == null) {
			_result(resultWrap);
			Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
				setResult(o);
			});
		}
		return (CompanyProductGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _solrId(Wrap<String> w);

	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String o) {
		this.solrId = CompanyProductGenPage.staticSetSolrId(siteRequest_, o);
	}
	public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProductGenPage solrIdInit() {
		Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
		if(solrId == null) {
			_solrId(solrIdWrap);
			Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
				setSolrId(o);
			});
		}
		return (CompanyProductGenPage)this;
	}

	public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
		return CompanyProductGenPage.staticSearchSolrId(siteRequest_, CompanyProductGenPage.staticSetSolrId(siteRequest_, o)).toString();
	}

	///////////////////////////
	// pageUriCompanyProduct //
	///////////////////////////


	/**	 The entity pageUriCompanyProduct
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriCompanyProduct;

	/**	<br> The entity pageUriCompanyProduct
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:pageUriCompanyProduct">Find the entity pageUriCompanyProduct in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriCompanyProduct(Wrap<String> c);

	public String getPageUriCompanyProduct() {
		return pageUriCompanyProduct;
	}
	public void setPageUriCompanyProduct(String o) {
		this.pageUriCompanyProduct = CompanyProductGenPage.staticSetPageUriCompanyProduct(siteRequest_, o);
	}
	public static String staticSetPageUriCompanyProduct(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProductGenPage pageUriCompanyProductInit() {
		Wrap<String> pageUriCompanyProductWrap = new Wrap<String>().var("pageUriCompanyProduct");
		if(pageUriCompanyProduct == null) {
			_pageUriCompanyProduct(pageUriCompanyProductWrap);
			Optional.ofNullable(pageUriCompanyProductWrap.getO()).ifPresent(o -> {
				setPageUriCompanyProduct(o);
			});
		}
		return (CompanyProductGenPage)this;
	}

	public static String staticSearchPageUriCompanyProduct(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriCompanyProduct(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriCompanyProduct(SiteRequest siteRequest_, String o) {
		return CompanyProductGenPage.staticSearchPageUriCompanyProduct(siteRequest_, CompanyProductGenPage.staticSetPageUriCompanyProduct(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<CompanyProductGenPageGen<DEV>> promiseDeepCompanyProductGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyProductGenPage();
	}

	public Future<CompanyProductGenPageGen<DEV>> promiseDeepCompanyProductGenPage() {
		Promise<CompanyProductGenPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyProductGenPage(promise2);
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

	public Future<Void> promiseCompanyProductGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListCompanyProduct_Init();
				listCompanyProductInit();
				resultCountInit();
				resultInit();
				solrIdInit();
				pageUriCompanyProductInit();
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

	@Override public Future<? extends CompanyProductGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyProductGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyProductGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyProductGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyProductGenPage(v);
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
	public Object obtainCompanyProductGenPage(String var) {
		CompanyProductGenPage oCompanyProductGenPage = (CompanyProductGenPage)this;
		switch(var) {
			case "searchListCompanyProduct_":
				return oCompanyProductGenPage.searchListCompanyProduct_;
			case "listCompanyProduct":
				return oCompanyProductGenPage.listCompanyProduct;
			case "resultCount":
				return oCompanyProductGenPage.resultCount;
			case "result":
				return oCompanyProductGenPage.result;
			case "solrId":
				return oCompanyProductGenPage.solrId;
			case "pageUriCompanyProduct":
				return oCompanyProductGenPage.pageUriCompanyProduct;
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
				o = relateCompanyProductGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyProductGenPage(String var, Object val) {
		CompanyProductGenPage oCompanyProductGenPage = (CompanyProductGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyProductGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyProductGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyProduct":
			return CompanyProductGenPage.staticSetListCompanyProduct(siteRequest_, o);
		case "resultCount":
			return CompanyProductGenPage.staticSetResultCount(siteRequest_, o);
		case "solrId":
			return CompanyProductGenPage.staticSetSolrId(siteRequest_, o);
		case "pageUriCompanyProduct":
			return CompanyProductGenPage.staticSetPageUriCompanyProduct(siteRequest_, o);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyProductGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyProductGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyProduct":
			return CompanyProductGenPage.staticSearchListCompanyProduct(siteRequest_, (JsonArray)o);
		case "resultCount":
			return CompanyProductGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return CompanyProductGenPage.staticSearchSolrId(siteRequest_, (String)o);
		case "pageUriCompanyProduct":
			return CompanyProductGenPage.staticSearchPageUriCompanyProduct(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyProductGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyProductGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyProduct":
			return CompanyProductGenPage.staticSearchStrListCompanyProduct(siteRequest_, (String)o);
		case "resultCount":
			return CompanyProductGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
		case "solrId":
			return CompanyProductGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
		case "pageUriCompanyProduct":
			return CompanyProductGenPage.staticSearchStrPageUriCompanyProduct(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyProductGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyProductGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyProduct":
			return CompanyProductGenPage.staticSearchFqListCompanyProduct(siteRequest_, o);
		case "resultCount":
			return CompanyProductGenPage.staticSearchFqResultCount(siteRequest_, o);
		case "solrId":
			return CompanyProductGenPage.staticSearchFqSolrId(siteRequest_, o);
		case "pageUriCompanyProduct":
			return CompanyProductGenPage.staticSearchFqPageUriCompanyProduct(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyProductGenPage";
	public static final String VAR_searchListCompanyProduct_ = "searchListCompanyProduct_";
	public static final String VAR_listCompanyProduct = "listCompanyProduct";
	public static final String VAR_resultCount = "resultCount";
	public static final String VAR_result = "result";
	public static final String VAR_solrId = "solrId";
	public static final String VAR_pageUriCompanyProduct = "pageUriCompanyProduct";

	public static final String DISPLAY_NAME_searchListCompanyProduct_ = "";
	public static final String DISPLAY_NAME_listCompanyProduct = "";
	public static final String DISPLAY_NAME_resultCount = "";
	public static final String DISPLAY_NAME_result = "";
	public static final String DISPLAY_NAME_solrId = "";
	public static final String DISPLAY_NAME_pageUriCompanyProduct = "";

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

	public static String displayNameForClass(String var) {
		return CompanyProductGenPage.displayNameCompanyProductGenPage(var);
	}
	public static String displayNameCompanyProductGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyProduct_:
			return DISPLAY_NAME_searchListCompanyProduct_;
		case VAR_listCompanyProduct:
			return DISPLAY_NAME_listCompanyProduct;
		case VAR_resultCount:
			return DISPLAY_NAME_resultCount;
		case VAR_result:
			return DISPLAY_NAME_result;
		case VAR_solrId:
			return DISPLAY_NAME_solrId;
		case VAR_pageUriCompanyProduct:
			return DISPLAY_NAME_pageUriCompanyProduct;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}

	public static String descriptionCompanyProductGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.descriptionPageLayout(var);
		}
	}

	public static String classSimpleNameCompanyProductGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyProduct_:
			return "SearchList";
		case VAR_listCompanyProduct:
			return "JsonArray";
		case VAR_resultCount:
			return "Integer";
		case VAR_result:
			return "CompanyProduct";
		case VAR_solrId:
			return "String";
		case VAR_pageUriCompanyProduct:
			return "String";
			default:
				return PageLayout.classSimpleNamePageLayout(var);
		}
	}

	public static Integer htmColumnCompanyProductGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmColumnPageLayout(var);
		}
	}

	public static Integer htmRowCompanyProductGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmRowPageLayout(var);
		}
	}

	public static Integer htmCellCompanyProductGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.htmCellPageLayout(var);
		}
	}

	public static Integer lengthMinCompanyProductGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMinPageLayout(var);
		}
	}

	public static Integer lengthMaxCompanyProductGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.lengthMaxPageLayout(var);
		}
	}

	public static Integer maxCompanyProductGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.maxPageLayout(var);
		}
	}

	public static Integer minCompanyProductGenPage(String var) {
		switch(var) {
			default:
				return PageLayout.minPageLayout(var);
		}
	}
}
