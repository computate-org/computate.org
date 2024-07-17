package org.computate.site.model.course;

import org.computate.site.model.BaseModel;
import org.computate.search.wrap.Wrap;
import io.vertx.pgclient.data.Point;

/**
 * Order: 3
 * Model: true
 * SqlOrder: 3
 * 
 * Api: true
 * Page: true
 * SuperPage.enUS: BaseModelPage
 * Indexed: true
 * Description: Take an exciting hands-on course on your own computer with computate
 * 
 * ApiTag: course
 * ApiUri: /api/course
 * 
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   PUTImport:
 *   SearchPage:
 *     Page: ComputateCoursePage
 *     ApiUri: /course
 * 
 * Role: SiteAdmin
 * 
 * AName: a course
 * IconGroup: regular
 * IconName: notebook
 */
public class ComputateCourse extends ComputateCourseGen<BaseModel> {

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
   */
  protected void _courseName(Wrap<String> w) {
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
   */
  protected void _courseDescription(Wrap<String> w) {
  }

  @Override
  protected void _objectTitle(Wrap<String> w) {
    w.o(courseName == null ? String.format("%s %s", CLASS_SIMPLE_NAME, pk) : courseName);
  }
}
