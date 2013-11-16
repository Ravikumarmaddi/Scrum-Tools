package ch.paru.scrumTools.server.api.manager;

import java.util.Set;

import org.reflections.Reflections;

import ch.paru.scrumTools.server.api.services.calendar.CalendarService;
import ch.paru.scrumTools.server.api.services.configuration.ConfigurationService;
import ch.paru.scrumTools.server.api.services.contact.ContactService;
import ch.paru.scrumTools.server.api.utils.exceptions.ServerException;

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
		Reflections reflections = new Reflections("ch.paru.scrumTools");

		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(ServerManager.class);
		if (annotated == null || annotated.size() != 1) {
			throw new ServerException("no servermanager found", null);
		}

		try {
			Class<?> managerClass = annotated.iterator().next();
			ServerFacade managerInstance = (ServerFacade) managerClass.newInstance();
			server = managerInstance;
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
}
