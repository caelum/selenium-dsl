package br.com.caelum.seleniumdsl.js;

/**
 * A javascript object. Not named object for obvious reasons.
 * 
 * @author Guilherme Silveira
 * 
 */
public interface Element {
	
	/**
	 * Returns this element's property valeu.
	 * @param property
	 * @return
	 */
	String value(String property);
	
	/**
	 * Returns this element's element.
	 * @param name
	 * @return
	 */
	Element element(String name);
	
	/**
	 * Return its own value.
	 * @return	its own value
	 */
	String value();

}
