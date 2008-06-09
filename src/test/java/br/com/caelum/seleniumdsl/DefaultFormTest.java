package br.com.caelum.seleniumdsl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.seleniumdsl.DefaultBrowser;
import br.com.caelum.seleniumdsl.Form;

import com.thoughtworks.selenium.Selenium;

public class DefaultFormTest {
	private Selenium mock;
	private Mockery mockery;
	private Form form;

	@Before
	public void setUp() {
		mockery = new Mockery();
		mock = mockery.mock(Selenium.class);
		form = new DefaultBrowser(mock).currentPage().form("id");
	}

	@Test
	public void testCheck() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).check(with(any(String.class)));
			}
		});
		form.check("id");
		mockery.assertIsSatisfied();
	}

	@Test
	public void testUncheck() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).uncheck(with(any(String.class)));
			}
		});
		form.uncheck("id");
		mockery.assertIsSatisfied();
	}

	@Test
	public void testClick() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).click("id");
			}
		});
		form.click("id");
		mockery.assertIsSatisfied();
	}

	@Test
	public void testIsChecked() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).getEval(with(any(String.class)));
			}
		});
		form.isChecked("id");
		mockery.assertIsSatisfied();
	}

	@Test
	public void testSubmit() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).submit(with(any(String.class)));
				exactly(1).of(mock).waitForPageToLoad(with(any(String.class)));
			}
		});
		form.submit();
		mockery.assertIsSatisfied();
	}
}