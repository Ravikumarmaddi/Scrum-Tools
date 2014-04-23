package ch.paru.scrumTools.backendServer.utils;

import ch.paru.scrumTools.backendServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.backendServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;
import ch.paru.scrumTools.backendServer.services.calendar.ServerTime;
import ch.paru.scrumTools.backendServer.services.contact.ServerContact;

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
