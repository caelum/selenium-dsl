package br.com.caelum.seleniumdsl.test.table.layout;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.seleniumdsl.table.layout.FullTableLayout;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

public class FullTableLayoutTest {
	private static final int ROW_COUNT = 5;

	private Mockery mockery;
	private Selenium mock;
	private FullTableLayout tableLayout;

	@Before
	public void setUp() {
		mockery = new Mockery();
		mock = mockery.mock(Selenium.class);

		tableLayout = new FullTableLayout(mock, "table", "id");
	}

	@Test
	public void testGetValueOfNormalRow() throws Exception {
		mockery.checking(new Expectations() {
			{
				one(mock).getText("xpath=//table[@id='table']/tbody/tr[1]/td[1]");
				will(returnValue("test"));
			}
		});
		Assert.assertEquals("test", tableLayout.value(1, 1));
		mockery.assertIsSatisfied();
	}

	@Test
	public void testGetValueOfFooter() throws Exception {
		mockery.checking(new Expectations() {
			{
				one(mock).getText("xpath=//table[@id='table']/tbody/tr[" + (ROW_COUNT - 1) + "]/td[1]");
				will(throwException(new SeleniumException("exception")));

				one(mock).getText("xpath=//table[@id='table']/tfoot/tr[1]/td[1]");
				will(returnValue("test"));
			}
		});
		Assert.assertEquals("test", tableLayout.value(ROW_COUNT - 1, 1));
		mockery.assertIsSatisfied();
	}


	@Test
	public void testGetValueOfFooterInsideTBody() throws Exception {
		mockery.checking(new Expectations() {
			{
				one(mock).getText("xpath=//table[@id='table']/tbody/tr[" + (ROW_COUNT - 1) + "]/td[1]");
				will(returnValue("test"));
			}
		});
		Assert.assertEquals("test", tableLayout.value(ROW_COUNT - 1, 1));
		mockery.assertIsSatisfied();
	}
}
