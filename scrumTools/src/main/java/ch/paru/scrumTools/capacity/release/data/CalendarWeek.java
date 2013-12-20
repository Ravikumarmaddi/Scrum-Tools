package ch.paru.scrumTools.capacity.release.data;

import com.google.common.base.Objects;

public class CalendarWeek {

	private int weekNumber;
	private int year;

	public CalendarWeek(int calendarWeek, int year) {
		weekNumber = calendarWeek;
		this.year = year;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	@Override
	public int hashCode() {
		return (weekNumber + "_" + year).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CalendarWeek other = (CalendarWeek) obj;
		if (weekNumber != other.weekNumber) {
			return false;
		}
		if (year != other.year) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("weekNumber", weekNumber).add("year", year).toString();
	}

	public int getYear() {
		return year;
	}
}
