package ch.paru.scrumTools.capacity.release.renderer.teamTable;

import java.util.List;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.data.Numbers;
import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.shared.renderer.teamTable.TeamTableContent;
import ch.paru.scrumTools.common.formatting.RoundingUtil;
import ch.paru.scrumTools.common.reflection.customs.Customizable;

import com.google.common.collect.Lists;

public class ReleaseTeamTableContent implements TeamTableContent, Customizable {

	private ReleaseData data;

	public ReleaseTeamTableContent(ReleaseData data) {
		this.data = data;
	}

	protected ReleaseData getData() {
		return data;
	}

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
	public List<String> getMemberRowValues(Team team, TeamMember member) {
		ConfigUser configuration = member.getConfiguration();
		Numbers numbers = member.getNumbers();

		List<String> values = Lists.newArrayList();
		values.add(team.getName());
		values.add(configuration.getName());
		values.add(configuration.getRole().getName());
		values.add(RoundingUtil.round(numbers.getAvailability()).toString());
		values.add(RoundingUtil.round(numbers.getFinalCapacity()).toString());
		return values;
	}
}
