package ch.paru.scrumTools.backendServer.services.mock;

import java.util.List;

import ch.paru.scrumTools.backendServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.backendServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;
import ch.paru.scrumTools.backendServer.services.calendar.ServerTime;
import ch.paru.scrumTools.backendServer.services.contact.ServerContact;
import ch.paru.scrumTools.backendServer.services.contact.ServerContactGroup;
import ch.paru.scrumTools.backendServer.utils.ServerAppointmentUtil;
import ch.paru.scrumTools.backendServer.utils.exceptions.ServerException;

import com.google.common.collect.Lists;

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

		switch (appointmentChooser) {
		case 0:
			appointments.add(ServerAppointmentUtil.createAllDayAbsenceAppointment("Typ 0A", CONTACT_FRITZ, day));
			appointments.add(ServerAppointmentUtil.createAllDayAbsenceAppointment("Typ 0B", CONTACT_PAUL, day));
			break;
		case 1:
			appointments.add(ServerAppointmentUtil.createAllDayAbsenceAppointment("Typ 1A", CONTACT_FRITZ, day));
			appointments.add(ServerAppointmentUtil.createAllDayAbsenceAppointment("Typ 1B", CONTACT_HANS, day));
			appointments.add(ServerAppointmentUtil.createAllDayAbsenceAppointment("Typ 1C", CONTACT_PAUL, day));
			appointments.add(ServerAppointmentUtil.createAllDayAbsenceAppointment("Typ 1D", CONTACT_PETER, day));
			break;
		case 2:
			appointments.add(ServerAppointmentUtil.createAllDayAbsenceAppointment("Typ 2A", CONTACT_FRITZ, day));
			appointments.add(ServerAppointmentUtil.createAllDayAbsenceAppointment("Typ 2B", CONTACT_PETER, day));
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
		throw new ServerException("mock contains not all categories", null);
	}
}
