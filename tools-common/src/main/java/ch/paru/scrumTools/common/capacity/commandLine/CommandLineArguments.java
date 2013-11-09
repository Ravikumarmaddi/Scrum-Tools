package ch.paru.scrumTools.common.capacity.commandLine;

public enum CommandLineArguments {
	CONFIG_FILE(0, "configuration file"), //
	START(1, "Day when the calculation starts (dd.MM.yyyy)"), //
	END(2, "Day when the calculation ends (dd.MM.yyyy)");

	private int index;
	private String message;

	private CommandLineArguments(int index, String message) {
		this.index = index;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getIndex() {
		return index;
	}

	public static CommandLineArguments[] getAllArguments() {
		CommandLineArguments[] values = values();
		CommandLineArguments[] args = new CommandLineArguments[values.length];

		for (CommandLineArguments arg : values) {
			args[arg.index] = arg;
		}

		return args;
	}
}
