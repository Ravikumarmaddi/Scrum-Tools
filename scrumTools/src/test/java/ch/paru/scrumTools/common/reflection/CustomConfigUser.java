package ch.paru.scrumTools.common.reflection;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;

@Custom
public class CustomConfigUser extends ConfigUser {

	public CustomConfigUser(String mailAddress) {
		super(mailAddress);
	}

}
