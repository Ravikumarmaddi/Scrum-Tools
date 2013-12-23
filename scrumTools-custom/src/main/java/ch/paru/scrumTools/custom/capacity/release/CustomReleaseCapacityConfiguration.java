package ch.paru.scrumTools.custom.capacity.release;

import ch.paru.scrumTools.capacity.release.configuration.ReleaseCapacityConfiguration;
import ch.paru.scrumTools.capacity.shared.factories.ConfigUserFactory;
import ch.paru.scrumTools.custom.capacity.shared.CustomConfigUserFactory;

public class CustomReleaseCapacityConfiguration extends ReleaseCapacityConfiguration {

	private static final String FACTOR_ADMINISTRATION = "factorAdministration";
	private static final String FACTOR_EDUCATION = "factorEducation";
	private static final String FACTOR_3RDLEVELSUPPORT = "factor3rdLevelSupport";
	private static final String FACTOR_FEASIBILITYSUPPORT = "factorFeasibilitySupportNextRelease";

	protected CustomReleaseCapacityConfiguration(String fileName) {
		super(fileName);
	}

	public static void init(String fileName) {
		new CustomReleaseCapacityConfiguration(fileName);
	}

	public static CustomReleaseCapacityConfiguration getInstance() {
		return (CustomReleaseCapacityConfiguration) ReleaseCapacityConfiguration.getInstance();
	}

	@Override
	protected ConfigUserFactory getUserFactory() {
		return new CustomConfigUserFactory();
	}

	public Double getAdministrationFactor() {
		return getDoubleInSection(SECTION, FACTOR_ADMINISTRATION);
	}

	public Double getEducationFactor() {
		return getDoubleInSection(SECTION, FACTOR_EDUCATION);
	}

	public Double get3rdLevelSupportFactor() {
		return getDoubleInSection(SECTION, FACTOR_3RDLEVELSUPPORT);
	}

	public Double getFeasabilityNextReleaseFactor() {
		return getDoubleInSection(SECTION, FACTOR_FEASIBILITYSUPPORT);
	}
}
