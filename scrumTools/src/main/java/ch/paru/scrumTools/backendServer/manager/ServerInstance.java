package ch.paru.scrumTools.backendServer.manager;

import ch.paru.scrumTools.backendServer.services.ExchangeServer;
import ch.paru.scrumTools.backendServer.services.calendar.CalendarService;
import ch.paru.scrumTools.backendServer.services.configuration.ConfigurationService;
import ch.paru.scrumTools.backendServer.services.contact.ContactService;
import ch.paru.scrumTools.backendServer.utils.configuration.ExchangeServerConfiguration;
import ch.paru.scrumTools.backendServer.utils.exceptions.ServerException;

public class ServerInstance implements ServerFacade {

	private static ServerInstance instance;

	private ServerFacade server;

	public static final ServerInstance getInstance() {
		if (instance == null) {
			instance = new ServerInstance();
		}
		return instance;
	}

	ServerInstance() {
		try {
			server = new ExchangeServer();
		}
		catch (Exception e) {
			throw new ServerException("instanciate of servermanager failed", e);
		}
	}

	@Override
	public CalendarService getCalendarService() {
		return server.getCalendarService();
	}

	@Override
	public ContactService getContactService() {
		return server.getContactService();
	}

	@Override
	public ConfigurationService getConfigurationService() {
		return server.getConfigurationService();
	}

	public static void init(String configFileName) {
		ExchangeServerConfiguration.init(configFileName);
	}
}
