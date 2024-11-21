package org.computate.site.model.course;

import org.computate.site.model.BaseModel;
import org.computate.site.result.BaseResult;
import org.computate.vertx.config.ComputateConfigKeys;

import java.math.BigDecimal;

import org.computate.search.wrap.Wrap;
import io.vertx.pgclient.data.Point;

/**
 * Order: 4
 * Description: Take an exciting hands-on course on your own computer with computate
 * AName: a course
 * Icon: <i class="fa-regular fa-notebook"></i>
 * Sort.asc: courseNum
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/course
 * EditPageUri: /en-us/edit/course/{pageId}
 * DisplayPageUri: /en-us/shop/course/{pageId}
 * UserPageUri: /en-us/use/course/{pageId}
 * ApiUri: /en-us/api/course
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
public class CompanyCourse extends CompanyCourseGen<BaseResult> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: course name
   * Description: The course name. 
   * HtmRow: 3
   * HtmCell: 1
   * Facet: true
   * Required: true
	 * HtmColumn: 1
   */
  protected void _name(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: course description
   * Description: The course description. 
   * HtmRow: 3
   * HtmCell: 2
   * Facet: true
   * Multiline: true
	 * HtmColumn: 2
   */
  protected void _description(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: course description
   * Description: The course description. 
   * HtmRow: 3
   * HtmCell: 3
   * Facet: true
   * Multiline: true
   */
  protected void _price(Wrap<BigDecimal> w) {
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
	 * DisplayName: email template
	 * Description: The HTML email template for this product. 
	 */
	protected void _emailTemplate(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRowTitleOpen: Useful URLs
	 * HtmRow: 4
	 * HtmCell: 1
	 * Facet: true
	 * DisplayName: product page
	 * Description: Visit this product's landing page. 
	 * Link: true
	 * VarUrlDisplayPage: true
	 */
	protected void _displayPage(Wrap<String> w) {
		w.o(String.format(DisplayPage_enUS_StringFormatUrl, siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), pageId));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * HtmRow: 4
	 * HtmCell: 2
	 * Facet: true
	 * DisplayName: view
	 * Description: View the project. 
	 * Link: true
	 * Icon: <i class="fa-solid fa-pen-to-square"></i>
	 * VarUrlEditPage: true
	 */
	protected void _editPage(Wrap<String> w) {
		w.o(String.format(EditPage_enUS_StringFormatUrl, siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), pageId));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: store URL
	 * Description: The store URL for this page. 
	 */
	protected void _storeUrl(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: download URI
	 * Description: The download relative URI for this page. 
	 */
	protected void _downloadUri(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: Course Number
	 * Description: The course number for this page. 
	 */
	protected void _courseNum(Wrap<Integer> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: title
	 * Description: The title of this page. 
	 * VarTitle: true
	 */
	protected void _title(Wrap<String> w) {
		w.o(String.format("%s — %s", NameAdjectiveSingular_enUS, name));
	}
}
