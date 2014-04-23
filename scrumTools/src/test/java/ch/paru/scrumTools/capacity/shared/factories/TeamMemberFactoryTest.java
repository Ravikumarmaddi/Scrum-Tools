package ch.paru.scrumTools.capacity.shared.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.backendServer.services.contact.ServerContact;

public class TeamMemberFactoryTest {
	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		TeamMemberFactory factory = new TeamMemberFactory();
		TeamMember result = factory.createTeamMember(new ServerContact("bla@bla.ch"));

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
