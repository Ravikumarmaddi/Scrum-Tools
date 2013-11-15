package ch.paru.scrumTools.server.api.calendar;

import ch.paru.scrumTools.server.api.configuration.ConfigurationKeys;
import ch.paru.scrumTools.server.api.configuration.ConfigurationService;
import ch.paru.scrumTools.server.api.exceptions.EchangeServerException;
import ch.paru.scrumTools.server.api.manager.ServerInstance;

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
			throw new EchangeServerException("no category name defined for '" + categoryKey + "'", null);
		}
		else {
			return categoryName;
		}
	}
}
