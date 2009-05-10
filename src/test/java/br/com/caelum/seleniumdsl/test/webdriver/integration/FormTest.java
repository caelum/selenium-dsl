package br.com.caelum.seleniumdsl.test.webdriver.integration;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.SelectField;

public class FormTest extends WebDriverTestCase {

	private Page page;
	private Form form;

	@Override
	public void setUp() {
		super.setUp();

		page = browser.open(URL + "form.html");
		form = page.form("form");
	}

	@Test
	public void fillFormField() {
		final String testContent = "test content";
		final String idField = "textinput";
		final Field field = form.field(idField);
		field.type(testContent);

		final String value = getWebDriver().findElement(By.name(idField)).getValue();
		Assert.assertEquals(value, testContent);
		Assert.assertEquals(value, field.content());
		Assert.assertTrue(field.contains(testContent));
	}

	@Test
	public void checkCheckbox() {
		final String idField = "checkbox";
		form.check(idField);
		Assert.assertTrue(form.isChecked(idField));
	}

	@Test
	public void uncheckCheckbox() {
		final String idField = "checkbox";
		form.uncheck(idField);
		Assert.assertFalse(form.isChecked(idField));
	}

	@Test
	public void shouldSelectWithChoose() {
		final String idField = "combobox";

		final SelectField select = form.select(idField);
		select.choose("Label 2");

		Assert.assertEquals("2", select.value());
		Assert.assertEquals("Label 2", select.content());
	}

	@Test
	public void shouldSelectWithChooseByIndex() {
		//There is a bug in InternetExplorerDriver or Internet Explorer
		//Whatever - It doesn't select options by index

		final String idField = "combobox";

		final SelectField select = form.select(idField);
		select.choose(2);

		Assert.assertEquals("3", select.value());
		Assert.assertEquals("Label 3", select.content());
	}

	@Test
	public void selectRadio() {
		form.check("radio2");
		Assert.assertTrue(getWebDriver().findElement(By.id("radio2")).isSelected());
		Assert.assertTrue(form.isChecked("radio2"));

		// radio1 should be unselected
		Assert.assertFalse(getWebDriver().findElement(By.id("radio1")).isSelected());
		Assert.assertFalse(form.isChecked("radio1"));
	}

	@Test
	public void testClickButton() {
		page.click("button");

		Assert.assertTrue(page.div("messages").contains("test message"));
		Assert.assertTrue(page.div("messages").exists());
		Assert.assertEquals("test message", page.div("messages").innerHTML());
	}
}