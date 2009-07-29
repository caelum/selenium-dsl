package br.com.caelum.seleniumdsl.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.seleniumdsl.Page;

public class HasLinkMatcher<T extends Page> extends TypeSafeMatcher<T> {

	private final String linkName;

	public HasLinkMatcher(String linkName) {
		this.linkName = linkName;
	}

	@Override
	public boolean matchesSafely(T item) {
		return item.hasLink(linkName);
	}

	public void describeTo(Description description) {
		description.appendText("a page containing link");
	}

	@Factory
	public static <T extends Page> Matcher<T> hasLink(String linkName) {
		return new HasLinkMatcher<T>(linkName);
	}

}
