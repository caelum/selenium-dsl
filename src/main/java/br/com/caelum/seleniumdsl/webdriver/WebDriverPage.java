package br.com.caelum.seleniumdsl.webdriver;

import java.io.File;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.caelum.seleniumdsl.ContentTag;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.js.Array;
import br.com.caelum.seleniumdsl.table.Table;
import br.com.caelum.seleniumdsl.webdriver.table.WebDriverTable;

public class WebDriverPage implements Page {

	private final WebDriver webDriver;

	public WebDriverPage(final WebDriver webDriver, final int timeout) {
		this.webDriver = webDriver;
	}

	public String title() {
		return webDriver.getTitle();
	}

	public Form form(final String id) {
		return new WebDriverForm(webDriver, id.equals("") ? "" : id + ".");
	}

	public ContentTag div(final String id) {
		return new WebDriverContentTag(webDriver, id);
	}

	public ContentTag span(final String id) {
		return new WebDriverContentTag(webDriver, id);
	}

	public Table table(final String id) {
		return new WebDriverTable(webDriver, id);
	}

	public Page navigate(final String link) {
		webDriver.findElement(new ByIdOrNameOrXPath(link)).click();
		return this;
	}

	public Page click(final String element) {
		webDriver.findElement(new ByIdOrNameOrXPath(element)).click();
		return this;
	}

	public boolean hasLink(final String link) {
		return webDriver.findElement(By.linkText(link)) != null;
	}

	public Array array(final String name) {
		throw new NotImplementedException();
	}

	public String invoke(final String cmd) {
		throw new NotImplementedException();
	}

	public void screenshot(final String filename) {
		if(webDriver instanceof FirefoxDriver) {
			((FirefoxDriver) webDriver).saveScreenshot(new File(filename));
		} else {
			throw new NotImplementedException();
		}
	}

	public Page waitUntil(final String condition, final long timeout) {
		throw new NotImplementedException();
	}

	public Page refresh() {
		webDriver.navigate().refresh();
		return this;
	}

	public Page clickLink(final String text) {
		webDriver.findElement(new ByLink(text)).click();
		return this;
	}

	public Page doubleClick(final String element) {
		throw new NotImplementedException();
	}

	public Page dragAndDrop(final String fromElement, final String toElement) {
		throw new NotImplementedException();
	}

	public Page mouseDown(final String element) {
		throw new NotImplementedException();
	}

	public Page mouseUp(final String element) {
		throw new NotImplementedException();
	}

	public Page navigateLink(final String text) {
		clickLink(text);
		return this;
	}
}
