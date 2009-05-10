package br.com.caelum.seleniumdsl.test.webdriver.integration;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.table.Table;
import br.com.caelum.seleniumdsl.webdriver.table.layout.WebDriverFullTableLayout;
import br.com.caelum.seleniumdsl.webdriver.table.layout.WebDriverPlainPlusThTableLayout;
import br.com.caelum.seleniumdsl.webdriver.table.layout.WebDriverPlainTableLayout;

public class TableTest extends WebDriverTestCase {

	private Page page;
	private Table plain;
	private Table plainTh;
	private Table full;
	private Table nested;

	@Override
	public void setUp() {
		super.setUp();
		page = browser.open(URL + "table.html");

		plain = page.table("plain");
		plainTh = page.table("plain_with_th");
		full = page.table("full");
		nested = page.table("nested");
	}

	@Test
	public void testTableLayoutType() {
		Assert.assertTrue(plain.getLayout().getClass().equals(WebDriverPlainTableLayout.class));
		Assert.assertTrue(plainTh.getLayout().getClass().equals(WebDriverPlainPlusThTableLayout.class));
		Assert.assertTrue(full.getLayout().getClass().equals(WebDriverFullTableLayout.class));
		Assert.assertTrue(nested.getLayout().getClass().equals(WebDriverFullTableLayout.class));
	}

	@Test
	public void testRowCount() {
		Assert.assertEquals(plain.getRowCount(), 5);
		Assert.assertEquals(plainTh.getRowCount(), 5);
		Assert.assertEquals(full.getRowCount(), 5);
		Assert.assertEquals(nested.getRowCount(), 5);
	}

	@Test
	public void testContentCount() {
		Assert.assertEquals(plain.getContentCount(), 4);
		Assert.assertEquals(plainTh.getContentCount(), 4);
		Assert.assertEquals(full.getContentCount(), 3);
		Assert.assertEquals(nested.getContentCount(), 3);
	}

	@Test
	public void testHeaderLabel() {
		Assert.assertEquals(plain.row(1).cell(1).headerValue(), "header_1");
		Assert.assertEquals(plainTh.row(1).cell(1).headerValue(), "header_1");
		Assert.assertEquals(full.row(1).cell(1).headerValue(), "header_1");
		Assert.assertEquals(nested.row(1).cell(1).headerValue(), "header_1");
	}

	@Test
	public void testHeaderLinkLabel() {
		Assert.assertEquals(plain.header().cell(2).headerLinkValue(), "header_2");
		Assert.assertEquals(plainTh.header().cell(2).headerLinkValue(), "header_2");
		Assert.assertEquals(full.header().cell(2).headerLinkValue(), "header_2");
		Assert.assertEquals(nested.header().cell(2).headerLinkValue(), "header_2");
	}

	@Test
	public void testGetValue() {
		Assert.assertEquals(plain.row(1).cell(1).value(), "cell_1_1");
		Assert.assertEquals(plainTh.row(1).cell(1).value(), "cell_1_1");
		Assert.assertEquals(full.row(1).cell(1).value(), "cell_1_1");
		Assert.assertEquals(nested.row(1).cell(1).value(), "cell_1_1");
		Assert.assertEquals(nested.row(2).cell(1).value(), "cell_1_2");
	}

	@Test
	public void testGetValueByCell() {
		Assert.assertEquals(plain.cell(1, 1).value(), "cell_1_1");
		Assert.assertEquals(plainTh.cell(1, 1).value(), "cell_1_1");
		Assert.assertEquals(full.cell(1, 1).value(), "cell_1_1");
		Assert.assertEquals(nested.cell(1, 1).value(), "cell_1_1");
		Assert.assertEquals(nested.cell(2, 1).value(), "cell_1_2");
	}

	@Test
	public void testGetValueByCellAndColumn() {
		Assert.assertEquals(plain.cell(2, "header_1").value(), "cell_1_2");
		Assert.assertEquals(plainTh.cell(2, "header_1").value(), "cell_1_2");
		Assert.assertEquals(full.cell(2, "header_1").value(), "cell_1_2");
		Assert.assertEquals(nested.cell(2, "header_1").value(), "cell_1_2");
	}

	@Test
	public void testCheck() {
		Assert.assertTrue(plain.row(2).cell(3).check().checked());
		Assert.assertTrue(plainTh.row(2).cell(3).check().checked());
		Assert.assertTrue(full.row(2).cell(3).check().checked());
		Assert.assertTrue(nested.row(2).cell(3).check().checked());
	}

	@Test
	public void testUncheck() {
		Assert.assertFalse(plain.row(2).cell(3).uncheck().checked());
		Assert.assertFalse(plainTh.row(2).cell(3).uncheck().checked());
		Assert.assertFalse(full.row(2).cell(3).uncheck().checked());
		Assert.assertFalse(nested.row(2).cell(3).uncheck().checked());
	}
}
