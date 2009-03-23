package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.table.Column;

import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class HtmlUnitColumn implements Column {

	private final HtmlTable table;
	private final int index;

	public HtmlUnitColumn(HtmlTable table, int index) {
		this.table = table;
		this.index = index;
	}

	public boolean contains(String text) {
		return find(text) != -1;
	}

	public boolean containsPartial(String value) {
		throw new NotImplementedException();
	}

	public int find(String text) {
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getRow(i).getCell(index).getTextContent().equals(text)) {
				return i;
			}
		}
		return -1;
	}

}
