package ch.paru.scrumTools.capacity.sprint.data;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.DataCollector;
import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.exchangeServer.manager.ServerFacade;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class SprintDataCollector extends DataCollector {

	private ServerFacade serverFacade;
	private SprintCapacityConfiguration config;

	public SprintDataCollector(ServerFacade serverFacade, SprintCapacityConfiguration config) {
		super(serverFacade);
		this.serverFacade = serverFacade;
		this.config = config;
	}

	public SprintData collectData(ServerDay startDay, ServerDay endDay) {
		SprintData data = new SprintData();

		List<String> teams = config.getTeams();
		loadTeams(data, teams);
		loadConfiguration(data, config);
		loadAbsences(data, startDay, endDay);

		loadDayCategories(data, startDay, endDay);
		validatePlanningDay(data, startDay);
		validateReviewDay(data, endDay);

		return data;
	}

	private void loadDayCategories(SprintData data, ServerDay startDay, ServerDay endDay) {
		CalendarService calendarService = serverFacade.getCalendarService();

		ServerDay day = startDay;
		while (ServerDayUtil.compare(day, endDay) <= 0) {
			if (calendarService.isWorkingDay(day)) {
				data.setDayCategory(day, SprintDayType.DAILY_BUSINESS);
			}

			day = ServerDayUtil.addDays(day, 1);
		}
	}

	private void validatePlanningDay(SprintData data, ServerDay startDay) {
		CalendarService calendarService = serverFacade.getCalendarService();

		ServerAppointment start = calendarService.getSingleAppointmentOfCategory(startDay,
				CalendarCategories.SPRINT_START);
		if (start == null) {
			return;
		}

		SprintDayType dayType = start.getCategories().contains(CalendarCategories.SPRINT_END) ? SprintDayType.PLANNING_HALFDAY
				: SprintDayType.PLANNING_FULLDAY;
		data.setDayCategory(startDay, dayType);
	}

	private void validateReviewDay(SprintData data, ServerDay endDay) {
		CalendarService calendarService = serverFacade.getCalendarService();

		ServerAppointment end = calendarService.getSingleAppointmentOfCategory(endDay, CalendarCategories.SPRINT_END);
		if (end == null) {
			return;
		}

		SprintDayType dayType = end.getCategories().contains(CalendarCategories.SPRINT_START) ? SprintDayType.RETRO_REVIEW_HALFDAY
				: SprintDayType.RETRO_REVIEW_FULLDAY;
		data.setDayCategory(endDay, dayType);
	}
}
