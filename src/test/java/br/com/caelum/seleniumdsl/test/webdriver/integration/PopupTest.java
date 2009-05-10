package br.com.caelum.seleniumdsl.test.webdriver.integration;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;

public class PopupTest extends WebDriverTestCase {

	private Page page;

	@Override
	public void setUp() {
		super.setUp();
		page = browser.open(URL + "openPopup.html");
	}

	@Test
	public void testaQuePopupAbreCorretamente() throws Exception {
		//There is a bug in InternetExplorerDriver, it doesnt open the window.

		final String input = "teste";
		page.click("popup");

		final Page popupPage = browser.window("popup");

		final Form form = popupPage.form("form");
		final Field field = form.field("textinput");
		field.type(input);

		Assert.assertTrue(field.contains(input));
	}

}
