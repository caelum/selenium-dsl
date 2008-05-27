package br.com.caelum.seleniumdsl;

/**
 * A form field.
 * 
 * @author Guilherme Silveira
 */
public interface Field {

	/**
	 * Types something on this field.
	 * 
	 * @param content
	 *            the content to be typed
	 * @return the Form
	 */
	Form type(String content);

	/**
	 * Checks if this field contains a value.
	 * 
	 * @param content
	 *            the text to be compared
	 * @return if the field contains the text
	 */
	boolean contains(String content);

	/**
	 * Retrieves its content.
	 * 
	 * @return the text of this field
	 */
	String content();

	/**
	 * Executes the blur action in this component. Typically used to simulate a
	 * typing+lose focus action on a field to execute its javascript action.
	 */
	void blur();

	/**
	 * Executes the change action in this component.
	 */
	void change();


}