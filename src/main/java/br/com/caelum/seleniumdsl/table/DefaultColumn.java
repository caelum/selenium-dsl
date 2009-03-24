package br.com.caelum.seleniumdsl.table;


public class DefaultColumn implements Column {

	private final Table table;

	private final int columnNumber;

	public DefaultColumn(Table table, int columnNumber) {
		this.table = table;
		this.columnNumber = columnNumber;
	}

	public boolean contains(String value) {
		return find(value) != -1;
	}

	public int find(String value) {
		int rowCount = table.getRowCount();
		for (int i = 1; i < rowCount; i++) {
			if (table.cell(i, columnNumber)
					.contains(value)) {
				return i;
			}
		}
		return -1;
	}

	public boolean containsPartial(String value) {
		int rowCount = table.getRowCount();
		for (int i = 1; i < rowCount; i++) {
			if (table.cell(i, columnNumber)
					.value()
					.contains(value)) {
				return true;
			}
		}
		return false;
	}
}
