package ch.paru.scrumTools.custom.capacity.release;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.renderer.generator.ReleaseOutputGenerator;
import ch.paru.scrumTools.capacity.release.renderer.generator.XlsReleaseOutputGenerator;
import ch.paru.scrumTools.common.formatting.RoundingUtil;
import ch.paru.scrumTools.common.reflection.customs.Custom;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

@Custom(type = ReleaseOutputGenerator.class)
public class CustomReleaseOutputGenerator extends XlsReleaseOutputGenerator {

	@Override
	public String getPreTableData(ReleaseData data) {
		StringBuffer sb = new StringBuffer();
		sb.append("Release Capacity: ");
		sb.append(ServerDayUtil.getDisplayText(data.getStartDay()));
		sb.append(" - ");
		sb.append(ServerDayUtil.getDisplayText(data.getEndDay()));
		sb.append(NEWLINE);
		sb.append(NEWLINE);
		return sb.toString();
	}

	@Override
	public String getPostTableData(ReleaseData data) {
		StringBuffer sb = new StringBuffer();
		sb.append(NEWLINE);
		sb.append(NEWLINE);

		sb.append(getDetails((CustomReleaseData) data));

		return sb.toString();
	}

	private String getDetails(CustomReleaseData data) {
		CustomReleaseCapacityConfiguration config = CustomReleaseCapacityConfiguration.getInstance();
		TeamValues teamValues = data.getTeamValues();
		StringBuffer sb = new StringBuffer();

		sb.append("TEAM TOTAL");
		sb.append(TAB);
		sb.append(TAB);
		sb.append(RoundingUtil.round(teamValues.getTeamTotal()));
		sb.append(NEWLINE);

		sb.append("TEAM TOTAL INTERNAL");
		sb.append(TAB);
		sb.append(TAB);
		sb.append(RoundingUtil.round(teamValues.getTeamTotalInternal()));
		sb.append(NEWLINE);

		sb.append("TEAM TOTAL EXTERNAL");
		sb.append(TAB);
		sb.append(TAB);
		sb.append(RoundingUtil.round(teamValues.getTeamTotalExternal()));
		sb.append(NEWLINE);

		sb.append("Administration");
		sb.append(TAB);
		sb.append("TEAM TOTAL INTERNAL * ");
		sb.append(RoundingUtil.round(config.getAdministrationFactor()));
		sb.append(TAB);
		sb.append(RoundingUtil.round(teamValues.getEffortAdministration()));
		sb.append(NEWLINE);

		sb.append("Education");
		sb.append(TAB);
		sb.append("TEAM TOTAL * ");
		sb.append(RoundingUtil.round(config.getEducationFactor()));
		sb.append(TAB);
		sb.append(RoundingUtil.round(teamValues.getEffortEducation()));
		sb.append(NEWLINE);

		sb.append("3rd Level Support");
		sb.append(TAB);
		sb.append("TEAM TOTAL * ");
		sb.append(RoundingUtil.round(config.get3rdLevelSupportFactor()));
		sb.append(TAB);
		sb.append(RoundingUtil.round(teamValues.getEffort3rdLevelSupport()));
		sb.append(NEWLINE);

		sb.append("Feasibility Support");
		sb.append(TAB);
		sb.append("TEAM TOTAL * ");
		sb.append(RoundingUtil.round(config.getFeasabilityNextReleaseFactor()));
		sb.append(TAB);
		sb.append(RoundingUtil.round(teamValues.getEffortFeasibilitySupport()));
		sb.append(NEWLINE);

		sb.append("TOTAL NEO CAPACITY");
		sb.append(TAB);
		sb.append("TEAM TOTAL - (Administration + Education + 3rd Level + Feasibility)");
		sb.append(TAB);
		sb.append(RoundingUtil.round(teamValues.getTotal()));
		sb.append(NEWLINE);

		return sb.toString();
	}

}
