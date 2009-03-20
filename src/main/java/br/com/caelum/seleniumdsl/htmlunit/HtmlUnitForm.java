package br.com.caelum.seleniumdsl.htmlunit;

import java.io.IOException;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.Field;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.SelectField;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

class HtmlUnitForm implements Form {

    private final HtmlForm form;
	private final HtmlUnitPage parent;

    public HtmlUnitForm(HtmlUnitPage page, HtmlForm htmlForm) {
        this.parent = page;
		this.form = htmlForm;
    }

    public Form check(String checkbox) {
        throw new NotImplementedException();
    }

    public void click(String element) {
        throw new NotImplementedException();
    }

    public Field field(String field) {
        return new HtmlUnitField(this, form.getInputByName(field));
    }

    public boolean isChecked(String checkbox) {
        throw new NotImplementedException();
    }

    public void navigate(String element) {
        throw new NotImplementedException();
    }

    public SelectField select(String selectField) {
    	return new HtmlUnitSelectField(this, form.getSelectByName(selectField));
    }

    public void submit() {
        try {
        	HtmlSubmitInput submit = form.getFirstByXPath("//input[@type='submit']");
        	parent.setPage((HtmlPage) submit.click());
		} catch (IOException e) {
			new IllegalStateException("Error while clicking", e);
		}
    }

    public Form uncheck(String checkbox) {
        throw new NotImplementedException();
    }

}
