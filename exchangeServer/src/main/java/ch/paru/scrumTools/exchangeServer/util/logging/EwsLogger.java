package ch.paru.scrumTools.exchangeServer.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EwsLogger {

	static {
		System.setProperty("org.slf4j.simpleLogger.showDateTime", "true");
		System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", "HH:mm:ss,SSS");
		System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
	}

	public static final Logger getLogger(Class<?> clazz) {
		return getLoggerByName(clazz.getSimpleName());
	}

	public static final Logger getTimeLogger() {
		return getLoggerByName("Timer");
	}

	private static Logger getLoggerByName(String name) {
		Logger logger = LoggerFactory.getLogger(name);
		return logger;
	}

}
