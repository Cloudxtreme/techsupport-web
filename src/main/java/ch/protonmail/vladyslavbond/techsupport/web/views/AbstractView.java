package ch.protonmail.vladyslavbond.techsupport.web.views;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

abstract class AbstractView
extends Object
implements View 
{
    @Override
    public void writeResponse (HttpServletResponse response)
    throws IOException, ViewException
    {
        response.setContentType("text/html");
        response.getWriter( ).write(this.getHTML( ));
    }
}