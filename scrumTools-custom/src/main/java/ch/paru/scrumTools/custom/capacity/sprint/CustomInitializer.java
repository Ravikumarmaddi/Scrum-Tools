package ch.paru.scrumTools.custom.capacity.sprint;

import ch.paru.scrumTools.capacity.sprint.SprintCapacityApplicationInitializer;

public class CustomInitializer extends SprintCapacityApplicationInitializer {

	@Override
	protected void initApplication(String configFileName) {
		CustomSprintCapacityConfiguration.init(configFileName);
	}
}
