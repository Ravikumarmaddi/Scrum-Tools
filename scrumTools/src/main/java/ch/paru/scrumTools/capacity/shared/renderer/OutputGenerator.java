package ch.paru.scrumTools.capacity.shared.renderer;

import java.util.List;

public abstract class OutputGenerator {
	public abstract String getTeamTableHeader(List<String> columnNames);

	public abstract String getTeamTableMemberRow(List<String> values);

	public abstract void print(String content);

	public String getFooter() {
		return "";
	}

	public String getHeader() {
		return "";
	}
}
