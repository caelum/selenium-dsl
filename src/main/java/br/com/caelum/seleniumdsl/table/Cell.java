package br.com.caelum.seleniumdsl.table;

public interface Cell {

	/**
	 * @return the text of the cell or null if the cell is not found
	 */
	public String value();

	/**
	 * @return the text of the link contained on this cell or null if the cell is not found
	 */
	public String getLink();

	public String headerValue();

	public String headerLinkValue();

	/**
	 * Checks a possible checkbox contained on this cell
	 * 
	 * @return The Cell
	 */
	public Cell check();

	/**
	 * Unchecks a possible checkbox contained on this cell
	 * 
	 * @return The Cell
	 */
	public Cell uncheck();

	/**
	 * @return if the checkbox contained on this cell is checked
	 */
	public boolean checked();

	/**
	 * @param content
	 *            the content to search
	 * @return if the cell's text contains the parameter
	 */
	public boolean contains(String content);

}