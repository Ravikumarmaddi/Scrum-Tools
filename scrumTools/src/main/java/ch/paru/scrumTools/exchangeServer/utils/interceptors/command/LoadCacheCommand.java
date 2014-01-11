package ch.paru.scrumTools.exchangeServer.utils.interceptors.command;

import java.util.Map;

import ch.paru.scrumTools.exchangeServer.services.configuration.ConfigurationKeys;
import ch.paru.scrumTools.exchangeServer.utils.configuration.ExchangeServerConfiguration;

public class LoadCacheCommand extends InterceptorCommand {

	public LoadCacheCommand() {
		super(InterceptorCommandType.LOAD_CACHE);
	}

	public boolean loadCacheEnabled() {
		ExchangeServerConfiguration configuration = ExchangeServerConfiguration.getInstance();
		return configuration.getBooleanValue(ConfigurationKeys.CACHE_INTERCEPTOR_PREFIX, "load_enabled", false);
	}

	private String getCacheDir() {
		ExchangeServerConfiguration configuration = ExchangeServerConfiguration.getInstance();
		return configuration.getStringValue(ConfigurationKeys.CACHE_INTERCEPTOR_PREFIX, "folder");
	}

	public void loadData(String name, Map<String, Object> data) {
		if (!loadCacheEnabled()) {
			return;
		}

		CacheFileHandler handler = new CacheFileHandler();
		Map<String, Object> content = handler.load(getCacheDir(), name);
		data.putAll(content);
	}
}
