package br.com.caelum.seleniumdsl.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import br.com.caelum.seleniumdsl.table.Row;


public class ContainsAllMatcher implements Matcher {

	private String column;
	private Set<String> contents;

	
	public ContainsAllMatcher(String[] contents) {
		this.contents = new HashSet<String>(Arrays.asList(contents));
	}

	public boolean matches(Row row) {
		if (contents.remove(row.get(column).value()))
			return contents.isEmpty();
		return false;
	}

	public void setColumn(String column) {
		this.column = column;

	}

}
