package org.computate.site.model.about;

import org.computate.site.result.BaseResult;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.search.wrap.Wrap;
import io.vertx.pgclient.data.Point;

/**
 * Order: 2
 * Description: Learn about our team, company, culture, and values. 
 * AName: an about page
 * PluralName: about
 * Icon: <i class="fa-solid fa-address-card"></i>
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/about
 * EditPageUri: /en-us/edit/about/{objectId}
 * DisplayPageUri: /en-us/learn/about/{objectId}
 * ApiUri: /en-us/api/about
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
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
public class CompanyAbout extends CompanyAboutGen<BaseResult> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: about name
   * Description: The about name. 
   * HtmRow: 3
   * HtmCell: 1
   * HtmColumn: 1
   * Facet: true
   */
  protected void _name(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: about description
   * Description: The about description. 
   * HtmRow: 3
   * HtmCell: 2
   * HtmColumn: 2
   * Facet: true
   */
  protected void _description(Wrap<String> w) {
  }

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

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: title
	 * Description: The title of this page. 
	 * UrlVar: pageUrlId
	 */
	protected void _title(Wrap<String> w) {
		w.o(name);
	}

	@Override
	protected void _objectTitle(Wrap<String> w) {
		w.o(String.format("%s — %s", CompanyAbout_NameAdjectiveSingular_enUS, title));
	}

	@Override
	protected void _objectId(Wrap<String> w) {
		w.o(pageId);
	}

	@Override
	protected void _id(Wrap<String> w) {
		w.o(String.format("%s_%s", CLASS_SIMPLE_NAME, pageId));
	}

	@Override
	protected void _pageUrlId(Wrap<String> w) {
		w.o(url);
	}
}

