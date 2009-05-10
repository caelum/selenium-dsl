package br.com.caelum.seleniumdsl.webdriver.table.layout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.caelum.seleniumdsl.table.layout.TableLayout;

public class WebDriverTableLayoutChooser {

	private final WebDriver webDriver;
	private final String id;
	private final String type;

	private static final String testThead = "id('%s')/thead";
	private static final String testTh = "id('%s')//th";

	public WebDriverTableLayoutChooser(final WebDriver webDriver, final String id, final String type) {
		this.webDriver = webDriver;
		this.id = id;
		this.type = type;
	}

	public TableLayout choose() {
		final int theadCount = webDriver.findElements(By.xpath(String.format(testThead, id))).size();
		final int thCount = webDriver.findElements(By.xpath(String.format(testTh, id))).size();

		TableLayout layout = null;
		if (theadCount <= 0 && thCount <= 0) {
			layout = new WebDriverPlainTableLayout(webDriver, id, type);
		} else if (theadCount <= 0 && thCount > 0) {
			layout = new WebDriverPlainPlusThTableLayout(webDriver, id, type);
		} else {
			layout = new WebDriverFullTableLayout(webDriver, id, type);
		}

		return layout;
	}
}
