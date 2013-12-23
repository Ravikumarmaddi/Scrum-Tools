package ch.paru.scrumTools.custom.capacity.release;

public class TeamValues {

	private double teamTotal;
	private double teamTotalInternal;
	private double teamTotalExternal;

	private double effortAdministration;
	private double effortEducation;
	private double effort3rdLevelSupport;
	private double effortFeasibilitySupport;
	private double total;

	TeamValues() {
		teamTotal = 0;
		teamTotalInternal = 0;
		teamTotalExternal = 0;
		effortAdministration = 0;
		effortEducation = 0;
		effort3rdLevelSupport = 0;
		effortFeasibilitySupport = 0;
		total = 0;
	}

	public void addTeamTotal(double value) {
		teamTotal = teamTotal + value;
	}

	public void addTeamTotalInternal(double value) {
		teamTotalInternal = teamTotalInternal + value;
	}

	public void addTeamTotalExternal(double value) {
		teamTotalExternal = teamTotalExternal + value;
	}

	public double getTeamTotal() {
		return teamTotal;
	}

	public double getTeamTotalInternal() {
		return teamTotalInternal;
	}

	public double getTeamTotalExternal() {
		return teamTotalExternal;
	}

	public void setAdministrationEffort(double value) {
		effortAdministration = value;
	}

	public void setEducationEffort(double value) {
		effortEducation = value;
	}

	public void set3rdLevelSupportEffort(double value) {
		effort3rdLevelSupport = value;
	}

	public void setFeasibilitySupportEffort(double value) {
		effortFeasibilitySupport = value;
	}

	public double getEffortAdministration() {
		return effortAdministration;
	}

	public double getEffortEducation() {
		return effortEducation;
	}

	public double getEffort3rdLevelSupport() {
		return effort3rdLevelSupport;
	}

	public double getEffortFeasibilitySupport() {
		return effortFeasibilitySupport;
	}

	public void setTotal(double value) {
		this.total = value;
	}

	public double getTotal() {
		return total;
	}
}
