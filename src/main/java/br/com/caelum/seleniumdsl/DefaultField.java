package br.com.caelum.seleniumdsl;

import com.thoughtworks.selenium.Selenium;

class DefaultField implements Field {

	private final String id;
	private final Form form;
	private final Selenium selenium;

	public DefaultField(Selenium selenium, Form form, String id) {
		this.selenium = selenium;
		this.form = form;
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Field#type(java.lang.String)
	 */
	public Form type(String content) {
		selenium.type(id, content);
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Field#contains(java.lang.String)
	 */
	public boolean contains(String content) {
		return content.contains(selenium.getValue(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Field#content()
	 */
	public String content() {
		return selenium.getValue(id);
	}
}