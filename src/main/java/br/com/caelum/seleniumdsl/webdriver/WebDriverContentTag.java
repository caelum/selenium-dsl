package br.com.caelum.seleniumdsl.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.caelum.seleniumdsl.ContentTag;

public class WebDriverContentTag implements ContentTag {

	private final WebDriver webDriver;
	private final String id;
	private WebElement element;

	public WebDriverContentTag(final WebDriver webDriver, final String id) {
		this.webDriver = webDriver;
		this.id = id;
	}

	public boolean contains(final String content) {
		return getElement().getText().contains(content);
	}

	public boolean exists() {
		return getElement() != null;
	}

	public String innerHTML() {
		return getElement().getText();
	}

	private WebElement getElement() {
		if(element == null) {
			element = webDriver.findElement(new ByIdOrNameOrXPath(id));
		}

		return element;
	}
}
