package ch.paru.scrumTools.capacity.sprint.custom.configuration;

import ch.paru.scrumTools.common.capacity.configuration.ConfigUser;

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
