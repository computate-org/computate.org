package org.computate.site.model.course;

import org.computate.site.config.ConfigKeys;
import org.computate.site.model.BaseModel;
import org.computate.site.page.SitePage;
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
 * Order: 4
 * Description: Take an exciting hands-on course on your own computer with computate
 * AName: a course
 * Icon: <i class="fa-duotone fa-regular fa-notebook"></i>
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
	 * VarName: true
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
	 * Description: The HTML email template for this course. 
	 */
	protected void _emailTemplate(Wrap<String> w) {
	}

	/**
	 * {@inheritDoc}
	 * DisplayName: course page
	 * Description: Visit this course's landing page. 
	 */
	@Override
	protected void _displayPage(Wrap<String> w) {
		super._displayPage(w);
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
	 * DisplayName: related article IDs
	 * Description: The related article IDs comma-separated. 
	 */
	protected void _relatedArticleIds(Wrap<String> w) {
	}

	/**
	 * Ignore: true
	 */
	protected void _relatedArticleSearch(Promise<SearchList<SitePage>> promise) {
		SearchList<SitePage> l = new SearchList<>();
		if(relatedArticleIds != null) {
			List<String> list = Arrays.asList(StringUtils.split(relatedArticleIds, ",")).stream().map(id -> id.trim()).collect(Collectors.toList());
			l.setC(SitePage.class);
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
				obj.remove(SitePage.VAR_relatedArticles);
				obj.remove(SitePage.VAR_relatedArticleIds);
				JsonObject obj2 = new JsonObject();
				obj2.put(SitePage.VAR_pageId, obj.getString(SitePage.VAR_pageId));
				obj2.put(SitePage.VAR_name, obj.getString(SitePage.VAR_name));
				obj2.put(SitePage.VAR_pageImageUri, obj.getString(SitePage.VAR_pageImageUri));
				obj2.put(SitePage.VAR_pageImageWidth, obj.getString(SitePage.VAR_pageImageWidth));
				obj2.put(SitePage.VAR_pageImageHeight, obj.getString(SitePage.VAR_pageImageHeight));
				obj2.put(SitePage.VAR_pageImageAlt, obj.getString(SitePage.VAR_pageImageAlt));
				obj2.put(SitePage.VAR_displayPage, obj.getString(SitePage.VAR_displayPage));
        array.add(obj2);
    });
    w.o(array);
  }
}
