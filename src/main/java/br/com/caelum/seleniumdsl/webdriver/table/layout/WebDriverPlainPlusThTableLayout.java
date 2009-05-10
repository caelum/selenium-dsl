package br.com.caelum.seleniumdsl.webdriver.table.layout;

import org.openqa.selenium.WebDriver;

import br.com.caelum.seleniumdsl.table.Table;
import br.com.caelum.seleniumdsl.table.layout.TableLayout;

public class WebDriverPlainPlusThTableLayout implements TableLayout {

	private final WebDriverTableLayoutHelper helper;

	public WebDriverPlainPlusThTableLayout(final WebDriver webDriver, final String id, final String type) {
		helper = new WebDriverTableLayoutHelper(webDriver, id, type);
	}

	public int getContentCount() {
		return getRowCount() - 1;
	}

	public int getColCount() {
		return helper.countXPath("/tbody/tr/th");
	}

	public String headerValue(final int col) {
		return helper.getXPathText("/*/tr[1]/th[" + col + "]");
	}

	public String headerLinkValue(final int col) {
		return helper.getTableElementByXPathText("/*/tr[1]/th[" + col + "]/a").getText();
	}

	public String value(final int row, final int col) {
		return helper.getXPathText("/*/tr[" + (row + 1) + "]/td[" + col + "]");
	}

	public int getRowCount() {
		return helper.getRowCount();
	}

	public boolean contains(final Table table, final String col, final String content) {
		return helper.contains(table, col, content);
	}
}
