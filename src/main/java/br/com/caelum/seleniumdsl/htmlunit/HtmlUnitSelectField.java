package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.SelectField;

import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;


class HtmlUnitSelectField implements SelectField {
    
    private final HtmlSelect select;
	private final HtmlUnitForm parent;

	public HtmlUnitSelectField(HtmlUnitForm parent, HtmlSelect select) {
		this.parent = parent;
		this.select = select;
	}

	public Form choose(String value) {
		for (HtmlOption option : select.getOptions()) {
			if (option.getText().trim().equals(value)) {
				select.setSelectedAttribute(option, true);
				return parent;
			}
		}
		throw new IllegalArgumentException("No option found with value: " + value);
    }

    public Form choose(int index) {
        throw new NotImplementedException();
    }

    public String content() {
    	return value();
    }

    public String value() {
        return select.getSelectedOptions().get(0).getText().trim();
    }

    public String[] values() {
        throw new NotImplementedException();
    }

}
