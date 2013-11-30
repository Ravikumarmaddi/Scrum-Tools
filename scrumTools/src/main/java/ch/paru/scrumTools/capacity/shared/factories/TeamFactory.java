package ch.paru.scrumTools.capacity.shared.factories;

import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class TeamFactory extends AbstractFactory {

	public Team createTeam(String teamName) {
		Class<? extends Team> instanceClass = getClassToUse(Team.class);
		return getInstance(instanceClass, teamName);
	}
}
