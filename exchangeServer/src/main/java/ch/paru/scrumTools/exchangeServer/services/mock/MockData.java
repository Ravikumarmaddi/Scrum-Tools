package ch.paru.scrumTools.exchangeServer.services.mock;

import java.util.List;

import com.google.common.collect.Lists;
import ch.paru.scrumTools.exchangeServer.EchangeServerException;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.EwsAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.EwsDay;
import ch.paru.scrumTools.exchangeServer.services.calendar.EwsTime;
import ch.paru.scrumTools.exchangeServer.services.contact.EwsContact;
import ch.paru.scrumTools.exchangeServer.services.contact.EwsContactGroup;

public class MockData {

	public static final EwsContact CONTACT_HANS;
	public static final EwsContact CONTACT_PETER;
	public static final EwsContact CONTACT_FRITZ;
	public static final EwsContact CONTACT_PAUL;
	public static final EwsContact CONTACT_URS;

	public static final EwsContact TEAM_MAILBOX;

	public static final EwsContactGroup GROUP_NORTH;
	public static final EwsContactGroup GROUP_SOUTH;
	public static final EwsContactGroup GROUP_WEST;

	private static int appointmentChooser = 0;

	static {
		// Contacts
		CONTACT_HANS = new EwsContact("hans@abcd.ef");
		CONTACT_PETER = new EwsContact("peter.mueller@abcd.ef");
		CONTACT_FRITZ = new EwsContact("fritz@abcd.ef");
		CONTACT_PAUL = new EwsContact("paul@abcd.ef");
		CONTACT_URS = new EwsContact("urs@abcd.ef");

		TEAM_MAILBOX = new EwsContact("team@abcd.ef");

		// Contact Groups
		GROUP_NORTH = new EwsContactGroup("NORTH");
		GROUP_NORTH.addMember(CONTACT_HANS);
		GROUP_NORTH.addMember(CONTACT_PETER);
		GROUP_SOUTH = new EwsContactGroup("SOUTH");
		GROUP_SOUTH.addMember(CONTACT_FRITZ);
		GROUP_SOUTH.addMember(CONTACT_PAUL);
		GROUP_WEST = new EwsContactGroup("WEST");
		GROUP_WEST.addMember(CONTACT_URS);
	}

	public static List<EwsAppointment> getTeamAbsences(EwsDay day) {
		List<EwsAppointment> appointments = Lists.newArrayList();

		int fullDayDuration = EwsAppointment.ALL_DAY_EVENT;
		EwsTime midnight = new EwsTime(0, 0);
		switch (appointmentChooser) {
		case 0:
			appointments.add(new EwsAppointment("Typ 0A", CONTACT_FRITZ, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			appointments.add(new EwsAppointment("Typ 0B", CONTACT_PAUL, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			break;
		case 1:
			appointments.add(new EwsAppointment("Typ 1A", CONTACT_FRITZ, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			appointments.add(new EwsAppointment("Typ 1B", CONTACT_HANS, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			appointments.add(new EwsAppointment("Typ 1C", CONTACT_PAUL, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			appointments.add(new EwsAppointment("Typ 1D", CONTACT_PETER, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			break;
		case 2:
			appointments.add(new EwsAppointment("Typ 2A", CONTACT_FRITZ, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			appointments.add(new EwsAppointment("Typ 2B", CONTACT_PETER, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			break;

		}

		appointmentChooser = (appointmentChooser + 1) % 3;
		return appointments;
	}

	public static List<EwsAppointment> getAppointments(EwsDay day, CalendarCategories category) {
		List<EwsAppointment> appointments;
		int fullDayDuration = EwsAppointment.ALL_DAY_EVENT;
		EwsTime midnight = new EwsTime(0, 0);

		switch (category) {
		case ABSENCES:
			return getTeamAbsences(day);

		case PUBLIC_HOLIDAY:
			return Lists.newArrayList();

		case SPRINT_START:
		case SPRINT_END:
			appointments = Lists.newArrayList();
			appointments.add(new EwsAppointment("START/STOP", TEAM_MAILBOX, day, fullDayDuration, midnight,
					CalendarCategories.SPRINT_START, CalendarCategories.SPRINT_END));
			return appointments;

		}
		throw new EchangeServerException("mock contains not all categories", null);
	}
}
