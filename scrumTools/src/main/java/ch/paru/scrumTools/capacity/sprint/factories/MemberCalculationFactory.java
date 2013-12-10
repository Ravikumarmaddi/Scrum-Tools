package ch.paru.scrumTools.capacity.sprint.factories;

import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.ConstantHourManager;
import ch.paru.scrumTools.capacity.sprint.data.calculator.MemberCalculation;
import ch.paru.scrumTools.capacity.sprint.data.calculator.RoleDetailCapacityCalculator;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class MemberCalculationFactory extends AbstractFactory {

	public MemberCalculation createCalculator(SprintData data, ConstantHourManager hourManager,
			RoleDetailCapacityCalculator roleDetailCapacityCalculator) {
		Class<? extends MemberCalculation> instanceClass = getClassToUse(MemberCalculation.class);
		Class<?>[] types = new Class<?>[] { SprintData.class, ConstantHourManager.class,
				RoleDetailCapacityCalculator.class };
		Object[] params = new Object[] { data, hourManager, roleDetailCapacityCalculator };
		return getInstance(instanceClass, types, params);
	}
}
