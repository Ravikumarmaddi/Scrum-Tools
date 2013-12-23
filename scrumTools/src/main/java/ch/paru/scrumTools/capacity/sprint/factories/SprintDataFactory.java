package ch.paru.scrumTools.capacity.sprint.factories;

import ch.paru.scrumTools.capacity.shared.factories.TeamFactory;
import ch.paru.scrumTools.capacity.shared.factories.TeamMemberFactory;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class SprintDataFactory extends AbstractFactory {

	public SprintData createData(TeamFactory teamFactory, TeamMemberFactory teamMemberFactor) {
		Class<? extends SprintData> instanceClass = getClassToUse(SprintData.class);
		Class<?>[] types = new Class<?>[] { TeamFactory.class, TeamMemberFactory.class };
		Object[] params = new Object[] { teamFactory, teamMemberFactor };
		return getInstance(instanceClass, types, params);
	}
}
