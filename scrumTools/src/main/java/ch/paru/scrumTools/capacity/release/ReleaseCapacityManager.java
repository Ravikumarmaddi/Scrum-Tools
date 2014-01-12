package ch.paru.scrumTools.capacity.release;

import ch.paru.scrumTools.capacity.release.configuration.ReleaseCapacityConfiguration;
import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.data.calculator.MemberReleaseCalculation;
import ch.paru.scrumTools.capacity.release.data.calculator.TeamReleaseCalculation;
import ch.paru.scrumTools.capacity.release.data.collector.ReleaseDataCollector;
import ch.paru.scrumTools.capacity.release.factories.MemberReleaseCalculationFactory;
import ch.paru.scrumTools.capacity.release.factories.ReleaseCapacityApplicationInitializerFactory;
import ch.paru.scrumTools.capacity.release.factories.ReleaseCapacityRendererFactory;
import ch.paru.scrumTools.capacity.release.factories.ReleaseDataFactory;
import ch.paru.scrumTools.capacity.release.factories.TeamReleaseCalculationFactory;
import ch.paru.scrumTools.capacity.release.init.ReleaseCapacityApplicationInitializer;
import ch.paru.scrumTools.capacity.shared.data.collector.AbsenceDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.ConfigurationDataCollector;
import ch.paru.scrumTools.capacity.shared.data.collector.TeamDataCollector;
import ch.paru.scrumTools.capacity.shared.factories.TeamFactory;
import ch.paru.scrumTools.capacity.shared.factories.TeamMemberFactory;
import ch.paru.scrumTools.capacity.shared.renderer.DataRenderer;
import ch.paru.scrumTools.exchangeServer.manager.ServerInstance;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;

public class ReleaseCapacityManager {

	private ServerDay startDay, endDay;

	public ReleaseCapacityManager(ServerDay startDay, ServerDay endDay) {
		this.startDay = startDay;
		this.endDay = endDay;
	}

	public void start(String configFileName) {
		ReleaseCapacityApplicationInitializerFactory initFactory = new ReleaseCapacityApplicationInitializerFactory();
		ReleaseCapacityApplicationInitializer initializer = initFactory.createInitializer();
		initializer.init(configFileName);

		// Collect Data
		ReleaseCapacityConfiguration configuration = ReleaseCapacityConfiguration.getInstance();
		ServerInstance serverFacade = ServerInstance.getInstance();
		TeamDataCollector teamDataCollector = new TeamDataCollector(serverFacade.getContactService());
		ConfigurationDataCollector configDataCollector = new ConfigurationDataCollector(configuration);
		AbsenceDataCollector absenceDataCollector = new AbsenceDataCollector(serverFacade.getCalendarService());
		ReleaseDataCollector collector = new ReleaseDataCollector(serverFacade.getCalendarService(),
				serverFacade.getContactService(), configuration, teamDataCollector, configDataCollector,
				absenceDataCollector);
		ReleaseData data = new ReleaseDataFactory().createData(new TeamFactory(), new TeamMemberFactory());
		data.setStartDay(startDay);
		data.setEndDay(endDay);
		collector.collectData(data);

		// Calculate Capacity
		MemberReleaseCalculation memberCalculation = new MemberReleaseCalculationFactory().createCalculator(data,
				serverFacade.getCalendarService());
		memberCalculation.calculateAllCapacities();
		TeamReleaseCalculation teamCalculation = new TeamReleaseCalculationFactory().createCalculator(data);
		teamCalculation.calculateAllCapacities();

		// Create Output
		DataRenderer<ReleaseData> renderer = new ReleaseCapacityRendererFactory().createRenderer();
		renderer.renderData(data);
	}
}
