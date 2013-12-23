package ch.paru.scrumTools.capacity.release.factories;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.shared.factories.TeamFactory;
import ch.paru.scrumTools.capacity.shared.factories.TeamMemberFactory;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class ReleaseDataFactory extends AbstractFactory {

	public ReleaseData createData(TeamFactory teamFactory, TeamMemberFactory teamMemberFactor) {
		Class<? extends ReleaseData> instanceClass = getClassToUse(ReleaseData.class);
		Class<?>[] types = new Class<?>[] { TeamFactory.class, TeamMemberFactory.class };
		Object[] params = new Object[] { teamFactory, teamMemberFactor };
		return getInstance(instanceClass, types, params);
	}
}
