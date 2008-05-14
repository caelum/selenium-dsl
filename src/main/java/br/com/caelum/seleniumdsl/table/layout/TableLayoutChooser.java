package br.com.caelum.seleniumdsl.table.layout;

import com.thoughtworks.selenium.Selenium;

public class TableLayoutChooser {

	private Selenium selenium;
	private String id;
	private String type;

	private static final String testThead = "id('%s')/thead";
	private static final String testTh = "id('%s')//th";

	public TableLayoutChooser(Selenium selenium, String id, String type) {
		super();
		this.selenium = selenium;
		this.id = id;
		this.type = type;
	}

	public TableLayout choose() {
		int theadCount = selenium.getXpathCount(String.format(testThead, id)).intValue();
		int thCount = selenium.getXpathCount(String.format(testTh, id)).intValue();

		TableLayout layout = null;
		if (theadCount <= 0 && thCount <= 0)
			layout = new PlainTableLayout(selenium, id, type);
		else if (theadCount <= 0 && thCount > 0)
			layout = new PlainPlusThTableLayout(selenium, id, type);
		else
			layout = new FullTableLayout(selenium, id, type);

		return layout;
	}
}
