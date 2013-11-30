package ch.paru.scrumTools.capacity.shared.configuration;

import ch.paru.scrumTools.common.reflection.Customizable;

import com.google.common.base.Objects;

public class ConfigRole implements Customizable {

	public static final String NAME = "name";
	public static final String CAPACITY = "capacity";
	public static final String CAPACITY_TYPE = "capacityType";

	private String name;
	private double capacity;
	private CapacityType capacityType;

	public ConfigRole(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacityType(CapacityType capacityType) {
		this.capacityType = capacityType;
	}

	public CapacityType getCapacityType() {
		return capacityType;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("name", getName()).add("capacity", getCapacity()).toString();
	}
}
