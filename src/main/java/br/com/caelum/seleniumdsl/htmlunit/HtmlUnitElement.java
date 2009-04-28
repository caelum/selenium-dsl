package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.js.Element;

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
        throw new NotImplementedException();
    }

    public String value() {
        throw new NotImplementedException();
    }

}
