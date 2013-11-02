package ch.paru.scrumTools.exchangeServer.services.calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.common.base.Objects;

public class ServerDay {

	private Calendar calendar;

	public ServerDay(int day, int month, int year) {
		calendar = new GregorianCalendar(year, month - 1, day);
	}

	public Date getDate() {
		return calendar.getTime();
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public int getCalendarWeek() {
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		ServerDay other = (ServerDay) obj;
		return toString().equals(other.toString());
	}

	@Override
	public String toString() {
		String date = "";
		date += calendar.get(Calendar.DAY_OF_MONTH);
		date += ".";
		date += (calendar.get(Calendar.MONTH) + 1);
		date += ".";
		date += calendar.get(Calendar.YEAR);

		return Objects.toStringHelper(this).add("date", date).toString();
	}

}
