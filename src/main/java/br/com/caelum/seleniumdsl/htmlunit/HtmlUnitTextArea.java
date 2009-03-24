package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;

import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

public class HtmlUnitTextArea implements Field {

	private final HtmlTextArea textArea;
	private final HtmlUnitForm parent;

	public HtmlUnitTextArea(HtmlUnitForm parent, HtmlTextArea textArea) {
		this.parent = parent;
		this.textArea = textArea;
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
		return textArea.getText();
	}

	public Form type(String content) {
		textArea.setText(content);
		return parent;
	}

}
