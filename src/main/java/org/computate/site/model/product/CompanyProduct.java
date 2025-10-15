package org.computate.site.model.product;

import org.computate.site.config.ConfigKeys;
import org.computate.site.result.BaseResult;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.search.list.SearchList;

import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
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
 * UserPageUri: /en-us/shop/product/{pageId}
 * EditPageUri: /en-us/edit/product/{pageId}
 * ApiUri: /en-us/api/product
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
   * DisplayName: product auth resource
   * Description: The unique authorization resource for the company product for multi-tenancy
   * Facet: true
   * AuthorizationResource: COMPANYPRODUCT
   **/
  protected void _productResource(Wrap<String> w) {
    w.o(String.format("%s-%s", CompanyProduct.CLASS_AUTH_RESOURCE, pageId));
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

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 4
   * HtmCell: 1
   * Facet: true
   * DisplayName: imageUri
   * Description: The page image URI
   */
  protected void _pageImageUri(Wrap<String> w) {
  }
  
  /**
   * DocValues: true
   * Description: The image width
   */
  protected void _pageImageWidth(Wrap<Integer> w) {
    if(pageImageUri != null) {
      Path path = Paths.get(siteRequest_.getConfig().getString(ConfigKeys.STATIC_PATH), pageImageUri);
      File file = path.toFile();
      if(file.exists()) {
        try {
          BufferedImage img = ImageIO.read(file);
          w.o(img.getWidth());
          setPageImageHeight(img.getHeight());
          setPageImageType(Files.probeContentType(path));
        } catch (Exception ex) {
          ExceptionUtils.rethrow(ex);
        }
      }
    }
  }

  /**
   * DocValues: true
   * Description: The image height
   */
  protected void _pageImageHeight(Wrap<Integer> c) {
  }

  /**
   * DocValues: true
   * Description: The image height
   */
  protected void _pageImageType(Wrap<String> c) {
  }

  /**
   * Persist: true
   * DocValues: true
   * Description: The image accessibility text. 
   */
  protected void _pageImageAlt(Wrap<String> c) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: labels string
   * Description: The labels String for this article comma-separated. 
   */
  protected void _labelsString(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: labels
   * Description: The labels for this article. 
   */
  protected void _labels(List<String> l) {
    if(labelsString != null) {
      l.addAll(Arrays.asList(StringUtils.split(labelsString, ",")).stream().map(id -> id.trim()).collect(Collectors.toList()));
    }
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: related article IDs
   * Description: The related article IDs comma-separated. 
   */
  protected void _relatedArticleIds(Wrap<String> w) {
  }

  /**
   * Ignore: true
   */
  protected void _relatedArticleSearch(Promise<SearchList<CompanyProduct>> promise) {
    SearchList<CompanyProduct> l = new SearchList<>();
    if(relatedArticleIds != null) {
      List<String> list = Arrays.asList(StringUtils.split(relatedArticleIds, ",")).stream().map(id -> id.trim()).collect(Collectors.toList());
      l.setC(CompanyProduct.class);
      l.q("*:*");
      l.fq(String.format("pageId_docvalues_string:" + list.stream()
          .map(id -> SearchTool.escapeQueryChars(id))
          .collect(Collectors.joining(" OR ", "(", ")"))
          ));
      l.setStore(true);
    }
    promise.complete(l);
  }

  /**
   * {@inheritDoc}
   * Stored: true
   * DisplayName: related articles
   * Description: A JSON array of related articles. 
   */
  protected void _relatedArticles(Wrap<JsonArray> w) {
    JsonArray array = new JsonArray();
    relatedArticleSearch.getList().stream().forEach(relatedArticle -> {
        JsonObject obj = JsonObject.mapFrom(relatedArticle);
        obj.remove(CompanyProduct.VAR_relatedArticles);
        obj.remove(CompanyProduct.VAR_relatedArticleIds);
        JsonObject obj2 = new JsonObject();
        obj2.put(CompanyProduct.VAR_pageId, obj.getString(CompanyProduct.VAR_pageId));
        obj2.put(CompanyProduct.VAR_name, obj.getString(CompanyProduct.VAR_name));
        obj2.put(CompanyProduct.VAR_pageImageUri, obj.getString(CompanyProduct.VAR_pageImageUri));
        obj2.put(CompanyProduct.VAR_pageImageWidth, obj.getString(CompanyProduct.VAR_pageImageWidth));
        obj2.put(CompanyProduct.VAR_pageImageHeight, obj.getString(CompanyProduct.VAR_pageImageHeight));
        obj2.put(CompanyProduct.VAR_pageImageAlt, obj.getString(CompanyProduct.VAR_pageImageAlt));
        obj2.put(CompanyProduct.VAR_displayPage, obj.getString(CompanyProduct.VAR_displayPage));
        array.add(obj2);
    });
    w.o(array);
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: dialog template
   * Description: The dialog template for this product. 
   */
  protected void _dialogTemplate(Wrap<String> w) {
    w.o(String.format("en-us/dialog/product/dialog-%s.htm", pageId));
  }

  @Override
  public String classStringFormatUrlDisplayPageForClass() {
    return "%s/en-us/search/product?fq=pageId:%s";
  }
}
