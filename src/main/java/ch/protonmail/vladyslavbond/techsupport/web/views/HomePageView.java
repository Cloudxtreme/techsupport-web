package ch.protonmail.vladyslavbond.techsupport.web.views;

import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.Party;

import ch.protonmail.vladyslavbond.techsupport.web.controllers.IssueFilteringMode;

import java.util.ArrayList;
import java.util.List;

public final class HomePageView
extends AbstractView
implements View
{
    private final PageView pageView;

    public HomePageView (Party readingParty, List<Issue> listOfIssues, IssueFilteringMode modeSelected) throws ViewException
    {
        try
        {
            List<String> contentOfBody = new ArrayList<String> ( );
            contentOfBody.add("<aside>");
            FormIssueFilteringView formView = new FormIssueFilteringView (modeSelected);
            contentOfBody.add(NativeTemplate.getInstance("form_issue_open.html").bind("idOfPartyIssuedBy", readingParty.getId( ).toString( )).build( ));
            contentOfBody.add("</aside>");
            contentOfBody.add("<main>");
            contentOfBody.add(formView.getHTML( ));
            for (Issue issue : listOfIssues)
            {
                IssueView issueView = new IssueView (issue);
                contentOfBody.add(issueView.getHTML( ));
            }
            contentOfBody.add("</main>");
            this.pageView = new PageView("Home page.", contentOfBody);
        } catch (Exception e) {
            throw new ViewException (e);
        }
    }
    
    @Override
    public String getHTML ( )
    throws ViewException
    {
        return this.pageView.getHTML( );
    }
}
