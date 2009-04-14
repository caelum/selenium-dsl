package br.com.caelum.seleniumdsl.htmlunit;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;

import br.com.caelum.seleniumdsl.ContentTag;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.js.Array;
import br.com.caelum.seleniumdsl.js.HtmlUnitArray;
import br.com.caelum.seleniumdsl.table.Table;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.ClickableElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

class HtmlUnitPage implements Page {

	private HtmlPage page;

	public HtmlUnitPage(HtmlPage page) {
		this.page = page;
	}

	public Array array(String name) {
		return new HtmlUnitArray(page.getWebClient(), name);
	}

	public Page click(String element) {
		return navigate(element);
	}
	
	public Page doubleClick(String element) {
		ClickableElement link =  page.getHtmlElementById(element);
		try {
			this.page = link.dblClick();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return this;
	}

	public ContentTag div(String id) {
		return new HtmlUnitContentTag(page, id);
	}

	public Form form(String id) {
		for (HtmlForm form : page.getForms()) {
			if (Arrays.asList("", form.getNameAttribute(), form.getIdAttribute()).contains(id)) {
				return new HtmlUnitForm(this, new HtmlFormWrapper(form));
			}
		}
		throw new ElementNotFoundException("form", "id|nome", id);
	}
	
	void setPage(HtmlPage page) {
		this.page = page;
	}

	public boolean hasLink(String link) {
		try {
			getFirstAnchorByText(link);
			return true;
		} catch (ElementNotFoundException e) {
			return false;
		}
	}

	public String invoke(String cmd) {
		ScriptResult result = page.executeJavaScript(cmd);
		return result.getJavaScriptResult().toString();
	}

	private HtmlAnchor getFirstAnchorByText(String text) {
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) page.getByXPath("//a");
		for (HtmlAnchor anchor : anchors) {
			if (text.trim().equals(anchor.asXml().replaceAll("<.*?>", "").trim())) {
				return anchor;
			}
		}
		throw new ElementNotFoundException("a", "text", text);
	}
	public Page navigate(String element) {
		if (element.startsWith("link=")) {
			String text = element.replace("link=", "");
			try {
				this.page = getFirstAnchorByText(text).click();
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		} else {
			List<HtmlElement> elements = page.getElementsByIdAndOrName(element);
			if (elements.isEmpty()) {
				throw new NotImplementedException(element + " not recognized");
			}
			ClickableElement clickable = (ClickableElement) elements.get(0);
			try {
				this.page = clickable.click();
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
		
		return this;
		
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
		List<HtmlElement> elements = page.getElementsByIdAndOrName(id);
		if (elements.isEmpty()) {
			return new InexistantTable(id);
		}
		return new HtmlUnitTable((HtmlTable)elements.get(0));
	}

	public String title() {
		return page.getTitleText();
	}

	public Page waitUntil(String condition, long timeout) {
		for (int i = 0; i < 10; i++) {
			ScriptResult result = page.executeJavaScript(condition);
			if (!ScriptResult.isFalse(result)) {
				this.page = (HtmlPage) result.getNewPage();
				return this;
			}
			try {
				Thread.sleep(timeout/10);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		throw new IllegalStateException("Condition " + condition + " doesn't hold");
	}

}
