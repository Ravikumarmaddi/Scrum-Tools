package ch.paru.scrumTools.capacity.shared.data;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.configuration.CapacityConfiguration;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.exchangeServer.manager.ServerFacade;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.contact.ContactService;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContactGroup;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class DataCollector {

	private ServerFacade server;

	protected DataCollector(ServerFacade server) {
		this.server = server;
	}

	protected void loadTeams(DataBox data, List<String> teams) {
		ContactService contactService = server.getContactService();
		List<ServerContactGroup> allContactGroups = contactService.getAllContactGroups();
		for (ServerContactGroup contactGroup : allContactGroups) {
			String teamName = contactGroup.getName();

			if (teams.contains(teamName)) {
				List<ServerContact> members = contactGroup.getMembers();
				for (ServerContact member : members) {
					data.addTeamMember(teamName, member);
				}
			}
		}
	}

	protected void loadConfiguration(DataBox data, CapacityConfiguration config) {
		List<ServerContact> allTeamMembers = data.getAllContacts();
		for (ServerContact member : allTeamMembers) {
			ConfigUser user = config.getUser(member.getMailAddress());
			data.setConfigurationForMember(member, user);
		}
	}

	protected void loadAbsences(DataBox data, ServerDay startDay, ServerDay endDay) {
		CalendarService calendarService = server.getCalendarService();

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
