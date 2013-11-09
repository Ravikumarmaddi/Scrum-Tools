package ch.paru.scrumTools.capacity.sprint.custom.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.paru.scrumTools.common.capacity.configuration.ConfigUser;

public class CustomSprintCapacityConfigurationTest {

	@Before
	public void init() {
		CustomSprintCapacityConfiguration.init("testconfig.txt");
	}

	@Test
	public void testUserConfig() {
		CustomSprintCapacityConfiguration configuration = CustomSprintCapacityConfiguration.getInstance();
		String mailAddress = "test@domain.com";
		ConfigUser user = configuration.getUser(mailAddress);
		Assert.assertEquals(mailAddress, user.getMailAddress());
		Assert.assertFalse(((CustomConfigUser) user).getIsIntern());
	}

}
