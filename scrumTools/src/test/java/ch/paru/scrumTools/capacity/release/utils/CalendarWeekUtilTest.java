package ch.paru.scrumTools.capacity.release.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Test;

import ch.paru.scrumTools.capacity.release.data.CalendarWeek;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class CalendarWeekUtilTest {

	@Test
	public void testCreateWeek() {
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		CalendarWeek cw = CalendarWeekUtil.createCalendarWeek(day);
		assertNotNull(cw);
		assertEquals(day.getCalendarWeek(), cw.getWeekNumber());
		assertEquals(day.getCalendar().get(Calendar.YEAR), cw.getYear());
	}

	@Test
	public void testDisplayText() {
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		CalendarWeek cw = CalendarWeekUtil.createCalendarWeek(day);

		String textDay = ServerDayUtil.getDisplayText(day);
		String textCW = CalendarWeekUtil.getDisplayText(cw);
		assertEquals("CW " + cw.getWeekNumber() + " (" + textDay + ")", textCW);
	}

}
