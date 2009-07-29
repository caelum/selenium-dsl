package br.com.caelum.seleniumdsl.hamcrest;

import org.hamcrest.Matcher;

import br.com.caelum.seleniumdsl.ContentTag;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.SelectField;
import br.com.caelum.seleniumdsl.table.Column;
import br.com.caelum.seleniumdsl.table.Table;

/**
 * A collection of hamcrest matchers for some SeleniumDSL classes
 * @author Lucas Cavalcanti
 *
 */
public class SeleniumDslMatchers {
	private SeleniumDslMatchers() {}

	/**
	 * Given div (or other ContentTag) exists?
	 */
	public static <T extends ContentTag> Matcher<T> divExists() {
		return DivExistsMatcher.<T>divExists();
	}

	/**
	 * Given div (or other ContentTag) contains this text?
	 */
	public static <T extends ContentTag> Matcher<T> divContains(String text) {
		return DivContainsMatcher.<T>divContains(text);
	}

	/**
	 * Given form has this checkbox checked?
	 */
	public static <T extends Form> Matcher<T> isChecked(String checkbox) {
		return IsCheckedMatcher.isChecked(checkbox);
	}

	/**
	 * Given page has this link?
	 */
	public static <T extends Page> Matcher<T> hasLink(String linkName) {
		return HasLinkMatcher.hasLink(linkName);
	}

	/**
	 * Given select field contains one option with this label?
	 */
	public static <T extends SelectField> Matcher<T> containsOption(String label) {
		return ContainsOptionMatcher.containsOption(label);
	}

	/**
	 * Given table contains a column with this name?
	 */
	public static <T extends Table> Matcher<T> containsColumn(String name) {
		return ContainsColumnMatcher.containsColumn(name);
	}

	/**
	 * Given column contains exactly this text in one of its cells?
	 */
	public static <T extends Column> Matcher<T> columnContains(String text) {
		return ColumnContainsMatcher.columnContains(text);
	}

	/**
	 * Given column contains a part of this text in one of its cells?
	 */
	public static <T extends Column> Matcher<T> columnContainsPartial(String text) {
		return ColumnContainsPartialMatcher.columnContainsPartial(text);
	}
}
