package ch.paru.scrumTools.common.capacity.configuration;

import org.apache.commons.configuration.SubnodeConfiguration;

public class ConfigUserFactory {

	public ConfigUser getNewInstance(String mailAddress) {
		return new ConfigUser(mailAddress);
	}

	public void setValues(ConfigUser user, SubnodeConfiguration section) {
		CapacityConfiguration configuration = CapacityConfiguration.getInstance();

		user.setName(section.getString(ConfigUser.NAME));
		user.setRole(configuration.getRole(section.getString(ConfigUser.ROLE)));
		user.setCapacity(section.getDouble(ConfigUser.CAPACITY, 1.0));
		user.setComment(section.getString(ConfigUser.COMMENT));
	}
}
