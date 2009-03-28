package br.com.caelum.seleniumdsl.htmlunit;

import br.com.caelum.seleniumdsl.ContentTag;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

class HtmlUnitContentTag implements ContentTag {
    
    private final HtmlPage page;
	private final String id;

	public HtmlUnitContentTag(HtmlPage page, String id) {
		this.page = page;
		this.id = id;
	}

	public boolean contains(String content) {
        return innerHTML().contains(content);
    }

    public boolean exists() {
    	return div() != null;
    }

	private HtmlElement div() {
		return page.getElementById(id);
	}

    public String innerHTML() {
    	StringBuilder html = new StringBuilder();
        for(HtmlElement child : div().getAllHtmlChildElements()) {
			html.append(child.asXml());
		}
		return html.toString();
    }
    
    @Override
	public String toString() {
		return div().asXml();
	} 

}
