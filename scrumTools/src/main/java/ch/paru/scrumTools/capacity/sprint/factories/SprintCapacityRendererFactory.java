package ch.paru.scrumTools.capacity.sprint.factories;

import ch.paru.scrumTools.capacity.sprint.renderer.SprintCapacityRenderer;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class SprintCapacityRendererFactory extends AbstractFactory {

	public SprintCapacityRenderer createRenderer() {
		Class<? extends SprintCapacityRenderer> instanceClass = getClassToUse(SprintCapacityRenderer.class);
		return getInstance(instanceClass);
	}
}
