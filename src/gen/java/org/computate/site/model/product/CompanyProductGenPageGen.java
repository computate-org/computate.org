package org.computate.site.model.product;

import org.computate.site.request.SiteRequest;
import org.computate.site.result.BaseResultPage;
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
import java.lang.Integer;
import java.math.BigDecimal;
import io.vertx.core.json.JsonArray;
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
 * <h3>About the CompanyProductGenPage class and it's generated class CompanyProductGenPageGen&lt;BaseResultPage&gt;: </h3>extends CompanyProductGenPageGen
 * <p>
 * This Java class extends a generated Java class CompanyProductGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage">Find the class CompanyProductGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyProductGenPageGen<BaseResultPage>
 * <p>This <code>class CompanyProductGenPage extends CompanyProductGenPageGen&lt;BaseResultPage&gt;</code>, which means it extends a newly generated CompanyProductGenPageGen. 
 * The generated <code>class CompanyProductGenPageGen extends BaseResultPage</code> which means that CompanyProductGenPage extends CompanyProductGenPageGen which extends BaseResultPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>IconGroup: null</h2>
 * <h2>IconName: null</h2>
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
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.product in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.product&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyProductGenPageGen<DEV> extends BaseResultPage {
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:searchListCompanyProduct_">Find the entity searchListCompanyProduct_ in Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listCompanyProduct = new JsonArray();

	/**	<br> The entity listCompanyProduct
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:listCompanyProduct">Find the entity listCompanyProduct in Solr</a>
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

	/////////////////////////
	// companyProductCount //
	/////////////////////////


	/**	 The entity companyProductCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer companyProductCount;

	/**	<br> The entity companyProductCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:companyProductCount">Find the entity companyProductCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyProductCount(Wrap<Integer> w);

	public Integer getCompanyProductCount() {
		return companyProductCount;
	}

	public void setCompanyProductCount(Integer companyProductCount) {
		this.companyProductCount = companyProductCount;
	}
	@JsonIgnore
	public void setCompanyProductCount(String o) {
		this.companyProductCount = CompanyProductGenPage.staticSetCompanyProductCount(siteRequest_, o);
	}
	public static Integer staticSetCompanyProductCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyProductGenPage companyProductCountInit() {
		Wrap<Integer> companyProductCountWrap = new Wrap<Integer>().var("companyProductCount");
		if(companyProductCount == null) {
			_companyProductCount(companyProductCountWrap);
			Optional.ofNullable(companyProductCountWrap.getO()).ifPresent(o -> {
				setCompanyProductCount(o);
			});
		}
		return (CompanyProductGenPage)this;
	}

	public static Integer staticSearchCompanyProductCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrCompanyProductCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCompanyProductCount(SiteRequest siteRequest_, String o) {
		return CompanyProductGenPage.staticSearchCompanyProductCount(siteRequest_, CompanyProductGenPage.staticSetCompanyProductCount(siteRequest_, o)).toString();
	}

	/////////////////////
	// companyProduct_ //
	/////////////////////


	/**	 The entity companyProduct_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CompanyProduct companyProduct_;

	/**	<br> The entity companyProduct_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:companyProduct_">Find the entity companyProduct_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyProduct_(Wrap<CompanyProduct> w);

	public CompanyProduct getCompanyProduct_() {
		return companyProduct_;
	}

	public void setCompanyProduct_(CompanyProduct companyProduct_) {
		this.companyProduct_ = companyProduct_;
	}
	public static CompanyProduct staticSetCompanyProduct_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyProductGenPage companyProduct_Init() {
		Wrap<CompanyProduct> companyProduct_Wrap = new Wrap<CompanyProduct>().var("companyProduct_");
		if(companyProduct_ == null) {
			_companyProduct_(companyProduct_Wrap);
			Optional.ofNullable(companyProduct_Wrap.getO()).ifPresent(o -> {
				setCompanyProduct_(o);
			});
		}
		return (CompanyProductGenPage)this;
	}

	////////
	// id //
	////////


	/**	 The entity id
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String id;

	/**	<br> The entity id
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = CompanyProductGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProductGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (CompanyProductGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return CompanyProductGenPage.staticSearchId(siteRequest_, CompanyProductGenPage.staticSetId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProductGenPage&fq=entiteVar_enUS_indexed_string:pageUriCompanyProduct">Find the entity pageUriCompanyProduct in Solr</a>
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

	public Future<Void> promiseDeepCompanyProductGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyProductGenPage();
	}

	public Future<Void> promiseDeepCompanyProductGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyProductGenPage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseResultPage(siteRequest_).onSuccess(b -> {
				promise.complete();
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
				companyProductCountInit();
				companyProduct_Init();
				idInit();
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

	@Override public Future<Void> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyProductGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyProductGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseResultPage(siteRequest_);
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
			case "companyProductCount":
				return oCompanyProductGenPage.companyProductCount;
			case "companyProduct_":
				return oCompanyProductGenPage.companyProduct_;
			case "id":
				return oCompanyProductGenPage.id;
			case "pageUriCompanyProduct":
				return oCompanyProductGenPage.pageUriCompanyProduct;
			default:
				return super.obtainBaseResultPage(var);
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
				return super.relateBaseResultPage(var, val);
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
		case "companyProductCount":
			return CompanyProductGenPage.staticSetCompanyProductCount(siteRequest_, o);
		case "id":
			return CompanyProductGenPage.staticSetId(siteRequest_, o);
		case "pageUriCompanyProduct":
			return CompanyProductGenPage.staticSetPageUriCompanyProduct(siteRequest_, o);
			default:
				return BaseResultPage.staticSetBaseResultPage(entityVar,  siteRequest_, o);
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
		case "companyProductCount":
			return CompanyProductGenPage.staticSearchCompanyProductCount(siteRequest_, (Integer)o);
		case "id":
			return CompanyProductGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriCompanyProduct":
			return CompanyProductGenPage.staticSearchPageUriCompanyProduct(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchBaseResultPage(entityVar,  siteRequest_, o);
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
		case "companyProductCount":
			return CompanyProductGenPage.staticSearchStrCompanyProductCount(siteRequest_, (Integer)o);
		case "id":
			return CompanyProductGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriCompanyProduct":
			return CompanyProductGenPage.staticSearchStrPageUriCompanyProduct(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchStrBaseResultPage(entityVar,  siteRequest_, o);
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
		case "companyProductCount":
			return CompanyProductGenPage.staticSearchFqCompanyProductCount(siteRequest_, o);
		case "id":
			return CompanyProductGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriCompanyProduct":
			return CompanyProductGenPage.staticSearchFqPageUriCompanyProduct(siteRequest_, o);
			default:
				return BaseResultPage.staticSearchFqBaseResultPage(entityVar,  siteRequest_, o);
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
	public static final String VAR_companyProductCount = "companyProductCount";
	public static final String VAR_companyProduct_ = "companyProduct_";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriCompanyProduct = "pageUriCompanyProduct";

	public static final String DISPLAY_NAME_searchListCompanyProduct_ = "";
	public static final String DISPLAY_NAME_listCompanyProduct = "";
	public static final String DISPLAY_NAME_companyProductCount = "";
	public static final String DISPLAY_NAME_companyProduct_ = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriCompanyProduct = "";

	public static String displayNameForClass(String var) {
		return CompanyProductGenPage.displayNameCompanyProductGenPage(var);
	}
	public static String displayNameCompanyProductGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyProduct_:
			return DISPLAY_NAME_searchListCompanyProduct_;
		case VAR_listCompanyProduct:
			return DISPLAY_NAME_listCompanyProduct;
		case VAR_companyProductCount:
			return DISPLAY_NAME_companyProductCount;
		case VAR_companyProduct_:
			return DISPLAY_NAME_companyProduct_;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriCompanyProduct:
			return DISPLAY_NAME_pageUriCompanyProduct;
		default:
			return BaseResultPage.displayNameBaseResultPage(var);
		}
	}
}
