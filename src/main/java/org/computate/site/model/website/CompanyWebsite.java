package org.computate.site.model.website;

import org.computate.site.model.BaseModel;
import org.computate.search.wrap.Wrap;
import io.vertx.pgclient.data.Point;

/**
 * Order: 5
 * Model: true
 * SqlOrder: 5
 * 
 * Api: true
 * Page: true
 * SuperPage.enUS: BaseModelPage
 * Indexed: true
 * Description: See the live websites built with computate
 * 
 * ApiTag: website
 * ApiUri: /api/website
 * 
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   PUTImport:
 *   SearchPage:
 *     Page: CompanyWebsitePage
 *     ApiUri: /website
 * 
 * Role: SiteAdmin
 * PublicRead: true
 * 
 * AName: a website
 * PluralName: websites
 * Icon: <i class="fa-regular fa-globe-pointer"></i>
 */
public class CompanyWebsite extends CompanyWebsiteGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: website name
   * Description: The website name. 
   * HtmRow: 3
   * HtmCell: 2
   * Facet: true
   */
  protected void _name(Wrap<String> w) {
  }
}
