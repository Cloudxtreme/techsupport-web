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
}