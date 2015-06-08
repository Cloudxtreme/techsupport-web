package ch.protonmail.vladyslavbond.techsupport.web.views;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.*;

public enum Page
{
      COMMENT_NEW ("discuss/post")
    , DISCUSS ("discuss")
    , LOG_IN ("login")
    , ERROR ("error")
    , HOME ("")
    , ISSUE_ASSIGN ("issue/assign")
    , ISSUE_CLOSE ("issue/close")
    , ISSUE_OPEN ("issue/open")
    ;

    private final String path;
    private final String contextPath;

    private Page (String path)
    {
        try
        {
            Context initCtx = new InitialContext( );
            Context envCtx = (Context)initCtx.lookup("java:comp/env");

            String contextPath = (String)envCtx.lookup("contextPath");

            assert contextPath != null && !contextPath.isEmpty( ) : "Web application context path is missing.";
            this.contextPath = contextPath;
            this.path = path;
        } catch (NamingException e) {
            throw new AssertionError ("Failed to lookup for web application context path.", e);
        }
    }

    public String getPath ( )
    {
        return this.contextPath + this.path;
    }

    public void redirect (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.sendRedirect(this.path);
    }

    public void forward (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.getRequestDispatcher("/" + this.path).forward(request, response);
    }
}