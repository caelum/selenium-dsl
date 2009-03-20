package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.SelectField;

import com.gargoylesoftware.htmlunit.html.HtmlSelect;


class HtmlUnitSelectField implements SelectField {
    
    private final HtmlSelect select;
	private final HtmlUnitForm parent;

	public HtmlUnitSelectField(HtmlUnitForm parent, HtmlSelect select) {
		this.parent = parent;
		this.select = select;
	}

	public Form choose(String value) {
        select.setSelectedAttribute(value, true);
        return parent;
    }

    public Form choose(int index) {
        throw new NotImplementedException();
    }

    public String content() {
        throw new NotImplementedException();
    }

    public String value() {
        throw new NotImplementedException();
    }

    public String[] values() {
        throw new NotImplementedException();
    }

}
