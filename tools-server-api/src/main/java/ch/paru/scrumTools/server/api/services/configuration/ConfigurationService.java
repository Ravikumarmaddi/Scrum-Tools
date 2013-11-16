package ch.paru.scrumTools.server.api.services.configuration;

public interface ConfigurationService {

	String getStringValue(ConfigurationKeys key);

	String getStringValue(ConfigurationKeys prefix, String key);

	Boolean getBooleanValue(ConfigurationKeys key);

	void init(String configFileName);

}
