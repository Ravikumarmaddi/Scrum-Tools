package ch.paru.scrumTools.exchangeServer.utils.interceptors.command;

import java.util.Map;

import ch.paru.scrumTools.exchangeServer.services.configuration.ConfigurationKeys;
import ch.paru.scrumTools.exchangeServer.utils.configuration.ExchangeServerConfiguration;

public class StoreCacheCommand extends InterceptorCommand {

	public StoreCacheCommand() {
		super(InterceptorCommandType.STORE_CACHE);
	}

	private boolean storeCacheEnabled() {
		ExchangeServerConfiguration configuration = ExchangeServerConfiguration.getInstance();
		return configuration.getBooleanValue(ConfigurationKeys.CACHE_INTERCEPTOR_PREFIX, "store_enabled", false);
	}

	private String getCacheDir() {
		ExchangeServerConfiguration configuration = ExchangeServerConfiguration.getInstance();
		return configuration.getStringValue(ConfigurationKeys.CACHE_INTERCEPTOR_PREFIX, "folder");
	}

	public void storeData(String name, Map<String, Object> data) {
		if (!storeCacheEnabled()) {
			return;
		}

		CacheFileHandler handler = new CacheFileHandler(getCacheDir(), name);
		handler.store(data);
	}
}
