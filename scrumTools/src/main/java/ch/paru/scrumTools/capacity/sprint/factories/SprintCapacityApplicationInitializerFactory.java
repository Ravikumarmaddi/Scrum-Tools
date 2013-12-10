package ch.paru.scrumTools.capacity.sprint.factories;

import ch.paru.scrumTools.capacity.sprint.init.SprintCapacityApplicationInitializer;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class SprintCapacityApplicationInitializerFactory extends AbstractFactory {

	public SprintCapacityApplicationInitializer createInitializer() {
		Class<? extends SprintCapacityApplicationInitializer> instanceClass = getClassToUse(SprintCapacityApplicationInitializer.class);
		return getInstance(instanceClass);
	}
}
