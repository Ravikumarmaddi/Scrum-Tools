package ch.paru.scrumTools.capacity.sprint;

import ch.paru.scrumTools.capacity.shared.commandLine.CommandLineParser;
import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;
import ch.paru.scrumTools.backendServer.utils.ServerDayUtil;

public class SprintCapacityApplication {

	public static void main(String[] args) {
		CommandLineParser parser = new CommandLineParser();
		parser.parse(args, 3);

		ServerDay startDay = ServerDayUtil.createDayFromDate(parser.getStartDate());
		ServerDay endDay = ServerDayUtil.createDayFromDate(parser.getEndDate());

		new SprintCapacityManager(startDay, endDay).start(parser.getConfigFile());
	}
}
