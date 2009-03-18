package br.com.caelum.seleniumdsl;


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
        // TODO Auto-generated method stub
        return null;
    }

    public String content() {
        // TODO Auto-generated method stub
        return null;
    }

    public String value() {
        // TODO Auto-generated method stub
        return null;
    }

    public String[] values() {
        // TODO Auto-generated method stub
        return null;
    }

}
