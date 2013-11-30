package ch.paru.scrumTools.capacity.sprint.factories;

import java.lang.reflect.Constructor;

import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.MemberCalculation;
import ch.paru.scrumTools.common.exception.ToolException;
import ch.paru.scrumTools.common.reflection.ReflectionUtil;

public class MemberCalculationFactory {

	public MemberCalculation createCalculator(SprintData data) {
		Class<? extends MemberCalculation> clazz = ReflectionUtil.getCustomClass(MemberCalculation.class);
		if (clazz == null) {
			clazz = MemberCalculation.class;
		}

		try {
			Constructor<? extends MemberCalculation> constructor = clazz.getConstructor(SprintData.class);
			return constructor.newInstance(data);
		}
		catch (Exception e) {
			throw new ToolException("instanciation of class failed", e);
		}
	}
}
