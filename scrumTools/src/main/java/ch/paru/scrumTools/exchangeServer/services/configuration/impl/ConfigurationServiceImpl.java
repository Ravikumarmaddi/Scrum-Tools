package ch.paru.scrumTools.exchangeServer.services.configuration.impl;

import microsoft.exchange.webservices.data.ExchangeService;
import ch.paru.scrumTools.exchangeServer.services.configuration.ConfigurationKeys;
import ch.paru.scrumTools.exchangeServer.services.configuration.ConfigurationService;
import ch.paru.scrumTools.exchangeServer.utils.configuration.ExchangeServerConfiguration;

public class ConfigurationServiceImpl implements ConfigurationService {

	public ConfigurationServiceImpl(ExchangeService server) {
	}

	@Override
	public String getStringValue(ConfigurationKeys key) {
		return ExchangeServerConfiguration.getInstance().getStringValue(key);
	}

	@Override
	public String getStringValue(ConfigurationKeys prefix, String key) {
		return ExchangeServerConfiguration.getInstance().getStringValue(prefix, key);
	}

	@Override
	public Boolean getBooleanValue(ConfigurationKeys key) {
		return ExchangeServerConfiguration.getInstance().getBooleanValue(key);
	}
}
