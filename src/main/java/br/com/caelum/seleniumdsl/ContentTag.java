package br.com.caelum.seleniumdsl;

public interface ContentTag {

	/**
	 * @param content
	 *            the string to be searched
	 * @return true if the tag contains the string
	 */
	public boolean contains(String content);

	/**
	 * @return if this element exists on the page
	 */
	public boolean exists();

	public String innerHTML();

}