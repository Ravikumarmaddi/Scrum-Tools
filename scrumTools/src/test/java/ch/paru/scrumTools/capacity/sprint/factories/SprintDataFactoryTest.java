package ch.paru.scrumTools.capacity.sprint.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.factories.TeamFactory;
import ch.paru.scrumTools.capacity.shared.factories.TeamMemberFactory;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;

public class SprintDataFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		SprintDataFactory factory = new SprintDataFactory();
		SprintData result = factory.createData(new TeamFactory(), new TeamMemberFactory());

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
