package ch.paru.scrumTools.capacity.release.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class ReleaseCapacityConfigurationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ReleaseCapacityConfiguration.init("testconfig.txt");
	}

	@Test
	public void testGetTeams() {
		ReleaseCapacityConfiguration instance = ReleaseCapacityConfiguration.getInstance();
		List<String> teams = instance.getTeams();
		assertNotNull(teams);
		assertEquals(1, teams.size());
		assertTrue(teams.contains("TestTeam"));
	}

}
