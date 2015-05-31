package ch.protonmail.vladyslavbond.techsupport.web;

import ch.protonmail.vladyslavbond.techsupport.domain.Factories;
import ch.protonmail.vladyslavbond.techsupport.domain.Identificator;
import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueException;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueFactory;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueFactoryException;
import ch.protonmail.vladyslavbond.techsupport.domain.Party;
import ch.protonmail.vladyslavbond.techsupport.domain.PartyException;
import ch.protonmail.vladyslavbond.techsupport.domain.PartyFactory;
import ch.protonmail.vladyslavbond.techsupport.domain.PartyFactoryException;

import ch.protonmail.vladyslavbond.techsupport.web.views.*;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class RequestHandler extends HttpServlet
{
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        try
        {
            List<Party> parties = new ArrayList<Party> ( );
            int i = 2;
            PartyFactory partyFactory = Factories.getPartyFactory( );
            while (i > 0)
            {
                parties.add(partyFactory.getInstance(new Identificator<Party> (i)));
                i--;
            }
            Template partyAsSelectOptionTemplate = TemplateFactory.newInstance("party_as_select_option");
            String partiesAsOptionsOfSelection = "";
            for (Party party : parties)
            {
                partiesAsOptionsOfSelection += partyAsSelectOptionTemplate
                    .bind("id", party.getId( ).toString( ))
                    .bind("name", party.getName( ))
                    .build( );
            }

            Template pageTemplate = TemplateFactory.newInstance("form_issue_open");
            String buildedPage = pageTemplate
                .bind("partiesAsOptionsOfSelection", partiesAsOptionsOfSelection)
                .build( );
                
            response.setContentType("text/html");
            PrintWriter responseWriter = response.getWriter( );
            responseWriter.write(buildedPage);
        } catch (Exception e) {
            throw new ServletException ("Failure.", e);
        }
    }
}
