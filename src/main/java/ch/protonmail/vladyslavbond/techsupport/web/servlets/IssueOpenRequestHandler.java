package ch.protonmail.vladyslavbond.techsupport.web.servlets;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;
import ch.protonmail.vladyslavbond.techsupport.domain.Issue;

import ch.protonmail.vladyslavbond.techsupport.web.controllers.*;
import ch.protonmail.vladyslavbond.techsupport.web.views.*;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class IssueOpenRequestHandler
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
				response.sendRedirect(Page.LOG_IN.getPath( ));
			}
			String description = (String)request.getParameter("description");
			if (description == null || description.isEmpty( ))
			{
				description = "Descripton is missing.";
			}
			IssueController issueController = Controllers.getIssueController( );
			Issue issue = issueController.open(readingParty, description);
			if (issue == null)
			{
				throw new ServletException ("Failed to open new issue for unknown reason.");
			}
			PageView pageView = new PageView ("Success.", "<p>You have successfully opened new issue.</p>");
			pageView.writeResponse(response);
		} catch (Exception e) {
			throw new ServletException ("Failed to open new issue.", e);
		}
	}
}