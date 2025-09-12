
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
    ctx.put(AUTHORIZE_NET_ENVIRONMENT, config.getString(AUTHORIZE_NET_ENVIRONMENT));
    ctx.put(AUTHORIZE_NET_URL, config.getString(AUTHORIZE_NET_URL));
    ctx.put(AUTHORIZE_NET_API_HOST_NAME, config.getString(AUTHORIZE_NET_API_HOST_NAME));
    ctx.put(AUTHORIZE_NET_API_PORT, config.getString(AUTHORIZE_NET_API_PORT));
    ctx.put(AUTHORIZE_NET_API_SSL, config.getString(AUTHORIZE_NET_API_SSL));
    ctx.put(AUTHORIZE_NET_API_URI, config.getString(AUTHORIZE_NET_API_URI));
    ctx.put(AUTHORIZE_NET_API_LOGIN_ID, config.getString(AUTHORIZE_NET_API_LOGIN_ID));
    ctx.put(AUTHORIZE_NET_PUBLIC_CLIENT_KEY, config.getString(AUTHORIZE_NET_PUBLIC_CLIENT_KEY));
    return ctx;
  }

  public static final String SITE_OS_NAME = "SITE_OS_NAME";
  public static final String SITE_OS_SHORT_NAME = "SITE_OS_SHORT_NAME";
  public static final String SITE_OS_VERSION = "SITE_OS_VERSION";
}
