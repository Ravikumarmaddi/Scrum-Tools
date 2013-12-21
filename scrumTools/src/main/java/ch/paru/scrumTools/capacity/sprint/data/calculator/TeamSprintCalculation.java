package ch.paru.scrumTools.capacity.sprint.data.calculator;

import ch.paru.scrumTools.capacity.shared.data.calculator.TeamCalculation;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.common.reflection.customs.Customizable;

public class TeamSprintCalculation extends TeamCalculation implements Customizable {

	public TeamSprintCalculation(SprintData data) {
		super(data);
	}

}
