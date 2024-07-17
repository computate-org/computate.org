package org.computate.site.model.research;

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
import org.computate.site.model.research.ComputateResearch;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these ComputateResearchGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ComputateResearchGenPageGen into the class ComputateResearchGenPage. 
 * </li>
 * <h3>About the ComputateResearchGenPage class and it's generated class ComputateResearchGenPageGen&lt;BaseModelPage&gt;: </h3>extends ComputateResearchGenPageGen
 * <p>
 * This Java class extends a generated Java class ComputateResearchGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearchGenPage">Find the class ComputateResearchGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ComputateResearchGenPageGen<BaseModelPage>
 * <p>This <code>class ComputateResearchGenPage extends ComputateResearchGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated ComputateResearchGenPageGen. 
 * The generated <code>class ComputateResearchGenPageGen extends BaseModelPage</code> which means that ComputateResearchGenPage extends ComputateResearchGenPageGen which extends BaseModelPage. 
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
 * <p>By adding a class comment "{@inheritDoc}", the ComputateResearchGenPage class will inherit the helpful inherited class comments from the super class ComputateResearchGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ComputateResearchGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class ComputateResearchGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearchGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.research in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.research&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ComputateResearchGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(ComputateResearchGenPage.class);

	//////////////////////////////////
	// searchListComputateResearch_ //
	//////////////////////////////////


	/**	 The entity searchListComputateResearch_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<ComputateResearch> searchListComputateResearch_;

	/**	<br> The entity searchListComputateResearch_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearchGenPage&fq=entiteVar_enUS_indexed_string:searchListComputateResearch_">Find the entity searchListComputateResearch_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListComputateResearch_(Wrap<SearchList<ComputateResearch>> w);

	public SearchList<ComputateResearch> getSearchListComputateResearch_() {
		return searchListComputateResearch_;
	}

	public void setSearchListComputateResearch_(SearchList<ComputateResearch> searchListComputateResearch_) {
		this.searchListComputateResearch_ = searchListComputateResearch_;
	}
	public static SearchList<ComputateResearch> staticSetSearchListComputateResearch_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ComputateResearchGenPage searchListComputateResearch_Init() {
		Wrap<SearchList<ComputateResearch>> searchListComputateResearch_Wrap = new Wrap<SearchList<ComputateResearch>>().var("searchListComputateResearch_");
		if(searchListComputateResearch_ == null) {
			_searchListComputateResearch_(searchListComputateResearch_Wrap);
			Optional.ofNullable(searchListComputateResearch_Wrap.getO()).ifPresent(o -> {
				setSearchListComputateResearch_(o);
			});
		}
		return (ComputateResearchGenPage)this;
	}

	///////////////////////////
	// listComputateResearch //
	///////////////////////////


	/**	 The entity listComputateResearch
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listComputateResearch = new JsonArray();

	/**	<br> The entity listComputateResearch
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearchGenPage&fq=entiteVar_enUS_indexed_string:listComputateResearch">Find the entity listComputateResearch in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listComputateResearch(JsonArray l);

	public JsonArray getListComputateResearch() {
		return listComputateResearch;
	}

	public void setListComputateResearch(JsonArray listComputateResearch) {
		this.listComputateResearch = listComputateResearch;
	}
	@JsonIgnore
	public void setListComputateResearch(String o) {
		this.listComputateResearch = ComputateResearchGenPage.staticSetListComputateResearch(siteRequest_, o);
	}
	public static JsonArray staticSetListComputateResearch(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected ComputateResearchGenPage listComputateResearchInit() {
		_listComputateResearch(listComputateResearch);
		return (ComputateResearchGenPage)this;
	}

	public static String staticSearchListComputateResearch(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListComputateResearch(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListComputateResearch(SiteRequest siteRequest_, String o) {
		return ComputateResearchGenPage.staticSearchListComputateResearch(siteRequest_, ComputateResearchGenPage.staticSetListComputateResearch(siteRequest_, o)).toString();
	}

	////////////////////////////
	// computateResearchCount //
	////////////////////////////


	/**	 The entity computateResearchCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer computateResearchCount;

	/**	<br> The entity computateResearchCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearchGenPage&fq=entiteVar_enUS_indexed_string:computateResearchCount">Find the entity computateResearchCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _computateResearchCount(Wrap<Integer> w);

	public Integer getComputateResearchCount() {
		return computateResearchCount;
	}

	public void setComputateResearchCount(Integer computateResearchCount) {
		this.computateResearchCount = computateResearchCount;
	}
	@JsonIgnore
	public void setComputateResearchCount(String o) {
		this.computateResearchCount = ComputateResearchGenPage.staticSetComputateResearchCount(siteRequest_, o);
	}
	public static Integer staticSetComputateResearchCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected ComputateResearchGenPage computateResearchCountInit() {
		Wrap<Integer> computateResearchCountWrap = new Wrap<Integer>().var("computateResearchCount");
		if(computateResearchCount == null) {
			_computateResearchCount(computateResearchCountWrap);
			Optional.ofNullable(computateResearchCountWrap.getO()).ifPresent(o -> {
				setComputateResearchCount(o);
			});
		}
		return (ComputateResearchGenPage)this;
	}

	public static Integer staticSearchComputateResearchCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrComputateResearchCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqComputateResearchCount(SiteRequest siteRequest_, String o) {
		return ComputateResearchGenPage.staticSearchComputateResearchCount(siteRequest_, ComputateResearchGenPage.staticSetComputateResearchCount(siteRequest_, o)).toString();
	}

	////////////////////////
	// computateResearch_ //
	////////////////////////


	/**	 The entity computateResearch_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected ComputateResearch computateResearch_;

	/**	<br> The entity computateResearch_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearchGenPage&fq=entiteVar_enUS_indexed_string:computateResearch_">Find the entity computateResearch_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _computateResearch_(Wrap<ComputateResearch> w);

	public ComputateResearch getComputateResearch_() {
		return computateResearch_;
	}

	public void setComputateResearch_(ComputateResearch computateResearch_) {
		this.computateResearch_ = computateResearch_;
	}
	public static ComputateResearch staticSetComputateResearch_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ComputateResearchGenPage computateResearch_Init() {
		Wrap<ComputateResearch> computateResearch_Wrap = new Wrap<ComputateResearch>().var("computateResearch_");
		if(computateResearch_ == null) {
			_computateResearch_(computateResearch_Wrap);
			Optional.ofNullable(computateResearch_Wrap.getO()).ifPresent(o -> {
				setComputateResearch_(o);
			});
		}
		return (ComputateResearchGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearchGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = ComputateResearchGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected ComputateResearchGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (ComputateResearchGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return ComputateResearchGenPage.staticSearchPk(siteRequest_, ComputateResearchGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearchGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = ComputateResearchGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateResearchGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (ComputateResearchGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return ComputateResearchGenPage.staticSearchId(siteRequest_, ComputateResearchGenPage.staticSetId(siteRequest_, o)).toString();
	}

	//////////////////////////////
	// pageUriComputateResearch //
	//////////////////////////////


	/**	 The entity pageUriComputateResearch
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriComputateResearch;

	/**	<br> The entity pageUriComputateResearch
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearchGenPage&fq=entiteVar_enUS_indexed_string:pageUriComputateResearch">Find the entity pageUriComputateResearch in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriComputateResearch(Wrap<String> c);

	public String getPageUriComputateResearch() {
		return pageUriComputateResearch;
	}
	public void setPageUriComputateResearch(String o) {
		this.pageUriComputateResearch = ComputateResearchGenPage.staticSetPageUriComputateResearch(siteRequest_, o);
	}
	public static String staticSetPageUriComputateResearch(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateResearchGenPage pageUriComputateResearchInit() {
		Wrap<String> pageUriComputateResearchWrap = new Wrap<String>().var("pageUriComputateResearch");
		if(pageUriComputateResearch == null) {
			_pageUriComputateResearch(pageUriComputateResearchWrap);
			Optional.ofNullable(pageUriComputateResearchWrap.getO()).ifPresent(o -> {
				setPageUriComputateResearch(o);
			});
		}
		return (ComputateResearchGenPage)this;
	}

	public static String staticSearchPageUriComputateResearch(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriComputateResearch(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriComputateResearch(SiteRequest siteRequest_, String o) {
		return ComputateResearchGenPage.staticSearchPageUriComputateResearch(siteRequest_, ComputateResearchGenPage.staticSetPageUriComputateResearch(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepComputateResearchGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepComputateResearchGenPage();
	}

	public Future<Void> promiseDeepComputateResearchGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseComputateResearchGenPage(promise2);
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

	public Future<Void> promiseComputateResearchGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListComputateResearch_Init();
				listComputateResearchInit();
				computateResearchCountInit();
				computateResearch_Init();
				pkInit();
				idInit();
				pageUriComputateResearchInit();
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
		return promiseDeepComputateResearchGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestComputateResearchGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestComputateResearchGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainComputateResearchGenPage(v);
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
	public Object obtainComputateResearchGenPage(String var) {
		ComputateResearchGenPage oComputateResearchGenPage = (ComputateResearchGenPage)this;
		switch(var) {
			case "searchListComputateResearch_":
				return oComputateResearchGenPage.searchListComputateResearch_;
			case "listComputateResearch":
				return oComputateResearchGenPage.listComputateResearch;
			case "computateResearchCount":
				return oComputateResearchGenPage.computateResearchCount;
			case "computateResearch_":
				return oComputateResearchGenPage.computateResearch_;
			case "pk":
				return oComputateResearchGenPage.pk;
			case "id":
				return oComputateResearchGenPage.id;
			case "pageUriComputateResearch":
				return oComputateResearchGenPage.pageUriComputateResearch;
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
				o = relateComputateResearchGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateComputateResearchGenPage(String var, Object val) {
		ComputateResearchGenPage oComputateResearchGenPage = (ComputateResearchGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetComputateResearchGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetComputateResearchGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listComputateResearch":
			return ComputateResearchGenPage.staticSetListComputateResearch(siteRequest_, o);
		case "computateResearchCount":
			return ComputateResearchGenPage.staticSetComputateResearchCount(siteRequest_, o);
		case "pk":
			return ComputateResearchGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return ComputateResearchGenPage.staticSetId(siteRequest_, o);
		case "pageUriComputateResearch":
			return ComputateResearchGenPage.staticSetPageUriComputateResearch(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchComputateResearchGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchComputateResearchGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listComputateResearch":
			return ComputateResearchGenPage.staticSearchListComputateResearch(siteRequest_, (JsonArray)o);
		case "computateResearchCount":
			return ComputateResearchGenPage.staticSearchComputateResearchCount(siteRequest_, (Integer)o);
		case "pk":
			return ComputateResearchGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return ComputateResearchGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriComputateResearch":
			return ComputateResearchGenPage.staticSearchPageUriComputateResearch(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrComputateResearchGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrComputateResearchGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listComputateResearch":
			return ComputateResearchGenPage.staticSearchStrListComputateResearch(siteRequest_, (String)o);
		case "computateResearchCount":
			return ComputateResearchGenPage.staticSearchStrComputateResearchCount(siteRequest_, (Integer)o);
		case "pk":
			return ComputateResearchGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return ComputateResearchGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriComputateResearch":
			return ComputateResearchGenPage.staticSearchStrPageUriComputateResearch(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqComputateResearchGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqComputateResearchGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listComputateResearch":
			return ComputateResearchGenPage.staticSearchFqListComputateResearch(siteRequest_, o);
		case "computateResearchCount":
			return ComputateResearchGenPage.staticSearchFqComputateResearchCount(siteRequest_, o);
		case "pk":
			return ComputateResearchGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return ComputateResearchGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriComputateResearch":
			return ComputateResearchGenPage.staticSearchFqPageUriComputateResearch(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "ComputateResearchGenPage";
	public static final String VAR_searchListComputateResearch_ = "searchListComputateResearch_";
	public static final String VAR_listComputateResearch = "listComputateResearch";
	public static final String VAR_computateResearchCount = "computateResearchCount";
	public static final String VAR_computateResearch_ = "computateResearch_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriComputateResearch = "pageUriComputateResearch";

	public static final String DISPLAY_NAME_searchListComputateResearch_ = "";
	public static final String DISPLAY_NAME_listComputateResearch = "";
	public static final String DISPLAY_NAME_computateResearchCount = "";
	public static final String DISPLAY_NAME_computateResearch_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriComputateResearch = "";

	public static String displayNameForClass(String var) {
		return ComputateResearchGenPage.displayNameComputateResearchGenPage(var);
	}
	public static String displayNameComputateResearchGenPage(String var) {
		switch(var) {
		case VAR_searchListComputateResearch_:
			return DISPLAY_NAME_searchListComputateResearch_;
		case VAR_listComputateResearch:
			return DISPLAY_NAME_listComputateResearch;
		case VAR_computateResearchCount:
			return DISPLAY_NAME_computateResearchCount;
		case VAR_computateResearch_:
			return DISPLAY_NAME_computateResearch_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriComputateResearch:
			return DISPLAY_NAME_pageUriComputateResearch;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
