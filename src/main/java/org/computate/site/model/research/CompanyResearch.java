package org.computate.site.model.research;

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
 * Description: Read about the computate project related research going on at Red Hat research
 * 
 * ApiTag: research
 * ApiUri: /api/research
 * 
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   PUTImport:
 *   SearchPage:
 *     Page: CompanyResearchPage
 *     ApiUri: /research
 * 
 * Role: SiteAdmin
 * 
 * AName: a research
 * PluralName: research
 * Icon: <i class="fa-duotone fa-solid fa-school"></i>
 */
public class CompanyResearch extends CompanyResearchGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: research name
   * Description: The research name. 
   * HtmRow: 3
   * HtmCell: 2
   * Facet: true
   */
  protected void _name(Wrap<String> w) {
  }
}
