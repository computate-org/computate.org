package org.computate.site.model.course;

import org.computate.site.request.SiteRequest;
import org.computate.site.model.BaseModel;
import io.vertx.core.json.JsonObject;
import java.util.Date;
import java.util.Set;
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
import java.lang.String;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ComputateCourseGen into the class ComputateCourse. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the ComputateCourse API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the ComputateCourse class and it's generated class ComputateCourseGen&lt;BaseModel&gt;: </h3>extends ComputateCourseGen
 * <p>
 * This Java class extends a generated Java class ComputateCourseGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourse">Find the class ComputateCourse in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ComputateCourseGen<BaseModel>
 * <p>This <code>class ComputateCourse extends ComputateCourseGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ComputateCourseGen. 
 * The generated <code>class ComputateCourseGen extends BaseModel</code> which means that ComputateCourse extends ComputateCourseGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiMethode: Search</h2>
 * <p>This class contains a comment <b>"ApiMethod: Search"</b>, which creates an API "Search". 
 * </p>
 * <h2>ApiMethode: GET</h2>
 * <p>This class contains a comment <b>"ApiMethod: GET"</b>, which creates an API "GET". 
 * </p>
 * <h2>ApiMethode: PATCH</h2>
 * <p>This class contains a comment <b>"ApiMethod: PATCH"</b>, which creates an API "PATCH". 
 * </p>
 * <h2>ApiMethode: POST</h2>
 * <p>This class contains a comment <b>"ApiMethod: POST"</b>, which creates an API "POST". 
 * </p>
 * <h2>ApiMethode: PUTImport</h2>
 * <p>This class contains a comment <b>"ApiMethod: PUTImport"</b>, which creates an API "PUTImport". 
 * </p>
 * <h2>ApiMethode: SearchPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: SearchPage"</b>, which creates an API "SearchPage". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: course"</b>, which groups all of the OpenAPIs for ComputateCourse objects under the tag "course". 
 * </p>
 * <h2>ApiUri.enUS: /api/course</h2>
 * <p>This class contains a comment <b>"ApiUri: /api/course"</b>, which defines the base API URI for ComputateCourse objects as "/api/course" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>IconGroup: regular</h2>
 * <p>This class contains a comment <b>"IconGroup: regular"</b>, which adds icons on the ComputateCourse page with a group of "regular". 
 * This will reference a Font Awesome icon group that starts with "fa-" followed by the icon group "regular", together is "fa-regular". 
 * A Font Awesome icon group of "regular" is expected to exist. 
 * The Font Awesome groups currently supported include: solid, regular, light, thin, duotone, sharp, and kit. 
 * See: <a href="https://fontawesome.com/docs/web/dig-deeper/styles">https://fontawesome.com/docs/web/dig-deeper/styles</a>. 
 * </p>
 * <h2>IconName: notebook</h2>
 * <p>This class contains a comment <b>"IconName: notebook"</b>, which adds icons on the ComputateCourse page with a name of "notebook". 
 * This will reference a Font Awesome icon that starts with the icon group "fa-regular fa-" followed by the icon name, which is "fa-regular fa-notebook". 
 * A Font Awesome icon of "fa-regular fa-notebook" is expected to exist. 
 * See: <a href="https://fontawesome.com/icons">https://fontawesome.com/icons</a>. 
 * </p>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ComputateCourse class will inherit the helpful inherited class comments from the super class ComputateCourseGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 3</h2>
 * <p>This class contains a comment <b>"Order: 3"</b>, which means this class will be sorted by the given number 3 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 3</h2>
 * <p>This class contains a comment <b>"SqlOrder: 3"</b>, which means this class will be sorted by the given number 3 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.course.ComputateCoursePage. 
 * </p>
 * <h2>SuperPage.enUS: BaseModelPage</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: BaseModelPage"</b>, which identifies the Java super class of the page code by it's class simple name "BaseModelPage". 
 * This means that the newly created class org.computate.site.model.course.ComputateCoursePage extends org.computate.site.model.BaseModelPage. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ComputateCourse Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>Role.enUS: SiteAdmin</h2>
 * <p>
 * This class contains a comment <b>"Role.enUS: SiteAdmin"</b>, which identifies the default role name "SiteAdmin" of the OAuth2/OpenID Connect user role required to access this ComputateCourse API. 
 * It's possible to reconfigure the roles required to access the ComputateCourse API by configuring an environment variable like this: 
 * </p>
 * <pre>AUTH_ROLE_REQUIRED_ComputateCourse: ["SiteAdmin"]</pre>
 * <h2>AName.enUS: a course</h2>
 * <p>This class contains a comment <b>"AName.enUS: a course"</b>, which identifies the language context to describe a ComputateCourse as "a course". 
 * </p>
 * <p>
 * Delete the class ComputateCourse in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourse&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
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
public abstract class ComputateCourseGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(ComputateCourse.class);

	public static final String ComputateCourse_Description_enUS = "Take an exciting hands-on course on your own computer with computate";
	public static final String ComputateCourse_AName_enUS = "a course";
	public static final String ComputateCourse_This_enUS = "this ";
	public static final String ComputateCourse_ThisName_enUS = "this course";
	public static final String ComputateCourse_A_enUS = "a ";
	public static final String ComputateCourse_TheName_enUS = "the course";
	public static final String ComputateCourse_SingularName_enUS = "course";
	public static final String ComputateCourse_PluralName_enUS = "courses";
	public static final String ComputateCourse_NameActual_enUS = "current course";
	public static final String ComputateCourse_AllName_enUS = "all the courses";
	public static final String ComputateCourse_SearchAllNameBy_enUS = "search courses by ";
	public static final String ComputateCourse_Title_enUS = "courses";
	public static final String ComputateCourse_ThePluralName_enUS = "the courses";
	public static final String ComputateCourse_NoNameFound_enUS = "no course found";
	public static final String ComputateCourse_ApiUri_enUS = "/api/course";
	public static final String ComputateCourse_ApiUriSearchPage_enUS = "/course";
	public static final String ComputateCourse_OfName_enUS = "of course";
	public static final String ComputateCourse_ANameAdjective_enUS = "a course";
	public static final String ComputateCourse_NameAdjectiveSingular_enUS = "course";
	public static final String ComputateCourse_NameAdjectivePlural_enUS = "courses";
	public static final String Search_enUS_Uri = "/api/course";
	public static final String Search_enUS_ImageUri = "/png/api/course-999.png";
	public static final String GET_enUS_Uri = "/api/course/{id}";
	public static final String GET_enUS_ImageUri = "/png/api/course/{id}-999.png";
	public static final String PATCH_enUS_Uri = "/api/course";
	public static final String PATCH_enUS_ImageUri = "/png/api/course-999.png";
	public static final String POST_enUS_Uri = "/api/course";
	public static final String POST_enUS_ImageUri = "/png/api/course-999.png";
	public static final String PUTImport_enUS_Uri = "/api/course-import";
	public static final String PUTImport_enUS_ImageUri = "/png/api/course-import-999.png";
	public static final String SearchPage_enUS_Uri = "/course";
	public static final String SearchPage_enUS_ImageUri = "/png/course-999.png";

	public static final String ComputateCourse_IconGroup = "regular";
	public static final String ComputateCourse_IconName = "notebook";

	////////////////
	// courseName //
	////////////////


	/**	 The entity courseName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String courseName;

	/**	<br> The entity courseName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourse&fq=entiteVar_enUS_indexed_string:courseName">Find the entity courseName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _courseName(Wrap<String> w);

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String o) {
		this.courseName = ComputateCourse.staticSetCourseName(siteRequest_, o);
	}
	public static String staticSetCourseName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateCourse courseNameInit() {
		Wrap<String> courseNameWrap = new Wrap<String>().var("courseName");
		if(courseName == null) {
			_courseName(courseNameWrap);
			Optional.ofNullable(courseNameWrap.getO()).ifPresent(o -> {
				setCourseName(o);
			});
		}
		return (ComputateCourse)this;
	}

	public static String staticSearchCourseName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrCourseName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCourseName(SiteRequest siteRequest_, String o) {
		return ComputateCourse.staticSearchCourseName(siteRequest_, ComputateCourse.staticSetCourseName(siteRequest_, o)).toString();
	}

	public String sqlCourseName() {
		return courseName;
	}

	///////////////////////
	// courseDescription //
	///////////////////////


	/**	 The entity courseDescription
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String courseDescription;

	/**	<br> The entity courseDescription
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.ComputateCourse&fq=entiteVar_enUS_indexed_string:courseDescription">Find the entity courseDescription in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _courseDescription(Wrap<String> w);

	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String o) {
		this.courseDescription = ComputateCourse.staticSetCourseDescription(siteRequest_, o);
	}
	public static String staticSetCourseDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateCourse courseDescriptionInit() {
		Wrap<String> courseDescriptionWrap = new Wrap<String>().var("courseDescription");
		if(courseDescription == null) {
			_courseDescription(courseDescriptionWrap);
			Optional.ofNullable(courseDescriptionWrap.getO()).ifPresent(o -> {
				setCourseDescription(o);
			});
		}
		return (ComputateCourse)this;
	}

	public static String staticSearchCourseDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrCourseDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCourseDescription(SiteRequest siteRequest_, String o) {
		return ComputateCourse.staticSearchCourseDescription(siteRequest_, ComputateCourse.staticSetCourseDescription(siteRequest_, o)).toString();
	}

	public String sqlCourseDescription() {
		return courseDescription;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepComputateCourse(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepComputateCourse();
	}

	public Future<Void> promiseDeepComputateCourse() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseComputateCourse(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseModel(siteRequest_).onSuccess(b -> {
				promise.complete();
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseComputateCourse(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				courseNameInit();
				courseDescriptionInit();
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
		return promiseDeepComputateCourse(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestComputateCourse(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestComputateCourse(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainComputateCourse(v);
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
	public Object obtainComputateCourse(String var) {
		ComputateCourse oComputateCourse = (ComputateCourse)this;
		switch(var) {
			case "courseName":
				return oComputateCourse.courseName;
			case "courseDescription":
				return oComputateCourse.courseDescription;
			default:
				return super.obtainBaseModel(var);
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
				o = relateComputateCourse(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateComputateCourse(String var, Object val) {
		ComputateCourse oComputateCourse = (ComputateCourse)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetComputateCourse(entityVar,  siteRequest_, o);
	}
	public static Object staticSetComputateCourse(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "courseName":
			return ComputateCourse.staticSetCourseName(siteRequest_, o);
		case "courseDescription":
			return ComputateCourse.staticSetCourseDescription(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchComputateCourse(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchComputateCourse(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "courseName":
			return ComputateCourse.staticSearchCourseName(siteRequest_, (String)o);
		case "courseDescription":
			return ComputateCourse.staticSearchCourseDescription(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrComputateCourse(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrComputateCourse(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "courseName":
			return ComputateCourse.staticSearchStrCourseName(siteRequest_, (String)o);
		case "courseDescription":
			return ComputateCourse.staticSearchStrCourseDescription(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqComputateCourse(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqComputateCourse(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "courseName":
			return ComputateCourse.staticSearchFqCourseName(siteRequest_, o);
		case "courseDescription":
			return ComputateCourse.staticSearchFqCourseDescription(siteRequest_, o);
			default:
				return BaseModel.staticSearchFqBaseModel(entityVar,  siteRequest_, o);
		}
	}

	/////////////
	// persist //
	/////////////

	@Override public boolean persistForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = persistComputateCourse(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistComputateCourse(String var, Object val) {
		String varLower = var.toLowerCase();
			if("coursename".equals(varLower)) {
				if(val instanceof String) {
					setCourseName((String)val);
				}
				saves.add("courseName");
				return val;
			} else if("coursedescription".equals(varLower)) {
				if(val instanceof String) {
					setCourseDescription((String)val);
				}
				saves.add("courseDescription");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateComputateCourse(doc);
	}
	public void populateComputateCourse(SolrResponse.Doc doc) {
		ComputateCourse oComputateCourse = (ComputateCourse)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("courseName")) {
				String courseName = (String)doc.get("courseName_docvalues_string");
				if(courseName != null)
					oComputateCourse.setCourseName(courseName);
			}

			if(saves.contains("courseDescription")) {
				String courseDescription = (String)doc.get("courseDescription_docvalues_string");
				if(courseDescription != null)
					oComputateCourse.setCourseDescription(courseDescription);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexComputateCourse(JsonObject doc) {
		if(courseName != null) {
			doc.put("courseName_docvalues_string", courseName);
		}
		if(courseDescription != null) {
			doc.put("courseDescription_docvalues_string", courseDescription);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredComputateCourse(String entityVar) {
		switch(entityVar) {
			case "courseName":
				return "courseName_docvalues_string";
			case "courseDescription":
				return "courseDescription_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedComputateCourse(String entityVar) {
		switch(entityVar) {
			case "courseName":
				return "courseName_docvalues_string";
			case "courseDescription":
				return "courseDescription_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarComputateCourse(String searchVar) {
		switch(searchVar) {
			case "courseName_docvalues_string":
				return "courseName";
			case "courseDescription_docvalues_string":
				return "courseDescription";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchComputateCourse(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedComputateCourse(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeComputateCourse(doc);
	}
	public void storeComputateCourse(SolrResponse.Doc doc) {
		ComputateCourse oComputateCourse = (ComputateCourse)this;
		SiteRequest siteRequest = oComputateCourse.getSiteRequest_();

		oComputateCourse.setCourseName(Optional.ofNullable(doc.get("courseName_docvalues_string")).map(v -> v.toString()).orElse(null));
		oComputateCourse.setCourseDescription(Optional.ofNullable(doc.get("courseDescription_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestComputateCourse() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ComputateCourse) {
			ComputateCourse original = (ComputateCourse)o;
			if(!Objects.equals(courseName, original.getCourseName()))
				apiRequest.addVars("courseName");
			if(!Objects.equals(courseDescription, original.getCourseDescription()))
				apiRequest.addVars("courseDescription");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(courseName).map(v -> "courseName: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(courseDescription).map(v -> "courseDescription: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "ComputateCourse";
	public static final String CLASS_API_ADDRESS_ComputateCourse = "computate.org-enUS-ComputateCourse";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_ComputateCourse;
	}
	public static final String VAR_courseName = "courseName";
	public static final String VAR_courseDescription = "courseDescription";

	public static List<String> varsQForClass() {
		return ComputateCourse.varsQComputateCourse(new ArrayList<String>());
	}
	public static List<String> varsQComputateCourse(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return ComputateCourse.varsFqComputateCourse(new ArrayList<String>());
	}
	public static List<String> varsFqComputateCourse(List<String> vars) {
		vars.add(VAR_courseName);
		vars.add(VAR_courseDescription);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return ComputateCourse.varsRangeComputateCourse(new ArrayList<String>());
	}
	public static List<String> varsRangeComputateCourse(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_courseName = "course name";
	public static final String DISPLAY_NAME_courseDescription = "course description";

	public static String displayNameForClass(String var) {
		return ComputateCourse.displayNameComputateCourse(var);
	}
	public static String displayNameComputateCourse(String var) {
		switch(var) {
		case VAR_courseName:
			return DISPLAY_NAME_courseName;
		case VAR_courseDescription:
			return DISPLAY_NAME_courseDescription;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionComputateCourse(String var) {
		switch(var) {
		case VAR_courseName:
			return "The course name. ";
		case VAR_courseDescription:
			return "The course description. ";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameComputateCourse(String var) {
		switch(var) {
		case VAR_courseName:
			return "String";
		case VAR_courseDescription:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnComputateCourse(String var) {
		switch(var) {
		case VAR_courseName:
			return 1;
		case VAR_courseDescription:
			return 2;
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowComputateCourse(String var) {
		switch(var) {
		case VAR_courseName:
			return 3;
		case VAR_courseDescription:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellComputateCourse(String var) {
		switch(var) {
		case VAR_courseName:
			return 1;
		case VAR_courseDescription:
			return 2;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinComputateCourse(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxComputateCourse(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxComputateCourse(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minComputateCourse(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
