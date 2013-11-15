package ch.paru.scrumTools.exchangeServer;

import java.lang.reflect.Constructor;

import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.WebCredentials;
import ch.paru.scrumTools.exchangeServer.services.calendar.impl.CalendarServiceImpl;
import ch.paru.scrumTools.exchangeServer.services.calendar.mock.CalendarServiceMock;
import ch.paru.scrumTools.exchangeServer.services.contact.impl.ContactServiceImpl;
import ch.paru.scrumTools.exchangeServer.services.contact.mock.ContactServiceMock;
import ch.paru.scrumTools.exchangeServer.util.configuration.ExchangeServerConfiguration;
import ch.paru.scrumTools.exchangeServer.util.interceptor.CacheInterceptorFactory;
import ch.paru.scrumTools.exchangeServer.util.interceptor.LogInterceptorFactory;
import ch.paru.scrumTools.server.api.calendar.CalendarService;
import ch.paru.scrumTools.server.api.contact.ContactService;
import ch.paru.scrumTools.server.api.exceptions.EchangeServerException;
import ch.paru.scrumTools.server.api.manager.ServerManager;

@ServerManager
public class ExchangeServer implements ch.paru.scrumTools.server.api.manager.ExchangeServer {

	//	private static ExchangeServer bm;

	private ExchangeService remote;

	private LogInterceptorFactory logInterceptorFactory;
	private CacheInterceptorFactory cacheInterceptorFactory;

	private ContactService contactService;
	private CalendarService calendarService;

	//	public static final ExchangeServer getInstance() {
	//		if (bm == null) {
	//			bm = new ExchangeServer();
	//		}
	//		return bm;
	//	}
	//
	//	public static final void initServer(String configFile) {
	//		ExchangeServerConfiguration.init(configFile);
	//	}

	public ExchangeServer() {
		logInterceptorFactory = new LogInterceptorFactory();
		cacheInterceptorFactory = new CacheInterceptorFactory();
	}

	@Override
	public CalendarService getCalendarService() {
		if (calendarService == null) {
			calendarService = getService(CalendarService.class, CalendarServiceImpl.class, CalendarServiceMock.class);
		}
		return calendarService;
	}

	@Override
	public ContactService getContactService() {
		if (contactService == null) {
			contactService = getService(ContactService.class, ContactServiceImpl.class, ContactServiceMock.class);
		}
		return contactService;
	}

	private <E> E getService(Class<E> clazz, Class<? extends E> impl, Class<? extends E> mock) {
		ExchangeServerConfiguration configuration = ExchangeServerConfiguration.getInstance();
		Class<? extends E> classToUse = configuration.getIsRealServiceUsed() ? impl : mock;

		try {
			Constructor<? extends E> constructor = classToUse.getConstructor(ExchangeService.class);
			E obj = constructor.newInstance(getServer());
			obj = cacheInterceptorFactory.getInterceptor(clazz, obj);
			obj = logInterceptorFactory.getInterceptor(clazz, obj);
			return obj;
		}
		catch (Exception e) {
			throw new EchangeServerException("instantiation of service impl failed", e);
		}
	}

	private ExchangeService getServer() {
		try {
			if (remote == null) {
				ExchangeServerConfiguration configuration = ExchangeServerConfiguration.getInstance();

				final ExchangeService service = new ExchangeService();
				final ExchangeCredentials credentials = new WebCredentials(configuration.getUsername(),
						configuration.getPassword());
				service.setCredentials(credentials);
				service.setUrl(new java.net.URI(configuration.getUrl()));
				service.setTraceEnabled(false);
				remote = service;
			}
			return remote;
		}
		catch (final Exception e) {
			throw new EchangeServerException("connection to server falied", e);
		}
	}

}
