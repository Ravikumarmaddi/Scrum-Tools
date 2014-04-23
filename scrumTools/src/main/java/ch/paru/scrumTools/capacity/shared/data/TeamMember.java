package ch.paru.scrumTools.capacity.shared.data;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.common.reflection.customs.Customizable;
import ch.paru.scrumTools.backendServer.services.calendar.ServerDay;
import ch.paru.scrumTools.backendServer.services.contact.ServerContact;

import com.google.common.collect.Lists;

public class TeamMember implements Customizable {

	private ServerContact contact;
	private ConfigUser config;
	private List<ServerDay> absences;
	private Numbers numbers;

	public TeamMember(ServerContact contact) {
		this.contact = contact;
		absences = Lists.newArrayList();
		numbers = new Numbers();
	}

	public ServerContact getContact() {
		return contact;
	}

	public void addAbsence(ServerDay day) {
		absences.add(day);
	}

	public void setConfiguration(ConfigUser config) {
		this.config = config;

	}

	public boolean isAvailable(ServerDay day) {
		return !absences.contains(day);
	}

	public ConfigUser getConfiguration() {
		return config;
	}

	public Numbers getNumbers() {
		return numbers;
	}

	public void setNumbers(Numbers numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return config != null ? config.getName() : contact.getMailAddress();
	}
}
