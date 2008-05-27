package br.com.caelum.seleniumdsl;

import com.thoughtworks.selenium.Selenium;

class DefaultField implements Field {

	final String id;
	private final Form form;
	final Selenium selenium;

	public DefaultField(Selenium selenium, Form form, String id) {
		this.selenium = selenium;
		this.form = form;
		this.id = id;
	}

	public Form type(String content) {
		selenium.type(id, content);
		return form;
	}

	public boolean contains(String content) {
		return content.contains(selenium.getValue(id));
	}

	public String content() {
		return selenium.getValue(id);
	}

	public void blur() {
		selenium.fireEvent(id, "blur");
	}
	public void change() {
		selenium.fireEvent(id, "change");
	}
}