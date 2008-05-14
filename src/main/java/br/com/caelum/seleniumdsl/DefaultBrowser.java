package br.com.caelum.seleniumdsl;

import com.thoughtworks.selenium.Selenium;

public class DefaultBrowser implements Browser {

	private final Selenium selenium;

	private final int timeout;

	public DefaultBrowser(Selenium selenium) {
		this(selenium, 10000);
	}

	public DefaultBrowser(Selenium selenium, int timeout) {
		this.selenium = selenium;
		this.timeout = timeout;
	}

	/* (non-Javadoc)
	 * @see br.com.caelum.seleniumdsl.Browser#open(java.lang.String)
	 */
	public Page open(String url) {
		selenium.open(url);
		selenium.waitForPageToLoad(Integer.toString(timeout));
		return currentPage();
	}

	/* (non-Javadoc)
	 * @see br.com.caelum.seleniumdsl.Browser#currentPage()
	 */
	public Page currentPage() {
		return new DefaultPage(selenium, timeout);
	}

}
