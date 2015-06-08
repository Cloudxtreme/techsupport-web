package ch.protonmail.vladyslavbond.techsupport.web.servlets;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;
import ch.protonmail.vladyslavbond.techsupport.domain.Issue;

import ch.protonmail.vladyslavbond.techsupport.web.controllers.*;
import ch.protonmail.vladyslavbond.techsupport.web.views.*;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class EntryPoint extends HttpServlet
{
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        HttpSession session = request.getSession( );
        Party partyLoggedIn = (Party)session.getAttribute("party");
        if (partyLoggedIn != null)
        {
            IssueFilteringMode modeSelected = null;
            try
            {
                modeSelected = IssueFilteringMode.valueOf((String)request.getParameter("mode"));
            } catch (Exception e) {
            }
            if (modeSelected == null)
            {
                modeSelected = IssueFilteringMode.ALL;
            }

            try
            {
                IssueFilteringController issueFilteringController = Controllers.getIssueFilteringController(partyLoggedIn, modeSelected);
                List<Issue> listOfIssues = issueFilteringController
                    .filter( );
                HomePageView pageView = new HomePageView(partyLoggedIn, listOfIssues, modeSelected);
                pageView.writeResponse(response);
            //} catch (ViewException e) {
                //request.setAttribute("errorMessage", e.getMessage( ));
                //Page.ERROR.forward(request, response);
            } catch (Exception e) {
                throw new ServletException ("Unexpected exception occured.", e);
            }
        } else {
            Page.LOG_IN.redirect(request, response);
        }
    }
}
