package ch.protonmail.vladyslavbond.techsupport.web.views;

public enum FormInputType
{
	  HIDDEN   ("hidden")
	, SUBMIT   ("submit")
	, TEXT     ("text")
	, PASSWORD ("password")
	;

	private final String stringRepresentation;

	private FormInputType (String stringRepresentation)
	{
		this.stringRepresentation = stringRepresentation;
	}

	@Override
	public String toString ( )
	{
		return this.stringRepresentation;
	}
}