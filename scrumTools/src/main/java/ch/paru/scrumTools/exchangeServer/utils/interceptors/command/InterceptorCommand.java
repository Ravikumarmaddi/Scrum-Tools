package ch.paru.scrumTools.exchangeServer.utils.interceptors.command;

public abstract class InterceptorCommand {

	private InterceptorCommandType type;

	protected InterceptorCommand(InterceptorCommandType type) {
		this.type = type;
	}

	public InterceptorCommandType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		return type.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		InterceptorCommand other = (InterceptorCommand) obj;
		if (type != other.type) {
			return false;
		}
		return true;
	}

}
