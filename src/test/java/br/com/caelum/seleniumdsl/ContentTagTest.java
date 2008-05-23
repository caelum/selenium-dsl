package br.com.caelum.seleniumdsl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.seleniumdsl.ContentTag;
import br.com.caelum.seleniumdsl.DefaultBrowser;

import com.thoughtworks.selenium.Selenium;

public class ContentTagTest {
	private Selenium mock;
	private Mockery mockery;
	private ContentTag tag;

	@Before
	public void setUp() {
		mockery = new Mockery();
		mock = mockery.mock(Selenium.class);
		tag = new DefaultBrowser(mock).currentPage().div("id");
	}

	@Test
	public void testContains() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).getText(with(any(String.class)));
			}
		});
		tag.contains("id");
		mockery.assertIsSatisfied();
	}

	@Test
	public void testExists() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).isElementPresent(with(any(String.class)));
			}
		});
		tag.exists();
		mockery.assertIsSatisfied();
	}

	@Test
	public void testInnerHTML() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).getText(with(any(String.class)));
			}
		});
		tag.innerHTML();
		mockery.assertIsSatisfied();
	}
}