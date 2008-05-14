package br.com.caelum.seleniumdsl;

import com.thoughtworks.selenium.Selenium;

class DefaultForm implements Form {

	private final String id;
	private final Selenium selenium;
	private int timeout;

	// FIXME: é suposto que id venha ou vazio, ou da forma "<id>."
	public DefaultForm(Selenium selenium, int timeout, String id) {
		this.selenium = selenium;
		this.timeout = timeout;
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Form#field(java.lang.String)
	 */
	public Field field(String fieldName) {
		return new DefaultField(selenium, this, id + fieldName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Form#click(java.lang.String)
	 */
	public void click(String buttonLabel) {
		selenium.click(buttonLabel);
		selenium.waitForPageToLoad(Integer.toString(timeout));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Form#selectField(java.lang.String)
	 */
	public SelectField selectField(String fieldName) {
		return new DefaultSelectField(selenium, this, id + fieldName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Form#check(java.lang.String)
	 */
	public Form check(String checkbox) {
		selenium.check(checkbox);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Form#uncheck(java.lang.String)
	 */
	public Form uncheck(String checkbox) {
		selenium.uncheck(checkbox);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Form#isChecked(java.lang.String)
	 */
	public boolean isChecked(String checkbox) {
		return selenium.getEval("selenium.page().findElement(\"" + checkbox + "\").checked").equals("true");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Form#submit()
	 */
	public void submit() {
		selenium.submit(id == "" ? "//form" : id.substring(0, id.length() - 1));
		selenium.waitForPageToLoad(Integer.toString(timeout));
	}

}
