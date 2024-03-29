package ch.paru.scrumTools.common.configuration;

import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;

import ch.paru.scrumTools.common.exception.ToolException;

public abstract class ToolConfiguration {

	private HierarchicalINIConfiguration config;

	protected String getStringInSection(String sectionName, String key) {
		SubnodeConfiguration section = getConfig().getSection(sectionName);
		String value = section.getString(key);
		if (value == null) {
			throw new ToolException("'" + key + "' not found in the section '" + sectionName + "'", null);
		}
		return value;
	}

	protected String getStringInSection(String sectionName, String key, String defaultValue) {
		try {
			return getStringInSection(sectionName, key);
		}
		catch (Exception e) {
			return defaultValue;
		}
	}

	protected Boolean getBooleanInSection(String sectionName, String key) {
		SubnodeConfiguration section = getConfig().getSection(sectionName);
		Boolean value = section.getBoolean(key);
		if (value == null) {
			throw new ToolException("'" + key + "' not found in the section '" + sectionName + "'", null);
		}
		return value;
	}

	protected Boolean getBooleanInSection(String sectionName, String key, boolean defaultValue) {
		try {
			return getBooleanInSection(sectionName, key);
		}
		catch (Exception e) {
			return defaultValue;
		}
	}

	protected Double getDoubleInSection(String sectionName, String key) {
		SubnodeConfiguration section = getConfig().getSection(sectionName);
		Double value = section.getDouble(key);
		if (value == null) {
			throw new ToolException("'" + key + "' not found in the section '" + sectionName + "'", null);
		}
		return value;
	}

	protected Double getDoubleInSection(String sectionName, String key, double defaultValue) {
		try {
			return getDoubleInSection(sectionName, key);
		}
		catch (Exception e) {
			return defaultValue;
		}
	}

	protected final void initConfig(String fileName) {
		try {
			config = new HierarchicalINIConfiguration();
			config.load(fileName);
		}
		catch (Exception e) {
			throw new ToolException("config init failed", e);
		}
	}

	protected final HierarchicalINIConfiguration getConfig() {
		return config;
	}
}
