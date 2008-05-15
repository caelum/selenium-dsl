package br.com.caelum.seleniumdsl.table;

public interface Column {

	/**
	 * Searches the value among all the cells of this column.
	 * 
	 * @param text
	 *            the text to search
	 * @return if any cell contains the text
	 */
	public boolean contains(String text);

	public boolean containsPartial(String value);

	/**
	 * Same as contains but returns the index.
	 * 
	 * @param text
	 *            the text to search
	 * @return the row index of the cell or -1 if not found
	 */
	public int find(String text);

}