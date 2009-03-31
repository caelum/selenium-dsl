package br.com.caelum.seleniumdsl.htmlunit;

import br.com.caelum.seleniumdsl.table.Cell;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class NullCell implements Cell {

	private final String table;
	private final int row;
	private final int col;

	public NullCell(String table, int row, int col) {
		this.table = table;
		this.row = row;
		this.col = col;
	}

	public Cell check() {
		throw exception();
	}

	private ElementNotFoundException exception() {
		return new ElementNotFoundException(table, "row - col", row + " - " + col);
	}

	public boolean checked() {
		throw exception();
	}

	public boolean contains(String content) {
		return false;
	}

	public String getLink() {
		throw exception();
	}

	public String headerLinkValue() {
		throw exception();
	}

	public String headerValue() {
		throw exception();
	}

	public Cell uncheck() {
		throw exception();
	}

	public String value() {
		return "";
	}

}
