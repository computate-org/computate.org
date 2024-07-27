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
import org.computate.site.model.website.CompanyWebsite;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyWebsiteGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyWebsiteGenPageGen into the class CompanyWebsiteGenPage. 
 * </li>
 * <h3>About the CompanyWebsiteGenPage class and it's generated class CompanyWebsiteGenPageGen&lt;BaseModelPage&gt;: </h3>extends CompanyWebsiteGenPageGen
 * <p>
 * This Java class extends a generated Java class CompanyWebsiteGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage">Find the class CompanyWebsiteGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyWebsiteGenPageGen<BaseModelPage>
 * <p>This <code>class CompanyWebsiteGenPage extends CompanyWebsiteGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated CompanyWebsiteGenPageGen. 
 * The generated <code>class CompanyWebsiteGenPageGen extends BaseModelPage</code> which means that CompanyWebsiteGenPage extends CompanyWebsiteGenPageGen which extends BaseModelPage. 
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
 * <p>By adding a class comment "{@inheritDoc}", the CompanyWebsiteGenPage class will inherit the helpful inherited class comments from the super class CompanyWebsiteGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyWebsiteGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class CompanyWebsiteGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
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
public abstract class CompanyWebsiteGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyWebsiteGenPage.class);

	///////////////////////////////
	// searchListCompanyWebsite_ //
	///////////////////////////////


	/**	 The entity searchListCompanyWebsite_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<CompanyWebsite> searchListCompanyWebsite_;

	/**	<br> The entity searchListCompanyWebsite_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:searchListCompanyWebsite_">Find the entity searchListCompanyWebsite_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListCompanyWebsite_(Wrap<SearchList<CompanyWebsite>> w);

	public SearchList<CompanyWebsite> getSearchListCompanyWebsite_() {
		return searchListCompanyWebsite_;
	}

	public void setSearchListCompanyWebsite_(SearchList<CompanyWebsite> searchListCompanyWebsite_) {
		this.searchListCompanyWebsite_ = searchListCompanyWebsite_;
	}
	public static SearchList<CompanyWebsite> staticSetSearchListCompanyWebsite_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyWebsiteGenPage searchListCompanyWebsite_Init() {
		Wrap<SearchList<CompanyWebsite>> searchListCompanyWebsite_Wrap = new Wrap<SearchList<CompanyWebsite>>().var("searchListCompanyWebsite_");
		if(searchListCompanyWebsite_ == null) {
			_searchListCompanyWebsite_(searchListCompanyWebsite_Wrap);
			Optional.ofNullable(searchListCompanyWebsite_Wrap.getO()).ifPresent(o -> {
				setSearchListCompanyWebsite_(o);
			});
		}
		return (CompanyWebsiteGenPage)this;
	}

	////////////////////////
	// listCompanyWebsite //
	////////////////////////


	/**	 The entity listCompanyWebsite
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listCompanyWebsite = new JsonArray();

	/**	<br> The entity listCompanyWebsite
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:listCompanyWebsite">Find the entity listCompanyWebsite in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listCompanyWebsite(JsonArray l);

	public JsonArray getListCompanyWebsite() {
		return listCompanyWebsite;
	}

	public void setListCompanyWebsite(JsonArray listCompanyWebsite) {
		this.listCompanyWebsite = listCompanyWebsite;
	}
	@JsonIgnore
	public void setListCompanyWebsite(String o) {
		this.listCompanyWebsite = CompanyWebsiteGenPage.staticSetListCompanyWebsite(siteRequest_, o);
	}
	public static JsonArray staticSetListCompanyWebsite(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected CompanyWebsiteGenPage listCompanyWebsiteInit() {
		_listCompanyWebsite(listCompanyWebsite);
		return (CompanyWebsiteGenPage)this;
	}

	public static String staticSearchListCompanyWebsite(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListCompanyWebsite(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListCompanyWebsite(SiteRequest siteRequest_, String o) {
		return CompanyWebsiteGenPage.staticSearchListCompanyWebsite(siteRequest_, CompanyWebsiteGenPage.staticSetListCompanyWebsite(siteRequest_, o)).toString();
	}

	/////////////////////////
	// companyWebsiteCount //
	/////////////////////////


	/**	 The entity companyWebsiteCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer companyWebsiteCount;

	/**	<br> The entity companyWebsiteCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:companyWebsiteCount">Find the entity companyWebsiteCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyWebsiteCount(Wrap<Integer> w);

	public Integer getCompanyWebsiteCount() {
		return companyWebsiteCount;
	}

	public void setCompanyWebsiteCount(Integer companyWebsiteCount) {
		this.companyWebsiteCount = companyWebsiteCount;
	}
	@JsonIgnore
	public void setCompanyWebsiteCount(String o) {
		this.companyWebsiteCount = CompanyWebsiteGenPage.staticSetCompanyWebsiteCount(siteRequest_, o);
	}
	public static Integer staticSetCompanyWebsiteCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyWebsiteGenPage companyWebsiteCountInit() {
		Wrap<Integer> companyWebsiteCountWrap = new Wrap<Integer>().var("companyWebsiteCount");
		if(companyWebsiteCount == null) {
			_companyWebsiteCount(companyWebsiteCountWrap);
			Optional.ofNullable(companyWebsiteCountWrap.getO()).ifPresent(o -> {
				setCompanyWebsiteCount(o);
			});
		}
		return (CompanyWebsiteGenPage)this;
	}

	public static Integer staticSearchCompanyWebsiteCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrCompanyWebsiteCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCompanyWebsiteCount(SiteRequest siteRequest_, String o) {
		return CompanyWebsiteGenPage.staticSearchCompanyWebsiteCount(siteRequest_, CompanyWebsiteGenPage.staticSetCompanyWebsiteCount(siteRequest_, o)).toString();
	}

	/////////////////////
	// companyWebsite_ //
	/////////////////////


	/**	 The entity companyWebsite_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected CompanyWebsite companyWebsite_;

	/**	<br> The entity companyWebsite_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:companyWebsite_">Find the entity companyWebsite_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _companyWebsite_(Wrap<CompanyWebsite> w);

	public CompanyWebsite getCompanyWebsite_() {
		return companyWebsite_;
	}

	public void setCompanyWebsite_(CompanyWebsite companyWebsite_) {
		this.companyWebsite_ = companyWebsite_;
	}
	public static CompanyWebsite staticSetCompanyWebsite_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected CompanyWebsiteGenPage companyWebsite_Init() {
		Wrap<CompanyWebsite> companyWebsite_Wrap = new Wrap<CompanyWebsite>().var("companyWebsite_");
		if(companyWebsite_ == null) {
			_companyWebsite_(companyWebsite_Wrap);
			Optional.ofNullable(companyWebsite_Wrap.getO()).ifPresent(o -> {
				setCompanyWebsite_(o);
			});
		}
		return (CompanyWebsiteGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = CompanyWebsiteGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected CompanyWebsiteGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (CompanyWebsiteGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return CompanyWebsiteGenPage.staticSearchPk(siteRequest_, CompanyWebsiteGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = CompanyWebsiteGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebsiteGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (CompanyWebsiteGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return CompanyWebsiteGenPage.staticSearchId(siteRequest_, CompanyWebsiteGenPage.staticSetId(siteRequest_, o)).toString();
	}

	///////////////////////////
	// pageUriCompanyWebsite //
	///////////////////////////


	/**	 The entity pageUriCompanyWebsite
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriCompanyWebsite;

	/**	<br> The entity pageUriCompanyWebsite
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.CompanyWebsiteGenPage&fq=entiteVar_enUS_indexed_string:pageUriCompanyWebsite">Find the entity pageUriCompanyWebsite in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriCompanyWebsite(Wrap<String> c);

	public String getPageUriCompanyWebsite() {
		return pageUriCompanyWebsite;
	}
	public void setPageUriCompanyWebsite(String o) {
		this.pageUriCompanyWebsite = CompanyWebsiteGenPage.staticSetPageUriCompanyWebsite(siteRequest_, o);
	}
	public static String staticSetPageUriCompanyWebsite(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyWebsiteGenPage pageUriCompanyWebsiteInit() {
		Wrap<String> pageUriCompanyWebsiteWrap = new Wrap<String>().var("pageUriCompanyWebsite");
		if(pageUriCompanyWebsite == null) {
			_pageUriCompanyWebsite(pageUriCompanyWebsiteWrap);
			Optional.ofNullable(pageUriCompanyWebsiteWrap.getO()).ifPresent(o -> {
				setPageUriCompanyWebsite(o);
			});
		}
		return (CompanyWebsiteGenPage)this;
	}

	public static String staticSearchPageUriCompanyWebsite(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriCompanyWebsite(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriCompanyWebsite(SiteRequest siteRequest_, String o) {
		return CompanyWebsiteGenPage.staticSearchPageUriCompanyWebsite(siteRequest_, CompanyWebsiteGenPage.staticSetPageUriCompanyWebsite(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepCompanyWebsiteGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyWebsiteGenPage();
	}

	public Future<Void> promiseDeepCompanyWebsiteGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyWebsiteGenPage(promise2);
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

	public Future<Void> promiseCompanyWebsiteGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListCompanyWebsite_Init();
				listCompanyWebsiteInit();
				companyWebsiteCountInit();
				companyWebsite_Init();
				pkInit();
				idInit();
				pageUriCompanyWebsiteInit();
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
		return promiseDeepCompanyWebsiteGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyWebsiteGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyWebsiteGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyWebsiteGenPage(v);
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
	public Object obtainCompanyWebsiteGenPage(String var) {
		CompanyWebsiteGenPage oCompanyWebsiteGenPage = (CompanyWebsiteGenPage)this;
		switch(var) {
			case "searchListCompanyWebsite_":
				return oCompanyWebsiteGenPage.searchListCompanyWebsite_;
			case "listCompanyWebsite":
				return oCompanyWebsiteGenPage.listCompanyWebsite;
			case "companyWebsiteCount":
				return oCompanyWebsiteGenPage.companyWebsiteCount;
			case "companyWebsite_":
				return oCompanyWebsiteGenPage.companyWebsite_;
			case "pk":
				return oCompanyWebsiteGenPage.pk;
			case "id":
				return oCompanyWebsiteGenPage.id;
			case "pageUriCompanyWebsite":
				return oCompanyWebsiteGenPage.pageUriCompanyWebsite;
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
				o = relateCompanyWebsiteGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyWebsiteGenPage(String var, Object val) {
		CompanyWebsiteGenPage oCompanyWebsiteGenPage = (CompanyWebsiteGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyWebsiteGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyWebsiteGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyWebsite":
			return CompanyWebsiteGenPage.staticSetListCompanyWebsite(siteRequest_, o);
		case "companyWebsiteCount":
			return CompanyWebsiteGenPage.staticSetCompanyWebsiteCount(siteRequest_, o);
		case "pk":
			return CompanyWebsiteGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return CompanyWebsiteGenPage.staticSetId(siteRequest_, o);
		case "pageUriCompanyWebsite":
			return CompanyWebsiteGenPage.staticSetPageUriCompanyWebsite(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyWebsiteGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyWebsiteGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchListCompanyWebsite(siteRequest_, (JsonArray)o);
		case "companyWebsiteCount":
			return CompanyWebsiteGenPage.staticSearchCompanyWebsiteCount(siteRequest_, (Integer)o);
		case "pk":
			return CompanyWebsiteGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return CompanyWebsiteGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchPageUriCompanyWebsite(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyWebsiteGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyWebsiteGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchStrListCompanyWebsite(siteRequest_, (String)o);
		case "companyWebsiteCount":
			return CompanyWebsiteGenPage.staticSearchStrCompanyWebsiteCount(siteRequest_, (Integer)o);
		case "pk":
			return CompanyWebsiteGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return CompanyWebsiteGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchStrPageUriCompanyWebsite(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyWebsiteGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyWebsiteGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchFqListCompanyWebsite(siteRequest_, o);
		case "companyWebsiteCount":
			return CompanyWebsiteGenPage.staticSearchFqCompanyWebsiteCount(siteRequest_, o);
		case "pk":
			return CompanyWebsiteGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return CompanyWebsiteGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriCompanyWebsite":
			return CompanyWebsiteGenPage.staticSearchFqPageUriCompanyWebsite(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyWebsiteGenPage";
	public static final String VAR_searchListCompanyWebsite_ = "searchListCompanyWebsite_";
	public static final String VAR_listCompanyWebsite = "listCompanyWebsite";
	public static final String VAR_companyWebsiteCount = "companyWebsiteCount";
	public static final String VAR_companyWebsite_ = "companyWebsite_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriCompanyWebsite = "pageUriCompanyWebsite";

	public static final String DISPLAY_NAME_searchListCompanyWebsite_ = "";
	public static final String DISPLAY_NAME_listCompanyWebsite = "";
	public static final String DISPLAY_NAME_companyWebsiteCount = "";
	public static final String DISPLAY_NAME_companyWebsite_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriCompanyWebsite = "";

	public static String displayNameForClass(String var) {
		return CompanyWebsiteGenPage.displayNameCompanyWebsiteGenPage(var);
	}
	public static String displayNameCompanyWebsiteGenPage(String var) {
		switch(var) {
		case VAR_searchListCompanyWebsite_:
			return DISPLAY_NAME_searchListCompanyWebsite_;
		case VAR_listCompanyWebsite:
			return DISPLAY_NAME_listCompanyWebsite;
		case VAR_companyWebsiteCount:
			return DISPLAY_NAME_companyWebsiteCount;
		case VAR_companyWebsite_:
			return DISPLAY_NAME_companyWebsite_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriCompanyWebsite:
			return DISPLAY_NAME_pageUriCompanyWebsite;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
