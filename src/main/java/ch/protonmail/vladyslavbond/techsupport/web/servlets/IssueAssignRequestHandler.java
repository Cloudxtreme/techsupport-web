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

public final class IssueAssignRequestHandler
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
            String idOfIssue = (String)request.getParameter("issue_id");
            if (idOfIssue == null || idOfIssue.isEmpty( ))
            {
                throw new InputInvalidException ("Id of issue to be assigned is missing.");
            }
            String idOfPartyAssigned = (String)request.getParameter("party_assigned_id");
            if (idOfPartyAssigned == null || idOfPartyAssigned.isEmpty( ))
            {
                throw new InputInvalidException ("Id of party to be assigned to the issue is missing.");
            }
            IssueController issueController = Controllers.getIssueController( );
            Issue issue = issueController.assign(readingParty, new Identificator<Party> (idOfPartyAssigned), new Identificator<Issue> (idOfIssue));
            if (issue == null)
            {
                throw new ServletException ("Failed to assign a party to an issue for unknown reason.");
            }
            PageView pageView = new PageView ("Success.", "<p>You have successfully assigned a party to an issue.</p>");
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
        Page.HOME.redirect(request, response);
    }
}