package ch.paru.scrumTools.capacity.shared.data;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class DataBoxTest {
	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final TeamMemberFactory TEAM_MEMBER_FACTORY_MOCK = MOCKS.createMock("TEAM_MEMBER_FACTORY_MOCK",
			TeamMemberFactory.class);

	@Test
	public void testAddTeamMember() {
		String teamName = "Team";
		ServerContact contact = MockData.CONTACT_HANS;
		TeamMember member = new TeamMember(contact);

		MOCKS.resetAll();
		expect(TEAM_MEMBER_FACTORY_MOCK.createTeamMember(contact)).andReturn(member);

		MOCKS.replayAll();
		DataBox data = new DataBox(TEAM_MEMBER_FACTORY_MOCK);
		data.addTeamMember(teamName, contact);

		MOCKS.verifyAll();
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
		ServerContact contact = MockData.CONTACT_HANS;
		ConfigUser user = new ConfigUser(contact.getMailAddress());
		TeamMember member = new TeamMember(contact);

		MOCKS.resetAll();
		expect(TEAM_MEMBER_FACTORY_MOCK.createTeamMember(contact)).andReturn(member);

		MOCKS.replayAll();
		DataBox data = new DataBox(TEAM_MEMBER_FACTORY_MOCK);
		data.addTeamMember("TEAM", contact);
		data.setConfigurationForMember(contact, user);

		MOCKS.verifyAll();
		assertEquals(user, member.getConfiguration());
	}

	@Test
	public void testAddAbsenceForMember() {
		ServerContact contact = MockData.CONTACT_HANS;
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		TeamMember member = new TeamMember(contact);

		MOCKS.resetAll();
		expect(TEAM_MEMBER_FACTORY_MOCK.createTeamMember(contact)).andReturn(member);

		MOCKS.replayAll();
		DataBox data = new DataBox(TEAM_MEMBER_FACTORY_MOCK);
		data.addTeamMember("TEAM", contact);
		data.addAbsenceForMember(contact, day);

		MOCKS.verifyAll();
		assertTrue(!member.isAvailable(day));
	}

}
