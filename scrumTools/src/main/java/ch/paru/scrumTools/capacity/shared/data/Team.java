package ch.paru.scrumTools.capacity.shared.data;

import java.util.List;

import ch.paru.scrumTools.common.reflection.customs.Customizable;

import com.google.common.collect.Lists;

public class Team implements Customizable {

	private String name;
	private List<TeamMember> members;

	private Numbers numbers;

	public Team(String name) {
		this.name = name;
		this.members = Lists.newArrayList();
		this.numbers = new Numbers();
	}

	public String getName() {
		return name;
	}

	public Numbers getNumbers() {
		return numbers;
	}

	public void setNumbers(Numbers numbers) {
		this.numbers = numbers;
	}

	public void addTeamMember(TeamMember teamMember) {
		members.add(teamMember);
	}

	public List<TeamMember> getAllMembers() {
		return members;
	}
}
