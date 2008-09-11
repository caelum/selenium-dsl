package br.com.caelum.seleniumdsl.test.integration;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.seleniumdsl.Page;

public class WaiterTest extends SeleniumTestCase {
	private Page page;

	@Override
	public void setUp() {
		super.setUp();
		page = browser.open("waiting.html");
	}

	@Test
	public void testWaitUntil() {
		page.click("button");
		page.waitUntil("updated==true", 5000);
		Assert.assertTrue(page.div("text").contains("Pressed"));
	}

}