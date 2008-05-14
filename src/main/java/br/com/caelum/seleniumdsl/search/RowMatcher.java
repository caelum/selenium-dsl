package br.com.caelum.seleniumdsl.search;

import br.com.caelum.seleniumdsl.table.Row;
import br.com.caelum.seleniumdsl.table.Table;


public interface RowMatcher {

	void setTable(Table table);

	Row next();
}