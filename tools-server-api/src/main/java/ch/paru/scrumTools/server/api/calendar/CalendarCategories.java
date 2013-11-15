package ch.paru.scrumTools.server.api.calendar;

import ch.paru.scrumTools.server.api.exceptions.EchangeServerException;

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
		//FIXME configurationService
		//ExchangeServerConfiguration config = ExchangeServerConfiguration.getInstance();
		String categoryName = null; //config.getCalendarCategory(categoryKey);

		if (Strings.isNullOrEmpty(categoryName)) {
			throw new EchangeServerException("no category name defined for '" + categoryKey + "'", null);
		}
		else {
			return categoryName;
		}
	}
}
