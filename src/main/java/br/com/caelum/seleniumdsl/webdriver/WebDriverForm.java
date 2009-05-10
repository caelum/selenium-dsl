package br.com.caelum.seleniumdsl.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.SelectField;

public class WebDriverForm implements Form {

	private final WebDriver webDriver;
	private final String id;

	public WebDriverForm(final WebDriver webDriver, final String id) {
		this.webDriver = webDriver;
		this.id = id;
	}

	public Form check(final String checkbox) {
		webDriver.findElement(new ByIdOrNameOrXPath(checkbox)).setSelected();
		return this;
	}

	public void click(final String element) {
		webDriver.findElement(new ByIdOrNameOrXPath(element)).click();
	}

	public Field field(final String field) {
		return new WebDriverField(webDriver, this, field);
	}

	public boolean isChecked(final String checkbox) {
		return webDriver.findElement(new ByIdOrNameOrXPath(checkbox)).isSelected();
	}

	public void navigate(final String element) {
		webDriver.findElement(new ByIdOrNameOrXPath(element)).click();
	}

	public SelectField select(final String selectField) {
		return new WebDriverSelectField(webDriver, this, selectField);
	}

	public void submit() {
		webDriver.findElement(id == "" ? By.xpath("//form") : By.id(id.substring(0, id.length() - 1))).submit();
	}

	public Form uncheck(final String checkbox) {
		final WebElement check = webDriver.findElement(By.name(checkbox));
		if(check.isSelected()) {
			check.toggle();
		}

		return this;
	}
}
