package ch.paru.scrumTools.custom.capacity.sprint;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.Numbers;
import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.renderer.generator.SprintOutputGenerator;
import ch.paru.scrumTools.capacity.sprint.renderer.generator.XlsSprintOutputGenerator;
import ch.paru.scrumTools.common.formatting.RoundingUtil;
import ch.paru.scrumTools.common.reflection.customs.Custom;
import ch.paru.scrumTools.backendServer.utils.ServerDayUtil;

@Custom(type = SprintOutputGenerator.class)
public class CustomSprintOutputGenerator extends XlsSprintOutputGenerator {

	@Override
	public String getPreTableData(SprintData data) {
		StringBuffer sb = new StringBuffer();
		sb.append("Sprint Capacity: ");
		sb.append(ServerDayUtil.getDisplayText(data.getStartDay()));
		sb.append(" - ");
		sb.append(ServerDayUtil.getDisplayText(data.getEndDay()));
		sb.append(NEWLINE);
		sb.append(NEWLINE);
		return sb.toString();
	}

	@Override
	public String getPostTableData(SprintData data) {
		StringBuffer sb = new StringBuffer();
		sb.append(NEWLINE);
		sb.append(NEWLINE);

		sb.append(getDetailHeader());

		List<Team> allTeams = data.getAllTeams();
		for (Team team : allTeams) {
			sb.append(getTeamDetailLine(team));
		}

		return sb.toString();
	}

	private String getTeamDetailLine(Team team) {
		Numbers numbers = team.getNumbers();

		StringBuffer sb = new StringBuffer();
		sb.append(team.getName());
		sb.append(TAB);
		sb.append(RoundingUtil.roundFullUp(numbers.getAvailability()));
		sb.append(TAB);
		sb.append(RoundingUtil.roundFullUp(numbers.getRawCapacity()));
		sb.append(TAB);
		sb.append(RoundingUtil.roundFullUp(numbers.getFinalCapacity()));
		sb.append(NEWLINE);
		return sb.toString();
	}

	private String getDetailHeader() {
		StringBuffer sb = new StringBuffer();
		sb.append("Team name");
		sb.append(TAB);
		sb.append("Team Availability");
		sb.append(TAB);
		sb.append("Team Raw Capacity");
		sb.append(TAB);
		sb.append("Team Final Capacity");
		sb.append(NEWLINE);
		return sb.toString();
	}
}
