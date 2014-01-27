package ch.paru.scrumTools.capacity.shared.configuration;

public class SectionHeaders {

	private static final String PREFIX_USER = "USER-";
	private static final String PREFIX_ROLE = "ROLE-";

	public String getUserSectionName(String mailAddress) {
		String result = mailAddress.substring(0, mailAddress.indexOf("@"));
		result = result.replace(".", "_");
		return PREFIX_USER + result;
	}

	public String getRoleSectionName(String name) {
		return PREFIX_ROLE + name;
	}
}
