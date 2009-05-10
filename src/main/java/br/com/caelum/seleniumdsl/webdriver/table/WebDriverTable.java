package br.com.caelum.seleniumdsl.webdriver.table;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.WebDriver;

import br.com.caelum.seleniumdsl.search.RowMatcher;
import br.com.caelum.seleniumdsl.search.RowVisitor;
import br.com.caelum.seleniumdsl.search.TableCriteria;
import br.com.caelum.seleniumdsl.table.Cell;
import br.com.caelum.seleniumdsl.table.Column;
import br.com.caelum.seleniumdsl.table.Row;
import br.com.caelum.seleniumdsl.table.Table;
import br.com.caelum.seleniumdsl.table.layout.TableLayout;
import br.com.caelum.seleniumdsl.webdriver.ByIdOrNameOrXPath;
import br.com.caelum.seleniumdsl.webdriver.table.layout.WebDriverTableLayoutChooser;

public class WebDriverTable implements Table {

	private final WebDriver webDriver;
	private final String id;
	private final String type;
	private final TableLayout layout;

	private Integer rowCount;
	private Integer colCount;

	public WebDriverTable(final WebDriver webDriver, final String id) {
		this(webDriver, id, "id");
	}

	public WebDriverTable(final WebDriver webDriver, final String value, final String type) {
		this.webDriver = webDriver;
		this.id = value;
		this.type = type;

		layout = new WebDriverTableLayoutChooser(webDriver, value, type).choose();
	}

	public Cell cell(final int row, final int col) {
		return new WebDriverCell(webDriver, this, row, col);
	}

	public Cell cell(final int row, final String col) {
		return new WebDriverCell(webDriver, this, row, findColumn(col));
	}

	public Column column(final int columnIndex) {
		return new WebDriverColumn(this, columnIndex);
	}

	public Column column(final String columnName) {
		return column(findColumn(columnName));
	}

	public Row header() {
		return row(1);
	}

	public Row row(final Integer row) {
		return new WebDriverRow(this, webDriver, row);
	}

	public TableCriteria createCriteria() {
		throw new NotImplementedException();
	}

	public boolean exists() {
		return webDriver.findElement(new ByIdOrNameOrXPath(getId())) != null;
	}

	public Integer findColumn(final String columnName) {
		final Row row = new WebDriverRow(this, webDriver, 1);
		final int colCount = getColCount();

		for (int i = 0; i < colCount; i++) {
			String current;
			try {
				current = row.cell(i + 1).headerValue();
			} catch (final Exception e) {
				current = row.cell(i + 1).value();
			}
			if (columnName.equals(current)) {
				return i + 1;
			}
		}

		throw new IllegalArgumentException("Column " + columnName + " not found");
	}

	public int getColCount() {
		if (colCount == null) {
			colCount = layout.getColCount();
		}

		return colCount;
	}

	public int getContentCount() {
		return layout.getContentCount();
	}

	public String getId() {
		return id;
	}

	public TableLayout getLayout() {
		return layout;
	}

	public int getRowCount() {
		if (rowCount == null) {
			rowCount = layout.getRowCount();
		}

		return rowCount;
	}

	public String getType() {
		return type;
	}

	public void iterate(final RowVisitor visitor) {
		throw new NotImplementedException();
	}

	public RowMatcher select(final RowMatcher matcher) {
		throw new NotImplementedException();
	}
}
