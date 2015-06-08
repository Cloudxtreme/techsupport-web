package ch.protonmail.vladyslavbond.techsupport.web.servlets;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;
import ch.protonmail.vladyslavbond.techsupport.domain.Identificator;
import ch.protonmail.vladyslavbond.techsupport.domain.Issue;

import ch.protonmail.vladyslavbond.techsupport.web.controllers.*;
import ch.protonmail.vladyslavbond.techsupport.web.views.*;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class DiscussRequestHandler 
extends HttpServlet 
{
	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response)
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
				throw new InputInvalidException ("Id of issue to discuss is missing.");
			}
			IssueController issueController = Controllers.getIssueController( );
			Issue issueToDiscuss = issueController.discuss(readingParty, new Identificator<Issue> (idOfIssue));
			DiscussPageView discussPageView = new DiscussPageView (readingParty, issueToDiscuss);
			discussPageView.writeResponse(response);
		} catch (InputInvalidException e) {
			request.setAttribute("errorMessage", e.getMessage( ));
			Page.ERROR.forward(request, response);
		} catch (Exception e) {
			throw new ServletException ("Discuss request handler failed to process GET request.", e);
		}
	}
}