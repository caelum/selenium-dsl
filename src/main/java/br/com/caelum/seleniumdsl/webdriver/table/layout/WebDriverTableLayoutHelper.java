package br.com.caelum.seleniumdsl.webdriver.table.layout;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.caelum.seleniumdsl.table.Table;

class WebDriverTableLayoutHelper {

	private static final Logger log = Logger.getLogger(WebDriverTableLayoutHelper.class.getName());

	private final WebDriver webDriver;
	private final String id;
	private final String type;

	WebDriverTableLayoutHelper(final WebDriver webDriver, final String id, final String type) {
		this.webDriver = webDriver;
		this.id = id;
		this.type = type;
	}

	int getRowCount() {
		return countXPath("/*/tr");
	}

	int countXPath(final String expr) {
		return webDriver.findElements(By.xpath("//table[@" + type + "='" + id + "']" + expr)).size();
	}

	String getXPathText(final String expr) {
		try {
			return webDriver.findElement(By.xpath("//table[@" + type + "='" + id + "']" + expr)).getText();
		} catch (final Exception e) {
			log.info(e.getMessage());
		}

		return null;
	}

	boolean contains(final Table table, final String col, final String content) {
		for (int i = 1; i < table.getRowCount(); i++) {
			if (table.cell(i, col)
					.contains(content)) {
				return true;
			}
		}
		return false;
	}

	public WebElement getTableElementByXPathText(final String expr) {
		try {
			return webDriver.findElement(By.xpath("//table[@" + type + "='" + id + "']" + expr));
		} catch (final Exception e) {
			log.info(e.getMessage());
		}

		return null;
	}
}
