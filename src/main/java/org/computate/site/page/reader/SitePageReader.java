
package org.computate.site.page.reader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import org.computate.search.tool.XmlTool;
import org.computate.search.wrap.Wrap;
import org.computate.site.config.ConfigKeys;
import org.computate.site.page.SitePage;
import org.computate.site.request.SiteRequest;
import org.computate.vertx.config.ComputateConfigKeys;

import com.google.common.io.Resources;
import com.google.common.util.concurrent.Futures;
import com.hubspot.jinjava.Jinjava;

import io.vertx.config.yaml.YamlProcessor;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;

import jinjava.org.jsoup.Jsoup;
import jinjava.org.jsoup.nodes.Document;

public class SitePageReader extends SitePageReaderGen<Object> {

	private Pattern PATTERN_HEADER = Pattern.compile("^h\\d+$");

	public SitePageReader(Vertx vertx, WorkerExecutor workerExecutor, KafkaProducer<String, String> kafkaProducer, SiteRequest siteRequest, JsonObject config) {
		super();
		setSiteRequest_(siteRequest);
		setConfig(config);
		setVertx(vertx);
		setWorkerExecutor(workerExecutor);
		setKafkaProducer(kafkaProducer);
	}

	public SitePageReader() {
	}

	/**
	 * {@inheritDoc}
	 * Ignore: true
	 * Description: The current request object
	 */
	protected void _siteRequest_(Wrap<SiteRequest> w) {
	}

	protected void _config(Wrap<JsonObject> w) {
		w.o(siteRequest_.getConfig());
	}

	protected void _webClient(Wrap<WebClient> w) {
		w.o(siteRequest_.getWebClient());
	}

	protected void _kafkaProducer(Wrap<KafkaProducer<String, String>> w) {
	}

	protected void _vertx(Wrap<Vertx> w) {
	}

	protected void _workerExecutor(Wrap<WorkerExecutor> w) {
	}

	protected void _jinjava(Wrap<Jinjava> w) {
	}

	/**
	 * Description: Generate I18n text
	 * Val.Complete.enUS:Importing %s data completed. 
	 * Val.Fail.enUS:Importing %s data failed. 
	 */
	public Future<JsonObject> i18nGenerator() {
		Promise<JsonObject> promise = Promise.promise();
		List<String> i18nPaths = Optional.ofNullable(config.getValue(ConfigKeys.I18N_PATHS)).map(v -> v instanceof JsonArray ? (JsonArray)v : new JsonArray(v.toString())).orElse(new JsonArray()).stream().map(o -> o.toString()).collect(Collectors.toList());
		JsonObject i18n = new JsonObject();
		YamlProcessor yamlProcessor = new YamlProcessor();

		i18nGeneratorPath(i18n, yamlProcessor, i18nPaths, 0).onSuccess(i18n2 -> {
		}).onFailure(ex -> {
			LOG.error(String.format(i18nGeneratorFail, SitePage.CLASS_SIMPLE_NAME), ex);
			promise.fail(ex);
		});
		return promise.future();
	}

