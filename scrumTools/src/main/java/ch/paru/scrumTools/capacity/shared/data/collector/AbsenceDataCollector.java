package ch.paru.scrumTools.capacity.shared.data.collector;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class AbsenceDataCollector {

	private CalendarService calendarService;

	public AbsenceDataCollector(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	public void loadAbsences(DataBox data, ServerDay startDay, ServerDay endDay) {
		ServerDay pointer = startDay;
		while (ServerDayUtil.compare(pointer, endDay) <= 0) {
			List<ServerAppointment> absences = calendarService.getAllAppointmentsOfCategory(pointer,
					CalendarCategories.ABSENCES);

			for (ServerAppointment appointment : absences) {
				data.addAbsenceForMember(appointment.getCreator(), pointer);
			}

			pointer = ServerDayUtil.addDays(pointer, 1);
		}
	}
}
