package ch.paru.scrumTools.backendServer.services.calendar.mock;

import java.util.Calendar;
import java.util.List;

import microsoft.exchange.webservices.data.ExchangeService;
import ch.paru.scrumTools.backendServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.backendServer.services.calendar.CalendarService;
import ch.paru.scrumTools.backendServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;
import ch.paru.scrumTools.backendServer.services.calendar.ServerTime;
import ch.paru.scrumTools.backendServer.services.contact.ServerContact;
import ch.paru.scrumTools.backendServer.services.mock.MockData;
import ch.paru.scrumTools.backendServer.utils.interceptors.AbstractInterceptedService;

import com.google.common.collect.Lists;

public class CalendarServiceMock extends AbstractInterceptedService implements CalendarService {

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
	public ServerAppointment getSingleAppointmentOfCategory(ServerDay day, CalendarCategories category,
			boolean nullIfNotSingle) {
		return new ServerAppointment("MOCK APPOINTMENT", new ServerContact("test@abcd.ef"), day, 100, new ServerTime(0,
				0));
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
