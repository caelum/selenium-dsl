package br.com.caelum.seleniumdsl.js;

import com.gargoylesoftware.htmlunit.WebClient;

public class HtmlUnitElement implements Element {

    private final WebClient webClient;
    private final String name;

    public HtmlUnitElement(WebClient webClient, String name) {
        this.webClient = webClient;
        this.name = name;
    }

    public Element element(String name) {
        return new HtmlUnitElement(webClient, this.name + "." + name);
    }

    public String value(String property) {
        // TODO Auto-generated method stub
        return null;
    }

    public String value() {
        // TODO Auto-generated method stub
        return null;
    }

}
