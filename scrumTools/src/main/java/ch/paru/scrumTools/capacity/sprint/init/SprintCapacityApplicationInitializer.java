package ch.paru.scrumTools.capacity.sprint.init;

import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.common.reflection.customs.Customizable;
import ch.paru.scrumTools.exchangeServer.manager.ServerInstance;

public class SprintCapacityApplicationInitializer implements Customizable {

	public final void init(String configFileName) {
		ServerInstance.init(configFileName);
		initApplication(configFileName);
	}

	protected void initApplication(String configFileName) {
		SprintCapacityConfiguration.init(configFileName);
	}
}
