package ch.protonmail.vladyslavbond.techsupport.web.controllers;

public final class UnknownPartyException extends Exception
{
	private final String username;

    public UnknownPartyException (String username)
    {
    	this.username = new String(username);
    }

    @Override
    public String getMessage ( )
    {
    	return "There is no party with name " + this.username + ".";
    }
}