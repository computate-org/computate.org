package org.computate.site.model.event;

import org.computate.search.wrap.Wrap;
import org.computate.site.model.BaseModel;

import io.vertx.pgclient.data.Point;

/**
 * Order: 1
 * Model: true
 * SqlOrder: 1
 * 
 * Api: true
 * Page: true
 * SuperPage.enUS: BaseModelPage
 * Indexed: true
 * Description: An event
 * 
 * ApiTag:  Computate Event
 * ApiUri: /api/event
 * 
 * ApiMethod.enUS: Search
 * ApiMethod: GET
 * ApiMethod: PATCH
 * ApiMethod: POST
 * ApiMethod: PUTImport
 * 
 * ApiMethod: SearchPage
 * Page.SearchPage: ComputateEventPage
 * ApiUri.SearchPage: /event
 * 
 * Role: SiteAdmin
 * 
 * AName: an event
 * IconGroup: duotone
 * IconName: map-location-dot
 */
public class ComputateEvent extends ComputateEventGen<BaseModel> {

  protected void _id(Wrap<String> w) {
  }

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * DisplayName: map location
	 * HtmRow: 3
	 * HtmCell: 1
	 * Facet: true
	 */
	protected void _location(Wrap<Point> w) {
	}
}
