package ch.paru.scrumTools.capacity.release.data.calculator;

import static org.easymock.EasyMock.expect;

import java.util.Map;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.release.data.CalendarWeek;
import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.data.WeekCapacity;
import ch.paru.scrumTools.capacity.release.utils.CalendarWeekUtil;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.data.Numbers;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.backendServer.services.calendar.CalendarService;
import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;
import ch.paru.scrumTools.backendServer.utils.ServerDayUtil;

import com.google.common.collect.Maps;

public class MemberReleaseCalculationTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final ReleaseData DATA_MOCK = MOCKS.createMock("DATA_MOCK", ReleaseData.class);
	private static final TeamMember MEMBER_MOCK = MOCKS.createMock("MEMBER_MOCK", TeamMember.class);
	private static final CalendarService CALENDAR_SERVICE_MOCK = MOCKS.createMock("CALENDAR_SERVICE_MOCK",
			CalendarService.class);
	private static final Numbers NUMBERS_MOCK = MOCKS.createMock("NUMBERS_MOCK", Numbers.class);
	private static final ConfigUser CONFIG_USER_MOCK = MOCKS.createMock("CONFIG_USER_MOCK", ConfigUser.class);

	@Test
	public void testCalculateReleaseCapacityForMember() {
		ServerDay day1 = ServerDayUtil.createDayFromNumbers(1, 3, 2013);
		ServerDay day2 = ServerDayUtil.createDayFromNumbers(1, 4, 2013);
		CalendarWeek week1 = CalendarWeekUtil.createCalendarWeek(day1);
		CalendarWeek week2 = CalendarWeekUtil.createCalendarWeek(day2);
		WeekCapacity capa1 = new WeekCapacity();
		capa1.addSingleDay();
		capa1.addSingleDay();
		WeekCapacity capa2 = new WeekCapacity();
		capa2.addSingleDay();
		capa2.addSingleDay();
		capa2.addSingleDay();
		capa2.addSingleDay();
		Map<CalendarWeek, WeekCapacity> capaMap = Maps.newHashMap();
		capaMap.put(week1, capa1);
		capaMap.put(week2, capa2);

		MOCKS.resetAll();
		expect(DATA_MOCK.getWeekCapacityForMember(MEMBER_MOCK)).andReturn(capaMap);
		expect(MEMBER_MOCK.getNumbers()).andReturn(NUMBERS_MOCK);
		expect(MEMBER_MOCK.getConfiguration()).andReturn(CONFIG_USER_MOCK);
		expect(CONFIG_USER_MOCK.getCapacity()).andReturn(0.5);
		NUMBERS_MOCK.setAvailability(6);
		NUMBERS_MOCK.setFinalCapacity(3);

		MOCKS.replayAll();
		MemberReleaseCalculation calculator = new MemberReleaseCalculation(DATA_MOCK, null);
		calculator.calculateReleaseCapacityForMember(MEMBER_MOCK);

		MOCKS.verifyAll();
	}

	@Test
	public void testCalculateWeekCapacityForMember() {
		ServerDay day1 = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		ServerDay day2 = ServerDayUtil.createDayFromNumbers(2, 2, 2013);
		ServerDay day3 = ServerDayUtil.createDayFromNumbers(3, 2, 2013);
		CalendarWeek week1 = CalendarWeekUtil.createCalendarWeek(day1);
		CalendarWeek week3 = CalendarWeekUtil.createCalendarWeek(day3);

		MOCKS.resetAll();
		expect(DATA_MOCK.getStartDay()).andReturn(day1);
		expect(DATA_MOCK.getEndDay()).andReturn(day3);
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day1)).andReturn(Boolean.TRUE);
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day2)).andReturn(Boolean.TRUE);
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day3)).andReturn(Boolean.TRUE);
		expect(MEMBER_MOCK.isAvailable(day1)).andReturn(Boolean.TRUE);
		expect(MEMBER_MOCK.isAvailable(day2)).andReturn(Boolean.FALSE);
		expect(MEMBER_MOCK.isAvailable(day3)).andReturn(Boolean.TRUE);
		DATA_MOCK.addTeamMemberWorkingDay(week1, MEMBER_MOCK);
		DATA_MOCK.addTeamMemberWorkingDay(week3, MEMBER_MOCK);

		MOCKS.replayAll();
		MemberReleaseCalculation calculator = new MemberReleaseCalculation(DATA_MOCK, CALENDAR_SERVICE_MOCK);
		calculator.calculateWeekCapacityForMember(MEMBER_MOCK);

		MOCKS.verifyAll();
	}

}
