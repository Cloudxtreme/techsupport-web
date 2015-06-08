package ch.protonmail.vladyslavbond.techsupport.web.servlets;

import ch.protonmail.vladyslavbond.techsupport.domain.Identificator;
import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.Party;

import ch.protonmail.vladyslavbond.techsupport.web.controllers.*;
import ch.protonmail.vladyslavbond.techsupport.web.views.*;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class IssueCloseRequestHandler
extends HttpServlet 
{
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        try
        {
            Party readingParty = (Party)request.getSession( ).getAttribute("party");
            if (readingParty == null)
            {
                Page.LOG_IN.redirect(request, response);
            }
            String idOfIssueToBeClosed = (String)request.getParameter("issue_id");
            if (idOfIssueToBeClosed == null || idOfIssueToBeClosed.isEmpty( ))
            {
                throw new InputInvalidException ("Id of issue to be closed is missing.");
            }
            IssueController issueController = Controllers.getIssueController( );
            Issue issue = issueController.close(readingParty, new Identificator<Issue> (idOfIssueToBeClosed));
            if (issue == null)
            {
                throw new ServletException ("Failed to close an issue for unknown reason.");
            }
            PageView pageView = new PageView ("Success.", "<p>You have successfully closed an issue.</p>");
            pageView.writeResponse(response);
        } catch (InputInvalidException e) {
            request.setAttribute("errorMessage", e.getMessage( ));
            Page.ERROR.forward(request, response);
        } catch (Exception e) {
            throw new ServletException ("Failed to open new issue.", e);
        }
    }

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.sendRedirect(Page.HOME.getPath( ));
    }
}