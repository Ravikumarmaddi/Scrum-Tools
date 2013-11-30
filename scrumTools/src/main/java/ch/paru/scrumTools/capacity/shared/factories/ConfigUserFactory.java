package ch.paru.scrumTools.capacity.shared.factories;

import java.lang.reflect.Constructor;

import org.apache.commons.configuration.SubnodeConfiguration;

import ch.paru.scrumTools.capacity.shared.configuration.CapacityConfiguration;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.common.exception.ToolException;
import ch.paru.scrumTools.common.reflection.CustomFactory;
import ch.paru.scrumTools.common.reflection.ReflectionUtil;

@CustomFactory
public class ConfigUserFactory {

	public final ConfigUser createConfigUser(String mailAddress) {
		Class<? extends ConfigUser> clazz = ReflectionUtil.getCustomClass(ConfigUser.class);
		if (clazz == null) {
			clazz = ConfigUser.class;
		}

		try {
			Constructor<? extends ConfigUser> constructor = clazz.getConstructor(String.class);
			return constructor.newInstance(mailAddress);
		}
		catch (Exception e) {
			throw new ToolException("instanciation of class failed", e);
		}
	}

	public void setValues(ConfigUser user, SubnodeConfiguration section) {
		CapacityConfiguration configuration = CapacityConfiguration.getInstance();

		user.setName(section.getString(ConfigUser.NAME));
		user.setRole(configuration.getRole(section.getString(ConfigUser.ROLE)));
		user.setCapacity(section.getDouble(ConfigUser.CAPACITY, 1.0));
		user.setComment(section.getString(ConfigUser.COMMENT));
	}
}
