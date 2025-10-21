
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
    ctx.put(AUTHORIZE_NET_API_LOGIN_ID, config.getString(AUTHORIZE_NET_API_LOGIN_ID));
    ctx.put(AUTHORIZE_NET_PUBLIC_CLIENT_KEY, config.getString(AUTHORIZE_NET_PUBLIC_CLIENT_KEY));
    return ctx;
  }

  public static final String SITE_OS_NAME = "SITE_OS_NAME";
  public static final String SITE_OS_SHORT_NAME = "SITE_OS_SHORT_NAME";
  public static final String SITE_OS_VERSION = "SITE_OS_VERSION";

	public static final String ENABLE_AUTHORIZE_NET = "ENABLE_AUTHORIZE_NET";
	public static final String AUTHORIZE_NET_ENVIRONMENT = "AUTHORIZE_NET_ENVIRONMENT";
	public static final String AUTHORIZE_NET_URL = "AUTHORIZE_NET_URL";
	public static final String AUTHORIZE_NET_ACCEPT_JS_URL = "AUTHORIZE_NET_ACCEPT_JS_URL";
	public static final String AUTHORIZE_NET_API_LOGIN_ID = "AUTHORIZE_NET_API_LOGIN_ID";
	public static final String AUTHORIZE_NET_TRANSACTION_KEY = "AUTHORIZE_NET_TRANSACTION_KEY";
	public static final String AUTHORIZE_NET_SIGNATURE_KEY = "AUTHORIZE_NET_SIGNATURE_KEY";
	public static final String AUTHORIZE_NET_PUBLIC_CLIENT_KEY = "AUTHORIZE_NET_PUBLIC_CLIENT_KEY";
	public static final String AUTHORIZE_NET_NOTIFICATION_URL = "AUTHORIZE_NET_NOTIFICATION_URL";
	public static final String AUTHORIZE_NET_WEBHOOK_UNLOCK_MILLIS = "AUTHORIZE_NET_WEBHOOK_UNLOCK_MILLIS";
	public static final String AUTHORIZE_NET_WEBHOOK_TIMEOUT_MILLIS = "AUTHORIZE_NET_WEBHOOK_TIMEOUT_MILLIS";
	public static final String GITHUB_TEAMS_TOKEN = "GITHUB_TEAMS_TOKEN";
	public static final String GITHUB_DEVELOPERS_TOKEN = "GITHUB_DEVELOPERS_TOKEN";
}
