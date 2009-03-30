package br.com.caelum.seleniumdsl.htmlunit;

import br.com.caelum.seleniumdsl.search.RowMatcher;
import br.com.caelum.seleniumdsl.search.RowVisitor;
import br.com.caelum.seleniumdsl.search.TableCriteria;
import br.com.caelum.seleniumdsl.table.Cell;
import br.com.caelum.seleniumdsl.table.Column;
import br.com.caelum.seleniumdsl.table.Row;
import br.com.caelum.seleniumdsl.table.Table;
import br.com.caelum.seleniumdsl.table.layout.TableLayout;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class InexistantTable implements Table {

	private final String id;

	public InexistantTable(String id) {
		this.id = id;
	}

	public Cell cell(int row, int col) {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public Cell cell(int row, String col) {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public Column column(int columnIndex) {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public Column column(String columnName) {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public TableCriteria createCriteria() {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public boolean exists() {
		return false;
	}

	public Integer findColumn(String columnName) {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public int getColCount() {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public int getContentCount() {
		return -1;
	}

	public String getId() {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public TableLayout getLayout() {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public int getRowCount() {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public String getType() {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public Row header() {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public void iterate(RowVisitor visitor) {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public Row row(Integer row) {
		throw new ElementNotFoundException("table", "id|name", id);
	}

	public RowMatcher select(RowMatcher matcher) {
		throw new ElementNotFoundException("table", "id|name", id);
	}

}
