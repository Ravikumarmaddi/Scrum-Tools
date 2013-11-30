package ch.paru.scrumTools.capacity.sprint.data.calculator;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.SprintDayType;
import ch.paru.scrumTools.common.reflection.Customizable;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;

public class MemberCalculation implements Customizable {

	private SprintData data;
	private ConstantHourManager hourManager;

	public MemberCalculation(SprintData data, ConstantHourManager hourManager) {
		this.data = data;
		this.hourManager = hourManager;
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
					dayCapacity = hourManager.getHoursPerDay();
					break;

				case SPRINT_START:
					dayCapacity = hourManager.getHoursPerDay() - hourManager.getHoursForSprintStart();
					break;

				case SPRINT_END:
					dayCapacity = hourManager.getHoursPerDay() - hourManager.getHoursForSprintEnd();
					break;
				}

				capacitySum = capacitySum + dayCapacity;
			}
		}

		teamMember.setCapacity(capacitySum);
	}

}
