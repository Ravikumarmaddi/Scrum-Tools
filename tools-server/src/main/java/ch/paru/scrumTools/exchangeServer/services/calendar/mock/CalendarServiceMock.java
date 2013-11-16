package ch.paru.scrumTools.exchangeServer.services.calendar.mock;

import java.util.Calendar;
import java.util.List;

import microsoft.exchange.webservices.data.ExchangeService;

import com.google.common.collect.Lists;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;
import ch.paru.scrumTools.server.api.services.calendar.CalendarCategories;
import ch.paru.scrumTools.server.api.services.calendar.CalendarService;
import ch.paru.scrumTools.server.api.services.calendar.ServerAppointment;
import ch.paru.scrumTools.server.api.services.calendar.ServerDay;
import ch.paru.scrumTools.server.api.services.calendar.ServerTime;
import ch.paru.scrumTools.server.api.services.contact.ServerContact;

public class CalendarServiceMock implements CalendarService {

	public CalendarServiceMock(ExchangeService server) {
	}

	public List<ServerAppointment> getAllTeamAbsences(ServerDay day) {
		return MockData.getTeamAbsences(day);
	}

	@Override
	public List<ServerAppointment> getAllAppointmentsOfCategory(ServerDay day, CalendarCategories category) {
		//TODO implement
		return Lists.newArrayList();
	}

	@Override
	public ServerAppointment getSingleAppointmentOfCategory(ServerDay day, CalendarCategories category) {
		return new ServerAppointment("MOCK APPOINTMENT", new ServerContact("test@abcd.ef"), day, 100, new ServerTime(0, 0));
	}

	@Override
	public boolean isWorkingDay(ServerDay day) {
		Calendar cal = day.getCalendar();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
			return false;
		}
		return true;
	}

}
