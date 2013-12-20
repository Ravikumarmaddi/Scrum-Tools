package ch.paru.scrumTools.capacity.release.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WeekCapacityTest {

	@Test
	public void testAddSingleDay() {
		WeekCapacity capacity = new WeekCapacity();
		assertEquals(0, capacity.getDaysAvailable());
		capacity.addSingleDay();
		capacity.addSingleDay();
		assertEquals(2, capacity.getDaysAvailable());
	}

}
