package br.com.caelum.seleniumdsl.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.seleniumdsl.ContentTag;

/**
 * See description on SeleniumDslMatchers
 * @author Lucas Cavalcanti
 */
public class DivContainsMatcher<T extends ContentTag> extends TypeSafeMatcher<T> {
	private final String text;

	public DivContainsMatcher(String text) {
		this.text = text;
	}
	@Override
	public boolean matchesSafely(ContentTag item) {
		return item.contains(text);
	}

	public void describeTo(Description description) {
		description.appendText("a div containing " + text);
	}

	@Factory
    public static <T extends ContentTag> Matcher<T> divContains(String text) {
        return new DivContainsMatcher<T>(text);
    }
}
