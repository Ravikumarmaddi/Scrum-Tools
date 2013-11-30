package ch.paru.scrumTools.common.reflection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;

public class ReflectionUtilTest {

	@Test
	public void testCustomFound() {
		Class<? extends ConfigUser> clazz = ReflectionUtil.getCustomClass(ConfigUser.class);
		assertNotNull(clazz);
		assertEquals(CustomConfigUser.class, clazz);
	}

	@Test
	public void testCustomNotFound() {
		Class<? extends TeamMember> clazz = ReflectionUtil.getCustomClass(TeamMember.class);
		assertNull(clazz);
	}

}
