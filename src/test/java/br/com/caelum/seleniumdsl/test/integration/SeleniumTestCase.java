package br.com.caelum.seleniumdsl.test.integration;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import br.com.caelum.seleniumdsl.Browser;
import br.com.caelum.seleniumdsl.DefaultBrowser;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumLogLevels;

public abstract class SeleniumTestCase {
	private static Selenium selenium;

	@BeforeClass
	public static void beforeStartup() {
		String port = getProperty("cargo.servlet.port", "8080");
		String browser = getProperty("seleniumBrowserString", "*firefox");
		String seleniumPort = getProperty("selenium.port", "4444");

		selenium = new DefaultSelenium("localhost", Integer.parseInt(seleniumPort), browser, "http://localhost:" + port);
		selenium.start();
		selenium.setContext("SeleniumDSL");
		selenium.setBrowserLogLevel(SeleniumLogLevels.WARN);
	}

	private static String getProperty(String key, String standard) {
		String value = System.getProperty(key);
		if (value == null || value.equals(""))
			return standard;
		return value;
	}

	@AfterClass
	public static void afterShutdown() {
		selenium.stop();
	}

	protected Browser browser;

	@Before
	public void setUp() {
		browser = new DefaultBrowser(selenium);
	}

	@After
	public void tearDown() throws IOException {
	}

	public static Selenium getSelenium() {
		return selenium;
	}
}
