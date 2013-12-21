package ch.paru.scrumTools.capacity.sprint.factories;

import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.ConstantHourManager;
import ch.paru.scrumTools.capacity.sprint.data.calculator.MemberSprintCalculation;
import ch.paru.scrumTools.capacity.sprint.data.calculator.RoleDetailSprintCapacityCalculator;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;

@CustomFactory
public class MemberSprintCalculationFactory extends AbstractFactory {

	public MemberSprintCalculation createCalculator(SprintData data, ConstantHourManager hourManager,
			RoleDetailSprintCapacityCalculator roleDetailCapacityCalculator) {
		Class<? extends MemberSprintCalculation> instanceClass = getClassToUse(MemberSprintCalculation.class);
		Class<?>[] types = new Class<?>[] { SprintData.class, ConstantHourManager.class,
				RoleDetailSprintCapacityCalculator.class };
		Object[] params = new Object[] { data, hourManager, roleDetailCapacityCalculator };
		return getInstance(instanceClass, types, params);
	}
}
