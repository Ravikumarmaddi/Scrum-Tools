package ch.paru.scrumTools.custom.capacity.release;

import java.util.List;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.data.calculator.TeamReleaseCalculation;
import ch.paru.scrumTools.capacity.shared.data.Numbers;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.common.reflection.customs.Custom;
import ch.paru.scrumTools.custom.capacity.shared.CustomConfigUser;

@Custom(type = TeamReleaseCalculation.class)
public class CustomTeamReleaseCalculation extends TeamReleaseCalculation {

	private CustomReleaseData data;

	public CustomTeamReleaseCalculation(ReleaseData data) {
		super(data);
		this.data = (CustomReleaseData) data;
	}

	@Override
	public void calculateAllCapacities() {
		TeamValues teamValues = data.getTeamValues();

		// Team internal / external
		List<TeamMember> members = data.getAllTeamMembers();
		for (TeamMember member : members) {
			Numbers memberValues = member.getNumbers();
			teamValues.addTeamTotal(memberValues.getFinalCapacity());
			CustomConfigUser userConfig = (CustomConfigUser) member.getConfiguration();
			if (userConfig.getIsIntern()) {
				teamValues.addTeamTotalInternal(memberValues.getFinalCapacity());
			}
			else {
				teamValues.addTeamTotalExternal(memberValues.getFinalCapacity());
			}
		}

		CustomReleaseCapacityConfiguration config = CustomReleaseCapacityConfiguration.getInstance();

		// Administration
		Double factorAdministration = config.getAdministrationFactor();
		teamValues.setAdministrationEffort(factorAdministration * teamValues.getTeamTotalInternal());

		// Education
		Double factorEducation = config.getEducationFactor();
		teamValues.setEducationEffort(factorEducation * teamValues.getTeamTotal());

		// 3rd Level Support
		Double factor3rdLevelSupport = config.get3rdLevelSupportFactor();
		teamValues.set3rdLevelSupportEffort(factor3rdLevelSupport * teamValues.getTeamTotal());

		// Feasibility Support Next Release
		Double factorFeasibilitySupport = config.getFeasabilityNextReleaseFactor();
		teamValues.setFeasibilitySupportEffort(factorFeasibilitySupport * teamValues.getTeamTotal());

		// Total
		double efforts = teamValues.getEffortAdministration() + teamValues.getEffortEducation()
				+ teamValues.getEffort3rdLevelSupport() + teamValues.getEffortFeasibilitySupport();
		double total = teamValues.getTeamTotal() - efforts;
		teamValues.setTotal(total);
	}
}
