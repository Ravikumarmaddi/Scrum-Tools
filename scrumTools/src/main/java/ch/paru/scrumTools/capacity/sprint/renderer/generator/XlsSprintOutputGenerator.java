package ch.paru.scrumTools.capacity.sprint.renderer.generator;

import java.util.List;

public class XlsSprintOutputGenerator extends SprintOutputGenerator {

	protected static final String NEWLINE = "\r\n";
	protected static final String TAB = "\t";

	@Override
	public String getTeamTableHeader(List<String> columnNames) {
		StringBuffer sb = new StringBuffer();
		for (String columnName : columnNames) {
			sb.append(columnName);
			sb.append(TAB);
		}
		sb.append(NEWLINE);
		return sb.toString();
	}

	@Override
	public String getTeamTableMemberRow(List<String> values) {
		StringBuffer sb = new StringBuffer();
		for (String value : values) {
			sb.append(value);
			sb.append(TAB);
		}
		sb.append(NEWLINE);
		return sb.toString();
	}

	@Override
	public void print(String content) {
		System.out.println(content);
	}
}
