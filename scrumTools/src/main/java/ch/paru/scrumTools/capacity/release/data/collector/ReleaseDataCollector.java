package ch.paru.scrumTools.capacity.release.data.collector;

import java.util.Calendar;
import java.util.List;

import ch.paru.scrumTools.capacity.release.configuration.ReleaseCapacityConfiguration;
import ch.paru.scrumTools.capacity.release.data.CalendarWeek;
import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.shared.data.collector.AbsenceDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.ConfigurationDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.TeamDataCollector;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class ReleaseDataCollector {

	private CalendarService calendarService;
	private ReleaseCapacityConfiguration config;
	private TeamDataCollector teamDataCollector;
	private ConfigurationDataCollector configDataCollector;
	private AbsenceDataCollector absenceDataCollector;

	public ReleaseDataCollector(CalendarService calendarService, ReleaseCapacityConfiguration config,
			TeamDataCollector teamDataCollector, ConfigurationDataCollector configDataCollector,
			AbsenceDataCollector absenceDataCollector) {
		this.calendarService = calendarService;
		this.config = config;
		this.teamDataCollector = teamDataCollector;
		this.configDataCollector = configDataCollector;
		this.absenceDataCollector = absenceDataCollector;
	}

	public void collectData(ReleaseData data) {
		ServerDay startDay = data.getStartDay();
		ServerDay endDay = data.getEndDay();

		List<String> teams = config.getTeams();
		teamDataCollector.loadTeams(data, teams);
		configDataCollector.loadConfiguration(data);
		absenceDataCollector.loadAbsences(data, startDay, endDay);

		loadWorkingDays(data, startDay, endDay);
		loadCalendarWeeks(data, startDay, endDay);
		loadDaysPerWeek(data, startDay, endDay);
	}

	private void loadDaysPerWeek(ReleaseData data, ServerDay startDay, ServerDay endDay) {
		List<TeamMember> teamMembers = data.getAllTeamMembers();
		for (TeamMember teamMember : teamMembers) {
			loadDaysPerWeekForMember(data, startDay, endDay, teamMember);
		}
	}

	private void loadDaysPerWeekForMember(ReleaseData data, ServerDay startDay, ServerDay endDay, TeamMember member) {
		ServerDay pointer = startDay;
		while (ServerDayUtil.compare(pointer, endDay) <= 0) {
			boolean isWorkingDay = calendarService.isWorkingDay(pointer);

			if (isWorkingDay && member.isAvailable(pointer)) {
				data.addTeamMemberWorkingDay(getCalendarWeek(pointer), member);
			}

			pointer = ServerDayUtil.addDays(pointer, 1);
		}
	}

	private void loadCalendarWeeks(ReleaseData data, ServerDay startDay, ServerDay endDay) {
		ServerDay pointer = startDay;
		while (ServerDayUtil.compare(pointer, endDay) <= 0) {
			data.addCalendarWeek(getCalendarWeek(pointer));
			pointer = ServerDayUtil.addDays(pointer, 1);
		}
	}

	private CalendarWeek getCalendarWeek(ServerDay day) {
		return new CalendarWeek(day.getCalendarWeek(), day.getCalendar().get(Calendar.YEAR));
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
