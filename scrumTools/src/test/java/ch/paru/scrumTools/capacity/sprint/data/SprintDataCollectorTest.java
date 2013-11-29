package ch.paru.scrumTools.capacity.sprint.data;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.exchangeServer.manager.ServerFacade;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.contact.ContactService;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContactGroup;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;
import ch.paru.scrumTools.exchangeServer.utils.ServerAppointmentUtil;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

import com.google.common.collect.Lists;

public class SprintDataCollectorTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final ServerFacade SERVER_FACADE_MOCK = MOCKS.createMock("SERVER_FACADE_MOCK", ServerFacade.class);
	private static final SprintCapacityConfiguration CONFIG_MOCK = MOCKS.createMock("CONFIG_MOCK",
			SprintCapacityConfiguration.class);
	private static final ContactService CONTACT_SERVICE_MOCK = MOCKS.createMock("CONTACT_SERVICE_MOCK",
			ContactService.class);
	private static final CalendarService CALENDAR_SERVICE_MOCK = MOCKS.createMock("CALENDAR_SERVICE_MOCK",
			CalendarService.class);

	@Test
	public void testCollectData() {
		String team = "Team";
		ServerContact contactA = MockData.CONTACT_PAUL;
		ServerContactGroup groupTeam = new ServerContactGroup(team);
		ServerContact contactB = MockData.CONTACT_PETER;
		groupTeam.addMember(contactA);
		groupTeam.addMember(contactB);
		String teamReserve = "TeamReserve";
		ServerContactGroup groupTeamReserve = new ServerContactGroup(teamReserve);
		groupTeamReserve.addMember(MockData.CONTACT_FRITZ);
		groupTeamReserve.addMember(MockData.CONTACT_HANS);

		ConfigUser userA = new ConfigUser(contactA.getMailAddress());
		ConfigUser userB = new ConfigUser(contactB.getMailAddress());

		ServerDay startDay = ServerDayUtil.createDayFromNumbers(1, 1, 2013);
		ServerDay endDay = ServerDayUtil.createDayFromNumbers(3, 1, 2013);

		ServerDay day1 = ServerDayUtil.addDays(startDay, 0);
		ServerAppointment appmtA1 = ServerAppointmentUtil.createAllDayAbsenceAppointment("Holiday", contactA, day1);
		ServerDay day2 = ServerDayUtil.addDays(startDay, 1);
		ServerAppointment appmtA2 = ServerAppointmentUtil.createAllDayAbsenceAppointment("Holiday", contactA, day2);
		ServerAppointment appmtB2 = ServerAppointmentUtil.createAllDayAbsenceAppointment("Free", contactB, day2);
		ServerDay day3 = ServerDayUtil.addDays(startDay, 2);
		ServerAppointment appmtA3 = ServerAppointmentUtil.createAllDayAbsenceAppointment("Holiday", contactA, day3);

		ServerAppointment sprintStart = ServerAppointmentUtil.createAllDayAppointment("Sprint Start",
				new ServerContact("admin@sprintTools.com"), day1, CalendarCategories.SPRINT_START);

		MOCKS.resetAll();
		expect(CONFIG_MOCK.getTeams()).andReturn(Lists.newArrayList(team));
		expect(CONFIG_MOCK.getUser(contactA.getMailAddress())).andReturn(userA);
		expect(CONFIG_MOCK.getUser(contactB.getMailAddress())).andReturn(userB);
		expect(SERVER_FACADE_MOCK.getContactService()).andReturn(CONTACT_SERVICE_MOCK);
		expect(SERVER_FACADE_MOCK.getCalendarService()).andReturn(CALENDAR_SERVICE_MOCK).times(4);
		expect(CONTACT_SERVICE_MOCK.getAllContactGroups()).andReturn(Lists.newArrayList(groupTeam, groupTeamReserve));
		expect(CALENDAR_SERVICE_MOCK.getAllAppointmentsOfCategory(day1, CalendarCategories.ABSENCES)).andReturn(
				Lists.newArrayList(appmtA1));
		expect(CALENDAR_SERVICE_MOCK.getAllAppointmentsOfCategory(day2, CalendarCategories.ABSENCES)).andReturn(
				Lists.newArrayList(appmtA2, appmtB2));
		expect(CALENDAR_SERVICE_MOCK.getAllAppointmentsOfCategory(day3, CalendarCategories.ABSENCES)).andReturn(
				Lists.newArrayList(appmtA3));
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day1)).andReturn(Boolean.TRUE);
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day2)).andReturn(Boolean.TRUE);
		expect(CALENDAR_SERVICE_MOCK.isWorkingDay(day3)).andReturn(Boolean.FALSE);
		expect(CALENDAR_SERVICE_MOCK.getSingleAppointmentOfCategory(day1, CalendarCategories.SPRINT_START)).andReturn(
				sprintStart);
		expect(CALENDAR_SERVICE_MOCK.getSingleAppointmentOfCategory(day3, CalendarCategories.SPRINT_END)).andReturn(
				null);

		MOCKS.replayAll();
		SprintDataCollector dataCollector = new SprintDataCollector(SERVER_FACADE_MOCK, CONFIG_MOCK);
		SprintData data = dataCollector.collectData(startDay, endDay);

		MOCKS.verifyAll();
		assertNotNull(data);

		assertEquals(1, data.getAllTeams().size());
		assertEquals(team, data.getAllTeams().get(0).getName());

		assertEquals(2, data.getAllTeamMembers().size());
		checkExistingTeamMember(data.getAllTeamMembers(), userA, startDay, endDay, Lists.newArrayList(day1, day2, day3));
		checkExistingTeamMember(data.getAllTeamMembers(), userB, startDay, endDay, Lists.newArrayList(day2));
		assertTrue(data.getAllContacts().contains(contactA));
		assertTrue(data.getAllContacts().contains(contactB));

		assertEquals(SprintDayType.PLANNING_FULLDAY, data.getDayType(day1));
		assertEquals(SprintDayType.DAILY_BUSINESS, data.getDayType(day2));
		assertEquals(null, data.getDayType(day3));
	}

	private void checkExistingTeamMember(List<TeamMember> allTeamMembers, ConfigUser userA, ServerDay startDay,
			ServerDay endDay, List<ServerDay> absenceDays) {
		for (TeamMember teamMember : allTeamMembers) {
			if (teamMember.getContact().getMailAddress().equals(userA.getMailAddress())) {
				assertEquals(userA, teamMember.getConfiguration());

				ServerDay day = startDay;
				while (ServerDayUtil.compare(day, endDay) <= 0) {
					if (absenceDays.contains(day)) {
						assertFalse(teamMember.isAvailable(day));
					}
					else {
						assertTrue(teamMember.isAvailable(day));
					}
					day = ServerDayUtil.addDays(day, 1);
				}
				return;
			}
		}
		fail(userA + " not found");
	}
}
