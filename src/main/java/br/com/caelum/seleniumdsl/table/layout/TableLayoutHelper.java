package br.com.caelum.seleniumdsl.table.layout;

import br.com.caelum.seleniumdsl.table.Table;

import com.thoughtworks.selenium.Selenium;

public class TableLayoutHelper {
	private Selenium selenium;
	private String id;
	private String type;

	public TableLayoutHelper(Selenium selenium, String id, String type) {
		this.selenium = selenium;
		this.id = id;
		this.type = type;
	}

	protected int getRowCount() {
		return countXPath("/*/tr");
	}

	protected int countXPath(String expr) {
		return selenium.getXpathCount("//table[@" + type + "='" + id + "']/" + expr).intValue();
	}

	protected String getXPathText(String expr) {
		return selenium.getText("xpath=//table[@" + type + "='" + id + "']/" + expr);
	}

	public boolean contains(Table table, String col, String content) {
		for (int i = 1; i < table.getRowCount(); i++)
			if (table.cell(i, col).contains(content))
				return true;
		return false;
	}
}
