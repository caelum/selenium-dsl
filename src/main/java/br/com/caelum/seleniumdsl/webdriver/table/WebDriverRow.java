package br.com.caelum.seleniumdsl.webdriver.table;

import org.openqa.selenium.WebDriver;

import br.com.caelum.seleniumdsl.table.Cell;
import br.com.caelum.seleniumdsl.table.Row;
import br.com.caelum.seleniumdsl.table.Table;

public class WebDriverRow implements Row {

	private final Table table;
	private final WebDriver webDriver;
	private final int index;

	public WebDriverRow(final Table table, final WebDriver webDriver, final int index) {
		this.table = table;
		this.webDriver = webDriver;
		this.index = index;
	}

	public Cell cell(final int column) {
		return new WebDriverCell(webDriver, table, index, column);
	}

	public Cell cell(final String column) {
		final int columnNumber = table.findColumn(column);
		return new WebDriverCell(webDriver, table, index, columnNumber);
	}

	public Integer index() {
		return index;
	}
}