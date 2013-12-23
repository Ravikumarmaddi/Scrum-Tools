package ch.paru.scrumTools.capacity.release.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.release.renderer.generator.ReleaseOutputGenerator;
import ch.paru.scrumTools.capacity.release.renderer.generator.XlsReleaseOutputGenerator;

public class ReleaseOutputGeneratorFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		ReleaseOutputGeneratorFactory factory = new ReleaseOutputGeneratorFactory();
		ReleaseOutputGenerator result = factory.createGenerator(XlsReleaseOutputGenerator.class);

		MOCKS.verifyAll();
		assertNotNull(result);
	}
}
