package ch.paru.scrumTools.capacity.sprint;

import ch.paru.scrumTools.capacity.shared.commandLine.CommandLineParser;
import ch.paru.scrumTools.capacity.shared.data.collector.AbsenceDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.ConfigurationDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.TeamDataCollector;
import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.SprintDataCollector;
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
		SprintCapacityConfiguration configuration = SprintCapacityConfiguration.getInstance();
		ServerInstance serverFacade = ServerInstance.getInstance();
		TeamDataCollector teamDataCollector = new TeamDataCollector(serverFacade.getContactService());
		ConfigurationDataCollector configDataCollector = new ConfigurationDataCollector(configuration);
		AbsenceDataCollector absenceDataCollector = new AbsenceDataCollector(serverFacade.getCalendarService());
		SprintDataCollector collector = new SprintDataCollector(serverFacade.getCalendarService(), configuration,
				teamDataCollector, configDataCollector, absenceDataCollector);
		SprintData data = new SprintData();
		collector.collectData(data, startDay, endDay);

		// Calculate Capacity

		// Create Output
	}

	private void init(String configFileName) {
		ServerInstance.init(configFileName);
		SprintCapacityConfiguration.init(configFileName);
	}

	public static void main(String[] args) {
		CommandLineParser parser = new CommandLineParser();
		parser.parse(args);

		ServerDay startDay = ServerDayUtil.createDayFromDate(parser.getStartDate());
		ServerDay endDay = ServerDayUtil.createDayFromDate(parser.getEndDate());

		new SprintCapacityCalculatorApplication(startDay, endDay).start(parser.getConfigFile());
	}
}
