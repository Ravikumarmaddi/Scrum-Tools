package ch.paru.scrumTools.capacity.sprint.data.calculator;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.SprintDayType;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;

public class MemberCalculation {

	private SprintData data;

	public MemberCalculation(SprintData data) {
		this.data = data;
	}

	public void calculateAllCapacities() {
		List<TeamMember> allTeamMembers = data.getAllTeamMembers();
		for (TeamMember teamMember : allTeamMembers) {
			calculateCapacityForMember(teamMember);
		}
	}

	void calculateCapacityForMember(TeamMember teamMember) {
		List<ServerDay> workingDays = data.getAllWorkingDays();
		double capacitySum = 0;

		for (ServerDay day : workingDays) {
			SprintDayType dayType = data.getDayType(day);

			if (teamMember.isAvailable(day)) {
				double dayCapacity = 0;

				switch (dayType) {
				case DAILY_BUSINESS:
					dayCapacity = getHoursPerDay();
					break;

				case SPRINT_START:
					dayCapacity = getHoursPerDay() - getHoursForSprintStart();
					break;

				case SPRINT_END:
					dayCapacity = getHoursPerDay() - getHoursForSprintEnd();
					break;
				}

				capacitySum = capacitySum + dayCapacity;
			}
		}

		teamMember.setCapacity(capacitySum);
	}

	protected double getHoursForSprintStart() {
		return 3.5;
	}

	protected double getHoursForSprintEnd() {
		return 3;
	}

	protected double getHoursPerDay() {
		return 8;
	}
}
