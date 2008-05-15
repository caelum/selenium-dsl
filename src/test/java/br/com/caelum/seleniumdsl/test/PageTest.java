package br.com.caelum.seleniumdsl.test;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.seleniumdsl.DefaultBrowser;
import br.com.caelum.seleniumdsl.Page;

import com.thoughtworks.selenium.Selenium;

public class PageTest {
	private Selenium mock;
	private Mockery mockery;
	private Page page;

	@Before
	public void setUp() {
		mockery = new Mockery();
		mock = mockery.mock(Selenium.class);
		page = new DefaultBrowser(mock).currentPage();
	}

	@Test
	public void testCheck() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).click(with(any(String.class)));
			}
		});
		page.check("id");
		mockery.assertIsSatisfied();
	}

	@Test
	public void testClick() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).click(with(any(String.class)));
				exactly(1).of(mock).waitForPageToLoad(with(any(String.class)));
			}
		});
		page.clickLink("id");
		mockery.assertIsSatisfied();
	}

	@Test
	public void testClickDontWait() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).click(with(any(String.class)));
			}
		});
		page.click("id");
		mockery.assertIsSatisfied();
	}

	@Test
	public void testHasLink() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).isTextPresent(with(any(String.class)));
			}
		});
		page.hasLink("id");
		mockery.assertIsSatisfied();
	}

	@Test
	public void testIsFilled() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).getValue(with(any(String.class)));
			}
		});
		page.isFilled("id", "value");
		mockery.assertIsSatisfied();
	}
}
