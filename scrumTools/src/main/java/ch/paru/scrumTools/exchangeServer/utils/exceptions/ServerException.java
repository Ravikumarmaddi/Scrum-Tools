package ch.paru.scrumTools.exchangeServer.utils.exceptions;


public class ServerException extends RuntimeException {

	public ServerException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
