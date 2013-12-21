package ch.paru.scrumTools.capacity.shared.data.calculator;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.capacity.shared.data.Numbers;
import ch.paru.scrumTools.capacity.shared.data.Team;
import ch.paru.scrumTools.capacity.shared.data.TeamMember;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;

import com.google.common.collect.Lists;

public class TeamCalculationTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final DataBox DATA_MOCK = MOCKS.createMock("DATA_MOCK", DataBox.class);

	@Test
	public void testCalculateTeam() {
		Team teamA = new Team("Team A");
		teamA.addTeamMember(createMember(MockData.CONTACT_FRITZ, 22, 20, 15));
		teamA.addTeamMember(createMember(MockData.CONTACT_HANS, 17, 15, 10));

		Team teamB = new Team("Team B");
		teamB.addTeamMember(createMember(MockData.CONTACT_PAUL, 12, 10, 7.5));
		TeamMember memberB2 = new TeamMember(MockData.CONTACT_PETER);
		memberB2.getNumbers().setRawCapacity(0);
		teamB.addTeamMember(createMember(MockData.CONTACT_PETER, 0, 0, 0));
		TeamMember memberB3 = new TeamMember(MockData.CONTACT_URS);
		memberB3.getNumbers().setRawCapacity(18);
		teamB.addTeamMember(createMember(MockData.CONTACT_URS, 20, 18, 12));

		MOCKS.resetAll();
		expect(DATA_MOCK.getAllTeams()).andReturn(Lists.newArrayList(teamA, teamB));

		MOCKS.replayAll();
		TeamCalculation calculator = new TeamCalculation(DATA_MOCK);
		calculator.calculateAllCapacities();

		MOCKS.verifyAll();
		Numbers teamANumbers = teamA.getNumbers();
		assertEquals(39, teamANumbers.getAvailability(), 0);
		assertEquals(35, teamANumbers.getRawCapacity(), 0);
		assertEquals(25, teamANumbers.getFinalCapacity(), 0);

		Numbers teamBNumbers = teamB.getNumbers();
		assertEquals(32, teamBNumbers.getAvailability(), 0);
		assertEquals(28, teamBNumbers.getRawCapacity(), 0);
		assertEquals(19.5, teamBNumbers.getFinalCapacity(), 0);

	}

	private TeamMember createMember(ServerContact contact, double availability, double rawCapacity, double finalCapacity) {
		TeamMember member = new TeamMember(contact);
		Numbers numbers = member.getNumbers();
		numbers.setAvailability(availability);
		numbers.setRawCapacity(rawCapacity);
		numbers.setFinalCapacity(finalCapacity);
		return member;
	}

}
