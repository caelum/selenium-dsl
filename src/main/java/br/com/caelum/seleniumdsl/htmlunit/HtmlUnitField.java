package br.com.caelum.seleniumdsl.htmlunit;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.javascript.host.Event;

class HtmlUnitField implements Field {

	private final HtmlInput input;
	private final Form parent;

	public HtmlUnitField(Form form, HtmlInput input) {
		this.parent = form;
		this.input = input;
	}

	public void blur() {
		input.fireEvent(Event.TYPE_BLUR);
	}

	public void change() {
		input.fireEvent(Event.TYPE_CHANGE);
	}

	public boolean contains(String content) {
		return content().contains(content);
	}

	public String content() {
		return input.getValueAttribute();
	}

	public Form type(String content) {
		input.setValueAttribute(content);
		return parent;
	}

}
