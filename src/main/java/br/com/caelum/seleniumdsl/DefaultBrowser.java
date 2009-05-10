package br.com.caelum.seleniumdsl;

import org.apache.commons.lang.NotImplementedException;

import com.thoughtworks.selenium.Selenium;

/**
 * A default browser implementation based on selenium. Other browser implementations might be created based on other
 * projects (webdriver?).
 * 
 * @author Guilherme Silveira
 */
public class DefaultBrowser implements Browser {

	private final Selenium selenium;

	private final int timeout;

	/**
	 * Creates the browser with the default 10seconds timeout limit.
	 * 
	 * @param selenium
	 */
	public DefaultBrowser(Selenium selenium) {
		this(selenium, 10000);
	}

	/**
	 * Creates the browser with this selenium implementation and the selected timeout in millisecs.
	 * 
	 * @param selenium
	 * @param timeout
	 */
	public DefaultBrowser(Selenium selenium, int timeout) {
		this.selenium = selenium;
		this.timeout = timeout;
	}

	public Page open(String url) {
		selenium.open(url);
		selenium.waitForPageToLoad(Integer.toString(timeout));
		return currentPage();
	}

	public Page currentPage() {
		return new DefaultPage(selenium, timeout);
	}

	public Object getDelegate() {
		return selenium;
	}

	public Page waitForPageLoad(long timeout) {
		selenium.waitForPageToLoad("" + timeout);
		return currentPage();
	}

	@Override
	public Page window(String id) {
		throw new NotImplementedException();
	}

}
