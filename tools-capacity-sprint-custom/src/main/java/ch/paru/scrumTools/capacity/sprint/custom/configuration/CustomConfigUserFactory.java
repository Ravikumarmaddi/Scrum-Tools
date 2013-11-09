package ch.paru.scrumTools.capacity.sprint.custom.configuration;

import org.apache.commons.configuration.SubnodeConfiguration;

import ch.paru.scrumTools.common.capacity.configuration.ConfigUser;
import ch.paru.scrumTools.common.capacity.configuration.ConfigUserFactory;

public class CustomConfigUserFactory extends ConfigUserFactory {

	@Override
	public ConfigUser getNewInstance(String mailAddress) {
		return new CustomConfigUser(mailAddress);
	}

	@Override
	public void setValues(ConfigUser user, SubnodeConfiguration section) {
		super.setValues(user, section);

		CustomConfigUser customerUser = (CustomConfigUser) user;
		customerUser.setIsIntern(section.getBoolean(CustomConfigUser.ISINTERN));
	}
}
