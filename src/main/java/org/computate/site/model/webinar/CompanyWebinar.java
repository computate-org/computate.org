package org.computate.site.model.webinar;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.search.list.SearchList;
import org.computate.site.model.BaseModel;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.data.Path;
import io.vertx.pgclient.data.Point;
import io.vertx.pgclient.data.Polygon;

/**
 * Order: 7
 * Model: true
 * 
 * Api: true
 * Page: true
 * PageTemplates: /en-us/webinar
 * UserPageTemplates: /en-us/user/webinar
 * SuperPage: BaseModelPage
 * Indexed: true
 * Description: Join the Computate community for regular weekly webinars
 * 
 * ApiTag: webinar
 * ApiUri: /api/webinar
 * 
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   PUTImport:
 *   SearchPage:
 *     Page: CompanyWebinarPage
 *     ApiUri: /webinar
 * AuthGroup:
 *   SuperAdmin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     SuperAdmin:
 * 
 * SearchPagePublicRead: true
 * 
 * AName: a webinar
 * PluralName: webinars
 * Icon: <i class="fa-regular fa-video"></i>
 */
public class CompanyWebinar extends CompanyWebinarGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: name
   * Description: The name of this webinar
   * HtmRow: 3
   * HtmCell: 1
   * HtmColumn: 1
   * HtmRowTitle: webinar details
   * Facet: true
   **/
  protected void _name(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: description
   * Description: A description of this webinar
   * HtmRow: 3
   * HtmCell: 2
   * Facet: true
   * HtmColumn: 2
   **/
  protected void _description(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 4
	 * HtmCell: 1
	 * Facet: true
	 * DisplayName: Page ID
	 * Description: The ID for this page. 
	 */
	protected void _pageId(Wrap<String> w) {
		toId(name);
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: resource URI
	 * Description: The resource relative URI for this page. 
	 */
	protected void _resourceUri(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: template URI
	 * Description: The template relative URI for this page. 
	 */
	protected void _templateUri(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: user URI
	 * Description: The user relative URI for this page. 
	 */
	protected void _userUri(Wrap<String> w) {
	}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: Webinar URL Americas
   * Description: The actual link to the Webinar in Americas
   * HtmRow: 4
   * HtmCell: 1
   * Facet: true
   **/
  protected void _webinarUrlAmericas(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: Webinar URL APAC
   * Description: The actual link to the Webinar in APAC
   * HtmRow: 4
   * HtmCell: 2
   * Facet: true
   **/
  protected void _webinarUrlApac(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: Webinar URL EMEA
   * Description: The actual link to the Webinar in EMEA
   * HtmRow: 4
   * HtmCell: 3
   * Facet: true
   **/
  protected void _webinarUrlEmea(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 3
	 * HtmCell: 2
	 * Facet: true
	 * DisplayName: URI
	 * Description: The relative URI for this page. 
	 */
	protected void _uri(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 3
	 * HtmCell: 2
	 * Facet: true
	 * DisplayName: URL
	 * Description: The URL for this page. 
	 */
	protected void _url(Wrap<String> w) {
		w.o(String.format("%s%s", siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), uri));
	}

	@Override
	protected void _pageUrlPk(Wrap<String> w) {
		w.o(url);
	}

	@Override
	protected void _pageUrlId(Wrap<String> w) {
		w.o(url);
	}

  @Override
  protected void _objectTitle(Wrap<String> w) {
    StringBuilder b = new StringBuilder();
    b.append(Optional.ofNullable(name).map(s -> String.format("%s — %s", CompanyWebinar_NameAdjectiveSingular_enUS, s)).orElse(pageId));
    w.o(b.toString().trim());
  }

  @Override
  protected void _objectId(Wrap<String> w) {
    w.o(pageId);
  }

	@Override
	protected void _id(Wrap<String> w) {
		w.o(String.format("%s_%s", CLASS_SIMPLE_NAME, pageId));
	}
}
