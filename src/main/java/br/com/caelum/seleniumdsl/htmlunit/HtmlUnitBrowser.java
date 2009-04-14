package br.com.caelum.seleniumdsl.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;

import br.com.caelum.seleniumdsl.Browser;
import br.com.caelum.seleniumdsl.Page;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitBrowser implements Browser {

    private final WebClient webClient = new WebClient();
    private HtmlUnitPage htmlUnitPage = null;
	private final String baseURL;

    public HtmlUnitBrowser(String baseURL) {
		this.baseURL = baseURL;
	}

	public HtmlUnitPage currentPage() {
        return htmlUnitPage;
    }

    public Object getDelegate() {
       return webClient;
    }

    public Page open(String url) {
    	String target = baseURL + url;
		try {
			HtmlPage page = (HtmlPage) webClient.getPage(target);
			htmlUnitPage  = new HtmlUnitPage(page);
		} catch (FailingHttpStatusCodeException e) {
			throw new IllegalArgumentException("Bad response for " + target, e);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Bad URL: " + target, e);
		} catch (IOException e) {
			throw new IllegalArgumentException("Connection Error to " + target, e);
		}
        return currentPage() ;
    }

    public Page waitForPageLoad(long timeout) {
    	return currentPage();
    }

}
