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

}