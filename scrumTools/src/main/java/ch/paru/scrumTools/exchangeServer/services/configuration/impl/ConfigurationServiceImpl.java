package ch.paru.scrumTools.exchangeServer.services.configuration.impl;

import ch.paru.scrumTools.exchangeServer.services.configuration.ConfigurationKeys;
import ch.paru.scrumTools.exchangeServer.services.configuration.ConfigurationService;
import ch.paru.scrumTools.exchangeServer.utils.configuration.ExchangeServerConfiguration;

public class ConfigurationServiceImpl implements ConfigurationService {

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

	@Override
	public void init(String configFileName) {
		ExchangeServerConfiguration.init(configFileName);
	}

}
