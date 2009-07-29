package br.com.caelum.seleniumdsl.hamcrest;

import org.hamcrest.Matcher;

import br.com.caelum.seleniumdsl.ContentTag;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.SelectField;

public class SeleniumDslMatchers {
	private SeleniumDslMatchers() {}

	public static <T extends ContentTag> Matcher<T> divExists() {
		return DivExistsMatcher.<T>divExists();
	}

	public static <T extends ContentTag> Matcher<T> divContains(String text) {
		return DivContainsMatcher.<T>divContains(text);
	}

	public static <T extends Form> Matcher<T> isChecked(String checkbox) {
		return IsCheckedMatcher.isChecked(checkbox);
	}

	public static <T extends Page> Matcher<T> hasLink(String linkName) {
		return HasLinkMatcher.hasLink(linkName);
	}

	public static <T extends SelectField> Matcher<T> containsOption(String label) {
		return ContainsOptionMatcher.containsOption(label);
	}
}
