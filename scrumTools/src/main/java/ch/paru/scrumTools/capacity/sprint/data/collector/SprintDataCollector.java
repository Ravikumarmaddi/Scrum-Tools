package ch.paru.scrumTools.capacity.sprint.data.collector;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.collector.AbsenceDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.ConfigurationDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.TeamDataCollector;
import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.SprintDayType;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class SprintDataCollector {

	private SprintCapacityConfiguration config;
	private TeamDataCollector teamDataCollector;
	private ConfigurationDataCollector configDataCollector;
	private AbsenceDataCollector absenceDataCollector;
	private CalendarService calendarService;

	public SprintDataCollector(CalendarService calendarService, SprintCapacityConfiguration config,
			TeamDataCollector teamDataCollector, ConfigurationDataCollector configDataCollector,
			AbsenceDataCollector absenceDataCollector) {
		this.calendarService = calendarService;
		this.config = config;
		this.teamDataCollector = teamDataCollector;
		this.configDataCollector = configDataCollector;
		this.absenceDataCollector = absenceDataCollector;
	}

	public void collectData(SprintData data, ServerDay startDay, ServerDay endDay) {
		List<String> teams = config.getTeams();
		teamDataCollector.loadTeams(data, teams);
		configDataCollector.loadConfiguration(data);
		absenceDataCollector.loadAbsences(data, startDay, endDay);

		loadDayCategories(data, startDay, endDay);
		validatePlanningDay(data, startDay);
		validateReviewDay(data, endDay);
	}

	private void loadDayCategories(SprintData data, ServerDay startDay, ServerDay endDay) {
		ServerDay day = startDay;
		while (ServerDayUtil.compare(day, endDay) <= 0) {
			if (calendarService.isWorkingDay(day)) {
				data.setDayCategory(day, SprintDayType.DAILY_BUSINESS);
			}

			day = ServerDayUtil.addDays(day, 1);
		}
	}

	private void validatePlanningDay(SprintData data, ServerDay startDay) {
		ServerAppointment start = calendarService.getSingleAppointmentOfCategory(startDay,
				CalendarCategories.SPRINT_START);
		if (start == null) {
			return;
		}

		data.setDayCategory(startDay, SprintDayType.SPRINT_START);
	}

	private void validateReviewDay(SprintData data, ServerDay endDay) {
		ServerAppointment end = calendarService.getSingleAppointmentOfCategory(endDay, CalendarCategories.SPRINT_END);
		if (end == null) {
			return;
		}

		data.setDayCategory(endDay, SprintDayType.SPRINT_END);
	}
}
