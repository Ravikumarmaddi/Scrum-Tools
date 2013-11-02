package ch.paru.scrumTools.exchangeServer.services.calendar;

import java.util.List;

public interface CalendarService {

	List<EwsAppointment> getAllAppointmentsOfCategory(EwsDay day, CalendarCategories category);

	EwsAppointment getSingleAppointmentOfCategory(EwsDay day, CalendarCategories category);

	boolean isWorkingDay(EwsDay day);
}
