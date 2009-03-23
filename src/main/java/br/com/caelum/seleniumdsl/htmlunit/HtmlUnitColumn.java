package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.table.Column;

import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class HtmlUnitColumn implements Column {

	public HtmlUnitColumn(HtmlTable table, int i) {
	}

	public boolean contains(String text) {
		throw new NotImplementedException();
	}

	public boolean containsPartial(String value) {
		throw new NotImplementedException();
	}

	public int find(String text) {
		throw new NotImplementedException();
	}

}
