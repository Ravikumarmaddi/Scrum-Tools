package ch.paru.scrumTools.capacity.shared.data.collector;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.configuration.CapacityConfiguration;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.backendServer.services.contact.ServerContact;

public class ConfigurationDataCollector {

	private CapacityConfiguration configuration;

	public ConfigurationDataCollector(CapacityConfiguration configuration) {
		this.configuration = configuration;
	}

	public void loadConfiguration(DataBox data) {
		List<ServerContact> allTeamMembers = data.getAllContacts();
		for (ServerContact member : allTeamMembers) {
			ConfigUser user = configuration.getUser(member.getMailAddress());
			data.setConfigurationForMember(member, user);
		}
	}
}
