package ch.paru.scrumTools.capacity.release.factories;

import ch.paru.scrumTools.capacity.release.renderer.generator.ReleaseOutputGenerator;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class ReleaseOutputGeneratorFactory extends AbstractFactory {

	public ReleaseOutputGenerator createGenerator(Class<? extends ReleaseOutputGenerator> defaultClass) {
		Class<? extends ReleaseOutputGenerator> instanceClass = getClassToUseWithDefault(ReleaseOutputGenerator.class,
				defaultClass);
		return getInstance(instanceClass);
	}
}
