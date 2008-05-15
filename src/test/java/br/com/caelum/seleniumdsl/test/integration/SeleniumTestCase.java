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

public class SeleniumTestCase {
	private static Selenium selenium;

	@BeforeClass
	public static void beforeStartup() {
		selenium = new DefaultSelenium("localhost", SeleniumServer.getDefaultPort(), "*firefox",
				"http://localhost:8080/seleniumdsl/");
		selenium.start();
		selenium.setContext("Selenium DSL testing");
		selenium.setBrowserLogLevel(SeleniumLogLevels.DEBUG);
	}

	@AfterClass
	public static void afterShutdown() {
		selenium.stop();
	}

	protected Browser browser;

	@Before
	public void setUp() {
		this.browser = new DefaultBrowser(selenium);
	}

	@After
	public void tearDown() throws IOException {
	}

	public static Selenium getSelenium() {
		return selenium;
	}
}
