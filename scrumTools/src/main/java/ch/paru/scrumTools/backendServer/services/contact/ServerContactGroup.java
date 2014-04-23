package ch.paru.scrumTools.backendServer.services.contact;

import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class ServerContactGroup {

	private String name;
	private List<ServerContact> members;

	public ServerContactGroup(String name) {
		this.name = name;
		members = Lists.newArrayList();
	}

	public String getName() {
		return name;
	}

	public void addMember(ServerContact contact) {
		members.add(contact);
	}

	public List<ServerContact> getMembers() {
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

		ServerContactGroup other = (ServerContactGroup) obj;
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
