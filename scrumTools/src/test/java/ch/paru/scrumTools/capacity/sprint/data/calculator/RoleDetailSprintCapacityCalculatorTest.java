package ch.paru.scrumTools.capacity.sprint.data.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.CapacityType;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigRole;

public class RoleDetailSprintCapacityCalculatorTest {

	@Test
	public void test_Person100_Role100_Factor() {
		ConfigRole role = new ConfigRole("name");
		role.setCapacity(1);
		role.setCapacityType(CapacityType.FACTOR);
		double memberFactor = 1;

		RoleDetailSprintCapacityCalculator calculator = new RoleDetailSprintCapacityCalculator();
		double value = calculator.getReducedCapacity(role, memberFactor, 100);

		assertEquals(100, value, 0.01);
	}

	@Test
	public void test_Person100_Role100_Absolute() {
		ConfigRole role = new ConfigRole("name");
		role.setCapacity(1);
		role.setCapacityType(CapacityType.ABSOLUTE);
		double memberFactor = 1;

		RoleDetailSprintCapacityCalculator calculator = new RoleDetailSprintCapacityCalculator();
		double value = calculator.getReducedCapacity(role, memberFactor, 100);

		assertEquals(100, value, 0.01);
	}

	@Test
	public void test_Person100_Role50_Factor() {
		ConfigRole role = new ConfigRole("name");
		role.setCapacity(.5);
		role.setCapacityType(CapacityType.FACTOR);
		double memberFactor = 1;

		RoleDetailSprintCapacityCalculator calculator = new RoleDetailSprintCapacityCalculator();
		double value = calculator.getReducedCapacity(role, memberFactor, 100);

		assertEquals(50, value, 0.01);
	}

	@Test
	public void test_Person80_Role50_Factor() {
		ConfigRole role = new ConfigRole("name");
		role.setCapacity(.5);
		role.setCapacityType(CapacityType.FACTOR);
		double memberFactor = .8;

		RoleDetailSprintCapacityCalculator calculator = new RoleDetailSprintCapacityCalculator();
		double value = calculator.getReducedCapacity(role, memberFactor, 100);

		assertEquals(40, value, 0.01);
	}

	@Test
	public void test_Person80_Role50_Absolute() {
		ConfigRole role = new ConfigRole("name");
		role.setCapacity(.5);
		role.setCapacityType(CapacityType.ABSOLUTE);
		double memberFactor = .8;

		RoleDetailSprintCapacityCalculator calculator = new RoleDetailSprintCapacityCalculator();
		double value = calculator.getReducedCapacity(role, memberFactor, 100);

		assertEquals(30, value, 0.01);
	}
}
