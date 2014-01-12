package ch.paru.scrumTools.exchangeServer.services.calendar;

import java.util.List;

import ch.paru.scrumTools.exchangeServer.utils.interceptors.InterceptedService;

public interface CalendarService extends InterceptedService {

	List<ServerAppointment> getAllAppointmentsOfCategory(ServerDay day, CalendarCategories category);

	ServerAppointment getSingleAppointmentOfCategory(ServerDay day, CalendarCategories category, boolean nullIfNotSingle);

	boolean isWorkingDay(ServerDay day);
}
