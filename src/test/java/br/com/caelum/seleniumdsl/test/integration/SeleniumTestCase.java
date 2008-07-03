package br.com.caelum.seleniumdsl.test.integration;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.server.SeleniumServer;

import br.com.caelum.seleniumdsl.Browser;
import br.com.caelum.seleniumdsl.DefaultBrowser;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumLogLevels;

public abstract class SeleniumTestCase {
	private static Selenium selenium;

	@BeforeClass
	public static void beforeStartup() {
		String port = System.getProperty("cargo.servlet.port");
		if (port == null || port.equals("")) {
			port = "8080";
		}
		String seleniumPort = System.getProperty("selenium.port");
		if (seleniumPort == null || seleniumPort.equals("")) {
			seleniumPort = String.valueOf(SeleniumServer.getDefaultPort());
			seleniumPort = "4445";
		}
		String browser = System.getProperty("seleniumBrowserString");
		browser = browser == null ? "*firefox" : browser;
		selenium = new DefaultSelenium("localhost", Integer
				.valueOf(seleniumPort), browser, "http://localhost:" + port);
		selenium.start();
		selenium.setContext("SeleniumDSL");
		selenium.setBrowserLogLevel(SeleniumLogLevels.WARN);
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
