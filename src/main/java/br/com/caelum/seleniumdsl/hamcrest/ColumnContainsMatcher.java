package br.com.caelum.seleniumdsl.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.seleniumdsl.table.Column;

public class ColumnContainsMatcher<T extends Column> extends TypeSafeMatcher<T> {

	private final String text;

	public ColumnContainsMatcher(String text) {
		this.text = text;
	}
	@Override
	public boolean matchesSafely(T item) {
		return item.contains(text);
	}

	public void describeTo(Description description) {
		description.appendValue("a column containing " + text);
	}

	@Factory
	public static <T extends Column> Matcher<T> columnContains(String text) {
		return new ColumnContainsMatcher<T>(text);
	}
}
