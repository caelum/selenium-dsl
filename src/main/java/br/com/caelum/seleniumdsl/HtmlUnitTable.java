package br.com.caelum.seleniumdsl;

import br.com.caelum.seleniumdsl.search.RowMatcher;
import br.com.caelum.seleniumdsl.search.RowVisitor;
import br.com.caelum.seleniumdsl.search.TableCriteria;
import br.com.caelum.seleniumdsl.table.Cell;
import br.com.caelum.seleniumdsl.table.Column;
import br.com.caelum.seleniumdsl.table.Row;
import br.com.caelum.seleniumdsl.table.Table;
import br.com.caelum.seleniumdsl.table.layout.TableLayout;

public class HtmlUnitTable implements Table {

    private final String id;

    public HtmlUnitTable(String id) {
        this.id = id;
    }

    public Cell cell(int row, int col) {
        // TODO Auto-generated method stub
        return null;
    }

    public Cell cell(int row, String col) {
        // TODO Auto-generated method stub
        return null;
    }

    public Column column(int columnIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    public Column column(String columnName) {
        // TODO Auto-generated method stub
        return null;
    }

    public TableCriteria createCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean exists() {
        // TODO Auto-generated method stub
        return false;
    }

    public Integer findColumn(String columnName) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getColCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getContentCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    public TableLayout getLayout() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getRowCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getType() {
        // TODO Auto-generated method stub
        return null;
    }

    public Row header() {
        // TODO Auto-generated method stub
        return null;
    }

    public void iterate(RowVisitor visitor) {
        // TODO Auto-generated method stub

    }

    public Row row(Integer row) {
        // TODO Auto-generated method stub
        return null;
    }

    public RowMatcher select(RowMatcher matcher) {
        // TODO Auto-generated method stub
        return null;
    }

}
