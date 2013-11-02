package ch.paru.scrumTools.exchangeServer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ch.paru.scrumTools.exchangeServer.services.calendar.EwsDay;

public class EwsDayUtil {

	static String[] WEEKDAYS = { "So.", "Mo.", "Di.", "Mi.", "Do.", "Fr.", "Sa." };

	private EwsDayUtil() {
	}

	public static EwsDay createDayFromCalendar(Calendar cal) {
		EwsDay day = new EwsDay(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
		return day;
	}

	public static EwsDay createDayFromDate(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return createDayFromCalendar(cal);
	}

	public static EwsDay createDayFromNumbers(int day, int month, int year) {
		return new EwsDay(day, month, year);
	}

	public static int compare(EwsDay day1, EwsDay day2) {
		return day1.getCalendar().compareTo(day2.getCalendar());
	}

	public static EwsDay addDays(EwsDay day, int offset) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(day.getDate());
		cal.add(Calendar.DAY_OF_MONTH, offset);
		return createDayFromCalendar(cal);
	}

	public static boolean isSunday(EwsDay day) {
		Calendar cal = day.getCalendar();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek == Calendar.SUNDAY;
	}

	public static int getCalendarWeek(EwsDay day) {
		Calendar cal = day.getCalendar();
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public static String getDisplayText(EwsDay day) {
		Calendar cal = day.getCalendar();

		StringBuffer sb = new StringBuffer();
		sb.append(formatWeekDay(cal));
		sb.append(" ");
		sb.append(cal.get(Calendar.DAY_OF_MONTH));
		sb.append(".");
		sb.append(cal.get(Calendar.MONTH) + 1);
		sb.append(".");
		sb.append(cal.get(Calendar.YEAR));
		return sb.toString();
	}

	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		return sdf.format(new Date());
	}

	private static String formatWeekDay(Calendar cal) {
		return WEEKDAYS[cal.get(Calendar.DAY_OF_WEEK) - 1];
	}
}
