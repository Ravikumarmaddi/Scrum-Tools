package ch.paru.scrumTools.capacity.shared.init;

import ch.paru.scrumTools.backendServer.manager.ServerInstance;

public abstract class CapacityApplicationInitializer {

	public final void init(String configFileName) {
		ServerInstance.init(configFileName);
		initApplication(configFileName);
	}

	protected abstract void initApplication(String configFileName);
}
