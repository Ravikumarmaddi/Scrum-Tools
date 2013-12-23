package ch.paru.scrumTools.custom.capacity.release;

import java.util.List;
import java.util.Map;

import ch.paru.scrumTools.capacity.release.data.CalendarWeek;
import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.data.WeekCapacity;
import ch.paru.scrumTools.capacity.release.renderer.teamTable.ReleaseTeamTableContent;
import ch.paru.scrumTools.capacity.release.utils.CalendarWeekUtil;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.common.reflection.customs.Custom;
import ch.paru.scrumTools.custom.capacity.shared.CustomConfigUser;

@Custom(type = ReleaseTeamTableContent.class)
public class CustomReleaseTeamTableContent extends ReleaseTeamTableContent {

	public CustomReleaseTeamTableContent(ReleaseData data) {
		super(data);
	}

	@Override
	public List<String> getColumnNames() {
		List<String> columnNames = super.getColumnNames();

		columnNames.add(2, "IS INTERNAL");

		List<CalendarWeek> weeks = getWeeks();
		for (CalendarWeek week : weeks) {
			columnNames.add(CalendarWeekUtil.getDisplayText(week));
		}

		return columnNames;
	}

	@Override
	public List<String> getMemberRowValues(TeamMember member) {
		List<String> memberRowValues = super.getMemberRowValues(member);

		CustomConfigUser user = (CustomConfigUser) member.getConfiguration();
		memberRowValues.add(2, Boolean.toString(user.getIsIntern()));

		List<CalendarWeek> weeks = getWeeks();
		Map<CalendarWeek, WeekCapacity> weekCapacityForMember = getData().getWeekCapacityForMember(member);
		for (CalendarWeek week : weeks) {
			WeekCapacity weekCapacity = weekCapacityForMember.get(week);
			memberRowValues.add(weekCapacity == null ? "" : Integer.toString(weekCapacity.getDaysAvailable()));
		}

		return memberRowValues;
	}

	private List<CalendarWeek> getWeeks() {
		return getData().getCalendarWeeks();
	}
}
