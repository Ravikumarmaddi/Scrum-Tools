package ch.paru.scrumTools.capacity.release.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import ch.paru.scrumTools.capacity.release.data.CalendarWeek;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class CalendarWeekUtil {

	private CalendarWeekUtil() {
	}

	public static CalendarWeek createCalendarWeek(ServerDay day) {
		return new CalendarWeek(day.getCalendarWeek(), day.getCalendar().get(Calendar.YEAR));
	}

	public static String getDisplayText(CalendarWeek week) {
		Calendar cal = new GregorianCalendar(new Locale("DE", "CH"));
		cal.set(Calendar.YEAR, week.getYear());
		cal.set(Calendar.WEEK_OF_YEAR, week.getWeekNumber());
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		ServerDay day = ServerDayUtil.createDayFromCalendar(cal);

		StringBuffer sb = new StringBuffer();
		sb.append("CW ");
		sb.append(week.getWeekNumber());
		sb.append(" (");
		sb.append(ServerDayUtil.getDisplayText(day));
		sb.append(")");
		return sb.toString();
	}
}
