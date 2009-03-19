package br.com.caelum.seleniumdsl;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
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
			htmlUnitPage  = new HtmlUnitPage(page);
		} catch (FailingHttpStatusCodeException e) {
			throw new IllegalArgumentException("Bad response", e);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Bad URL", e);
		} catch (IOException e) {
			throw new IllegalArgumentException("Connection Error", e);
		}
        return currentPage() ;
    }

    public Page waitForPageLoad(long timeout) {
        try {
            webClient.wait(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return currentPage();
    }

}
