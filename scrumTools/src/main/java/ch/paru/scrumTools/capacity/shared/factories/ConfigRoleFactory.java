package ch.paru.scrumTools.capacity.shared.factories;

import java.lang.reflect.Constructor;

import org.apache.commons.configuration.SubnodeConfiguration;

import ch.paru.scrumTools.capacity.shared.configuration.CapacityType;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigRole;
import ch.paru.scrumTools.common.exception.ToolException;
import ch.paru.scrumTools.common.reflection.CustomFactory;
import ch.paru.scrumTools.common.reflection.ReflectionUtil;

@CustomFactory
public class ConfigRoleFactory {

	public final ConfigRole createConfigRole(String name) {
		Class<? extends ConfigRole> clazz = ReflectionUtil.getCustomClass(ConfigRole.class);
		if (clazz == null) {
			clazz = ConfigRole.class;
		}

		try {
			Constructor<? extends ConfigRole> constructor = clazz.getConstructor(String.class);
			return constructor.newInstance(name);
		}
		catch (Exception e) {
			throw new ToolException("instanciation of class failed", e);
		}
	}

	public void setValues(ConfigRole role, SubnodeConfiguration section) {
		role.setCapacity(section.getDouble(ConfigRole.CAPACITY, 1.0));
		String CapacityTypeName = section.getString(ConfigRole.CAPACITY_TYPE, CapacityType.FACTOR.name());
		CapacityType type = CapacityType.valueOf(CapacityTypeName);
		role.setCapacityType(type);
	}
}
