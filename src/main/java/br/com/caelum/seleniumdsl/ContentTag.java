package br.com.caelum.seleniumdsl;

public interface ContentTag {

	/**
	 * @param content
	 *            the string to be searched
	 * @return true if the tag contains the string
	 */
	public abstract boolean contains(String content);

	/**
	 * @return if this element exists on the page
	 */
	public abstract boolean exists();

	public abstract String innerHTML();

}