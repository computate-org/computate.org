package org.computate.site.model.spinedoc;

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
import java.lang.Boolean;
import java.math.BigDecimal;
import java.lang.String;
import java.lang.Integer;
import org.computate.vertx.search.list.SearchList;
import org.computate.site.model.spinedoc.SpineDoc;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SpineDocGen into the class SpineDoc. 
 * </li><li>You can add a class comment "Model: true" if you wish to persist these SpineDoc objects in a relational PostgreSQL database transactionally in the RESTful API. 
 * The code to persist and query the SpineDocGen data in the database will then be automatically generated. 
 * </li>
 * <h3>About the SpineDoc class and it's generated class SpineDocGen&lt;BaseResult&gt;: </h3>extends SpineDocGen
 * <p>
 * This Java class extends a generated Java class SpineDocGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc">Find the class SpineDoc in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SpineDocGen<BaseResult>
 * <p>This <code>class SpineDoc extends SpineDocGen&lt;BaseResult&gt;</code>, which means it extends a newly generated SpineDocGen. 
 * The generated <code>class SpineDocGen extends BaseResult</code> which means that SpineDoc extends SpineDocGen which extends BaseResult. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: SPINE docs"</b>, which groups all of the OpenAPIs for SpineDoc objects under the tag "SPINE docs". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/spine-doc</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/spine-doc"</b>, which defines the base API URI for SpineDoc objects as "/en-us/api/spine-doc" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SpineDoc class will inherit the helpful inherited class comments from the super class SpineDocGen. 
 * </p>
 * <h2>Rows: 100</h2>
 * <p>This class contains a comment <b>"Rows: 100"</b>, which means the SpineDoc API will return a default of 100 records instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>Order: 5</h2>
 * <p>This class contains a comment <b>"Order: 5"</b>, which means this class will be sorted by the given number 5 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 5</h2>
 * <p>This class contains a comment <b>"SqlOrder: 5"</b>, which means this class will be sorted by the given number 5 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.spinedoc.SpineDocPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.site.model.spinedoc.SpineDocPage extends org.computate.site.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the SpineDoc Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a SPINE doc</h2>
 * <p>This class contains a comment <b>"AName.enUS: a SPINE doc"</b>, which identifies the language context to describe a SpineDoc as "a SPINE doc". 
 * </p>
 * <p>
 * Delete the class SpineDoc in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.spinedoc in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.spinedoc&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class SpineDocGen<DEV> extends BaseResult {
  protected static final Logger LOG = LoggerFactory.getLogger(SpineDoc.class);

  public static final String Description_enUS = "Learn about the bones that form the platform and data models built with SPINE Programming Theory and increase your developer productivity up to 4900%. ";
  public static final String AName_enUS = "a SPINE doc";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this SPINE doc";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the SPINE doc";
  public static final String SingularName_enUS = "SPINE doc";
  public static final String PluralName_enUS = "SPINE docs";
  public static final String NameActual_enUS = "current SPINE doc";
  public static final String AllName_enUS = "all SPINE docs";
  public static final String SearchAllNameBy_enUS = "search SPINE docs by ";
  public static final String SearchAllName_enUS = "search SPINE docs";
  public static final String Title_enUS = "SPINE docs";
  public static final String ThePluralName_enUS = "the SPINE docs";
  public static final String NoNameFound_enUS = "no SPINE doc found";
  public static final String ApiUri_enUS = "/en-us/api/spine-doc";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/spine-doc";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/spine-doc/{pageId}";
  public static final String OfName_enUS = "of SPINE doc";
  public static final String ANameAdjective_enUS = "a SPINE doc";
  public static final String NameAdjectiveSingular_enUS = "SPINE doc";
  public static final String NameAdjectivePlural_enUS = "SPINE docs";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/spine-doc";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/spine-doc";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/spine-doc";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/spine-doc/{pageId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/spine-doc/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/spine-doc/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/spine-doc";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/spine-doc";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/spine-doc";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/spine-doc";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/spine-doc";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/spine-doc";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/spine-doc-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/spine-doc-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/spine-doc-import";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/spine-doc/{pageId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/spine-doc/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/spine-doc/%s";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/spine-doc";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/spine-doc";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/spine-doc";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/spine-doc/{pageId}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/spine-doc/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/spine-doc/%s";
  public static final String DisplayPage_enUS_OpenApiUri = "/en-us/learn/spine-doc/{pageId}";
  public static final String DisplayPage_enUS_StringFormatUri = "/en-us/learn/spine-doc/%s";
  public static final String DisplayPage_enUS_StringFormatUrl = "%s/en-us/learn/spine-doc/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/spine-doc";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/spine-doc";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/spine-doc";

  public static final String Icon = "<i class=\"fa-kit fa-spine\"></i>";
  public static final Integer Rows = 100;

	/////////////
  // article //
	/////////////


  /**
   *  The entity article
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected Boolean article;

  /**
   * <br> The entity article
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:article">Find the entity article in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _article(Wrap<Boolean> w);

  public Boolean getArticle() {
    return article;
  }

  public void setArticle(Boolean article) {
    this.article = article;
  }
  @JsonIgnore
  public void setArticle(String o) {
    this.article = SpineDoc.staticSetArticle(siteRequest_, o);
  }
  public static Boolean staticSetArticle(SiteRequest siteRequest_, String o) {
    return Boolean.parseBoolean(o);
  }
  protected SpineDoc articleInit() {
    Wrap<Boolean> articleWrap = new Wrap<Boolean>().var("article");
    if(article == null) {
      _article(articleWrap);
      Optional.ofNullable(articleWrap.getO()).ifPresent(o -> {
        setArticle(o);
      });
    }
    return (SpineDoc)this;
  }

  public static Boolean staticSearchArticle(SiteRequest siteRequest_, Boolean o) {
    return o;
  }

  public static String staticSearchStrArticle(SiteRequest siteRequest_, Boolean o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqArticle(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchArticle(siteRequest_, SpineDoc.staticSetArticle(siteRequest_, o)).toString();
  }

	////////////////
  // importance //
	////////////////


  /**
   *  The entity importance
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected BigDecimal importance;

  /**
   * <br> The entity importance
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:importance">Find the entity importance in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _importance(Wrap<BigDecimal> w);

  public BigDecimal getImportance() {
    return importance;
  }

  public void setImportance(BigDecimal importance) {
    this.importance = importance;
  }
  @JsonIgnore
  public void setImportance(String o) {
    this.importance = SpineDoc.staticSetImportance(siteRequest_, o);
  }
  public static Integer staticScaleImportance() {
    return 2;
  }
  public static MathContext staticMathContextImportance() {
    return new MathContext(0, RoundingMode.valueOf("HALF_UP"));
  }
  public static BigDecimal staticSetImportance(SiteRequest siteRequest_, String o) {
    o = StringUtils.removeAll(o, "[^\\d\\.-]");
    if(NumberUtils.isParsable(o))
      return new BigDecimal(o, staticMathContextImportance()).setScale(staticScaleImportance(), RoundingMode.valueOf("HALF_UP"));
    return null;
  }
  @JsonIgnore
  public void setImportance(Double o) {
    setImportance(new BigDecimal(o, staticMathContextImportance()).setScale(staticScaleImportance(), RoundingMode.valueOf("HALF_UP")));
  }
  @JsonIgnore
  public void setImportance(Integer o) {
    setImportance(new BigDecimal(o, staticMathContextImportance()).setScale(staticScaleImportance(), RoundingMode.valueOf("HALF_UP")));
  }
  @JsonIgnore
  public void setImportance(Number o) {
    setImportance(new BigDecimal(o.doubleValue(), staticMathContextImportance()).setScale(staticScaleImportance(), RoundingMode.valueOf("HALF_UP")));
  }
  protected SpineDoc importanceInit() {
    Wrap<BigDecimal> importanceWrap = new Wrap<BigDecimal>().var("importance");
    if(importance == null) {
      _importance(importanceWrap);
      Optional.ofNullable(importanceWrap.getO()).ifPresent(o -> {
        setImportance(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchImportance(SiteRequest siteRequest_, BigDecimal o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchStrImportance(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqImportance(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchImportance(siteRequest_, SpineDoc.staticSetImportance(siteRequest_, o)).toString();
  }

  public BigDecimal sqlImportance() {
    return importance;
  }

  public static String staticJsonImportance(BigDecimal importance) {
    return Optional.ofNullable(importance).map(v -> v.toString()).orElse(null);
  }

	////////////////
  // commentVar //
	////////////////


  /**
   *  The entity commentVar
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String commentVar;

  /**
   * <br> The entity commentVar
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:commentVar">Find the entity commentVar in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _commentVar(Wrap<String> w);

  public String getCommentVar() {
    return commentVar;
  }
  public void setCommentVar(String o) {
    this.commentVar = SpineDoc.staticSetCommentVar(siteRequest_, o);
  }
  public static String staticSetCommentVar(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc commentVarInit() {
    Wrap<String> commentVarWrap = new Wrap<String>().var("commentVar");
    if(commentVar == null) {
      _commentVar(commentVarWrap);
      Optional.ofNullable(commentVarWrap.getO()).ifPresent(o -> {
        setCommentVar(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchCommentVar(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCommentVar(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCommentVar(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchCommentVar(siteRequest_, SpineDoc.staticSetCommentVar(siteRequest_, o)).toString();
  }

  public String sqlCommentVar() {
    return commentVar;
  }

  public static String staticJsonCommentVar(String commentVar) {
    return commentVar;
  }

	//////////////
  // javaType //
	//////////////


  /**
   *  The entity javaType
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String javaType;

  /**
   * <br> The entity javaType
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:javaType">Find the entity javaType in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _javaType(Wrap<String> w);

  public String getJavaType() {
    return javaType;
  }
  public void setJavaType(String o) {
    this.javaType = SpineDoc.staticSetJavaType(siteRequest_, o);
  }
  public static String staticSetJavaType(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc javaTypeInit() {
    Wrap<String> javaTypeWrap = new Wrap<String>().var("javaType");
    if(javaType == null) {
      _javaType(javaTypeWrap);
      Optional.ofNullable(javaTypeWrap.getO()).ifPresent(o -> {
        setJavaType(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchJavaType(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrJavaType(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqJavaType(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchJavaType(siteRequest_, SpineDoc.staticSetJavaType(siteRequest_, o)).toString();
  }

  public String sqlJavaType() {
    return javaType;
  }

  public static String staticJsonJavaType(String javaType) {
    return javaType;
  }

	///////////////
  // javaClass //
	///////////////


  /**
   *  The entity javaClass
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String javaClass;

  /**
   * <br> The entity javaClass
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:javaClass">Find the entity javaClass in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _javaClass(Wrap<String> w);

  public String getJavaClass() {
    return javaClass;
  }
  public void setJavaClass(String o) {
    this.javaClass = SpineDoc.staticSetJavaClass(siteRequest_, o);
  }
  public static String staticSetJavaClass(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc javaClassInit() {
    Wrap<String> javaClassWrap = new Wrap<String>().var("javaClass");
    if(javaClass == null) {
      _javaClass(javaClassWrap);
      Optional.ofNullable(javaClassWrap.getO()).ifPresent(o -> {
        setJavaClass(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchJavaClass(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrJavaClass(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqJavaClass(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchJavaClass(siteRequest_, SpineDoc.staticSetJavaClass(siteRequest_, o)).toString();
  }

  public String sqlJavaClass() {
    return javaClass;
  }

  public static String staticJsonJavaClass(String javaClass) {
    return javaClass;
  }

	//////////////////
  // sinceVersion //
	//////////////////


  /**
   *  The entity sinceVersion
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String sinceVersion;

  /**
   * <br> The entity sinceVersion
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:sinceVersion">Find the entity sinceVersion in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _sinceVersion(Wrap<String> w);

  public String getSinceVersion() {
    return sinceVersion;
  }
  public void setSinceVersion(String o) {
    this.sinceVersion = SpineDoc.staticSetSinceVersion(siteRequest_, o);
  }
  public static String staticSetSinceVersion(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc sinceVersionInit() {
    Wrap<String> sinceVersionWrap = new Wrap<String>().var("sinceVersion");
    if(sinceVersion == null) {
      _sinceVersion(sinceVersionWrap);
      Optional.ofNullable(sinceVersionWrap.getO()).ifPresent(o -> {
        setSinceVersion(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchSinceVersion(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSinceVersion(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSinceVersion(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchSinceVersion(siteRequest_, SpineDoc.staticSetSinceVersion(siteRequest_, o)).toString();
  }

  public String sqlSinceVersion() {
    return sinceVersion;
  }

  public static String staticJsonSinceVersion(String sinceVersion) {
    return sinceVersion;
  }

	///////////////////
  // supportStatus //
	///////////////////


  /**
   *  The entity supportStatus
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String supportStatus;

  /**
   * <br> The entity supportStatus
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:supportStatus">Find the entity supportStatus in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _supportStatus(Wrap<String> w);

  public String getSupportStatus() {
    return supportStatus;
  }
  public void setSupportStatus(String o) {
    this.supportStatus = SpineDoc.staticSetSupportStatus(siteRequest_, o);
  }
  public static String staticSetSupportStatus(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc supportStatusInit() {
    Wrap<String> supportStatusWrap = new Wrap<String>().var("supportStatus");
    if(supportStatus == null) {
      _supportStatus(supportStatusWrap);
      Optional.ofNullable(supportStatusWrap.getO()).ifPresent(o -> {
        setSupportStatus(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchSupportStatus(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSupportStatus(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSupportStatus(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchSupportStatus(siteRequest_, SpineDoc.staticSetSupportStatus(siteRequest_, o)).toString();
  }

  public String sqlSupportStatus() {
    return supportStatus;
  }

  public static String staticJsonSupportStatus(String supportStatus) {
    return supportStatus;
  }

	//////////////////
  // exampleValue //
	//////////////////


  /**
   *  The entity exampleValue
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String exampleValue;

  /**
   * <br> The entity exampleValue
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:exampleValue">Find the entity exampleValue in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _exampleValue(Wrap<String> w);

  public String getExampleValue() {
    return exampleValue;
  }
  public void setExampleValue(String o) {
    this.exampleValue = SpineDoc.staticSetExampleValue(siteRequest_, o);
  }
  public static String staticSetExampleValue(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc exampleValueInit() {
    Wrap<String> exampleValueWrap = new Wrap<String>().var("exampleValue");
    if(exampleValue == null) {
      _exampleValue(exampleValueWrap);
      Optional.ofNullable(exampleValueWrap.getO()).ifPresent(o -> {
        setExampleValue(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchExampleValue(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrExampleValue(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqExampleValue(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchExampleValue(siteRequest_, SpineDoc.staticSetExampleValue(siteRequest_, o)).toString();
  }

  public String sqlExampleValue() {
    return exampleValue;
  }

  public static String staticJsonExampleValue(String exampleValue) {
    return exampleValue;
  }

	/////////////////
  // exampleCode //
	/////////////////


  /**
   *  The entity exampleCode
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String exampleCode;

  /**
   * <br> The entity exampleCode
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:exampleCode">Find the entity exampleCode in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _exampleCode(Wrap<String> w);

  public String getExampleCode() {
    return exampleCode;
  }
  public void setExampleCode(String o) {
    this.exampleCode = SpineDoc.staticSetExampleCode(siteRequest_, o);
  }
  public static String staticSetExampleCode(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc exampleCodeInit() {
    Wrap<String> exampleCodeWrap = new Wrap<String>().var("exampleCode");
    if(exampleCode == null) {
      _exampleCode(exampleCodeWrap);
      Optional.ofNullable(exampleCodeWrap.getO()).ifPresent(o -> {
        setExampleCode(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchExampleCode(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrExampleCode(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqExampleCode(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchExampleCode(siteRequest_, SpineDoc.staticSetExampleCode(siteRequest_, o)).toString();
  }

  public String sqlExampleCode() {
    return exampleCode;
  }

  public static String staticJsonExampleCode(String exampleCode) {
    return exampleCode;
  }

	//////////////
  // copyCode //
	//////////////


  /**
   *  The entity copyCode
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String copyCode;

  /**
   * <br> The entity copyCode
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:copyCode">Find the entity copyCode in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _copyCode(Wrap<String> w);

  public String getCopyCode() {
    return copyCode;
  }
  public void setCopyCode(String o) {
    this.copyCode = SpineDoc.staticSetCopyCode(siteRequest_, o);
  }
  public static String staticSetCopyCode(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc copyCodeInit() {
    Wrap<String> copyCodeWrap = new Wrap<String>().var("copyCode");
    if(copyCode == null) {
      _copyCode(copyCodeWrap);
      Optional.ofNullable(copyCodeWrap.getO()).ifPresent(o -> {
        setCopyCode(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchCopyCode(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCopyCode(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCopyCode(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchCopyCode(siteRequest_, SpineDoc.staticSetCopyCode(siteRequest_, o)).toString();
  }

  public String sqlCopyCode() {
    return copyCode;
  }

  public static String staticJsonCopyCode(String copyCode) {
    return copyCode;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:courseNum">Find the entity courseNum in Solr</a>
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
    this.courseNum = SpineDoc.staticSetCourseNum(siteRequest_, o);
  }
  public static Integer staticSetCourseNum(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected SpineDoc courseNumInit() {
    Wrap<Integer> courseNumWrap = new Wrap<Integer>().var("courseNum");
    if(courseNum == null) {
      _courseNum(courseNumWrap);
      Optional.ofNullable(courseNumWrap.getO()).ifPresent(o -> {
        setCourseNum(o);
      });
    }
    return (SpineDoc)this;
  }

  public static Integer staticSearchCourseNum(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrCourseNum(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCourseNum(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchCourseNum(siteRequest_, SpineDoc.staticSetCourseNum(siteRequest_, o)).toString();
  }

  public Integer sqlCourseNum() {
    return courseNum;
  }

  public static String staticJsonCourseNum(Integer courseNum) {
    return Optional.ofNullable(courseNum).map(v -> v.toString()).orElse(null);
  }

	///////////////
  // lessonNum //
	///////////////


  /**
   *  The entity lessonNum
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer lessonNum;

  /**
   * <br> The entity lessonNum
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:lessonNum">Find the entity lessonNum in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _lessonNum(Wrap<Integer> w);

  public Integer getLessonNum() {
    return lessonNum;
  }

  public void setLessonNum(Integer lessonNum) {
    this.lessonNum = lessonNum;
  }
  @JsonIgnore
  public void setLessonNum(String o) {
    this.lessonNum = SpineDoc.staticSetLessonNum(siteRequest_, o);
  }
  public static Integer staticSetLessonNum(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected SpineDoc lessonNumInit() {
    Wrap<Integer> lessonNumWrap = new Wrap<Integer>().var("lessonNum");
    if(lessonNum == null) {
      _lessonNum(lessonNumWrap);
      Optional.ofNullable(lessonNumWrap.getO()).ifPresent(o -> {
        setLessonNum(o);
      });
    }
    return (SpineDoc)this;
  }

  public static Integer staticSearchLessonNum(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrLessonNum(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqLessonNum(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchLessonNum(siteRequest_, SpineDoc.staticSetLessonNum(siteRequest_, o)).toString();
  }

  public Integer sqlLessonNum() {
    return lessonNum;
  }

  public static String staticJsonLessonNum(Integer lessonNum) {
    return Optional.ofNullable(lessonNum).map(v -> v.toString()).orElse(null);
  }

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _name(Wrap<String> w);

  public String getName() {
    return name;
  }
  public void setName(String o) {
    this.name = SpineDoc.staticSetName(siteRequest_, o);
  }
  public static String staticSetName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc nameInit() {
    Wrap<String> nameWrap = new Wrap<String>().var("name");
    if(name == null) {
      _name(nameWrap);
      Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
        setName(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchName(siteRequest_, SpineDoc.staticSetName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _description(Wrap<String> w);

  public String getDescription() {
    return description;
  }
  public void setDescription(String o) {
    this.description = SpineDoc.staticSetDescription(siteRequest_, o);
  }
  public static String staticSetDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc descriptionInit() {
    Wrap<String> descriptionWrap = new Wrap<String>().var("description");
    if(description == null) {
      _description(descriptionWrap);
      Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
        setDescription(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchDescription(siteRequest_, SpineDoc.staticSetDescription(siteRequest_, o)).toString();
  }

  public String sqlDescription() {
    return description;
  }

  public static String staticJsonDescription(String description) {
    return description;
  }

	////////////////
  // authorName //
	////////////////


  /**
   *  The entity authorName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String authorName;

  /**
   * <br> The entity authorName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:authorName">Find the entity authorName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _authorName(Wrap<String> w);

  public String getAuthorName() {
    return authorName;
  }
  public void setAuthorName(String o) {
    this.authorName = SpineDoc.staticSetAuthorName(siteRequest_, o);
  }
  public static String staticSetAuthorName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc authorNameInit() {
    Wrap<String> authorNameWrap = new Wrap<String>().var("authorName");
    if(authorName == null) {
      _authorName(authorNameWrap);
      Optional.ofNullable(authorNameWrap.getO()).ifPresent(o -> {
        setAuthorName(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchAuthorName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAuthorName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAuthorName(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchAuthorName(siteRequest_, SpineDoc.staticSetAuthorName(siteRequest_, o)).toString();
  }

  public String sqlAuthorName() {
    return authorName;
  }

  public static String staticJsonAuthorName(String authorName) {
    return authorName;
  }

	///////////////
  // authorUrl //
	///////////////


  /**
   *  The entity authorUrl
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String authorUrl;

  /**
   * <br> The entity authorUrl
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:authorUrl">Find the entity authorUrl in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _authorUrl(Wrap<String> w);

  public String getAuthorUrl() {
    return authorUrl;
  }
  public void setAuthorUrl(String o) {
    this.authorUrl = SpineDoc.staticSetAuthorUrl(siteRequest_, o);
  }
  public static String staticSetAuthorUrl(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc authorUrlInit() {
    Wrap<String> authorUrlWrap = new Wrap<String>().var("authorUrl");
    if(authorUrl == null) {
      _authorUrl(authorUrlWrap);
      Optional.ofNullable(authorUrlWrap.getO()).ifPresent(o -> {
        setAuthorUrl(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchAuthorUrl(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAuthorUrl(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAuthorUrl(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchAuthorUrl(siteRequest_, SpineDoc.staticSetAuthorUrl(siteRequest_, o)).toString();
  }

  public String sqlAuthorUrl() {
    return authorUrl;
  }

  public static String staticJsonAuthorUrl(String authorUrl) {
    return authorUrl;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageId(Wrap<String> w);

  public String getPageId() {
    return pageId;
  }
  public void setPageId(String o) {
    this.pageId = SpineDoc.staticSetPageId(siteRequest_, o);
  }
  public static String staticSetPageId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc pageIdInit() {
    Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
    if(pageId == null) {
      _pageId(pageIdWrap);
      Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
        setPageId(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchPageId(siteRequest_, SpineDoc.staticSetPageId(siteRequest_, o)).toString();
  }

  public String sqlPageId() {
    return pageId;
  }

  public static String staticJsonPageId(String pageId) {
    return pageId;
  }

	//////////////////
  // pageVideoUrl //
	//////////////////


  /**
   *  The entity pageVideoUrl
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageVideoUrl;

  /**
   * <br> The entity pageVideoUrl
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:pageVideoUrl">Find the entity pageVideoUrl in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageVideoUrl(Wrap<String> w);

  public String getPageVideoUrl() {
    return pageVideoUrl;
  }
  public void setPageVideoUrl(String o) {
    this.pageVideoUrl = SpineDoc.staticSetPageVideoUrl(siteRequest_, o);
  }
  public static String staticSetPageVideoUrl(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc pageVideoUrlInit() {
    Wrap<String> pageVideoUrlWrap = new Wrap<String>().var("pageVideoUrl");
    if(pageVideoUrl == null) {
      _pageVideoUrl(pageVideoUrlWrap);
      Optional.ofNullable(pageVideoUrlWrap.getO()).ifPresent(o -> {
        setPageVideoUrl(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchPageVideoUrl(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageVideoUrl(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageVideoUrl(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchPageVideoUrl(siteRequest_, SpineDoc.staticSetPageVideoUrl(siteRequest_, o)).toString();
  }

  public String sqlPageVideoUrl() {
    return pageVideoUrl;
  }

  public static String staticJsonPageVideoUrl(String pageVideoUrl) {
    return pageVideoUrl;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:pageImageUri">Find the entity pageImageUri in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageUri(Wrap<String> w);

  public String getPageImageUri() {
    return pageImageUri;
  }
  public void setPageImageUri(String o) {
    this.pageImageUri = SpineDoc.staticSetPageImageUri(siteRequest_, o);
  }
  public static String staticSetPageImageUri(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc pageImageUriInit() {
    Wrap<String> pageImageUriWrap = new Wrap<String>().var("pageImageUri");
    if(pageImageUri == null) {
      _pageImageUri(pageImageUriWrap);
      Optional.ofNullable(pageImageUriWrap.getO()).ifPresent(o -> {
        setPageImageUri(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchPageImageUri(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageImageUri(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageUri(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchPageImageUri(siteRequest_, SpineDoc.staticSetPageImageUri(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:pageImageWidth">Find the entity pageImageWidth in Solr</a>
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
    this.pageImageWidth = SpineDoc.staticSetPageImageWidth(siteRequest_, o);
  }
  public static Integer staticSetPageImageWidth(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected SpineDoc pageImageWidthInit() {
    Wrap<Integer> pageImageWidthWrap = new Wrap<Integer>().var("pageImageWidth");
    if(pageImageWidth == null) {
      _pageImageWidth(pageImageWidthWrap);
      Optional.ofNullable(pageImageWidthWrap.getO()).ifPresent(o -> {
        setPageImageWidth(o);
      });
    }
    return (SpineDoc)this;
  }

  public static Integer staticSearchPageImageWidth(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrPageImageWidth(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageWidth(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchPageImageWidth(siteRequest_, SpineDoc.staticSetPageImageWidth(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:pageImageHeight">Find the entity pageImageHeight in Solr</a>
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
    this.pageImageHeight = SpineDoc.staticSetPageImageHeight(siteRequest_, o);
  }
  public static Integer staticSetPageImageHeight(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected SpineDoc pageImageHeightInit() {
    Wrap<Integer> pageImageHeightWrap = new Wrap<Integer>().var("pageImageHeight");
    if(pageImageHeight == null) {
      _pageImageHeight(pageImageHeightWrap);
      Optional.ofNullable(pageImageHeightWrap.getO()).ifPresent(o -> {
        setPageImageHeight(o);
      });
    }
    return (SpineDoc)this;
  }

  public static Integer staticSearchPageImageHeight(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrPageImageHeight(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageHeight(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchPageImageHeight(siteRequest_, SpineDoc.staticSetPageImageHeight(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:pageImageType">Find the entity pageImageType in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageType(Wrap<String> c);

  public String getPageImageType() {
    return pageImageType;
  }
  public void setPageImageType(String o) {
    this.pageImageType = SpineDoc.staticSetPageImageType(siteRequest_, o);
  }
  public static String staticSetPageImageType(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc pageImageTypeInit() {
    Wrap<String> pageImageTypeWrap = new Wrap<String>().var("pageImageType");
    if(pageImageType == null) {
      _pageImageType(pageImageTypeWrap);
      Optional.ofNullable(pageImageTypeWrap.getO()).ifPresent(o -> {
        setPageImageType(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchPageImageType(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageImageType(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageType(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchPageImageType(siteRequest_, SpineDoc.staticSetPageImageType(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:pageImageAlt">Find the entity pageImageAlt in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageAlt(Wrap<String> c);

  public String getPageImageAlt() {
    return pageImageAlt;
  }
  public void setPageImageAlt(String o) {
    this.pageImageAlt = SpineDoc.staticSetPageImageAlt(siteRequest_, o);
  }
  public static String staticSetPageImageAlt(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc pageImageAltInit() {
    Wrap<String> pageImageAltWrap = new Wrap<String>().var("pageImageAlt");
    if(pageImageAlt == null) {
      _pageImageAlt(pageImageAltWrap);
      Optional.ofNullable(pageImageAltWrap.getO()).ifPresent(o -> {
        setPageImageAlt(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchPageImageAlt(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageImageAlt(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageAlt(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchPageImageAlt(siteRequest_, SpineDoc.staticSetPageImageAlt(siteRequest_, o)).toString();
  }

  public String sqlPageImageAlt() {
    return pageImageAlt;
  }

  public static String staticJsonPageImageAlt(String pageImageAlt) {
    return pageImageAlt;
  }

	////////////////////////////
  // prerequisiteArticleIds //
	////////////////////////////


  /**
   *  The entity prerequisiteArticleIds
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String prerequisiteArticleIds;

  /**
   * <br> The entity prerequisiteArticleIds
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:prerequisiteArticleIds">Find the entity prerequisiteArticleIds in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _prerequisiteArticleIds(Wrap<String> w);

  public String getPrerequisiteArticleIds() {
    return prerequisiteArticleIds;
  }
  public void setPrerequisiteArticleIds(String o) {
    this.prerequisiteArticleIds = SpineDoc.staticSetPrerequisiteArticleIds(siteRequest_, o);
  }
  public static String staticSetPrerequisiteArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc prerequisiteArticleIdsInit() {
    Wrap<String> prerequisiteArticleIdsWrap = new Wrap<String>().var("prerequisiteArticleIds");
    if(prerequisiteArticleIds == null) {
      _prerequisiteArticleIds(prerequisiteArticleIdsWrap);
      Optional.ofNullable(prerequisiteArticleIdsWrap.getO()).ifPresent(o -> {
        setPrerequisiteArticleIds(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchPrerequisiteArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPrerequisiteArticleIds(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPrerequisiteArticleIds(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchPrerequisiteArticleIds(siteRequest_, SpineDoc.staticSetPrerequisiteArticleIds(siteRequest_, o)).toString();
  }

  public String sqlPrerequisiteArticleIds() {
    return prerequisiteArticleIds;
  }

  public static String staticJsonPrerequisiteArticleIds(String prerequisiteArticleIds) {
    return prerequisiteArticleIds;
  }

	///////////////////////////////
  // prerequisiteArticleSearch //
	///////////////////////////////


  /**
   *  The entity prerequisiteArticleSearch
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<SpineDoc> prerequisiteArticleSearch;

  /**
   * <br> The entity prerequisiteArticleSearch
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:prerequisiteArticleSearch">Find the entity prerequisiteArticleSearch in Solr</a>
   * <br>
   * @param promise is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _prerequisiteArticleSearch(Promise<SearchList<SpineDoc>> promise);

  public SearchList<SpineDoc> getPrerequisiteArticleSearch() {
    return prerequisiteArticleSearch;
  }

  public void setPrerequisiteArticleSearch(SearchList<SpineDoc> prerequisiteArticleSearch) {
    this.prerequisiteArticleSearch = prerequisiteArticleSearch;
  }
  public static SearchList<SpineDoc> staticSetPrerequisiteArticleSearch(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected Future<SearchList<SpineDoc>> prerequisiteArticleSearchPromise() {
    Promise<SearchList<SpineDoc>> promise = Promise.promise();
    Promise<SearchList<SpineDoc>> promise2 = Promise.promise();
    _prerequisiteArticleSearch(promise2);
    promise2.future().onSuccess(o -> {
      if(o != null && prerequisiteArticleSearch == null) {
        o.promiseDeepForClass(siteRequest_).onSuccess(a -> {
          setPrerequisiteArticleSearch(o);
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

	//////////////////////////
  // prerequisiteArticles //
	//////////////////////////


  /**
   *  The entity prerequisiteArticles
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray prerequisiteArticles;

  /**
   * <br> The entity prerequisiteArticles
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:prerequisiteArticles">Find the entity prerequisiteArticles in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _prerequisiteArticles(Wrap<JsonArray> w);

  public JsonArray getPrerequisiteArticles() {
    return prerequisiteArticles;
  }

  public void setPrerequisiteArticles(JsonArray prerequisiteArticles) {
    this.prerequisiteArticles = prerequisiteArticles;
  }
  @JsonIgnore
  public void setPrerequisiteArticles(String o) {
    this.prerequisiteArticles = SpineDoc.staticSetPrerequisiteArticles(siteRequest_, o);
  }
  public static JsonArray staticSetPrerequisiteArticles(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected SpineDoc prerequisiteArticlesInit() {
    Wrap<JsonArray> prerequisiteArticlesWrap = new Wrap<JsonArray>().var("prerequisiteArticles");
    if(prerequisiteArticles == null) {
      _prerequisiteArticles(prerequisiteArticlesWrap);
      Optional.ofNullable(prerequisiteArticlesWrap.getO()).ifPresent(o -> {
        setPrerequisiteArticles(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchPrerequisiteArticles(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrPrerequisiteArticles(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPrerequisiteArticles(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchPrerequisiteArticles(siteRequest_, SpineDoc.staticSetPrerequisiteArticles(siteRequest_, o)).toString();
  }

	////////////////////
  // nextArticleIds //
	////////////////////


  /**
   *  The entity nextArticleIds
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String nextArticleIds;

  /**
   * <br> The entity nextArticleIds
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:nextArticleIds">Find the entity nextArticleIds in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _nextArticleIds(Wrap<String> w);

  public String getNextArticleIds() {
    return nextArticleIds;
  }
  public void setNextArticleIds(String o) {
    this.nextArticleIds = SpineDoc.staticSetNextArticleIds(siteRequest_, o);
  }
  public static String staticSetNextArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc nextArticleIdsInit() {
    Wrap<String> nextArticleIdsWrap = new Wrap<String>().var("nextArticleIds");
    if(nextArticleIds == null) {
      _nextArticleIds(nextArticleIdsWrap);
      Optional.ofNullable(nextArticleIdsWrap.getO()).ifPresent(o -> {
        setNextArticleIds(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchNextArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrNextArticleIds(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqNextArticleIds(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchNextArticleIds(siteRequest_, SpineDoc.staticSetNextArticleIds(siteRequest_, o)).toString();
  }

  public String sqlNextArticleIds() {
    return nextArticleIds;
  }

  public static String staticJsonNextArticleIds(String nextArticleIds) {
    return nextArticleIds;
  }

	///////////////////////
  // nextArticleSearch //
	///////////////////////


  /**
   *  The entity nextArticleSearch
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<SpineDoc> nextArticleSearch;

  /**
   * <br> The entity nextArticleSearch
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:nextArticleSearch">Find the entity nextArticleSearch in Solr</a>
   * <br>
   * @param promise is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _nextArticleSearch(Promise<SearchList<SpineDoc>> promise);

  public SearchList<SpineDoc> getNextArticleSearch() {
    return nextArticleSearch;
  }

  public void setNextArticleSearch(SearchList<SpineDoc> nextArticleSearch) {
    this.nextArticleSearch = nextArticleSearch;
  }
  public static SearchList<SpineDoc> staticSetNextArticleSearch(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected Future<SearchList<SpineDoc>> nextArticleSearchPromise() {
    Promise<SearchList<SpineDoc>> promise = Promise.promise();
    Promise<SearchList<SpineDoc>> promise2 = Promise.promise();
    _nextArticleSearch(promise2);
    promise2.future().onSuccess(o -> {
      if(o != null && nextArticleSearch == null) {
        o.promiseDeepForClass(siteRequest_).onSuccess(a -> {
          setNextArticleSearch(o);
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

	//////////////////
  // nextArticles //
	//////////////////


  /**
   *  The entity nextArticles
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray nextArticles;

  /**
   * <br> The entity nextArticles
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:nextArticles">Find the entity nextArticles in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _nextArticles(Wrap<JsonArray> w);

  public JsonArray getNextArticles() {
    return nextArticles;
  }

  public void setNextArticles(JsonArray nextArticles) {
    this.nextArticles = nextArticles;
  }
  @JsonIgnore
  public void setNextArticles(String o) {
    this.nextArticles = SpineDoc.staticSetNextArticles(siteRequest_, o);
  }
  public static JsonArray staticSetNextArticles(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected SpineDoc nextArticlesInit() {
    Wrap<JsonArray> nextArticlesWrap = new Wrap<JsonArray>().var("nextArticles");
    if(nextArticles == null) {
      _nextArticles(nextArticlesWrap);
      Optional.ofNullable(nextArticlesWrap.getO()).ifPresent(o -> {
        setNextArticles(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchNextArticles(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrNextArticles(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqNextArticles(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchNextArticles(siteRequest_, SpineDoc.staticSetNextArticles(siteRequest_, o)).toString();
  }

	//////////////////
  // labelsString //
	//////////////////


  /**
   *  The entity labelsString
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String labelsString;

  /**
   * <br> The entity labelsString
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:labelsString">Find the entity labelsString in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _labelsString(Wrap<String> w);

  public String getLabelsString() {
    return labelsString;
  }
  public void setLabelsString(String o) {
    this.labelsString = SpineDoc.staticSetLabelsString(siteRequest_, o);
  }
  public static String staticSetLabelsString(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc labelsStringInit() {
    Wrap<String> labelsStringWrap = new Wrap<String>().var("labelsString");
    if(labelsString == null) {
      _labelsString(labelsStringWrap);
      Optional.ofNullable(labelsStringWrap.getO()).ifPresent(o -> {
        setLabelsString(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchLabelsString(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrLabelsString(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqLabelsString(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchLabelsString(siteRequest_, SpineDoc.staticSetLabelsString(siteRequest_, o)).toString();
  }

  public String sqlLabelsString() {
    return labelsString;
  }

  public static String staticJsonLabelsString(String labelsString) {
    return labelsString;
  }

	////////////
  // labels //
	////////////


  /**
   *  The entity labels
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> labels = new ArrayList<String>();

  /**
   * <br> The entity labels
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:labels">Find the entity labels in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _labels(List<String> l);

  public List<String> getLabels() {
    return labels;
  }

  public void setLabels(List<String> labels) {
    this.labels = labels;
  }
  @JsonIgnore
  public void setLabels(String o) {
    String l = SpineDoc.staticSetLabels(siteRequest_, o);
    if(l != null)
      addLabels(l);
  }
  public static String staticSetLabels(SiteRequest siteRequest_, String o) {
    return o;
  }
  public SpineDoc addLabels(String...objects) {
    for(String o : objects) {
      addLabels(o);
    }
    return (SpineDoc)this;
  }
  public SpineDoc addLabels(String o) {
    if(o != null)
      this.labels.add(o);
    return (SpineDoc)this;
  }
  @JsonIgnore
  public void setLabels(JsonArray objects) {
    labels.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addLabels(o);
    }
  }
  protected SpineDoc labelsInit() {
    _labels(labels);
    return (SpineDoc)this;
  }

  public static String staticSearchLabels(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrLabels(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqLabels(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchLabels(siteRequest_, SpineDoc.staticSetLabels(siteRequest_, o)).toString();
  }

  public String[] sqlLabels() {
    return labels.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonLabels(List<String> labels) {
    JsonArray a = new JsonArray();
    labels.stream().forEach(v -> a.add(v.toString()));
    return a;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:relatedArticleIds">Find the entity relatedArticleIds in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _relatedArticleIds(Wrap<String> w);

  public String getRelatedArticleIds() {
    return relatedArticleIds;
  }
  public void setRelatedArticleIds(String o) {
    this.relatedArticleIds = SpineDoc.staticSetRelatedArticleIds(siteRequest_, o);
  }
  public static String staticSetRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected SpineDoc relatedArticleIdsInit() {
    Wrap<String> relatedArticleIdsWrap = new Wrap<String>().var("relatedArticleIds");
    if(relatedArticleIds == null) {
      _relatedArticleIds(relatedArticleIdsWrap);
      Optional.ofNullable(relatedArticleIdsWrap.getO()).ifPresent(o -> {
        setRelatedArticleIds(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchRelatedArticleIds(siteRequest_, SpineDoc.staticSetRelatedArticleIds(siteRequest_, o)).toString();
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
  protected SearchList<SpineDoc> relatedArticleSearch;

  /**
   * <br> The entity relatedArticleSearch
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:relatedArticleSearch">Find the entity relatedArticleSearch in Solr</a>
   * <br>
   * @param promise is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _relatedArticleSearch(Promise<SearchList<SpineDoc>> promise);

  public SearchList<SpineDoc> getRelatedArticleSearch() {
    return relatedArticleSearch;
  }

  public void setRelatedArticleSearch(SearchList<SpineDoc> relatedArticleSearch) {
    this.relatedArticleSearch = relatedArticleSearch;
  }
  public static SearchList<SpineDoc> staticSetRelatedArticleSearch(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected Future<SearchList<SpineDoc>> relatedArticleSearchPromise() {
    Promise<SearchList<SpineDoc>> promise = Promise.promise();
    Promise<SearchList<SpineDoc>> promise2 = Promise.promise();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.spinedoc.SpineDoc&fq=entiteVar_enUS_indexed_string:relatedArticles">Find the entity relatedArticles in Solr</a>
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
    this.relatedArticles = SpineDoc.staticSetRelatedArticles(siteRequest_, o);
  }
  public static JsonArray staticSetRelatedArticles(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected SpineDoc relatedArticlesInit() {
    Wrap<JsonArray> relatedArticlesWrap = new Wrap<JsonArray>().var("relatedArticles");
    if(relatedArticles == null) {
      _relatedArticles(relatedArticlesWrap);
      Optional.ofNullable(relatedArticlesWrap.getO()).ifPresent(o -> {
        setRelatedArticles(o);
      });
    }
    return (SpineDoc)this;
  }

  public static String staticSearchRelatedArticles(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrRelatedArticles(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRelatedArticles(SiteRequest siteRequest_, String o) {
    return SpineDoc.staticSearchRelatedArticles(siteRequest_, SpineDoc.staticSetRelatedArticles(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<SpineDocGen<DEV>> promiseDeepSpineDoc(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepSpineDoc();
  }

  public Future<SpineDocGen<DEV>> promiseDeepSpineDoc() {
    Promise<SpineDocGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseSpineDoc(promise2);
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

  public Future<Void> promiseSpineDoc(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        articleInit();
        importanceInit();
        commentVarInit();
        javaTypeInit();
        javaClassInit();
        sinceVersionInit();
        supportStatusInit();
        exampleValueInit();
        exampleCodeInit();
        copyCodeInit();
        courseNumInit();
        lessonNumInit();
        nameInit();
        descriptionInit();
        authorNameInit();
        authorUrlInit();
        pageIdInit();
        pageVideoUrlInit();
        pageImageUriInit();
        pageImageWidthInit();
        pageImageHeightInit();
        pageImageTypeInit();
        pageImageAltInit();
        prerequisiteArticleIdsInit();
        promise2.complete();
      } catch(Exception ex) {
        promise2.fail(ex);
      }
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      prerequisiteArticleSearchPromise().onSuccess(prerequisiteArticleSearch -> {
        promise2.complete();
      }).onFailure(ex -> {
        promise2.fail(ex);
      });
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        prerequisiteArticlesInit();
        nextArticleIdsInit();
        promise2.complete();
      } catch(Exception ex) {
        promise2.fail(ex);
      }
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      nextArticleSearchPromise().onSuccess(nextArticleSearch -> {
        promise2.complete();
      }).onFailure(ex -> {
        promise2.fail(ex);
      });
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        nextArticlesInit();
        labelsStringInit();
        labelsInit();
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

  @Override public Future<? extends SpineDocGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepSpineDoc(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestSpineDoc(SiteRequest siteRequest_) {
      super.siteRequestBaseResult(siteRequest_);
    if(prerequisiteArticleSearch != null)
      prerequisiteArticleSearch.setSiteRequest_(siteRequest_);
    if(nextArticleSearch != null)
      nextArticleSearch.setSiteRequest_(siteRequest_);
    if(relatedArticleSearch != null)
      relatedArticleSearch.setSiteRequest_(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestSpineDoc(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainSpineDoc(v);
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
  public Object obtainSpineDoc(String var) {
    SpineDoc oSpineDoc = (SpineDoc)this;
    switch(var) {
      case "article":
        return oSpineDoc.article;
      case "importance":
        return oSpineDoc.importance;
      case "commentVar":
        return oSpineDoc.commentVar;
      case "javaType":
        return oSpineDoc.javaType;
      case "javaClass":
        return oSpineDoc.javaClass;
      case "sinceVersion":
        return oSpineDoc.sinceVersion;
      case "supportStatus":
        return oSpineDoc.supportStatus;
      case "exampleValue":
        return oSpineDoc.exampleValue;
      case "exampleCode":
        return oSpineDoc.exampleCode;
      case "copyCode":
        return oSpineDoc.copyCode;
      case "courseNum":
        return oSpineDoc.courseNum;
      case "lessonNum":
        return oSpineDoc.lessonNum;
      case "name":
        return oSpineDoc.name;
      case "description":
        return oSpineDoc.description;
      case "authorName":
        return oSpineDoc.authorName;
      case "authorUrl":
        return oSpineDoc.authorUrl;
      case "pageId":
        return oSpineDoc.pageId;
      case "pageVideoUrl":
        return oSpineDoc.pageVideoUrl;
      case "pageImageUri":
        return oSpineDoc.pageImageUri;
      case "pageImageWidth":
        return oSpineDoc.pageImageWidth;
      case "pageImageHeight":
        return oSpineDoc.pageImageHeight;
      case "pageImageType":
        return oSpineDoc.pageImageType;
      case "pageImageAlt":
        return oSpineDoc.pageImageAlt;
      case "prerequisiteArticleIds":
        return oSpineDoc.prerequisiteArticleIds;
      case "prerequisiteArticleSearch":
        return oSpineDoc.prerequisiteArticleSearch;
      case "prerequisiteArticles":
        return oSpineDoc.prerequisiteArticles;
      case "nextArticleIds":
        return oSpineDoc.nextArticleIds;
      case "nextArticleSearch":
        return oSpineDoc.nextArticleSearch;
      case "nextArticles":
        return oSpineDoc.nextArticles;
      case "labelsString":
        return oSpineDoc.labelsString;
      case "labels":
        return oSpineDoc.labels;
      case "relatedArticleIds":
        return oSpineDoc.relatedArticleIds;
      case "relatedArticleSearch":
        return oSpineDoc.relatedArticleSearch;
      case "relatedArticles":
        return oSpineDoc.relatedArticles;
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
        o = relateSpineDoc(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateSpineDoc(String var, Object val) {
    SpineDoc oSpineDoc = (SpineDoc)this;
    switch(var) {
      default:
        return super.relateBaseResult(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, SpineDoc o) {
    return staticSetSpineDoc(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetSpineDoc(String entityVar, SiteRequest siteRequest_, String v, SpineDoc o) {
    switch(entityVar) {
    case "article":
      return SpineDoc.staticSetArticle(siteRequest_, v);
    case "importance":
      return SpineDoc.staticSetImportance(siteRequest_, v);
    case "commentVar":
      return SpineDoc.staticSetCommentVar(siteRequest_, v);
    case "javaType":
      return SpineDoc.staticSetJavaType(siteRequest_, v);
    case "javaClass":
      return SpineDoc.staticSetJavaClass(siteRequest_, v);
    case "sinceVersion":
      return SpineDoc.staticSetSinceVersion(siteRequest_, v);
    case "supportStatus":
      return SpineDoc.staticSetSupportStatus(siteRequest_, v);
    case "exampleValue":
      return SpineDoc.staticSetExampleValue(siteRequest_, v);
    case "exampleCode":
      return SpineDoc.staticSetExampleCode(siteRequest_, v);
    case "copyCode":
      return SpineDoc.staticSetCopyCode(siteRequest_, v);
    case "courseNum":
      return SpineDoc.staticSetCourseNum(siteRequest_, v);
    case "lessonNum":
      return SpineDoc.staticSetLessonNum(siteRequest_, v);
    case "name":
      return SpineDoc.staticSetName(siteRequest_, v);
    case "description":
      return SpineDoc.staticSetDescription(siteRequest_, v);
    case "authorName":
      return SpineDoc.staticSetAuthorName(siteRequest_, v);
    case "authorUrl":
      return SpineDoc.staticSetAuthorUrl(siteRequest_, v);
    case "pageId":
      return SpineDoc.staticSetPageId(siteRequest_, v);
    case "pageVideoUrl":
      return SpineDoc.staticSetPageVideoUrl(siteRequest_, v);
    case "pageImageUri":
      return SpineDoc.staticSetPageImageUri(siteRequest_, v);
    case "pageImageWidth":
      return SpineDoc.staticSetPageImageWidth(siteRequest_, v);
    case "pageImageHeight":
      return SpineDoc.staticSetPageImageHeight(siteRequest_, v);
    case "pageImageType":
      return SpineDoc.staticSetPageImageType(siteRequest_, v);
    case "pageImageAlt":
      return SpineDoc.staticSetPageImageAlt(siteRequest_, v);
    case "prerequisiteArticleIds":
      return SpineDoc.staticSetPrerequisiteArticleIds(siteRequest_, v);
    case "prerequisiteArticles":
      return SpineDoc.staticSetPrerequisiteArticles(siteRequest_, v);
    case "nextArticleIds":
      return SpineDoc.staticSetNextArticleIds(siteRequest_, v);
    case "nextArticles":
      return SpineDoc.staticSetNextArticles(siteRequest_, v);
    case "labelsString":
      return SpineDoc.staticSetLabelsString(siteRequest_, v);
    case "labels":
      return SpineDoc.staticSetLabels(siteRequest_, v);
    case "relatedArticleIds":
      return SpineDoc.staticSetRelatedArticleIds(siteRequest_, v);
    case "relatedArticles":
      return SpineDoc.staticSetRelatedArticles(siteRequest_, v);
      default:
        return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<SpineDoc> fqSpineDoc(SiteRequest siteRequest, String var, Object val) {
    Promise<SpineDoc> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<SpineDoc> searchList = new SearchList<SpineDoc>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(SpineDoc.class);
        searchList.fq(String.format("%s:", SpineDoc.varIndexedSpineDoc(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the SPINE doc", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the SPINE doc", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the SPINE doc", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchSpineDoc(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchSpineDoc(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "article":
      return SpineDoc.staticSearchArticle(siteRequest_, (Boolean)o);
    case "importance":
      return SpineDoc.staticSearchImportance(siteRequest_, (BigDecimal)o);
    case "commentVar":
      return SpineDoc.staticSearchCommentVar(siteRequest_, (String)o);
    case "javaType":
      return SpineDoc.staticSearchJavaType(siteRequest_, (String)o);
    case "javaClass":
      return SpineDoc.staticSearchJavaClass(siteRequest_, (String)o);
    case "sinceVersion":
      return SpineDoc.staticSearchSinceVersion(siteRequest_, (String)o);
    case "supportStatus":
      return SpineDoc.staticSearchSupportStatus(siteRequest_, (String)o);
    case "exampleValue":
      return SpineDoc.staticSearchExampleValue(siteRequest_, (String)o);
    case "exampleCode":
      return SpineDoc.staticSearchExampleCode(siteRequest_, (String)o);
    case "copyCode":
      return SpineDoc.staticSearchCopyCode(siteRequest_, (String)o);
    case "courseNum":
      return SpineDoc.staticSearchCourseNum(siteRequest_, (Integer)o);
    case "lessonNum":
      return SpineDoc.staticSearchLessonNum(siteRequest_, (Integer)o);
    case "name":
      return SpineDoc.staticSearchName(siteRequest_, (String)o);
    case "description":
      return SpineDoc.staticSearchDescription(siteRequest_, (String)o);
    case "authorName":
      return SpineDoc.staticSearchAuthorName(siteRequest_, (String)o);
    case "authorUrl":
      return SpineDoc.staticSearchAuthorUrl(siteRequest_, (String)o);
    case "pageId":
      return SpineDoc.staticSearchPageId(siteRequest_, (String)o);
    case "pageVideoUrl":
      return SpineDoc.staticSearchPageVideoUrl(siteRequest_, (String)o);
    case "pageImageUri":
      return SpineDoc.staticSearchPageImageUri(siteRequest_, (String)o);
    case "pageImageWidth":
      return SpineDoc.staticSearchPageImageWidth(siteRequest_, (Integer)o);
    case "pageImageHeight":
      return SpineDoc.staticSearchPageImageHeight(siteRequest_, (Integer)o);
    case "pageImageType":
      return SpineDoc.staticSearchPageImageType(siteRequest_, (String)o);
    case "pageImageAlt":
      return SpineDoc.staticSearchPageImageAlt(siteRequest_, (String)o);
    case "prerequisiteArticleIds":
      return SpineDoc.staticSearchPrerequisiteArticleIds(siteRequest_, (String)o);
    case "prerequisiteArticles":
      return SpineDoc.staticSearchPrerequisiteArticles(siteRequest_, (JsonArray)o);
    case "nextArticleIds":
      return SpineDoc.staticSearchNextArticleIds(siteRequest_, (String)o);
    case "nextArticles":
      return SpineDoc.staticSearchNextArticles(siteRequest_, (JsonArray)o);
    case "labelsString":
      return SpineDoc.staticSearchLabelsString(siteRequest_, (String)o);
    case "labels":
      return SpineDoc.staticSearchLabels(siteRequest_, (String)o);
    case "relatedArticleIds":
      return SpineDoc.staticSearchRelatedArticleIds(siteRequest_, (String)o);
    case "relatedArticles":
      return SpineDoc.staticSearchRelatedArticles(siteRequest_, (JsonArray)o);
      default:
        return BaseResult.staticSearchBaseResult(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrSpineDoc(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrSpineDoc(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "article":
      return SpineDoc.staticSearchStrArticle(siteRequest_, (Boolean)o);
    case "importance":
      return SpineDoc.staticSearchStrImportance(siteRequest_, (String)o);
    case "commentVar":
      return SpineDoc.staticSearchStrCommentVar(siteRequest_, (String)o);
    case "javaType":
      return SpineDoc.staticSearchStrJavaType(siteRequest_, (String)o);
    case "javaClass":
      return SpineDoc.staticSearchStrJavaClass(siteRequest_, (String)o);
    case "sinceVersion":
      return SpineDoc.staticSearchStrSinceVersion(siteRequest_, (String)o);
    case "supportStatus":
      return SpineDoc.staticSearchStrSupportStatus(siteRequest_, (String)o);
    case "exampleValue":
      return SpineDoc.staticSearchStrExampleValue(siteRequest_, (String)o);
    case "exampleCode":
      return SpineDoc.staticSearchStrExampleCode(siteRequest_, (String)o);
    case "copyCode":
      return SpineDoc.staticSearchStrCopyCode(siteRequest_, (String)o);
    case "courseNum":
      return SpineDoc.staticSearchStrCourseNum(siteRequest_, (Integer)o);
    case "lessonNum":
      return SpineDoc.staticSearchStrLessonNum(siteRequest_, (Integer)o);
    case "name":
      return SpineDoc.staticSearchStrName(siteRequest_, (String)o);
    case "description":
      return SpineDoc.staticSearchStrDescription(siteRequest_, (String)o);
    case "authorName":
      return SpineDoc.staticSearchStrAuthorName(siteRequest_, (String)o);
    case "authorUrl":
      return SpineDoc.staticSearchStrAuthorUrl(siteRequest_, (String)o);
    case "pageId":
      return SpineDoc.staticSearchStrPageId(siteRequest_, (String)o);
    case "pageVideoUrl":
      return SpineDoc.staticSearchStrPageVideoUrl(siteRequest_, (String)o);
    case "pageImageUri":
      return SpineDoc.staticSearchStrPageImageUri(siteRequest_, (String)o);
    case "pageImageWidth":
      return SpineDoc.staticSearchStrPageImageWidth(siteRequest_, (Integer)o);
    case "pageImageHeight":
      return SpineDoc.staticSearchStrPageImageHeight(siteRequest_, (Integer)o);
    case "pageImageType":
      return SpineDoc.staticSearchStrPageImageType(siteRequest_, (String)o);
    case "pageImageAlt":
      return SpineDoc.staticSearchStrPageImageAlt(siteRequest_, (String)o);
    case "prerequisiteArticleIds":
      return SpineDoc.staticSearchStrPrerequisiteArticleIds(siteRequest_, (String)o);
    case "prerequisiteArticles":
      return SpineDoc.staticSearchStrPrerequisiteArticles(siteRequest_, (String)o);
    case "nextArticleIds":
      return SpineDoc.staticSearchStrNextArticleIds(siteRequest_, (String)o);
    case "nextArticles":
      return SpineDoc.staticSearchStrNextArticles(siteRequest_, (String)o);
    case "labelsString":
      return SpineDoc.staticSearchStrLabelsString(siteRequest_, (String)o);
    case "labels":
      return SpineDoc.staticSearchStrLabels(siteRequest_, (String)o);
    case "relatedArticleIds":
      return SpineDoc.staticSearchStrRelatedArticleIds(siteRequest_, (String)o);
    case "relatedArticles":
      return SpineDoc.staticSearchStrRelatedArticles(siteRequest_, (String)o);
      default:
        return BaseResult.staticSearchStrBaseResult(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqSpineDoc(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqSpineDoc(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "article":
      return SpineDoc.staticSearchFqArticle(siteRequest_, o);
    case "importance":
      return SpineDoc.staticSearchFqImportance(siteRequest_, o);
    case "commentVar":
      return SpineDoc.staticSearchFqCommentVar(siteRequest_, o);
    case "javaType":
      return SpineDoc.staticSearchFqJavaType(siteRequest_, o);
    case "javaClass":
      return SpineDoc.staticSearchFqJavaClass(siteRequest_, o);
    case "sinceVersion":
      return SpineDoc.staticSearchFqSinceVersion(siteRequest_, o);
    case "supportStatus":
      return SpineDoc.staticSearchFqSupportStatus(siteRequest_, o);
    case "exampleValue":
      return SpineDoc.staticSearchFqExampleValue(siteRequest_, o);
    case "exampleCode":
      return SpineDoc.staticSearchFqExampleCode(siteRequest_, o);
    case "copyCode":
      return SpineDoc.staticSearchFqCopyCode(siteRequest_, o);
    case "courseNum":
      return SpineDoc.staticSearchFqCourseNum(siteRequest_, o);
    case "lessonNum":
      return SpineDoc.staticSearchFqLessonNum(siteRequest_, o);
    case "name":
      return SpineDoc.staticSearchFqName(siteRequest_, o);
    case "description":
      return SpineDoc.staticSearchFqDescription(siteRequest_, o);
    case "authorName":
      return SpineDoc.staticSearchFqAuthorName(siteRequest_, o);
    case "authorUrl":
      return SpineDoc.staticSearchFqAuthorUrl(siteRequest_, o);
    case "pageId":
      return SpineDoc.staticSearchFqPageId(siteRequest_, o);
    case "pageVideoUrl":
      return SpineDoc.staticSearchFqPageVideoUrl(siteRequest_, o);
    case "pageImageUri":
      return SpineDoc.staticSearchFqPageImageUri(siteRequest_, o);
    case "pageImageWidth":
      return SpineDoc.staticSearchFqPageImageWidth(siteRequest_, o);
    case "pageImageHeight":
      return SpineDoc.staticSearchFqPageImageHeight(siteRequest_, o);
    case "pageImageType":
      return SpineDoc.staticSearchFqPageImageType(siteRequest_, o);
    case "pageImageAlt":
      return SpineDoc.staticSearchFqPageImageAlt(siteRequest_, o);
    case "prerequisiteArticleIds":
      return SpineDoc.staticSearchFqPrerequisiteArticleIds(siteRequest_, o);
    case "prerequisiteArticles":
      return SpineDoc.staticSearchFqPrerequisiteArticles(siteRequest_, o);
    case "nextArticleIds":
      return SpineDoc.staticSearchFqNextArticleIds(siteRequest_, o);
    case "nextArticles":
      return SpineDoc.staticSearchFqNextArticles(siteRequest_, o);
    case "labelsString":
      return SpineDoc.staticSearchFqLabelsString(siteRequest_, o);
    case "labels":
      return SpineDoc.staticSearchFqLabels(siteRequest_, o);
    case "relatedArticleIds":
      return SpineDoc.staticSearchFqRelatedArticleIds(siteRequest_, o);
    case "relatedArticles":
      return SpineDoc.staticSearchFqRelatedArticles(siteRequest_, o);
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
          o = persistSpineDoc(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistSpineDoc(String var, Object val) {
    String varLower = var.toLowerCase();
      if("importance".equals(varLower)) {
        if(val instanceof String) {
          setImportance((String)val);
        } else if(val instanceof Number) {
          setImportance(new BigDecimal(((Number)val).doubleValue()));
        } else if(val instanceof BigDecimal) {
          setImportance((BigDecimal)val);
        }
        saves.add("importance");
        return val;
      } else if("commentvar".equals(varLower)) {
        if(val instanceof String) {
          setCommentVar((String)val);
        }
        saves.add("commentVar");
        return val;
      } else if("javatype".equals(varLower)) {
        if(val instanceof String) {
          setJavaType((String)val);
        }
        saves.add("javaType");
        return val;
      } else if("javaclass".equals(varLower)) {
        if(val instanceof String) {
          setJavaClass((String)val);
        }
        saves.add("javaClass");
        return val;
      } else if("sinceversion".equals(varLower)) {
        if(val instanceof String) {
          setSinceVersion((String)val);
        }
        saves.add("sinceVersion");
        return val;
      } else if("supportstatus".equals(varLower)) {
        if(val instanceof String) {
          setSupportStatus((String)val);
        }
        saves.add("supportStatus");
        return val;
      } else if("examplevalue".equals(varLower)) {
        if(val instanceof String) {
          setExampleValue((String)val);
        }
        saves.add("exampleValue");
        return val;
      } else if("examplecode".equals(varLower)) {
        if(val instanceof String) {
          setExampleCode((String)val);
        }
        saves.add("exampleCode");
        return val;
      } else if("copycode".equals(varLower)) {
        if(val instanceof String) {
          setCopyCode((String)val);
        }
        saves.add("copyCode");
        return val;
      } else if("coursenum".equals(varLower)) {
        if(val instanceof Integer) {
          setCourseNum((Integer)val);
        } else {
          setCourseNum(val == null ? null : val.toString());
        }
        saves.add("courseNum");
        return val;
      } else if("lessonnum".equals(varLower)) {
        if(val instanceof Integer) {
          setLessonNum((Integer)val);
        } else {
          setLessonNum(val == null ? null : val.toString());
        }
        saves.add("lessonNum");
        return val;
      } else if("name".equals(varLower)) {
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
      } else if("authorname".equals(varLower)) {
        if(val instanceof String) {
          setAuthorName((String)val);
        }
        saves.add("authorName");
        return val;
      } else if("authorurl".equals(varLower)) {
        if(val instanceof String) {
          setAuthorUrl((String)val);
        }
        saves.add("authorUrl");
        return val;
      } else if("pageid".equals(varLower)) {
        if(val instanceof String) {
          setPageId((String)val);
        }
        saves.add("pageId");
        return val;
      } else if("pagevideourl".equals(varLower)) {
        if(val instanceof String) {
          setPageVideoUrl((String)val);
        }
        saves.add("pageVideoUrl");
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
      } else if("prerequisitearticleids".equals(varLower)) {
        if(val instanceof String) {
          setPrerequisiteArticleIds((String)val);
        }
        saves.add("prerequisiteArticleIds");
        return val;
      } else if("nextarticleids".equals(varLower)) {
        if(val instanceof String) {
          setNextArticleIds((String)val);
        }
        saves.add("nextArticleIds");
        return val;
      } else if("labelsstring".equals(varLower)) {
        if(val instanceof String) {
          setLabelsString((String)val);
        }
        saves.add("labelsString");
        return val;
      } else if("labels".equals(varLower)) {
        if(val instanceof List<?>) {
          ((List<String>)val).stream().forEach(v -> addLabels(v));
        } else if(val instanceof String[]) {
          Arrays.asList((String[])val).stream().forEach(v -> addLabels((String)v));
        } else if(val instanceof JsonArray) {
          ((JsonArray)val).stream().forEach(v -> addLabels(staticSetLabels(siteRequest_, v.toString())));
        }
        if(!saves.contains("labels")) {
          saves.add("labels");
        }
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
    populateSpineDoc(doc);
  }
  public void populateSpineDoc(SolrResponse.Doc doc) {
    SpineDoc oSpineDoc = (SpineDoc)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("importance")) {
        String importance = (String)doc.get("importance_docvalues_string");
        if(importance != null)
          oSpineDoc.setImportance(importance);
      }

      if(saves.contains("commentVar")) {
        String commentVar = (String)doc.get("commentVar_docvalues_string");
        if(commentVar != null)
          oSpineDoc.setCommentVar(commentVar);
      }

      if(saves.contains("javaType")) {
        String javaType = (String)doc.get("javaType_docvalues_string");
        if(javaType != null)
          oSpineDoc.setJavaType(javaType);
      }

      if(saves.contains("javaClass")) {
        String javaClass = (String)doc.get("javaClass_docvalues_string");
        if(javaClass != null)
          oSpineDoc.setJavaClass(javaClass);
      }

      if(saves.contains("sinceVersion")) {
        String sinceVersion = (String)doc.get("sinceVersion_docvalues_string");
        if(sinceVersion != null)
          oSpineDoc.setSinceVersion(sinceVersion);
      }

      if(saves.contains("supportStatus")) {
        String supportStatus = (String)doc.get("supportStatus_docvalues_string");
        if(supportStatus != null)
          oSpineDoc.setSupportStatus(supportStatus);
      }

      if(saves.contains("exampleValue")) {
        String exampleValue = (String)doc.get("exampleValue_docvalues_string");
        if(exampleValue != null)
          oSpineDoc.setExampleValue(exampleValue);
      }

      if(saves.contains("exampleCode")) {
        String exampleCode = (String)doc.get("exampleCode_docvalues_string");
        if(exampleCode != null)
          oSpineDoc.setExampleCode(exampleCode);
      }

      if(saves.contains("copyCode")) {
        String copyCode = (String)doc.get("copyCode_docvalues_string");
        if(copyCode != null)
          oSpineDoc.setCopyCode(copyCode);
      }

      if(saves.contains("courseNum")) {
        Integer courseNum = (Integer)doc.get("courseNum_docvalues_int");
        if(courseNum != null)
          oSpineDoc.setCourseNum(courseNum);
      }

      if(saves.contains("lessonNum")) {
        Integer lessonNum = (Integer)doc.get("lessonNum_docvalues_int");
        if(lessonNum != null)
          oSpineDoc.setLessonNum(lessonNum);
      }

      if(saves.contains("name")) {
        String name = (String)doc.get("name_docvalues_string");
        if(name != null)
          oSpineDoc.setName(name);
      }

      if(saves.contains("description")) {
        String description = (String)doc.get("description_docvalues_string");
        if(description != null)
          oSpineDoc.setDescription(description);
      }

      if(saves.contains("authorName")) {
        String authorName = (String)doc.get("authorName_docvalues_string");
        if(authorName != null)
          oSpineDoc.setAuthorName(authorName);
      }

      if(saves.contains("authorUrl")) {
        String authorUrl = (String)doc.get("authorUrl_docvalues_string");
        if(authorUrl != null)
          oSpineDoc.setAuthorUrl(authorUrl);
      }

      if(saves.contains("pageId")) {
        String pageId = (String)doc.get("pageId_docvalues_string");
        if(pageId != null)
          oSpineDoc.setPageId(pageId);
      }

      if(saves.contains("pageVideoUrl")) {
        String pageVideoUrl = (String)doc.get("pageVideoUrl_docvalues_string");
        if(pageVideoUrl != null)
          oSpineDoc.setPageVideoUrl(pageVideoUrl);
      }

      if(saves.contains("pageImageUri")) {
        String pageImageUri = (String)doc.get("pageImageUri_docvalues_string");
        if(pageImageUri != null)
          oSpineDoc.setPageImageUri(pageImageUri);
      }

      if(saves.contains("pageImageWidth")) {
        Integer pageImageWidth = (Integer)doc.get("pageImageWidth_docvalues_int");
        if(pageImageWidth != null)
          oSpineDoc.setPageImageWidth(pageImageWidth);
      }

      if(saves.contains("pageImageHeight")) {
        Integer pageImageHeight = (Integer)doc.get("pageImageHeight_docvalues_int");
        if(pageImageHeight != null)
          oSpineDoc.setPageImageHeight(pageImageHeight);
      }

      if(saves.contains("pageImageType")) {
        String pageImageType = (String)doc.get("pageImageType_docvalues_string");
        if(pageImageType != null)
          oSpineDoc.setPageImageType(pageImageType);
      }

      if(saves.contains("pageImageAlt")) {
        String pageImageAlt = (String)doc.get("pageImageAlt_docvalues_string");
        if(pageImageAlt != null)
          oSpineDoc.setPageImageAlt(pageImageAlt);
      }

      if(saves.contains("prerequisiteArticleIds")) {
        String prerequisiteArticleIds = (String)doc.get("prerequisiteArticleIds_docvalues_string");
        if(prerequisiteArticleIds != null)
          oSpineDoc.setPrerequisiteArticleIds(prerequisiteArticleIds);
      }

      if(saves.contains("prerequisiteArticles")) {
        String prerequisiteArticles = (String)doc.get("prerequisiteArticles_stored_string");
        if(prerequisiteArticles != null)
          oSpineDoc.setPrerequisiteArticles(prerequisiteArticles);
      }

      if(saves.contains("nextArticleIds")) {
        String nextArticleIds = (String)doc.get("nextArticleIds_docvalues_string");
        if(nextArticleIds != null)
          oSpineDoc.setNextArticleIds(nextArticleIds);
      }

      if(saves.contains("nextArticles")) {
        String nextArticles = (String)doc.get("nextArticles_stored_string");
        if(nextArticles != null)
          oSpineDoc.setNextArticles(nextArticles);
      }

      if(saves.contains("labelsString")) {
        String labelsString = (String)doc.get("labelsString_docvalues_string");
        if(labelsString != null)
          oSpineDoc.setLabelsString(labelsString);
      }

      if(saves.contains("labels")) {
        List<String> labels = (List<String>)doc.get("labels_docvalues_strings");
        if(labels != null) {
          labels.stream().forEach( v -> {
            oSpineDoc.labels.add(SpineDoc.staticSetLabels(siteRequest_, v));
          });
        }
      }

      if(saves.contains("relatedArticleIds")) {
        String relatedArticleIds = (String)doc.get("relatedArticleIds_docvalues_string");
        if(relatedArticleIds != null)
          oSpineDoc.setRelatedArticleIds(relatedArticleIds);
      }

      if(saves.contains("relatedArticles")) {
        String relatedArticles = (String)doc.get("relatedArticles_stored_string");
        if(relatedArticles != null)
          oSpineDoc.setRelatedArticles(relatedArticles);
      }
    }

    super.populateBaseResult(doc);
  }

  public void indexSpineDoc(JsonObject doc) {
    if(importance != null) {
      doc.put("importance_docvalues_string", importance.toPlainString());
      doc.put("importance_docvalues_double", importance.doubleValue());
    }
    if(commentVar != null) {
      doc.put("commentVar_docvalues_string", commentVar);
    }
    if(javaType != null) {
      doc.put("javaType_docvalues_string", javaType);
    }
    if(javaClass != null) {
      doc.put("javaClass_docvalues_string", javaClass);
    }
    if(sinceVersion != null) {
      doc.put("sinceVersion_docvalues_string", sinceVersion);
    }
    if(supportStatus != null) {
      doc.put("supportStatus_docvalues_string", supportStatus);
    }
    if(exampleValue != null) {
      doc.put("exampleValue_docvalues_string", exampleValue);
    }
    if(exampleCode != null) {
      doc.put("exampleCode_docvalues_string", exampleCode);
    }
    if(copyCode != null) {
      doc.put("copyCode_docvalues_string", copyCode);
    }
    if(courseNum != null) {
      doc.put("courseNum_docvalues_int", courseNum);
    }
    if(lessonNum != null) {
      doc.put("lessonNum_docvalues_int", lessonNum);
    }
    if(name != null) {
      doc.put("name_docvalues_string", name);
    }
    if(description != null) {
      doc.put("description_docvalues_string", description);
    }
    if(authorName != null) {
      doc.put("authorName_docvalues_string", authorName);
    }
    if(authorUrl != null) {
      doc.put("authorUrl_docvalues_string", authorUrl);
    }
    if(pageId != null) {
      doc.put("pageId_docvalues_string", pageId);
    }
    if(pageVideoUrl != null) {
      doc.put("pageVideoUrl_docvalues_string", pageVideoUrl);
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
    if(prerequisiteArticleIds != null) {
      doc.put("prerequisiteArticleIds_docvalues_string", prerequisiteArticleIds);
    }
    if(prerequisiteArticles != null) {
      doc.put("prerequisiteArticles_stored_string", prerequisiteArticles.toString());
    }
    if(nextArticleIds != null) {
      doc.put("nextArticleIds_docvalues_string", nextArticleIds);
    }
    if(nextArticles != null) {
      doc.put("nextArticles_stored_string", nextArticles.toString());
    }
    if(labelsString != null) {
      doc.put("labelsString_docvalues_string", labelsString);
    }
    if(labels != null) {
      JsonArray l = new JsonArray();
      doc.put("labels_docvalues_strings", l);
      for(String o : labels) {
        l.add(SpineDoc.staticSearchLabels(siteRequest_, o));
      }
    }
    if(relatedArticleIds != null) {
      doc.put("relatedArticleIds_docvalues_string", relatedArticleIds);
    }
    if(relatedArticles != null) {
      doc.put("relatedArticles_stored_string", relatedArticles.toString());
    }
    super.indexBaseResult(doc);

	}

  public static String varStoredSpineDoc(String entityVar) {
    switch(entityVar) {
      case "importance":
        return "importance_docvalues_string";
      case "commentVar":
        return "commentVar_docvalues_string";
      case "javaType":
        return "javaType_docvalues_string";
      case "javaClass":
        return "javaClass_docvalues_string";
      case "sinceVersion":
        return "sinceVersion_docvalues_string";
      case "supportStatus":
        return "supportStatus_docvalues_string";
      case "exampleValue":
        return "exampleValue_docvalues_string";
      case "exampleCode":
        return "exampleCode_docvalues_string";
      case "copyCode":
        return "copyCode_docvalues_string";
      case "courseNum":
        return "courseNum_docvalues_int";
      case "lessonNum":
        return "lessonNum_docvalues_int";
      case "name":
        return "name_docvalues_string";
      case "description":
        return "description_docvalues_string";
      case "authorName":
        return "authorName_docvalues_string";
      case "authorUrl":
        return "authorUrl_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      case "pageVideoUrl":
        return "pageVideoUrl_docvalues_string";
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
      case "prerequisiteArticleIds":
        return "prerequisiteArticleIds_docvalues_string";
      case "prerequisiteArticles":
        return "prerequisiteArticles_stored_string";
      case "nextArticleIds":
        return "nextArticleIds_docvalues_string";
      case "nextArticles":
        return "nextArticles_stored_string";
      case "labelsString":
        return "labelsString_docvalues_string";
      case "labels":
        return "labels_docvalues_strings";
      case "relatedArticleIds":
        return "relatedArticleIds_docvalues_string";
      case "relatedArticles":
        return "relatedArticles_stored_string";
      default:
        return BaseResult.varStoredBaseResult(entityVar);
    }
  }

  public static String varIndexedSpineDoc(String entityVar) {
    switch(entityVar) {
      case "importance":
        return "importance_docvalues_string";
      case "commentVar":
        return "commentVar_docvalues_string";
      case "javaType":
        return "javaType_docvalues_string";
      case "javaClass":
        return "javaClass_docvalues_string";
      case "sinceVersion":
        return "sinceVersion_docvalues_string";
      case "supportStatus":
        return "supportStatus_docvalues_string";
      case "exampleValue":
        return "exampleValue_docvalues_string";
      case "exampleCode":
        return "exampleCode_docvalues_string";
      case "copyCode":
        return "copyCode_docvalues_string";
      case "courseNum":
        return "courseNum_docvalues_int";
      case "lessonNum":
        return "lessonNum_docvalues_int";
      case "name":
        return "name_docvalues_string";
      case "description":
        return "description_docvalues_string";
      case "authorName":
        return "authorName_docvalues_string";
      case "authorUrl":
        return "authorUrl_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      case "pageVideoUrl":
        return "pageVideoUrl_docvalues_string";
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
      case "prerequisiteArticleIds":
        return "prerequisiteArticleIds_docvalues_string";
      case "nextArticleIds":
        return "nextArticleIds_docvalues_string";
      case "labelsString":
        return "labelsString_docvalues_string";
      case "labels":
        return "labels_docvalues_strings";
      case "relatedArticleIds":
        return "relatedArticleIds_docvalues_string";
      default:
        return BaseResult.varIndexedBaseResult(entityVar);
    }
  }

  public static String searchVarSpineDoc(String searchVar) {
    switch(searchVar) {
      case "importance_docvalues_string":
        return "importance";
      case "commentVar_docvalues_string":
        return "commentVar";
      case "javaType_docvalues_string":
        return "javaType";
      case "javaClass_docvalues_string":
        return "javaClass";
      case "sinceVersion_docvalues_string":
        return "sinceVersion";
      case "supportStatus_docvalues_string":
        return "supportStatus";
      case "exampleValue_docvalues_string":
        return "exampleValue";
      case "exampleCode_docvalues_string":
        return "exampleCode";
      case "copyCode_docvalues_string":
        return "copyCode";
      case "courseNum_docvalues_int":
        return "courseNum";
      case "lessonNum_docvalues_int":
        return "lessonNum";
      case "name_docvalues_string":
        return "name";
      case "description_docvalues_string":
        return "description";
      case "authorName_docvalues_string":
        return "authorName";
      case "authorUrl_docvalues_string":
        return "authorUrl";
      case "pageId_docvalues_string":
        return "pageId";
      case "pageVideoUrl_docvalues_string":
        return "pageVideoUrl";
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
      case "prerequisiteArticleIds_docvalues_string":
        return "prerequisiteArticleIds";
      case "nextArticleIds_docvalues_string":
        return "nextArticleIds";
      case "labelsString_docvalues_string":
        return "labelsString";
      case "labels_docvalues_strings":
        return "labels";
      case "relatedArticleIds_docvalues_string":
        return "relatedArticleIds";
      default:
        return BaseResult.searchVarBaseResult(searchVar);
    }
  }

  public static String varSearchSpineDoc(String entityVar) {
    switch(entityVar) {
      default:
        return BaseResult.varSearchBaseResult(entityVar);
    }
  }

  public static String varSuggestedSpineDoc(String entityVar) {
    switch(entityVar) {
      default:
        return BaseResult.varSuggestedBaseResult(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeSpineDoc(doc);
  }
  public void storeSpineDoc(SolrResponse.Doc doc) {
    SpineDoc oSpineDoc = (SpineDoc)this;
    SiteRequest siteRequest = oSpineDoc.getSiteRequest_();

    oSpineDoc.setImportance(Optional.ofNullable(doc.get("importance_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setCommentVar(Optional.ofNullable(doc.get("commentVar_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setJavaType(Optional.ofNullable(doc.get("javaType_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setJavaClass(Optional.ofNullable(doc.get("javaClass_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setSinceVersion(Optional.ofNullable(doc.get("sinceVersion_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setSupportStatus(Optional.ofNullable(doc.get("supportStatus_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setExampleValue(Optional.ofNullable(doc.get("exampleValue_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setExampleCode(Optional.ofNullable(doc.get("exampleCode_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setCopyCode(Optional.ofNullable(doc.get("copyCode_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setCourseNum(Optional.ofNullable(doc.get("courseNum_docvalues_int")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setLessonNum(Optional.ofNullable(doc.get("lessonNum_docvalues_int")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setAuthorName(Optional.ofNullable(doc.get("authorName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setAuthorUrl(Optional.ofNullable(doc.get("authorUrl_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setPageVideoUrl(Optional.ofNullable(doc.get("pageVideoUrl_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setPageImageUri(Optional.ofNullable(doc.get("pageImageUri_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setPageImageWidth(Optional.ofNullable(doc.get("pageImageWidth_docvalues_int")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setPageImageHeight(Optional.ofNullable(doc.get("pageImageHeight_docvalues_int")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setPageImageType(Optional.ofNullable(doc.get("pageImageType_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setPageImageAlt(Optional.ofNullable(doc.get("pageImageAlt_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setPrerequisiteArticleIds(Optional.ofNullable(doc.get("prerequisiteArticleIds_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setPrerequisiteArticles(Optional.ofNullable(doc.get("prerequisiteArticles_stored_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setNextArticleIds(Optional.ofNullable(doc.get("nextArticleIds_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setNextArticles(Optional.ofNullable(doc.get("nextArticles_stored_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setLabelsString(Optional.ofNullable(doc.get("labelsString_docvalues_string")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("labels_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oSpineDoc.addLabels(SpineDoc.staticSetLabels(siteRequest, v.toString()));
    });
    oSpineDoc.setRelatedArticleIds(Optional.ofNullable(doc.get("relatedArticleIds_docvalues_string")).map(v -> v.toString()).orElse(null));
    oSpineDoc.setRelatedArticles(Optional.ofNullable(doc.get("relatedArticles_stored_string")).map(v -> v.toString()).orElse(null));

    super.storeBaseResult(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestSpineDoc() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof SpineDoc) {
      SpineDoc original = (SpineDoc)o;
      if(!Objects.equals(importance, original.getImportance()) && importance != null && original.getImportance() != null && importance.compareTo(original.getImportance()) != 0)
        apiRequest.addVars("importance");
      if(!Objects.equals(commentVar, original.getCommentVar()))
        apiRequest.addVars("commentVar");
      if(!Objects.equals(javaType, original.getJavaType()))
        apiRequest.addVars("javaType");
      if(!Objects.equals(javaClass, original.getJavaClass()))
        apiRequest.addVars("javaClass");
      if(!Objects.equals(sinceVersion, original.getSinceVersion()))
        apiRequest.addVars("sinceVersion");
      if(!Objects.equals(supportStatus, original.getSupportStatus()))
        apiRequest.addVars("supportStatus");
      if(!Objects.equals(exampleValue, original.getExampleValue()))
        apiRequest.addVars("exampleValue");
      if(!Objects.equals(exampleCode, original.getExampleCode()))
        apiRequest.addVars("exampleCode");
      if(!Objects.equals(copyCode, original.getCopyCode()))
        apiRequest.addVars("copyCode");
      if(!Objects.equals(courseNum, original.getCourseNum()))
        apiRequest.addVars("courseNum");
      if(!Objects.equals(lessonNum, original.getLessonNum()))
        apiRequest.addVars("lessonNum");
      if(!Objects.equals(name, original.getName()))
        apiRequest.addVars("name");
      if(!Objects.equals(description, original.getDescription()))
        apiRequest.addVars("description");
      if(!Objects.equals(authorName, original.getAuthorName()))
        apiRequest.addVars("authorName");
      if(!Objects.equals(authorUrl, original.getAuthorUrl()))
        apiRequest.addVars("authorUrl");
      if(!Objects.equals(pageId, original.getPageId()))
        apiRequest.addVars("pageId");
      if(!Objects.equals(pageVideoUrl, original.getPageVideoUrl()))
        apiRequest.addVars("pageVideoUrl");
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
      if(!Objects.equals(prerequisiteArticleIds, original.getPrerequisiteArticleIds()))
        apiRequest.addVars("prerequisiteArticleIds");
      if(!Objects.equals(prerequisiteArticles, original.getPrerequisiteArticles()))
        apiRequest.addVars("prerequisiteArticles");
      if(!Objects.equals(nextArticleIds, original.getNextArticleIds()))
        apiRequest.addVars("nextArticleIds");
      if(!Objects.equals(nextArticles, original.getNextArticles()))
        apiRequest.addVars("nextArticles");
      if(!Objects.equals(labelsString, original.getLabelsString()))
        apiRequest.addVars("labelsString");
      if(!Objects.equals(labels, original.getLabels()))
        apiRequest.addVars("labels");
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
    sb.append(Optional.ofNullable(importance).map(v -> "importance: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(commentVar).map(v -> "commentVar: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(javaType).map(v -> "javaType: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(javaClass).map(v -> "javaClass: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(sinceVersion).map(v -> "sinceVersion: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(supportStatus).map(v -> "supportStatus: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(exampleValue).map(v -> "exampleValue: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(exampleCode).map(v -> "exampleCode: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(copyCode).map(v -> "copyCode: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(courseNum).map(v -> "courseNum: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(lessonNum).map(v -> "lessonNum: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(name).map(v -> "name: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(authorName).map(v -> "authorName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(authorUrl).map(v -> "authorUrl: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageVideoUrl).map(v -> "pageVideoUrl: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageImageUri).map(v -> "pageImageUri: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageImageWidth).map(v -> "pageImageWidth: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(pageImageHeight).map(v -> "pageImageHeight: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(pageImageType).map(v -> "pageImageType: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageImageAlt).map(v -> "pageImageAlt: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(prerequisiteArticleIds).map(v -> "prerequisiteArticleIds: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(prerequisiteArticles).map(v -> "prerequisiteArticles: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(nextArticleIds).map(v -> "nextArticleIds: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(nextArticles).map(v -> "nextArticles: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(labelsString).map(v -> "labelsString: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(labels).map(v -> "labels: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(relatedArticleIds).map(v -> "relatedArticleIds: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(relatedArticles).map(v -> "relatedArticles: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "SpineDoc";
  public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.spinedoc.SpineDoc";
  public static final String CLASS_AUTH_RESOURCE = "SPINEDOC";
  public static final String CLASS_API_ADDRESS_SpineDoc = "computate.org-enUS-SpineDoc";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_SpineDoc;
  }
  public static final String VAR_article = "article";
  public static final String SET_article = "setArticle";
  public static final String VAR_importance = "importance";
  public static final String SET_importance = "setImportance";
  public static final String VAR_commentVar = "commentVar";
  public static final String SET_commentVar = "setCommentVar";
  public static final String VAR_javaType = "javaType";
  public static final String SET_javaType = "setJavaType";
  public static final String VAR_javaClass = "javaClass";
  public static final String SET_javaClass = "setJavaClass";
  public static final String VAR_sinceVersion = "sinceVersion";
  public static final String SET_sinceVersion = "setSinceVersion";
  public static final String VAR_supportStatus = "supportStatus";
  public static final String SET_supportStatus = "setSupportStatus";
  public static final String VAR_exampleValue = "exampleValue";
  public static final String SET_exampleValue = "setExampleValue";
  public static final String VAR_exampleCode = "exampleCode";
  public static final String SET_exampleCode = "setExampleCode";
  public static final String VAR_copyCode = "copyCode";
  public static final String SET_copyCode = "setCopyCode";
  public static final String VAR_courseNum = "courseNum";
  public static final String SET_courseNum = "setCourseNum";
  public static final String VAR_lessonNum = "lessonNum";
  public static final String SET_lessonNum = "setLessonNum";
  public static final String VAR_name = "name";
  public static final String SET_name = "setName";
  public static final String VAR_description = "description";
  public static final String SET_description = "setDescription";
  public static final String VAR_authorName = "authorName";
  public static final String SET_authorName = "setAuthorName";
  public static final String VAR_authorUrl = "authorUrl";
  public static final String SET_authorUrl = "setAuthorUrl";
  public static final String VAR_pageId = "pageId";
  public static final String SET_pageId = "setPageId";
  public static final String VAR_pageVideoUrl = "pageVideoUrl";
  public static final String SET_pageVideoUrl = "setPageVideoUrl";
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
  public static final String VAR_prerequisiteArticleIds = "prerequisiteArticleIds";
  public static final String SET_prerequisiteArticleIds = "setPrerequisiteArticleIds";
  public static final String VAR_prerequisiteArticleSearch = "prerequisiteArticleSearch";
  public static final String SET_prerequisiteArticleSearch = "setPrerequisiteArticleSearch";
  public static final String VAR_prerequisiteArticles = "prerequisiteArticles";
  public static final String SET_prerequisiteArticles = "setPrerequisiteArticles";
  public static final String VAR_nextArticleIds = "nextArticleIds";
  public static final String SET_nextArticleIds = "setNextArticleIds";
  public static final String VAR_nextArticleSearch = "nextArticleSearch";
  public static final String SET_nextArticleSearch = "setNextArticleSearch";
  public static final String VAR_nextArticles = "nextArticles";
  public static final String SET_nextArticles = "setNextArticles";
  public static final String VAR_labelsString = "labelsString";
  public static final String SET_labelsString = "setLabelsString";
  public static final String VAR_labels = "labels";
  public static final String SET_labels = "setLabels";
  public static final String VAR_relatedArticleIds = "relatedArticleIds";
  public static final String SET_relatedArticleIds = "setRelatedArticleIds";
  public static final String VAR_relatedArticleSearch = "relatedArticleSearch";
  public static final String SET_relatedArticleSearch = "setRelatedArticleSearch";
  public static final String VAR_relatedArticles = "relatedArticles";
  public static final String SET_relatedArticles = "setRelatedArticles";

  public static List<String> varsQForClass() {
    return SpineDoc.varsQSpineDoc(new ArrayList<String>());
  }
  public static List<String> varsQSpineDoc(List<String> vars) {
    BaseResult.varsQBaseResult(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return SpineDoc.varsFqSpineDoc(new ArrayList<String>());
  }
  public static List<String> varsFqSpineDoc(List<String> vars) {
    vars.add(VAR_importance);
    vars.add(VAR_commentVar);
    vars.add(VAR_javaType);
    vars.add(VAR_javaClass);
    vars.add(VAR_sinceVersion);
    vars.add(VAR_supportStatus);
    vars.add(VAR_exampleValue);
    vars.add(VAR_exampleCode);
    vars.add(VAR_copyCode);
    vars.add(VAR_courseNum);
    vars.add(VAR_lessonNum);
    vars.add(VAR_authorName);
    vars.add(VAR_authorUrl);
    vars.add(VAR_pageId);
    vars.add(VAR_pageVideoUrl);
    vars.add(VAR_pageImageUri);
    BaseResult.varsFqBaseResult(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return SpineDoc.varsRangeSpineDoc(new ArrayList<String>());
  }
  public static List<String> varsRangeSpineDoc(List<String> vars) {
    vars.add(VAR_importance);
    vars.add(VAR_courseNum);
    vars.add(VAR_lessonNum);
    BaseResult.varsRangeBaseResult(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_article = "";
  public static final String DISPLAY_NAME_importance = "importance";
  public static final String DISPLAY_NAME_commentVar = "comment var";
  public static final String DISPLAY_NAME_javaType = "Java type";
  public static final String DISPLAY_NAME_javaClass = "Value type";
  public static final String DISPLAY_NAME_sinceVersion = "since version";
  public static final String DISPLAY_NAME_supportStatus = "support status";
  public static final String DISPLAY_NAME_exampleValue = "example value";
  public static final String DISPLAY_NAME_exampleCode = "example code";
  public static final String DISPLAY_NAME_copyCode = "copy code";
  public static final String DISPLAY_NAME_courseNum = "course number";
  public static final String DISPLAY_NAME_lessonNum = "lesson number";
  public static final String DISPLAY_NAME_name = "title";
  public static final String DISPLAY_NAME_description = "description";
  public static final String DISPLAY_NAME_authorName = "author name";
  public static final String DISPLAY_NAME_authorUrl = "author URL";
  public static final String DISPLAY_NAME_pageId = "Page ID";
  public static final String DISPLAY_NAME_pageVideoUrl = "video URL";
  public static final String DISPLAY_NAME_pageImageUri = "imageUri";
  public static final String DISPLAY_NAME_pageImageWidth = "";
  public static final String DISPLAY_NAME_pageImageHeight = "";
  public static final String DISPLAY_NAME_pageImageType = "";
  public static final String DISPLAY_NAME_pageImageAlt = "";
  public static final String DISPLAY_NAME_prerequisiteArticleIds = "prerequisite article IDs";
  public static final String DISPLAY_NAME_prerequisiteArticleSearch = "";
  public static final String DISPLAY_NAME_prerequisiteArticles = "prerequisite articles";
  public static final String DISPLAY_NAME_nextArticleIds = "next article IDs";
  public static final String DISPLAY_NAME_nextArticleSearch = "";
  public static final String DISPLAY_NAME_nextArticles = "next articles";
  public static final String DISPLAY_NAME_labelsString = "labels string";
  public static final String DISPLAY_NAME_labels = "labels";
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
    return SpineDoc.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return description;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/spine-doc/%s";
  }

  @Override
  public String enUSStringFormatUrlDisplayPageForClass() {
    return "%s/en-us/learn/spine-doc/%s";
  }

  public static String varJsonForClass(String var, Boolean patch) {
    return SpineDoc.varJsonSpineDoc(var, patch);
  }
  public static String varJsonSpineDoc(String var, Boolean patch) {
    switch(var) {
    case VAR_article:
      return patch ? SET_article : VAR_article;
    case VAR_importance:
      return patch ? SET_importance : VAR_importance;
    case VAR_commentVar:
      return patch ? SET_commentVar : VAR_commentVar;
    case VAR_javaType:
      return patch ? SET_javaType : VAR_javaType;
    case VAR_javaClass:
      return patch ? SET_javaClass : VAR_javaClass;
    case VAR_sinceVersion:
      return patch ? SET_sinceVersion : VAR_sinceVersion;
    case VAR_supportStatus:
      return patch ? SET_supportStatus : VAR_supportStatus;
    case VAR_exampleValue:
      return patch ? SET_exampleValue : VAR_exampleValue;
    case VAR_exampleCode:
      return patch ? SET_exampleCode : VAR_exampleCode;
    case VAR_copyCode:
      return patch ? SET_copyCode : VAR_copyCode;
    case VAR_courseNum:
      return patch ? SET_courseNum : VAR_courseNum;
    case VAR_lessonNum:
      return patch ? SET_lessonNum : VAR_lessonNum;
    case VAR_name:
      return patch ? SET_name : VAR_name;
    case VAR_description:
      return patch ? SET_description : VAR_description;
    case VAR_authorName:
      return patch ? SET_authorName : VAR_authorName;
    case VAR_authorUrl:
      return patch ? SET_authorUrl : VAR_authorUrl;
    case VAR_pageId:
      return patch ? SET_pageId : VAR_pageId;
    case VAR_pageVideoUrl:
      return patch ? SET_pageVideoUrl : VAR_pageVideoUrl;
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
    case VAR_prerequisiteArticleIds:
      return patch ? SET_prerequisiteArticleIds : VAR_prerequisiteArticleIds;
    case VAR_prerequisiteArticleSearch:
      return patch ? SET_prerequisiteArticleSearch : VAR_prerequisiteArticleSearch;
    case VAR_prerequisiteArticles:
      return patch ? SET_prerequisiteArticles : VAR_prerequisiteArticles;
    case VAR_nextArticleIds:
      return patch ? SET_nextArticleIds : VAR_nextArticleIds;
    case VAR_nextArticleSearch:
      return patch ? SET_nextArticleSearch : VAR_nextArticleSearch;
    case VAR_nextArticles:
      return patch ? SET_nextArticles : VAR_nextArticles;
    case VAR_labelsString:
      return patch ? SET_labelsString : VAR_labelsString;
    case VAR_labels:
      return patch ? SET_labels : VAR_labels;
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
    return SpineDoc.displayNameSpineDoc(var);
  }
  public static String displayNameSpineDoc(String var) {
    switch(var) {
    case VAR_article:
      return DISPLAY_NAME_article;
    case VAR_importance:
      return DISPLAY_NAME_importance;
    case VAR_commentVar:
      return DISPLAY_NAME_commentVar;
    case VAR_javaType:
      return DISPLAY_NAME_javaType;
    case VAR_javaClass:
      return DISPLAY_NAME_javaClass;
    case VAR_sinceVersion:
      return DISPLAY_NAME_sinceVersion;
    case VAR_supportStatus:
      return DISPLAY_NAME_supportStatus;
    case VAR_exampleValue:
      return DISPLAY_NAME_exampleValue;
    case VAR_exampleCode:
      return DISPLAY_NAME_exampleCode;
    case VAR_copyCode:
      return DISPLAY_NAME_copyCode;
    case VAR_courseNum:
      return DISPLAY_NAME_courseNum;
    case VAR_lessonNum:
      return DISPLAY_NAME_lessonNum;
    case VAR_name:
      return DISPLAY_NAME_name;
    case VAR_description:
      return DISPLAY_NAME_description;
    case VAR_authorName:
      return DISPLAY_NAME_authorName;
    case VAR_authorUrl:
      return DISPLAY_NAME_authorUrl;
    case VAR_pageId:
      return DISPLAY_NAME_pageId;
    case VAR_pageVideoUrl:
      return DISPLAY_NAME_pageVideoUrl;
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
    case VAR_prerequisiteArticleIds:
      return DISPLAY_NAME_prerequisiteArticleIds;
    case VAR_prerequisiteArticleSearch:
      return DISPLAY_NAME_prerequisiteArticleSearch;
    case VAR_prerequisiteArticles:
      return DISPLAY_NAME_prerequisiteArticles;
    case VAR_nextArticleIds:
      return DISPLAY_NAME_nextArticleIds;
    case VAR_nextArticleSearch:
      return DISPLAY_NAME_nextArticleSearch;
    case VAR_nextArticles:
      return DISPLAY_NAME_nextArticles;
    case VAR_labelsString:
      return DISPLAY_NAME_labelsString;
    case VAR_labels:
      return DISPLAY_NAME_labels;
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

  public static String descriptionSpineDoc(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_importance:
      return "The importance of this page. ";
    case VAR_commentVar:
      return "What comment var the doc refers to. ";
    case VAR_javaType:
      return "What Java type the doc refers to: class, method, field, consructor. ";
    case VAR_javaClass:
      return "What Java Class the value of this doc would be in Java. ";
    case VAR_sinceVersion:
      return "What version introduced the SPINE comment. ";
    case VAR_supportStatus:
      return "The support status of this SPINE comment. ";
    case VAR_exampleValue:
      return "The example value to show for this SPINE comment. ";
    case VAR_exampleCode:
      return "The example code to show for this SPINE comment. ";
    case VAR_copyCode:
      return "The code to copy for this SPINE comment. ";
    case VAR_courseNum:
      return "The course number for this page. ";
    case VAR_lessonNum:
      return "The lesson number for this page. ";
    case VAR_name:
      return "The name of this page. ";
    case VAR_description:
      return "The description of this page. ";
    case VAR_authorName:
      return "The author name";
    case VAR_authorUrl:
      return "The author URL";
    case VAR_pageId:
      return "The ID for this page. ";
    case VAR_pageVideoUrl:
      return "The video URL for this page. ";
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
    case VAR_prerequisiteArticleIds:
      return "The prerequisite article IDs comma-separated. ";
    case VAR_prerequisiteArticles:
      return "A JSON array of prerequisite articles. ";
    case VAR_nextArticleIds:
      return "The next article IDs comma-separated. ";
    case VAR_nextArticles:
      return "A JSON array of next articles. ";
    case VAR_labelsString:
      return "The labels String for this article comma-separated. ";
    case VAR_labels:
      return "The labels for this article. ";
    case VAR_relatedArticleIds:
      return "The related article IDs comma-separated. ";
    case VAR_relatedArticles:
      return "A JSON array of related articles. ";
      default:
        return BaseResult.descriptionBaseResult(var);
    }
  }

  public static String classSimpleNameSpineDoc(String var) {
    switch(var) {
    case VAR_article:
      return "Boolean";
    case VAR_importance:
      return "BigDecimal";
    case VAR_commentVar:
      return "String";
    case VAR_javaType:
      return "String";
    case VAR_javaClass:
      return "String";
    case VAR_sinceVersion:
      return "String";
    case VAR_supportStatus:
      return "String";
    case VAR_exampleValue:
      return "String";
    case VAR_exampleCode:
      return "String";
    case VAR_copyCode:
      return "String";
    case VAR_courseNum:
      return "Integer";
    case VAR_lessonNum:
      return "Integer";
    case VAR_name:
      return "String";
    case VAR_description:
      return "String";
    case VAR_authorName:
      return "String";
    case VAR_authorUrl:
      return "String";
    case VAR_pageId:
      return "String";
    case VAR_pageVideoUrl:
      return "String";
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
    case VAR_prerequisiteArticleIds:
      return "String";
    case VAR_prerequisiteArticleSearch:
      return "SearchList";
    case VAR_prerequisiteArticles:
      return "JsonArray";
    case VAR_nextArticleIds:
      return "String";
    case VAR_nextArticleSearch:
      return "SearchList";
    case VAR_nextArticles:
      return "JsonArray";
    case VAR_labelsString:
      return "String";
    case VAR_labels:
      return "List";
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

  public static Integer htmColumnSpineDoc(String var) {
    switch(var) {
    case VAR_name:
      return 1;
    case VAR_description:
      return 2;
      default:
        return BaseResult.htmColumnBaseResult(var);
    }
  }

  public static Integer htmRowSpineDoc(String var) {
    switch(var) {
    case VAR_authorName:
      return 3;
    case VAR_authorUrl:
      return 3;
    case VAR_pageId:
      return 99;
    case VAR_pageImageUri:
      return 4;
      default:
        return BaseResult.htmRowBaseResult(var);
    }
  }

  public static Integer htmCellSpineDoc(String var) {
    switch(var) {
    case VAR_authorName:
      return 3;
    case VAR_authorUrl:
      return 3;
    case VAR_pageId:
      return 1;
    case VAR_pageImageUri:
      return 1;
      default:
        return BaseResult.htmCellBaseResult(var);
    }
  }

  public static Integer lengthMinSpineDoc(String var) {
    switch(var) {
      default:
        return BaseResult.lengthMinBaseResult(var);
    }
  }

  public static Integer lengthMaxSpineDoc(String var) {
    switch(var) {
      default:
        return BaseResult.lengthMaxBaseResult(var);
    }
  }

  public static Integer maxSpineDoc(String var) {
    switch(var) {
      default:
        return BaseResult.maxBaseResult(var);
    }
  }

  public static Integer minSpineDoc(String var) {
    switch(var) {
      default:
        return BaseResult.minBaseResult(var);
    }
  }
}
