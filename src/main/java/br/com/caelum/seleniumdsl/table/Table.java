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
	 * @return the br.com.caelum.seleniumdsl.Column object
	 */
	public Column column(int columnIndex);

	/**
	 * @param columnName
	 *            the label of the column.
	 * @return the br.com.caelum.seleniumdsl.Column object
	 */
	public Column column(String columnName);

	public int getColCount();

	public int getRowCount();

	public int getContentCount();

	public Row header();

	/**
	 * @param row
	 *            Only counts the CONTENTS of the table. So index 1 is the first data row of the table, ignoring the header.
	 * @return the Row object
	 */
	public Row row(Integer row);

	/**
	 * @param row
	 *            Only counts the CONTENTS of the table. So index 1 is the first data row of the table, ignoring the header.
	 * @return the Cell object
	 */
	public Cell cell(int row, int col);

	/**
	 * @param row
	 *            Only counts the CONTENTS of the table. So index 1 is the first data row of the table, ignoring the header.
	 * @return the Cell object
	 */
	public Cell cell(int row, String col);

	/**
	 * @param col
	 *            the header text of the column
	 * @param content
	 *            the content to search
	 * @return true if the content exists
	 */
	public boolean contains(String col, String content);

	public String getId();

	public boolean exists();

	public void iterate(RowVisitor visitor);

	public Integer findColumn(String columnName);

	public RowMatcher select(RowMatcher matcher);

	public TableCriteria createCriteria();

	public TableLayout getLayout();

}