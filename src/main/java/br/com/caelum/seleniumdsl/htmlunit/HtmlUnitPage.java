package br.com.caelum.seleniumdsl.htmlunit;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.NotImplementedException;
import org.mozilla.javascript.NativeArray;

import br.com.caelum.seleniumdsl.ContentTag;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.js.Array;
import br.com.caelum.seleniumdsl.table.Table;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
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
		ClickableElement clickable;
		if (element.startsWith("link=")) {
			return clickLink(element.replace("link=", ""));
		} else if (element.startsWith("//")){
			clickable = page.getFirstByXPath(element);
		} else {
			List<HtmlElement> elements = page.getElementsByIdAndOrName(element);
			if (elements.isEmpty()) {
				throw new ElementNotFoundException("*", "id|name", element);
			}
			clickable = (ClickableElement) elements.get(0);
		}
		try {
			this.page = clickable.click();
		} catch (IOException e) {
			throw new IllegalStateException(e);
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
			com.gargoylesoftware.htmlunit.Page newPage = page.getWebClient().getCurrentWindow().getEnclosedPage();
			if (newPage instanceof UnexpectedPage) {
				UnexpectedPage unexpected = (UnexpectedPage) newPage;
				try {
					String error = new Scanner(unexpected.getInputStream()).useDelimiter("$$").next();
					throw new IllegalStateException("Unexpected page: \n" + error);
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
			page = (HtmlPage) newPage;
			sleep();
			tries--;
			if (tries == 0) {
				throw new IllegalStateException("Page was not loaded");
			}
		}
		waitForLoad();
	}
	
	public HtmlUnitPage mouseDown(String element) {
		ClickableElement div = (ClickableElement) page.getElementsByIdAndOrName(element).get(0);
		setPage((HtmlPage) div.mouseDown());
		return this;
	}
	
	public HtmlUnitPage mouseUp(String element) {
		ClickableElement div = (ClickableElement) page.getElementsByIdAndOrName(element).get(0);
		setPage((HtmlPage) div.mouseUp());
		return this;
	}
	
	public HtmlUnitPage dragAndDrop(String fromElement, String toElement) {
		mouseDown(fromElement);
		setPage((HtmlPage) page.getElementsByIdAndOrName(toElement).get(0).mouseMove());
		mouseUp(toElement);
		return this;
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
		if (result.getJavaScriptResult() instanceof NativeArray) {
			NativeArray array = (NativeArray) result.getJavaScriptResult();
			return array.get(0, null).toString();
		}
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
