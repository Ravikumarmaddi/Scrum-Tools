package ch.paru.scrumTools.capacity.shared.configuration;

import org.apache.commons.configuration.SubnodeConfiguration;

import ch.paru.scrumTools.capacity.shared.factories.ConfigRoleFactory;
import ch.paru.scrumTools.capacity.shared.factories.ConfigUserFactory;
import ch.paru.scrumTools.common.configuration.ToolConfiguration;
import ch.paru.scrumTools.common.exception.ToolException;

public class CapacityConfiguration extends ToolConfiguration {

	private static final String SECTION_NAME = "CAPACITY-CONFIG";
	private static final String COMPANY_DOMAIN = "companyDomain";

	private static final String PREFIX_USER = "USER-";
	private static final String PREFIX_ROLE = "ROLE-";

	private static CapacityConfiguration instance;

	public static void init(String fileName) {
		new CapacityConfiguration(fileName);
	}

	protected CapacityConfiguration(String fileName) {
		initConfig(fileName);
		instance = this;
	}

	public static CapacityConfiguration getInstance() {
		if (instance == null) {
			throw new ToolException("config instance has not been intialized", null);
		}
		return instance;
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
		String sectionName = getConfigUserSectionName(mailAddress, getStringInSection(SECTION_NAME, COMPANY_DOMAIN));
		SubnodeConfiguration section = getConfig().getSection(sectionName);

		if (section.isEmpty()) {
			throw new ToolException("for '" + mailAddress + "' is no config user available", null);
		}

		ConfigUserFactory userFactory = getUserFactory();
		ConfigUser user = userFactory.createConfigUser(mailAddress);
		userFactory.setValues(user, section);
		return user;
	}

	public ConfigRole getRole(String roleName) {
		String sectionName = getConfigRoleSectionName(roleName);
		SubnodeConfiguration section = getConfig().getSection(sectionName);

		if (section.isEmpty()) {
			throw new ToolException("for '" + roleName + "' is no config role available", null);
		}

		ConfigRoleFactory roleFactory = getRoleFactory();
		ConfigRole role = roleFactory.createConfigRole(section.getString(ConfigRole.NAME));
		roleFactory.setValues(role, section);
		return role;
	}

	protected ConfigUserFactory getUserFactory() {
		return new ConfigUserFactory();
	}

	protected ConfigRoleFactory getRoleFactory() {
		return new ConfigRoleFactory();
	}
}
