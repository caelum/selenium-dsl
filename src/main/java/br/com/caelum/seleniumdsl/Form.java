package br.com.caelum.seleniumdsl;

/**
 * A web form.
 * 
 * @author Guilherme Silveira
 */
public interface Form {

	/**
	 * @param field
	 *            the field's name or id
	 * @return the Field
	 */
	public Field field(String field);

	/**
	 * Clicks something on the page
	 * 
	 * @param element
	 *            can be the element ID, name or an xpath expression
	 */
	public void click(String element);

	/**
	 * Clicks something on the page and waits
	 * 
	 * @param element
	 *            can be the element ID, name or an xpath expression
	 */
	public void navigate(String element);

	/**
	 * 
	 * @param selectField
	 *            the select's name or id
	 * @return the SelectField
	 */
	public SelectField select(String selectField);

	/**
	 * Checks a checkbox
	 * 
	 * @param checkbox
	 *            the input's name or id
	 * @return the Form
	 */
	public Form check(String checkbox);

	/**
	 * Unchecks a checkbox
	 * 
	 * @param checkbox
	 *            the input's name or id
	 * @return the Form
	 */
	public Form uncheck(String checkbox);

	/**
	 * @param checkbox
	 *            the input's name or id
	 * @return if the checkbox is checked
	 */
	public boolean isChecked(String checkbox);

	/**
	 * Submits this form
	 */
	public void submit();

}