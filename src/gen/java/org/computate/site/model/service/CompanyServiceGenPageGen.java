package org.computate.site.model.service;

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
import org.computate.site.model.service.CompanyService;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyServiceGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyServiceGenPageGen into the class CompanyServiceGenPage. 
 * </li>
 * <h3>About the CompanyServiceGenPage class and it's generated class CompanyServiceGenPageGen&lt;BaseResultPage&gt;: </h3>extends CompanyServiceGenPageGen
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
 * extends CompanyServiceGenPageGen<BaseResultPage>
 * <p>This <code>class CompanyServiceGenPage extends CompanyServiceGenPageGen&lt;BaseResultPage&gt;</code>, which means it extends a newly generated CompanyServiceGenPageGen. 
 * The generated <code>class CompanyServiceGenPageGen extends BaseResultPage</code> which means that CompanyServiceGenPage extends CompanyServiceGenPageGen which extends BaseResultPage. 
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
public abstract class CompanyServiceGenPageGen<DEV> extends BaseResultPage {
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

	/////////////////////////
	// companyServiceCount //
	/////////////////////////


	/**	 The entity companyServiceCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer companyServiceCount;

	/**	<br> The entity companyServiceCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServiceGenPage&fq=entiteVar_enUS_indexed_string:companyServiceCount">Find the entity companyServiceCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyServiceCount(Wrap<Integer> w);

	public Integer getCompanyServiceCount() {
		return companyServiceCount;
	}

	public void setCompanyServiceCount(Integer companyServiceCount) {
		this.companyServiceCount = companyServiceCount;
	}
	@JsonIgnore
	public void setCompanyServiceCount(String o) {
		this.companyServiceCount = CompanyServiceGenPage.staticSetCompanyServiceCount(siteRequest_, o);
	}
	public static Integer staticSetCompanyServiceCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyServiceGenPage companyServiceCountInit() {
		Wrap<Integer> companyServiceCountWrap = new Wrap<Integer>().var("companyServiceCount");
		if(companyServiceCount == null) {
			_companyServiceCount(companyServiceCountWrap);
			Optional.ofNullable(companyServiceCountWrap.getO()).ifPresent(o -> {
				setCompanyServiceCount(o);
			});
		}
		return (CompanyServiceGenPage)this;
	}

	public static Integer staticSearchCompanyServiceCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrCompanyServiceCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCompanyServiceCount(SiteRequest siteRequest_, String o) {
		return CompanyServiceGenPage.staticSearchCompanyServiceCount(siteRequest_, CompanyServiceGenPage.staticSetCompanyServiceCount(siteRequest_, o)).toString();
	}

	/////////////////////
	// companyService_ //
	/////////////////////


	/**	 The entity companyService_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CompanyService companyService_;

	/**	<br> The entity companyService_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServiceGenPage&fq=entiteVar_enUS_indexed_string:companyService_">Find the entity companyService_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyService_(Wrap<CompanyService> w);

	public CompanyService getCompanyService_() {
		return companyService_;
	}

	public void setCompanyService_(CompanyService companyService_) {
		this.companyService_ = companyService_;
	}
	public static CompanyService staticSetCompanyService_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyServiceGenPage companyService_Init() {
		Wrap<CompanyService> companyService_Wrap = new Wrap<CompanyService>().var("companyService_");
		if(companyService_ == null) {
			_companyService_(companyService_Wrap);
			Optional.ofNullable(companyService_Wrap.getO()).ifPresent(o -> {
				setCompanyService_(o);
			});
		}
		return (CompanyServiceGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServiceGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = CompanyServiceGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyServiceGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (CompanyServiceGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return CompanyServiceGenPage.staticSearchId(siteRequest_, CompanyServiceGenPage.staticSetId(siteRequest_, o)).toString();
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

	public Future<Void> promiseDeepCompanyServiceGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyServiceGenPage();
	}

	public Future<Void> promiseDeepCompanyServiceGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyServiceGenPage(promise2);
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

	public Future<Void> promiseCompanyServiceGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListCompanyService_Init();
				listCompanyServiceInit();
				companyServiceCountInit();
				companyService_Init();
				idInit();
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

	@Override public Future<Void> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyServiceGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyServiceGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseResultPage(siteRequest_);
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
			case "companyServiceCount":
				return oCompanyServiceGenPage.companyServiceCount;
			case "companyService_":
				return oCompanyServiceGenPage.companyService_;
			case "id":
				return oCompanyServiceGenPage.id;
			case "pageUriCompanyService":
				return oCompanyServiceGenPage.pageUriCompanyService;
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
				return super.relateBaseResultPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyServiceGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyServiceGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyService":
			return CompanyServiceGenPage.staticSetListCompanyService(siteRequest_, o);
		case "companyServiceCount":
			return CompanyServiceGenPage.staticSetCompanyServiceCount(siteRequest_, o);
		case "id":
			return CompanyServiceGenPage.staticSetId(siteRequest_, o);
		case "pageUriCompanyService":
			return CompanyServiceGenPage.staticSetPageUriCompanyService(siteRequest_, o);
			default:
				return BaseResultPage.staticSetBaseResultPage(entityVar,  siteRequest_, o);
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
		case "companyServiceCount":
			return CompanyServiceGenPage.staticSearchCompanyServiceCount(siteRequest_, (Integer)o);
		case "id":
			return CompanyServiceGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriCompanyService":
			return CompanyServiceGenPage.staticSearchPageUriCompanyService(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchBaseResultPage(entityVar,  siteRequest_, o);
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
		case "companyServiceCount":
			return CompanyServiceGenPage.staticSearchStrCompanyServiceCount(siteRequest_, (Integer)o);
		case "id":
			return CompanyServiceGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriCompanyService":
			return CompanyServiceGenPage.staticSearchStrPageUriCompanyService(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchStrBaseResultPage(entityVar,  siteRequest_, o);
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
		case "companyServiceCount":
			return CompanyServiceGenPage.staticSearchFqCompanyServiceCount(siteRequest_, o);
		case "id":
			return CompanyServiceGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriCompanyService":
			return CompanyServiceGenPage.staticSearchFqPageUriCompanyService(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyServiceGenPage";
	public static final String VAR_searchListCompanyService_ = "searchListCompanyService_";
	public static final String VAR_listCompanyService = "listCompanyService";
	public static final String VAR_companyServiceCount = "companyServiceCount";
	public static final String VAR_companyService_ = "companyService_";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriCompanyService = "pageUriCompanyService";

	public static final String DISPLAY_NAME_searchListCompanyService_ = "";
	public static final String DISPLAY_NAME_listCompanyService = "";
	public static final String DISPLAY_NAME_companyServiceCount = "";
	public static final String DISPLAY_NAME_companyService_ = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriCompanyService = "";

	public static String displayNameForClass(String var) {
		return CompanyServiceGenPage.displayNameCompanyServiceGenPage(var);
	}
	public static String displayNameCompanyServiceGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyService_:
			return DISPLAY_NAME_searchListCompanyService_;
		case VAR_listCompanyService:
			return DISPLAY_NAME_listCompanyService;
		case VAR_companyServiceCount:
			return DISPLAY_NAME_companyServiceCount;
		case VAR_companyService_:
			return DISPLAY_NAME_companyService_;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriCompanyService:
			return DISPLAY_NAME_pageUriCompanyService;
		default:
			return BaseResultPage.displayNameBaseResultPage(var);
		}
	}
}
