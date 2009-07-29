package br.com.caelum.seleniumdsl.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.seleniumdsl.table.Table;

public class ContainsColumnMatcher<T extends Table> extends TypeSafeMatcher<T> {

	private final String name;

	public ContainsColumnMatcher(String name) {
		this.name = name;
	}
	@Override
	public boolean matchesSafely(T item) {
		try {
			Integer index = item.findColumn(name);
			return index >= 0;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	public void describeTo(Description description) {
		description.appendText("a table with a column " + name);
	}

	@Factory
	public static <T extends Table> Matcher<T> containsColumn(String name) {
		return new ContainsColumnMatcher<T>(name);
	}

}
