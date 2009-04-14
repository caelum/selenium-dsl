package br.com.caelum.seleniumdsl;

/**
 * A select field
 * @author guilherme
 *
 */
public interface SelectField {

	/**
	 * Chooses an specific option for this select field.
	 * @param value
	 * @return
	 */
	Form choose(String value);

	/**
	 * Chooses an specific option for this select field.
	 * @param index	option index
	 * @return
	 */
	Form choose(int index);

	/**
	 * Returns the value for the current selected option.
	 * @return
	 */
	String value();
	
	/**
	 * Returns an array with all values for that select combo.
	 * @return
	 */
	String[] values();
	
	/**
	 * Returns the currrent content for the selected option.
	 * @return
	 */
	String content();
	
	/**
	 * Executes the blur action in this component. Typically used to simulate a
	 * typing+lose focus action on a field to execute its javascript action.
	 */
	void blur();

}