package ch.paru.scrumTools.common.capacity.configuration;

import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;

import ch.paru.scrumTools.common.capacity.ToolException;

public class CapacityConfiguration {

	private static final String COMPANY_DOMAIN = "companyDomain";

	private static final String PREFIX_USER = "USER-";
	private static final String PREFIX_ROLE = "ROLE-";

	private static CapacityConfiguration instance;

	private HierarchicalINIConfiguration config;

	public static void init(String fileName) {
		instance = new CapacityConfiguration(fileName);
	}

	private CapacityConfiguration(String fileName) {
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

	public String getRootString(String key) {
		return config.getString(key);
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

		ConfigUser user = new ConfigUser(mailAddress);
		user.setName(section.getString(ConfigUser.NAME));
		user.setRole(getRole(section.getString(ConfigUser.ROLE)));
		user.setCapacity(section.getDouble(ConfigUser.CAPACITY, 1.0));
		user.setComment(section.getString(ConfigUser.COMMENT));
		return user;
	}

	public ConfigRole getRole(String roleName) {
		String sectionName = getConfigRoleSectionName(roleName);
		SubnodeConfiguration section = config.getSection(sectionName);

		ConfigRole role = new ConfigRole();
		role.setName(section.getString(ConfigRole.NAME));
		role.setCapacity(section.getDouble(ConfigRole.CAPACITY, 1.0));
		String CapacityTypeName = section.getString(ConfigRole.CAPACITY_TYPE, CapacityType.FACTOR.name());
		CapacityType type = CapacityType.valueOf(CapacityTypeName);
		role.setCapacityType(type);
		return role;
	}
}
