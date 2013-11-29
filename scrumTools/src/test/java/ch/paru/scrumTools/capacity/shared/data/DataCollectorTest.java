package ch.paru.scrumTools.capacity.shared.data;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Test;

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
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

import com.google.common.collect.Lists;

public class DataCollectorTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final ServerFacade SERVER_FACADE_MOCK = MOCKS.createMock("SERVER_FACADE_MOCK", ServerFacade.class);
	private static final ContactService CONTACT_SERVICE_MOCK = MOCKS.createMock("CONTACT_SERVICE_MOCK",
			ContactService.class);
	private static final CalendarService CALENDAR_SERVICE_MOCK = MOCKS.createMock("CALENDAR_SERVICE_MOCK",
			CalendarService.class);
	private static final ServerAppointment APPOINTMENT_MOCK = MOCKS.createMock("APPOINTMENT_MOCK",
			ServerAppointment.class);
	private static final CapacityConfiguration CONFIG_MOCK = MOCKS.createMock("CONFIG_MOCK",
			CapacityConfiguration.class);

	@Test
	public void testLoadTeams() {
		String groupName = "groupName";
		ServerContactGroup group = new ServerContactGroup(groupName);
		group.addMember(MockData.CONTACT_FRITZ);
		group.addMember(MockData.CONTACT_HANS);
		ArrayList<ServerContactGroup> contactGroups = Lists.newArrayList(group);
		DataBox box = new DataBox();
		MOCKS.resetAll();
		expect(SERVER_FACADE_MOCK.getContactService()).andReturn(CONTACT_SERVICE_MOCK);
		expect(CONTACT_SERVICE_MOCK.getAllContactGroups()).andReturn(contactGroups);
		MOCKS.replayAll();
		DataCollectorInstance instance = new DataCollectorInstance(SERVER_FACADE_MOCK);
		instance.loadTeams(box, Lists.newArrayList(groupName));
		MOCKS.verifyAll();
		List<Team> allTeams = box.getAllTeams();
		assertEquals(1, allTeams.size());
		Team team = allTeams.get(0);
		assertEquals(groupName, team.getName());
		List<TeamMember> allTeamMembers = box.getAllTeamMembers();
		assertEquals(2, allTeamMembers.size());
	}

	@Test
	public void testLoadAbsences() {
		DataBox box = new DataBox();
		ServerDay startDay = ServerDayUtil.createDayFromNumbers(1, 1, 2013);
		ServerDay endDay = ServerDayUtil.addDays(startDay, 1);
		ServerContact contactFritz = MockData.CONTACT_FRITZ;
		box.addTeamMember("team", contactFritz);
		ArrayList<ServerAppointment> absencesStartDay = Lists.newArrayList(APPOINTMENT_MOCK);
		ArrayList<ServerAppointment> absencesEndDay = new ArrayList<ServerAppointment>();
		MOCKS.resetAll();
		expect(SERVER_FACADE_MOCK.getCalendarService()).andReturn(CALENDAR_SERVICE_MOCK);
		expect(CALENDAR_SERVICE_MOCK.getAllAppointmentsOfCategory(startDay, CalendarCategories.ABSENCES)).andReturn(
				absencesStartDay);
		expect(CALENDAR_SERVICE_MOCK.getAllAppointmentsOfCategory(endDay, CalendarCategories.ABSENCES)).andReturn(
				absencesEndDay);
		expect(APPOINTMENT_MOCK.getCreator()).andReturn(contactFritz);
		MOCKS.replayAll();
		DataCollectorInstance instance = new DataCollectorInstance(SERVER_FACADE_MOCK);
		instance.loadAbsences(box, startDay, endDay);
		MOCKS.verifyAll();
		List<TeamMember> allTeamMembers = box.getAllTeamMembers();
		assertEquals(1, allTeamMembers.size());
		TeamMember member = allTeamMembers.get(0);
		assertFalse(member.isAvailable(startDay));
		assertTrue(member.isAvailable(endDay));
	}

	@Test
	public void testLoadConfiguration() {
		ServerContact contactFritz = MockData.CONTACT_FRITZ;
		ConfigUser user = new ConfigUser(contactFritz.getMailAddress());
		DataBox box = new DataBox();
		box.addTeamMember("TEAMNAME", contactFritz);
		MOCKS.resetAll();
		expect(CONFIG_MOCK.getUser(contactFritz.getMailAddress())).andReturn(user);
		MOCKS.replayAll();
		DataCollectorInstance instance = new DataCollectorInstance(SERVER_FACADE_MOCK);
		instance.loadConfiguration(box, CONFIG_MOCK);
		MOCKS.verifyAll();
		List<TeamMember> allTeamMembers = box.getAllTeamMembers();
		assertEquals(1, allTeamMembers.size());
		TeamMember member = allTeamMembers.get(0);
		assertEquals(user, member.getConfiguration());
	}

}
