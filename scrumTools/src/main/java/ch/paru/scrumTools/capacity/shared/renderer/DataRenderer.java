package ch.paru.scrumTools.capacity.shared.renderer;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.shared.renderer.generator.OutputGenerator;
import ch.paru.scrumTools.capacity.shared.renderer.teamTable.TeamTableContent;

public abstract class DataRenderer<T extends DataBox> {

	public void renderData(T data) {
		TeamTableContent tableContent = getTeamTable();
		OutputGenerator<T> generator = getOutputGenerator();

		StringBuffer sb = new StringBuffer();
		sb.append(generator.getPreTableData(data));
		sb.append(generator.getTeamTableHeader(tableContent.getColumnNames()));

		List<Team> allTeams = data.getAllTeams();
		for (Team team : allTeams) {
			List<TeamMember> allMembers = team.getAllMembers();
			for (TeamMember teamMember : allMembers) {
				sb.append(generator.getTeamTableMemberRow(tableContent.getMemberRowValues(teamMember)));
			}
		}

		sb.append(generator.getPostTableData(data));

		generator.print(sb.toString());
	}

	protected abstract TeamTableContent getTeamTable();

	protected abstract OutputGenerator<T> getOutputGenerator();
}
