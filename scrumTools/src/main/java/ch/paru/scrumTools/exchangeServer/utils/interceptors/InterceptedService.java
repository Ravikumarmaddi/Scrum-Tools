package ch.paru.scrumTools.exchangeServer.utils.interceptors;

import ch.paru.scrumTools.exchangeServer.utils.interceptors.command.InterceptorCommand;

public interface InterceptedService {

	// only 1 method allow, otherwise listening mechanism will not longer work
	void runInterceptorCommand(InterceptorCommand command);
}
