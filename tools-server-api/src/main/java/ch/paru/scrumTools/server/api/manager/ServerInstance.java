package ch.paru.scrumTools.server.api.manager;

import java.util.Set;

import org.reflections.Reflections;

import ch.paru.scrumTools.server.api.calendar.CalendarService;
import ch.paru.scrumTools.server.api.contact.ContactService;
import ch.paru.scrumTools.server.api.exceptions.EchangeServerException;

public class ServerInstance implements ExchangeServer {

	private static ServerInstance instance;

	private ExchangeServer server;

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
			throw new EchangeServerException("no servermanager found", null);
		}

		try {
			Class<?> managerClass = annotated.iterator().next();
			ExchangeServer managerInstance = (ExchangeServer) managerClass.newInstance();
			server = managerInstance;
		}
		catch (Exception e) {
			throw new EchangeServerException("instanciate of servermanager failed", e);
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
}
