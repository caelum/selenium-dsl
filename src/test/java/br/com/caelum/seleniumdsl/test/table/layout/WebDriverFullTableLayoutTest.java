package br.com.caelum.seleniumdsl.test.table.layout;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.caelum.seleniumdsl.webdriver.table.layout.WebDriverFullTableLayout;

public class WebDriverFullTableLayoutTest {
	private Mockery mockery;
	private WebDriver mock;
	private WebDriverFullTableLayout tableLayout;
	private WebElement header;
	private WebElement row1;
	private WebElement row2;
	private WebElement footer;
	private List<WebElement> rows;

	@Before
	public void setUp() {
		mockery = new Mockery();
		mock = mockery.mock(WebDriver.class);
		header = mockery.mock(WebElement.class, "header");
		row1 = mockery.mock(WebElement.class, "row1");
		row2 = mockery.mock(WebElement.class, "row2");
		footer = mockery.mock(WebElement.class, "footer");
		rows = Arrays.asList(header, row1, row2, footer);

		mockery.checking(new Expectations() {
			{
				allowing(mock).findElements(with(ByMatcher.byEqualTo(By.xpath("//table[@id='table']/*/tr"))));
				will(returnValue(rows));
			}
		});

		tableLayout = new WebDriverFullTableLayout(mock, "table", "id");
	}

	@Test
	public void testGetValueOfNormalRow() throws Exception {
		mockery.checking(new Expectations() {
			{
				one(mock).findElement(with(ByMatcher.byEqualTo(By.xpath("//table[@id='table']/tbody/tr[1]/td[1]"))));
				will(returnValue(row1));

				one(row1).getText();
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
				one(mock).findElement(with(ByMatcher.byEqualTo(By.xpath("//table[@id='table']/tfoot/tr[1]/td[1]"))));
				will(returnValue(footer));

				one(footer).getText();
				will(returnValue("test"));
			}
		});
		Assert.assertEquals("test", tableLayout.value(rows.size() - 1, 1));
		mockery.assertIsSatisfied();
	}
}

class ByMatcher extends TypeSafeMatcher<By> {

	private final By expected;

	public ByMatcher(By expected) {
		this.expected = expected;
	}

	public void describeTo(Description desc) {
		desc.appendText("By equal to " + expected.toString());
	}

	@Override
	public boolean matchesSafely(By toMatch) {
		return toMatch.toString().equals(expected.toString());
	}

	@Factory
	public static Matcher<By> byEqualTo(By expected) {
		return new ByMatcher(expected);
	}
}
