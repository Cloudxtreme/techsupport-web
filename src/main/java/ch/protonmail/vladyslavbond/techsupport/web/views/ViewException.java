package ch.protonmail.vladyslavbond.techsupport.web.views;

public final class ViewException extends Exception
{
    public ViewException (String message, Throwable cause)
    {
        super(message, cause);
    }

    public ViewException (String message)
    {
        super(message);
    }

    public ViewException (Throwable cause)
    {
        super("Failed to process view.", cause);
    }
}