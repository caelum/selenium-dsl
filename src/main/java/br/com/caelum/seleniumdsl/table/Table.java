package br.com.caelum.seleniumdsl.table;

import br.com.caelum.seleniumdsl.search.RowMatcher;
import br.com.caelum.seleniumdsl.search.RowVisitor;
import br.com.caelum.seleniumdsl.search.TableCriteria;
import br.com.caelum.seleniumdsl.table.layout.TableLayout;

public interface Table {

	public String getType();

	/**
	 * @param columnIndex
	 *            the index of the column.
	 * @return the br.com.caelum.seleniumdsl.table.Column object
	 */
	public Column column(int columnIndex);

	/**
	 * @param columnName
	 *            the label of the column.
	 * @return the br.com.caelum.seleniumdsl.Column object
	 */
	public Column column(String columnName);

	public int getColCount();

	/**
	 * The first time that method is called for, it makes the internal cache. If method is called for again, it uses the
	 * cached value.
	 */
	public int getRowCount();

	/**
	 * @return the number of rows of the contents, ignoring header and footer (if one is found)
	 */
	public int getContentCount();

	/**
	 * @return the header br.com.caelum.seleniumdsl.table.Row object
	 */
	public Row header();

	/**
	 * @param row
	 *            Only counts the CONTENTS of the table. So index 1 is the first data row of the table, ignoring the
	 *            header.
	 * @return the br.com.caelum.seleniumdsl.table.Row object
	 */
	public Row row(Integer row);

	/**
	 * @param row
	 *            Only counts the CONTENTS of the table. So index 1 is the first data row of the table, ignoring the
	 *            header.
	 * @return the Cell object
	 */
	public Cell cell(int row, int col);

	/**
	 * @param row
	 *            Only counts the CONTENTS of the table. So index 1 is the first data row of the table, ignoring the
	 *            header.
	 * @return the Cell object
	 */
	public Cell cell(int row, String col);

	public String getId();

	public boolean exists();

	public void iterate(RowVisitor visitor);

	/**
	 * @param columnName
	 *            the header label of the column
	 * @return the column index
	 */
	public Integer findColumn(String columnName);

	public RowMatcher select(RowMatcher matcher);

	public TableCriteria createCriteria();

	public TableLayout getLayout();

}