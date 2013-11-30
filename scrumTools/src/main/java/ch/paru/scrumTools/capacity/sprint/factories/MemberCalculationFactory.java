package ch.paru.scrumTools.capacity.sprint.factories;

import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.MemberCalculation;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class MemberCalculationFactory extends AbstractFactory {

	public MemberCalculation createCalculator(SprintData data) {
		Class<? extends MemberCalculation> instanceClass = getClassToUse(MemberCalculation.class);
		return getInstance(instanceClass, data);
	}
}
