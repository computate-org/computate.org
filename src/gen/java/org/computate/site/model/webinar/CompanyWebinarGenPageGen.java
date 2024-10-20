package org.computate.site.model.webinar;

import org.computate.site.request.SiteRequest;
import org.computate.site.model.BaseModelPage;
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
import org.computate.site.model.webinar.CompanyWebinar;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyWebinarGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyWebinarGenPageGen into the class CompanyWebinarGenPage. 
 * </li>
 * <h3>About the CompanyWebinarGenPage class and it's generated class CompanyWebinarGenPageGen&lt;BaseModelPage&gt;: </h3>extends CompanyWebinarGenPageGen
 * <p>
 * This Java class extends a generated Java class CompanyWebinarGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinarGenPage">Find the class CompanyWebinarGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyWebinarGenPageGen<BaseModelPage>
 * <p>This <code>class CompanyWebinarGenPage extends CompanyWebinarGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated CompanyWebinarGenPageGen. 
 * The generated <code>class CompanyWebinarGenPageGen extends BaseModelPage</code> which means that CompanyWebinarGenPage extends CompanyWebinarGenPageGen which extends BaseModelPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyWebinarGenPage class will inherit the helpful inherited class comments from the super class CompanyWebinarGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyWebinarGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class CompanyWebinarGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinarGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.webinar in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.webinar&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyWebinarGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyWebinarGenPage.class);

	///////////////////////////////
	// searchListCompanyWebinar_ //
	///////////////////////////////


	/**	 The entity searchListCompanyWebinar_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<CompanyWebinar> searchListCompanyWebinar_;

	/**	<br> The entity searchListCompanyWebinar_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinarGenPage&fq=entiteVar_enUS_indexed_string:searchListCompanyWebinar_">Find the entity searchListCompanyWebinar_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListCompanyWebinar_(Wrap<SearchList<CompanyWebinar>> w);

	public SearchList<CompanyWebinar> getSearchListCompanyWebinar_() {
		return searchListCompanyWebinar_;
	}

	public void setSearchListCompanyWebinar_(SearchList<CompanyWebinar> searchListCompanyWebinar_) {
		this.searchListCompanyWebinar_ = searchListCompanyWebinar_;
	}
	public static SearchList<CompanyWebinar> staticSetSearchListCompanyWebinar_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyWebinarGenPage searchListCompanyWebinar_Init() {
		Wrap<SearchList<CompanyWebinar>> searchListCompanyWebinar_Wrap = new Wrap<SearchList<CompanyWebinar>>().var("searchListCompanyWebinar_");
		if(searchListCompanyWebinar_ == null) {
			_searchListCompanyWebinar_(searchListCompanyWebinar_Wrap);
			Optional.ofNullable(searchListCompanyWebinar_Wrap.getO()).ifPresent(o -> {
				setSearchListCompanyWebinar_(o);
			});
		}
		return (CompanyWebinarGenPage)this;
	}

	////////////////////////
	// listCompanyWebinar //
	////////////////////////


	/**	 The entity listCompanyWebinar
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listCompanyWebinar = new JsonArray();

	/**	<br> The entity listCompanyWebinar
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinarGenPage&fq=entiteVar_enUS_indexed_string:listCompanyWebinar">Find the entity listCompanyWebinar in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listCompanyWebinar(JsonArray l);

	public JsonArray getListCompanyWebinar() {
		return listCompanyWebinar;
	}

	public void setListCompanyWebinar(JsonArray listCompanyWebinar) {
		this.listCompanyWebinar = listCompanyWebinar;
	}
	@JsonIgnore
	public void setListCompanyWebinar(String o) {
		this.listCompanyWebinar = CompanyWebinarGenPage.staticSetListCompanyWebinar(siteRequest_, o);
	}
	public static JsonArray staticSetListCompanyWebinar(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected CompanyWebinarGenPage listCompanyWebinarInit() {
		_listCompanyWebinar(listCompanyWebinar);
		return (CompanyWebinarGenPage)this;
	}

	public static String staticSearchListCompanyWebinar(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListCompanyWebinar(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListCompanyWebinar(SiteRequest siteRequest_, String o) {
		return CompanyWebinarGenPage.staticSearchListCompanyWebinar(siteRequest_, CompanyWebinarGenPage.staticSetListCompanyWebinar(siteRequest_, o)).toString();
	}

	/////////////////////////
	// companyWebinarCount //
	/////////////////////////


	/**	 The entity companyWebinarCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer companyWebinarCount;

	/**	<br> The entity companyWebinarCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinarGenPage&fq=entiteVar_enUS_indexed_string:companyWebinarCount">Find the entity companyWebinarCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyWebinarCount(Wrap<Integer> w);

	public Integer getCompanyWebinarCount() {
		return companyWebinarCount;
	}

	public void setCompanyWebinarCount(Integer companyWebinarCount) {
		this.companyWebinarCount = companyWebinarCount;
	}
	@JsonIgnore
	public void setCompanyWebinarCount(String o) {
		this.companyWebinarCount = CompanyWebinarGenPage.staticSetCompanyWebinarCount(siteRequest_, o);
	}
	public static Integer staticSetCompanyWebinarCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyWebinarGenPage companyWebinarCountInit() {
		Wrap<Integer> companyWebinarCountWrap = new Wrap<Integer>().var("companyWebinarCount");
		if(companyWebinarCount == null) {
			_companyWebinarCount(companyWebinarCountWrap);
			Optional.ofNullable(companyWebinarCountWrap.getO()).ifPresent(o -> {
				setCompanyWebinarCount(o);
			});
		}
		return (CompanyWebinarGenPage)this;
	}

	public static Integer staticSearchCompanyWebinarCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrCompanyWebinarCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCompanyWebinarCount(SiteRequest siteRequest_, String o) {
		return CompanyWebinarGenPage.staticSearchCompanyWebinarCount(siteRequest_, CompanyWebinarGenPage.staticSetCompanyWebinarCount(siteRequest_, o)).toString();
	}

	/////////////////////
	// companyWebinar_ //
	/////////////////////


	/**	 The entity companyWebinar_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CompanyWebinar companyWebinar_;

	/**	<br> The entity companyWebinar_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinarGenPage&fq=entiteVar_enUS_indexed_string:companyWebinar_">Find the entity companyWebinar_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyWebinar_(Wrap<CompanyWebinar> w);

	public CompanyWebinar getCompanyWebinar_() {
		return companyWebinar_;
	}

	public void setCompanyWebinar_(CompanyWebinar companyWebinar_) {
		this.companyWebinar_ = companyWebinar_;
	}
	public static CompanyWebinar staticSetCompanyWebinar_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyWebinarGenPage companyWebinar_Init() {
		Wrap<CompanyWebinar> companyWebinar_Wrap = new Wrap<CompanyWebinar>().var("companyWebinar_");
		if(companyWebinar_ == null) {
			_companyWebinar_(companyWebinar_Wrap);
			Optional.ofNullable(companyWebinar_Wrap.getO()).ifPresent(o -> {
				setCompanyWebinar_(o);
			});
		}
		return (CompanyWebinarGenPage)this;
	}

	////////
	// pk //
	////////


	/**	 The entity pk
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long pk;

	/**	<br> The entity pk
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinarGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pk(Wrap<Long> w);

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}
	@JsonIgnore
	public void setPk(String o) {
		this.pk = CompanyWebinarGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected CompanyWebinarGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (CompanyWebinarGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return CompanyWebinarGenPage.staticSearchPk(siteRequest_, CompanyWebinarGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinarGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = CompanyWebinarGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebinarGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (CompanyWebinarGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return CompanyWebinarGenPage.staticSearchId(siteRequest_, CompanyWebinarGenPage.staticSetId(siteRequest_, o)).toString();
	}

	///////////////////////////
	// pageUriCompanyWebinar //
	///////////////////////////


	/**	 The entity pageUriCompanyWebinar
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriCompanyWebinar;

	/**	<br> The entity pageUriCompanyWebinar
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.webinar.CompanyWebinarGenPage&fq=entiteVar_enUS_indexed_string:pageUriCompanyWebinar">Find the entity pageUriCompanyWebinar in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriCompanyWebinar(Wrap<String> c);

	public String getPageUriCompanyWebinar() {
		return pageUriCompanyWebinar;
	}
	public void setPageUriCompanyWebinar(String o) {
		this.pageUriCompanyWebinar = CompanyWebinarGenPage.staticSetPageUriCompanyWebinar(siteRequest_, o);
	}
	public static String staticSetPageUriCompanyWebinar(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebinarGenPage pageUriCompanyWebinarInit() {
		Wrap<String> pageUriCompanyWebinarWrap = new Wrap<String>().var("pageUriCompanyWebinar");
		if(pageUriCompanyWebinar == null) {
			_pageUriCompanyWebinar(pageUriCompanyWebinarWrap);
			Optional.ofNullable(pageUriCompanyWebinarWrap.getO()).ifPresent(o -> {
				setPageUriCompanyWebinar(o);
			});
		}
		return (CompanyWebinarGenPage)this;
	}

	public static String staticSearchPageUriCompanyWebinar(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriCompanyWebinar(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriCompanyWebinar(SiteRequest siteRequest_, String o) {
		return CompanyWebinarGenPage.staticSearchPageUriCompanyWebinar(siteRequest_, CompanyWebinarGenPage.staticSetPageUriCompanyWebinar(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepCompanyWebinarGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyWebinarGenPage();
	}

	public Future<Void> promiseDeepCompanyWebinarGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyWebinarGenPage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseModelPage(siteRequest_).onSuccess(b -> {
				promise.complete();
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseCompanyWebinarGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListCompanyWebinar_Init();
				listCompanyWebinarInit();
				companyWebinarCountInit();
				companyWebinar_Init();
				pkInit();
				idInit();
				pageUriCompanyWebinarInit();
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
		return promiseDeepCompanyWebinarGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyWebinarGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyWebinarGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyWebinarGenPage(v);
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
	public Object obtainCompanyWebinarGenPage(String var) {
		CompanyWebinarGenPage oCompanyWebinarGenPage = (CompanyWebinarGenPage)this;
		switch(var) {
			case "searchListCompanyWebinar_":
				return oCompanyWebinarGenPage.searchListCompanyWebinar_;
			case "listCompanyWebinar":
				return oCompanyWebinarGenPage.listCompanyWebinar;
			case "companyWebinarCount":
				return oCompanyWebinarGenPage.companyWebinarCount;
			case "companyWebinar_":
				return oCompanyWebinarGenPage.companyWebinar_;
			case "pk":
				return oCompanyWebinarGenPage.pk;
			case "id":
				return oCompanyWebinarGenPage.id;
			case "pageUriCompanyWebinar":
				return oCompanyWebinarGenPage.pageUriCompanyWebinar;
			default:
				return super.obtainBaseModelPage(var);
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
				o = relateCompanyWebinarGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyWebinarGenPage(String var, Object val) {
		CompanyWebinarGenPage oCompanyWebinarGenPage = (CompanyWebinarGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyWebinarGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyWebinarGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyWebinar":
			return CompanyWebinarGenPage.staticSetListCompanyWebinar(siteRequest_, o);
		case "companyWebinarCount":
			return CompanyWebinarGenPage.staticSetCompanyWebinarCount(siteRequest_, o);
		case "pk":
			return CompanyWebinarGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return CompanyWebinarGenPage.staticSetId(siteRequest_, o);
		case "pageUriCompanyWebinar":
			return CompanyWebinarGenPage.staticSetPageUriCompanyWebinar(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyWebinarGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyWebinarGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyWebinar":
			return CompanyWebinarGenPage.staticSearchListCompanyWebinar(siteRequest_, (JsonArray)o);
		case "companyWebinarCount":
			return CompanyWebinarGenPage.staticSearchCompanyWebinarCount(siteRequest_, (Integer)o);
		case "pk":
			return CompanyWebinarGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return CompanyWebinarGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriCompanyWebinar":
			return CompanyWebinarGenPage.staticSearchPageUriCompanyWebinar(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyWebinarGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyWebinarGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyWebinar":
			return CompanyWebinarGenPage.staticSearchStrListCompanyWebinar(siteRequest_, (String)o);
		case "companyWebinarCount":
			return CompanyWebinarGenPage.staticSearchStrCompanyWebinarCount(siteRequest_, (Integer)o);
		case "pk":
			return CompanyWebinarGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return CompanyWebinarGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriCompanyWebinar":
			return CompanyWebinarGenPage.staticSearchStrPageUriCompanyWebinar(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyWebinarGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyWebinarGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyWebinar":
			return CompanyWebinarGenPage.staticSearchFqListCompanyWebinar(siteRequest_, o);
		case "companyWebinarCount":
			return CompanyWebinarGenPage.staticSearchFqCompanyWebinarCount(siteRequest_, o);
		case "pk":
			return CompanyWebinarGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return CompanyWebinarGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriCompanyWebinar":
			return CompanyWebinarGenPage.staticSearchFqPageUriCompanyWebinar(siteRequest_, o);
			default:
				return BaseModelPage.staticSearchFqBaseModelPage(entityVar,  siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyWebinarGenPage";
	public static final String VAR_searchListCompanyWebinar_ = "searchListCompanyWebinar_";
	public static final String VAR_listCompanyWebinar = "listCompanyWebinar";
	public static final String VAR_companyWebinarCount = "companyWebinarCount";
	public static final String VAR_companyWebinar_ = "companyWebinar_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriCompanyWebinar = "pageUriCompanyWebinar";

	public static final String DISPLAY_NAME_searchListCompanyWebinar_ = "";
	public static final String DISPLAY_NAME_listCompanyWebinar = "";
	public static final String DISPLAY_NAME_companyWebinarCount = "";
	public static final String DISPLAY_NAME_companyWebinar_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriCompanyWebinar = "";

	public static String displayNameForClass(String var) {
		return CompanyWebinarGenPage.displayNameCompanyWebinarGenPage(var);
	}
	public static String displayNameCompanyWebinarGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyWebinar_:
			return DISPLAY_NAME_searchListCompanyWebinar_;
		case VAR_listCompanyWebinar:
			return DISPLAY_NAME_listCompanyWebinar;
		case VAR_companyWebinarCount:
			return DISPLAY_NAME_companyWebinarCount;
		case VAR_companyWebinar_:
			return DISPLAY_NAME_companyWebinar_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriCompanyWebinar:
			return DISPLAY_NAME_pageUriCompanyWebinar;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
