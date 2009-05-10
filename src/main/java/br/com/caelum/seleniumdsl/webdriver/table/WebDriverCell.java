package br.com.caelum.seleniumdsl.webdriver.table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.caelum.seleniumdsl.table.Cell;
import br.com.caelum.seleniumdsl.table.Table;

import com.thoughtworks.selenium.SeleniumException;

public class WebDriverCell implements Cell {

	private final Table table;
	private final int row;
	private final int col;
	private final WebDriver webDriver;

	public WebDriverCell(final WebDriver webDriver, final Table table, final int row, final int col) {
		this.webDriver = webDriver;
		this.table = table;
		this.row = row;
		this.col = col;
	}

	public String value() {
		return table.getLayout().value(row, col);
	}

	public String getLink() {
		return webDriver.findElement(By.xpath(getXPath() + "/a")).getAttribute("href");
	}

	public String headerValue() {
		try {
			return table.getLayout().headerValue(col);
		} catch (final SeleniumException e) {
			return headerLinkValue();
		}
	}

	public String headerLinkValue() {
		return table.getLayout().headerLinkValue(col);
	}

	private String getXPath() {
		return "//table[@" + table.getType() + "='" + table.getId() + "']/*/tr[" + row + "]/td[" + col + "]";
	}

	public Cell check() {
		webDriver.findElement(By.xpath(getXPath() + "/input")).toggle();
		return this;
	}

	public Cell uncheck() {
		final WebElement check = webDriver.findElement(By.xpath(getXPath() + "/input"));
		if(check.isSelected()) {
			check.toggle();
		}

		return this;
	}

	public boolean checked() {
		return webDriver.findElement(By.xpath(getXPath() + "/input")).isSelected();
	}

	public boolean contains(final String content) {
		return content.equals(value());
	}

}
