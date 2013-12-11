package ch.paru.scrumTools.capacity.sprint.renderer;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.shared.renderer.OutputGenerator;
import ch.paru.scrumTools.capacity.shared.renderer.TeamTableContent;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.factories.SprintTeamTableContentFactory;

public class SprintCapacityRenderer {

	public void renderData(SprintData data) {
		TeamTableContent tableContent = new SprintTeamTableContentFactory().createTable();
		OutputGenerator generator = new XlsSprintOutputGenerator();

		StringBuffer sb = new StringBuffer();
		sb.append(generator.getTeamTableHeader(tableContent.getColumnNames()));

		List<Team> allTeams = data.getAllTeams();
		for (Team team : allTeams) {
			List<TeamMember> allMembers = team.getAllMembers();
			for (TeamMember teamMember : allMembers) {
				sb.append(generator.getTeamTableMemberRow(tableContent.getMemberRowValues(teamMember)));
			}
		}

		generator.print(sb.toString());
	}
}