package org.computate.site.model.course;

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
import org.computate.site.model.course.ComputateCourse;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these ComputateCourseGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ComputateCourseGenPageGen into the class ComputateCourseGenPage. 
 * </li>
 * <h3>About the ComputateCourseGenPage class and it's generated class ComputateCourseGenPageGen&lt;BaseModelPage&gt;: </h3>extends ComputateCourseGenPageGen
 * <p>
 * This Java class extends a generated Java class ComputateCourseGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourseGenPage">Find the class ComputateCourseGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ComputateCourseGenPageGen<BaseModelPage>
 * <p>This <code>class ComputateCourseGenPage extends ComputateCourseGenPageGen&lt;BaseModelPage&gt;</code>, which means it extends a newly generated ComputateCourseGenPageGen. 
 * The generated <code>class ComputateCourseGenPageGen extends BaseModelPage</code> which means that ComputateCourseGenPage extends ComputateCourseGenPageGen which extends BaseModelPage. 
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
 * <p>By adding a class comment "{@inheritDoc}", the ComputateCourseGenPage class will inherit the helpful inherited class comments from the super class ComputateCourseGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ComputateCourseGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class ComputateCourseGenPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourseGenPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.course in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.course&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ComputateCourseGenPageGen<DEV> extends BaseModelPage {
	protected static final Logger LOG = LoggerFactory.getLogger(ComputateCourseGenPage.class);

	////////////////////////////////
	// searchListComputateCourse_ //
	////////////////////////////////


	/**	 The entity searchListComputateCourse_
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<ComputateCourse> searchListComputateCourse_;

	/**	<br> The entity searchListComputateCourse_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourseGenPage&fq=entiteVar_enUS_indexed_string:searchListComputateCourse_">Find the entity searchListComputateCourse_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _searchListComputateCourse_(Wrap<SearchList<ComputateCourse>> w);

	public SearchList<ComputateCourse> getSearchListComputateCourse_() {
		return searchListComputateCourse_;
	}

	public void setSearchListComputateCourse_(SearchList<ComputateCourse> searchListComputateCourse_) {
		this.searchListComputateCourse_ = searchListComputateCourse_;
	}
	public static SearchList<ComputateCourse> staticSetSearchListComputateCourse_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ComputateCourseGenPage searchListComputateCourse_Init() {
		Wrap<SearchList<ComputateCourse>> searchListComputateCourse_Wrap = new Wrap<SearchList<ComputateCourse>>().var("searchListComputateCourse_");
		if(searchListComputateCourse_ == null) {
			_searchListComputateCourse_(searchListComputateCourse_Wrap);
			Optional.ofNullable(searchListComputateCourse_Wrap.getO()).ifPresent(o -> {
				setSearchListComputateCourse_(o);
			});
		}
		return (ComputateCourseGenPage)this;
	}

	/////////////////////////
	// listComputateCourse //
	/////////////////////////


	/**	 The entity listComputateCourse
	 *	 It is constructed before being initialized with the constructor by default. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected JsonArray listComputateCourse = new JsonArray();

	/**	<br> The entity listComputateCourse
	 *  It is constructed before being initialized with the constructor by default. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourseGenPage&fq=entiteVar_enUS_indexed_string:listComputateCourse">Find the entity listComputateCourse in Solr</a>
	 * <br>
	 * @param l is the entity already constructed. 
	 **/
	protected abstract void _listComputateCourse(JsonArray l);

	public JsonArray getListComputateCourse() {
		return listComputateCourse;
	}

	public void setListComputateCourse(JsonArray listComputateCourse) {
		this.listComputateCourse = listComputateCourse;
	}
	@JsonIgnore
	public void setListComputateCourse(String o) {
		this.listComputateCourse = ComputateCourseGenPage.staticSetListComputateCourse(siteRequest_, o);
	}
	public static JsonArray staticSetListComputateCourse(SiteRequest siteRequest_, String o) {
		if(o != null) {
				return new JsonArray(o);
		}
		return null;
	}
	protected ComputateCourseGenPage listComputateCourseInit() {
		_listComputateCourse(listComputateCourse);
		return (ComputateCourseGenPage)this;
	}

	public static String staticSearchListComputateCourse(SiteRequest siteRequest_, JsonArray o) {
		return o.toString();
	}

	public static String staticSearchStrListComputateCourse(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqListComputateCourse(SiteRequest siteRequest_, String o) {
		return ComputateCourseGenPage.staticSearchListComputateCourse(siteRequest_, ComputateCourseGenPage.staticSetListComputateCourse(siteRequest_, o)).toString();
	}

	//////////////////////////
	// computateCourseCount //
	//////////////////////////


	/**	 The entity computateCourseCount
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer computateCourseCount;

	/**	<br> The entity computateCourseCount
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourseGenPage&fq=entiteVar_enUS_indexed_string:computateCourseCount">Find the entity computateCourseCount in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _computateCourseCount(Wrap<Integer> w);

	public Integer getComputateCourseCount() {
		return computateCourseCount;
	}

	public void setComputateCourseCount(Integer computateCourseCount) {
		this.computateCourseCount = computateCourseCount;
	}
	@JsonIgnore
	public void setComputateCourseCount(String o) {
		this.computateCourseCount = ComputateCourseGenPage.staticSetComputateCourseCount(siteRequest_, o);
	}
	public static Integer staticSetComputateCourseCount(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected ComputateCourseGenPage computateCourseCountInit() {
		Wrap<Integer> computateCourseCountWrap = new Wrap<Integer>().var("computateCourseCount");
		if(computateCourseCount == null) {
			_computateCourseCount(computateCourseCountWrap);
			Optional.ofNullable(computateCourseCountWrap.getO()).ifPresent(o -> {
				setComputateCourseCount(o);
			});
		}
		return (ComputateCourseGenPage)this;
	}

	public static Integer staticSearchComputateCourseCount(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrComputateCourseCount(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqComputateCourseCount(SiteRequest siteRequest_, String o) {
		return ComputateCourseGenPage.staticSearchComputateCourseCount(siteRequest_, ComputateCourseGenPage.staticSetComputateCourseCount(siteRequest_, o)).toString();
	}

	//////////////////////
	// computateCourse_ //
	//////////////////////


	/**	 The entity computateCourse_
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected ComputateCourse computateCourse_;

	/**	<br> The entity computateCourse_
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourseGenPage&fq=entiteVar_enUS_indexed_string:computateCourse_">Find the entity computateCourse_ in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _computateCourse_(Wrap<ComputateCourse> w);

	public ComputateCourse getComputateCourse_() {
		return computateCourse_;
	}

	public void setComputateCourse_(ComputateCourse computateCourse_) {
		this.computateCourse_ = computateCourse_;
	}
	public static ComputateCourse staticSetComputateCourse_(SiteRequest siteRequest_, String o) {
		return null;
	}
	protected ComputateCourseGenPage computateCourse_Init() {
		Wrap<ComputateCourse> computateCourse_Wrap = new Wrap<ComputateCourse>().var("computateCourse_");
		if(computateCourse_ == null) {
			_computateCourse_(computateCourse_Wrap);
			Optional.ofNullable(computateCourse_Wrap.getO()).ifPresent(o -> {
				setComputateCourse_(o);
			});
		}
		return (ComputateCourseGenPage)this;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourseGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
		this.pk = ComputateCourseGenPage.staticSetPk(siteRequest_, o);
	}
	public static Long staticSetPk(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected ComputateCourseGenPage pkInit() {
		Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
		if(pk == null) {
			_pk(pkWrap);
			Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
				setPk(o);
			});
		}
		return (ComputateCourseGenPage)this;
	}

	public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
		return o;
	}

	public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
		return ComputateCourseGenPage.staticSearchPk(siteRequest_, ComputateCourseGenPage.staticSetPk(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourseGenPage&fq=entiteVar_enUS_indexed_string:id">Find the entity id in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _id(Wrap<String> w);

	public String getId() {
		return id;
	}
	public void setId(String o) {
		this.id = ComputateCourseGenPage.staticSetId(siteRequest_, o);
	}
	public static String staticSetId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateCourseGenPage idInit() {
		Wrap<String> idWrap = new Wrap<String>().var("id");
		if(id == null) {
			_id(idWrap);
			Optional.ofNullable(idWrap.getO()).ifPresent(o -> {
				setId(o);
			});
		}
		return (ComputateCourseGenPage)this;
	}

	public static String staticSearchId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqId(SiteRequest siteRequest_, String o) {
		return ComputateCourseGenPage.staticSearchId(siteRequest_, ComputateCourseGenPage.staticSetId(siteRequest_, o)).toString();
	}

	////////////////////////////
	// pageUriComputateCourse //
	////////////////////////////


	/**	 The entity pageUriComputateCourse
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageUriComputateCourse;

	/**	<br> The entity pageUriComputateCourse
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourseGenPage&fq=entiteVar_enUS_indexed_string:pageUriComputateCourse">Find the entity pageUriComputateCourse in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageUriComputateCourse(Wrap<String> c);

	public String getPageUriComputateCourse() {
		return pageUriComputateCourse;
	}
	public void setPageUriComputateCourse(String o) {
		this.pageUriComputateCourse = ComputateCourseGenPage.staticSetPageUriComputateCourse(siteRequest_, o);
	}
	public static String staticSetPageUriComputateCourse(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateCourseGenPage pageUriComputateCourseInit() {
		Wrap<String> pageUriComputateCourseWrap = new Wrap<String>().var("pageUriComputateCourse");
		if(pageUriComputateCourse == null) {
			_pageUriComputateCourse(pageUriComputateCourseWrap);
			Optional.ofNullable(pageUriComputateCourseWrap.getO()).ifPresent(o -> {
				setPageUriComputateCourse(o);
			});
		}
		return (ComputateCourseGenPage)this;
	}

	public static String staticSearchPageUriComputateCourse(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageUriComputateCourse(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageUriComputateCourse(SiteRequest siteRequest_, String o) {
		return ComputateCourseGenPage.staticSearchPageUriComputateCourse(siteRequest_, ComputateCourseGenPage.staticSetPageUriComputateCourse(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepComputateCourseGenPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepComputateCourseGenPage();
	}

	public Future<Void> promiseDeepComputateCourseGenPage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseComputateCourseGenPage(promise2);
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

	public Future<Void> promiseComputateCourseGenPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				searchListComputateCourse_Init();
				listComputateCourseInit();
				computateCourseCountInit();
				computateCourse_Init();
				pkInit();
				idInit();
				pageUriComputateCourseInit();
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
		return promiseDeepComputateCourseGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestComputateCourseGenPage(SiteRequest siteRequest_) {
			super.siteRequestBaseModelPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestComputateCourseGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainComputateCourseGenPage(v);
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
	public Object obtainComputateCourseGenPage(String var) {
		ComputateCourseGenPage oComputateCourseGenPage = (ComputateCourseGenPage)this;
		switch(var) {
			case "searchListComputateCourse_":
				return oComputateCourseGenPage.searchListComputateCourse_;
			case "listComputateCourse":
				return oComputateCourseGenPage.listComputateCourse;
			case "computateCourseCount":
				return oComputateCourseGenPage.computateCourseCount;
			case "computateCourse_":
				return oComputateCourseGenPage.computateCourse_;
			case "pk":
				return oComputateCourseGenPage.pk;
			case "id":
				return oComputateCourseGenPage.id;
			case "pageUriComputateCourse":
				return oComputateCourseGenPage.pageUriComputateCourse;
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
				o = relateComputateCourseGenPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateComputateCourseGenPage(String var, Object val) {
		ComputateCourseGenPage oComputateCourseGenPage = (ComputateCourseGenPage)this;
		switch(var) {
			default:
				return super.relateBaseModelPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetComputateCourseGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetComputateCourseGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listComputateCourse":
			return ComputateCourseGenPage.staticSetListComputateCourse(siteRequest_, o);
		case "computateCourseCount":
			return ComputateCourseGenPage.staticSetComputateCourseCount(siteRequest_, o);
		case "pk":
			return ComputateCourseGenPage.staticSetPk(siteRequest_, o);
		case "id":
			return ComputateCourseGenPage.staticSetId(siteRequest_, o);
		case "pageUriComputateCourse":
			return ComputateCourseGenPage.staticSetPageUriComputateCourse(siteRequest_, o);
			default:
				return BaseModelPage.staticSetBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchComputateCourseGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchComputateCourseGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listComputateCourse":
			return ComputateCourseGenPage.staticSearchListComputateCourse(siteRequest_, (JsonArray)o);
		case "computateCourseCount":
			return ComputateCourseGenPage.staticSearchComputateCourseCount(siteRequest_, (Integer)o);
		case "pk":
			return ComputateCourseGenPage.staticSearchPk(siteRequest_, (Long)o);
		case "id":
			return ComputateCourseGenPage.staticSearchId(siteRequest_, (String)o);
		case "pageUriComputateCourse":
			return ComputateCourseGenPage.staticSearchPageUriComputateCourse(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrComputateCourseGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrComputateCourseGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "listComputateCourse":
			return ComputateCourseGenPage.staticSearchStrListComputateCourse(siteRequest_, (String)o);
		case "computateCourseCount":
			return ComputateCourseGenPage.staticSearchStrComputateCourseCount(siteRequest_, (Integer)o);
		case "pk":
			return ComputateCourseGenPage.staticSearchStrPk(siteRequest_, (Long)o);
		case "id":
			return ComputateCourseGenPage.staticSearchStrId(siteRequest_, (String)o);
		case "pageUriComputateCourse":
			return ComputateCourseGenPage.staticSearchStrPageUriComputateCourse(siteRequest_, (String)o);
			default:
				return BaseModelPage.staticSearchStrBaseModelPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqComputateCourseGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqComputateCourseGenPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "listComputateCourse":
			return ComputateCourseGenPage.staticSearchFqListComputateCourse(siteRequest_, o);
		case "computateCourseCount":
			return ComputateCourseGenPage.staticSearchFqComputateCourseCount(siteRequest_, o);
		case "pk":
			return ComputateCourseGenPage.staticSearchFqPk(siteRequest_, o);
		case "id":
			return ComputateCourseGenPage.staticSearchFqId(siteRequest_, o);
		case "pageUriComputateCourse":
			return ComputateCourseGenPage.staticSearchFqPageUriComputateCourse(siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "ComputateCourseGenPage";
	public static final String VAR_searchListComputateCourse_ = "searchListComputateCourse_";
	public static final String VAR_listComputateCourse = "listComputateCourse";
	public static final String VAR_computateCourseCount = "computateCourseCount";
	public static final String VAR_computateCourse_ = "computateCourse_";
	public static final String VAR_pk = "pk";
	public static final String VAR_id = "id";
	public static final String VAR_pageUriComputateCourse = "pageUriComputateCourse";

	public static final String DISPLAY_NAME_searchListComputateCourse_ = "";
	public static final String DISPLAY_NAME_listComputateCourse = "";
	public static final String DISPLAY_NAME_computateCourseCount = "";
	public static final String DISPLAY_NAME_computateCourse_ = "";
	public static final String DISPLAY_NAME_pk = "";
	public static final String DISPLAY_NAME_id = "";
	public static final String DISPLAY_NAME_pageUriComputateCourse = "";

	public static String displayNameForClass(String var) {
		return ComputateCourseGenPage.displayNameComputateCourseGenPage(var);
	}
	public static String displayNameComputateCourseGenPage(String var) {
		switch(var) {
		case VAR_searchListComputateCourse_:
			return DISPLAY_NAME_searchListComputateCourse_;
		case VAR_listComputateCourse:
			return DISPLAY_NAME_listComputateCourse;
		case VAR_computateCourseCount:
			return DISPLAY_NAME_computateCourseCount;
		case VAR_computateCourse_:
			return DISPLAY_NAME_computateCourse_;
		case VAR_pk:
			return DISPLAY_NAME_pk;
		case VAR_id:
			return DISPLAY_NAME_id;
		case VAR_pageUriComputateCourse:
			return DISPLAY_NAME_pageUriComputateCourse;
		default:
			return BaseModelPage.displayNameBaseModelPage(var);
		}
	}
}
