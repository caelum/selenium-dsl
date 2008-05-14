package br.com.caelum.seleniumdsl;

import com.thoughtworks.selenium.Selenium;

class DefaultContentTag implements ContentTag {

	private final Selenium selenium;
	private final String id;

	public DefaultContentTag(Selenium selenium, String id) {
		this.selenium = selenium;
		this.id = id;
	}

	public boolean contains(String content) {
		return selenium.getText(id).contains(content);
	}

	public boolean exists() {
		return selenium.isElementPresent(id);
	}

	public String innerHTML() {
		return selenium.getText(id);
	}

}
