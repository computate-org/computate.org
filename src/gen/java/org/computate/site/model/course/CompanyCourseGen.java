package org.computate.site.model.course;

import org.computate.site.request.SiteRequest;
import org.computate.site.result.BaseResult;
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
import java.lang.String;
import java.math.BigDecimal;
import java.lang.Integer;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;
import io.vertx.core.json.JsonObject;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyCourseGen into the class CompanyCourse. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the CompanyCourse API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "Model: true" if you wish to persist these CompanyCourse objects in a relational PostgreSQL database transactionally in the RESTful API. 
 * The code to persist and query the CompanyCourseGen data in the database will then be automatically generated. 
 * </li>
 * <h3>About the CompanyCourse class and it's generated class CompanyCourseGen&lt;BaseResult&gt;: </h3>extends CompanyCourseGen
 * <p>
 * This Java class extends a generated Java class CompanyCourseGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse">Find the class CompanyCourse in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyCourseGen<BaseResult>
 * <p>This <code>class CompanyCourse extends CompanyCourseGen&lt;BaseResult&gt;</code>, which means it extends a newly generated CompanyCourseGen. 
 * The generated <code>class CompanyCourseGen extends BaseResult</code> which means that CompanyCourse extends CompanyCourseGen which extends BaseResult. 
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
 * <p>This class contains a comment <b>"ApiTag: course"</b>, which groups all of the OpenAPIs for CompanyCourse objects under the tag "course". 
 * </p>
 * <h2>ApiUri.enUS: /api/course</h2>
 * <p>This class contains a comment <b>"ApiUri: /api/course"</b>, which defines the base API URI for CompanyCourse objects as "/api/course" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyCourse class will inherit the helpful inherited class comments from the super class CompanyCourseGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 4</h2>
 * <p>This class contains a comment <b>"Order: 4"</b>, which means this class will be sorted by the given number 4 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.course.CompanyCoursePage. 
 * </p>
 * <h2>SuperPage.enUS: BaseResultPage</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: BaseResultPage"</b>, which identifies the Java super class of the page code by it's class simple name "BaseResultPage". 
 * This means that the newly created class org.computate.site.model.course.CompanyCoursePage extends org.computate.site.result.BaseResultPage. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyCourse Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a course</h2>
 * <p>This class contains a comment <b>"AName.enUS: a course"</b>, which identifies the language context to describe a CompanyCourse as "a course". 
 * </p>
 * <p>
 * Delete the class CompanyCourse in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.course in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.course&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyCourseGen<DEV> extends BaseResult {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyCourse.class);

	public static final String CompanyCourse_Description_enUS = "Take an exciting hands-on course on your own computer with computate";
	public static final String CompanyCourse_AName_enUS = "a course";
	public static final String CompanyCourse_This_enUS = "this ";
	public static final String CompanyCourse_ThisName_enUS = "this course";
	public static final String CompanyCourse_A_enUS = "a ";
	public static final String CompanyCourse_TheName_enUS = "the course";
	public static final String CompanyCourse_SingularName_enUS = "course";
	public static final String CompanyCourse_PluralName_enUS = "courses";
	public static final String CompanyCourse_NameActual_enUS = "current course";
	public static final String CompanyCourse_AllName_enUS = "all courses";
	public static final String CompanyCourse_SearchAllNameBy_enUS = "search courses by ";
	public static final String CompanyCourse_Title_enUS = "courses";
	public static final String CompanyCourse_ThePluralName_enUS = "the courses";
	public static final String CompanyCourse_NoNameFound_enUS = "no course found";
	public static final String CompanyCourse_ApiUri_enUS = "/api/course";
	public static final String CompanyCourse_ApiUriSearchPage_enUS = "/course";
	public static final String CompanyCourse_OfName_enUS = "of course";
	public static final String CompanyCourse_ANameAdjective_enUS = "a course";
	public static final String CompanyCourse_NameAdjectiveSingular_enUS = "course";
	public static final String CompanyCourse_NameAdjectivePlural_enUS = "courses";
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

	public static final String CompanyCourse_Icon = "<i class=\"fa-regular fa-notebook\"></i>";

	//////////
	// name //
	//////////


	/**	 The entity name
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String name;

	/**	<br> The entity name
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = CompanyCourse.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchName(siteRequest_, CompanyCourse.staticSetName(siteRequest_, o)).toString();
	}

	public String sqlName() {
		return name;
	}

	/////////////////
	// description //
	/////////////////


	/**	 The entity description
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String description;

	/**	<br> The entity description
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = CompanyCourse.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchDescription(siteRequest_, CompanyCourse.staticSetDescription(siteRequest_, o)).toString();
	}

	public String sqlDescription() {
		return description;
	}

	///////////
	// price //
	///////////


	/**	 The entity price
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal price;

	/**	<br> The entity price
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:price">Find the entity price in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _price(Wrap<BigDecimal> w);

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@JsonIgnore
	public void setPrice(String o) {
		this.price = CompanyCourse.staticSetPrice(siteRequest_, o);
	}
	public static BigDecimal staticSetPrice(SiteRequest siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	@JsonIgnore
	public void setPrice(Double o) {
		setPrice(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setPrice(Integer o) {
		setPrice(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	@JsonIgnore
	public void setPrice(Number o) {
		setPrice(new BigDecimal(o.doubleValue(), MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));
	}
	protected CompanyCourse priceInit() {
		Wrap<BigDecimal> priceWrap = new Wrap<BigDecimal>().var("price");
		if(price == null) {
			_price(priceWrap);
			Optional.ofNullable(priceWrap.getO()).ifPresent(o -> {
				setPrice(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static Double staticSearchPrice(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrPrice(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPrice(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchPrice(siteRequest_, CompanyCourse.staticSetPrice(siteRequest_, o)).toString();
	}

	public BigDecimal sqlPrice() {
		return price;
	}

	////////////
	// pageId //
	////////////


	/**	 The entity pageId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageId;

	/**	<br> The entity pageId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageId(Wrap<String> w);

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String o) {
		this.pageId = CompanyCourse.staticSetPageId(siteRequest_, o);
	}
	public static String staticSetPageId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse pageIdInit() {
		Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
		if(pageId == null) {
			_pageId(pageIdWrap);
			Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
				setPageId(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchPageId(siteRequest_, CompanyCourse.staticSetPageId(siteRequest_, o)).toString();
	}

	public String sqlPageId() {
		return pageId;
	}

	/////////////////
	// resourceUri //
	/////////////////


	/**	 The entity resourceUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String resourceUri;

	/**	<br> The entity resourceUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:resourceUri">Find the entity resourceUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _resourceUri(Wrap<String> w);

	public String getResourceUri() {
		return resourceUri;
	}
	public void setResourceUri(String o) {
		this.resourceUri = CompanyCourse.staticSetResourceUri(siteRequest_, o);
	}
	public static String staticSetResourceUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse resourceUriInit() {
		Wrap<String> resourceUriWrap = new Wrap<String>().var("resourceUri");
		if(resourceUri == null) {
			_resourceUri(resourceUriWrap);
			Optional.ofNullable(resourceUriWrap.getO()).ifPresent(o -> {
				setResourceUri(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchResourceUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrResourceUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResourceUri(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchResourceUri(siteRequest_, CompanyCourse.staticSetResourceUri(siteRequest_, o)).toString();
	}

	public String sqlResourceUri() {
		return resourceUri;
	}

	/////////////////
	// templateUri //
	/////////////////


	/**	 The entity templateUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String templateUri;

	/**	<br> The entity templateUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:templateUri">Find the entity templateUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _templateUri(Wrap<String> w);

	public String getTemplateUri() {
		return templateUri;
	}
	public void setTemplateUri(String o) {
		this.templateUri = CompanyCourse.staticSetTemplateUri(siteRequest_, o);
	}
	public static String staticSetTemplateUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse templateUriInit() {
		Wrap<String> templateUriWrap = new Wrap<String>().var("templateUri");
		if(templateUri == null) {
			_templateUri(templateUriWrap);
			Optional.ofNullable(templateUriWrap.getO()).ifPresent(o -> {
				setTemplateUri(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchTemplateUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTemplateUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTemplateUri(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchTemplateUri(siteRequest_, CompanyCourse.staticSetTemplateUri(siteRequest_, o)).toString();
	}

	public String sqlTemplateUri() {
		return templateUri;
	}

	///////////////////
	// emailTemplate //
	///////////////////


	/**	 The entity emailTemplate
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String emailTemplate;

	/**	<br> The entity emailTemplate
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:emailTemplate">Find the entity emailTemplate in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailTemplate(Wrap<String> w);

	public String getEmailTemplate() {
		return emailTemplate;
	}
	public void setEmailTemplate(String o) {
		this.emailTemplate = CompanyCourse.staticSetEmailTemplate(siteRequest_, o);
	}
	public static String staticSetEmailTemplate(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse emailTemplateInit() {
		Wrap<String> emailTemplateWrap = new Wrap<String>().var("emailTemplate");
		if(emailTemplate == null) {
			_emailTemplate(emailTemplateWrap);
			Optional.ofNullable(emailTemplateWrap.getO()).ifPresent(o -> {
				setEmailTemplate(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchEmailTemplate(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEmailTemplate(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEmailTemplate(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchEmailTemplate(siteRequest_, CompanyCourse.staticSetEmailTemplate(siteRequest_, o)).toString();
	}

	public String sqlEmailTemplate() {
		return emailTemplate;
	}

	/////////
	// uri //
	/////////


	/**	 The entity uri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String uri;

	/**	<br> The entity uri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:uri">Find the entity uri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _uri(Wrap<String> w);

	public String getUri() {
		return uri;
	}
	public void setUri(String o) {
		this.uri = CompanyCourse.staticSetUri(siteRequest_, o);
	}
	public static String staticSetUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse uriInit() {
		Wrap<String> uriWrap = new Wrap<String>().var("uri");
		if(uri == null) {
			_uri(uriWrap);
			Optional.ofNullable(uriWrap.getO()).ifPresent(o -> {
				setUri(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUri(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchUri(siteRequest_, CompanyCourse.staticSetUri(siteRequest_, o)).toString();
	}

	public String sqlUri() {
		return uri;
	}

	/////////
	// url //
	/////////


	/**	 The entity url
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String url;

	/**	<br> The entity url
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:url">Find the entity url in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _url(Wrap<String> w);

	public String getUrl() {
		return url;
	}
	public void setUrl(String o) {
		this.url = CompanyCourse.staticSetUrl(siteRequest_, o);
	}
	public static String staticSetUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse urlInit() {
		Wrap<String> urlWrap = new Wrap<String>().var("url");
		if(url == null) {
			_url(urlWrap);
			Optional.ofNullable(urlWrap.getO()).ifPresent(o -> {
				setUrl(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUrl(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchUrl(siteRequest_, CompanyCourse.staticSetUrl(siteRequest_, o)).toString();
	}

	public String sqlUrl() {
		return url;
	}

	/////////////////
	// downloadUri //
	/////////////////


	/**	 The entity downloadUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String downloadUri;

	/**	<br> The entity downloadUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:downloadUri">Find the entity downloadUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _downloadUri(Wrap<String> w);

	public String getDownloadUri() {
		return downloadUri;
	}
	public void setDownloadUri(String o) {
		this.downloadUri = CompanyCourse.staticSetDownloadUri(siteRequest_, o);
	}
	public static String staticSetDownloadUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse downloadUriInit() {
		Wrap<String> downloadUriWrap = new Wrap<String>().var("downloadUri");
		if(downloadUri == null) {
			_downloadUri(downloadUriWrap);
			Optional.ofNullable(downloadUriWrap.getO()).ifPresent(o -> {
				setDownloadUri(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchDownloadUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDownloadUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDownloadUri(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchDownloadUri(siteRequest_, CompanyCourse.staticSetDownloadUri(siteRequest_, o)).toString();
	}

	public String sqlDownloadUri() {
		return downloadUri;
	}

	/////////////
	// userUri //
	/////////////


	/**	 The entity userUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String userUri;

	/**	<br> The entity userUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:userUri">Find the entity userUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _userUri(Wrap<String> w);

	public String getUserUri() {
		return userUri;
	}
	public void setUserUri(String o) {
		this.userUri = CompanyCourse.staticSetUserUri(siteRequest_, o);
	}
	public static String staticSetUserUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse userUriInit() {
		Wrap<String> userUriWrap = new Wrap<String>().var("userUri");
		if(userUri == null) {
			_userUri(userUriWrap);
			Optional.ofNullable(userUriWrap.getO()).ifPresent(o -> {
				setUserUri(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchUserUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUserUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUserUri(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchUserUri(siteRequest_, CompanyCourse.staticSetUserUri(siteRequest_, o)).toString();
	}

	public String sqlUserUri() {
		return userUri;
	}

	//////////////
	// storeUrl //
	//////////////


	/**	 The entity storeUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String storeUrl;

	/**	<br> The entity storeUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:storeUrl">Find the entity storeUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _storeUrl(Wrap<String> w);

	public String getStoreUrl() {
		return storeUrl;
	}
	public void setStoreUrl(String o) {
		this.storeUrl = CompanyCourse.staticSetStoreUrl(siteRequest_, o);
	}
	public static String staticSetStoreUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse storeUrlInit() {
		Wrap<String> storeUrlWrap = new Wrap<String>().var("storeUrl");
		if(storeUrl == null) {
			_storeUrl(storeUrlWrap);
			Optional.ofNullable(storeUrlWrap.getO()).ifPresent(o -> {
				setStoreUrl(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchStoreUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrStoreUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqStoreUrl(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchStoreUrl(siteRequest_, CompanyCourse.staticSetStoreUrl(siteRequest_, o)).toString();
	}

	public String sqlStoreUrl() {
		return storeUrl;
	}

	///////////
	// title //
	///////////


	/**	 The entity title
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String title;

	/**	<br> The entity title
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:title">Find the entity title in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _title(Wrap<String> w);

	public String getTitle() {
		return title;
	}
	public void setTitle(String o) {
		this.title = CompanyCourse.staticSetTitle(siteRequest_, o);
	}
	public static String staticSetTitle(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyCourse titleInit() {
		Wrap<String> titleWrap = new Wrap<String>().var("title");
		if(title == null) {
			_title(titleWrap);
			Optional.ofNullable(titleWrap.getO()).ifPresent(o -> {
				setTitle(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static String staticSearchTitle(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTitle(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTitle(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchTitle(siteRequest_, CompanyCourse.staticSetTitle(siteRequest_, o)).toString();
	}

	public String sqlTitle() {
		return title;
	}

	///////////////
	// courseNum //
	///////////////


	/**	 The entity courseNum
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer courseNum;

	/**	<br> The entity courseNum
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.course.CompanyCourse&fq=entiteVar_enUS_indexed_string:courseNum">Find the entity courseNum in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _courseNum(Wrap<Integer> w);

	public Integer getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}
	@JsonIgnore
	public void setCourseNum(String o) {
		this.courseNum = CompanyCourse.staticSetCourseNum(siteRequest_, o);
	}
	public static Integer staticSetCourseNum(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyCourse courseNumInit() {
		Wrap<Integer> courseNumWrap = new Wrap<Integer>().var("courseNum");
		if(courseNum == null) {
			_courseNum(courseNumWrap);
			Optional.ofNullable(courseNumWrap.getO()).ifPresent(o -> {
				setCourseNum(o);
			});
		}
		return (CompanyCourse)this;
	}

	public static Integer staticSearchCourseNum(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrCourseNum(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCourseNum(SiteRequest siteRequest_, String o) {
		return CompanyCourse.staticSearchCourseNum(siteRequest_, CompanyCourse.staticSetCourseNum(siteRequest_, o)).toString();
	}

	public Integer sqlCourseNum() {
		return courseNum;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepCompanyCourse(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyCourse();
	}

	public Future<Void> promiseDeepCompanyCourse() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyCourse(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepBaseResult(siteRequest_).onSuccess(b -> {
				promise.complete();
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseCompanyCourse(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				nameInit();
				descriptionInit();
				priceInit();
				pageIdInit();
				resourceUriInit();
				templateUriInit();
				emailTemplateInit();
				uriInit();
				urlInit();
				downloadUriInit();
				userUriInit();
				storeUrlInit();
				titleInit();
				courseNumInit();
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
		return promiseDeepCompanyCourse(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyCourse(SiteRequest siteRequest_) {
			super.siteRequestBaseResult(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyCourse(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyCourse(v);
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
	public Object obtainCompanyCourse(String var) {
		CompanyCourse oCompanyCourse = (CompanyCourse)this;
		switch(var) {
			case "name":
				return oCompanyCourse.name;
			case "description":
				return oCompanyCourse.description;
			case "price":
				return oCompanyCourse.price;
			case "pageId":
				return oCompanyCourse.pageId;
			case "resourceUri":
				return oCompanyCourse.resourceUri;
			case "templateUri":
				return oCompanyCourse.templateUri;
			case "emailTemplate":
				return oCompanyCourse.emailTemplate;
			case "uri":
				return oCompanyCourse.uri;
			case "url":
				return oCompanyCourse.url;
			case "downloadUri":
				return oCompanyCourse.downloadUri;
			case "userUri":
				return oCompanyCourse.userUri;
			case "storeUrl":
				return oCompanyCourse.storeUrl;
			case "title":
				return oCompanyCourse.title;
			case "courseNum":
				return oCompanyCourse.courseNum;
			default:
				return super.obtainBaseResult(var);
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
				o = relateCompanyCourse(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyCourse(String var, Object val) {
		CompanyCourse oCompanyCourse = (CompanyCourse)this;
		switch(var) {
			default:
				return super.relateBaseResult(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyCourse(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyCourse(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return CompanyCourse.staticSetName(siteRequest_, o);
		case "description":
			return CompanyCourse.staticSetDescription(siteRequest_, o);
		case "price":
			return CompanyCourse.staticSetPrice(siteRequest_, o);
		case "pageId":
			return CompanyCourse.staticSetPageId(siteRequest_, o);
		case "resourceUri":
			return CompanyCourse.staticSetResourceUri(siteRequest_, o);
		case "templateUri":
			return CompanyCourse.staticSetTemplateUri(siteRequest_, o);
		case "emailTemplate":
			return CompanyCourse.staticSetEmailTemplate(siteRequest_, o);
		case "uri":
			return CompanyCourse.staticSetUri(siteRequest_, o);
		case "url":
			return CompanyCourse.staticSetUrl(siteRequest_, o);
		case "downloadUri":
			return CompanyCourse.staticSetDownloadUri(siteRequest_, o);
		case "userUri":
			return CompanyCourse.staticSetUserUri(siteRequest_, o);
		case "storeUrl":
			return CompanyCourse.staticSetStoreUrl(siteRequest_, o);
		case "title":
			return CompanyCourse.staticSetTitle(siteRequest_, o);
		case "courseNum":
			return CompanyCourse.staticSetCourseNum(siteRequest_, o);
			default:
				return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyCourse(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyCourse(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return CompanyCourse.staticSearchName(siteRequest_, (String)o);
		case "description":
			return CompanyCourse.staticSearchDescription(siteRequest_, (String)o);
		case "price":
			return CompanyCourse.staticSearchPrice(siteRequest_, (BigDecimal)o);
		case "pageId":
			return CompanyCourse.staticSearchPageId(siteRequest_, (String)o);
		case "resourceUri":
			return CompanyCourse.staticSearchResourceUri(siteRequest_, (String)o);
		case "templateUri":
			return CompanyCourse.staticSearchTemplateUri(siteRequest_, (String)o);
		case "emailTemplate":
			return CompanyCourse.staticSearchEmailTemplate(siteRequest_, (String)o);
		case "uri":
			return CompanyCourse.staticSearchUri(siteRequest_, (String)o);
		case "url":
			return CompanyCourse.staticSearchUrl(siteRequest_, (String)o);
		case "downloadUri":
			return CompanyCourse.staticSearchDownloadUri(siteRequest_, (String)o);
		case "userUri":
			return CompanyCourse.staticSearchUserUri(siteRequest_, (String)o);
		case "storeUrl":
			return CompanyCourse.staticSearchStoreUrl(siteRequest_, (String)o);
		case "title":
			return CompanyCourse.staticSearchTitle(siteRequest_, (String)o);
		case "courseNum":
			return CompanyCourse.staticSearchCourseNum(siteRequest_, (Integer)o);
			default:
				return BaseResult.staticSearchBaseResult(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyCourse(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyCourse(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return CompanyCourse.staticSearchStrName(siteRequest_, (String)o);
		case "description":
			return CompanyCourse.staticSearchStrDescription(siteRequest_, (String)o);
		case "price":
			return CompanyCourse.staticSearchStrPrice(siteRequest_, (Double)o);
		case "pageId":
			return CompanyCourse.staticSearchStrPageId(siteRequest_, (String)o);
		case "resourceUri":
			return CompanyCourse.staticSearchStrResourceUri(siteRequest_, (String)o);
		case "templateUri":
			return CompanyCourse.staticSearchStrTemplateUri(siteRequest_, (String)o);
		case "emailTemplate":
			return CompanyCourse.staticSearchStrEmailTemplate(siteRequest_, (String)o);
		case "uri":
			return CompanyCourse.staticSearchStrUri(siteRequest_, (String)o);
		case "url":
			return CompanyCourse.staticSearchStrUrl(siteRequest_, (String)o);
		case "downloadUri":
			return CompanyCourse.staticSearchStrDownloadUri(siteRequest_, (String)o);
		case "userUri":
			return CompanyCourse.staticSearchStrUserUri(siteRequest_, (String)o);
		case "storeUrl":
			return CompanyCourse.staticSearchStrStoreUrl(siteRequest_, (String)o);
		case "title":
			return CompanyCourse.staticSearchStrTitle(siteRequest_, (String)o);
		case "courseNum":
			return CompanyCourse.staticSearchStrCourseNum(siteRequest_, (Integer)o);
			default:
				return BaseResult.staticSearchStrBaseResult(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyCourse(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyCourse(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return CompanyCourse.staticSearchFqName(siteRequest_, o);
		case "description":
			return CompanyCourse.staticSearchFqDescription(siteRequest_, o);
		case "price":
			return CompanyCourse.staticSearchFqPrice(siteRequest_, o);
		case "pageId":
			return CompanyCourse.staticSearchFqPageId(siteRequest_, o);
		case "resourceUri":
			return CompanyCourse.staticSearchFqResourceUri(siteRequest_, o);
		case "templateUri":
			return CompanyCourse.staticSearchFqTemplateUri(siteRequest_, o);
		case "emailTemplate":
			return CompanyCourse.staticSearchFqEmailTemplate(siteRequest_, o);
		case "uri":
			return CompanyCourse.staticSearchFqUri(siteRequest_, o);
		case "url":
			return CompanyCourse.staticSearchFqUrl(siteRequest_, o);
		case "downloadUri":
			return CompanyCourse.staticSearchFqDownloadUri(siteRequest_, o);
		case "userUri":
			return CompanyCourse.staticSearchFqUserUri(siteRequest_, o);
		case "storeUrl":
			return CompanyCourse.staticSearchFqStoreUrl(siteRequest_, o);
		case "title":
			return CompanyCourse.staticSearchFqTitle(siteRequest_, o);
		case "courseNum":
			return CompanyCourse.staticSearchFqCourseNum(siteRequest_, o);
			default:
				return BaseResult.staticSearchFqBaseResult(entityVar,  siteRequest_, o);
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
					o = persistCompanyCourse(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistCompanyCourse(String var, Object val) {
		String varLower = var.toLowerCase();
			if("name".equals(varLower)) {
				if(val instanceof String) {
					setName((String)val);
				}
				saves.add("name");
				return val;
			} else if("description".equals(varLower)) {
				if(val instanceof String) {
					setDescription((String)val);
				}
				saves.add("description");
				return val;
			} else if("price".equals(varLower)) {
				if(val instanceof String) {
					setPrice((String)val);
				} else if(val instanceof Number) {
					setPrice(new BigDecimal(((Number)val).doubleValue()));
				}
				saves.add("price");
				return val;
			} else if("pageid".equals(varLower)) {
				if(val instanceof String) {
					setPageId((String)val);
				}
				saves.add("pageId");
				return val;
			} else if("resourceuri".equals(varLower)) {
				if(val instanceof String) {
					setResourceUri((String)val);
				}
				saves.add("resourceUri");
				return val;
			} else if("templateuri".equals(varLower)) {
				if(val instanceof String) {
					setTemplateUri((String)val);
				}
				saves.add("templateUri");
				return val;
			} else if("emailtemplate".equals(varLower)) {
				if(val instanceof String) {
					setEmailTemplate((String)val);
				}
				saves.add("emailTemplate");
				return val;
			} else if("uri".equals(varLower)) {
				if(val instanceof String) {
					setUri((String)val);
				}
				saves.add("uri");
				return val;
			} else if("url".equals(varLower)) {
				if(val instanceof String) {
					setUrl((String)val);
				}
				saves.add("url");
				return val;
			} else if("downloaduri".equals(varLower)) {
				if(val instanceof String) {
					setDownloadUri((String)val);
				}
				saves.add("downloadUri");
				return val;
			} else if("useruri".equals(varLower)) {
				if(val instanceof String) {
					setUserUri((String)val);
				}
				saves.add("userUri");
				return val;
			} else if("storeurl".equals(varLower)) {
				if(val instanceof String) {
					setStoreUrl((String)val);
				}
				saves.add("storeUrl");
				return val;
			} else if("title".equals(varLower)) {
				if(val instanceof String) {
					setTitle((String)val);
				}
				saves.add("title");
				return val;
			} else if("coursenum".equals(varLower)) {
				if(val instanceof Integer) {
					setCourseNum((Integer)val);
				} else {
					setCourseNum(val == null ? null : val.toString());
				}
				saves.add("courseNum");
				return val;
		} else {
			return super.persistBaseResult(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateCompanyCourse(doc);
	}
	public void populateCompanyCourse(SolrResponse.Doc doc) {
		CompanyCourse oCompanyCourse = (CompanyCourse)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oCompanyCourse.setName(name);
			}

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oCompanyCourse.setDescription(description);
			}

			if(saves.contains("price")) {
				Double price = (Double)doc.get("price_docvalues_double");
				if(price != null)
					oCompanyCourse.setPrice(price);
			}

			if(saves.contains("pageId")) {
				String pageId = (String)doc.get("pageId_docvalues_string");
				if(pageId != null)
					oCompanyCourse.setPageId(pageId);
			}

			if(saves.contains("resourceUri")) {
				String resourceUri = (String)doc.get("resourceUri_docvalues_string");
				if(resourceUri != null)
					oCompanyCourse.setResourceUri(resourceUri);
			}

			if(saves.contains("templateUri")) {
				String templateUri = (String)doc.get("templateUri_docvalues_string");
				if(templateUri != null)
					oCompanyCourse.setTemplateUri(templateUri);
			}

			if(saves.contains("emailTemplate")) {
				String emailTemplate = (String)doc.get("emailTemplate_docvalues_string");
				if(emailTemplate != null)
					oCompanyCourse.setEmailTemplate(emailTemplate);
			}

			if(saves.contains("uri")) {
				String uri = (String)doc.get("uri_docvalues_string");
				if(uri != null)
					oCompanyCourse.setUri(uri);
			}

			if(saves.contains("url")) {
				String url = (String)doc.get("url_docvalues_string");
				if(url != null)
					oCompanyCourse.setUrl(url);
			}

			if(saves.contains("downloadUri")) {
				String downloadUri = (String)doc.get("downloadUri_docvalues_string");
				if(downloadUri != null)
					oCompanyCourse.setDownloadUri(downloadUri);
			}

			if(saves.contains("userUri")) {
				String userUri = (String)doc.get("userUri_docvalues_string");
				if(userUri != null)
					oCompanyCourse.setUserUri(userUri);
			}

			if(saves.contains("storeUrl")) {
				String storeUrl = (String)doc.get("storeUrl_docvalues_string");
				if(storeUrl != null)
					oCompanyCourse.setStoreUrl(storeUrl);
			}

			if(saves.contains("title")) {
				String title = (String)doc.get("title_docvalues_string");
				if(title != null)
					oCompanyCourse.setTitle(title);
			}

			if(saves.contains("courseNum")) {
				Integer courseNum = (Integer)doc.get("courseNum_docvalues_int");
				if(courseNum != null)
					oCompanyCourse.setCourseNum(courseNum);
			}
		}

		super.populateBaseResult(doc);
	}

	public void indexCompanyCourse(JsonObject doc) {
		if(name != null) {
			doc.put("name_docvalues_string", name);
		}
		if(description != null) {
			doc.put("description_docvalues_string", description);
		}
		if(price != null) {
			doc.put("price_docvalues_double", price.doubleValue());
		}
		if(pageId != null) {
			doc.put("pageId_docvalues_string", pageId);
		}
		if(resourceUri != null) {
			doc.put("resourceUri_docvalues_string", resourceUri);
		}
		if(templateUri != null) {
			doc.put("templateUri_docvalues_string", templateUri);
		}
		if(emailTemplate != null) {
			doc.put("emailTemplate_docvalues_string", emailTemplate);
		}
		if(uri != null) {
			doc.put("uri_docvalues_string", uri);
		}
		if(url != null) {
			doc.put("url_docvalues_string", url);
		}
		if(downloadUri != null) {
			doc.put("downloadUri_docvalues_string", downloadUri);
		}
		if(userUri != null) {
			doc.put("userUri_docvalues_string", userUri);
		}
		if(storeUrl != null) {
			doc.put("storeUrl_docvalues_string", storeUrl);
		}
		if(title != null) {
			doc.put("title_docvalues_string", title);
		}
		if(courseNum != null) {
			doc.put("courseNum_docvalues_int", courseNum);
		}
		super.indexBaseResult(doc);

	}

	public static String varStoredCompanyCourse(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "price":
				return "price_docvalues_double";
			case "pageId":
				return "pageId_docvalues_string";
			case "resourceUri":
				return "resourceUri_docvalues_string";
			case "templateUri":
				return "templateUri_docvalues_string";
			case "emailTemplate":
				return "emailTemplate_docvalues_string";
			case "uri":
				return "uri_docvalues_string";
			case "url":
				return "url_docvalues_string";
			case "downloadUri":
				return "downloadUri_docvalues_string";
			case "userUri":
				return "userUri_docvalues_string";
			case "storeUrl":
				return "storeUrl_docvalues_string";
			case "title":
				return "title_docvalues_string";
			case "courseNum":
				return "courseNum_docvalues_int";
			default:
				return BaseResult.varStoredBaseResult(entityVar);
		}
	}

	public static String varIndexedCompanyCourse(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "price":
				return "price_docvalues_double";
			case "pageId":
				return "pageId_docvalues_string";
			case "resourceUri":
				return "resourceUri_docvalues_string";
			case "templateUri":
				return "templateUri_docvalues_string";
			case "emailTemplate":
				return "emailTemplate_docvalues_string";
			case "uri":
				return "uri_docvalues_string";
			case "url":
				return "url_docvalues_string";
			case "downloadUri":
				return "downloadUri_docvalues_string";
			case "userUri":
				return "userUri_docvalues_string";
			case "storeUrl":
				return "storeUrl_docvalues_string";
			case "title":
				return "title_docvalues_string";
			case "courseNum":
				return "courseNum_docvalues_int";
			default:
				return BaseResult.varIndexedBaseResult(entityVar);
		}
	}

	public static String searchVarCompanyCourse(String searchVar) {
		switch(searchVar) {
			case "name_docvalues_string":
				return "name";
			case "description_docvalues_string":
				return "description";
			case "price_docvalues_double":
				return "price";
			case "pageId_docvalues_string":
				return "pageId";
			case "resourceUri_docvalues_string":
				return "resourceUri";
			case "templateUri_docvalues_string":
				return "templateUri";
			case "emailTemplate_docvalues_string":
				return "emailTemplate";
			case "uri_docvalues_string":
				return "uri";
			case "url_docvalues_string":
				return "url";
			case "downloadUri_docvalues_string":
				return "downloadUri";
			case "userUri_docvalues_string":
				return "userUri";
			case "storeUrl_docvalues_string":
				return "storeUrl";
			case "title_docvalues_string":
				return "title";
			case "courseNum_docvalues_int":
				return "courseNum";
			default:
				return BaseResult.searchVarBaseResult(searchVar);
		}
	}

	public static String varSearchCompanyCourse(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSearchBaseResult(entityVar);
		}
	}

	public static String varSuggestedCompanyCourse(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSuggestedBaseResult(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeCompanyCourse(doc);
	}
	public void storeCompanyCourse(SolrResponse.Doc doc) {
		CompanyCourse oCompanyCourse = (CompanyCourse)this;
		SiteRequest siteRequest = oCompanyCourse.getSiteRequest_();

		oCompanyCourse.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setPrice(Optional.ofNullable(doc.get("price_docvalues_double")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setResourceUri(Optional.ofNullable(doc.get("resourceUri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setTemplateUri(Optional.ofNullable(doc.get("templateUri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setEmailTemplate(Optional.ofNullable(doc.get("emailTemplate_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setUri(Optional.ofNullable(doc.get("uri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setUrl(Optional.ofNullable(doc.get("url_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setDownloadUri(Optional.ofNullable(doc.get("downloadUri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setUserUri(Optional.ofNullable(doc.get("userUri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setStoreUrl(Optional.ofNullable(doc.get("storeUrl_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setTitle(Optional.ofNullable(doc.get("title_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyCourse.setCourseNum(Optional.ofNullable(doc.get("courseNum_docvalues_int")).map(v -> v.toString()).orElse(null));

		super.storeBaseResult(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestCompanyCourse() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof CompanyCourse) {
			CompanyCourse original = (CompanyCourse)o;
			if(!Objects.equals(name, original.getName()))
				apiRequest.addVars("name");
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
			if(!Objects.equals(price, original.getPrice()) && price != null && original.getPrice() != null && price.compareTo(original.getPrice()) != 0)
				apiRequest.addVars("price");
			if(!Objects.equals(pageId, original.getPageId()))
				apiRequest.addVars("pageId");
			if(!Objects.equals(resourceUri, original.getResourceUri()))
				apiRequest.addVars("resourceUri");
			if(!Objects.equals(templateUri, original.getTemplateUri()))
				apiRequest.addVars("templateUri");
			if(!Objects.equals(emailTemplate, original.getEmailTemplate()))
				apiRequest.addVars("emailTemplate");
			if(!Objects.equals(uri, original.getUri()))
				apiRequest.addVars("uri");
			if(!Objects.equals(url, original.getUrl()))
				apiRequest.addVars("url");
			if(!Objects.equals(downloadUri, original.getDownloadUri()))
				apiRequest.addVars("downloadUri");
			if(!Objects.equals(userUri, original.getUserUri()))
				apiRequest.addVars("userUri");
			if(!Objects.equals(storeUrl, original.getStoreUrl()))
				apiRequest.addVars("storeUrl");
			if(!Objects.equals(title, original.getTitle()))
				apiRequest.addVars("title");
			if(!Objects.equals(courseNum, original.getCourseNum()))
				apiRequest.addVars("courseNum");
			super.apiRequestBaseResult();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(name).map(v -> "name: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(price).map(v -> "price: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(resourceUri).map(v -> "resourceUri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(templateUri).map(v -> "templateUri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(emailTemplate).map(v -> "emailTemplate: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(uri).map(v -> "uri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(url).map(v -> "url: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(downloadUri).map(v -> "downloadUri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(userUri).map(v -> "userUri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(storeUrl).map(v -> "storeUrl: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(title).map(v -> "title: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(courseNum).map(v -> "courseNum: " + v + "\n").orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "CompanyCourse";
	public static final String CLASS_API_ADDRESS_CompanyCourse = "computate.org-enUS-CompanyCourse";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_CompanyCourse;
	}
	public static final String VAR_name = "name";
	public static final String VAR_description = "description";
	public static final String VAR_price = "price";
	public static final String VAR_pageId = "pageId";
	public static final String VAR_resourceUri = "resourceUri";
	public static final String VAR_templateUri = "templateUri";
	public static final String VAR_emailTemplate = "emailTemplate";
	public static final String VAR_uri = "uri";
	public static final String VAR_url = "url";
	public static final String VAR_downloadUri = "downloadUri";
	public static final String VAR_userUri = "userUri";
	public static final String VAR_storeUrl = "storeUrl";
	public static final String VAR_title = "title";
	public static final String VAR_courseNum = "courseNum";

	public static List<String> varsQForClass() {
		return CompanyCourse.varsQCompanyCourse(new ArrayList<String>());
	}
	public static List<String> varsQCompanyCourse(List<String> vars) {
		BaseResult.varsQBaseResult(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return CompanyCourse.varsFqCompanyCourse(new ArrayList<String>());
	}
	public static List<String> varsFqCompanyCourse(List<String> vars) {
		vars.add(VAR_name);
		vars.add(VAR_description);
		vars.add(VAR_price);
		vars.add(VAR_pageId);
		vars.add(VAR_resourceUri);
		vars.add(VAR_templateUri);
		vars.add(VAR_emailTemplate);
		vars.add(VAR_uri);
		vars.add(VAR_url);
		vars.add(VAR_downloadUri);
		vars.add(VAR_userUri);
		vars.add(VAR_storeUrl);
		vars.add(VAR_courseNum);
		BaseResult.varsFqBaseResult(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return CompanyCourse.varsRangeCompanyCourse(new ArrayList<String>());
	}
	public static List<String> varsRangeCompanyCourse(List<String> vars) {
		vars.add(VAR_price);
		vars.add(VAR_courseNum);
		BaseResult.varsRangeBaseResult(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_name = "course name";
	public static final String DISPLAY_NAME_description = "course description";
	public static final String DISPLAY_NAME_price = "course description";
	public static final String DISPLAY_NAME_pageId = "Page ID";
	public static final String DISPLAY_NAME_resourceUri = "resource URI";
	public static final String DISPLAY_NAME_templateUri = "template URI";
	public static final String DISPLAY_NAME_emailTemplate = "email template";
	public static final String DISPLAY_NAME_uri = "URI";
	public static final String DISPLAY_NAME_url = "URL";
	public static final String DISPLAY_NAME_downloadUri = "download URI";
	public static final String DISPLAY_NAME_userUri = "user URI";
	public static final String DISPLAY_NAME_storeUrl = "store URL";
	public static final String DISPLAY_NAME_title = "title";
	public static final String DISPLAY_NAME_courseNum = "Course Number";

	public static String displayNameForClass(String var) {
		return CompanyCourse.displayNameCompanyCourse(var);
	}
	public static String displayNameCompanyCourse(String var) {
		switch(var) {
		case VAR_name:
			return DISPLAY_NAME_name;
		case VAR_description:
			return DISPLAY_NAME_description;
		case VAR_price:
			return DISPLAY_NAME_price;
		case VAR_pageId:
			return DISPLAY_NAME_pageId;
		case VAR_resourceUri:
			return DISPLAY_NAME_resourceUri;
		case VAR_templateUri:
			return DISPLAY_NAME_templateUri;
		case VAR_emailTemplate:
			return DISPLAY_NAME_emailTemplate;
		case VAR_uri:
			return DISPLAY_NAME_uri;
		case VAR_url:
			return DISPLAY_NAME_url;
		case VAR_downloadUri:
			return DISPLAY_NAME_downloadUri;
		case VAR_userUri:
			return DISPLAY_NAME_userUri;
		case VAR_storeUrl:
			return DISPLAY_NAME_storeUrl;
		case VAR_title:
			return DISPLAY_NAME_title;
		case VAR_courseNum:
			return DISPLAY_NAME_courseNum;
		default:
			return BaseResult.displayNameBaseResult(var);
		}
	}

	public static String descriptionCompanyCourse(String var) {
		switch(var) {
		case VAR_name:
			return "The course name. ";
		case VAR_description:
			return "The course description. ";
		case VAR_price:
			return "The course description. ";
		case VAR_pageId:
			return "The ID for this page. ";
		case VAR_resourceUri:
			return "The resource relative URI for this page. ";
		case VAR_templateUri:
			return "The template relative URI for this page. ";
		case VAR_emailTemplate:
			return "The HTML email template for this product. ";
		case VAR_uri:
			return "The relative URI for this page. ";
		case VAR_url:
			return "The URL for this page. ";
		case VAR_downloadUri:
			return "The download relative URI for this page. ";
		case VAR_userUri:
			return "The user relative URI for this page. ";
		case VAR_storeUrl:
			return "The store URL for this page. ";
		case VAR_title:
			return "The title of this page. ";
		case VAR_courseNum:
			return "The course number for this page. ";
			default:
				return BaseResult.descriptionBaseResult(var);
		}
	}

	public static String classSimpleNameCompanyCourse(String var) {
		switch(var) {
		case VAR_name:
			return "String";
		case VAR_description:
			return "String";
		case VAR_price:
			return "BigDecimal";
		case VAR_pageId:
			return "String";
		case VAR_resourceUri:
			return "String";
		case VAR_templateUri:
			return "String";
		case VAR_emailTemplate:
			return "String";
		case VAR_uri:
			return "String";
		case VAR_url:
			return "String";
		case VAR_downloadUri:
			return "String";
		case VAR_userUri:
			return "String";
		case VAR_storeUrl:
			return "String";
		case VAR_title:
			return "String";
		case VAR_courseNum:
			return "Integer";
			default:
				return BaseResult.classSimpleNameBaseResult(var);
		}
	}

	public static Integer htmColumnCompanyCourse(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
			default:
				return BaseResult.htmColumnBaseResult(var);
		}
	}

	public static Integer htmRowCompanyCourse(String var) {
		switch(var) {
		case VAR_name:
			return 3;
		case VAR_description:
			return 3;
		case VAR_price:
			return 3;
		case VAR_pageId:
			return 4;
		case VAR_uri:
			return 3;
		case VAR_url:
			return 3;
			default:
				return BaseResult.htmRowBaseResult(var);
		}
	}

	public static Integer htmCellCompanyCourse(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
		case VAR_price:
			return 3;
		case VAR_pageId:
			return 1;
		case VAR_uri:
			return 2;
		case VAR_url:
			return 2;
			default:
				return BaseResult.htmCellBaseResult(var);
		}
	}

	public static Integer lengthMinCompanyCourse(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMinBaseResult(var);
		}
	}

	public static Integer lengthMaxCompanyCourse(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMaxBaseResult(var);
		}
	}

	public static Integer maxCompanyCourse(String var) {
		switch(var) {
			default:
				return BaseResult.maxBaseResult(var);
		}
	}

	public static Integer minCompanyCourse(String var) {
		switch(var) {
			default:
				return BaseResult.minBaseResult(var);
		}
	}
}
