package ch.paru.scrumTools.capacity.shared.data.collector;

import static org.easymock.EasyMock.expect;

import java.util.ArrayList;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.backendServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.backendServer.services.calendar.CalendarService;
import ch.paru.scrumTools.backendServer.services.calendar.ServerAppointment;
import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;
import ch.paru.scrumTools.backendServer.services.contact.ServerContact;
import ch.paru.scrumTools.backendServer.services.mock.MockData;
import ch.paru.scrumTools.backendServer.utils.ServerAppointmentUtil;
import ch.paru.scrumTools.backendServer.utils.ServerDayUtil;

import com.google.common.collect.Lists;

public class AbsenceDataCollectorTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final DataBox DATA_BOX_MOCK = MOCKS.createMock("DATA_BOX_MOCK", DataBox.class);
	private static final CalendarService CALENDAR_SERVICE_MOCK = MOCKS.createMock("CALENDAR_SERVICE_MOCK",
			CalendarService.class);

	@Test
	public void testLoadAbsences() {
		ServerDay startDay = ServerDayUtil.createDayFromNumbers(1, 1, 2013);
		ServerDay endDay = ServerDayUtil.addDays(startDay, 1);
		ServerContact contactFritz = MockData.CONTACT_FRITZ;
		ServerAppointment appmt = ServerAppointmentUtil.createAllDayAbsenceAppointment("Absence", contactFritz,
				startDay);

		MOCKS.resetAll();
		expect(CALENDAR_SERVICE_MOCK.getAllAppointmentsOfCategory(startDay, CalendarCategories.ABSENCES)).andReturn(
				Lists.newArrayList(appmt));
		expect(CALENDAR_SERVICE_MOCK.getAllAppointmentsOfCategory(endDay, CalendarCategories.ABSENCES)).andReturn(
				new ArrayList<ServerAppointment>());
		DATA_BOX_MOCK.addAbsenceForMember(contactFritz, startDay);

		MOCKS.replayAll();
		AbsenceDataCollector collector = new AbsenceDataCollector(CALENDAR_SERVICE_MOCK);
		collector.loadAbsences(DATA_BOX_MOCK, startDay, endDay);

		MOCKS.verifyAll();
	}

}
