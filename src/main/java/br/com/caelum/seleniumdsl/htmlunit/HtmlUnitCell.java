package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.table.Cell;

import com.gargoylesoftware.htmlunit.html.HtmlTableCell;

public class HtmlUnitCell implements Cell {

	public HtmlUnitCell(HtmlTableCell cell) {
	}

	public Cell check() {
		throw new NotImplementedException();
	}

	public boolean checked() {
		throw new NotImplementedException();
	}

	public boolean contains(String content) {
		throw new NotImplementedException();
	}

	public String getLink() {
		throw new NotImplementedException();
	}

	public String headerLinkValue() {
		throw new NotImplementedException();
	}

	public String headerValue() {
		throw new NotImplementedException();
	}

	public Cell uncheck() {
		throw new NotImplementedException();
	}

	public String value() {
		throw new NotImplementedException();
	}

}
