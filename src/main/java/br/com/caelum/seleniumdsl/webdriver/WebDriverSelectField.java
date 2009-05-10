package br.com.caelum.seleniumdsl.webdriver;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.SelectField;

public class WebDriverSelectField implements SelectField {

	private final WebDriver webDriver;
	private final Form form;
	private final String id;
	private Select select;

	public WebDriverSelectField(final WebDriver webDriver, final Form form, final String id) {
		this.webDriver = webDriver;
		this.form = form;
		this.id = id;
	}

	public Form choose(final String value) {
		getSelect().selectByVisibleText(value);
		return form;
	}

	public Form choose(final int index) {
		getSelect().selectByIndex(index);
		return form;
	}

	public String content() {
		return getSelect().getFirstSelectedOption().getText();
	}

	public String value() {
		return getSelect().getFirstSelectedOption().getValue();
	}

	public String[] values() {
		final List<WebElement> options = getSelect().getOptions();
		final String[] values = new String[options.size()];

		for (int i = 0; i < options.size(); i++) {
			values[i] = options.get(i).getValue();
		}

		return values;
	}

	private Select getSelect() {
		if(select == null ) {
			select = new Select(webDriver.findElement(new ByIdOrNameOrXPath(id)));
		}

		return select;
	}

	public void blur() {
		throw new NotImplementedException();
	}
}
