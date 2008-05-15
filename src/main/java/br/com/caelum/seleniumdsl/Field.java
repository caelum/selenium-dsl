package br.com.caelum.seleniumdsl;

public interface Field {

	/**
	 * Types something on this field.
	 * 
	 * @param content
	 *            the content to be typed
	 * @return the Form
	 */
	public Form type(String content);

	/**
	 * @param text
	 *            the text to be compared
	 * @return if the field contains the text
	 */
	public boolean contains(String text);

	/**
	 * @return the text of this field
	 */
	public String content();

}