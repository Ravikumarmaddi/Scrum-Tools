package ch.paru.scrumTools.capacity.shared.factories;

import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;
import ch.paru.scrumTools.backendServer.services.contact.ServerContact;

@CustomFactory
public class TeamMemberFactory extends AbstractFactory {

	public TeamMember createTeamMember(ServerContact contact) {
		Class<? extends TeamMember> instanceClass = getClassToUse(TeamMember.class);
		return getInstance(instanceClass, contact);
	}
}
