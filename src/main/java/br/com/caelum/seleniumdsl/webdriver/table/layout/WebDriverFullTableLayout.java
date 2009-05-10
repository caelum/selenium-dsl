package br.com.caelum.seleniumdsl.webdriver.table.layout;

import org.openqa.selenium.WebDriver;

import br.com.caelum.seleniumdsl.table.Table;
import br.com.caelum.seleniumdsl.table.layout.TableLayout;

public class WebDriverFullTableLayout implements TableLayout {

	private final WebDriverTableLayoutHelper helper;

	public WebDriverFullTableLayout(final WebDriver webDriver, final String id, final String type) {
		helper = new WebDriverTableLayoutHelper(webDriver, id, type);
	}

	public int getContentCount() {
		return helper.countXPath("/tbody/tr");
	}

	public int getColCount() {
		return helper.countXPath("/thead/tr/th");
	}

	public String headerValue(final int col) {
		return helper.getXPathText("/thead/tr[1]/th[" + col + "]");
	}

	public String headerLinkValue(final int col) {
		return helper.getTableElementByXPathText("/thead/tr[1]/th[" + col + "]/a").getText();
	}

	public String value(final int row, final int col) {
		return helper.getXPathText("/tbody/tr[" + row + "]/td[" + col + "]");
	}

	public int getRowCount() {
		return helper.getRowCount();
	}

	public boolean contains(final Table table, final String col, final String content) {
		return helper.contains(table, col, content);
	}
}
