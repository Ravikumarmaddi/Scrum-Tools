package ch.paru.scrumTools.capacity.shared.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.data.Team;

public class TeamFactoryTest {
	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		TeamFactory factory = new TeamFactory();
		Team result = factory.createTeam("NAME");

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
