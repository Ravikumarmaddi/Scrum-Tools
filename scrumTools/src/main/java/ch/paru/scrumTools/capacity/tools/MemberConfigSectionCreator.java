package ch.paru.scrumTools.capacity.tools;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.commandLine.CommandLineParser;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.configuration.SectionHeaders;
import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.capacity.shared.data.collector.TeamDataCollector;
import ch.paru.scrumTools.capacity.shared.factories.TeamFactory;
import ch.paru.scrumTools.capacity.shared.factories.TeamMemberFactory;
import ch.paru.scrumTools.capacity.sprint.configuration.SprintCapacityConfiguration;
import ch.paru.scrumTools.backendServer.manager.ServerInstance;
import ch.paru.scrumTools.backendServer.services.contact.ContactService;
import ch.paru.scrumTools.backendServer.services.contact.ServerContact;

public class MemberConfigSectionCreator {

	private MemberConfigSectionCreator(String[] args) {
		CommandLineParser parser = new CommandLineParser();
		parser.parse(args, 1);

		ServerInstance.init(parser.getConfigFile());
		SprintCapacityConfiguration.init(parser.getConfigFile());
	}

	private void run() {
		DataBox data = new DataBox(new TeamFactory(), new TeamMemberFactory());
		SprintCapacityConfiguration config = SprintCapacityConfiguration.getInstance();
		loadTeamData(data, config.getTeams());

		createConfig(data);
	}

	private void createConfig(DataBox data) {
		SectionHeaders sectionHeaders = new SectionHeaders();

		List<ServerContact> allContacts = data.getAllContacts();
		for (ServerContact contact : allContacts) {
			String sectionName = sectionHeaders.getUserSectionName(contact.getMailAddress());

			System.out.println("[" + sectionName + "]");
			System.out.println(ConfigUser.NAME + "=");
			System.out.println(ConfigUser.ROLE + "=");
			System.out.println();
		}
	}

	private void loadTeamData(DataBox data, List<String> teams) {
		ServerInstance server = ServerInstance.getInstance();
		ContactService contactService = server.getContactService();
		TeamDataCollector collector = new TeamDataCollector(contactService);
		collector.loadTeams(data, teams);

	}

	public static void main(String[] args) {
		MemberConfigSectionCreator creator = new MemberConfigSectionCreator(args);
		creator.run();
	}
}
