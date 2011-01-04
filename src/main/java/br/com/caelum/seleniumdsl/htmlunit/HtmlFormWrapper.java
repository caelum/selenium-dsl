package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

class HtmlFormWrapper {

	private static final Logger logger = Logger.getLogger(HtmlFormWrapper.class);
	
	private final HtmlForm delegate;

	HtmlFormWrapper(HtmlForm delegate) {
		this.delegate = delegate;
	}

	public <X> X getFirstByXPath(String xpathExpr) {
		return delegate.<X>getFirstByXPath(xpathExpr);
	}

	public <I extends HtmlInput> I getInputByNameOrId(String name) {
		try {
			return this.<I>getInputByNameOrIdOrDie(name);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Input with name or id " + name + " not found");
			}
			return null;
		}
	}
	public HtmlTextArea getTextAreaByNameOrIdOrDie(String name) {
		try {
			return delegate.getTextAreaByName(name);
		} catch (ElementNotFoundException e) {
			return delegate.getElementById(name);
		}
	}
	public HtmlTextArea getTextAreaByNameOrId(String name) {
		try {
			return getTextAreaByNameOrIdOrDie(name);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("TextArea with name or id " + name + " not found");
			}
			return null;
		}
	}

	public <I extends HtmlInput> I getInputByNameOrIdOrDie(String name) throws ElementNotFoundException {
		try {
			return delegate.<I>getInputByName(name);
		} catch (ElementNotFoundException e) {
			return delegate.<I>getElementById(name);
		}
	}

	public <E extends HtmlElement> E getElementByIdOrDie(String id) throws ElementNotFoundException {
		return delegate.<E>getElementById(id);
	}

	
	public HtmlSelect getSelectByNameOrIdOrDie(String name) {
		try {
			return delegate.getSelectByName(name);
		} catch (ElementNotFoundException e) {
			return delegate.getElementById(name);
		}
	}

	public HtmlSubmitInput getSubmitButton() {
		try {
			return delegate.getOneHtmlElementByAttribute("input", "type", "submit");
		} catch (ElementNotFoundException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Submit not found");
			}
			return null;
		}
	}

	public HtmlPage submitJs() {
		HtmlPage page = (HtmlPage) delegate.getPage();
		ScriptResult result = page.executeJavaScript("document.getElementById('" + delegate.getId() + "').submit()");
		return (HtmlPage) result.getNewPage();
	}

	public <I extends HtmlInput> I getInputByName(String name) {
		try {
			return delegate.<I>getInputByName(name);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Input with name " + name + " not found.");
			}
			return null;
		}
	}


	@Override
	public String toString() {
		return delegate.toString();
	}
}
