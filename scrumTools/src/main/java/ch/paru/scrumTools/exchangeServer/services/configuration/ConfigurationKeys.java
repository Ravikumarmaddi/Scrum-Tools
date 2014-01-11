package ch.paru.scrumTools.exchangeServer.services.configuration;

public enum ConfigurationKeys {
	USE_REAL_SERVICE("useRealService"), //
	URL("url"), //
	USERNAME("username"), //
	PASSWORD("password"), //
	CALENDAR_CATEGORY_PREFIX("calendar_category_"), //
	CACHE_INTERCEPTOR_PREFIX("cacheinterceptor_");

	private String key;

	private ConfigurationKeys(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
