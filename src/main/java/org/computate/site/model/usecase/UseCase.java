package org.computate.site.model.usecase;

import org.computate.site.result.BaseResult;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.search.wrap.Wrap;
import io.vertx.pgclient.data.Point;

/**
 * Order: 3
 * Description: See computate use cases for real organizations. 
 * AName: a use case
 * PluralName: use cases
 * Icon: <i class="fa-duotone fa-regular fa-file-invoice"></i>
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/use-case
 * EditPageUri: /en-us/edit/use-case/{pageId}
 * DisplayPageUri: /en-us/shop/use-case/{pageId}
 * UserPageUri: /en-us/use/use-case/{pageId}
 * ApiUri: /en-us/api/use-case
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
public class UseCase extends UseCaseGen<BaseResult> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: use case name
   * Description: The use case name. 
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
   * DisplayName: author name
   * Description: The author name. 
   * HtmRow: 3
   * HtmCell: 2
   * HtmColumn: 2
   * Facet: true
   */
  protected void _authorName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: use case description
   * Description: The use case description. 
   * HtmRow: 3
   * HtmCell: 3
   * HtmColumn: 3
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
