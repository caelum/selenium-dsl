package br.com.caelum.seleniumdsl;

import com.thoughtworks.selenium.Selenium;

/**
 * Selenium based implementation.
 * @author Guilherme Silveira
 *
 */
class DefaultSelectField implements SelectField {

	private final String id;
	private final Selenium selenium;
	private final Form form;

	public DefaultSelectField(Selenium selenium, Form form, String id) {
		this.selenium = selenium;
		this.form = form;
		this.id = id;
	}

	public Form choose(String value) {
		selenium.select(id, value);
		return form;
	}

	public Form choose(int index) {
		String[] options = selenium.getSelectOptions(id);
		selenium.select(id, options[index]);
		return form;
	}

	public String value() {
		return selenium.getValue(id);
	}

	public String[] values() {
		return selenium.getSelectOptions(id);
	}

}
