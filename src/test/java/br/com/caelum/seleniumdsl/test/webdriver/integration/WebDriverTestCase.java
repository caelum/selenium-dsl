package br.com.caelum.seleniumdsl.test.webdriver.integration;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.caelum.seleniumdsl.Browser;
import br.com.caelum.seleniumdsl.webdriver.WebDriverBrowser;

public abstract class WebDriverTestCase {

	protected static WebDriver webDriver;
	protected Browser browser;
	protected static final String URL = "http://localhost:8080/selenium-dsl/";

	@BeforeClass
	public static void beforeStartup() {
		webDriver = new FirefoxDriver();
	}

	@AfterClass
	public static void afterShutdown() {
		webDriver.close();
	}

	@Before
	public void setUp() {
		browser = new WebDriverBrowser(webDriver);
	}

	@After
	public void tearDown() throws IOException {
	}

	public static WebDriver getWebDriver() {
		return webDriver;
	}
}
