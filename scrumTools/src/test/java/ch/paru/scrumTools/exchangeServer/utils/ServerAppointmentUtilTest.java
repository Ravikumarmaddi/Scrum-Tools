package ch.paru.scrumTools.exchangeServer.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;

public class ServerAppointmentUtilTest {

	@Test
	public void testAllDayAppointment() {
		String title = "TITLE";
		ServerContact creator = new ServerContact("bla@bla.net");
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		CalendarCategories category = CalendarCategories.PUBLIC_HOLIDAY;

		ServerAppointment appointment = ServerAppointmentUtil.createAllDayAppointment(title, creator, day, category);

		assertEquals(title, appointment.getSubject());
		assertEquals(creator, appointment.getCreator());
		assertEquals(day, appointment.getDay());
		assertEquals(ServerAppointment.ALL_DAY_EVENT, appointment.getDurationInMin());
		assertEquals(1, appointment.getCategories().size());
		assertTrue(appointment.getCategories().contains(category));
	}

	@Test
	public void testAllDayAbsenceAppointment() {
		String title = "TITLE";
		ServerContact creator = new ServerContact("bla@bla.net");
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		CalendarCategories category = CalendarCategories.ABSENCES;

		ServerAppointment appointment = ServerAppointmentUtil.createAllDayAbsenceAppointment(title, creator, day);

		assertEquals(title, appointment.getSubject());
		assertEquals(creator, appointment.getCreator());
		assertEquals(day, appointment.getDay());
		assertEquals(ServerAppointment.ALL_DAY_EVENT, appointment.getDurationInMin());
		assertEquals(1, appointment.getCategories().size());
		assertTrue(appointment.getCategories().contains(category));
	}
}
