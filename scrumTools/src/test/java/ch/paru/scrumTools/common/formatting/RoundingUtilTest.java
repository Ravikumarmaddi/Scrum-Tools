package ch.paru.scrumTools.common.formatting;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class RoundingUtilTest {

	@Test
	public void round_evens() throws Exception {
		BigDecimal actual = RoundingUtil.round(1.0);
		assertEquals(new BigDecimal("1.00"), actual);
	}

	@Test
	public void round_below_1() throws Exception {
		BigDecimal actual = RoundingUtil.round(0.111);
		assertEquals(new BigDecimal("0.11"), actual);
	}

	@Test
	public void round_up_1() throws Exception {
		BigDecimal actual = RoundingUtil.round(1.115);
		assertEquals(new BigDecimal("1.11"), actual);
	}

	@Test
	public void round_decimal_down() throws Exception {
		BigDecimal actual = RoundingUtil.round(0.567890);
		assertEquals(new BigDecimal("0.57"), actual);
	}

	@Test
	public void round_normal_down() throws Exception {
		BigDecimal actual = RoundingUtil.round(14.892);
		assertEquals(new BigDecimal("14.89"), actual);
	}

	@Test
	public void round_Full_1() throws Exception {
		BigDecimal actual = RoundingUtil.roundFullUp(14.89);
		assertEquals(new BigDecimal(15.0), actual);
	}

	@Test
	public void round_Full_2() throws Exception {
		BigDecimal actual = RoundingUtil.roundFullUp(14.2);
		assertEquals(new BigDecimal(14.0), actual);
	}

	@Test
	public void round_Full_5() throws Exception {
		BigDecimal actual = RoundingUtil.roundFullUp(14.5);
		assertEquals(new BigDecimal(15.0), actual);
	}

	@Test
	public void round_Full_51() throws Exception {
		BigDecimal actual = RoundingUtil.roundFullUp(14.61);
		assertEquals(new BigDecimal(15.0), actual);
	}

}
