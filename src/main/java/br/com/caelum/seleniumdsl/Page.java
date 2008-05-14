package br.com.caelum.seleniumdsl;

import br.com.caelum.seleniumdsl.table.Table;

public interface Page {

	public String getText(String xpath);

	public Form form(String id);

	public ContentTag div(String id);

	public ContentTag span(String id);

	public Table table(String id);

	public Page click(String link);

	public Page clickDontWait(String link);

	public boolean hasLink(String link);

	public boolean isFilled(String textBoxId, String value);

	public Page check(String checkbox);

	public String title();

}