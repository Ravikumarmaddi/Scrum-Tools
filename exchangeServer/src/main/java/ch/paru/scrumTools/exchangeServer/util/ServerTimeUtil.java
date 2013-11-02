package ch.paru.scrumTools.exchangeServer.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ch.paru.scrumTools.exchangeServer.services.calendar.ServerTime;

public class ServerTimeUtil {
	private static int HOUR_OFFSET = 2;

	private static int BEFORE = -1;
	private static int AFTER = 1;
	private static int EQUAL = 0;

	private ServerTimeUtil() {
	}

	public static ServerTime createTimeFromDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, HOUR_OFFSET);
		return new ServerTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
	}

	public static int compare(ServerTime time1, ServerTime time2) {
		if (time1.getHour() < time2.getHour()) {
			return BEFORE;
		}
		else if (time1.getHour() > time2.getHour()) {
			return AFTER;
		}

		if (time1.getMin() < time2.getMin()) {
			return BEFORE;
		}
		else if (time1.getMin() > time2.getMin()) {
			return AFTER;
		}

		return EQUAL;
	}

	public static boolean isABeforeB(ServerTime timeA, ServerTime timeB) {
		return compare(timeA, timeB) == BEFORE;
	}

	public static boolean isAAfterB(ServerTime timeA, ServerTime timeB) {
		return compare(timeA, timeB) == AFTER;
	}
}
