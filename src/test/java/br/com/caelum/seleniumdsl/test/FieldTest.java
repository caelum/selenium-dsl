package br.com.caelum.seleniumdsl.test;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.seleniumdsl.DefaultBrowser;
import br.com.caelum.seleniumdsl.Field;

import com.thoughtworks.selenium.Selenium;

public class FieldTest {
	private Selenium mock;
	private Mockery mockery;
	private Field field;

	@Before
	public void setUp() {
		mockery = new Mockery();
		mock = mockery.mock(Selenium.class);
		field = new DefaultBrowser(mock).currentPage().form("id").field("id");
	}

	@Test
	public void testContains() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).getValue(with(any(String.class)));
			}
		});
		field.contains("content");
		mockery.assertIsSatisfied();
	}

	@Test
	public void testContent() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).getValue(with(any(String.class)));
			}
		});
		field.content();
		mockery.assertIsSatisfied();
	}

	@Test
	public void testType() {
		mockery.checking(new Expectations() {
			{
				exactly(1).of(mock).type(with(any(String.class)), with(any(String.class)));
			}
		});
		field.type("content");
		mockery.assertIsSatisfied();
	}

}