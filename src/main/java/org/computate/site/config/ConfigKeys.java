
package org.computate.site.config;

import org.computate.vertx.config.ComputateConfigKeys;

import io.vertx.core.json.JsonObject;

/**
 * Keyword: classSimpleNameConfigKeys
 */
public class ConfigKeys extends ComputateConfigKeys {

  public static JsonObject getPageContext(JsonObject config) {
    JsonObject ctx = ComputateConfigKeys.getPageContext(config);
    ctx.put(SITE_OS_NAME, config.getString(SITE_OS_NAME));
    ctx.put(SITE_OS_SHORT_NAME, config.getString(SITE_OS_SHORT_NAME));
    ctx.put(SITE_OS_VERSION, config.getString(SITE_OS_VERSION));
    return ctx;
  }

  public static final String SITE_OS_NAME = "SITE_OS_NAME";
  public static final String SITE_OS_SHORT_NAME = "SITE_OS_SHORT_NAME";
  public static final String SITE_OS_VERSION = "SITE_OS_VERSION";
}
