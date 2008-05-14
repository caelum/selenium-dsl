package br.com.caelum.seleniumdsl.test.integration;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;

public class FormTest extends SeleniumTestCase {
	private Page page;
	private Form form;

	@Override
	public void setUp() {
		super.setUp();
		page = browser.open("form.html");
		form = page.form("");
	}

	@Test
	public void fillFormField() {
		String testContent = "test content";
		String idField = "textinput";
		Field field = form.field(idField);
		field.type(testContent);
		String value = getSelenium().getValue(idField);
		Assert.assertEquals(value, testContent);
		Assert.assertEquals(value, field.content());
		Assert.assertTrue(field.contains(testContent));
	}

	@Test
	public void checkCheckbox() {
		String fieldId = "checkbox";
		form.check(fieldId);
		String value = getSelenium().getValue(fieldId);
		Assert.assertEquals(value, "on");
		Assert.assertTrue(form.isChecked(fieldId));
	}

	@Test
	public void uncheckCheckbox() {
		String fieldId = "checkbox";
		form.uncheck(fieldId);
		String value = getSelenium().getValue(fieldId);
		Assert.assertFalse(value.equals("on"));
		Assert.assertFalse(form.isChecked(fieldId));
	}

	@Test
	public void shouldSelectWithChoose() {
		String fieldId = "combobox";
		form.selectField(fieldId).choose("Label 2");
		String value = getSelenium().getSelectedValue(fieldId);
		Assert.assertEquals(value, "2");

	}

	public void shouldSelectWithChooseByIndex() {
		String fieldId = "combobox";
		form.selectField(fieldId).chooseByIndex(2);
		String value = getSelenium().getSelectedLabel(fieldId);
		Assert.assertEquals(value, "Label 3");
	}

	@Test
	/**
	 * The support for the tag <input type="radio"/> on selenium is non-existent.
	 * 
	 * @author filipesabella
	 */
	public void selectRadio() {
		form.check("radio2");
		Assert.assertTrue(getSelenium().isChecked("radio2"));
		Assert.assertTrue(form.isChecked("radio2"));

		// radio1 should be unselected
		Assert.assertFalse(getSelenium().isChecked("radio1"));
		Assert.assertFalse(form.isChecked("radio1"));
	}

	@Test
	public void testClickButton() {
		page.clickDontWait("button");
		Assert.assertTrue(page.div("messages").contains("test message"));
	}
}