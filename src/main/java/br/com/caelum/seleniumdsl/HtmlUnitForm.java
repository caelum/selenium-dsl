package br.com.caelum.seleniumdsl;

import org.apache.commons.lang.NotImplementedException;

public class HtmlUnitForm implements Form {

    private final String id;

    public HtmlUnitForm(String id) {
        this.id = id;
    }

    public Form check(String checkbox) {
        throw new NotImplementedException();
    }

    public void click(String element) {
        throw new NotImplementedException();
    }

    public Field field(String field) {
        throw new NotImplementedException();
    }

    public boolean isChecked(String checkbox) {
        throw new NotImplementedException();
    }

    public void navigate(String element) {
        throw new NotImplementedException();
    }

    public SelectField select(String selectField) {
        throw new NotImplementedException();
    }

    public void submit() {
        throw new NotImplementedException();
    }

    public Form uncheck(String checkbox) {
        throw new NotImplementedException();
    }

}
