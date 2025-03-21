package org.computate.site.model.product;

import org.computate.site.result.BaseResult;
import org.computate.vertx.config.ComputateConfigKeys;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.computate.search.wrap.Wrap;
import io.vertx.pgclient.data.Point;

/**
 * Order: 5
 * Description: See the computate products that will help you build your own data-driven platforms
 * AName: a product
 * Icon: <i class="fa-duotone fa-regular fa-conveyor-belt"></i>
 * Sort.asc: productNum
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/product
 * EditPageUri: /en-us/edit/product/{pageId}
 * DisplayPageUri: /en-us/shop/product/{pageId}
 * UserPageUri: /en-us/use/product/{pageId}
 * DownloadUri: /en-us/download/product/{pageId}
 * ApiUri: /en-us/api/product
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 *   Download:
 *     ApiUri: /en-us/download/product/{pageId}
 *     ApiMediaType200: application/zip
 *     Role: User
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
public class CompanyProduct extends CompanyProductGen<BaseResult> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: product name
   * Description: The product name. 
   * HtmRow: 3
	 * HtmRowTitleOpen: product details
   * HtmCell: 1
   * HtmColumn: 0
   * Facet: true
	 * VarName: true
   */
  protected void _name(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: product description
   * Description: The product description. 
   * HtmRow: 3
   * HtmCell: 2
   * HtmColumn: 1
   * Facet: true
	 * VarDescription: true
   */
  protected void _description(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: price
   * Description: The price of the product per developer. 
   * HtmRow: 3
   * HtmCell: 3
   * Facet: true
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
	 * DisplayName: download URL
	 * Description: The download URL for this page. 
	 * VarUrlDownload: true
	 */
	protected void _downloadUrl(Wrap<String> w) {
		String f = classStringFormatUrlDownloadForClass();
		if(f != null)
			w.o(String.format(f, siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), idForClass()));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * Facet: true
	 * DisplayName: Product Number
	 * Description: The product number for this page. 
	 */
	protected void _productNum(Wrap<Integer> w) {
	}
}
