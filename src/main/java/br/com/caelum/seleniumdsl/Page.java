package br.com.caelum.seleniumdsl;

import br.com.caelum.seleniumdsl.table.Table;

public interface Page {

	public Form form(String id);

	public ContentTag div(String id);

	public ContentTag span(String id);

	public Table table(String id);

	/**
	 * Clicks a link and waits for the browser to load the page
	 * 
	 * @param link
	 *            the link's id or name
	 * @return the Page
	 */
	public Page clickLink(String link);

	/**
	 * Clicks something
	 * 
	 * @param element
	 *            the element's id or name
	 * @return the Page
	 */
	public Page click(String element);

	public boolean hasLink(String link);

	public boolean isFilled(String textBoxId, String value);

	public Page check(String checkbox);

	public String title();

}