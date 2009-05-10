package br.com.caelum.seleniumdsl.webdriver.table;

import br.com.caelum.seleniumdsl.table.Column;
import br.com.caelum.seleniumdsl.table.Table;

public class WebDriverColumn implements Column {

	private final Table table;
	private final int columnNumber;

	public WebDriverColumn(final Table table, final int columnNumber) {
		this.table = table;
		this.columnNumber = columnNumber;
	}

	public boolean contains(final String value) {
		return find(value) != -1;
	}

	public int find(final String value) {
		final int rowCount = table.getRowCount();
		for (int i = 1; i < rowCount; i++) {
			if (table.cell(i, columnNumber)
					.contains(value)) {
				return i;
			}
		}
		return -1;
	}

	public boolean containsPartial(final String value) {
		final int rowCount = table.getRowCount();
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
