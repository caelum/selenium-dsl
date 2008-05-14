package br.com.caelum.seleniumdsl.table;

public interface Row {

	public abstract Cell get(int column);

	public abstract Cell get(String column);

	public abstract Integer getIndex();

}