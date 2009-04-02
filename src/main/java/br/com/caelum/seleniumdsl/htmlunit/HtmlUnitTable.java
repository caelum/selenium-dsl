package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.search.RowMatcher;
import br.com.caelum.seleniumdsl.search.RowVisitor;
import br.com.caelum.seleniumdsl.search.TableCriteria;
import br.com.caelum.seleniumdsl.table.Cell;
import br.com.caelum.seleniumdsl.table.Column;
import br.com.caelum.seleniumdsl.table.DefaultColumn;
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
        try {
			return new HtmlUnitCell(table.getRow(row).getCell(col - 1));
		} catch (IndexOutOfBoundsException e) {
			return new NullCell(table.getId() + " - " + table.getAttribute("name"), row, col);
		}
    }

    public Cell cell(int row, String col) {
        return cell(row, findColumn(col));
    }

    public Column column(int columnIndex) {
        throw new NotImplementedException();
    }

    public Column column(String columnName) {
    	return new DefaultColumn(this, findColumn(columnName));
    }

    public TableCriteria createCriteria() {
        throw new NotImplementedException();
    }

    public boolean exists() {
        return table != null;
    }

    public Integer findColumn(String columnName) {
    	HtmlTableRow header = table.getRow(0);
    	for (int i = 0; i < header.getCells().size(); i++) {
    		if (header.getCell(i).getTextContent().trim().equals(columnName)) {
    			return i + 1;
    		}
		}
    	throw new IllegalArgumentException("Cannot find column " + columnName + " in: " + header.asText());
    }

    public int getColCount() {
        throw new NotImplementedException();
    }

    public int getContentCount() {
    	int result = table.getRowCount();
    	if (table.getHeader() != null) {
    		result -= table.getHeader().getRows().size();
    	}
    	if (table.getFooter() != null) {
    		result -= table.getFooter().getRows().size();
    	}
        return result;
    }

    public String getId() {
        throw new NotImplementedException();
    }

    public TableLayout getLayout() {
        throw new NotImplementedException();
    }

    public int getRowCount() {
        return table.getRowCount();
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
        return new HtmlUnitRow(this, row);
    }

    public RowMatcher select(RowMatcher matcher) {
        throw new NotImplementedException();
    }

}
