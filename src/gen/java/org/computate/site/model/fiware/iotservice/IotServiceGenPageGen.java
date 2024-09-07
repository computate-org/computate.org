package org.computate.site.model.fiware.iotservice;

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
import org.computate.site.model.fiware.iotservice.IotService;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these IotServiceGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class IotServiceGenPageGen into the class IotServiceGenPage. 
 * </li>
 * <h3>About the IotServiceGenPage class and it's generated class IotServiceGenPageGen&lt;BaseModelPage&gt;: </h3>extends IotServiceGenPageGen
 * <p>
 * This Java class extends a generated Java class IotServiceGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotServiceGenPage">Find the class IotServiceGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends IotServiceGenPageGen<BaseModelPage>
 * <p>This <code>class IotServiceGenPage extends IotServiceGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated IotServiceGenPageGen. 
 * The generated <code>class IotServiceGenPageGen extends BaseModelPage</code> which means that IotServiceGenPage extends IotServiceGenPageGen which extends BaseModelPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the IotServiceGenPage class will inherit the helpful inherited class comments from the super class IotServiceGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the IotServiceGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class IotServiceGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotServiceGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.fiware.iotservice in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.fiware.iotservice&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class IotServiceGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(IotServiceGenPage.class);

	///////////////////////////
	// searchListIotService_ //
	///////////////////////////


	/**	 The entity searchListIotService_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<IotService> searchListIotService_;

	/**	<br> The entity searchListIotService_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotServiceGenPage&fq=entiteVar_enUS_indexed_string:searchListIotService_">Find the entity searchListIotService_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListIotService_(Wrap<SearchList<IotService>> w);

	public SearchList<IotService> getSearchListIotService_() {
		return searchListIotService_;
	}

	public void setSearchListIotService_(SearchList<IotService> searchListIotService_) {
		this.searchListIotService_ = searchListIotService_;
	}
	public static SearchList<IotService> staticSetSearchListIotService_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected IotServiceGenPage searchListIotService_Init() {
		Wrap<SearchList<IotService>> searchListIotService_Wrap = new Wrap<SearchList<IotService>>().var("searchListIotService_");
		if(searchListIotService_ == null) {
			_searchListIotService_(searchListIotService_Wrap);
			Optional.ofNullable(searchListIotService_Wrap.getO()).ifPresent(o -> {
				setSearchListIotService_(o);
			});
		}
		return (IotServiceGenPage)this;
	}

	////////////////////
	// listIotService //
	////////////////////


	/**	 The entity listIotService
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listIotService = new JsonArray();

	/**	<br> The entity listIotService
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotServiceGenPage&fq=entiteVar_enUS_indexed_string:listIotService">Find the entity listIotService in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listIotService(JsonArray l);

	public JsonArray getListIotService() {
		return listIotService;
	}

	public void setListIotService(JsonArray listIotService) {
		this.listIotService = listIotService;
	}
	@JsonIgnore
	public void setListIotService(String o) {
		this.listIotService = IotServiceGenPage.staticSetListIotService(siteRequest_, o);
	}
	public static JsonArray staticSetListIotService(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected IotServiceGenPage listIotServiceInit() {
		_listIotService(listIotService);
		return (IotServiceGenPage)this;
	}

	public static String staticSearchListIotService(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListIotService(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListIotService(SiteRequest siteRequest_, String o) {
		return IotServiceGenPage.staticSearchListIotService(siteRequest_, IotServiceGenPage.staticSetListIotService(siteRequest_, o)).toString();
	}

	/////////////////////
	// iotServiceCount //
	/////////////////////


	/**	 The entity iotServiceCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer iotServiceCount;

	/**	<br> The entity iotServiceCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotServiceGenPage&fq=entiteVar_enUS_indexed_string:iotServiceCount">Find the entity iotServiceCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _iotServiceCount(Wrap<Integer> w);

	public Integer getIotServiceCount() {
		return iotServiceCount;
	}

	public void setIotServiceCount(Integer iotServiceCount) {
		this.iotServiceCount = iotServiceCount;
	}
	@JsonIgnore
	public void setIotServiceCount(String o) {
		this.iotServiceCount = IotServiceGenPage.staticSetIotServiceCount(siteRequest_, o);
	}
	public static Integer staticSetIotServiceCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected IotServiceGenPage iotServiceCountInit() {
		Wrap<Integer> iotServiceCountWrap = new Wrap<Integer>().var("iotServiceCount");
		if(iotServiceCount == null) {
			_iotServiceCount(iotServiceCountWrap);
			Optional.ofNullable(iotServiceCountWrap.getO()).ifPresent(o -> {
				setIotServiceCount(o);
			});
		}
		return (IotServiceGenPage)this;
	}

	public static Integer staticSearchIotServiceCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrIotServiceCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqIotServiceCount(SiteRequest siteRequest_, String o) {
		return IotServiceGenPage.staticSearchIotServiceCount(siteRequest_, IotServiceGenPage.staticSetIotServiceCount(siteRequest_, o)).toString();
	}

	/////////////////
	// iotService_ //
	/////////////////


	/**	 The entity iotService_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected IotService iotService_;

	/**	<br> The entity iotService_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotServiceGenPage&fq=entiteVar_enUS_indexed_string:iotService_">Find the entity iotService_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _iotService_(Wrap<IotService> w);

	public IotService getIotService_() {
		return iotService_;
	}

	public void setIotService_(IotService iotService_) {
		this.iotService_ = iotService_;
	}
	public static IotService staticSetIotService_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected IotServiceGenPage iotService_Init() {
		Wrap<IotService> iotService_Wrap = new Wrap<IotService>().var("iotService_");
		if(iotService_ == null) {
			_iotService_(iotService_Wrap);
			Optional.ofNullable(iotService_Wrap.getO()).ifPresent(o -> {
				setIotService_(o);
			});
		}
		return (IotServiceGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotServiceGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = IotServiceGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected IotServiceGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (IotServiceGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return IotServiceGenPage.staticSearchPk(siteRequest_, IotServiceGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotServiceGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = IotServiceGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected IotServiceGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (IotServiceGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return IotServiceGenPage.staticSearchId(siteRequest_, IotServiceGenPage.staticSetId(siteRequest_, o)).toString();
	}

	///////////////////////
	// pageUriIotService //
	///////////////////////


	/**	 The entity pageUriIotService
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriIotService;

	/**	<br> The entity pageUriIotService
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.iotservice.IotServiceGenPage&fq=entiteVar_enUS_indexed_string:pageUriIotService">Find the entity pageUriIotService in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriIotService(Wrap<String> c);

	public String getPageUriIotService() {
		return pageUriIotService;
	}
	public void setPageUriIotService(String o) {
		this.pageUriIotService = IotServiceGenPage.staticSetPageUriIotService(siteRequest_, o);
	}
	public static String staticSetPageUriIotService(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected IotServiceGenPage pageUriIotServiceInit() {
		Wrap<String> pageUriIotServiceWrap = new Wrap<String>().var("pageUriIotService");
		if(pageUriIotService == null) {
			_pageUriIotService(pageUriIotServiceWrap);
			Optional.ofNullable(pageUriIotServiceWrap.getO()).ifPresent(o -> {
				setPageUriIotService(o);
			});
		}
		return (IotServiceGenPage)this;
	}

	public static String staticSearchPageUriIotService(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriIotService(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriIotService(SiteRequest siteRequest_, String o) {
		return IotServiceGenPage.staticSearchPageUriIotService(siteRequest_, IotServiceGenPage.staticSetPageUriIotService(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepIotServiceGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepIotServiceGenPage();
	}

	public Future<Void> promiseDeepIotServiceGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseIotServiceGenPage(promise2);
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

	public Future<Void> promiseIotServiceGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListIotService_Init();
				listIotServiceInit();
				iotServiceCountInit();
				iotService_Init();
				pkInit();
				idInit();
				pageUriIotServiceInit();
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
		return promiseDeepIotServiceGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestIotServiceGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestIotServiceGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainIotServiceGenPage(v);
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
	public Object obtainIotServiceGenPage(String var) {
		IotServiceGenPage oIotServiceGenPage = (IotServiceGenPage)this;
		switch(var) {
			case "searchListIotService_":
				return oIotServiceGenPage.searchListIotService_;
			case "listIotService":
				return oIotServiceGenPage.listIotService;
			case "iotServiceCount":
				return oIotServiceGenPage.iotServiceCount;
			case "iotService_":
				return oIotServiceGenPage.iotService_;
			case "pk":
				return oIotServiceGenPage.pk;
			case "id":
				return oIotServiceGenPage.id;
			case "pageUriIotService":
				return oIotServiceGenPage.pageUriIotService;
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
				o = relateIotServiceGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateIotServiceGenPage(String var, Object val) {
		IotServiceGenPage oIotServiceGenPage = (IotServiceGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetIotServiceGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetIotServiceGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listIotService":
			return IotServiceGenPage.staticSetListIotService(siteRequest_, o);
		case "iotServiceCount":
			return IotServiceGenPage.staticSetIotServiceCount(siteRequest_, o);
		case "pk":
			return IotServiceGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return IotServiceGenPage.staticSetId(siteRequest_, o);
		case "pageUriIotService":
			return IotServiceGenPage.staticSetPageUriIotService(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchIotServiceGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchIotServiceGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listIotService":
			return IotServiceGenPage.staticSearchListIotService(siteRequest_, (JsonArray)o);
		case "iotServiceCount":
			return IotServiceGenPage.staticSearchIotServiceCount(siteRequest_, (Integer)o);
		case "pk":
			return IotServiceGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return IotServiceGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriIotService":
			return IotServiceGenPage.staticSearchPageUriIotService(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrIotServiceGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrIotServiceGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listIotService":
			return IotServiceGenPage.staticSearchStrListIotService(siteRequest_, (String)o);
		case "iotServiceCount":
			return IotServiceGenPage.staticSearchStrIotServiceCount(siteRequest_, (Integer)o);
		case "pk":
			return IotServiceGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return IotServiceGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriIotService":
			return IotServiceGenPage.staticSearchStrPageUriIotService(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqIotServiceGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqIotServiceGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listIotService":
			return IotServiceGenPage.staticSearchFqListIotService(siteRequest_, o);
		case "iotServiceCount":
			return IotServiceGenPage.staticSearchFqIotServiceCount(siteRequest_, o);
		case "pk":
			return IotServiceGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return IotServiceGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriIotService":
			return IotServiceGenPage.staticSearchFqPageUriIotService(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "IotServiceGenPage";
	public static final String VAR_searchListIotService_ = "searchListIotService_";
	public static final String VAR_listIotService = "listIotService";
	public static final String VAR_iotServiceCount = "iotServiceCount";
	public static final String VAR_iotService_ = "iotService_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriIotService = "pageUriIotService";

	public static final String DISPLAY_NAME_searchListIotService_ = "";
	public static final String DISPLAY_NAME_listIotService = "";
	public static final String DISPLAY_NAME_iotServiceCount = "";
	public static final String DISPLAY_NAME_iotService_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriIotService = "";

	public static String displayNameForClass(String var) {
		return IotServiceGenPage.displayNameIotServiceGenPage(var);
	}
	public static String displayNameIotServiceGenPage(String var) {
		switch(var) {
		case VAR_searchListIotService_:
			return DISPLAY_NAME_searchListIotService_;
		case VAR_listIotService:
			return DISPLAY_NAME_listIotService;
		case VAR_iotServiceCount:
			return DISPLAY_NAME_iotServiceCount;
		case VAR_iotService_:
			return DISPLAY_NAME_iotService_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriIotService:
			return DISPLAY_NAME_pageUriIotService;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
