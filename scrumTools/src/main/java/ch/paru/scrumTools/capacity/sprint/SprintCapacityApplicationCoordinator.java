package ch.paru.scrumTools.capacity.sprint;

import ch.paru.scrumTools.capacity.shared.data.TeamFactory;
import ch.paru.scrumTools.capacity.shared.data.TeamMemberFactory;
import ch.paru.scrumTools.capacity.shared.data.collector.AbsenceDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.ConfigurationDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.TeamDataCollector;
import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.MemberCalculation;
import ch.paru.scrumTools.capacity.sprint.data.calculator.TeamCalculation;
import ch.paru.scrumTools.capacity.sprint.data.collector.SprintDataCollector;
import ch.paru.scrumTools.exchangeServer.manager.ServerInstance;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;

public class SprintCapacityApplicationCoordinator {

	private ServerDay startDay, endDay;

	public SprintCapacityApplicationCoordinator(ServerDay startDay, ServerDay endDay) {
		this.startDay = startDay;
		this.endDay = endDay;
	}

	public void start(String configFileName) {
		init(configFileName);

		// Collect Data
		SprintCapacityConfiguration configuration = SprintCapacityConfiguration.getInstance();
		ServerInstance serverFacade = ServerInstance.getInstance();
		TeamDataCollector teamDataCollector = new TeamDataCollector(serverFacade.getContactService());
		ConfigurationDataCollector configDataCollector = new ConfigurationDataCollector(configuration);
		AbsenceDataCollector absenceDataCollector = new AbsenceDataCollector(serverFacade.getCalendarService());
		SprintDataCollector collector = new SprintDataCollector(serverFacade.getCalendarService(), configuration,
				teamDataCollector, configDataCollector, absenceDataCollector);
		TeamFactory teamFactory = new TeamFactory();
		TeamMemberFactory teamMemberFactory = new TeamMemberFactory();
		SprintData data = new SprintData(teamFactory, teamMemberFactory);
		collector.collectData(data, startDay, endDay);

		// Calculate Capacity
		MemberCalculation memberCalculation = new MemberCalculation(data);
		memberCalculation.calculateAllCapacities();
		TeamCalculation teamCalculation = new TeamCalculation(data);
		teamCalculation.calculateAllCapacities();

		// Create Output
	}

	private void init(String configFileName) {
		ServerInstance.init(configFileName);
		SprintCapacityConfiguration.init(configFileName);
	}
}
