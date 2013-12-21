package ch.paru.scrumTools.capacity.sprint.data.calculator;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigRole;

public class RoleDetailSprintCapacityCalculator {

	public double getReducedCapacity(ConfigRole role, double memberFactor, double rawCapacity) {
		switch (role.getCapacityType()) {
		case ABSOLUTE:
			double absoluteValue = rawCapacity * (memberFactor - (1 - role.getCapacity()));
			return checkPositiveValue(absoluteValue);

		case FACTOR:
			double factoredValue = rawCapacity * memberFactor * role.getCapacity();
			return checkPositiveValue(factoredValue);
		}
		return 0;
	}

	private double checkPositiveValue(double value) {
		if (value < 0) {
			return 0;
		}
		return value;
	}
}
