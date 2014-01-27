package ch.paru.scrumTools.capacity.shared.commandLine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import ch.paru.scrumTools.common.exception.ToolException;
import ch.paru.scrumTools.common.logging.ToolLogger;
import ch.paru.scrumTools.common.logging.ToolLoggerFactory;

import com.google.common.collect.Maps;

public class CommandLineParser {

	private static final ToolLogger LOGGER = ToolLoggerFactory.getLogger(CommandLineParser.class);

	private Map<CommandLineArguments, String> values = Maps.newHashMap();

	public void parse(String[] args, int amount) {
		if (args == null || args.length != amount) {
			printHelp();
			throw new ToolException("not exact amount of parameters", null);
		}

		CommandLineArguments[] commandLineArgs = CommandLineArguments.getAllArguments();
		for (CommandLineArguments arg : commandLineArgs) {
			if (args.length - 1 >= arg.getIndex()) {
				values.put(arg, args[arg.getIndex()]);
			}
		}
	}

	public String getConfigFile() {
		return values.get(CommandLineArguments.CONFIG_FILE);
	}

	public Date getStartDate() {
		return parseDate(CommandLineArguments.START);
	}

	public Date getEndDate() {
		return parseDate(CommandLineArguments.END);
	}

	private Date parseDate(CommandLineArguments key) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			return format.parse(values.get(key));
		}
		catch (Exception e) {
			throw new ToolException("date parse failed", e);
		}
	}

	private void printHelp() {
		LOGGER.info("needed arguments:");
		CommandLineArguments[] args = CommandLineArguments.getAllArguments();
		for (CommandLineArguments arg : args) {
			LOGGER.info("\t" + arg.name() + ": " + arg.getMessage());
		}
	}
}
