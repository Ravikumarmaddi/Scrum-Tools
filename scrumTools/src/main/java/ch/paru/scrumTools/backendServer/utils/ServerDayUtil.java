package ch.paru.scrumTools.backendServer.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;

public class ServerDayUtil {

	private ServerDayUtil() {
	}

	public static ServerDay createDayFromCalendar(Calendar cal) {
		ServerDay day = new ServerDay(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.YEAR));
		return day;
	}

	public static ServerDay createDayFromDate(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return createDayFromCalendar(cal);
	}

	public static ServerDay createDayFromNumbers(int day, int month, int year) {
		return new ServerDay(day, month, year);
	}

	public static int compare(ServerDay day1, ServerDay day2) {
		return day1.getCalendar().compareTo(day2.getCalendar());
	}

	public static ServerDay addDays(ServerDay day, int offset) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(day.getDate());
		cal.add(Calendar.DAY_OF_MONTH, offset);
		return createDayFromCalendar(cal);
	}

	public static int getCalendarWeek(ServerDay day) {
		Calendar cal = day.getCalendar();
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public static String getDisplayText(ServerDay day) {
		Calendar cal = day.getCalendar();

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		return sdf.format(cal.getTime());
	}
}
