package ch.paru.scrumTools.capacity.shared.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;

public class ConfigUserFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		ConfigUserFactory factory = new ConfigUserFactory();
		ConfigUser result = factory.createConfigUser("bla@bla.ch");

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
