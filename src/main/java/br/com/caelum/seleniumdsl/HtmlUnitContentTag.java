package br.com.caelum.seleniumdsl;

public class HtmlUnitContentTag implements ContentTag {
    
    private final String id;

    public HtmlUnitContentTag(String id) {
        this.id = id;
    }

    public boolean contains(String content) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean exists() {
        // TODO Auto-generated method stub
        return false;
    }

    public String innerHTML() {
        // TODO Auto-generated method stub
        return null;
    }

}
