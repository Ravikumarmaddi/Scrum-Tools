package ch.paru.scrumTools.exchangeServer.services.contact;

import com.google.common.base.Objects;

public class ServerContact {
	private String mailAddress;

	public ServerContact(String mailAddress) {
		this.mailAddress = mailAddress.toLowerCase();
	}

	public String getMailAddress() {
		return mailAddress;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(mailAddress);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		ServerContact other = (ServerContact) obj;
		return getMailAddress().equals(other.getMailAddress());
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("mailAddress", mailAddress).toString();
	}
}
