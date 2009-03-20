package br.com.caelum.seleniumdsl.htmlunit;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.ContentTag;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

class HtmlUnitContentTag implements ContentTag {
    
    private final HtmlElement element;

    public HtmlUnitContentTag(HtmlElement element) {
        this.element = element;
    }

    public boolean contains(String content) {
        return innerHTML().contains(content);
    }

    public boolean exists() {
        throw new NotImplementedException();
    }

    // FIXME returns element html also
    public String innerHTML() {
        return element.asXml();
    }
    
    @Override
	public String toString() {
		return element.asXml();
	} 

}
