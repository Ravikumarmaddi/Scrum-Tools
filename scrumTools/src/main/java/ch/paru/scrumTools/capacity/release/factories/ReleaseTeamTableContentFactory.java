package ch.paru.scrumTools.capacity.release.factories;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.renderer.teamTable.ReleaseTeamTableContent;
import ch.paru.scrumTools.capacity.shared.renderer.teamTable.TeamTableContent;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class ReleaseTeamTableContentFactory extends AbstractFactory {

	public TeamTableContent createTable(ReleaseData data) {
		Class<? extends ReleaseTeamTableContent> instanceClass = getClassToUse(ReleaseTeamTableContent.class);
		return getInstance(instanceClass, data);
	}
}
