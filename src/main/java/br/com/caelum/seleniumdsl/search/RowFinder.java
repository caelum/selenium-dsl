package br.com.caelum.seleniumdsl.search;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.seleniumdsl.table.Row;


public class RowFinder implements RowVisitor {
	private Row found = null;
	private List<Matcher> matchers;

	public RowFinder() {
		this.matchers = new ArrayList<Matcher>();
	}

	public RowFinder limitEquals(int row, String content) {
		matchers.add(new EqualsMatcher(row, content));
		return this;
	}

	public void visit(Row row) {
		for (Matcher m : matchers) {
			if (!m.matches(row)) {
				return;
			}
		}
		found = row;
	}

	public boolean found() {
		return found != null;
	}

	public Row getFound() {
		return found;
	}
}
