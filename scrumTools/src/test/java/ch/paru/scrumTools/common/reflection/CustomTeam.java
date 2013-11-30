package ch.paru.scrumTools.common.reflection;

import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.common.reflection.customs.Custom;

@Custom(type = Team.class)
public class CustomTeam extends Team {

	public CustomTeam(String mailAddress) {
		super(mailAddress);
	}

}
