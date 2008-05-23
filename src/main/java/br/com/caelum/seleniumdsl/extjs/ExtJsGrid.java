package br.com.caelum.seleniumdsl.extjs;

import com.thoughtworks.selenium.Selenium;

public class ExtJsGrid {
	
	private final Selenium selenium;
	private final String name;

	public ExtJsGrid(Selenium selenium, String grid) {
		this.selenium = selenium;
		this.name = grid;
	}
	
	public Store store() {
		return new Store(selenium, this);
	}

	public String getIdentifier() {
		return name;
	}

}
