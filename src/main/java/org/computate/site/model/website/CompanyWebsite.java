package org.computate.site.model.website;

import org.computate.site.result.BaseResult;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.search.wrap.Wrap;
import io.vertx.pgclient.data.Point;

/**
 * Order: 10
 * Description: See the live websites built with computate
 * AName: a website
 * PluralName: websites
 * Icon: <i class="fa-duotone fa-regular fa-globe-pointer"></i>
 * Sort.desc: websiteNum
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/website
 * EditPageUri: /en-us/edit/website/{pageId}
 * DisplayPageUri: /en-us/view/website/{pageId}
 * ApiUri: /en-us/api/website
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
public class CompanyWebsite extends CompanyWebsiteGen<BaseResult> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: service name
   * Description: The service name. 
   * HtmRow: 3
   * HtmCell: 1
   * HtmColumn: 1
   * Facet: true
	 * VarName: true
   */
  protected void _name(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: service description
   * Description: The service description. 
   * HtmRow: 3
   * HtmCell: 2
   * HtmColumn: 2
   * Facet: true
	 * VarDescription: true
   */
  protected void _description(Wrap<String> w) {
  }

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
	 * DisplayName: Website Number
	 * Description: The website number for sorting. 
	 */
	protected void _websiteNum(Wrap<Integer> w) {
	}
}
