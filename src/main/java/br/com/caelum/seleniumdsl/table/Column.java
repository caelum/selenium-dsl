package br.com.caelum.seleniumdsl.table;

public interface Column {

	public abstract boolean contains(String value);

	public abstract int find(String value);

	public abstract boolean containsPartial(String value);

}