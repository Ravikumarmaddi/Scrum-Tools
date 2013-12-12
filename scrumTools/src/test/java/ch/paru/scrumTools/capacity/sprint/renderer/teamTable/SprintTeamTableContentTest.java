package ch.paru.scrumTools.capacity.sprint.renderer.teamTable;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.ConfigRole;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.sprint.renderer.teamTable.SprintTeamTableContent;

public class SprintTeamTableContentTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static TeamMember MEMBER_MOCK = MOCKS.createMock("MEMBER_MOCK", TeamMember.class);
	private static Team TEAM_MOCK = MOCKS.createMock("TEAM_MOCK", Team.class);
	private static ConfigUser CONFIG_USER_MOCK = MOCKS.createMock("CONFIG_USER_MOCK", ConfigUser.class);
	private static ConfigRole CONFIG_ROLE_MOCK = MOCKS.createMock("CONFIG_ROLE_MOCK", ConfigRole.class);

	@Test
	public void testAmountOfElements() {
		MOCKS.resetAll();
		expect(MEMBER_MOCK.getTeam()).andReturn(TEAM_MOCK);
		expect(TEAM_MOCK.getName()).andReturn("NAME");
		expect(MEMBER_MOCK.getConfiguration()).andReturn(CONFIG_USER_MOCK).times(2);
		expect(MEMBER_MOCK.getAvailability()).andReturn(2d);
		expect(MEMBER_MOCK.getCapacity()).andReturn(4d);
		expect(CONFIG_USER_MOCK.getRole()).andReturn(CONFIG_ROLE_MOCK);
		expect(CONFIG_USER_MOCK.getName()).andReturn("USERNAME");
		expect(CONFIG_ROLE_MOCK.getName()).andReturn("DEV");

		MOCKS.replayAll();
		SprintTeamTableContent tableContent = new SprintTeamTableContent();
		List<String> columnNames = tableContent.getColumnNames();
		List<String> memberRow = tableContent.getMemberRowValues(MEMBER_MOCK);

		MOCKS.verifyAll();
		assertNotNull(columnNames);
		assertNotNull(memberRow);
		assertEquals(5, columnNames.size());
		assertTrue(columnNames.size() == memberRow.size());
	}

}
