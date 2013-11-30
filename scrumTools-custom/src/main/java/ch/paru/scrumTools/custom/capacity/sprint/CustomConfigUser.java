package ch.paru.scrumTools.custom.capacity.sprint;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.common.reflection.Custom;

@Custom(type = ConfigUser.class)
public class CustomConfigUser extends ConfigUser {

	public static final String ISINTERN = "isIntern";

	private Boolean isIntern;

	public CustomConfigUser(String mailAddress) {
		super(mailAddress);
	}

	public Boolean getIsIntern() {
		return isIntern;
	}

	public void setIsIntern(Boolean isIntern) {
		this.isIntern = isIntern;
	}
}
