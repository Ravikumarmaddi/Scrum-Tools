package ch.paru.scrumTools.capacity.sprint.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class SprintDataTest {

	@Test
	public void testSetDayCategory() {
		//prepare
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 3, 2013);
		SprintDayType dailyBusiness = SprintDayType.DAILY_BUSINESS;

		//run
		SprintData data = new SprintData();
		data.setDayCategory(day, dailyBusiness);

		//validate
		assertEquals(dailyBusiness, data.getDayType(day));
	}

	@Test
	public void testSetDayCategoryOverwrite() {
		//prepare
		ServerDay day = ServerDayUtil.createDayFromNumbers(1, 3, 2013);
		SprintDayType dailyBusiness = SprintDayType.DAILY_BUSINESS;
		SprintDayType sprintStart = SprintDayType.PLANNING_FULLDAY;

		//run
		SprintData data = new SprintData();
		data.setDayCategory(day, dailyBusiness);
		data.setDayCategory(day, sprintStart);

		//validate
		assertEquals(sprintStart, data.getDayType(day));
	}

}
