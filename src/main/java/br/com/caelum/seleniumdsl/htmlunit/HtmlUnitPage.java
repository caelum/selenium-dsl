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
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.ClickableElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

class HtmlUnitPage implements Page {

	private static final int SLEEP_TIME = 10;

	private HtmlPage page;
	
	private HtmlPage lastPage;

	public HtmlUnitPage(HtmlPage page) {
		setPage(page);
		lastPage = page;
	}

	public Array array(String name) {
		return new HtmlUnitArray(page.getWebClient(), name);
	}

	public Page click(String element) {
		if (element.startsWith("link=")) {
			return clickLink(element.replace("link=", ""));
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

	public Page clickLink(String text) {
		try {
			this.page = getFirstAnchorByText(text).click();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return this;
	}
	
	public Page navigate(String element) {
		lastPage = page;
		
		click(element);
		waitForPageToChange(5000);
		
		return this;
	}

	void waitForPageToChange(long timeout) {
		long tries = timeout / SLEEP_TIME;
		while (lastPage.equals(page)) {
			page = (HtmlPage) page.getWebClient().getCurrentWindow().getEnclosedPage();
			sleep();
			tries--;
			if (tries == 0) {
				throw new IllegalStateException("Page was not loaded");
			}
		}
		waitForLoad();
	}
	
	public Page navigateLink(String text) {
		return navigate("link=" + text);
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
		this.lastPage = this.page;
		this.page = page;
		waitForLoad();
	}

	HtmlPage getPage() {
		return page;
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

	private void waitForLoad() {
		while (page.isBeingParsed()) {
			sleep();
		}
	}

	private void sleep() {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public Page refresh() {
		try {
			setPage((HtmlPage) page.refresh());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return this;
	}

	public void screenshot(String filename) {
		throw new NotImplementedException();
	}

	public ContentTag span(String id) {
		return div(id);
	}

	public Table table(String id) {
		List<HtmlElement> elements = page.getElementsByIdAndOrName(id);
		for (HtmlElement htmlElement : elements) {
			if (htmlElement instanceof HtmlTable) {
				return new HtmlUnitTable((HtmlTable) htmlElement);
			}
		}
		return new InexistantTable(id);
	}

	public String title() {
		return page.getTitleText();
	}

	public Page waitUntil(String condition, long timeout) {
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < timeout) {
			ScriptResult result;
			try {
				result = page.executeJavaScript(condition);
			} catch (ScriptException e) {
				throw new IllegalStateException(page.toString(), e);
			}
			if (!ScriptResult.isFalse(result)) {
				this.page = (HtmlPage) result.getNewPage();
				return this;
			}
			sleep();
		}
		throw new IllegalStateException("Condition " + condition + " doesn't hold");
	}

}
