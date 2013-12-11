package ch.paru.scrumTools.capacity.sprint.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.renderer.TeamTableContent;

public class SprintTeamTableFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		SprintTeamTableFactory factory = new SprintTeamTableFactory();
		TeamTableContent result = factory.createTable();

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
