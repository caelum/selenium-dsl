package br.com.caelum.seleniumdsl;

public interface Field {

	public abstract Form type(String content);

	public abstract boolean contains(String content);

	public abstract String content();

}