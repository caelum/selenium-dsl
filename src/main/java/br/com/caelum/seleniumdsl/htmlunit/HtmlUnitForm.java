package br.com.caelum.seleniumdsl.htmlunit;

import java.io.IOException;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.SelectField;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.ClickableElement;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

class HtmlUnitForm implements Form {

    private final HtmlForm form;
	private final HtmlUnitPage parent;

    public HtmlUnitForm(HtmlUnitPage page, HtmlForm htmlForm) {
        this.parent = page;
		this.form = htmlForm;
    }

    public Form check(String checkbox) {
    	
    	HtmlCheckBoxInput chk;
    	if (checkbox.startsWith("//")) {
    		chk = form.getFirstByXPath(checkbox);
    	} else {
    		chk = form.getInputByName(checkbox);
    	}
    	parent.setPage((HtmlPage) chk.setChecked(true));
    	return this;
    }

    public void click(String element) {
        navigate(element);
    }

    public Field field(String field) {
        try {
			return new HtmlUnitField(this, form.getInputByName(field));
		} catch (ElementNotFoundException e) {
			try {
				return new HtmlUnitTextArea(this, form.getTextAreaByName(field));
			} catch (ElementNotFoundException e1) {
				HtmlElement element = form.getElementById(field);
				if (element instanceof HtmlTextArea) {
					return new HtmlUnitTextArea(this, (HtmlTextArea) element);
				}
				return new HtmlUnitField(this, (HtmlInput) element);
			}
		}
    }
    
    public boolean isChecked(String checkbox) {
        throw new NotImplementedException();
    }

    public void navigate(String element) {
    	ClickableElement button;
    	try {
			button = form.getElementById(element);
		} catch (ElementNotFoundException e1) {
			button = form.getInputByName(element);
		}
    	try {
			parent.setPage((HtmlPage) button.click());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
    }

    public SelectField select(String selectField) {
    	try {
			return new HtmlUnitSelectField(this, form.getSelectByName(selectField));
		} catch (ElementNotFoundException e) {
			return new HtmlUnitSelectField(this, (HtmlSelect) form.getElementById(selectField));
		}
    }

    public void submit() {
        try {
        	HtmlSubmitInput submit = form.getOneHtmlElementByAttribute("input", "type", "submit");
        	parent.setPage((HtmlPage) submit.click());
        } catch (ElementNotFoundException e) {
        	HtmlPage page = (HtmlPage) form.getPage();
			ScriptResult result = page.executeJavaScript("document.getElementById('" + form.getId() + "').submit()");
			parent.setPage((HtmlPage) result.getNewPage());
		} catch (IOException e) {
			new IllegalStateException("Error while clicking", e);
		}
    }

    public Form uncheck(String checkbox) {
        throw new NotImplementedException();
    }
    
    HtmlUnitPage getParent() {
		return parent;
	}

}
