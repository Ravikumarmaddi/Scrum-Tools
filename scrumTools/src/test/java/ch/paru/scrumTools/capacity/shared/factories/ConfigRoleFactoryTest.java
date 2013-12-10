package ch.paru.scrumTools.capacity.shared.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigRole;

public class ConfigRoleFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		ConfigRoleFactory factory = new ConfigRoleFactory();
		ConfigRole result = factory.createConfigRole("NAME");

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
