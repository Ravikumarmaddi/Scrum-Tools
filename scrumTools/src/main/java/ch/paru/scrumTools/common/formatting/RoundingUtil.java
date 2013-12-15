package ch.paru.scrumTools.common.formatting;

import java.math.BigDecimal;

public class RoundingUtil {

	public static BigDecimal round(double input) {
		BigDecimal roundedOutput = new BigDecimal(input).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return roundedOutput;
	}

	public static BigDecimal roundFullUp(double input) {
		BigDecimal roundedOutput = new BigDecimal(input).setScale(0, BigDecimal.ROUND_HALF_UP);
		return roundedOutput;
	}

}
