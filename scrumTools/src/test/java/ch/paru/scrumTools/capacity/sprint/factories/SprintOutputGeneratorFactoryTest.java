package ch.paru.scrumTools.capacity.sprint.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.sprint.renderer.generator.SprintOutputGenerator;
import ch.paru.scrumTools.capacity.sprint.renderer.generator.XlsSprintOutputGenerator;

public class SprintOutputGeneratorFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		SprintOutputGeneratorFactory factory = new SprintOutputGeneratorFactory();
		SprintOutputGenerator result = factory.createGenerator(XlsSprintOutputGenerator.class);

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
