package ch.paru.scrumTools.capacity.release.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.release.init.ReleaseCapacityApplicationInitializer;

public class ReleaseCapacityApplicationInitializerFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		ReleaseCapacityApplicationInitializerFactory factory = new ReleaseCapacityApplicationInitializerFactory();
		ReleaseCapacityApplicationInitializer result = factory.createInitializer();

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
