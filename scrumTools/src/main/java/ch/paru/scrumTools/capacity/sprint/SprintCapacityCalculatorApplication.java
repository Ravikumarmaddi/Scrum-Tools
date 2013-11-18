package ch.paru.scrumTools.capacity.sprint;

import ch.paru.scrumTools.capacity.shared.commandLine.CommandLineParser;
import ch.paru.scrumTools.capacity.shared.configuration.CapacityConfiguration;
import ch.paru.scrumTools.exchangeServer.manager.ServerInstance;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class SprintCapacityCalculatorApplication {

	private ServerDay startDay, endDay;

	private SprintCapacityCalculatorApplication(ServerDay startDay, ServerDay endDay) {
		this.startDay = startDay;
		this.endDay = endDay;
	}

	private void start(String configFileName) {
		init(configFileName);

		// Collect Data

		// Calculate Capacity

		// Create Output
	}

	private void init(String configFileName) {
		ServerInstance.init(configFileName);
		CapacityConfiguration.init(configFileName);
	}

	public static void main(String[] args) {
		CommandLineParser parser = new CommandLineParser();
		parser.parse(args);

		ServerDay startDay = ServerDayUtil.createDayFromDate(parser.getStartDate());
		ServerDay endDay = ServerDayUtil.createDayFromDate(parser.getEndDate());

		new SprintCapacityCalculatorApplication(startDay, endDay).start(parser.getConfigFile());
	}
}
