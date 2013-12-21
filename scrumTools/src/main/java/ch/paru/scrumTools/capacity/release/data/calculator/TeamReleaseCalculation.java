package ch.paru.scrumTools.capacity.release.data.calculator;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.shared.data.calculator.TeamCalculation;
import ch.paru.scrumTools.common.reflection.customs.Customizable;

public class TeamReleaseCalculation extends TeamCalculation implements Customizable {

	public TeamReleaseCalculation(ReleaseData data) {
		super(data);
	}

}
