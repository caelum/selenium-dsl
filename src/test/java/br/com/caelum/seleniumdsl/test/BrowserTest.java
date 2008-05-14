package br.com.caelum.seleniumdsl.test;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.seleniumdsl.Browser;
import br.com.caelum.seleniumdsl.DefaultBrowser;

import com.thoughtworks.selenium.Selenium;

public class BrowserTest {
	private Selenium mock;
	private Mockery mockery;
	private Browser browser;

	@Before
	public void setUp() {
		mockery = new Mockery();
		mock = mockery.mock(Selenium.class);
		browser = new DefaultBrowser(mock);
	}

	@Test
	public void testOpen() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).open(with(any(String.class)));
				exactly(1).of(mock).waitForPageToLoad(with(any(String.class)));
			}
		});
		browser.open("");
		mockery.assertIsSatisfied();
	}
}
