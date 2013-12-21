package ch.paru.scrumTools.capacity.shared.data.calculator;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.capacity.shared.data.Numbers;
import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;

public class TeamCalculation {

	private DataBox data;

	public TeamCalculation(DataBox data) {
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
