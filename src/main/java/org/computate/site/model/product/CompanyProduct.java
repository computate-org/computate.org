package org.computate.site.model.product;

import org.computate.site.result.BaseResult;
import org.computate.vertx.config.ComputateConfigKeys;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.computate.search.wrap.Wrap;
import io.vertx.pgclient.data.Point;

/**
 * Order: 5
 * 
 * Api: true
 * Page: true
 * PageTemplates: /en-us/product
 * UserPageTemplates: /en-us/user/product
 * SuperPage: BaseResultPage
 * Indexed: true
 * Description: See the computate products that will help you build your own data-driven platforms
 * 
 * ApiTag: product
 * ApiUri: /api/product
 * 
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   PUTImport:
 *   SearchPage:
 *     Page: CompanyProductPage
 *     ApiUri: /product
 *   SearchDownload:
 *     ApiUri: /download/product
 *     ApiMediaType200: application/zip
 *     Role: User
 * AuthGroup:
 *   SuperAdmin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     SuperAdmin:
 * 
 * PublicRead: true
 * 
 * AName: a product
 * PluralName: products
 * Icon: <i class="fa-regular fa-conveyor-belt"></i>
 * Sort.asc: productNum
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
   * HtmColumn: 1
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
   * HtmColumn: 2
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
	}

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmRow: 4
	 * HtmRowTitleOpen: Useful URLs
	 * HtmCell: 1
	 * Facet: true
	 * DisplayName: product page
	 * Description: Visit this product's landing page. 
	 * Link: true
	 */
	protected void _url(Wrap<String> w) {
		w.o(String.format("%s%s", siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), uri));
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
