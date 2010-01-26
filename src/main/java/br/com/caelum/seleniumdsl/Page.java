package br.com.caelum.seleniumdsl;

import br.com.caelum.seleniumdsl.js.Array;
import br.com.caelum.seleniumdsl.table.Table;

/**
 * A web page.
 *
 * @author Guilherme Silveira
 */
public interface Page {

	/**
	 * Accesses a web form.
	 *
	 * @param id
	 * @return
	 */
	Form form(String id);

	/**
	 * Access to a div.
	 *
	 * @param id
	 * @return
	 */
	ContentTag div(String id);

	/**
	 * Access to a span.
	 *
	 * @param id
	 * @return
	 */
	ContentTag span(String id);

	/**
	 * Access to a table.
	 *
	 * @param id
	 * @return
	 */
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
	 * Clicks a link and waits for the browser to load the page
	 *
	 * @param text
	 *            the text of the link
	 * @return the Page
	 */
	Page navigateLink(String text);

	/**
	 * Clicks something
	 *
	 * @param element
	 *            the element's id or name or a Selenium expression
	 * @return the Page
	 */
	Page click(String element);

	/**
	 * Clicks a link
	 *
	 * @param text
	 *            the text of the link
	 * @return the Page
	 */
	Page clickLink(String text);
	/**
	 * Double clicks something
	 *
	 * @param element
	 *            the element's id or name or a Selenium expression
	 * @return the Page
	 */
	Page doubleClick(String element);

	/**
	 * Checks for the existence of a link
	 *
	 * @param link
	 * @return
	 */
	boolean hasLink(String link);

	/**
	 * Returns the page title.
	 *
	 * @return the page title
	 */
	String title();

	/**
	 * Returns access to a javascript array.
	 *
	 * @param name
	 *            the variable name
	 * @return the javascript array
	 */
	Array array(String name);

	/**
	 * Invokes a javascript command.
	 *
	 * @param cmd
	 *            a command
	 * @return the result
	 */
	String invoke(String cmd);

	/**
	 * Waits at most timeout miliseconds until condition is true.
	 *
	 * @param condition
	 *            a javascript command that evals to true or false
	 * @param timeout
	 *            max time to wait
	 */
	Page waitUntil(String condition, long timeout);

	/**
	 * Takes a screenshot to the designated file.
	 *
	 * @param filename
	 */
	void screenshot(String filename);

	/**
	 * Simulates the user clicking the "Refresh" button on their browser.
	 */
	Page refresh();

	/**
	 * Simulates the user pressing the mouse and holding on the designated element.
	 */
	Page mouseDown(String element);

	/**
	 * Simulates the user releasing the mouseon the designated element.
	 */
	Page mouseUp(String element);

	/**
	 * Simulates the user dragging and dropping.
	 */
	Page dragAndDrop(String fromElement, String toElement);

	/**
	 * Retrieves the HTML source of this page
	 */
	String htmlSource();

}