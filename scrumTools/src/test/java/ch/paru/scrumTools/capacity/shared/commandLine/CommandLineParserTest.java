package ch.paru.scrumTools.capacity.shared.commandLine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.util.Locale;

import org.junit.Test;

import ch.paru.scrumTools.common.exception.ToolException;

public class CommandLineParserTest {

	@Test
	public void testNoParameter() {
		CommandLineParser parser = new CommandLineParser();
		try {
			parser.parse(new String[] {}, 3);
		}
		catch (ToolException e) {
			assertEquals("not exact amount of parameters", e.getMessage());
			return;
		}
		fail("no exception thrown");
	}

	@Test
	public void testCorrect3Parameter() {
		String configFile = "file.txt";
		String startDate = "11.12.2013";
		String endDate = "13.12.2013";

		CommandLineParser parser = new CommandLineParser();
		parser.parse(new String[] { configFile, startDate, endDate }, 3);

		assertEquals(configFile, parser.getConfigFile());
		DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("de", "CH"));
		assertEquals(startDate, dateFormatter.format(parser.getStartDate()));
		assertEquals(endDate, dateFormatter.format(parser.getEndDate()));
	}

	@Test
	public void testCorrect1Parameter() {
		String configFile = "file.txt";

		CommandLineParser parser = new CommandLineParser();
		parser.parse(new String[] { configFile }, 1);

		assertEquals(configFile, parser.getConfigFile());
	}

	@Test
	public void testTooMuchParameters() {
		String configFile = "file.txt";

		CommandLineParser parser = new CommandLineParser();
		try {
			parser.parse(new String[] { configFile, configFile, configFile }, 2);
		}
		catch (ToolException e) {
			assertEquals("not exact amount of parameters", e.getMessage());
			return;
		}
		fail("no exception thrown");
	}

	@Test
	public void testIncorrectDateFormat() {
		String configFile = "file.txt";
		String date = "12/11/2013";

		CommandLineParser parser = new CommandLineParser();
		parser.parse(new String[] { configFile, date, date }, 3);

		try {
			parser.getStartDate();
		}
		catch (ToolException e) {
			assertEquals("date parse failed", e.getMessage());
			return;
		}
		fail("no exception for incorrect ddate format");
	}
}
