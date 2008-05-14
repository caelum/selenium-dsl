package br.com.caelum.seleniumdsl.table.layout;

import br.com.caelum.seleniumdsl.table.Table;

public interface TableLayout {
	/**
	 * @param col
	 *            the column index
	 * @return the text of the column
	 */
	public String headerValue(int col);

	/**
	 * Used when the header's text is a link. Common when doing pagination
	 * 
	 * @param col
	 *            the column index
	 * @return the '<a href' tag's text
	 */
	public String headerLinkValue(int col);

	/**
	 * @return The tbody rows. Some layouts will remove the first row and return all the others, ignoring if the last row is a footer or no.
	 */
	public int getContentCount();

	/**
	 * @param row
	 *            the row index
	 * @param col
	 *            the col index
	 * @return the text of the cell
	 */
	public String value(int row, int col);

	/**
	 * @return The number of columns on the header.
	 */
	public int getColCount();

	/**
	 * @return The full row count, including possible header and footer.
	 */
	public int getRowCount();

	/**
	 * @param col
	 *            the header text of the column
	 * @param content
	 *            the content to search
	 * @return true if the content exists
	 */
	public boolean contains(Table table, String col, String content);
}
