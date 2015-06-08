package ch.protonmail.vladyslavbond.techsupport.web.controllers;

public final class IssueControllerException
extends Exception
{
	public IssueControllerException (String message, Throwable cause)
	{
		super(message, cause);
	}

	public IssueControllerException (String message)
	{
		super(message);
	}

	public IssueControllerException (Throwable cause)
	{
		this("Issue controller failed to process a request.", cause);
	}
}