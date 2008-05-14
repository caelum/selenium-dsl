package br.com.caelum.seleniumdsl.search;

import br.com.caelum.seleniumdsl.table.Row;


public interface RowVisitor {
	void visit(Row row);
}
