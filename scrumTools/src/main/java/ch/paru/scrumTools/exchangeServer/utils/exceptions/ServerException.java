package ch.paru.scrumTools.exchangeServer.utils.exceptions;

import ch.paru.scrumTools.common.exception.ToolException;

public class ServerException extends ToolException {

	public ServerException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
