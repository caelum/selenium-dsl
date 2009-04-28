package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.table.Cell;

import com.gargoylesoftware.htmlunit.html.HtmlTableCell;

public class HtmlUnitCell implements Cell {

	private static final char NBSP = (char) 160;
	private final HtmlTableCell cell;

	public HtmlUnitCell(HtmlTableCell cell) {
		this.cell = cell;
	}

	public Cell check() {
		throw new NotImplementedException();
	}

	public boolean checked() {
		throw new NotImplementedException();
	}

	public boolean contains(String content) {
		return value().equals(content);
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
		return cell.asXml().replaceAll("<.*?>", "").replace(NBSP, ' ').replaceAll("\\s+", " ").trim();
	}

}
