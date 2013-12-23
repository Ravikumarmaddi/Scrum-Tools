package ch.paru.scrumTools.capacity.release.configuration;

import java.util.Arrays;
import java.util.List;

import ch.paru.scrumTools.capacity.shared.configuration.CapacityConfiguration;
import ch.paru.scrumTools.common.exception.ToolException;

public class ReleaseCapacityConfiguration extends CapacityConfiguration {

	protected static final String SECTION = "RELEASECAPACITY-CONFIG";
	private static final String TEAMS = "teams";

	private static ReleaseCapacityConfiguration instance;

	protected ReleaseCapacityConfiguration(String fileName) {
		super(fileName);
		instance = this;
	}

	public static ReleaseCapacityConfiguration getInstance() {
		if (instance == null) {
			throw new ToolException("config instance has not been intialized", null);
		}
		return instance;
	}

	public static void init(String fileName) {
		new ReleaseCapacityConfiguration(fileName);
	}

	public List<String> getTeams() {
		String teams = getStringInSection(SECTION, TEAMS);
		return Arrays.asList(teams.split(","));
	}
}
