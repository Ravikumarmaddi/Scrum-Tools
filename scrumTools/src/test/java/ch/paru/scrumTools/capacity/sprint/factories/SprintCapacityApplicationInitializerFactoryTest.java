package ch.paru.scrumTools.capacity.sprint.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.sprint.init.SprintCapacityApplicationInitializer;

public class SprintCapacityApplicationInitializerFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		SprintCapacityApplicationInitializerFactory factory = new SprintCapacityApplicationInitializerFactory();
		SprintCapacityApplicationInitializer result = factory.createInitializer();

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
