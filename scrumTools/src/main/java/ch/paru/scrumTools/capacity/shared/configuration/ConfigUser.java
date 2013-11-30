package ch.paru.scrumTools.capacity.shared.configuration;

import ch.paru.scrumTools.common.reflection.customs.Customizable;

import com.google.common.base.Objects;

public class ConfigUser implements Customizable {
	public static final String NAME = "name";
	public static final String ROLE = "role";
	public static final String CAPACITY = "capacity";
	public static final String COMMENT = "comment";

	private String mailAddress;
	private String name;
	private ConfigRole role;
	private Double capacity;
	private String comment;

	public ConfigUser(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ConfigRole getRole() {
		return role;
	}

	public void setRole(ConfigRole role) {
		this.role = role;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("mailAddress", getMailAddress()).add("name", getName())
				.add("role", getRole()).add("capacity", getCapacity()).add("comment", getComment()).toString();
	}

}
