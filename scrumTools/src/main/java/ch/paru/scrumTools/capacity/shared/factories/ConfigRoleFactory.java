package ch.paru.scrumTools.capacity.shared.factories;

import org.apache.commons.configuration.SubnodeConfiguration;

import ch.paru.scrumTools.capacity.shared.configuration.CapacityType;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigRole;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class ConfigRoleFactory extends AbstractFactory {

	public final ConfigRole createConfigRole(String name) {
		Class<? extends ConfigRole> instanceClass = getClassToUse(ConfigRole.class);
		return getInstance(instanceClass, name);

	}

	public void setValues(ConfigRole role, SubnodeConfiguration section) {
		role.setCapacity(section.getDouble(ConfigRole.CAPACITY, 1.0));
		String CapacityTypeName = section.getString(ConfigRole.CAPACITY_TYPE, CapacityType.FACTOR.name());
		CapacityType type = CapacityType.valueOf(CapacityTypeName);
		role.setCapacityType(type);
	}
}
