package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.log4j.Logger;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.javascript.host.Event;

class HtmlUnitField implements Field {

	
	private static final Logger logger = Logger.getLogger(HtmlUnitField.class);
	
	private final HtmlInput input;
	private final Form parent;

	public HtmlUnitField(Form form, HtmlInput input) {
		this.parent = form;
		this.input = input;
	}

	public void blur() {
		if (logger.isDebugEnabled()) {
			logger.debug("Firing blur on " + input);
		}
		input.fireEvent(Event.TYPE_BLUR);
	}

	public void change() {
		if (logger.isDebugEnabled()) {
			logger.debug("Firing change on " + input);
		}
		input.fireEvent(Event.TYPE_CHANGE);
	}

	public boolean contains(String content) {
		return content().contains(content);
	}

	public String content() {
		return input.getValueAttribute();
	}

	public Form type(String content) {
		if (logger.isDebugEnabled()) {
			logger.debug("setting value " + content + " on " + input);
		}
		input.setValueAttribute(content);
		return parent;
	}

}
