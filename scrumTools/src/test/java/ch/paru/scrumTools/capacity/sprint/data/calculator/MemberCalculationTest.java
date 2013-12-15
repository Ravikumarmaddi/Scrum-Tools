package ch.paru.scrumTools.capacity.sprint.data.calculator;

import static org.easymock.EasyMock.anyDouble;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigRole;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.data.Numbers;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.SprintDayType;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

import com.google.common.collect.Lists;

public class MemberCalculationTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final SprintData DATA_MOCK = MOCKS.createMock("DATA_MOCK", SprintData.class);
	private static final TeamMember MEMBER_MOCK = MOCKS.createMock("MEMBER_MOCK", TeamMember.class);
	private static final ConfigUser CONFIG_USER_MOCK = MOCKS.createMock("CONFIG_USER_MOCK", ConfigUser.class);
	private static final RoleDetailCapacityCalculator ROLE_CALCULATOR_MOCK = MOCKS.createMock("ROLE_CALCULATOR_MOCK",
			RoleDetailCapacityCalculator.class);
	private static final Numbers NUMBERS_MOCK = MOCKS.createMock("NUMBERS_MOCK", Numbers.class);

	@Test
	public void testCalculateMemberCapacity() {
		ServerDay day1 = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		ServerDay day2 = ServerDayUtil.createDayFromNumbers(2, 2, 2013);
		ServerDay day3 = ServerDayUtil.createDayFromNumbers(3, 2, 2013);
		ServerDay day4 = ServerDayUtil.createDayFromNumbers(4, 2, 2013);
		ConfigRole role = new ConfigRole("");
		Double memberFactor = new Double(1);

		MOCKS.resetAll();
		expect(DATA_MOCK.getAllWorkingDays()).andReturn(Lists.newArrayList(day1, day2, day3, day4));
		expect(DATA_MOCK.getDayType(day1)).andReturn(SprintDayType.SPRINT_START);
		expect(DATA_MOCK.getDayType(day2)).andReturn(SprintDayType.DAILY_BUSINESS);
		expect(DATA_MOCK.getDayType(day3)).andReturn(SprintDayType.DAILY_BUSINESS);
		expect(DATA_MOCK.getDayType(day4)).andReturn(SprintDayType.SPRINT_END);
		expect(MEMBER_MOCK.isAvailable(day1)).andReturn(Boolean.TRUE);
		expect(MEMBER_MOCK.isAvailable(day2)).andReturn(Boolean.TRUE);
		expect(MEMBER_MOCK.isAvailable(day3)).andReturn(Boolean.FALSE);
		expect(MEMBER_MOCK.isAvailable(day4)).andReturn(Boolean.TRUE);
		expect(MEMBER_MOCK.getConfiguration()).andReturn(CONFIG_USER_MOCK).times(3);
		expect(MEMBER_MOCK.getNumbers()).andReturn(NUMBERS_MOCK);
		expect(CONFIG_USER_MOCK.getCapacity()).andReturn(memberFactor).times(3);
		expect(CONFIG_USER_MOCK.getRole()).andReturn(role).times(3);
		expect(ROLE_CALCULATOR_MOCK.getReducedCapacity(anyObject(ConfigRole.class), anyDouble(), anyDouble()))
				.andReturn(100d).times(3);
		NUMBERS_MOCK.setAvailability(17.5);
		NUMBERS_MOCK.setRawCapacity(300);
		NUMBERS_MOCK.setFinalCapacity(225);

		MOCKS.replayAll();
		MemberCalculation calculator = new MemberCalculation(DATA_MOCK, new ConstantHourManager(), ROLE_CALCULATOR_MOCK);
		calculator.calculateCapacityForMember(MEMBER_MOCK);

		MOCKS.verifyAll();
	}
}
