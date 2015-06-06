package ch.protonmail.vladyslavbond.techsupport.web.servlets;

public final class InputInvalidException 
extends Exception 
{
    public InputInvalidException (String message)	
    {
    	super(message);
    }
}