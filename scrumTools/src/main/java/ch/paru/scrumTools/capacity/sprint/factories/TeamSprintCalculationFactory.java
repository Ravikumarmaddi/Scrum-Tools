package ch.paru.scrumTools.capacity.sprint.factories;

import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.TeamSprintCalculation;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class TeamSprintCalculationFactory extends AbstractFactory {

	public TeamSprintCalculation createCalculator(SprintData data) {
		Class<? extends TeamSprintCalculation> instanceClass = getClassToUse(TeamSprintCalculation.class);
		return getInstance(instanceClass, data);
	}
}
