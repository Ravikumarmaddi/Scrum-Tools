package ch.paru.scrumTools.capacity.shared.data;

public class Numbers {

	private double availability;
	private double rawCapacity;
	private double finalCapacity;

	public double getAvailability() {
		return availability;
	}

	public void setAvailability(double availability) {
		this.availability = availability;
	}

	public double getFinalCapacity() {
		return finalCapacity;
	}

	public double getRawCapacity() {
		return rawCapacity;
	}

	public void setFinalCapacity(double finalCapacity) {
		this.finalCapacity = finalCapacity;
	}

	public void setRawCapacity(double rawCapacity) {
		this.rawCapacity = rawCapacity;
	}

	public void addFinalCapacity(double finalCapacity) {
		this.finalCapacity = this.finalCapacity + finalCapacity;
	}

	public void addRawCapacity(double rawCapacity) {
		this.rawCapacity = this.rawCapacity + rawCapacity;
	}

	public void addAvailability(double availability) {
		this.availability = this.availability + availability;
	}

}
