package ch.paru.scrumTools.exchangeServer.services.configuration;

import ch.paru.scrumTools.exchangeServer.util.configuration.ExchangeServerConfiguration;
import ch.paru.scrumTools.server.api.configuration.ConfigurationKeys;
import ch.paru.scrumTools.server.api.configuration.ConfigurationService;

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
