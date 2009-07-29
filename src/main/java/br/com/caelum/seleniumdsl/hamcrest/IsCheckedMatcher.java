package br.com.caelum.seleniumdsl.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.seleniumdsl.Form;

public class IsCheckedMatcher<T extends Form> extends TypeSafeMatcher<T>{

	private final String checkbox;

	public IsCheckedMatcher(String checkbox) {
		this.checkbox = checkbox;
	}
	@Override
	public boolean matchesSafely(T item) {
		return item.isChecked(checkbox);
	}

	public void describeTo(Description description) {
		description.appendText("a form with field " + checkbox + " checked");
	}

	@Factory
	public static <T extends Form> Matcher<T> isChecked(String checkbox) {
		return new IsCheckedMatcher<T>(checkbox);
	}

}
