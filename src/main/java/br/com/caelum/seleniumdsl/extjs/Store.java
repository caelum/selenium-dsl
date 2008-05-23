package br.com.caelum.seleniumdsl.extjs;

import com.thoughtworks.selenium.Selenium;

public class Store {

	private final ExtJsGrid grid;
	private final Selenium selenium;

	public Store(Selenium selenium, ExtJsGrid grid) {
		this.selenium = selenium;
		this.grid = grid;
	}
	
	public Record recordAt(int position) {
		return new DefaultRecord(selenium, grid.getIdentifier() + ".store.data.getAt(" + position +")");
	}

}
