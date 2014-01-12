package ch.paru.scrumTools.exchangeServer.utils.interceptors.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarCategories;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

import com.google.common.collect.Maps;

public class CacheFileHandlerTest {

	@Test
	public void testStoreLoad() {
		Map<String, Object> map = Maps.newHashMap();
		map.put("contactgroup", MockData.GROUP_NORTH);
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 2, 2013);
		map.put("appointment", MockData.getAppointments(day, CalendarCategories.ABSENCES));

		String filePath = "target/";
		String cacheName = "test";

		CacheFileHandler handler = new CacheFileHandler(filePath, cacheName);
		handler.store(map);
		Map<String, Object> fromFile = handler.load();

		assertEquals(map, fromFile);
	}

	@Test
	public void testStoreLoadUnknownFile() {
		String filePath = "target/";
		String cacheName = "unknown";

		CacheFileHandler handler = new CacheFileHandler(filePath, cacheName);
		Map<String, Object> fromFile = handler.load();

		assertTrue(fromFile.isEmpty());
	}
}
