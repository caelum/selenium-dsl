package br.com.caelum.seleniumdsl;

/**
 * An html element with inner html.
 * 
 * @author Guilherme Silveira
 */
public interface ContentTag {

	/**
	 * Searches this element for a specific string.
	 * 
	 * @param content
	 *            the string to be searched
	 * @return true if the tag contains the string
	 */
	public boolean contains(String content);

	/**
	 * Checks if this element exists
	 * 
	 * @return if this element exists on the page
	 */
	public boolean exists();

	/**
	 * Returns its inner html.
	 * 
	 * @return the inner html code
	 */
	public String innerHTML();

}