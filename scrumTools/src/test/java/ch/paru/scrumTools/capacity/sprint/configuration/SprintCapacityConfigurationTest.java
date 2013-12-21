package ch.paru.scrumTools.capacity.sprint.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class SprintCapacityConfigurationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SprintCapacityConfiguration.init("testconfig.txt");
	}

	@Test
	public void testGetTeams() {
		SprintCapacityConfiguration instance = SprintCapacityConfiguration.getInstance();
		List<String> teams = instance.getTeams();
		assertNotNull(teams);
		assertEquals(1, teams.size());
		assertTrue(teams.contains("TestTeam"));
	}

	@Test
	public void testGetConstantValue() {
		SprintCapacityConfiguration instance = SprintCapacityConfiguration.getInstance();
		Double value = instance.getConstantHourValue("testValue");
		assertNotNull(value);
		assertEquals(3, value.doubleValue(), 0);
	}
}
