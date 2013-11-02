package ch.paru.scrumTools.exchangeServer.services.calendar;

import java.util.List;

public interface CalendarService {

	List<ServerAppointment> getAllAppointmentsOfCategory(ServerDay day, CalendarCategories category);

	ServerAppointment getSingleAppointmentOfCategory(ServerDay day, CalendarCategories category);

	boolean isWorkingDay(ServerDay day);
}
