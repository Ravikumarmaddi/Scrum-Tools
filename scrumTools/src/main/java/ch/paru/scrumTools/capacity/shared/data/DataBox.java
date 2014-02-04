package ch.paru.scrumTools.capacity.shared.data;

import java.util.List;
import java.util.Map;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.factories.TeamFactory;
import ch.paru.scrumTools.capacity.shared.factories.TeamMemberFactory;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DataBox {

	private ServerDay startDay;
	private ServerDay endDay;

	private Map<String, Team> teams;
	private Map<ServerContact, TeamMember> members;
	private TeamMemberFactory teamMemberFactory;
	private TeamFactory teamFactory;

	public DataBox(TeamFactory teamFactory, TeamMemberFactory teamMemberFactory) {
		this.teamFactory = teamFactory;
		this.teamMemberFactory = teamMemberFactory;
		teams = Maps.newHashMap();
		members = Maps.newHashMap();
	}

	public void setStartDay(ServerDay startDay) {
		this.startDay = startDay;
	}

	public void setEndDay(ServerDay endDay) {
		this.endDay = endDay;
	}

	public ServerDay getStartDay() {
		return startDay;
	}

	public ServerDay getEndDay() {
		return endDay;
	}

	public void addTeamMember(String teamName, ServerContact contact) {
		if (!teams.containsKey(teamName)) {
			Team newTeam = teamFactory.createTeam(teamName);
			teams.put(teamName, newTeam);
		}

		Team team = teams.get(teamName);

		if (members.containsKey(contact)) {
			//the <contact> is already in another team
			TeamMember teamMember = members.get(contact);
			team.addTeamMember(teamMember);
		}
		else {
			TeamMember teamMember = teamMemberFactory.createTeamMember(contact);
			team.addTeamMember(teamMember);
			members.put(contact, teamMember);
		}
	}

	public void addAbsenceForMember(ServerContact member, ServerDay day) {
		TeamMember teamMember = members.get(member);
		if (teamMember != null) {
			teamMember.addAbsence(day);
		}
	}

	public List<ServerContact> getAllContacts() {
		return Lists.newArrayList(members.keySet());
	}

	public void setConfigurationForMember(ServerContact member, ConfigUser config) {
		TeamMember teamMember = members.get(member);
		teamMember.setConfiguration(config);
	}

	public List<TeamMember> getAllTeamMembers() {
		return Lists.newArrayList(members.values());
	}

	public List<Team> getAllTeams() {
		return Lists.newArrayList(teams.values());
	}
}
