package org.computate.site.model.webinar;

import java.util.Arrays;
import java.util.Optional;

import org.computate.search.wrap.Wrap;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Promise: true
 **/
public class CompanyWebinarPage extends CompanyWebinarPageGen<CompanyWebinarGenPage> {

  @Override
  protected void _listCompanyWebinar(JsonArray l) {
    Optional.ofNullable(searchListCompanyWebinar_).map(o -> o.getList()).orElse(Arrays.asList()).stream().map(o -> {
      JsonObject json = JsonObject.mapFrom(o);
      json.remove(CompanyWebinar.VAR_webinarUrlAmericas);
      json.remove(CompanyWebinar.VAR_webinarUrlApac);
      json.remove(CompanyWebinar.VAR_webinarUrlEmea);
      return json;
    }).forEach(o -> l.add(o));
  }

  @Override
  protected void _pageResponse(Wrap<String> w) {
    if(searchListCompanyWebinar_ != null) {
      JsonObject response = JsonObject.mapFrom(searchListCompanyWebinar_.getResponse());
      response.getJsonObject("response").getJsonArray("docs").forEach(doc -> {
        JsonObject fields = ((JsonObject)doc).getJsonObject("fields");
        fields.remove(String.format("%s_docvalues_string", CompanyWebinar.VAR_webinarUrlAmericas));
        fields.remove(String.format("%s_docvalues_string", CompanyWebinar.VAR_webinarUrlApac));
        fields.remove(String.format("%s_docvalues_string", CompanyWebinar.VAR_webinarUrlEmea));
      });
      w.o(response.encode());
    }
  }
}
