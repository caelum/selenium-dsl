package br.com.caelum.seleniumdsl.test.table;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.seleniumdsl.table.DefaultTable;
import br.com.caelum.seleniumdsl.table.Table;

import com.thoughtworks.selenium.Selenium;

public class PlainThTableTest {
	private static final String BASE_COUNT_PATH = "//table[@id='id']/";
	private static final String BASE_TEXT_PATH = "xpath=//table[@id='id']/";

	private Selenium mock;
	private Mockery mockery;
	private Table table;

	@Before
	public void setUp() {
		mockery = new Mockery();
		mock = mockery.mock(Selenium.class);

		mockery.checking(new Expectations() {
			{
				String testThead = "id('%s')/thead";
				String testTh = "id('%s')//th";

				one(mock).getXpathCount(String.format(testThead, "id"));
				will(returnValue(0));

				one(mock).getXpathCount(String.format(testTh, "id"));
				will(returnValue(1));

			}
		});

		table = new DefaultTable(mock, "id");
	}

	@Test
	public void testRowCount() {
		mockery.checking(new Expectations() {
			{
				String rowCount = BASE_COUNT_PATH + "/*/tr";

				one(mock).getXpathCount(rowCount);
				will(returnValue(5));
			}
		});

		Assert.assertEquals(table.getRowCount(), 5);
		mockery.assertIsSatisfied();
	}

	@Test
	public void testContentCount() {
		mockery.checking(new Expectations() {
			{
				String rowCount = BASE_COUNT_PATH + "/*/tr";

				one(mock).getXpathCount(rowCount);
				will(returnValue(5));
			}
		});

		Assert.assertEquals(table.getContentCount(), 4);
		mockery.assertIsSatisfied();
	}

	@Test
	public void testColCount() {
		mockery.checking(new Expectations() {
			{
				String colCount = BASE_COUNT_PATH + "/tbody/tr/th";
				one(mock).getXpathCount(colCount);
				will(returnValue(3));
			}
		});

		Assert.assertEquals(table.getColCount(), 3);
		mockery.assertIsSatisfied();
	}

}
