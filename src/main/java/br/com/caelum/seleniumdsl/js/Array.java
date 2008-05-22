package br.com.caelum.seleniumdsl.js;

/**
 * A javascript array.
 * 
 * @author Guilherme Silveira
 */
public interface Array {

	/**
	 * Return this arrays size
	 * 
	 * @return
	 */
	int size();

	/**
	 * Returns an specific array within this array.
	 * 
	 * @param k
	 *            the element number in this array
	 * @return the element itself
	 */
	Array array(int k);

	/**
	 * Returns an specific element.
	 * 
	 * @param k
	 *            the element number in this array
	 * @return the element itself
	 */
	Element get(int k);

}
