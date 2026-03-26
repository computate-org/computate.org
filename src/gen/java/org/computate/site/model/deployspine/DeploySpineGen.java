package org.computate.site.model.deployspine;

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
import com.fasterxml.jackson.databind.JsonDeserializer;
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
import java.lang.Integer;
import org.computate.vertx.search.list.SearchList;
import org.computate.site.page.SitePage;
import io.vertx.core.json.JsonArray;
import org.computate.vertx.serialize.vertx.JsonArrayDeserializer;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.computate.search.tool.SearchTool;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;
import io.vertx.core.json.JsonObject;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class DeploySpineGen into the class DeploySpine. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the DeploySpine API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "Model: true" if you wish to persist these DeploySpine objects in a relational PostgreSQL database transactionally in the RESTful API. 
 * The code to persist and query the DeploySpineGen data in the database will then be automatically generated. 
 * </li>
 * <h3>About the DeploySpine class and it's generated class DeploySpineGen&lt;BaseResult&gt;: </h3>extends DeploySpineGen
 * <p>
 * This Java class extends a generated Java class DeploySpineGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine">Find the class DeploySpine in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends DeploySpineGen<BaseResult>
 * <p>This <code>class DeploySpine extends DeploySpineGen&lt;BaseResult&gt;</code>, which means it extends a newly generated DeploySpineGen. 
 * The generated <code>class DeploySpineGen extends BaseResult</code> which means that DeploySpine extends DeploySpineGen which extends BaseResult. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: Deploy SPINE apps to OpenShift Local"</b>, which groups all of the OpenAPIs for DeploySpine objects under the tag "Deploy SPINE apps to OpenShift Local". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/deploy-spine</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/deploy-spine"</b>, which defines the base API URI for DeploySpine objects as "/en-us/api/deploy-spine" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the DeploySpine class will inherit the helpful inherited class comments from the super class DeploySpineGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 3</h2>
 * <p>This class contains a comment <b>"Order: 3"</b>, which means this class will be sorted by the given number 3 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 3</h2>
 * <p>This class contains a comment <b>"SqlOrder: 3"</b>, which means this class will be sorted by the given number 3 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.deployspine.DeploySpinePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.site.model.deployspine.DeploySpinePage extends org.computate.site.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the DeploySpine Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a Deploy SPINE apps to OpenShift Local</h2>
 * <p>This class contains a comment <b>"AName.enUS: a Deploy SPINE apps to OpenShift Local"</b>, which identifies the language context to describe a DeploySpine as "a Deploy SPINE apps to OpenShift Local". 
 * </p>
 * <p>
 * Delete the class DeploySpine in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.deployspine in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.deployspine&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * Generated: true
 **/
public abstract class DeploySpineGen<DEV> extends BaseResult {
  protected static final Logger LOG = LoggerFactory.getLogger(DeploySpine.class);

  public static final String Description_frFR = "Learn how to deploy SPINE apps to OpenShift Local to run community driven open source cloud applications built with SPINE Programming Theory in OpenShift Local on your own computer. ";
  public static final String AName_frFR = "a Deploy SPINE apps to OpenShift Local";
  public static final String SingularName_frFR = "Deploy SPINE apps to OpenShift Local";
  public static final String PluralName_frFR = "Deploy SPINE apps to OpenShift Local";
  public static final String Title_frFR = "Deploy SPINE apps to OpenShift Local";
  public static final String ThePluralName_frFR = "les Deploy SPINE apps to OpenShift Local";
  public static final String NameAdjectiveSingular_frFR = "Deploy SPINE apps to OpenShift Local";
  public static final String NameAdjectivePlural_frFR = "Deploy SPINE apps to OpenShift Local";

  public static final String Description_enUS = "Learn how to deploy SPINE apps to OpenShift Local to run community driven open source cloud applications built with SPINE Programming Theory in OpenShift Local on your own computer. ";
  public static final String AName_enUS = "a Deploy SPINE apps to OpenShift Local";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this Deploy SPINE apps to OpenShift Local";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the Deploy SPINE apps to OpenShift Local";
  public static final String SingularName_enUS = "Deploy SPINE apps to OpenShift Local";
  public static final String PluralName_enUS = "Deploy SPINE apps to OpenShift Local";
  public static final String NameActual_enUS = "current Deploy SPINE apps to OpenShift Local";
  public static final String AllName_enUS = "all Deploy SPINE apps to OpenShift Local";
  public static final String SearchAllNameBy_enUS = "search Deploy SPINE apps to OpenShift Local by ";
  public static final String SearchAllName_enUS = "search Deploy SPINE apps to OpenShift Local";
  public static final String Title_enUS = "Deploy SPINE apps to OpenShift Local";
  public static final String ThePluralName_enUS = "the Deploy SPINE apps to OpenShift Local";
  public static final String NoNameFound_enUS = "no Deploy SPINE apps to OpenShift Local found";
  public static final String ApiUri_enUS = "/en-us/api/deploy-spine";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/deploy-spine";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/deploy-spine/{pageId}";
  public static final String OfName_enUS = "of Deploy SPINE apps to OpenShift Local";
  public static final String ANameAdjective_enUS = "a Deploy SPINE apps to OpenShift Local";
  public static final String NameAdjectiveSingular_enUS = "Deploy SPINE apps to OpenShift Local";
  public static final String NameAdjectivePlural_enUS = "Deploy SPINE apps to OpenShift Local";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/deploy-spine";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/deploy-spine";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/deploy-spine";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/deploy-spine/{pageId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/deploy-spine/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/deploy-spine/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/deploy-spine";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/deploy-spine";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/deploy-spine";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/deploy-spine";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/deploy-spine";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/deploy-spine";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/deploy-spine/{pageId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/deploy-spine/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/deploy-spine/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/deploy-spine-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/deploy-spine-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/deploy-spine-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/deploy-spine";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/deploy-spine";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/deploy-spine";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/deploy-spine/{pageId}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/deploy-spine/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/deploy-spine/%s";
  public static final String DisplayPage_enUS_OpenApiUri = "/en-us/learn/deploy-spine/{pageId}";
  public static final String DisplayPage_enUS_StringFormatUri = "/en-us/learn/deploy-spine/%s";
  public static final String DisplayPage_enUS_StringFormatUrl = "%s/en-us/learn/deploy-spine/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/deploy-spine";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/deploy-spine";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/deploy-spine";

