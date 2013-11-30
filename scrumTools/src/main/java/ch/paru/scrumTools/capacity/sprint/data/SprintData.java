package ch.paru.scrumTools.capacity.sprint.data;

import java.util.List;
import java.util.Map;

import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.capacity.shared.factories.TeamFactory;
import ch.paru.scrumTools.capacity.shared.factories.TeamMemberFactory;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SprintData extends DataBox {

	private Map<ServerDay, SprintDayType> dayCategories;
	private List<ServerDay> workingDays;

	public SprintData(TeamFactory teamFactory, TeamMemberFactory teamMemberFactory) {
		super(teamFactory, teamMemberFactory);
		dayCategories = Maps.newHashMap();
		workingDays = Lists.newArrayList();
	}

	public void setDayCategory(ServerDay day, SprintDayType dayType) {
		dayCategories.put(day, dayType);
		if (!workingDays.contains(day)) {
			workingDays.add(day);
		}
	}

	public SprintDayType getDayType(ServerDay day) {
		return dayCategories.get(day);
	}

	public List<ServerDay> getAllWorkingDays() {
		return workingDays;
	}

}
