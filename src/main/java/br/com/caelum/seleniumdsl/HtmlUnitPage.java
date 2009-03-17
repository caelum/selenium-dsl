package br.com.caelum.seleniumdsl;

import java.io.IOException;

import br.com.caelum.seleniumdsl.js.Array;
import br.com.caelum.seleniumdsl.js.HtmlUnitArray;
import br.com.caelum.seleniumdsl.table.Table;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/* is it necessary to wait?? */

public class HtmlUnitPage implements Page {

    private final HtmlPage page;
    private final WebClient webClient;
    private final long timeout;

    public HtmlUnitPage(HtmlPage page, WebClient webClient) {
        this(page, webClient, 10000);
    }
    
    public HtmlUnitPage(HtmlPage page, WebClient webClient, long timeout) {
        this.page = page;
        this.webClient = webClient;
        this.timeout = timeout;
    }

    public Array array(String name) {
        return new HtmlUnitArray(name);
    }

    public Page click(String element) {
        HtmlAnchor anchorByName = page.getAnchorByName(element);
        try {
            anchorByName.click();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ContentTag div(String id) {
        return new HtmlUnitContentTag(id);
    }

    public Form form(String id) {
        return new HtmlUnitForm(id.equals("") ? "" : id + ".");
    }

    public boolean hasLink(String link) {
        HtmlAnchor anchorByName = page.getAnchorByName(link);
        return anchorByName == null;
    }

    public String invoke(String cmd) {
        // TODO Auto-generated method stub
        return null;
    }

    public Page navigate(String element) {
        click(element);
        return this;
    }

    public Page refresh() {
        // TODO Auto-generated method stub
        return this;
    }

    public void screenshot(String filename) {
        throw new IllegalStateException();
    }

    public ContentTag span(String id) {
        return new HtmlUnitContentTag(id);
    }

    public Table table(String id) {
        return new HtmlUnitTable(id);
    }

    public String title() {
        return page.getTitleText();
    }

    public Page waitUntil(String condition, long timeout) {
        try {
            webClient.wait(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

}
