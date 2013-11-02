package ch.paru.scrumTools.exchangeServer.services.calendar.impl;

import java.util.Calendar;
import java.util.List;

import microsoft.exchange.webservices.data.Appointment;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.StringList;

import com.google.common.collect.Lists;
import ch.paru.scrumTools.exchangeServer.EchangeServerException;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;
import ch.paru.scrumTools.exchangeServer.services.calendar.EwsAppointment;
import ch.paru.scrumTools.exchangeServer.services.calendar.EwsDay;
import ch.paru.scrumTools.exchangeServer.services.calendar.EwsTime;
import ch.paru.scrumTools.exchangeServer.services.contact.EwsContact;
import ch.paru.scrumTools.exchangeServer.util.EwsDayUtil;
import ch.paru.scrumTools.exchangeServer.util.EwsTimeUtil;

public class CalendarServiceImpl implements CalendarService {

	private AppointmentLoader appointLoader;

	public CalendarServiceImpl(ExchangeService server) {
		appointLoader = new AppointmentLoader(server);
	}

	@Override
	public boolean isWorkingDay(EwsDay day) {
		try {
			Calendar cal = day.getCalendar();
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
				return false;
			}

			List<EwsAppointment> appointments = getAppointmentsOfCategory(day, CalendarCategories.PUBLIC_HOLIDAY);
			return appointments.size() == 0;
		}
		catch (final Exception e) {
			throw new EchangeServerException("Working day could not be calculated", e);
		}
	}

	@Override
	public List<EwsAppointment> getAllAppointmentsOfCategory(EwsDay day, CalendarCategories category) {
		try {
			return getAppointmentsOfCategory(day, category);
		}
		catch (final Exception e) {
			throw new EchangeServerException("appointments for category '" + category + "' could not be loaded", e);
		}
	}

	@Override
	public EwsAppointment getSingleAppointmentOfCategory(EwsDay day, CalendarCategories category) {
		List<EwsAppointment> appointments = getAllAppointmentsOfCategory(day, category);

		if (appointments.size() != 1) {
			throw new EchangeServerException("not exactly one appointment: " + day + " - " + category, null);
		}

		return appointments.get(0);
	}

	private List<EwsAppointment> getAppointmentsOfCategory(EwsDay day, CalendarCategories cat) throws Exception {
		EwsDay startDay = EwsDayUtil.addDays(day, 0);
		EwsDay endDay = EwsDayUtil.addDays(day, 1);

		final List<Appointment> findResults = appointLoader.loadAppointments(startDay, endDay);

		List<EwsAppointment> appointments = Lists.newArrayList();

		for (final Appointment appt : findResults) {
			if (appt.getCategories().contains(cat.getCategoryName())) {
				String subject = appt.getSubject();
				EwsContact creator = new EwsContact(appt.getOrganizer().getAddress());
				int duration = (int) appt.getDuration().getTotalMinutes();
				EwsTime startTime = EwsTimeUtil.createTimeFromDate(appt.getStart());
				EwsAppointment appointment = new EwsAppointment(subject, creator, day, duration, startTime);
				readCategories(appt, appointment);
				appointments.add(appointment);
			}
		}

		return appointments;
	}

	private void readCategories(Appointment remoteAppointment, EwsAppointment ewsAppointment) throws Exception {
		StringList assignedCategories = remoteAppointment.getCategories();
		CalendarCategories[] categories = CalendarCategories.values();
		for (int i = 0; i < categories.length; i++) {
			CalendarCategories cat = categories[i];
			if (assignedCategories.contains(cat.getCategoryName())) {
				ewsAppointment.addCategory(cat);
			}
		}
	}

}
