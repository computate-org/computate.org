package org.computate.site.model.research;

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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ComputateResearchGen into the class ComputateResearch. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the ComputateResearch API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the ComputateResearch class and it's generated class ComputateResearchGen&lt;BaseModel&gt;: </h3>extends ComputateResearchGen
 * <p>
 * This Java class extends a generated Java class ComputateResearchGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearch">Find the class ComputateResearch in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ComputateResearchGen<BaseModel>
 * <p>This <code>class ComputateResearch extends ComputateResearchGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ComputateResearchGen. 
 * The generated <code>class ComputateResearchGen extends BaseModel</code> which means that ComputateResearch extends ComputateResearchGen which extends BaseModel. 
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
 * <p>This class contains a comment <b>"ApiTag: research"</b>, which groups all of the OpenAPIs for ComputateResearch objects under the tag "research". 
 * </p>
 * <h2>ApiUri.enUS: /api/research</h2>
 * <p>This class contains a comment <b>"ApiUri: /api/research"</b>, which defines the base API URI for ComputateResearch objects as "/api/research" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>IconGroup: duotone</h2>
 * <p>This class contains a comment <b>"IconGroup: duotone"</b>, which adds icons on the ComputateResearch page with a group of "duotone". 
 * This will reference a Font Awesome icon group that starts with "fa-" followed by the icon group "duotone", together is "fa-duotone". 
 * A Font Awesome icon group of "duotone" is expected to exist. 
 * The Font Awesome groups currently supported include: solid, regular, light, thin, duotone, sharp, and kit. 
 * See: <a href="https://fontawesome.com/docs/web/dig-deeper/styles">https://fontawesome.com/docs/web/dig-deeper/styles</a>. 
 * </p>
 * <h2>IconName: school</h2>
 * <p>This class contains a comment <b>"IconName: school"</b>, which adds icons on the ComputateResearch page with a name of "school". 
 * This will reference a Font Awesome icon that starts with the icon group "fa-duotone fa-" followed by the icon name, which is "fa-duotone fa-school". 
 * A Font Awesome icon of "fa-duotone fa-school" is expected to exist. 
 * See: <a href="https://fontawesome.com/icons">https://fontawesome.com/icons</a>. 
 * </p>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ComputateResearch class will inherit the helpful inherited class comments from the super class ComputateResearchGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 4</h2>
 * <p>This class contains a comment <b>"Order: 4"</b>, which means this class will be sorted by the given number 4 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 4</h2>
 * <p>This class contains a comment <b>"SqlOrder: 4"</b>, which means this class will be sorted by the given number 4 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.site.model.research.ComputateResearchPage. 
 * </p>
 * <h2>SuperPage.enUS: BaseModelPage</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: BaseModelPage"</b>, which identifies the Java super class of the page code by it's class simple name "BaseModelPage". 
 * This means that the newly created class org.computate.site.model.research.ComputateResearchPage extends org.computate.site.model.BaseModelPage. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ComputateResearch Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * This class contains a comment <b>"Role.enUS: SiteAdmin"</b>, which identifies the default role name "SiteAdmin" of the OAuth2/OpenID Connect user role required to access this ComputateResearch API. 
 * It's possible to reconfigure the roles required to access the ComputateResearch API by configuring an environment variable like this: 
 * </p>
 * <pre>AUTH_ROLE_REQUIRED_ComputateResearch: ["SiteAdmin"]</pre>
 * <h2>AName.enUS: a research</h2>
 * <p>This class contains a comment <b>"AName.enUS: a research"</b>, which identifies the language context to describe a ComputateResearch as "a research". 
 * </p>
 * <p>
 * Delete the class ComputateResearch in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearch&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.research in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.research&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing:443/solr/computate/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ComputateResearchGen<DEV> extends BaseModel {
	protected static final Logger LOG = LoggerFactory.getLogger(ComputateResearch.class);

	public static final String ComputateResearch_Description_enUS = "Read about the computate project related research going on at Red Hat research";
	public static final String ComputateResearch_AName_enUS = "a research";
	public static final String ComputateResearch_This_enUS = "this ";
	public static final String ComputateResearch_ThisName_enUS = "this research";
	public static final String ComputateResearch_A_enUS = "a ";
	public static final String ComputateResearch_TheName_enUS = "the research";
	public static final String ComputateResearch_SingularName_enUS = "research";
	public static final String ComputateResearch_PluralName_enUS = "research";
	public static final String ComputateResearch_NameActual_enUS = "current research";
	public static final String ComputateResearch_AllName_enUS = "all the research";
	public static final String ComputateResearch_SearchAllNameBy_enUS = "search research by ";
	public static final String ComputateResearch_Title_enUS = "research";
	public static final String ComputateResearch_ThePluralName_enUS = "the research";
	public static final String ComputateResearch_NoNameFound_enUS = "no research found";
	public static final String ComputateResearch_ApiUri_enUS = "/api/research";
	public static final String ComputateResearch_ApiUriSearchPage_enUS = "/research";
	public static final String ComputateResearch_OfName_enUS = "of research";
	public static final String ComputateResearch_ANameAdjective_enUS = "a research";
	public static final String ComputateResearch_NameAdjectiveSingular_enUS = "research";
	public static final String ComputateResearch_NameAdjectivePlural_enUS = "research";
	public static final String Search_enUS_Uri = "/api/research";
	public static final String Search_enUS_ImageUri = "/png/api/research-999.png";
	public static final String GET_enUS_Uri = "/api/research/{id}";
	public static final String GET_enUS_ImageUri = "/png/api/research/{id}-999.png";
	public static final String PATCH_enUS_Uri = "/api/research";
	public static final String PATCH_enUS_ImageUri = "/png/api/research-999.png";
	public static final String POST_enUS_Uri = "/api/research";
	public static final String POST_enUS_ImageUri = "/png/api/research-999.png";
	public static final String PUTImport_enUS_Uri = "/api/research-import";
	public static final String PUTImport_enUS_ImageUri = "/png/api/research-import-999.png";
	public static final String SearchPage_enUS_Uri = "/research";
	public static final String SearchPage_enUS_ImageUri = "/png/research-999.png";

	public static final String ComputateResearch_IconGroup = "duotone";
	public static final String ComputateResearch_IconName = "school";

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
	 * <br><a href="https://solr.apps-crc.testing:443/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.ComputateResearch&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
	 * <br>
	 * @param w is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _name(Wrap<String> w);

	public String getName() {
		return name;
	}
	public void setName(String o) {
		this.name = ComputateResearch.staticSetName(siteRequest_, o);
	}
	public static String staticSetName(SiteRequest siteRequest_, String o) {
		return o;
	}
	protected ComputateResearch nameInit() {
		Wrap<String> nameWrap = new Wrap<String>().var("name");
		if(name == null) {
			_name(nameWrap);
			Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
				setName(o);
			});
		}
		return (ComputateResearch)this;
	}

	public static String staticSearchName(SiteRequest siteRequest_, String o) {
		return o;
	}

	public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
		return ComputateResearch.staticSearchName(siteRequest_, ComputateResearch.staticSetName(siteRequest_, o)).toString();
	}

	public String sqlName() {
		return name;
	}

	//////////////
	// initDeep //
	//////////////

	public Future<Void> promiseDeepComputateResearch(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepComputateResearch();
	}

	public Future<Void> promiseDeepComputateResearch() {
		Promise<Void> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseComputateResearch(promise2);
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

	public Future<Void> promiseComputateResearch(Promise<Void> promise) {
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
		return promiseDeepComputateResearch(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestComputateResearch(SiteRequest siteRequest_) {
			super.siteRequestBaseModel(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestComputateResearch(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainComputateResearch(v);
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
	public Object obtainComputateResearch(String var) {
		ComputateResearch oComputateResearch = (ComputateResearch)this;
		switch(var) {
			case "name":
				return oComputateResearch.name;
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
				o = relateComputateResearch(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateComputateResearch(String var, Object val) {
		ComputateResearch oComputateResearch = (ComputateResearch)this;
		switch(var) {
			default:
				return super.relateBaseModel(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetComputateResearch(entityVar,  siteRequest_, o);
	}
	public static Object staticSetComputateResearch(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return ComputateResearch.staticSetName(siteRequest_, o);
			default:
				return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchComputateResearch(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchComputateResearch(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return ComputateResearch.staticSearchName(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrComputateResearch(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrComputateResearch(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
		case "name":
			return ComputateResearch.staticSearchStrName(siteRequest_, (String)o);
			default:
				return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqComputateResearch(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqComputateResearch(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
		case "name":
			return ComputateResearch.staticSearchFqName(siteRequest_, o);
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
					o = persistComputateResearch(v, val);
				else if(o instanceof BaseModel) {
					BaseModel oBaseModel = (BaseModel)o;
					o = oBaseModel.persistForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object persistComputateResearch(String var, Object val) {
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
		populateComputateResearch(doc);
	}
	public void populateComputateResearch(SolrResponse.Doc doc) {
		ComputateResearch oComputateResearch = (ComputateResearch)this;
		saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
		if(saves != null) {

			if(saves.contains("name")) {
				String name = (String)doc.get("name_docvalues_string");
				if(name != null)
					oComputateResearch.setName(name);
			}
		}

		super.populateBaseModel(doc);
	}

	public void indexComputateResearch(JsonObject doc) {
		if(name != null) {
			doc.put("name_docvalues_string", name);
		}
		super.indexBaseModel(doc);

	}

	public static String varStoredComputateResearch(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			default:
				return BaseModel.varStoredBaseModel(entityVar);
		}
	}

	public static String varIndexedComputateResearch(String entityVar) {
		switch(entityVar) {
			case "name":
				return "name_docvalues_string";
			default:
				return BaseModel.varIndexedBaseModel(entityVar);
		}
	}

	public static String searchVarComputateResearch(String searchVar) {
		switch(searchVar) {
			case "name_docvalues_string":
				return "name";
			default:
				return BaseModel.searchVarBaseModel(searchVar);
		}
	}

	public static String varSearchComputateResearch(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSearchBaseModel(entityVar);
		}
	}

	public static String varSuggestedComputateResearch(String entityVar) {
		switch(entityVar) {
			default:
				return BaseModel.varSuggestedBaseModel(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrResponse.Doc doc) {
		storeComputateResearch(doc);
	}
	public void storeComputateResearch(SolrResponse.Doc doc) {
		ComputateResearch oComputateResearch = (ComputateResearch)this;
		SiteRequest siteRequest = oComputateResearch.getSiteRequest_();

		oComputateResearch.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));

		super.storeBaseModel(doc);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestComputateResearch() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ComputateResearch) {
			ComputateResearch original = (ComputateResearch)o;
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

	public static final String CLASS_SIMPLE_NAME = "ComputateResearch";
	public static final String CLASS_API_ADDRESS_ComputateResearch = "computate.org-enUS-ComputateResearch";
	public static String getClassApiAddress() {
		return CLASS_API_ADDRESS_ComputateResearch;
	}
	public static final String VAR_name = "name";

	public static List<String> varsQForClass() {
		return ComputateResearch.varsQComputateResearch(new ArrayList<String>());
	}
	public static List<String> varsQComputateResearch(List<String> vars) {
		BaseModel.varsQBaseModel(vars);
		return vars;
	}

	public static List<String> varsFqForClass() {
		return ComputateResearch.varsFqComputateResearch(new ArrayList<String>());
	}
	public static List<String> varsFqComputateResearch(List<String> vars) {
		vars.add(VAR_name);
		BaseModel.varsFqBaseModel(vars);
		return vars;
	}

	public static List<String> varsRangeForClass() {
		return ComputateResearch.varsRangeComputateResearch(new ArrayList<String>());
	}
	public static List<String> varsRangeComputateResearch(List<String> vars) {
		BaseModel.varsRangeBaseModel(vars);
		return vars;
	}

	public static final String DISPLAY_NAME_name = "research name";

	public static String displayNameForClass(String var) {
		return ComputateResearch.displayNameComputateResearch(var);
	}
	public static String displayNameComputateResearch(String var) {
		switch(var) {
		case VAR_name:
			return DISPLAY_NAME_name;
		default:
			return BaseModel.displayNameBaseModel(var);
		}
	}

	public static String descriptionComputateResearch(String var) {
		switch(var) {
		case VAR_name:
			return "The research name. ";
			default:
				return BaseModel.descriptionBaseModel(var);
		}
	}

	public static String classSimpleNameComputateResearch(String var) {
		switch(var) {
		case VAR_name:
			return "String";
			default:
				return BaseModel.classSimpleNameBaseModel(var);
		}
	}

	public static Integer htmColumnComputateResearch(String var) {
		switch(var) {
			default:
				return BaseModel.htmColumnBaseModel(var);
		}
	}

	public static Integer htmRowComputateResearch(String var) {
		switch(var) {
		case VAR_name:
			return 3;
			default:
				return BaseModel.htmRowBaseModel(var);
		}
	}

	public static Integer htmCellComputateResearch(String var) {
		switch(var) {
		case VAR_name:
			return 2;
			default:
				return BaseModel.htmCellBaseModel(var);
		}
	}

	public static Integer lengthMinComputateResearch(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMinBaseModel(var);
		}
	}

	public static Integer lengthMaxComputateResearch(String var) {
		switch(var) {
			default:
				return BaseModel.lengthMaxBaseModel(var);
		}
	}

	public static Integer maxComputateResearch(String var) {
		switch(var) {
			default:
				return BaseModel.maxBaseModel(var);
		}
	}

	public static Integer minComputateResearch(String var) {
		switch(var) {
			default:
				return BaseModel.minBaseModel(var);
		}
	}
}
