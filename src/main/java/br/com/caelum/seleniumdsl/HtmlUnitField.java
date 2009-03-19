package br.com.caelum.seleniumdsl;

import java.io.IOException;

import org.apache.commons.lang.NotImplementedException;

import com.gargoylesoftware.htmlunit.html.HtmlInput;

public class HtmlUnitField implements Field {

	private final HtmlInput input;
	private final Form parent;

	public HtmlUnitField(Form form, HtmlInput input) {
		this.parent = form;
		this.input = input;
	}

	public void blur() {
		throw new NotImplementedException();
	}

	public void change() {
		throw new NotImplementedException();
	}

	public boolean contains(String content) {
		throw new NotImplementedException();
	}

	public String content() {
		throw new NotImplementedException();
	}

	public Form type(String content) {
		try {
			input.type(content);
		} catch (IOException e) {
			throw new IllegalArgumentException("can't type " + content, e);
		}
		return parent;
	}

}
