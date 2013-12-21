package ch.paru.scrumTools.capacity.sprint.data.calculator;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigRole;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.data.Numbers;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.SprintDayType;
import ch.paru.scrumTools.common.reflection.customs.Customizable;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;

public class MemberSprintCalculation implements Customizable {

	private SprintData data;
	private ConstantHourManager hourManager;
	private RoleDetailSprintCapacityCalculator roleDetailCapacityCalculator;

	public MemberSprintCalculation(SprintData data, ConstantHourManager hourManager,
			RoleDetailSprintCapacityCalculator roleDetailCapacityCalculator) {
		this.data = data;
		this.hourManager = hourManager;
		this.roleDetailCapacityCalculator = roleDetailCapacityCalculator;
	}

	public void calculateAllCapacities() {
		List<TeamMember> allTeamMembers = data.getAllTeamMembers();
		for (TeamMember teamMember : allTeamMembers) {
			calculateCapacityForMember(teamMember);
		}
	}

	protected void calculateCapacityForMember(TeamMember teamMember) {
		List<ServerDay> workingDays = data.getAllWorkingDays();
		double rawCapacitySum = 0;
		double finalCapacitySum = 0;
		double availabilitySum = 0;

		for (ServerDay day : workingDays) {
			SprintDayType dayType = data.getDayType(day);

			if (teamMember.isAvailable(day)) {
				double dayAvailability = 0;

				switch (dayType) {
				case DAILY_BUSINESS:
					dayAvailability = hourManager.getHoursPerDay();
					break;

				case SPRINT_START:
					dayAvailability = hourManager.getHoursPerDay() - hourManager.getHoursForSprintStart();
					break;

				case SPRINT_END:
					dayAvailability = hourManager.getHoursPerDay() - hourManager.getHoursForSprintEnd();
					break;
				}

				ConfigUser userConfiguration = teamMember.getConfiguration();

				Double memberFactor = userConfiguration.getCapacity();
				availabilitySum = availabilitySum + dayAvailability * memberFactor;

				ConfigRole role = userConfiguration.getRole();
				double dayCapacity = roleDetailCapacityCalculator.getReducedCapacity(role, memberFactor,
						dayAvailability);
				double hourFactor = hourManager.getWorkingHoursPerDay() / hourManager.getHoursPerDay();
				double dayWorkingCapacity = dayCapacity * hourFactor;

				rawCapacitySum = rawCapacitySum + dayCapacity;
				finalCapacitySum = finalCapacitySum + dayWorkingCapacity;
			}
		}

		Numbers numbers = teamMember.getNumbers();
		numbers.setAvailability(availabilitySum);
		numbers.setRawCapacity(rawCapacitySum);
		numbers.setFinalCapacity(finalCapacitySum);
	}
}
