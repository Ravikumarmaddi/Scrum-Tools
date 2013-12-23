package ch.paru.scrumTools.custom.capacity.sprint;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.custom.capacity.shared.CustomConfigUser;

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
