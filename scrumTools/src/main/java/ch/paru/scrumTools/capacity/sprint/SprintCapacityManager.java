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
import ch.paru.scrumTools.capacity.sprint.data.calculator.MemberCalculation;
import ch.paru.scrumTools.capacity.sprint.data.calculator.RoleDetailCapacityCalculator;
import ch.paru.scrumTools.capacity.sprint.data.calculator.TeamCalculation;
import ch.paru.scrumTools.capacity.sprint.data.collector.SprintDataCollector;
import ch.paru.scrumTools.capacity.sprint.factories.MemberCalculationFactory;
import ch.paru.scrumTools.capacity.sprint.factories.SprintCapacityApplicationInitializerFactory;
import ch.paru.scrumTools.capacity.sprint.factories.SprintCapacityRendererFactory;
import ch.paru.scrumTools.capacity.sprint.factories.TeamCalculationFactory;
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
		SprintDataCollector collector = new SprintDataCollector(serverFacade.getCalendarService(), configuration,
				teamDataCollector, configDataCollector, absenceDataCollector);
		SprintData data = new SprintData(new TeamFactory(), new TeamMemberFactory());
		data.setStartDay(startDay);
		data.setEndDay(endDay);
		collector.collectData(data);

		// Calculate Capacity
		MemberCalculation memberCalculation = new MemberCalculationFactory().createCalculator(data,
				new ConstantHourManager(configuration), new RoleDetailCapacityCalculator());
		memberCalculation.calculateAllCapacities();
		TeamCalculation teamCalculation = new TeamCalculationFactory().createCalculator(data);
		teamCalculation.calculateAllCapacities();

		// Create Output
		DataRenderer<SprintData> renderer = new SprintCapacityRendererFactory().createRenderer();
		renderer.renderData(data);
	}
}
