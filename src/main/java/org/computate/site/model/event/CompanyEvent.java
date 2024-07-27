package org.computate.site.model.event;

import java.util.List;

import org.computate.search.wrap.Wrap;
import org.computate.site.model.BaseModel;

import io.vertx.pgclient.data.Point;

/**
 * Order: 2
 * Model: true
 * SqlOrder: 2
 * 
 * Api: true
 * Page: true
 * SuperPage.enUS: BaseModelPage
 * Indexed: true
 * Description: See the upcoming computate in-person and online events
 * 
 * ApiTag:  Computate Event
 * ApiUri: /api/event
 * 
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   PUTImport:
 *   SearchPage:
 *     Page: CompanyEventPage
 *     ApiUri: /event
 *     PublicRead: true
 * 
 * Role: SiteAdmin
 * 
 * AName: an event
 * IconGroup: duotone
 * IconName: map-location-dot
 */
public class CompanyEvent extends CompanyEventGen<BaseModel> {

  protected void _id(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * Location: true
   * DocValues: true
   * Persist: true
   * DisplayName: map location
   * HtmRow: 3
   * HtmCell: 1
   * Facet: true
   */
  protected void _location(Wrap<Point> w) {
  }

  /**
   * {@inheritDoc}
   * LocationColor: true
   * Indexed: true
   * Stored: true
   * DisplayName: location colors
   * Description: The colors of each location Points. 
   */
  protected void _locationColors(List<String> l) {
    l.add("#3388ff");
  }

  /**
   * {@inheritDoc}
   * LocationTitle: true
   * Indexed: true
   * Stored: true
   * DisplayName: location titles
   * Description: The titles of each location Paths. 
   */
  protected void _locationTitles(List<String> l) {
    l.add(objectTitle);
  }

  /**
   * {@inheritDoc}
   * LocationUrl: true
   * Indexed: true
   * Stored: true
   * DisplayName: location links
   * Description: The links of each location Paths. 
   */
  protected void _locationLinks(List<String> l) {
    l.add(pageUrlId);
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: event name
   * Description: The event name. 
   * HtmRow: 3
   * HtmCell: 2
   * Facet: true
   */
  protected void _eventName(Wrap<String> w) {
  }
}
