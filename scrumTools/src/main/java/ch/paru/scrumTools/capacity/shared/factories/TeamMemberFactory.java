package ch.paru.scrumTools.capacity.shared.factories;

import java.lang.reflect.Constructor;

import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.common.exception.ToolException;
import ch.paru.scrumTools.common.reflection.CustomFactory;
import ch.paru.scrumTools.common.reflection.ReflectionUtil;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;

@CustomFactory
public class TeamMemberFactory {

	public TeamMember createTeamMember(ServerContact contact) {
		Class<? extends TeamMember> memberClass = ReflectionUtil.getCustomClass(TeamMember.class);
		if (memberClass == null) {
			memberClass = TeamMember.class;
		}

		try {
			Constructor<? extends TeamMember> constructor = memberClass.getConstructor(ServerContact.class);
			return constructor.newInstance(contact);
		}
		catch (Exception e) {
			throw new ToolException("instanciation of class failed", e);
		}
	}
}
