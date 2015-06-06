package ch.protonmail.vladyslavbond.techsupport.web.views;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface View
{
    public abstract String getHTML ( ) throws ViewException;

    public abstract void writeResponse (HttpServletResponse response) throws ViewException, IOException;
}