package ch.paru.scrumTools.exchangeServer.services.contact;

import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class EwsContactGroup {

	private String name;
	private List<EwsContact> members;

	public EwsContactGroup(String name) {
		this.name = name;
		members = Lists.newArrayList();
	}

	public String getName() {
		return name;
	}

	public void addMember(EwsContact contact) {
		members.add(contact);
	}

	public List<EwsContact> getMembers() {
		return ImmutableList.copyOf(members);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name, members);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		EwsContactGroup other = (EwsContactGroup) obj;
		if (!getName().equals(other.getName())) {
			return false;
		}
		return Objects.equal(members, other.members);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("name", name).add("members", members).toString();
	}
}
