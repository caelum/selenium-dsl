package br.com.caelum.seleniumdsl;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitBrowser implements Browser {

    private final WebClient webClient = new WebClient();
    private HtmlUnitPage htmlUnitPage = null;

    public Page currentPage() {
        return htmlUnitPage;
    }

    public Object getDelegate() {
       return webClient;
    }

    public Page open(String url) {
        try {
            HtmlPage page = (HtmlPage) webClient.getPage(url);
            htmlUnitPage  = new HtmlUnitPage(page, webClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentPage() ;
    }

    public Page waitForPageLoad(long timeout) {
        try {
            webClient.wait(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return currentPage();
    }

}
