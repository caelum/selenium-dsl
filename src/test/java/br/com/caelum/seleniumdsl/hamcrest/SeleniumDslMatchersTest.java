package br.com.caelum.seleniumdsl.hamcrest;

import static br.com.caelum.seleniumdsl.hamcrest.SeleniumDslMatchers.divContains;
import static br.com.caelum.seleniumdsl.hamcrest.SeleniumDslMatchers.divExists;
import static org.junit.Assert.assertThat;
import br.com.caelum.seleniumdsl.ContentTag;
public class SeleniumDslMatchersTest {

	public void testingCompilationOfContentTagMatchers() {
		ContentTag tag = null;
		assertThat(tag, divExists());
		assertThat(tag, divContains("Anything"));
	}
}
