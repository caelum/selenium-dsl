package br.com.caelum.seleniumdsl;

import java.io.IOException;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.js.Array;
import br.com.caelum.seleniumdsl.js.HtmlUnitArray;
import br.com.caelum.seleniumdsl.table.Table;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitPage implements Page {

	private HtmlPage page;

	public HtmlUnitPage(HtmlPage page) {
		this.page = page;
	}

	public Array array(String name) {
		return new HtmlUnitArray(page.getWebClient(), name);
	}

	public Page click(String element) {
		throw new NotImplementedException();
	}

	public ContentTag div(String id) {
		HtmlElement div = page.getElementById(id);
		if (div == null) {
			throw new ElementNotFoundException("div", "id", id);
		}
		return new HtmlUnitContentTag(div);
	}

	public Form form(String id) {
		return new HtmlUnitForm(this, page.getFormByName(id));
	}
	
	void setPage(HtmlPage page) {
		this.page = page;
	}

	public boolean hasLink(String link) {
		HtmlAnchor anchorByName = page.getAnchorByName(link);
		return anchorByName == null;
	}

	public String invoke(String cmd) {
		throw new NotImplementedException();
	}

	public Page navigate(String element) {
		if (element.startsWith("link=")) {
			HtmlAnchor anchorByName = page.getFirstAnchorByText(element.replace("link=", ""));
			try {
				this.page = anchorByName.click();
				return this;
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		}
		throw new NotImplementedException(element + " not recognized");
	}

	public Page refresh() {
		throw new NotImplementedException();
	}

	public void screenshot(String filename) {
		throw new NotImplementedException();
	}

	public ContentTag span(String id) {
		return div(id);
	}

	public Table table(String id) {
		return new HtmlUnitTable(id);
	}

	public String title() {
		return page.getTitleText();
	}

	public Page waitUntil(String condition, long timeout) {
		throw new NotImplementedException();
	}

}
