package ch.paru.scrumTools.backendServer.services.contact;

import java.util.List;

import ch.paru.scrumTools.backendServer.utils.interceptors.InterceptedService;

public interface ContactService extends InterceptedService {

	List<ServerContactGroup> getAllContactGroups();
}
