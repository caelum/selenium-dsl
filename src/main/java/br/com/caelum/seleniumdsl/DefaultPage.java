package br.com.caelum.seleniumdsl;

import br.com.caelum.seleniumdsl.table.DefaultTable;
import br.com.caelum.seleniumdsl.table.Table;

import com.thoughtworks.selenium.Selenium;

class DefaultPage implements Page {

	final Selenium selenium;
	private final int timeout;

	public DefaultPage(Selenium selenium, int timeout) {
		this.selenium = selenium;
		this.timeout = timeout;
	}

	public String title() {
		return selenium.getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Page#getText(java.lang.String)
	 */
	public String getText(String xpath) {
		return selenium.getText(xpath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Page#form(java.lang.String)
	 */
	public Form form(String id) {
		return new DefaultForm(selenium, timeout, id.equals("") ? "" : id + ".");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Page#div(java.lang.String)
	 */
	public ContentTag div(String id) {
		return new DefaultContentTag(selenium, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Page#span(java.lang.String)
	 */
	public ContentTag span(String id) {
		return new DefaultContentTag(selenium, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Page#table(java.lang.String)
	 */
	public Table table(String id) {
		return new DefaultTable(selenium, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Page#click(java.lang.String)
	 */
	public Page click(String link) {
		selenium.click(link);
		selenium.waitForPageToLoad(Integer.toString(timeout));
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Page#clickDontWait(java.lang.String)
	 */
	public Page clickDontWait(String link) {
		selenium.click(link);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Page#hasLink(java.lang.String)
	 */
	public boolean hasLink(String link) {
		return selenium.isTextPresent(link);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Page#isFilled(java.lang.String, java.lang.String)
	 */
	public boolean isFilled(String textBoxId, String value) {
		return selenium.getValue(textBoxId).equals(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.caelum.seleniumdsl.Page#check(java.lang.String)
	 */
	public Page check(String checkbox) {
		selenium.click(checkbox);
		return this;
	}

}
