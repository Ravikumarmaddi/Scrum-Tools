package ch.paru.scrumTools.capacity.shared.configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CapacityConfigurationTest {

	@Before
	public void init() {
		CapacityConfiguration.init("testconfig.txt");
	}

	@Test
	public void testUserConfig() {
		CapacityConfiguration configuration = CapacityConfiguration.getInstance();
		String mailAddress = "test@domain.com";
		ConfigUser user = configuration.getUser(mailAddress);
		Assert.assertEquals(mailAddress, user.getMailAddress());
	}

}
