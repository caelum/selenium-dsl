package br.com.caelum.seleniumdsl.webdriver;

import org.openqa.selenium.WebDriver;

import br.com.caelum.seleniumdsl.Browser;
import br.com.caelum.seleniumdsl.Page;

public class WebDriverBrowser implements Browser {

	private final WebDriver webDriver;
	private final int timeout;

	public WebDriverBrowser(final WebDriver webDriver) {
		this(webDriver, 10000);
	}

	public WebDriverBrowser(final WebDriver webDriver, final int timeout) {
		this.webDriver = webDriver;
		this.timeout = timeout;
	}

	public Page open(final String url) {
		webDriver.get(url);
		return currentPage();
	}

	public Page currentPage() {
		return new WebDriverPage(webDriver, timeout);
	}

	public Object getDelegate() {
		return webDriver;
	}

	public Page waitForPageLoad(final long timeout) {
		return currentPage();
	}

	public Page window(final String id) {
		return new WebDriverPage(webDriver.switchTo().window(id), 0);
	}
}
