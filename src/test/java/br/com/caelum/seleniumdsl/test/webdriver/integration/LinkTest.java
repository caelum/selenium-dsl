package br.com.caelum.seleniumdsl.test.webdriver.integration;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.seleniumdsl.Page;

public class LinkTest extends WebDriverTestCase {

	private Page page;

	@Override
	public void setUp() {
		super.setUp();
		page = browser.open(URL + "link.html");
	}

	@Test
	public void testaQueAbreLinkPorDescricao() throws Exception {
		page.clickLink("Teste Link");
		Assert.assertEquals("Main Page", page.title());
	}

	@Test
	public void testaQueAbreLinkPorParteDaDescricao() throws Exception {
		page.clickLink("Teste");
		Assert.assertEquals("Main Page", page.title());
	}

	@Test
	public void testaQueAbreLinkPorName() throws Exception {
		page.clickLink("linkName");
		Assert.assertEquals("Main Page", page.title());
	}

	@Test
	public void testaQueAbreLinkPorId() throws Exception {
		page.clickLink("linkId");
		Assert.assertEquals("Main Page", page.title());
	}

	@Test
	public void testaQueAbreLinkPorXPath() throws Exception {
		page.clickLink("//a[text() = 'Teste Link']");
		Assert.assertEquals("Main Page", page.title());
	}
}
