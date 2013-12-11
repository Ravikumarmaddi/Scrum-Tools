package ch.paru.scrumTools.capacity.sprint.renderer;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.renderer.OutputGenerator;

public class XlsSprintOutputGenerator extends OutputGenerator {

	@Override
	public String getTeamTableHeader(List<String> columnNames) {
		StringBuffer sb = new StringBuffer();
		for (String columnName : columnNames) {
			sb.append(columnName);
			sb.append("\t");
		}
		sb.append("\r\n");
		return sb.toString();
	}

	@Override
	public String getTeamTableMemberRow(List<String> values) {
		StringBuffer sb = new StringBuffer();
		for (String value : values) {
			sb.append(value);
			sb.append("\t");
		}
		sb.append("\r\n");
		return sb.toString();
	}

	@Override
	public void print(String content) {
		System.out.println(content);
	}
}
