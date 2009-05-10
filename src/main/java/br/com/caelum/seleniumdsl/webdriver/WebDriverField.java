package br.com.caelum.seleniumdsl.webdriver;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;

public class WebDriverField implements Field {

	private final String id;
	private final Form form;
	private final WebDriver webDriver;
	private WebElement element;

	public WebDriverField(final WebDriver webDriver, final Form form, final String id) {
		this.webDriver = webDriver;
		this.form = form;
		this.id = id;
	}

	public Form type(final String content) {
		getElement().sendKeys(content);
		return form;
	}

	public boolean contains(final String content) {
		return content.contains(getElement().getValue());
	}

	public String content() {
		return getElement().getValue();
	}

	public void blur() {
		throw new NotImplementedException();
	}

	public void change() {
		throw new NotImplementedException();
	}

	private WebElement getElement() {
		if(element == null) {
			element = webDriver.findElement(new ByIdOrNameOrXPath(id));
		}

		return element;
	}
}