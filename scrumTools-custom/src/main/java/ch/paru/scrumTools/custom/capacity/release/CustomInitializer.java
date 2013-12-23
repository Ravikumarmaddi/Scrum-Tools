package ch.paru.scrumTools.custom.capacity.release;

import ch.paru.scrumTools.capacity.release.init.ReleaseCapacityApplicationInitializer;
import ch.paru.scrumTools.common.reflection.customs.Custom;

@Custom(type = ReleaseCapacityApplicationInitializer.class)
public class CustomInitializer extends ReleaseCapacityApplicationInitializer {

	@Override
	protected void initApplication(String configFileName) {
		CustomReleaseCapacityConfiguration.init(configFileName);
	}
}
