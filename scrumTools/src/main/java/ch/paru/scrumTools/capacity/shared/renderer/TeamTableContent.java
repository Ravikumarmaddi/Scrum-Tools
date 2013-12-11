package ch.paru.scrumTools.capacity.shared.renderer;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.TeamMember;

public interface TeamTableContent {

	public List<String> getMemberRowValues(TeamMember member);

	public List<String> getColumnNames();

}
