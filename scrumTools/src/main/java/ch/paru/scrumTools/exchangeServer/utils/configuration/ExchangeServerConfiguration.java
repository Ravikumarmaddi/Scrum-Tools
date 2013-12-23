package ch.paru.scrumTools.exchangeServer.utils.configuration;

import ch.paru.scrumTools.common.configuration.ToolConfiguration;
import ch.paru.scrumTools.exchangeServer.services.configuration.ConfigurationKeys;
import ch.paru.scrumTools.exchangeServer.utils.exceptions.ServerException;

public class ExchangeServerConfiguration extends ToolConfiguration {

	private static final String SECTION_NAME = "EXCHANGESERVER-CONFIG";

	private static ExchangeServerConfiguration instance;

	public static void init(String fileName) {
		instance = new ExchangeServerConfiguration(fileName);
	}

	private ExchangeServerConfiguration(String fileName) {
		initConfig(fileName);
	}

	public static ExchangeServerConfiguration getInstance() {
		if (instance == null) {
			throw new ServerException("config instance has not been intialized", null);
		}
		return instance;
	}

	public String getStringValue(ConfigurationKeys key) {
		return getStringInSection(SECTION_NAME, key.getKey());
	}

	public String getStringValue(ConfigurationKeys prefix, String key) {
		return getStringInSection(SECTION_NAME, prefix.getKey() + key);
	}

	public Boolean getBooleanValue(ConfigurationKeys key) {
		return getBooleanInSection(SECTION_NAME, key.getKey());
	}

}
