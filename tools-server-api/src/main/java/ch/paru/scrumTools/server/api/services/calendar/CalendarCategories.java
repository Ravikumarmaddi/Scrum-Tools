package ch.paru.scrumTools.server.api.services.calendar;

import ch.paru.scrumTools.server.api.manager.ServerInstance;
import ch.paru.scrumTools.server.api.services.configuration.ConfigurationKeys;
import ch.paru.scrumTools.server.api.services.configuration.ConfigurationService;
import ch.paru.scrumTools.server.api.utils.exceptions.ServerException;

import com.google.common.base.Strings;

public enum CalendarCategories {
	ABSENCES("absences"), //
	PUBLIC_HOLIDAY("publicholiday"), //
	SPRINT_MEETINGS("sprintmeeting"), //
	SPRINT_START("sprintstart"), //
	SPRINT_END("sprintend");

	private String categoryKey;

	private CalendarCategories(String name) {
		this.categoryKey = name;
	}

	public String getCategoryName() {

		ServerInstance instance = ServerInstance.getInstance();
		ConfigurationService service = instance.getConfigurationService();
		String categoryName = service.getStringValue(ConfigurationKeys.CALENDAR_CATEGORY_PREFIX, categoryKey);

		if (Strings.isNullOrEmpty(categoryName)) {
			throw new ServerException("no category name defined for '" + categoryKey + "'", null);
		}
		else {
			return categoryName;
		}
	}
}
