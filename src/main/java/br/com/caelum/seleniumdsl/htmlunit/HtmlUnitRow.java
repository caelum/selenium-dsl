package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.table.Cell;
import br.com.caelum.seleniumdsl.table.Row;

public class HtmlUnitRow implements Row {

	private final HtmlUnitTable table;
	private final Integer row;

	public HtmlUnitRow(HtmlUnitTable table, Integer row) {
		this.table = table;
		this.row = row;
	}

	public Cell cell(int column) {
		return table.cell(row, column);
	}

	public Cell cell(String column) {
		return table.cell(row, column);
	}

	public Integer index() {
		throw new NotImplementedException();
	}

}
