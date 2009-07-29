package br.com.caelum.seleniumdsl.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.seleniumdsl.SelectField;

public class ContainsOptionMatcher<T extends SelectField> extends TypeSafeMatcher<T> {

	private final String label;

	public ContainsOptionMatcher(String label) {
		this.label = label;
	}
	@Override
	public boolean matchesSafely(T item) {
		String[] values = item.values();
		for (String string : values) {
			if (label.equals(string)) {
				return true;
			}
		}
		return false;
	}

	public void describeTo(Description description) {
		description.appendText("a select field that contains option " + label);
	}

	@Factory
	public static <T extends SelectField> Matcher<T> containsOption(String label) {
		return new ContainsOptionMatcher<T>(label);
	}
}
