package ch.paru.scrumTools.capacity.sprint.data.calculator;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;

public class ConstantHourManagerTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final SprintCapacityConfiguration CONFIG_MOCK = MOCKS.createMock("CONFIG_MOCK",
			SprintCapacityConfiguration.class);

	@Test
	public void testStartValue() {
		double returnValue = 9;

		MOCKS.resetAll();
		expect(CONFIG_MOCK.getConstantHourValue(ConstantHourManager.SPRINT_START)).andReturn(returnValue);

		MOCKS.replayAll();
		ConstantHourManager manager = new ConstantHourManager(CONFIG_MOCK);
		double value = manager.getHoursForSprintStart();

		MOCKS.verifyAll();
		assertEquals(returnValue, value, 0);
	}

	@Test
	public void testEndValue() {
		double returnValue = 9;

		MOCKS.resetAll();
		expect(CONFIG_MOCK.getConstantHourValue(ConstantHourManager.SPRINT_FINISH)).andReturn(returnValue);

		MOCKS.replayAll();
		ConstantHourManager manager = new ConstantHourManager(CONFIG_MOCK);
		double value = manager.getHoursForSprintEnd();

		MOCKS.verifyAll();
		assertEquals(returnValue, value, 0);
	}

	@Test
	public void testDayHours() {
		double returnValue = 9;

		MOCKS.resetAll();
		expect(CONFIG_MOCK.getConstantHourValue(ConstantHourManager.HOURS_PER_DAY)).andReturn(returnValue);

		MOCKS.replayAll();
		ConstantHourManager manager = new ConstantHourManager(CONFIG_MOCK);
		double value = manager.getHoursPerDay();

		MOCKS.verifyAll();
		assertEquals(returnValue, value, 0);
	}

	@Test
	public void testWorkingHours() {
		double returnValue = 9;

		MOCKS.resetAll();
		expect(CONFIG_MOCK.getConstantHourValue(ConstantHourManager.WORKINGHOURS_PER_DAY)).andReturn(returnValue);

		MOCKS.replayAll();
		ConstantHourManager manager = new ConstantHourManager(CONFIG_MOCK);
		double value = manager.getWorkingHoursPerDay();

		MOCKS.verifyAll();
		assertEquals(returnValue, value, 0);
	}

}
