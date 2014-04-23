package ch.paru.scrumTools.capacity.release.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;
import ch.paru.scrumTools.backendServer.services.mock.MockData;
import ch.paru.scrumTools.backendServer.utils.ServerDayUtil;

public class ReleaseDataTest {

	@Test
	public void testMemberCapacity_noMember() {
		//prepare
		CalendarWeek week = new CalendarWeek(1, 2013);

		//run
		ReleaseData data = new ReleaseData(null, null);
		data.addTeamMemberWorkingDay(week, null);

		//validate
	}

	@Test
	public void testMemberCapacity_noInput() {
		//prepare

		//run
		ReleaseData data = new ReleaseData(null, null);
		data.addTeamMemberWorkingDay(null, null);

		//validate
	}

	@Test
	public void testMemberCapacity_normal1Week() {
		//prepare
		CalendarWeek week = new CalendarWeek(1, 2013);
		TeamMember member = new TeamMember(MockData.CONTACT_HANS);
		WeekCapacity weekCapacity = new WeekCapacity();
		weekCapacity.addSingleDay();

		//run
		ReleaseData data = new ReleaseData(null, null);
		data.addTeamMemberWorkingDay(week, member);

		//validate
		Map<CalendarWeek, WeekCapacity> capacitiesForMember = data.getWeekCapacityForMember(member);
		assertNotNull(capacitiesForMember);
		assertEquals(1, capacitiesForMember.size());
		assertTrue(capacitiesForMember.containsKey(week));
		assertTrue(capacitiesForMember.containsValue(weekCapacity));
	}

	@Test
	public void testMemberCapacity_normal2Weeks() {
		//prepare
		CalendarWeek week1 = new CalendarWeek(1, 2013);
		CalendarWeek week2 = new CalendarWeek(2, 2013);
		TeamMember member = new TeamMember(MockData.CONTACT_HANS);
		WeekCapacity week1Capacity = new WeekCapacity();
		week1Capacity.addSingleDay();
		WeekCapacity week2Capacity = new WeekCapacity();
		week2Capacity.addSingleDay();
		week2Capacity.addSingleDay();

		//run
		ReleaseData data = new ReleaseData(null, null);
		data.addTeamMemberWorkingDay(week1, member);
		data.addTeamMemberWorkingDay(week2, member);
		data.addTeamMemberWorkingDay(week2, member);

		//validate
		Map<CalendarWeek, WeekCapacity> capacitiesForMember = data.getWeekCapacityForMember(member);
		assertNotNull(capacitiesForMember);
		assertEquals(2, capacitiesForMember.size());
		assertTrue(capacitiesForMember.containsKey(week1));
		assertTrue(capacitiesForMember.containsKey(week2));
		assertTrue(capacitiesForMember.containsValue(week1Capacity));
		assertTrue(capacitiesForMember.containsValue(week2Capacity));
		assertEquals(week1Capacity, capacitiesForMember.get(week1));
		assertEquals(week2Capacity, capacitiesForMember.get(week2));
	}

	@Test
	public void testWorkingDays() {
		//prepare
		ServerDay day1 = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		ServerDay day2 = ServerDayUtil.createDayFromNumbers(2, 2, 2013);

		//run
		ReleaseData data = new ReleaseData(null, null);
		data.addWorkingDay(day1);

		//validate
		assertTrue(data.isWorkingDay(day1));
		assertFalse(data.isWorkingDay(day2));
	}

	@Test
	public void testCalendarWeeks() {
		//prepare
		CalendarWeek week1 = new CalendarWeek(1, 2013);
		CalendarWeek week2 = new CalendarWeek(2, 2013);

		//run
		ReleaseData data = new ReleaseData(null, null);
		data.addCalendarWeek(week1);
		data.addCalendarWeek(week2);

		//validate
		List<CalendarWeek> weeks = data.getCalendarWeeks();
		assertNotNull(weeks);
		assertEquals(2, weeks.size());
		assertTrue(weeks.contains(week1));
		assertTrue(weeks.contains(week2));
	}

	@Test
	public void testCalendarWeeks_TwiceAdded() {
		//prepare
		CalendarWeek week = new CalendarWeek(1, 2013);

		//run
		ReleaseData data = new ReleaseData(null, null);
		data.addCalendarWeek(week);
		data.addCalendarWeek(week);

		//validate
		List<CalendarWeek> weeks = data.getCalendarWeeks();
		assertNotNull(weeks);
		assertEquals(1, weeks.size());
		assertTrue(weeks.contains(week));
	}
}
