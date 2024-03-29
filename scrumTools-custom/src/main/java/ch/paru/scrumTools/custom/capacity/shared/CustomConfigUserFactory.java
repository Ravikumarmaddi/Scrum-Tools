package ch.paru.scrumTools.custom.capacity.shared;

import org.apache.commons.configuration.SubnodeConfiguration;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.factories.ConfigUserFactory;

public class CustomConfigUserFactory extends ConfigUserFactory {

	@Override
	public void setValues(ConfigUser user, SubnodeConfiguration section) {
		super.setValues(user, section);

		CustomConfigUser customerUser = (CustomConfigUser) user;
		customerUser.setIsIntern(section.getBoolean(CustomConfigUser.ISINTERN));
	}
}
