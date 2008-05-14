package br.com.caelum.seleniumdsl.search;

import br.com.caelum.seleniumdsl.table.Row;
import br.com.caelum.seleniumdsl.table.Table;


public class TableCriterion {
	private String columnName;
	private String content;
	private Integer column;

	public TableCriterion(String columnName, String content) {
		this.columnName = columnName;
		this.content = content;
	}

	public TableCriterion(Integer column, String content) {
		this.column = column;
		this.content = content;
	}
	public TableCriterion prepare(Table table) {
		if(columnName != null && columnName.length() > 0)
			this.column = table.findColumn(columnName);
		return this;
	}
	
	public boolean match(Row row) {
		return content.equals(row.get(column).value());
	}
}
