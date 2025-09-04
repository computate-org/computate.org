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
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct">Find the class CompanyProduct in Solr. </a></p>
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
 * <h2>ApiMethode: DELETE</h2>
 * <p>This class contains a comment <b>"ApiMethod: DELETE"</b>, which creates an API "DELETE". 
 * </p>
 * <h2>ApiMethode: PUTImport</h2>
 * <p>This class contains a comment <b>"ApiMethod: PUTImport"</b>, which creates an API "PUTImport". 
 * </p>
 * <h2>ApiMethode: Download</h2>
 * <p>This class contains a comment <b>"ApiMethod: Download"</b>, which creates an API "Download". 
 * </p>
 * <h2>ApiMethode: SearchPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: SearchPage"</b>, which creates an API "SearchPage". 
 * </p>
 * <h2>ApiMethode: EditPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: EditPage"</b>, which creates an API "EditPage". 
 * </p>
 * <h2>ApiMethode: DisplayPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: DisplayPage"</b>, which creates an API "DisplayPage". 
 * </p>
 * <h2>ApiMethode: UserPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: UserPage"</b>, which creates an API "UserPage". 
 * </p>
 * <h2>ApiMethode: DELETEFilter</h2>
 * <p>This class contains a comment <b>"ApiMethod: DELETEFilter"</b>, which creates an API "DELETEFilter". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: products"</b>, which groups all of the OpenAPIs for CompanyProduct objects under the tag "products". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/product</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/product"</b>, which defines the base API URI for CompanyProduct objects as "/en-us/api/product" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
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
 * <h2>SqlOrder: 5</h2>
 * <p>This class contains a comment <b>"SqlOrder: 5"</b>, which means this class will be sorted by the given number 5 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.product.CompanyProductPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.site.model.product.CompanyProductPage extends org.computate.site.page.PageLayout. 
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
 * <h2>AName.enUS: a product</h2>
 * <p>This class contains a comment <b>"AName.enUS: a product"</b>, which identifies the language context to describe a CompanyProduct as "a product". 
 * </p>
 * <p>
 * Delete the class CompanyProduct in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.product in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.product&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyProductGen<DEV> extends BaseResult {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyProduct.class);

	public static final String Description_enUS = "See the computate products that will help you build your own data-driven platforms";
	public static final String AName_enUS = "a product";
	public static final String This_enUS = "this ";
	public static final String ThisName_enUS = "this product";
	public static final String A_enUS = "a ";
	public static final String TheName_enUS = "the product";
	public static final String SingularName_enUS = "product";
	public static final String PluralName_enUS = "products";
	public static final String NameActual_enUS = "current product";
	public static final String AllName_enUS = "all products";
	public static final String SearchAllNameBy_enUS = "search products by ";
	public static final String SearchAllName_enUS = "search products";
	public static final String Title_enUS = "products";
	public static final String ThePluralName_enUS = "the products";
	public static final String NoNameFound_enUS = "no product found";
	public static final String ApiUri_enUS = "/en-us/api/product";
	public static final String ApiUriSearchPage_enUS = "/en-us/search/product";
	public static final String ApiUriEditPage_enUS = "/en-us/edit/product/{pageId}";
	public static final String OfName_enUS = "of product";
	public static final String ANameAdjective_enUS = "a product";
	public static final String NameAdjectiveSingular_enUS = "product";
	public static final String NameAdjectivePlural_enUS = "products";
	public static final String Search_enUS_OpenApiUri = "/en-us/api/product";
	public static final String Search_enUS_StringFormatUri = "/en-us/api/product";
	public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/product";
	public static final String GET_enUS_OpenApiUri = "/en-us/api/product/{pageId}";
	public static final String GET_enUS_StringFormatUri = "/en-us/api/product/%s";
	public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/product/%s";
	public static final String PATCH_enUS_OpenApiUri = "/en-us/api/product";
	public static final String PATCH_enUS_StringFormatUri = "/en-us/api/product";
	public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/product";
	public static final String POST_enUS_OpenApiUri = "/en-us/api/product";
	public static final String POST_enUS_StringFormatUri = "/en-us/api/product";
	public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/product";
	public static final String DELETE_enUS_OpenApiUri = "/en-us/api/product/{pageId}";
	public static final String DELETE_enUS_StringFormatUri = "/en-us/api/product/%s";
	public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/product/%s";
	public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/product-import";
	public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/product-import";
	public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/product-import";
	public static final String Download_enUS_OpenApiUri = "/en-us/download/product/{pageId}";
	public static final String Download_enUS_StringFormatUri = "/en-us/download/product/%s";
	public static final String Download_enUS_StringFormatUrl = "%s/en-us/download/product/%s";
	public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/product";
	public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/product";
	public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/product";
	public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/product/{pageId}";
	public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/product/%s";
	public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/product/%s";
	public static final String DisplayPage_enUS_OpenApiUri = "/en-us/shop/product/{pageId}";
	public static final String DisplayPage_enUS_StringFormatUri = "/en-us/shop/product/%s";
	public static final String DisplayPage_enUS_StringFormatUrl = "%s/en-us/shop/product/%s";
	public static final String UserPage_enUS_OpenApiUri = "/en-us/use/product/{pageId}";
	public static final String UserPage_enUS_StringFormatUri = "/en-us/use/product/%s";
	public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/use/product/%s";
	public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/product";
	public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/product";
	public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/product";

	public static final String Icon = "<i class=\"fa-duotone fa-regular fa-conveyor-belt\"></i>";

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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _description(Wrap<String> w);

	public String getDescription() {
		return description;
	}
	public void setDescription(String o) {
		this.description = CompanyProduct.staticSetDescription(siteRequest_, o);
	}
	public static String staticSetDescription(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProduct descriptionInit() {
		Wrap<String> descriptionWrap = new Wrap<String>().var("description");
		if(description == null) {
			_description(descriptionWrap);
			Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
				setDescription(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchDescription(siteRequest_, CompanyProduct.staticSetDescription(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:price">Find the entity price in Solr</a>
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
		this.price = CompanyProduct.staticSetPrice(siteRequest_, o);
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
	protected CompanyProduct priceInit() {
		Wrap<BigDecimal> priceWrap = new Wrap<BigDecimal>().var("price");
		if(price == null) {
			_price(priceWrap);
			Optional.ofNullable(priceWrap.getO()).ifPresent(o -> {
				setPrice(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static Double staticSearchPrice(SiteRequest siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSearchStrPrice(SiteRequest siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPrice(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchPrice(siteRequest_, CompanyProduct.staticSetPrice(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:emailTemplate">Find the entity emailTemplate in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailTemplate(Wrap<String> w);

	public String getEmailTemplate() {
		return emailTemplate;
	}
	public void setEmailTemplate(String o) {
		this.emailTemplate = CompanyProduct.staticSetEmailTemplate(siteRequest_, o);
	}
	public static String staticSetEmailTemplate(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProduct emailTemplateInit() {
		Wrap<String> emailTemplateWrap = new Wrap<String>().var("emailTemplate");
		if(emailTemplate == null) {
			_emailTemplate(emailTemplateWrap);
			Optional.ofNullable(emailTemplateWrap.getO()).ifPresent(o -> {
				setEmailTemplate(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static String staticSearchEmailTemplate(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrEmailTemplate(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqEmailTemplate(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchEmailTemplate(siteRequest_, CompanyProduct.staticSetEmailTemplate(siteRequest_, o)).toString();
	}

	public String sqlEmailTemplate() {
		return emailTemplate;
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
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:storeUrl">Find the entity storeUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _storeUrl(Wrap<String> w);

	public String getStoreUrl() {
		return storeUrl;
	}
	public void setStoreUrl(String o) {
		this.storeUrl = CompanyProduct.staticSetStoreUrl(siteRequest_, o);
	}
	public static String staticSetStoreUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProduct storeUrlInit() {
		Wrap<String> storeUrlWrap = new Wrap<String>().var("storeUrl");
		if(storeUrl == null) {
			_storeUrl(storeUrlWrap);
			Optional.ofNullable(storeUrlWrap.getO()).ifPresent(o -> {
				setStoreUrl(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static String staticSearchStoreUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrStoreUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqStoreUrl(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchStoreUrl(siteRequest_, CompanyProduct.staticSetStoreUrl(siteRequest_, o)).toString();
	}

	public String sqlStoreUrl() {
		return storeUrl;
	}

	/////////////////
	// downloadUrl //
	/////////////////


	/**	 The entity downloadUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String downloadUrl;

	/**	<br> The entity downloadUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:downloadUrl">Find the entity downloadUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _downloadUrl(Wrap<String> w);

	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String o) {
		this.downloadUrl = CompanyProduct.staticSetDownloadUrl(siteRequest_, o);
	}
	public static String staticSetDownloadUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected CompanyProduct downloadUrlInit() {
		Wrap<String> downloadUrlWrap = new Wrap<String>().var("downloadUrl");
		if(downloadUrl == null) {
			_downloadUrl(downloadUrlWrap);
			Optional.ofNullable(downloadUrlWrap.getO()).ifPresent(o -> {
				setDownloadUrl(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static String staticSearchDownloadUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrDownloadUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqDownloadUrl(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchDownloadUrl(siteRequest_, CompanyProduct.staticSetDownloadUrl(siteRequest_, o)).toString();
	}

	public String sqlDownloadUrl() {
		return downloadUrl;
	}

	////////////////
	// productNum //
	////////////////


	/**	 The entity productNum
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer productNum;

	/**	<br> The entity productNum
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.product.CompanyProduct&fq=entiteVar_enUS_indexed_string:productNum">Find the entity productNum in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _productNum(Wrap<Integer> w);

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	@JsonIgnore
	public void setProductNum(String o) {
		this.productNum = CompanyProduct.staticSetProductNum(siteRequest_, o);
	}
	public static Integer staticSetProductNum(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected CompanyProduct productNumInit() {
		Wrap<Integer> productNumWrap = new Wrap<Integer>().var("productNum");
		if(productNum == null) {
			_productNum(productNumWrap);
			Optional.ofNullable(productNumWrap.getO()).ifPresent(o -> {
				setProductNum(o);
			});
		}
		return (CompanyProduct)this;
	}

	public static Integer staticSearchProductNum(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrProductNum(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqProductNum(SiteRequest siteRequest_, String o) {
		return CompanyProduct.staticSearchProductNum(siteRequest_, CompanyProduct.staticSetProductNum(siteRequest_, o)).toString();
	}

	public Integer sqlProductNum() {
		return productNum;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<CompanyProductGen<DEV>> promiseDeepCompanyProduct(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyProduct();
	}

	public Future<CompanyProductGen<DEV>> promiseDeepCompanyProduct() {
		Promise<CompanyProductGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyProduct(promise2);
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

	public Future<Void> promiseCompanyProduct(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				nameInit();
				descriptionInit();
				priceInit();
				pageIdInit();
				emailTemplateInit();
				storeUrlInit();
				downloadUrlInit();
				productNumInit();
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

	@Override public Future<? extends CompanyProductGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
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
			case "description":
				return oCompanyProduct.description;
			case "price":
				return oCompanyProduct.price;
			case "pageId":
				return oCompanyProduct.pageId;
			case "emailTemplate":
				return oCompanyProduct.emailTemplate;
			case "storeUrl":
				return oCompanyProduct.storeUrl;
			case "downloadUrl":
				return oCompanyProduct.downloadUrl;
			case "productNum":
				return oCompanyProduct.productNum;
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

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, CompanyProduct o) {
		return staticSetCompanyProduct(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetCompanyProduct(String entityVar, SiteRequest siteRequest_, String v, CompanyProduct o) {
		switch(entityVar) {
		case "name":
			return CompanyProduct.staticSetName(siteRequest_, v);
		case "description":
			return CompanyProduct.staticSetDescription(siteRequest_, v);
		case "price":
			return CompanyProduct.staticSetPrice(siteRequest_, v);
		case "pageId":
			return CompanyProduct.staticSetPageId(siteRequest_, v);
		case "emailTemplate":
			return CompanyProduct.staticSetEmailTemplate(siteRequest_, v);
		case "storeUrl":
			return CompanyProduct.staticSetStoreUrl(siteRequest_, v);
		case "downloadUrl":
			return CompanyProduct.staticSetDownloadUrl(siteRequest_, v);
		case "productNum":
			return CompanyProduct.staticSetProductNum(siteRequest_, v);
			default:
				return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, v, o);
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
		case "description":
			return CompanyProduct.staticSearchDescription(siteRequest_, (String)o);
		case "price":
			return CompanyProduct.staticSearchPrice(siteRequest_, (BigDecimal)o);
		case "pageId":
			return CompanyProduct.staticSearchPageId(siteRequest_, (String)o);
		case "emailTemplate":
			return CompanyProduct.staticSearchEmailTemplate(siteRequest_, (String)o);
		case "storeUrl":
			return CompanyProduct.staticSearchStoreUrl(siteRequest_, (String)o);
		case "downloadUrl":
			return CompanyProduct.staticSearchDownloadUrl(siteRequest_, (String)o);
		case "productNum":
			return CompanyProduct.staticSearchProductNum(siteRequest_, (Integer)o);
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
		case "description":
			return CompanyProduct.staticSearchStrDescription(siteRequest_, (String)o);
		case "price":
			return CompanyProduct.staticSearchStrPrice(siteRequest_, (Double)o);
		case "pageId":
			return CompanyProduct.staticSearchStrPageId(siteRequest_, (String)o);
		case "emailTemplate":
			return CompanyProduct.staticSearchStrEmailTemplate(siteRequest_, (String)o);
		case "storeUrl":
			return CompanyProduct.staticSearchStrStoreUrl(siteRequest_, (String)o);
		case "downloadUrl":
			return CompanyProduct.staticSearchStrDownloadUrl(siteRequest_, (String)o);
		case "productNum":
			return CompanyProduct.staticSearchStrProductNum(siteRequest_, (Integer)o);
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
		case "description":
			return CompanyProduct.staticSearchFqDescription(siteRequest_, o);
		case "price":
			return CompanyProduct.staticSearchFqPrice(siteRequest_, o);
		case "pageId":
			return CompanyProduct.staticSearchFqPageId(siteRequest_, o);
		case "emailTemplate":
			return CompanyProduct.staticSearchFqEmailTemplate(siteRequest_, o);
		case "storeUrl":
			return CompanyProduct.staticSearchFqStoreUrl(siteRequest_, o);
		case "downloadUrl":
			return CompanyProduct.staticSearchFqDownloadUrl(siteRequest_, o);
		case "productNum":
			return CompanyProduct.staticSearchFqProductNum(siteRequest_, o);
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
			} else if("emailtemplate".equals(varLower)) {
				if(val instanceof String) {
					setEmailTemplate((String)val);
				}
				saves.add("emailTemplate");
				return val;
			} else if("storeurl".equals(varLower)) {
				if(val instanceof String) {
					setStoreUrl((String)val);
				}
				saves.add("storeUrl");
				return val;
			} else if("downloadurl".equals(varLower)) {
				if(val instanceof String) {
					setDownloadUrl((String)val);
				}
				saves.add("downloadUrl");
				return val;
			} else if("productnum".equals(varLower)) {
				if(val instanceof Integer) {
					setProductNum((Integer)val);
				} else {
					setProductNum(val == null ? null : val.toString());
				}
				saves.add("productNum");
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

			if(saves.contains("description")) {
				String description = (String)doc.get("description_docvalues_string");
				if(description != null)
					oCompanyProduct.setDescription(description);
			}

			if(saves.contains("price")) {
				Double price = (Double)doc.get("price_docvalues_double");
				if(price != null)
					oCompanyProduct.setPrice(price);
			}

			if(saves.contains("pageId")) {
				String pageId = (String)doc.get("pageId_docvalues_string");
				if(pageId != null)
					oCompanyProduct.setPageId(pageId);
			}

			if(saves.contains("emailTemplate")) {
				String emailTemplate = (String)doc.get("emailTemplate_docvalues_string");
				if(emailTemplate != null)
					oCompanyProduct.setEmailTemplate(emailTemplate);
			}

			if(saves.contains("storeUrl")) {
				String storeUrl = (String)doc.get("storeUrl_docvalues_string");
				if(storeUrl != null)
					oCompanyProduct.setStoreUrl(storeUrl);
			}

			if(saves.contains("downloadUrl")) {
				String downloadUrl = (String)doc.get("downloadUrl_docvalues_string");
				if(downloadUrl != null)
					oCompanyProduct.setDownloadUrl(downloadUrl);
			}

			if(saves.contains("productNum")) {
				Integer productNum = (Integer)doc.get("productNum_docvalues_int");
				if(productNum != null)
					oCompanyProduct.setProductNum(productNum);
			}
		}

		super.populateBaseResult(doc);
	}

	public void indexCompanyProduct(JsonObject doc) {
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
		if(emailTemplate != null) {
			doc.put("emailTemplate_docvalues_string", emailTemplate);
		}
		if(storeUrl != null) {
			doc.put("storeUrl_docvalues_string", storeUrl);
		}
		if(downloadUrl != null) {
			doc.put("downloadUrl_docvalues_string", downloadUrl);
		}
		if(productNum != null) {
			doc.put("productNum_docvalues_int", productNum);
		}
		super.indexBaseResult(doc);

	}

	public static String varStoredCompanyProduct(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "price":
				return "price_docvalues_double";
			case "pageId":
				return "pageId_docvalues_string";
			case "emailTemplate":
				return "emailTemplate_docvalues_string";
			case "storeUrl":
				return "storeUrl_docvalues_string";
			case "downloadUrl":
				return "downloadUrl_docvalues_string";
			case "productNum":
				return "productNum_docvalues_int";
			default:
				return BaseResult.varStoredBaseResult(entityVar);
		}
	}

	public static String varIndexedCompanyProduct(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			case "description":
				return "description_docvalues_string";
			case "price":
				return "price_docvalues_double";
			case "pageId":
				return "pageId_docvalues_string";
			case "emailTemplate":
				return "emailTemplate_docvalues_string";
			case "storeUrl":
				return "storeUrl_docvalues_string";
			case "downloadUrl":
				return "downloadUrl_docvalues_string";
			case "productNum":
				return "productNum_docvalues_int";
			default:
				return BaseResult.varIndexedBaseResult(entityVar);
		}
	}

	public static String searchVarCompanyProduct(String searchVar) {
		switch(searchVar) {
			case "name_docvalues_string":
				return "name";
			case "description_docvalues_string":
				return "description";
			case "price_docvalues_double":
				return "price";
			case "pageId_docvalues_string":
				return "pageId";
			case "emailTemplate_docvalues_string":
				return "emailTemplate";
			case "storeUrl_docvalues_string":
				return "storeUrl";
			case "downloadUrl_docvalues_string":
				return "downloadUrl";
			case "productNum_docvalues_int":
				return "productNum";
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
		oCompanyProduct.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setPrice(Optional.ofNullable(doc.get("price_docvalues_double")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setEmailTemplate(Optional.ofNullable(doc.get("emailTemplate_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setStoreUrl(Optional.ofNullable(doc.get("storeUrl_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setDownloadUrl(Optional.ofNullable(doc.get("downloadUrl_docvalues_string")).map(v -> v.toString()).orElse(null));
		oCompanyProduct.setProductNum(Optional.ofNullable(doc.get("productNum_docvalues_int")).map(v -> v.toString()).orElse(null));

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
			if(!Objects.equals(description, original.getDescription()))
				apiRequest.addVars("description");
			if(!Objects.equals(price, original.getPrice()) && price != null && original.getPrice() != null && price.compareTo(original.getPrice()) != 0)
				apiRequest.addVars("price");
			if(!Objects.equals(pageId, original.getPageId()))
				apiRequest.addVars("pageId");
			if(!Objects.equals(emailTemplate, original.getEmailTemplate()))
				apiRequest.addVars("emailTemplate");
			if(!Objects.equals(storeUrl, original.getStoreUrl()))
				apiRequest.addVars("storeUrl");
			if(!Objects.equals(downloadUrl, original.getDownloadUrl()))
				apiRequest.addVars("downloadUrl");
			if(!Objects.equals(productNum, original.getProductNum()))
				apiRequest.addVars("productNum");
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
		sb.append(Optional.ofNullable(emailTemplate).map(v -> "emailTemplate: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(storeUrl).map(v -> "storeUrl: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(downloadUrl).map(v -> "downloadUrl: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(productNum).map(v -> "productNum: " + v + "\n").orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "CompanyProduct";
	public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.product.CompanyProduct";
	public static final String CLASS_AUTH_RESOURCE = "COMPANYPRODUCT";
	public static final String CLASS_API_ADDRESS_CompanyProduct = "computate.org-enUS-CompanyProduct";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_CompanyProduct;
	}
	public static final String VAR_name = "name";
	public static final String VAR_description = "description";
	public static final String VAR_price = "price";
	public static final String VAR_pageId = "pageId";
	public static final String VAR_emailTemplate = "emailTemplate";
	public static final String VAR_storeUrl = "storeUrl";
	public static final String VAR_downloadUrl = "downloadUrl";
	public static final String VAR_productNum = "productNum";

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
		vars.add(VAR_description);
		vars.add(VAR_price);
		vars.add(VAR_pageId);
		vars.add(VAR_emailTemplate);
		vars.add(VAR_storeUrl);
		vars.add(VAR_downloadUrl);
		vars.add(VAR_productNum);
		BaseResult.varsFqBaseResult(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return CompanyProduct.varsRangeCompanyProduct(new ArrayList<String>());
	}
	public static List<String> varsRangeCompanyProduct(List<String> vars) {
		vars.add(VAR_price);
		vars.add(VAR_productNum);
		BaseResult.varsRangeBaseResult(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_name = "product name";
	public static final String DISPLAY_NAME_description = "product description";
	public static final String DISPLAY_NAME_price = "price";
	public static final String DISPLAY_NAME_pageId = "Page ID";
	public static final String DISPLAY_NAME_emailTemplate = "email template";
	public static final String DISPLAY_NAME_storeUrl = "store URL";
	public static final String DISPLAY_NAME_downloadUrl = "download URL";
	public static final String DISPLAY_NAME_productNum = "Product Number";

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
		return CompanyProduct.NameAdjectiveSingular_enUS;
	}

	@Override
	public String descriptionForClass() {
		return description;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return "%s/en-us/edit/product/%s";
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return "%s/en-us/shop/product/%s";
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return "%s/en-us/use/product/%s";
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return "%s/en-us/download/product/%s";
	}

	public static String displayNameForClass(String var) {
		return CompanyProduct.displayNameCompanyProduct(var);
	}
	public static String displayNameCompanyProduct(String var) {
		switch(var) {
		case VAR_name:
			return DISPLAY_NAME_name;
		case VAR_description:
			return DISPLAY_NAME_description;
		case VAR_price:
			return DISPLAY_NAME_price;
		case VAR_pageId:
			return DISPLAY_NAME_pageId;
		case VAR_emailTemplate:
			return DISPLAY_NAME_emailTemplate;
		case VAR_storeUrl:
			return DISPLAY_NAME_storeUrl;
		case VAR_downloadUrl:
			return DISPLAY_NAME_downloadUrl;
		case VAR_productNum:
			return DISPLAY_NAME_productNum;
		default:
			return BaseResult.displayNameBaseResult(var);
		}
	}

	public static String descriptionCompanyProduct(String var) {
		if(var == null)
			return null;
		switch(var) {
		case VAR_name:
			return "The product name. ";
		case VAR_description:
			return "The product description. ";
		case VAR_price:
			return "The price of the product per developer. ";
		case VAR_pageId:
			return "The ID for this page. ";
		case VAR_emailTemplate:
			return "The HTML email template for this product. ";
		case VAR_storeUrl:
			return "The store URL for this page. ";
		case VAR_downloadUrl:
			return "The download URL for this page. ";
		case VAR_productNum:
			return "The product number for this page. ";
			default:
				return BaseResult.descriptionBaseResult(var);
		}
	}

	public static String classSimpleNameCompanyProduct(String var) {
		switch(var) {
		case VAR_name:
			return "String";
		case VAR_description:
			return "String";
		case VAR_price:
			return "BigDecimal";
		case VAR_pageId:
			return "String";
		case VAR_emailTemplate:
			return "String";
		case VAR_storeUrl:
			return "String";
		case VAR_downloadUrl:
			return "String";
		case VAR_productNum:
			return "Integer";
			default:
				return BaseResult.classSimpleNameBaseResult(var);
		}
	}

	public static Integer htmColumnCompanyProduct(String var) {
		switch(var) {
		case VAR_name:
			return 0;
		case VAR_description:
			return 1;
			default:
				return BaseResult.htmColumnBaseResult(var);
		}
	}

	public static Integer htmRowCompanyProduct(String var) {
		switch(var) {
		case VAR_name:
			return 3;
		case VAR_description:
			return 3;
		case VAR_price:
			return 3;
		case VAR_pageId:
			return 99;
			default:
				return BaseResult.htmRowBaseResult(var);
		}
	}

	public static Integer htmCellCompanyProduct(String var) {
		switch(var) {
		case VAR_name:
			return 1;
		case VAR_description:
			return 2;
		case VAR_price:
			return 3;
		case VAR_pageId:
			return 1;
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
