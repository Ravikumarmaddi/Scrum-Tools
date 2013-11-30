package ch.paru.scrumTools.capacity.shared.data;

import java.util.List;

import ch.paru.scrumTools.common.reflection.Customizable;

import com.google.common.collect.Lists;

public class Team implements Customizable {

	private String name;
	private List<TeamMember> members;

	private double capacity;

	public Team(String name) {
		this.name = name;
		this.members = Lists.newArrayList();
		this.capacity = 0;
	}

	public String getName() {
		return name;
	}

	public void addCapacity(double amount) {
		this.capacity += amount;
	}

	public double getCapacity() {
		return capacity;
	}

	public void addTeamMember(TeamMember teamMember) {
		members.add(teamMember);
	}

	public List<TeamMember> getAllMembers() {
		return members;
	}
}
