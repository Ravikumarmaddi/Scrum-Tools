package ch.paru.scrumTools.capacity.sprint.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.ConstantHourManager;
import ch.paru.scrumTools.capacity.sprint.data.calculator.MemberSprintCalculation;
import ch.paru.scrumTools.capacity.sprint.data.calculator.RoleDetailSprintCapacityCalculator;

public class MemberSprintCalculationFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		MemberSprintCalculationFactory factory = new MemberSprintCalculationFactory();
		MemberSprintCalculation result = factory.createCalculator(new SprintData(null, null), new ConstantHourManager(null),
				new RoleDetailSprintCapacityCalculator());

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
