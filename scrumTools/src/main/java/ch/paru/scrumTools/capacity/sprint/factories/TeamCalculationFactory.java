package ch.paru.scrumTools.capacity.sprint.factories;

import java.lang.reflect.Constructor;

import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.TeamCalculation;
import ch.paru.scrumTools.common.exception.ToolException;
import ch.paru.scrumTools.common.reflection.ReflectionUtil;

public class TeamCalculationFactory {

	public TeamCalculation createCalculator(SprintData data) {
		Class<? extends TeamCalculation> clazz = ReflectionUtil.getCustomClass(TeamCalculation.class);
		if (clazz == null) {
			clazz = TeamCalculation.class;
		}

		try {
			Constructor<? extends TeamCalculation> constructor = clazz.getConstructor(SprintData.class);
			return constructor.newInstance(data);
		}
		catch (Exception e) {
			throw new ToolException("instanciation of class failed", e);
		}
	}
}
