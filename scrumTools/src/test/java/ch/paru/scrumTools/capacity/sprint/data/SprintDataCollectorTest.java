package ch.paru.scrumTools.capacity.sprint.data;

import static org.easymock.EasyMock.expect;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.data.collector.AbsenceDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.ConfigurationDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.TeamDataCollector;
import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.utils.ServerAppointmentUtil;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

import com.google.common.collect.Lists;

public class SprintDataCollectorTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final TeamDataCollector TEAM_COLLECTOR_MOCK = MOCKS.createMock("TEAM_COLLECTOR_MOCK",
			TeamDataCollector.class);
	private static final ConfigurationDataCollector CONFIG_COLLECTOR_MOCK = MOCKS.createMock("CONFIG_COLLECTOR_MOCK",
			ConfigurationDataCollector.class);
	private static final AbsenceDataCollector ABSENCE_COLLECTOR_MOCK = MOCKS.createMock("ABSENCE_COLLECTOR_MOCK",
			AbsenceDataCollector.class);
	private static final CalendarService CALENDAR_SERVICE_MOCK = MOCKS.createMock("CALENDAR_SERVICE_MOCK",
			CalendarService.class);
	private static final SprintCapacityConfiguration CONFIG_MOCK = MOCKS.createMock("CONFIG_MOCK",
			SprintCapacityConfiguration.class);
	private static final SprintData DATA_MOCK = MOCKS.createMock("DATA_MOCK", SprintData.class);

	@Test
	public void testCollectData() {
		String team = "Team";
		ServerDay startDay = ServerDayUtil.createDayFromNumbers(1, 1, 2013);
		ServerDay endDay = ServerDayUtil.createDayFromNumbers(3, 1, 2013);
		//
		ServerDay day1 = ServerDayUtil.addDays(startDay, 0);
		ServerDay day2 = ServerDayUtil.addDays(startDay, 1);
		ServerDay day3 = ServerDayUtil.addDays(startDay, 2);

		ServerAppointment sprintStart = ServerAppointmentUtil.createAllDayAppointment("Sprint Start",
				new ServerContact("admin@sprintTools.com"), day1, CalendarCategories.SPRINT_START);

		MOCKS.resetAll();
		expect(CONFIG_MOCK.getTeams()).andReturn(Lists.newArrayList(team));
		TEAM_COLLECTOR_MOCK.loadTeams(DATA_MOCK, Lists.newArrayList(team));
		CONFIG_COLLECTOR_MOCK.loadConfiguration(DATA_MOCK);
		ABSENCE_COLLECTOR_MOCK.loadAbsences(DATA_MOCK, startDay, endDay);
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day1)).andReturn(Boolean.TRUE);
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day2)).andReturn(Boolean.TRUE);
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day3)).andReturn(Boolean.FALSE);
		DATA_MOCK.setDayCategory(day1, SprintDayType.DAILY_BUSINESS);
		DATA_MOCK.setDayCategory(day2, SprintDayType.DAILY_BUSINESS);
		DATA_MOCK.setDayCategory(day1, SprintDayType.SPRINT_START);
		expect(CALENDAR_SERVICE_MOCK.getSingleAppointmentOfCategory(day1, CalendarCategories.SPRINT_START)).andReturn(
				sprintStart);
		expect(CALENDAR_SERVICE_MOCK.getSingleAppointmentOfCategory(day3, CalendarCategories.SPRINT_END)).andReturn(
				null);

		MOCKS.replayAll();
		SprintDataCollector dataCollector = new SprintDataCollector(CALENDAR_SERVICE_MOCK, CONFIG_MOCK,
				TEAM_COLLECTOR_MOCK, CONFIG_COLLECTOR_MOCK, ABSENCE_COLLECTOR_MOCK);
		dataCollector.collectData(DATA_MOCK, startDay, endDay);

		MOCKS.verifyAll();
	}

}
