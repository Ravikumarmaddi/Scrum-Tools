package ch.paru.scrumTools.exchangeServer.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import ch.paru.scrumTools.exchangeServer.services.calendar.ServerTime;

public class ServerTimeUtilTest {

	private static final int TIMESHIFT_OFFSET = 2;

	@Test
	public void testCompare_Before() {
		ServerTime time1 = new ServerTime(1, 0);
		ServerTime time2 = new ServerTime(2, 0);
		int result = ServerTimeUtil.compare(time1, time2);
		assertEquals(-1, result);
	}

	@Test
	public void testCompare_After() {
		ServerTime time1 = new ServerTime(3, 0);
		ServerTime time2 = new ServerTime(2, 0);
		int result = ServerTimeUtil.compare(time1, time2);
		assertEquals(1, result);
	}

	@Test
	public void testCompare_Equal() {
		ServerTime time = new ServerTime(3, 0);
		int result = ServerTimeUtil.compare(time, time);
		assertEquals(0, result);
	}

	@Test
	public void testABeforeB_OK() {
		ServerTime time1 = new ServerTime(1, 0);
		ServerTime time2 = new ServerTime(2, 0);
		boolean result = ServerTimeUtil.isABeforeB(time1, time2);
		assertTrue(result);
	}

	@Test
	public void testABeforeB_FAIL() {
		ServerTime time1 = new ServerTime(1, 0);
		ServerTime time2 = new ServerTime(2, 0);
		boolean result = ServerTimeUtil.isABeforeB(time2, time1);
		assertFalse(result);
	}

	@Test
	public void testAAfterB_OK() {
		ServerTime time1 = new ServerTime(3, 0);
		ServerTime time2 = new ServerTime(2, 0);
		boolean result = ServerTimeUtil.isAAfterB(time1, time2);
		assertTrue(result);
	}

	@Test
	public void testAAfterB_FAIL() {
		ServerTime time1 = new ServerTime(3, 0);
		ServerTime time2 = new ServerTime(2, 0);
		boolean result = ServerTimeUtil.isAAfterB(time2, time1);
		assertFalse(result);
	}

	@Test
	public void testCreateTime() {
		Calendar now = new GregorianCalendar();
		ServerTime result = ServerTimeUtil.createTimeFromDate(now.getTime());
		assertNotNull(result);
		int h = now.get(Calendar.HOUR_OF_DAY);
		assertEquals((h + TIMESHIFT_OFFSET) % 24, result.getHour());
		assertEquals(now.get(Calendar.MINUTE), result.getMin());
	}

}
