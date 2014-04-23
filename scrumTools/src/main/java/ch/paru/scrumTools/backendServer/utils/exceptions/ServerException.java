package ch.paru.scrumTools.backendServer.utils.exceptions;

import ch.paru.scrumTools.common.exception.ToolException;

public class ServerException extends ToolException {

	public ServerException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
