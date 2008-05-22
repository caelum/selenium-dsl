package br.com.caelum.seleniumdsl;

/**
 * The web browser interface. This is the entry point.
 * 
 * @author Guilherme Silveira
 */
public interface Browser {

	/**
	 * Opens a specific url.
	 * 
	 * @param url
	 *            the url
	 * @return the web page
	 */
	Page open(String url);

	/**
	 * Access the current page.
	 * 
	 * @return page the page itself
	 */
	Page currentPage();

	/**
	 * Returns the internal implementation which is used by this browser api.
	 * 
	 * @return the delegate object
	 */
	Object getDelegate();

}