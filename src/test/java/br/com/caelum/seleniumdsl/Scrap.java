package br.com.caelum.seleniumdsl;

import org.openqa.selenium.server.SeleniumServer;

import br.com.caelum.seleniumdsl.Browser;
import br.com.caelum.seleniumdsl.DefaultBrowser;
import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumLogLevels;

public class Scrap {
	public static void main(String[] args) {
		Selenium selenium = new DefaultSelenium("http://www.google.com", SeleniumServer.getDefaultPort(), "*firefox",
				"http://www.google.com");
		selenium.start();
		selenium.setContext("A real test, using the real Selenium on the browser side served by Jetty, driven from Java");
		selenium.setBrowserLogLevel(SeleniumLogLevels.WARN);

		Browser browser = new DefaultBrowser(selenium);
		browser.open("/");
		selenium.stop();

		Page currentPage = browser.currentPage();
		Form form = currentPage.form("id");
		Field field = form.field("fieldName");
	}
}
