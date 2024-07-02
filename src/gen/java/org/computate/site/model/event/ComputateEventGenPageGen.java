package org.computate.site.model.event;

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
import org.computate.site.model.event.ComputateEvent;
import io.vertx.core.json.JsonArray;
import java.lang.Integer;
import java.lang.String;
import java.lang.Long;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these ComputateEventGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ComputateEventGenPageGen into the class ComputateEventGenPage. 
 * </li>
 * <h3>About the ComputateEventGenPage class and it's generated class ComputateEventGenPageGen&lt;BaseModelPage&gt;: </h3>extends ComputateEventGenPageGen
 * <p>
 * This Java class extends a generated Java class ComputateEventGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEventGenPage">Find the class ComputateEventGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ComputateEventGenPageGen<BaseModelPage>
 * <p>This <code>class ComputateEventGenPage extends ComputateEventGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated ComputateEventGenPageGen. 
 * The generated <code>class ComputateEventGenPageGen extends BaseModelPage</code> which means that ComputateEventGenPage extends ComputateEventGenPageGen which extends BaseModelPage. 
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
 * <p>By adding a class comment "{@inheritDoc}", the ComputateEventGenPage class will inherit the helpful inherited class comments from the super class ComputateEventGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ComputateEventGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class ComputateEventGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEventGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.event in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.event&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ComputateEventGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(ComputateEventGenPage.class);

	///////////////////////////////
	// searchListComputateEvent_ //
	///////////////////////////////


	/**	 The entity searchListComputateEvent_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<ComputateEvent> searchListComputateEvent_;

	/**	<br> The entity searchListComputateEvent_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEventGenPage&fq=entiteVar_enUS_indexed_string:searchListComputateEvent_">Find the entity searchListComputateEvent_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListComputateEvent_(Wrap<SearchList<ComputateEvent>> w);

	public SearchList<ComputateEvent> getSearchListComputateEvent_() {
		return searchListComputateEvent_;
	}

	public void setSearchListComputateEvent_(SearchList<ComputateEvent> searchListComputateEvent_) {
		this.searchListComputateEvent_ = searchListComputateEvent_;
	}
	public static SearchList<ComputateEvent> staticSetSearchListComputateEvent_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ComputateEventGenPage searchListComputateEvent_Init() {
		Wrap<SearchList<ComputateEvent>> searchListComputateEvent_Wrap = new Wrap<SearchList<ComputateEvent>>().var("searchListComputateEvent_");
		if(searchListComputateEvent_ == null) {
			_searchListComputateEvent_(searchListComputateEvent_Wrap);
			Optional.ofNullable(searchListComputateEvent_Wrap.getO()).ifPresent(o -> {
				setSearchListComputateEvent_(o);
			});
		}
		return (ComputateEventGenPage)this;
	}

	////////////////////////
	// listComputateEvent //
	////////////////////////


	/**	 The entity listComputateEvent
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listComputateEvent = new JsonArray();

	/**	<br> The entity listComputateEvent
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEventGenPage&fq=entiteVar_enUS_indexed_string:listComputateEvent">Find the entity listComputateEvent in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listComputateEvent(JsonArray l);

	public JsonArray getListComputateEvent() {
		return listComputateEvent;
	}

	public void setListComputateEvent(JsonArray listComputateEvent) {
		this.listComputateEvent = listComputateEvent;
	}
	@JsonIgnore
	public void setListComputateEvent(String o) {
		this.listComputateEvent = ComputateEventGenPage.staticSetListComputateEvent(siteRequest_, o);
	}
	public static JsonArray staticSetListComputateEvent(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected ComputateEventGenPage listComputateEventInit() {
		_listComputateEvent(listComputateEvent);
		return (ComputateEventGenPage)this;
	}

	public static String staticSearchListComputateEvent(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListComputateEvent(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListComputateEvent(SiteRequest siteRequest_, String o) {
		return ComputateEventGenPage.staticSearchListComputateEvent(siteRequest_, ComputateEventGenPage.staticSetListComputateEvent(siteRequest_, o)).toString();
	}

	/////////////////////////
	// computateEventCount //
	/////////////////////////


	/**	 The entity computateEventCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer computateEventCount;

	/**	<br> The entity computateEventCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEventGenPage&fq=entiteVar_enUS_indexed_string:computateEventCount">Find the entity computateEventCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _computateEventCount(Wrap<Integer> w);

	public Integer getComputateEventCount() {
		return computateEventCount;
	}

	public void setComputateEventCount(Integer computateEventCount) {
		this.computateEventCount = computateEventCount;
	}
	@JsonIgnore
	public void setComputateEventCount(String o) {
		this.computateEventCount = ComputateEventGenPage.staticSetComputateEventCount(siteRequest_, o);
	}
	public static Integer staticSetComputateEventCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected ComputateEventGenPage computateEventCountInit() {
		Wrap<Integer> computateEventCountWrap = new Wrap<Integer>().var("computateEventCount");
		if(computateEventCount == null) {
			_computateEventCount(computateEventCountWrap);
			Optional.ofNullable(computateEventCountWrap.getO()).ifPresent(o -> {
				setComputateEventCount(o);
			});
		}
		return (ComputateEventGenPage)this;
	}

	public static Integer staticSearchComputateEventCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrComputateEventCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqComputateEventCount(SiteRequest siteRequest_, String o) {
		return ComputateEventGenPage.staticSearchComputateEventCount(siteRequest_, ComputateEventGenPage.staticSetComputateEventCount(siteRequest_, o)).toString();
	}

	/////////////////////
	// computateEvent_ //
	/////////////////////


	/**	 The entity computateEvent_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected ComputateEvent computateEvent_;

	/**	<br> The entity computateEvent_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEventGenPage&fq=entiteVar_enUS_indexed_string:computateEvent_">Find the entity computateEvent_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _computateEvent_(Wrap<ComputateEvent> w);

	public ComputateEvent getComputateEvent_() {
		return computateEvent_;
	}

	public void setComputateEvent_(ComputateEvent computateEvent_) {
		this.computateEvent_ = computateEvent_;
	}
	public static ComputateEvent staticSetComputateEvent_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ComputateEventGenPage computateEvent_Init() {
		Wrap<ComputateEvent> computateEvent_Wrap = new Wrap<ComputateEvent>().var("computateEvent_");
		if(computateEvent_ == null) {
			_computateEvent_(computateEvent_Wrap);
			Optional.ofNullable(computateEvent_Wrap.getO()).ifPresent(o -> {
				setComputateEvent_(o);
			});
		}
		return (ComputateEventGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEventGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = ComputateEventGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected ComputateEventGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (ComputateEventGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return ComputateEventGenPage.staticSearchPk(siteRequest_, ComputateEventGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEventGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = ComputateEventGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateEventGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (ComputateEventGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return ComputateEventGenPage.staticSearchId(siteRequest_, ComputateEventGenPage.staticSetId(siteRequest_, o)).toString();
	}

	///////////////////////////
	// pageUriComputateEvent //
	///////////////////////////


	/**	 The entity pageUriComputateEvent
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriComputateEvent;

	/**	<br> The entity pageUriComputateEvent
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.event.ComputateEventGenPage&fq=entiteVar_enUS_indexed_string:pageUriComputateEvent">Find the entity pageUriComputateEvent in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriComputateEvent(Wrap<String> c);

	public String getPageUriComputateEvent() {
		return pageUriComputateEvent;
	}
	public void setPageUriComputateEvent(String o) {
		this.pageUriComputateEvent = ComputateEventGenPage.staticSetPageUriComputateEvent(siteRequest_, o);
	}
	public static String staticSetPageUriComputateEvent(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateEventGenPage pageUriComputateEventInit() {
		Wrap<String> pageUriComputateEventWrap = new Wrap<String>().var("pageUriComputateEvent");
		if(pageUriComputateEvent == null) {
			_pageUriComputateEvent(pageUriComputateEventWrap);
			Optional.ofNullable(pageUriComputateEventWrap.getO()).ifPresent(o -> {
				setPageUriComputateEvent(o);
			});
		}
		return (ComputateEventGenPage)this;
	}

	public static String staticSearchPageUriComputateEvent(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriComputateEvent(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriComputateEvent(SiteRequest siteRequest_, String o) {
		return ComputateEventGenPage.staticSearchPageUriComputateEvent(siteRequest_, ComputateEventGenPage.staticSetPageUriComputateEvent(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepComputateEventGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepComputateEventGenPage();
	}

	public Future<Void> promiseDeepComputateEventGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseComputateEventGenPage(promise2);
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

	public Future<Void> promiseComputateEventGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListComputateEvent_Init();
				listComputateEventInit();
				computateEventCountInit();
				computateEvent_Init();
				pkInit();
				idInit();
				pageUriComputateEventInit();
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
		return promiseDeepComputateEventGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestComputateEventGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestComputateEventGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainComputateEventGenPage(v);
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
	public Object obtainComputateEventGenPage(String var) {
		ComputateEventGenPage oComputateEventGenPage = (ComputateEventGenPage)this;
		switch(var) {
			case "searchListComputateEvent_":
				return oComputateEventGenPage.searchListComputateEvent_;
			case "listComputateEvent":
				return oComputateEventGenPage.listComputateEvent;
			case "computateEventCount":
				return oComputateEventGenPage.computateEventCount;
			case "computateEvent_":
				return oComputateEventGenPage.computateEvent_;
			case "pk":
				return oComputateEventGenPage.pk;
			case "id":
				return oComputateEventGenPage.id;
			case "pageUriComputateEvent":
				return oComputateEventGenPage.pageUriComputateEvent;
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
				o = relateComputateEventGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateComputateEventGenPage(String var, Object val) {
		ComputateEventGenPage oComputateEventGenPage = (ComputateEventGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetComputateEventGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetComputateEventGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listComputateEvent":
			return ComputateEventGenPage.staticSetListComputateEvent(siteRequest_, o);
		case "computateEventCount":
			return ComputateEventGenPage.staticSetComputateEventCount(siteRequest_, o);
		case "pk":
			return ComputateEventGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return ComputateEventGenPage.staticSetId(siteRequest_, o);
		case "pageUriComputateEvent":
			return ComputateEventGenPage.staticSetPageUriComputateEvent(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchComputateEventGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchComputateEventGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listComputateEvent":
			return ComputateEventGenPage.staticSearchListComputateEvent(siteRequest_, (JsonArray)o);
		case "computateEventCount":
			return ComputateEventGenPage.staticSearchComputateEventCount(siteRequest_, (Integer)o);
		case "pk":
			return ComputateEventGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return ComputateEventGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriComputateEvent":
			return ComputateEventGenPage.staticSearchPageUriComputateEvent(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrComputateEventGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrComputateEventGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listComputateEvent":
			return ComputateEventGenPage.staticSearchStrListComputateEvent(siteRequest_, (String)o);
		case "computateEventCount":
			return ComputateEventGenPage.staticSearchStrComputateEventCount(siteRequest_, (Integer)o);
		case "pk":
			return ComputateEventGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return ComputateEventGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriComputateEvent":
			return ComputateEventGenPage.staticSearchStrPageUriComputateEvent(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqComputateEventGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqComputateEventGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listComputateEvent":
			return ComputateEventGenPage.staticSearchFqListComputateEvent(siteRequest_, o);
		case "computateEventCount":
			return ComputateEventGenPage.staticSearchFqComputateEventCount(siteRequest_, o);
		case "pk":
			return ComputateEventGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return ComputateEventGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriComputateEvent":
			return ComputateEventGenPage.staticSearchFqPageUriComputateEvent(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "ComputateEventGenPage";
	public static final String VAR_searchListComputateEvent_ = "searchListComputateEvent_";
	public static final String VAR_listComputateEvent = "listComputateEvent";
	public static final String VAR_computateEventCount = "computateEventCount";
	public static final String VAR_computateEvent_ = "computateEvent_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriComputateEvent = "pageUriComputateEvent";

	public static final String DISPLAY_NAME_searchListComputateEvent_ = "";
	public static final String DISPLAY_NAME_listComputateEvent = "";
	public static final String DISPLAY_NAME_computateEventCount = "";
	public static final String DISPLAY_NAME_computateEvent_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriComputateEvent = "";

	public static String displayNameForClass(String var) {
		return ComputateEventGenPage.displayNameComputateEventGenPage(var);
	}
	public static String displayNameComputateEventGenPage(String var) {
		switch(var) {
		case VAR_searchListComputateEvent_:
			return DISPLAY_NAME_searchListComputateEvent_;
		case VAR_listComputateEvent:
			return DISPLAY_NAME_listComputateEvent;
		case VAR_computateEventCount:
			return DISPLAY_NAME_computateEventCount;
		case VAR_computateEvent_:
			return DISPLAY_NAME_computateEvent_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriComputateEvent:
			return DISPLAY_NAME_pageUriComputateEvent;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
