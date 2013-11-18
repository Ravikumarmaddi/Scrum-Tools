package ch.paru.scrumTools.capacity.shared.data;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.exchangeServer.manager.ServerFacade;
import ch.paru.scrumTools.exchangeServer.services.contact.ContactService;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContactGroup;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;

import com.google.common.collect.Lists;

public class DataCollectorTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final ServerFacade SERVER_FACADE_MOCK = MOCKS.createMock("SERVER_FACADE_MOCK", ServerFacade.class);
	private static final ContactService CONTACT_SERVICE_MOCK = MOCKS.createMock("CONTACT_SERVICE_MOCK",
			ContactService.class);

	@Test
	public void testLoadTeams() {
		String groupName = "groupName";
		ServerContactGroup group = new ServerContactGroup(groupName);
		group.addMember(MockData.CONTACT_FRITZ);
		group.addMember(MockData.CONTACT_HANS);
		ArrayList<ServerContactGroup> contactGroups = Lists.newArrayList(group);
		DataBox box = new DataBox();
		MOCKS.resetAll();
		expect(SERVER_FACADE_MOCK.getContactService()).andReturn(CONTACT_SERVICE_MOCK);
		expect(CONTACT_SERVICE_MOCK.getAllContactGroups()).andReturn(contactGroups);
		MOCKS.replayAll();
		DataCollectorInstance instance = new DataCollectorInstance(SERVER_FACADE_MOCK);
		instance.loadTeams(box, Lists.newArrayList(groupName));
		MOCKS.verifyAll();
		List<Team> allTeams = box.getAllTeams();
		assertEquals(1, allTeams.size());
		Team team = allTeams.get(0);
		assertEquals(groupName, team.getName());
		List<TeamMember> allTeamMembers = box.getAllTeamMembers();
		assertEquals(2, allTeamMembers.size());
	}

}
