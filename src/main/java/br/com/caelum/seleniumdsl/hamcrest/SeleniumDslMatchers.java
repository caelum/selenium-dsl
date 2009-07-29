package br.com.caelum.seleniumdsl.hamcrest;

import org.hamcrest.Matcher;

import br.com.caelum.seleniumdsl.ContentTag;

public class SeleniumDslMatchers {
	private SeleniumDslMatchers() {}

	public static <T extends ContentTag> Matcher<T> divExists() {
		return DivExistsMatcher.<T>divExists();
	}

	public static <T extends ContentTag> Matcher<T> divContains(String text) {
		return DivContainsMatcher.<T>divContains(text);
	}
}
