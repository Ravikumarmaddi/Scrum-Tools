package ch.paru.scrumTools.capacity.sprint.factories;

import ch.paru.scrumTools.capacity.shared.renderer.TeamTableContent;
import ch.paru.scrumTools.capacity.sprint.renderer.SprintTeamTableContent;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class SprintTeamTableFactory extends AbstractFactory {

	public TeamTableContent createTable() {
		Class<? extends SprintTeamTableContent> instanceClass = getClassToUse(SprintTeamTableContent.class);
		return getInstance(instanceClass);
	}
}
