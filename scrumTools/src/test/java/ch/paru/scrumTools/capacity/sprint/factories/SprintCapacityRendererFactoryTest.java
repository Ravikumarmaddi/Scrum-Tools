package ch.paru.scrumTools.capacity.sprint.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.sprint.renderer.SprintCapacityRenderer;

public class SprintCapacityRendererFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		SprintCapacityRendererFactory factory = new SprintCapacityRendererFactory();
		SprintCapacityRenderer result = factory.createRenderer();

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
