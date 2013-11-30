package ch.paru.scrumTools.capacity.sprint.factories;

import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.TeamCalculation;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class TeamCalculationFactory extends AbstractFactory {

	public TeamCalculation createCalculator(SprintData data) {
		Class<? extends TeamCalculation> instanceClass = getClassToUse(TeamCalculation.class);
		return getInstance(instanceClass, data);
	}
}
