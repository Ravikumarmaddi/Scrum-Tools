package ch.paru.scrumTools.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolLoggerFactory {

	static {
		System.setProperty("org.slf4j.simpleLogger.showDateTime", "true");
		System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", "HH:mm:ss,SSS");
		System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
	}

	public static final ToolLogger getLogger(Class<?> clazz) {
		return getLoggerByName(clazz.getSimpleName());
	}

	public static final ToolLogger getTimeLogger() {
		return getLoggerByName("Timer");
	}

	private static ToolLogger getLoggerByName(String name) {
		Logger logger = LoggerFactory.getLogger(name);
		ToolLogger toolLogger = new ToolLogger(logger);
		return toolLogger;
	}

}
