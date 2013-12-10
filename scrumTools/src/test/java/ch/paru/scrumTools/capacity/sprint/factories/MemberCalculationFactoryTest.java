package ch.paru.scrumTools.capacity.sprint.factories;

import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.ConstantHourManager;
import ch.paru.scrumTools.capacity.sprint.data.calculator.MemberCalculation;
import ch.paru.scrumTools.capacity.sprint.data.calculator.RoleDetailCapacityCalculator;

public class MemberCalculationFactoryTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();

	@Test
	public void testInstance() {
		MOCKS.resetAll();

		MOCKS.replayAll();
		MemberCalculationFactory factory = new MemberCalculationFactory();
		MemberCalculation result = factory.createCalculator(new SprintData(null, null), new ConstantHourManager(),
				new RoleDetailCapacityCalculator());

		MOCKS.verifyAll();
		assertNotNull(result);
	}

}
