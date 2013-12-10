package ch.paru.scrumTools.common.logging;

import org.slf4j.Logger;

public class ToolLogger {

	private Logger logger;

	ToolLogger(Logger logger) {
		this.logger = logger;
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void debug(String msg) {
		logger.debug(msg);
	}

	public void trace(String msg) {
		logger.trace(msg);
	}
}
