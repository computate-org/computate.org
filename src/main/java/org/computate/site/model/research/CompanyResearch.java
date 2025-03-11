package org.computate.site.model.research;

import org.computate.site.result.BaseResult;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.search.wrap.Wrap;
import io.vertx.pgclient.data.Point;

/**
 * Order: 9
 * Description: Read about the computate project related research going on at Red Hat research
 * AName: a research
 * PluralName: research
 * Icon: <i class="fa-duotone fa-regular fa-school"></i>
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/research
 * EditPageUri: /en-us/edit/research/{pageId}
 * DisplayPageUri: /en-us/view/research/{pageId}
 * ApiUri: /en-us/api/research
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
public class CompanyResearch extends CompanyResearchGen<BaseResult> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: research name
   * Description: The research name. 
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
   * DisplayName: research description
   * Description: The research description. 
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
