package ch.paru.scrumTools.backendServer.services.configuration;

public interface ConfigurationService {

	String getStringValue(ConfigurationKeys key);

	String getStringValue(ConfigurationKeys prefix, String key);

	Boolean getBooleanValue(ConfigurationKeys key);
}
