package org.computate.site.model.product;

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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyProductGen into the class CompanyProduct. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the CompanyProduct API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "Model: true" if you wish to persist these CompanyProduct objects in a relational PostgreSQL database transactionally in the RESTful API. 
 * The code to persist and query the CompanyProductGen data in the database will then be automatically generated. 
 * </li>
 * <h3>About the CompanyProduct class and it's generated class CompanyProductGen&lt;BaseResult&gt;: </h3>extends CompanyProductGen
 * <p>
 * This Java class extends a generated Java class CompanyProductGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct">Find the class CompanyProduct in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyProductGen<BaseResult>
 * <p>This <code>class CompanyProduct extends CompanyProductGen&lt;BaseResult&gt;</code>, which means it extends a newly generated CompanyProductGen. 
 * The generated <code>class CompanyProductGen extends BaseResult</code> which means that CompanyProduct extends CompanyProductGen which extends BaseResult. 
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
 * <p>This class contains a comment <b>"ApiTag: product"</b>, which groups all of the OpenAPIs for CompanyProduct objects under the tag "product". 
 * </p>
 * <h2>ApiUri.enUS: /api/product</h2>
 * <p>This class contains a comment <b>"ApiUri: /api/product"</b>, which defines the base API URI for CompanyProduct objects as "/api/product" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>IconGroup: regular</h2>
 * <p>This class contains a comment <b>"IconGroup: regular"</b>, which adds icons on the CompanyProduct page with a group of "regular". 
 * This will reference a Font Awesome icon group that starts with "fa-" followed by the icon group "regular", together is "fa-regular". 
 * A Font Awesome icon group of "regular" is expected to exist. 
 * The Font Awesome groups currently supported include: solid, regular, light, thin, duotone, sharp, and kit. 
 * See: <a href="https://fontawesome.com/docs/web/dig-deeper/styles">https://fontawesome.com/docs/web/dig-deeper/styles</a>. 
 * </p>
 * <h2>IconName: conveyor-belt</h2>
 * <p>This class contains a comment <b>"IconName: conveyor-belt"</b>, which adds icons on the CompanyProduct page with a name of "conveyor-belt". 
 * This will reference a Font Awesome icon that starts with the icon group "fa-regular fa-" followed by the icon name, which is "fa-regular fa-conveyor-belt". 
 * A Font Awesome icon of "fa-regular fa-conveyor-belt" is expected to exist. 
 * See: <a href="https://fontawesome.com/icons">https://fontawesome.com/icons</a>. 
 * </p>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyProduct class will inherit the helpful inherited class comments from the super class CompanyProductGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 5</h2>
 * <p>This class contains a comment <b>"Order: 5"</b>, which means this class will be sorted by the given number 5 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.product.CompanyProductPage. 
 * </p>
 * <h2>SuperPage.enUS: BaseResultPage</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: BaseResultPage"</b>, which identifies the Java super class of the page code by it's class simple name "BaseResultPage". 
 * This means that the newly created class org.computate.site.model.product.CompanyProductPage extends org.computate.site.result.BaseResultPage. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyProduct Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * This class contains a comment <b>"Role.enUS: SiteAdmin"</b>, which identifies the default role name "SiteAdmin" of the OAuth2/OpenID Connect user role required to access this CompanyProduct API. 
 * It's possible to reconfigure the roles required to access the CompanyProduct API by configuring an environment variable like this: 
 * </p>
 * <pre>AUTH_ROLE_REQUIRED_CompanyProduct: ["SiteAdmin"]</pre>
 * <h2>AName.enUS: a product</h2>
 * <p>This class contains a comment <b>"AName.enUS: a product"</b>, which identifies the language context to describe a CompanyProduct as "a product". 
 * </p>
 * <p>
 * Delete the class CompanyProduct in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.product in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.product&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyProductGen<DEV> extends BaseResult {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyProduct.class);

	public static final String CompanyProduct_Description_enUS = "See the computate products that will help you build your own data-driven platforms";
	public static final String CompanyProduct_AName_enUS = "a product";
	public static final String CompanyProduct_This_enUS = "this ";
	public static final String CompanyProduct_ThisName_enUS = "this product";
	public static final String CompanyProduct_A_enUS = "a ";
	public static final String CompanyProduct_TheName_enUS = "the product";
	public static final String CompanyProduct_SingularName_enUS = "product";
	public static final String CompanyProduct_PluralName_enUS = "products";
	public static final String CompanyProduct_NameActual_enUS = "current product";
	public static final String CompanyProduct_AllName_enUS = "all the products";
	public static final String CompanyProduct_SearchAllNameBy_enUS = "search products by ";
	public static final String CompanyProduct_Title_enUS = "products";
	public static final String CompanyProduct_ThePluralName_enUS = "the products";
	public static final String CompanyProduct_NoNameFound_enUS = "no product found";
	public static final String CompanyProduct_ApiUri_enUS = "/api/product";
	public static final String CompanyProduct_ApiUriSearchPage_enUS = "/product";
	public static final String CompanyProduct_OfName_enUS = "of product";
	public static final String CompanyProduct_ANameAdjective_enUS = "a product";
	public static final String CompanyProduct_NameAdjectiveSingular_enUS = "product";
	public static final String CompanyProduct_NameAdjectivePlural_enUS = "products";
	public static final String Search_enUS_Uri = "/api/product";
	public static final String Search_enUS_ImageUri = "/png/api/product-999.png";
	public static final String GET_enUS_Uri = "/api/product/{id}";
	public static final String GET_enUS_ImageUri = "/png/api/product/{id}-999.png";
	public static final String PATCH_enUS_Uri = "/api/product";
	public static final String PATCH_enUS_ImageUri = "/png/api/product-999.png";
	public static final String POST_enUS_Uri = "/api/product";
	public static final String POST_enUS_ImageUri = "/png/api/product-999.png";
	public static final String PUTImport_enUS_Uri = "/api/product-import";
	public static final String PUTImport_enUS_ImageUri = "/png/api/product-import-999.png";
	public static final String SearchPage_enUS_Uri = "/product";
	public static final String SearchPage_enUS_ImageUri = "/png/product-999.png";

	public static final String CompanyProduct_IconGroup = "regular";
	public static final String CompanyProduct_IconName = "conveyor-belt";

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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = CompanyProduct.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProduct nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchName(siteRequest_, CompanyProduct.staticSetName(siteRequest_, o)).toString();
	}

	public String sqlName() {
		return name;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageId(Wrap<String> w);

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String o) {
		this.pageId = CompanyProduct.staticSetPageId(siteRequest_, o);
	}
	public static String staticSetPageId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProduct pageIdInit() {
		Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
		if(pageId == null) {
			_pageId(pageIdWrap);
			Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
				setPageId(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchPageId(siteRequest_, CompanyProduct.staticSetPageId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:resourceUri">Find the entity resourceUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _resourceUri(Wrap<String> w);

	public String getResourceUri() {
		return resourceUri;
	}
	public void setResourceUri(String o) {
		this.resourceUri = CompanyProduct.staticSetResourceUri(siteRequest_, o);
	}
	public static String staticSetResourceUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProduct resourceUriInit() {
		Wrap<String> resourceUriWrap = new Wrap<String>().var("resourceUri");
		if(resourceUri == null) {
			_resourceUri(resourceUriWrap);
			Optional.ofNullable(resourceUriWrap.getO()).ifPresent(o -> {
				setResourceUri(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static String staticSearchResourceUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrResourceUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResourceUri(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchResourceUri(siteRequest_, CompanyProduct.staticSetResourceUri(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:templateUri">Find the entity templateUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _templateUri(Wrap<String> w);

	public String getTemplateUri() {
		return templateUri;
	}
	public void setTemplateUri(String o) {
		this.templateUri = CompanyProduct.staticSetTemplateUri(siteRequest_, o);
	}
	public static String staticSetTemplateUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProduct templateUriInit() {
		Wrap<String> templateUriWrap = new Wrap<String>().var("templateUri");
		if(templateUri == null) {
			_templateUri(templateUriWrap);
			Optional.ofNullable(templateUriWrap.getO()).ifPresent(o -> {
				setTemplateUri(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static String staticSearchTemplateUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTemplateUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTemplateUri(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchTemplateUri(siteRequest_, CompanyProduct.staticSetTemplateUri(siteRequest_, o)).toString();
	}

	public String sqlTemplateUri() {
		return templateUri;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:uri">Find the entity uri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _uri(Wrap<String> w);

	public String getUri() {
		return uri;
	}
	public void setUri(String o) {
		this.uri = CompanyProduct.staticSetUri(siteRequest_, o);
	}
	public static String staticSetUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProduct uriInit() {
		Wrap<String> uriWrap = new Wrap<String>().var("uri");
		if(uri == null) {
			_uri(uriWrap);
			Optional.ofNullable(uriWrap.getO()).ifPresent(o -> {
				setUri(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static String staticSearchUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUri(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchUri(siteRequest_, CompanyProduct.staticSetUri(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:url">Find the entity url in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _url(Wrap<String> w);

	public String getUrl() {
		return url;
	}
	public void setUrl(String o) {
		this.url = CompanyProduct.staticSetUrl(siteRequest_, o);
	}
	public static String staticSetUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProduct urlInit() {
		Wrap<String> urlWrap = new Wrap<String>().var("url");
		if(url == null) {
			_url(urlWrap);
			Optional.ofNullable(urlWrap.getO()).ifPresent(o -> {
				setUrl(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static String staticSearchUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUrl(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchUrl(siteRequest_, CompanyProduct.staticSetUrl(siteRequest_, o)).toString();
	}

	public String sqlUrl() {
		return url;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:title">Find the entity title in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _title(Wrap<String> w);

	public String getTitle() {
		return title;
	}
	public void setTitle(String o) {
		this.title = CompanyProduct.staticSetTitle(siteRequest_, o);
	}
	public static String staticSetTitle(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProduct titleInit() {
		Wrap<String> titleWrap = new Wrap<String>().var("title");
		if(title == null) {
			_title(titleWrap);
			Optional.ofNullable(titleWrap.getO()).ifPresent(o -> {
				setTitle(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static String staticSearchTitle(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTitle(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTitle(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchTitle(siteRequest_, CompanyProduct.staticSetTitle(siteRequest_, o)).toString();
	}

	public String sqlTitle() {
		return title;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepCompanyProduct(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyProduct();
	}

	public Future<Void> promiseDeepCompanyProduct() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyProduct(promise2);
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

	public Future<Void> promiseCompanyProduct(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				nameInit();
				pageIdInit();
				resourceUriInit();
				templateUriInit();
				uriInit();
				urlInit();
				titleInit();
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
		return promiseDeepCompanyProduct(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyProduct(SiteRequest siteRequest_) {
			super.siteRequestBaseResult(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyProduct(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyProduct(v);
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
	public Object obtainCompanyProduct(String var) {
		CompanyProduct oCompanyProduct = (CompanyProduct)this;
		switch(var) {
			case "name":
				return oCompanyProduct.name;
			case "pageId":
				return oCompanyProduct.pageId;
			case "resourceUri":
				return oCompanyProduct.resourceUri;
			case "templateUri":
				return oCompanyProduct.templateUri;
			case "uri":
				return oCompanyProduct.uri;
			case "url":
				return oCompanyProduct.url;
			case "title":
				return oCompanyProduct.title;
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
				o = relateCompanyProduct(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyProduct(String var, Object val) {
		CompanyProduct oCompanyProduct = (CompanyProduct)this;
		switch(var) {
			default:
				return super.relateBaseResult(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyProduct(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyProduct(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return CompanyProduct.staticSetName(siteRequest_, o);
		case "pageId":
			return CompanyProduct.staticSetPageId(siteRequest_, o);
		case "resourceUri":
			return CompanyProduct.staticSetResourceUri(siteRequest_, o);
		case "templateUri":
			return CompanyProduct.staticSetTemplateUri(siteRequest_, o);
		case "uri":
			return CompanyProduct.staticSetUri(siteRequest_, o);
		case "url":
			return CompanyProduct.staticSetUrl(siteRequest_, o);
		case "title":
			return CompanyProduct.staticSetTitle(siteRequest_, o);
			default:
				return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyProduct(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyProduct(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return CompanyProduct.staticSearchName(siteRequest_, (String)o);
		case "pageId":
			return CompanyProduct.staticSearchPageId(siteRequest_, (String)o);
		case "resourceUri":
			return CompanyProduct.staticSearchResourceUri(siteRequest_, (String)o);
		case "templateUri":
			return CompanyProduct.staticSearchTemplateUri(siteRequest_, (String)o);
		case "uri":
			return CompanyProduct.staticSearchUri(siteRequest_, (String)o);
		case "url":
			return CompanyProduct.staticSearchUrl(siteRequest_, (String)o);
		case "title":
			return CompanyProduct.staticSearchTitle(siteRequest_, (String)o);
			default:
				return BaseResult.staticSearchBaseResult(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyProduct(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyProduct(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return CompanyProduct.staticSearchStrName(siteRequest_, (String)o);
		case "pageId":
			return CompanyProduct.staticSearchStrPageId(siteRequest_, (String)o);
		case "resourceUri":
			return CompanyProduct.staticSearchStrResourceUri(siteRequest_, (String)o);
		case "templateUri":
			return CompanyProduct.staticSearchStrTemplateUri(siteRequest_, (String)o);
		case "uri":
			return CompanyProduct.staticSearchStrUri(siteRequest_, (String)o);
		case "url":
			return CompanyProduct.staticSearchStrUrl(siteRequest_, (String)o);
		case "title":
			return CompanyProduct.staticSearchStrTitle(siteRequest_, (String)o);
			default:
				return BaseResult.staticSearchStrBaseResult(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyProduct(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyProduct(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return CompanyProduct.staticSearchFqName(siteRequest_, o);
		case "pageId":
			return CompanyProduct.staticSearchFqPageId(siteRequest_, o);
		case "resourceUri":
			return CompanyProduct.staticSearchFqResourceUri(siteRequest_, o);
		case "templateUri":
			return CompanyProduct.staticSearchFqTemplateUri(siteRequest_, o);
		case "uri":
			return CompanyProduct.staticSearchFqUri(siteRequest_, o);
		case "url":
			return CompanyProduct.staticSearchFqUrl(siteRequest_, o);
		case "title":
			return CompanyProduct.staticSearchFqTitle(siteRequest_, o);
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
					o = persistCompanyProduct(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistCompanyProduct(String var, Object val) {
		String varLower = var.toLowerCase();
			if("name".equals(varLower)) {
				if(val instanceof String) {
					setName((String)val);
				}
				saves.add("name");
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
			} else if("title".equals(varLower)) {
				if(val instanceof String) {
					setTitle((String)val);
				}
				saves.add("title");
				return val;
		} else {
			return super.persistBaseResult(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateCompanyProduct(doc);
	}
	public void populateCompanyProduct(SolrResponse.Doc doc) {
		CompanyProduct oCompanyProduct = (CompanyProduct)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oCompanyProduct.setName(name);
			}

			if(saves.contains("pageId")) {
				String pageId = (String)doc.get("pageId_docvalues_string");
				if(pageId != null)
					oCompanyProduct.setPageId(pageId);
			}

			if(saves.contains("resourceUri")) {
				String resourceUri = (String)doc.get("resourceUri_docvalues_string");
				if(resourceUri != null)
					oCompanyProduct.setResourceUri(resourceUri);
			}

			if(saves.contains("templateUri")) {
				String templateUri = (String)doc.get("templateUri_docvalues_string");
				if(templateUri != null)
					oCompanyProduct.setTemplateUri(templateUri);
			}

			if(saves.contains("uri")) {
				String uri = (String)doc.get("uri_docvalues_string");
				if(uri != null)
					oCompanyProduct.setUri(uri);
			}

			if(saves.contains("url")) {
				String url = (String)doc.get("url_docvalues_string");
				if(url != null)
					oCompanyProduct.setUrl(url);
			}

			if(saves.contains("title")) {
				String title = (String)doc.get("title_docvalues_string");
				if(title != null)
					oCompanyProduct.setTitle(title);
			}
		}

		super.populateBaseResult(doc);
	}

	public void indexCompanyProduct(JsonObject doc) {
		if(name != null) {
			doc.put("name_docvalues_string", name);
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
		if(uri != null) {
			doc.put("uri_docvalues_string", uri);
		}
		if(url != null) {
			doc.put("url_docvalues_string", url);
		}
		if(title != null) {
			doc.put("title_docvalues_string", title);
		}
		super.indexBaseResult(doc);

	}

	public static String varStoredCompanyProduct(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "resourceUri":
				return "resourceUri_docvalues_string";
			case "templateUri":
				return "templateUri_docvalues_string";
			case "uri":
				return "uri_docvalues_string";
			case "url":
				return "url_docvalues_string";
			case "title":
				return "title_docvalues_string";
			default:
				return BaseResult.varStoredBaseResult(entityVar);
		}
	}

	public static String varIndexedCompanyProduct(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "pageId":
				return "pageId_docvalues_string";
			case "resourceUri":
				return "resourceUri_docvalues_string";
			case "templateUri":
				return "templateUri_docvalues_string";
			case "uri":
				return "uri_docvalues_string";
			case "url":
				return "url_docvalues_string";
			case "title":
				return "title_docvalues_string";
			default:
				return BaseResult.varIndexedBaseResult(entityVar);
		}
	}

	public static String searchVarCompanyProduct(String searchVar) {
		switch(searchVar) {
			case "name_docvalues_string":
				return "name";
			case "pageId_docvalues_string":
				return "pageId";
			case "resourceUri_docvalues_string":
				return "resourceUri";
			case "templateUri_docvalues_string":
				return "templateUri";
			case "uri_docvalues_string":
				return "uri";
			case "url_docvalues_string":
				return "url";
			case "title_docvalues_string":
				return "title";
			default:
				return BaseResult.searchVarBaseResult(searchVar);
		}
	}

	public static String varSearchCompanyProduct(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSearchBaseResult(entityVar);
		}
	}

	public static String varSuggestedCompanyProduct(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSuggestedBaseResult(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeCompanyProduct(doc);
	}
	public void storeCompanyProduct(SolrResponse.Doc doc) {
		CompanyProduct oCompanyProduct = (CompanyProduct)this;
		SiteRequest siteRequest = oCompanyProduct.getSiteRequest_();

		oCompanyProduct.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setResourceUri(Optional.ofNullable(doc.get("resourceUri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setTemplateUri(Optional.ofNullable(doc.get("templateUri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setUri(Optional.ofNullable(doc.get("uri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setUrl(Optional.ofNullable(doc.get("url_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setTitle(Optional.ofNullable(doc.get("title_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseResult(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestCompanyProduct() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof CompanyProduct) {
			CompanyProduct original = (CompanyProduct)o;
			if(!Objects.equals(name, original.getName()))
				apiRequest.addVars("name");
			if(!Objects.equals(pageId, original.getPageId()))
				apiRequest.addVars("pageId");
			if(!Objects.equals(resourceUri, original.getResourceUri()))
				apiRequest.addVars("resourceUri");
			if(!Objects.equals(templateUri, original.getTemplateUri()))
				apiRequest.addVars("templateUri");
			if(!Objects.equals(uri, original.getUri()))
				apiRequest.addVars("uri");
			if(!Objects.equals(url, original.getUrl()))
				apiRequest.addVars("url");
			if(!Objects.equals(title, original.getTitle()))
				apiRequest.addVars("title");
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
		sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(resourceUri).map(v -> "resourceUri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(templateUri).map(v -> "templateUri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(uri).map(v -> "uri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(url).map(v -> "url: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(title).map(v -> "title: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "CompanyProduct";
	public static final String CLASS_API_ADDRESS_CompanyProduct = "computate.org-enUS-CompanyProduct";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_CompanyProduct;
	}
	public static final String VAR_name = "name";
	public static final String VAR_pageId = "pageId";
	public static final String VAR_resourceUri = "resourceUri";
	public static final String VAR_templateUri = "templateUri";
	public static final String VAR_uri = "uri";
	public static final String VAR_url = "url";
	public static final String VAR_title = "title";

	public static List<String> varsQForClass() {
		return CompanyProduct.varsQCompanyProduct(new ArrayList<String>());
	}
	public static List<String> varsQCompanyProduct(List<String> vars) {
		BaseResult.varsQBaseResult(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return CompanyProduct.varsFqCompanyProduct(new ArrayList<String>());
	}
	public static List<String> varsFqCompanyProduct(List<String> vars) {
		vars.add(VAR_name);
		vars.add(VAR_pageId);
		vars.add(VAR_resourceUri);
		vars.add(VAR_templateUri);
		vars.add(VAR_uri);
		vars.add(VAR_url);
		BaseResult.varsFqBaseResult(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return CompanyProduct.varsRangeCompanyProduct(new ArrayList<String>());
	}
	public static List<String> varsRangeCompanyProduct(List<String> vars) {
		BaseResult.varsRangeBaseResult(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_name = "product name";
	public static final String DISPLAY_NAME_pageId = "Page ID";
	public static final String DISPLAY_NAME_resourceUri = "resource URI";
	public static final String DISPLAY_NAME_templateUri = "template URI";
	public static final String DISPLAY_NAME_uri = "URI";
	public static final String DISPLAY_NAME_url = "URL";
	public static final String DISPLAY_NAME_title = "title";

	public static String displayNameForClass(String var) {
		return CompanyProduct.displayNameCompanyProduct(var);
	}
	public static String displayNameCompanyProduct(String var) {
		switch(var) {
		case VAR_name:
			return DISPLAY_NAME_name;
		case VAR_pageId:
			return DISPLAY_NAME_pageId;
		case VAR_resourceUri:
			return DISPLAY_NAME_resourceUri;
		case VAR_templateUri:
			return DISPLAY_NAME_templateUri;
		case VAR_uri:
			return DISPLAY_NAME_uri;
		case VAR_url:
			return DISPLAY_NAME_url;
		case VAR_title:
			return DISPLAY_NAME_title;
		default:
			return BaseResult.displayNameBaseResult(var);
		}
	}

	public static String descriptionCompanyProduct(String var) {
		switch(var) {
		case VAR_name:
			return "The product name. ";
		case VAR_pageId:
			return "The ID for this page. ";
		case VAR_resourceUri:
			return "The resource relative URI for this page. ";
		case VAR_templateUri:
			return "The template relative URI for this page. ";
		case VAR_uri:
			return "The relative URI for this page. ";
		case VAR_url:
			return "The URL for this page. ";
		case VAR_title:
			return "The title of this page. ";
			default:
				return BaseResult.descriptionBaseResult(var);
		}
	}

	public static String classSimpleNameCompanyProduct(String var) {
		switch(var) {
		case VAR_name:
			return "String";
		case VAR_pageId:
			return "String";
		case VAR_resourceUri:
			return "String";
		case VAR_templateUri:
			return "String";
		case VAR_uri:
			return "String";
		case VAR_url:
			return "String";
		case VAR_title:
			return "String";
			default:
				return BaseResult.classSimpleNameBaseResult(var);
		}
	}

	public static Integer htmColumnCompanyProduct(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_title:
			return 1;
			default:
				return BaseResult.htmColumnBaseResult(var);
		}
	}

	public static Integer htmRowCompanyProduct(String var) {
		switch(var) {
		case VAR_name:
			return 3;
		case VAR_pageId:
			return 3;
		case VAR_uri:
			return 3;
		case VAR_url:
			return 3;
			default:
				return BaseResult.htmRowBaseResult(var);
		}
	}

	public static Integer htmCellCompanyProduct(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_pageId:
			return 2;
		case VAR_uri:
			return 2;
		case VAR_url:
			return 2;
			default:
				return BaseResult.htmCellBaseResult(var);
		}
	}

	public static Integer lengthMinCompanyProduct(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMinBaseResult(var);
		}
	}

	public static Integer lengthMaxCompanyProduct(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMaxBaseResult(var);
		}
	}

	public static Integer maxCompanyProduct(String var) {
		switch(var) {
			default:
				return BaseResult.maxBaseResult(var);
		}
	}

	public static Integer minCompanyProduct(String var) {
		switch(var) {
			default:
				return BaseResult.minBaseResult(var);
		}
	}
}