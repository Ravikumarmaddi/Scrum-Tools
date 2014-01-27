package ch.paru.scrumTools.capacity.shared.configuration;

import org.apache.commons.configuration.SubnodeConfiguration;

import ch.paru.scrumTools.capacity.shared.factories.ConfigRoleFactory;
import ch.paru.scrumTools.capacity.shared.factories.ConfigUserFactory;
import ch.paru.scrumTools.common.configuration.ToolConfiguration;
import ch.paru.scrumTools.common.exception.ToolException;

public class CapacityConfiguration extends ToolConfiguration {

	@SuppressWarnings("unused")
	private static final String SECTION_NAME = "CAPACITY-CONFIG";

	private SectionHeaders sectionHeaders;

	private static CapacityConfiguration instance;

	public static void init(String fileName) {
		new CapacityConfiguration(fileName);
	}

	protected CapacityConfiguration(String fileName) {
		initConfig(fileName);
		instance = this;
		sectionHeaders = new SectionHeaders();
	}

	public static CapacityConfiguration getInstance() {
		if (instance == null) {
			throw new ToolException("config instance has not been intialized", null);
		}
		return instance;
	}

	public ConfigUser getUser(String mailAddress) {
		String sectionName = sectionHeaders.getUserSectionName(mailAddress);
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
		String sectionName = sectionHeaders.getRoleSectionName(roleName);
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
