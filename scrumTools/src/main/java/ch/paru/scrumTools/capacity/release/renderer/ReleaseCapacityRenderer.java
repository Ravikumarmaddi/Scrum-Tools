package ch.paru.scrumTools.capacity.release.renderer;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.factories.ReleaseOutputGeneratorFactory;
import ch.paru.scrumTools.capacity.release.factories.ReleaseTeamTableContentFactory;
import ch.paru.scrumTools.capacity.release.renderer.generator.XlsReleaseOutputGenerator;
import ch.paru.scrumTools.capacity.shared.renderer.DataRenderer;
import ch.paru.scrumTools.capacity.shared.renderer.generator.OutputGenerator;
import ch.paru.scrumTools.capacity.shared.renderer.teamTable.TeamTableContent;
import ch.paru.scrumTools.common.reflection.customs.Customizable;

public class ReleaseCapacityRenderer extends DataRenderer<ReleaseData> implements Customizable {

	@Override
	protected TeamTableContent getTeamTable(ReleaseData data) {
		return new ReleaseTeamTableContentFactory().createTable(data);
	}

	@Override
	protected OutputGenerator<ReleaseData> getOutputGenerator() {
		return new ReleaseOutputGeneratorFactory().createGenerator(XlsReleaseOutputGenerator.class);
	}
}