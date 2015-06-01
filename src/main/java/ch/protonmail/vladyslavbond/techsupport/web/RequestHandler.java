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

import java.util.Set;
import java.util.HashSet;

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
            Set<Party> parties = new HashSet<Party> ( );
            int i = 2;
            PartyFactory partyFactory = Factories.getPartyFactory( );
            while (i > 0)
            {
                parties.add(partyFactory.getInstance(new Identificator<Party> (i)));
                i--;
            }
                
            HomePageView homePageView = new HomePageView (parties);
            response.setContentType("text/html");
            PrintWriter responseWriter = response.getWriter( );
            responseWriter.write(homePageView.getHTML( ));
        } catch (Exception e) {
            throw new ServletException ("Failure.", e);
        }
    }
}
