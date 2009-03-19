package br.com.caelum.seleniumdsl;

import org.apache.commons.lang.NotImplementedException;


public class HtmlUnitSelectField implements SelectField {
    
    private final HtmlUnitForm form;
    private final String id;
    private final HtmlUnitPage page;

    public HtmlUnitSelectField(HtmlUnitPage page, HtmlUnitForm form, String id) {
        this.page = page;
        this.form = form;
        this.id = id;
    }

    public Form choose(String value) {
        form.select(value);
        return form;
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
