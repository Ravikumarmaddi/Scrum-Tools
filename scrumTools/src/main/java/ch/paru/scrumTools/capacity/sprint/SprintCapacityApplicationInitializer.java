package ch.paru.scrumTools.capacity.sprint;

import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.exchangeServer.manager.ServerInstance;

public class SprintCapacityApplicationInitializer {

	public final void init(String configFileName) {
		ServerInstance.init(configFileName);
		initApplication(configFileName);
	}

	protected void initApplication(String configFileName) {
		SprintCapacityConfiguration.init(configFileName);
	}
}
