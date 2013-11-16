package ch.paru.scrumTools.server.api.services.configuration;

public enum ConfigurationKeys {
	USE_REAL_SERVICE("useRealService"), //
	URL("url"), //
	USERNAME("username"), //
	PASSWORD("password"), //
	CALENDAR_CATEGORY_PREFIX("calendar_category_"), ;

	private String key;

	private ConfigurationKeys(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
