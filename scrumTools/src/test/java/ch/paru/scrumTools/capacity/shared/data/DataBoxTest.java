package ch.paru.scrumTools.capacity.shared.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class DataBoxTest {

	@Test
	public void testAddTeamMember() {
		//prepare
		String teamName = "Team";
		ServerContact contact = MockData.CONTACT_HANS;

		//run
		DataBox data = new DataBox();
		data.addTeamMember(teamName, contact);

		//validate
		List<ServerContact> allContacts = data.getAllContacts();
		assertEquals(1, allContacts.size());
		assertTrue(allContacts.contains(contact));

		List<TeamMember> allTeamMembers = data.getAllTeamMembers();
		assertEquals(1, allTeamMembers.size());
		assertEquals(contact, allTeamMembers.get(0).getContact());

		List<Team> allTeams = data.getAllTeams();
		assertEquals(1, allTeams.size());
		Team team = allTeams.get(0);
		assertEquals(teamName, team.getName());
		assertEquals(1, team.getAllMembers().size());
	}

	@Test
	public void testSetConfigForMember() {
		//prepare
		ServerContact contact = MockData.CONTACT_HANS;
		ConfigUser user = new ConfigUser(contact.getMailAddress());

		//run
		DataBox data = new DataBox();
		data.addTeamMember("TEAM", contact);
		data.setConfigurationForMember(contact, user);

		//validate
		assertEquals(user, data.getAllTeamMembers().get(0).getConfiguration());
	}

	@Test
	public void testAddAbsenceForMember() {
		//prepare
		ServerContact contact = MockData.CONTACT_HANS;
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2013);

		//run
		DataBox data = new DataBox();
		data.addTeamMember("TEAM", contact);
		data.addAbsenceForMember(contact, day);

		//validate
		TeamMember member = data.getAllTeamMembers().get(0);
		assertTrue(!member.isAvailable(day));
	}

}
