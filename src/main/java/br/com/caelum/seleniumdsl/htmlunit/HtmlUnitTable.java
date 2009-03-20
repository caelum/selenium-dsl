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

class HtmlUnitTable implements Table {

    private final String id;

    public HtmlUnitTable(String id) {
        this.id = id;
    }

    public Cell cell(int row, int col) {
        throw new NotImplementedException();
    }

    public Cell cell(int row, String col) {
        throw new NotImplementedException();
    }

    public Column column(int columnIndex) {
        throw new NotImplementedException();
    }

    public Column column(String columnName) {
        throw new NotImplementedException();
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
