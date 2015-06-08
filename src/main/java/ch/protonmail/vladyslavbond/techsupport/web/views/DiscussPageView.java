package ch.protonmail.vladyslavbond.techsupport.web.views;

import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueFactoryException;
import ch.protonmail.vladyslavbond.techsupport.domain.Party;
import ch.protonmail.vladyslavbond.techsupport.domain.PartyFactoryException;
import ch.protonmail.vladyslavbond.techsupport.domain.Factories;

import java.util.ArrayList;
import java.util.List;

public final class DiscussPageView
extends AbstractView
implements View
{
    private final String html;

    public DiscussPageView (Party readingParty, Issue issueToDiscuss) 
    throws ViewException
    {
        try
        {
            List<String> contentOfBody = new ArrayList<String> ( );
            contentOfBody.add("<aside>");
            contentOfBody.add
                (
                    NativeTemplate
                        .getInstance("form_issue_close.html")
                            .bind("action", Page.ISSUE_CLOSE.getPath( ))
                            .bind("idOfIssue", issueToDiscuss.getId( ).toString( ))
                            .bind("idOfPartyClosedBy", readingParty.getId( ).toString( ))
                            .build( )
                )
            ;
            StringBuilder stringBuilder = new StringBuilder ( );
            for (Party partyToAssignTo : Factories.getPartyFactory( ).getInstance(readingParty))
            {
                boolean isSelected = partyToAssignTo.getId( ).equals(readingParty.getId( ));
                FormSelectOptionView optionView = new FormSelectOptionView (partyToAssignTo, isSelected);
                stringBuilder.append(optionView.getHTML( ));
            }
            contentOfBody.add
                (
                    NativeTemplate
                        .getInstance("form_issue_assign.html")
                            .bind("action", Page.ISSUE_ASSIGN.getPath( ))
                            .bind("idOfIssue", issueToDiscuss.getId( ).toString( ))
                            .bind("idOfPartyAssignedBy", readingParty.getId( ).toString( ))
                            .bind("options", stringBuilder.toString( ))
                            .build( )
                )
            ;
            contentOfBody.add
                (
                    NativeTemplate
                        .getInstance("form_post_comment.html")
                            .bind("action", Page.COMMENT_NEW.getPath( ))
                            .bind("idOfIssue", issueToDiscuss.getId( ).toString( ))
                            .build( )
                )
            ;
            contentOfBody.add("</aside>");
            contentOfBody.add("<main>");
            CommentView commentView = new CommentView (issueToDiscuss.getDescription( ));
            contentOfBody.add(commentView.getHTML( ));
            contentOfBody.add("</main>");
            this.html = NativeTemplate.getInstance("page_standard.html")
                .bind("titleOfPage", "Discuss.")
                .bind("contentOfBody", contentOfBody)
                .build( );
        } catch (PartyFactoryException | TemplateParameterMissing | TemplateValueMissing | TemplateFileMissing e) {
            throw new ViewException (e);
        }
    }
    
    @Override
    public String getHTML ( )
    throws ViewException
    {
        return new String(this.html);
    }
}