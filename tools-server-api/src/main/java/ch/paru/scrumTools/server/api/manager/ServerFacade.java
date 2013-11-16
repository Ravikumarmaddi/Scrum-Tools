package ch.paru.scrumTools.server.api.manager;

import ch.paru.scrumTools.server.api.services.calendar.CalendarService;
import ch.paru.scrumTools.server.api.services.configuration.ConfigurationService;
import ch.paru.scrumTools.server.api.services.contact.ContactService;

public interface ServerFacade {

	CalendarService getCalendarService();

	ContactService getContactService();

	ConfigurationService getConfigurationService();
}
