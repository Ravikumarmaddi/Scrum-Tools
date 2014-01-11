package ch.paru.scrumTools.exchangeServer.services.contact;

import java.util.List;

import ch.paru.scrumTools.exchangeServer.utils.interceptors.InterceptedService;

public interface ContactService extends InterceptedService {

	List<ServerContactGroup> getAllContactGroups();
}
