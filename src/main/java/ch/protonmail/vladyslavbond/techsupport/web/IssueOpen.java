package ch.protonmail.vladyslavbond.techsupport.web;

import ch.protonmail.vladyslavbond.techsupport.domain.Factories;
import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueFactory;
import ch.protonmail.vladyslavbond.techsupport.domain.Party;
import ch.protonmail.vladyslavbond.techsupport.domain.PartyFactory;
import ch.protonmail.vladyslavbond.techsupport.domain.Identificator;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class IssueOpen extends HttpServlet
{
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
	{
		try
		{
			Identificator<Party> idOfParty = new Identificator<Party> (request.getParameter("party_id"));
			if (idOfParty == null)
			{
				throw new RuntimeException ("Id of party is missing.");
			}
			PartyFactory partyFactory = Factories.getPartyFactory( );
			Party party = partyFactory.getInstance(idOfParty);
			String description = request.getParameter("description");
			if (description == null || description.isEmpty( ))
			{
				throw new RuntimeException ("Description of issue is missing.");
			}
			IssueFactory issueFactory = Factories.getIssueFactory( );
			Issue issue = issueFactory.getInstance(party, description);
			response.getWriter( ).write("<!DOCTYPE html><html lang='en'><head><title>Issue opening.</title></head><body><p>Id of your issue is #" + issue.getId( ).toString( ) + ".</p></body></html>");
		} catch (Exception e) {
			throw new ServletException ("Failed to process request.", e);
		}
	}
}
