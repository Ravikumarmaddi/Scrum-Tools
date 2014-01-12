package ch.paru.scrumTools.capacity.shared.data.collector;

import static org.easymock.EasyMock.expect;

import java.util.ArrayList;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.exchangeServer.services.contact.ContactService;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContactGroup;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;

import com.google.common.collect.Lists;

public class TeamDataCollectorTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final ContactService CONTACT_SERVICE_MOCK = MOCKS.createMock("CONTACT_SERVICE_MOCK",
			ContactService.class);
	private static final DataBox DATA_BOX_MOCK = MOCKS.createMock("DATA_BOX_MOCK", DataBox.class);

	@Test
	public void testLoadTeams() {
		String groupName = "groupName";
		ServerContactGroup group = new ServerContactGroup(groupName);
		ServerContact contactFritz = MockData.CONTACT_FRITZ;
		ServerContact contactHans = MockData.CONTACT_HANS;
		group.addMember(contactFritz);
		group.addMember(contactHans);
		ArrayList<ServerContactGroup> contactGroups = Lists.newArrayList(group);

		MOCKS.resetAll();
		expect(CONTACT_SERVICE_MOCK.getAllContactGroups()).andReturn(contactGroups);
		DATA_BOX_MOCK.addTeamMember(groupName, contactFritz);
		DATA_BOX_MOCK.addTeamMember(groupName, contactHans);

		MOCKS.replayAll();
		TeamDataCollector collector = new TeamDataCollector(CONTACT_SERVICE_MOCK);
		collector.loadTeams(DATA_BOX_MOCK, Lists.newArrayList(groupName));

		MOCKS.verifyAll();
	}
}
