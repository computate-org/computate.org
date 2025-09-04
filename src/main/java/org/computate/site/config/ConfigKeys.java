
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
    ctx.put(AUTHORIZE_NET_URL, config.getString(AUTHORIZE_NET_URL));
    return ctx;
  }

  public static final String SITE_OS_NAME = "SITE_OS_NAME";
  public static final String SITE_OS_SHORT_NAME = "SITE_OS_SHORT_NAME";
  public static final String SITE_OS_VERSION = "SITE_OS_VERSION";

	public static final String ENABLE_AUTHORIZE_NET = "ENABLE_AUTHORIZE_NET";
	public static final String AUTHORIZE_NET_URL = "AUTHORIZE_NET_URL";
	public static final String AUTHORIZE_NET_API_LOGIN_ID = "AUTHORIZE_NET_API_LOGIN_ID";
	public static final String AUTHORIZE_NET_TRANSACTION_KEY = "AUTHORIZE_NET_TRANSACTION_KEY";
	public static final String AUTHORIZE_NET_NOTIFICATION_URL = "AUTHORIZE_NET_NOTIFICATION_URL";
	public static final String AUTHORIZE_NET_WEBHOOK_UNLOCK_MILLIS = "AUTHORIZE_NET_WEBHOOK_UNLOCK_MILLIS";
	public static final String AUTHORIZE_NET_WEBHOOK_TIMEOUT_MILLIS = "AUTHORIZE_NET_WEBHOOK_TIMEOUT_MILLIS";
}
