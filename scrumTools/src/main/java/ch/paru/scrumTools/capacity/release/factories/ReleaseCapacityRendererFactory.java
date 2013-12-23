package ch.paru.scrumTools.capacity.release.factories;

import ch.paru.scrumTools.capacity.release.renderer.ReleaseCapacityRenderer;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class ReleaseCapacityRendererFactory extends AbstractFactory {

	public ReleaseCapacityRenderer createRenderer() {
		Class<? extends ReleaseCapacityRenderer> instanceClass = getClassToUse(ReleaseCapacityRenderer.class);
		return getInstance(instanceClass);
	}
}
