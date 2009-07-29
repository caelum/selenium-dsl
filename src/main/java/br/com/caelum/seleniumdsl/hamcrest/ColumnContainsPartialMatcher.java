package br.com.caelum.seleniumdsl.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.seleniumdsl.table.Column;

public class ColumnContainsPartialMatcher<T extends Column> extends TypeSafeMatcher<T> {

	private final String text;

	public ColumnContainsPartialMatcher(String text) {
		this.text = text;
	}
	@Override
	public boolean matchesSafely(T item) {
		return item.containsPartial(text);
	}

	public void describeTo(Description description) {
		description.appendValue("a column containing " + text);
	}

	@Factory
	public static <T extends Column> Matcher<T> columnContainsPartial(String text) {
		return new ColumnContainsPartialMatcher<T>(text);
	}
}
