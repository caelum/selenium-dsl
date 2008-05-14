package br.com.caelum.seleniumdsl.search;

import br.com.caelum.seleniumdsl.table.Row;


interface Matcher {
	boolean matches(Row row);

	void setColumn(String column);
	
}
