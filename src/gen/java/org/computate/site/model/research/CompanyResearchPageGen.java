package org.computate.site.model.research;

import org.computate.site.request.SiteRequest;
import org.computate.site.model.research.CompanyResearchGenPage;
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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyResearchPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyResearchPageGen into the class CompanyResearchPage. 
 * </li>
 * <h3>About the CompanyResearchPage class and it's generated class CompanyResearchPageGen&lt;CompanyResearchGenPage&gt;: </h3>extends CompanyResearchPageGen
 * <p>
 * This Java class extends a generated Java class CompanyResearchPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchPage">Find the class CompanyResearchPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyResearchPageGen<CompanyResearchGenPage>
 * <p>This <code>class CompanyResearchPage extends CompanyResearchPageGen&lt;CompanyResearchGenPage&gt;</code>, which means it extends a newly generated CompanyResearchPageGen. 
 * The generated <code>class CompanyResearchPageGen extends CompanyResearchGenPage</code> which means that CompanyResearchPage extends CompanyResearchPageGen which extends CompanyResearchGenPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyResearchPage class will inherit the helpful inherited class comments from the super class CompanyResearchPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyResearchPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class CompanyResearchPage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.research.CompanyResearchPage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.research in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.research&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyResearchPageGen<DEV> extends CompanyResearchGenPage {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyResearchPage.class);

	//////////////
	// initDeep //
	//////////////

	public Future<CompanyResearchPageGen<DEV>> promiseDeepCompanyResearchPage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyResearchPage();
	}

	public Future<CompanyResearchPageGen<DEV>> promiseDeepCompanyResearchPage() {
		Promise<CompanyResearchPageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyResearchPage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepCompanyResearchGenPage(siteRequest_).onSuccess(b -> {
				promise.complete(this);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseCompanyResearchPage(Promise<Void> promise) {
		Future.future(a -> a.complete()).compose(a -> {
			Promise<Void> promise2 = Promise.promise();
			try {
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

	@Override public Future<? extends CompanyResearchPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyResearchPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyResearchPage(SiteRequest siteRequest_) {
			super.siteRequestCompanyResearchGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyResearchPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyResearchPage(v);
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
	public Object obtainCompanyResearchPage(String var) {
		CompanyResearchPage oCompanyResearchPage = (CompanyResearchPage)this;
		switch(var) {
			default:
				return super.obtainCompanyResearchGenPage(var);
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
				o = relateCompanyResearchPage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyResearchPage(String var, Object val) {
		CompanyResearchPage oCompanyResearchPage = (CompanyResearchPage)this;
		switch(var) {
			default:
				return super.relateCompanyResearchGenPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, CompanyResearchPage o) {
		return staticSetCompanyResearchPage(entityVar,  siteRequest_, v, o);
	}
	public static Object staticSetCompanyResearchPage(String entityVar, SiteRequest siteRequest_, String v, CompanyResearchPage o) {
		switch(entityVar) {
			default:
				return CompanyResearchGenPage.staticSetCompanyResearchGenPage(entityVar,  siteRequest_, v, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyResearchPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyResearchPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return CompanyResearchGenPage.staticSearchCompanyResearchGenPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyResearchPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyResearchPage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return CompanyResearchGenPage.staticSearchStrCompanyResearchGenPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyResearchPage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyResearchPage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
			default:
				return CompanyResearchGenPage.staticSearchFqCompanyResearchGenPage(entityVar,  siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyResearchPage";
	public static final String CLASS_CANONICAL_NAME = "org.computate.site.model.research.CompanyResearchPage";


	@Override
	public String idForClass() {
		return null;
	}

	@Override
	public String titleForClass() {
		return null;
	}

	@Override
	public String nameForClass() {
		return null;
	}

	@Override
	public String classNameAdjectiveSingularForClass() {
		return null;
	}

	@Override
	public String descriptionForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlEditPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlDisplayPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlUserPageForClass() {
		return null;
	}

	@Override
	public String classStringFormatUrlDownloadForClass() {
		return null;
	}

	public static String displayNameForClass(String var) {
		return CompanyResearchPage.displayNameCompanyResearchPage(var);
	}
	public static String displayNameCompanyResearchPage(String var) {
		switch(var) {
		default:
			return CompanyResearchGenPage.displayNameCompanyResearchGenPage(var);
		}
	}

	public static String descriptionCompanyResearchPage(String var) {
		if(var == null)
			return null;
		switch(var) {
			default:
				return CompanyResearchGenPage.descriptionCompanyResearchGenPage(var);
		}
	}

	public static String classSimpleNameCompanyResearchPage(String var) {
		switch(var) {
			default:
				return CompanyResearchGenPage.classSimpleNameCompanyResearchGenPage(var);
		}
	}

	public static Integer htmColumnCompanyResearchPage(String var) {
		switch(var) {
			default:
				return CompanyResearchGenPage.htmColumnCompanyResearchGenPage(var);
		}
	}

	public static Integer htmRowCompanyResearchPage(String var) {
		switch(var) {
			default:
				return CompanyResearchGenPage.htmRowCompanyResearchGenPage(var);
		}
	}

	public static Integer htmCellCompanyResearchPage(String var) {
		switch(var) {
			default:
				return CompanyResearchGenPage.htmCellCompanyResearchGenPage(var);
		}
	}

	public static Integer lengthMinCompanyResearchPage(String var) {
		switch(var) {
			default:
				return CompanyResearchGenPage.lengthMinCompanyResearchGenPage(var);
		}
	}

	public static Integer lengthMaxCompanyResearchPage(String var) {
		switch(var) {
			default:
				return CompanyResearchGenPage.lengthMaxCompanyResearchGenPage(var);
		}
	}

	public static Integer maxCompanyResearchPage(String var) {
		switch(var) {
			default:
				return CompanyResearchGenPage.maxCompanyResearchGenPage(var);
		}
	}

	public static Integer minCompanyResearchPage(String var) {
		switch(var) {
			default:
				return CompanyResearchGenPage.minCompanyResearchGenPage(var);
		}
	}
}
