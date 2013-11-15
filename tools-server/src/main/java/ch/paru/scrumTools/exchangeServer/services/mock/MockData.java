package ch.paru.scrumTools.exchangeServer.services.mock;

import java.util.List;

import com.google.common.collect.Lists;
import ch.paru.scrumTools.server.api.calendar.CalendarCategories;
import ch.paru.scrumTools.server.api.calendar.ServerAppointment;
import ch.paru.scrumTools.server.api.calendar.ServerDay;
import ch.paru.scrumTools.server.api.calendar.ServerTime;
import ch.paru.scrumTools.server.api.contact.ServerContact;
import ch.paru.scrumTools.server.api.contact.ServerContactGroup;
import ch.paru.scrumTools.server.api.exceptions.EchangeServerException;

public class MockData {

	public static final ServerContact CONTACT_HANS;
	public static final ServerContact CONTACT_PETER;
	public static final ServerContact CONTACT_FRITZ;
	public static final ServerContact CONTACT_PAUL;
	public static final ServerContact CONTACT_URS;

	public static final ServerContact TEAM_MAILBOX;

	public static final ServerContactGroup GROUP_NORTH;
	public static final ServerContactGroup GROUP_SOUTH;
	public static final ServerContactGroup GROUP_WEST;

	private static int appointmentChooser = 0;

	static {
		// Contacts
		CONTACT_HANS = new ServerContact("hans@abcd.ef");
		CONTACT_PETER = new ServerContact("peter.mueller@abcd.ef");
		CONTACT_FRITZ = new ServerContact("fritz@abcd.ef");
		CONTACT_PAUL = new ServerContact("paul@abcd.ef");
		CONTACT_URS = new ServerContact("urs@abcd.ef");

		TEAM_MAILBOX = new ServerContact("team@abcd.ef");

		// Contact Groups
		GROUP_NORTH = new ServerContactGroup("NORTH");
		GROUP_NORTH.addMember(CONTACT_HANS);
		GROUP_NORTH.addMember(CONTACT_PETER);
		GROUP_SOUTH = new ServerContactGroup("SOUTH");
		GROUP_SOUTH.addMember(CONTACT_FRITZ);
		GROUP_SOUTH.addMember(CONTACT_PAUL);
		GROUP_WEST = new ServerContactGroup("WEST");
		GROUP_WEST.addMember(CONTACT_URS);
	}

	public static List<ServerAppointment> getTeamAbsences(ServerDay day) {
		List<ServerAppointment> appointments = Lists.newArrayList();

		int fullDayDuration = ServerAppointment.ALL_DAY_EVENT;
		ServerTime midnight = new ServerTime(0, 0);
		switch (appointmentChooser) {
		case 0:
			appointments.add(new ServerAppointment("Typ 0A", CONTACT_FRITZ, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			appointments.add(new ServerAppointment("Typ 0B", CONTACT_PAUL, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			break;
		case 1:
			appointments.add(new ServerAppointment("Typ 1A", CONTACT_FRITZ, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			appointments.add(new ServerAppointment("Typ 1B", CONTACT_HANS, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			appointments.add(new ServerAppointment("Typ 1C", CONTACT_PAUL, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			appointments.add(new ServerAppointment("Typ 1D", CONTACT_PETER, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			break;
		case 2:
			appointments.add(new ServerAppointment("Typ 2A", CONTACT_FRITZ, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			appointments.add(new ServerAppointment("Typ 2B", CONTACT_PETER, day, fullDayDuration, midnight,
					CalendarCategories.ABSENCES));
			break;

		}

		appointmentChooser = (appointmentChooser + 1) % 3;
		return appointments;
	}

	public static List<ServerAppointment> getAppointments(ServerDay day, CalendarCategories category) {
		List<ServerAppointment> appointments;
		int fullDayDuration = ServerAppointment.ALL_DAY_EVENT;
		ServerTime midnight = new ServerTime(0, 0);

		switch (category) {
		case ABSENCES:
			return getTeamAbsences(day);

		case PUBLIC_HOLIDAY:
			return Lists.newArrayList();

		case SPRINT_START:
		case SPRINT_END:
			appointments = Lists.newArrayList();
			appointments.add(new ServerAppointment("START/STOP", TEAM_MAILBOX, day, fullDayDuration, midnight,
					CalendarCategories.SPRINT_START, CalendarCategories.SPRINT_END));
			return appointments;

		}
		throw new EchangeServerException("mock contains not all categories", null);
	}
}
