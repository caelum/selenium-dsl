package br.com.caelum.seleniumdsl.extjs;

import com.thoughtworks.selenium.Selenium;

public class DefaultRecord implements Record {

	private final String id;
	private final Selenium selenium;

	public DefaultRecord(Selenium selenium, String id) {
		this.selenium = selenium;
		this.id = id;
	}

	public void set(String dataIndex, boolean value) {
		selenium.getEval("this.browserbot.getCurrentWindow()." + id + ".set('" + dataIndex + "', " + value + ")");
	}

}
