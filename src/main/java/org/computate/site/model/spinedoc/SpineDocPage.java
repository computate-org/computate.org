package org.computate.site.model.spinedoc;

import java.util.Arrays;
import java.util.Optional;

import org.computate.search.wrap.Wrap;
import org.computate.site.request.SiteRequest;
import org.computate.vertx.search.list.SearchList;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Promise: true
 **/
public class SpineDocPage extends SpineDocPageGen<SpineDocGenPage> {

  /**
   * {@inheritDoc}
   * Ignore: true
   **/
  protected void _allSpineDocSearch(Promise<SearchList<SpineDoc>> promise) {
    SearchList<SpineDoc> searchList = new SearchList<SpineDoc>();
    SiteRequest siteRequest = new SiteRequest();
    siteRequest.setConfig(siteRequest_.getConfig());
    siteRequest.setPublicRead(true);
    siteRequest.initDeepSiteRequest(siteRequest);
    siteRequest.setWebClient(webClient);
  
    searchList.setStore(true);
    searchList.q("*:*");
    searchList.sort("javaType_docvalues_string", "asc");
    searchList.sort("commentVar_docvalues_string", "asc");
    searchList.rows(1000);
    searchList.setC(SpineDoc.class);
    searchList.setSiteRequest_(siteRequest);
    promise.complete(searchList);
  }

  /**
   * {@inheritDoc}
   **/
  protected void _classSpineDocs(JsonArray l) {
    Optional.ofNullable(allSpineDocSearch).map(o -> o.getList()).orElse(Arrays.asList()).stream().filter(o -> "class".equals(o.getJavaType())).map(o -> JsonObject.mapFrom(o)).forEach(o -> l.add(o));
  }

  /**
   * {@inheritDoc}
   **/
  protected void _methodSpineDocs(JsonArray l) {
    Optional.ofNullable(allSpineDocSearch).map(o -> o.getList()).orElse(Arrays.asList()).stream().filter(o -> "method".equals(o.getJavaType())).map(o -> JsonObject.mapFrom(o)).forEach(o -> l.add(o));
  }
}
