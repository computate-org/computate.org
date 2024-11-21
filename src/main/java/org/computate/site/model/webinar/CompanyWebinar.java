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
 * Description: Join the Computate community for regular weekly webinars
 * AName: a webinar
 * PluralName: webinars
 * Icon: <i class="fa-regular fa-video"></i>
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/webinar
 * EditPageUri: /en-us/edit/webinar/{pageId}
 * DisplayPageUri: /en-us/view/webinar/{pageId}
 * UserPageUri: /en-us/join/webinar/{pageId}
 * ApiUri: /en-us/api/webinar
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   PUTImport:
 * 
 * AuthGroup:
 *   Admin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     Admin:
 *   SuperAdmin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     SuperAdmin:
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
	 * VarName: true
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
	 * VarDescription: true
   **/
  protected void _description(Wrap<String> w) {}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRowTitleOpen: Useful URLs
	 * HtmRow: 99
	 * HtmCell: 1
	 * Facet: true
	 * DisplayName: Page ID
	 * Description: The ID for this page. 
	 * VarId: true
	 */
	protected void _pageId(Wrap<String> w) {
		w.o(toId(name));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: join URI
	 * Description: The join relative URI for this page. 
	 */
	protected void _joinUri(Wrap<String> w) {
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
	 * Link: true
	 * HtmColumn: 0
	 * DisplayName: Join the webinar
	 * Icon: <i class="fa-solid fa-video"></i>
	 * Description: Access the webinar links. 
	 */
	protected void _joinUrl(Wrap<String> w) {
		w.o(String.format("%s%s", siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), joinUri));
	}
}
