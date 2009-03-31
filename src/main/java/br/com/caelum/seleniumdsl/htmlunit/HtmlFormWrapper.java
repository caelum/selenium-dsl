package br.com.caelum.seleniumdsl.htmlunit;

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

	private final HtmlForm delegate;

	HtmlFormWrapper(HtmlForm delegate) {
		this.delegate = delegate;
	}

	public <X> X getFirstByXPath(String xpathExpr) {
		return delegate.getFirstByXPath(xpathExpr);
	}

	public <I extends HtmlInput> I getInputByNameOrId(String name) {
		try {
			return getInputByNameOrIdOrDie(name);
		} catch (ElementNotFoundException e) {
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
		} catch (ElementNotFoundException e) {
			return null;
		}
	}

	public <I extends HtmlInput> I getInputByNameOrIdOrDie(String name) throws ElementNotFoundException {
		try {
			return delegate.getInputByName(name);
		} catch (ElementNotFoundException e) {
			return delegate.getElementById(name);
		}
	}

	public <E extends HtmlElement> E getElementByIdOrDie(String id) throws ElementNotFoundException {
		return delegate.getElementById(id);
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
			return delegate.getInputByName(name);
		} catch (Exception e) {
			return null;
		}
	}


}
