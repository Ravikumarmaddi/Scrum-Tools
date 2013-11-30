package ch.paru.scrumTools.common.reflection;

import ch.paru.scrumTools.capacity.shared.data.Team;

@Custom(type = Team.class)
public class CustomTeam extends Team {

	public CustomTeam(String mailAddress) {
		super(mailAddress);
	}

}
