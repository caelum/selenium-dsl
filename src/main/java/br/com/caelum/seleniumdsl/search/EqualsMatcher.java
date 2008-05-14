package br.com.caelum.seleniumdsl.search;

import br.com.caelum.seleniumdsl.table.Row;


class EqualsMatcher implements Matcher {
	private Integer column;
	private String content;
	private String columnName;

	public EqualsMatcher(int column, String content) {
		this.column = column;
		this.content = content;
	}

	public EqualsMatcher(String content) {
		this.content = content;
	}

	public boolean matches(Row row) {
		//FIXME
		if (column != null)
			return row.get(column).value().equals(content);
		else
			return row.get(columnName).value().equals(content);
	}

	public void setColumn(String column) {
		this.columnName = column;
	}
}
