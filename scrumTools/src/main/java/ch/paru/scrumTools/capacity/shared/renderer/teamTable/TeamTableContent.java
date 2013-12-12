package ch.paru.scrumTools.capacity.shared.renderer.teamTable;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.TeamMember;

public interface TeamTableContent {

	public List<String> getMemberRowValues(TeamMember member);

	public List<String> getColumnNames();

}
