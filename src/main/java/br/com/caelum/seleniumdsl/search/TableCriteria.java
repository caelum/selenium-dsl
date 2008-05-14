package br.com.caelum.seleniumdsl.search;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.seleniumdsl.table.Row;
import br.com.caelum.seleniumdsl.table.Table;

public class TableCriteria {

	private final Table table;
	private List<TableCriterion> criteria;

	public TableCriteria(Table table) {
		this.table = table;
		this.criteria = new ArrayList<TableCriterion>();
	}

	public TableCriteria eq(String columnName, String value) {
		this.add(new TableCriterion(columnName, value));
		return this;
	}

	public TableCriteria add(TableCriterion criterion) {
		this.criteria.add(criterion.prepare(table));
		return this;
	}

	public Row getFirstResult() {
		int rowCount = table.getRowCount();
		OUTTER: for (int i = 0; i < rowCount; i++) {
			Row row = table.row(i + 1);
			for (TableCriterion criterion : criteria) {
				if (!criterion.match(row))
					continue OUTTER;
			}
			return row;
		}

		return null;
	}

	public boolean matches() {
		return getFirstResult() != null;
	}
}
