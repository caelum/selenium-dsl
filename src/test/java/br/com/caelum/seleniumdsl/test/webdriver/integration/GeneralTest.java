package br.com.caelum.seleniumdsl.test.webdriver.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.table.Table;

public class GeneralTest extends WebDriverTestCase {

	@Override
	@Before
	public void setUp() {
		super.setUp();
		browser.open(URL + "mypage.jsp");
	}

	@Test
	public void testTableRowCount() {
		final Table table = browser.currentPage().table("table");
		Assert.assertEquals(3, table.getRowCount());
	}

	@Test
	public void testTableColCount() {
		final Table table = browser.currentPage().table("table");
		Assert.assertEquals(2, table.getColCount());
	}

	@Test
	public void testTableHeaderLabel() {
		final Table table = browser.currentPage().table("table");
		Assert.assertEquals("First Column", table.header().cell(1).headerValue());
	}

	@Test
	public void testTableContent() {
		final Table table = browser.currentPage().table("table");
		Assert.assertEquals("cell_1_1", table.cell(1, "First Column").value());
		Assert.assertTrue(table.cell(1, "First Column").contains("cell_1_1"));
	}

	@Test
	public void testLogin() {
		final Form form = browser.currentPage().form("form");
		form.field("login").type("myLogin");
		form.field("password").type("myPassword");
		form.check("remember");

		form.click("enviar");

		// logged successfuly
		Assert.assertEquals("Main Page", browser.currentPage().title());
	}

	@Test
	public void testInvalidLogin() {
		final Form form = browser.currentPage().form("form");
		form.field("login").type("invalid_login");
		form.field("password").type("invalid_password");
		form.click("enviar");

		final Page page = browser.currentPage();

		Assert.assertEquals("Login", page.title());
		Assert.assertTrue(page.div("errors").contains("Invalid login!"));

		Assert.assertTrue(form.isChecked("remember"));
		// ensures that the form remains with the previously typed login
		Assert.assertEquals(form.field("login").content(), "invalid_login");
		// or
		Assert.assertTrue(form.field("login").contains("invalid_login"));
	}
}