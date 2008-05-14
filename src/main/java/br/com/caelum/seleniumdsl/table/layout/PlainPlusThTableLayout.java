package br.com.caelum.seleniumdsl.table.layout;

import br.com.caelum.seleniumdsl.table.Table;

import com.thoughtworks.selenium.Selenium;

public class PlainPlusThTableLayout implements TableLayout {

	private TableLayoutHelper helper;

	public PlainPlusThTableLayout(Selenium selenium, String id, String type) {
		helper = new TableLayoutHelper(selenium, id, type);
	}

	public int getContentCount() {
		return getRowCount() - 1;
	}

	public int getColCount() {
		return helper.countXPath("/tbody/tr/th");
	}

	public String headerValue(int col) {
		return helper.getXPathText("/*/tr[1]/th[" + col + "]");
	}

	public String headerLinkValue(int col) {
		return helper.getXPathText("/*/tr[1]/th[" + col + "]/a/text()");
	}

	public String value(int row, int col) {
		return helper.getXPathText("/*/tr[" + (row + 1) + "]/td[" + col + "]");
	}

	public int getRowCount() {
		return helper.getRowCount();
	}

	public boolean contains(Table table, String col, String content) {
		return helper.contains(table, col, content);
	}
}
