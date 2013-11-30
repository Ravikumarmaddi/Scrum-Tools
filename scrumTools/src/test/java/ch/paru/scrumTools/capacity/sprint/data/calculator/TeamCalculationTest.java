package ch.paru.scrumTools.capacity.sprint.data.calculator;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.capacity.sprint.data.SprintData;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;

import com.google.common.collect.Lists;

public class TeamCalculationTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final SprintData DATA_MOCK = MOCKS.createMock("DATA_MOCK", SprintData.class);

	@Test
	public void testCalculateTeam() {
		Team teamA = new Team("Team A");
		TeamMember memberA1 = new TeamMember(MockData.CONTACT_FRITZ);
		memberA1.setCapacity(20);
		teamA.addTeamMember(memberA1);
		TeamMember memberA2 = new TeamMember(MockData.CONTACT_HANS);
		memberA2.setCapacity(15);
		teamA.addTeamMember(memberA2);

		Team teamB = new Team("Team B");
		TeamMember memberB1 = new TeamMember(MockData.CONTACT_PAUL);
		memberB1.setCapacity(10);
		teamB.addTeamMember(memberB1);
		TeamMember memberB2 = new TeamMember(MockData.CONTACT_PETER);
		memberB2.setCapacity(0);
		teamB.addTeamMember(memberB2);
		TeamMember memberB3 = new TeamMember(MockData.CONTACT_URS);
		memberB3.setCapacity(18);
		teamB.addTeamMember(memberB3);

		MOCKS.resetAll();
		expect(DATA_MOCK.getAllTeams()).andReturn(Lists.newArrayList(teamA, teamB));

		MOCKS.replayAll();
		TeamCalculation calculator = new TeamCalculation(DATA_MOCK);
		calculator.calculateAllCapacities();

		MOCKS.verifyAll();
		assertEquals(35, teamA.getCapacity(), 0);
		assertEquals(28, teamB.getCapacity(), 0);
	}

}
