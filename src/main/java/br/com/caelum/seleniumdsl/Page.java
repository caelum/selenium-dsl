package br.com.caelum.seleniumdsl;

import br.com.caelum.seleniumdsl.js.Array;
import br.com.caelum.seleniumdsl.table.Table;

/**
 * A web page.
 * 
 * @author Guilherme Silveira
 */
public interface Page {

	Form form(String id);

	ContentTag div(String id);

	ContentTag span(String id);

	Table table(String id);

	/**
	 * Clicks an element and waits for the browser to load the page
	 * 
	 * @param element
	 *            the elements's id or name or an Selenium expression
	 * @return the Page
	 */
	Page navigate(String element);

	/**
	 * Clicks something
	 * 
	 * @param element
	 *            the element's id or name or a Selenium expression
	 * @return the Page
	 */
	Page click(String element);

	boolean hasLink(String link);

	boolean isFilled(String textBoxId, String value);

	Page check(String checkbox);

	/**
	 * Returns the page title.
	 * 
	 * @return the page title
	 */
	String title();
	
	/**
	 * Returns access to a javascript array.
	 * @param name	the variable name
	 * @return	the javascript array
	 */
	Array array(String name);

}