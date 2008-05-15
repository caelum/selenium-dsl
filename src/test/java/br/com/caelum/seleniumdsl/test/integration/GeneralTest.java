package br.com.caelum.seleniumdsl.test.integration;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.server.SeleniumServer;

import br.com.caelum.seleniumdsl.Browser;
import br.com.caelum.seleniumdsl.DefaultBrowser;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.table.Table;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumLogLevels;

public class GeneralTest {

	private static Selenium selenium;

	private Browser browser;

	@BeforeClass
	public static void beforeStartup() {
		selenium = new DefaultSelenium("localhost", SeleniumServer.getDefaultPort(), "*firefox",
				"http://localhost:8080/");
		selenium.start();
		selenium.setContext("Selenium DSL testing");
		selenium.setBrowserLogLevel(SeleniumLogLevels.DEBUG);
	}

	@AfterClass
	public static void afterShutdown() {
		selenium.stop();
	}

	@Before
	public void setUp() {
		browser = new DefaultBrowser(selenium);
		browser.open("seleniumdsl/mypage.html");
	}

	@Test
	public void testTableRowCount() {
		Table table = browser.currentPage()
				.table("table");
		Assert.assertEquals(table.getRowCount(), 3);
	}

	@Test
	public void testTableColCount() {
		Table table = browser.currentPage()
				.table("table");
		Assert.assertEquals(table.getColCount(), 2);
	}

	@Test
	public void testTableHeaderLabel() {
		Table table = browser.currentPage()
				.table("table");
		// indexes start at 1
		Assert.assertEquals(table.header()
				.cell(1)
				.headerValue(), "First Column");
	}

	@Test
	public void testTableContent() {
		Table table = browser.currentPage()
				.table("table");
		// second row, column named "First Column"
		// value() returns null if the cell isn't found
		Assert.assertEquals(table.cell(1, "First Column")
				.value(), "cell_1_1");
		// or
		Assert.assertTrue(table.cell(1, "First Column")
				.contains("cell_1_1"));
	}

	@Test
	public void testLogin() {
		Form form = browser.currentPage()
				.form("form");
		form.field("login")
				.type("myLogin");
		form.field("password")
				.type("myPassword");
		form.check("remember");

		form.submit();

		// logged successfuly
		Assert.assertEquals("Main Page", browser.currentPage()
				.title());
	}

	@Test
	public void testInvalidLogin() {
		Form form = browser.currentPage()
				.form("form");
		form.field("login")
				.type("invalid_login");
		form.field("password")
				.type("invalid_password");

		form.submit();

		Page page = browser.currentPage();

		Assert.assertEquals("Login", page.title());
		Assert.assertTrue(page.div("errors")
				.contains("Invalid login!"));

		Assert.assertTrue(form.isChecked("rememeber"));
		// ensures that the form remains with the previously typed login
		Assert.assertEquals(form.field("login")
				.content(), "invalid_login");
		// or
		Assert.assertTrue(form.field("login")
				.contains("invalid_login"));
	}
}