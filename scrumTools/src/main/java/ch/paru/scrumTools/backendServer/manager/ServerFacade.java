package ch.paru.scrumTools.backendServer.manager;

import ch.paru.scrumTools.backendServer.services.calendar.CalendarService;
import ch.paru.scrumTools.backendServer.services.configuration.ConfigurationService;
import ch.paru.scrumTools.backendServer.services.contact.ContactService;

public interface ServerFacade {

	CalendarService getCalendarService();

	ContactService getContactService();

	ConfigurationService getConfigurationService();
}
