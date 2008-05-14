package br.com.caelum.seleniumdsl;

public interface SelectField {

	public abstract Form choose(String value);

	public abstract Form chooseByIndex(Integer index);

	public abstract String value();

}