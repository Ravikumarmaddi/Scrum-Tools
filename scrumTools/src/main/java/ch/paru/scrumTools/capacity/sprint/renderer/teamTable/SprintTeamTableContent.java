package ch.paru.scrumTools.capacity.sprint.renderer.teamTable;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.shared.renderer.teamTable.TeamTableContent;
import ch.paru.scrumTools.common.reflection.customs.Customizable;

import com.google.common.collect.Lists;

public class SprintTeamTableContent implements TeamTableContent, Customizable {

	@Override
	public List<String> getColumnNames() {
		List<String> values = Lists.newArrayList();
		values.add("TEAM");
		values.add("MEMBER");
		values.add("ROLE");
		values.add("AVAILABILITY");
		values.add("CAPACITY");
		return values;
	}

	@Override
	public List<String> getMemberRowValues(TeamMember member) {
		List<String> values = Lists.newArrayList();
		values.add(member.getTeam().getName());
		values.add(member.getConfiguration().getName());
		values.add(member.getConfiguration().getRole().getName());
		values.add(Double.toString(member.getAvailability()));
		values.add(Double.toString(member.getCapacity()));
		return values;
	}
}
