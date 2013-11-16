package ch.paru.scrumTools.server.api.utils.exceptions;


public class ServerException extends RuntimeException {

	public ServerException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
