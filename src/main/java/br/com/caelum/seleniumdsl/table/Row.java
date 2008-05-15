package br.com.caelum.seleniumdsl.table;

public interface Row {

	public Cell cell(int column);

	public Cell cell(String column);

	public Integer index();

}