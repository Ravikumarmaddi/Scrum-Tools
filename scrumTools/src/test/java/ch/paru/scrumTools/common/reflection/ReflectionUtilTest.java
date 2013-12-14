package ch.paru.scrumTools.common.reflection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.data.Team;

public class ReflectionUtilTest {

	@Test
	public void testCustomFound() {
		Class<? extends Team> clazz = ReflectionUtil.getCustomClass(Team.class);
		assertNotNull(clazz);
		assertEquals(CustomTeam.class, clazz);
	}

	@Test
	public void testCustomNotFound() {
		Class<? extends ConfigUser> clazz = ReflectionUtil.getCustomClass(ConfigUser.class);
		assertNull(clazz);
	}

}
