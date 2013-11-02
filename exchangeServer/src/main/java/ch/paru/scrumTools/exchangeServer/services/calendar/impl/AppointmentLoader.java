package ch.paru.scrumTools.exchangeServer.services.calendar.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import microsoft.exchange.webservices.data.Appointment;
import microsoft.exchange.webservices.data.CalendarFolder;
import microsoft.exchange.webservices.data.CalendarView;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.FindItemsResults;
import microsoft.exchange.webservices.data.WellKnownFolderName;

import org.slf4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import ch.paru.scrumTools.exchangeServer.services.calendar.EwsDay;
import ch.paru.scrumTools.exchangeServer.util.EwsDayUtil;
import ch.paru.scrumTools.exchangeServer.util.logging.EwsLogger;

public class AppointmentLoader {

	private static final Logger LOGGER = EwsLogger.getLogger(AppointmentLoader.class);

	private ExchangeService server;
	private Map<String, List<Appointment>> appointmentCache;

	AppointmentLoader(ExchangeService server) {
		this.server = server;
		appointmentCache = Maps.newHashMap();
	}

	public List<Appointment> loadAppointments(EwsDay startDay, EwsDay endDay) throws Exception {
		String cacheKey = getCacheKey(startDay, endDay);
		LOGGER.debug("AppointmentLoader: Key: " + cacheKey);
		if (!appointmentCache.containsKey(cacheKey)) {
			LOGGER.debug(cacheKey + " not found in Cache");
			List<Appointment> appointments = getAppointments(setMorningTime(startDay), setEveningTime(endDay));
			appointmentCache.put(cacheKey, appointments);
		}
		return appointmentCache.get(cacheKey);
	}

	private List<Appointment> getAppointments(Date startDate, Date endDate) throws Exception {
		final CalendarFolder cf = CalendarFolder.bind(server, WellKnownFolderName.Calendar);
		final FindItemsResults<Appointment> findResults = cf.findAppointments(new CalendarView(startDate, endDate));
		return initAllAppointments(findResults);
	}

	private List<Appointment> initAllAppointments(FindItemsResults<Appointment> appointments) throws Exception {
		List<Appointment> list = Lists.newArrayList();

		for (final Appointment appt : appointments.getItems()) {
			appt.load();
			list.add(appt);
		}

		return list;
	}

	private String getCacheKey(EwsDay startDay, EwsDay endDay) {
		StringBuffer sb = new StringBuffer();
		sb.append(EwsDayUtil.getDisplayText(startDay));
		sb.append("_");
		sb.append(EwsDayUtil.getDisplayText(endDay));
		return sb.toString();
	}

	/* Needed because the timezone handling is not working properly. AllDay appointments are starts always @ 10pm */
	private Date setMorningTime(EwsDay day) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(day.getDate());
		cal.add(Calendar.HOUR_OF_DAY, +3);
		return cal.getTime();
	}

	private Date setEveningTime(EwsDay day) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(day.getDate());
		cal.add(Calendar.HOUR_OF_DAY, -3);
		return cal.getTime();
	}
}
