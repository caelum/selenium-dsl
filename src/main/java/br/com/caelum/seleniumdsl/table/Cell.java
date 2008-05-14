package br.com.caelum.seleniumdsl.table;

public interface Cell {

	public abstract String value();

	public abstract String getLink();

	public abstract String headerValue();

	public abstract String headerLinkValue();

	public abstract Cell check();

	public abstract Cell uncheck();

	public abstract boolean checked();

	public abstract boolean contains(String content);

}