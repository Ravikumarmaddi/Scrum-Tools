package ch.paru.scrumTools.capacity.sprint;

import ch.paru.scrumTools.capacity.shared.data.collector.AbsenceDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.ConfigurationDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.TeamDataCollector;
import ch.paru.scrumTools.capacity.shared.factories.TeamFactory;
import ch.paru.scrumTools.capacity.shared.factories.TeamMemberFactory;
import ch.paru.scrumTools.capacity.shared.renderer.DataRenderer;
import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.capacity.sprint.data.calculator.ConstantHourManager;
import ch.paru.scrumTools.capacity.sprint.data.calculator.MemberSprintCalculation;
import ch.paru.scrumTools.capacity.sprint.data.calculator.RoleDetailSprintCapacityCalculator;
import ch.paru.scrumTools.capacity.sprint.data.calculator.TeamSprintCalculation;
import ch.paru.scrumTools.capacity.sprint.data.collector.SprintDataCollector;
import ch.paru.scrumTools.capacity.sprint.factories.MemberSprintCalculationFactory;
import ch.paru.scrumTools.capacity.sprint.factories.SprintCapacityApplicationInitializerFactory;
import ch.paru.scrumTools.capacity.sprint.factories.SprintCapacityRendererFactory;
import ch.paru.scrumTools.capacity.sprint.factories.SprintDataFactory;
import ch.paru.scrumTools.capacity.sprint.factories.TeamSprintCalculationFactory;
import ch.paru.scrumTools.capacity.sprint.init.SprintCapacityApplicationInitializer;
import ch.paru.scrumTools.exchangeServer.manager.ServerInstance;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;

public class SprintCapacityManager {

	private ServerDay startDay, endDay;

	public SprintCapacityManager(ServerDay startDay, ServerDay endDay) {
		this.startDay = startDay;
		this.endDay = endDay;
	}

	public void start(String configFileName) {
		SprintCapacityApplicationInitializerFactory initFactory = new SprintCapacityApplicationInitializerFactory();
		SprintCapacityApplicationInitializer initializer = initFactory.createInitializer();
		initializer.init(configFileName);

		// Collect Data
		SprintCapacityConfiguration configuration = SprintCapacityConfiguration.getInstance();
		ServerInstance serverFacade = ServerInstance.getInstance();
		TeamDataCollector teamDataCollector = new TeamDataCollector(serverFacade.getContactService());
		ConfigurationDataCollector configDataCollector = new ConfigurationDataCollector(configuration);
		AbsenceDataCollector absenceDataCollector = new AbsenceDataCollector(serverFacade.getCalendarService());
		SprintDataCollector collector = new SprintDataCollector(serverFacade.getCalendarService(),
				serverFacade.getContactService(), configuration, teamDataCollector, configDataCollector,
				absenceDataCollector);
		SprintData data = new SprintDataFactory().createData(new TeamFactory(), new TeamMemberFactory());
		data.setStartDay(startDay);
		data.setEndDay(endDay);
		collector.collectData(data);

		// Calculate Capacity
		MemberSprintCalculation memberCalculation = new MemberSprintCalculationFactory().createCalculator(data,
				new ConstantHourManager(configuration), new RoleDetailSprintCapacityCalculator());
		memberCalculation.calculateAllCapacities();
		TeamSprintCalculation teamCalculation = new TeamSprintCalculationFactory().createCalculator(data);
		teamCalculation.calculateAllCapacities();

		// Create Output
		DataRenderer<SprintData> renderer = new SprintCapacityRendererFactory().createRenderer();
		renderer.renderData(data);
	}
}
