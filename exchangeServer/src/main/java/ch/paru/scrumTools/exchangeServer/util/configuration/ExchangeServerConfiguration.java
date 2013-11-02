package ch.paru.scrumTools.exchangeServer.util.configuration;

import org.apache.commons.configuration.HierarchicalINIConfiguration;

import ch.paru.scrumTools.exchangeServer.EchangeServerException;

public class ExchangeServerConfiguration {
	private static final String EWS_USE_REAL_SERVICE = "ews_useRealService";
	private static final String EWS_URL = "ews_url";
	private static final String EWS_USERNAME = "ews_username";
	private static final String EWS_PASSWORD = "ews_password";
	private static final String CALENDAR_CATEGORY_PREFIX = "calendar_category_";

	private static ExchangeServerConfiguration instance;

	private HierarchicalINIConfiguration config;

	public static void init(String fileName) {
		instance = new ExchangeServerConfiguration(fileName);
	}

	private ExchangeServerConfiguration(String fileName) {
		try {
			config = new HierarchicalINIConfiguration();
			config.load(fileName);
		}
		catch (Exception e) {
			throw new EchangeServerException("config init failed", e);
		}
	}

	public static ExchangeServerConfiguration getInstance() {
		if (instance == null) {
			throw new EchangeServerException("config instance has not been intialized", null);
		}
		return instance;
	}

	public boolean getIsRealServiceUsed() {
		return getConfig().getBoolean(EWS_USE_REAL_SERVICE);
	}

	public String getEwsUrl() {
		return getConfig().getString(EWS_URL);
	}

	public String getEwsUsername() {
		return getConfig().getString(EWS_USERNAME);
	}

	public String getEwsPassword() {
		return getConfig().getString(EWS_PASSWORD);
	}

	public String getCalendarCategory(String key) {
		return getConfig().getString(CALENDAR_CATEGORY_PREFIX + key);
	}

	protected HierarchicalINIConfiguration getConfig() {
		return config;
	}

}
