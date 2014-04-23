package ch.paru.scrumTools.backendServer.services.calendar.impl;

import java.util.Calendar;
import java.util.List;

import microsoft.exchange.webservices.data.Appointment;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.StringList;
import ch.paru.scrumTools.backendServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.backendServer.services.calendar.CalendarService;
import ch.paru.scrumTools.backendServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;
import ch.paru.scrumTools.backendServer.services.calendar.ServerTime;
import ch.paru.scrumTools.backendServer.services.contact.ServerContact;
import ch.paru.scrumTools.backendServer.utils.ServerDayUtil;
import ch.paru.scrumTools.backendServer.utils.ServerTimeUtil;
import ch.paru.scrumTools.backendServer.utils.exceptions.ServerException;
import ch.paru.scrumTools.backendServer.utils.interceptors.AbstractInterceptedService;

import com.google.common.collect.Lists;

public class CalendarServiceImpl extends AbstractInterceptedService implements CalendarService {

	private AppointmentLoader appointLoader;

	public CalendarServiceImpl(ExchangeService server) {
		appointLoader = new AppointmentLoader(server);
	}

	@Override
	public boolean isWorkingDay(ServerDay day) {
		try {
			Calendar cal = day.getCalendar();
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
				return false;
			}

			List<ServerAppointment> appointments = getAppointmentsOfCategory(day, CalendarCategories.PUBLIC_HOLIDAY);
			return appointments.size() == 0;
		}
		catch (final Exception e) {
			throw new ServerException("Working day could not be calculated", e);
		}
	}

	@Override
	public List<ServerAppointment> getAllAppointmentsOfCategory(ServerDay day, CalendarCategories category) {
		try {
			return getAppointmentsOfCategory(day, category);
		}
		catch (final Exception e) {
			throw new ServerException("appointments for category '" + category + "' could not be loaded", e);
		}
	}

	@Override
	public ServerAppointment getSingleAppointmentOfCategory(ServerDay day, CalendarCategories category,
			boolean nullIfNotSingle) {
		List<ServerAppointment> appointments = getAllAppointmentsOfCategory(day, category);

		if (appointments.size() != 1) {
			if (nullIfNotSingle) {
				return null;
			}
			throw new ServerException("not exactly one appointment: " + day + " - " + category, null);
		}

		return appointments.get(0);
	}

	private List<ServerAppointment> getAppointmentsOfCategory(ServerDay day, CalendarCategories cat) throws Exception {
		ServerDay startDay = ServerDayUtil.addDays(day, 0);
		ServerDay endDay = ServerDayUtil.addDays(day, 1);

		final List<Appointment> findResults = appointLoader.loadAppointments(startDay, endDay);

		List<ServerAppointment> appointments = Lists.newArrayList();

		for (final Appointment appt : findResults) {
			if (appt.getCategories().contains(cat.getCategoryName())) {
				String subject = appt.getSubject();
				ServerContact creator = new ServerContact(appt.getOrganizer().getAddress());
				int duration = (int) appt.getDuration().getTotalMinutes();
				ServerTime startTime = ServerTimeUtil.createTimeFromDate(appt.getStart());
				ServerAppointment appointment = new ServerAppointment(subject, creator, day, duration, startTime);
				readCategories(appt, appointment);
				appointments.add(appointment);
			}
		}

		return appointments;
	}

	private void readCategories(Appointment remoteAppointment, ServerAppointment appointment) throws Exception {
		StringList assignedCategories = remoteAppointment.getCategories();
		CalendarCategories[] categories = CalendarCategories.values();
		for (int i = 0; i < categories.length; i++) {
			CalendarCategories cat = categories[i];
			if (assignedCategories.contains(cat.getCategoryName())) {
				appointment.addCategory(cat);
			}
		}
	}

}
