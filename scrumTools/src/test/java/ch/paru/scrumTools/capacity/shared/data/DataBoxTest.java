package ch.paru.scrumTools.capacity.shared.data;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.factories.TeamFactory;
import ch.paru.scrumTools.capacity.shared.factories.TeamMemberFactory;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class DataBoxTest {
	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final TeamFactory TEAM_FACTORY_MOCK = MOCKS.createMock("TEAM_FACTORY_MOCK", TeamFactory.class);
	private static final TeamMemberFactory TEAM_MEMBER_FACTORY_MOCK = MOCKS.createMock("TEAM_MEMBER_FACTORY_MOCK",
			TeamMemberFactory.class);

	@Test
	public void testAddTeamMember() {
		String teamName = "Team";
		Team team = new Team(teamName);
		ServerContact contactA = MockData.CONTACT_HANS;
		TeamMember memberA = new TeamMember(contactA);
		ServerContact contactB = MockData.CONTACT_FRITZ;
		TeamMember memberB = new TeamMember(contactB);

		MOCKS.resetAll();
		expect(TEAM_FACTORY_MOCK.createTeam(teamName)).andReturn(team);
		expect(TEAM_MEMBER_FACTORY_MOCK.createTeamMember(contactA)).andReturn(memberA);
		expect(TEAM_MEMBER_FACTORY_MOCK.createTeamMember(contactB)).andReturn(memberB);

		MOCKS.replayAll();
		DataBox data = new DataBox(TEAM_FACTORY_MOCK, TEAM_MEMBER_FACTORY_MOCK);
		data.addTeamMember(teamName, contactA);
		data.addTeamMember(teamName, contactB);

		MOCKS.verifyAll();
		List<ServerContact> allContacts = data.getAllContacts();
		assertEquals(2, allContacts.size());
		assertTrue(allContacts.contains(contactA));
		assertTrue(allContacts.contains(contactB));

		List<TeamMember> allTeamMembers = data.getAllTeamMembers();
		assertEquals(2, allTeamMembers.size());
		assertEquals(contactA, allTeamMembers.get(0).getContact());
		assertEquals(contactB, allTeamMembers.get(1).getContact());

		List<Team> allTeams = data.getAllTeams();
		assertEquals(1, allTeams.size());
		assertEquals(team, allTeams.get(0));
		assertEquals(2, team.getAllMembers().size());
	}

	@Test
	public void testSetConfigForMember() {
		String teamName = "TEAM";
		Team team = new Team(teamName);
		ServerContact contact = MockData.CONTACT_HANS;
		ConfigUser user = new ConfigUser(contact.getMailAddress());
		TeamMember member = new TeamMember(contact);

		MOCKS.resetAll();
		expect(TEAM_FACTORY_MOCK.createTeam(teamName)).andReturn(team);
		expect(TEAM_MEMBER_FACTORY_MOCK.createTeamMember(contact)).andReturn(member);

		MOCKS.replayAll();
		DataBox data = new DataBox(TEAM_FACTORY_MOCK, TEAM_MEMBER_FACTORY_MOCK);
		data.addTeamMember(teamName, contact);
		data.setConfigurationForMember(contact, user);

		MOCKS.verifyAll();
		assertEquals(user, member.getConfiguration());
	}

	@Test
	public void testAddAbsenceForMember() {
		String teamName = "TEAM";
		Team team = new Team(teamName);
		ServerContact contact = MockData.CONTACT_HANS;
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		TeamMember member = new TeamMember(contact);

		MOCKS.resetAll();
		expect(TEAM_FACTORY_MOCK.createTeam(teamName)).andReturn(team);
		expect(TEAM_MEMBER_FACTORY_MOCK.createTeamMember(contact)).andReturn(member);

		MOCKS.replayAll();
		DataBox data = new DataBox(TEAM_FACTORY_MOCK, TEAM_MEMBER_FACTORY_MOCK);
		data.addTeamMember(teamName, contact);
		data.addAbsenceForMember(contact, day);

		MOCKS.verifyAll();
		assertFalse(member.isAvailable(day));
	}

	@Test
	public void testAddAbsenceForUnknownMember() {
		ServerContact contact = MockData.CONTACT_HANS;
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		TeamMember member = new TeamMember(contact);

		MOCKS.resetAll();

		MOCKS.replayAll();
		DataBox data = new DataBox(TEAM_FACTORY_MOCK, TEAM_MEMBER_FACTORY_MOCK);
		data.addAbsenceForMember(contact, day);

		MOCKS.verifyAll();
		assertTrue(member.isAvailable(day));
	}

	@Test
	public void testStartDate() {
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2014);
		DataBox data = new DataBox(null, null);
		data.setStartDay(day);
		assertEquals(day, data.getStartDay());
		assertNull(data.getEndDay());
	}

	@Test
	public void testEndDate() {
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2014);
		DataBox data = new DataBox(null, null);
		data.setEndDay(day);
		assertEquals(day, data.getEndDay());
		assertNull(data.getStartDay());
	}

}
