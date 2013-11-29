package ch.paru.scrumTools.exchangeServer.utils;

import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerTime;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;

public class ServerAppointmentUtil {

	private ServerAppointmentUtil() {
	}

	public static ServerAppointment createAllDayAbsenceAppointment(String title, ServerContact creator, ServerDay day) {
		return createAllDayAppointment(title, creator, day, CalendarCategories.ABSENCES);
	}

	public static ServerAppointment createAllDayAppointment(String title, ServerContact creator, ServerDay day,
			CalendarCategories category) {
		return new ServerAppointment(title, creator, day, ServerAppointment.ALL_DAY_EVENT, new ServerTime(0, 0),
				category);
	}
}
