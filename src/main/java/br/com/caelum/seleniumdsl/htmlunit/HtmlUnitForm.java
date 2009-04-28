package br.com.caelum.seleniumdsl.htmlunit;

import java.io.IOException;

import org.apache.log4j.Logger;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.SelectField;

import com.gargoylesoftware.htmlunit.html.ClickableElement;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

class HtmlUnitForm implements Form {
	
	private static final Logger logger = Logger.getLogger(HtmlUnitForm.class);
	
    private final HtmlFormWrapper form;
	private final HtmlUnitPage parent;

    public HtmlUnitForm(HtmlUnitPage page, HtmlFormWrapper htmlForm) {
        this.parent = page;
		this.form = htmlForm;
    }

    public Form check(String checkbox) {
    	if (logger.isDebugEnabled()) {
			logger.debug("Checking " + checkbox);
		}
    	HtmlCheckBoxInput chk = getCheckbox(checkbox);
    	parent.setPage((HtmlPage) chk.setChecked(true));
    	return this;
    }

	private HtmlCheckBoxInput getCheckbox(String checkbox) {
    	if (checkbox.startsWith("//")) {
    		if (logger.isDebugEnabled()) {
				logger.debug("finding checkbox by xpath " + checkbox);
			}
    		return form.getFirstByXPath(checkbox);
    	} else {
    		return form.getInputByNameOrIdOrDie(checkbox);
    	}
	}

    public void click(String element) {
        navigate(element);
    }

    public Field field(String field) {
    	HtmlInput input = form.getInputByNameOrId(field);
    	if (input != null) {
    		if (logger.isDebugEnabled()) {
				logger.debug("Field is an input: " + input);
			}
    		return new HtmlUnitField(this, input);
    	}
    	HtmlTextArea textArea = form.getTextAreaByNameOrId(field);
    	if (textArea != null) {
    		if (logger.isDebugEnabled()) {
				logger.debug("Field is a textarea: " + input);
			}
			return new HtmlUnitTextArea(this, textArea);
		}
    	
    	if (logger.isDebugEnabled()) {
			logger.debug("Trying to find a generic input");
		}
    	HtmlElement element = form.getElementByIdOrDie(field);
    	if (element instanceof HtmlTextArea) {
    		return new HtmlUnitTextArea(this, (HtmlTextArea) element);
    	}
    	return new HtmlUnitField(this, (HtmlInput) element);
    }

    public boolean isChecked(String checkbox) {
        return getCheckbox(checkbox).isChecked();
    }

    public void navigate(String element) {
    	ClickableElement button = form.getInputByName(element);
    	if (button == null) {
    		if (logger.isDebugEnabled()) {
				logger.debug("Element " + element + " was not found by name, trying id");
			}
    		button = form.getElementByIdOrDie(element);
    	}
    	try {
			parent.setPage((HtmlPage) button.click());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
    }

    public SelectField select(String selectField) {
    	return new HtmlUnitSelectField(this, form.getSelectByNameOrIdOrDie(selectField));
    }

    public void submit() {
    	try {
	    	HtmlSubmitInput submit = form.getSubmitButton();
	    	if (submit != null) {
	    		if (logger.isDebugEnabled()) {
					logger.debug("Submiting " + form + " via submit button");
				}
	        	HtmlPage page = submit.click();
				parent.setPage(page);
	    	} else {
	    		if (logger.isDebugEnabled()) {
	    			logger.debug("Submiting " + form + " via javascript");
	    		}
	    		parent.setPage(form.submitJs());
	    	}
		} catch (IOException e) {
			throw new IllegalStateException("Error while submitting form", e);
		}
    }

    public Form uncheck(String checkbox) {
    	if (logger.isDebugEnabled()) {
			logger.debug("Unchecking " + checkbox);
		}
    	HtmlCheckBoxInput check = getCheckbox(checkbox);
    	parent.setPage((HtmlPage) check.setChecked(false));
        return this;
    }
    
    HtmlUnitPage getParent() {
		return parent;
	}

}
