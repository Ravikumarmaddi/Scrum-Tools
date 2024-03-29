package ch.paru.scrumTools.capacity.sprint.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.renderer.teamTable.TeamTableContent;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;

public class SprintTeamTableContentFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		SprintTeamTableContentFactory factory = new SprintTeamTableContentFactory();
		TeamTableContent result = factory.createTable(new SprintData(null, null));

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
