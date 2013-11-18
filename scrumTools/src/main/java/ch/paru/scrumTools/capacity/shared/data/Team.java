package ch.paru.scrumTools.capacity.shared.data;

import java.util.List;

import com.google.common.collect.Lists;

public class Team {

	private String name;
	private List<TeamMember> members;

	private double rawSprintCapacity; // very raw
	private double rawSprintCapacityWithHourFactor; // raw reduced by working hours
	private double reducedSprintCapacity; // working hours reduced by overall reduction

	Team(String name) {
		this.name = name;
		this.members = Lists.newArrayList();
		this.rawSprintCapacity = 0;
	}

	public String getName() {
		return name;
	}

	public void addRawSprintCapacity(double hours) {
		this.rawSprintCapacity += hours;
	}

	public double getRawSprintCapacity() {
		return rawSprintCapacity;
	}

	public void addTeamMember(TeamMember teamMember) {
		members.add(teamMember);
	}

	public List<TeamMember> getAllMembers() {
		return members;
	}

	public double getReducedSprintCapacity() {
		return reducedSprintCapacity;
	}

	public void setReducedSprintCapacity(double reducedSprintCapacity) {
		this.reducedSprintCapacity = reducedSprintCapacity;
	}

	public double getRawSprintCapacityWithHourFactor() {
		return rawSprintCapacityWithHourFactor;
	}

	public void setRawSprintCapacityWithHourFactor(double rawSprintCapacityWithHourFactor) {
		this.rawSprintCapacityWithHourFactor = rawSprintCapacityWithHourFactor;
	}

}
