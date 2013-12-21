package ch.paru.scrumTools.capacity.sprint.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.TeamSprintCalculation;

public class TeamSprintCalculationFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		TeamSprintCalculationFactory factory = new TeamSprintCalculationFactory();
		TeamSprintCalculation result = factory.createCalculator(new SprintData(null, null));

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
