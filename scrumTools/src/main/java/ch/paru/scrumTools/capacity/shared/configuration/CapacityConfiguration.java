package ch.paru.scrumTools.capacity.shared.configuration;

import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;

import ch.paru.scrumTools.common.exception.ToolException;

public class CapacityConfiguration {

	private static final String COMPANY_DOMAIN = "companyDomain";

	private static final String PREFIX_USER = "USER-";
	private static final String PREFIX_ROLE = "ROLE-";

	private static CapacityConfiguration instance;

	private HierarchicalINIConfiguration config;

	public static void init(String fileName) {
		instance = new CapacityConfiguration(fileName);
	}

	protected CapacityConfiguration(String fileName) {
		try {
			config = new HierarchicalINIConfiguration();
			config.load(fileName);
		}
		catch (Exception e) {
			throw new ToolException("config init failed", e);
		}
	}

	public static CapacityConfiguration getInstance() {
		if (instance == null) {
			throw new ToolException("config instance has not been intialized", null);
		}
		return instance;
	}

	private String getRootString(String key) {
		String value = config.getString(key);
		if (value == null) {
			throw new ToolException("'" + key + "' not found in the configuration", null);
		}
		return value;
	}

	private String getConfigUserSectionName(String mailAddress, String domain) {
		int usernameLength = mailAddress.length() - domain.length();
		String result = mailAddress.substring(0, usernameLength);
		result = result.replace(".", "_");
		return PREFIX_USER + result;
	}

	private String getConfigRoleSectionName(String name) {
		return PREFIX_ROLE + name;
	}

	public ConfigUser getUser(String mailAddress) {
		String sectionName = getConfigUserSectionName(mailAddress, getRootString(COMPANY_DOMAIN));
		SubnodeConfiguration section = config.getSection(sectionName);

		if (section.isEmpty()) {
			throw new ToolException("for '" + mailAddress + "' is no config user available", null);
		}

		ConfigUserFactory userFactory = getUserFactory();
		ConfigUser user = userFactory.getNewInstance(mailAddress);
		userFactory.setValues(user, section);
		return user;
	}

	public ConfigRole getRole(String roleName) {
		String sectionName = getConfigRoleSectionName(roleName);
		SubnodeConfiguration section = config.getSection(sectionName);

		if (section.isEmpty()) {
			throw new ToolException("for '" + roleName + "' is no config role available", null);
		}

		ConfigRole role = new ConfigRole();
		role.setName(section.getString(ConfigRole.NAME));
		role.setCapacity(section.getDouble(ConfigRole.CAPACITY, 1.0));
		String CapacityTypeName = section.getString(ConfigRole.CAPACITY_TYPE, CapacityType.FACTOR.name());
		CapacityType type = CapacityType.valueOf(CapacityTypeName);
		role.setCapacityType(type);
		return role;
	}

	protected ConfigUserFactory getUserFactory() {
		return new ConfigUserFactory();
	}
}