	/**
	 * Description: Generate I18n path
	 * Val.Complete.enUS:Generating i18n path for page %s completed. 
	 * Val.Fail.enUS:Generating i18n path for page %s failed. 
	 */
	private Future<JsonObject> i18nGeneratorPath(JsonObject i18n, YamlProcessor yamlProcessor, List<String> i18nPaths, Integer i) {
		Promise<JsonObject> promise = Promise.promise();
		if(i < i18nPaths.size()) {
			String i18nPath = i18nPaths.get(i);
			vertx.fileSystem().readFile(i18nPath).onSuccess(i18nBuffer -> {
				yamlProcessor.process(vertx, null, i18nBuffer).onSuccess(i18n2 -> {
					JsonObject i18n3 = i18n.copy().mergeIn(i18n2, true);
					i18nGeneratorPath(i18n3, yamlProcessor, i18nPaths, i + 1).onSuccess(i18n4 -> {
						promise.complete(i18n4);
					}).onFailure(ex -> {
						LOG.error(String.format(i18nGeneratorPathFail, SitePage.CLASS_SIMPLE_NAME), ex);
						promise.fail(ex);
					});
				}).onFailure(ex -> {
					LOG.error(String.format(i18nGeneratorPathFail, SitePage.CLASS_SIMPLE_NAME), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format(i18nGeneratorPathFail, SitePage.CLASS_SIMPLE_NAME), ex);
				promise.fail(ex);
			});
		} else {
			promise.complete(i18n);
		}
		return promise.future();
	}

	/**
	 * Description: Delete page data
	 * Val.Complete.enUS:Importing %s data completed. 
	 * Val.Fail.enUS:Importing %s data failed. 
	 */
	public Future<Void> deletePageData(ZonedDateTime now) {
		Promise<Void> promise = Promise.promise();
		String solrHostName = config.getString(ComputateConfigKeys.SOLR_HOST_NAME);
		Integer solrPort = config.getInteger(ComputateConfigKeys.SOLR_PORT);
		String solrCollection = config.getString(ComputateConfigKeys.SOLR_COLLECTION);
		Boolean solrSsl = config.getBoolean(ConfigKeys.SOLR_SSL);
		String solrRequestUri = String.format("/solr/%s/update%s", solrCollection, "?softCommit=true&overwrite=true&wt=json");
		String deleteQuery = String.format("classSimpleName_docvalues_string:(%s) AND created_docvalues_date:[* TO %s]", SitePage.CLASS_SIMPLE_NAME, SitePage.staticSearchStrCreated(null, SitePage.staticSearchCreated(null, now)));
		String deleteXml = String.format("<delete><query>%s</query></delete>", deleteQuery);
		webClient.post(solrPort, solrHostName, solrRequestUri)
				.ssl(solrSsl)
				.putHeader("Content-Type", "text/xml")
				.sendBuffer(Buffer.buffer().appendString(deleteXml))
				.onSuccess(d -> {
			try {
				promise.complete();
			} catch(Exception ex) {
				LOG.error(String.format("Could not read response from Solr: http://%s:%s%s", solrHostName, solrPort, solrRequestUri), ex);
				promise.fail(ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format("Search failed. "), new RuntimeException(ex));
			promise.fail(ex);
		});
		return promise.future();
	}

	/**
	 * Description: Import all Site HTML data
	 * Val.Complete.enUS:Importing all %s data completed. 
	 * Val.Fail.enUS:Importing all %s data failed. 
	 */
	public Future<Void> importDataSitePages() {
		Promise<Void> promise = Promise.promise();
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE)));
		// i18nGenerator().onSuccess(i18n -> {
		try {
			String siteTemplatePath = config.getString(ConfigKeys.TEMPLATE_PATH);
			List<String> dynamicPagePaths = Optional.ofNullable(config.getValue(ConfigKeys.DYNAMIC_PAGE_PATHS)).map(v -> v instanceof JsonArray ? (JsonArray)v : new JsonArray(v.toString())).orElse(new JsonArray()).stream().map(o -> o.toString()).collect(Collectors.toList());
			List<String> pageResourcePaths = new ArrayList<>();
			List<String> pageTemplatePaths = new ArrayList<>();
			dynamicPagePaths.forEach(dynamicPagePath -> {
				try {
					try(Stream<Path> stream = Files.walk(Paths.get(config.getString(ConfigKeys.TEMPLATE_PATH), dynamicPagePath))) {
						stream.filter(Files::isRegularFile).filter(p -> 
								p.getFileName().toString().endsWith(".htm")
								|| p.getFileName().toString().endsWith(".html")
								).forEach(path -> {
							pageResourcePaths.add(StringUtils.substringAfter(path.toAbsolutePath().toString(), "/src/main/resources/"));
							pageTemplatePaths.add(StringUtils.substringAfter(path.toAbsolutePath().toString(), siteTemplatePath + "/"));
						});
					}
				} catch(Exception ex) {
					LOG.error(String.format(importDataSitePagesFail, SitePage.CLASS_SIMPLE_NAME), ex);
					ExceptionUtils.rethrow(ex);
				}
			});
			YamlProcessor yamlProcessor = new YamlProcessor();
	
			importDataSitePage(null, yamlProcessor, pageResourcePaths, pageTemplatePaths, 0).onSuccess(a -> {
				deletePageData(now).onSuccess(b -> {
					LOG.info(String.format(importDataSitePagesComplete, SitePage.CLASS_SIMPLE_NAME));
					promise.complete();
				}).onFailure(ex -> {
					LOG.error(String.format(importDataSitePagesFail, SitePage.CLASS_SIMPLE_NAME), ex);
					promise.fail(ex);
				});
			}).onFailure(ex -> {
				LOG.error(String.format(importDataSitePagesFail, SitePage.CLASS_SIMPLE_NAME), ex);
				promise.fail(ex);
			});
		} catch(Throwable ex) {
			LOG.error(String.format(importDataSitePagesFail, SitePage.CLASS_SIMPLE_NAME), ex);
			promise.fail(ex);
		}
		// }).onFailure(ex -> {
		// 	LOG.error(String.format(importDataSitePagesFail, SitePage.CLASS_SIMPLE_NAME), ex);
		// 	promise.fail(ex);
		// });
		return promise.future();
	}

