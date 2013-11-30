package ch.paru.scrumTools.capacity.shared.data;

import java.lang.reflect.Constructor;

import ch.paru.scrumTools.common.exception.ToolException;
import ch.paru.scrumTools.common.reflection.CustomFactory;
import ch.paru.scrumTools.common.reflection.ReflectionUtil;

@CustomFactory
public class TeamFactory {

	public Team createTeam(String teamName) {
		Class<? extends Team> teamClass = ReflectionUtil.getCustomClass(Team.class);
		if (teamClass == null) {
			teamClass = Team.class;
		}

		try {
			Constructor<? extends Team> constructor = teamClass.getConstructor(String.class);
			return constructor.newInstance(teamName);
		}
		catch (Exception e) {
			throw new ToolException("instanciation of class failed", e);
		}
	}
}
