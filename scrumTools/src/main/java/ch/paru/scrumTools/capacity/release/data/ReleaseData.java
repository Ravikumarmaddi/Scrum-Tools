package ch.paru.scrumTools.capacity.release.data;

import java.util.List;
import java.util.Map;

import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.shared.factories.TeamFactory;
import ch.paru.scrumTools.capacity.shared.factories.TeamMemberFactory;
import ch.paru.scrumTools.common.reflection.customs.Customizable;
import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ReleaseData extends DataBox implements Customizable {

	private List<ServerDay> workingDays;
	private List<CalendarWeek> weeks;
	private Map<TeamMember, Map<CalendarWeek, WeekCapacity>> memberCapacity;

	public ReleaseData(TeamFactory teamFactory, TeamMemberFactory teamMemberFactory) {
		super(teamFactory, teamMemberFactory);

		workingDays = Lists.newArrayList();
		weeks = Lists.newArrayList();
		memberCapacity = Maps.newHashMap();
	}

	public void addWorkingDay(ServerDay day) {
		if (!workingDays.contains(day)) {
			workingDays.add(day);
		}
	}

	public boolean isWorkingDay(ServerDay day) {
		return workingDays.contains(day);
	}

	public void addCalendarWeek(CalendarWeek calendarWeek) {
		if (!weeks.contains(calendarWeek)) {
			weeks.add(calendarWeek);
		}
	}

	public List<CalendarWeek> getCalendarWeeks() {
		return weeks;
	}

	public void addTeamMemberWorkingDay(CalendarWeek calendarWeek, TeamMember member) {
		if (calendarWeek == null || member == null) {
			return;
		}

		if (!memberCapacity.containsKey(member)) {
			Map<CalendarWeek, WeekCapacity> capaMap = Maps.newHashMap();
			memberCapacity.put(member, capaMap);
		}

		Map<CalendarWeek, WeekCapacity> capaMap = memberCapacity.get(member);
		if (!capaMap.containsKey(calendarWeek)) {
			capaMap.put(calendarWeek, new WeekCapacity());
		}

		WeekCapacity capacity = capaMap.get(calendarWeek);
		capacity.addSingleDay();
	}

	public Map<CalendarWeek, WeekCapacity> getWeekCapacityForMember(TeamMember member) {
		Map<CalendarWeek, WeekCapacity> map = memberCapacity.get(member);
		if (map == null) {
			map = Maps.newHashMap();
		}
		return map;
	}

}
