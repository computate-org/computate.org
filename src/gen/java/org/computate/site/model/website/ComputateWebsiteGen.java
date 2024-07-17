package org.computate.site.model.website;

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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ComputateWebsiteGen into the class ComputateWebsite. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the ComputateWebsite API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the ComputateWebsite class and it's generated class ComputateWebsiteGen&lt;BaseModel&gt;: </h3>extends ComputateWebsiteGen
 * <p>
 * This Java class extends a generated Java class ComputateWebsiteGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsite">Find the class ComputateWebsite in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ComputateWebsiteGen<BaseModel>
 * <p>This <code>class ComputateWebsite extends ComputateWebsiteGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ComputateWebsiteGen. 
 * The generated <code>class ComputateWebsiteGen extends BaseModel</code> which means that ComputateWebsite extends ComputateWebsiteGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: website"</b>, which groups all of the OpenAPIs for ComputateWebsite objects under the tag "website". 
 * </p>
 * <h2>ApiUri.enUS: /api/website</h2>
 * <p>This class contains a comment <b>"ApiUri: /api/website"</b>, which defines the base API URI for ComputateWebsite objects as "/api/website" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>IconGroup: regular</h2>
 * <p>This class contains a comment <b>"IconGroup: regular"</b>, which adds icons on the ComputateWebsite page with a group of "regular". 
 * This will reference a Font Awesome icon group that starts with "fa-" followed by the icon group "regular", together is "fa-regular". 
 * A Font Awesome icon group of "regular" is expected to exist. 
 * The Font Awesome groups currently supported include: solid, regular, light, thin, duotone, sharp, and kit. 
 * See: <a href="https://fontawesome.com/docs/web/dig-deeper/styles">https://fontawesome.com/docs/web/dig-deeper/styles</a>. 
 * </p>
 * <h2>IconName: globe-pointer</h2>
 * <p>This class contains a comment <b>"IconName: globe-pointer"</b>, which adds icons on the ComputateWebsite page with a name of "globe-pointer". 
 * This will reference a Font Awesome icon that starts with the icon group "fa-regular fa-" followed by the icon name, which is "fa-regular fa-globe-pointer". 
 * A Font Awesome icon of "fa-regular fa-globe-pointer" is expected to exist. 
 * See: <a href="https://fontawesome.com/icons">https://fontawesome.com/icons</a>. 
 * </p>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ComputateWebsite class will inherit the helpful inherited class comments from the super class ComputateWebsiteGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 5</h2>
 * <p>This class contains a comment <b>"Order: 5"</b>, which means this class will be sorted by the given number 5 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 5</h2>
 * <p>This class contains a comment <b>"SqlOrder: 5"</b>, which means this class will be sorted by the given number 5 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.website.ComputateWebsitePage. 
 * </p>
 * <h2>SuperPage.enUS: BaseModelPage</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: BaseModelPage"</b>, which identifies the Java super class of the page code by it's class simple name "BaseModelPage". 
 * This means that the newly created class org.computate.site.model.website.ComputateWebsitePage extends org.computate.site.model.BaseModelPage. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ComputateWebsite Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * This class contains a comment <b>"Role.enUS: SiteAdmin"</b>, which identifies the default role name "SiteAdmin" of the OAuth2/OpenID Connect user role required to access this ComputateWebsite API. 
 * It's possible to reconfigure the roles required to access the ComputateWebsite API by configuring an environment variable like this: 
 * </p>
 * <pre>AUTH_ROLE_REQUIRED_ComputateWebsite: ["SiteAdmin"]</pre>
 * <h2>AName.enUS: a website</h2>
 * <p>This class contains a comment <b>"AName.enUS: a website"</b>, which identifies the language context to describe a ComputateWebsite as "a website". 
 * </p>
 * <p>
 * Delete the class ComputateWebsite in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsite&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
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
public abstract class ComputateWebsiteGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(ComputateWebsite.class);

	public static final String ComputateWebsite_Description_enUS = "See the live websites built with computate";
	public static final String ComputateWebsite_AName_enUS = "a website";
	public static final String ComputateWebsite_This_enUS = "this ";
	public static final String ComputateWebsite_ThisName_enUS = "this website";
	public static final String ComputateWebsite_A_enUS = "a ";
	public static final String ComputateWebsite_TheName_enUS = "the website";
	public static final String ComputateWebsite_SingularName_enUS = "website";
	public static final String ComputateWebsite_PluralName_enUS = "websites";
	public static final String ComputateWebsite_NameActual_enUS = "current website";
	public static final String ComputateWebsite_AllName_enUS = "all the websites";
	public static final String ComputateWebsite_SearchAllNameBy_enUS = "search websites by ";
	public static final String ComputateWebsite_Title_enUS = "websites";
	public static final String ComputateWebsite_ThePluralName_enUS = "the websites";
	public static final String ComputateWebsite_NoNameFound_enUS = "no website found";
	public static final String ComputateWebsite_ApiUri_enUS = "/api/website";
	public static final String ComputateWebsite_ApiUriSearchPage_enUS = "/website";
	public static final String ComputateWebsite_OfName_enUS = "of website";
	public static final String ComputateWebsite_ANameAdjective_enUS = "a website";
	public static final String ComputateWebsite_NameAdjectiveSingular_enUS = "website";
	public static final String ComputateWebsite_NameAdjectivePlural_enUS = "websites";
	public static final String Search_enUS_Uri = "/api/website";
	public static final String Search_enUS_ImageUri = "/png/api/website-999.png";
	public static final String GET_enUS_Uri = "/api/website/{id}";
	public static final String GET_enUS_ImageUri = "/png/api/website/{id}-999.png";
	public static final String PATCH_enUS_Uri = "/api/website";
	public static final String PATCH_enUS_ImageUri = "/png/api/website-999.png";
	public static final String POST_enUS_Uri = "/api/website";
	public static final String POST_enUS_ImageUri = "/png/api/website-999.png";
	public static final String PUTImport_enUS_Uri = "/api/website-import";
	public static final String PUTImport_enUS_ImageUri = "/png/api/website-import-999.png";
	public static final String SearchPage_enUS_Uri = "/website";
	public static final String SearchPage_enUS_ImageUri = "/png/website-999.png";

	public static final String ComputateWebsite_IconGroup = "regular";
	public static final String ComputateWebsite_IconName = "globe-pointer";

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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.website.ComputateWebsite&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = ComputateWebsite.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateWebsite nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (ComputateWebsite)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return ComputateWebsite.staticSearchName(siteRequest_, ComputateWebsite.staticSetName(siteRequest_, o)).toString();
	}

	public String sqlName() {
		return name;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepComputateWebsite(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepComputateWebsite();
	}

	public Future<Void> promiseDeepComputateWebsite() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseComputateWebsite(promise2);
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

	public Future<Void> promiseComputateWebsite(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
				nameInit();
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
		return promiseDeepComputateWebsite(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestComputateWebsite(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestComputateWebsite(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainComputateWebsite(v);
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
	public Object obtainComputateWebsite(String var) {
		ComputateWebsite oComputateWebsite = (ComputateWebsite)this;
		switch(var) {
			case "name":
				return oComputateWebsite.name;
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
				o = relateComputateWebsite(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateComputateWebsite(String var, Object val) {
		ComputateWebsite oComputateWebsite = (ComputateWebsite)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetComputateWebsite(entityVar,  siteRequest_, o);
	}
	public static Object staticSetComputateWebsite(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return ComputateWebsite.staticSetName(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchComputateWebsite(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchComputateWebsite(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return ComputateWebsite.staticSearchName(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrComputateWebsite(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrComputateWebsite(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return ComputateWebsite.staticSearchStrName(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqComputateWebsite(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqComputateWebsite(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return ComputateWebsite.staticSearchFqName(siteRequest_, o);
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
					o = persistComputateWebsite(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistComputateWebsite(String var, Object val) {
		String varLower = var.toLowerCase();
			if("name".equals(varLower)) {
				if(val instanceof String) {
					setName((String)val);
				}
				saves.add("name");
				return val;
		} else {
			return super.persistBaseModel(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrResponse.Doc doc) {
		populateComputateWebsite(doc);
	}
	public void populateComputateWebsite(SolrResponse.Doc doc) {
		ComputateWebsite oComputateWebsite = (ComputateWebsite)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oComputateWebsite.setName(name);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexComputateWebsite(JsonObject doc) {
		if(name != null) {
			doc.put("name_docvalues_string", name);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredComputateWebsite(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedComputateWebsite(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarComputateWebsite(String searchVar) {
		switch(searchVar) {
			case "name_docvalues_string":
				return "name";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchComputateWebsite(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedComputateWebsite(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeComputateWebsite(doc);
	}
	public void storeComputateWebsite(SolrResponse.Doc doc) {
		ComputateWebsite oComputateWebsite = (ComputateWebsite)this;
		SiteRequest siteRequest = oComputateWebsite.getSiteRequest_();

		oComputateWebsite.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestComputateWebsite() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ComputateWebsite) {
			ComputateWebsite original = (ComputateWebsite)o;
			if(!Objects.equals(name, original.getName()))
				apiRequest.addVars("name");
			super.apiRequestBaseModel();
		}
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(Optional.ofNullable(name).map(v -> "name: \"" + v + "\"\n" ).orElse(""));
		return sb.toString();
	}

	public static final String CLASS_SIMPLE_NAME = "ComputateWebsite";
	public static final String CLASS_API_ADDRESS_ComputateWebsite = "computate.org-enUS-ComputateWebsite";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_ComputateWebsite;
	}
	public static final String VAR_name = "name";

	public static List<String> varsQForClass() {
		return ComputateWebsite.varsQComputateWebsite(new ArrayList<String>());
	}
	public static List<String> varsQComputateWebsite(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return ComputateWebsite.varsFqComputateWebsite(new ArrayList<String>());
	}
	public static List<String> varsFqComputateWebsite(List<String> vars) {
		vars.add(VAR_name);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return ComputateWebsite.varsRangeComputateWebsite(new ArrayList<String>());
	}
	public static List<String> varsRangeComputateWebsite(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_name = "website name";

	public static String displayNameForClass(String var) {
		return ComputateWebsite.displayNameComputateWebsite(var);
	}
	public static String displayNameComputateWebsite(String var) {
		switch(var) {
		case VAR_name:
			return DISPLAY_NAME_name;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionComputateWebsite(String var) {
		switch(var) {
		case VAR_name:
			return "The website name. ";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameComputateWebsite(String var) {
		switch(var) {
		case VAR_name:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnComputateWebsite(String var) {
		switch(var) {
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowComputateWebsite(String var) {
		switch(var) {
		case VAR_name:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellComputateWebsite(String var) {
		switch(var) {
		case VAR_name:
			return 2;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinComputateWebsite(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxComputateWebsite(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxComputateWebsite(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minComputateWebsite(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
