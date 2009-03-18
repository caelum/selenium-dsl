package br.com.caelum.seleniumdsl.js;

import com.gargoylesoftware.htmlunit.WebClient;

public class HtmlUnitArray implements Array {

    private final String name;
    private final WebClient webClient;

    public HtmlUnitArray(WebClient webClient,String name) {
        this.webClient = webClient;
        this.name = name;
    }

    public Array array(int k) {
        return new HtmlUnitArray(webClient, name + "[" + k + "]");
    }

    public Element get(int k) {
        return new HtmlUnitElement(webClient, name + "[" + k + "]");
    }

    public int size() {
        return 0;
    }

}
