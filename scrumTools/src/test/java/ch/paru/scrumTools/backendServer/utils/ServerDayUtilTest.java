package ch.paru.scrumTools.backendServer.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Test;

import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;

public class ServerDayUtilTest {

	@Test
	public void testDisplayText() {
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		String text = ServerDayUtil.getDisplayText(day);
		assertEquals("01.02.2013", text);
	}

	@Test
	public void testGetCalendarWeek() {
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		int cw = day.getCalendar().get(Calendar.WEEK_OF_YEAR);
		int result = ServerDayUtil.getCalendarWeek(day);
		assertEquals(cw, result);
	}

	@Test
	public void testAddDays_Positive() {
		int firstDay = 2;
		int offset = 10;
		ServerDay day = ServerDayUtil.createDayFromNumbers(firstDay, 2, 2013);
		ServerDay dayEnd = ServerDayUtil.createDayFromNumbers(firstDay + offset, 2, 2013);

		ServerDay result = ServerDayUtil.addDays(day, offset);

		assertNotNull(result);
		assertEquals(dayEnd, result);
	}

	@Test
	public void testAddDays_Negative() {
		int firstDay = 22;
		int offset = -10;
		ServerDay day = ServerDayUtil.createDayFromNumbers(firstDay, 2, 2013);
		ServerDay dayEnd = ServerDayUtil.createDayFromNumbers(firstDay + offset, 2, 2013);

		ServerDay result = ServerDayUtil.addDays(day, offset);

		assertNotNull(result);
		assertEquals(dayEnd, result);
	}

	@Test
	public void testAddDays_Zero() {
		int firstDay = 22;
		int offset = 0;
		ServerDay day = ServerDayUtil.createDayFromNumbers(firstDay, 2, 2013);
		ServerDay dayEnd = ServerDayUtil.createDayFromNumbers(firstDay + offset, 2, 2013);

		ServerDay result = ServerDayUtil.addDays(day, offset);

		assertNotNull(result);
		assertEquals(dayEnd, result);
	}

}
