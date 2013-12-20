package ch.paru.scrumTools.capacity.release.init;

import ch.paru.scrumTools.capacity.release.configuration.ReleaseCapacityConfiguration;
import ch.paru.scrumTools.capacity.shared.init.CapacityApplicationInitializer;
import ch.paru.scrumTools.common.reflection.customs.Customizable;

public class ReleaseCapacityApplicationInitializer extends CapacityApplicationInitializer implements Customizable {

	@Override
	protected void initApplication(String configFileName) {
		ReleaseCapacityConfiguration.init(configFileName);
	}
}
