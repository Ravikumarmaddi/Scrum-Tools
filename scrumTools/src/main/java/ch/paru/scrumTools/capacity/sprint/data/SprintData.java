package ch.paru.scrumTools.capacity.sprint.data;

import java.util.Map;

import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;

import com.google.common.collect.Maps;

public class SprintData extends DataBox {

	private Map<ServerDay, SprintDayType> dayCategories;

	public SprintData() {
		dayCategories = Maps.newHashMap();
	}

	public void setDayCategory(ServerDay day, SprintDayType dayType) {
		dayCategories.put(day, dayType);
	}

	public SprintDayType getDayType(ServerDay day) {
		return dayCategories.get(day);
	}

}
