package br.com.caelum.seleniumdsl.hamcrest;

import static br.com.caelum.seleniumdsl.hamcrest.SeleniumDslMatchers.divContains;
import static br.com.caelum.seleniumdsl.hamcrest.SeleniumDslMatchers.divExists;
import static br.com.caelum.seleniumdsl.hamcrest.SeleniumDslMatchers.hasLink;
import static br.com.caelum.seleniumdsl.hamcrest.SeleniumDslMatchers.isChecked;
import static org.junit.Assert.assertThat;
import br.com.caelum.seleniumdsl.ContentTag;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;
public class SeleniumDslMatchersTest {

	public void testingCompilationOfContentTagMatchers() {
		ContentTag tag = null;
		assertThat(tag, divExists());
		assertThat(tag, divContains("Anything"));
	}

	public void testingCompilationOfFormMatchers() {
		Form form = null;
		assertThat(form, isChecked(""));

		Page page = null;
		assertThat(page, hasLink(""));
	}
}
