package ch.paru.scrumTools.capacity.release.data.collector;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.release.configuration.ReleaseCapacityConfiguration;
import ch.paru.scrumTools.capacity.release.data.CalendarWeek;
import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.shared.data.collector.AbsenceDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.ConfigurationDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.TeamDataCollector;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.contact.ContactService;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;
import ch.paru.scrumTools.exchangeServer.utils.interceptors.command.LoadCacheCommand;
import ch.paru.scrumTools.exchangeServer.utils.interceptors.command.StoreCacheCommand;

import com.google.common.collect.Lists;

public class ReleaseDataCollectorTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final CalendarService CALENDAR_SERVICE_MOCK = MOCKS.createMock("CALENDAR_SERVICE_MOCK",
			CalendarService.class);
	private static final ContactService CONTACT_SERVICE_MOCK = MOCKS.createMock("CONTACT_SERVICE_MOCK",
			ContactService.class);
	private static final ReleaseCapacityConfiguration CONFIG_MOCK = MOCKS.createMock("CONFIG_MOCK",
			ReleaseCapacityConfiguration.class);
	private static final TeamDataCollector TEAM_COLLECTOR_MOCK = MOCKS.createMock("TEAM_COLLECTOR_MOCK",
			TeamDataCollector.class);
	private static final ConfigurationDataCollector CONFIG_COLLECTOR_MOCK = MOCKS.createMock("CONFIG_COLLECTOR_MOCK",
			ConfigurationDataCollector.class);
	private static final AbsenceDataCollector ABSENCE_COLLECTOR_MOCK = MOCKS.createMock("ABSENCE_COLLECTOR_MOCK",
			AbsenceDataCollector.class);
	private static final ReleaseData DATA_MOCK = MOCKS.createMock("DATA_MOCK", ReleaseData.class);

	@Test
	public void testCollectData() {
		String team = "Team";
		ServerDay startDay = ServerDayUtil.createDayFromNumbers(1, 1, 2013);
		ServerDay endDay = ServerDayUtil.createDayFromNumbers(3, 1, 2013);
		ServerDay day1 = ServerDayUtil.addDays(startDay, 0);
		ServerDay day2 = ServerDayUtil.addDays(startDay, 1);
		ServerDay day3 = ServerDayUtil.addDays(startDay, 2);
		CalendarWeek week = new CalendarWeek(1, 2013);

		MOCKS.resetAll();
		expect(CONFIG_MOCK.getTeams()).andReturn(Lists.newArrayList(team));
		TEAM_COLLECTOR_MOCK.loadTeams(DATA_MOCK, Lists.newArrayList(team));
		CONFIG_COLLECTOR_MOCK.loadConfiguration(DATA_MOCK);
		ABSENCE_COLLECTOR_MOCK.loadAbsences(DATA_MOCK, startDay, endDay);
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day1)).andReturn(Boolean.TRUE).anyTimes();
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day2)).andReturn(Boolean.TRUE).anyTimes();
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day3)).andReturn(Boolean.FALSE).anyTimes();
		CALENDAR_SERVICE_MOCK.runInterceptorCommand(new LoadCacheCommand());
		CALENDAR_SERVICE_MOCK.runInterceptorCommand(new StoreCacheCommand());
		CONTACT_SERVICE_MOCK.runInterceptorCommand(new LoadCacheCommand());
		CONTACT_SERVICE_MOCK.runInterceptorCommand(new StoreCacheCommand());
		expect(DATA_MOCK.getStartDay()).andReturn(startDay);
		expect(DATA_MOCK.getEndDay()).andReturn(endDay);
		DATA_MOCK.addWorkingDay(day1);
		DATA_MOCK.addWorkingDay(day2);
		DATA_MOCK.addCalendarWeek(week);
		expectLastCall().times(3);

		MOCKS.replayAll();
		ReleaseDataCollector dataCollector = new ReleaseDataCollector(CALENDAR_SERVICE_MOCK, CONTACT_SERVICE_MOCK,
				CONFIG_MOCK, TEAM_COLLECTOR_MOCK, CONFIG_COLLECTOR_MOCK, ABSENCE_COLLECTOR_MOCK);
		dataCollector.collectData(DATA_MOCK);

		MOCKS.verifyAll();
	}

}
