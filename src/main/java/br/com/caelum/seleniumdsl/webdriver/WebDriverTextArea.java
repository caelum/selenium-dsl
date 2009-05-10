package br.com.caelum.seleniumdsl.webdriver;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.WebElement;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;

public class WebDriverTextArea implements Field {

	private final WebElement webElement;
	private final WebDriverForm webDriverForm;

	public WebDriverTextArea(final WebDriverForm webDriverForm, final WebElement webElement) {
		this.webDriverForm = webDriverForm;
		this.webElement = webElement;
	}

	public void blur() {
		throw new NotImplementedException();
	}

	public void change() {
		throw new NotImplementedException();
	}

	public boolean contains(final String content) {
		return webElement.getText().equals(content);
	}

	public String content() {
		return webElement.getText();
	}

	public Form type(final String content) {
		webElement.sendKeys(content);
		return webDriverForm;
	}

}
