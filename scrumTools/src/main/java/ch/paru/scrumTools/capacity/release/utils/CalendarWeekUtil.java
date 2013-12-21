package ch.paru.scrumTools.capacity.release.utils;

import java.util.Calendar;

import ch.paru.scrumTools.capacity.release.data.CalendarWeek;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;

public class CalendarWeekUtil {

	private CalendarWeekUtil() {
	}

	public static CalendarWeek createCalendarWeek(ServerDay day) {
		return new CalendarWeek(day.getCalendarWeek(), day.getCalendar().get(Calendar.YEAR));
	}
}
