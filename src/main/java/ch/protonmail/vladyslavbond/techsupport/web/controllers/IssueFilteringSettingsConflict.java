package ch.protonmail.vladyslavbond.techsupport.web.controllers;

public final class IssueFilteringSettingsConflict extends Exception
{
	private final String message;

	public IssueFilteringSettingsConflict (String message)
	{
	    this.message = message;
	}

	@Override
	public String getMessage ( )
	{
	    return new String(this.message);
	}
}