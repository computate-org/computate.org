package org.computate.site.model.service;

import org.computate.site.request.SiteRequest;
import org.computate.site.model.service.CompanyServiceGenPage;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these CompanyServicePage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class CompanyServicePageGen into the class CompanyServicePage. 
 * </li>
 * <h3>About the CompanyServicePage class and it's generated class CompanyServicePageGen&lt;CompanyServiceGenPage&gt;: </h3>extends CompanyServicePageGen
 * <p>
 * This Java class extends a generated Java class CompanyServicePageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServicePage">Find the class CompanyServicePage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends CompanyServicePageGen<CompanyServiceGenPage>
 * <p>This <code>class CompanyServicePage extends CompanyServicePageGen&lt;CompanyServiceGenPage&gt;</code>, which means it extends a newly generated CompanyServicePageGen. 
 * The generated <code>class CompanyServicePageGen extends CompanyServiceGenPage</code> which means that CompanyServicePage extends CompanyServicePageGen which extends CompanyServiceGenPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the CompanyServicePage class will inherit the helpful inherited class comments from the super class CompanyServicePageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the CompanyServicePage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class CompanyServicePage in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.site.model.service.CompanyServicePage&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.site.model.service in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.site.model.service&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project computate.org in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:computate.org&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class CompanyServicePageGen<DEV> extends CompanyServiceGenPage {
	protected static final Logger LOG = LoggerFactory.getLogger(CompanyServicePage.class);

	//////////////
	// initDeep //
	//////////////

	public Future<CompanyServicePageGen<DEV>> promiseDeepCompanyServicePage(SiteRequest siteRequest_) {
		setSiteRequest_(siteRequest_);
		return promiseDeepCompanyServicePage();
	}

	public Future<CompanyServicePageGen<DEV>> promiseDeepCompanyServicePage() {
		Promise<CompanyServicePageGen<DEV>> promise = Promise.promise();
		Promise<Void> promise2 = Promise.promise();
		promiseCompanyServicePage(promise2);
		promise2.future().onSuccess(a -> {
			super.promiseDeepCompanyServiceGenPage(siteRequest_).onSuccess(b -> {
				promise.complete(this);
			}).onFailure(ex -> {
				promise.fail(ex);
			});
		}).onFailure(ex -> {
			promise.fail(ex);
		});
		return promise.future();
	}

	public Future<Void> promiseCompanyServicePage(Promise<Void> promise) {
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

	@Override public Future<? extends CompanyServicePageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
		return promiseDeepCompanyServicePage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestCompanyServicePage(SiteRequest siteRequest_) {
			super.siteRequestCompanyServiceGenPage(siteRequest_);
	}

	public void siteRequestForClass(SiteRequest siteRequest_) {
		siteRequestCompanyServicePage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainCompanyServicePage(v);
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
	public Object obtainCompanyServicePage(String var) {
		CompanyServicePage oCompanyServicePage = (CompanyServicePage)this;
		switch(var) {
			default:
				return super.obtainCompanyServiceGenPage(var);
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
				o = relateCompanyServicePage(v, val);
			else if(o instanceof BaseModel) {
				BaseModel baseModel = (BaseModel)o;
				o = baseModel.relateForClass(v, val);
			}
		}
		return o != null;
	}
	public Object relateCompanyServicePage(String var, Object val) {
		CompanyServicePage oCompanyServicePage = (CompanyServicePage)this;
		switch(var) {
			default:
				return super.relateCompanyServiceGenPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSetCompanyServicePage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetCompanyServicePage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
			default:
				return CompanyServiceGenPage.staticSetCompanyServiceGenPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSearch //
	////////////////

	public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchCompanyServicePage(entityVar,  siteRequest_, o);
	}
	public static Object staticSearchCompanyServicePage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return CompanyServiceGenPage.staticSearchCompanyServiceGenPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSearchStr //
	///////////////////

	public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
		return staticSearchStrCompanyServicePage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchStrCompanyServicePage(String entityVar, SiteRequest siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return CompanyServiceGenPage.staticSearchStrCompanyServiceGenPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSearchFq //
	//////////////////

	public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
		return staticSearchFqCompanyServicePage(entityVar,  siteRequest_, o);
	}
	public static String staticSearchFqCompanyServicePage(String entityVar, SiteRequest siteRequest_, String o) {
		switch(entityVar) {
			default:
				return CompanyServiceGenPage.staticSearchFqCompanyServiceGenPage(entityVar,  siteRequest_, o);
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

	public static final String CLASS_SIMPLE_NAME = "CompanyServicePage";


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

	public static String displayNameForClass(String var) {
		return CompanyServicePage.displayNameCompanyServicePage(var);
	}
	public static String displayNameCompanyServicePage(String var) {
		switch(var) {
		default:
			return CompanyServiceGenPage.displayNameCompanyServiceGenPage(var);
		}
	}

	public static String descriptionCompanyServicePage(String var) {
		switch(var) {
			default:
				return CompanyServiceGenPage.descriptionCompanyServiceGenPage(var);
		}
	}

	public static String classSimpleNameCompanyServicePage(String var) {
		switch(var) {
			default:
				return CompanyServiceGenPage.classSimpleNameCompanyServiceGenPage(var);
		}
	}

	public static Integer htmColumnCompanyServicePage(String var) {
		switch(var) {
			default:
				return CompanyServiceGenPage.htmColumnCompanyServiceGenPage(var);
		}
	}

	public static Integer htmRowCompanyServicePage(String var) {
		switch(var) {
			default:
				return CompanyServiceGenPage.htmRowCompanyServiceGenPage(var);
		}
	}

	public static Integer htmCellCompanyServicePage(String var) {
		switch(var) {
			default:
				return CompanyServiceGenPage.htmCellCompanyServiceGenPage(var);
		}
	}

	public static Integer lengthMinCompanyServicePage(String var) {
		switch(var) {
			default:
				return CompanyServiceGenPage.lengthMinCompanyServiceGenPage(var);
		}
	}

	public static Integer lengthMaxCompanyServicePage(String var) {
		switch(var) {
			default:
				return CompanyServiceGenPage.lengthMaxCompanyServiceGenPage(var);
		}
	}

	public static Integer maxCompanyServicePage(String var) {
		switch(var) {
			default:
				return CompanyServiceGenPage.maxCompanyServiceGenPage(var);
		}
	}

	public static Integer minCompanyServicePage(String var) {
		switch(var) {
			default:
				return CompanyServiceGenPage.minCompanyServiceGenPage(var);
		}
	}
}
