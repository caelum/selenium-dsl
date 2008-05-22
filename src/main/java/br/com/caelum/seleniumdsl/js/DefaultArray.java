package br.com.caelum.seleniumdsl.js;

import com.thoughtworks.selenium.Selenium;

/**
 * A selenium based array
 * 
 * @author Guilherme Silveira
 */
public class DefaultArray implements Array {

	private final Selenium selenium;
	private final String name;

	public DefaultArray(Selenium selenium, String name) {
		this.selenium = selenium;
		this.name = name;
	}

	public Array array(int k) {
		return new DefaultArray(selenium, name + "[" + k + "]");
	}

	public Element get(int k) {
		return new DefaultElement(selenium, name + "[" + k + "]");
	}

	public int size() {
		return Integer.parseInt(selenium.getEval("this.browserbot.getCurrentWindow()." + name + ".length"));
	}

}
