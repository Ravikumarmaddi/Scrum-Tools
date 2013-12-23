package ch.paru.scrumTools.custom.capacity.sprint;

import ch.paru.scrumTools.capacity.shared.factories.ConfigUserFactory;
import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.custom.capacity.shared.CustomConfigUserFactory;

public class CustomSprintCapacityConfiguration extends SprintCapacityConfiguration {

	protected CustomSprintCapacityConfiguration(String fileName) {
		super(fileName);
	}

	public static void init(String fileName) {
		new CustomSprintCapacityConfiguration(fileName);
	}

	public static CustomSprintCapacityConfiguration getInstance() {
		return (CustomSprintCapacityConfiguration) SprintCapacityConfiguration.getInstance();
	}

	@Override
	protected ConfigUserFactory getUserFactory() {
		return new CustomConfigUserFactory();
	}
}
