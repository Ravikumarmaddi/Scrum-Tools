package ch.paru.scrumTools.exchangeServer.util.configuration;

import org.apache.commons.configuration.HierarchicalINIConfiguration;

import ch.paru.scrumTools.server.api.exceptions.EchangeServerException;

public class ExchangeServerConfiguration {
	private static final String USE_REAL_SERVICE = "useRealService";
	private static final String URL = "url";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
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
		return getConfig().getBoolean(USE_REAL_SERVICE);
	}

	public String getUrl() {
		return getConfig().getString(URL);
	}

	public String getUsername() {
		return getConfig().getString(USERNAME);
	}

	public String getPassword() {
		return getConfig().getString(PASSWORD);
	}

	public String getCalendarCategory(String key) {
		return getConfig().getString(CALENDAR_CATEGORY_PREFIX + key);
	}

	protected HierarchicalINIConfiguration getConfig() {
		return config;
	}

}
