package ch.paru.scrumTools.custom.capacity.release;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.shared.factories.TeamFactory;
import ch.paru.scrumTools.capacity.shared.factories.TeamMemberFactory;
import ch.paru.scrumTools.common.reflection.customs.Custom;

@Custom(type = ReleaseData.class)
public class CustomReleaseData extends ReleaseData {

	private TeamValues teamValues;

	public CustomReleaseData(TeamFactory teamFactory, TeamMemberFactory teamMemberFactory) {
		super(teamFactory, teamMemberFactory);
		teamValues = new TeamValues();
	}

	public TeamValues getTeamValues() {
		return teamValues;
	}

	public void setTeamValues(TeamValues teamValues) {
		this.teamValues = teamValues;
	}

}