	/**
	 * Description: Import Site HTML data
	 * Val.Complete.enUS:Importing %s data completed. 
	 * Val.Fail.enUS:Importing %s data failed. 
	 */
	private Future<Void> importDataSitePage(JsonObject i18n, YamlProcessor yamlProcessor, List<String> pageResourcePaths, List<String> pageTemplatePaths, Integer i) {
		Promise<Void> promise = Promise.promise();
		try {
			if(i < pageResourcePaths.size()) {
				String pageResourcePath = pageResourcePaths.get(i);
				String pageTemplatePath = pageTemplatePaths.get(i);
				importSitePage(i18n, yamlProcessor, pageResourcePath, pageTemplatePath).onSuccess(a -> {
					importDataSitePage(i18n, yamlProcessor, pageResourcePaths, pageTemplatePaths, i + 1).onSuccess(b -> {
						promise.complete();
					}).onFailure(ex -> {
						LOG.error(String.format(importDataSitePageFail, SitePage.CLASS_SIMPLE_NAME), ex);
						promise.fail(ex);
					});
				}).onFailure(ex -> {
					LOG.error(String.format(importDataSitePageFail, SitePage.CLASS_SIMPLE_NAME), ex);
					promise.fail(ex);
				});
			} else {
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error(String.format(importDataSitePageFail, SitePage.CLASS_SIMPLE_NAME), ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	/**
	 * Description: Import page
	 * Val.Complete.enUS:Importing page completed: %s
	 * Val.Fail.enUS:Importing page failed: %s
	 */
	private Future<Void> importSitePage(JsonObject i18n, YamlProcessor yamlProcessor, String resourceUri, String templateUri) {
		Promise<Void> promise = Promise.promise();
		vertx.fileSystem().readFile(resourceUri).onSuccess(buffer -> {
			try {
				// Jinjava template rendering
				String template = Resources.toString(Resources.getResource(resourceUri), StandardCharsets.UTF_8);

				String siteBaseUrl = config.getString(ConfigKeys.SITE_BASE_URL);
				JsonObject ctx = new JsonObject();
				ctx.put(ConfigKeys.STATIC_BASE_URL, config.getString(ConfigKeys.STATIC_BASE_URL));
				ctx.put(ConfigKeys.SITE_BASE_URL, config.getString(ConfigKeys.SITE_BASE_URL));
				ctx.put(ConfigKeys.GITHUB_ORG, config.getString(ConfigKeys.GITHUB_ORG));
				ctx.put(ConfigKeys.SITE_NAME, config.getString(ConfigKeys.SITE_NAME));
				ctx.put(ConfigKeys.SITE_DISPLAY_NAME, config.getString(ConfigKeys.SITE_DISPLAY_NAME));
				ctx.put(ConfigKeys.SITE_POWERED_BY_URL, config.getString(ConfigKeys.SITE_POWERED_BY_URL));
				ctx.put(ConfigKeys.SITE_POWERED_BY_NAME, config.getString(ConfigKeys.SITE_POWERED_BY_NAME));
				ctx.put(ConfigKeys.SITE_POWERED_BY_IMAGE_URI, config.getString(ConfigKeys.SITE_POWERED_BY_IMAGE_URI));
				ctx.put(ConfigKeys.FONTAWESOME_KIT, config.getString(ConfigKeys.FONTAWESOME_KIT));

				Matcher m = Pattern.compile("<meta property=\"([^\"]+)\"\\s+content=\"([^\"]*)\"/>", Pattern.MULTILINE).matcher(template);
				boolean trouve = m.find();
				while (trouve) {
					String siteKey = m.group(1);
					if(siteKey.startsWith("site:")) {
						String key = StringUtils.substringAfter(siteKey, "site:");
						String val = m.group(2);
						if(val instanceof String) {
							String rendered = jinjava.render(val, ctx.getMap());
							ctx.put(key, rendered);
						} else {
							ctx.put(key, val);
						}
					}
					trouve = m.find();
				}

				// JSoup HTML parsing
				String renderedTemplate = jinjava.render(template, ctx.getMap());

				Document htmDoc = Jsoup.parse(renderedTemplate);
				String pageId = StringUtils.substringBeforeLast(StringUtils.substringAfterLast(resourceUri, "/"), ".");
				SiteRequest siteRequest = new SiteRequest();
				siteRequest.setConfig(config);
				siteRequest.setWebClient(webClient);
				siteRequest.initDeepSiteRequest(siteRequest);

				String uri = ctx.getString(SitePage.VAR_uri);
				String url = String.format("%s%s", siteBaseUrl, uri);
				SitePage page = new SitePage();
				page.setSiteRequest_(siteRequest);
				page.persistForClass(SitePage.VAR_resourceUri, resourceUri);
				page.persistForClass(SitePage.VAR_templateUri, templateUri);
				page.persistForClass(SitePage.VAR_pageId, pageId);
				page.persistForClass(SitePage.VAR_objectTitle, ctx.getString(SitePage.VAR_title));
				page.persistForClass(SitePage.VAR_created, ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));
				page.persistForClass(SitePage.VAR_modified, ctx.getString(SitePage.VAR_created));
				page.persistForClass(SitePage.VAR_courseNum, ctx.getInteger(SitePage.VAR_courseNum));
				page.persistForClass(SitePage.VAR_lessonNum, ctx.getInteger(SitePage.VAR_lessonNum));
				page.persistForClass(SitePage.VAR_author, ctx.getString(SitePage.VAR_author));
				page.persistForClass(SitePage.VAR_uri, uri);
				page.persistForClass(SitePage.VAR_url, url);
				page.persistForClass(SitePage.VAR_title, ctx.getString(SitePage.VAR_title));
				page.persistForClass(SitePage.VAR_pageId, ctx.getString(SitePage.VAR_pageId));
				page.persistForClass(SitePage.VAR_pageImageUri, ctx.getString(SitePage.VAR_pageImageUri));
				page.promiseDeepForClass(siteRequest).onSuccess(a -> {
					try {
						JsonObject pageBody = JsonObject.mapFrom(page);
						JsonObject pageParams = new JsonObject();
						pageParams.put("body", pageBody);
						pageParams.put("path", new JsonObject());
						pageParams.put("cookie", new JsonObject());
						pageParams.put("query", new JsonObject().put("softCommit", true).put("q", "*:*").put("var", new JsonArray().add("refresh:false")));
						JsonObject pageContext = new JsonObject().put("params", pageParams);
						JsonObject pageRequest = new JsonObject().put("context", pageContext);

						vertx.eventBus().request(SitePage.CLASS_API_ADDRESS_SitePage, pageRequest, new DeliveryOptions().setSendTimeout(config.getLong(ConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000).addHeader("action", String.format("putimport%sFuture", SitePage.CLASS_SIMPLE_NAME))).onSuccess(message -> {
							promise.complete();
						}).onFailure(ex -> {
							promise.fail(ex);
						});
					} catch(Exception ex) {
						LOG.error(String.format(importSitePageFail, SitePage.CLASS_SIMPLE_NAME), ex);
						promise.fail(ex);
					}
				}).onFailure(ex -> {
					LOG.error(String.format(importSitePageFail, SitePage.CLASS_SIMPLE_NAME), ex);
					promise.fail(ex);
				});
			} catch(Exception ex) {
				LOG.error(String.format(importSitePageFail, SitePage.CLASS_SIMPLE_NAME), ex);
				promise.fail(ex);
			}
		}).onFailure(ex -> {
			LOG.error(String.format(importSitePageFail, SitePage.CLASS_SIMPLE_NAME), ex);
			promise.fail(ex);
		});
		return promise.future();
	}
}
