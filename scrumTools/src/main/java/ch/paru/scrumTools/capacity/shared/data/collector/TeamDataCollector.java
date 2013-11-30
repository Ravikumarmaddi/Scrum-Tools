package ch.paru.scrumTools.capacity.shared.data.collector;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.exchangeServer.services.contact.ContactService;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContactGroup;

public class TeamDataCollector {

	private ContactService contactService;

	public TeamDataCollector(ContactService contactService) {
		this.contactService = contactService;
	}

	public void loadTeams(DataBox data, List<String> teamsToLoad) {
		List<ServerContactGroup> allContactGroups = contactService.getAllContactGroups();
		for (ServerContactGroup contactGroup : allContactGroups) {
			String teamName = contactGroup.getName();

			if (teamsToLoad.contains(teamName)) {
				List<ServerContact> members = contactGroup.getMembers();
				for (ServerContact member : members) {
					data.addTeamMember(teamName, member);
				}
			}
		}
	}
}