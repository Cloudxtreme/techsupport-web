package ch.protonmail.vladyslavbond.techsupport.web.servlets;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;

import ch.protonmail.vladyslavbond.techsupport.web.controllers.*;
import ch.protonmail.vladyslavbond.techsupport.web.views.*;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInRequestHandler
extends HttpServlet
{
    @Override
    public void doPost 
    (
          HttpServletRequest  request
        , HttpServletResponse response
    )
    throws ServletException, IOException
    {
        try
        {
            HttpSession session = request.getSession( );

            String username = (String)request.getParameter("username");
            if (username == null || username.isEmpty( ))
            {
                throw new InputInvalidException ("Username is missing.");
            }
            String password = (String)request.getParameter("password");
            if (password == null || password.isEmpty( ))
            {
                throw new InputInvalidException ("Password is missing.");
            }

            Party partyLoggedIn = LogInController.logIn(username, password);
            if (partyLoggedIn != null)
            {
                session.setAttribute("party", partyLoggedIn);
                Page.HOME.redirect(request, response);
            } else {
                throw new InputInvalidException ("Failed to log in for unknown reason.");
            }
        } catch (UnknownPartyException | WrongPasswordException | InputInvalidException e) {
            //throw new ServletException ("Failure.", e);
            request.setAttribute("errorMessage", e.getMessage( ));
            Page.ERROR.forward(request, response);
        }
    }

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        Party partyLoggedIn = (Party)request.getSession( ).getAttribute("party");
        if (partyLoggedIn == null)
        {
            try
            {
                LogInPageView logInPageView = new LogInPageView ( );
                logInPageView.writeResponse(response);
            } catch (Exception e) {
                request.setAttribute("errorMessage", e.getMessage( ));
                Page.ERROR.forward(request, response);
            }
        } else {
            Page.HOME.redirect(request, response);
        }
    }
}