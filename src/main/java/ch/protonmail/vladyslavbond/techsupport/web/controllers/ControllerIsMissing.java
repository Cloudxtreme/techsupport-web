package ch.protonmail.vladyslavbond.techsupport.web.controllers;

public final class ControllerIsMissing extends Exception
{
    private final Class<? extends Controller> typeOfControllerWhichIsMissing;

    public ControllerIsMissing (Class<? extends Controller> typeOfControllerWhichIsMissing)
    {
        this.typeOfControllerWhichIsMissing = typeOfControllerWhichIsMissing;
    }

    @Override
    public String getMessage ( )
    {
        return this.typeOfControllerWhichIsMissing.toString( ) + " controller were missing.";
    }
}