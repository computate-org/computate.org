package org.computate.site.model.fiware.weatherobserved;

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
import org.computate.site.model.fiware.weatherobserved.WeatherObserved;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these WeatherObservedGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class WeatherObservedGenPageGen into the class WeatherObservedGenPage. 
 * </li>
 * <h3>About the WeatherObservedGenPage class and it's generated class WeatherObservedGenPageGen&lt;BaseModelPage&gt;: </h3>extends WeatherObservedGenPageGen
 * <p>
 * This Java class extends a generated Java class WeatherObservedGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObservedGenPage">Find the class WeatherObservedGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends WeatherObservedGenPageGen<BaseModelPage>
 * <p>This <code>class WeatherObservedGenPage extends WeatherObservedGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated WeatherObservedGenPageGen. 
 * The generated <code>class WeatherObservedGenPageGen extends BaseModelPage</code> which means that WeatherObservedGenPage extends WeatherObservedGenPageGen which extends BaseModelPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the WeatherObservedGenPage class will inherit the helpful inherited class comments from the super class WeatherObservedGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the WeatherObservedGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class WeatherObservedGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObservedGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.fiware.weatherobserved in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class WeatherObservedGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(WeatherObservedGenPage.class);

	////////////////////////////////
	// searchListWeatherObserved_ //
	////////////////////////////////


	/**	 The entity searchListWeatherObserved_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<WeatherObserved> searchListWeatherObserved_;

	/**	<br> The entity searchListWeatherObserved_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObservedGenPage&fq=entiteVar_enUS_indexed_string:searchListWeatherObserved_">Find the entity searchListWeatherObserved_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListWeatherObserved_(Wrap<SearchList<WeatherObserved>> w);

	public SearchList<WeatherObserved> getSearchListWeatherObserved_() {
		return searchListWeatherObserved_;
	}

	public void setSearchListWeatherObserved_(SearchList<WeatherObserved> searchListWeatherObserved_) {
		this.searchListWeatherObserved_ = searchListWeatherObserved_;
	}
	public static SearchList<WeatherObserved> staticSetSearchListWeatherObserved_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected WeatherObservedGenPage searchListWeatherObserved_Init() {
		Wrap<SearchList<WeatherObserved>> searchListWeatherObserved_Wrap = new Wrap<SearchList<WeatherObserved>>().var("searchListWeatherObserved_");
		if(searchListWeatherObserved_ == null) {
			_searchListWeatherObserved_(searchListWeatherObserved_Wrap);
			Optional.ofNullable(searchListWeatherObserved_Wrap.getO()).ifPresent(o -> {
				setSearchListWeatherObserved_(o);
			});
		}
		return (WeatherObservedGenPage)this;
	}

	/////////////////////////
	// listWeatherObserved //
	/////////////////////////


	/**	 The entity listWeatherObserved
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listWeatherObserved = new JsonArray();

	/**	<br> The entity listWeatherObserved
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObservedGenPage&fq=entiteVar_enUS_indexed_string:listWeatherObserved">Find the entity listWeatherObserved in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listWeatherObserved(JsonArray l);

	public JsonArray getListWeatherObserved() {
		return listWeatherObserved;
	}

	public void setListWeatherObserved(JsonArray listWeatherObserved) {
		this.listWeatherObserved = listWeatherObserved;
	}
	@JsonIgnore
	public void setListWeatherObserved(String o) {
		this.listWeatherObserved = WeatherObservedGenPage.staticSetListWeatherObserved(siteRequest_, o);
	}
	public static JsonArray staticSetListWeatherObserved(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected WeatherObservedGenPage listWeatherObservedInit() {
		_listWeatherObserved(listWeatherObserved);
		return (WeatherObservedGenPage)this;
	}

	public static String staticSearchListWeatherObserved(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListWeatherObserved(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListWeatherObserved(SiteRequest siteRequest_, String o) {
		return WeatherObservedGenPage.staticSearchListWeatherObserved(siteRequest_, WeatherObservedGenPage.staticSetListWeatherObserved(siteRequest_, o)).toString();
	}

	//////////////////////////
	// weatherObservedCount //
	//////////////////////////


	/**	 The entity weatherObservedCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer weatherObservedCount;

	/**	<br> The entity weatherObservedCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObservedGenPage&fq=entiteVar_enUS_indexed_string:weatherObservedCount">Find the entity weatherObservedCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _weatherObservedCount(Wrap<Integer> w);

	public Integer getWeatherObservedCount() {
		return weatherObservedCount;
	}

	public void setWeatherObservedCount(Integer weatherObservedCount) {
		this.weatherObservedCount = weatherObservedCount;
	}
	@JsonIgnore
	public void setWeatherObservedCount(String o) {
		this.weatherObservedCount = WeatherObservedGenPage.staticSetWeatherObservedCount(siteRequest_, o);
	}
	public static Integer staticSetWeatherObservedCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected WeatherObservedGenPage weatherObservedCountInit() {
		Wrap<Integer> weatherObservedCountWrap = new Wrap<Integer>().var("weatherObservedCount");
		if(weatherObservedCount == null) {
			_weatherObservedCount(weatherObservedCountWrap);
			Optional.ofNullable(weatherObservedCountWrap.getO()).ifPresent(o -> {
				setWeatherObservedCount(o);
			});
		}
		return (WeatherObservedGenPage)this;
	}

	public static Integer staticSearchWeatherObservedCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrWeatherObservedCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqWeatherObservedCount(SiteRequest siteRequest_, String o) {
		return WeatherObservedGenPage.staticSearchWeatherObservedCount(siteRequest_, WeatherObservedGenPage.staticSetWeatherObservedCount(siteRequest_, o)).toString();
	}

	//////////////////////
	// weatherObserved_ //
	//////////////////////


	/**	 The entity weatherObserved_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected WeatherObserved weatherObserved_;

	/**	<br> The entity weatherObserved_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObservedGenPage&fq=entiteVar_enUS_indexed_string:weatherObserved_">Find the entity weatherObserved_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _weatherObserved_(Wrap<WeatherObserved> w);

	public WeatherObserved getWeatherObserved_() {
		return weatherObserved_;
	}

	public void setWeatherObserved_(WeatherObserved weatherObserved_) {
		this.weatherObserved_ = weatherObserved_;
	}
	public static WeatherObserved staticSetWeatherObserved_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected WeatherObservedGenPage weatherObserved_Init() {
		Wrap<WeatherObserved> weatherObserved_Wrap = new Wrap<WeatherObserved>().var("weatherObserved_");
		if(weatherObserved_ == null) {
			_weatherObserved_(weatherObserved_Wrap);
			Optional.ofNullable(weatherObserved_Wrap.getO()).ifPresent(o -> {
				setWeatherObserved_(o);
			});
		}
		return (WeatherObservedGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObservedGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = WeatherObservedGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected WeatherObservedGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (WeatherObservedGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return WeatherObservedGenPage.staticSearchPk(siteRequest_, WeatherObservedGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObservedGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = WeatherObservedGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObservedGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (WeatherObservedGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return WeatherObservedGenPage.staticSearchId(siteRequest_, WeatherObservedGenPage.staticSetId(siteRequest_, o)).toString();
	}

	////////////////////////////
	// pageUriWeatherObserved //
	////////////////////////////


	/**	 The entity pageUriWeatherObserved
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriWeatherObserved;

	/**	<br> The entity pageUriWeatherObserved
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.fiware.weatherobserved.WeatherObservedGenPage&fq=entiteVar_enUS_indexed_string:pageUriWeatherObserved">Find the entity pageUriWeatherObserved in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriWeatherObserved(Wrap<String> c);

	public String getPageUriWeatherObserved() {
		return pageUriWeatherObserved;
	}
	public void setPageUriWeatherObserved(String o) {
		this.pageUriWeatherObserved = WeatherObservedGenPage.staticSetPageUriWeatherObserved(siteRequest_, o);
	}
	public static String staticSetPageUriWeatherObserved(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected WeatherObservedGenPage pageUriWeatherObservedInit() {
		Wrap<String> pageUriWeatherObservedWrap = new Wrap<String>().var("pageUriWeatherObserved");
		if(pageUriWeatherObserved == null) {
			_pageUriWeatherObserved(pageUriWeatherObservedWrap);
			Optional.ofNullable(pageUriWeatherObservedWrap.getO()).ifPresent(o -> {
				setPageUriWeatherObserved(o);
			});
		}
		return (WeatherObservedGenPage)this;
	}

	public static String staticSearchPageUriWeatherObserved(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriWeatherObserved(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriWeatherObserved(SiteRequest siteRequest_, String o) {
		return WeatherObservedGenPage.staticSearchPageUriWeatherObserved(siteRequest_, WeatherObservedGenPage.staticSetPageUriWeatherObserved(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepWeatherObservedGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepWeatherObservedGenPage();
	}

	public Future<Void> promiseDeepWeatherObservedGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseWeatherObservedGenPage(promise2);
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

	public Future<Void> promiseWeatherObservedGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListWeatherObserved_Init();
				listWeatherObservedInit();
				weatherObservedCountInit();
				weatherObserved_Init();
				pkInit();
				idInit();
				pageUriWeatherObservedInit();
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
		return promiseDeepWeatherObservedGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestWeatherObservedGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestWeatherObservedGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainWeatherObservedGenPage(v);
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
	public Object obtainWeatherObservedGenPage(String var) {
		WeatherObservedGenPage oWeatherObservedGenPage = (WeatherObservedGenPage)this;
		switch(var) {
			case "searchListWeatherObserved_":
				return oWeatherObservedGenPage.searchListWeatherObserved_;
			case "listWeatherObserved":
				return oWeatherObservedGenPage.listWeatherObserved;
			case "weatherObservedCount":
				return oWeatherObservedGenPage.weatherObservedCount;
			case "weatherObserved_":
				return oWeatherObservedGenPage.weatherObserved_;
			case "pk":
				return oWeatherObservedGenPage.pk;
			case "id":
				return oWeatherObservedGenPage.id;
			case "pageUriWeatherObserved":
				return oWeatherObservedGenPage.pageUriWeatherObserved;
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
				o = relateWeatherObservedGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateWeatherObservedGenPage(String var, Object val) {
		WeatherObservedGenPage oWeatherObservedGenPage = (WeatherObservedGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetWeatherObservedGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetWeatherObservedGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listWeatherObserved":
			return WeatherObservedGenPage.staticSetListWeatherObserved(siteRequest_, o);
		case "weatherObservedCount":
			return WeatherObservedGenPage.staticSetWeatherObservedCount(siteRequest_, o);
		case "pk":
			return WeatherObservedGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return WeatherObservedGenPage.staticSetId(siteRequest_, o);
		case "pageUriWeatherObserved":
			return WeatherObservedGenPage.staticSetPageUriWeatherObserved(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchWeatherObservedGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchWeatherObservedGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listWeatherObserved":
			return WeatherObservedGenPage.staticSearchListWeatherObserved(siteRequest_, (JsonArray)o);
		case "weatherObservedCount":
			return WeatherObservedGenPage.staticSearchWeatherObservedCount(siteRequest_, (Integer)o);
		case "pk":
			return WeatherObservedGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return WeatherObservedGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriWeatherObserved":
			return WeatherObservedGenPage.staticSearchPageUriWeatherObserved(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrWeatherObservedGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrWeatherObservedGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listWeatherObserved":
			return WeatherObservedGenPage.staticSearchStrListWeatherObserved(siteRequest_, (String)o);
		case "weatherObservedCount":
			return WeatherObservedGenPage.staticSearchStrWeatherObservedCount(siteRequest_, (Integer)o);
		case "pk":
			return WeatherObservedGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return WeatherObservedGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriWeatherObserved":
			return WeatherObservedGenPage.staticSearchStrPageUriWeatherObserved(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqWeatherObservedGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqWeatherObservedGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listWeatherObserved":
			return WeatherObservedGenPage.staticSearchFqListWeatherObserved(siteRequest_, o);
		case "weatherObservedCount":
			return WeatherObservedGenPage.staticSearchFqWeatherObservedCount(siteRequest_, o);
		case "pk":
			return WeatherObservedGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return WeatherObservedGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriWeatherObserved":
			return WeatherObservedGenPage.staticSearchFqPageUriWeatherObserved(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "WeatherObservedGenPage";
	public static final String VAR_searchListWeatherObserved_ = "searchListWeatherObserved_";
	public static final String VAR_listWeatherObserved = "listWeatherObserved";
	public static final String VAR_weatherObservedCount = "weatherObservedCount";
	public static final String VAR_weatherObserved_ = "weatherObserved_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriWeatherObserved = "pageUriWeatherObserved";

	public static final String DISPLAY_NAME_searchListWeatherObserved_ = "";
	public static final String DISPLAY_NAME_listWeatherObserved = "";
	public static final String DISPLAY_NAME_weatherObservedCount = "";
	public static final String DISPLAY_NAME_weatherObserved_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriWeatherObserved = "";

	public static String displayNameForClass(String var) {
		return WeatherObservedGenPage.displayNameWeatherObservedGenPage(var);
	}
	public static String displayNameWeatherObservedGenPage(String var) {
		switch(var) {
		case VAR_searchListWeatherObserved_:
			return DISPLAY_NAME_searchListWeatherObserved_;
		case VAR_listWeatherObserved:
			return DISPLAY_NAME_listWeatherObserved;
		case VAR_weatherObservedCount:
			return DISPLAY_NAME_weatherObservedCount;
		case VAR_weatherObserved_:
			return DISPLAY_NAME_weatherObserved_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriWeatherObserved:
			return DISPLAY_NAME_pageUriWeatherObserved;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
