package ch.paru.scrumTools.exchangeServer.services.calendar.mock;

import java.util.Calendar;
import java.util.List;

import microsoft.exchange.webservices.data.ExchangeService;

import com.google.common.collect.Lists;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.calendar.EwsAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.EwsDay;
import ch.paru.scrumTools.exchangeServer.services.calendar.EwsTime;
import ch.paru.scrumTools.exchangeServer.services.contact.EwsContact;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;

public class CalendarServiceMock implements CalendarService {

	public CalendarServiceMock(ExchangeService server) {
	}

	public List<EwsAppointment> getAllTeamAbsences(EwsDay day) {
		return MockData.getTeamAbsences(day);
	}

	@Override
	public List<EwsAppointment> getAllAppointmentsOfCategory(EwsDay day, CalendarCategories category) {
		//TODO implement
		return Lists.newArrayList();
	}

	@Override
	public EwsAppointment getSingleAppointmentOfCategory(EwsDay day, CalendarCategories category) {
		return new EwsAppointment("MOCK APPOINTMENT", new EwsContact("test@abcd.ef"), day, 100, new EwsTime(0, 0));
	}

	@Override
	public boolean isWorkingDay(EwsDay day) {
		Calendar cal = day.getCalendar();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
			return false;
		}
		return true;
	}

}
