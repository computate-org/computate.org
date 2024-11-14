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
 * PluralName: products
 * Icon: <i class="fa-regular fa-conveyor-belt"></i>
 * Sort.asc: productNum
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/product
 * EditPageUri: /en-us/edit/product/{pageId}
 * DisplayPageUri: /en-us/shop/product/{pageId}
 * UserPageUri: /en-us/use/product/{pageId}
 * ApiUri: /en-us/api/product
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 *   SearchDownload:
 *     ApiUri: /download/product
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
	 * Facet: true
	 * DisplayName: Page ID
	 * Description: The ID for this page. 
	 * VarId: true
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
	 * DisplayName: email template
	 * Description: The HTML email template for this product. 
	 */
	protected void _emailTemplate(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 3
	 * HtmCell: 3
	 * Facet: true
	 * DisplayName: URI
	 * Description: The relative URI for this page. 
	 */
	protected void _uri(Wrap<String> w) {
		w.o(String.format(DisplayPage_enUS_StringFormatUri, pageId));
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
	 */
	protected void _url(Wrap<String> w) {
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
	 */
	protected void _viewPage(Wrap<String> w) {
		w.o(String.format(DisplayPage_enUS_StringFormatUrl, siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), pageId));
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * HtmColumn: 3
	 * HtmRow: 5
	 * HtmCell: 3
	 * Facet: true
	 * Description: Edit the project. 
	 * Link: true
	 * Icon: <i class="fa-solid fa-pen-to-square"></i>
	 */
	protected void _editPage(Wrap<String> w) {
		w.o(String.format(EditPage_enUS_StringFormatUrl, siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), pageId));
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
	 * DisplayName: user URI
	 * Description: The user relative URI for this page. 
	 */
	protected void _userUri(Wrap<String> w) {
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
	 * DisplayName: title
	 * Description: The title of this page. 
	 * UrlVar: pageUrlId
	 */
	protected void _title(Wrap<String> w) {
		w.o(name);
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

	@Override
	protected void _objectTitle(Wrap<String> w) {
		w.o(String.format("%s — %s", CompanyProduct_NameAdjectiveSingular_enUS, title));
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
