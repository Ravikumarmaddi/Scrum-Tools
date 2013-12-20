package ch.paru.scrumTools.capacity.sprint.init;

import ch.paru.scrumTools.capacity.shared.init.CapacityApplicationInitializer;
import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.common.reflection.customs.Customizable;

public class SprintCapacityApplicationInitializer extends CapacityApplicationInitializer implements Customizable {

	@Override
	protected void initApplication(String configFileName) {
		SprintCapacityConfiguration.init(configFileName);
	}

}
