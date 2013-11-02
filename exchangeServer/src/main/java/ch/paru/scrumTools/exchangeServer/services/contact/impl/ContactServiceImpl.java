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

import com.google.common.collect.Lists;
import ch.paru.scrumTools.exchangeServer.EchangeServerException;
import ch.paru.scrumTools.exchangeServer.services.contact.ContactService;
import ch.paru.scrumTools.exchangeServer.services.contact.EwsContact;
import ch.paru.scrumTools.exchangeServer.services.contact.EwsContactGroup;

public class ContactServiceImpl implements ContactService {

	private final ExchangeService server;

	public ContactServiceImpl(ExchangeService server) {
		this.server = server;
	}

	@Override
	public List<EwsContactGroup> getAllContactGroups() {
		try {
			List<EwsContactGroup> groups = Lists.newArrayList();

			FindItemsResults<Item> items = server.findItems(WellKnownFolderName.Contacts, new ItemView(100));
			for (Item item : items) {
				if (item instanceof ContactGroup) {
					groups.add(createGroup((ContactGroup) item));
				}
			}

			return groups;
		}
		catch (final Exception e) {
			throw new EchangeServerException("Contact Groups could not be loaded", e);
		}
	}

	private EwsContactGroup createGroup(ContactGroup group) throws Exception {
		EwsContactGroup contactGroup = new EwsContactGroup(group.getDisplayName());

		ExpandGroupResults expandGroup = server.expandGroup(group.getId());
		for (EmailAddress emailAddress : expandGroup) {
			contactGroup.addMember(new EwsContact(emailAddress.getAddress()));
		}

		return contactGroup;
	}
}
