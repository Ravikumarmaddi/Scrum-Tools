package ch.paru.scrumTools.exchangeServer.util.configuration;

import org.apache.commons.configuration.HierarchicalINIConfiguration;

import ch.paru.scrumTools.server.api.services.configuration.ConfigurationKeys;
import ch.paru.scrumTools.server.api.utils.exceptions.ServerException;

public class ExchangeServerConfiguration {

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
			throw new ServerException("config init failed", e);
		}
	}

	public static ExchangeServerConfiguration getInstance() {
		if (instance == null) {
			throw new ServerException("config instance has not been intialized", null);
		}
		return instance;
	}

	public String getStringValue(ConfigurationKeys key) {
		return getConfig().getString(key.getKey());
	}

	public String getStringValue(ConfigurationKeys prefix, String key) {
		return getConfig().getString(prefix.getKey() + key);
	}

	public Boolean getBooleanValue(ConfigurationKeys key) {
		return getConfig().getBoolean(key.getKey());
	}

	private HierarchicalINIConfiguration getConfig() {
		return config;
	}

}
