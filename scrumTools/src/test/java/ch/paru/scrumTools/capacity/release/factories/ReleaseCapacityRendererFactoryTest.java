package ch.paru.scrumTools.capacity.release.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.release.renderer.ReleaseCapacityRenderer;

public class ReleaseCapacityRendererFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		ReleaseCapacityRendererFactory factory = new ReleaseCapacityRendererFactory();
		ReleaseCapacityRenderer result = factory.createRenderer();

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
