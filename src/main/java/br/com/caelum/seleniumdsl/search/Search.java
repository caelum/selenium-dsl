package br.com.caelum.seleniumdsl.search;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.seleniumdsl.table.Row;
import br.com.caelum.seleniumdsl.table.Table;


public class Search implements RowMatcher {

	protected List<Matcher> matchers = new ArrayList<Matcher>();
	private Table table;
	private int currentRow = 0;
	private int rowCount;
	
	protected void where(String name, Matcher matcher) {
		matcher.setColumn(name);
		matchers.add(matcher);
	}
	
	protected Matcher equals(String content) {
		return new EqualsMatcher(content);
	}
	
	protected Matcher containsAll(String...contents) {
		return new ContainsAllMatcher(contents);
	}
	
	public void setTable(Table table) {
		this.table = table;
		this.rowCount = table.getRowCount();
	}
	
	public Row next() {
		OUTTER:
		for (currentRow++; currentRow <= rowCount; currentRow++) {
			Row row = table.row(currentRow);
			for (Matcher matcher : matchers) {
				if(!matcher.matches(row))
					continue OUTTER;
			}
			return row;
		}
		return null;
	}
}
