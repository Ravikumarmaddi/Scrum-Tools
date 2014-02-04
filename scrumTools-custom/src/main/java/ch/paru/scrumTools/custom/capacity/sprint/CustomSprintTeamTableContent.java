package ch.paru.scrumTools.custom.capacity.sprint;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.renderer.teamTable.SprintTeamTableContent;
import ch.paru.scrumTools.common.reflection.customs.Custom;
import ch.paru.scrumTools.custom.capacity.shared.CustomConfigUser;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

@Custom(type = SprintTeamTableContent.class)
public class CustomSprintTeamTableContent extends SprintTeamTableContent {

	public CustomSprintTeamTableContent(SprintData data) {
		super(data);
	}

	@Override
	public List<String> getColumnNames() {
		List<String> columnNames = super.getColumnNames();

		columnNames.add(2, "IS INTERNAL");

		List<ServerDay> days = getDays();
		for (ServerDay day : days) {
			columnNames.add(ServerDayUtil.getDisplayText(day));
		}

		return columnNames;
	}

	@Override
	public List<String> getMemberRowValues(Team team, TeamMember member) {
		List<String> memberRowValues = super.getMemberRowValues(team, member);

		CustomConfigUser user = (CustomConfigUser) member.getConfiguration();
		memberRowValues.add(2, Boolean.toString(user.getIsIntern()));

		List<ServerDay> days = getDays();
		for (ServerDay day : days) {
			memberRowValues.add(member.isAvailable(day) ? "X" : "");
		}

		return memberRowValues;
	}

	private List<ServerDay> getDays() {
		return getData().getAllWorkingDays();
	}
}
