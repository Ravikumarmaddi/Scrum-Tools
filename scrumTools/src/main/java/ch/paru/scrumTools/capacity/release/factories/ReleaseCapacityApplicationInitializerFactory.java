package ch.paru.scrumTools.capacity.release.factories;

import ch.paru.scrumTools.capacity.release.init.ReleaseCapacityApplicationInitializer;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class ReleaseCapacityApplicationInitializerFactory extends AbstractFactory {

	public ReleaseCapacityApplicationInitializer createInitializer() {
		Class<? extends ReleaseCapacityApplicationInitializer> instanceClass = getClassToUse(ReleaseCapacityApplicationInitializer.class);
		return getInstance(instanceClass);
	}
}
