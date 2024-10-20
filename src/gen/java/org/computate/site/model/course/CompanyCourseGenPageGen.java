package org.computate.site.model.course;

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
import org.computate.site.model.course.CompanyCourse;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyCourseGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyCourseGenPageGen into the class CompanyCourseGenPage. 
 * </li>
 * <h3>About the CompanyCourseGenPage class and it's generated class CompanyCourseGenPageGen&lt;BaseResultPage&gt;: </h3>extends CompanyCourseGenPageGen
 * <p>
 * This Java class extends a generated Java class CompanyCourseGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourseGenPage">Find the class CompanyCourseGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyCourseGenPageGen<BaseResultPage>
 * <p>This <code>class CompanyCourseGenPage extends CompanyCourseGenPageGen&lt;BaseResultPage&gt;</code>, which means it extends a newly generated CompanyCourseGenPageGen. 
 * The generated <code>class CompanyCourseGenPageGen extends BaseResultPage</code> which means that CompanyCourseGenPage extends CompanyCourseGenPageGen which extends BaseResultPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyCourseGenPage class will inherit the helpful inherited class comments from the super class CompanyCourseGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyCourseGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class CompanyCourseGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourseGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.course in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.course&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyCourseGenPageGen<DEV> extends BaseResultPage {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyCourseGenPage.class);

	//////////////////////////////
	// searchListCompanyCourse_ //
	//////////////////////////////


	/**	 The entity searchListCompanyCourse_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<CompanyCourse> searchListCompanyCourse_;

	/**	<br> The entity searchListCompanyCourse_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourseGenPage&fq=entiteVar_enUS_indexed_string:searchListCompanyCourse_">Find the entity searchListCompanyCourse_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListCompanyCourse_(Wrap<SearchList<CompanyCourse>> w);

	public SearchList<CompanyCourse> getSearchListCompanyCourse_() {
		return searchListCompanyCourse_;
	}

	public void setSearchListCompanyCourse_(SearchList<CompanyCourse> searchListCompanyCourse_) {
		this.searchListCompanyCourse_ = searchListCompanyCourse_;
	}
	public static SearchList<CompanyCourse> staticSetSearchListCompanyCourse_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyCourseGenPage searchListCompanyCourse_Init() {
		Wrap<SearchList<CompanyCourse>> searchListCompanyCourse_Wrap = new Wrap<SearchList<CompanyCourse>>().var("searchListCompanyCourse_");
		if(searchListCompanyCourse_ == null) {
			_searchListCompanyCourse_(searchListCompanyCourse_Wrap);
			Optional.ofNullable(searchListCompanyCourse_Wrap.getO()).ifPresent(o -> {
				setSearchListCompanyCourse_(o);
			});
		}
		return (CompanyCourseGenPage)this;
	}

	///////////////////////
	// listCompanyCourse //
	///////////////////////


	/**	 The entity listCompanyCourse
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listCompanyCourse = new JsonArray();

	/**	<br> The entity listCompanyCourse
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourseGenPage&fq=entiteVar_enUS_indexed_string:listCompanyCourse">Find the entity listCompanyCourse in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listCompanyCourse(JsonArray l);

	public JsonArray getListCompanyCourse() {
		return listCompanyCourse;
	}

	public void setListCompanyCourse(JsonArray listCompanyCourse) {
		this.listCompanyCourse = listCompanyCourse;
	}
	@JsonIgnore
	public void setListCompanyCourse(String o) {
		this.listCompanyCourse = CompanyCourseGenPage.staticSetListCompanyCourse(siteRequest_, o);
	}
	public static JsonArray staticSetListCompanyCourse(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected CompanyCourseGenPage listCompanyCourseInit() {
		_listCompanyCourse(listCompanyCourse);
		return (CompanyCourseGenPage)this;
	}

	public static String staticSearchListCompanyCourse(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListCompanyCourse(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListCompanyCourse(SiteRequest siteRequest_, String o) {
		return CompanyCourseGenPage.staticSearchListCompanyCourse(siteRequest_, CompanyCourseGenPage.staticSetListCompanyCourse(siteRequest_, o)).toString();
	}

	////////////////////////
	// companyCourseCount //
	////////////////////////


	/**	 The entity companyCourseCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer companyCourseCount;

	/**	<br> The entity companyCourseCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourseGenPage&fq=entiteVar_enUS_indexed_string:companyCourseCount">Find the entity companyCourseCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyCourseCount(Wrap<Integer> w);

	public Integer getCompanyCourseCount() {
		return companyCourseCount;
	}

	public void setCompanyCourseCount(Integer companyCourseCount) {
		this.companyCourseCount = companyCourseCount;
	}
	@JsonIgnore
	public void setCompanyCourseCount(String o) {
		this.companyCourseCount = CompanyCourseGenPage.staticSetCompanyCourseCount(siteRequest_, o);
	}
	public static Integer staticSetCompanyCourseCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyCourseGenPage companyCourseCountInit() {
		Wrap<Integer> companyCourseCountWrap = new Wrap<Integer>().var("companyCourseCount");
		if(companyCourseCount == null) {
			_companyCourseCount(companyCourseCountWrap);
			Optional.ofNullable(companyCourseCountWrap.getO()).ifPresent(o -> {
				setCompanyCourseCount(o);
			});
		}
		return (CompanyCourseGenPage)this;
	}

	public static Integer staticSearchCompanyCourseCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrCompanyCourseCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCompanyCourseCount(SiteRequest siteRequest_, String o) {
		return CompanyCourseGenPage.staticSearchCompanyCourseCount(siteRequest_, CompanyCourseGenPage.staticSetCompanyCourseCount(siteRequest_, o)).toString();
	}

	////////////////////
	// companyCourse_ //
	////////////////////


	/**	 The entity companyCourse_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CompanyCourse companyCourse_;

	/**	<br> The entity companyCourse_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourseGenPage&fq=entiteVar_enUS_indexed_string:companyCourse_">Find the entity companyCourse_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyCourse_(Wrap<CompanyCourse> w);

	public CompanyCourse getCompanyCourse_() {
		return companyCourse_;
	}

	public void setCompanyCourse_(CompanyCourse companyCourse_) {
		this.companyCourse_ = companyCourse_;
	}
	public static CompanyCourse staticSetCompanyCourse_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyCourseGenPage companyCourse_Init() {
		Wrap<CompanyCourse> companyCourse_Wrap = new Wrap<CompanyCourse>().var("companyCourse_");
		if(companyCourse_ == null) {
			_companyCourse_(companyCourse_Wrap);
			Optional.ofNullable(companyCourse_Wrap.getO()).ifPresent(o -> {
				setCompanyCourse_(o);
			});
		}
		return (CompanyCourseGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourseGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = CompanyCourseGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourseGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (CompanyCourseGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return CompanyCourseGenPage.staticSearchId(siteRequest_, CompanyCourseGenPage.staticSetId(siteRequest_, o)).toString();
	}

	//////////////////////////
	// pageUriCompanyCourse //
	//////////////////////////


	/**	 The entity pageUriCompanyCourse
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriCompanyCourse;

	/**	<br> The entity pageUriCompanyCourse
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourseGenPage&fq=entiteVar_enUS_indexed_string:pageUriCompanyCourse">Find the entity pageUriCompanyCourse in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriCompanyCourse(Wrap<String> c);

	public String getPageUriCompanyCourse() {
		return pageUriCompanyCourse;
	}
	public void setPageUriCompanyCourse(String o) {
		this.pageUriCompanyCourse = CompanyCourseGenPage.staticSetPageUriCompanyCourse(siteRequest_, o);
	}
	public static String staticSetPageUriCompanyCourse(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourseGenPage pageUriCompanyCourseInit() {
		Wrap<String> pageUriCompanyCourseWrap = new Wrap<String>().var("pageUriCompanyCourse");
		if(pageUriCompanyCourse == null) {
			_pageUriCompanyCourse(pageUriCompanyCourseWrap);
			Optional.ofNullable(pageUriCompanyCourseWrap.getO()).ifPresent(o -> {
				setPageUriCompanyCourse(o);
			});
		}
		return (CompanyCourseGenPage)this;
	}

	public static String staticSearchPageUriCompanyCourse(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriCompanyCourse(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriCompanyCourse(SiteRequest siteRequest_, String o) {
		return CompanyCourseGenPage.staticSearchPageUriCompanyCourse(siteRequest_, CompanyCourseGenPage.staticSetPageUriCompanyCourse(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepCompanyCourseGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyCourseGenPage();
	}

	public Future<Void> promiseDeepCompanyCourseGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyCourseGenPage(promise2);
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

	public Future<Void> promiseCompanyCourseGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListCompanyCourse_Init();
				listCompanyCourseInit();
				companyCourseCountInit();
				companyCourse_Init();
				idInit();
				pageUriCompanyCourseInit();
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
		return promiseDeepCompanyCourseGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyCourseGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseResultPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyCourseGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyCourseGenPage(v);
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
	public Object obtainCompanyCourseGenPage(String var) {
		CompanyCourseGenPage oCompanyCourseGenPage = (CompanyCourseGenPage)this;
		switch(var) {
			case "searchListCompanyCourse_":
				return oCompanyCourseGenPage.searchListCompanyCourse_;
			case "listCompanyCourse":
				return oCompanyCourseGenPage.listCompanyCourse;
			case "companyCourseCount":
				return oCompanyCourseGenPage.companyCourseCount;
			case "companyCourse_":
				return oCompanyCourseGenPage.companyCourse_;
			case "id":
				return oCompanyCourseGenPage.id;
			case "pageUriCompanyCourse":
				return oCompanyCourseGenPage.pageUriCompanyCourse;
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
				o = relateCompanyCourseGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyCourseGenPage(String var, Object val) {
		CompanyCourseGenPage oCompanyCourseGenPage = (CompanyCourseGenPage)this;
		switch(var) {
			default:
				return super.relateBaseResultPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyCourseGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyCourseGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyCourse":
			return CompanyCourseGenPage.staticSetListCompanyCourse(siteRequest_, o);
		case "companyCourseCount":
			return CompanyCourseGenPage.staticSetCompanyCourseCount(siteRequest_, o);
		case "id":
			return CompanyCourseGenPage.staticSetId(siteRequest_, o);
		case "pageUriCompanyCourse":
			return CompanyCourseGenPage.staticSetPageUriCompanyCourse(siteRequest_, o);
			default:
				return BaseResultPage.staticSetBaseResultPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyCourseGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyCourseGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyCourse":
			return CompanyCourseGenPage.staticSearchListCompanyCourse(siteRequest_, (JsonArray)o);
		case "companyCourseCount":
			return CompanyCourseGenPage.staticSearchCompanyCourseCount(siteRequest_, (Integer)o);
		case "id":
			return CompanyCourseGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriCompanyCourse":
			return CompanyCourseGenPage.staticSearchPageUriCompanyCourse(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchBaseResultPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyCourseGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyCourseGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyCourse":
			return CompanyCourseGenPage.staticSearchStrListCompanyCourse(siteRequest_, (String)o);
		case "companyCourseCount":
			return CompanyCourseGenPage.staticSearchStrCompanyCourseCount(siteRequest_, (Integer)o);
		case "id":
			return CompanyCourseGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriCompanyCourse":
			return CompanyCourseGenPage.staticSearchStrPageUriCompanyCourse(siteRequest_, (String)o);
			default:
				return BaseResultPage.staticSearchStrBaseResultPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyCourseGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyCourseGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyCourse":
			return CompanyCourseGenPage.staticSearchFqListCompanyCourse(siteRequest_, o);
		case "companyCourseCount":
			return CompanyCourseGenPage.staticSearchFqCompanyCourseCount(siteRequest_, o);
		case "id":
			return CompanyCourseGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriCompanyCourse":
			return CompanyCourseGenPage.staticSearchFqPageUriCompanyCourse(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyCourseGenPage";
	public static final String VAR_searchListCompanyCourse_ = "searchListCompanyCourse_";
	public static final String VAR_listCompanyCourse = "listCompanyCourse";
	public static final String VAR_companyCourseCount = "companyCourseCount";
	public static final String VAR_companyCourse_ = "companyCourse_";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriCompanyCourse = "pageUriCompanyCourse";

	public static final String DISPLAY_NAME_searchListCompanyCourse_ = "";
	public static final String DISPLAY_NAME_listCompanyCourse = "";
	public static final String DISPLAY_NAME_companyCourseCount = "";
	public static final String DISPLAY_NAME_companyCourse_ = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriCompanyCourse = "";

	public static String displayNameForClass(String var) {
		return CompanyCourseGenPage.displayNameCompanyCourseGenPage(var);
	}
	public static String displayNameCompanyCourseGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyCourse_:
			return DISPLAY_NAME_searchListCompanyCourse_;
		case VAR_listCompanyCourse:
			return DISPLAY_NAME_listCompanyCourse;
		case VAR_companyCourseCount:
			return DISPLAY_NAME_companyCourseCount;
		case VAR_companyCourse_:
			return DISPLAY_NAME_companyCourse_;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriCompanyCourse:
			return DISPLAY_NAME_pageUriCompanyCourse;
		default:
			return BaseResultPage.displayNameBaseResultPage(var);
		}
	}
}
