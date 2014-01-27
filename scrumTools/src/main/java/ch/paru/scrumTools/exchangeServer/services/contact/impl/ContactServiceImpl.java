package ch.paru.scrumTools.exchangeServer.services.contact.impl;

import java.util.List;

import microsoft.exchange.webservices.data.ContactGroup;
import microsoft.exchange.webservices.data.EmailAddress;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.ExpandGroupResults;
import microsoft.exchange.webservices.data.FindItemsResults;
import microsoft.exchange.webservices.data.Item;
import microsoft.exchange.webservices.data.ItemView;
import microsoft.exchange.webservices.data.WellKnownFolderName;
import ch.paru.scrumTools.common.logging.ToolLogger;
import ch.paru.scrumTools.common.logging.ToolLoggerFactory;
import ch.paru.scrumTools.exchangeServer.services.contact.ContactService;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContact;
import ch.paru.scrumTools.exchangeServer.services.contact.ServerContactGroup;
import ch.paru.scrumTools.exchangeServer.utils.exceptions.ServerException;
import ch.paru.scrumTools.exchangeServer.utils.interceptors.AbstractInterceptedService;

import com.google.common.collect.Lists;

public class ContactServiceImpl extends AbstractInterceptedService implements ContactService {

	private static final ToolLogger LOGGER = ToolLoggerFactory.getLogger(ContactServiceImpl.class);

	private final ExchangeService server;

	public ContactServiceImpl(ExchangeService server) {
		this.server = server;
	}

	@Override
	public List<ServerContactGroup> getAllContactGroups() {
		try {
			List<ServerContactGroup> groups = Lists.newArrayList();

			FindItemsResults<Item> items = server.findItems(WellKnownFolderName.Contacts, new ItemView(100));
			for (Item item : items) {
				if (item instanceof ContactGroup) {
					groups.add(createGroup((ContactGroup) item));
				}
			}

			return groups;
		}
		catch (final Exception e) {
			throw new ServerException("Contact Groups could not be loaded", e);
		}
	}

	private ServerContactGroup createGroup(ContactGroup group) throws Exception {
		ServerContactGroup contactGroup = new ServerContactGroup(group.getDisplayName());
		LOGGER.trace("Group Name: " + contactGroup.getName());

		ExpandGroupResults expandGroup = server.expandGroup(group.getId());
		for (EmailAddress emailAddress : expandGroup) {
			ServerContact contact = new ServerContact(emailAddress.getAddress());
			contactGroup.addMember(contact);
			LOGGER.trace("new contact to group: " + contact);
		}

		return contactGroup;
	}
}
