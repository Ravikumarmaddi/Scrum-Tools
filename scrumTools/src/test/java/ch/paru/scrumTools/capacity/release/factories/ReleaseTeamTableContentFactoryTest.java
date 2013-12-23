package ch.paru.scrumTools.capacity.release.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.shared.renderer.teamTable.TeamTableContent;

public class ReleaseTeamTableContentFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		ReleaseTeamTableContentFactory factory = new ReleaseTeamTableContentFactory();
		TeamTableContent result = factory.createTable(new ReleaseData(null, null));

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
