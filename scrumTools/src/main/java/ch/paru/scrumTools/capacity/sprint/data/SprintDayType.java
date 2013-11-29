package ch.paru.scrumTools.capacity.sprint.data;

public enum SprintDayType {
	RETRO_REVIEW_FULLDAY(1), //
	RETRO_REVIEW_HALFDAY(0.5), //
	PLANNING_FULLDAY(1), //
	PLANNING_HALFDAY(0.5), //
	DAILY_BUSINESS(1);

	private double dayFactor;

	private SprintDayType(double factor) {
		dayFactor = factor;
	}

	public double getDayFactor() {
		return dayFactor;
	};
}
