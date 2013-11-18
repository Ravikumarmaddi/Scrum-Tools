package ch.paru.scrumTools.exchangeServer.manager;

import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.configuration.ConfigurationService;
import ch.paru.scrumTools.exchangeServer.services.contact.ContactService;

public interface ServerFacade {

	CalendarService getCalendarService();

	ContactService getContactService();

	ConfigurationService getConfigurationService();
}
