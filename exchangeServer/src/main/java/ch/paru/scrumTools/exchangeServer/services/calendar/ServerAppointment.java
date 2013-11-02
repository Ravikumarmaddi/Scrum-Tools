package ch.paru.scrumTools.exchangeServer.services.calendar;

import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;

public class ServerAppointment {
	public static final int ALL_DAY_EVENT = -1;

	private ServerContact creator;
	private String subject;
	private final ServerDay day;
	private int durationInMin;
	private ServerTime startTime;
	private List<CalendarCategories> categories;

	public ServerAppointment(String subject, ServerContact creator, ServerDay day, int durationInMin, ServerTime startTime,
			CalendarCategories... categories) {
		this.creator = creator;
		this.subject = subject;
		this.day = day;
		this.durationInMin = durationInMin;
		this.startTime = startTime;
		this.categories = Lists.newArrayList(categories);
	}

	public ServerContact getCreator() {
		return creator;
	}

	public String getSubject() {
		return subject;
	}

	public ServerDay getDay() {
		return day;
	}

	public int getDurationInMin() {
		return durationInMin;
	}

	public void setDurationInMin(int durationInMin) {
		this.durationInMin = durationInMin;
	}

	public ServerTime getStartTime() {
		return startTime;
	}

	public void addCategory(CalendarCategories category) {
		categories.add(category);
	}

	public List<CalendarCategories> getCategories() {
		return categories;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		ServerAppointment other = (ServerAppointment) obj;
		if (!day.equals(other.day)) {
			return false;
		}
		if (!subject.equals(other.subject)) {
			return false;
		}
		if (!creator.equals(other.creator)) {
			return false;
		}
		if (durationInMin != other.durationInMin) {
			return false;
		}
		if (!startTime.equals(other.startTime)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(creator, subject, day, durationInMin, startTime);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("day", day).add("creator", creator).add("subject", subject)
				.add("durationInMin", getDurationInMin()).add("startTime", getStartTime()).toString();
	}

}
