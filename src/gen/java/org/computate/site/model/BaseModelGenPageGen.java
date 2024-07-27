package org.computate.site.model;

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
import java.lang.String;
import org.computate.search.response.solr.SolrResponse.Stats;
import org.computate.search.response.solr.SolrResponse.FacetCounts;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Void;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these BaseModelGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class BaseModelGenPageGen into the class BaseModelGenPage. 
 * </li>
 * <h3>About the BaseModelGenPage class and it's generated class BaseModelGenPageGen&lt;PageLayout&gt;: </h3>extends BaseModelGenPageGen
 * <p>
 * This Java class extends a generated Java class BaseModelGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage">Find the class BaseModelGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends BaseModelGenPageGen<PageLayout>
 * <p>This <code>class BaseModelGenPage extends BaseModelGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated BaseModelGenPageGen. 
 * The generated <code>class BaseModelGenPageGen extends PageLayout</code> which means that BaseModelGenPage extends BaseModelGenPageGen which extends PageLayout. 
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
 * <p>By adding a class comment "{@inheritDoc}", the BaseModelGenPage class will inherit the helpful inherited class comments from the super class BaseModelGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the BaseModelGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class BaseModelGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class BaseModelGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOG = LoggerFactory.getLogger(BaseModelGenPage.class);

	//////////////////////////
	// searchListBaseModel_ //
	//////////////////////////


	/**	 The entity searchListBaseModel_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<BaseModel> searchListBaseModel_;

	/**	<br> The entity searchListBaseModel_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:searchListBaseModel_">Find the entity searchListBaseModel_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListBaseModel_(Wrap<SearchList<BaseModel>> w);

	public SearchList<BaseModel> getSearchListBaseModel_() {
		return searchListBaseModel_;
	}

	public void setSearchListBaseModel_(SearchList<BaseModel> searchListBaseModel_) {
		this.searchListBaseModel_ = searchListBaseModel_;
	}
	public static SearchList<BaseModel> staticSetSearchListBaseModel_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BaseModelGenPage searchListBaseModel_Init() {
		Wrap<SearchList<BaseModel>> searchListBaseModel_Wrap = new Wrap<SearchList<BaseModel>>().var("searchListBaseModel_");
		if(searchListBaseModel_ == null) {
			_searchListBaseModel_(searchListBaseModel_Wrap);
			Optional.ofNullable(searchListBaseModel_Wrap.getO()).ifPresent(o -> {
				setSearchListBaseModel_(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	//////////////////
	// pageResponse //
	//////////////////


	/**	 The entity pageResponse
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageResponse;

	/**	<br> The entity pageResponse
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:pageResponse">Find the entity pageResponse in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageResponse(Wrap<String> w);

	public String getPageResponse() {
		return pageResponse;
	}
	public void setPageResponse(String o) {
		this.pageResponse = BaseModelGenPage.staticSetPageResponse(siteRequest_, o);
	}
	public static String staticSetPageResponse(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModelGenPage pageResponseInit() {
		Wrap<String> pageResponseWrap = new Wrap<String>().var("pageResponse");
		if(pageResponse == null) {
			_pageResponse(pageResponseWrap);
			Optional.ofNullable(pageResponseWrap.getO()).ifPresent(o -> {
				setPageResponse(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	public static String staticSearchPageResponse(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageResponse(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageResponse(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchPageResponse(siteRequest_, BaseModelGenPage.staticSetPageResponse(siteRequest_, o)).toString();
	}

	///////////
	// stats //
	///////////


	/**	 The entity stats
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Stats stats;

	/**	<br> The entity stats
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:stats">Find the entity stats in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _stats(Wrap<Stats> w);

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}
	public static Stats staticSetStats(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BaseModelGenPage statsInit() {
		Wrap<Stats> statsWrap = new Wrap<Stats>().var("stats");
		if(stats == null) {
			_stats(statsWrap);
			Optional.ofNullable(statsWrap.getO()).ifPresent(o -> {
				setStats(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	/////////////////
	// facetCounts //
	/////////////////


	/**	 The entity facetCounts
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected FacetCounts facetCounts;

	/**	<br> The entity facetCounts
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:facetCounts">Find the entity facetCounts in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _facetCounts(Wrap<FacetCounts> w);

	public FacetCounts getFacetCounts() {
		return facetCounts;
	}

	public void setFacetCounts(FacetCounts facetCounts) {
		this.facetCounts = facetCounts;
	}
	public static FacetCounts staticSetFacetCounts(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BaseModelGenPage facetCountsInit() {
		Wrap<FacetCounts> facetCountsWrap = new Wrap<FacetCounts>().var("facetCounts");
		if(facetCounts == null) {
			_facetCounts(facetCountsWrap);
			Optional.ofNullable(facetCountsWrap.getO()).ifPresent(o -> {
				setFacetCounts(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	////////////////
	// pagination //
	////////////////


	/**	 The entity pagination
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject pagination = new JsonObject();

	/**	<br> The entity pagination
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:pagination">Find the entity pagination in Solr</a>
	 * <br>
	 * @param pagination is the entity already constructed. 
	 **/
	protected abstract void _pagination(JsonObject pagination);

	public JsonObject getPagination() {
		return pagination;
	}

	public void setPagination(JsonObject pagination) {
		this.pagination = pagination;
	}
	@JsonIgnore
	public void setPagination(String o) {
		this.pagination = BaseModelGenPage.staticSetPagination(siteRequest_, o);
	}
	public static JsonObject staticSetPagination(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected BaseModelGenPage paginationInit() {
		_pagination(pagination);
		return (BaseModelGenPage)this;
	}

	public static String staticSearchPagination(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrPagination(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPagination(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchPagination(siteRequest_, BaseModelGenPage.staticSetPagination(siteRequest_, o)).toString();
	}

	///////////
	// varsQ //
	///////////


	/**	 The entity varsQ
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject varsQ = new JsonObject();

	/**	<br> The entity varsQ
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:varsQ">Find the entity varsQ in Solr</a>
	 * <br>
	 * @param vars is the entity already constructed. 
	 **/
	protected abstract void _varsQ(JsonObject vars);

	public JsonObject getVarsQ() {
		return varsQ;
	}

	public void setVarsQ(JsonObject varsQ) {
		this.varsQ = varsQ;
	}
	@JsonIgnore
	public void setVarsQ(String o) {
		this.varsQ = BaseModelGenPage.staticSetVarsQ(siteRequest_, o);
	}
	public static JsonObject staticSetVarsQ(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected BaseModelGenPage varsQInit() {
		_varsQ(varsQ);
		return (BaseModelGenPage)this;
	}

	public static String staticSearchVarsQ(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrVarsQ(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqVarsQ(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchVarsQ(siteRequest_, BaseModelGenPage.staticSetVarsQ(siteRequest_, o)).toString();
	}

	////////////
	// varsFq //
	////////////


	/**	 The entity varsFq
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject varsFq = new JsonObject();

	/**	<br> The entity varsFq
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:varsFq">Find the entity varsFq in Solr</a>
	 * <br>
	 * @param vars is the entity already constructed. 
	 **/
	protected abstract void _varsFq(JsonObject vars);

	public JsonObject getVarsFq() {
		return varsFq;
	}

	public void setVarsFq(JsonObject varsFq) {
		this.varsFq = varsFq;
	}
	@JsonIgnore
	public void setVarsFq(String o) {
		this.varsFq = BaseModelGenPage.staticSetVarsFq(siteRequest_, o);
	}
	public static JsonObject staticSetVarsFq(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected BaseModelGenPage varsFqInit() {
		_varsFq(varsFq);
		return (BaseModelGenPage)this;
	}

	public static String staticSearchVarsFq(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrVarsFq(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqVarsFq(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchVarsFq(siteRequest_, BaseModelGenPage.staticSetVarsFq(siteRequest_, o)).toString();
	}

	///////////////
	// varsRange //
	///////////////


	/**	 The entity varsRange
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject varsRange = new JsonObject();

	/**	<br> The entity varsRange
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:varsRange">Find the entity varsRange in Solr</a>
	 * <br>
	 * @param vars is the entity already constructed. 
	 **/
	protected abstract void _varsRange(JsonObject vars);

	public JsonObject getVarsRange() {
		return varsRange;
	}

	public void setVarsRange(JsonObject varsRange) {
		this.varsRange = varsRange;
	}
	@JsonIgnore
	public void setVarsRange(String o) {
		this.varsRange = BaseModelGenPage.staticSetVarsRange(siteRequest_, o);
	}
	public static JsonObject staticSetVarsRange(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected BaseModelGenPage varsRangeInit() {
		_varsRange(varsRange);
		return (BaseModelGenPage)this;
	}

	public static String staticSearchVarsRange(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrVarsRange(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqVarsRange(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchVarsRange(siteRequest_, BaseModelGenPage.staticSetVarsRange(siteRequest_, o)).toString();
	}

	///////////
	// query //
	///////////


	/**	 The entity query
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject query = new JsonObject();

	/**	<br> The entity query
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:query">Find the entity query in Solr</a>
	 * <br>
	 * @param query is the entity already constructed. 
	 **/
	protected abstract void _query(JsonObject query);

	public JsonObject getQuery() {
		return query;
	}

	public void setQuery(JsonObject query) {
		this.query = query;
	}
	@JsonIgnore
	public void setQuery(String o) {
		this.query = BaseModelGenPage.staticSetQuery(siteRequest_, o);
	}
	public static JsonObject staticSetQuery(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected BaseModelGenPage queryInit() {
		_query(query);
		return (BaseModelGenPage)this;
	}

	public static String staticSearchQuery(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrQuery(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqQuery(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchQuery(siteRequest_, BaseModelGenPage.staticSetQuery(siteRequest_, o)).toString();
	}

	///////////////////////
	// defaultRangeStats //
	///////////////////////


	/**	 The entity defaultRangeStats
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonObject defaultRangeStats;

	/**	<br> The entity defaultRangeStats
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:defaultRangeStats">Find the entity defaultRangeStats in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _defaultRangeStats(Wrap<JsonObject> w);

	public JsonObject getDefaultRangeStats() {
		return defaultRangeStats;
	}

	public void setDefaultRangeStats(JsonObject defaultRangeStats) {
		this.defaultRangeStats = defaultRangeStats;
	}
	@JsonIgnore
	public void setDefaultRangeStats(String o) {
		this.defaultRangeStats = BaseModelGenPage.staticSetDefaultRangeStats(siteRequest_, o);
	}
	public static JsonObject staticSetDefaultRangeStats(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonObject(o);
		}
		return null;
	}
	protected BaseModelGenPage defaultRangeStatsInit() {
		Wrap<JsonObject> defaultRangeStatsWrap = new Wrap<JsonObject>().var("defaultRangeStats");
		if(defaultRangeStats == null) {
			_defaultRangeStats(defaultRangeStatsWrap);
			Optional.ofNullable(defaultRangeStatsWrap.getO()).ifPresent(o -> {
				setDefaultRangeStats(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	public static String staticSearchDefaultRangeStats(SiteRequest siteRequest_, JsonObject o) {
		return o.toString();
	}

	public static String staticSearchStrDefaultRangeStats(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultRangeStats(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchDefaultRangeStats(siteRequest_, BaseModelGenPage.staticSetDefaultRangeStats(siteRequest_, o)).toString();
	}

	//////////////////////////
	// defaultFieldListVars //
	//////////////////////////


	/**	 The entity defaultFieldListVars
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> defaultFieldListVars = new ArrayList<String>();

	/**	<br> The entity defaultFieldListVars
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:defaultFieldListVars">Find the entity defaultFieldListVars in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _defaultFieldListVars(List<String> l);

	public List<String> getDefaultFieldListVars() {
		return defaultFieldListVars;
	}

	public void setDefaultFieldListVars(List<String> defaultFieldListVars) {
		this.defaultFieldListVars = defaultFieldListVars;
	}
	public void setDefaultFieldListVars(String o) {
		String l = BaseModelGenPage.staticSetDefaultFieldListVars(siteRequest_, o);
		if(l != null)
			addDefaultFieldListVars(l);
	}
	public static String staticSetDefaultFieldListVars(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BaseModelGenPage addDefaultFieldListVars(String...objects) {
		for(String o : objects) {
			addDefaultFieldListVars(o);
		}
		return (BaseModelGenPage)this;
	}
	public BaseModelGenPage addDefaultFieldListVars(String o) {
		if(o != null)
			this.defaultFieldListVars.add(o);
		return (BaseModelGenPage)this;
	}
	@JsonIgnore
	public void setDefaultFieldListVars(JsonArray objects) {
		defaultFieldListVars.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addDefaultFieldListVars(o);
		}
	}
	protected BaseModelGenPage defaultFieldListVarsInit() {
		_defaultFieldListVars(defaultFieldListVars);
		return (BaseModelGenPage)this;
	}

	public static String staticSearchDefaultFieldListVars(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultFieldListVars(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultFieldListVars(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchDefaultFieldListVars(siteRequest_, BaseModelGenPage.staticSetDefaultFieldListVars(siteRequest_, o)).toString();
	}

	//////////////////////
	// defaultStatsVars //
	//////////////////////


	/**	 The entity defaultStatsVars
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> defaultStatsVars = new ArrayList<String>();

	/**	<br> The entity defaultStatsVars
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:defaultStatsVars">Find the entity defaultStatsVars in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _defaultStatsVars(List<String> l);

	public List<String> getDefaultStatsVars() {
		return defaultStatsVars;
	}

	public void setDefaultStatsVars(List<String> defaultStatsVars) {
		this.defaultStatsVars = defaultStatsVars;
	}
	public void setDefaultStatsVars(String o) {
		String l = BaseModelGenPage.staticSetDefaultStatsVars(siteRequest_, o);
		if(l != null)
			addDefaultStatsVars(l);
	}
	public static String staticSetDefaultStatsVars(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BaseModelGenPage addDefaultStatsVars(String...objects) {
		for(String o : objects) {
			addDefaultStatsVars(o);
		}
		return (BaseModelGenPage)this;
	}
	public BaseModelGenPage addDefaultStatsVars(String o) {
		if(o != null)
			this.defaultStatsVars.add(o);
		return (BaseModelGenPage)this;
	}
	@JsonIgnore
	public void setDefaultStatsVars(JsonArray objects) {
		defaultStatsVars.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addDefaultStatsVars(o);
		}
	}
	protected BaseModelGenPage defaultStatsVarsInit() {
		_defaultStatsVars(defaultStatsVars);
		return (BaseModelGenPage)this;
	}

	public static String staticSearchDefaultStatsVars(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultStatsVars(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultStatsVars(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchDefaultStatsVars(siteRequest_, BaseModelGenPage.staticSetDefaultStatsVars(siteRequest_, o)).toString();
	}

	//////////////////////
	// defaultPivotVars //
	//////////////////////


	/**	 The entity defaultPivotVars
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.ARRAY)
	@JsonInclude(Include.NON_NULL)
	protected List<String> defaultPivotVars = new ArrayList<String>();

	/**	<br> The entity defaultPivotVars
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:defaultPivotVars">Find the entity defaultPivotVars in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _defaultPivotVars(List<String> l);

	public List<String> getDefaultPivotVars() {
		return defaultPivotVars;
	}

	public void setDefaultPivotVars(List<String> defaultPivotVars) {
		this.defaultPivotVars = defaultPivotVars;
	}
	public void setDefaultPivotVars(String o) {
		String l = BaseModelGenPage.staticSetDefaultPivotVars(siteRequest_, o);
		if(l != null)
			addDefaultPivotVars(l);
	}
	public static String staticSetDefaultPivotVars(SiteRequest siteRequest_, String o) {
		return o;
	}
	public BaseModelGenPage addDefaultPivotVars(String...objects) {
		for(String o : objects) {
			addDefaultPivotVars(o);
		}
		return (BaseModelGenPage)this;
	}
	public BaseModelGenPage addDefaultPivotVars(String o) {
		if(o != null)
			this.defaultPivotVars.add(o);
		return (BaseModelGenPage)this;
	}
	@JsonIgnore
	public void setDefaultPivotVars(JsonArray objects) {
		defaultPivotVars.clear();
		if(objects == null)
			return;
		for(int i = 0; i < objects.size(); i++) {
			String o = objects.getString(i);
			addDefaultPivotVars(o);
		}
	}
	protected BaseModelGenPage defaultPivotVarsInit() {
		_defaultPivotVars(defaultPivotVars);
		return (BaseModelGenPage)this;
	}

	public static String staticSearchDefaultPivotVars(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDefaultPivotVars(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDefaultPivotVars(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchDefaultPivotVars(siteRequest_, BaseModelGenPage.staticSetDefaultPivotVars(siteRequest_, o)).toString();
	}

	///////////////////
	// listBaseModel //
	///////////////////


	/**	 The entity listBaseModel
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listBaseModel = new JsonArray();

	/**	<br> The entity listBaseModel
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:listBaseModel">Find the entity listBaseModel in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listBaseModel(JsonArray l);

	public JsonArray getListBaseModel() {
		return listBaseModel;
	}

	public void setListBaseModel(JsonArray listBaseModel) {
		this.listBaseModel = listBaseModel;
	}
	@JsonIgnore
	public void setListBaseModel(String o) {
		this.listBaseModel = BaseModelGenPage.staticSetListBaseModel(siteRequest_, o);
	}
	public static JsonArray staticSetListBaseModel(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected BaseModelGenPage listBaseModelInit() {
		_listBaseModel(listBaseModel);
		return (BaseModelGenPage)this;
	}

	public static String staticSearchListBaseModel(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListBaseModel(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListBaseModel(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchListBaseModel(siteRequest_, BaseModelGenPage.staticSetListBaseModel(siteRequest_, o)).toString();
	}

	////////////////////
	// baseModelCount //
	////////////////////


	/**	 The entity baseModelCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer baseModelCount;

	/**	<br> The entity baseModelCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:baseModelCount">Find the entity baseModelCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _baseModelCount(Wrap<Integer> w);

	public Integer getBaseModelCount() {
		return baseModelCount;
	}

	public void setBaseModelCount(Integer baseModelCount) {
		this.baseModelCount = baseModelCount;
	}
	@JsonIgnore
	public void setBaseModelCount(String o) {
		this.baseModelCount = BaseModelGenPage.staticSetBaseModelCount(siteRequest_, o);
	}
	public static Integer staticSetBaseModelCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected BaseModelGenPage baseModelCountInit() {
		Wrap<Integer> baseModelCountWrap = new Wrap<Integer>().var("baseModelCount");
		if(baseModelCount == null) {
			_baseModelCount(baseModelCountWrap);
			Optional.ofNullable(baseModelCountWrap.getO()).ifPresent(o -> {
				setBaseModelCount(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	public static Integer staticSearchBaseModelCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrBaseModelCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqBaseModelCount(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchBaseModelCount(siteRequest_, BaseModelGenPage.staticSetBaseModelCount(siteRequest_, o)).toString();
	}

	////////////////
	// baseModel_ //
	////////////////


	/**	 The entity baseModel_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected BaseModel baseModel_;

	/**	<br> The entity baseModel_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:baseModel_">Find the entity baseModel_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _baseModel_(Wrap<BaseModel> w);

	public BaseModel getBaseModel_() {
		return baseModel_;
	}

	public void setBaseModel_(BaseModel baseModel_) {
		this.baseModel_ = baseModel_;
	}
	public static BaseModel staticSetBaseModel_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected BaseModelGenPage baseModel_Init() {
		Wrap<BaseModel> baseModel_Wrap = new Wrap<BaseModel>().var("baseModel_");
		if(baseModel_ == null) {
			_baseModel_(baseModel_Wrap);
			Optional.ofNullable(baseModel_Wrap.getO()).ifPresent(o -> {
				setBaseModel_(o);
			});
		}
		return (BaseModelGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = BaseModelGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected BaseModelGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchPk(siteRequest_, BaseModelGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = BaseModelGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModelGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchId(siteRequest_, BaseModelGenPage.staticSetId(siteRequest_, o)).toString();
	}

	///////////////////
	// promiseBefore //
	///////////////////


	/**	 The entity promiseBefore
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Void promiseBefore;

	/**	<br> The entity promiseBefore
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:promiseBefore">Find the entity promiseBefore in Solr</a>
	 * <br>
	 * @param promise is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _promiseBefore(Promise<Void> promise);

	public Void getPromiseBefore() {
		return promiseBefore;
	}

	public void setPromiseBefore(Void promiseBefore) {
		this.promiseBefore = promiseBefore;
	}
	public static Void staticSetPromiseBefore(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected Future<Void> promiseBeforePromise() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		_promiseBefore(promise2);
		promise2.future().onSuccess(o -> {
			setPromiseBefore(o);
			promise.complete(o);
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	/////////////////////
	// classSimpleName //
	/////////////////////


	/**	 The entity classSimpleName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String classSimpleName;

	/**	<br> The entity classSimpleName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:classSimpleName">Find the entity classSimpleName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _classSimpleName(Wrap<String> w);

	public String getClassSimpleName() {
		return classSimpleName;
	}
	public void setClassSimpleName(String o) {
		this.classSimpleName = BaseModelGenPage.staticSetClassSimpleName(siteRequest_, o);
	}
	public static String staticSetClassSimpleName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModelGenPage classSimpleNameInit() {
		Wrap<String> classSimpleNameWrap = new Wrap<String>().var("classSimpleName");
		if(classSimpleName == null) {
			_classSimpleName(classSimpleNameWrap);
			Optional.ofNullable(classSimpleNameWrap.getO()).ifPresent(o -> {
				setClassSimpleName(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	public static String staticSearchClassSimpleName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrClassSimpleName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqClassSimpleName(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchClassSimpleName(siteRequest_, BaseModelGenPage.staticSetClassSimpleName(siteRequest_, o)).toString();
	}

	///////////////
	// pageTitle //
	///////////////


	/**	 The entity pageTitle
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageTitle;

	/**	<br> The entity pageTitle
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:pageTitle">Find the entity pageTitle in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageTitle(Wrap<String> c);

	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String o) {
		this.pageTitle = BaseModelGenPage.staticSetPageTitle(siteRequest_, o);
	}
	public static String staticSetPageTitle(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModelGenPage pageTitleInit() {
		Wrap<String> pageTitleWrap = new Wrap<String>().var("pageTitle");
		if(pageTitle == null) {
			_pageTitle(pageTitleWrap);
			Optional.ofNullable(pageTitleWrap.getO()).ifPresent(o -> {
				setPageTitle(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	public static String staticSearchPageTitle(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageTitle(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageTitle(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchPageTitle(siteRequest_, BaseModelGenPage.staticSetPageTitle(siteRequest_, o)).toString();
	}

	/////////////
	// pageUri //
	/////////////


	/**	 The entity pageUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUri;

	/**	<br> The entity pageUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:pageUri">Find the entity pageUri in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUri(Wrap<String> c);

	public String getPageUri() {
		return pageUri;
	}
	public void setPageUri(String o) {
		this.pageUri = BaseModelGenPage.staticSetPageUri(siteRequest_, o);
	}
	public static String staticSetPageUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModelGenPage pageUriInit() {
		Wrap<String> pageUriWrap = new Wrap<String>().var("pageUri");
		if(pageUri == null) {
			_pageUri(pageUriWrap);
			Optional.ofNullable(pageUriWrap.getO()).ifPresent(o -> {
				setPageUri(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	public static String staticSearchPageUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUri(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchPageUri(siteRequest_, BaseModelGenPage.staticSetPageUri(siteRequest_, o)).toString();
	}

	////////////
	// apiUri //
	////////////


	/**	 The entity apiUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String apiUri;

	/**	<br> The entity apiUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:apiUri">Find the entity apiUri in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _apiUri(Wrap<String> c);

	public String getApiUri() {
		return apiUri;
	}
	public void setApiUri(String o) {
		this.apiUri = BaseModelGenPage.staticSetApiUri(siteRequest_, o);
	}
	public static String staticSetApiUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModelGenPage apiUriInit() {
		Wrap<String> apiUriWrap = new Wrap<String>().var("apiUri");
		if(apiUri == null) {
			_apiUri(apiUriWrap);
			Optional.ofNullable(apiUriWrap.getO()).ifPresent(o -> {
				setApiUri(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	public static String staticSearchApiUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrApiUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqApiUri(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchApiUri(siteRequest_, BaseModelGenPage.staticSetApiUri(siteRequest_, o)).toString();
	}

	//////////////////
	// promiseAfter //
	//////////////////


	/**	 The entity promiseAfter
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Void promiseAfter;

	/**	<br> The entity promiseAfter
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:promiseAfter">Find the entity promiseAfter in Solr</a>
	 * <br>
	 * @param promise is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _promiseAfter(Promise<Void> promise);

	public Void getPromiseAfter() {
		return promiseAfter;
	}

	public void setPromiseAfter(Void promiseAfter) {
		this.promiseAfter = promiseAfter;
	}
	public static Void staticSetPromiseAfter(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected Future<Void> promiseAfterPromise() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		_promiseAfter(promise2);
		promise2.future().onSuccess(o -> {
			setPromiseAfter(o);
			promise.complete(o);
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	/////////////////////
	// pageDescription //
	/////////////////////


	/**	 The entity pageDescription
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageDescription;

	/**	<br> The entity pageDescription
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:pageDescription">Find the entity pageDescription in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageDescription(Wrap<String> c);

	public String getPageDescription() {
		return pageDescription;
	}
	public void setPageDescription(String o) {
		this.pageDescription = BaseModelGenPage.staticSetPageDescription(siteRequest_, o);
	}
	public static String staticSetPageDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModelGenPage pageDescriptionInit() {
		Wrap<String> pageDescriptionWrap = new Wrap<String>().var("pageDescription");
		if(pageDescription == null) {
			_pageDescription(pageDescriptionWrap);
			Optional.ofNullable(pageDescriptionWrap.getO()).ifPresent(o -> {
				setPageDescription(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	public static String staticSearchPageDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageDescription(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchPageDescription(siteRequest_, BaseModelGenPage.staticSetPageDescription(siteRequest_, o)).toString();
	}

	//////////////////
	// pageImageUri //
	//////////////////


	/**	 The entity pageImageUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageImageUri;

	/**	<br> The entity pageImageUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.BaseModelGenPage&fq=entiteVar_enUS_indexed_string:pageImageUri">Find the entity pageImageUri in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageImageUri(Wrap<String> c);

	public String getPageImageUri() {
		return pageImageUri;
	}
	public void setPageImageUri(String o) {
		this.pageImageUri = BaseModelGenPage.staticSetPageImageUri(siteRequest_, o);
	}
	public static String staticSetPageImageUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected BaseModelGenPage pageImageUriInit() {
		Wrap<String> pageImageUriWrap = new Wrap<String>().var("pageImageUri");
		if(pageImageUri == null) {
			_pageImageUri(pageImageUriWrap);
			Optional.ofNullable(pageImageUriWrap.getO()).ifPresent(o -> {
				setPageImageUri(o);
			});
		}
		return (BaseModelGenPage)this;
	}

	public static String staticSearchPageImageUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageImageUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageUri(SiteRequest siteRequest_, String o) {
		return BaseModelGenPage.staticSearchPageImageUri(siteRequest_, BaseModelGenPage.staticSetPageImageUri(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepBaseModelGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepBaseModelGenPage();
	}

	public Future<Void> promiseDeepBaseModelGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseBaseModelGenPage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepPageLayout(siteRequest_).onSuccess(b -> {
				promise.complete();
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseBaseModelGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListBaseModel_Init();
				pageResponseInit();
				statsInit();
				facetCountsInit();
				paginationInit();
				varsQInit();
				varsFqInit();
				varsRangeInit();
				queryInit();
				defaultRangeStatsInit();
				defaultFieldListVarsInit();
				defaultStatsVarsInit();
				defaultPivotVarsInit();
				listBaseModelInit();
				baseModelCountInit();
				baseModel_Init();
				pkInit();
				idInit();
				promise2.complete();
			} catch(Exception ex) {
				promise2.fail(ex);
			}
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			promiseBeforePromise().onSuccess(promiseBefore -> {
				promise2.complete();
			}).onFailure(ex -> {
				promise2.fail(ex);
			});
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				classSimpleNameInit();
				pageTitleInit();
				pageUriInit();
				apiUriInit();
				promise2.complete();
			} catch(Exception ex) {
				promise2.fail(ex);
			}
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			promiseAfterPromise().onSuccess(promiseAfter -> {
				promise2.complete();
			}).onFailure(ex -> {
				promise2.fail(ex);
			});
			return promise2.future();
		}).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				pageDescriptionInit();
				pageImageUriInit();
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
		return promiseDeepBaseModelGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestBaseModelGenPage(SiteRequest siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestBaseModelGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainBaseModelGenPage(v);
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
	public Object obtainBaseModelGenPage(String var) {
		BaseModelGenPage oBaseModelGenPage = (BaseModelGenPage)this;
		switch(var) {
			case "searchListBaseModel_":
				return oBaseModelGenPage.searchListBaseModel_;
			case "pageResponse":
				return oBaseModelGenPage.pageResponse;
			case "stats":
				return oBaseModelGenPage.stats;
			case "facetCounts":
				return oBaseModelGenPage.facetCounts;
			case "pagination":
				return oBaseModelGenPage.pagination;
			case "varsQ":
				return oBaseModelGenPage.varsQ;
			case "varsFq":
				return oBaseModelGenPage.varsFq;
			case "varsRange":
				return oBaseModelGenPage.varsRange;
			case "query":
				return oBaseModelGenPage.query;
			case "defaultRangeStats":
				return oBaseModelGenPage.defaultRangeStats;
			case "defaultFieldListVars":
				return oBaseModelGenPage.defaultFieldListVars;
			case "defaultStatsVars":
				return oBaseModelGenPage.defaultStatsVars;
			case "defaultPivotVars":
				return oBaseModelGenPage.defaultPivotVars;
			case "listBaseModel":
				return oBaseModelGenPage.listBaseModel;
			case "baseModelCount":
				return oBaseModelGenPage.baseModelCount;
			case "baseModel_":
				return oBaseModelGenPage.baseModel_;
			case "pk":
				return oBaseModelGenPage.pk;
			case "id":
				return oBaseModelGenPage.id;
			case "promiseBefore":
				return oBaseModelGenPage.promiseBefore;
			case "classSimpleName":
				return oBaseModelGenPage.classSimpleName;
			case "pageTitle":
				return oBaseModelGenPage.pageTitle;
			case "pageUri":
				return oBaseModelGenPage.pageUri;
			case "apiUri":
				return oBaseModelGenPage.apiUri;
			case "promiseAfter":
				return oBaseModelGenPage.promiseAfter;
			case "pageDescription":
				return oBaseModelGenPage.pageDescription;
			case "pageImageUri":
				return oBaseModelGenPage.pageImageUri;
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
				o = relateBaseModelGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateBaseModelGenPage(String var, Object val) {
		BaseModelGenPage oBaseModelGenPage = (BaseModelGenPage)this;
		switch(var) {
			default:
				return super.relatePageLayout(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetBaseModelGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetBaseModelGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "pageResponse":
			return BaseModelGenPage.staticSetPageResponse(siteRequest_, o);
		case "pagination":
			return BaseModelGenPage.staticSetPagination(siteRequest_, o);
		case "varsQ":
			return BaseModelGenPage.staticSetVarsQ(siteRequest_, o);
		case "varsFq":
			return BaseModelGenPage.staticSetVarsFq(siteRequest_, o);
		case "varsRange":
			return BaseModelGenPage.staticSetVarsRange(siteRequest_, o);
		case "query":
			return BaseModelGenPage.staticSetQuery(siteRequest_, o);
		case "defaultRangeStats":
			return BaseModelGenPage.staticSetDefaultRangeStats(siteRequest_, o);
		case "defaultFieldListVars":
			return BaseModelGenPage.staticSetDefaultFieldListVars(siteRequest_, o);
		case "defaultStatsVars":
			return BaseModelGenPage.staticSetDefaultStatsVars(siteRequest_, o);
		case "defaultPivotVars":
			return BaseModelGenPage.staticSetDefaultPivotVars(siteRequest_, o);
		case "listBaseModel":
			return BaseModelGenPage.staticSetListBaseModel(siteRequest_, o);
		case "baseModelCount":
			return BaseModelGenPage.staticSetBaseModelCount(siteRequest_, o);
		case "pk":
			return BaseModelGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return BaseModelGenPage.staticSetId(siteRequest_, o);
		case "classSimpleName":
			return BaseModelGenPage.staticSetClassSimpleName(siteRequest_, o);
		case "pageTitle":
			return BaseModelGenPage.staticSetPageTitle(siteRequest_, o);
		case "pageUri":
			return BaseModelGenPage.staticSetPageUri(siteRequest_, o);
		case "apiUri":
			return BaseModelGenPage.staticSetApiUri(siteRequest_, o);
		case "pageDescription":
			return BaseModelGenPage.staticSetPageDescription(siteRequest_, o);
		case "pageImageUri":
			return BaseModelGenPage.staticSetPageImageUri(siteRequest_, o);
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchBaseModelGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchBaseModelGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "pageResponse":
			return BaseModelGenPage.staticSearchPageResponse(siteRequest_, (String)o);
		case "pagination":
			return BaseModelGenPage.staticSearchPagination(siteRequest_, (JsonObject)o);
		case "varsQ":
			return BaseModelGenPage.staticSearchVarsQ(siteRequest_, (JsonObject)o);
		case "varsFq":
			return BaseModelGenPage.staticSearchVarsFq(siteRequest_, (JsonObject)o);
		case "varsRange":
			return BaseModelGenPage.staticSearchVarsRange(siteRequest_, (JsonObject)o);
		case "query":
			return BaseModelGenPage.staticSearchQuery(siteRequest_, (JsonObject)o);
		case "defaultRangeStats":
			return BaseModelGenPage.staticSearchDefaultRangeStats(siteRequest_, (JsonObject)o);
		case "defaultFieldListVars":
			return BaseModelGenPage.staticSearchDefaultFieldListVars(siteRequest_, (String)o);
		case "defaultStatsVars":
			return BaseModelGenPage.staticSearchDefaultStatsVars(siteRequest_, (String)o);
		case "defaultPivotVars":
			return BaseModelGenPage.staticSearchDefaultPivotVars(siteRequest_, (String)o);
		case "listBaseModel":
			return BaseModelGenPage.staticSearchListBaseModel(siteRequest_, (JsonArray)o);
		case "baseModelCount":
			return BaseModelGenPage.staticSearchBaseModelCount(siteRequest_, (Integer)o);
		case "pk":
			return BaseModelGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return BaseModelGenPage.staticSearchId(siteRequest_, (String)o);
		case "classSimpleName":
			return BaseModelGenPage.staticSearchClassSimpleName(siteRequest_, (String)o);
		case "pageTitle":
			return BaseModelGenPage.staticSearchPageTitle(siteRequest_, (String)o);
		case "pageUri":
			return BaseModelGenPage.staticSearchPageUri(siteRequest_, (String)o);
		case "apiUri":
			return BaseModelGenPage.staticSearchApiUri(siteRequest_, (String)o);
		case "pageDescription":
			return BaseModelGenPage.staticSearchPageDescription(siteRequest_, (String)o);
		case "pageImageUri":
			return BaseModelGenPage.staticSearchPageImageUri(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrBaseModelGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrBaseModelGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "pageResponse":
			return BaseModelGenPage.staticSearchStrPageResponse(siteRequest_, (String)o);
		case "pagination":
			return BaseModelGenPage.staticSearchStrPagination(siteRequest_, (String)o);
		case "varsQ":
			return BaseModelGenPage.staticSearchStrVarsQ(siteRequest_, (String)o);
		case "varsFq":
			return BaseModelGenPage.staticSearchStrVarsFq(siteRequest_, (String)o);
		case "varsRange":
			return BaseModelGenPage.staticSearchStrVarsRange(siteRequest_, (String)o);
		case "query":
			return BaseModelGenPage.staticSearchStrQuery(siteRequest_, (String)o);
		case "defaultRangeStats":
			return BaseModelGenPage.staticSearchStrDefaultRangeStats(siteRequest_, (String)o);
		case "defaultFieldListVars":
			return BaseModelGenPage.staticSearchStrDefaultFieldListVars(siteRequest_, (String)o);
		case "defaultStatsVars":
			return BaseModelGenPage.staticSearchStrDefaultStatsVars(siteRequest_, (String)o);
		case "defaultPivotVars":
			return BaseModelGenPage.staticSearchStrDefaultPivotVars(siteRequest_, (String)o);
		case "listBaseModel":
			return BaseModelGenPage.staticSearchStrListBaseModel(siteRequest_, (String)o);
		case "baseModelCount":
			return BaseModelGenPage.staticSearchStrBaseModelCount(siteRequest_, (Integer)o);
		case "pk":
			return BaseModelGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return BaseModelGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "classSimpleName":
			return BaseModelGenPage.staticSearchStrClassSimpleName(siteRequest_, (String)o);
		case "pageTitle":
			return BaseModelGenPage.staticSearchStrPageTitle(siteRequest_, (String)o);
		case "pageUri":
			return BaseModelGenPage.staticSearchStrPageUri(siteRequest_, (String)o);
		case "apiUri":
			return BaseModelGenPage.staticSearchStrApiUri(siteRequest_, (String)o);
		case "pageDescription":
			return BaseModelGenPage.staticSearchStrPageDescription(siteRequest_, (String)o);
		case "pageImageUri":
			return BaseModelGenPage.staticSearchStrPageImageUri(siteRequest_, (String)o);
			default:
				return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqBaseModelGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqBaseModelGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "pageResponse":
			return BaseModelGenPage.staticSearchFqPageResponse(siteRequest_, o);
		case "pagination":
			return BaseModelGenPage.staticSearchFqPagination(siteRequest_, o);
		case "varsQ":
			return BaseModelGenPage.staticSearchFqVarsQ(siteRequest_, o);
		case "varsFq":
			return BaseModelGenPage.staticSearchFqVarsFq(siteRequest_, o);
		case "varsRange":
			return BaseModelGenPage.staticSearchFqVarsRange(siteRequest_, o);
		case "query":
			return BaseModelGenPage.staticSearchFqQuery(siteRequest_, o);
		case "defaultRangeStats":
			return BaseModelGenPage.staticSearchFqDefaultRangeStats(siteRequest_, o);
		case "defaultFieldListVars":
			return BaseModelGenPage.staticSearchFqDefaultFieldListVars(siteRequest_, o);
		case "defaultStatsVars":
			return BaseModelGenPage.staticSearchFqDefaultStatsVars(siteRequest_, o);
		case "defaultPivotVars":
			return BaseModelGenPage.staticSearchFqDefaultPivotVars(siteRequest_, o);
		case "listBaseModel":
			return BaseModelGenPage.staticSearchFqListBaseModel(siteRequest_, o);
		case "baseModelCount":
			return BaseModelGenPage.staticSearchFqBaseModelCount(siteRequest_, o);
		case "pk":
			return BaseModelGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return BaseModelGenPage.staticSearchFqId(siteRequest_, o);
		case "classSimpleName":
			return BaseModelGenPage.staticSearchFqClassSimpleName(siteRequest_, o);
		case "pageTitle":
			return BaseModelGenPage.staticSearchFqPageTitle(siteRequest_, o);
		case "pageUri":
			return BaseModelGenPage.staticSearchFqPageUri(siteRequest_, o);
		case "apiUri":
			return BaseModelGenPage.staticSearchFqApiUri(siteRequest_, o);
		case "pageDescription":
			return BaseModelGenPage.staticSearchFqPageDescription(siteRequest_, o);
		case "pageImageUri":
			return BaseModelGenPage.staticSearchFqPageImageUri(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "BaseModelGenPage";
	public static final String VAR_searchListBaseModel_ = "searchListBaseModel_";
	public static final String VAR_pageResponse = "pageResponse";
	public static final String VAR_stats = "stats";
	public static final String VAR_facetCounts = "facetCounts";
	public static final String VAR_pagination = "pagination";
	public static final String VAR_varsQ = "varsQ";
	public static final String VAR_varsFq = "varsFq";
	public static final String VAR_varsRange = "varsRange";
	public static final String VAR_query = "query";
	public static final String VAR_defaultRangeStats = "defaultRangeStats";
	public static final String VAR_defaultFieldListVars = "defaultFieldListVars";
	public static final String VAR_defaultStatsVars = "defaultStatsVars";
	public static final String VAR_defaultPivotVars = "defaultPivotVars";
	public static final String VAR_listBaseModel = "listBaseModel";
	public static final String VAR_baseModelCount = "baseModelCount";
	public static final String VAR_baseModel_ = "baseModel_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_promiseBefore = "promiseBefore";
	public static final String VAR_classSimpleName = "classSimpleName";
	public static final String VAR_pageTitle = "pageTitle";
	public static final String VAR_pageUri = "pageUri";
	public static final String VAR_apiUri = "apiUri";
	public static final String VAR_promiseAfter = "promiseAfter";
	public static final String VAR_pageDescription = "pageDescription";
	public static final String VAR_pageImageUri = "pageImageUri";

	public static final String DISPLAY_NAME_searchListBaseModel_ = "";
	public static final String DISPLAY_NAME_pageResponse = "";
	public static final String DISPLAY_NAME_stats = "";
	public static final String DISPLAY_NAME_facetCounts = "";
	public static final String DISPLAY_NAME_pagination = "";
	public static final String DISPLAY_NAME_varsQ = "";
	public static final String DISPLAY_NAME_varsFq = "";
	public static final String DISPLAY_NAME_varsRange = "";
	public static final String DISPLAY_NAME_query = "";
	public static final String DISPLAY_NAME_defaultRangeStats = "";
	public static final String DISPLAY_NAME_defaultFieldListVars = "";
	public static final String DISPLAY_NAME_defaultStatsVars = "";
	public static final String DISPLAY_NAME_defaultPivotVars = "";
	public static final String DISPLAY_NAME_listBaseModel = "";
	public static final String DISPLAY_NAME_baseModelCount = "";
	public static final String DISPLAY_NAME_baseModel_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_promiseBefore = "";
	public static final String DISPLAY_NAME_classSimpleName = "";
	public static final String DISPLAY_NAME_pageTitle = "";
	public static final String DISPLAY_NAME_pageUri = "";
	public static final String DISPLAY_NAME_apiUri = "";
	public static final String DISPLAY_NAME_promiseAfter = "";
	public static final String DISPLAY_NAME_pageDescription = "";
	public static final String DISPLAY_NAME_pageImageUri = "";

	public static String displayNameForClass(String var) {
		return BaseModelGenPage.displayNameBaseModelGenPage(var);
	}
	public static String displayNameBaseModelGenPage(String var) {
		switch(var) {
		case VAR_searchListBaseModel_:
			return DISPLAY_NAME_searchListBaseModel_;
		case VAR_pageResponse:
			return DISPLAY_NAME_pageResponse;
		case VAR_stats:
			return DISPLAY_NAME_stats;
		case VAR_facetCounts:
			return DISPLAY_NAME_facetCounts;
		case VAR_pagination:
			return DISPLAY_NAME_pagination;
		case VAR_varsQ:
			return DISPLAY_NAME_varsQ;
		case VAR_varsFq:
			return DISPLAY_NAME_varsFq;
		case VAR_varsRange:
			return DISPLAY_NAME_varsRange;
		case VAR_query:
			return DISPLAY_NAME_query;
		case VAR_defaultRangeStats:
			return DISPLAY_NAME_defaultRangeStats;
		case VAR_defaultFieldListVars:
			return DISPLAY_NAME_defaultFieldListVars;
		case VAR_defaultStatsVars:
			return DISPLAY_NAME_defaultStatsVars;
		case VAR_defaultPivotVars:
			return DISPLAY_NAME_defaultPivotVars;
		case VAR_listBaseModel:
			return DISPLAY_NAME_listBaseModel;
		case VAR_baseModelCount:
			return DISPLAY_NAME_baseModelCount;
		case VAR_baseModel_:
			return DISPLAY_NAME_baseModel_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_promiseBefore:
			return DISPLAY_NAME_promiseBefore;
		case VAR_classSimpleName:
			return DISPLAY_NAME_classSimpleName;
		case VAR_pageTitle:
			return DISPLAY_NAME_pageTitle;
		case VAR_pageUri:
			return DISPLAY_NAME_pageUri;
		case VAR_apiUri:
			return DISPLAY_NAME_apiUri;
		case VAR_promiseAfter:
			return DISPLAY_NAME_promiseAfter;
		case VAR_pageDescription:
			return DISPLAY_NAME_pageDescription;
		case VAR_pageImageUri:
			return DISPLAY_NAME_pageImageUri;
		default:
			return PageLayout.displayNamePageLayout(var);
		}
	}
}
