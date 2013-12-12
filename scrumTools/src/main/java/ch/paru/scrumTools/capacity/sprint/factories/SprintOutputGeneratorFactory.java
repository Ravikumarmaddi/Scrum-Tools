package ch.paru.scrumTools.capacity.sprint.factories;

import ch.paru.scrumTools.capacity.sprint.renderer.generator.SprintOutputGenerator;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class SprintOutputGeneratorFactory extends AbstractFactory {

	public SprintOutputGenerator createGenerator(Class<? extends SprintOutputGenerator> defaultClass) {
		Class<? extends SprintOutputGenerator> instanceClass = getClassToUseWithDefault(SprintOutputGenerator.class,
				defaultClass);
		return getInstance(instanceClass);
	}
}
