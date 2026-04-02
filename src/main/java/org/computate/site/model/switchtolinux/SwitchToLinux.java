package org.computate.site.model.switchtolinux;

import org.computate.site.config.ConfigKeys;
import org.computate.site.page.SitePage;
import org.computate.site.result.BaseResult;
import org.computate.vertx.search.list.SearchList;

import java.awt.image.BufferedImage;
import java.io.File;
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

/**
 * Order: 4
 * Description: Learn how to switch to Linux to run and develop open source cloud applications in OpenShift Local on your own computer. 
 * AName: a Switch to Linux
 * PluralName: Switch to Linux
 * Icon: <i class="fa-brands fa-linux"></i>
 * Sort.asc: courseNum
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/switch-to-linux
 * EditPageUri: /en-us/edit/switch-to-linux/{pageId}
 * DisplayPageUri: /en-us/learn/switch-to-linux/{pageId}
 * ApiUri: /en-us/api/switch-to-linux
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
public class SwitchToLinux extends SwitchToLinuxGen<BaseResult> {

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
   * HtmRow: 3
   * HtmCell: 3
   * Facet: true
   * DisplayName: author name
   * Description: The author name
   */
  protected void _authorName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 3
   * HtmCell: 3
   * Facet: true
   * DisplayName: author URL
   * Description: The author URL
   */
  protected void _authorUrl(Wrap<String> w) {
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
   * DisplayName: Course Number
   * Description: The course number for this page. 
   */
  protected void _courseNum(Wrap<Integer> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Facet: true
   * DisplayName: video URL
   * Description: The video URL for this page. 
   */
  protected void _pageVideoUrl(Wrap<String> w) {
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
   * DisplayName: prerequisite article IDs
   * Description: The prerequisite article IDs comma-separated. 
   */
  protected void _prerequisiteArticleIds(Wrap<String> w) {
  }

  /**
   * Ignore: true
   */
  protected void _prerequisiteArticleSearch(Promise<SearchList<SwitchToLinux>> promise) {
    SearchList<SwitchToLinux> l = new SearchList<>();
    if(prerequisiteArticleIds != null) {
      List<String> list = Arrays.asList(StringUtils.split(prerequisiteArticleIds, ",")).stream().map(id -> id.trim()).collect(Collectors.toList());
      l.setC(SwitchToLinux.class);
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
   * DisplayName: prerequisite articles
   * Description: A JSON array of prerequisite articles. 
   */
  protected void _prerequisiteArticles(Wrap<JsonArray> w) {
    JsonArray array = new JsonArray();
    prerequisiteArticleSearch.getList().stream().forEach(prerequisiteArticle -> {
        JsonObject obj = JsonObject.mapFrom(prerequisiteArticle);
        obj.remove(SwitchToLinux.VAR_prerequisiteArticles);
        obj.remove(SwitchToLinux.VAR_prerequisiteArticleIds);
        JsonObject obj2 = new JsonObject();
        obj2.put(SwitchToLinux.VAR_pageId, obj.getString(SwitchToLinux.VAR_pageId));
        obj2.put(SwitchToLinux.VAR_name, obj.getString(SwitchToLinux.VAR_name));
        obj2.put(SwitchToLinux.VAR_pageImageUri, obj.getString(SwitchToLinux.VAR_pageImageUri));
        obj2.put(SwitchToLinux.VAR_pageImageWidth, obj.getString(SwitchToLinux.VAR_pageImageWidth));
        obj2.put(SwitchToLinux.VAR_pageImageHeight, obj.getString(SwitchToLinux.VAR_pageImageHeight));
        obj2.put(SwitchToLinux.VAR_pageImageAlt, obj.getString(SwitchToLinux.VAR_pageImageAlt));
        obj2.put(SwitchToLinux.VAR_displayPage, obj.getString(SwitchToLinux.VAR_displayPage));
        array.add(obj2);
    });
    w.o(array);
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: next article IDs
   * Description: The next article IDs comma-separated. 
   */
  protected void _nextArticleIds(Wrap<String> w) {
  }

  /**
   * Ignore: true
   */
  protected void _nextArticleSearch(Promise<SearchList<SwitchToLinux>> promise) {
    SearchList<SwitchToLinux> l = new SearchList<>();
    if(nextArticleIds != null) {
      List<String> list = Arrays.asList(StringUtils.split(nextArticleIds, ",")).stream().map(id -> id.trim()).collect(Collectors.toList());
      l.setC(SwitchToLinux.class);
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
   * DisplayName: next articles
   * Description: A JSON array of next articles. 
   */
  protected void _nextArticles(Wrap<JsonArray> w) {
    JsonArray array = new JsonArray();
    nextArticleSearch.getList().stream().forEach(nextArticle -> {
        JsonObject obj = JsonObject.mapFrom(nextArticle);
        obj.remove(SwitchToLinux.VAR_nextArticles);
        obj.remove(SwitchToLinux.VAR_nextArticleIds);
        JsonObject obj2 = new JsonObject();
        obj2.put(SwitchToLinux.VAR_pageId, obj.getString(SwitchToLinux.VAR_pageId));
        obj2.put(SwitchToLinux.VAR_name, obj.getString(SwitchToLinux.VAR_name));
        obj2.put(SwitchToLinux.VAR_pageImageUri, obj.getString(SwitchToLinux.VAR_pageImageUri));
        obj2.put(SwitchToLinux.VAR_pageImageWidth, obj.getString(SwitchToLinux.VAR_pageImageWidth));
        obj2.put(SwitchToLinux.VAR_pageImageHeight, obj.getString(SwitchToLinux.VAR_pageImageHeight));
        obj2.put(SwitchToLinux.VAR_pageImageAlt, obj.getString(SwitchToLinux.VAR_pageImageAlt));
        obj2.put(SwitchToLinux.VAR_displayPage, obj.getString(SwitchToLinux.VAR_displayPage));
        array.add(obj2);
    });
    w.o(array);
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
