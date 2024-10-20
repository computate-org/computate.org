package org.computate.site.model.event;

import java.util.List;

import org.computate.search.wrap.Wrap;
import org.computate.site.result.BaseResult;
import org.computate.vertx.config.ComputateConfigKeys;

import io.vertx.pgclient.data.Point;

/**
 * Order: 6
 * 
 * Api: true
 * Page: true
 * PageTemplates: /en-us/event
 * SuperPage: BaseResultPage
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
 * AName: an event
 * Icon: <i class="fa-duotone fa-solid fa-map-location-dot"></i>
 */
public class CompanyEvent extends CompanyEventGen<BaseResult> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: event name
   * Description: The event name. 
   * HtmRow: 3
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
   * DisplayName: event description
   * Description: The event description. 
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
	 * HtmRow: 4
	 * HtmCell: 1
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
	 * HtmRow: 3
	 * HtmCell: 2
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
	 * HtmRow: 3
	 * HtmCell: 2
	 * Facet: true
	 * DisplayName: URL
	 * Description: The URL for this page. 
	 */
	protected void _url(Wrap<String> w) {
		w.o(String.format("%s%s", siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), uri));
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

	@Override
	protected void _objectTitle(Wrap<String> w) {
		w.o(String.format("%s — %s", CompanyEvent_NameAdjectiveSingular_enUS, title));
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
}
