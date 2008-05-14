package br.com.caelum.seleniumdsl;

public interface Form {

	public abstract Field field(String fieldName);

	public abstract void click(String buttonLabel);

	public abstract SelectField selectField(String fieldName);

	public abstract Form check(String checkbox);

	public abstract Form uncheck(String checkbox);

	public abstract boolean isChecked(String checkbox);

	public abstract void submit();

}