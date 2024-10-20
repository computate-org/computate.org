package org.computate.site.model.casestudy;

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
import org.computate.site.model.casestudy.CaseStudy;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CaseStudyGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CaseStudyGenPageGen into the class CaseStudyGenPage. 
 * </li>
 * <h3>About the CaseStudyGenPage class and it's generated class CaseStudyGenPageGen&lt;BaseResultPage&gt;: </h3>extends CaseStudyGenPageGen
 * <p>
 * This Java class extends a generated Java class CaseStudyGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.casestudy.CaseStudyGenPage">Find the class CaseStudyGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CaseStudyGenPageGen<BaseResultPage>
 * <p>This <code>class CaseStudyGenPage extends CaseStudyGenPageGen&lt;BaseResultPage&gt;</code>, which means it extends a newly generated CaseStudyGenPageGen. 
 * The generated <code>class CaseStudyGenPageGen extends BaseResultPage</code> which means that CaseStudyGenPage extends CaseStudyGenPageGen which extends BaseResultPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CaseStudyGenPage class will inherit the helpful inherited class comments from the super class CaseStudyGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CaseStudyGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class CaseStudyGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.casestudy.CaseStudyGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.casestudy in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.casestudy&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CaseStudyGenPageGen<DEV> extends BaseResultPage {
	protected static final Logger LOG = LoggerFactory.getLogger(CaseStudyGenPage.class);

	//////////////////////////
	// searchListCaseStudy_ //
	//////////////////////////


	/**	 The entity searchListCaseStudy_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<CaseStudy> searchListCaseStudy_;

	/**	<br> The entity searchListCaseStudy_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.casestudy.CaseStudyGenPage&fq=entiteVar_enUS_indexed_string:searchListCaseStudy_">Find the entity searchListCaseStudy_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListCaseStudy_(Wrap<SearchList<CaseStudy>> w);

	public SearchList<CaseStudy> getSearchListCaseStudy_() {
		return searchListCaseStudy_;
	}

	public void setSearchListCaseStudy_(SearchList<CaseStudy> searchListCaseStudy_) {
		this.searchListCaseStudy_ = searchListCaseStudy_;
	}
	public static SearchList<CaseStudy> staticSetSearchListCaseStudy_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CaseStudyGenPage searchListCaseStudy_Init() {
		Wrap<SearchList<CaseStudy>> searchListCaseStudy_Wrap = new Wrap<SearchList<CaseStudy>>().var("searchListCaseStudy_");
		if(searchListCaseStudy_ == null) {
			_searchListCaseStudy_(searchListCaseStudy_Wrap);
			Optional.ofNullable(searchListCaseStudy_Wrap.getO()).ifPresent(o -> {
				setSearchListCaseStudy_(o);
			});
		}
		return (CaseStudyGenPage)this;
	}

	///////////////////
	// listCaseStudy //
	///////////////////


	/**	 The entity listCaseStudy
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listCaseStudy = new JsonArray();

	/**	<br> The entity listCaseStudy
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.casestudy.CaseStudyGenPage&fq=entiteVar_enUS_indexed_string:listCaseStudy">Find the entity listCaseStudy in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listCaseStudy(JsonArray l);

	public JsonArray getListCaseStudy() {
		return listCaseStudy;
	}

	public void setListCaseStudy(JsonArray listCaseStudy) {
		this.listCaseStudy = listCaseStudy;
	}
	@JsonIgnore
	public void setListCaseStudy(String o) {
		this.listCaseStudy = CaseStudyGenPage.staticSetListCaseStudy(siteRequest_, o);
	}
	public static JsonArray staticSetListCaseStudy(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected CaseStudyGenPage listCaseStudyInit() {
		_listCaseStudy(listCaseStudy);
		return (CaseStudyGenPage)this;
	}

	public static String staticSearchListCaseStudy(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListCaseStudy(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListCaseStudy(SiteRequest siteRequest_, String o) {
		return CaseStudyGenPage.staticSearchListCaseStudy(siteRequest_, CaseStudyGenPage.staticSetListCaseStudy(siteRequest_, o)).toString();
	}

	////////////////////
	// caseStudyCount //
	////////////////////


	/**	 The entity caseStudyCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer caseStudyCount;

	/**	<br> The entity caseStudyCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.casestudy.CaseStudyGenPage&fq=entiteVar_enUS_indexed_string:caseStudyCount">Find the entity caseStudyCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _caseStudyCount(Wrap<Integer> w);

	public Integer getCaseStudyCount() {
		return caseStudyCount;
	}

	public void setCaseStudyCount(Integer caseStudyCount) {
		this.caseStudyCount = caseStudyCount;
	}
	@JsonIgnore
	public void setCaseStudyCount(String o) {
		this.caseStudyCount = CaseStudyGenPage.staticSetCaseStudyCount(siteRequest_, o);
	}
	public static Integer staticSetCaseStudyCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CaseStudyGenPage caseStudyCountInit() {
		Wrap<Integer> caseStudyCountWrap = new Wrap<Integer>().var("caseStudyCount");
		if(caseStudyCount == null) {
			_caseStudyCount(caseStudyCountWrap);
			Optional.ofNullable(caseStudyCountWrap.getO()).ifPresent(o -> {
				setCaseStudyCount(o);
			});
		}
		return (CaseStudyGenPage)this;
	}

	public static Integer staticSearchCaseStudyCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrCaseStudyCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCaseStudyCount(SiteRequest siteRequest_, String o) {
		return CaseStudyGenPage.staticSearchCaseStudyCount(siteRequest_, CaseStudyGenPage.staticSetCaseStudyCount(siteRequest_, o)).toString();
	}

	////////////////
	// caseStudy_ //
	////////////////


	/**	 The entity caseStudy_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CaseStudy caseStudy_;

	/**	<br> The entity caseStudy_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.casestudy.CaseStudyGenPage&fq=entiteVar_enUS_indexed_string:caseStudy_">Find the entity caseStudy_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _caseStudy_(Wrap<CaseStudy> w);

	public CaseStudy getCaseStudy_() {
		return caseStudy_;
	}

	public void setCaseStudy_(CaseStudy caseStudy_) {
		this.caseStudy_ = caseStudy_;
	}
	public static CaseStudy staticSetCaseStudy_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CaseStudyGenPage caseStudy_Init() {
		Wrap<CaseStudy> caseStudy_Wrap = new Wrap<CaseStudy>().var("caseStudy_");
		if(caseStudy_ == null) {
			_caseStudy_(caseStudy_Wrap);
			Optional.ofNullable(caseStudy_Wrap.getO()).ifPresent(o -> {
				setCaseStudy_(o);
			});
		}
		return (CaseStudyGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.casestudy.CaseStudyGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = CaseStudyGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CaseStudyGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (CaseStudyGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return CaseStudyGenPage.staticSearchId(siteRequest_, CaseStudyGenPage.staticSetId(siteRequest_, o)).toString();
	}

	//////////////////////
	// pageUriCaseStudy //
	//////////////////////


	/**	 The entity pageUriCaseStudy
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriCaseStudy;

	/**	<br> The entity pageUriCaseStudy
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.casestudy.CaseStudyGenPage&fq=entiteVar_enUS_indexed_string:pageUriCaseStudy">Find the entity pageUriCaseStudy in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriCaseStudy(Wrap<String> c);

	public String getPageUriCaseStudy() {
		return pageUriCaseStudy;
	}
	public void setPageUriCaseStudy(String o) {
		this.pageUriCaseStudy = CaseStudyGenPage.staticSetPageUriCaseStudy(siteRequest_, o);
	}
	public static String staticSetPageUriCaseStudy(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CaseStudyGenPage pageUriCaseStudyInit() {
		Wrap<String> pageUriCaseStudyWrap = new Wrap<String>().var("pageUriCaseStudy");
		if(pageUriCaseStudy == null) {
			_pageUriCaseStudy(pageUriCaseStudyWrap);
			Optional.ofNullable(pageUriCaseStudyWrap.getO()).ifPresent(o -> {
				setPageUriCaseStudy(o);
			});
		}
		return (CaseStudyGenPage)this;
	}

	public static String staticSearchPageUriCaseStudy(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriCaseStudy(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriCaseStudy(SiteRequest siteRequest_, String o) {
		return CaseStudyGenPage.staticSearchPageUriCaseStudy(siteRequest_, CaseStudyGenPage.staticSetPageUriCaseStudy(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepCaseStudyGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCaseStudyGenPage();
	}

	public Future<Void> promiseDeepCaseStudyGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCaseStudyGenPage(promise2);
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

	public Future<Void> promiseCaseStudyGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListCaseStudy_Init();
				listCaseStudyInit();
				caseStudyCountInit();
				caseStudy_Init();
				idInit();
				pageUriCaseStudyInit();
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
		return promiseDeepCaseStudyGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCaseStudyGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseResultPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCaseStudyGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCaseStudyGenPage(v);
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
	public Object obtainCaseStudyGenPage(String var) {
		CaseStudyGenPage oCaseStudyGenPage = (CaseStudyGenPage)this;
		switch(var) {
			case "searchListCaseStudy_":
				return oCaseStudyGenPage.searchListCaseStudy_;
			case "listCaseStudy":
				return oCaseStudyGenPage.listCaseStudy;
			case "caseStudyCount":
				return oCaseStudyGenPage.caseStudyCount;
			case "caseStudy_":
				return oCaseStudyGenPage.caseStudy_;
			case "id":
				return oCaseStudyGenPage.id;
			case "pageUriCaseStudy":
				return oCaseStudyGenPage.pageUriCaseStudy;
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
				o = relateCaseStudyGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCaseStudyGenPage(String var, Object val) {
		CaseStudyGenPage oCaseStudyGenPage = (CaseStudyGenPage)this;
		switch(var) {
			default:
				return super.relateBaseResultPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCaseStudyGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCaseStudyGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCaseStudy":
			return CaseStudyGenPage.staticSetListCaseStudy(siteRequest_, o);
		case "caseStudyCount":
			return CaseStudyGenPage.staticSetCaseStudyCount(siteRequest_, o);
		case "id":
			return CaseStudyGenPage.staticSetId(siteRequest_, o);
		case "pageUriCaseStudy":
			return CaseStudyGenPage.staticSetPageUriCaseStudy(siteRequest_, o);
			default:
				return BaseResultPage.staticSetBaseResultPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCaseStudyGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCaseStudyGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCaseStudy":
			return CaseStudyGenPage.staticSearchListCaseStudy(siteRequest_, (JsonArray)o);
		case "caseStudyCount":
			return CaseStudyGenPage.staticSearchCaseStudyCount(siteRequest_, (Integer)o);
		case "id":
			return CaseStudyGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriCaseStudy":
			return CaseStudyGenPage.staticSearchPageUriCaseStudy(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchBaseResultPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCaseStudyGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCaseStudyGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCaseStudy":
			return CaseStudyGenPage.staticSearchStrListCaseStudy(siteRequest_, (String)o);
		case "caseStudyCount":
			return CaseStudyGenPage.staticSearchStrCaseStudyCount(siteRequest_, (Integer)o);
		case "id":
			return CaseStudyGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriCaseStudy":
			return CaseStudyGenPage.staticSearchStrPageUriCaseStudy(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchStrBaseResultPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCaseStudyGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCaseStudyGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCaseStudy":
			return CaseStudyGenPage.staticSearchFqListCaseStudy(siteRequest_, o);
		case "caseStudyCount":
			return CaseStudyGenPage.staticSearchFqCaseStudyCount(siteRequest_, o);
		case "id":
			return CaseStudyGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriCaseStudy":
			return CaseStudyGenPage.staticSearchFqPageUriCaseStudy(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CaseStudyGenPage";
	public static final String VAR_searchListCaseStudy_ = "searchListCaseStudy_";
	public static final String VAR_listCaseStudy = "listCaseStudy";
	public static final String VAR_caseStudyCount = "caseStudyCount";
	public static final String VAR_caseStudy_ = "caseStudy_";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriCaseStudy = "pageUriCaseStudy";

	public static final String DISPLAY_NAME_searchListCaseStudy_ = "";
	public static final String DISPLAY_NAME_listCaseStudy = "";
	public static final String DISPLAY_NAME_caseStudyCount = "";
	public static final String DISPLAY_NAME_caseStudy_ = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriCaseStudy = "";

	public static String displayNameForClass(String var) {
		return CaseStudyGenPage.displayNameCaseStudyGenPage(var);
	}
	public static String displayNameCaseStudyGenPage(String var) {
		switch(var) {
		case VAR_searchListCaseStudy_:
			return DISPLAY_NAME_searchListCaseStudy_;
		case VAR_listCaseStudy:
			return DISPLAY_NAME_listCaseStudy;
		case VAR_caseStudyCount:
			return DISPLAY_NAME_caseStudyCount;
		case VAR_caseStudy_:
			return DISPLAY_NAME_caseStudy_;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriCaseStudy:
			return DISPLAY_NAME_pageUriCaseStudy;
		default:
			return BaseResultPage.displayNameBaseResultPage(var);
		}
	}
}
