package ch.paru.scrumTools.capacity.sprint.data.calculator;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.Numbers;
import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.common.reflection.customs.Customizable;

public class TeamCalculation implements Customizable {

	private SprintData data;

	public TeamCalculation(SprintData data) {
		this.data = data;
	}

	public void calculateAllCapacities() {
		List<Team> allTeams = data.getAllTeams();
		for (Team team : allTeams) {
			Numbers teamNumbers = team.getNumbers();
			List<TeamMember> allMembers = team.getAllMembers();
			for (TeamMember teamMember : allMembers) {
				Numbers memberNumbers = teamMember.getNumbers();
				teamNumbers.addAvailability(memberNumbers.getAvailability());
				teamNumbers.addFinalCapacity(memberNumbers.getFinalCapacity());
				teamNumbers.addRawCapacity(memberNumbers.getRawCapacity());
			}
		}
	}
}
