package ch.paru.scrumTools.capacity.shared.renderer.generator;

import java.util.List;

import ch.paru.scrumTools.capacity.shared.data.DataBox;

public abstract class OutputGenerator<T extends DataBox> {
	public abstract String getTeamTableHeader(List<String> columnNames);

	public abstract String getTeamTableMemberRow(List<String> values);

	public abstract void print(String content);

	public String getPostTableData(T data) {
		return "";
	}

	public String getPreTableData(T data) {
		return "";
	}
}
