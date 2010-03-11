package br.com.caelum.seleniumdsl.test.table.layout;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.caelum.seleniumdsl.webdriver.table.layout.WebDriverFullTableLayout;

public class WebDriverFullTableLayoutTest {
	private static final int ROWS = 4;

	private Mockery mockery;
	private WebDriver mock;
	private WebDriverFullTableLayout tableLayout;
	private WebElement row;

	@Before
	public void setUp() {
		mockery = new Mockery();
		mock = mockery.mock(WebDriver.class);
		row = mockery.mock(WebElement.class);

		mockery.checking(new Expectations() {
			{
				allowing(row).getText();
				will(returnValue("test"));
			}
		});

		tableLayout = new WebDriverFullTableLayout(mock, "table", "id");
	}

	@Test
	public void testGetValueOfNormalRow() throws Exception {
		mockery.checking(new Expectations() {
			{
				one(mock).findElement(By.xpath("//table[@id='table']/tbody/tr[1]/td[1]"));
				will(returnValue(row));
			}
		});
		Assert.assertEquals("test", tableLayout.value(1, 1));
		mockery.assertIsSatisfied();
	}

	@Test
	public void testGetValueOfFooter() throws Exception {
		mockery.checking(new Expectations() {
			{
				one(mock).findElement(By.xpath("//table[@id='table']/tbody/tr[" + (ROWS - 1) + "]/td[1]"));
				will(throwException(new Exception()));

				one(mock).findElement(By.xpath("//table[@id='table']/tfoot/tr[1]/td[1]"));
				will(returnValue(row));
			}
		});
		Assert.assertEquals("test", tableLayout.value(ROWS - 1, 1));
		mockery.assertIsSatisfied();
	}

	@Test
	public void testGetValueOfFooterInsideTBody() throws Exception {
		mockery.checking(new Expectations() {
			{
				one(mock).findElement(By.xpath("//table[@id='table']/tbody/tr[" + (ROWS - 1) + "]/td[1]"));
				will(returnValue(row));
			}
		});
		Assert.assertEquals("test", tableLayout.value(ROWS - 1, 1));
		mockery.assertIsSatisfied();
	}
}