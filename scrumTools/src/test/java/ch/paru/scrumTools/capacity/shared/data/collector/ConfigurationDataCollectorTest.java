package ch.paru.scrumTools.capacity.shared.data.collector;

import static org.easymock.EasyMock.expect;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import ch.paru.scrumTools.capacity.shared.configuration.CapacityConfiguration;
import ch.paru.scrumTools.capacity.shared.configuration.ConfigUser;
import ch.paru.scrumTools.capacity.shared.data.DataBox;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;

import com.google.common.collect.Lists;

public class ConfigurationDataCollectorTest {

	private static final EasyMockSupport MOCKS = new EasyMockSupport();
	private static final DataBox DATA_BOX_MOCK = MOCKS.createMock("DATA_BOX_MOCK", DataBox.class);
	private static final CapacityConfiguration CONFIG_MOCK = MOCKS.createMock("CONFIG_MOCK",
			CapacityConfiguration.class);

	@Test
	public void testLoadConfiguration() {
		ServerContact contactFritz = MockData.CONTACT_FRITZ;
		ConfigUser userFritz = new ConfigUser(contactFritz.getMailAddress());

		MOCKS.resetAll();
		expect(DATA_BOX_MOCK.getAllContacts()).andReturn(Lists.newArrayList(contactFritz));
		expect(CONFIG_MOCK.getUser(contactFritz.getMailAddress())).andReturn(userFritz);
		DATA_BOX_MOCK.setConfigurationForMember(contactFritz, userFritz);

		MOCKS.replayAll();
		ConfigurationDataCollector collector = new ConfigurationDataCollector(CONFIG_MOCK);
		collector.loadConfiguration(DATA_BOX_MOCK);

		MOCKS.verifyAll();
	}

}
