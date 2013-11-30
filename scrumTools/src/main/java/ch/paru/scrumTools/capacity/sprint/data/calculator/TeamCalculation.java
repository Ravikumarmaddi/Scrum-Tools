package ch.paru.scrumTools.capacity.sprint.data.calculator;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;

public class TeamCalculation {

	private SprintData data;

	public TeamCalculation(SprintData data) {
		this.data = data;
	}

	public void calculateAllCapacities() {
		List<Team> allTeams = data.getAllTeams();
		for (Team team : allTeams) {
			List<TeamMember> allMembers = team.getAllMembers();
			for (TeamMember teamMember : allMembers) {
				team.addCapacity(teamMember.getCapacity());
			}
		}
	}
}
