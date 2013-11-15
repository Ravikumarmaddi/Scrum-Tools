package ch.paru.scrumTools.server.api.manager;

import ch.paru.scrumTools.server.api.calendar.CalendarService;
import ch.paru.scrumTools.server.api.configuration.ConfigurationService;
import ch.paru.scrumTools.server.api.contact.ContactService;

public interface ServerFacade {

	CalendarService getCalendarService();

	ContactService getContactService();

	ConfigurationService getConfigurationService();
}
