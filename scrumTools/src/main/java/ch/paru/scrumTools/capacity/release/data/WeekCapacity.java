package ch.paru.scrumTools.capacity.release.data;

import com.google.common.base.Objects;

public class WeekCapacity {
	private int daysAvailable;

	public WeekCapacity() {
		daysAvailable = 0;
	}

	public void addSingleDay() {
		daysAvailable++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + daysAvailable;
		return result;
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
		WeekCapacity other = (WeekCapacity) obj;
		if (daysAvailable != other.daysAvailable) {
			return false;
		}
		return true;
	}

	public int getDaysAvailable() {
		return daysAvailable;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("daysAvailable", daysAvailable).toString();
	}
}
