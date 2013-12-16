package ch.paru.scrumTools.capacity.sprint.configuration;

import java.util.Arrays;
import java.util.List;

import ch.paru.scrumTools.capacity.shared.configuration.CapacityConfiguration;
import ch.paru.scrumTools.common.exception.ToolException;

public class SprintCapacityConfiguration extends CapacityConfiguration {

	private static final String SECTION = "SPRINTCAPACITY-CONFIG";
	private static final String TEAMS = "teams";
	private static final String CONSTANT_HOUR_PREFIX = "constant_hours_";

	private static SprintCapacityConfiguration instance;

	protected SprintCapacityConfiguration(String fileName) {
		super(fileName);
		instance = this;
	}

	public static SprintCapacityConfiguration getInstance() {
		if (instance == null) {
			throw new ToolException("config instance has not been intialized", null);
		}
		return instance;
	}

	public static void init(String fileName) {
		new SprintCapacityConfiguration(fileName);
	}

	public List<String> getTeams() {
		String teams = getStringInSection(SECTION, TEAMS);
		return Arrays.asList(teams.split(","));
	}

	public Double getConstantHourValue(String key) {
		String value = getStringInSection(SECTION, CONSTANT_HOUR_PREFIX + key);
		return Double.valueOf(value);
	}

}
