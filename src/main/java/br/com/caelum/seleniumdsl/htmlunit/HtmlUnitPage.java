package br.com.caelum.seleniumdsl.htmlunit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import net.sourceforge.htmlunit.corejs.javascript.NativeArray;

import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;

import br.com.caelum.seleniumdsl.ContentTag;
import br.com.caelum.seleniumdsl.Form;
import br.com.caelum.seleniumdsl.Page;
import br.com.caelum.seleniumdsl.js.Array;
import br.com.caelum.seleniumdsl.table.Table;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

class HtmlUnitPage implements Page {

	private static final Logger logger = Logger.getLogger(HtmlUnitPage.class);

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
		HtmlElement clickable;
		if (element.startsWith("link=")) {
			if (logger.isDebugEnabled()) {
				logger.debug("Element " + element + " is a link");
			}
			return clickLink(element.replace("link=", ""));
		} else if (element.startsWith("//")){
			if (logger.isDebugEnabled()) {
				logger.debug("Element " + element + " is a xpath");
			}
			clickable = page.getFirstByXPath(element);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Finding element " + element + " by id or name.");
			}
			final List<HtmlElement> elements = page.getElementsByIdAndOrName(element);
			if (elements.isEmpty()) {
				throw new ElementNotFoundException("*", "id|name", element);
			}
			clickable = (HtmlElement) elements.get(0);
		}
		try {
			this.page = clickable.click();
		} catch (final IOException e) {
			throw new IllegalStateException(e);
		}

		return this;
	}

	public Page clickLink(String text) {
		try {
			this.page = getFirstAnchorByText(text).click();
		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
		return this;
	}

	public Page navigate(String element) {
		if (logger.isDebugEnabled()) {
			logger.debug("Navigating element " + element);
		}
		lastPage = page;

		click(element);
		waitForPageToChange(5000);

		return this;
	}

	void waitForPageToChange(long timeout) {
		if (logger.isDebugEnabled()) {
			logger.debug("Waiting page to change for: " + timeout + "ms");
		}
		long tries = timeout / SLEEP_TIME;
		while (lastPage.equals(page)) {
			final com.gargoylesoftware.htmlunit.Page newPage = page.getWebClient().getCurrentWindow().getEnclosedPage();
			if (newPage instanceof UnexpectedPage) {
				final UnexpectedPage unexpected = (UnexpectedPage) newPage;
				try {
					final String error = new Scanner(unexpected.getInputStream()).useDelimiter("$$").next();
					throw new IllegalStateException("Unexpected page: \n" + error);
				} catch (final IOException e) {
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
		if (logger.isDebugEnabled()) {
			logger.debug("Mouse down on " + element);
		}
		final HtmlElement div = (HtmlElement) page.getElementsByIdAndOrName(element).get(0);
		setPage((HtmlPage) div.mouseDown());
		return this;
	}

	public HtmlUnitPage mouseUp(String element) {
		if (logger.isDebugEnabled()) {
			logger.debug("Mouse up on " + element);
		}
		final HtmlElement div = (HtmlElement) page.getElementsByIdAndOrName(element).get(0);
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
		if (logger.isDebugEnabled()) {
			logger.debug("Double clicking " + element);
		}
		final HtmlElement link =  page.getHtmlElementById(element);
		try {
			this.page = link.dblClick();
		} catch (final IOException e) {
			throw new IllegalStateException(e);
		}
		return this;
	}

	public ContentTag div(String id) {
		return new HtmlUnitContentTag(page, id);
	}

	public Form form(String id) {
		for (final HtmlForm form : page.getForms()) {
			if (Arrays.asList("", form.getNameAttribute(), form.getAttribute("id")).contains(id)) {
				return new HtmlUnitForm(this, new HtmlFormWrapper(form));
			}
		}
		if (logger.isDebugEnabled()) {
			final List<String> forms = new ArrayList<String>();
			for (final HtmlForm form : page.getForms()) {
				forms.add(form.toString());
			}
			logger.debug("No forms found with id: " + id + ". Forms found: \n" + forms);
		}
		throw new ElementNotFoundException("form", "id|nome", id);
	}

	void setPage(HtmlPage page) {
		if (logger.isDebugEnabled()) {
			logger.debug("Change page " + this.page + " to " + page);
		}
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
		} catch (final ElementNotFoundException e) {
			return false;
		}
	}

	public String invoke(String cmd) {
		if (logger.isDebugEnabled()) {
			logger.debug("Invoking javascript: " + cmd);
		}
		final ScriptResult result = page.executeJavaScript(cmd);
		if (result.getJavaScriptResult() instanceof NativeArray) {
			if (logger.isDebugEnabled()) {
				logger.debug("Result is a native array");
			}
			final NativeArray array = (NativeArray) result.getJavaScriptResult();
			return array.get(0, null).toString();
		}
		return result.getJavaScriptResult().toString();
	}

	private HtmlAnchor getFirstAnchorByText(String text) {
		final List<HtmlAnchor> anchors = (List<HtmlAnchor>) page.getByXPath("//a");
		for (final HtmlAnchor anchor : anchors) {
			if (text.trim().equals(anchor.asXml().replaceAll("<.*?>", "").trim())) {
				return anchor;
			}
		}
		if (logger.isDebugEnabled()) {
			final List<String> list = new ArrayList<String>();
			for (final HtmlAnchor anchor : anchors) {
				list.add(anchor.asXml().replaceAll("<.*?>", "").trim());
			}
			logger.debug("No link found with text: " + text + ". Links found: \n" + list);
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
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public Page refresh() {
		try {
			setPage((HtmlPage) page.refresh());
		} catch (final IOException e) {
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
		final List<HtmlElement> elements = page.getElementsByIdAndOrName(id);
		for (final HtmlElement htmlElement : elements) {
			if (htmlElement instanceof HtmlTable) {
				return new HtmlUnitTable((HtmlTable) htmlElement);
			}
		}
		if (logger.isDebugEnabled()) {
			final List<String> list = new ArrayList<String>();
			for (final HtmlElement element : elements) {
				if (element instanceof HtmlTable) {
					list.add(element.toString());
				}
			}
			logger.debug("Table with name or id " + id + " not found. Found tables: \n" + list);
		}
		return new InexistantTable(id);
	}

	public String title() {
		return page.getTitleText();
	}

	public Page waitUntil(String condition, long timeout) {
		if (logger.isDebugEnabled()) {
			logger.debug("Waiting for condition " + condition);
		}
		final long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < timeout) {
			ScriptResult result;
			try {
				result = page.executeJavaScript(condition);
			} catch (final ScriptException e) {
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

	public String htmlSource() {
		throw new NotImplementedException();
	}

}
