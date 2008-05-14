package br.com.caelum.seleniumdsl;

import com.thoughtworks.selenium.Selenium;

class DefaultSelectField implements SelectField {

	private final String id;
	private final Selenium selenium;
	private final Form form;

	public DefaultSelectField(Selenium selenium, Form form, String id) {
		this.selenium = selenium;
		this.form = form;
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.SelectField#choose(java.lang.String)
	 */
	public Form choose(String value) {
		selenium.select(id, value);
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.SelectField#chooseByIndex(java.lang.Integer)
	 */
	public Form chooseByIndex(Integer index) {
		String[] options = selenium.getSelectOptions(id);
		selenium.select(id, options[index]);
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.SelectField#value()
	 */
	public String value() {
		return selenium.getValue(id);
	}

}
