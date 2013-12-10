package ch.paru.scrumTools.custom.capacity.sprint;

import ch.paru.scrumTools.capacity.sprint.init.SprintCapacityApplicationInitializer;
import ch.paru.scrumTools.common.reflection.customs.Custom;

@Custom(type = SprintCapacityApplicationInitializer.class)
public class CustomInitializer extends SprintCapacityApplicationInitializer {

	@Override
	protected void initApplication(String configFileName) {
		CustomSprintCapacityConfiguration.init(configFileName);
	}
}
