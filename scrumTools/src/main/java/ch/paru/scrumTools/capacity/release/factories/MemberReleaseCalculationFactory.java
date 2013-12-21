package ch.paru.scrumTools.capacity.release.factories;

import ch.paru.scrumTools.capacity.release.data.ReleaseData;
import ch.paru.scrumTools.capacity.release.data.calculator.MemberReleaseCalculation;
import ch.paru.scrumTools.common.reflection.customs.AbstractFactory;
import ch.paru.scrumTools.common.reflection.customs.CustomFactory;
import ch.paru.scrumTools.exchangeServer.services.calendar.CalendarService;

@CustomFactory
public class MemberReleaseCalculationFactory extends AbstractFactory {

	public MemberReleaseCalculation createCalculator(ReleaseData data, CalendarService calendarService) {
		Class<? extends MemberReleaseCalculation> instanceClass = getClassToUse(MemberReleaseCalculation.class);
		Class<?>[] types = new Class<?>[] { ReleaseData.class, CalendarService.class };
		Object[] params = new Object[] { data, calendarService };
		return getInstance(instanceClass, types, params);
	}
}
