package ch.paru.scrumTools.capacity.sprint.custom.configuration;

import ch.paru.scrumTools.common.capacity.ToolException;
import ch.paru.scrumTools.common.capacity.configuration.CapacityConfiguration;
import ch.paru.scrumTools.common.capacity.configuration.ConfigUserFactory;

public class CustomSprintCapacityConfiguration extends CapacityConfiguration {

	private static CustomSprintCapacityConfiguration instance;

	protected CustomSprintCapacityConfiguration(String fileName) {
		super(fileName);
	}

	public static void init(String fileName) {
		CapacityConfiguration.init(fileName);
		instance = new CustomSprintCapacityConfiguration(fileName);
	}

	public static CustomSprintCapacityConfiguration getInstance() {
		if (instance == null) {
			throw new ToolException("custom config instance has not been intialized", null);
		}
		return instance;
	}

	@Override
	protected ConfigUserFactory getUserFactory() {
		return new CustomConfigUserFactory();
	}
}
