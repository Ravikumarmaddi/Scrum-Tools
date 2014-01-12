package ch.paru.scrumTools.capacity.release.data.collector;

import java.util.List;

import ch.paru.scrumTools.capacity.release.configuration.ReleaseCapacityConfiguration;
import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.utils.CalendarWeekUtil;
import ch.paru.scrumTools.capacity.shared.data.collector.AbsenceDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.ConfigurationDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.TeamDataCollector;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.contact.ContactService;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;
import ch.paru.scrumTools.exchangeServer.utils.interceptors.command.LoadCacheCommand;
import ch.paru.scrumTools.exchangeServer.utils.interceptors.command.StoreCacheCommand;

public class ReleaseDataCollector {

	private CalendarService calendarService;
	private ReleaseCapacityConfiguration config;
	private TeamDataCollector teamDataCollector;
	private ConfigurationDataCollector configDataCollector;
	private AbsenceDataCollector absenceDataCollector;
	private ContactService contactService;

	public ReleaseDataCollector(CalendarService calendarService, ContactService contactService,
			ReleaseCapacityConfiguration config, TeamDataCollector teamDataCollector,
			ConfigurationDataCollector configDataCollector, AbsenceDataCollector absenceDataCollector) {
		this.calendarService = calendarService;
		this.contactService = contactService;
		this.config = config;
		this.teamDataCollector = teamDataCollector;
		this.configDataCollector = configDataCollector;
		this.absenceDataCollector = absenceDataCollector;
	}

	public void collectData(ReleaseData data) {
		ServerDay startDay = data.getStartDay();
		ServerDay endDay = data.getEndDay();

		loadCacheFromFile();

		List<String> teams = config.getTeams();
		teamDataCollector.loadTeams(data, teams);
		configDataCollector.loadConfiguration(data);
		absenceDataCollector.loadAbsences(data, startDay, endDay);

		loadWorkingDays(data, startDay, endDay);
		loadCalendarWeeks(data, startDay, endDay);

		saveCacheToFile();
	}

	private void loadCacheFromFile() {
		LoadCacheCommand cmd = new LoadCacheCommand();
		calendarService.runInterceptorCommand(cmd);
		contactService.runInterceptorCommand(cmd);
	}

	private void saveCacheToFile() {
		StoreCacheCommand cmd = new StoreCacheCommand();
		calendarService.runInterceptorCommand(cmd);
		contactService.runInterceptorCommand(cmd);
	}

	private void loadCalendarWeeks(ReleaseData data, ServerDay startDay, ServerDay endDay) {
		ServerDay pointer = startDay;
		while (ServerDayUtil.compare(pointer, endDay) <= 0) {
			data.addCalendarWeek(CalendarWeekUtil.createCalendarWeek(pointer));
			pointer = ServerDayUtil.addDays(pointer, 1);
		}
	}

	private void loadWorkingDays(ReleaseData data, ServerDay startDay, ServerDay endDay) {
		ServerDay pointer = startDay;
		while (ServerDayUtil.compare(pointer, endDay) <= 0) {
			if (calendarService.isWorkingDay(pointer)) {
				data.addWorkingDay(pointer);
			}
			pointer = ServerDayUtil.addDays(pointer, 1);
		}
	}

}
