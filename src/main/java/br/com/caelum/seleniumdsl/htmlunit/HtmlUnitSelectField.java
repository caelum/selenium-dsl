package br.com.caelum.seleniumdsl.htmlunit;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.SelectField;

import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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
				parent.getParent().setPage((HtmlPage) select.setSelectedAttribute(option, true));
				return parent;
			}
		}
		throw new IllegalArgumentException("No option found with value: " + value);
    }

    public Form choose(int index) {
        select.getOption(index).setSelected(true);
        return parent;
    }

    public String content() {
    	return select.getSelectedOptions().get(0).getText().trim();
    }

    public String value() {
    	return select.getSelectedOptions().get(0).getValueAttribute();
    }

    public String[] values() {
    	List<String> options = new ArrayList<String>();
        for (HtmlOption option : select.getOptions()) {
        	options.add(option.getText());
        }
        String[] values = new String[select.getOptionSize()];
        return options.toArray(values);
    }

}
