package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.search.RowMatcher;
import br.com.caelum.seleniumdsl.search.RowVisitor;
import br.com.caelum.seleniumdsl.search.TableCriteria;
import br.com.caelum.seleniumdsl.table.Cell;
import br.com.caelum.seleniumdsl.table.Column;
import br.com.caelum.seleniumdsl.table.Row;
import br.com.caelum.seleniumdsl.table.Table;
import br.com.caelum.seleniumdsl.table.layout.TableLayout;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

class HtmlUnitTable implements Table {

    private final HtmlTable table;

    public HtmlUnitTable(HtmlTable htmlTable) {
        this.table = htmlTable;
    }

    public Cell cell(int row, int col) {
        throw new NotImplementedException();
    }

    public Cell cell(int row, String col) {
        return new HtmlUnitCell(table.getRow(row).getCell(getColumn(col)));
    }

    public Column column(int columnIndex) {
        throw new NotImplementedException();
    }

    public Column column(String columnName) {
    	return new HtmlUnitColumn(table, getColumn(columnName));
    }

    private int getColumn(String name) {
    	HtmlTableRow header = table.getRow(0);
    	for (int i = 0; i < header.getCells().size(); i++) {
    		if (header.getCell(i).getTextContent().equals(name)) {
    			return i;
    		}
		}
    	throw new IllegalArgumentException("Cannot find column " + name + " in: " + header.asText());
    }
    public TableCriteria createCriteria() {
        throw new NotImplementedException();
    }

    public boolean exists() {
        throw new NotImplementedException();
    }

    public Integer findColumn(String columnName) {
        throw new NotImplementedException();
    }

    public int getColCount() {
        throw new NotImplementedException();
    }

    public int getContentCount() {
        throw new NotImplementedException();
    }

    public String getId() {
        throw new NotImplementedException();
    }

    public TableLayout getLayout() {
        throw new NotImplementedException();
    }

    public int getRowCount() {
        throw new NotImplementedException();
    }

    public String getType() {
        throw new NotImplementedException();
    }

    public Row header() {
        throw new NotImplementedException();
    }

    public void iterate(RowVisitor visitor) {
        throw new NotImplementedException();
    }

    public Row row(Integer row) {
        throw new NotImplementedException();
    }

    public RowMatcher select(RowMatcher matcher) {
        throw new NotImplementedException();
    }

}
