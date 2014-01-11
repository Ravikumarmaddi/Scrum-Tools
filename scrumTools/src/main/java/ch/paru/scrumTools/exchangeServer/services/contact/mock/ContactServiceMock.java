package ch.paru.scrumTools.exchangeServer.services.contact.mock;

import java.util.List;

import microsoft.exchange.webservices.data.ExchangeService;
import ch.paru.scrumTools.exchangeServer.services.contact.ContactService;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContactGroup;
import ch.paru.scrumTools.exchangeServer.services.mock.MockData;
import ch.paru.scrumTools.exchangeServer.utils.interceptors.AbstractInterceptedService;

import com.google.common.collect.Lists;

public class ContactServiceMock extends AbstractInterceptedService implements ContactService {

	public ContactServiceMock(ExchangeService server) {
	}

	@Override
	public List<ServerContactGroup> getAllContactGroups() {
		List<ServerContactGroup> groups = Lists.newArrayList();
		groups.add(MockData.GROUP_NORTH);
		groups.add(MockData.GROUP_SOUTH);
		groups.add(MockData.GROUP_WEST);
		return groups;
	}

}
