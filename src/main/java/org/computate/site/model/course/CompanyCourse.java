package org.computate.site.model.course;

import org.computate.site.model.BaseModel;
import org.computate.search.wrap.Wrap;
import io.vertx.pgclient.data.Point;

/**
 * Order: 4
 * Model: true
 * SqlOrder: 4
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
 *     Page: CompanyCoursePage
 *     ApiUri: /course
 * 
 * Role: SiteAdmin
 * PublicRead: true
 * 
 * AName: a course
 * Icon: <i class="fa-regular fa-notebook"></i>
 */
public class CompanyCourse extends CompanyCourseGen<BaseModel> {

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
	 * HtmColumn: 2
   */
  protected void _courseDescription(Wrap<String> w) {
  }

  @Override
  protected void _objectTitle(Wrap<String> w) {
    w.o(courseName == null ? String.format("%s %s", CLASS_SIMPLE_NAME, pk) : courseName);
  }
}