  public static final String Icon = "<i class=\"fa-kit fa-openshift\"></i>";

	//////////
  // name //
	//////////


  /**
   *  The entity name
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String name;

  /**
   * <br> The entity name
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _name(Wrap<String> w);

  public String getName() {
    return name;
  }
  public void setName(String o) {
    this.name = DeploySpine.staticSetName(siteRequest_, o);
  }
  public static String staticSetName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected DeploySpine nameInit() {
    Wrap<String> nameWrap = new Wrap<String>().var("name");
    if(name == null) {
      _name(nameWrap);
      Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
        setName(o);
      });
    }
    return (DeploySpine)this;
  }

  public static String staticSearchName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
    return DeploySpine.staticSearchName(siteRequest_, DeploySpine.staticSetName(siteRequest_, o)).toString();
  }

  public String sqlName() {
    return name;
  }

  public static String staticJsonName(String name) {
    return name;
  }

	/////////////////
  // description //
	/////////////////


  /**
   *  The entity description
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String description;

  /**
   * <br> The entity description
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _description(Wrap<String> w);

  public String getDescription() {
    return description;
  }
  public void setDescription(String o) {
    this.description = DeploySpine.staticSetDescription(siteRequest_, o);
  }
  public static String staticSetDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected DeploySpine descriptionInit() {
    Wrap<String> descriptionWrap = new Wrap<String>().var("description");
    if(description == null) {
      _description(descriptionWrap);
      Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
        setDescription(o);
      });
    }
    return (DeploySpine)this;
  }

  public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
    return DeploySpine.staticSearchDescription(siteRequest_, DeploySpine.staticSetDescription(siteRequest_, o)).toString();
  }

  public String sqlDescription() {
    return description;
  }

  public static String staticJsonDescription(String description) {
    return description;
  }

	////////////
  // pageId //
	////////////


  /**
   *  The entity pageId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageId;

  /**
   * <br> The entity pageId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageId(Wrap<String> w);

  public String getPageId() {
    return pageId;
  }
  public void setPageId(String o) {
    this.pageId = DeploySpine.staticSetPageId(siteRequest_, o);
  }
  public static String staticSetPageId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected DeploySpine pageIdInit() {
    Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
    if(pageId == null) {
      _pageId(pageIdWrap);
      Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
        setPageId(o);
      });
    }
    return (DeploySpine)this;
  }

  public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
    return DeploySpine.staticSearchPageId(siteRequest_, DeploySpine.staticSetPageId(siteRequest_, o)).toString();
  }

  public String sqlPageId() {
    return pageId;
  }

  public static String staticJsonPageId(String pageId) {
    return pageId;
  }

	///////////////
  // courseNum //
	///////////////


  /**
   *  The entity courseNum
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer courseNum;

  /**
   * <br> The entity courseNum
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:courseNum">Find the entity courseNum in Solr</a>
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
    this.courseNum = DeploySpine.staticSetCourseNum(siteRequest_, o);
  }
  public static Integer staticSetCourseNum(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected DeploySpine courseNumInit() {
    Wrap<Integer> courseNumWrap = new Wrap<Integer>().var("courseNum");
    if(courseNum == null) {
      _courseNum(courseNumWrap);
      Optional.ofNullable(courseNumWrap.getO()).ifPresent(o -> {
        setCourseNum(o);
      });
    }
    return (DeploySpine)this;
  }

  public static Integer staticSearchCourseNum(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrCourseNum(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCourseNum(SiteRequest siteRequest_, String o) {
    return DeploySpine.staticSearchCourseNum(siteRequest_, DeploySpine.staticSetCourseNum(siteRequest_, o)).toString();
  }

  public Integer sqlCourseNum() {
    return courseNum;
  }

  public static String staticJsonCourseNum(Integer courseNum) {
    return Optional.ofNullable(courseNum).map(v -> v.toString()).orElse(null);
  }

	//////////////////
  // pageImageUri //
	//////////////////


  /**
   *  The entity pageImageUri
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageImageUri;

  /**
   * <br> The entity pageImageUri
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:pageImageUri">Find the entity pageImageUri in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageUri(Wrap<String> w);

  public String getPageImageUri() {
    return pageImageUri;
  }
  public void setPageImageUri(String o) {
    this.pageImageUri = DeploySpine.staticSetPageImageUri(siteRequest_, o);
  }
  public static String staticSetPageImageUri(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected DeploySpine pageImageUriInit() {
    Wrap<String> pageImageUriWrap = new Wrap<String>().var("pageImageUri");
    if(pageImageUri == null) {
      _pageImageUri(pageImageUriWrap);
      Optional.ofNullable(pageImageUriWrap.getO()).ifPresent(o -> {
        setPageImageUri(o);
      });
    }
    return (DeploySpine)this;
  }

  public static String staticSearchPageImageUri(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageImageUri(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageUri(SiteRequest siteRequest_, String o) {
    return DeploySpine.staticSearchPageImageUri(siteRequest_, DeploySpine.staticSetPageImageUri(siteRequest_, o)).toString();
  }

  public String sqlPageImageUri() {
    return pageImageUri;
  }

  public static String staticJsonPageImageUri(String pageImageUri) {
    return pageImageUri;
  }

	////////////////////
  // pageImageWidth //
	////////////////////


  /**
   *  The entity pageImageWidth
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer pageImageWidth;

  /**
   * <br> The entity pageImageWidth
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:pageImageWidth">Find the entity pageImageWidth in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageWidth(Wrap<Integer> w);

  public Integer getPageImageWidth() {
    return pageImageWidth;
  }

  public void setPageImageWidth(Integer pageImageWidth) {
    this.pageImageWidth = pageImageWidth;
  }
  @JsonIgnore
  public void setPageImageWidth(String o) {
    this.pageImageWidth = DeploySpine.staticSetPageImageWidth(siteRequest_, o);
  }
  public static Integer staticSetPageImageWidth(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected DeploySpine pageImageWidthInit() {
    Wrap<Integer> pageImageWidthWrap = new Wrap<Integer>().var("pageImageWidth");
    if(pageImageWidth == null) {
      _pageImageWidth(pageImageWidthWrap);
      Optional.ofNullable(pageImageWidthWrap.getO()).ifPresent(o -> {
        setPageImageWidth(o);
      });
    }
    return (DeploySpine)this;
  }

  public static Integer staticSearchPageImageWidth(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrPageImageWidth(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageWidth(SiteRequest siteRequest_, String o) {
    return DeploySpine.staticSearchPageImageWidth(siteRequest_, DeploySpine.staticSetPageImageWidth(siteRequest_, o)).toString();
  }

	/////////////////////
  // pageImageHeight //
	/////////////////////


  /**
   *  The entity pageImageHeight
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer pageImageHeight;

  /**
   * <br> The entity pageImageHeight
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:pageImageHeight">Find the entity pageImageHeight in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageHeight(Wrap<Integer> c);

  public Integer getPageImageHeight() {
    return pageImageHeight;
  }

  public void setPageImageHeight(Integer pageImageHeight) {
    this.pageImageHeight = pageImageHeight;
  }
  @JsonIgnore
  public void setPageImageHeight(String o) {
    this.pageImageHeight = DeploySpine.staticSetPageImageHeight(siteRequest_, o);
  }
  public static Integer staticSetPageImageHeight(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected DeploySpine pageImageHeightInit() {
    Wrap<Integer> pageImageHeightWrap = new Wrap<Integer>().var("pageImageHeight");
    if(pageImageHeight == null) {
      _pageImageHeight(pageImageHeightWrap);
      Optional.ofNullable(pageImageHeightWrap.getO()).ifPresent(o -> {
        setPageImageHeight(o);
      });
    }
    return (DeploySpine)this;
  }

  public static Integer staticSearchPageImageHeight(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrPageImageHeight(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageHeight(SiteRequest siteRequest_, String o) {
    return DeploySpine.staticSearchPageImageHeight(siteRequest_, DeploySpine.staticSetPageImageHeight(siteRequest_, o)).toString();
  }

	///////////////////
  // pageImageType //
	///////////////////


  /**
   *  The entity pageImageType
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageImageType;

  /**
   * <br> The entity pageImageType
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:pageImageType">Find the entity pageImageType in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageType(Wrap<String> c);

  public String getPageImageType() {
    return pageImageType;
  }
  public void setPageImageType(String o) {
    this.pageImageType = DeploySpine.staticSetPageImageType(siteRequest_, o);
  }
  public static String staticSetPageImageType(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected DeploySpine pageImageTypeInit() {
    Wrap<String> pageImageTypeWrap = new Wrap<String>().var("pageImageType");
    if(pageImageType == null) {
      _pageImageType(pageImageTypeWrap);
      Optional.ofNullable(pageImageTypeWrap.getO()).ifPresent(o -> {
        setPageImageType(o);
      });
    }
    return (DeploySpine)this;
  }

  public static String staticSearchPageImageType(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageImageType(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageType(SiteRequest siteRequest_, String o) {
    return DeploySpine.staticSearchPageImageType(siteRequest_, DeploySpine.staticSetPageImageType(siteRequest_, o)).toString();
  }

	//////////////////
  // pageImageAlt //
	//////////////////


  /**
   *  The entity pageImageAlt
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageImageAlt;

  /**
   * <br> The entity pageImageAlt
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:pageImageAlt">Find the entity pageImageAlt in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageAlt(Wrap<String> c);

  public String getPageImageAlt() {
    return pageImageAlt;
  }
  public void setPageImageAlt(String o) {
    this.pageImageAlt = DeploySpine.staticSetPageImageAlt(siteRequest_, o);
  }
  public static String staticSetPageImageAlt(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected DeploySpine pageImageAltInit() {
    Wrap<String> pageImageAltWrap = new Wrap<String>().var("pageImageAlt");
    if(pageImageAlt == null) {
      _pageImageAlt(pageImageAltWrap);
      Optional.ofNullable(pageImageAltWrap.getO()).ifPresent(o -> {
        setPageImageAlt(o);
      });
    }
    return (DeploySpine)this;
  }

  public static String staticSearchPageImageAlt(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageImageAlt(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageAlt(SiteRequest siteRequest_, String o) {
    return DeploySpine.staticSearchPageImageAlt(siteRequest_, DeploySpine.staticSetPageImageAlt(siteRequest_, o)).toString();
  }

  public String sqlPageImageAlt() {
    return pageImageAlt;
  }

  public static String staticJsonPageImageAlt(String pageImageAlt) {
    return pageImageAlt;
  }

	///////////////////////
  // relatedArticleIds //
	///////////////////////


  /**
   *  The entity relatedArticleIds
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String relatedArticleIds;

  /**
   * <br> The entity relatedArticleIds
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:relatedArticleIds">Find the entity relatedArticleIds in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _relatedArticleIds(Wrap<String> w);

  public String getRelatedArticleIds() {
    return relatedArticleIds;
  }
  public void setRelatedArticleIds(String o) {
    this.relatedArticleIds = DeploySpine.staticSetRelatedArticleIds(siteRequest_, o);
  }
  public static String staticSetRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected DeploySpine relatedArticleIdsInit() {
    Wrap<String> relatedArticleIdsWrap = new Wrap<String>().var("relatedArticleIds");
    if(relatedArticleIds == null) {
      _relatedArticleIds(relatedArticleIdsWrap);
      Optional.ofNullable(relatedArticleIdsWrap.getO()).ifPresent(o -> {
        setRelatedArticleIds(o);
      });
    }
    return (DeploySpine)this;
  }

  public static String staticSearchRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return DeploySpine.staticSearchRelatedArticleIds(siteRequest_, DeploySpine.staticSetRelatedArticleIds(siteRequest_, o)).toString();
  }

  public String sqlRelatedArticleIds() {
    return relatedArticleIds;
  }

  public static String staticJsonRelatedArticleIds(String relatedArticleIds) {
    return relatedArticleIds;
  }

	//////////////////////////
  // relatedArticleSearch //
	//////////////////////////


  /**
   *  The entity relatedArticleSearch
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<SitePage> relatedArticleSearch;

  /**
   * <br> The entity relatedArticleSearch
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:relatedArticleSearch">Find the entity relatedArticleSearch in Solr</a>
   * <br>
   * @param promise is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _relatedArticleSearch(Promise<SearchList<SitePage>> promise);

  public SearchList<SitePage> getRelatedArticleSearch() {
    return relatedArticleSearch;
  }

  public void setRelatedArticleSearch(SearchList<SitePage> relatedArticleSearch) {
    this.relatedArticleSearch = relatedArticleSearch;
  }
  public static SearchList<SitePage> staticSetRelatedArticleSearch(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected Future<SearchList<SitePage>> relatedArticleSearchPromise() {
    Promise<SearchList<SitePage>> promise = Promise.promise();
    Promise<SearchList<SitePage>> promise2 = Promise.promise();
    _relatedArticleSearch(promise2);
    promise2.future().onSuccess(o -> {
      if(o != null && relatedArticleSearch == null) {
        o.promiseDeepForClass(siteRequest_).onSuccess(a -> {
          setRelatedArticleSearch(o);
          promise.complete(o);
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      } else {
        promise.complete(o);
      }
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

	/////////////////////
  // relatedArticles //
	/////////////////////


  /**
   *  The entity relatedArticles
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray relatedArticles;

  /**
   * <br> The entity relatedArticles
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.deployspine.DeploySpine&fq=entiteVar_enUS_indexed_string:relatedArticles">Find the entity relatedArticles in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _relatedArticles(Wrap<JsonArray> w);

  public JsonArray getRelatedArticles() {
    return relatedArticles;
  }

  public void setRelatedArticles(JsonArray relatedArticles) {
    this.relatedArticles = relatedArticles;
  }
  @JsonIgnore
  public void setRelatedArticles(String o) {
    this.relatedArticles = DeploySpine.staticSetRelatedArticles(siteRequest_, o);
  }
  public static JsonArray staticSetRelatedArticles(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected DeploySpine relatedArticlesInit() {
    Wrap<JsonArray> relatedArticlesWrap = new Wrap<JsonArray>().var("relatedArticles");
    if(relatedArticles == null) {
      _relatedArticles(relatedArticlesWrap);
      Optional.ofNullable(relatedArticlesWrap.getO()).ifPresent(o -> {
        setRelatedArticles(o);
      });
    }
    return (DeploySpine)this;
  }

  public static String staticSearchRelatedArticles(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrRelatedArticles(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRelatedArticles(SiteRequest siteRequest_, String o) {
    return DeploySpine.staticSearchRelatedArticles(siteRequest_, DeploySpine.staticSetRelatedArticles(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<DeploySpineGen<DEV>> promiseDeepDeploySpine(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepDeploySpine();
  }

  public Future<DeploySpineGen<DEV>> promiseDeepDeploySpine() {
    Promise<DeploySpineGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseDeploySpine(promise2);
    promise2.future().onSuccess(a -> {
      super.promiseDeepBaseResult(siteRequest_).onSuccess(b -> {
        promise.complete(this);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> promiseDeploySpine(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        nameInit();
        descriptionInit();
        pageIdInit();
        courseNumInit();
        pageImageUriInit();
        pageImageWidthInit();
        pageImageHeightInit();
        pageImageTypeInit();
        pageImageAltInit();
        relatedArticleIdsInit();
        promise2.complete();
      } catch(Exception ex) {
        promise2.fail(ex);
      }
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      relatedArticleSearchPromise().onSuccess(relatedArticleSearch -> {
        promise2.complete();
      }).onFailure(ex -> {
        promise2.fail(ex);
      });
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        relatedArticlesInit();
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

  @Override public Future<? extends DeploySpineGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepDeploySpine(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestDeploySpine(SiteRequest siteRequest_) {
      super.siteRequestBaseResult(siteRequest_);
    if(relatedArticleSearch != null)
      relatedArticleSearch.setSiteRequest_(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestDeploySpine(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainDeploySpine(v);
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
  public Object obtainDeploySpine(String var) {
    DeploySpine oDeploySpine = (DeploySpine)this;
    switch(var) {
      case "name":
        return oDeploySpine.name;
      case "description":
        return oDeploySpine.description;
      case "pageId":
        return oDeploySpine.pageId;
      case "courseNum":
        return oDeploySpine.courseNum;
      case "pageImageUri":
        return oDeploySpine.pageImageUri;
      case "pageImageWidth":
        return oDeploySpine.pageImageWidth;
      case "pageImageHeight":
        return oDeploySpine.pageImageHeight;
      case "pageImageType":
        return oDeploySpine.pageImageType;
      case "pageImageAlt":
        return oDeploySpine.pageImageAlt;
      case "relatedArticleIds":
        return oDeploySpine.relatedArticleIds;
      case "relatedArticleSearch":
        return oDeploySpine.relatedArticleSearch;
      case "relatedArticles":
        return oDeploySpine.relatedArticles;
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
        o = relateDeploySpine(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateDeploySpine(String var, Object val) {
    DeploySpine oDeploySpine = (DeploySpine)this;
    switch(var) {
      default:
        return super.relateBaseResult(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, DeploySpine o) {
    return staticSetDeploySpine(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetDeploySpine(String entityVar, SiteRequest siteRequest_, String v, DeploySpine o) {
    switch(entityVar) {
    case "name":
      return DeploySpine.staticSetName(siteRequest_, v);
    case "description":
      return DeploySpine.staticSetDescription(siteRequest_, v);
    case "pageId":
      return DeploySpine.staticSetPageId(siteRequest_, v);
    case "courseNum":
      return DeploySpine.staticSetCourseNum(siteRequest_, v);
    case "pageImageUri":
      return DeploySpine.staticSetPageImageUri(siteRequest_, v);
    case "pageImageWidth":
      return DeploySpine.staticSetPageImageWidth(siteRequest_, v);
    case "pageImageHeight":
      return DeploySpine.staticSetPageImageHeight(siteRequest_, v);
    case "pageImageType":
      return DeploySpine.staticSetPageImageType(siteRequest_, v);
    case "pageImageAlt":
      return DeploySpine.staticSetPageImageAlt(siteRequest_, v);
    case "relatedArticleIds":
      return DeploySpine.staticSetRelatedArticleIds(siteRequest_, v);
    case "relatedArticles":
      return DeploySpine.staticSetRelatedArticles(siteRequest_, v);
      default:
        return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<DeploySpine> fqDeploySpine(SiteRequest siteRequest, String var, Object val) {
    Promise<DeploySpine> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<DeploySpine> searchList = new SearchList<DeploySpine>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(DeploySpine.class);
        searchList.fq(String.format("%s:", DeploySpine.varIndexedDeploySpine(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the Deploy SPINE apps to OpenShift Local", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the Deploy SPINE apps to OpenShift Local", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the Deploy SPINE apps to OpenShift Local", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchDeploySpine(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchDeploySpine(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "name":
      return DeploySpine.staticSearchName(siteRequest_, (String)o);
    case "description":
      return DeploySpine.staticSearchDescription(siteRequest_, (String)o);
    case "pageId":
      return DeploySpine.staticSearchPageId(siteRequest_, (String)o);
    case "courseNum":
      return DeploySpine.staticSearchCourseNum(siteRequest_, (Integer)o);
    case "pageImageUri":
      return DeploySpine.staticSearchPageImageUri(siteRequest_, (String)o);
    case "pageImageWidth":
      return DeploySpine.staticSearchPageImageWidth(siteRequest_, (Integer)o);
    case "pageImageHeight":
      return DeploySpine.staticSearchPageImageHeight(siteRequest_, (Integer)o);
    case "pageImageType":
      return DeploySpine.staticSearchPageImageType(siteRequest_, (String)o);
    case "pageImageAlt":
      return DeploySpine.staticSearchPageImageAlt(siteRequest_, (String)o);
    case "relatedArticleIds":
      return DeploySpine.staticSearchRelatedArticleIds(siteRequest_, (String)o);
    case "relatedArticles":
      return DeploySpine.staticSearchRelatedArticles(siteRequest_, (JsonArray)o);
      default:
        return BaseResult.staticSearchBaseResult(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrDeploySpine(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrDeploySpine(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "name":
      return DeploySpine.staticSearchStrName(siteRequest_, (String)o);
    case "description":
      return DeploySpine.staticSearchStrDescription(siteRequest_, (String)o);
    case "pageId":
      return DeploySpine.staticSearchStrPageId(siteRequest_, (String)o);
    case "courseNum":
      return DeploySpine.staticSearchStrCourseNum(siteRequest_, (Integer)o);
    case "pageImageUri":
      return DeploySpine.staticSearchStrPageImageUri(siteRequest_, (String)o);
    case "pageImageWidth":
      return DeploySpine.staticSearchStrPageImageWidth(siteRequest_, (Integer)o);
    case "pageImageHeight":
      return DeploySpine.staticSearchStrPageImageHeight(siteRequest_, (Integer)o);
    case "pageImageType":
      return DeploySpine.staticSearchStrPageImageType(siteRequest_, (String)o);
    case "pageImageAlt":
      return DeploySpine.staticSearchStrPageImageAlt(siteRequest_, (String)o);
    case "relatedArticleIds":
      return DeploySpine.staticSearchStrRelatedArticleIds(siteRequest_, (String)o);
    case "relatedArticles":
      return DeploySpine.staticSearchStrRelatedArticles(siteRequest_, (String)o);
      default:
        return BaseResult.staticSearchStrBaseResult(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqDeploySpine(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqDeploySpine(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "name":
      return DeploySpine.staticSearchFqName(siteRequest_, o);
    case "description":
      return DeploySpine.staticSearchFqDescription(siteRequest_, o);
    case "pageId":
      return DeploySpine.staticSearchFqPageId(siteRequest_, o);
    case "courseNum":
      return DeploySpine.staticSearchFqCourseNum(siteRequest_, o);
    case "pageImageUri":
      return DeploySpine.staticSearchFqPageImageUri(siteRequest_, o);
    case "pageImageWidth":
      return DeploySpine.staticSearchFqPageImageWidth(siteRequest_, o);
    case "pageImageHeight":
      return DeploySpine.staticSearchFqPageImageHeight(siteRequest_, o);
    case "pageImageType":
      return DeploySpine.staticSearchFqPageImageType(siteRequest_, o);
    case "pageImageAlt":
      return DeploySpine.staticSearchFqPageImageAlt(siteRequest_, o);
    case "relatedArticleIds":
      return DeploySpine.staticSearchFqRelatedArticleIds(siteRequest_, o);
    case "relatedArticles":
      return DeploySpine.staticSearchFqRelatedArticles(siteRequest_, o);
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
          o = persistDeploySpine(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistDeploySpine(String var, Object val) {
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
      } else if("pageid".equals(varLower)) {
        if(val instanceof String) {
          setPageId((String)val);
        }
        saves.add("pageId");
        return val;
      } else if("coursenum".equals(varLower)) {
        if(val instanceof Integer) {
          setCourseNum((Integer)val);
        } else {
          setCourseNum(val == null ? null : val.toString());
        }
        saves.add("courseNum");
        return val;
      } else if("pageimageuri".equals(varLower)) {
        if(val instanceof String) {
          setPageImageUri((String)val);
        }
        saves.add("pageImageUri");
        return val;
      } else if("pageimagealt".equals(varLower)) {
        if(val instanceof String) {
          setPageImageAlt((String)val);
        }
        saves.add("pageImageAlt");
        return val;
      } else if("relatedarticleids".equals(varLower)) {
        if(val instanceof String) {
          setRelatedArticleIds((String)val);
        }
        saves.add("relatedArticleIds");
        return val;
    } else {
      return super.persistBaseResult(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateDeploySpine(doc);
  }
  public void populateDeploySpine(SolrResponse.Doc doc) {
    DeploySpine oDeploySpine = (DeploySpine)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("name")) {
        String name = (String)doc.get("name_docvalues_string");
        if(name != null)
          oDeploySpine.setName(name);
      }

      if(saves.contains("description")) {
        String description = (String)doc.get("description_docvalues_string");
        if(description != null)
          oDeploySpine.setDescription(description);
      }

      if(saves.contains("pageId")) {
        String pageId = (String)doc.get("pageId_docvalues_string");
        if(pageId != null)
          oDeploySpine.setPageId(pageId);
      }

      if(saves.contains("courseNum")) {
        Integer courseNum = (Integer)doc.get("courseNum_docvalues_int");
        if(courseNum != null)
          oDeploySpine.setCourseNum(courseNum);
      }

      if(saves.contains("pageImageUri")) {
        String pageImageUri = (String)doc.get("pageImageUri_docvalues_string");
        if(pageImageUri != null)
          oDeploySpine.setPageImageUri(pageImageUri);
      }

      if(saves.contains("pageImageWidth")) {
        Integer pageImageWidth = (Integer)doc.get("pageImageWidth_docvalues_int");
        if(pageImageWidth != null)
          oDeploySpine.setPageImageWidth(pageImageWidth);
      }

      if(saves.contains("pageImageHeight")) {
        Integer pageImageHeight = (Integer)doc.get("pageImageHeight_docvalues_int");
        if(pageImageHeight != null)
          oDeploySpine.setPageImageHeight(pageImageHeight);
      }

      if(saves.contains("pageImageType")) {
        String pageImageType = (String)doc.get("pageImageType_docvalues_string");
        if(pageImageType != null)
          oDeploySpine.setPageImageType(pageImageType);
      }

      if(saves.contains("pageImageAlt")) {
        String pageImageAlt = (String)doc.get("pageImageAlt_docvalues_string");
        if(pageImageAlt != null)
          oDeploySpine.setPageImageAlt(pageImageAlt);
      }

      if(saves.contains("relatedArticleIds")) {
        String relatedArticleIds = (String)doc.get("relatedArticleIds_docvalues_string");
        if(relatedArticleIds != null)
          oDeploySpine.setRelatedArticleIds(relatedArticleIds);
      }

      if(saves.contains("relatedArticles")) {
        String relatedArticles = (String)doc.get("relatedArticles_stored_string");
        if(relatedArticles != null)
          oDeploySpine.setRelatedArticles(relatedArticles);
      }
    }

    super.populateBaseResult(doc);
  }

  public void indexDeploySpine(JsonObject doc) {
    if(name != null) {
      doc.put("name_docvalues_string", name);
    }
    if(description != null) {
      doc.put("description_docvalues_string", description);
    }
    if(pageId != null) {
      doc.put("pageId_docvalues_string", pageId);
    }
    if(courseNum != null) {
      doc.put("courseNum_docvalues_int", courseNum);
    }
    if(pageImageUri != null) {
      doc.put("pageImageUri_docvalues_string", pageImageUri);
    }
    if(pageImageWidth != null) {
      doc.put("pageImageWidth_docvalues_int", pageImageWidth);
    }
    if(pageImageHeight != null) {
      doc.put("pageImageHeight_docvalues_int", pageImageHeight);
    }
    if(pageImageType != null) {
      doc.put("pageImageType_docvalues_string", pageImageType);
    }
    if(pageImageAlt != null) {
      doc.put("pageImageAlt_docvalues_string", pageImageAlt);
    }
    if(relatedArticleIds != null) {
      doc.put("relatedArticleIds_docvalues_string", relatedArticleIds);
    }
    if(relatedArticles != null) {
      doc.put("relatedArticles_stored_string", relatedArticles.toString());
    }
    super.indexBaseResult(doc);

	}

  public static String varStoredDeploySpine(String entityVar) {
    switch(entityVar) {
      case "name":
        return "name_docvalues_string";
      case "description":
        return "description_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      case "courseNum":
        return "courseNum_docvalues_int";
      case "pageImageUri":
        return "pageImageUri_docvalues_string";
      case "pageImageWidth":
        return "pageImageWidth_docvalues_int";
      case "pageImageHeight":
        return "pageImageHeight_docvalues_int";
      case "pageImageType":
        return "pageImageType_docvalues_string";
      case "pageImageAlt":
        return "pageImageAlt_docvalues_string";
      case "relatedArticleIds":
        return "relatedArticleIds_docvalues_string";
      case "relatedArticles":
        return "relatedArticles_stored_string";
      default:
        return BaseResult.varStoredBaseResult(entityVar);
    }
  }

  public static String varIndexedDeploySpine(String entityVar) {
    switch(entityVar) {
      case "name":
        return "name_docvalues_string";
      case "description":
        return "description_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      case "courseNum":
        return "courseNum_docvalues_int";
      case "pageImageUri":
        return "pageImageUri_docvalues_string";
      case "pageImageWidth":
        return "pageImageWidth_docvalues_int";
      case "pageImageHeight":
        return "pageImageHeight_docvalues_int";
      case "pageImageType":
        return "pageImageType_docvalues_string";
      case "pageImageAlt":
        return "pageImageAlt_docvalues_string";
      case "relatedArticleIds":
        return "relatedArticleIds_docvalues_string";
      default:
        return BaseResult.varIndexedBaseResult(entityVar);
    }
  }

  public static String searchVarDeploySpine(String searchVar) {
    switch(searchVar) {
      case "name_docvalues_string":
        return "name";
      case "description_docvalues_string":
        return "description";
      case "pageId_docvalues_string":
        return "pageId";
      case "courseNum_docvalues_int":
        return "courseNum";
      case "pageImageUri_docvalues_string":
        return "pageImageUri";
      case "pageImageWidth_docvalues_int":
        return "pageImageWidth";
      case "pageImageHeight_docvalues_int":
        return "pageImageHeight";
      case "pageImageType_docvalues_string":
        return "pageImageType";
      case "pageImageAlt_docvalues_string":
        return "pageImageAlt";
      case "relatedArticleIds_docvalues_string":
        return "relatedArticleIds";
      default:
        return BaseResult.searchVarBaseResult(searchVar);
    }
  }

  public static String varSearchDeploySpine(String entityVar) {
    switch(entityVar) {
      default:
        return BaseResult.varSearchBaseResult(entityVar);
    }
  }

  public static String varSuggestedDeploySpine(String entityVar) {
    switch(entityVar) {
      default:
        return BaseResult.varSuggestedBaseResult(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeDeploySpine(doc);
  }
  public void storeDeploySpine(SolrResponse.Doc doc) {
    DeploySpine oDeploySpine = (DeploySpine)this;
    SiteRequest siteRequest = oDeploySpine.getSiteRequest_();

    oDeploySpine.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
    oDeploySpine.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
    oDeploySpine.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oDeploySpine.setCourseNum(Optional.ofNullable(doc.get("courseNum_docvalues_int")).map(v -> v.toString()).orElse(null));
    oDeploySpine.setPageImageUri(Optional.ofNullable(doc.get("pageImageUri_docvalues_string")).map(v -> v.toString()).orElse(null));
    oDeploySpine.setPageImageWidth(Optional.ofNullable(doc.get("pageImageWidth_docvalues_int")).map(v -> v.toString()).orElse(null));
    oDeploySpine.setPageImageHeight(Optional.ofNullable(doc.get("pageImageHeight_docvalues_int")).map(v -> v.toString()).orElse(null));
    oDeploySpine.setPageImageType(Optional.ofNullable(doc.get("pageImageType_docvalues_string")).map(v -> v.toString()).orElse(null));
    oDeploySpine.setPageImageAlt(Optional.ofNullable(doc.get("pageImageAlt_docvalues_string")).map(v -> v.toString()).orElse(null));
    oDeploySpine.setRelatedArticleIds(Optional.ofNullable(doc.get("relatedArticleIds_docvalues_string")).map(v -> v.toString()).orElse(null));
    oDeploySpine.setRelatedArticles(Optional.ofNullable(doc.get("relatedArticles_stored_string")).map(v -> v.toString()).orElse(null));

    super.storeBaseResult(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestDeploySpine() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof DeploySpine) {
      DeploySpine original = (DeploySpine)o;
      if(!Objects.equals(name, original.getName()))
        apiRequest.addVars("name");
      if(!Objects.equals(description, original.getDescription()))
        apiRequest.addVars("description");
      if(!Objects.equals(pageId, original.getPageId()))
        apiRequest.addVars("pageId");
      if(!Objects.equals(courseNum, original.getCourseNum()))
        apiRequest.addVars("courseNum");
      if(!Objects.equals(pageImageUri, original.getPageImageUri()))
        apiRequest.addVars("pageImageUri");
      if(!Objects.equals(pageImageWidth, original.getPageImageWidth()))
        apiRequest.addVars("pageImageWidth");
      if(!Objects.equals(pageImageHeight, original.getPageImageHeight()))
        apiRequest.addVars("pageImageHeight");
      if(!Objects.equals(pageImageType, original.getPageImageType()))
        apiRequest.addVars("pageImageType");
      if(!Objects.equals(pageImageAlt, original.getPageImageAlt()))
        apiRequest.addVars("pageImageAlt");
      if(!Objects.equals(relatedArticleIds, original.getRelatedArticleIds()))
        apiRequest.addVars("relatedArticleIds");
      if(!Objects.equals(relatedArticles, original.getRelatedArticles()))
        apiRequest.addVars("relatedArticles");
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
    sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(courseNum).map(v -> "courseNum: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(pageImageUri).map(v -> "pageImageUri: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageImageWidth).map(v -> "pageImageWidth: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(pageImageHeight).map(v -> "pageImageHeight: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(pageImageType).map(v -> "pageImageType: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageImageAlt).map(v -> "pageImageAlt: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(relatedArticleIds).map(v -> "relatedArticleIds: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(relatedArticles).map(v -> "relatedArticles: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "DeploySpine";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.deployspine.DeploySpine";
  public static final String CLASS_AUTH_RESOURCE = "DEPLOYSPINE";
  public static final String CLASS_API_ADDRESS_DeploySpine = "computate.org-enUS-DeploySpine";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_DeploySpine;
  }
  public static final String VAR_name = "name";
  public static final String SET_name = "setName";
  public static final String VAR_description = "description";
  public static final String SET_description = "setDescription";
  public static final String VAR_pageId = "pageId";
  public static final String SET_pageId = "setPageId";
  public static final String VAR_courseNum = "courseNum";
  public static final String SET_courseNum = "setCourseNum";
  public static final String VAR_pageImageUri = "pageImageUri";
  public static final String SET_pageImageUri = "setPageImageUri";
  public static final String VAR_pageImageWidth = "pageImageWidth";
  public static final String SET_pageImageWidth = "setPageImageWidth";
  public static final String VAR_pageImageHeight = "pageImageHeight";
  public static final String SET_pageImageHeight = "setPageImageHeight";
  public static final String VAR_pageImageType = "pageImageType";
  public static final String SET_pageImageType = "setPageImageType";
  public static final String VAR_pageImageAlt = "pageImageAlt";
  public static final String SET_pageImageAlt = "setPageImageAlt";
  public static final String VAR_relatedArticleIds = "relatedArticleIds";
  public static final String SET_relatedArticleIds = "setRelatedArticleIds";
  public static final String VAR_relatedArticleSearch = "relatedArticleSearch";
  public static final String SET_relatedArticleSearch = "setRelatedArticleSearch";
  public static final String VAR_relatedArticles = "relatedArticles";
  public static final String SET_relatedArticles = "setRelatedArticles";

  public static List<String> varsQForClass() {
    return DeploySpine.varsQDeploySpine(new ArrayList<String>());
  }
  public static List<String> varsQDeploySpine(List<String> vars) {
    BaseResult.varsQBaseResult(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return DeploySpine.varsFqDeploySpine(new ArrayList<String>());
  }
  public static List<String> varsFqDeploySpine(List<String> vars) {
    vars.add(VAR_name);
    vars.add(VAR_description);
    vars.add(VAR_pageId);
    vars.add(VAR_courseNum);
    vars.add(VAR_pageImageUri);
    BaseResult.varsFqBaseResult(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return DeploySpine.varsRangeDeploySpine(new ArrayList<String>());
  }
  public static List<String> varsRangeDeploySpine(List<String> vars) {
    vars.add(VAR_courseNum);
    BaseResult.varsRangeBaseResult(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_name = "course name";
  public static final String DISPLAY_NAME_description = "course description";
  public static final String DISPLAY_NAME_pageId = "Page ID";
  public static final String DISPLAY_NAME_courseNum = "Course Number";
  public static final String DISPLAY_NAME_pageImageUri = "imageUri";
  public static final String DISPLAY_NAME_pageImageWidth = "";
  public static final String DISPLAY_NAME_pageImageHeight = "";
  public static final String DISPLAY_NAME_pageImageType = "";
  public static final String DISPLAY_NAME_pageImageAlt = "";
  public static final String DISPLAY_NAME_relatedArticleIds = "related article IDs";
  public static final String DISPLAY_NAME_relatedArticleSearch = "";
  public static final String DISPLAY_NAME_relatedArticles = "related articles";

  @Override
  public String idForClass() {
    return pageId;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return name;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return DeploySpine.NameAdjectiveSingular_enUS;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/deploy-spine/%s";
  }

  @Override
  public String enUSStringFormatUrlDisplayPageForClass() {
    return "%s/en-us/learn/deploy-spine/%s";
  }

  public static String varJsonForClass(String var, Boolean patch) {
    return DeploySpine.varJsonDeploySpine(var, patch);
  }
  public static String varJsonDeploySpine(String var, Boolean patch) {
    switch(var) {
    case VAR_name:
      return patch ? SET_name : VAR_name;
    case VAR_description:
      return patch ? SET_description : VAR_description;
    case VAR_pageId:
      return patch ? SET_pageId : VAR_pageId;
    case VAR_courseNum:
      return patch ? SET_courseNum : VAR_courseNum;
    case VAR_pageImageUri:
      return patch ? SET_pageImageUri : VAR_pageImageUri;
    case VAR_pageImageWidth:
      return patch ? SET_pageImageWidth : VAR_pageImageWidth;
    case VAR_pageImageHeight:
      return patch ? SET_pageImageHeight : VAR_pageImageHeight;
    case VAR_pageImageType:
      return patch ? SET_pageImageType : VAR_pageImageType;
    case VAR_pageImageAlt:
      return patch ? SET_pageImageAlt : VAR_pageImageAlt;
    case VAR_relatedArticleIds:
      return patch ? SET_relatedArticleIds : VAR_relatedArticleIds;
    case VAR_relatedArticleSearch:
      return patch ? SET_relatedArticleSearch : VAR_relatedArticleSearch;
    case VAR_relatedArticles:
      return patch ? SET_relatedArticles : VAR_relatedArticles;
    default:
      return BaseResult.varJsonBaseResult(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return DeploySpine.displayNameDeploySpine(var);
  }
  public static String displayNameDeploySpine(String var) {
    switch(var) {
    case VAR_name:
      return DISPLAY_NAME_name;
    case VAR_description:
      return DISPLAY_NAME_description;
    case VAR_pageId:
      return DISPLAY_NAME_pageId;
    case VAR_courseNum:
      return DISPLAY_NAME_courseNum;
    case VAR_pageImageUri:
      return DISPLAY_NAME_pageImageUri;
    case VAR_pageImageWidth:
      return DISPLAY_NAME_pageImageWidth;
    case VAR_pageImageHeight:
      return DISPLAY_NAME_pageImageHeight;
    case VAR_pageImageType:
      return DISPLAY_NAME_pageImageType;
    case VAR_pageImageAlt:
      return DISPLAY_NAME_pageImageAlt;
    case VAR_relatedArticleIds:
      return DISPLAY_NAME_relatedArticleIds;
    case VAR_relatedArticleSearch:
      return DISPLAY_NAME_relatedArticleSearch;
    case VAR_relatedArticles:
      return DISPLAY_NAME_relatedArticles;
    default:
      return BaseResult.displayNameBaseResult(var);
    }
  }

  public static String descriptionDeploySpine(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_name:
      return "The course name. ";
    case VAR_description:
      return "The course description. ";
    case VAR_pageId:
      return "The ID for this page. ";
    case VAR_courseNum:
      return "The course number for this page. ";
    case VAR_pageImageUri:
      return "The page image URI";
    case VAR_pageImageWidth:
      return "The image width";
    case VAR_pageImageHeight:
      return "The image height";
    case VAR_pageImageType:
      return "The image height";
    case VAR_pageImageAlt:
      return "The image accessibility text. ";
    case VAR_relatedArticleIds:
      return "The related article IDs comma-separated. ";
    case VAR_relatedArticles:
      return "A JSON array of related articles. ";
      default:
        return BaseResult.descriptionBaseResult(var);
    }
  }

  public static String classSimpleNameDeploySpine(String var) {
    switch(var) {
    case VAR_name:
      return "String";
    case VAR_description:
      return "String";
    case VAR_pageId:
      return "String";
    case VAR_courseNum:
      return "Integer";
    case VAR_pageImageUri:
      return "String";
    case VAR_pageImageWidth:
      return "Integer";
    case VAR_pageImageHeight:
      return "Integer";
    case VAR_pageImageType:
      return "String";
    case VAR_pageImageAlt:
      return "String";
    case VAR_relatedArticleIds:
      return "String";
    case VAR_relatedArticleSearch:
      return "SearchList";
    case VAR_relatedArticles:
      return "JsonArray";
      default:
        return BaseResult.classSimpleNameBaseResult(var);
    }
  }

  public static Integer htmColumnDeploySpine(String var) {
    switch(var) {
    case VAR_name:
      return 1;
    case VAR_description:
      return 2;
      default:
        return BaseResult.htmColumnBaseResult(var);
    }
  }

  public static Integer htmRowDeploySpine(String var) {
    switch(var) {
    case VAR_name:
      return 3;
    case VAR_description:
      return 3;
    case VAR_pageId:
      return 99;
    case VAR_pageImageUri:
      return 4;
      default:
        return BaseResult.htmRowBaseResult(var);
    }
  }

  public static Integer htmCellDeploySpine(String var) {
    switch(var) {
    case VAR_name:
      return 1;
    case VAR_description:
      return 2;
    case VAR_pageId:
      return 1;
    case VAR_pageImageUri:
      return 1;
      default:
        return BaseResult.htmCellBaseResult(var);
    }
  }

  public static Integer lengthMinDeploySpine(String var) {
    switch(var) {
      default:
        return BaseResult.lengthMinBaseResult(var);
    }
  }

  public static Integer lengthMaxDeploySpine(String var) {
    switch(var) {
      default:
        return BaseResult.lengthMaxBaseResult(var);
    }
  }

  public static Integer maxDeploySpine(String var) {
    switch(var) {
      default:
        return BaseResult.maxBaseResult(var);
    }
  }

  public static Integer minDeploySpine(String var) {
    switch(var) {
      default:
        return BaseResult.minBaseResult(var);
    }
  }
}
