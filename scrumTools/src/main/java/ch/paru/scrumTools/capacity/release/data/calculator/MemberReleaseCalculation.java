package ch.paru.scrumTools.capacity.release.data.calculator;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ch.paru.scrumTools.capacity.release.data.CalendarWeek;
import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.data.WeekCapacity;
import ch.paru.scrumTools.capacity.release.utils.CalendarWeekUtil;
import ch.paru.scrumTools.capacity.shared.data.Numbers;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.common.reflection.customs.Customizable;
import ch.paru.scrumTools.backendServer.services.calendar.CalendarService;
import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;
import ch.paru.scrumTools.backendServer.utils.ServerDayUtil;

public class MemberReleaseCalculation implements Customizable {

	private ReleaseData data;
	private CalendarService calendarService;

	public MemberReleaseCalculation(ReleaseData data, CalendarService calendarService) {
		this.data = data;
		this.calendarService = calendarService;
	}

	public void calculateAllCapacities() {
		List<TeamMember> allTeamMembers = data.getAllTeamMembers();
		for (TeamMember teamMember : allTeamMembers) {
			calculateWeekCapacityForMember(teamMember);
			calculateReleaseCapacityForMember(teamMember);
		}
	}

	protected void calculateReleaseCapacityForMember(TeamMember teamMember) {
		Map<CalendarWeek, WeekCapacity> weekCapacities = data.getWeekCapacityForMember(teamMember);
		Set<CalendarWeek> weeks = weekCapacities.keySet();

		int totalDays = 0;
		for (CalendarWeek week : weeks) {
			WeekCapacity weekCapacity = weekCapacities.get(week);
			totalDays = totalDays + weekCapacity.getDaysAvailable();
		}

		Numbers numbers = teamMember.getNumbers();
		numbers.setAvailability(totalDays);

		Double memberCapacityFactor = teamMember.getConfiguration().getCapacity();
		numbers.setFinalCapacity(memberCapacityFactor * totalDays);
	}

	protected void calculateWeekCapacityForMember(TeamMember teamMember) {
		ServerDay pointer = data.getStartDay();
		ServerDay endDay = data.getEndDay();
		while (ServerDayUtil.compare(pointer, endDay) <= 0) {
			boolean isWorkingDay = calendarService.isWorkingDay(pointer);

			if (isWorkingDay && teamMember.isAvailable(pointer)) {
				data.addTeamMemberWorkingDay(CalendarWeekUtil.createCalendarWeek(pointer), teamMember);
			}

			pointer = ServerDayUtil.addDays(pointer, 1);
		}
	}
}
