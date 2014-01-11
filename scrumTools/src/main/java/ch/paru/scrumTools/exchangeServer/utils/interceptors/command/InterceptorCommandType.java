package ch.paru.scrumTools.exchangeServer.utils.interceptors.command;

public enum InterceptorCommandType {
	LOAD_CACHE, STORE_CACHE;

	public String getConfigName() {
		return name().toLowerCase();
	}
}
