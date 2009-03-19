package br.com.caelum.seleniumdsl;

import org.apache.commons.lang.NotImplementedException;

public class HtmlUnitContentTag implements ContentTag {
    
    private final String id;

    public HtmlUnitContentTag(String id) {
        this.id = id;
    }

    public boolean contains(String content) {
        throw new NotImplementedException();
    }

    public boolean exists() {
        throw new NotImplementedException();
    }

    public String innerHTML() {
        throw new NotImplementedException();
    }

}
