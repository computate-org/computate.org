package org.computate.site.model.webinar;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.search.list.SearchList;

import org.computate.site.model.BaseModel;

import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.data.Path;
import io.vertx.pgclient.data.Point;
import io.vertx.pgclient.data.Polygon;

/**
 * Order: 7
 * Description: Join the Computate community for regular weekly webinars
 * AName: a webinar
 * PluralName: webinars
 * Icon: <i class="fa-regular fa-video"></i>
 * 
 * PublicRead: true
 * SearchPageUri: /en-us/search/webinar
 * EditPageUri: /en-us/edit/webinar/{pageId}
 * DisplayPageUri: /en-us/view/webinar/{pageId}
 * UserPageUri: /en-us/join/webinar/{pageId}
 * ApiUri: /en-us/api/webinar
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
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
public class CompanyWebinar extends CompanyWebinarGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: name
   * Description: The name of this webinar
   * HtmRow: 3
   * HtmCell: 1
   * HtmColumn: 1
   * HtmRowTitle: webinar details
   * Facet: true
   * VarName: true
   **/
  protected void _name(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: description
   * Description: A description of this webinar
   * HtmRow: 3
   * HtmCell: 2
   * Facet: true
   * HtmColumn: 2
   * VarDescription: true
   **/
  protected void _description(Wrap<String> w) {}

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
   * DisplayName: join URI
   * Description: The join relative URI for this page. 
   */
  protected void _joinUri(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: Webinar URL Americas
   * Description: The actual link to the Webinar in Americas
   * HtmRow: 4
   * HtmCell: 1
   * Facet: true
   **/
  protected void _webinarUrlAmericas(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: Webinar URL APAC
   * Description: The actual link to the Webinar in APAC
   * HtmRow: 4
   * HtmCell: 2
   * Facet: true
   **/
  protected void _webinarUrlApac(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: Webinar URL EMEA
   * Description: The actual link to the Webinar in EMEA
   * HtmRow: 4
   * HtmCell: 3
   * Facet: true
   **/
  protected void _webinarUrlEmea(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: ICal URL
   * Description: The ICalendar URL for the events. 
   * HtmRow: 4
   * HtmCell: 4
   * Facet: true
   **/
  protected void _icalUrl(Wrap<String> w) {}

  /**
   * Val.Fail.enUS: Failed to query the ICal file: %s
   */
  protected void _caldav(Promise<String> promise) {
    if(icalUrl == null) {
      promise.complete();
    } else {
      try {
        ZonedDateTime now = ZonedDateTime.now();
        String utcDateTimeFormat = "yyyyMMdd'T'HHmmss";
        ZoneId zoneId = ZoneId.of("UTC");
        DateTimeFormatter utcDateTimeFormatter = DateTimeFormatter.ofPattern(utcDateTimeFormat);
        String start = utcDateTimeFormatter.format(Instant.now().minus(1, ChronoUnit.HOURS).atZone(zoneId));
        String end = utcDateTimeFormatter.format(Instant.now().plus(1, ChronoUnit.DAYS).atZone(zoneId));
        URL url = new URL(icalUrl);
        String host = url.getHost();
        Integer port = Optional.of(url.getPort()).map(p -> p.equals(-1) ? null : p).orElse("https".equals(url.getProtocol()) ? 443 : 80);
        Boolean ssl = "https".equals(url.getProtocol());
        String uri = url.getPath();
        siteRequest_.getWebClient().get(port, host, uri).ssl(ssl)
            .followRedirects(true)
            .send().onSuccess(response -> {
          try {
            String icalStr = response.body().toString();
            Matcher mEvent = Pattern.compile("^BEGIN:VEVENT($[\\w\\W]+?)^END:VEVENT$", Pattern.MULTILINE).matcher(icalStr);
            boolean mFound = mEvent.find();
            LOG.info(icalStr);
            while (mFound) {
              String eventStr = mEvent.group(1);

              Matcher mStart = Pattern.compile("^DTSTART;TZID=(.*):(.*)", Pattern.MULTILINE).matcher(eventStr);
              mStart.find();
              String startZoneId = mStart.group(1);
              String startDateStr = mStart.group(2);
              ZonedDateTime startDateTime = ZonedDateTime.parse(startDateStr, ComputateZonedDateTimeSerializer.ICAL_FORMATTER.withZone(ZoneId.of(startZoneId)));

              Matcher mEnd = Pattern.compile("^DTEND;TZID=(.*):(.*)", Pattern.MULTILINE).matcher(eventStr);
              mEnd.find();
              String endZoneId = mEnd.group(1);
              String endDateStr = mEnd.group(2);
              ZonedDateTime endDateTime = ZonedDateTime.parse(endDateStr, ComputateZonedDateTimeSerializer.ICAL_FORMATTER.withZone(ZoneId.of(endZoneId)));
              if(endDateTime.isBefore(now)) {
                break;
              }

              ZonedDateTime nextStart = null;
              ZonedDateTime nextEnd = null;
              Matcher mRule = Pattern.compile("^RRULE:(.*)", Pattern.MULTILINE).matcher(eventStr);
              if(mRule.find()) {
                String ruleStr = mRule.group(1);
                if(ruleStr.contains("FREQ=WEEKLY") && ruleStr.contains("BYDAY=") && ruleStr.contains("UNTIL=")) {
                  Matcher mByDay = Pattern.compile(";BYDAY=(.*?);", Pattern.MULTILINE).matcher(ruleStr);
                  String byDayStr = mByDay.group(1);
                  Matcher mUntil = Pattern.compile(";UNTIL=(.*?)[;\n]", Pattern.MULTILINE).matcher(ruleStr);
                  String untilStr = mUntil.group(1);
                  ZonedDateTime until = ZonedDateTime.parse(untilStr, ComputateZonedDateTimeSerializer.ICAL_FORMATTER.withZone(ZoneId.of(startZoneId)));
                  String[] byDays = byDayStr.split(",");
                  for(String byDay : byDays) {
                    ZonedDateTime nextGuess = now
                        .withHour(endDateTime.getHour())
                        .withMinute(endDateTime.getMinute())
                        .withSecond(endDateTime.getSecond())
                        .withNano(endDateTime.getNano())
                        .with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(byDay)));
                    if(nextGuess.isAfter(until)) {
                      break;
                    } else {
                      nextEnd = nextGuess;
                      nextStart = nextGuess
                          .withHour(startDateTime.getHour())
                          .withMinute(startDateTime.getMinute())
                          .withSecond(startDateTime.getSecond())
                          .withNano(startDateTime.getNano());
                      break;
                    }
                  }
                }
                String ruleFreqStr = mRule.group(1);
                String ruleByDayStr = mRule.group(2);
                String untilDateStr = mRule.group(3);
                ZonedDateTime untilDateTime = ZonedDateTime.parse(untilDateStr, ComputateZonedDateTimeSerializer.ICAL_FORMATTER.withZone(ZoneId.of(endZoneId)));
              }

              if(nextStart != null) {
                Matcher mException = Pattern.compile("^EXDATE;TZID=(.*):(.*)", Pattern.MULTILINE).matcher(eventStr);
                boolean mExceptionFound = mException.find();
                while (mExceptionFound) {
                  String exceptionZoneId = mException.group(1);
                  String exceptionDateStr = mException.group(2);
                  ZonedDateTime exceptionDateTime = ZonedDateTime.parse(exceptionDateStr, ComputateZonedDateTimeSerializer.ICAL_FORMATTER.withZone(ZoneId.of(exceptionZoneId)));
                  if(exceptionDateTime.isEqual(nextStart)
                      || exceptionDateTime.isEqual(nextEnd)
                      || exceptionDateTime.isAfter(nextStart) && exceptionDateTime.isBefore(nextEnd)) {
                    nextStart = null;
                    nextEnd = null;
                    break;
                  }
                }
              }
              if(nextStart != null) {
                nextWebinar = nextStart;
              }
            }
            promise.complete();
          } catch(Throwable ex) {
            LOG.error(String.format(caldavFail_enUS, icalUrl), ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format(caldavFail_enUS, icalUrl), ex);
          promise.fail(ex);
        });
      } catch(Throwable ex) {
        LOG.error(String.format(caldavFail_enUS, icalUrl), ex);
        promise.fail(ex);
      }
    }
  }

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Modify: false
	 * HtmRow: 4
	 * HtmCell: 5
   * HtmColumn: 2
	 * DisplayName.enUS: next webinar
	 * FormatHtm: MMM d, yyyy h:mm:ss a
	 * Description: The start date time of the next webinar. 
	 * Facet: true
	 */
	protected void _nextWebinar(Wrap<ZonedDateTime> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Link: true
   * HtmColumn: 0
   * DisplayName: Join the webinar
   * Icon: <i class="fa-solid fa-video"></i>
   * Description: Access the webinar links. 
   */
  protected void _joinUrl(Wrap<String> w) {
    w.o(String.format("%s%s", siteRequest_.getConfig().getString(ComputateConfigKeys.SITE_BASE_URL), joinUri));
  }
}
