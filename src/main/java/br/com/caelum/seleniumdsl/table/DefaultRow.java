package br.com.caelum.seleniumdsl.table;

import com.thoughtworks.selenium.Selenium;

public class DefaultRow implements Row {

	private final Table table;
	private final Selenium selenium;
	private final int index;

	public DefaultRow(Table table, Selenium selenium, int index) {
		this.table = table;
		this.selenium = selenium;
		this.index = index;
	}

	public Cell get(int column) {
		return new DefaultCell(selenium, table, index, column);
	}

	public Cell get(String column) {
		int columnNumber = table.findColumn(column);
		return new DefaultCell(selenium, table, index, columnNumber);
	}

	public Integer getIndex() {
		return index;
	}
}