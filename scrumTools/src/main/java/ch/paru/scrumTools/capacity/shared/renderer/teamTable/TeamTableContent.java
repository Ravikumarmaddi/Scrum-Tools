package ch.paru.scrumTools.capacity.shared.renderer.teamTable;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;

public interface TeamTableContent {

	public List<String> getMemberRowValues(Team team, TeamMember member);

	public List<String> getColumnNames();

}
