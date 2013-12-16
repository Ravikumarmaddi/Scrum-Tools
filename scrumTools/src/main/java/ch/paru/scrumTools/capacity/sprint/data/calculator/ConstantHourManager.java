package ch.paru.scrumTools.capacity.sprint.data.calculator;

import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;

public class ConstantHourManager {

	static final String SPRINT_START = "sprintStart";
	static final String SPRINT_FINISH = "sprintFinish";
	static final String HOURS_PER_DAY = "perDay";
	static final String WORKINGHOURS_PER_DAY = "perWorkingDay";

	private SprintCapacityConfiguration config;

	public ConstantHourManager(SprintCapacityConfiguration config) {
		this.config = config;

	}

	public double getHoursForSprintStart() {
		return config.getConstantHourValue(SPRINT_START);
	}

	public double getHoursForSprintEnd() {
		return config.getConstantHourValue(SPRINT_FINISH);
	}

	public double getHoursPerDay() {
		return config.getConstantHourValue(HOURS_PER_DAY);
	}

	public double getWorkingHoursPerDay() {
		return config.getConstantHourValue(WORKINGHOURS_PER_DAY);
	}
}
