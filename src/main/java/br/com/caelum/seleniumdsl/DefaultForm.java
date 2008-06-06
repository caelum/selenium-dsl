package br.com.caelum.seleniumdsl;

import com.thoughtworks.selenium.Selenium;

class DefaultForm implements Form {

	private final String id;
	private final Selenium selenium;
	private int timeout;

	/**
	 * The form id is used in order to submit differente forms in the same web page.
	 */
	public DefaultForm(Selenium selenium, int timeout, String id) {
		this.selenium = selenium;
		this.timeout = timeout;
		this.id = id;
	}

	public Field field(String field) {
		return new DefaultField(selenium, this, field);
	}

	public void click(String element) {
		selenium.click(element);
	}

	public void navigate(String element) {
		selenium.click(element);
		selenium.waitForPageToLoad(Integer.toString(timeout));
	}

	public SelectField select(String selectField) {
		return new DefaultSelectField(selenium, this, selectField);
	}

	public Form check(String checkbox) {
		selenium.check(checkbox);
		return this;
	}

	public Form uncheck(String checkbox) {
		selenium.uncheck(checkbox);
		return this;
	}

	public boolean isChecked(String checkbox) {
		return selenium.getEval("selenium.page().findElement(\"" + checkbox + "\").checked")
				.equals("true");
	}

	public void submit() {
		selenium.submit(id == "" ? "//form" : id.substring(0, id.length() - 1));
		selenium.waitForPageToLoad(Integer.toString(timeout));
	}

}
