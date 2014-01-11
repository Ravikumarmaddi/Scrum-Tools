package ch.paru.scrumTools.exchangeServer.utils.interceptors.command;

import ch.paru.scrumTools.exchangeServer.services.configuration.ConfigurationKeys;
import ch.paru.scrumTools.exchangeServer.utils.configuration.ExchangeServerConfiguration;

public class LoadCacheCommand extends InterceptorCommand {

	public LoadCacheCommand() {
		super(InterceptorCommandType.STORE_CACHE);
	}

	public boolean loadCache() {
		ExchangeServerConfiguration configuration = ExchangeServerConfiguration.getInstance();
		return configuration.getBooleanValue(ConfigurationKeys.INTERCEPTOR_PREFIX, getType().getConfigName(), false);
	}
}
