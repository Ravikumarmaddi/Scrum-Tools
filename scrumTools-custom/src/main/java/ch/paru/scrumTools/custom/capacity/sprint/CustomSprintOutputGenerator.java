package ch.paru.scrumTools.custom.capacity.sprint;

import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.renderer.generator.SprintOutputGenerator;
import ch.paru.scrumTools.capacity.sprint.renderer.generator.XlsSprintOutputGenerator;
import ch.paru.scrumTools.common.reflection.customs.Custom;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

@Custom(type = SprintOutputGenerator.class)
public class CustomSprintOutputGenerator extends XlsSprintOutputGenerator {

	@Override
	public String getPreTableData(SprintData data) {
		StringBuffer sb = new StringBuffer();
		sb.append("Sprint Capacity: ");
		sb.append(ServerDayUtil.getDisplayText(data.getStartDay()));
		sb.append(" - ");
		sb.append(ServerDayUtil.getDisplayText(data.getEndDay()));
		sb.append(NEWLINE);
		sb.append(NEWLINE);
		return sb.toString();
	}
}
