package org.computate.site.model.website;

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
import org.computate.site.model.website.ComputateWebsite;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these ComputateWebsiteGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ComputateWebsiteGenPageGen into the class ComputateWebsiteGenPage. 
 * </li>
 * <h3>About the ComputateWebsiteGenPage class and it's generated class ComputateWebsiteGenPageGen&lt;BaseModelPage&gt;: </h3>extends ComputateWebsiteGenPageGen
 * <p>
 * This Java class extends a generated Java class ComputateWebsiteGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsiteGenPage">Find the class ComputateWebsiteGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ComputateWebsiteGenPageGen<BaseModelPage>
 * <p>This <code>class ComputateWebsiteGenPage extends ComputateWebsiteGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated ComputateWebsiteGenPageGen. 
 * The generated <code>class ComputateWebsiteGenPageGen extends BaseModelPage</code> which means that ComputateWebsiteGenPage extends ComputateWebsiteGenPageGen which extends BaseModelPage. 
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
 * <p>By adding a class comment "{@inheritDoc}", the ComputateWebsiteGenPage class will inherit the helpful inherited class comments from the super class ComputateWebsiteGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ComputateWebsiteGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class ComputateWebsiteGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsiteGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.website in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.website&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ComputateWebsiteGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(ComputateWebsiteGenPage.class);

	/////////////////////////////////
	// searchListComputateWebsite_ //
	/////////////////////////////////


	/**	 The entity searchListComputateWebsite_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<ComputateWebsite> searchListComputateWebsite_;

	/**	<br> The entity searchListComputateWebsite_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsiteGenPage&fq=entiteVar_enUS_indexed_string:searchListComputateWebsite_">Find the entity searchListComputateWebsite_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListComputateWebsite_(Wrap<SearchList<ComputateWebsite>> w);

	public SearchList<ComputateWebsite> getSearchListComputateWebsite_() {
		return searchListComputateWebsite_;
	}

	public void setSearchListComputateWebsite_(SearchList<ComputateWebsite> searchListComputateWebsite_) {
		this.searchListComputateWebsite_ = searchListComputateWebsite_;
	}
	public static SearchList<ComputateWebsite> staticSetSearchListComputateWebsite_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ComputateWebsiteGenPage searchListComputateWebsite_Init() {
		Wrap<SearchList<ComputateWebsite>> searchListComputateWebsite_Wrap = new Wrap<SearchList<ComputateWebsite>>().var("searchListComputateWebsite_");
		if(searchListComputateWebsite_ == null) {
			_searchListComputateWebsite_(searchListComputateWebsite_Wrap);
			Optional.ofNullable(searchListComputateWebsite_Wrap.getO()).ifPresent(o -> {
				setSearchListComputateWebsite_(o);
			});
		}
		return (ComputateWebsiteGenPage)this;
	}

	//////////////////////////
	// listComputateWebsite //
	//////////////////////////


	/**	 The entity listComputateWebsite
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listComputateWebsite = new JsonArray();

	/**	<br> The entity listComputateWebsite
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsiteGenPage&fq=entiteVar_enUS_indexed_string:listComputateWebsite">Find the entity listComputateWebsite in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listComputateWebsite(JsonArray l);

	public JsonArray getListComputateWebsite() {
		return listComputateWebsite;
	}

	public void setListComputateWebsite(JsonArray listComputateWebsite) {
		this.listComputateWebsite = listComputateWebsite;
	}
	@JsonIgnore
	public void setListComputateWebsite(String o) {
		this.listComputateWebsite = ComputateWebsiteGenPage.staticSetListComputateWebsite(siteRequest_, o);
	}
	public static JsonArray staticSetListComputateWebsite(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected ComputateWebsiteGenPage listComputateWebsiteInit() {
		_listComputateWebsite(listComputateWebsite);
		return (ComputateWebsiteGenPage)this;
	}

	public static String staticSearchListComputateWebsite(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListComputateWebsite(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListComputateWebsite(SiteRequest siteRequest_, String o) {
		return ComputateWebsiteGenPage.staticSearchListComputateWebsite(siteRequest_, ComputateWebsiteGenPage.staticSetListComputateWebsite(siteRequest_, o)).toString();
	}

	///////////////////////////
	// computateWebsiteCount //
	///////////////////////////


	/**	 The entity computateWebsiteCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer computateWebsiteCount;

	/**	<br> The entity computateWebsiteCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsiteGenPage&fq=entiteVar_enUS_indexed_string:computateWebsiteCount">Find the entity computateWebsiteCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _computateWebsiteCount(Wrap<Integer> w);

	public Integer getComputateWebsiteCount() {
		return computateWebsiteCount;
	}

	public void setComputateWebsiteCount(Integer computateWebsiteCount) {
		this.computateWebsiteCount = computateWebsiteCount;
	}
	@JsonIgnore
	public void setComputateWebsiteCount(String o) {
		this.computateWebsiteCount = ComputateWebsiteGenPage.staticSetComputateWebsiteCount(siteRequest_, o);
	}
	public static Integer staticSetComputateWebsiteCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected ComputateWebsiteGenPage computateWebsiteCountInit() {
		Wrap<Integer> computateWebsiteCountWrap = new Wrap<Integer>().var("computateWebsiteCount");
		if(computateWebsiteCount == null) {
			_computateWebsiteCount(computateWebsiteCountWrap);
			Optional.ofNullable(computateWebsiteCountWrap.getO()).ifPresent(o -> {
				setComputateWebsiteCount(o);
			});
		}
		return (ComputateWebsiteGenPage)this;
	}

	public static Integer staticSearchComputateWebsiteCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrComputateWebsiteCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqComputateWebsiteCount(SiteRequest siteRequest_, String o) {
		return ComputateWebsiteGenPage.staticSearchComputateWebsiteCount(siteRequest_, ComputateWebsiteGenPage.staticSetComputateWebsiteCount(siteRequest_, o)).toString();
	}

	///////////////////////
	// computateWebsite_ //
	///////////////////////


	/**	 The entity computateWebsite_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected ComputateWebsite computateWebsite_;

	/**	<br> The entity computateWebsite_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsiteGenPage&fq=entiteVar_enUS_indexed_string:computateWebsite_">Find the entity computateWebsite_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _computateWebsite_(Wrap<ComputateWebsite> w);

	public ComputateWebsite getComputateWebsite_() {
		return computateWebsite_;
	}

	public void setComputateWebsite_(ComputateWebsite computateWebsite_) {
		this.computateWebsite_ = computateWebsite_;
	}
	public static ComputateWebsite staticSetComputateWebsite_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ComputateWebsiteGenPage computateWebsite_Init() {
		Wrap<ComputateWebsite> computateWebsite_Wrap = new Wrap<ComputateWebsite>().var("computateWebsite_");
		if(computateWebsite_ == null) {
			_computateWebsite_(computateWebsite_Wrap);
			Optional.ofNullable(computateWebsite_Wrap.getO()).ifPresent(o -> {
				setComputateWebsite_(o);
			});
		}
		return (ComputateWebsiteGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsiteGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = ComputateWebsiteGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected ComputateWebsiteGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (ComputateWebsiteGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return ComputateWebsiteGenPage.staticSearchPk(siteRequest_, ComputateWebsiteGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsiteGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = ComputateWebsiteGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateWebsiteGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (ComputateWebsiteGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return ComputateWebsiteGenPage.staticSearchId(siteRequest_, ComputateWebsiteGenPage.staticSetId(siteRequest_, o)).toString();
	}

	/////////////////////////////
	// pageUriComputateWebsite //
	/////////////////////////////


	/**	 The entity pageUriComputateWebsite
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriComputateWebsite;

	/**	<br> The entity pageUriComputateWebsite
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsiteGenPage&fq=entiteVar_enUS_indexed_string:pageUriComputateWebsite">Find the entity pageUriComputateWebsite in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriComputateWebsite(Wrap<String> c);

	public String getPageUriComputateWebsite() {
		return pageUriComputateWebsite;
	}
	public void setPageUriComputateWebsite(String o) {
		this.pageUriComputateWebsite = ComputateWebsiteGenPage.staticSetPageUriComputateWebsite(siteRequest_, o);
	}
	public static String staticSetPageUriComputateWebsite(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateWebsiteGenPage pageUriComputateWebsiteInit() {
		Wrap<String> pageUriComputateWebsiteWrap = new Wrap<String>().var("pageUriComputateWebsite");
		if(pageUriComputateWebsite == null) {
			_pageUriComputateWebsite(pageUriComputateWebsiteWrap);
			Optional.ofNullable(pageUriComputateWebsiteWrap.getO()).ifPresent(o -> {
				setPageUriComputateWebsite(o);
			});
		}
		return (ComputateWebsiteGenPage)this;
	}

	public static String staticSearchPageUriComputateWebsite(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriComputateWebsite(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriComputateWebsite(SiteRequest siteRequest_, String o) {
		return ComputateWebsiteGenPage.staticSearchPageUriComputateWebsite(siteRequest_, ComputateWebsiteGenPage.staticSetPageUriComputateWebsite(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepComputateWebsiteGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepComputateWebsiteGenPage();
	}

	public Future<Void> promiseDeepComputateWebsiteGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseComputateWebsiteGenPage(promise2);
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

	public Future<Void> promiseComputateWebsiteGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListComputateWebsite_Init();
				listComputateWebsiteInit();
				computateWebsiteCountInit();
				computateWebsite_Init();
				pkInit();
				idInit();
				pageUriComputateWebsiteInit();
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
		return promiseDeepComputateWebsiteGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestComputateWebsiteGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestComputateWebsiteGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainComputateWebsiteGenPage(v);
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
	public Object obtainComputateWebsiteGenPage(String var) {
		ComputateWebsiteGenPage oComputateWebsiteGenPage = (ComputateWebsiteGenPage)this;
		switch(var) {
			case "searchListComputateWebsite_":
				return oComputateWebsiteGenPage.searchListComputateWebsite_;
			case "listComputateWebsite":
				return oComputateWebsiteGenPage.listComputateWebsite;
			case "computateWebsiteCount":
				return oComputateWebsiteGenPage.computateWebsiteCount;
			case "computateWebsite_":
				return oComputateWebsiteGenPage.computateWebsite_;
			case "pk":
				return oComputateWebsiteGenPage.pk;
			case "id":
				return oComputateWebsiteGenPage.id;
			case "pageUriComputateWebsite":
				return oComputateWebsiteGenPage.pageUriComputateWebsite;
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
				o = relateComputateWebsiteGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateComputateWebsiteGenPage(String var, Object val) {
		ComputateWebsiteGenPage oComputateWebsiteGenPage = (ComputateWebsiteGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetComputateWebsiteGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetComputateWebsiteGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listComputateWebsite":
			return ComputateWebsiteGenPage.staticSetListComputateWebsite(siteRequest_, o);
		case "computateWebsiteCount":
			return ComputateWebsiteGenPage.staticSetComputateWebsiteCount(siteRequest_, o);
		case "pk":
			return ComputateWebsiteGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return ComputateWebsiteGenPage.staticSetId(siteRequest_, o);
		case "pageUriComputateWebsite":
			return ComputateWebsiteGenPage.staticSetPageUriComputateWebsite(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchComputateWebsiteGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchComputateWebsiteGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listComputateWebsite":
			return ComputateWebsiteGenPage.staticSearchListComputateWebsite(siteRequest_, (JsonArray)o);
		case "computateWebsiteCount":
			return ComputateWebsiteGenPage.staticSearchComputateWebsiteCount(siteRequest_, (Integer)o);
		case "pk":
			return ComputateWebsiteGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return ComputateWebsiteGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriComputateWebsite":
			return ComputateWebsiteGenPage.staticSearchPageUriComputateWebsite(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrComputateWebsiteGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrComputateWebsiteGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listComputateWebsite":
			return ComputateWebsiteGenPage.staticSearchStrListComputateWebsite(siteRequest_, (String)o);
		case "computateWebsiteCount":
			return ComputateWebsiteGenPage.staticSearchStrComputateWebsiteCount(siteRequest_, (Integer)o);
		case "pk":
			return ComputateWebsiteGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return ComputateWebsiteGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriComputateWebsite":
			return ComputateWebsiteGenPage.staticSearchStrPageUriComputateWebsite(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqComputateWebsiteGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqComputateWebsiteGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listComputateWebsite":
			return ComputateWebsiteGenPage.staticSearchFqListComputateWebsite(siteRequest_, o);
		case "computateWebsiteCount":
			return ComputateWebsiteGenPage.staticSearchFqComputateWebsiteCount(siteRequest_, o);
		case "pk":
			return ComputateWebsiteGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return ComputateWebsiteGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriComputateWebsite":
			return ComputateWebsiteGenPage.staticSearchFqPageUriComputateWebsite(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "ComputateWebsiteGenPage";
	public static final String VAR_searchListComputateWebsite_ = "searchListComputateWebsite_";
	public static final String VAR_listComputateWebsite = "listComputateWebsite";
	public static final String VAR_computateWebsiteCount = "computateWebsiteCount";
	public static final String VAR_computateWebsite_ = "computateWebsite_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriComputateWebsite = "pageUriComputateWebsite";

	public static final String DISPLAY_NAME_searchListComputateWebsite_ = "";
	public static final String DISPLAY_NAME_listComputateWebsite = "";
	public static final String DISPLAY_NAME_computateWebsiteCount = "";
	public static final String DISPLAY_NAME_computateWebsite_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriComputateWebsite = "";

	public static String displayNameForClass(String var) {
		return ComputateWebsiteGenPage.displayNameComputateWebsiteGenPage(var);
	}
	public static String displayNameComputateWebsiteGenPage(String var) {
		switch(var) {
		case VAR_searchListComputateWebsite_:
			return DISPLAY_NAME_searchListComputateWebsite_;
		case VAR_listComputateWebsite:
			return DISPLAY_NAME_listComputateWebsite;
		case VAR_computateWebsiteCount:
			return DISPLAY_NAME_computateWebsiteCount;
		case VAR_computateWebsite_:
			return DISPLAY_NAME_computateWebsite_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriComputateWebsite:
			return DISPLAY_NAME_pageUriComputateWebsite;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
