package org.computate.site.model.research;

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
import org.computate.site.model.research.CompanyResearch;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyResearchGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyResearchGenPageGen into the class CompanyResearchGenPage. 
 * </li>
 * <h3>About the CompanyResearchGenPage class and it's generated class CompanyResearchGenPageGen&lt;BaseResultPage&gt;: </h3>extends CompanyResearchGenPageGen
 * <p>
 * This Java class extends a generated Java class CompanyResearchGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage">Find the class CompanyResearchGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyResearchGenPageGen<BaseResultPage>
 * <p>This <code>class CompanyResearchGenPage extends CompanyResearchGenPageGen&lt;BaseResultPage&gt;</code>, which means it extends a newly generated CompanyResearchGenPageGen. 
 * The generated <code>class CompanyResearchGenPageGen extends BaseResultPage</code> which means that CompanyResearchGenPage extends CompanyResearchGenPageGen which extends BaseResultPage. 
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
public abstract class CompanyResearchGenPageGen<DEV> extends BaseResultPage {
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
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:searchListCompanyResearch_">Find the entity searchListCompanyResearch_ in Solr</a>
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
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listCompanyResearch = new JsonArray();

	/**	<br> The entity listCompanyResearch
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:listCompanyResearch">Find the entity listCompanyResearch in Solr</a>
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

	//////////////////////////
	// companyResearchCount //
	//////////////////////////


	/**	 The entity companyResearchCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer companyResearchCount;

	/**	<br> The entity companyResearchCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:companyResearchCount">Find the entity companyResearchCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyResearchCount(Wrap<Integer> w);

	public Integer getCompanyResearchCount() {
		return companyResearchCount;
	}

	public void setCompanyResearchCount(Integer companyResearchCount) {
		this.companyResearchCount = companyResearchCount;
	}
	@JsonIgnore
	public void setCompanyResearchCount(String o) {
		this.companyResearchCount = CompanyResearchGenPage.staticSetCompanyResearchCount(siteRequest_, o);
	}
	public static Integer staticSetCompanyResearchCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyResearchGenPage companyResearchCountInit() {
		Wrap<Integer> companyResearchCountWrap = new Wrap<Integer>().var("companyResearchCount");
		if(companyResearchCount == null) {
			_companyResearchCount(companyResearchCountWrap);
			Optional.ofNullable(companyResearchCountWrap.getO()).ifPresent(o -> {
				setCompanyResearchCount(o);
			});
		}
		return (CompanyResearchGenPage)this;
	}

	public static Integer staticSearchCompanyResearchCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrCompanyResearchCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCompanyResearchCount(SiteRequest siteRequest_, String o) {
		return CompanyResearchGenPage.staticSearchCompanyResearchCount(siteRequest_, CompanyResearchGenPage.staticSetCompanyResearchCount(siteRequest_, o)).toString();
	}

	//////////////////////
	// companyResearch_ //
	//////////////////////


	/**	 The entity companyResearch_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CompanyResearch companyResearch_;

	/**	<br> The entity companyResearch_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:companyResearch_">Find the entity companyResearch_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyResearch_(Wrap<CompanyResearch> w);

	public CompanyResearch getCompanyResearch_() {
		return companyResearch_;
	}

	public void setCompanyResearch_(CompanyResearch companyResearch_) {
		this.companyResearch_ = companyResearch_;
	}
	public static CompanyResearch staticSetCompanyResearch_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyResearchGenPage companyResearch_Init() {
		Wrap<CompanyResearch> companyResearch_Wrap = new Wrap<CompanyResearch>().var("companyResearch_");
		if(companyResearch_ == null) {
			_companyResearch_(companyResearch_Wrap);
			Optional.ofNullable(companyResearch_Wrap.getO()).ifPresent(o -> {
				setCompanyResearch_(o);
			});
		}
		return (CompanyResearchGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = CompanyResearchGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyResearchGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (CompanyResearchGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return CompanyResearchGenPage.staticSearchId(siteRequest_, CompanyResearchGenPage.staticSetId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchGenPage&fq=entiteVar_enUS_indexed_string:pageUriCompanyResearch">Find the entity pageUriCompanyResearch in Solr</a>
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

	public Future<Void> promiseDeepCompanyResearchGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyResearchGenPage();
	}

	public Future<Void> promiseDeepCompanyResearchGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyResearchGenPage(promise2);
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

	public Future<Void> promiseCompanyResearchGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListCompanyResearch_Init();
				listCompanyResearchInit();
				companyResearchCountInit();
				companyResearch_Init();
				idInit();
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

	@Override public Future<Void> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyResearchGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyResearchGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseResultPage(siteRequest_);
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
			case "companyResearchCount":
				return oCompanyResearchGenPage.companyResearchCount;
			case "companyResearch_":
				return oCompanyResearchGenPage.companyResearch_;
			case "id":
				return oCompanyResearchGenPage.id;
			case "pageUriCompanyResearch":
				return oCompanyResearchGenPage.pageUriCompanyResearch;
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
				return super.relateBaseResultPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyResearchGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyResearchGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyResearch":
			return CompanyResearchGenPage.staticSetListCompanyResearch(siteRequest_, o);
		case "companyResearchCount":
			return CompanyResearchGenPage.staticSetCompanyResearchCount(siteRequest_, o);
		case "id":
			return CompanyResearchGenPage.staticSetId(siteRequest_, o);
		case "pageUriCompanyResearch":
			return CompanyResearchGenPage.staticSetPageUriCompanyResearch(siteRequest_, o);
			default:
				return BaseResultPage.staticSetBaseResultPage(entityVar,  siteRequest_, o);
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
		case "companyResearchCount":
			return CompanyResearchGenPage.staticSearchCompanyResearchCount(siteRequest_, (Integer)o);
		case "id":
			return CompanyResearchGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriCompanyResearch":
			return CompanyResearchGenPage.staticSearchPageUriCompanyResearch(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchBaseResultPage(entityVar,  siteRequest_, o);
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
		case "companyResearchCount":
			return CompanyResearchGenPage.staticSearchStrCompanyResearchCount(siteRequest_, (Integer)o);
		case "id":
			return CompanyResearchGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriCompanyResearch":
			return CompanyResearchGenPage.staticSearchStrPageUriCompanyResearch(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchStrBaseResultPage(entityVar,  siteRequest_, o);
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
		case "companyResearchCount":
			return CompanyResearchGenPage.staticSearchFqCompanyResearchCount(siteRequest_, o);
		case "id":
			return CompanyResearchGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriCompanyResearch":
			return CompanyResearchGenPage.staticSearchFqPageUriCompanyResearch(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyResearchGenPage";
	public static final String VAR_searchListCompanyResearch_ = "searchListCompanyResearch_";
	public static final String VAR_listCompanyResearch = "listCompanyResearch";
	public static final String VAR_companyResearchCount = "companyResearchCount";
	public static final String VAR_companyResearch_ = "companyResearch_";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriCompanyResearch = "pageUriCompanyResearch";

	public static final String DISPLAY_NAME_searchListCompanyResearch_ = "";
	public static final String DISPLAY_NAME_listCompanyResearch = "";
	public static final String DISPLAY_NAME_companyResearchCount = "";
	public static final String DISPLAY_NAME_companyResearch_ = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriCompanyResearch = "";

	public static String displayNameForClass(String var) {
		return CompanyResearchGenPage.displayNameCompanyResearchGenPage(var);
	}
	public static String displayNameCompanyResearchGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyResearch_:
			return DISPLAY_NAME_searchListCompanyResearch_;
		case VAR_listCompanyResearch:
			return DISPLAY_NAME_listCompanyResearch;
		case VAR_companyResearchCount:
			return DISPLAY_NAME_companyResearchCount;
		case VAR_companyResearch_:
			return DISPLAY_NAME_companyResearch_;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriCompanyResearch:
			return DISPLAY_NAME_pageUriCompanyResearch;
		default:
			return BaseResultPage.displayNameBaseResultPage(var);
		}
	}
}
