package br.com.caelum.seleniumdsl;

public interface Browser {

	public abstract Page open(String url);

	public abstract Page currentPage();

}