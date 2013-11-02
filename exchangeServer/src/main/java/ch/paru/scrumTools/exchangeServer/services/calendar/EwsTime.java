package ch.paru.scrumTools.exchangeServer.services.calendar;

import com.google.common.base.Objects;

public class EwsTime {
	private int hour;
	private int min;

	public EwsTime(int hour, int min) {
		this.hour = hour;
		this.min = min;
	}

	public int getHour() {
		return hour;
	}

	public int getMin() {
		return min;
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

		EwsTime other = (EwsTime) obj;
		return toString().equals(other.toString());
	}

	public String toString() {
		return Objects.toStringHelper(this).add("hour", getHour()).add("min", getMin()).toString();
	}
}
