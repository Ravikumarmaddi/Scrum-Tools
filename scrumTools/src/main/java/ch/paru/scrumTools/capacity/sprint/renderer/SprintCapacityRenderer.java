package ch.paru.scrumTools.capacity.sprint.renderer;

import ch.paru.scrumTools.capacity.shared.renderer.DataRenderer;
import ch.paru.scrumTools.capacity.shared.renderer.generator.OutputGenerator;
import ch.paru.scrumTools.capacity.shared.renderer.teamTable.TeamTableContent;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.factories.SprintOutputGeneratorFactory;
import ch.paru.scrumTools.capacity.sprint.factories.SprintTeamTableContentFactory;
import ch.paru.scrumTools.capacity.sprint.renderer.generator.XlsSprintOutputGenerator;
import ch.paru.scrumTools.common.reflection.customs.Customizable;

public class SprintCapacityRenderer extends DataRenderer<SprintData> implements Customizable {

	@Override
	protected TeamTableContent getTeamTable(SprintData data) {
		return new SprintTeamTableContentFactory().createTable(data);
	}

	@Override
	protected OutputGenerator<SprintData> getOutputGenerator() {
		return new SprintOutputGeneratorFactory().createGenerator(XlsSprintOutputGenerator.class);
	}
}