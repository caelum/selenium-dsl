package br.com.caelum.seleniumdsl.webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class ByIdOrNameOrXPath extends By {

	private final String property;
	private WebDriverException exception;
	private List<By> listOfBy;

	public ByIdOrNameOrXPath(final String property) {
		this.property = property;
		fillListOfBy();
	}

	private void fillListOfBy() {
		listOfBy = Arrays.asList(By.id(property), By.name(property), By.xpath(property));
	}

	@Override
	public List<WebElement> findElements(final SearchContext finder) {
		final List<WebElement> elements = new ArrayList<WebElement>();

		for (final By by : listOfBy) {
			elements.addAll(by.findElements(finder));
		}

		return elements;
	}

	@Override
	public WebElement findElement(final SearchContext finder) {
		for (final By by : listOfBy) {
			try {
				final WebElement toReturn = by.findElement(finder);
				if (toReturn != null) {
					return toReturn;
				}
			} catch (final WebDriverException e) {
				exception = e;
			}
		}

		throw exception;
	}
}
