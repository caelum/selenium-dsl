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
public class DivExistsMatcher<T extends ContentTag> extends TypeSafeMatcher<T> {
	@Override
	public boolean matchesSafely(ContentTag item) {
		return item.exists();
	}

	public void describeTo(Description description) {
		description.appendText("a div that exists");
	}

	@Factory
    public static <T extends ContentTag> Matcher<T> divExists() {
        return new DivExistsMatcher<T>();
    }
}
