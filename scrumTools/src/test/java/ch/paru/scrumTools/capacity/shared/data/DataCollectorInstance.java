package ch.paru.scrumTools.capacity.shared.data;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.configuration.CapacityConfiguration;
import ch.paru.scrumTools.exchangeServer.manager.ServerFacade;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;

public class DataCollectorInstance extends DataCollector {

	public DataCollectorInstance(ServerFacade server) {
		super(server);
	}

	@Override
	public void loadAbsences(DataBox data, ServerDay startDay, ServerDay endDay) {
		super.loadAbsences(data, startDay, endDay);
	}

	@Override
	public void loadConfiguration(DataBox data, CapacityConfiguration config) {
		super.loadConfiguration(data, config);
	}

	@Override
	public void loadTeams(DataBox data, List<String> teams) {
		super.loadTeams(data, teams);
	}

}
