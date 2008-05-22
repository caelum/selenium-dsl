package br.com.caelum.seleniumdsl.js;

import com.thoughtworks.selenium.Selenium;

/**
 * A selenium based implementation of Element.
 * 
 * @author Guilherme Silveira
 */
public class DefaultElement implements Element {

	private final Selenium selenium;
	private final String name;

	public DefaultElement(Selenium selenium, String name) {
		this.selenium = selenium;
		this.name = name;
	}

	public Element element(String name) {
		return new DefaultElement(selenium, this.name + "." + name);
	}

	public String value(String property) {
		return selenium.getEval("this.browserbot.getCurrentWindow()." + name + "." + property);
	}

	public String value() {
		return selenium.getEval("this.browserbot.getCurrentWindow()." + name);
	}

}
