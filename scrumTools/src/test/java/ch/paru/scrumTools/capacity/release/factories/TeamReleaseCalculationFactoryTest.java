package ch.paru.scrumTools.capacity.release.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.data.calculator.TeamReleaseCalculation;

public class TeamReleaseCalculationFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		TeamReleaseCalculationFactory factory = new TeamReleaseCalculationFactory();
		TeamReleaseCalculation result = factory.createCalculator(new ReleaseData(null, null));

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
