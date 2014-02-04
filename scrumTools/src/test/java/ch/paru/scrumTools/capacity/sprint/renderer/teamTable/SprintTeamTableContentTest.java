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
import ch.paru.scrumTools.capacity.shared.data.Numbers;
import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;

public class SprintTeamTableContentTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static TeamMember MEMBER_MOCK = MOCKS.createMock("MEMBER_MOCK", TeamMember.class);
	private static Team TEAM_MOCK = MOCKS.createMock("TEAM_MOCK", Team.class);
	private static ConfigUser CONFIG_USER_MOCK = MOCKS.createMock("CONFIG_USER_MOCK", ConfigUser.class);
	private static ConfigRole CONFIG_ROLE_MOCK = MOCKS.createMock("CONFIG_ROLE_MOCK", ConfigRole.class);
	private static final Numbers NUMBERS_MOCK = MOCKS.createMock("NUMBERS_MOCK", Numbers.class);

	@Test
	public void testAmountOfElements() {
		MOCKS.resetAll();
		expect(TEAM_MOCK.getName()).andReturn("NAME");
		expect(MEMBER_MOCK.getConfiguration()).andReturn(CONFIG_USER_MOCK);
		expect(MEMBER_MOCK.getNumbers()).andReturn(NUMBERS_MOCK);
		expect(NUMBERS_MOCK.getAvailability()).andReturn(10d);
		expect(NUMBERS_MOCK.getFinalCapacity()).andReturn(6d);
		expect(CONFIG_USER_MOCK.getRole()).andReturn(CONFIG_ROLE_MOCK);
		expect(CONFIG_USER_MOCK.getName()).andReturn("USERNAME");
		expect(CONFIG_ROLE_MOCK.getName()).andReturn("DEV");

		MOCKS.replayAll();
		SprintTeamTableContent tableContent = new SprintTeamTableContent(null);
		List<String> columnNames = tableContent.getColumnNames();
		List<String> memberRow = tableContent.getMemberRowValues(TEAM_MOCK, MEMBER_MOCK);

		MOCKS.verifyAll();
		assertNotNull(columnNames);
		assertNotNull(memberRow);
		assertEquals(5, columnNames.size());
		assertTrue(columnNames.size() == memberRow.size());
	}

}
