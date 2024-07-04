package org.computate.site.page;

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
import java.lang.Boolean;
import java.lang.String;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class SitePageGen into the class SitePage. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the SitePage API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "Model: true" if you wish to persist these SitePage objects in a relational PostgreSQL database transactionally in the RESTful API. 
 * The code to persist and query the SitePageGen data in the database will then be automatically generated. 
 * </li>
 * <h3>About the SitePage class and it's generated class SitePageGen&lt;BaseResult&gt;: </h3>extends SitePageGen
 * <p>
 * This Java class extends a generated Java class SitePageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage">Find the class SitePage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends SitePageGen<BaseResult>
 * <p>This <code>class SitePage extends SitePageGen&lt;BaseResult&gt;</code>, which means it extends a newly generated SitePageGen. 
 * The generated <code>class SitePageGen extends BaseResult</code> which means that SitePage extends SitePageGen which extends BaseResult. 
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
 * <h2>ApiMethode: POST</h2>
 * <p>This class contains a comment <b>"ApiMethod: POST"</b>, which creates an API "POST". 
 * </p>
 * <h2>ApiMethode: PATCH</h2>
 * <p>This class contains a comment <b>"ApiMethod: PATCH"</b>, which creates an API "PATCH". 
 * </p>
 * <h2>ApiMethode: PUTImport</h2>
 * <p>This class contains a comment <b>"ApiMethod: PUTImport"</b>, which creates an API "PUTImport". 
 * </p>
 * <h2>ApiMethode: SearchPage</h2>
 * <p>This class contains a comment <b>"ApiMethod: SearchPage"</b>, which creates an API "SearchPage". 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: Page"</b>, which groups all of the OpenAPIs for SitePage objects under the tag "Page". 
 * </p>
 * <h2>ApiUri.enUS: /api/page</h2>
 * <p>This class contains a comment <b>"ApiUri: /api/page"</b>, which defines the base API URI for SitePage objects as "/api/page" in the OpenAPI spec. 
 * </p>
 * <h2>Color: 2017-shaded-spruce</h2>
 * <p>This class contains a comment <b>"Color: 2017-shaded-spruce"</b>, which styles the SitePage page "2017-shaded-spruce". 
 * This will reference a CSS class defined by the stylesheets in the project that starts with "w3-". 
 * A css class of "w3-2017-shaded-spruce" is expected to exist in the project stylesheets, and is inspired by W3 CSS colors. 
 * See: <a href="https://www.w3schools.com/w3css/w3css_colors.asp">https://www.w3schools.com/w3css/w3css_colors.asp</a>. 
 * </p>
 * <h2>IconGroup: duotone</h2>
 * <p>This class contains a comment <b>"IconGroup: duotone"</b>, which adds icons on the SitePage page with a group of "duotone". 
 * This will reference a Font Awesome icon group that starts with "fa-" followed by the icon group "duotone", together is "fa-duotone". 
 * A Font Awesome icon group of "duotone" is expected to exist. 
 * The Font Awesome groups currently supported include: solid, regular, light, thin, duotone, sharp, and kit. 
 * See: <a href="https://fontawesome.com/docs/web/dig-deeper/styles">https://fontawesome.com/docs/web/dig-deeper/styles</a>. 
 * </p>
 * <h2>IconName: newspaper</h2>
 * <p>This class contains a comment <b>"IconName: newspaper"</b>, which adds icons on the SitePage page with a name of "newspaper". 
 * This will reference a Font Awesome icon that starts with the icon group "fa-duotone fa-" followed by the icon name, which is "fa-duotone fa-newspaper". 
 * A Font Awesome icon of "fa-duotone fa-newspaper" is expected to exist. 
 * See: <a href="https://fontawesome.com/icons">https://fontawesome.com/icons</a>. 
 * </p>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the SitePage class will inherit the helpful inherited class comments from the super class SitePageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 100</h2>
 * <p>This class contains a comment <b>"Order: 100"</b>, which means this class will be sorted by the given number 100 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 100</h2>
 * <p>This class contains a comment <b>"SqlOrder: 100"</b>, which means this class will be sorted by the given number 100 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.page.SitePagePage. 
 * </p>
 * <h2>SuperPage.enUS: BaseResultPage</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: BaseResultPage"</b>, which identifies the Java super class of the page code by it's class simple name "BaseResultPage". 
 * This means that the newly created class org.computate.site.page.SitePagePage extends org.computate.site.result.BaseResultPage. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the SitePage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * This class contains a comment <b>"Role.enUS: SiteAdmin"</b>, which identifies the default role name "SiteAdmin" of the OAuth2/OpenID Connect user role required to access this SitePage API. 
 * It's possible to reconfigure the roles required to access the SitePage API by configuring an environment variable like this: 
 * </p>
 * <pre>AUTH_ROLE_REQUIRED_SitePage: ["SiteAdmin"]</pre>
 * <h2>AName.enUS: an article</h2>
 * <p>This class contains a comment <b>"AName.enUS: an article"</b>, which identifies the language context to describe a SitePage as "an article". 
 * </p>
 * <p>
 * Delete the class SitePage in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.page in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.page&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class SitePageGen<DEV> extends BaseResult {
	protected static final Logger LOG = LoggerFactory.getLogger(SitePage.class);

	public static final String SitePage_Description_enUS = "An article in the site. ";
	public static final String SitePage_AName_enUS = "an article";
	public static final String SitePage_This_enUS = "this ";
	public static final String SitePage_ThisName_enUS = "this article";
	public static final String SitePage_A_enUS = "a ";
	public static final String SitePage_TheName_enUS = "thearticle";
	public static final String SitePage_NameSingular_enUS = "article";
	public static final String SitePage_NamePlural_enUS = "articles";
	public static final String SitePage_NameActual_enUS = "current article";
	public static final String SitePage_AllName_enUS = "all the articles";
	public static final String SitePage_SearchAllNameBy_enUS = "search articles by ";
	public static final String SitePage_Title_enUS = "articles";
	public static final String SitePage_ThePluralName_enUS = "the articles";
	public static final String SitePage_NoNameFound_enUS = "no article found";
	public static final String SitePage_ApiUri_enUS = "/api/page";
	public static final String SitePage_ApiUriSearchPage_enUS = "/page";
	public static final String SitePage_OfName_enUS = "of article";
	public static final String SitePage_ANameAdjective_enUS = "an article";
	public static final String SitePage_NameAdjectiveSingular_enUS = "article";
	public static final String SitePage_NameAdjectivePlural_enUS = "articles";
	public static final String Search_enUS_Uri = "/api/page";
	public static final String Search_enUS_ImageUri = "/png/api/page-999.png";
	public static final String GET_enUS_Uri = "/api/page/{id}";
	public static final String GET_enUS_ImageUri = "/png/api/page/{id}-999.png";
	public static final String POST_enUS_Uri = "/api/page";
	public static final String POST_enUS_ImageUri = "/png/api/page-999.png";
	public static final String PATCH_enUS_Uri = "/api/page";
	public static final String PATCH_enUS_ImageUri = "/png/api/page-999.png";
	public static final String PUTImport_enUS_Uri = "/api/page-import";
	public static final String PUTImport_enUS_ImageUri = "/png/api/page-import-999.png";
	public static final String SearchPage_enUS_Uri = "/page";
	public static final String SearchPage_enUS_ImageUri = "/png/page-999.png";

	public static final String SitePage_Color = "2017-shaded-spruce";
	public static final String SitePage_IconGroup = "duotone";
	public static final String SitePage_IconName = "newspaper";

	/////////////
	// article //
	/////////////


	/**	 The entity article
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected Boolean article;

	/**	<br> The entity article
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:article">Find the entity article in Solr</a>
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
		this.article = SitePage.staticSetArticle(siteRequest_, o);
	}
	public static Boolean staticSetArticle(SiteRequest siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SitePage articleInit() {
		Wrap<Boolean> articleWrap = new Wrap<Boolean>().var("article");
		if(article == null) {
			_article(articleWrap);
			Optional.ofNullable(articleWrap.getO()).ifPresent(o -> {
				setArticle(o);
			});
		}
		return (SitePage)this;
	}

	public static Boolean staticSearchArticle(SiteRequest siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSearchStrArticle(SiteRequest siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqArticle(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchArticle(siteRequest_, SitePage.staticSetArticle(siteRequest_, o)).toString();
	}

	///////////////
	// githubOrg //
	///////////////


	/**	 The entity githubOrg
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String githubOrg;

	/**	<br> The entity githubOrg
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:githubOrg">Find the entity githubOrg in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _githubOrg(Wrap<String> w);

	public String getGithubOrg() {
		return githubOrg;
	}
	public void setGithubOrg(String o) {
		this.githubOrg = SitePage.staticSetGithubOrg(siteRequest_, o);
	}
	public static String staticSetGithubOrg(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage githubOrgInit() {
		Wrap<String> githubOrgWrap = new Wrap<String>().var("githubOrg");
		if(githubOrg == null) {
			_githubOrg(githubOrgWrap);
			Optional.ofNullable(githubOrgWrap.getO()).ifPresent(o -> {
				setGithubOrg(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchGithubOrg(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrGithubOrg(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqGithubOrg(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchGithubOrg(siteRequest_, SitePage.staticSetGithubOrg(siteRequest_, o)).toString();
	}

	//////////////
	// siteName //
	//////////////


	/**	 The entity siteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String siteName;

	/**	<br> The entity siteName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:siteName">Find the entity siteName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteName(Wrap<String> w);

	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String o) {
		this.siteName = SitePage.staticSetSiteName(siteRequest_, o);
	}
	public static String staticSetSiteName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage siteNameInit() {
		Wrap<String> siteNameWrap = new Wrap<String>().var("siteName");
		if(siteName == null) {
			_siteName(siteNameWrap);
			Optional.ofNullable(siteNameWrap.getO()).ifPresent(o -> {
				setSiteName(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchSiteName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSiteName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSiteName(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchSiteName(siteRequest_, SitePage.staticSetSiteName(siteRequest_, o)).toString();
	}

	/////////////////////
	// siteDisplayName //
	/////////////////////


	/**	 The entity siteDisplayName
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String siteDisplayName;

	/**	<br> The entity siteDisplayName
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:siteDisplayName">Find the entity siteDisplayName in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteDisplayName(Wrap<String> w);

	public String getSiteDisplayName() {
		return siteDisplayName;
	}
	public void setSiteDisplayName(String o) {
		this.siteDisplayName = SitePage.staticSetSiteDisplayName(siteRequest_, o);
	}
	public static String staticSetSiteDisplayName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage siteDisplayNameInit() {
		Wrap<String> siteDisplayNameWrap = new Wrap<String>().var("siteDisplayName");
		if(siteDisplayName == null) {
			_siteDisplayName(siteDisplayNameWrap);
			Optional.ofNullable(siteDisplayNameWrap.getO()).ifPresent(o -> {
				setSiteDisplayName(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchSiteDisplayName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSiteDisplayName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSiteDisplayName(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchSiteDisplayName(siteRequest_, SitePage.staticSetSiteDisplayName(siteRequest_, o)).toString();
	}

	///////////////////
	// sitePublicUrl //
	///////////////////


	/**	 The entity sitePublicUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String sitePublicUrl;

	/**	<br> The entity sitePublicUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:sitePublicUrl">Find the entity sitePublicUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sitePublicUrl(Wrap<String> w);

	public String getSitePublicUrl() {
		return sitePublicUrl;
	}
	public void setSitePublicUrl(String o) {
		this.sitePublicUrl = SitePage.staticSetSitePublicUrl(siteRequest_, o);
	}
	public static String staticSetSitePublicUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage sitePublicUrlInit() {
		Wrap<String> sitePublicUrlWrap = new Wrap<String>().var("sitePublicUrl");
		if(sitePublicUrl == null) {
			_sitePublicUrl(sitePublicUrlWrap);
			Optional.ofNullable(sitePublicUrlWrap.getO()).ifPresent(o -> {
				setSitePublicUrl(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchSitePublicUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSitePublicUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSitePublicUrl(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchSitePublicUrl(siteRequest_, SitePage.staticSetSitePublicUrl(siteRequest_, o)).toString();
	}

	////////////////////
	// mailingListUrl //
	////////////////////


	/**	 The entity mailingListUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String mailingListUrl;

	/**	<br> The entity mailingListUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:mailingListUrl">Find the entity mailingListUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _mailingListUrl(Wrap<String> w);

	public String getMailingListUrl() {
		return mailingListUrl;
	}
	public void setMailingListUrl(String o) {
		this.mailingListUrl = SitePage.staticSetMailingListUrl(siteRequest_, o);
	}
	public static String staticSetMailingListUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage mailingListUrlInit() {
		Wrap<String> mailingListUrlWrap = new Wrap<String>().var("mailingListUrl");
		if(mailingListUrl == null) {
			_mailingListUrl(mailingListUrlWrap);
			Optional.ofNullable(mailingListUrlWrap.getO()).ifPresent(o -> {
				setMailingListUrl(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchMailingListUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrMailingListUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqMailingListUrl(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchMailingListUrl(siteRequest_, SitePage.staticSetMailingListUrl(siteRequest_, o)).toString();
	}

	///////////////
	// quayioOrg //
	///////////////


	/**	 The entity quayioOrg
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String quayioOrg;

	/**	<br> The entity quayioOrg
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:quayioOrg">Find the entity quayioOrg in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _quayioOrg(Wrap<String> w);

	public String getQuayioOrg() {
		return quayioOrg;
	}
	public void setQuayioOrg(String o) {
		this.quayioOrg = SitePage.staticSetQuayioOrg(siteRequest_, o);
	}
	public static String staticSetQuayioOrg(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage quayioOrgInit() {
		Wrap<String> quayioOrgWrap = new Wrap<String>().var("quayioOrg");
		if(quayioOrg == null) {
			_quayioOrg(quayioOrgWrap);
			Optional.ofNullable(quayioOrgWrap.getO()).ifPresent(o -> {
				setQuayioOrg(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchQuayioOrg(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrQuayioOrg(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqQuayioOrg(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchQuayioOrg(siteRequest_, SitePage.staticSetQuayioOrg(siteRequest_, o)).toString();
	}

	////////////////////
	// sitePomGroupId //
	////////////////////


	/**	 The entity sitePomGroupId
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String sitePomGroupId;

	/**	<br> The entity sitePomGroupId
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:sitePomGroupId">Find the entity sitePomGroupId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sitePomGroupId(Wrap<String> w);

	public String getSitePomGroupId() {
		return sitePomGroupId;
	}
	public void setSitePomGroupId(String o) {
		this.sitePomGroupId = SitePage.staticSetSitePomGroupId(siteRequest_, o);
	}
	public static String staticSetSitePomGroupId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage sitePomGroupIdInit() {
		Wrap<String> sitePomGroupIdWrap = new Wrap<String>().var("sitePomGroupId");
		if(sitePomGroupId == null) {
			_sitePomGroupId(sitePomGroupIdWrap);
			Optional.ofNullable(sitePomGroupIdWrap.getO()).ifPresent(o -> {
				setSitePomGroupId(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchSitePomGroupId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSitePomGroupId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSitePomGroupId(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchSitePomGroupId(siteRequest_, SitePage.staticSetSitePomGroupId(siteRequest_, o)).toString();
	}

	///////////////////
	// staticBaseUrl //
	///////////////////


	/**	 The entity staticBaseUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String staticBaseUrl;

	/**	<br> The entity staticBaseUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:staticBaseUrl">Find the entity staticBaseUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _staticBaseUrl(Wrap<String> w);

	public String getStaticBaseUrl() {
		return staticBaseUrl;
	}
	public void setStaticBaseUrl(String o) {
		this.staticBaseUrl = SitePage.staticSetStaticBaseUrl(siteRequest_, o);
	}
	public static String staticSetStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage staticBaseUrlInit() {
		Wrap<String> staticBaseUrlWrap = new Wrap<String>().var("staticBaseUrl");
		if(staticBaseUrl == null) {
			_staticBaseUrl(staticBaseUrlWrap);
			Optional.ofNullable(staticBaseUrlWrap.getO()).ifPresent(o -> {
				setStaticBaseUrl(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqStaticBaseUrl(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchStaticBaseUrl(siteRequest_, SitePage.staticSetStaticBaseUrl(siteRequest_, o)).toString();
	}

	////////////////
	// staticPath //
	////////////////


	/**	 The entity staticPath
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String staticPath;

	/**	<br> The entity staticPath
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:staticPath">Find the entity staticPath in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _staticPath(Wrap<String> w);

	public String getStaticPath() {
		return staticPath;
	}
	public void setStaticPath(String o) {
		this.staticPath = SitePage.staticSetStaticPath(siteRequest_, o);
	}
	public static String staticSetStaticPath(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage staticPathInit() {
		Wrap<String> staticPathWrap = new Wrap<String>().var("staticPath");
		if(staticPath == null) {
			_staticPath(staticPathWrap);
			Optional.ofNullable(staticPathWrap.getO()).ifPresent(o -> {
				setStaticPath(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchStaticPath(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrStaticPath(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqStaticPath(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchStaticPath(siteRequest_, SitePage.staticSetStaticPath(siteRequest_, o)).toString();
	}

	/////////////////
	// siteBaseUrl //
	/////////////////


	/**	 The entity siteBaseUrl
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String siteBaseUrl;

	/**	<br> The entity siteBaseUrl
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:siteBaseUrl">Find the entity siteBaseUrl in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _siteBaseUrl(Wrap<String> w);

	public String getSiteBaseUrl() {
		return siteBaseUrl;
	}
	public void setSiteBaseUrl(String o) {
		this.siteBaseUrl = SitePage.staticSetSiteBaseUrl(siteRequest_, o);
	}
	public static String staticSetSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage siteBaseUrlInit() {
		Wrap<String> siteBaseUrlWrap = new Wrap<String>().var("siteBaseUrl");
		if(siteBaseUrl == null) {
			_siteBaseUrl(siteBaseUrlWrap);
			Optional.ofNullable(siteBaseUrlWrap.getO()).ifPresent(o -> {
				setSiteBaseUrl(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqSiteBaseUrl(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchSiteBaseUrl(siteRequest_, SitePage.staticSetSiteBaseUrl(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:courseNum">Find the entity courseNum in Solr</a>
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
		this.courseNum = SitePage.staticSetCourseNum(siteRequest_, o);
	}
	public static Integer staticSetCourseNum(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SitePage courseNumInit() {
		Wrap<Integer> courseNumWrap = new Wrap<Integer>().var("courseNum");
		if(courseNum == null) {
			_courseNum(courseNumWrap);
			Optional.ofNullable(courseNumWrap.getO()).ifPresent(o -> {
				setCourseNum(o);
			});
		}
		return (SitePage)this;
	}

	public static Integer staticSearchCourseNum(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrCourseNum(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqCourseNum(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchCourseNum(siteRequest_, SitePage.staticSetCourseNum(siteRequest_, o)).toString();
	}

	public Integer sqlCourseNum() {
		return courseNum;
	}

	///////////////
	// lessonNum //
	///////////////


	/**	 The entity lessonNum
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer lessonNum;

	/**	<br> The entity lessonNum
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:lessonNum">Find the entity lessonNum in Solr</a>
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
		this.lessonNum = SitePage.staticSetLessonNum(siteRequest_, o);
	}
	public static Integer staticSetLessonNum(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SitePage lessonNumInit() {
		Wrap<Integer> lessonNumWrap = new Wrap<Integer>().var("lessonNum");
		if(lessonNum == null) {
			_lessonNum(lessonNumWrap);
			Optional.ofNullable(lessonNumWrap.getO()).ifPresent(o -> {
				setLessonNum(o);
			});
		}
		return (SitePage)this;
	}

	public static Integer staticSearchLessonNum(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrLessonNum(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqLessonNum(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchLessonNum(siteRequest_, SitePage.staticSetLessonNum(siteRequest_, o)).toString();
	}

	public Integer sqlLessonNum() {
		return lessonNum;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageId(Wrap<String> w);

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String o) {
		this.pageId = SitePage.staticSetPageId(siteRequest_, o);
	}
	public static String staticSetPageId(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage pageIdInit() {
		Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
		if(pageId == null) {
			_pageId(pageIdWrap);
			Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
				setPageId(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchPageId(siteRequest_, SitePage.staticSetPageId(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:resourceUri">Find the entity resourceUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _resourceUri(Wrap<String> w);

	public String getResourceUri() {
		return resourceUri;
	}
	public void setResourceUri(String o) {
		this.resourceUri = SitePage.staticSetResourceUri(siteRequest_, o);
	}
	public static String staticSetResourceUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage resourceUriInit() {
		Wrap<String> resourceUriWrap = new Wrap<String>().var("resourceUri");
		if(resourceUri == null) {
			_resourceUri(resourceUriWrap);
			Optional.ofNullable(resourceUriWrap.getO()).ifPresent(o -> {
				setResourceUri(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchResourceUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrResourceUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqResourceUri(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchResourceUri(siteRequest_, SitePage.staticSetResourceUri(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:templateUri">Find the entity templateUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _templateUri(Wrap<String> w);

	public String getTemplateUri() {
		return templateUri;
	}
	public void setTemplateUri(String o) {
		this.templateUri = SitePage.staticSetTemplateUri(siteRequest_, o);
	}
	public static String staticSetTemplateUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage templateUriInit() {
		Wrap<String> templateUriWrap = new Wrap<String>().var("templateUri");
		if(templateUri == null) {
			_templateUri(templateUriWrap);
			Optional.ofNullable(templateUriWrap.getO()).ifPresent(o -> {
				setTemplateUri(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchTemplateUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTemplateUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTemplateUri(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchTemplateUri(siteRequest_, SitePage.staticSetTemplateUri(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:uri">Find the entity uri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _uri(Wrap<String> w);

	public String getUri() {
		return uri;
	}
	public void setUri(String o) {
		this.uri = SitePage.staticSetUri(siteRequest_, o);
	}
	public static String staticSetUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage uriInit() {
		Wrap<String> uriWrap = new Wrap<String>().var("uri");
		if(uri == null) {
			_uri(uriWrap);
			Optional.ofNullable(uriWrap.getO()).ifPresent(o -> {
				setUri(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUri(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchUri(siteRequest_, SitePage.staticSetUri(siteRequest_, o)).toString();
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:url">Find the entity url in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _url(Wrap<String> w);

	public String getUrl() {
		return url;
	}
	public void setUrl(String o) {
		this.url = SitePage.staticSetUrl(siteRequest_, o);
	}
	public static String staticSetUrl(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage urlInit() {
		Wrap<String> urlWrap = new Wrap<String>().var("url");
		if(url == null) {
			_url(urlWrap);
			Optional.ofNullable(urlWrap.getO()).ifPresent(o -> {
				setUrl(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchUrl(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrUrl(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqUrl(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchUrl(siteRequest_, SitePage.staticSetUrl(siteRequest_, o)).toString();
	}

	public String sqlUrl() {
		return url;
	}

	////////
	// h1 //
	////////


	/**	 The entity h1
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String h1;

	/**	<br> The entity h1
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:h1">Find the entity h1 in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _h1(Wrap<String> w);

	public String getH1() {
		return h1;
	}
	public void setH1(String o) {
		this.h1 = SitePage.staticSetH1(siteRequest_, o);
	}
	public static String staticSetH1(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage h1Init() {
		Wrap<String> h1Wrap = new Wrap<String>().var("h1");
		if(h1 == null) {
			_h1(h1Wrap);
			Optional.ofNullable(h1Wrap.getO()).ifPresent(o -> {
				setH1(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchH1(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrH1(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqH1(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchH1(siteRequest_, SitePage.staticSetH1(siteRequest_, o)).toString();
	}

	public String sqlH1() {
		return h1;
	}

	////////
	// h2 //
	////////


	/**	 The entity h2
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String h2;

	/**	<br> The entity h2
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:h2">Find the entity h2 in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _h2(Wrap<String> w);

	public String getH2() {
		return h2;
	}
	public void setH2(String o) {
		this.h2 = SitePage.staticSetH2(siteRequest_, o);
	}
	public static String staticSetH2(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage h2Init() {
		Wrap<String> h2Wrap = new Wrap<String>().var("h2");
		if(h2 == null) {
			_h2(h2Wrap);
			Optional.ofNullable(h2Wrap.getO()).ifPresent(o -> {
				setH2(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchH2(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrH2(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqH2(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchH2(siteRequest_, SitePage.staticSetH2(siteRequest_, o)).toString();
	}

	public String sqlH2() {
		return h2;
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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:title">Find the entity title in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _title(Wrap<String> w);

	public String getTitle() {
		return title;
	}
	public void setTitle(String o) {
		this.title = SitePage.staticSetTitle(siteRequest_, o);
	}
	public static String staticSetTitle(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage titleInit() {
		Wrap<String> titleWrap = new Wrap<String>().var("title");
		if(title == null) {
			_title(titleWrap);
			Optional.ofNullable(titleWrap.getO()).ifPresent(o -> {
				setTitle(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchTitle(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrTitle(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqTitle(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchTitle(siteRequest_, SitePage.staticSetTitle(siteRequest_, o)).toString();
	}

	public String sqlTitle() {
		return title;
	}

	////////////
	// author //
	////////////


	/**	 The entity author
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String author;

	/**	<br> The entity author
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:author">Find the entity author in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _author(Wrap<String> w);

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String o) {
		this.author = SitePage.staticSetAuthor(siteRequest_, o);
	}
	public static String staticSetAuthor(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage authorInit() {
		Wrap<String> authorWrap = new Wrap<String>().var("author");
		if(author == null) {
			_author(authorWrap);
			Optional.ofNullable(authorWrap.getO()).ifPresent(o -> {
				setAuthor(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchAuthor(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrAuthor(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqAuthor(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchAuthor(siteRequest_, SitePage.staticSetAuthor(siteRequest_, o)).toString();
	}

	public String sqlAuthor() {
		return author;
	}

	//////////////////
	// pageImageUri //
	//////////////////


	/**	 The entity pageImageUri
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageImageUri;

	/**	<br> The entity pageImageUri
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:pageImageUri">Find the entity pageImageUri in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageImageUri(Wrap<String> w);

	public String getPageImageUri() {
		return pageImageUri;
	}
	public void setPageImageUri(String o) {
		this.pageImageUri = SitePage.staticSetPageImageUri(siteRequest_, o);
	}
	public static String staticSetPageImageUri(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage pageImageUriInit() {
		Wrap<String> pageImageUriWrap = new Wrap<String>().var("pageImageUri");
		if(pageImageUri == null) {
			_pageImageUri(pageImageUriWrap);
			Optional.ofNullable(pageImageUriWrap.getO()).ifPresent(o -> {
				setPageImageUri(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchPageImageUri(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageImageUri(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageUri(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchPageImageUri(siteRequest_, SitePage.staticSetPageImageUri(siteRequest_, o)).toString();
	}

	public String sqlPageImageUri() {
		return pageImageUri;
	}

	////////////////////
	// pageImageWidth //
	////////////////////


	/**	 The entity pageImageWidth
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer pageImageWidth;

	/**	<br> The entity pageImageWidth
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:pageImageWidth">Find the entity pageImageWidth in Solr</a>
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
		this.pageImageWidth = SitePage.staticSetPageImageWidth(siteRequest_, o);
	}
	public static Integer staticSetPageImageWidth(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SitePage pageImageWidthInit() {
		Wrap<Integer> pageImageWidthWrap = new Wrap<Integer>().var("pageImageWidth");
		if(pageImageWidth == null) {
			_pageImageWidth(pageImageWidthWrap);
			Optional.ofNullable(pageImageWidthWrap.getO()).ifPresent(o -> {
				setPageImageWidth(o);
			});
		}
		return (SitePage)this;
	}

	public static Integer staticSearchPageImageWidth(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrPageImageWidth(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageWidth(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchPageImageWidth(siteRequest_, SitePage.staticSetPageImageWidth(siteRequest_, o)).toString();
	}

	/////////////////////
	// pageImageHeight //
	/////////////////////


	/**	 The entity pageImageHeight
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer pageImageHeight;

	/**	<br> The entity pageImageHeight
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:pageImageHeight">Find the entity pageImageHeight in Solr</a>
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
		this.pageImageHeight = SitePage.staticSetPageImageHeight(siteRequest_, o);
	}
	public static Integer staticSetPageImageHeight(SiteRequest siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SitePage pageImageHeightInit() {
		Wrap<Integer> pageImageHeightWrap = new Wrap<Integer>().var("pageImageHeight");
		if(pageImageHeight == null) {
			_pageImageHeight(pageImageHeightWrap);
			Optional.ofNullable(pageImageHeightWrap.getO()).ifPresent(o -> {
				setPageImageHeight(o);
			});
		}
		return (SitePage)this;
	}

	public static Integer staticSearchPageImageHeight(SiteRequest siteRequest_, Integer o) {
		return o;
	}

	public static String staticSearchStrPageImageHeight(SiteRequest siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageHeight(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchPageImageHeight(siteRequest_, SitePage.staticSetPageImageHeight(siteRequest_, o)).toString();
	}

	///////////////////
	// pageImageType //
	///////////////////


	/**	 The entity pageImageType
	 *	 is defined as null before being initialized. 
	 */
	@JsonProperty
	@JsonInclude(Include.NON_NULL)
	protected String pageImageType;

	/**	<br> The entity pageImageType
	 *  is defined as null before being initialized. 
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.page.SitePage&fq=entiteVar_enUS_indexed_string:pageImageType">Find the entity pageImageType in Solr</a>
	 * <br>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageImageType(Wrap<String> c);

	public String getPageImageType() {
		return pageImageType;
	}
	public void setPageImageType(String o) {
		this.pageImageType = SitePage.staticSetPageImageType(siteRequest_, o);
	}
	public static String staticSetPageImageType(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected SitePage pageImageTypeInit() {
		Wrap<String> pageImageTypeWrap = new Wrap<String>().var("pageImageType");
		if(pageImageType == null) {
			_pageImageType(pageImageTypeWrap);
			Optional.ofNullable(pageImageTypeWrap.getO()).ifPresent(o -> {
				setPageImageType(o);
			});
		}
		return (SitePage)this;
	}

	public static String staticSearchPageImageType(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrPageImageType(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqPageImageType(SiteRequest siteRequest_, String o) {
		return SitePage.staticSearchPageImageType(siteRequest_, SitePage.staticSetPageImageType(siteRequest_, o)).toString();
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepSitePage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepSitePage();
	}

	public Future<Void> promiseDeepSitePage() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseSitePage(promise2);
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

	public Future<Void> promiseSitePage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				articleInit();
				githubOrgInit();
				siteNameInit();
				siteDisplayNameInit();
				sitePublicUrlInit();
				mailingListUrlInit();
				quayioOrgInit();
				sitePomGroupIdInit();
				staticBaseUrlInit();
				staticPathInit();
				siteBaseUrlInit();
				courseNumInit();
				lessonNumInit();
				pageIdInit();
				resourceUriInit();
				templateUriInit();
				uriInit();
				urlInit();
				h1Init();
				h2Init();
				titleInit();
				authorInit();
				pageImageUriInit();
				pageImageWidthInit();
				pageImageHeightInit();
				pageImageTypeInit();
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
		return promiseDeepSitePage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSitePage(SiteRequest siteRequest_) {
			super.siteRequestBaseResult(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestSitePage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSitePage(v);
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
	public Object obtainSitePage(String var) {
		SitePage oSitePage = (SitePage)this;
		switch(var) {
			case "article":
				return oSitePage.article;
			case "githubOrg":
				return oSitePage.githubOrg;
			case "siteName":
				return oSitePage.siteName;
			case "siteDisplayName":
				return oSitePage.siteDisplayName;
			case "sitePublicUrl":
				return oSitePage.sitePublicUrl;
			case "mailingListUrl":
				return oSitePage.mailingListUrl;
			case "quayioOrg":
				return oSitePage.quayioOrg;
			case "sitePomGroupId":
				return oSitePage.sitePomGroupId;
			case "staticBaseUrl":
				return oSitePage.staticBaseUrl;
			case "staticPath":
				return oSitePage.staticPath;
			case "siteBaseUrl":
				return oSitePage.siteBaseUrl;
			case "courseNum":
				return oSitePage.courseNum;
			case "lessonNum":
				return oSitePage.lessonNum;
			case "pageId":
				return oSitePage.pageId;
			case "resourceUri":
				return oSitePage.resourceUri;
			case "templateUri":
				return oSitePage.templateUri;
			case "uri":
				return oSitePage.uri;
			case "url":
				return oSitePage.url;
			case "h1":
				return oSitePage.h1;
			case "h2":
				return oSitePage.h2;
			case "title":
				return oSitePage.title;
			case "author":
				return oSitePage.author;
			case "pageImageUri":
				return oSitePage.pageImageUri;
			case "pageImageWidth":
				return oSitePage.pageImageWidth;
			case "pageImageHeight":
				return oSitePage.pageImageHeight;
			case "pageImageType":
				return oSitePage.pageImageType;
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
				o = relateSitePage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateSitePage(String var, Object val) {
		SitePage oSitePage = (SitePage)this;
		switch(var) {
			default:
				return super.relateBaseResult(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetSitePage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSitePage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "article":
			return SitePage.staticSetArticle(siteRequest_, o);
		case "githubOrg":
			return SitePage.staticSetGithubOrg(siteRequest_, o);
		case "siteName":
			return SitePage.staticSetSiteName(siteRequest_, o);
		case "siteDisplayName":
			return SitePage.staticSetSiteDisplayName(siteRequest_, o);
		case "sitePublicUrl":
			return SitePage.staticSetSitePublicUrl(siteRequest_, o);
		case "mailingListUrl":
			return SitePage.staticSetMailingListUrl(siteRequest_, o);
		case "quayioOrg":
			return SitePage.staticSetQuayioOrg(siteRequest_, o);
		case "sitePomGroupId":
			return SitePage.staticSetSitePomGroupId(siteRequest_, o);
		case "staticBaseUrl":
			return SitePage.staticSetStaticBaseUrl(siteRequest_, o);
		case "staticPath":
			return SitePage.staticSetStaticPath(siteRequest_, o);
		case "siteBaseUrl":
			return SitePage.staticSetSiteBaseUrl(siteRequest_, o);
		case "courseNum":
			return SitePage.staticSetCourseNum(siteRequest_, o);
		case "lessonNum":
			return SitePage.staticSetLessonNum(siteRequest_, o);
		case "pageId":
			return SitePage.staticSetPageId(siteRequest_, o);
		case "resourceUri":
			return SitePage.staticSetResourceUri(siteRequest_, o);
		case "templateUri":
			return SitePage.staticSetTemplateUri(siteRequest_, o);
		case "uri":
			return SitePage.staticSetUri(siteRequest_, o);
		case "url":
			return SitePage.staticSetUrl(siteRequest_, o);
		case "h1":
			return SitePage.staticSetH1(siteRequest_, o);
		case "h2":
			return SitePage.staticSetH2(siteRequest_, o);
		case "title":
			return SitePage.staticSetTitle(siteRequest_, o);
		case "author":
			return SitePage.staticSetAuthor(siteRequest_, o);
		case "pageImageUri":
			return SitePage.staticSetPageImageUri(siteRequest_, o);
		case "pageImageWidth":
			return SitePage.staticSetPageImageWidth(siteRequest_, o);
		case "pageImageHeight":
			return SitePage.staticSetPageImageHeight(siteRequest_, o);
		case "pageImageType":
			return SitePage.staticSetPageImageType(siteRequest_, o);
			default:
				return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchSitePage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchSitePage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "article":
			return SitePage.staticSearchArticle(siteRequest_, (Boolean)o);
		case "githubOrg":
			return SitePage.staticSearchGithubOrg(siteRequest_, (String)o);
		case "siteName":
			return SitePage.staticSearchSiteName(siteRequest_, (String)o);
		case "siteDisplayName":
			return SitePage.staticSearchSiteDisplayName(siteRequest_, (String)o);
		case "sitePublicUrl":
			return SitePage.staticSearchSitePublicUrl(siteRequest_, (String)o);
		case "mailingListUrl":
			return SitePage.staticSearchMailingListUrl(siteRequest_, (String)o);
		case "quayioOrg":
			return SitePage.staticSearchQuayioOrg(siteRequest_, (String)o);
		case "sitePomGroupId":
			return SitePage.staticSearchSitePomGroupId(siteRequest_, (String)o);
		case "staticBaseUrl":
			return SitePage.staticSearchStaticBaseUrl(siteRequest_, (String)o);
		case "staticPath":
			return SitePage.staticSearchStaticPath(siteRequest_, (String)o);
		case "siteBaseUrl":
			return SitePage.staticSearchSiteBaseUrl(siteRequest_, (String)o);
		case "courseNum":
			return SitePage.staticSearchCourseNum(siteRequest_, (Integer)o);
		case "lessonNum":
			return SitePage.staticSearchLessonNum(siteRequest_, (Integer)o);
		case "pageId":
			return SitePage.staticSearchPageId(siteRequest_, (String)o);
		case "resourceUri":
			return SitePage.staticSearchResourceUri(siteRequest_, (String)o);
		case "templateUri":
			return SitePage.staticSearchTemplateUri(siteRequest_, (String)o);
		case "uri":
			return SitePage.staticSearchUri(siteRequest_, (String)o);
		case "url":
			return SitePage.staticSearchUrl(siteRequest_, (String)o);
		case "h1":
			return SitePage.staticSearchH1(siteRequest_, (String)o);
		case "h2":
			return SitePage.staticSearchH2(siteRequest_, (String)o);
		case "title":
			return SitePage.staticSearchTitle(siteRequest_, (String)o);
		case "author":
			return SitePage.staticSearchAuthor(siteRequest_, (String)o);
		case "pageImageUri":
			return SitePage.staticSearchPageImageUri(siteRequest_, (String)o);
		case "pageImageWidth":
			return SitePage.staticSearchPageImageWidth(siteRequest_, (Integer)o);
		case "pageImageHeight":
			return SitePage.staticSearchPageImageHeight(siteRequest_, (Integer)o);
		case "pageImageType":
			return SitePage.staticSearchPageImageType(siteRequest_, (String)o);
			default:
				return BaseResult.staticSearchBaseResult(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrSitePage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrSitePage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "article":
			return SitePage.staticSearchStrArticle(siteRequest_, (Boolean)o);
		case "githubOrg":
			return SitePage.staticSearchStrGithubOrg(siteRequest_, (String)o);
		case "siteName":
			return SitePage.staticSearchStrSiteName(siteRequest_, (String)o);
		case "siteDisplayName":
			return SitePage.staticSearchStrSiteDisplayName(siteRequest_, (String)o);
		case "sitePublicUrl":
			return SitePage.staticSearchStrSitePublicUrl(siteRequest_, (String)o);
		case "mailingListUrl":
			return SitePage.staticSearchStrMailingListUrl(siteRequest_, (String)o);
		case "quayioOrg":
			return SitePage.staticSearchStrQuayioOrg(siteRequest_, (String)o);
		case "sitePomGroupId":
			return SitePage.staticSearchStrSitePomGroupId(siteRequest_, (String)o);
		case "staticBaseUrl":
			return SitePage.staticSearchStrStaticBaseUrl(siteRequest_, (String)o);
		case "staticPath":
			return SitePage.staticSearchStrStaticPath(siteRequest_, (String)o);
		case "siteBaseUrl":
			return SitePage.staticSearchStrSiteBaseUrl(siteRequest_, (String)o);
		case "courseNum":
			return SitePage.staticSearchStrCourseNum(siteRequest_, (Integer)o);
		case "lessonNum":
			return SitePage.staticSearchStrLessonNum(siteRequest_, (Integer)o);
		case "pageId":
			return SitePage.staticSearchStrPageId(siteRequest_, (String)o);
		case "resourceUri":
			return SitePage.staticSearchStrResourceUri(siteRequest_, (String)o);
		case "templateUri":
			return SitePage.staticSearchStrTemplateUri(siteRequest_, (String)o);
		case "uri":
			return SitePage.staticSearchStrUri(siteRequest_, (String)o);
		case "url":
			return SitePage.staticSearchStrUrl(siteRequest_, (String)o);
		case "h1":
			return SitePage.staticSearchStrH1(siteRequest_, (String)o);
		case "h2":
			return SitePage.staticSearchStrH2(siteRequest_, (String)o);
		case "title":
			return SitePage.staticSearchStrTitle(siteRequest_, (String)o);
		case "author":
			return SitePage.staticSearchStrAuthor(siteRequest_, (String)o);
		case "pageImageUri":
			return SitePage.staticSearchStrPageImageUri(siteRequest_, (String)o);
		case "pageImageWidth":
			return SitePage.staticSearchStrPageImageWidth(siteRequest_, (Integer)o);
		case "pageImageHeight":
			return SitePage.staticSearchStrPageImageHeight(siteRequest_, (Integer)o);
		case "pageImageType":
			return SitePage.staticSearchStrPageImageType(siteRequest_, (String)o);
			default:
				return BaseResult.staticSearchStrBaseResult(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqSitePage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqSitePage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "article":
			return SitePage.staticSearchFqArticle(siteRequest_, o);
		case "githubOrg":
			return SitePage.staticSearchFqGithubOrg(siteRequest_, o);
		case "siteName":
			return SitePage.staticSearchFqSiteName(siteRequest_, o);
		case "siteDisplayName":
			return SitePage.staticSearchFqSiteDisplayName(siteRequest_, o);
		case "sitePublicUrl":
			return SitePage.staticSearchFqSitePublicUrl(siteRequest_, o);
		case "mailingListUrl":
			return SitePage.staticSearchFqMailingListUrl(siteRequest_, o);
		case "quayioOrg":
			return SitePage.staticSearchFqQuayioOrg(siteRequest_, o);
		case "sitePomGroupId":
			return SitePage.staticSearchFqSitePomGroupId(siteRequest_, o);
		case "staticBaseUrl":
			return SitePage.staticSearchFqStaticBaseUrl(siteRequest_, o);
		case "staticPath":
			return SitePage.staticSearchFqStaticPath(siteRequest_, o);
		case "siteBaseUrl":
			return SitePage.staticSearchFqSiteBaseUrl(siteRequest_, o);
		case "courseNum":
			return SitePage.staticSearchFqCourseNum(siteRequest_, o);
		case "lessonNum":
			return SitePage.staticSearchFqLessonNum(siteRequest_, o);
		case "pageId":
			return SitePage.staticSearchFqPageId(siteRequest_, o);
		case "resourceUri":
			return SitePage.staticSearchFqResourceUri(siteRequest_, o);
		case "templateUri":
			return SitePage.staticSearchFqTemplateUri(siteRequest_, o);
		case "uri":
			return SitePage.staticSearchFqUri(siteRequest_, o);
		case "url":
			return SitePage.staticSearchFqUrl(siteRequest_, o);
		case "h1":
			return SitePage.staticSearchFqH1(siteRequest_, o);
		case "h2":
			return SitePage.staticSearchFqH2(siteRequest_, o);
		case "title":
			return SitePage.staticSearchFqTitle(siteRequest_, o);
		case "author":
			return SitePage.staticSearchFqAuthor(siteRequest_, o);
		case "pageImageUri":
			return SitePage.staticSearchFqPageImageUri(siteRequest_, o);
		case "pageImageWidth":
			return SitePage.staticSearchFqPageImageWidth(siteRequest_, o);
		case "pageImageHeight":
			return SitePage.staticSearchFqPageImageHeight(siteRequest_, o);
		case "pageImageType":
			return SitePage.staticSearchFqPageImageType(siteRequest_, o);
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
					o = persistSitePage(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistSitePage(String var, Object val) {
		String varLower = var.toLowerCase();
			if("coursenum".equals(varLower)) {
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
			} else if("h1".equals(varLower)) {
				if(val instanceof String) {
					setH1((String)val);
				}
				saves.add("h1");
				return val;
			} else if("h2".equals(varLower)) {
				if(val instanceof String) {
					setH2((String)val);
				}
				saves.add("h2");
				return val;
			} else if("title".equals(varLower)) {
				if(val instanceof String) {
					setTitle((String)val);
				}
				saves.add("title");
				return val;
			} else if("author".equals(varLower)) {
				if(val instanceof String) {
					setAuthor((String)val);
				}
				saves.add("author");
				return val;
			} else if("pageimageuri".equals(varLower)) {
				if(val instanceof String) {
					setPageImageUri((String)val);
				}
				saves.add("pageImageUri");
				return val;
		} else {
			return super.persistBaseResult(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateSitePage(doc);
	}
	public void populateSitePage(SolrResponse.Doc doc) {
		SitePage oSitePage = (SitePage)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("courseNum")) {
				Integer courseNum = (Integer)doc.get("courseNum_docvalues_int");
				if(courseNum != null)
					oSitePage.setCourseNum(courseNum);
			}

			if(saves.contains("lessonNum")) {
				Integer lessonNum = (Integer)doc.get("lessonNum_docvalues_int");
				if(lessonNum != null)
					oSitePage.setLessonNum(lessonNum);
			}

			if(saves.contains("pageId")) {
				String pageId = (String)doc.get("pageId_docvalues_string");
				if(pageId != null)
					oSitePage.setPageId(pageId);
			}

			if(saves.contains("resourceUri")) {
				String resourceUri = (String)doc.get("resourceUri_docvalues_string");
				if(resourceUri != null)
					oSitePage.setResourceUri(resourceUri);
			}

			if(saves.contains("templateUri")) {
				String templateUri = (String)doc.get("templateUri_docvalues_string");
				if(templateUri != null)
					oSitePage.setTemplateUri(templateUri);
			}

			if(saves.contains("uri")) {
				String uri = (String)doc.get("uri_docvalues_string");
				if(uri != null)
					oSitePage.setUri(uri);
			}

			if(saves.contains("url")) {
				String url = (String)doc.get("url_docvalues_string");
				if(url != null)
					oSitePage.setUrl(url);
			}

			if(saves.contains("h1")) {
				String h1 = (String)doc.get("h1_docvalues_string");
				if(h1 != null)
					oSitePage.setH1(h1);
			}

			if(saves.contains("h2")) {
				String h2 = (String)doc.get("h2_docvalues_string");
				if(h2 != null)
					oSitePage.setH2(h2);
			}

			if(saves.contains("title")) {
				String title = (String)doc.get("title_docvalues_string");
				if(title != null)
					oSitePage.setTitle(title);
			}

			if(saves.contains("author")) {
				String author = (String)doc.get("author_docvalues_string");
				if(author != null)
					oSitePage.setAuthor(author);
			}

			if(saves.contains("pageImageUri")) {
				String pageImageUri = (String)doc.get("pageImageUri_docvalues_string");
				if(pageImageUri != null)
					oSitePage.setPageImageUri(pageImageUri);
			}
		}

		super.populateBaseResult(doc);
	}

	public void indexSitePage(JsonObject doc) {
		if(courseNum != null) {
			doc.put("courseNum_docvalues_int", courseNum);
		}
		if(lessonNum != null) {
			doc.put("lessonNum_docvalues_int", lessonNum);
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
		if(h1 != null) {
			doc.put("h1_docvalues_string", h1);
		}
		if(h2 != null) {
			doc.put("h2_docvalues_string", h2);
		}
		if(title != null) {
			doc.put("title_docvalues_string", title);
		}
		if(author != null) {
			doc.put("author_docvalues_string", author);
		}
		if(pageImageUri != null) {
			doc.put("pageImageUri_docvalues_string", pageImageUri);
		}
		super.indexBaseResult(doc);

	}

	public static String varStoredSitePage(String entityVar) {
		switch(entityVar) {
			case "courseNum":
				return "courseNum_docvalues_int";
			case "lessonNum":
				return "lessonNum_docvalues_int";
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
			case "h1":
				return "h1_docvalues_string";
			case "h2":
				return "h2_docvalues_string";
			case "title":
				return "title_docvalues_string";
			case "author":
				return "author_docvalues_string";
			case "pageImageUri":
				return "pageImageUri_docvalues_string";
			default:
				return BaseResult.varStoredBaseResult(entityVar);
		}
	}

	public static String varIndexedSitePage(String entityVar) {
		switch(entityVar) {
			case "courseNum":
				return "courseNum_docvalues_int";
			case "lessonNum":
				return "lessonNum_docvalues_int";
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
			case "h1":
				return "h1_docvalues_string";
			case "h2":
				return "h2_docvalues_string";
			case "title":
				return "title_docvalues_string";
			case "author":
				return "author_docvalues_string";
			case "pageImageUri":
				return "pageImageUri_docvalues_string";
			default:
				return BaseResult.varIndexedBaseResult(entityVar);
		}
	}

	public static String searchVarSitePage(String searchVar) {
		switch(searchVar) {
			case "courseNum_docvalues_int":
				return "courseNum";
			case "lessonNum_docvalues_int":
				return "lessonNum";
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
			case "h1_docvalues_string":
				return "h1";
			case "h2_docvalues_string":
				return "h2";
			case "title_docvalues_string":
				return "title";
			case "author_docvalues_string":
				return "author";
			case "pageImageUri_docvalues_string":
				return "pageImageUri";
			default:
				return BaseResult.searchVarBaseResult(searchVar);
		}
	}

	public static String varSearchSitePage(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSearchBaseResult(entityVar);
		}
	}

	public static String varSuggestedSitePage(String entityVar) {
		switch(entityVar) {
			default:
				return BaseResult.varSuggestedBaseResult(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeSitePage(doc);
	}
	public void storeSitePage(SolrResponse.Doc doc) {
		SitePage oSitePage = (SitePage)this;
		SiteRequest siteRequest = oSitePage.getSiteRequest_();

		oSitePage.setCourseNum(Optional.ofNullable(doc.get("courseNum_docvalues_int")).map(v -> v.toString()).orElse(null));
		oSitePage.setLessonNum(Optional.ofNullable(doc.get("lessonNum_docvalues_int")).map(v -> v.toString()).orElse(null));
		oSitePage.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setResourceUri(Optional.ofNullable(doc.get("resourceUri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setTemplateUri(Optional.ofNullable(doc.get("templateUri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setUri(Optional.ofNullable(doc.get("uri_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setUrl(Optional.ofNullable(doc.get("url_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setH1(Optional.ofNullable(doc.get("h1_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setH2(Optional.ofNullable(doc.get("h2_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setTitle(Optional.ofNullable(doc.get("title_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setAuthor(Optional.ofNullable(doc.get("author_docvalues_string")).map(v -> v.toString()).orElse(null));
		oSitePage.setPageImageUri(Optional.ofNullable(doc.get("pageImageUri_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseResult(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSitePage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SitePage) {
			SitePage original = (SitePage)o;
			if(!Objects.equals(courseNum, original.getCourseNum()))
				apiRequest.addVars("courseNum");
			if(!Objects.equals(lessonNum, original.getLessonNum()))
				apiRequest.addVars("lessonNum");
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
			if(!Objects.equals(h1, original.getH1()))
				apiRequest.addVars("h1");
			if(!Objects.equals(h2, original.getH2()))
				apiRequest.addVars("h2");
			if(!Objects.equals(title, original.getTitle()))
				apiRequest.addVars("title");
			if(!Objects.equals(author, original.getAuthor()))
				apiRequest.addVars("author");
			if(!Objects.equals(pageImageUri, original.getPageImageUri()))
				apiRequest.addVars("pageImageUri");
			super.apiRequestBaseResult();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(courseNum).map(v -> "courseNum: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(lessonNum).map(v -> "lessonNum: " + v + "\n").orElse(""));
		sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(resourceUri).map(v -> "resourceUri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(templateUri).map(v -> "templateUri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(uri).map(v -> "uri: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(url).map(v -> "url: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(h1).map(v -> "h1: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(h2).map(v -> "h2: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(title).map(v -> "title: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(author).map(v -> "author: \"" + v + "\"\n" ).orElse(""));
		sb.append(Optional.ofNullable(pageImageUri).map(v -> "pageImageUri: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "SitePage";
	public static final String CLASS_API_ADDRESS_SitePage = "computate.org-enUS-SitePage";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_SitePage;
	}
	public static final String VAR_article = "article";
	public static final String VAR_githubOrg = "githubOrg";
	public static final String VAR_siteName = "siteName";
	public static final String VAR_siteDisplayName = "siteDisplayName";
	public static final String VAR_sitePublicUrl = "sitePublicUrl";
	public static final String VAR_mailingListUrl = "mailingListUrl";
	public static final String VAR_quayioOrg = "quayioOrg";
	public static final String VAR_sitePomGroupId = "sitePomGroupId";
	public static final String VAR_staticBaseUrl = "staticBaseUrl";
	public static final String VAR_staticPath = "staticPath";
	public static final String VAR_siteBaseUrl = "siteBaseUrl";
	public static final String VAR_courseNum = "courseNum";
	public static final String VAR_lessonNum = "lessonNum";
	public static final String VAR_pageId = "pageId";
	public static final String VAR_resourceUri = "resourceUri";
	public static final String VAR_templateUri = "templateUri";
	public static final String VAR_uri = "uri";
	public static final String VAR_url = "url";
	public static final String VAR_h1 = "h1";
	public static final String VAR_h2 = "h2";
	public static final String VAR_title = "title";
	public static final String VAR_author = "author";
	public static final String VAR_pageImageUri = "pageImageUri";
	public static final String VAR_pageImageWidth = "pageImageWidth";
	public static final String VAR_pageImageHeight = "pageImageHeight";
	public static final String VAR_pageImageType = "pageImageType";

	public static List<String> varsQForClass() {
		return SitePage.varsQSitePage(new ArrayList<String>());
	}
	public static List<String> varsQSitePage(List<String> vars) {
		BaseResult.varsQBaseResult(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return SitePage.varsFqSitePage(new ArrayList<String>());
	}
	public static List<String> varsFqSitePage(List<String> vars) {
		vars.add(VAR_courseNum);
		vars.add(VAR_lessonNum);
		vars.add(VAR_pageId);
		vars.add(VAR_resourceUri);
		vars.add(VAR_templateUri);
		vars.add(VAR_uri);
		vars.add(VAR_url);
		vars.add(VAR_author);
		vars.add(VAR_pageImageUri);
		BaseResult.varsFqBaseResult(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return SitePage.varsRangeSitePage(new ArrayList<String>());
	}
	public static List<String> varsRangeSitePage(List<String> vars) {
		vars.add(VAR_courseNum);
		vars.add(VAR_lessonNum);
		BaseResult.varsRangeBaseResult(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_article = "";
	public static final String DISPLAY_NAME_githubOrg = "";
	public static final String DISPLAY_NAME_siteName = "";
	public static final String DISPLAY_NAME_siteDisplayName = "";
	public static final String DISPLAY_NAME_sitePublicUrl = "";
	public static final String DISPLAY_NAME_mailingListUrl = "";
	public static final String DISPLAY_NAME_quayioOrg = "";
	public static final String DISPLAY_NAME_sitePomGroupId = "";
	public static final String DISPLAY_NAME_staticBaseUrl = "";
	public static final String DISPLAY_NAME_staticPath = "";
	public static final String DISPLAY_NAME_siteBaseUrl = "";
	public static final String DISPLAY_NAME_courseNum = "Course Number";
	public static final String DISPLAY_NAME_lessonNum = "Lesson Number";
	public static final String DISPLAY_NAME_pageId = "Page ID";
	public static final String DISPLAY_NAME_resourceUri = "resource URI";
	public static final String DISPLAY_NAME_templateUri = "template URI";
	public static final String DISPLAY_NAME_uri = "URI";
	public static final String DISPLAY_NAME_url = "URL";
	public static final String DISPLAY_NAME_h1 = "header 1";
	public static final String DISPLAY_NAME_h2 = "header 2";
	public static final String DISPLAY_NAME_title = "title";
	public static final String DISPLAY_NAME_author = "author";
	public static final String DISPLAY_NAME_pageImageUri = "imageUri";
	public static final String DISPLAY_NAME_pageImageWidth = "";
	public static final String DISPLAY_NAME_pageImageHeight = "";
	public static final String DISPLAY_NAME_pageImageType = "";

	public static String displayNameForClass(String var) {
		return SitePage.displayNameSitePage(var);
	}
	public static String displayNameSitePage(String var) {
		switch(var) {
		case VAR_article:
			return DISPLAY_NAME_article;
		case VAR_githubOrg:
			return DISPLAY_NAME_githubOrg;
		case VAR_siteName:
			return DISPLAY_NAME_siteName;
		case VAR_siteDisplayName:
			return DISPLAY_NAME_siteDisplayName;
		case VAR_sitePublicUrl:
			return DISPLAY_NAME_sitePublicUrl;
		case VAR_mailingListUrl:
			return DISPLAY_NAME_mailingListUrl;
		case VAR_quayioOrg:
			return DISPLAY_NAME_quayioOrg;
		case VAR_sitePomGroupId:
			return DISPLAY_NAME_sitePomGroupId;
		case VAR_staticBaseUrl:
			return DISPLAY_NAME_staticBaseUrl;
		case VAR_staticPath:
			return DISPLAY_NAME_staticPath;
		case VAR_siteBaseUrl:
			return DISPLAY_NAME_siteBaseUrl;
		case VAR_courseNum:
			return DISPLAY_NAME_courseNum;
		case VAR_lessonNum:
			return DISPLAY_NAME_lessonNum;
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
		case VAR_h1:
			return DISPLAY_NAME_h1;
		case VAR_h2:
			return DISPLAY_NAME_h2;
		case VAR_title:
			return DISPLAY_NAME_title;
		case VAR_author:
			return DISPLAY_NAME_author;
		case VAR_pageImageUri:
			return DISPLAY_NAME_pageImageUri;
		case VAR_pageImageWidth:
			return DISPLAY_NAME_pageImageWidth;
		case VAR_pageImageHeight:
			return DISPLAY_NAME_pageImageHeight;
		case VAR_pageImageType:
			return DISPLAY_NAME_pageImageType;
		default:
			return BaseResult.displayNameBaseResult(var);
		}
	}

	public static String descriptionSitePage(String var) {
		switch(var) {
		case VAR_courseNum:
			return "The course number for this page. ";
		case VAR_lessonNum:
			return "The lesson number for this page. ";
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
		case VAR_h1:
			return "The 1st header of this page. ";
		case VAR_h2:
			return "The 2nd header of this page. ";
		case VAR_title:
			return "The title of this page. ";
		case VAR_author:
			return "The author";
		case VAR_pageImageUri:
			return "The page image URI";
		case VAR_pageImageWidth:
			return "The image width";
		case VAR_pageImageHeight:
			return "The image height";
		case VAR_pageImageType:
			return "The image height";
			default:
				return BaseResult.descriptionBaseResult(var);
		}
	}

	public static String classSimpleNameSitePage(String var) {
		switch(var) {
		case VAR_article:
			return "Boolean";
		case VAR_githubOrg:
			return "String";
		case VAR_siteName:
			return "String";
		case VAR_siteDisplayName:
			return "String";
		case VAR_sitePublicUrl:
			return "String";
		case VAR_mailingListUrl:
			return "String";
		case VAR_quayioOrg:
			return "String";
		case VAR_sitePomGroupId:
			return "String";
		case VAR_staticBaseUrl:
			return "String";
		case VAR_staticPath:
			return "String";
		case VAR_siteBaseUrl:
			return "String";
		case VAR_courseNum:
			return "Integer";
		case VAR_lessonNum:
			return "Integer";
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
		case VAR_h1:
			return "String";
		case VAR_h2:
			return "String";
		case VAR_title:
			return "String";
		case VAR_author:
			return "String";
		case VAR_pageImageUri:
			return "String";
		case VAR_pageImageWidth:
			return "Integer";
		case VAR_pageImageHeight:
			return "Integer";
		case VAR_pageImageType:
			return "String";
			default:
				return BaseResult.classSimpleNameBaseResult(var);
		}
	}

	public static Integer htmColumnSitePage(String var) {
		switch(var) {
		case VAR_url:
			return 4;
			default:
				return BaseResult.htmColumnBaseResult(var);
		}
	}

	public static Integer htmRowSitePage(String var) {
		switch(var) {
		case VAR_pageId:
			return 3;
		case VAR_uri:
			return 3;
		case VAR_url:
			return 3;
		case VAR_author:
			return 3;
		case VAR_pageImageUri:
			return 4;
			default:
				return BaseResult.htmRowBaseResult(var);
		}
	}

	public static Integer htmCellSitePage(String var) {
		switch(var) {
		case VAR_pageId:
			return 1;
		case VAR_uri:
			return 2;
		case VAR_url:
			return 2;
		case VAR_author:
			return 3;
		case VAR_pageImageUri:
			return 1;
			default:
				return BaseResult.htmCellBaseResult(var);
		}
	}

	public static Integer lengthMinSitePage(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMinBaseResult(var);
		}
	}

	public static Integer lengthMaxSitePage(String var) {
		switch(var) {
			default:
				return BaseResult.lengthMaxBaseResult(var);
		}
	}

	public static Integer maxSitePage(String var) {
		switch(var) {
			default:
				return BaseResult.maxBaseResult(var);
		}
	}

	public static Integer minSitePage(String var) {
		switch(var) {
			default:
				return BaseResult.minBaseResult(var);
		}
	}
}