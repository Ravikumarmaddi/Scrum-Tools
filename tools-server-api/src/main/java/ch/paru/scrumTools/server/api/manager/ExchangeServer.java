package ch.paru.scrumTools.server.api.manager;

import ch.paru.scrumTools.server.api.calendar.CalendarService;
import ch.paru.scrumTools.server.api.contact.ContactService;

public interface ExchangeServer {

	CalendarService getCalendarService();

	ContactService getContactService();
}
