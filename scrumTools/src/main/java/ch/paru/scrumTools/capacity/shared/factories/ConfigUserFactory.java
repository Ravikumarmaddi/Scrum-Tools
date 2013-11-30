package ch.paru.scrumTools.capacity.shared.factories;

import org.apache.commons.configuration.SubnodeConfiguration;

import ch.paru.scrumTools.capacity.shared.configuration.CapacityConfiguration;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class ConfigUserFactory extends AbstractFactory {

	public final ConfigUser createConfigUser(String mailAddress) {
		Class<? extends ConfigUser> instanceClass = getClassToUse(ConfigUser.class);
		return getInstance(instanceClass, mailAddress);
	}

	public void setValues(ConfigUser user, SubnodeConfiguration section) {
		CapacityConfiguration configuration = CapacityConfiguration.getInstance();

		user.setName(section.getString(ConfigUser.NAME));
		user.setRole(configuration.getRole(section.getString(ConfigUser.ROLE)));
		user.setCapacity(section.getDouble(ConfigUser.CAPACITY, 1.0));
		user.setComment(section.getString(ConfigUser.COMMENT));
	}
}
