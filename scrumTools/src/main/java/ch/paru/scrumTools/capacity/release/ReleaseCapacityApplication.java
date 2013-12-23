package ch.paru.scrumTools.capacity.release;

import ch.paru.scrumTools.capacity.shared.commandLine.CommandLineParser;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.utils.ServerDayUtil;

public class ReleaseCapacityApplication {

	public static void main(String[] args) {
		CommandLineParser parser = new CommandLineParser();
		parser.parse(args);

		ServerDay startDay = ServerDayUtil.createDayFromDate(parser.getStartDate());
		ServerDay endDay = ServerDayUtil.createDayFromDate(parser.getEndDate());

		new ReleaseCapacityManager(startDay, endDay).start(parser.getConfigFile());
	}
}