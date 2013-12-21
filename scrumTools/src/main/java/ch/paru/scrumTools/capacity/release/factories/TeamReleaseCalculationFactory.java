package ch.paru.scrumTools.capacity.release.factories;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.data.calculator.TeamReleaseCalculation;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class TeamReleaseCalculationFactory extends AbstractFactory {

	public TeamReleaseCalculation createCalculator(ReleaseData data) {
		Class<? extends TeamReleaseCalculation> instanceClass = getClassToUse(TeamReleaseCalculation.class);
		return getInstance(instanceClass, data);
	}
}
