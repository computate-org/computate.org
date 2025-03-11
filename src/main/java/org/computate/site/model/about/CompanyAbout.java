package org.computate.site.model.about;

import org.computate.site.result.BaseResult;
import org.computate.vertx.config.ComputateConfigKeys;

import java.math.BigDecimal;

import org.computate.search.wrap.Wrap;
import io.vertx.pgclient.data.Point;

/**
 * Order: 2
 * Description: Learn about our team, company, culture, and values. 
 * AName: an about page
 * PluralName: about
 * Icon: <i class="fa-duotone fa-regular fa-address-card"></i>
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/about
 * EditPageUri: /en-us/edit/about/{pageId}
 * DisplayPageUri: /en-us/learn/about/{pageId}
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
	 * VarName: true
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
}

