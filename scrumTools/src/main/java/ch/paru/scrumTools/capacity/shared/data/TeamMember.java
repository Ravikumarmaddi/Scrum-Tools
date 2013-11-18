package ch.paru.scrumTools.capacity.shared.data;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.exchangeServer.services.calendar.ServerDay;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;

import com.google.common.collect.Lists;

public class TeamMember {

	private ServerContact contact;
	private ConfigUser config;
	private List<ServerDay> absences;
	private double capacityInHours; //TODO remove hours
	private double rawCapacityHours; //TODO remove hours
	private double calculationFactor;

	TeamMember(ServerContact contact) {
		this.contact = contact;
		absences = Lists.newArrayList();
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

	public void setCapacity(double capacity) {
		this.capacityInHours = capacity;
	}

	public double getCapacity() {
		return capacityInHours;
	}

	@Override
	public String toString() {
		return config.getName();
	}

	public double getCalculationFactor() {
		return calculationFactor;
	}

	public void setCalculationFactor(double calculationFactor) {
		this.calculationFactor = calculationFactor;
	}

	public double getRawCapacityHours() {
		return rawCapacityHours;
	}

	public void setRawCapacityHours(double rawCapacityHours) {
		this.rawCapacityHours = rawCapacityHours;
	}

}
